package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.ToggleMethod.p268v1.C2759ToggleMethod;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneToggleIbEvent */
public final class ExploreFiltersPaneToggleIbEvent implements Struct {
    public static final Adapter<ExploreFiltersPaneToggleIbEvent, Builder> ADAPTER = new ExploreFiltersPaneToggleIbEventAdapter();
    public final Context context;
    public final String event_name;
    public final Boolean instant_book;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final String section;
    public final C2139ExploreSubtab subtab;
    public final C2759ToggleMethod toggle_method;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneToggleIbEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreFiltersPaneToggleIbEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Boolean instant_book;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public C2759ToggleMethod toggle_method;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneToggleIbEvent:1.0.0";
            this.event_name = "explore_filters_pane_toggle_ib";
            this.page = "inline_filters";
            this.section = "ib";
            this.operation = C2451Operation.Toggle;
        }

        public Builder(Context context2, C2759ToggleMethod toggle_method2, C2139ExploreSubtab subtab2, C2731SearchContext search_context2, Boolean instant_book2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneToggleIbEvent:1.0.0";
            this.event_name = "explore_filters_pane_toggle_ib";
            this.context = context2;
            this.page = "inline_filters";
            this.section = "ib";
            this.operation = C2451Operation.Toggle;
            this.toggle_method = toggle_method2;
            this.subtab = subtab2;
            this.search_context = search_context2;
            this.instant_book = instant_book2;
        }

        public ExploreFiltersPaneToggleIbEvent build() {
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
            } else if (this.toggle_method == null) {
                throw new IllegalStateException("Required field 'toggle_method' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context == null) {
                throw new IllegalStateException("Required field 'search_context' is missing");
            } else if (this.instant_book != null) {
                return new ExploreFiltersPaneToggleIbEvent(this);
            } else {
                throw new IllegalStateException("Required field 'instant_book' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneToggleIbEvent$ExploreFiltersPaneToggleIbEventAdapter */
    private static final class ExploreFiltersPaneToggleIbEventAdapter implements Adapter<ExploreFiltersPaneToggleIbEvent, Builder> {
        private ExploreFiltersPaneToggleIbEventAdapter() {
        }

        public void write(Protocol protocol, ExploreFiltersPaneToggleIbEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreFiltersPaneToggleIbEvent");
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
            protocol.writeFieldBegin("toggle_method", 6, 8);
            protocol.writeI32(struct.toggle_method.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 7, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 8, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingAnalytics.INSTANT_BOOK, 9, 2);
            protocol.writeBool(struct.instant_book.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreFiltersPaneToggleIbEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.section = builder.section;
        this.operation = builder.operation;
        this.toggle_method = builder.toggle_method;
        this.subtab = builder.subtab;
        this.search_context = builder.search_context;
        this.instant_book = builder.instant_book;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreFiltersPaneToggleIbEvent)) {
            return false;
        }
        ExploreFiltersPaneToggleIbEvent that = (ExploreFiltersPaneToggleIbEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.toggle_method == that.toggle_method || this.toggle_method.equals(that.toggle_method)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.search_context == that.search_context || this.search_context.equals(that.search_context)) && (this.instant_book == that.instant_book || this.instant_book.equals(that.instant_book))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.toggle_method.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035) ^ this.instant_book.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreFiltersPaneToggleIbEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", operation=" + this.operation + ", toggle_method=" + this.toggle_method + ", subtab=" + this.subtab + ", search_context=" + this.search_context + ", instant_book=" + this.instant_book + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
