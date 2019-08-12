package com.airbnb.jitney.event.logging.p167P3.p168v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.P3.v2.UserScrollData */
public final class UserScrollData implements Struct {
    public static final Adapter<UserScrollData, Object> ADAPTER = new UserScrollDataAdapter();
    public final Integer document_height;
    public final Integer increments;
    public final Integer scroll_position;

    /* renamed from: com.airbnb.jitney.event.logging.P3.v2.UserScrollData$UserScrollDataAdapter */
    private static final class UserScrollDataAdapter implements Adapter<UserScrollData, Object> {
        private UserScrollDataAdapter() {
        }

        public void write(Protocol protocol, UserScrollData struct) throws IOException {
            protocol.writeStructBegin("UserScrollData");
            if (struct.increments != null) {
                protocol.writeFieldBegin("increments", 1, 8);
                protocol.writeI32(struct.increments.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.scroll_position != null) {
                protocol.writeFieldBegin("scroll_position", 2, 8);
                protocol.writeI32(struct.scroll_position.intValue());
                protocol.writeFieldEnd();
            }
            if (struct.document_height != null) {
                protocol.writeFieldBegin("document_height", 3, 8);
                protocol.writeI32(struct.document_height.intValue());
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
        if (!(other instanceof UserScrollData)) {
            return false;
        }
        UserScrollData that = (UserScrollData) other;
        if ((this.increments == that.increments || (this.increments != null && this.increments.equals(that.increments))) && (this.scroll_position == that.scroll_position || (this.scroll_position != null && this.scroll_position.equals(that.scroll_position)))) {
            if (this.document_height == that.document_height) {
                return true;
            }
            if (this.document_height != null && this.document_height.equals(that.document_height)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((16777619 ^ (this.increments == null ? 0 : this.increments.hashCode())) * -2128831035) ^ (this.scroll_position == null ? 0 : this.scroll_position.hashCode())) * -2128831035;
        if (this.document_height != null) {
            i = this.document_height.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "UserScrollData{increments=" + this.increments + ", scroll_position=" + this.scroll_position + ", document_height=" + this.document_height + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
