package com.airbnb.jitney.event.logging.AutocompletionTuple.p038v1;

import com.facebook.react.uimanager.ViewProps;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.AutocompletionTuple.v1.AutocompletionTuple */
public final class C1799AutocompletionTuple implements Struct {
    public static final Adapter<C1799AutocompletionTuple, Object> ADAPTER = new AutocompletionTupleAdapter();
    public final String location;
    public final Long place_id;
    public final Long position;
    public final String source;

    /* renamed from: com.airbnb.jitney.event.logging.AutocompletionTuple.v1.AutocompletionTuple$AutocompletionTupleAdapter */
    private static final class AutocompletionTupleAdapter implements Adapter<C1799AutocompletionTuple, Object> {
        private AutocompletionTupleAdapter() {
        }

        public void write(Protocol protocol, C1799AutocompletionTuple struct) throws IOException {
            protocol.writeStructBegin("AutocompletionTuple");
            protocol.writeFieldBegin("location", 1, PassportService.SF_DG11);
            protocol.writeString(struct.location);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source", 2, PassportService.SF_DG11);
            protocol.writeString(struct.source);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ViewProps.POSITION, 3, 10);
            protocol.writeI64(struct.position.longValue());
            protocol.writeFieldEnd();
            if (struct.place_id != null) {
                protocol.writeFieldBegin("place_id", 4, 10);
                protocol.writeI64(struct.place_id.longValue());
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof C1799AutocompletionTuple)) {
            return false;
        }
        C1799AutocompletionTuple that = (C1799AutocompletionTuple) other;
        if ((this.location == that.location || this.location.equals(that.location)) && ((this.source == that.source || this.source.equals(that.source)) && (this.position == that.position || this.position.equals(that.position)))) {
            if (this.place_id == that.place_id) {
                return true;
            }
            if (this.place_id != null && this.place_id.equals(that.place_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((16777619 ^ this.location.hashCode()) * -2128831035) ^ this.source.hashCode()) * -2128831035) ^ this.position.hashCode()) * -2128831035) ^ (this.place_id == null ? 0 : this.place_id.hashCode())) * -2128831035;
    }

    public String toString() {
        return "AutocompletionTuple{location=" + this.location + ", source=" + this.source + ", position=" + this.position + ", place_id=" + this.place_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
