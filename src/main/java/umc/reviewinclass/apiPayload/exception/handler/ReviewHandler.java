package umc.reviewinclass.apiPayload.exception.handler;

import umc.reviewinclass.apiPayload.code.BaseErrorCode;
import umc.reviewinclass.apiPayload.exception.GeneralException;

public class ReviewHandler extends GeneralException {
    public ReviewHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
