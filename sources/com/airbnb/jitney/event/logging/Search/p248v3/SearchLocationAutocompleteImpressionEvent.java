package com.airbnb.jitney.event.logging.Search.p248v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.AutocompletionTuple.p040v3.C1803AutocompletionTuple;
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

/* renamed from: com.airbnb.jitney.event.logging.Search.v3.SearchLocationAutocompleteImpressionEvent */
public final class SearchLocationAutocompleteImpressionEvent implements Struct {
    public static final Adapter<SearchLocationAutocompleteImpressionEvent, Builder> ADAPTER = new SearchLocationAutocompleteImpressionEventAdapter();
    public final C1803AutocompletionTuple autocomplete_suggestion_clicked;
    public final List<C1803AutocompletionTuple> autocomplete_suggestions;
    public final Long cdn_cache_max_age;
    public final Context context;
    public final String event_name;
    public final Long latency_ms;
    public final String location_input;
    public final C2451Operation operation;
    public final String schema;
    public final String user_market;

    /* renamed from: com.airbnb.jitney.event.logging.Search.v3.SearchLocationAutocompleteImpressionEvent$Builder */
    public static final class Builder implements StructBuilder<SearchLocationAutocompleteImpressionEvent> {
        /* access modifiers changed from: private */
        public C1803AutocompletionTuple autocomplete_suggestion_clicked;
        /* access modifiers changed from: private */
        public List<C1803AutocompletionTuple> autocomplete_suggestions;
        /* access modifiers changed from: private */
        public Long cdn_cache_max_age;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "search_location_autocomplete_impression";
        /* access modifiers changed from: private */
        public Long latency_ms;
        /* access modifiers changed from: private */
        public String location_input;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Search:SearchLocationAutocompleteImpressionEvent:3.0.0";
        /* access modifiers changed from: private */
        public String user_market;

        private Builder() {
        }

        public Builder(Context context2, C2451Operation operation2, String location_input2, List<C1803AutocompletionTuple> autocomplete_suggestions2) {
            this.context = context2;
            this.operation = operation2;
            this.location_input = location_input2;
            this.autocomplete_suggestions = autocomplete_suggestions2;
        }

        public Builder autocomplete_suggestion_clicked(C1803AutocompletionTuple autocomplete_suggestion_clicked2) {
            this.autocomplete_suggestion_clicked = autocomplete_suggestion_clicked2;
            return this;
        }

        public Builder user_market(String user_market2) {
            this.user_market = user_market2;
            return this;
        }

        public Builder latency_ms(Long latency_ms2) {
            this.latency_ms = latency_ms2;
            return this;
        }

        public SearchLocationAutocompleteImpressionEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.location_input == null) {
                throw new IllegalStateException("Required field 'location_input' is missing");
            } else if (this.autocomplete_suggestions != null) {
                return new SearchLocationAutocompleteImpressionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'autocomplete_suggestions' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Search.v3.SearchLocationAutocompleteImpressionEvent$SearchLocationAutocompleteImpressionEventAdapter */
    private static final class SearchLocationAutocompleteImpressionEventAdapter implements Adapter<SearchLocationAutocompleteImpressionEvent, Builder> {
        private SearchLocationAutocompleteImpressionEventAdapter() {
        }

        public void write(Protocol protocol, SearchLocationAutocompleteImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("SearchLocationAutocompleteImpressionEvent");
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
            protocol.writeFieldBegin("location_input", 4, PassportService.SF_DG11);
            protocol.writeString(struct.location_input);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("autocomplete_suggestions", 5, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.autocomplete_suggestions.size());
            for (C1803AutocompletionTuple item0 : struct.autocomplete_suggestions) {
                C1803AutocompletionTuple.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.autocomplete_suggestion_clicked != null) {
                protocol.writeFieldBegin("autocomplete_suggestion_clicked", 6, PassportService.SF_DG12);
                C1803AutocompletionTuple.ADAPTER.write(protocol, struct.autocomplete_suggestion_clicked);
                protocol.writeFieldEnd();
            }
            if (struct.user_market != null) {
                protocol.writeFieldBegin("user_market", 7, PassportService.SF_DG11);
                protocol.writeString(struct.user_market);
                protocol.writeFieldEnd();
            }
            if (struct.latency_ms != null) {
                protocol.writeFieldBegin("latency_ms", 8, 10);
                protocol.writeI64(struct.latency_ms.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.cdn_cache_max_age != null) {
                protocol.writeFieldBegin("cdn_cache_max_age", 9, 10);
                protocol.writeI64(struct.cdn_cache_max_age.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SearchLocationAutocompleteImpressionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.location_input = builder.location_input;
        this.autocomplete_suggestions = Collections.unmodifiableList(builder.autocomplete_suggestions);
        this.autocomplete_suggestion_clicked = builder.autocomplete_suggestion_clicked;
        this.user_market = builder.user_market;
        this.latency_ms = builder.latency_ms;
        this.cdn_cache_max_age = builder.cdn_cache_max_age;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SearchLocationAutocompleteImpressionEvent)) {
            return false;
        }
        SearchLocationAutocompleteImpressionEvent that = (SearchLocationAutocompleteImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.location_input == that.location_input || this.location_input.equals(that.location_input)) && ((this.autocomplete_suggestions == that.autocomplete_suggestions || this.autocomplete_suggestions.equals(that.autocomplete_suggestions)) && ((this.autocomplete_suggestion_clicked == that.autocomplete_suggestion_clicked || (this.autocomplete_suggestion_clicked != null && this.autocomplete_suggestion_clicked.equals(that.autocomplete_suggestion_clicked))) && ((this.user_market == that.user_market || (this.user_market != null && this.user_market.equals(that.user_market))) && (this.latency_ms == that.latency_ms || (this.latency_ms != null && this.latency_ms.equals(that.latency_ms))))))))))) {
            if (this.cdn_cache_max_age == that.cdn_cache_max_age) {
                return true;
            }
            if (this.cdn_cache_max_age != null && this.cdn_cache_max_age.equals(that.cdn_cache_max_age)) {
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
        int code = (((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.location_input.hashCode()) * -2128831035) ^ this.autocomplete_suggestions.hashCode()) * -2128831035) ^ (this.autocomplete_suggestion_clicked == null ? 0 : this.autocomplete_suggestion_clicked.hashCode())) * -2128831035) ^ (this.user_market == null ? 0 : this.user_market.hashCode())) * -2128831035) ^ (this.latency_ms == null ? 0 : this.latency_ms.hashCode())) * -2128831035;
        if (this.cdn_cache_max_age != null) {
            i = this.cdn_cache_max_age.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SearchLocationAutocompleteImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", location_input=" + this.location_input + ", autocomplete_suggestions=" + this.autocomplete_suggestions + ", autocomplete_suggestion_clicked=" + this.autocomplete_suggestion_clicked + ", user_market=" + this.user_market + ", latency_ms=" + this.latency_ms + ", cdn_cache_max_age=" + this.cdn_cache_max_age + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
