package deeplook.server.domain.predict.exception;

import deeplook.server.global.exception.BaseException;

public class PredictServerBadRequestException extends BaseException {

    public PredictServerBadRequestException() {
        super(PredictErrorCode.FLASK_SERVER_BAD_REQUEST);
    }
}
