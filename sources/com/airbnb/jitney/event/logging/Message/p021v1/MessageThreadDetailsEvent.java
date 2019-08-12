package com.airbnb.jitney.event.logging.Message.p021v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;
import org.spongycastle.i18n.ErrorBundle;

/* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadDetailsEvent */
public final class MessageThreadDetailsEvent implements Struct {
    public static final Adapter<MessageThreadDetailsEvent, Builder> ADAPTER = new MessageThreadDetailsEventAdapter();
    public final ActionType action_type;
    public final Context context;
    public final String event_name;
    public final String inbox_type;
    public final Long listing_id;
    public final Long message_thread_id;
    public final String operation;
    public final String page;
    public final String schema;
    public final String target;
    public final String user_role;

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadDetailsEvent$Builder */
    public static final class Builder implements StructBuilder<MessageThreadDetailsEvent> {
        /* access modifiers changed from: private */
        public ActionType action_type;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String inbox_type;
        /* access modifiers changed from: private */
        public Long listing_id;
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
            this.schema = "com.airbnb.jitney.event.logging.Message:MessageThreadDetailsEvent:1.0.0";
            this.event_name = "message_thread_details";
            this.page = "message_thread";
            this.target = ErrorBundle.DETAIL_ENTRY;
            this.operation = "click";
        }

        public Builder(Context context2, Long message_thread_id2, ActionType action_type2, String user_role2, String inbox_type2) {
            this.schema = "com.airbnb.jitney.event.logging.Message:MessageThreadDetailsEvent:1.0.0";
            this.event_name = "message_thread_details";
            this.context = context2;
            this.page = "message_thread";
            this.target = ErrorBundle.DETAIL_ENTRY;
            this.operation = "click";
            this.message_thread_id = message_thread_id2;
            this.action_type = action_type2;
            this.user_role = user_role2;
            this.inbox_type = inbox_type2;
        }

        public Builder listing_id(Long listing_id2) {
            this.listing_id = listing_id2;
            return this;
        }

        public MessageThreadDetailsEvent build() {
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
            } else if (this.action_type == null) {
                throw new IllegalStateException("Required field 'action_type' is missing");
            } else if (this.user_role == null) {
                throw new IllegalStateException("Required field 'user_role' is missing");
            } else if (this.inbox_type != null) {
                return new MessageThreadDetailsEvent(this);
            } else {
                throw new IllegalStateException("Required field 'inbox_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Message.v1.MessageThreadDetailsEvent$MessageThreadDetailsEventAdapter */
    private static final class MessageThreadDetailsEventAdapter implements Adapter<MessageThreadDetailsEvent, Builder> {
        private MessageThreadDetailsEventAdapter() {
        }

        public void write(Protocol protocol, MessageThreadDetailsEvent struct) throws IOException {
            protocol.writeStructBegin("MessageThreadDetailsEvent");
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
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 6, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("message_thread_id", 7, 10);
            protocol.writeI64(struct.message_thread_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, 8, 8);
            protocol.writeI32(struct.action_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_role", 9, PassportService.SF_DG11);
            protocol.writeString(struct.user_role);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ThreadFragmentIntents.ARG_INBOX_TYPE, 10, PassportService.SF_DG11);
            protocol.writeString(struct.inbox_type);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private MessageThreadDetailsEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.message_thread_id = builder.message_thread_id;
        this.action_type = builder.action_type;
        this.user_role = builder.user_role;
        this.inbox_type = builder.inbox_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof MessageThreadDetailsEvent)) {
            return false;
        }
        MessageThreadDetailsEvent that = (MessageThreadDetailsEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || (this.listing_id != null && this.listing_id.equals(that.listing_id))) && ((this.message_thread_id == that.message_thread_id || this.message_thread_id.equals(that.message_thread_id)) && ((this.action_type == that.action_type || this.action_type.equals(that.action_type)) && ((this.user_role == that.user_role || this.user_role.equals(that.user_role)) && (this.inbox_type == that.inbox_type || this.inbox_type.equals(that.inbox_type)))))))))))) {
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.listing_id != null) {
            i = this.listing_id.hashCode();
        }
        return (((((((((code ^ i) * -2128831035) ^ this.message_thread_id.hashCode()) * -2128831035) ^ this.action_type.hashCode()) * -2128831035) ^ this.user_role.hashCode()) * -2128831035) ^ this.inbox_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "MessageThreadDetailsEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", message_thread_id=" + this.message_thread_id + ", action_type=" + this.action_type + ", user_role=" + this.user_role + ", inbox_type=" + this.inbox_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
