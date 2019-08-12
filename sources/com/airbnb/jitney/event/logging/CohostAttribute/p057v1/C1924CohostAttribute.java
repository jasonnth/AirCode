package com.airbnb.jitney.event.logging.CohostAttribute.p057v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.CohostAttribute.v1.CohostAttribute */
public final class C1924CohostAttribute implements Struct {
    public static final Adapter<C1924CohostAttribute, Object> ADAPTER = new CohostAttributeAdapter();
    public final Long cohost_id;
    public final Boolean contract_cleaning_fee;
    public final Long contract_percent;
    public final String contract_start_date;
    public final Boolean is_active;

    /* renamed from: com.airbnb.jitney.event.logging.CohostAttribute.v1.CohostAttribute$CohostAttributeAdapter */
    private static final class CohostAttributeAdapter implements Adapter<C1924CohostAttribute, Object> {
        private CohostAttributeAdapter() {
        }

        public void write(Protocol protocol, C1924CohostAttribute struct) throws IOException {
            protocol.writeStructBegin("CohostAttribute");
            protocol.writeFieldBegin("cohost_id", 1, 10);
            protocol.writeI64(struct.cohost_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("contract_percent", 2, 10);
            protocol.writeI64(struct.contract_percent.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("contract_cleaning_fee", 3, 2);
            protocol.writeBool(struct.contract_cleaning_fee.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("contract_start_date", 4, PassportService.SF_DG11);
            protocol.writeString(struct.contract_start_date);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_active", 5, 2);
            protocol.writeBool(struct.is_active.booleanValue());
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
        if (!(other instanceof C1924CohostAttribute)) {
            return false;
        }
        C1924CohostAttribute that = (C1924CohostAttribute) other;
        if ((this.cohost_id == that.cohost_id || this.cohost_id.equals(that.cohost_id)) && ((this.contract_percent == that.contract_percent || this.contract_percent.equals(that.contract_percent)) && ((this.contract_cleaning_fee == that.contract_cleaning_fee || this.contract_cleaning_fee.equals(that.contract_cleaning_fee)) && ((this.contract_start_date == that.contract_start_date || this.contract_start_date.equals(that.contract_start_date)) && (this.is_active == that.is_active || this.is_active.equals(that.is_active)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ this.cohost_id.hashCode()) * -2128831035) ^ this.contract_percent.hashCode()) * -2128831035) ^ this.contract_cleaning_fee.hashCode()) * -2128831035) ^ this.contract_start_date.hashCode()) * -2128831035) ^ this.is_active.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostAttribute{cohost_id=" + this.cohost_id + ", contract_percent=" + this.contract_percent + ", contract_cleaning_fee=" + this.contract_cleaning_fee + ", contract_start_date=" + this.contract_start_date + ", is_active=" + this.is_active + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
