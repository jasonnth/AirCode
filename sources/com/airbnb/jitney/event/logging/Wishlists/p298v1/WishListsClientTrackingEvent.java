package com.airbnb.jitney.event.logging.Wishlists.p298v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.WishListsClientTrackingEvent */
public final class WishListsClientTrackingEvent implements Struct {
    public static final Adapter<WishListsClientTrackingEvent, Object> ADAPTER = new WishListsClientTrackingEventAdapter();
    public final Context context;
    public final DetailsPageTracking details_page;
    public final String event_name;
    public final IndexPageTracking index_page;
    public final NewListModalTracking new_list_modal;
    public final OutsideAppTracking outside_app;
    public final Page page;
    public final String schema;
    public final Scope scope;
    public final Section section;

    /* renamed from: com.airbnb.jitney.event.logging.Wishlists.v1.WishListsClientTrackingEvent$WishListsClientTrackingEventAdapter */
    private static final class WishListsClientTrackingEventAdapter implements Adapter<WishListsClientTrackingEvent, Object> {
        private WishListsClientTrackingEventAdapter() {
        }

        public void write(Protocol protocol, WishListsClientTrackingEvent struct) throws IOException {
            protocol.writeStructBegin("WishListsClientTrackingEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 1, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("event_name", 2, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("scope", 3, 8);
            protocol.writeI32(struct.scope.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 4, 8);
            protocol.writeI32(struct.page.value);
            protocol.writeFieldEnd();
            if (struct.section != null) {
                protocol.writeFieldBegin(BaseAnalytics.SECTION, 5, 8);
                protocol.writeI32(struct.section.value);
                protocol.writeFieldEnd();
            }
            if (struct.outside_app != null) {
                protocol.writeFieldBegin("outside_app", 6, PassportService.SF_DG12);
                OutsideAppTracking.ADAPTER.write(protocol, struct.outside_app);
                protocol.writeFieldEnd();
            }
            if (struct.index_page != null) {
                protocol.writeFieldBegin("index_page", 7, PassportService.SF_DG12);
                IndexPageTracking.ADAPTER.write(protocol, struct.index_page);
                protocol.writeFieldEnd();
            }
            if (struct.new_list_modal != null) {
                protocol.writeFieldBegin("new_list_modal", 8, PassportService.SF_DG12);
                NewListModalTracking.ADAPTER.write(protocol, struct.new_list_modal);
                protocol.writeFieldEnd();
            }
            if (struct.details_page != null) {
                protocol.writeFieldBegin("details_page", 9, PassportService.SF_DG12);
                DetailsPageTracking.ADAPTER.write(protocol, struct.details_page);
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
        if (!(other instanceof WishListsClientTrackingEvent)) {
            return false;
        }
        WishListsClientTrackingEvent that = (WishListsClientTrackingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.context == that.context || this.context.equals(that.context)) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.scope == that.scope || this.scope.equals(that.scope)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || (this.section != null && this.section.equals(that.section))) && ((this.outside_app == that.outside_app || (this.outside_app != null && this.outside_app.equals(that.outside_app))) && ((this.index_page == that.index_page || (this.index_page != null && this.index_page.equals(that.index_page))) && (this.new_list_modal == that.new_list_modal || (this.new_list_modal != null && this.new_list_modal.equals(that.new_list_modal))))))))))) {
            if (this.details_page == that.details_page) {
                return true;
            }
            if (this.details_page != null && this.details_page.equals(that.details_page)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.scope.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ (this.section == null ? 0 : this.section.hashCode())) * -2128831035) ^ (this.outside_app == null ? 0 : this.outside_app.hashCode())) * -2128831035) ^ (this.index_page == null ? 0 : this.index_page.hashCode())) * -2128831035) ^ (this.new_list_modal == null ? 0 : this.new_list_modal.hashCode())) * -2128831035;
        if (this.details_page != null) {
            i = this.details_page.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "WishListsClientTrackingEvent{schema=" + this.schema + ", context=" + this.context + ", event_name=" + this.event_name + ", scope=" + this.scope + ", page=" + this.page + ", section=" + this.section + ", outside_app=" + this.outside_app + ", index_page=" + this.index_page + ", new_list_modal=" + this.new_list_modal + ", details_page=" + this.details_page + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
