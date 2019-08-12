package com.airbnb.jitney.event.logging.Hero.p112v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.HeroContext.p113v1.C2205HeroContext;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Hero.v3.HeroClickHeroEvent */
public final class HeroClickHeroEvent implements Struct {
    public static final Adapter<HeroClickHeroEvent, Builder> ADAPTER = new HeroClickHeroEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2205HeroContext hero_context;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String section;
    public final C2139ExploreSubtab subtab;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Hero.v3.HeroClickHeroEvent$Builder */
    public static final class Builder implements StructBuilder<HeroClickHeroEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2205HeroContext hero_context;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Hero:HeroClickHeroEvent:3.1.0";
            this.event_name = "hero_click_hero";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, String page2, String section2, String target2, C2205HeroContext hero_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Hero:HeroClickHeroEvent:3.1.0";
            this.event_name = "hero_click_hero";
            this.context = context2;
            this.page = page2;
            this.section = section2;
            this.target = target2;
            this.operation = C2451Operation.Click;
            this.hero_context = hero_context2;
        }

        public HeroClickHeroEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.section == null) {
                throw new IllegalStateException("Required field 'section' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.hero_context != null) {
                return new HeroClickHeroEvent(this);
            } else {
                throw new IllegalStateException("Required field 'hero_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Hero.v3.HeroClickHeroEvent$HeroClickHeroEventAdapter */
    private static final class HeroClickHeroEventAdapter implements Adapter<HeroClickHeroEvent, Builder> {
        private HeroClickHeroEventAdapter() {
        }

        public void write(Protocol protocol, HeroClickHeroEvent struct) throws IOException {
            protocol.writeStructBegin("HeroClickHeroEvent");
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
            protocol.writeFieldBegin("hero_context", 7, PassportService.SF_DG12);
            C2205HeroContext.ADAPTER.write(protocol, struct.hero_context);
            protocol.writeFieldEnd();
            if (struct.subtab != null) {
                protocol.writeFieldBegin("subtab", 8, 8);
                protocol.writeI32(struct.subtab.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private HeroClickHeroEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.target = builder.target;
        this.operation = builder.operation;
        this.hero_context = builder.hero_context;
        this.subtab = builder.subtab;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof HeroClickHeroEvent)) {
            return false;
        }
        HeroClickHeroEvent that = (HeroClickHeroEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.hero_context == that.hero_context || this.hero_context.equals(that.hero_context))))))))) {
            if (this.subtab == that.subtab) {
                return true;
            }
            if (this.subtab != null && this.subtab.equals(that.subtab)) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.hero_context.hashCode()) * -2128831035;
        if (this.subtab != null) {
            i = this.subtab.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "HeroClickHeroEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", operation=" + this.operation + ", hero_context=" + this.hero_context + ", subtab=" + this.subtab + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
