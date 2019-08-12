package com.airbnb.jitney.event.logging.Search.p246v1;

import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchHiddenListingsSearchEvent */
public final class SearchHiddenListingsSearchEvent implements Struct {
    public static final Adapter<SearchHiddenListingsSearchEvent, Object> ADAPTER = new SearchHiddenListingsSearchEventAdapter();
    public final Context context;
    public final String event_name;
    public final String schema;
    public final C2731SearchContext search_context;

    /* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchHiddenListingsSearchEvent$SearchHiddenListingsSearchEventAdapter */
    private static final class SearchHiddenListingsSearchEventAdapter implements Adapter<SearchHiddenListingsSearchEvent, Object> {
        private SearchHiddenListingsSearchEventAdapter() {
        }

        public void write(Protocol protocol, SearchHiddenListingsSearchEvent struct) throws IOException {
            protocol.writeStructBegin("SearchHiddenListingsSearchEvent");
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
            protocol.writeFieldBegin("search_context", 3, PassportService.SF_DG12);
            C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
            protocol.writeFieldEnd();
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
        if (!(other instanceof SearchHiddenListingsSearchEvent)) {
            return false;
        }
        SearchHiddenListingsSearchEvent that = (SearchHiddenListingsSearchEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SearchHiddenListingsSearchEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
