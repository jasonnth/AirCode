package com.airbnb.jitney.event.logging.RefineFilterSuggestion.p220v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.RefineFilterSuggestion.v1.RefineFilterSuggestionDollarsPriceFilterEvent */
public final class RefineFilterSuggestionDollarsPriceFilterEvent implements Struct {
    public static final Adapter<RefineFilterSuggestionDollarsPriceFilterEvent, Object> ADAPTER = new RefineFilterSuggestionDollarsPriceFilterEventAdapter();
    public final Context context;
    public final List<Long> dollars_max_price;
    public final List<Long> dollars_min_price;
    public final Long dollars_option_clicked;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final C2731SearchContext search_context;

    /* renamed from: com.airbnb.jitney.event.logging.RefineFilterSuggestion.v1.RefineFilterSuggestionDollarsPriceFilterEvent$RefineFilterSuggestionDollarsPriceFilterEventAdapter */
    private static final class RefineFilterSuggestionDollarsPriceFilterEventAdapter implements Adapter<RefineFilterSuggestionDollarsPriceFilterEvent, Object> {
        private RefineFilterSuggestionDollarsPriceFilterEventAdapter() {
        }

        public void write(Protocol protocol, RefineFilterSuggestionDollarsPriceFilterEvent struct) throws IOException {
            protocol.writeStructBegin("RefineFilterSuggestionDollarsPriceFilterEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("dollars_max_price", 5, 15);
            protocol.writeListBegin(10, struct.dollars_max_price.size());
            for (Long item0 : struct.dollars_max_price) {
                protocol.writeI64(item0.longValue());
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("dollars_min_price", 6, 15);
            protocol.writeListBegin(10, struct.dollars_min_price.size());
            for (Long item02 : struct.dollars_min_price) {
                protocol.writeI64(item02.longValue());
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            if (struct.dollars_option_clicked != null) {
                protocol.writeFieldBegin("dollars_option_clicked", 7, 10);
                protocol.writeI64(struct.dollars_option_clicked.longValue());
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
        if (!(other instanceof RefineFilterSuggestionDollarsPriceFilterEvent)) {
            return false;
        }
        RefineFilterSuggestionDollarsPriceFilterEvent that = (RefineFilterSuggestionDollarsPriceFilterEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.search_context == that.search_context || this.search_context.equals(that.search_context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.dollars_max_price == that.dollars_max_price || this.dollars_max_price.equals(that.dollars_max_price)) && (this.dollars_min_price == that.dollars_min_price || this.dollars_min_price.equals(that.dollars_min_price)))))))) {
            if (this.dollars_option_clicked == that.dollars_option_clicked) {
                return true;
            }
            if (this.dollars_option_clicked != null && this.dollars_option_clicked.equals(that.dollars_option_clicked)) {
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
        int code = (((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.dollars_max_price.hashCode()) * -2128831035) ^ this.dollars_min_price.hashCode()) * -2128831035;
        if (this.dollars_option_clicked != null) {
            i = this.dollars_option_clicked.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "RefineFilterSuggestionDollarsPriceFilterEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", search_context=" + this.search_context + ", operation=" + this.operation + ", dollars_max_price=" + this.dollars_max_price + ", dollars_min_price=" + this.dollars_min_price + ", dollars_option_clicked=" + this.dollars_option_clicked + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
