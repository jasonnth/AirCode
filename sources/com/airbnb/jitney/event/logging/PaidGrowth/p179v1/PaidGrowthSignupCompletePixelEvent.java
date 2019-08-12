package com.airbnb.jitney.event.logging.PaidGrowth.p179v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v1.PaidGrowthSignupCompletePixelEvent */
public final class PaidGrowthSignupCompletePixelEvent implements Struct {
    public static final Adapter<PaidGrowthSignupCompletePixelEvent, Object> ADAPTER = new PaidGrowthSignupCompletePixelEventAdapter();
    public final Context context;
    public final String device_id;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.PaidGrowth.v1.PaidGrowthSignupCompletePixelEvent$PaidGrowthSignupCompletePixelEventAdapter */
    private static final class PaidGrowthSignupCompletePixelEventAdapter implements Adapter<PaidGrowthSignupCompletePixelEvent, Object> {
        private PaidGrowthSignupCompletePixelEventAdapter() {
        }

        public void write(Protocol protocol, PaidGrowthSignupCompletePixelEvent struct) throws IOException {
            protocol.writeStructBegin("PaidGrowthSignupCompletePixelEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("device_id", 4, PassportService.SF_DG11);
            protocol.writeString(struct.device_id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 5, 10);
            protocol.writeI64(struct.user_id.longValue());
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
        if (!(other instanceof PaidGrowthSignupCompletePixelEvent)) {
            return false;
        }
        PaidGrowthSignupCompletePixelEvent that = (PaidGrowthSignupCompletePixelEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.device_id == that.device_id || this.device_id.equals(that.device_id)) && (this.user_id == that.user_id || this.user_id.equals(that.user_id))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.device_id.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PaidGrowthSignupCompletePixelEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", device_id=" + this.device_id + ", user_id=" + this.user_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
