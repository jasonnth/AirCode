package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
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

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBookingPermissionsCompanyClickEvent */
public final class BusinessTravelBookingPermissionsCompanyClickEvent implements Struct {
    public static final Adapter<BusinessTravelBookingPermissionsCompanyClickEvent, Object> ADAPTER = new BusinessTravelBookingPermissionsCompanyClickEventAdapter();
    public final C1796AddRemoveMethod add_remove_method;
    public final Long business_entity_id;
    public final Context context;
    public final String event_name;
    public final Boolean is_successful;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2785UserRoleType user_role;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBookingPermissionsCompanyClickEvent$BusinessTravelBookingPermissionsCompanyClickEventAdapter */
    private static final class BusinessTravelBookingPermissionsCompanyClickEventAdapter implements Adapter<BusinessTravelBookingPermissionsCompanyClickEvent, Object> {
        private BusinessTravelBookingPermissionsCompanyClickEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelBookingPermissionsCompanyClickEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelBookingPermissionsCompanyClickEvent");
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
            protocol.writeFieldBegin("user_role", 7, 8);
            protocol.writeI32(struct.user_role.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("business_entity_id", 8, 10);
            protocol.writeI64(struct.business_entity_id.longValue());
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
        if (!(other instanceof BusinessTravelBookingPermissionsCompanyClickEvent)) {
            return false;
        }
        BusinessTravelBookingPermissionsCompanyClickEvent that = (BusinessTravelBookingPermissionsCompanyClickEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.add_remove_method == that.add_remove_method || this.add_remove_method.equals(that.add_remove_method)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.is_successful == that.is_successful || this.is_successful.equals(that.is_successful)) && ((this.user_role == that.user_role || this.user_role.equals(that.user_role)) && (this.business_entity_id == that.business_entity_id || this.business_entity_id.equals(that.business_entity_id)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.add_remove_method.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.is_successful.hashCode()) * -2128831035) ^ this.user_role.hashCode()) * -2128831035) ^ this.business_entity_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelBookingPermissionsCompanyClickEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", add_remove_method=" + this.add_remove_method + ", operation=" + this.operation + ", page=" + this.page + ", is_successful=" + this.is_successful + ", user_role=" + this.user_role + ", business_entity_id=" + this.business_entity_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
