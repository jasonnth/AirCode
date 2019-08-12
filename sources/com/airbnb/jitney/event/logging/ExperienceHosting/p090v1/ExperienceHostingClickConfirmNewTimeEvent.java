package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.TimeRange.p265v1.C2753TimeRange;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickConfirmNewTimeEvent */
public final class ExperienceHostingClickConfirmNewTimeEvent implements Struct {
    public static final Adapter<ExperienceHostingClickConfirmNewTimeEvent, Object> ADAPTER = new ExperienceHostingClickConfirmNewTimeEventAdapter();
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final C2753TimeRange time;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingClickConfirmNewTimeEvent$ExperienceHostingClickConfirmNewTimeEventAdapter */
    private static final class ExperienceHostingClickConfirmNewTimeEventAdapter implements Adapter<ExperienceHostingClickConfirmNewTimeEvent, Object> {
        private ExperienceHostingClickConfirmNewTimeEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingClickConfirmNewTimeEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingClickConfirmNewTimeEvent");
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
            if (struct.time != null) {
                protocol.writeFieldBegin("time", 6, PassportService.SF_DG12);
                C2753TimeRange.ADAPTER.write(protocol, struct.time);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_DATES, 7, 15);
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
        if (!(other instanceof ExperienceHostingClickConfirmNewTimeEvent)) {
            return false;
        }
        ExperienceHostingClickConfirmNewTimeEvent that = (ExperienceHostingClickConfirmNewTimeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.time == that.time || (this.time != null && this.time.equals(that.time))) && (this.dates == that.dates || this.dates.equals(that.dates))))))))) {
            return true;
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.time != null) {
            i = this.time.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.dates.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingClickConfirmNewTimeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", product_info=" + this.product_info + ", operation=" + this.operation + ", time=" + this.time + ", dates=" + this.dates + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
