package deeplook.server.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    public String getCode();
    public String getMessage();
    public HttpStatus getHttpStatus();
}
