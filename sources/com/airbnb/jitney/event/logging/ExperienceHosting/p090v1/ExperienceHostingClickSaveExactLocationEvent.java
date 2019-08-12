package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.LatLngBox.p132v1.C2366LatLngBox;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickSaveExactLocationEvent */
public final class ExperienceHostingClickSaveExactLocationEvent implements Struct {
    public static final Adapter<ExperienceHostingClickSaveExactLocationEvent, Object> ADAPTER = new ExperienceHostingClickSaveExactLocationEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final C2366LatLngBox lat_lng_box;
    public final String map_provider;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final String section;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickSaveExactLocationEvent$ExperienceHostingClickSaveExactLocationEventAdapter */
    private static final class ExperienceHostingClickSaveExactLocationEventAdapter implements Adapter<ExperienceHostingClickSaveExactLocationEvent, Object> {
        private ExperienceHostingClickSaveExactLocationEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingClickSaveExactLocationEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingClickSaveExactLocationEvent");
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
            protocol.writeFieldBegin("lat_lng_box", 6, PassportService.SF_DG12);
            C2366LatLngBox.ADAPTER.write(protocol, struct.lat_lng_box);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("product_info", 7, PassportService.SF_DG12);
            C2587ProductInfo.ADAPTER.write(protocol, struct.product_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("map_provider", 8, PassportService.SF_DG11);
            protocol.writeString(struct.map_provider);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 9, 15);
            protocol.writeListBegin(PassportService.SF_DG11, struct.dates.size());
            for (String item0 : struct.dates) {
                protocol.writeString(item0);
            }
            protocol.writeListEnd();
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
        if (!(other instanceof ExperienceHostingClickSaveExactLocationEvent)) {
            return false;
        }
        ExperienceHostingClickSaveExactLocationEvent that = (ExperienceHostingClickSaveExactLocationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.lat_lng_box == that.lat_lng_box || this.lat_lng_box.equals(that.lat_lng_box)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.map_provider == that.map_provider || this.map_provider.equals(that.map_provider)) && (this.dates == that.dates || this.dates.equals(that.dates))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.lat_lng_box.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.map_provider.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingClickSaveExactLocationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", operation=" + this.operation + ", lat_lng_box=" + this.lat_lng_box + ", product_info=" + this.product_info + ", map_provider=" + this.map_provider + ", dates=" + this.dates + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
