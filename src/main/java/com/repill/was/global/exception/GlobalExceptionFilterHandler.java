package com.repill.was.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.global.shard.response.CommonResponse.ErrorType;
import com.repill.was.global.shard.response.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalExceptionFilterHandler extends OncePerRequestFilter {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e, ErrorCode.COMMON_ACCESS_TOKEN_EXPIRE);
        } catch (AuthenticationException | IllegalArgumentException e) {
            setErrorResponse(HttpStatus.FORBIDDEN, response, e, ErrorCode.COMMON_UNAUTHORIZED);
        } catch (BadRequestException | JwtException e) {
            setErrorResponse(HttpStatus.NOT_FOUND, response, e, ErrorCode.COMMON_SYSTEM_ERROR);
        }
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
