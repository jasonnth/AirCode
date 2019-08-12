package com.airbnb.jitney.event.logging.AutocompletionTuple.p040v3;

import com.facebook.react.uimanager.ViewProps;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.AutocompletionTuple.v3.AutocompletionTuple */
public final class C1803AutocompletionTuple implements Struct {
    public static final Adapter<C1803AutocompletionTuple, Builder> ADAPTER = new AutocompletionTupleAdapter();
    public final String api_place_id;
    public final String filter_value;
    public final String location;
    public final Long position;
    public final String source;

    /* renamed from: com.airbnb.jitney.event.logging.AutocompletionTuple.v3.AutocompletionTuple$AutocompletionTupleAdapter */
    private static final class AutocompletionTupleAdapter implements Adapter<C1803AutocompletionTuple, Builder> {
        private AutocompletionTupleAdapter() {
        }

        public void write(Protocol protocol, C1803AutocompletionTuple struct) throws IOException {
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
            if (struct.filter_value != null) {
                protocol.writeFieldBegin("filter_value", 5, PassportService.SF_DG11);
                protocol.writeString(struct.filter_value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.AutocompletionTuple.v3.AutocompletionTuple$Builder */
    public static final class Builder implements StructBuilder<C1803AutocompletionTuple> {
        /* access modifiers changed from: private */
        public String api_place_id;
        /* access modifiers changed from: private */
        public String filter_value;
        /* access modifiers changed from: private */
        public String location;
        /* access modifiers changed from: private */
        public Long position;
        /* access modifiers changed from: private */
        public String source;

        private Builder() {
        }

        public Builder(String location2, String source2, Long position2) {
            this.location = location2;
            this.source = source2;
            this.position = position2;
        }

        public Builder api_place_id(String api_place_id2) {
            this.api_place_id = api_place_id2;
            return this;
        }

        public Builder filter_value(String filter_value2) {
            this.filter_value = filter_value2;
            return this;
        }

        public C1803AutocompletionTuple build() {
            if (this.location == null) {
                throw new IllegalStateException("Required field 'location' is missing");
            } else if (this.source == null) {
                throw new IllegalStateException("Required field 'source' is missing");
            } else if (this.position != null) {
                return new C1803AutocompletionTuple(this);
            } else {
                throw new IllegalStateException("Required field 'position' is missing");
            }
        }
    }

    private C1803AutocompletionTuple(Builder builder) {
        this.location = builder.location;
        this.source = builder.source;
        this.position = builder.position;
        this.api_place_id = builder.api_place_id;
        this.filter_value = builder.filter_value;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C1803AutocompletionTuple)) {
            return false;
        }
        C1803AutocompletionTuple that = (C1803AutocompletionTuple) other;
        if ((this.location == that.location || this.location.equals(that.location)) && ((this.source == that.source || this.source.equals(that.source)) && ((this.position == that.position || this.position.equals(that.position)) && (this.api_place_id == that.api_place_id || (this.api_place_id != null && this.api_place_id.equals(that.api_place_id)))))) {
            if (this.filter_value == that.filter_value) {
                return true;
            }
            if (this.filter_value != null && this.filter_value.equals(that.filter_value)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((16777619 ^ this.location.hashCode()) * -2128831035) ^ this.source.hashCode()) * -2128831035) ^ this.position.hashCode()) * -2128831035) ^ (this.api_place_id == null ? 0 : this.api_place_id.hashCode())) * -2128831035;
        if (this.filter_value != null) {
            i = this.filter_value.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "AutocompletionTuple{location=" + this.location + ", source=" + this.source + ", position=" + this.position + ", api_place_id=" + this.api_place_id + ", filter_value=" + this.filter_value + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
