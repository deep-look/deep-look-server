package deeplook.server.domain.predict.exception;

import deeplook.server.global.exception.BaseErrorCode;
import deeplook.server.global.exception.BaseException;

public class PredictServerIntervalServerException extends BaseException {
    public PredictServerIntervalServerException() {
        super(PredictErrorCode.FLASK_SERVER_INTERNAL_SERVER_ERROR);
    }
}
