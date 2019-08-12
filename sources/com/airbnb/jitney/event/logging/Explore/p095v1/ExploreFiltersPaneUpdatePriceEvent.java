package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
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
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneUpdatePriceEvent */
public final class ExploreFiltersPaneUpdatePriceEvent implements Struct {
    public static final Adapter<ExploreFiltersPaneUpdatePriceEvent, Builder> ADAPTER = new ExploreFiltersPaneUpdatePriceEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final List<Long> price_range;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section;
    public final C2139ExploreSubtab subtab;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneUpdatePriceEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreFiltersPaneUpdatePriceEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public List<Long> price_range;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneUpdatePriceEvent:1.0.0";
            this.event_name = "explore_filters_pane_update_price";
            this.page = "inline_filters";
            this.section = EditPriceFragment.RESULT_PRICE;
            this.operation = C2451Operation.Save;
        }

        public Builder(Context context2, C2139ExploreSubtab subtab2, C2731SearchContext search_context2, List<Long> price_range2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneUpdatePriceEvent:1.0.0";
            this.event_name = "explore_filters_pane_update_price";
            this.context = context2;
            this.page = "inline_filters";
            this.section = EditPriceFragment.RESULT_PRICE;
            this.operation = C2451Operation.Save;
            this.subtab = subtab2;
            this.search_context = search_context2;
            this.price_range = price_range2;
        }

        public ExploreFiltersPaneUpdatePriceEvent build() {
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
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context == null) {
                throw new IllegalStateException("Required field 'search_context' is missing");
            } else if (this.price_range != null) {
                return new ExploreFiltersPaneUpdatePriceEvent(this);
            } else {
                throw new IllegalStateException("Required field 'price_range' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneUpdatePriceEvent$ExploreFiltersPaneUpdatePriceEventAdapter */
    private static final class ExploreFiltersPaneUpdatePriceEventAdapter implements Adapter<ExploreFiltersPaneUpdatePriceEvent, Builder> {
        private ExploreFiltersPaneUpdatePriceEventAdapter() {
        }

        public void write(Protocol protocol, ExploreFiltersPaneUpdatePriceEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreFiltersPaneUpdatePriceEvent");
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
            protocol.writeFieldBegin("subtab", 6, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 7, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.PRICE_RANGE, 8, 15);
            protocol.writeListBegin(10, struct.price_range.size());
            for (Long item0 : struct.price_range) {
                protocol.writeI64(item0.longValue());
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreFiltersPaneUpdatePriceEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.operation = builder.operation;
        this.subtab = builder.subtab;
        this.search_context = builder.search_context;
        this.price_range = Collections.unmodifiableList(builder.price_range);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreFiltersPaneUpdatePriceEvent)) {
            return false;
        }
        ExploreFiltersPaneUpdatePriceEvent that = (ExploreFiltersPaneUpdatePriceEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.search_context == that.search_context || this.search_context.equals(that.search_context)) && (this.price_range == that.price_range || this.price_range.equals(that.price_range)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035) ^ this.price_range.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreFiltersPaneUpdatePriceEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", operation=" + this.operation + ", subtab=" + this.subtab + ", search_context=" + this.search_context + ", price_range=" + this.price_range + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
