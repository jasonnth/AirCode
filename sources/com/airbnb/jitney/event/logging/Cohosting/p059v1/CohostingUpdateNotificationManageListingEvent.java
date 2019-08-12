package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingManageListingPage.p067v2.C1957CohostingManageListingPage;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingUpdateNotificationManageListingEvent */
public final class CohostingUpdateNotificationManageListingEvent implements Struct {
    public static final Adapter<CohostingUpdateNotificationManageListingEvent, Builder> ADAPTER = new CohostingUpdateNotificationManageListingEventAdapter();
    public final C1957CohostingManageListingPage cohost_page;
    public final C1951CohostingContext cohosting_context;
    public final Context context;
    public final String event_name;
    public final Long mute_type;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingUpdateNotificationManageListingEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingUpdateNotificationManageListingEvent> {
        /* access modifiers changed from: private */
        public C1957CohostingManageListingPage cohost_page;
        /* access modifiers changed from: private */
        public C1951CohostingContext cohosting_context;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long mute_type;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingUpdateNotificationManageListingEvent:1.0.0";
            this.event_name = "cohosting_update_notification_manage_listing";
            this.cohost_page = C1957CohostingManageListingPage.NotificationDetailPage;
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Long mute_type2, C1951CohostingContext cohosting_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingUpdateNotificationManageListingEvent:1.0.0";
            this.event_name = "cohosting_update_notification_manage_listing";
            this.context = context2;
            this.cohost_page = C1957CohostingManageListingPage.NotificationDetailPage;
            this.operation = C2451Operation.Click;
            this.mute_type = mute_type2;
            this.cohosting_context = cohosting_context2;
        }

        public CohostingUpdateNotificationManageListingEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.cohost_page == null) {
                throw new IllegalStateException("Required field 'cohost_page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.mute_type == null) {
                throw new IllegalStateException("Required field 'mute_type' is missing");
            } else if (this.cohosting_context != null) {
                return new CohostingUpdateNotificationManageListingEvent(this);
            } else {
                throw new IllegalStateException("Required field 'cohosting_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingUpdateNotificationManageListingEvent$CohostingUpdateNotificationManageListingEventAdapter */
    private static final class CohostingUpdateNotificationManageListingEventAdapter implements Adapter<CohostingUpdateNotificationManageListingEvent, Builder> {
        private CohostingUpdateNotificationManageListingEventAdapter() {
        }

        public void write(Protocol protocol, CohostingUpdateNotificationManageListingEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingUpdateNotificationManageListingEvent");
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
            protocol.writeFieldBegin("cohost_page", 3, 8);
            protocol.writeI32(struct.cohost_page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mute_type", 5, 10);
            protocol.writeI64(struct.mute_type.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohosting_context", 6, PassportService.SF_DG12);
            C1951CohostingContext.ADAPTER.write(protocol, struct.cohosting_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CohostingUpdateNotificationManageListingEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.cohost_page = builder.cohost_page;
        this.operation = builder.operation;
        this.mute_type = builder.mute_type;
        this.cohosting_context = builder.cohosting_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CohostingUpdateNotificationManageListingEvent)) {
            return false;
        }
        CohostingUpdateNotificationManageListingEvent that = (CohostingUpdateNotificationManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_page == that.cohost_page || this.cohost_page.equals(that.cohost_page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.mute_type == that.mute_type || this.mute_type.equals(that.mute_type)) && (this.cohosting_context == that.cohosting_context || this.cohosting_context.equals(that.cohosting_context)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.mute_type.hashCode()) * -2128831035) ^ this.cohosting_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingUpdateNotificationManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_page=" + this.cohost_page + ", operation=" + this.operation + ", mute_type=" + this.mute_type + ", cohosting_context=" + this.cohosting_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
