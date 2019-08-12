package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class JumioScanReferenceUploadRequest extends BaseRequestV2<BaseResponse> {
    private static final int SCAN_SOURCE_SDK = 2;
    private static final int SOURCE_FLOW_IDENTITY = 3;
    private static final String VENDOR_JUMIO = "jumio";
    private final String scanReference;

    public JumioScanReferenceUploadRequest(String scanReference2) {
        this.scanReference = scanReference2;
    }

    public String getPath() {
        return "government_id_results";
    }

    public Strap getBody() {
        return Strap.make().mo11639kv("vendor_reference", this.scanReference).mo11639kv("vendor", VENDOR_JUMIO).mo11637kv("scan_source", 2).mo11637kv("source_flow", 3);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }
}
