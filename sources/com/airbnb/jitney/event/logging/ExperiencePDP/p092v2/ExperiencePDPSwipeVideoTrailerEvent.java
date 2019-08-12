package com.airbnb.jitney.event.logging.ExperiencePDP.p092v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Direction.p012v1.C0940Direction;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.SwipeMethod.p262v1.C2748SwipeMethod;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperiencePDP.v2.ExperiencePDPSwipeVideoTrailerEvent */
public final class ExperiencePDPSwipeVideoTrailerEvent implements Struct {
    public static final Adapter<ExperiencePDPSwipeVideoTrailerEvent, Object> ADAPTER = new ExperiencePDPSwipeVideoTrailerEventAdapter();
    public final Context context;
    public final C0940Direction direction;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final String section;
    public final C2748SwipeMethod swipe_method;
    public final String target;
    public final Double video_completion;
    public final Long video_id;
    public final Double video_length_seconds;

    /* renamed from: com.airbnb.jitney.event.logging.ExperiencePDP.v2.ExperiencePDPSwipeVideoTrailerEvent$ExperiencePDPSwipeVideoTrailerEventAdapter */
    private static final class ExperiencePDPSwipeVideoTrailerEventAdapter implements Adapter<ExperiencePDPSwipeVideoTrailerEvent, Object> {
        private ExperiencePDPSwipeVideoTrailerEventAdapter() {
        }

        public void write(Protocol protocol, ExperiencePDPSwipeVideoTrailerEvent struct) throws IOException {
            protocol.writeStructBegin("ExperiencePDPSwipeVideoTrailerEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 4, PassportService.SF_DG11);
            protocol.writeString(struct.section);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("product_info", 7, PassportService.SF_DG12);
            C2587ProductInfo.ADAPTER.write(protocol, struct.product_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("video_completion", 8, 4);
            protocol.writeDouble(struct.video_completion.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("video_id", 9, 10);
            protocol.writeI64(struct.video_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("swipe_method", 10, 8);
            protocol.writeI32(struct.swipe_method.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("direction", 11, 8);
            protocol.writeI32(struct.direction.value);
            protocol.writeFieldEnd();
            if (struct.video_length_seconds != null) {
                protocol.writeFieldBegin("video_length_seconds", 12, 4);
                protocol.writeDouble(struct.video_length_seconds.doubleValue());
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
        if (!(other instanceof ExperiencePDPSwipeVideoTrailerEvent)) {
            return false;
        }
        ExperiencePDPSwipeVideoTrailerEvent that = (ExperiencePDPSwipeVideoTrailerEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.video_completion == that.video_completion || this.video_completion.equals(that.video_completion)) && ((this.video_id == that.video_id || this.video_id.equals(that.video_id)) && ((this.swipe_method == that.swipe_method || this.swipe_method.equals(that.swipe_method)) && (this.direction == that.direction || this.direction.equals(that.direction))))))))))))) {
            if (this.video_length_seconds == that.video_length_seconds) {
                return true;
            }
            if (this.video_length_seconds != null && this.video_length_seconds.equals(that.video_length_seconds)) {
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
        int code = (((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.video_completion.hashCode()) * -2128831035) ^ this.video_id.hashCode()) * -2128831035) ^ this.swipe_method.hashCode()) * -2128831035) ^ this.direction.hashCode()) * -2128831035;
        if (this.video_length_seconds != null) {
            i = this.video_length_seconds.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExperiencePDPSwipeVideoTrailerEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", operation=" + this.operation + ", product_info=" + this.product_info + ", video_completion=" + this.video_completion + ", video_id=" + this.video_id + ", swipe_method=" + this.swipe_method + ", direction=" + this.direction + ", video_length_seconds=" + this.video_length_seconds + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
