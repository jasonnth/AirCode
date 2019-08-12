package com.airbnb.jitney.event.logging.PaidAmenitiesOrderContext.p178v1;

import com.airbnb.android.lib.fragments.SeasonalCalendarRuleRangeSelectorDialog;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidAmenitiesOrderContext.v1.PaidAmenitiesOrderContext */
public final class C2493PaidAmenitiesOrderContext implements Struct {
    public static final Adapter<C2493PaidAmenitiesOrderContext, Builder> ADAPTER = new PaidAmenitiesOrderContextAdapter();
    public final String end_date;
    public final Long paid_amenity_id;
    public final Long paid_amenity_order_id;
    public final Long paid_amenity_order_number_of_units;
    public final Long reservation_id;
    public final String start_date;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenitiesOrderContext.v1.PaidAmenitiesOrderContext$Builder */
    public static final class Builder implements StructBuilder<C2493PaidAmenitiesOrderContext> {
        /* access modifiers changed from: private */
        public String end_date;
        /* access modifiers changed from: private */
        public Long paid_amenity_id;
        /* access modifiers changed from: private */
        public Long paid_amenity_order_id;
        /* access modifiers changed from: private */
        public Long paid_amenity_order_number_of_units;
        /* access modifiers changed from: private */
        public Long reservation_id;
        /* access modifiers changed from: private */
        public String start_date;
        /* access modifiers changed from: private */
        public Long user_id;

        public C2493PaidAmenitiesOrderContext build() {
            return new C2493PaidAmenitiesOrderContext(this);
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.PaidAmenitiesOrderContext.v1.PaidAmenitiesOrderContext$PaidAmenitiesOrderContextAdapter */
    private static final class PaidAmenitiesOrderContextAdapter implements Adapter<C2493PaidAmenitiesOrderContext, Builder> {
        private PaidAmenitiesOrderContextAdapter() {
        }

        public void write(Protocol protocol, C2493PaidAmenitiesOrderContext struct) throws IOException {
            protocol.writeStructBegin("PaidAmenitiesOrderContext");
            if (struct.paid_amenity_order_id != null) {
                protocol.writeFieldBegin("paid_amenity_order_id", 1, 10);
                protocol.writeI64(struct.paid_amenity_order_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.paid_amenity_id != null) {
                protocol.writeFieldBegin("paid_amenity_id", 2, 10);
                protocol.writeI64(struct.paid_amenity_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.paid_amenity_order_number_of_units != null) {
                protocol.writeFieldBegin("paid_amenity_order_number_of_units", 3, 10);
                protocol.writeI64(struct.paid_amenity_order_number_of_units.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.start_date != null) {
                protocol.writeFieldBegin(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_START_DATE, 4, PassportService.SF_DG11);
                protocol.writeString(struct.start_date);
                protocol.writeFieldEnd();
            }
            if (struct.end_date != null) {
                protocol.writeFieldBegin(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_END_DATE, 5, PassportService.SF_DG11);
                protocol.writeString(struct.end_date);
                protocol.writeFieldEnd();
            }
            if (struct.user_id != null) {
                protocol.writeFieldBegin("user_id", 6, 10);
                protocol.writeI64(struct.user_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.reservation_id != null) {
                protocol.writeFieldBegin("reservation_id", 7, 10);
                protocol.writeI64(struct.reservation_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2493PaidAmenitiesOrderContext(Builder builder) {
        this.paid_amenity_order_id = builder.paid_amenity_order_id;
        this.paid_amenity_id = builder.paid_amenity_id;
        this.paid_amenity_order_number_of_units = builder.paid_amenity_order_number_of_units;
        this.start_date = builder.start_date;
        this.end_date = builder.end_date;
        this.user_id = builder.user_id;
        this.reservation_id = builder.reservation_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2493PaidAmenitiesOrderContext)) {
            return false;
        }
        C2493PaidAmenitiesOrderContext that = (C2493PaidAmenitiesOrderContext) other;
        if ((this.paid_amenity_order_id == that.paid_amenity_order_id || (this.paid_amenity_order_id != null && this.paid_amenity_order_id.equals(that.paid_amenity_order_id))) && ((this.paid_amenity_id == that.paid_amenity_id || (this.paid_amenity_id != null && this.paid_amenity_id.equals(that.paid_amenity_id))) && ((this.paid_amenity_order_number_of_units == that.paid_amenity_order_number_of_units || (this.paid_amenity_order_number_of_units != null && this.paid_amenity_order_number_of_units.equals(that.paid_amenity_order_number_of_units))) && ((this.start_date == that.start_date || (this.start_date != null && this.start_date.equals(that.start_date))) && ((this.end_date == that.end_date || (this.end_date != null && this.end_date.equals(that.end_date))) && (this.user_id == that.user_id || (this.user_id != null && this.user_id.equals(that.user_id)))))))) {
            if (this.reservation_id == that.reservation_id) {
                return true;
            }
            if (this.reservation_id != null && this.reservation_id.equals(that.reservation_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((16777619 ^ (this.paid_amenity_order_id == null ? 0 : this.paid_amenity_order_id.hashCode())) * -2128831035) ^ (this.paid_amenity_id == null ? 0 : this.paid_amenity_id.hashCode())) * -2128831035) ^ (this.paid_amenity_order_number_of_units == null ? 0 : this.paid_amenity_order_number_of_units.hashCode())) * -2128831035) ^ (this.start_date == null ? 0 : this.start_date.hashCode())) * -2128831035) ^ (this.end_date == null ? 0 : this.end_date.hashCode())) * -2128831035) ^ (this.user_id == null ? 0 : this.user_id.hashCode())) * -2128831035;
        if (this.reservation_id != null) {
            i = this.reservation_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PaidAmenitiesOrderContext{paid_amenity_order_id=" + this.paid_amenity_order_id + ", paid_amenity_id=" + this.paid_amenity_id + ", paid_amenity_order_number_of_units=" + this.paid_amenity_order_number_of_units + ", start_date=" + this.start_date + ", end_date=" + this.end_date + ", user_id=" + this.user_id + ", reservation_id=" + this.reservation_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
