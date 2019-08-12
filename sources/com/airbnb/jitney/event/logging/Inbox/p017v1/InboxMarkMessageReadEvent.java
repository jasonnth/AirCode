package com.airbnb.jitney.event.logging.Inbox.p017v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Inbox.v1.InboxMarkMessageReadEvent */
public final class InboxMarkMessageReadEvent implements Struct {
    public static final Adapter<InboxMarkMessageReadEvent, Builder> ADAPTER = new InboxMarkMessageReadEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long message_thread_id;
    public final String operation;
    public final String page;
    public final String schema;
    public final String target;
    public final String user_role;

    /* renamed from: com.airbnb.jitney.event.logging.Inbox.v1.InboxMarkMessageReadEvent$Builder */
    public static final class Builder implements StructBuilder<InboxMarkMessageReadEvent> {
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
        public String schema;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public String user_role;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Inbox:InboxMarkMessageReadEvent:1.0.0";
            this.event_name = "inbox_mark_message_read";
            this.page = "inbox";
            this.target = "mark_message_read";
            this.operation = "click";
        }

        public Builder(Context context2, Long message_thread_id2, String user_role2) {
            this.schema = "com.airbnb.jitney.event.logging.Inbox:InboxMarkMessageReadEvent:1.0.0";
            this.event_name = "inbox_mark_message_read";
            this.context = context2;
            this.page = "inbox";
            this.target = "mark_message_read";
            this.operation = "click";
            this.message_thread_id = message_thread_id2;
            this.user_role = user_role2;
        }

        public InboxMarkMessageReadEvent build() {
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
            } else if (this.user_role != null) {
                return new InboxMarkMessageReadEvent(this);
            } else {
                throw new IllegalStateException("Required field 'user_role' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Inbox.v1.InboxMarkMessageReadEvent$InboxMarkMessageReadEventAdapter */
    private static final class InboxMarkMessageReadEventAdapter implements Adapter<InboxMarkMessageReadEvent, Builder> {
        private InboxMarkMessageReadEventAdapter() {
        }

        public void write(Protocol protocol, InboxMarkMessageReadEvent struct) throws IOException {
            protocol.writeStructBegin("InboxMarkMessageReadEvent");
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
            protocol.writeFieldBegin("user_role", 7, PassportService.SF_DG11);
            protocol.writeString(struct.user_role);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private InboxMarkMessageReadEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.message_thread_id = builder.message_thread_id;
        this.user_role = builder.user_role;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof InboxMarkMessageReadEvent)) {
            return false;
        }
        InboxMarkMessageReadEvent that = (InboxMarkMessageReadEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.message_thread_id == that.message_thread_id || this.message_thread_id.equals(that.message_thread_id)) && (this.user_role == that.user_role || this.user_role.equals(that.user_role))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.message_thread_id.hashCode()) * -2128831035) ^ this.user_role.hashCode()) * -2128831035;
    }

    public String toString() {
        return "InboxMarkMessageReadEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", message_thread_id=" + this.message_thread_id + ", user_role=" + this.user_role + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
