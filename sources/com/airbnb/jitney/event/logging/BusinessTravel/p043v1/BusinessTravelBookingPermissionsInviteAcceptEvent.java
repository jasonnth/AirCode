package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.jitney.event.logging.AddRemoveMethod.p036v1.C1796AddRemoveMethod;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.UserRoleType.p280v1.C2785UserRoleType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBookingPermissionsInviteAcceptEvent */
public final class BusinessTravelBookingPermissionsInviteAcceptEvent implements Struct {
    public static final Adapter<BusinessTravelBookingPermissionsInviteAcceptEvent, Object> ADAPTER = new BusinessTravelBookingPermissionsInviteAcceptEventAdapter();
    public final C1796AddRemoveMethod add_remove_method;
    public final Context context;
    public final String event_name;
    public final String invited_user_email;
    public final Long invited_user_id;
    public final C2785UserRoleType invited_user_role;
    public final Boolean is_successful;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBookingPermissionsInviteAcceptEvent$BusinessTravelBookingPermissionsInviteAcceptEventAdapter */
    private static final class BusinessTravelBookingPermissionsInviteAcceptEventAdapter implements Adapter<BusinessTravelBookingPermissionsInviteAcceptEvent, Object> {
        private BusinessTravelBookingPermissionsInviteAcceptEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelBookingPermissionsInviteAcceptEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelBookingPermissionsInviteAcceptEvent");
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
            protocol.writeFieldBegin("add_remove_method", 3, 8);
            protocol.writeI32(struct.add_remove_method.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 5, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_successful", 6, 2);
            protocol.writeBool(struct.is_successful.booleanValue());
            protocol.writeFieldEnd();
            if (struct.invited_user_id != null) {
                protocol.writeFieldBegin("invited_user_id", 7, 10);
                protocol.writeI64(struct.invited_user_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(CohostingManagementJitneyLogger.INVITED_USER_EMAIL, 8, PassportService.SF_DG11);
            protocol.writeString(struct.invited_user_email);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("invited_user_role", 9, 8);
            protocol.writeI32(struct.invited_user_role.value);
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
        if (!(other instanceof BusinessTravelBookingPermissionsInviteAcceptEvent)) {
            return false;
        }
        BusinessTravelBookingPermissionsInviteAcceptEvent that = (BusinessTravelBookingPermissionsInviteAcceptEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.add_remove_method == that.add_remove_method || this.add_remove_method.equals(that.add_remove_method)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.is_successful == that.is_successful || this.is_successful.equals(that.is_successful)) && ((this.invited_user_id == that.invited_user_id || (this.invited_user_id != null && this.invited_user_id.equals(that.invited_user_id))) && ((this.invited_user_email == that.invited_user_email || this.invited_user_email.equals(that.invited_user_email)) && (this.invited_user_role == that.invited_user_role || this.invited_user_role.equals(that.invited_user_role))))))))))) {
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
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.add_remove_method.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.is_successful.hashCode()) * -2128831035;
        if (this.invited_user_id != null) {
            i = this.invited_user_id.hashCode();
        }
        return (((((code ^ i) * -2128831035) ^ this.invited_user_email.hashCode()) * -2128831035) ^ this.invited_user_role.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelBookingPermissionsInviteAcceptEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", add_remove_method=" + this.add_remove_method + ", operation=" + this.operation + ", page=" + this.page + ", is_successful=" + this.is_successful + ", invited_user_id=" + this.invited_user_id + ", invited_user_email=" + this.invited_user_email + ", invited_user_role=" + this.invited_user_role + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
