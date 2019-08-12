package com.airbnb.jitney.event.logging.MTNotifications.p145v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.MtNotificationType.p156v1.C2442MtNotificationType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.MTNotifications.v1.MTNotificationsClickMtNotificationEvent */
public final class MTNotificationsClickMtNotificationEvent implements Struct {
    public static final Adapter<MTNotificationsClickMtNotificationEvent, Object> ADAPTER = new MTNotificationsClickMtNotificationEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2442MtNotificationType mt_notification_type;
    public final Long notification_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.MTNotifications.v1.MTNotificationsClickMtNotificationEvent$MTNotificationsClickMtNotificationEventAdapter */
    private static final class MTNotificationsClickMtNotificationEventAdapter implements Adapter<MTNotificationsClickMtNotificationEvent, Object> {
        private MTNotificationsClickMtNotificationEventAdapter() {
        }

        public void write(Protocol protocol, MTNotificationsClickMtNotificationEvent struct) throws IOException {
            protocol.writeStructBegin("MTNotificationsClickMtNotificationEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("notification_id", 5, 10);
            protocol.writeI64(struct.notification_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_notification_type", 6, 8);
            protocol.writeI32(struct.mt_notification_type.value);
            protocol.writeFieldEnd();
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
        if (!(other instanceof MTNotificationsClickMtNotificationEvent)) {
            return false;
        }
        MTNotificationsClickMtNotificationEvent that = (MTNotificationsClickMtNotificationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.notification_id == that.notification_id || this.notification_id.equals(that.notification_id)) && (this.mt_notification_type == that.mt_notification_type || this.mt_notification_type.equals(that.mt_notification_type)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.notification_id.hashCode()) * -2128831035) ^ this.mt_notification_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "MTNotificationsClickMtNotificationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", notification_id=" + this.notification_id + ", mt_notification_type=" + this.mt_notification_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
