package com.kakao;

public class KakaoParameterException extends Exception {
    private ERROR_CODE code = ERROR_CODE.UNKNOWN;

    public enum ERROR_CODE {
        UNKNOWN,
        CORE_PARAMETER_MISSING,
        MINIMUM_IMAGE_SIZE_REQUIRED,
        DUPLICATE_OBJECTS_USED,
        UNSUPPORTED_ENCODING,
        JSON_PARSING_ERROR,
        NOT_EXIST_IMAGE
    }

    public String getMessage() {
        return this.code != null ? this.code + ":" + super.getMessage() : super.getMessage();
    }

    KakaoParameterException(String message) {
        super(message);
    }

    public KakaoParameterException(ERROR_CODE code2, String e) {
        super(e);
        this.code = code2;
    }

    KakaoParameterException(ERROR_CODE code2, Exception e) {
        super(e);
        this.code = code2;
    }
}
