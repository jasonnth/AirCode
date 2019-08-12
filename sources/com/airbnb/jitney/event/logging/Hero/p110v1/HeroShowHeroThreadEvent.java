package com.airbnb.jitney.event.logging.Hero.p110v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.HeroContext.p113v1.C2205HeroContext;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
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

/* renamed from: com.airbnb.jitney.event.logging.Hero.v1.HeroShowHeroThreadEvent */
public final class HeroShowHeroThreadEvent implements Struct {
    public static final Adapter<HeroShowHeroThreadEvent, Builder> ADAPTER = new HeroShowHeroThreadEventAdapter();
    public final Context context;
    public final String event_name;
    public final List<C2205HeroContext> hero_contexts;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String section;

    /* renamed from: com.airbnb.jitney.event.logging.Hero.v1.HeroShowHeroThreadEvent$Builder */
    public static final class Builder implements StructBuilder<HeroShowHeroThreadEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public List<C2205HeroContext> hero_contexts;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String section;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Hero:HeroShowHeroThreadEvent:1.0.0";
            this.event_name = "hero_show_hero_thread";
            this.page = "inbox";
            this.section = "hero_thread";
            this.operation = C2451Operation.Show;
        }

        public Builder(Context context2, List<C2205HeroContext> hero_contexts2) {
            this.schema = "com.airbnb.jitney.event.logging.Hero:HeroShowHeroThreadEvent:1.0.0";
            this.event_name = "hero_show_hero_thread";
            this.context = context2;
            this.page = "inbox";
            this.section = "hero_thread";
            this.operation = C2451Operation.Show;
            this.hero_contexts = hero_contexts2;
        }

        public HeroShowHeroThreadEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.section == null) {
                throw new IllegalStateException("Required field 'section' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.hero_contexts != null) {
                return new HeroShowHeroThreadEvent(this);
            } else {
                throw new IllegalStateException("Required field 'hero_contexts' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Hero.v1.HeroShowHeroThreadEvent$HeroShowHeroThreadEventAdapter */
    private static final class HeroShowHeroThreadEventAdapter implements Adapter<HeroShowHeroThreadEvent, Builder> {
        private HeroShowHeroThreadEventAdapter() {
        }

        public void write(Protocol protocol, HeroShowHeroThreadEvent struct) throws IOException {
            protocol.writeStructBegin("HeroShowHeroThreadEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("hero_contexts", 6, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.hero_contexts.size());
            for (C2205HeroContext item0 : struct.hero_contexts) {
                C2205HeroContext.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private HeroShowHeroThreadEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.operation = builder.operation;
        this.hero_contexts = Collections.unmodifiableList(builder.hero_contexts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof HeroShowHeroThreadEvent)) {
            return false;
        }
        HeroShowHeroThreadEvent that = (HeroShowHeroThreadEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.hero_contexts == that.hero_contexts || this.hero_contexts.equals(that.hero_contexts)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.hero_contexts.hashCode()) * -2128831035;
    }

    public String toString() {
        return "HeroShowHeroThreadEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", operation=" + this.operation + ", hero_contexts=" + this.hero_contexts + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
