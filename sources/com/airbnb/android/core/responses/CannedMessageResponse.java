package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.TemplateMessage;
import com.airbnb.android.core.requests.AirRequestFactory.Provider;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.List;

public class CannedMessageResponse extends BaseResponse implements Provider<TemplateMessage> {
    @JsonProperty("template_messages")
    public List<TemplateMessage> templateMessages;

    public Collection<TemplateMessage> provide() {
        return this.templateMessages;
    }
}
