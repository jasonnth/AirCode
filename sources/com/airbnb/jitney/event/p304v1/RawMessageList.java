package com.airbnb.jitney.event.p304v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.v1.RawMessageList */
public final class RawMessageList implements Struct {
    public static final Adapter<RawMessageList, Builder> ADAPTER = new RawMessageListAdapter();
    public final List<RawMessage> messages;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.v1.RawMessageList$Builder */
    public static final class Builder implements StructBuilder<RawMessageList> {
        /* access modifiers changed from: private */
        public List<RawMessage> messages;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event:RawMessageList:1.1.2";

        private Builder() {
        }

        public Builder(List<RawMessage> messages2) {
            this.messages = messages2;
        }

        public RawMessageList build() {
            if (this.messages != null) {
                return new RawMessageList(this);
            }
            throw new IllegalStateException("Required field 'messages' is missing");
        }
    }

    /* renamed from: com.airbnb.jitney.event.v1.RawMessageList$RawMessageListAdapter */
    private static final class RawMessageListAdapter implements Adapter<RawMessageList, Builder> {
        private RawMessageListAdapter() {
        }

        public void write(Protocol protocol, RawMessageList struct) throws IOException {
            protocol.writeStructBegin("RawMessageList");
            protocol.writeFieldBegin("messages", 1, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.messages.size());
            for (RawMessage item0 : struct.messages) {
                RawMessage.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private RawMessageList(Builder builder) {
        this.messages = Collections.unmodifiableList(builder.messages);
        this.schema = builder.schema;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof RawMessageList)) {
            return false;
        }
        RawMessageList that = (RawMessageList) other;
        if (this.messages == that.messages || this.messages.equals(that.messages)) {
            if (this.schema == that.schema) {
                return true;
            }
            if (this.schema != null && this.schema.equals(that.schema)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((16777619 ^ this.messages.hashCode()) * -2128831035) ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035;
    }

    public String toString() {
        return "RawMessageList{messages=" + this.messages + ", schema=" + this.schema + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
