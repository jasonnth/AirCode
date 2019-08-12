package com.airbnb.jitney.event.logging.Location.p138v3;

import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Location.v3.UserLocationUpdateEvent */
public final class UserLocationUpdateEvent implements Struct {
    public static final Adapter<UserLocationUpdateEvent, Object> ADAPTER = new UserLocationUpdateEventAdapter();
    public final Double altitude;
    public final Context context;
    public final String encrypted_payload;
    public final Double heading;
    public final Double horizontal_accuracy;
    public final String schema;
    public final Double speed;
    public final Long timestamp;
    public final Double vertical_accuracy;

    /* renamed from: com.airbnb.jitney.event.logging.Location.v3.UserLocationUpdateEvent$UserLocationUpdateEventAdapter */
    private static final class UserLocationUpdateEventAdapter implements Adapter<UserLocationUpdateEvent, Object> {
        private UserLocationUpdateEventAdapter() {
        }

        public void write(Protocol protocol, UserLocationUpdateEvent struct) throws IOException {
            protocol.writeStructBegin("UserLocationUpdateEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 1, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("encrypted_payload", 2, PassportService.SF_DG11);
            protocol.writeString(struct.encrypted_payload);
            protocol.writeFieldEnd();
            if (struct.speed != null) {
                protocol.writeFieldBegin("speed", 3, 4);
                protocol.writeDouble(struct.speed.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.heading != null) {
                protocol.writeFieldBegin("heading", 4, 4);
                protocol.writeDouble(struct.heading.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.altitude != null) {
                protocol.writeFieldBegin("altitude", 5, 4);
                protocol.writeDouble(struct.altitude.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.horizontal_accuracy != null) {
                protocol.writeFieldBegin("horizontal_accuracy", 6, 4);
                protocol.writeDouble(struct.horizontal_accuracy.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.vertical_accuracy != null) {
                protocol.writeFieldBegin("vertical_accuracy", 7, 4);
                protocol.writeDouble(struct.vertical_accuracy.doubleValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(ErfExperimentsModel.TIMESTAMP, 8, 10);
            protocol.writeI64(struct.timestamp.longValue());
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
        if (!(other instanceof UserLocationUpdateEvent)) {
            return false;
        }
        UserLocationUpdateEvent that = (UserLocationUpdateEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.context == that.context || this.context.equals(that.context)) && ((this.encrypted_payload == that.encrypted_payload || this.encrypted_payload.equals(that.encrypted_payload)) && ((this.speed == that.speed || (this.speed != null && this.speed.equals(that.speed))) && ((this.heading == that.heading || (this.heading != null && this.heading.equals(that.heading))) && ((this.altitude == that.altitude || (this.altitude != null && this.altitude.equals(that.altitude))) && ((this.horizontal_accuracy == that.horizontal_accuracy || (this.horizontal_accuracy != null && this.horizontal_accuracy.equals(that.horizontal_accuracy))) && ((this.vertical_accuracy == that.vertical_accuracy || (this.vertical_accuracy != null && this.vertical_accuracy.equals(that.vertical_accuracy))) && (this.timestamp == that.timestamp || this.timestamp.equals(that.timestamp)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.encrypted_payload.hashCode()) * -2128831035) ^ (this.speed == null ? 0 : this.speed.hashCode())) * -2128831035) ^ (this.heading == null ? 0 : this.heading.hashCode())) * -2128831035) ^ (this.altitude == null ? 0 : this.altitude.hashCode())) * -2128831035) ^ (this.horizontal_accuracy == null ? 0 : this.horizontal_accuracy.hashCode())) * -2128831035;
        if (this.vertical_accuracy != null) {
            i = this.vertical_accuracy.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.timestamp.hashCode()) * -2128831035;
    }

    public String toString() {
        return "UserLocationUpdateEvent{schema=" + this.schema + ", context=" + this.context + ", encrypted_payload=" + this.encrypted_payload + ", speed=" + this.speed + ", heading=" + this.heading + ", altitude=" + this.altitude + ", horizontal_accuracy=" + this.horizontal_accuracy + ", vertical_accuracy=" + this.vertical_accuracy + ", timestamp=" + this.timestamp + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
