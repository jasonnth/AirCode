package com.airbnb.jitney.event.logging.Explore.p095v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ExpansionMethod.p089v1.C1990ExpansionMethod;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.FilterSection.p100v1.C2141FilterSection;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneClickSeeAllEvent */
public final class ExploreFiltersPaneClickSeeAllEvent implements Struct {
    public static final Adapter<ExploreFiltersPaneClickSeeAllEvent, Builder> ADAPTER = new ExploreFiltersPaneClickSeeAllEventAdapter();
    public final Context context;
    public final String event_name;
    public final C1990ExpansionMethod expansion_method;
    public final C2141FilterSection filter_section;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final C2139ExploreSubtab subtab;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneClickSeeAllEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreFiltersPaneClickSeeAllEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C1990ExpansionMethod expansion_method;
        /* access modifiers changed from: private */
        public C2141FilterSection filter_section;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneClickSeeAllEvent:1.0.0";
            this.event_name = "explore_filters_pane_click_see_all";
            this.page = ShareConstants.WEB_DIALOG_PARAM_FILTERS;
            this.target = "see_all";
        }

        public Builder(Context context2, C2141FilterSection filter_section2, C1990ExpansionMethod expansion_method2, C2139ExploreSubtab subtab2, C2731SearchContext search_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreFiltersPaneClickSeeAllEvent:1.0.0";
            this.event_name = "explore_filters_pane_click_see_all";
            this.context = context2;
            this.page = ShareConstants.WEB_DIALOG_PARAM_FILTERS;
            this.filter_section = filter_section2;
            this.target = "see_all";
            this.expansion_method = expansion_method2;
            this.subtab = subtab2;
            this.search_context = search_context2;
        }

        public ExploreFiltersPaneClickSeeAllEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.filter_section == null) {
                throw new IllegalStateException("Required field 'filter_section' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.expansion_method == null) {
                throw new IllegalStateException("Required field 'expansion_method' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.search_context != null) {
                return new ExploreFiltersPaneClickSeeAllEvent(this);
            } else {
                throw new IllegalStateException("Required field 'search_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v1.ExploreFiltersPaneClickSeeAllEvent$ExploreFiltersPaneClickSeeAllEventAdapter */
    private static final class ExploreFiltersPaneClickSeeAllEventAdapter implements Adapter<ExploreFiltersPaneClickSeeAllEvent, Builder> {
        private ExploreFiltersPaneClickSeeAllEventAdapter() {
        }

        public void write(Protocol protocol, ExploreFiltersPaneClickSeeAllEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreFiltersPaneClickSeeAllEvent");
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
            protocol.writeFieldBegin("filter_section", 4, 8);
            protocol.writeI32(struct.filter_section.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("expansion_method", 6, 8);
            protocol.writeI32(struct.expansion_method.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab", 7, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 8, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreFiltersPaneClickSeeAllEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.filter_section = builder.filter_section;
        this.target = builder.target;
        this.expansion_method = builder.expansion_method;
        this.subtab = builder.subtab;
        this.search_context = builder.search_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreFiltersPaneClickSeeAllEvent)) {
            return false;
        }
        ExploreFiltersPaneClickSeeAllEvent that = (ExploreFiltersPaneClickSeeAllEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.filter_section == that.filter_section || this.filter_section.equals(that.filter_section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.expansion_method == that.expansion_method || this.expansion_method.equals(that.expansion_method)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.filter_section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.expansion_method.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExploreFiltersPaneClickSeeAllEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", filter_section=" + this.filter_section + ", target=" + this.target + ", expansion_method=" + this.expansion_method + ", subtab=" + this.subtab + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
