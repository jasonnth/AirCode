package com.airbnb.android.core.requests;

import com.airbnb.airrequest.MultipartRequestV2;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.responses.AccountVerificationSelfiePostResponse;
import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Part;

public class AccountVerificationSelfiePostRequest extends MultipartRequestV2<AccountVerificationSelfiePostResponse> {
    private final File file;
    private final String vendor;

    static class Body {
        @JsonProperty("vendor")
        String vendor;

        Body(String vendor2) {
            this.vendor = vendor2;
        }
    }

    public AccountVerificationSelfiePostRequest(File file2, String vendor2) {
        this.file = file2;
        this.vendor = vendor2;
    }

    public Type successResponseType() {
        return AccountVerificationSelfiePostResponse.class;
    }

    public String getPath() {
        return "user_selfies";
    }

    public List<Part> getParts() {
        return Arrays.asList(new Part[]{new Part(ContentFrameworkAnalytics.IMAGE, RequestBody.create(IOUtils.getContentType(this.file.getName()), this.file), "binary", this.file.getPath()), new Part("json_root_body", new Body(this.vendor))});
    }
}
