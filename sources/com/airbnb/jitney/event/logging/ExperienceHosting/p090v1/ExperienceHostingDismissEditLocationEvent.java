package com.airbnb.jitney.event.logging.ExperienceHosting.p090v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Address.p037v1.C1797Address;
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

/* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingDismissEditLocationEvent */
public final class ExperienceHostingDismissEditLocationEvent implements Struct {
    public static final Adapter<ExperienceHostingDismissEditLocationEvent, Object> ADAPTER = new ExperienceHostingDismissEditLocationEventAdapter();
    public final C1797Address address;
    public final Context context;
    public final List<String> dates;
    public final String event_name;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final String text;

    /* renamed from: com.airbnb.jitney.event.logging.ExperienceHosting.v1.ExperienceHostingDismissEditLocationEvent$ExperienceHostingDismissEditLocationEventAdapter */
    private static final class ExperienceHostingDismissEditLocationEventAdapter implements Adapter<ExperienceHostingDismissEditLocationEvent, Object> {
        private ExperienceHostingDismissEditLocationEventAdapter() {
        }

        public void write(Protocol protocol, ExperienceHostingDismissEditLocationEvent struct) throws IOException {
            protocol.writeStructBegin("ExperienceHostingDismissEditLocationEvent");
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
            if (struct.address != null) {
                protocol.writeFieldBegin("address", 7, PassportService.SF_DG12);
                C1797Address.ADAPTER.write(protocol, struct.address);
                protocol.writeFieldEnd();
            }
            if (struct.text != null) {
                protocol.writeFieldBegin("text", 8, PassportService.SF_DG11);
                protocol.writeString(struct.text);
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
        if (!(other instanceof ExperienceHostingDismissEditLocationEvent)) {
            return false;
        }
        ExperienceHostingDismissEditLocationEvent that = (ExperienceHostingDismissEditLocationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.dates == that.dates || this.dates.equals(that.dates)) && (this.address == that.address || (this.address != null && this.address.equals(that.address)))))))))) {
            if (this.text == that.text) {
                return true;
            }
            if (this.text != null && this.text.equals(that.text)) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.dates.hashCode()) * -2128831035) ^ (this.address == null ? 0 : this.address.hashCode())) * -2128831035;
        if (this.text != null) {
            i = this.text.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ExperienceHostingDismissEditLocationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", product_info=" + this.product_info + ", operation=" + this.operation + ", dates=" + this.dates + ", address=" + this.address + ", text=" + this.text + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
