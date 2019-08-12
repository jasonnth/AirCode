package com.airbnb.jitney.event.logging.LYS.p129v1;

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

/* renamed from: com.airbnb.jitney.event.logging.LYS.v1.LYSWmpwClickContinueEvent */
public final class LYSWmpwClickContinueEvent implements Struct {
    public static final Adapter<LYSWmpwClickContinueEvent, Builder> ADAPTER = new LYSWmpwClickContinueEventAdapter();
    public final Context context;
    public final String event_name;
    public final Boolean listing_created;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String target;
    public final String value;

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v1.LYSWmpwClickContinueEvent$Builder */
    public static final class Builder implements StructBuilder<LYSWmpwClickContinueEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Boolean listing_created;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;
        /* access modifiers changed from: private */
        public String value;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSWmpwClickContinueEvent:1.0.0";
            this.event_name = "lys_wmpw_click_continue";
            this.page = "Wmpw";
            this.target = "ContinueButton";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, String value2, Boolean listing_created2) {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSWmpwClickContinueEvent:1.0.0";
            this.event_name = "lys_wmpw_click_continue";
            this.context = context2;
            this.page = "Wmpw";
            this.target = "ContinueButton";
            this.operation = C2451Operation.Click;
            this.value = value2;
            this.listing_created = listing_created2;
        }

        public LYSWmpwClickContinueEvent build() {
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
            } else if (this.value == null) {
                throw new IllegalStateException("Required field 'value' is missing");
            } else if (this.listing_created != null) {
                return new LYSWmpwClickContinueEvent(this);
            } else {
                throw new IllegalStateException("Required field 'listing_created' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v1.LYSWmpwClickContinueEvent$LYSWmpwClickContinueEventAdapter */
    private static final class LYSWmpwClickContinueEventAdapter implements Adapter<LYSWmpwClickContinueEvent, Builder> {
        private LYSWmpwClickContinueEventAdapter() {
        }

        public void write(Protocol protocol, LYSWmpwClickContinueEvent struct) throws IOException {
            protocol.writeStructBegin("LYSWmpwClickContinueEvent");
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
            protocol.writeFieldBegin("value", 6, PassportService.SF_DG11);
            protocol.writeString(struct.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_created", 7, 2);
            protocol.writeBool(struct.listing_created.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private LYSWmpwClickContinueEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.value = builder.value;
        this.listing_created = builder.listing_created;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof LYSWmpwClickContinueEvent)) {
            return false;
        }
        LYSWmpwClickContinueEvent that = (LYSWmpwClickContinueEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.value == that.value || this.value.equals(that.value)) && (this.listing_created == that.listing_created || this.listing_created.equals(that.listing_created))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.value.hashCode()) * -2128831035) ^ this.listing_created.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LYSWmpwClickContinueEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", value=" + this.value + ", listing_created=" + this.listing_created + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
