package com.airbnb.jitney.event.logging.Virality.p283v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SharedItemType.p256v1.C2740SharedItemType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityShareSheetImpressionEvent */
public final class ViralityShareSheetImpressionEvent implements Struct {
    public static final Adapter<ViralityShareSheetImpressionEvent, Object> ADAPTER = new ViralityShareSheetImpressionEventAdapter();
    public final Context context;
    public final String entry_point;
    public final String event_name;
    public final C2451Operation operation;
    public final Long photo_index;
    public final String schema;
    public final Long shared_item_id;
    public final C2740SharedItemType shared_item_type;

    /* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityShareSheetImpressionEvent$ViralityShareSheetImpressionEventAdapter */
    private static final class ViralityShareSheetImpressionEventAdapter implements Adapter<ViralityShareSheetImpressionEvent, Object> {
        private ViralityShareSheetImpressionEventAdapter() {
        }

        public void write(Protocol protocol, ViralityShareSheetImpressionEvent struct) throws IOException {
            protocol.writeStructBegin("ViralityShareSheetImpressionEvent");
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
            protocol.writeFieldBegin("shared_item_type", 3, 8);
            protocol.writeI32(struct.shared_item_type.value);
            protocol.writeFieldEnd();
            if (struct.shared_item_id != null) {
                protocol.writeFieldBegin("shared_item_id", 4, 10);
                protocol.writeI64(struct.shared_item_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.photo_index != null) {
                protocol.writeFieldBegin("photo_index", 5, 10);
                protocol.writeI64(struct.photo_index.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.entry_point != null) {
                protocol.writeFieldBegin(ShareActivityIntents.ARG_ENTRY_POINT, 6, PassportService.SF_DG11);
                protocol.writeString(struct.entry_point);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 7, 8);
            protocol.writeI32(struct.operation.value);
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
        if (!(other instanceof ViralityShareSheetImpressionEvent)) {
            return false;
        }
        ViralityShareSheetImpressionEvent that = (ViralityShareSheetImpressionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.shared_item_type == that.shared_item_type || this.shared_item_type.equals(that.shared_item_type)) && ((this.shared_item_id == that.shared_item_id || (this.shared_item_id != null && this.shared_item_id.equals(that.shared_item_id))) && ((this.photo_index == that.photo_index || (this.photo_index != null && this.photo_index.equals(that.photo_index))) && ((this.entry_point == that.entry_point || (this.entry_point != null && this.entry_point.equals(that.entry_point))) && (this.operation == that.operation || this.operation.equals(that.operation))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.shared_item_type.hashCode()) * -2128831035) ^ (this.shared_item_id == null ? 0 : this.shared_item_id.hashCode())) * -2128831035) ^ (this.photo_index == null ? 0 : this.photo_index.hashCode())) * -2128831035;
        if (this.entry_point != null) {
            i = this.entry_point.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ViralityShareSheetImpressionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", shared_item_type=" + this.shared_item_type + ", shared_item_id=" + this.shared_item_id + ", photo_index=" + this.photo_index + ", entry_point=" + this.entry_point + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
