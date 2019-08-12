package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.airbnb.jitney.event.logging.core.request.p026v1.Request;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.P3EngagementEvent */
public final class P3EngagementEvent implements Struct {
    public static final Adapter<P3EngagementEvent, Builder> ADAPTER = new P3EngagementEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String p3_impression_id;
    public final PageNavigationAction page_navigation_action;
    public final Request request;
    public final ReviewsAction reviews_action;
    public final String schema;
    public final String search_ranking_id;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.P3EngagementEvent$Builder */
    public static final class Builder implements StructBuilder<P3EngagementEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "p3_engagement";
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String p3_impression_id;
        /* access modifiers changed from: private */
        public PageNavigationAction page_navigation_action;
        /* access modifiers changed from: private */
        public Request request;
        /* access modifiers changed from: private */
        public ReviewsAction reviews_action;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.P3:P3EngagementEvent:2.0.0";
        /* access modifiers changed from: private */
        public String search_ranking_id;

        private Builder() {
        }

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder p3_impression_id(String p3_impression_id2) {
            this.p3_impression_id = p3_impression_id2;
            return this;
        }

        public Builder search_ranking_id(String search_ranking_id2) {
            this.search_ranking_id = search_ranking_id2;
            return this;
        }

        public Builder page_navigation_action(PageNavigationAction page_navigation_action2) {
            this.page_navigation_action = page_navigation_action2;
            return this;
        }

        public Builder listing_id(Long listing_id2) {
            this.listing_id = listing_id2;
            return this;
        }

        public P3EngagementEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context != null) {
                return new P3EngagementEvent(this);
            } else {
                throw new IllegalStateException("Required field 'context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.P3EngagementEvent$P3EngagementEventAdapter */
    private static final class P3EngagementEventAdapter implements Adapter<P3EngagementEvent, Builder> {
        private P3EngagementEventAdapter() {
        }

        public void write(Protocol protocol, P3EngagementEvent struct) throws IOException {
            protocol.writeStructBegin("P3EngagementEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 2, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            if (struct.request != null) {
                protocol.writeFieldBegin(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, 3, PassportService.SF_DG12);
                Request.ADAPTER.write(protocol, struct.request);
                protocol.writeFieldEnd();
            }
            if (struct.p3_impression_id != null) {
                protocol.writeFieldBegin("p3_impression_id", 5, PassportService.SF_DG11);
                protocol.writeString(struct.p3_impression_id);
                protocol.writeFieldEnd();
            }
            if (struct.search_ranking_id != null) {
                protocol.writeFieldBegin("search_ranking_id", 6, PassportService.SF_DG11);
                protocol.writeString(struct.search_ranking_id);
                protocol.writeFieldEnd();
            }
            if (struct.page_navigation_action != null) {
                protocol.writeFieldBegin("page_navigation_action", 4, PassportService.SF_DG12);
                PageNavigationAction.ADAPTER.write(protocol, struct.page_navigation_action);
                protocol.writeFieldEnd();
            }
            if (struct.reviews_action != null) {
                protocol.writeFieldBegin("reviews_action", 7, PassportService.SF_DG12);
                ReviewsAction.ADAPTER.write(protocol, struct.reviews_action);
                protocol.writeFieldEnd();
            }
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 8, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private P3EngagementEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.request = builder.request;
        this.p3_impression_id = builder.p3_impression_id;
        this.search_ranking_id = builder.search_ranking_id;
        this.page_navigation_action = builder.page_navigation_action;
        this.reviews_action = builder.reviews_action;
        this.listing_id = builder.listing_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof P3EngagementEvent)) {
            return false;
        }
        P3EngagementEvent that = (P3EngagementEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.request == that.request || (this.request != null && this.request.equals(that.request))) && ((this.p3_impression_id == that.p3_impression_id || (this.p3_impression_id != null && this.p3_impression_id.equals(that.p3_impression_id))) && ((this.search_ranking_id == that.search_ranking_id || (this.search_ranking_id != null && this.search_ranking_id.equals(that.search_ranking_id))) && ((this.page_navigation_action == that.page_navigation_action || (this.page_navigation_action != null && this.page_navigation_action.equals(that.page_navigation_action))) && (this.reviews_action == that.reviews_action || (this.reviews_action != null && this.reviews_action.equals(that.reviews_action)))))))))) {
            if (this.listing_id == that.listing_id) {
                return true;
            }
            if (this.listing_id != null && this.listing_id.equals(that.listing_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ (this.request == null ? 0 : this.request.hashCode())) * -2128831035) ^ (this.p3_impression_id == null ? 0 : this.p3_impression_id.hashCode())) * -2128831035) ^ (this.search_ranking_id == null ? 0 : this.search_ranking_id.hashCode())) * -2128831035) ^ (this.page_navigation_action == null ? 0 : this.page_navigation_action.hashCode())) * -2128831035) ^ (this.reviews_action == null ? 0 : this.reviews_action.hashCode())) * -2128831035;
        if (this.listing_id != null) {
            i = this.listing_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "P3EngagementEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", request=" + this.request + ", p3_impression_id=" + this.p3_impression_id + ", search_ranking_id=" + this.search_ranking_id + ", page_navigation_action=" + this.page_navigation_action + ", reviews_action=" + this.reviews_action + ", listing_id=" + this.listing_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
