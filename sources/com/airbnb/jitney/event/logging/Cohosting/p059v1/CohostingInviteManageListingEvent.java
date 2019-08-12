package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.jitney.event.logging.CohostContext.p058v1.C1926CohostContext;
import com.airbnb.jitney.event.logging.CohostingManageListingPage.p066v1.C1956CohostingManageListingPage;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingInviteManageListingEvent */
public final class CohostingInviteManageListingEvent implements Struct {
    public static final Adapter<CohostingInviteManageListingEvent, Object> ADAPTER = new CohostingInviteManageListingEventAdapter();
    public final C1926CohostContext cohost_context;
    public final C1956CohostingManageListingPage cohost_page;
    public final Context context;
    public final String event_name;
    public final String invited_user_email;
    public final Boolean pay_cleaning_fees;
    public final String schema;
    public final Long share_of_earnings;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingInviteManageListingEvent$CohostingInviteManageListingEventAdapter */
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
            protocol.writeFieldBegin(CohostingManagementJitneyLogger.INVITED_USER_EMAIL, 4, PassportService.SF_DG11);
            protocol.writeString(struct.invited_user_email);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("share_of_earnings", 5, 10);
            protocol.writeI64(struct.share_of_earnings.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("pay_cleaning_fees", 6, 2);
            protocol.writeBool(struct.pay_cleaning_fees.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohost_context", 7, PassportService.SF_DG12);
            C1926CohostContext.ADAPTER.write(protocol, struct.cohost_context);
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_page == that.cohost_page || this.cohost_page.equals(that.cohost_page)) && ((this.invited_user_email == that.invited_user_email || this.invited_user_email.equals(that.invited_user_email)) && ((this.share_of_earnings == that.share_of_earnings || this.share_of_earnings.equals(that.share_of_earnings)) && ((this.pay_cleaning_fees == that.pay_cleaning_fees || this.pay_cleaning_fees.equals(that.pay_cleaning_fees)) && (this.cohost_context == that.cohost_context || this.cohost_context.equals(that.cohost_context))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_page.hashCode()) * -2128831035) ^ this.invited_user_email.hashCode()) * -2128831035) ^ this.share_of_earnings.hashCode()) * -2128831035) ^ this.pay_cleaning_fees.hashCode()) * -2128831035) ^ this.cohost_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingInviteManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_page=" + this.cohost_page + ", invited_user_email=" + this.invited_user_email + ", share_of_earnings=" + this.share_of_earnings + ", pay_cleaning_fees=" + this.pay_cleaning_fees + ", cohost_context=" + this.cohost_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
