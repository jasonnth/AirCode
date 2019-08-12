package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.MultipartRequest;
import com.airbnb.android.core.responses.OfficialIdUploadResponse;
import com.airbnb.android.utils.IOUtils;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Part;

public class OfficialIdUploadRequest extends MultipartRequest<OfficialIdUploadResponse> {
    private static final String BACK_ID_PARAM = "back_image";
    private static final String CALLER_PARAM = "caller";
    private static final String COUNTRY_PARAM = "country";
    private static final String FRONT_ID_PARAM = "front_image";
    private static final String ID_TYPE_PARAM = "idType";
    public static final String MISNAP_CALLER = "mitek_sdk";
    private final File backIdFile;
    private String caller;
    private final String country;
    private final File frontIdFile;
    private final String idType;

    public OfficialIdUploadRequest(File frontIdFile2, File backIdFile2, String country2, String idType2, BaseRequestListener<OfficialIdUploadResponse> listener) {
        withListener(listener);
        this.frontIdFile = frontIdFile2;
        this.backIdFile = backIdFile2;
        this.country = country2;
        this.idType = idType2;
    }

    public OfficialIdUploadRequest setCaller(String caller2) {
        this.caller = caller2;
        return this;
    }

    public String getPath() {
        return "official_id/upload";
    }

    public List<Part> getParts() {
        List<Part> parts = new ArrayList<>();
        parts.add(new Part(FRONT_ID_PARAM, RequestBody.create(IOUtils.getContentType(this.frontIdFile.getName()), this.frontIdFile), "binary", this.frontIdFile.getPath()));
        if (this.backIdFile != null) {
            parts.add(new Part(BACK_ID_PARAM, RequestBody.create(IOUtils.getContentType(this.backIdFile.getName()), this.backIdFile), "binary", this.backIdFile.getPath()));
        }
        parts.add(new Part("country", RequestBody.create(MediaType.parse("text/plain"), this.country)));
        parts.add(new Part(ID_TYPE_PARAM, RequestBody.create(MediaType.parse("text/plain"), this.idType)));
        if (!TextUtils.isEmpty(this.caller)) {
            parts.add(new Part(CALLER_PARAM, RequestBody.create(MediaType.parse("text/plain"), this.caller)));
        }
        return parts;
    }

    public Type successResponseType() {
        return OfficialIdUploadResponse.class;
    }
}
