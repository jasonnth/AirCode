package com.airbnb.jitney.event.logging.Message.p021v1;

import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadTapImageEvent */
public final class MessageThreadTapImageEvent implements Struct {
    public static final Adapter<MessageThreadTapImageEvent, Builder> ADAPTER = new MessageThreadTapImageEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long message_thread_id;
    public final String operation;
    public final String page;
    public final Long post_id;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadTapImageEvent$Builder */
    public static final class Builder implements StructBuilder<MessageThreadTapImageEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long message_thread_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long post_id;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Message:MessageThreadTapImageEvent:1.0.0";
            this.event_name = "message_thread_tap_image";
            this.page = "message_thread";
            this.target = ContentFrameworkAnalytics.IMAGE;
            this.operation = "tap";
        }

        public Builder(Context context2, Long message_thread_id2, Long post_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Message:MessageThreadTapImageEvent:1.0.0";
            this.event_name = "message_thread_tap_image";
            this.context = context2;
            this.page = "message_thread";
            this.target = ContentFrameworkAnalytics.IMAGE;
            this.operation = "tap";
            this.message_thread_id = message_thread_id2;
            this.post_id = post_id2;
        }

        public MessageThreadTapImageEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.message_thread_id == null) {
                throw new IllegalStateException("Required field 'message_thread_id' is missing");
            } else if (this.post_id != null) {
                return new MessageThreadTapImageEvent(this);
            } else {
                throw new IllegalStateException("Required field 'post_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadTapImageEvent$MessageThreadTapImageEventAdapter */
    private static final class MessageThreadTapImageEventAdapter implements Adapter<MessageThreadTapImageEvent, Builder> {
        private MessageThreadTapImageEventAdapter() {
        }

        public void write(Protocol protocol, MessageThreadTapImageEvent struct) throws IOException {
            protocol.writeStructBegin("MessageThreadTapImageEvent");
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
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("message_thread_id", 6, 10);
            protocol.writeI64(struct.message_thread_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("post_id", 7, 10);
            protocol.writeI64(struct.post_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private MessageThreadTapImageEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.message_thread_id = builder.message_thread_id;
        this.post_id = builder.post_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof MessageThreadTapImageEvent)) {
            return false;
        }
        MessageThreadTapImageEvent that = (MessageThreadTapImageEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.message_thread_id == that.message_thread_id || this.message_thread_id.equals(that.message_thread_id)) && (this.post_id == that.post_id || this.post_id.equals(that.post_id))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.message_thread_id.hashCode()) * -2128831035) ^ this.post_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "MessageThreadTapImageEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", message_thread_id=" + this.message_thread_id + ", post_id=" + this.post_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
