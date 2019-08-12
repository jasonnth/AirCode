package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingInviteFlowClickTarget.p063v1.C1953CohostingInviteFlowClickTarget;
import com.airbnb.jitney.event.logging.CohostingInviteFlowPage.p064v1.C1954CohostingInviteFlowPage;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingAcceptButtonClickInviteFlowEvent */
public final class CohostingAcceptButtonClickInviteFlowEvent implements Struct {
    public static final Adapter<CohostingAcceptButtonClickInviteFlowEvent, Builder> ADAPTER = new CohostingAcceptButtonClickInviteFlowEventAdapter();
    public final C1953CohostingInviteFlowClickTarget cohost_invite_click_target;
    public final Context context;
    public final String event_name;
    public final String expires_at;
    public final Long invitation_id;
    public final C1954CohostingInviteFlowPage invite_page;
    public final String invitee_identifier;
    public final String invitee_identifier_type;
    public final Long invitee_user_id;
    public final Long inviter_user_id;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String original_invitee_email;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingAcceptButtonClickInviteFlowEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingAcceptButtonClickInviteFlowEvent> {
        /* access modifiers changed from: private */
        public C1953CohostingInviteFlowClickTarget cohost_invite_click_target;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String expires_at;
        /* access modifiers changed from: private */
        public Long invitation_id;
        /* access modifiers changed from: private */
        public C1954CohostingInviteFlowPage invite_page;
        /* access modifiers changed from: private */
        public String invitee_identifier;
        /* access modifiers changed from: private */
        public String invitee_identifier_type;
        /* access modifiers changed from: private */
        public Long invitee_user_id;
        /* access modifiers changed from: private */
        public Long inviter_user_id;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String original_invitee_email;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingAcceptButtonClickInviteFlowEvent:1.0.0";
            this.event_name = "cohosting_accept_button_click_invite_flow";
            this.invite_page = C1954CohostingInviteFlowPage.InviteDetailPage;
            this.cohost_invite_click_target = C1953CohostingInviteFlowClickTarget.AcceptButton;
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Long invitation_id2, Long inviter_user_id2, String original_invitee_email2, Long invitee_user_id2, String invitee_identifier_type2, String invitee_identifier2, String expires_at2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingAcceptButtonClickInviteFlowEvent:1.0.0";
            this.event_name = "cohosting_accept_button_click_invite_flow";
            this.context = context2;
            this.invite_page = C1954CohostingInviteFlowPage.InviteDetailPage;
            this.cohost_invite_click_target = C1953CohostingInviteFlowClickTarget.AcceptButton;
            this.operation = C2451Operation.Click;
            this.invitation_id = invitation_id2;
            this.inviter_user_id = inviter_user_id2;
            this.original_invitee_email = original_invitee_email2;
            this.invitee_user_id = invitee_user_id2;
            this.invitee_identifier_type = invitee_identifier_type2;
            this.invitee_identifier = invitee_identifier2;
            this.expires_at = expires_at2;
        }

        public Builder listing_id(Long listing_id2) {
            this.listing_id = listing_id2;
            return this;
        }

        public CohostingAcceptButtonClickInviteFlowEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.invite_page == null) {
                throw new IllegalStateException("Required field 'invite_page' is missing");
            } else if (this.cohost_invite_click_target == null) {
                throw new IllegalStateException("Required field 'cohost_invite_click_target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.invitation_id == null) {
                throw new IllegalStateException("Required field 'invitation_id' is missing");
            } else if (this.inviter_user_id == null) {
                throw new IllegalStateException("Required field 'inviter_user_id' is missing");
            } else if (this.original_invitee_email == null) {
                throw new IllegalStateException("Required field 'original_invitee_email' is missing");
            } else if (this.invitee_user_id == null) {
                throw new IllegalStateException("Required field 'invitee_user_id' is missing");
            } else if (this.invitee_identifier_type == null) {
                throw new IllegalStateException("Required field 'invitee_identifier_type' is missing");
            } else if (this.invitee_identifier == null) {
                throw new IllegalStateException("Required field 'invitee_identifier' is missing");
            } else if (this.expires_at != null) {
                return new CohostingAcceptButtonClickInviteFlowEvent(this);
            } else {
                throw new IllegalStateException("Required field 'expires_at' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingAcceptButtonClickInviteFlowEvent$CohostingAcceptButtonClickInviteFlowEventAdapter */
    private static final class CohostingAcceptButtonClickInviteFlowEventAdapter implements Adapter<CohostingAcceptButtonClickInviteFlowEvent, Builder> {
        private CohostingAcceptButtonClickInviteFlowEventAdapter() {
        }

        public void write(Protocol protocol, CohostingAcceptButtonClickInviteFlowEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingAcceptButtonClickInviteFlowEvent");
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
            protocol.writeFieldBegin("invite_page", 3, 8);
            protocol.writeI32(struct.invite_page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohost_invite_click_target", 4, 8);
            protocol.writeI32(struct.cohost_invite_click_target.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("invitation_id", 6, 10);
            protocol.writeI64(struct.invitation_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("inviter_user_id", 7, 10);
            protocol.writeI64(struct.inviter_user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("original_invitee_email", 8, PassportService.SF_DG11);
            protocol.writeString(struct.original_invitee_email);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("invitee_user_id", 9, 10);
            protocol.writeI64(struct.invitee_user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("invitee_identifier_type", 10, PassportService.SF_DG11);
            protocol.writeString(struct.invitee_identifier_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("invitee_identifier", 11, PassportService.SF_DG11);
            protocol.writeString(struct.invitee_identifier);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("expires_at", 12, PassportService.SF_DG11);
            protocol.writeString(struct.expires_at);
            protocol.writeFieldEnd();
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 13, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CohostingAcceptButtonClickInviteFlowEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.invite_page = builder.invite_page;
        this.cohost_invite_click_target = builder.cohost_invite_click_target;
        this.operation = builder.operation;
        this.invitation_id = builder.invitation_id;
        this.inviter_user_id = builder.inviter_user_id;
        this.original_invitee_email = builder.original_invitee_email;
        this.invitee_user_id = builder.invitee_user_id;
        this.invitee_identifier_type = builder.invitee_identifier_type;
        this.invitee_identifier = builder.invitee_identifier;
        this.expires_at = builder.expires_at;
        this.listing_id = builder.listing_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CohostingAcceptButtonClickInviteFlowEvent)) {
            return false;
        }
        CohostingAcceptButtonClickInviteFlowEvent that = (CohostingAcceptButtonClickInviteFlowEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.invite_page == that.invite_page || this.invite_page.equals(that.invite_page)) && ((this.cohost_invite_click_target == that.cohost_invite_click_target || this.cohost_invite_click_target.equals(that.cohost_invite_click_target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.invitation_id == that.invitation_id || this.invitation_id.equals(that.invitation_id)) && ((this.inviter_user_id == that.inviter_user_id || this.inviter_user_id.equals(that.inviter_user_id)) && ((this.original_invitee_email == that.original_invitee_email || this.original_invitee_email.equals(that.original_invitee_email)) && ((this.invitee_user_id == that.invitee_user_id || this.invitee_user_id.equals(that.invitee_user_id)) && ((this.invitee_identifier_type == that.invitee_identifier_type || this.invitee_identifier_type.equals(that.invitee_identifier_type)) && ((this.invitee_identifier == that.invitee_identifier || this.invitee_identifier.equals(that.invitee_identifier)) && (this.expires_at == that.expires_at || this.expires_at.equals(that.expires_at)))))))))))))) {
            if (this.listing_id == that.listing_id) {
                return true;
            }
            if (this.listing_id != null && this.listing_id.equals(that.listing_id)) {
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
        int code = (((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.invite_page.hashCode()) * -2128831035) ^ this.cohost_invite_click_target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.invitation_id.hashCode()) * -2128831035) ^ this.inviter_user_id.hashCode()) * -2128831035) ^ this.original_invitee_email.hashCode()) * -2128831035) ^ this.invitee_user_id.hashCode()) * -2128831035) ^ this.invitee_identifier_type.hashCode()) * -2128831035) ^ this.invitee_identifier.hashCode()) * -2128831035) ^ this.expires_at.hashCode()) * -2128831035;
        if (this.listing_id != null) {
            i = this.listing_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CohostingAcceptButtonClickInviteFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", invite_page=" + this.invite_page + ", cohost_invite_click_target=" + this.cohost_invite_click_target + ", operation=" + this.operation + ", invitation_id=" + this.invitation_id + ", inviter_user_id=" + this.inviter_user_id + ", original_invitee_email=" + this.original_invitee_email + ", invitee_user_id=" + this.invitee_user_id + ", invitee_identifier_type=" + this.invitee_identifier_type + ", invitee_identifier=" + this.invitee_identifier + ", expires_at=" + this.expires_at + ", listing_id=" + this.listing_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
