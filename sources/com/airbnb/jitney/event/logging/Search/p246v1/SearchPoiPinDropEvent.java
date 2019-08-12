package com.airbnb.jitney.event.logging.Search.p246v1;

import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.LatLngPair.p133v1.C2368LatLngPair;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchPoiPinDropEvent */
public final class SearchPoiPinDropEvent implements Struct {
    public static final Adapter<SearchPoiPinDropEvent, Object> ADAPTER = new SearchPoiPinDropEventAdapter();
    public final String api_place_id;
    public final String api_place_name;
    public final Context context;
    public final String event_name;
    public final C2368LatLngPair lat_lng;
    public final String page;
    public final String schema;
    public final C2731SearchContext search_context;
    public final C2139ExploreSubtab subtab;

    /* renamed from: com.airbnb.jitney.event.logging.Search.v1.SearchPoiPinDropEvent$SearchPoiPinDropEventAdapter */
    private static final class SearchPoiPinDropEventAdapter implements Adapter<SearchPoiPinDropEvent, Object> {
        private SearchPoiPinDropEventAdapter() {
        }

        public void write(Protocol protocol, SearchPoiPinDropEvent struct) throws IOException {
            protocol.writeStructBegin("SearchPoiPinDropEvent");
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
            protocol.writeFieldBegin("api_place_id", 4, PassportService.SF_DG11);
            protocol.writeString(struct.api_place_id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("api_place_name", 5, PassportService.SF_DG11);
            protocol.writeString(struct.api_place_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("lat_lng", 6, PassportService.SF_DG12);
            C2368LatLngPair.ADAPTER.write(protocol, struct.lat_lng);
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

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SearchPoiPinDropEvent)) {
            return false;
        }
        SearchPoiPinDropEvent that = (SearchPoiPinDropEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.api_place_id == that.api_place_id || this.api_place_id.equals(that.api_place_id)) && ((this.api_place_name == that.api_place_name || this.api_place_name.equals(that.api_place_name)) && ((this.lat_lng == that.lat_lng || this.lat_lng.equals(that.lat_lng)) && ((this.subtab == that.subtab || this.subtab.equals(that.subtab)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.api_place_id.hashCode()) * -2128831035) ^ this.api_place_name.hashCode()) * -2128831035) ^ this.lat_lng.hashCode()) * -2128831035) ^ this.subtab.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SearchPoiPinDropEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", api_place_id=" + this.api_place_id + ", api_place_name=" + this.api_place_name + ", lat_lng=" + this.lat_lng + ", subtab=" + this.subtab + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
