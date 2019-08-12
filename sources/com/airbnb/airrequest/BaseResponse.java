package com.airbnb.airrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
    @JsonProperty("error_code")
    public int errorCode;
    public Metadata metadata = Metadata.DEFAULT;
    @JsonProperty("result")
    private String result;

    public static class Metadata {
        public static final Metadata DEFAULT = new Metadata();
        private final boolean isCached;
        private final boolean isDouble;
        private final AirRequest request;

        public Metadata() {
            this.isDouble = false;
            this.isCached = false;
            this.request = null;
        }

        public Metadata(AirRequest request2, AirResponse<?> airResponse) {
            this.request = request2;
            this.isDouble = request2.isDoubleResponse();
            this.isCached = airResponse.raw().networkResponse() == null && airResponse.isSuccess();
        }

        public boolean isDouble() {
            return this.isDouble;
        }

        public boolean isCached() {
            return this.isCached;
        }

        public AirRequest request() {
            return this.request;
        }

        public String toString() {
            return "Metadata{isDouble=" + this.isDouble + ", isCached=" + this.isCached + '}';
        }
    }

    public boolean isOkay() {
        return this.errorCode == 0;
    }

    public boolean isSuccess() {
        return "success".equalsIgnoreCase(this.result);
    }

    public String toString() {
        return "BaseResponse{result='" + this.result + '\'' + ", errorCode=" + this.errorCode + ", metadata=" + this.metadata + '}';
    }
}
