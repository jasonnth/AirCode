package com.alipay.sdk.app;

public class Result {
    static String getCancel() {
        ResultStatus status = ResultStatus.getResultState(ResultStatus.CANCELED.getStatus());
        return parseResult(status.getStatus(), status.getMemo(), "");
    }

    static String getError() {
        ResultStatus status = ResultStatus.getResultState(ResultStatus.FAILED.getStatus());
        return parseResult(status.getStatus(), status.getMemo(), "");
    }

    private static String parseResult(int resultStatus, String memo, String result) {
        StringBuilder sb = new StringBuilder();
        sb.append("resultStatus={").append(resultStatus).append("};memo={").append(memo).append("};result={").append(result).append("}");
        return sb.toString();
    }
}
