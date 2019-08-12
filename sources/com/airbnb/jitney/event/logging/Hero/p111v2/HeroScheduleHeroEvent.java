package com.airbnb.jitney.event.logging.Hero.p111v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.HeroContext.p113v1.C2205HeroContext;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Hero.v2.HeroScheduleHeroEvent */
public final class HeroScheduleHeroEvent implements Struct {
    public static final Adapter<HeroScheduleHeroEvent, Object> ADAPTER = new HeroScheduleHeroEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2205HeroContext hero_context;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2139ExploreSubtab subtab;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Hero.v2.HeroScheduleHeroEvent$HeroScheduleHeroEventAdapter */
    private static final class HeroScheduleHeroEventAdapter implements Adapter<HeroScheduleHeroEvent, Object> {
        private HeroScheduleHeroEventAdapter() {
        }

        public void write(Protocol protocol, HeroScheduleHeroEvent struct) throws IOException {
            protocol.writeStructBegin("HeroScheduleHeroEvent");
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
            protocol.writeFieldBegin("hero_context", 6, PassportService.SF_DG12);
            C2205HeroContext.ADAPTER.write(protocol, struct.hero_context);
            protocol.writeFieldEnd();
            if (struct.subtab != null) {
                protocol.writeFieldBegin("subtab", 7, 8);
                protocol.writeI32(struct.subtab.value);
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof HeroScheduleHeroEvent)) {
            return false;
        }
        HeroScheduleHeroEvent that = (HeroScheduleHeroEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.hero_context == that.hero_context || this.hero_context.equals(that.hero_context)))))))) {
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
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.hero_context.hashCode()) * -2128831035;
        if (this.subtab != null) {
            i = this.subtab.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "HeroScheduleHeroEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", hero_context=" + this.hero_context + ", subtab=" + this.subtab + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}