package com.airbnb.jitney.event.logging.Places.p202v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Places.v1.PlacesViewFeedbackEvent */
public final class PlacesViewFeedbackEvent implements Struct {
    public static final Adapter<PlacesViewFeedbackEvent, Object> ADAPTER = new PlacesViewFeedbackEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long mt_product_id;
    public final C2444MtProduct mt_product_type;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Places.v1.PlacesViewFeedbackEvent$PlacesViewFeedbackEventAdapter */
    private static final class PlacesViewFeedbackEventAdapter implements Adapter<PlacesViewFeedbackEvent, Object> {
        private PlacesViewFeedbackEventAdapter() {
        }

        public void write(Protocol protocol, PlacesViewFeedbackEvent struct) throws IOException {
            protocol.writeStructBegin("PlacesViewFeedbackEvent");
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
            protocol.writeFieldBegin("mt_product_type", 5, 8);
            protocol.writeI32(struct.mt_product_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_product_id", 6, 10);
            protocol.writeI64(struct.mt_product_id.longValue());
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
        if (!(other instanceof PlacesViewFeedbackEvent)) {
            return false;
        }
        PlacesViewFeedbackEvent that = (PlacesViewFeedbackEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.mt_product_type == that.mt_product_type || this.mt_product_type.equals(that.mt_product_type)) && (this.mt_product_id == that.mt_product_id || this.mt_product_id.equals(that.mt_product_id)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.mt_product_type.hashCode()) * -2128831035) ^ this.mt_product_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PlacesViewFeedbackEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", mt_product_type=" + this.mt_product_type + ", mt_product_id=" + this.mt_product_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
