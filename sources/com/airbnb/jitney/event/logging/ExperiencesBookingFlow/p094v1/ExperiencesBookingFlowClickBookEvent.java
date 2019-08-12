package com.airbnb.jitney.event.logging.ExperiencesBookingFlow.p094v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperiencesBookingFlow.v1.ExperiencesBookingFlowClickBookEvent */
public final class ExperiencesBookingFlowClickBookEvent implements Struct {
    public static final Adapter<ExperiencesBookingFlowClickBookEvent, Object> ADAPTER = new ExperiencesBookingFlowClickBookEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Double price;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.ExperiencesBookingFlow.v1.ExperiencesBookingFlowClickBookEvent$ExperiencesBookingFlowClickBookEventAdapter */
    private static final class ExperiencesBookingFlowClickBookEventAdapter implements Adapter<ExperiencesBookingFlowClickBookEvent, Object> {
        private ExperiencesBookingFlowClickBookEventAdapter() {
        }

        public void write(Protocol protocol, ExperiencesBookingFlowClickBookEvent struct) throws IOException {
            protocol.writeStructBegin("ExperiencesBookingFlowClickBookEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(EditPriceFragment.RESULT_PRICE, 6, 4);
            protocol.writeDouble(struct.price.doubleValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("product_info", 7, PassportService.SF_DG12);
            C2587ProductInfo.ADAPTER.write(protocol, struct.product_info);
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
        if (!(other instanceof ExperiencesBookingFlowClickBookEvent)) {
            return false;
        }
        ExperiencesBookingFlowClickBookEvent that = (ExperiencesBookingFlowClickBookEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.price == that.price || this.price.equals(that.price)) && (this.product_info == that.product_info || this.product_info.equals(that.product_info))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.price.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperiencesBookingFlowClickBookEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", target=" + this.target + ", price=" + this.price + ", product_info=" + this.product_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
