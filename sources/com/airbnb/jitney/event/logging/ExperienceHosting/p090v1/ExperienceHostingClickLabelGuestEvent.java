package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
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

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickLabelGuestEvent */
public final class ExperienceHostingClickLabelGuestEvent implements Struct {
    public static final Adapter<ExperienceHostingClickLabelGuestEvent, Object> ADAPTER = new ExperienceHostingClickLabelGuestEventAdapter();
    public final Context context;
    public final String event_name;
    public final Map<Long, String> guest_labels;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickLabelGuestEvent$ExperienceHostingClickLabelGuestEventAdapter */
    private static final class ExperienceHostingClickLabelGuestEventAdapter implements Adapter<ExperienceHostingClickLabelGuestEvent, Object> {
        private ExperienceHostingClickLabelGuestEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingClickLabelGuestEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingClickLabelGuestEvent");
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
            protocol.writeFieldBegin("guest_labels", 6, 13);
            protocol.writeMapBegin(10, PassportService.SF_DG11, struct.guest_labels.size());
            for (Entry<Long, String> entry0 : struct.guest_labels.entrySet()) {
                String value0 = (String) entry0.getValue();
                protocol.writeI64(((Long) entry0.getKey()).longValue());
                protocol.writeString(value0);
            }
            protocol.writeMapEnd();
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
        if (!(other instanceof ExperienceHostingClickLabelGuestEvent)) {
            return false;
        }
        ExperienceHostingClickLabelGuestEvent that = (ExperienceHostingClickLabelGuestEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && (this.guest_labels == that.guest_labels || this.guest_labels.equals(that.guest_labels)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.guest_labels.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingClickLabelGuestEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", product_info=" + this.product_info + ", guest_labels=" + this.guest_labels + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}