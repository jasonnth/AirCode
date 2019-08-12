package com.alipay.sdk.app;

public enum ResultStatus {
    SUCCEEDED(9000, "授权成功"),
    FAILED(4000, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(4001, "参数错误"),
    PAY_WAITTING(8000, "授权结果确认中");
    
    private String memo;
    private int status;

    private ResultStatus(int status2, String memo2) {
        this.status = status2;
        this.memo = memo2;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMemo() {
        return this.memo;
    }

    public static ResultStatus getResultState(int status2) {
        switch (status2) {
            case 4001:
                return PARAMS_ERROR;
            case 6001:
                return CANCELED;
            case 6002:
                return NETWORK_ERROR;
            case 8000:
                return PAY_WAITTING;
            case 9000:
                return SUCCEEDED;
            default:
                return FAILED;
        }
    }
}
