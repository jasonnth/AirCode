package com.airbnb.jitney.event.logging.AutocompletionTuple.p039v2;

import com.facebook.react.uimanager.ViewProps;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.AutocompletionTuple.v2.AutocompletionTuple */
public final class C1801AutocompletionTuple implements Struct {
    public static final Adapter<C1801AutocompletionTuple, Object> ADAPTER = new AutocompletionTupleAdapter();
    public final String api_place_id;
    public final String location;
    public final Long position;
    public final String source;

    /* renamed from: com.airbnb.jitney.event.logging.AutocompletionTuple.v2.AutocompletionTuple$AutocompletionTupleAdapter */
    private static final class AutocompletionTupleAdapter implements Adapter<C1801AutocompletionTuple, Object> {
        private AutocompletionTupleAdapter() {
        }

        public void write(Protocol protocol, C1801AutocompletionTuple struct) throws IOException {
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
            if (struct.api_place_id != null) {
                protocol.writeFieldBegin("api_place_id", 4, PassportService.SF_DG11);
                protocol.writeString(struct.api_place_id);
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
        if (!(other instanceof C1801AutocompletionTuple)) {
            return false;
        }
        C1801AutocompletionTuple that = (C1801AutocompletionTuple) other;
        if ((this.location == that.location || this.location.equals(that.location)) && ((this.source == that.source || this.source.equals(that.source)) && (this.position == that.position || this.position.equals(that.position)))) {
            if (this.api_place_id == that.api_place_id) {
                return true;
            }
            if (this.api_place_id != null && this.api_place_id.equals(that.api_place_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((16777619 ^ this.location.hashCode()) * -2128831035) ^ this.source.hashCode()) * -2128831035) ^ this.position.hashCode()) * -2128831035) ^ (this.api_place_id == null ? 0 : this.api_place_id.hashCode())) * -2128831035;
    }

    public String toString() {
        return "AutocompletionTuple{location=" + this.location + ", source=" + this.source + ", position=" + this.position + ", api_place_id=" + this.api_place_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
