package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SocialConnection;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class SocialConnectionsResponse extends BaseResponse {
    @JsonProperty("connections")
    public ArrayList<SocialConnection> connections;
    @JsonProperty("metadata")
    private MetaData metaData;
    public String targetUserName;

    private static class MetaData {
        /* access modifiers changed from: private */
        @JsonProperty("caption")
        public String caption;

        private MetaData() {
        }
    }

    public String getCaption() {
        if (this.metaData == null) {
            return "";
        }
        return this.metaData.caption;
    }

    public int getNumConnections() {
        if (this.connections == null) {
            return 0;
        }
        return this.connections.size();
    }
}
