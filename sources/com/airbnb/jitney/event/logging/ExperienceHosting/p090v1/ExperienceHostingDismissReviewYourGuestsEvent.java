package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.GuestReview.p108v1.C2186GuestReview;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingDismissReviewYourGuestsEvent */
public final class ExperienceHostingDismissReviewYourGuestsEvent implements Struct {
    public static final Adapter<ExperienceHostingDismissReviewYourGuestsEvent, Object> ADAPTER = new ExperienceHostingDismissReviewYourGuestsEventAdapter();
    public final Context context;
    public final String event_name;
    public final Map<Long, String> guest_descriptions;
    public final Map<Long, String> guest_labels;
    public final Map<Long, C2186GuestReview> guest_reviews;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingDismissReviewYourGuestsEvent$ExperienceHostingDismissReviewYourGuestsEventAdapter */
    private static final class ExperienceHostingDismissReviewYourGuestsEventAdapter implements Adapter<ExperienceHostingDismissReviewYourGuestsEvent, Object> {
        private ExperienceHostingDismissReviewYourGuestsEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingDismissReviewYourGuestsEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingDismissReviewYourGuestsEvent");
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
            protocol.writeFieldBegin("product_info", 5, PassportService.SF_DG12);
            C2587ProductInfo.ADAPTER.write(protocol, struct.product_info);
            protocol.writeFieldEnd();
            if (struct.guest_reviews != null) {
                protocol.writeFieldBegin("guest_reviews", 6, 13);
                protocol.writeMapBegin(10, 16, struct.guest_reviews.size());
                for (Entry<Long, C2186GuestReview> entry0 : struct.guest_reviews.entrySet()) {
                    C2186GuestReview value0 = (C2186GuestReview) entry0.getValue();
                    protocol.writeI64(((Long) entry0.getKey()).longValue());
                    protocol.writeI32(value0.value);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guest_labels != null) {
                protocol.writeFieldBegin("guest_labels", 7, 13);
                protocol.writeMapBegin(10, PassportService.SF_DG11, struct.guest_labels.size());
                for (Entry<Long, String> entry02 : struct.guest_labels.entrySet()) {
                    String value02 = (String) entry02.getValue();
                    protocol.writeI64(((Long) entry02.getKey()).longValue());
                    protocol.writeString(value02);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guest_descriptions != null) {
                protocol.writeFieldBegin("guest_descriptions", 8, 13);
                protocol.writeMapBegin(10, PassportService.SF_DG11, struct.guest_descriptions.size());
                for (Entry<Long, String> entry03 : struct.guest_descriptions.entrySet()) {
                    String value03 = (String) entry03.getValue();
                    protocol.writeI64(((Long) entry03.getKey()).longValue());
                    protocol.writeString(value03);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof ExperienceHostingDismissReviewYourGuestsEvent)) {
            return false;
        }
        ExperienceHostingDismissReviewYourGuestsEvent that = (ExperienceHostingDismissReviewYourGuestsEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.guest_reviews == that.guest_reviews || (this.guest_reviews != null && this.guest_reviews.equals(that.guest_reviews))) && (this.guest_labels == that.guest_labels || (this.guest_labels != null && this.guest_labels.equals(that.guest_labels)))))))))) {
            if (this.guest_descriptions == that.guest_descriptions) {
                return true;
            }
            if (this.guest_descriptions != null && this.guest_descriptions.equals(that.guest_descriptions)) {
                return true;
            }
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ (this.guest_reviews == null ? 0 : this.guest_reviews.hashCode())) * -2128831035) ^ (this.guest_labels == null ? 0 : this.guest_labels.hashCode())) * -2128831035;
        if (this.guest_descriptions != null) {
            i = this.guest_descriptions.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingDismissReviewYourGuestsEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", product_info=" + this.product_info + ", guest_reviews=" + this.guest_reviews + ", guest_labels=" + this.guest_labels + ", guest_descriptions=" + this.guest_descriptions + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
