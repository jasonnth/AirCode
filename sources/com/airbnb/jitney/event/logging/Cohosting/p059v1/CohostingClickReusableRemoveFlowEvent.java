package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ReusableRemoveClickType.p229v1.C2636ReusableRemoveClickType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingClickReusableRemoveFlowEvent */
public final class CohostingClickReusableRemoveFlowEvent implements Struct {
    public static final Adapter<CohostingClickReusableRemoveFlowEvent, Object> ADAPTER = new CohostingClickReusableRemoveFlowEventAdapter();
    public final String action;
    public final String comment_text;
    public final Context context;
    public final String event_name;
    public final String message;
    public final C2451Operation operation;
    public final Long reason;
    public final Long removed_cohost_id;
    public final C2636ReusableRemoveClickType reusable_remove_button;
    public final String schema;
    public final Long source_id;
    public final String source_type;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingClickReusableRemoveFlowEvent$CohostingClickReusableRemoveFlowEventAdapter */
    private static final class CohostingClickReusableRemoveFlowEventAdapter implements Adapter<CohostingClickReusableRemoveFlowEvent, Object> {
        private CohostingClickReusableRemoveFlowEventAdapter() {
        }

        public void write(Protocol protocol, CohostingClickReusableRemoveFlowEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingClickReusableRemoveFlowEvent");
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
            protocol.writeFieldBegin("reusable_remove_button", 3, 8);
            protocol.writeI32(struct.reusable_remove_button.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 5, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source_id", 6, 10);
            protocol.writeI64(struct.source_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source_type", 7, PassportService.SF_DG11);
            protocol.writeString(struct.source_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("action", 8, PassportService.SF_DG11);
            protocol.writeString(struct.action);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(CancellationAnalytics.VALUE_PAGE_REASON, 9, 10);
            protocol.writeI64(struct.reason.longValue());
            protocol.writeFieldEnd();
            if (struct.comment_text != null) {
                protocol.writeFieldBegin("comment_text", 10, PassportService.SF_DG11);
                protocol.writeString(struct.comment_text);
                protocol.writeFieldEnd();
            }
            if (struct.removed_cohost_id != null) {
                protocol.writeFieldBegin("removed_cohost_id", 11, 10);
                protocol.writeI64(struct.removed_cohost_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.message != null) {
                protocol.writeFieldBegin("message", 12, PassportService.SF_DG11);
                protocol.writeString(struct.message);
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
        if (!(other instanceof CohostingClickReusableRemoveFlowEvent)) {
            return false;
        }
        CohostingClickReusableRemoveFlowEvent that = (CohostingClickReusableRemoveFlowEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.reusable_remove_button == that.reusable_remove_button || this.reusable_remove_button.equals(that.reusable_remove_button)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.source_id == that.source_id || this.source_id.equals(that.source_id)) && ((this.source_type == that.source_type || this.source_type.equals(that.source_type)) && ((this.action == that.action || this.action.equals(that.action)) && ((this.reason == that.reason || this.reason.equals(that.reason)) && ((this.comment_text == that.comment_text || (this.comment_text != null && this.comment_text.equals(that.comment_text))) && (this.removed_cohost_id == that.removed_cohost_id || (this.removed_cohost_id != null && this.removed_cohost_id.equals(that.removed_cohost_id)))))))))))))) {
            if (this.message == that.message) {
                return true;
            }
            if (this.message != null && this.message.equals(that.message)) {
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
        int code = (((((((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.reusable_remove_button.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.source_id.hashCode()) * -2128831035) ^ this.source_type.hashCode()) * -2128831035) ^ this.action.hashCode()) * -2128831035) ^ this.reason.hashCode()) * -2128831035) ^ (this.comment_text == null ? 0 : this.comment_text.hashCode())) * -2128831035) ^ (this.removed_cohost_id == null ? 0 : this.removed_cohost_id.hashCode())) * -2128831035;
        if (this.message != null) {
            i = this.message.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CohostingClickReusableRemoveFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", reusable_remove_button=" + this.reusable_remove_button + ", operation=" + this.operation + ", user_id=" + this.user_id + ", source_id=" + this.source_id + ", source_type=" + this.source_type + ", action=" + this.action + ", reason=" + this.reason + ", comment_text=" + this.comment_text + ", removed_cohost_id=" + this.removed_cohost_id + ", message=" + this.message + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
