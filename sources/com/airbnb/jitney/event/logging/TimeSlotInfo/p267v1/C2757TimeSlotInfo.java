package com.airbnb.jitney.event.logging.TimeSlotInfo.p267v1;

import com.airbnb.jitney.event.logging.TimeSlot.p266v1.C2755TimeSlot;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.TimeSlotInfo.v1.TimeSlotInfo */
public final class C2757TimeSlotInfo implements Struct {
    public static final Adapter<C2757TimeSlotInfo, Builder> ADAPTER = new TimeSlotInfoAdapter();
    public final List<C2755TimeSlot> all_available_time_slots;
    public final C2755TimeSlot selected_time_slot;
    public final Long selected_time_slot_index;

    /* renamed from: com.airbnb.jitney.event.logging.TimeSlotInfo.v1.TimeSlotInfo$Builder */
    public static final class Builder implements StructBuilder<C2757TimeSlotInfo> {
        /* access modifiers changed from: private */
        public List<C2755TimeSlot> all_available_time_slots;
        /* access modifiers changed from: private */
        public C2755TimeSlot selected_time_slot;
        /* access modifiers changed from: private */
        public Long selected_time_slot_index;

        private Builder() {
        }

        public Builder(List<C2755TimeSlot> all_available_time_slots2, C2755TimeSlot selected_time_slot2, Long selected_time_slot_index2) {
            this.all_available_time_slots = all_available_time_slots2;
            this.selected_time_slot = selected_time_slot2;
            this.selected_time_slot_index = selected_time_slot_index2;
        }

        public C2757TimeSlotInfo build() {
            if (this.all_available_time_slots == null) {
                throw new IllegalStateException("Required field 'all_available_time_slots' is missing");
            } else if (this.selected_time_slot == null) {
                throw new IllegalStateException("Required field 'selected_time_slot' is missing");
            } else if (this.selected_time_slot_index != null) {
                return new C2757TimeSlotInfo(this);
            } else {
                throw new IllegalStateException("Required field 'selected_time_slot_index' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.TimeSlotInfo.v1.TimeSlotInfo$TimeSlotInfoAdapter */
    private static final class TimeSlotInfoAdapter implements Adapter<C2757TimeSlotInfo, Builder> {
        private TimeSlotInfoAdapter() {
        }

        public void write(Protocol protocol, C2757TimeSlotInfo struct) throws IOException {
            protocol.writeStructBegin("TimeSlotInfo");
            protocol.writeFieldBegin("all_available_time_slots", 1, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.all_available_time_slots.size());
            for (C2755TimeSlot item0 : struct.all_available_time_slots) {
                C2755TimeSlot.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("selected_time_slot", 2, PassportService.SF_DG12);
            C2755TimeSlot.ADAPTER.write(protocol, struct.selected_time_slot);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("selected_time_slot_index", 3, 10);
            protocol.writeI64(struct.selected_time_slot_index.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2757TimeSlotInfo(Builder builder) {
        this.all_available_time_slots = Collections.unmodifiableList(builder.all_available_time_slots);
        this.selected_time_slot = builder.selected_time_slot;
        this.selected_time_slot_index = builder.selected_time_slot_index;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2757TimeSlotInfo)) {
            return false;
        }
        C2757TimeSlotInfo that = (C2757TimeSlotInfo) other;
        if ((this.all_available_time_slots == that.all_available_time_slots || this.all_available_time_slots.equals(that.all_available_time_slots)) && ((this.selected_time_slot == that.selected_time_slot || this.selected_time_slot.equals(that.selected_time_slot)) && (this.selected_time_slot_index == that.selected_time_slot_index || this.selected_time_slot_index.equals(that.selected_time_slot_index)))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ this.all_available_time_slots.hashCode()) * -2128831035) ^ this.selected_time_slot.hashCode()) * -2128831035) ^ this.selected_time_slot_index.hashCode()) * -2128831035;
    }

    public String toString() {
        return "TimeSlotInfo{all_available_time_slots=" + this.all_available_time_slots + ", selected_time_slot=" + this.selected_time_slot + ", selected_time_slot_index=" + this.selected_time_slot_index + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
