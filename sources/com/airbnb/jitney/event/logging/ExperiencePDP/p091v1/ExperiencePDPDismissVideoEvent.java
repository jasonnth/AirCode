package com.airbnb.jitney.event.logging.ExperiencePDP.p091v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperiencePDP.v1.ExperiencePDPDismissVideoEvent */
public final class ExperiencePDPDismissVideoEvent implements Struct {
    public static final Adapter<ExperiencePDPDismissVideoEvent, Object> ADAPTER = new ExperiencePDPDismissVideoEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final Double video_completion;
    public final Long video_id;

    /* renamed from: com.airbnb.jitney.event.logging.ExperiencePDP.v1.ExperiencePDPDismissVideoEvent$ExperiencePDPDismissVideoEventAdapter */
    private static final class ExperiencePDPDismissVideoEventAdapter implements Adapter<ExperiencePDPDismissVideoEvent, Object> {
        private ExperiencePDPDismissVideoEventAdapter() {
        }

        public void write(Protocol protocol, ExperiencePDPDismissVideoEvent struct) throws IOException {
            protocol.writeStructBegin("ExperiencePDPDismissVideoEvent");
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
            protocol.writeFieldBegin("video_completion", 6, 4);
            protocol.writeDouble(struct.video_completion.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("video_id", 7, 10);
            protocol.writeI64(struct.video_id.longValue());
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
        if (!(other instanceof ExperiencePDPDismissVideoEvent)) {
            return false;
        }
        ExperiencePDPDismissVideoEvent that = (ExperiencePDPDismissVideoEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.video_completion == that.video_completion || this.video_completion.equals(that.video_completion)) && (this.video_id == that.video_id || this.video_id.equals(that.video_id))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.video_completion.hashCode()) * -2128831035) ^ this.video_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperiencePDPDismissVideoEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", product_info=" + this.product_info + ", video_completion=" + this.video_completion + ", video_id=" + this.video_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
