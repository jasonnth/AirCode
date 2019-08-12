package com.airbnb.jitney.event.logging.I18n.p014v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.I18n.v1.I18nTrackTranslationEvent */
public final class I18nTrackTranslationEvent implements Struct {
    public static final Adapter<I18nTrackTranslationEvent, Object> ADAPTER = new I18nTrackTranslationEventAdapter();
    public final String button_text;
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String schema;
    public final Long thread_id;
    public final String thread_type;

    /* renamed from: com.airbnb.jitney.event.logging.I18n.v1.I18nTrackTranslationEvent$I18nTrackTranslationEventAdapter */
    private static final class I18nTrackTranslationEventAdapter implements Adapter<I18nTrackTranslationEvent, Object> {
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
            protocol.writeFieldBegin("button_text", 6, PassportService.SF_DG11);
            protocol.writeString(struct.button_text);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("thread_type", 7, PassportService.SF_DG11);
            protocol.writeString(struct.thread_type);
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
        if (!(other instanceof I18nTrackTranslationEvent)) {
            return false;
        }
        I18nTrackTranslationEvent that = (I18nTrackTranslationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.thread_id == that.thread_id || this.thread_id.equals(that.thread_id)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.button_text == that.button_text || this.button_text.equals(that.button_text)) && (this.thread_type == that.thread_type || this.thread_type.equals(that.thread_type))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.thread_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.button_text.hashCode()) * -2128831035) ^ this.thread_type.hashCode()) * -2128831035;
    }

    public String toString() {
        return "I18nTrackTranslationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", thread_id=" + this.thread_id + ", listing_id=" + this.listing_id + ", operation=" + this.operation + ", button_text=" + this.button_text + ", thread_type=" + this.thread_type + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
