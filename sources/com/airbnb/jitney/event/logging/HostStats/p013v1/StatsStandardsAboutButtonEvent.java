package com.airbnb.jitney.event.logging.HostStats.p013v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsStandardsAboutButtonEvent */
public final class StatsStandardsAboutButtonEvent implements Struct {
    public static final Adapter<StatsStandardsAboutButtonEvent, Builder> ADAPTER = new StatsStandardsAboutButtonEventAdapter();
    public final Context context;
    public final String event_name;
    public final String operation;
    public final String page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsStandardsAboutButtonEvent$Builder */
    public static final class Builder implements StructBuilder<StatsStandardsAboutButtonEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsStandardsAboutButtonEvent:1.0.0";
            this.event_name = "stats_standards_about_button";
            this.page = "stats_home";
            this.target = "about_button";
            this.operation = "tap";
        }

        public Builder(Context context2) {
            this.schema = "com.airbnb.jitney.event.logging.HostStats:StatsStandardsAboutButtonEvent:1.0.0";
            this.event_name = "stats_standards_about_button";
            this.context = context2;
            this.page = "stats_home";
            this.target = "about_button";
            this.operation = "tap";
        }

        public StatsStandardsAboutButtonEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation != null) {
                return new StatsStandardsAboutButtonEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.HostStats.v1.StatsStandardsAboutButtonEvent$StatsStandardsAboutButtonEventAdapter */
    private static final class StatsStandardsAboutButtonEventAdapter implements Adapter<StatsStandardsAboutButtonEvent, Builder> {
        private StatsStandardsAboutButtonEventAdapter() {
        }

        public void write(Protocol protocol, StatsStandardsAboutButtonEvent struct) throws IOException {
            protocol.writeStructBegin("StatsStandardsAboutButtonEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private StatsStandardsAboutButtonEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof StatsStandardsAboutButtonEvent)) {
            return false;
        }
        StatsStandardsAboutButtonEvent that = (StatsStandardsAboutButtonEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && (this.operation == that.operation || this.operation.equals(that.operation))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "StatsStandardsAboutButtonEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}