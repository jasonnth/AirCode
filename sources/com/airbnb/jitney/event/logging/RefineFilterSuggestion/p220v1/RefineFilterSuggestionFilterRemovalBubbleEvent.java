package com.airbnb.jitney.event.logging.RefineFilterSuggestion.p220v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.FilterType.p101v1.C2142FilterType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.RefineFilterSuggestion.v1.RefineFilterSuggestionFilterRemovalBubbleEvent */
public final class RefineFilterSuggestionFilterRemovalBubbleEvent implements Struct {
    public static final Adapter<RefineFilterSuggestionFilterRemovalBubbleEvent, Object> ADAPTER = new RefineFilterSuggestionFilterRemovalBubbleEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2142FilterType filter_type;
    public final String filter_value;
    public final C2451Operation operation;
    public final String schema;
    public final C2731SearchContext search_context;

    /* renamed from: com.airbnb.jitney.event.logging.RefineFilterSuggestion.v1.RefineFilterSuggestionFilterRemovalBubbleEvent$RefineFilterSuggestionFilterRemovalBubbleEventAdapter */
    private static final class RefineFilterSuggestionFilterRemovalBubbleEventAdapter implements Adapter<RefineFilterSuggestionFilterRemovalBubbleEvent, Object> {
        private RefineFilterSuggestionFilterRemovalBubbleEventAdapter() {
        }

        public void write(Protocol protocol, RefineFilterSuggestionFilterRemovalBubbleEvent struct) throws IOException {
            protocol.writeStructBegin("RefineFilterSuggestionFilterRemovalBubbleEvent");
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
            protocol.writeFieldBegin("filter_type", 3, 8);
            protocol.writeI32(struct.filter_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("filter_value", 4, PassportService.SF_DG11);
            protocol.writeString(struct.filter_value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("search_context", 6, PassportService.SF_DG12);
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
        if (!(other instanceof RefineFilterSuggestionFilterRemovalBubbleEvent)) {
            return false;
        }
        RefineFilterSuggestionFilterRemovalBubbleEvent that = (RefineFilterSuggestionFilterRemovalBubbleEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.filter_type == that.filter_type || this.filter_type.equals(that.filter_type)) && ((this.filter_value == that.filter_value || this.filter_value.equals(that.filter_value)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.filter_type.hashCode()) * -2128831035) ^ this.filter_value.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RefineFilterSuggestionFilterRemovalBubbleEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", filter_type=" + this.filter_type + ", filter_value=" + this.filter_value + ", operation=" + this.operation + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
