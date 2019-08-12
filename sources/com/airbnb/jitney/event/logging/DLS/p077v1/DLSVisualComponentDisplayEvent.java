package com.airbnb.jitney.event.logging.DLS.p077v1;

import com.airbnb.jitney.event.logging.VisualComponentDlsType.p288v1.C2805VisualComponentDlsType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.DLS.v1.DLSVisualComponentDisplayEvent */
public final class DLSVisualComponentDisplayEvent implements Struct {
    public static final Adapter<DLSVisualComponentDisplayEvent, Builder> ADAPTER = new DLSVisualComponentDisplayEventAdapter();
    public final Context context;
    public final String event_name;
    public final String page;
    public final String schema;
    public final C2805VisualComponentDlsType visual_component_dls_type;
    public final String visual_component_name;

    /* renamed from: com.airbnb.jitney.event.logging.DLS.v1.DLSVisualComponentDisplayEvent$Builder */
    public static final class Builder implements StructBuilder<DLSVisualComponentDisplayEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "dls_visual_component_display";
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.DLS:DLSVisualComponentDisplayEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2805VisualComponentDlsType visual_component_dls_type;
        /* access modifiers changed from: private */
        public String visual_component_name;

        private Builder() {
        }

        public Builder(Context context2, String visual_component_name2, C2805VisualComponentDlsType visual_component_dls_type2) {
            this.context = context2;
            this.visual_component_name = visual_component_name2;
            this.visual_component_dls_type = visual_component_dls_type2;
        }

        public DLSVisualComponentDisplayEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.visual_component_name == null) {
                throw new IllegalStateException("Required field 'visual_component_name' is missing");
            } else if (this.visual_component_dls_type != null) {
                return new DLSVisualComponentDisplayEvent(this);
            } else {
                throw new IllegalStateException("Required field 'visual_component_dls_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.DLS.v1.DLSVisualComponentDisplayEvent$DLSVisualComponentDisplayEventAdapter */
    private static final class DLSVisualComponentDisplayEventAdapter implements Adapter<DLSVisualComponentDisplayEvent, Builder> {
        private DLSVisualComponentDisplayEventAdapter() {
        }

        public void write(Protocol protocol, DLSVisualComponentDisplayEvent struct) throws IOException {
            protocol.writeStructBegin("DLSVisualComponentDisplayEvent");
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
            protocol.writeFieldBegin("visual_component_name", 3, PassportService.SF_DG11);
            protocol.writeString(struct.visual_component_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("visual_component_dls_type", 4, 8);
            protocol.writeI32(struct.visual_component_dls_type.value);
            protocol.writeFieldEnd();
            if (struct.page != null) {
                protocol.writeFieldBegin("page", 5, PassportService.SF_DG11);
                protocol.writeString(struct.page);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private DLSVisualComponentDisplayEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.visual_component_name = builder.visual_component_name;
        this.visual_component_dls_type = builder.visual_component_dls_type;
        this.page = builder.page;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof DLSVisualComponentDisplayEvent)) {
            return false;
        }
        DLSVisualComponentDisplayEvent that = (DLSVisualComponentDisplayEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.visual_component_name == that.visual_component_name || this.visual_component_name.equals(that.visual_component_name)) && (this.visual_component_dls_type == that.visual_component_dls_type || this.visual_component_dls_type.equals(that.visual_component_dls_type)))))) {
            if (this.page == that.page) {
                return true;
            }
            if (this.page != null && this.page.equals(that.page)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.visual_component_name.hashCode()) * -2128831035) ^ this.visual_component_dls_type.hashCode()) * -2128831035;
        if (this.page != null) {
            i = this.page.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "DLSVisualComponentDisplayEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", visual_component_name=" + this.visual_component_name + ", visual_component_dls_type=" + this.visual_component_dls_type + ", page=" + this.page + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
