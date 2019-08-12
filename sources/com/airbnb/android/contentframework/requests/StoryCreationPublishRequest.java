package com.airbnb.android.contentframework.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.contentframework.responses.StoryCreationPublishResponse;
import java.lang.reflect.Type;

public class StoryCreationPublishRequest extends BaseRequestV2<StoryCreationPublishResponse> {
    public Type successResponseType() {
        return StoryCreationPublishResponse.class;
    }

    public String getPath() {
        return "content_framework_articles";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
