package com.airbnb.jitney.event.logging.LYS.p130v2;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSUpdateLysLastFinishedStepIdEvent */
public final class LYSUpdateLysLastFinishedStepIdEvent implements Struct {
    public static final Adapter<LYSUpdateLysLastFinishedStepIdEvent, Builder> ADAPTER = new LYSUpdateLysLastFinishedStepIdEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final String schema;
    public final String step_id;

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSUpdateLysLastFinishedStepIdEvent$Builder */
    public static final class Builder implements StructBuilder<LYSUpdateLysLastFinishedStepIdEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "lys_update_lys_last_finished_step_id";
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.LYS:LYSUpdateLysLastFinishedStepIdEvent:2.0.0";
        /* access modifiers changed from: private */
        public String step_id;

        private Builder() {
        }

        public Builder(Context context2, Long listing_id2, String step_id2) {
            this.context = context2;
            this.listing_id = listing_id2;
            this.step_id = step_id2;
        }

        public LYSUpdateLysLastFinishedStepIdEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.step_id != null) {
                return new LYSUpdateLysLastFinishedStepIdEvent(this);
            } else {
                throw new IllegalStateException("Required field 'step_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSUpdateLysLastFinishedStepIdEvent$LYSUpdateLysLastFinishedStepIdEventAdapter */
    private static final class LYSUpdateLysLastFinishedStepIdEventAdapter implements Adapter<LYSUpdateLysLastFinishedStepIdEvent, Builder> {
        private LYSUpdateLysLastFinishedStepIdEventAdapter() {
        }

        public void write(Protocol protocol, LYSUpdateLysLastFinishedStepIdEvent struct) throws IOException {
            protocol.writeStructBegin("LYSUpdateLysLastFinishedStepIdEvent");
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
            protocol.writeFieldBegin("listing_id", 3, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("step_id", 4, PassportService.SF_DG11);
            protocol.writeString(struct.step_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private LYSUpdateLysLastFinishedStepIdEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.listing_id = builder.listing_id;
        this.step_id = builder.step_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof LYSUpdateLysLastFinishedStepIdEvent)) {
            return false;
        }
        LYSUpdateLysLastFinishedStepIdEvent that = (LYSUpdateLysLastFinishedStepIdEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.step_id == that.step_id || this.step_id.equals(that.step_id)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.step_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LYSUpdateLysLastFinishedStepIdEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", listing_id=" + this.listing_id + ", step_id=" + this.step_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
