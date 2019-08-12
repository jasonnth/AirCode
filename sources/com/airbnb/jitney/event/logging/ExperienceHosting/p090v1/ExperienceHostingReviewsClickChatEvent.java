package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingReviewsClickChatEvent */
public final class ExperienceHostingReviewsClickChatEvent implements Struct {
    public static final Adapter<ExperienceHostingReviewsClickChatEvent, Object> ADAPTER = new ExperienceHostingReviewsClickChatEventAdapter();
    public final String confirmation_code;
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final Long review_rating;
    public final String schema;
    public final String uuid;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingReviewsClickChatEvent$ExperienceHostingReviewsClickChatEventAdapter */
    private static final class ExperienceHostingReviewsClickChatEventAdapter implements Adapter<ExperienceHostingReviewsClickChatEvent, Object> {
        private ExperienceHostingReviewsClickChatEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingReviewsClickChatEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingReviewsClickChatEvent");
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
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 5, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
            for (String item0 : struct.dates) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("uuid", 6, PassportService.SF_DG11);
            protocol.writeString(struct.uuid);
            protocol.writeFieldEnd();
            if (struct.review_rating != null) {
                protocol.writeFieldBegin("review_rating", 7, 10);
                protocol.writeI64(struct.review_rating.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("product_info", 8, PassportService.SF_DG12);
            C2587ProductInfo.ADAPTER.write(protocol, struct.product_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("confirmation_code", 9, PassportService.SF_DG11);
            protocol.writeString(struct.confirmation_code);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExperienceHostingReviewsClickChatEvent)) {
            return false;
        }
        ExperienceHostingReviewsClickChatEvent that = (ExperienceHostingReviewsClickChatEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.dates == that.dates || this.dates.equals(that.dates)) && ((this.uuid == that.uuid || this.uuid.equals(that.uuid)) && ((this.review_rating == that.review_rating || (this.review_rating != null && this.review_rating.equals(that.review_rating))) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && (this.confirmation_code == that.confirmation_code || this.confirmation_code.equals(that.confirmation_code))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035) ^ this.uuid.hashCode()) * -2128831035;
        if (this.review_rating != null) {
            i = this.review_rating.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.confirmation_code.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingReviewsClickChatEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", dates=" + this.dates + ", uuid=" + this.uuid + ", review_rating=" + this.review_rating + ", product_info=" + this.product_info + ", confirmation_code=" + this.confirmation_code + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
