package com.airbnb.jitney.event.logging.RefineFilterSuggestion.p220v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.FilterType.p101v1.C2142FilterType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.RefineFilterSuggestion.v1.RefineFilterSuggestionFilterSuggestionEvent */
public final class RefineFilterSuggestionFilterSuggestionEvent implements Struct {
    public static final Adapter<RefineFilterSuggestionFilterSuggestionEvent, Builder> ADAPTER = new RefineFilterSuggestionFilterSuggestionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2142FilterType filter_type;
    public final String filter_value;
    public final C2451Operation operation;
    public final String schema;
    public final C2731SearchContext search_context;

    /* renamed from: com.airbnb.jitney.event.logging.RefineFilterSuggestion.v1.RefineFilterSuggestionFilterSuggestionEvent$Builder */
    public static final class Builder implements StructBuilder<RefineFilterSuggestionFilterSuggestionEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "refinefiltersuggestion_filter_suggestion";
        /* access modifiers changed from: private */
        public C2142FilterType filter_type;
        /* access modifiers changed from: private */
        public String filter_value;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.RefineFilterSuggestion:RefineFilterSuggestionFilterSuggestionEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;

        private Builder() {
        }

        public Builder(Context context2, C2142FilterType filter_type2, String filter_value2, C2451Operation operation2, C2731SearchContext search_context2) {
            this.context = context2;
            this.filter_type = filter_type2;
            this.filter_value = filter_value2;
            this.operation = operation2;
            this.search_context = search_context2;
        }

        public RefineFilterSuggestionFilterSuggestionEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.filter_type == null) {
                throw new IllegalStateException("Required field 'filter_type' is missing");
            } else if (this.filter_value == null) {
                throw new IllegalStateException("Required field 'filter_value' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.search_context != null) {
                return new RefineFilterSuggestionFilterSuggestionEvent(this);
            } else {
                throw new IllegalStateException("Required field 'search_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.RefineFilterSuggestion.v1.RefineFilterSuggestionFilterSuggestionEvent$RefineFilterSuggestionFilterSuggestionEventAdapter */
    private static final class RefineFilterSuggestionFilterSuggestionEventAdapter implements Adapter<RefineFilterSuggestionFilterSuggestionEvent, Builder> {
        private RefineFilterSuggestionFilterSuggestionEventAdapter() {
        }

        public void write(Protocol protocol, RefineFilterSuggestionFilterSuggestionEvent struct) throws IOException {
            protocol.writeStructBegin("RefineFilterSuggestionFilterSuggestionEvent");
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

    private RefineFilterSuggestionFilterSuggestionEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.filter_type = builder.filter_type;
        this.filter_value = builder.filter_value;
        this.operation = builder.operation;
        this.search_context = builder.search_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof RefineFilterSuggestionFilterSuggestionEvent)) {
            return false;
        }
        RefineFilterSuggestionFilterSuggestionEvent that = (RefineFilterSuggestionFilterSuggestionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.filter_type == that.filter_type || this.filter_type.equals(that.filter_type)) && ((this.filter_value == that.filter_value || this.filter_value.equals(that.filter_value)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.search_context == that.search_context || this.search_context.equals(that.search_context)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.filter_type.hashCode()) * -2128831035) ^ this.filter_value.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.search_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RefineFilterSuggestionFilterSuggestionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", filter_type=" + this.filter_type + ", filter_value=" + this.filter_value + ", operation=" + this.operation + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
