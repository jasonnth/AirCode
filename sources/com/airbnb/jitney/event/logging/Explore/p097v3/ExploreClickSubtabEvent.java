package com.airbnb.jitney.event.logging.Explore.p097v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
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

/* renamed from: com.airbnb.jitney.event.logging.Explore.v3.ExploreClickSubtabEvent */
public final class ExploreClickSubtabEvent implements Struct {
    public static final Adapter<ExploreClickSubtabEvent, Builder> ADAPTER = new ExploreClickSubtabEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final List<String> dates_next;
    public final String event_name;
    public final Long guests;
    public final String location;
    public final String location_next;
    public final Boolean new_query;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final C2139ExploreSubtab subtab;
    public final C2139ExploreSubtab subtab_previous;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v3.ExploreClickSubtabEvent$Builder */
    public static final class Builder implements StructBuilder<ExploreClickSubtabEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public List<String> dates;
        /* access modifiers changed from: private */
        public List<String> dates_next;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public String location;
        /* access modifiers changed from: private */
        public String location_next;
        /* access modifiers changed from: private */
        public Boolean new_query;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab;
        /* access modifiers changed from: private */
        public C2139ExploreSubtab subtab_previous;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickSubtabEvent:3.0.0";
            this.event_name = "explore_click_subtab";
            this.page = P3Arguments.FROM_EXPLORE;
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, String target2, C2139ExploreSubtab subtab2, C2139ExploreSubtab subtab_previous2, C2731SearchContext search_context2, Boolean new_query2) {
            this.schema = "com.airbnb.jitney.event.logging.Explore:ExploreClickSubtabEvent:3.0.0";
            this.event_name = "explore_click_subtab";
            this.context = context2;
            this.page = P3Arguments.FROM_EXPLORE;
            this.target = target2;
            this.operation = C2451Operation.Click;
            this.subtab = subtab2;
            this.subtab_previous = subtab_previous2;
            this.search_context = search_context2;
            this.new_query = new_query2;
        }

        public Builder location(String location2) {
            this.location = location2;
            return this;
        }

        public Builder dates(List<String> dates2) {
            this.dates = dates2;
            return this;
        }

        public Builder guests(Long guests2) {
            this.guests = guests2;
            return this;
        }

        public Builder location_next(String location_next2) {
            this.location_next = location_next2;
            return this;
        }

        public ExploreClickSubtabEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.subtab == null) {
                throw new IllegalStateException("Required field 'subtab' is missing");
            } else if (this.subtab_previous == null) {
                throw new IllegalStateException("Required field 'subtab_previous' is missing");
            } else if (this.search_context == null) {
                throw new IllegalStateException("Required field 'search_context' is missing");
            } else if (this.new_query != null) {
                return new ExploreClickSubtabEvent(this);
            } else {
                throw new IllegalStateException("Required field 'new_query' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Explore.v3.ExploreClickSubtabEvent$ExploreClickSubtabEventAdapter */
    private static final class ExploreClickSubtabEventAdapter implements Adapter<ExploreClickSubtabEvent, Builder> {
        private ExploreClickSubtabEventAdapter() {
        }

        public void write(Protocol protocol, ExploreClickSubtabEvent struct) throws IOException {
            protocol.writeStructBegin("ExploreClickSubtabEvent");
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
            if (struct.location != null) {
                protocol.writeFieldBegin("location", 6, PassportService.SF_DG11);
                protocol.writeString(struct.location);
                protocol.writeFieldEnd();
            }
            if (struct.dates != null) {
                protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 7, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
                for (String item0 : struct.dates) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 8, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("subtab", 9, 8);
            protocol.writeI32(struct.subtab.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("subtab_previous", 10, 8);
            protocol.writeI32(struct.subtab_previous.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 11, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("new_query", 12, 2);
            protocol.writeBool(struct.new_query.booleanValue());
            protocol.writeFieldEnd();
            if (struct.location_next != null) {
                protocol.writeFieldBegin("location_next", 13, PassportService.SF_DG11);
                protocol.writeString(struct.location_next);
                protocol.writeFieldEnd();
            }
            if (struct.dates_next != null) {
                protocol.writeFieldBegin("dates_next", 14, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.dates_next.size());
                for (String item02 : struct.dates_next) {
                    protocol.writeString(item02);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ExploreClickSubtabEvent(Builder builder) {
        List<String> list = null;
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.location = builder.location;
        this.dates = builder.dates == null ? null : Collections.unmodifiableList(builder.dates);
        this.guests = builder.guests;
        this.subtab = builder.subtab;
        this.subtab_previous = builder.subtab_previous;
        this.search_context = builder.search_context;
        this.new_query = builder.new_query;
        this.location_next = builder.location_next;
        if (builder.dates_next != null) {
            list = Collections.unmodifiableList(builder.dates_next);
        }
        this.dates_next = list;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ExploreClickSubtabEvent)) {
            return false;
        }
        ExploreClickSubtabEvent that = (ExploreClickSubtabEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.location == that.location || (this.location != null && this.location.equals(that.location))) && ((this.dates == that.dates || (this.dates != null && this.dates.equals(that.dates))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && ((this.subtab_previous == that.subtab_previous || this.subtab_previous.equals(that.subtab_previous)) && ((this.search_context == that.search_context || this.search_context.equals(that.search_context)) && ((this.new_query == that.new_query || this.new_query.equals(that.new_query)) && (this.location_next == that.location_next || (this.location_next != null && this.location_next.equals(that.location_next)))))))))))))))) {
            if (this.dates_next == that.dates_next) {
                return true;
            }
            if (this.dates_next != null && this.dates_next.equals(that.dates_next)) {
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
        int code = (((((((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ (this.location == null ? 0 : this.location.hashCode())) * -2128831035) ^ (this.dates == null ? 0 : this.dates.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.subtab_previous.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035) ^ this.new_query.hashCode()) * -2128831035) ^ (this.location_next == null ? 0 : this.location_next.hashCode())) * -2128831035;
        if (this.dates_next != null) {
            i = this.dates_next.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExploreClickSubtabEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", location=" + this.location + ", dates=" + this.dates + ", guests=" + this.guests + ", subtab=" + this.subtab + ", subtab_previous=" + this.subtab_previous + ", search_context=" + this.search_context + ", new_query=" + this.new_query + ", location_next=" + this.location_next + ", dates_next=" + this.dates_next + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
