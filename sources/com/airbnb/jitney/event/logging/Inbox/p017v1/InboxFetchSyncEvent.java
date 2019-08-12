package com.airbnb.jitney.event.logging.Inbox.p017v1;

import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.airbnb.jitney.event.logging.core.request.p026v1.RequestState;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Inbox.v1.InboxFetchSyncEvent */
public final class InboxFetchSyncEvent implements Struct {
    public static final Adapter<InboxFetchSyncEvent, Builder> ADAPTER = new InboxFetchSyncEventAdapter();
    public final Context context;
    public final String event_name;
    public final String inbox_type;
    public final String page;
    public final RequestState request_state;
    public final String schema;
    public final String user_role;

    /* renamed from: com.airbnb.jitney.event.logging.Inbox.v1.InboxFetchSyncEvent$Builder */
    public static final class Builder implements StructBuilder<InboxFetchSyncEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String inbox_type;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public RequestState request_state;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String user_role;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Inbox:InboxFetchSyncEvent:1.0.0";
            this.event_name = "inbox_fetch_sync";
            this.page = "inbox";
        }

        public Builder(Context context2, RequestState request_state2, String user_role2, String inbox_type2) {
            this.schema = "com.airbnb.jitney.event.logging.Inbox:InboxFetchSyncEvent:1.0.0";
            this.event_name = "inbox_fetch_sync";
            this.context = context2;
            this.page = "inbox";
            this.request_state = request_state2;
            this.user_role = user_role2;
            this.inbox_type = inbox_type2;
        }

        public InboxFetchSyncEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.request_state == null) {
                throw new IllegalStateException("Required field 'request_state' is missing");
            } else if (this.user_role == null) {
                throw new IllegalStateException("Required field 'user_role' is missing");
            } else if (this.inbox_type != null) {
                return new InboxFetchSyncEvent(this);
            } else {
                throw new IllegalStateException("Required field 'inbox_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Inbox.v1.InboxFetchSyncEvent$InboxFetchSyncEventAdapter */
    private static final class InboxFetchSyncEventAdapter implements Adapter<InboxFetchSyncEvent, Builder> {
        private InboxFetchSyncEventAdapter() {
        }

        public void write(Protocol protocol, InboxFetchSyncEvent struct) throws IOException {
            protocol.writeStructBegin("InboxFetchSyncEvent");
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
            protocol.writeFieldBegin("request_state", 4, 8);
            protocol.writeI32(struct.request_state.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_role", 5, PassportService.SF_DG11);
            protocol.writeString(struct.user_role);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ThreadFragmentIntents.ARG_INBOX_TYPE, 6, PassportService.SF_DG11);
            protocol.writeString(struct.inbox_type);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private InboxFetchSyncEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.request_state = builder.request_state;
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
        if (!(other instanceof InboxFetchSyncEvent)) {
            return false;
        }
        InboxFetchSyncEvent that = (InboxFetchSyncEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.request_state == that.request_state || this.request_state.equals(that.request_state)) && ((this.user_role == that.user_role || this.user_role.equals(that.user_role)) && (this.inbox_type == that.inbox_type || this.inbox_type.equals(that.inbox_type)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.request_state.hashCode()) * -2128831035) ^ this.user_role.hashCode()) * -2128831035) ^ this.inbox_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "InboxFetchSyncEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", request_state=" + this.request_state + ", user_role=" + this.user_role + ", inbox_type=" + this.inbox_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
