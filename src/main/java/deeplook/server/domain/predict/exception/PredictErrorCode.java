package deeplook.server.domain.predict.exception;

import deeplook.server.global.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum PredictErrorCode implements BaseErrorCode {
    FLASK_SERVER_BAD_REQUEST(BAD_REQUEST, "FEIGN_400", "FLASK SERVER에 잘못된 요청을 보냈습니다."),
    PREDICT_NOT_FOUND(NOT_FOUND, "PREDIDCT_RESULT_404", "해당 유저가 검사한 기록이 존재하지 않습니다."),
    FLASK_SERVER_INTERNAL_SERVER_ERROR(BAD_REQUEST, "FEIGN_500", "검사 과정에서 오류가 발생하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
