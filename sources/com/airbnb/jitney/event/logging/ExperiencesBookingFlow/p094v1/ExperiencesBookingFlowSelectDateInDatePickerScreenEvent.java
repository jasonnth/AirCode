package com.airbnb.jitney.event.logging.ExperiencesBookingFlow.p094v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.SpotsBooked.p260v1.C2746SpotsBooked;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperiencesBookingFlow.v1.ExperiencesBookingFlowSelectDateInDatePickerScreenEvent */
public final class ExperiencesBookingFlowSelectDateInDatePickerScreenEvent implements Struct {
    public static final Adapter<ExperiencesBookingFlowSelectDateInDatePickerScreenEvent, Object> ADAPTER = new ExperiencesBookingFlowSelectDateInDatePickerScreenEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final Double price;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final C2746SpotsBooked spots_booked;
    public final Long spots_left;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.ExperiencesBookingFlow.v1.ExperiencesBookingFlowSelectDateInDatePickerScreenEvent$ExperiencesBookingFlowSelectDateInDatePickerScreenEventAdapter */
    private static final class ExperiencesBookingFlowSelectDateInDatePickerScreenEventAdapter implements Adapter<ExperiencesBookingFlowSelectDateInDatePickerScreenEvent, Object> {
        private ExperiencesBookingFlowSelectDateInDatePickerScreenEventAdapter() {
        }

        public void write(Protocol protocol, ExperiencesBookingFlowSelectDateInDatePickerScreenEvent struct) throws IOException {
            protocol.writeStructBegin("ExperiencesBookingFlowSelectDateInDatePickerScreenEvent");
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
            protocol.writeFieldBegin("spots_booked", 8, 8);
            protocol.writeI32(struct.spots_booked.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 9, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
            for (String item0 : struct.dates) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("spots_left", 10, 10);
            protocol.writeI64(struct.spots_left.longValue());
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
        if (!(other instanceof ExperiencesBookingFlowSelectDateInDatePickerScreenEvent)) {
            return false;
        }
        ExperiencesBookingFlowSelectDateInDatePickerScreenEvent that = (ExperiencesBookingFlowSelectDateInDatePickerScreenEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.price == that.price || this.price.equals(that.price)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.spots_booked == that.spots_booked || this.spots_booked.equals(that.spots_booked)) && ((this.dates == that.dates || this.dates.equals(that.dates)) && (this.spots_left == that.spots_left || this.spots_left.equals(that.spots_left)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.price.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.spots_booked.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035) ^ this.spots_left.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperiencesBookingFlowSelectDateInDatePickerScreenEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", target=" + this.target + ", price=" + this.price + ", product_info=" + this.product_info + ", spots_booked=" + this.spots_booked + ", dates=" + this.dates + ", spots_left=" + this.spots_left + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
