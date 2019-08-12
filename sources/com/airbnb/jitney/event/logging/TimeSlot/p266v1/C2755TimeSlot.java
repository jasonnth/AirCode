package com.airbnb.jitney.event.logging.TimeSlot.p266v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TimeSlot.v1.TimeSlot */
public final class C2755TimeSlot implements Struct {
    public static final Adapter<C2755TimeSlot, Builder> ADAPTER = new TimeSlotAdapter();
    public final Boolean has_double_confirmation;
    public final String slot_text;
    public final String slot_time;

    /* renamed from: com.airbnb.jitney.event.logging.TimeSlot.v1.TimeSlot$Builder */
    public static final class Builder implements StructBuilder<C2755TimeSlot> {
        /* access modifiers changed from: private */
        public Boolean has_double_confirmation;
        /* access modifiers changed from: private */
        public String slot_text;
        /* access modifiers changed from: private */
        public String slot_time;

        private Builder() {
        }

        public Builder(String slot_time2, Boolean has_double_confirmation2) {
            this.slot_time = slot_time2;
            this.has_double_confirmation = has_double_confirmation2;
        }

        public C2755TimeSlot build() {
            if (this.slot_time == null) {
                throw new IllegalStateException("Required field 'slot_time' is missing");
            } else if (this.has_double_confirmation != null) {
                return new C2755TimeSlot(this);
            } else {
                throw new IllegalStateException("Required field 'has_double_confirmation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.TimeSlot.v1.TimeSlot$TimeSlotAdapter */
    private static final class TimeSlotAdapter implements Adapter<C2755TimeSlot, Builder> {
        private TimeSlotAdapter() {
        }

        public void write(Protocol protocol, C2755TimeSlot struct) throws IOException {
            protocol.writeStructBegin("TimeSlot");
            protocol.writeFieldBegin("slot_time", 1, PassportService.SF_DG11);
            protocol.writeString(struct.slot_time);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_double_confirmation", 2, 2);
            protocol.writeBool(struct.has_double_confirmation.booleanValue());
            protocol.writeFieldEnd();
            if (struct.slot_text != null) {
                protocol.writeFieldBegin("slot_text", 3, PassportService.SF_DG11);
                protocol.writeString(struct.slot_text);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2755TimeSlot(Builder builder) {
        this.slot_time = builder.slot_time;
        this.has_double_confirmation = builder.has_double_confirmation;
        this.slot_text = builder.slot_text;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2755TimeSlot)) {
            return false;
        }
        C2755TimeSlot that = (C2755TimeSlot) other;
        if ((this.slot_time == that.slot_time || this.slot_time.equals(that.slot_time)) && (this.has_double_confirmation == that.has_double_confirmation || this.has_double_confirmation.equals(that.has_double_confirmation))) {
            if (this.slot_text == that.slot_text) {
                return true;
            }
            if (this.slot_text != null && this.slot_text.equals(that.slot_text)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.slot_time.hashCode()) * -2128831035) ^ this.has_double_confirmation.hashCode()) * -2128831035) ^ (this.slot_text == null ? 0 : this.slot_text.hashCode())) * -2128831035;
    }

    public String toString() {
        return "TimeSlot{slot_time=" + this.slot_time + ", has_double_confirmation=" + this.has_double_confirmation + ", slot_text=" + this.slot_text + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
