package com.airbnb.jitney.event.logging.CommunityCommitment.p071v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.CommunityCommitment.v1.CommunityCommitmentLearnMoreScreenScrollEvent */
public final class CommunityCommitmentLearnMoreScreenScrollEvent implements Struct {
    public static final Adapter<CommunityCommitmentLearnMoreScreenScrollEvent, Builder> ADAPTER = new CommunityCommitmentLearnMoreScreenScrollEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.CommunityCommitment.v1.CommunityCommitmentLearnMoreScreenScrollEvent$Builder */
    public static final class Builder implements StructBuilder<CommunityCommitmentLearnMoreScreenScrollEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.CommunityCommitment:CommunityCommitmentLearnMoreScreenScrollEvent:1.0.0";
            this.event_name = "communitycommitment_learn_more_screen_scroll";
            this.page = "learn_more_screen";
            this.operation = C2451Operation.Scroll;
        }

        public Builder(Context context2) {
            this.schema = "com.airbnb.jitney.event.logging.CommunityCommitment:CommunityCommitmentLearnMoreScreenScrollEvent:1.0.0";
            this.event_name = "communitycommitment_learn_more_screen_scroll";
            this.context = context2;
            this.page = "learn_more_screen";
            this.operation = C2451Operation.Scroll;
        }

        public CommunityCommitmentLearnMoreScreenScrollEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation != null) {
                return new CommunityCommitmentLearnMoreScreenScrollEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.CommunityCommitment.v1.CommunityCommitmentLearnMoreScreenScrollEvent$CommunityCommitmentLearnMoreScreenScrollEventAdapter */
    private static final class CommunityCommitmentLearnMoreScreenScrollEventAdapter implements Adapter<CommunityCommitmentLearnMoreScreenScrollEvent, Builder> {
        private CommunityCommitmentLearnMoreScreenScrollEventAdapter() {
        }

        public void write(Protocol protocol, CommunityCommitmentLearnMoreScreenScrollEvent struct) throws IOException {
            protocol.writeStructBegin("CommunityCommitmentLearnMoreScreenScrollEvent");
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
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CommunityCommitmentLearnMoreScreenScrollEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CommunityCommitmentLearnMoreScreenScrollEvent)) {
            return false;
        }
        CommunityCommitmentLearnMoreScreenScrollEvent that = (CommunityCommitmentLearnMoreScreenScrollEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && (this.operation == that.operation || this.operation.equals(that.operation)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CommunityCommitmentLearnMoreScreenScrollEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
