package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.MessageTranslationResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class MessageTranslationRequest extends BaseRequestV2<MessageTranslationResponse> {
    private String FORMAT = TimelineRequest.ARG_FORMAT;
    private String FORMAT_MESSAGE_TRANSLATION = "for_message_translation";
    private String TARGET_LANG = "target_lang";
    private String THREAD_ID = "thread_id";
    private String TIMESTAMP = "time_stamp";
    private String lastMessageTimeStamp;
    private String targetLocale;
    private long threadID;

    public MessageTranslationRequest(String targetLocale2, long threadID2, String lastMessageTimeStamp2) {
        this.targetLocale = targetLocale2;
        this.threadID = threadID2;
        this.lastMessageTimeStamp = lastMessageTimeStamp2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(this.FORMAT, this.FORMAT_MESSAGE_TRANSLATION).mo7895kv(this.TARGET_LANG, this.targetLocale).mo7894kv(this.THREAD_ID, this.threadID).mo7895kv(this.TIMESTAMP, this.lastMessageTimeStamp);
    }

    public String getPath() {
        return "messages";
    }

    public long getCacheOnlyTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Type successResponseType() {
        return MessageTranslationResponse.class;
    }
}
