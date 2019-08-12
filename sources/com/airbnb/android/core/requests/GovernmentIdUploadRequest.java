package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.MultipartRequestV2;
import com.airbnb.android.core.responses.GovernmentIdUploadResponse;
import com.airbnb.android.utils.IOUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.RequestBody;
import p032rx.Observer;
import retrofit2.Part;

public class GovernmentIdUploadRequest extends MultipartRequestV2<GovernmentIdUploadResponse> {
    private static final String PARAM_IMAGE_BACK = "image_back";
    private static final String PARAM_IMAGE_FRONT = "image_front";
    private File backImageFile;
    private final File frontImageFile;
    private final Body jsonRootBody = new Body();

    static class Body {
        @JsonProperty("barcode_data")
        String barcode;
        @JsonProperty("document_issuing_country")
        String country;
        @JsonProperty("document_type")
        String docType;
        @JsonProperty("document_issuing_state")
        String state;
        @JsonProperty("vendor")
        String vendor;

        Body() {
        }
    }

    public enum Vendor {
        Mitek,
        Jumio
    }

    public GovernmentIdUploadRequest(File frontIdFile, String country, String docType, Vendor vendor, BaseRequestListener<GovernmentIdUploadResponse> listener) {
        withListener((Observer) listener);
        this.jsonRootBody.docType = docType;
        this.jsonRootBody.country = country;
        this.jsonRootBody.vendor = vendor.name().toLowerCase();
        this.frontImageFile = frontIdFile;
    }

    public GovernmentIdUploadRequest setBackIdFile(File backFile) {
        this.backImageFile = backFile;
        return this;
    }

    public GovernmentIdUploadRequest setBarcode(String barCode) {
        this.jsonRootBody.barcode = barCode;
        return this;
    }

    public GovernmentIdUploadRequest setState(String state) {
        this.jsonRootBody.state = state;
        return this;
    }

    public String getPath() {
        return "government_id_results";
    }

    public List<Part> getParts() {
        List<Part> requestParts = new ArrayList<>();
        requestParts.add(new Part("json_root_body", this.jsonRootBody));
        requestParts.add(new Part(PARAM_IMAGE_FRONT, RequestBody.create(IOUtils.getContentType(this.frontImageFile.getName()), this.frontImageFile), "binary", this.frontImageFile.getPath()));
        if (this.backImageFile != null) {
            requestParts.add(new Part(PARAM_IMAGE_BACK, RequestBody.create(IOUtils.getContentType(this.backImageFile.getName()), this.backImageFile), "binary", this.backImageFile.getPath()));
        }
        return requestParts;
    }

    public Type successResponseType() {
        return GovernmentIdUploadResponse.class;
    }
}
