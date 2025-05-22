package umc.reviewinclass.apiPayload.exception.handler;

import umc.reviewinclass.apiPayload.code.BaseErrorCode;
import umc.reviewinclass.apiPayload.exception.GeneralException;

public class PlatformHandler extends GeneralException {
    public PlatformHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
