package com.airbnb.jitney.event.logging.ExperiencePDP.p091v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ProductInfo.p210v1.C2587ProductInfo;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExperiencePDP.v1.ExperiencePDPSwipeSuggestionsCarouselEvent */
public final class ExperiencePDPSwipeSuggestionsCarouselEvent implements Struct {
    public static final Adapter<ExperiencePDPSwipeSuggestionsCarouselEvent, Object> ADAPTER = new ExperiencePDPSwipeSuggestionsCarouselEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long max_scroll_item_index;
    public final C2451Operation operation;
    public final String page;
    public final C2587ProductInfo product_info;
    public final String schema;
    public final String section;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.ExperiencePDP.v1.ExperiencePDPSwipeSuggestionsCarouselEvent$ExperiencePDPSwipeSuggestionsCarouselEventAdapter */
    private static final class ExperiencePDPSwipeSuggestionsCarouselEventAdapter implements Adapter<ExperiencePDPSwipeSuggestionsCarouselEvent, Object> {
        private ExperiencePDPSwipeSuggestionsCarouselEventAdapter() {
        }

        public void write(Protocol protocol, ExperiencePDPSwipeSuggestionsCarouselEvent struct) throws IOException {
            protocol.writeStructBegin("ExperiencePDPSwipeSuggestionsCarouselEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("product_info", 7, PassportService.SF_DG12);
            C2587ProductInfo.ADAPTER.write(protocol, struct.product_info);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("max_scroll_item_index", 8, 10);
            protocol.writeI64(struct.max_scroll_item_index.longValue());
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
        if (!(other instanceof ExperiencePDPSwipeSuggestionsCarouselEvent)) {
            return false;
        }
        ExperiencePDPSwipeSuggestionsCarouselEvent that = (ExperiencePDPSwipeSuggestionsCarouselEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.section == that.section || this.section.equals(that.section)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.product_info == that.product_info || this.product_info.equals(that.product_info)) && (this.max_scroll_item_index == that.max_scroll_item_index || this.max_scroll_item_index.equals(that.max_scroll_item_index)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.section.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.product_info.hashCode()) * -2128831035) ^ this.max_scroll_item_index.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExperiencePDPSwipeSuggestionsCarouselEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", section=" + this.section + ", target=" + this.target + ", operation=" + this.operation + ", product_info=" + this.product_info + ", max_scroll_item_index=" + this.max_scroll_item_index + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
