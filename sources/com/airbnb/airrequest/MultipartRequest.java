package com.airbnb.airrequest;

import com.airbnb.airrequest.AirRequest.RequestType;
import java.util.List;
import retrofit2.Part;

public abstract class MultipartRequest<T> extends BaseRequest<T> {
    public abstract List<Part> getParts();

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getContentType() {
        return null;
    }

    public RequestType getRequestType() {
        return RequestType.MULTIPART;
    }
}
