package com.airbnb.jitney.event.logging.I18n.p015v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.TranslationButtonTextType.p024v1.C0975TranslationButtonTextType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.I18n.v2.I18nTrackTranslationEvent */
public final class I18nTrackTranslationEvent implements Struct {
    public static final Adapter<I18nTrackTranslationEvent, Builder> ADAPTER = new I18nTrackTranslationEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String schema;
    public final Long thread_id;
    public final String thread_type;
    public final C0975TranslationButtonTextType translation_button_text;

    /* renamed from: com.airbnb.jitney.event.logging.I18n.v2.I18nTrackTranslationEvent$Builder */
    public static final class Builder implements StructBuilder<I18nTrackTranslationEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public Long thread_id;
        /* access modifiers changed from: private */
        public String thread_type;
        /* access modifiers changed from: private */
        public C0975TranslationButtonTextType translation_button_text;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.I18n:I18nTrackTranslationEvent:2.0.0";
            this.event_name = "i18n_track_translation";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Long thread_id2, Long listing_id2, C0975TranslationButtonTextType translation_button_text2, String thread_type2) {
            this.schema = "com.airbnb.jitney.event.logging.I18n:I18nTrackTranslationEvent:2.0.0";
            this.event_name = "i18n_track_translation";
            this.context = context2;
            this.thread_id = thread_id2;
            this.listing_id = listing_id2;
            this.operation = C2451Operation.Click;
            this.translation_button_text = translation_button_text2;
            this.thread_type = thread_type2;
        }

        public I18nTrackTranslationEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.thread_id == null) {
                throw new IllegalStateException("Required field 'thread_id' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.translation_button_text == null) {
                throw new IllegalStateException("Required field 'translation_button_text' is missing");
            } else if (this.thread_type != null) {
                return new I18nTrackTranslationEvent(this);
            } else {
                throw new IllegalStateException("Required field 'thread_type' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.I18n.v2.I18nTrackTranslationEvent$I18nTrackTranslationEventAdapter */
    private static final class I18nTrackTranslationEventAdapter implements Adapter<I18nTrackTranslationEvent, Builder> {
        private I18nTrackTranslationEventAdapter() {
        }

        public void write(Protocol protocol, I18nTrackTranslationEvent struct) throws IOException {
            protocol.writeStructBegin("I18nTrackTranslationEvent");
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
            protocol.writeFieldBegin("thread_id", 3, 10);
            protocol.writeI64(struct.thread_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 4, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("translation_button_text", 6, 8);
            protocol.writeI32(struct.translation_button_text.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("thread_type", 7, PassportService.SF_DG11);
            protocol.writeString(struct.thread_type);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private I18nTrackTranslationEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.thread_id = builder.thread_id;
        this.listing_id = builder.listing_id;
        this.operation = builder.operation;
        this.translation_button_text = builder.translation_button_text;
        this.thread_type = builder.thread_type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof I18nTrackTranslationEvent)) {
            return false;
        }
        I18nTrackTranslationEvent that = (I18nTrackTranslationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.thread_id == that.thread_id || this.thread_id.equals(that.thread_id)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.translation_button_text == that.translation_button_text || this.translation_button_text.equals(that.translation_button_text)) && (this.thread_type == that.thread_type || this.thread_type.equals(that.thread_type))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.thread_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.translation_button_text.hashCode()) * -2128831035) ^ this.thread_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "I18nTrackTranslationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", thread_id=" + this.thread_id + ", listing_id=" + this.listing_id + ", operation=" + this.operation + ", translation_button_text=" + this.translation_button_text + ", thread_type=" + this.thread_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
