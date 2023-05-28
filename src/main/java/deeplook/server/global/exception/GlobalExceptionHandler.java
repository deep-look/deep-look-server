package deeplook.server.global.exception;

import deeplook.server.global.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /*
         javax.validation.Valid or @Validated 으로 binding error 발생시 발생
           주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException Error", e);
        return ErrorResponse.builder()
                .errorCode(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY)
                .build();
    }

    /*
      @ModelAttribute 으로 binding error 발생시 BindException 발생.
     */
    @ExceptionHandler(BindException.class)
    private ErrorResponse handleBindException(BindException e) {
        log.error("BindException Error", e);
        return ErrorResponse.builder()
                .errorCode(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY)
                .build();
    }

    /*
      enum type 일치하지 않아 binding 못할 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException Error", e);
        return ErrorResponse.builder()
                .errorCode(GlobalErrorCode.INVALID_HTTP_MESSAGE_BODY)
                .build();
    }

    /*
        지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException Error", e);
        return ErrorResponse.builder()
                .errorCode(GlobalErrorCode.UNSUPPORTED_HTTP_METHOD)
                .build();
    }
    /*
        request 값을 읽을 수 없을 때 발생
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException error", e);
        return ErrorResponse.builder()
                .errorCode(GlobalErrorCode.BAD_REQUEST_ERROR)
                .build();
    }

    /*
        비지니스 로직 에러
     */
    @ExceptionHandler(BaseException.class)
    private ErrorResponse handleBusinessException(BaseException e) {
        log.error("BusinessError ");
        log.error(e.getMessage(), e);
        return ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .build();
    }
    /*
        나머지 예외 처리
     */
    @ExceptionHandler(Exception.class)
    private ErrorResponse handleException(Exception e) {
        log.error("Exception Error ", e);
        return ErrorResponse.builder()
                .errorCode(GlobalErrorCode.SERVER_ERROR)
                .build();
    }

}
