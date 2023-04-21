package com.repill.was.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repill.was.global.shard.enums.Headers;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.global.shard.response.CommonResponse.ErrorType;
import com.repill.was.global.shard.response.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Slf4j
public class GlobalExceptionFilterHandler extends OncePerRequestFilter {

    private static final String JWT_SCHEME_FORMAT = "Bearer ";

    private String secretKey;

    public GlobalExceptionFilterHandler(@Value("${spring.jwt.secret}") String secretKey) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        MDC.put("traceId", UUID.randomUUID().toString());
        try {
            if (isAsyncDispatch(request)) {
                filterChain.doFilter(request, response);
            } else {
                doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response),
                        filterChain);
            }
            MDC.clear();
        } catch (ExpiredJwtException e) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e, ErrorCode.COMMON_ACCESS_TOKEN_EXPIRE);
        } catch (AuthenticationException | IllegalArgumentException e) {
            setErrorResponse(HttpStatus.FORBIDDEN, response, e, ErrorCode.COMMON_UNAUTHORIZED);
        } catch (BadRequestException | JwtException e) {
            setErrorResponse(HttpStatus.NOT_FOUND, response, e, ErrorCode.COMMON_SYSTEM_ERROR);
        }
    }

    protected void doFilterWrapped(RequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            logRequest(request);
            filterChain.doFilter(request, response);
        } finally {
            logResponse(response);
            response.copyBodyToResponse();
        }
    }
    private void logRequest(RequestWrapper request) throws IOException {
        String userId = null;
        String token = resolveToken(request);
        if(token != null) {
            token = token.replace(JWT_SCHEME_FORMAT, "");
            userId = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                    .getSubject();
        }
        String queryString = request.getQueryString();
        log.info("Request : {} uri=[{}] content-type=[{}] userId= {}",
                request.getMethod(),
                queryString == null ? request.getRequestURI() : request.getRequestURI() + queryString,
                request.getContentType(),
                userId
        );

        logPayload("Request", request.getContentType(), request.getInputStream());
    }

    private static String resolveToken(HttpServletRequest req) {
        return req.getHeader(Headers.AUTHORIZATION.getKey());
    }

    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
        logPayload("Response", response.getContentType(), response.getContentInputStream());
    }

    private static void logPayload(String prefix, String contentType, InputStream inputStream) throws IOException {
        boolean visible = isVisible(MediaType.valueOf(contentType == null ? "application/json" : contentType));
        if (visible) {
            byte[] content = StreamUtils.copyToByteArray(inputStream);
            if (content.length > 0) {
                String contentString = new String(content);
                log.info("{} Payload: {}", prefix, contentString);
            }
        } else {
            log.info("{} Payload: Binary Content", prefix);
        }
    }

    private static boolean isVisible(MediaType mediaType) {
        final List<MediaType> VISIBLE_TYPES = Arrays.asList(
                MediaType.valueOf("text/*"),
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.valueOf("application/*+json"),
                MediaType.valueOf("application/*+xml"),
                MediaType.MULTIPART_FORM_DATA
        );

        return VISIBLE_TYPES.stream()
                .anyMatch(visibleType -> visibleType.includes(mediaType));
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex, ErrorCode errorCode){
        response.setStatus(status.value());
        response.setContentType("application/json");
        CommonResponse<Object> fail = CommonResponse.fail(ex.getMessage(),
                errorCode.name(), ErrorType.ERROR);
        try{
            String result = mapper.writeValueAsString(fail);
            response.getWriter().write(result);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
