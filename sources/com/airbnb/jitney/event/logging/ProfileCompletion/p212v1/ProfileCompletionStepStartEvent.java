package com.airbnb.jitney.event.logging.ProfileCompletion.p212v1;

import com.airbnb.jitney.event.logging.ProfileCompletionStep.p214v1.C2595ProfileCompletionStep;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ProfileCompletion.v1.ProfileCompletionStepStartEvent */
public final class ProfileCompletionStepStartEvent implements Struct {
    public static final Adapter<ProfileCompletionStepStartEvent, Builder> ADAPTER = new ProfileCompletionStepStartEventAdapter();
    public final Context context;
    public final String event_name;
    public final List<C2595ProfileCompletionStep> profile_completion_completed_steps;
    public final List<C2595ProfileCompletionStep> profile_completion_incomplete_steps;
    public final C2595ProfileCompletionStep profile_completion_step;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ProfileCompletion.v1.ProfileCompletionStepStartEvent$Builder */
    public static final class Builder implements StructBuilder<ProfileCompletionStepStartEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "profilecompletion_step_start";
        /* access modifiers changed from: private */
        public List<C2595ProfileCompletionStep> profile_completion_completed_steps;
        /* access modifiers changed from: private */
        public List<C2595ProfileCompletionStep> profile_completion_incomplete_steps;
        /* access modifiers changed from: private */
        public C2595ProfileCompletionStep profile_completion_step;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.ProfileCompletion:ProfileCompletionStepStartEvent:1.0.0";

        private Builder() {
        }

        public Builder(Context context2, C2595ProfileCompletionStep profile_completion_step2, List<C2595ProfileCompletionStep> profile_completion_completed_steps2, List<C2595ProfileCompletionStep> profile_completion_incomplete_steps2) {
            this.context = context2;
            this.profile_completion_step = profile_completion_step2;
            this.profile_completion_completed_steps = profile_completion_completed_steps2;
            this.profile_completion_incomplete_steps = profile_completion_incomplete_steps2;
        }

        public ProfileCompletionStepStartEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.profile_completion_step == null) {
                throw new IllegalStateException("Required field 'profile_completion_step' is missing");
            } else if (this.profile_completion_completed_steps == null) {
                throw new IllegalStateException("Required field 'profile_completion_completed_steps' is missing");
            } else if (this.profile_completion_incomplete_steps != null) {
                return new ProfileCompletionStepStartEvent(this);
            } else {
                throw new IllegalStateException("Required field 'profile_completion_incomplete_steps' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.ProfileCompletion.v1.ProfileCompletionStepStartEvent$ProfileCompletionStepStartEventAdapter */
    private static final class ProfileCompletionStepStartEventAdapter implements Adapter<ProfileCompletionStepStartEvent, Builder> {
        private ProfileCompletionStepStartEventAdapter() {
        }

        public void write(Protocol protocol, ProfileCompletionStepStartEvent struct) throws IOException {
            protocol.writeStructBegin("ProfileCompletionStepStartEvent");
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
            protocol.writeFieldBegin("profile_completion_step", 3, 8);
            protocol.writeI32(struct.profile_completion_step.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("profile_completion_completed_steps", 4, 15);
            protocol.writeListBegin(16, struct.profile_completion_completed_steps.size());
            for (C2595ProfileCompletionStep item0 : struct.profile_completion_completed_steps) {
                protocol.writeI32(item0.value);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("profile_completion_incomplete_steps", 5, 15);
            protocol.writeListBegin(16, struct.profile_completion_incomplete_steps.size());
            for (C2595ProfileCompletionStep item02 : struct.profile_completion_incomplete_steps) {
                protocol.writeI32(item02.value);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ProfileCompletionStepStartEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.profile_completion_step = builder.profile_completion_step;
        this.profile_completion_completed_steps = Collections.unmodifiableList(builder.profile_completion_completed_steps);
        this.profile_completion_incomplete_steps = Collections.unmodifiableList(builder.profile_completion_incomplete_steps);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ProfileCompletionStepStartEvent)) {
            return false;
        }
        ProfileCompletionStepStartEvent that = (ProfileCompletionStepStartEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.profile_completion_step == that.profile_completion_step || this.profile_completion_step.equals(that.profile_completion_step)) && ((this.profile_completion_completed_steps == that.profile_completion_completed_steps || this.profile_completion_completed_steps.equals(that.profile_completion_completed_steps)) && (this.profile_completion_incomplete_steps == that.profile_completion_incomplete_steps || this.profile_completion_incomplete_steps.equals(that.profile_completion_incomplete_steps))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.profile_completion_step.hashCode()) * -2128831035) ^ this.profile_completion_completed_steps.hashCode()) * -2128831035) ^ this.profile_completion_incomplete_steps.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ProfileCompletionStepStartEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", profile_completion_step=" + this.profile_completion_step + ", profile_completion_completed_steps=" + this.profile_completion_completed_steps + ", profile_completion_incomplete_steps=" + this.profile_completion_incomplete_steps + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
