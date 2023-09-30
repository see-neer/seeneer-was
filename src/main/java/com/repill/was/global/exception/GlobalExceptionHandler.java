package com.repill.was.global.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.repill.was.global.response.CommonResponse;
import com.repill.was.global.response.ErrorCode;
import com.repill.was.global.slack.SlackApRestTemplateImpl;
import com.repill.was.global.slack.SlackApiWebClientImpl;
import com.repill.was.global.slack.SlackService;
import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final SlackService slackService;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    public enum ExceptionErrorType {
        HARD, SOFT
    }

    // 예상하지 못한 error는 공통 Error Message
    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<Object> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        sendErrorMessageToSlack(exception, activeProfile, ExceptionErrorType.HARD);
        return CommonResponse.fail(exception.getMessage(), ErrorCode.COMMON_SYSTEM_ERROR.name(), CommonResponse.ErrorType.ERROR);
    }

    private void sendErrorMessageToSlack(Exception e, String activeProfile, ExceptionErrorType type) {
        String traceId = Span.current().getSpanContext().getTraceId();
        String time = LocalDateTime.now().toString();
        String message = "";
        if(activeProfile.equals("prod") && ExceptionErrorType.HARD.equals(type)) {
            message = "<!subteam^S032ZFBSD7F>\n";
        }
        message += "*환경 : [ " + activeProfile + " ] * \n";
        message += "*TraceId : " + traceId + "* \n";
        message += "*Time : " + time + "* \n";
        message += "*Message : " + e.getMessage() + "* \n";
        message += getStackTrace(e);
        //Webclient
        slackService.sendSlackMessage("{\"text\": \"" + message + "\"}", new SlackApiWebClientImpl());
        //RestTemplate
        slackService.sendSlackMessage("{\"text\": \"" + message + "\"}", new SlackApRestTemplateImpl());
    }

    private static String getStackTrace(Exception e) {
        String trace = "";
        String trace2 = "";
        trace2 += "```" + "\n";
        for (StackTraceElement log : e.getStackTrace()) {
            trace += log + "\n";
        }
        String substring = trace.substring(0, 1500);
        trace2 += substring;
        trace2 += "```";
        return trace2;
    }

    // 비즈니스 로직상 예상 할 수 있는 에러는 커스터마이징 Error Message
    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<Object> BusinessException(Exception exception) {
        log.error(exception.getMessage(), exception);
        sendErrorMessageToSlack(exception, activeProfile, ExceptionErrorType.SOFT);
        return CommonResponse.fail(exception.getMessage(), ErrorCode.COMMON_ILLEGAL_STATUS.name(), CommonResponse.ErrorType.WARNING);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected CommonResponse<Object> handle(MethodArgumentNotValidException exception)
            throws JsonProcessingException {
        return CommonResponse.fail(CommonResponse.Error.of(exception.getBindingResult()));
    }

    // 잘못된 parameter를 보낼 때
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            RequestRejectedException.class,
            MissingRequestHeaderException.class,
            HttpMessageNotReadableException.class,
            TypeMismatchException.class
    })
    protected CommonResponse<Object> handle(Exception exception) {
        log.error(exception.getMessage(), exception);
        return CommonResponse.fail(exception.getMessage(), ErrorCode.COMMON_INVALID_PARAMETER.name(), CommonResponse.ErrorType.ERROR);
    }

    // 인증값이 없을 때
    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResponse<Object> handleAuthenticationException(AuthenticationException exception) {
        return CommonResponse.fail(exception.getMessage(), ErrorCode.COMMON_UNAUTHORIZED.name(), CommonResponse.ErrorType.ERROR);
    }

    // 권한에 맞지 않는 호출이 발생 할 때
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected CommonResponse<Object> handleAccessDeniedException(AccessDeniedException exception) {
        return CommonResponse.fail(ErrorCode.COMMON_FORBIDDEN.getErrorMsg(), ErrorCode.COMMON_FORBIDDEN.name(), CommonResponse.ErrorType.ERROR);
    }
}

