package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.airrequest.Transformer;
import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.android.core.analytics.MessagingAnalytics;
import com.airbnb.android.core.analytics.MessagingAnalytics.Action;
import com.airbnb.android.core.responses.CreateMessageResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateMessageRequest extends BaseRequestV2<CreateMessageResponse> {
    protected final String message;
    protected final long threadId;

    public static class TransformerFactory implements Factory {
        public Transformer<CreateMessageResponse> transformerFor(AirRequest request, AirRequestInitializer initializer) {
            if (request instanceof CreateMessageRequest) {
                return MessagingAnalytics.instrument(Action.Send);
            }
            return null;
        }
    }

    public static CreateMessageRequest create(long threadId2, String message2) {
        return new CreateMessageRequest(threadId2, message2);
    }

    protected CreateMessageRequest(long threadId2, String message2) {
        this.threadId = threadId2;
        this.message = message2;
    }

    public String getPath() {
        return "messages";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getBody() {
        Strap params = Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "for_threads").mo11638kv("thread_id", this.threadId).mo11639kv("message", this.message);
        try {
            JSONObject jsonObject = new JSONObject();
            for (String property : params.keySet()) {
                jsonObject.put(property, params.get(property));
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    public Type successResponseType() {
        return CreateMessageResponse.class;
    }

    public AirResponse<CreateMessageResponse> transformResponse(AirResponse<CreateMessageResponse> response) {
        return super.transformResponse(response);
    }
}
