package com.airbnb.jitney.event.logging.Identity.p118v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.IdentityAction.p119v1.IdentityActionType;
import com.airbnb.jitney.event.logging.IdentityActor.p120v1.IdentityActorType;
import com.airbnb.jitney.event.logging.IdentityReason.p124v1.IdentityReasonType;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentitySemanticEvent */
public final class IdentitySemanticEvent implements Struct {
    public static final Adapter<IdentitySemanticEvent, Builder> ADAPTER = new IdentitySemanticEventAdapter();
    public final IdentityActionType action;
    public final IdentityActorType actor;
    public final Context context;
    public final String event_name;
    public final String extra_info;
    public final Boolean is_mobile_handoff;
    public final Boolean is_previous_rejected;
    public final C2451Operation operation;
    public final IdentityReasonType reason;
    public final String schema;
    public final IdentityVerificationType verification;

    /* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentitySemanticEvent$Builder */
    public static final class Builder implements StructBuilder<IdentitySemanticEvent> {
        /* access modifiers changed from: private */
        public IdentityActionType action;
        /* access modifiers changed from: private */
        public IdentityActorType actor;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "identity_semantic";
        /* access modifiers changed from: private */
        public String extra_info;
        /* access modifiers changed from: private */
        public Boolean is_mobile_handoff;
        /* access modifiers changed from: private */
        public Boolean is_previous_rejected;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public IdentityReasonType reason;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Identity:IdentitySemanticEvent:1.0.0";
        /* access modifiers changed from: private */
        public IdentityVerificationType verification;

        private Builder() {
        }

        public Builder(Context context2, C2451Operation operation2, IdentityActionType action2, IdentityActorType actor2) {
            this.context = context2;
            this.operation = operation2;
            this.action = action2;
            this.actor = actor2;
        }

        public Builder verification(IdentityVerificationType verification2) {
            this.verification = verification2;
            return this;
        }

        public Builder is_mobile_handoff(Boolean is_mobile_handoff2) {
            this.is_mobile_handoff = is_mobile_handoff2;
            return this;
        }

        public Builder extra_info(String extra_info2) {
            this.extra_info = extra_info2;
            return this;
        }

        public IdentitySemanticEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.action == null) {
                throw new IllegalStateException("Required field 'action' is missing");
            } else if (this.actor != null) {
                return new IdentitySemanticEvent(this);
            } else {
                throw new IllegalStateException("Required field 'actor' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentitySemanticEvent$IdentitySemanticEventAdapter */
    private static final class IdentitySemanticEventAdapter implements Adapter<IdentitySemanticEvent, Builder> {
        private IdentitySemanticEventAdapter() {
        }

        public void write(Protocol protocol, IdentitySemanticEvent struct) throws IOException {
            protocol.writeStructBegin("IdentitySemanticEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            if (struct.verification != null) {
                protocol.writeFieldBegin("verification", 4, 8);
                protocol.writeI32(struct.verification.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("action", 5, 8);
            protocol.writeI32(struct.action.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("actor", 6, 8);
            protocol.writeI32(struct.actor.value);
            protocol.writeFieldEnd();
            if (struct.reason != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_REASON, 7, 8);
                protocol.writeI32(struct.reason.value);
                protocol.writeFieldEnd();
            }
            if (struct.is_mobile_handoff != null) {
                protocol.writeFieldBegin("is_mobile_handoff", 8, 2);
                protocol.writeBool(struct.is_mobile_handoff.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.is_previous_rejected != null) {
                protocol.writeFieldBegin("is_previous_rejected", 9, 2);
                protocol.writeBool(struct.is_previous_rejected.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.extra_info != null) {
                protocol.writeFieldBegin("extra_info", 10, PassportService.SF_DG11);
                protocol.writeString(struct.extra_info);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private IdentitySemanticEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.verification = builder.verification;
        this.action = builder.action;
        this.actor = builder.actor;
        this.reason = builder.reason;
        this.is_mobile_handoff = builder.is_mobile_handoff;
        this.is_previous_rejected = builder.is_previous_rejected;
        this.extra_info = builder.extra_info;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof IdentitySemanticEvent)) {
            return false;
        }
        IdentitySemanticEvent that = (IdentitySemanticEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.verification == that.verification || (this.verification != null && this.verification.equals(that.verification))) && ((this.action == that.action || this.action.equals(that.action)) && ((this.actor == that.actor || this.actor.equals(that.actor)) && ((this.reason == that.reason || (this.reason != null && this.reason.equals(that.reason))) && ((this.is_mobile_handoff == that.is_mobile_handoff || (this.is_mobile_handoff != null && this.is_mobile_handoff.equals(that.is_mobile_handoff))) && (this.is_previous_rejected == that.is_previous_rejected || (this.is_previous_rejected != null && this.is_previous_rejected.equals(that.is_previous_rejected)))))))))))) {
            if (this.extra_info == that.extra_info) {
                return true;
            }
            if (this.extra_info != null && this.extra_info.equals(that.extra_info)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ (this.verification == null ? 0 : this.verification.hashCode())) * -2128831035) ^ this.action.hashCode()) * -2128831035) ^ this.actor.hashCode()) * -2128831035) ^ (this.reason == null ? 0 : this.reason.hashCode())) * -2128831035) ^ (this.is_mobile_handoff == null ? 0 : this.is_mobile_handoff.hashCode())) * -2128831035) ^ (this.is_previous_rejected == null ? 0 : this.is_previous_rejected.hashCode())) * -2128831035;
        if (this.extra_info != null) {
            i = this.extra_info.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "IdentitySemanticEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", verification=" + this.verification + ", action=" + this.action + ", actor=" + this.actor + ", reason=" + this.reason + ", is_mobile_handoff=" + this.is_mobile_handoff + ", is_previous_rejected=" + this.is_previous_rejected + ", extra_info=" + this.extra_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
