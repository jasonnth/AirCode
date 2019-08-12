package com.airbnb.jitney.event.logging.core.HelperMessage.p299v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.core.HelperMessage.v1.HelperMessage */
public final class C2833HelperMessage implements Struct {
    public static final Adapter<C2833HelperMessage, Builder> ADAPTER = new HelperMessageAdapter();
    public final Boolean displayed;
    public final String location;
    public final String payload;
    public final String type;

    /* renamed from: com.airbnb.jitney.event.logging.core.HelperMessage.v1.HelperMessage$Builder */
    public static final class Builder implements StructBuilder<C2833HelperMessage> {
        /* access modifiers changed from: private */
        public Boolean displayed;
        /* access modifiers changed from: private */
        public String location;
        /* access modifiers changed from: private */
        public String payload;
        /* access modifiers changed from: private */
        public String type;

        private Builder() {
        }

        public Builder(String type2, String location2, Boolean displayed2, String payload2) {
            this.type = type2;
            this.location = location2;
            this.displayed = displayed2;
            this.payload = payload2;
        }

        public C2833HelperMessage build() {
            if (this.type == null) {
                throw new IllegalStateException("Required field 'type' is missing");
            } else if (this.location == null) {
                throw new IllegalStateException("Required field 'location' is missing");
            } else if (this.displayed == null) {
                throw new IllegalStateException("Required field 'displayed' is missing");
            } else if (this.payload != null) {
                return new C2833HelperMessage(this);
            } else {
                throw new IllegalStateException("Required field 'payload' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.core.HelperMessage.v1.HelperMessage$HelperMessageAdapter */
    private static final class HelperMessageAdapter implements Adapter<C2833HelperMessage, Builder> {
        private HelperMessageAdapter() {
        }

        public void write(Protocol protocol, C2833HelperMessage struct) throws IOException {
            protocol.writeStructBegin("HelperMessage");
            protocol.writeFieldBegin("type", 1, PassportService.SF_DG11);
            protocol.writeString(struct.type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("location", 2, PassportService.SF_DG11);
            protocol.writeString(struct.location);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("displayed", 3, 2);
            protocol.writeBool(struct.displayed.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("payload", 4, PassportService.SF_DG11);
            protocol.writeString(struct.payload);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2833HelperMessage(Builder builder) {
        this.type = builder.type;
        this.location = builder.location;
        this.displayed = builder.displayed;
        this.payload = builder.payload;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2833HelperMessage)) {
            return false;
        }
        C2833HelperMessage that = (C2833HelperMessage) other;
        if ((this.type == that.type || this.type.equals(that.type)) && ((this.location == that.location || this.location.equals(that.location)) && ((this.displayed == that.displayed || this.displayed.equals(that.displayed)) && (this.payload == that.payload || this.payload.equals(that.payload))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((16777619 ^ this.type.hashCode()) * -2128831035) ^ this.location.hashCode()) * -2128831035) ^ this.displayed.hashCode()) * -2128831035) ^ this.payload.hashCode()) * -2128831035;
    }

    public String toString() {
        return "HelperMessage{type=" + this.type + ", location=" + this.location + ", displayed=" + this.displayed + ", payload=" + this.payload + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
