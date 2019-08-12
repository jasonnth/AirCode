package com.airbnb.jitney.event.logging.Virality.p285v3;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.OperationResult.p166v1.C2452OperationResult;
import com.airbnb.jitney.event.logging.ShareModule.p252v1.C2735ShareModule;
import com.airbnb.jitney.event.logging.ShareRecipient.p253v1.C2736ShareRecipient;
import com.airbnb.jitney.event.logging.ShareServiceType.p255v1.C2739ShareServiceType;
import com.airbnb.jitney.event.logging.SharedItemType.p257v2.C2741SharedItemType;
import com.airbnb.jitney.event.logging.ViralityEntryPoint.p287v2.C2804ViralityEntryPoint;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Virality.v3.ViralityShareActionEvent */
public final class ViralityShareActionEvent implements Struct {
    public static final Adapter<ViralityShareActionEvent, Object> ADAPTER = new ViralityShareActionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final C2452OperationResult operation_result;
    public final Long photo_index;
    public final String schema;
    public final C2735ShareModule share_module;
    public final List<C2736ShareRecipient> share_recipients;
    public final String share_service_freeform;
    public final C2739ShareServiceType share_service_type;
    public final Long shared_item_id;
    public final C2741SharedItemType shared_item_type;
    public final String target;
    public final Long total_shares_sent;
    public final C2804ViralityEntryPoint virality_entry_point;

    /* renamed from: com.airbnb.jitney.event.logging.Virality.v3.ViralityShareActionEvent$ViralityShareActionEventAdapter */
    private static final class ViralityShareActionEventAdapter implements Adapter<ViralityShareActionEvent, Object> {
        private ViralityShareActionEventAdapter() {
        }

        public void write(Protocol protocol, ViralityShareActionEvent struct) throws IOException {
            protocol.writeStructBegin("ViralityShareActionEvent");
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
            protocol.writeFieldBegin("virality_entry_point", 6, 8);
            protocol.writeI32(struct.virality_entry_point.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 7, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 8, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("operation_result", 9, 8);
            protocol.writeI32(struct.operation_result.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("share_service_type", 10, 8);
            protocol.writeI32(struct.share_service_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("share_service_freeform", 11, PassportService.SF_DG11);
            protocol.writeString(struct.share_service_freeform);
            protocol.writeFieldEnd();
            if (struct.total_shares_sent != null) {
                protocol.writeFieldBegin("total_shares_sent", 12, 10);
                protocol.writeI64(struct.total_shares_sent.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.share_recipients != null) {
                protocol.writeFieldBegin("share_recipients", 13, 15);
                protocol.writeListBegin(PassportService.SF_DG12, struct.share_recipients.size());
                for (C2736ShareRecipient item0 : struct.share_recipients) {
                    C2736ShareRecipient.ADAPTER.write(protocol, item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("share_module", 14, 8);
            protocol.writeI32(struct.share_module.value);
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
        if (!(other instanceof ViralityShareActionEvent)) {
            return false;
        }
        ViralityShareActionEvent that = (ViralityShareActionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.shared_item_type == that.shared_item_type || this.shared_item_type.equals(that.shared_item_type)) && ((this.shared_item_id == that.shared_item_id || (this.shared_item_id != null && this.shared_item_id.equals(that.shared_item_id))) && ((this.photo_index == that.photo_index || (this.photo_index != null && this.photo_index.equals(that.photo_index))) && ((this.virality_entry_point == that.virality_entry_point || this.virality_entry_point.equals(that.virality_entry_point)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation_result == that.operation_result || this.operation_result.equals(that.operation_result)) && ((this.share_service_type == that.share_service_type || this.share_service_type.equals(that.share_service_type)) && ((this.share_service_freeform == that.share_service_freeform || this.share_service_freeform.equals(that.share_service_freeform)) && ((this.total_shares_sent == that.total_shares_sent || (this.total_shares_sent != null && this.total_shares_sent.equals(that.total_shares_sent))) && ((this.share_recipients == that.share_recipients || (this.share_recipients != null && this.share_recipients.equals(that.share_recipients))) && (this.share_module == that.share_module || this.share_module.equals(that.share_module)))))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.shared_item_type.hashCode()) * -2128831035) ^ (this.shared_item_id == null ? 0 : this.shared_item_id.hashCode())) * -2128831035;
        if (this.photo_index == null) {
            hashCode = 0;
        } else {
            hashCode = this.photo_index.hashCode();
        }
        int code2 = (((((((((((((((code ^ hashCode) * -2128831035) ^ this.virality_entry_point.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation_result.hashCode()) * -2128831035) ^ this.share_service_type.hashCode()) * -2128831035) ^ this.share_service_freeform.hashCode()) * -2128831035) ^ (this.total_shares_sent == null ? 0 : this.total_shares_sent.hashCode())) * -2128831035;
        if (this.share_recipients != null) {
            i = this.share_recipients.hashCode();
        }
        return (((code2 ^ i) * -2128831035) ^ this.share_module.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ViralityShareActionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", shared_item_type=" + this.shared_item_type + ", shared_item_id=" + this.shared_item_id + ", photo_index=" + this.photo_index + ", virality_entry_point=" + this.virality_entry_point + ", operation=" + this.operation + ", target=" + this.target + ", operation_result=" + this.operation_result + ", share_service_type=" + this.share_service_type + ", share_service_freeform=" + this.share_service_freeform + ", total_shares_sent=" + this.total_shares_sent + ", share_recipients=" + this.share_recipients + ", share_module=" + this.share_module + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
