package com.airbnb.jitney.event.logging.DeclineFlow.p079v0;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.DeclineFlow.v0.DeclineFlowEditSaveEvent */
public final class DeclineFlowEditSaveEvent implements Struct {
    public static final Adapter<DeclineFlowEditSaveEvent, Builder> ADAPTER = new DeclineFlowEditSaveEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final Long message_length;
    public final Long message_thread_id;
    public final String operation;
    public final String page;
    public final Long reservation_id;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.DeclineFlow.v0.DeclineFlowEditSaveEvent$Builder */
    public static final class Builder implements StructBuilder<DeclineFlowEditSaveEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public Long message_length;
        /* access modifiers changed from: private */
        public Long message_thread_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Long reservation_id;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.DeclineFlow:DeclineFlowEditSaveEvent:0.1.0";
            this.event_name = "reservation_object_decline_flow_edit_save";
            this.page = "personal_message";
            this.target = "reservation_object";
            this.operation = "click";
        }

        public Builder(Context context2, Long listing_id2, Long reservation_id2) {
            this.schema = "com.airbnb.jitney.event.logging.DeclineFlow:DeclineFlowEditSaveEvent:0.1.0";
            this.event_name = "reservation_object_decline_flow_edit_save";
            this.context = context2;
            this.page = "personal_message";
            this.target = "reservation_object";
            this.operation = "click";
            this.listing_id = listing_id2;
            this.reservation_id = reservation_id2;
        }

        public Builder page(String page2) {
            if (page2 == null) {
                throw new NullPointerException("Required field 'page' cannot be null");
            }
            this.page = page2;
            return this;
        }

        public Builder message_thread_id(Long message_thread_id2) {
            this.message_thread_id = message_thread_id2;
            return this;
        }

        public Builder message_length(Long message_length2) {
            this.message_length = message_length2;
            return this;
        }

        public DeclineFlowEditSaveEvent build() {
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
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.reservation_id != null) {
                return new DeclineFlowEditSaveEvent(this);
            } else {
                throw new IllegalStateException("Required field 'reservation_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.DeclineFlow.v0.DeclineFlowEditSaveEvent$DeclineFlowEditSaveEventAdapter */
    private static final class DeclineFlowEditSaveEventAdapter implements Adapter<DeclineFlowEditSaveEvent, Builder> {
        private DeclineFlowEditSaveEventAdapter() {
        }

        public void write(Protocol protocol, DeclineFlowEditSaveEvent struct) throws IOException {
            protocol.writeStructBegin("DeclineFlowEditSaveEvent");
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
            protocol.writeFieldBegin("listing_id", 6, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("reservation_id", 7, 10);
            protocol.writeI64(struct.reservation_id.longValue());
            protocol.writeFieldEnd();
            if (struct.message_thread_id != null) {
                protocol.writeFieldBegin("message_thread_id", 8, 10);
                protocol.writeI64(struct.message_thread_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.message_length != null) {
                protocol.writeFieldBegin("message_length", 9, 10);
                protocol.writeI64(struct.message_length.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private DeclineFlowEditSaveEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.reservation_id = builder.reservation_id;
        this.message_thread_id = builder.message_thread_id;
        this.message_length = builder.message_length;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof DeclineFlowEditSaveEvent)) {
            return false;
        }
        DeclineFlowEditSaveEvent that = (DeclineFlowEditSaveEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.reservation_id == that.reservation_id || this.reservation_id.equals(that.reservation_id)) && (this.message_thread_id == that.message_thread_id || (this.message_thread_id != null && this.message_thread_id.equals(that.message_thread_id))))))))))) {
            if (this.message_length == that.message_length) {
                return true;
            }
            if (this.message_length != null && this.message_length.equals(that.message_length)) {
                return true;
            }
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
        int code = (((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.reservation_id.hashCode()) * -2128831035) ^ (this.message_thread_id == null ? 0 : this.message_thread_id.hashCode())) * -2128831035;
        if (this.message_length != null) {
            i = this.message_length.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "DeclineFlowEditSaveEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", reservation_id=" + this.reservation_id + ", message_thread_id=" + this.message_thread_id + ", message_length=" + this.message_length + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
