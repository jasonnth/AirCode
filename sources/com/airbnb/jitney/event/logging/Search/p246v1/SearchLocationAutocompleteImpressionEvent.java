package com.airbnb.jitney.event.logging.Search.p246v1;

import com.airbnb.jitney.event.logging.AutocompletionTuple.p038v1.C1799AutocompletionTuple;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchLocationAutocompleteImpressionEvent */
public final class SearchLocationAutocompleteImpressionEvent implements Struct {
    public static final Adapter<SearchLocationAutocompleteImpressionEvent, Object> ADAPTER = new SearchLocationAutocompleteImpressionEventAdapter();
    public final C1799AutocompletionTuple autocomplete_suggestion_clicked;
    public final List<C1799AutocompletionTuple> autocomplete_suggestions;
    public final Context context;
    public final String event_name;
    public final String location_input;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchLocationAutocompleteImpressionEvent$SearchLocationAutocompleteImpressionEventAdapter */
    private static final class SearchLocationAutocompleteImpressionEventAdapter implements Adapter<SearchLocationAutocompleteImpressionEvent, Object> {
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
            protocol.writeFieldBegin("location_input", 3, PassportService.SF_DG11);
            protocol.writeString(struct.location_input);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("autocomplete_suggestions", 4, 15);
            protocol.writeListBegin(PassportService.SF_DG12, struct.autocomplete_suggestions.size());
            for (C1799AutocompletionTuple item0 : struct.autocomplete_suggestions) {
                C1799AutocompletionTuple.ADAPTER.write(protocol, item0);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.autocomplete_suggestion_clicked != null) {
                protocol.writeFieldBegin("autocomplete_suggestion_clicked", 5, PassportService.SF_DG12);
                C1799AutocompletionTuple.ADAPTER.write(protocol, struct.autocomplete_suggestion_clicked);
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
        if (!(other instanceof SearchLocationAutocompleteImpressionEvent)) {
            return false;
        }
        SearchLocationAutocompleteImpressionEvent that = (SearchLocationAutocompleteImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.location_input == that.location_input || this.location_input.equals(that.location_input)) && (this.autocomplete_suggestions == that.autocomplete_suggestions || this.autocomplete_suggestions.equals(that.autocomplete_suggestions)))))) {
            if (this.autocomplete_suggestion_clicked == that.autocomplete_suggestion_clicked) {
                return true;
            }
            if (this.autocomplete_suggestion_clicked != null && this.autocomplete_suggestion_clicked.equals(that.autocomplete_suggestion_clicked)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.location_input.hashCode()) * -2128831035) ^ this.autocomplete_suggestions.hashCode()) * -2128831035;
        if (this.autocomplete_suggestion_clicked != null) {
            i = this.autocomplete_suggestion_clicked.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SearchLocationAutocompleteImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", location_input=" + this.location_input + ", autocomplete_suggestions=" + this.autocomplete_suggestions + ", autocomplete_suggestion_clicked=" + this.autocomplete_suggestion_clicked + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
