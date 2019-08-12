package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.uimanager.ViewProps;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickMtPdpElementEvent */
public final class ExploreClickMtPdpElementEvent implements Struct {
    public static final Adapter<ExploreClickMtPdpElementEvent, Builder> ADAPTER = new ExploreClickMtPdpElementEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long mt_product_id;
    public final C2444MtProduct mt_product_type;
    public final C2451Operation operation;
    public final String page;
    public final Map<String, String> pdp_context;
    public final Long position;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickMtPdpElementEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreClickMtPdpElementEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long mt_product_id;
        /* access modifiers changed from: private */
        public C2444MtProduct mt_product_type;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public Map<String, String> pdp_context;
        /* access modifiers changed from: private */
        public Long position;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickMtPdpElementEvent:1.0.0";
            this.event_name = "explore_click_mt_pdp_element";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, String page2, Long mt_product_id2, C2444MtProduct mt_product_type2, String target2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickMtPdpElementEvent:1.0.0";
            this.event_name = "explore_click_mt_pdp_element";
            this.context = context2;
            this.operation = C2451Operation.Click;
            this.page = page2;
            this.mt_product_id = mt_product_id2;
            this.mt_product_type = mt_product_type2;
            this.target = target2;
        }

        public Builder section(String section2) {
            this.section = section2;
            return this;
        }

        public Builder position(Long position2) {
            this.position = position2;
            return this;
        }

        public Builder pdp_context(Map<String, String> pdp_context2) {
            this.pdp_context = pdp_context2;
            return this;
        }

        public ExploreClickMtPdpElementEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.mt_product_id == null) {
                throw new IllegalStateException("Required field 'mt_product_id' is missing");
            } else if (this.mt_product_type == null) {
                throw new IllegalStateException("Required field 'mt_product_type' is missing");
            } else if (this.target != null) {
                return new ExploreClickMtPdpElementEvent(this);
            } else {
                throw new IllegalStateException("Required field 'target' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreClickMtPdpElementEvent$ExploreClickMtPdpElementEventAdapter */
    private static final class ExploreClickMtPdpElementEventAdapter implements Adapter<ExploreClickMtPdpElementEvent, Builder> {
        private ExploreClickMtPdpElementEventAdapter() {
        }

        public void write(Protocol protocol, ExploreClickMtPdpElementEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreClickMtPdpElementEvent");
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
            protocol.writeFieldBegin("page", 4, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            if (struct.section != null) {
                protocol.writeFieldBegin(BaseAnalytics.SECTION, 5, PassportService.SF_DG11);
                protocol.writeString(struct.section);
                protocol.writeFieldEnd();
            }
            if (struct.position != null) {
                protocol.writeFieldBegin(ViewProps.POSITION, 6, 10);
                protocol.writeI64(struct.position.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("mt_product_id", 7, 10);
            protocol.writeI64(struct.mt_product_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_product_type", 8, 8);
            protocol.writeI32(struct.mt_product_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 9, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            if (struct.search_context != null) {
                protocol.writeFieldBegin("search_context", 10, PassportService.SF_DG12);
                C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
                protocol.writeFieldEnd();
            }
            if (struct.pdp_context != null) {
                protocol.writeFieldBegin("pdp_context", 11, 13);
                protocol.writeMapBegin(PassportService.SF_DG11, PassportService.SF_DG11, struct.pdp_context.size());
                for (Entry<String, String> entry0 : struct.pdp_context.entrySet()) {
                    String value0 = (String) entry0.getValue();
                    protocol.writeString((String) entry0.getKey());
                    protocol.writeString(value0);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreClickMtPdpElementEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.page = builder.page;
        this.section = builder.section;
        this.position = builder.position;
        this.mt_product_id = builder.mt_product_id;
        this.mt_product_type = builder.mt_product_type;
        this.target = builder.target;
        this.search_context = builder.search_context;
        this.pdp_context = builder.pdp_context == null ? null : Collections.unmodifiableMap(builder.pdp_context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreClickMtPdpElementEvent)) {
            return false;
        }
        ExploreClickMtPdpElementEvent that = (ExploreClickMtPdpElementEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || (this.section != null && this.section.equals(that.section))) && ((this.position == that.position || (this.position != null && this.position.equals(that.position))) && ((this.mt_product_id == that.mt_product_id || this.mt_product_id.equals(that.mt_product_id)) && ((this.mt_product_type == that.mt_product_type || this.mt_product_type.equals(that.mt_product_type)) && ((this.target == that.target || this.target.equals(that.target)) && (this.search_context == that.search_context || (this.search_context != null && this.search_context.equals(that.search_context))))))))))))) {
            if (this.pdp_context == that.pdp_context) {
                return true;
            }
            if (this.pdp_context != null && this.pdp_context.equals(that.pdp_context)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ (this.section == null ? 0 : this.section.hashCode())) * -2128831035) ^ (this.position == null ? 0 : this.position.hashCode())) * -2128831035) ^ this.mt_product_id.hashCode()) * -2128831035) ^ this.mt_product_type.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ (this.search_context == null ? 0 : this.search_context.hashCode())) * -2128831035;
        if (this.pdp_context != null) {
            i = this.pdp_context.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExploreClickMtPdpElementEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", page=" + this.page + ", section=" + this.section + ", position=" + this.position + ", mt_product_id=" + this.mt_product_id + ", mt_product_type=" + this.mt_product_type + ", target=" + this.target + ", search_context=" + this.search_context + ", pdp_context=" + this.pdp_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
