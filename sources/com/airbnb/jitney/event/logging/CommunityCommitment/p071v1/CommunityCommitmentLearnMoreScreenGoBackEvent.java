package com.airbnb.jitney.event.logging.CommunityCommitment.p071v1;

import com.airbnb.jitney.event.logging.CommunityBackButtonType.p070v1.C1960CommunityBackButtonType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.CommunityCommitment.v1.CommunityCommitmentLearnMoreScreenGoBackEvent */
public final class CommunityCommitmentLearnMoreScreenGoBackEvent implements Struct {
    public static final Adapter<CommunityCommitmentLearnMoreScreenGoBackEvent, Builder> ADAPTER = new CommunityCommitmentLearnMoreScreenGoBackEventAdapter();
    public final C1960CommunityBackButtonType community_back_button;
    public final Context context;
    public final String event_name;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.CommunityCommitment.v1.CommunityCommitmentLearnMoreScreenGoBackEvent$Builder */
    public static final class Builder implements StructBuilder<CommunityCommitmentLearnMoreScreenGoBackEvent> {
        /* access modifiers changed from: private */
        public C1960CommunityBackButtonType community_back_button;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.CommunityCommitment:CommunityCommitmentLearnMoreScreenGoBackEvent:1.0.0";
            this.event_name = "communitycommitment_learn_more_screen_go_back";
            this.page = "learn_more_screen";
        }

        public Builder(Context context2, C1960CommunityBackButtonType community_back_button2) {
            this.schema = "com.airbnb.jitney.event.logging.CommunityCommitment:CommunityCommitmentLearnMoreScreenGoBackEvent:1.0.0";
            this.event_name = "communitycommitment_learn_more_screen_go_back";
            this.context = context2;
            this.page = "learn_more_screen";
            this.community_back_button = community_back_button2;
        }

        public CommunityCommitmentLearnMoreScreenGoBackEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.community_back_button != null) {
                return new CommunityCommitmentLearnMoreScreenGoBackEvent(this);
            } else {
                throw new IllegalStateException("Required field 'community_back_button' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.CommunityCommitment.v1.CommunityCommitmentLearnMoreScreenGoBackEvent$CommunityCommitmentLearnMoreScreenGoBackEventAdapter */
    private static final class CommunityCommitmentLearnMoreScreenGoBackEventAdapter implements Adapter<CommunityCommitmentLearnMoreScreenGoBackEvent, Builder> {
        private CommunityCommitmentLearnMoreScreenGoBackEventAdapter() {
        }

        public void write(Protocol protocol, CommunityCommitmentLearnMoreScreenGoBackEvent struct) throws IOException {
            protocol.writeStructBegin("CommunityCommitmentLearnMoreScreenGoBackEvent");
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
            protocol.writeFieldBegin("community_back_button", 4, 8);
            protocol.writeI32(struct.community_back_button.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CommunityCommitmentLearnMoreScreenGoBackEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.community_back_button = builder.community_back_button;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CommunityCommitmentLearnMoreScreenGoBackEvent)) {
            return false;
        }
        CommunityCommitmentLearnMoreScreenGoBackEvent that = (CommunityCommitmentLearnMoreScreenGoBackEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && (this.community_back_button == that.community_back_button || this.community_back_button.equals(that.community_back_button)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.community_back_button.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CommunityCommitmentLearnMoreScreenGoBackEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", community_back_button=" + this.community_back_button + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
