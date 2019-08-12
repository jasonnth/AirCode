package com.airbnb.jitney.event.logging.Cohosting.p060v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
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

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingInviteManageListingEvent */
public final class CohostingInviteManageListingEvent implements Struct {
    public static final Adapter<CohostingInviteManageListingEvent, Object> ADAPTER = new CohostingInviteManageListingEventAdapter();
    public final C1957CohostingManageListingPage cohost_page;
    public final C1951CohostingContext cohosting_context;
    public final Context context;
    public final String event_name;
    public final Long fixed_fee;
    public final String fixed_fee_currency;
    public final String invited_user_email;
    public final C2451Operation operation;
    public final Boolean pay_cleaning_fees;
    public final Long percent_of_shared_earnings;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingInviteManageListingEvent$CohostingInviteManageListingEventAdapter */
    private static final class CohostingInviteManageListingEventAdapter implements Adapter<CohostingInviteManageListingEvent, Object> {
        private CohostingInviteManageListingEventAdapter() {
        }

        public void write(Protocol protocol, CohostingInviteManageListingEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingInviteManageListingEvent");
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
            protocol.writeFieldBegin(CohostingManagementJitneyLogger.INVITED_USER_EMAIL, 5, PassportService.SF_DG11);
            protocol.writeString(struct.invited_user_email);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("percent_of_shared_earnings", 6, 10);
            protocol.writeI64(struct.percent_of_shared_earnings.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("fixed_fee", 7, 10);
            protocol.writeI64(struct.fixed_fee.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("fixed_fee_currency", 8, PassportService.SF_DG11);
            protocol.writeString(struct.fixed_fee_currency);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("pay_cleaning_fees", 9, 2);
            protocol.writeBool(struct.pay_cleaning_fees.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohosting_context", 10, PassportService.SF_DG12);
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
        if (!(other instanceof CohostingInviteManageListingEvent)) {
            return false;
        }
        CohostingInviteManageListingEvent that = (CohostingInviteManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_page == that.cohost_page || this.cohost_page.equals(that.cohost_page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.invited_user_email == that.invited_user_email || this.invited_user_email.equals(that.invited_user_email)) && ((this.percent_of_shared_earnings == that.percent_of_shared_earnings || this.percent_of_shared_earnings.equals(that.percent_of_shared_earnings)) && ((this.fixed_fee == that.fixed_fee || this.fixed_fee.equals(that.fixed_fee)) && ((this.fixed_fee_currency == that.fixed_fee_currency || this.fixed_fee_currency.equals(that.fixed_fee_currency)) && ((this.pay_cleaning_fees == that.pay_cleaning_fees || this.pay_cleaning_fees.equals(that.pay_cleaning_fees)) && (this.cohosting_context == that.cohosting_context || this.cohosting_context.equals(that.cohosting_context)))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.invited_user_email.hashCode()) * -2128831035) ^ this.percent_of_shared_earnings.hashCode()) * -2128831035) ^ this.fixed_fee.hashCode()) * -2128831035) ^ this.fixed_fee_currency.hashCode()) * -2128831035) ^ this.pay_cleaning_fees.hashCode()) * -2128831035) ^ this.cohosting_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingInviteManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_page=" + this.cohost_page + ", operation=" + this.operation + ", invited_user_email=" + this.invited_user_email + ", percent_of_shared_earnings=" + this.percent_of_shared_earnings + ", fixed_fee=" + this.fixed_fee + ", fixed_fee_currency=" + this.fixed_fee_currency + ", pay_cleaning_fees=" + this.pay_cleaning_fees + ", cohosting_context=" + this.cohosting_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
