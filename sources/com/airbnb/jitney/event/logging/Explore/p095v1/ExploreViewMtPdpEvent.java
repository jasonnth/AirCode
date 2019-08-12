package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreViewMtPdpEvent */
public final class ExploreViewMtPdpEvent implements Struct {
    public static final Adapter<ExploreViewMtPdpEvent, Builder> ADAPTER = new ExploreViewMtPdpEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final Long guests;
    public final String location;
    public final C2443MtPdpReferrer mt_pdp_referrer;
    public final Long mt_product_id;
    public final C2444MtProduct mt_product_type;
    public final C2451Operation operation;
    public final String page;
    public final String page_referrer;
    public final Map<String, String> pdp_context;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String source;
    public final C2139ExploreSubtab subtab;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreViewMtPdpEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreViewMtPdpEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public String location;
        /* access modifiers changed from: private */
        public C2443MtPdpReferrer mt_pdp_referrer;
        /* access modifiers changed from: private */
        public Long mt_product_id;
        /* access modifiers changed from: private */
        public C2444MtProduct mt_product_type;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String page_referrer;
        /* access modifiers changed from: private */
        public Map<String, String> pdp_context;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public String source;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreViewMtPdpEvent:1.0.0";
            this.event_name = "explore_view_mt_pdp";
            this.page = "mt_pdp";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, Long mt_product_id2, C2444MtProduct mt_product_type2, C2443MtPdpReferrer mt_pdp_referrer2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreViewMtPdpEvent:1.0.0";
            this.event_name = "explore_view_mt_pdp";
            this.context = context2;
            this.page = "mt_pdp";
            this.operation = C2451Operation.Impression;
            this.mt_product_id = mt_product_id2;
            this.mt_product_type = mt_product_type2;
            this.mt_pdp_referrer = mt_pdp_referrer2;
        }

        public Builder search_context(C2731SearchContext search_context2) {
            this.search_context = search_context2;
            return this;
        }

        public ExploreViewMtPdpEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.mt_product_id == null) {
                throw new IllegalStateException("Required field 'mt_product_id' is missing");
            } else if (this.mt_product_type == null) {
                throw new IllegalStateException("Required field 'mt_product_type' is missing");
            } else if (this.mt_pdp_referrer != null) {
                return new ExploreViewMtPdpEvent(this);
            } else {
                throw new IllegalStateException("Required field 'mt_pdp_referrer' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreViewMtPdpEvent$ExploreViewMtPdpEventAdapter */
    private static final class ExploreViewMtPdpEventAdapter implements Adapter<ExploreViewMtPdpEvent, Builder> {
        private ExploreViewMtPdpEventAdapter() {
        }

        public void write(Protocol protocol, ExploreViewMtPdpEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreViewMtPdpEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_product_id", 5, 10);
            protocol.writeI64(struct.mt_product_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_product_type", 6, 8);
            protocol.writeI32(struct.mt_product_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("mt_pdp_referrer", 7, 8);
            protocol.writeI32(struct.mt_pdp_referrer.value);
            protocol.writeFieldEnd();
            if (struct.page_referrer != null) {
                protocol.writeFieldBegin("page_referrer", 8, PassportService.SF_DG11);
                protocol.writeString(struct.page_referrer);
                protocol.writeFieldEnd();
            }
            if (struct.search_context != null) {
                protocol.writeFieldBegin("search_context", 9, PassportService.SF_DG12);
                C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
                protocol.writeFieldEnd();
            }
            if (struct.source != null) {
                protocol.writeFieldBegin("source", 10, PassportService.SF_DG11);
                protocol.writeString(struct.source);
                protocol.writeFieldEnd();
            }
            if (struct.location != null) {
                protocol.writeFieldBegin("location", 11, PassportService.SF_DG11);
                protocol.writeString(struct.location);
                protocol.writeFieldEnd();
            }
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 12, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 13, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.subtab != null) {
                protocol.writeFieldBegin("subtab", 14, 8);
                protocol.writeI32(struct.subtab.value);
                protocol.writeFieldEnd();
            }
            if (struct.pdp_context != null) {
                protocol.writeFieldBegin("pdp_context", 15, 13);
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

    private ExploreViewMtPdpEvent(Builder builder) {
        Map<String, String> map = null;
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.mt_product_id = builder.mt_product_id;
        this.mt_product_type = builder.mt_product_type;
        this.mt_pdp_referrer = builder.mt_pdp_referrer;
        this.page_referrer = builder.page_referrer;
        this.search_context = builder.search_context;
        this.source = builder.source;
        this.location = builder.location;
        this.dates = builder.dates == null ? null : Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.subtab = builder.subtab;
        if (builder.pdp_context != null) {
            map = Collections.unmodifiableMap(builder.pdp_context);
        }
        this.pdp_context = map;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreViewMtPdpEvent)) {
            return false;
        }
        ExploreViewMtPdpEvent that = (ExploreViewMtPdpEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.mt_product_id == that.mt_product_id || this.mt_product_id.equals(that.mt_product_id)) && ((this.mt_product_type == that.mt_product_type || this.mt_product_type.equals(that.mt_product_type)) && ((this.mt_pdp_referrer == that.mt_pdp_referrer || this.mt_pdp_referrer.equals(that.mt_pdp_referrer)) && ((this.page_referrer == that.page_referrer || (this.page_referrer != null && this.page_referrer.equals(that.page_referrer))) && ((this.search_context == that.search_context || (this.search_context != null && this.search_context.equals(that.search_context))) && ((this.source == that.source || (this.source != null && this.source.equals(that.source))) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && (this.subtab == that.subtab || (this.subtab != null && this.subtab.equals(that.subtab))))))))))))))))) {
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
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.mt_product_id.hashCode()) * -2128831035) ^ this.mt_product_type.hashCode()) * -2128831035) ^ this.mt_pdp_referrer.hashCode()) * -2128831035) ^ (this.page_referrer == null ? 0 : this.page_referrer.hashCode())) * -2128831035) ^ (this.search_context == null ? 0 : this.search_context.hashCode())) * -2128831035) ^ (this.source == null ? 0 : this.source.hashCode())) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.subtab == null ? 0 : this.subtab.hashCode())) * -2128831035;
        if (this.pdp_context != null) {
            i = this.pdp_context.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExploreViewMtPdpEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", mt_product_id=" + this.mt_product_id + ", mt_product_type=" + this.mt_product_type + ", mt_pdp_referrer=" + this.mt_pdp_referrer + ", page_referrer=" + this.page_referrer + ", search_context=" + this.search_context + ", source=" + this.source + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", subtab=" + this.subtab + ", pdp_context=" + this.pdp_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
