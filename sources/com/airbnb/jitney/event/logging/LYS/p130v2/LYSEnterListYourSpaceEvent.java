package com.airbnb.jitney.event.logging.LYS.p130v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSEnterListYourSpaceEvent */
public final class LYSEnterListYourSpaceEvent implements Struct {
    public static final Adapter<LYSEnterListYourSpaceEvent, Builder> ADAPTER = new LYSEnterListYourSpaceEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String lys_session_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSEnterListYourSpaceEvent$Builder */
    public static final class Builder implements StructBuilder<LYSEnterListYourSpaceEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String lys_session_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSEnterListYourSpaceEvent:2.0.0";
            this.event_name = "lys_enter_list_your_space";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, String page2, String target2, String lys_session_id2) {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSEnterListYourSpaceEvent:2.0.0";
            this.event_name = "lys_enter_list_your_space";
            this.context = context2;
            this.page = page2;
            this.target = target2;
            this.operation = C2451Operation.Click;
            this.lys_session_id = lys_session_id2;
        }

        public Builder listing_id(Long listing_id2) {
            this.listing_id = listing_id2;
            return this;
        }

        public LYSEnterListYourSpaceEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.lys_session_id != null) {
                return new LYSEnterListYourSpaceEvent(this);
            } else {
                throw new IllegalStateException("Required field 'lys_session_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSEnterListYourSpaceEvent$LYSEnterListYourSpaceEventAdapter */
    private static final class LYSEnterListYourSpaceEventAdapter implements Adapter<LYSEnterListYourSpaceEvent, Builder> {
        private LYSEnterListYourSpaceEventAdapter() {
        }

        public void write(Protocol protocol, LYSEnterListYourSpaceEvent struct) throws IOException {
            protocol.writeStructBegin("LYSEnterListYourSpaceEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 6, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(ManageListingIntents.INTENT_EXTRA_SESSION_ID, 7, PassportService.SF_DG11);
            protocol.writeString(struct.lys_session_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private LYSEnterListYourSpaceEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.lys_session_id = builder.lys_session_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof LYSEnterListYourSpaceEvent)) {
            return false;
        }
        LYSEnterListYourSpaceEvent that = (LYSEnterListYourSpaceEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || (this.listing_id != null && this.listing_id.equals(that.listing_id))) && (this.lys_session_id == that.lys_session_id || this.lys_session_id.equals(that.lys_session_id))))))))) {
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.listing_id != null) {
            i = this.listing_id.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.lys_session_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LYSEnterListYourSpaceEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", lys_session_id=" + this.lys_session_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
