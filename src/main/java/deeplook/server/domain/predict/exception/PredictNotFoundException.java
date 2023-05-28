package deeplook.server.domain.predict.exception;

import deeplook.server.global.exception.BaseErrorCode;
import deeplook.server.global.exception.BaseException;

public class PredictNotFoundException extends BaseException {
    public PredictNotFoundException() {
        super(PredictErrorCode.PREDICT_NOT_FOUND);
    }
}
