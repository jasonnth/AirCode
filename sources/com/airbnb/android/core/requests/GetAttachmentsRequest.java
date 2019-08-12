package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.responses.AttachmentsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetAttachmentsRequest extends BaseRequestV2<AttachmentsResponse> {
    private final long attachableId;
    private final String attachableType;

    private GetAttachmentsRequest(long attachableId2, String attachableType2) {
        this.attachableId = attachableId2;
        this.attachableType = attachableType2;
    }

    public static GetAttachmentsRequest forHelpThreadIssue(HelpThreadIssue issue) {
        return new GetAttachmentsRequest(issue.getId(), "help_threads");
    }

    public Type successResponseType() {
        return AttachmentsResponse.class;
    }

    public String getPath() {
        return "attachments";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("attachable_type", this.attachableType).mo7894kv("attachable_id", this.attachableId);
    }

    public long getCacheTimeoutMs() {
        return 2419200000L;
    }

    public long getAttachableId() {
        return this.attachableId;
    }
}
