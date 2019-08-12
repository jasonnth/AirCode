package com.airbnb.jitney.event.logging.ImageAnnotations.p126v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ImageAnnotationsPageType.p016v1.C0951ImageAnnotationsPageType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ImageAnnotations.v1.ImageAnnotationsClickRotateImageEvent */
public final class ImageAnnotationsClickRotateImageEvent implements Struct {
    public static final Adapter<ImageAnnotationsClickRotateImageEvent, Builder> ADAPTER = new ImageAnnotationsClickRotateImageEventAdapter();
    public final Context context;
    public final String event_name;
    public final C0951ImageAnnotationsPageType image_annotations_page;
    public final Boolean is_host_mode;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ImageAnnotations.v1.ImageAnnotationsClickRotateImageEvent$Builder */
    public static final class Builder implements StructBuilder<ImageAnnotationsClickRotateImageEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C0951ImageAnnotationsPageType image_annotations_page;
        /* access modifiers changed from: private */
        public Boolean is_host_mode;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.ImageAnnotations:ImageAnnotationsClickRotateImageEvent:1.0.0";
            this.event_name = "imageannotations_click_rotate_image";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Boolean is_host_mode2, C0951ImageAnnotationsPageType image_annotations_page2) {
            this.schema = "com.airbnb.jitney.event.logging.ImageAnnotations:ImageAnnotationsClickRotateImageEvent:1.0.0";
            this.event_name = "imageannotations_click_rotate_image";
            this.context = context2;
            this.is_host_mode = is_host_mode2;
            this.image_annotations_page = image_annotations_page2;
            this.operation = C2451Operation.Click;
        }

        public ImageAnnotationsClickRotateImageEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.is_host_mode == null) {
                throw new IllegalStateException("Required field 'is_host_mode' is missing");
            } else if (this.image_annotations_page == null) {
                throw new IllegalStateException("Required field 'image_annotations_page' is missing");
            } else if (this.operation != null) {
                return new ImageAnnotationsClickRotateImageEvent(this);
            } else {
                throw new IllegalStateException("Required field 'operation' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.ImageAnnotations.v1.ImageAnnotationsClickRotateImageEvent$ImageAnnotationsClickRotateImageEventAdapter */
    private static final class ImageAnnotationsClickRotateImageEventAdapter implements Adapter<ImageAnnotationsClickRotateImageEvent, Builder> {
        private ImageAnnotationsClickRotateImageEventAdapter() {
        }

        public void write(Protocol protocol, ImageAnnotationsClickRotateImageEvent struct) throws IOException {
            protocol.writeStructBegin("ImageAnnotationsClickRotateImageEvent");
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
            protocol.writeFieldBegin("is_host_mode", 3, 2);
            protocol.writeBool(struct.is_host_mode.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("image_annotations_page", 4, 8);
            protocol.writeI32(struct.image_annotations_page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private ImageAnnotationsClickRotateImageEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.is_host_mode = builder.is_host_mode;
        this.image_annotations_page = builder.image_annotations_page;
        this.operation = builder.operation;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ImageAnnotationsClickRotateImageEvent)) {
            return false;
        }
        ImageAnnotationsClickRotateImageEvent that = (ImageAnnotationsClickRotateImageEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.is_host_mode == that.is_host_mode || this.is_host_mode.equals(that.is_host_mode)) && ((this.image_annotations_page == that.image_annotations_page || this.image_annotations_page.equals(that.image_annotations_page)) && (this.operation == that.operation || this.operation.equals(that.operation))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.is_host_mode.hashCode()) * -2128831035) ^ this.image_annotations_page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ImageAnnotationsClickRotateImageEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", is_host_mode=" + this.is_host_mode + ", image_annotations_page=" + this.image_annotations_page + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
