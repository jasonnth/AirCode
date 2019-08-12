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

/* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadFetchImageEvent */
public final class MessageThreadFetchImageEvent implements Struct {
    public static final Adapter<MessageThreadFetchImageEvent, Builder> ADAPTER = new MessageThreadFetchImageEventAdapter();
    public final Context context;
    public final String event_name;
    public final Boolean fetch_success;
    public final Long image_height;
    public final Long image_size;
    public final Long image_width;
    public final Long message_thread_id;
    public final String operation;
    public final String page;
    public final Long post_id;
    public final String schema;
    public final Long seconds_to_load;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadFetchImageEvent$Builder */
    public static final class Builder implements StructBuilder<MessageThreadFetchImageEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Boolean fetch_success;
        /* access modifiers changed from: private */
        public Long image_height;
        /* access modifiers changed from: private */
        public Long image_size;
        /* access modifiers changed from: private */
        public Long image_width;
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
        public Long seconds_to_load;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Message:MessageThreadFetchImageEvent:1.0.0";
            this.event_name = "message_thread_fetch_image";
            this.page = "message_thread";
            this.target = ContentFrameworkAnalytics.IMAGE;
            this.operation = "fetch";
        }

        public Builder(Context context2, Long message_thread_id2, Long post_id2, Boolean fetch_success2, Long image_size2, Long seconds_to_load2) {
            this.schema = "com.airbnb.jitney.event.logging.Message:MessageThreadFetchImageEvent:1.0.0";
            this.event_name = "message_thread_fetch_image";
            this.context = context2;
            this.page = "message_thread";
            this.target = ContentFrameworkAnalytics.IMAGE;
            this.operation = "fetch";
            this.message_thread_id = message_thread_id2;
            this.post_id = post_id2;
            this.fetch_success = fetch_success2;
            this.image_size = image_size2;
            this.seconds_to_load = seconds_to_load2;
        }

        public MessageThreadFetchImageEvent build() {
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
            } else if (this.post_id == null) {
                throw new IllegalStateException("Required field 'post_id' is missing");
            } else if (this.fetch_success == null) {
                throw new IllegalStateException("Required field 'fetch_success' is missing");
            } else if (this.image_size == null) {
                throw new IllegalStateException("Required field 'image_size' is missing");
            } else if (this.seconds_to_load != null) {
                return new MessageThreadFetchImageEvent(this);
            } else {
                throw new IllegalStateException("Required field 'seconds_to_load' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadFetchImageEvent$MessageThreadFetchImageEventAdapter */
    private static final class MessageThreadFetchImageEventAdapter implements Adapter<MessageThreadFetchImageEvent, Builder> {
        private MessageThreadFetchImageEventAdapter() {
        }

        public void write(Protocol protocol, MessageThreadFetchImageEvent struct) throws IOException {
            protocol.writeStructBegin("MessageThreadFetchImageEvent");
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
            protocol.writeFieldBegin("fetch_success", 8, 2);
            protocol.writeBool(struct.fetch_success.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("image_size", 9, 10);
            protocol.writeI64(struct.image_size.longValue());
            protocol.writeFieldEnd();
            if (struct.image_height != null) {
                protocol.writeFieldBegin("image_height", 10, 10);
                protocol.writeI64(struct.image_height.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.image_width != null) {
                protocol.writeFieldBegin("image_width", 11, 10);
                protocol.writeI64(struct.image_width.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("seconds_to_load", 12, 10);
            protocol.writeI64(struct.seconds_to_load.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private MessageThreadFetchImageEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.message_thread_id = builder.message_thread_id;
        this.post_id = builder.post_id;
        this.fetch_success = builder.fetch_success;
        this.image_size = builder.image_size;
        this.image_height = builder.image_height;
        this.image_width = builder.image_width;
        this.seconds_to_load = builder.seconds_to_load;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof MessageThreadFetchImageEvent)) {
            return false;
        }
        MessageThreadFetchImageEvent that = (MessageThreadFetchImageEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.message_thread_id == that.message_thread_id || this.message_thread_id.equals(that.message_thread_id)) && ((this.post_id == that.post_id || this.post_id.equals(that.post_id)) && ((this.fetch_success == that.fetch_success || this.fetch_success.equals(that.fetch_success)) && ((this.image_size == that.image_size || this.image_size.equals(that.image_size)) && ((this.image_height == that.image_height || (this.image_height != null && this.image_height.equals(that.image_height))) && ((this.image_width == that.image_width || (this.image_width != null && this.image_width.equals(that.image_width))) && (this.seconds_to_load == that.seconds_to_load || this.seconds_to_load.equals(that.seconds_to_load)))))))))))))) {
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
        int code = (((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.message_thread_id.hashCode()) * -2128831035) ^ this.post_id.hashCode()) * -2128831035) ^ this.fetch_success.hashCode()) * -2128831035) ^ this.image_size.hashCode()) * -2128831035) ^ (this.image_height == null ? 0 : this.image_height.hashCode())) * -2128831035;
        if (this.image_width != null) {
            i = this.image_width.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.seconds_to_load.hashCode()) * -2128831035;
    }

    public String toString() {
        return "MessageThreadFetchImageEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", message_thread_id=" + this.message_thread_id + ", post_id=" + this.post_id + ", fetch_success=" + this.fetch_success + ", image_size=" + this.image_size + ", image_height=" + this.image_height + ", image_width=" + this.image_width + ", seconds_to_load=" + this.seconds_to_load + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
