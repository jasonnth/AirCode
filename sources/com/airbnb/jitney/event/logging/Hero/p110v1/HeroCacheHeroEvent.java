package com.airbnb.jitney.event.logging.Hero.p110v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Hero.v1.HeroCacheHeroEvent */
public final class HeroCacheHeroEvent implements Struct {
    public static final Adapter<HeroCacheHeroEvent, Object> ADAPTER = new HeroCacheHeroEventAdapter();
    public final List<String> button_texts;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2139ExploreSubtab subtab;
    public final String target;
    public final String text;

    /* renamed from: com.airbnb.jitney.event.logging.Hero.v1.HeroCacheHeroEvent$HeroCacheHeroEventAdapter */
    private static final class HeroCacheHeroEventAdapter implements Adapter<HeroCacheHeroEvent, Object> {
        private HeroCacheHeroEventAdapter() {
        }

        public void write(Protocol protocol, HeroCacheHeroEvent struct) throws IOException {
            protocol.writeStructBegin("HeroCacheHeroEvent");
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
            protocol.writeFieldBegin("text", 6, PassportService.SF_DG11);
            protocol.writeString(struct.text);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("button_texts", 7, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.button_texts.size());
            for (String item0 : struct.button_texts) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
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

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof HeroCacheHeroEvent)) {
            return false;
        }
        HeroCacheHeroEvent that = (HeroCacheHeroEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.text == that.text || this.text.equals(that.text)) && (this.button_texts == that.button_texts || this.button_texts.equals(that.button_texts))))))))) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.text.hashCode()) * -2128831035) ^ this.button_texts.hashCode()) * -2128831035;
        if (this.subtab != null) {
            i = this.subtab.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "HeroCacheHeroEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", text=" + this.text + ", button_texts=" + this.button_texts + ", subtab=" + this.subtab + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
