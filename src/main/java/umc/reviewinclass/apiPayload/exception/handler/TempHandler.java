package umc.reviewinclass.apiPayload.exception.handler;

import umc.reviewinclass.apiPayload.code.BaseErrorCode;
import umc.reviewinclass.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
