package com.airbnb.jitney.event.logging.Message.p021v1;

import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Message.v1.SendMessageEvent */
public final class SendMessageEvent implements Struct {
    public static final Adapter<SendMessageEvent, Builder> ADAPTER = new SendMessageEventAdapter();
    public final Long content_file_size;
    public final Context context;
    public final String event_name;
    public final String inbox_type;
    public final String message_content_type;
    public final Long message_thread_id;
    public final String schema;
    public final SendState send_state;
    public final String send_trigger;
    public final String unique_send_identifier;
    public final String user_role;

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.SendMessageEvent$Builder */
    public static final class Builder implements StructBuilder<SendMessageEvent> {
        /* access modifiers changed from: private */
        public Long content_file_size;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "send_message";
        /* access modifiers changed from: private */
        public String inbox_type;
        /* access modifiers changed from: private */
        public String message_content_type;
        /* access modifiers changed from: private */
        public Long message_thread_id;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Message:SendMessageEvent:1.0.0";
        /* access modifiers changed from: private */
        public SendState send_state;
        /* access modifiers changed from: private */
        public String send_trigger;
        /* access modifiers changed from: private */
        public String unique_send_identifier;
        /* access modifiers changed from: private */
        public String user_role;

        private Builder() {
        }

        public Builder(Context context2, String user_role2, SendState send_state2, String message_content_type2, String send_trigger2, String unique_send_identifier2, String inbox_type2, Long message_thread_id2) {
            this.context = context2;
            this.user_role = user_role2;
            this.send_state = send_state2;
            this.message_content_type = message_content_type2;
            this.send_trigger = send_trigger2;
            this.unique_send_identifier = unique_send_identifier2;
            this.inbox_type = inbox_type2;
            this.message_thread_id = message_thread_id2;
        }

        public SendMessageEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.user_role == null) {
                throw new IllegalStateException("Required field 'user_role' is missing");
            } else if (this.send_state == null) {
                throw new IllegalStateException("Required field 'send_state' is missing");
            } else if (this.message_content_type == null) {
                throw new IllegalStateException("Required field 'message_content_type' is missing");
            } else if (this.send_trigger == null) {
                throw new IllegalStateException("Required field 'send_trigger' is missing");
            } else if (this.unique_send_identifier == null) {
                throw new IllegalStateException("Required field 'unique_send_identifier' is missing");
            } else if (this.inbox_type == null) {
                throw new IllegalStateException("Required field 'inbox_type' is missing");
            } else if (this.message_thread_id != null) {
                return new SendMessageEvent(this);
            } else {
                throw new IllegalStateException("Required field 'message_thread_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.SendMessageEvent$SendMessageEventAdapter */
    private static final class SendMessageEventAdapter implements Adapter<SendMessageEvent, Builder> {
        private SendMessageEventAdapter() {
        }

        public void write(Protocol protocol, SendMessageEvent struct) throws IOException {
            protocol.writeStructBegin("SendMessageEvent");
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
            protocol.writeFieldBegin("user_role", 3, PassportService.SF_DG11);
            protocol.writeString(struct.user_role);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("send_state", 4, 8);
            protocol.writeI32(struct.send_state.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("message_content_type", 5, PassportService.SF_DG11);
            protocol.writeString(struct.message_content_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("send_trigger", 6, PassportService.SF_DG11);
            protocol.writeString(struct.send_trigger);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("unique_send_identifier", 7, PassportService.SF_DG11);
            protocol.writeString(struct.unique_send_identifier);
            protocol.writeFieldEnd();
            if (struct.content_file_size != null) {
                protocol.writeFieldBegin("content_file_size", 8, 10);
                protocol.writeI64(struct.content_file_size.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(ThreadFragmentIntents.ARG_INBOX_TYPE, 9, PassportService.SF_DG11);
            protocol.writeString(struct.inbox_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("message_thread_id", 10, 10);
            protocol.writeI64(struct.message_thread_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SendMessageEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.user_role = builder.user_role;
        this.send_state = builder.send_state;
        this.message_content_type = builder.message_content_type;
        this.send_trigger = builder.send_trigger;
        this.unique_send_identifier = builder.unique_send_identifier;
        this.content_file_size = builder.content_file_size;
        this.inbox_type = builder.inbox_type;
        this.message_thread_id = builder.message_thread_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SendMessageEvent)) {
            return false;
        }
        SendMessageEvent that = (SendMessageEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.user_role == that.user_role || this.user_role.equals(that.user_role)) && ((this.send_state == that.send_state || this.send_state.equals(that.send_state)) && ((this.message_content_type == that.message_content_type || this.message_content_type.equals(that.message_content_type)) && ((this.send_trigger == that.send_trigger || this.send_trigger.equals(that.send_trigger)) && ((this.unique_send_identifier == that.unique_send_identifier || this.unique_send_identifier.equals(that.unique_send_identifier)) && ((this.content_file_size == that.content_file_size || (this.content_file_size != null && this.content_file_size.equals(that.content_file_size))) && ((this.inbox_type == that.inbox_type || this.inbox_type.equals(that.inbox_type)) && (this.message_thread_id == that.message_thread_id || this.message_thread_id.equals(that.message_thread_id)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.user_role.hashCode()) * -2128831035) ^ this.send_state.hashCode()) * -2128831035) ^ this.message_content_type.hashCode()) * -2128831035) ^ this.send_trigger.hashCode()) * -2128831035) ^ this.unique_send_identifier.hashCode()) * -2128831035;
        if (this.content_file_size != null) {
            i = this.content_file_size.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.inbox_type.hashCode()) * -2128831035) ^ this.message_thread_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SendMessageEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", user_role=" + this.user_role + ", send_state=" + this.send_state + ", message_content_type=" + this.message_content_type + ", send_trigger=" + this.send_trigger + ", unique_send_identifier=" + this.unique_send_identifier + ", content_file_size=" + this.content_file_size + ", inbox_type=" + this.inbox_type + ", message_thread_id=" + this.message_thread_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
