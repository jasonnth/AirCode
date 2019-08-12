package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.JumioCredential;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class JumioCredentialsResponse extends BaseResponse {
    @JsonProperty("jumio_credentials")
    protected List<JumioCredential> jumioCredentials;

    public JumioCredential getJumioCredential() {
        return (JumioCredential) this.jumioCredentials.get(0);
    }
}
