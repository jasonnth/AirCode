package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SesameCreditVerificationResponse extends BaseResponse {
    @JsonProperty("sesame")
    public SesameData sesame;

    static class SesameData {
        /* access modifiers changed from: private */
        @JsonProperty("redirectUrl")
        public String redirectUrl;
        /* access modifiers changed from: private */
        @JsonProperty("success")
        public boolean success;

        SesameData() {
        }
    }

    public String getUrl() {
        if (this.sesame.success) {
            return this.sesame.redirectUrl;
        }
        return null;
    }

    public boolean success() {
        return this.sesame.success;
    }
}
