package deeplook.server.global.common.response;

import deeplook.server.global.exception.BaseErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ErrorResponse extends BaseResponse {
    private final HttpStatus httpStatus;

    @Builder
    public ErrorResponse(BaseErrorCode errorCode) {
        super(false, errorCode.getCode(), errorCode.getMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }
}
