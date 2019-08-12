package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingManageListingPage.p067v2.C1957CohostingManageListingPage;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionListingManagerDetailEvent */
public final class CohostingImpressionListingManagerDetailEvent implements Struct {
    public static final Adapter<CohostingImpressionListingManagerDetailEvent, Object> ADAPTER = new CohostingImpressionListingManagerDetailEventAdapter();
    public final C1957CohostingManageListingPage cohost_page;
    public final C1951CohostingContext cohosting_context;
    public final Context context;
    public final String event_name;
    public final Boolean has_chat_component;
    public final Boolean has_email_component;
    public final Boolean has_make_primary_host_component;
    public final Boolean has_payment_component;
    public final Boolean has_phone_component;
    public final Boolean has_remove_cohost_component;
    public final Boolean has_resolution_center_component;
    public final Boolean has_transaction_history_component;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionListingManagerDetailEvent$CohostingImpressionListingManagerDetailEventAdapter */
    private static final class CohostingImpressionListingManagerDetailEventAdapter implements Adapter<CohostingImpressionListingManagerDetailEvent, Object> {
        private CohostingImpressionListingManagerDetailEventAdapter() {
        }

        public void write(Protocol protocol, CohostingImpressionListingManagerDetailEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingImpressionListingManagerDetailEvent");
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
            protocol.writeFieldBegin("has_chat_component", 5, 2);
            protocol.writeBool(struct.has_chat_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_email_component", 6, 2);
            protocol.writeBool(struct.has_email_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_phone_component", 7, 2);
            protocol.writeBool(struct.has_phone_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_payment_component", 8, 2);
            protocol.writeBool(struct.has_payment_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_transaction_history_component", 9, 2);
            protocol.writeBool(struct.has_transaction_history_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_resolution_center_component", 10, 2);
            protocol.writeBool(struct.has_resolution_center_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_make_primary_host_component", 11, 2);
            protocol.writeBool(struct.has_make_primary_host_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("has_remove_cohost_component", 12, 2);
            protocol.writeBool(struct.has_remove_cohost_component.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohosting_context", 13, PassportService.SF_DG12);
            C1951CohostingContext.ADAPTER.write(protocol, struct.cohosting_context);
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
        if (!(other instanceof CohostingImpressionListingManagerDetailEvent)) {
            return false;
        }
        CohostingImpressionListingManagerDetailEvent that = (CohostingImpressionListingManagerDetailEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_page == that.cohost_page || this.cohost_page.equals(that.cohost_page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.has_chat_component == that.has_chat_component || this.has_chat_component.equals(that.has_chat_component)) && ((this.has_email_component == that.has_email_component || this.has_email_component.equals(that.has_email_component)) && ((this.has_phone_component == that.has_phone_component || this.has_phone_component.equals(that.has_phone_component)) && ((this.has_payment_component == that.has_payment_component || this.has_payment_component.equals(that.has_payment_component)) && ((this.has_transaction_history_component == that.has_transaction_history_component || this.has_transaction_history_component.equals(that.has_transaction_history_component)) && ((this.has_resolution_center_component == that.has_resolution_center_component || this.has_resolution_center_component.equals(that.has_resolution_center_component)) && ((this.has_make_primary_host_component == that.has_make_primary_host_component || this.has_make_primary_host_component.equals(that.has_make_primary_host_component)) && ((this.has_remove_cohost_component == that.has_remove_cohost_component || this.has_remove_cohost_component.equals(that.has_remove_cohost_component)) && (this.cohosting_context == that.cohosting_context || this.cohosting_context.equals(that.cohosting_context))))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.has_chat_component.hashCode()) * -2128831035) ^ this.has_email_component.hashCode()) * -2128831035) ^ this.has_phone_component.hashCode()) * -2128831035) ^ this.has_payment_component.hashCode()) * -2128831035) ^ this.has_transaction_history_component.hashCode()) * -2128831035) ^ this.has_resolution_center_component.hashCode()) * -2128831035) ^ this.has_make_primary_host_component.hashCode()) * -2128831035) ^ this.has_remove_cohost_component.hashCode()) * -2128831035) ^ this.cohosting_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingImpressionListingManagerDetailEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_page=" + this.cohost_page + ", operation=" + this.operation + ", has_chat_component=" + this.has_chat_component + ", has_email_component=" + this.has_email_component + ", has_phone_component=" + this.has_phone_component + ", has_payment_component=" + this.has_payment_component + ", has_transaction_history_component=" + this.has_transaction_history_component + ", has_resolution_center_component=" + this.has_resolution_center_component + ", has_make_primary_host_component=" + this.has_make_primary_host_component + ", has_remove_cohost_component=" + this.has_remove_cohost_component + ", cohosting_context=" + this.cohosting_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
