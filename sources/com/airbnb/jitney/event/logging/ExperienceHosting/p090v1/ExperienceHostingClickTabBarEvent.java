package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickTabBarEvent */
public final class ExperienceHostingClickTabBarEvent implements Struct {
    public static final Adapter<ExperienceHostingClickTabBarEvent, Object> ADAPTER = new ExperienceHostingClickTabBarEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String section;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickTabBarEvent$ExperienceHostingClickTabBarEventAdapter */
    private static final class ExperienceHostingClickTabBarEventAdapter implements Adapter<ExperienceHostingClickTabBarEvent, Object> {
        private ExperienceHostingClickTabBarEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingClickTabBarEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingClickTabBarEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.SECTION, 4, PassportService.SF_DG11);
            protocol.writeString(struct.section);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
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
        if (!(other instanceof ExperienceHostingClickTabBarEvent)) {
            return false;
        }
        ExperienceHostingClickTabBarEvent that = (ExperienceHostingClickTabBarEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && (this.operation == that.operation || this.operation.equals(that.operation)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingClickTabBarEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}