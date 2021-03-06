package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
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

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickCallAirbnbEvent */
public final class ExperienceHostingClickCallAirbnbEvent implements Struct {
    public static final Adapter<ExperienceHostingClickCallAirbnbEvent, Object> ADAPTER = new ExperienceHostingClickCallAirbnbEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickCallAirbnbEvent$ExperienceHostingClickCallAirbnbEventAdapter */
    private static final class ExperienceHostingClickCallAirbnbEventAdapter implements Adapter<ExperienceHostingClickCallAirbnbEvent, Object> {
        private ExperienceHostingClickCallAirbnbEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingClickCallAirbnbEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingClickCallAirbnbEvent");
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
            protocol.writeFieldBegin("product_info", 4, PassportService.SF_DG12);
            C2587ProductInfo.ADAPTER.write(protocol, struct.product_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 6, 15);
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
        if (!(other instanceof ExperienceHostingClickCallAirbnbEvent)) {
            return false;
        }
        ExperienceHostingClickCallAirbnbEvent that = (ExperienceHostingClickCallAirbnbEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.dates == that.dates || this.dates.equals(that.dates)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingClickCallAirbnbEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", product_info=" + this.product_info + ", operation=" + this.operation + ", dates=" + this.dates + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
