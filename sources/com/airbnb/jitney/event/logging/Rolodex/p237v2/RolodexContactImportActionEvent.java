package com.airbnb.jitney.event.logging.Rolodex.p237v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.ContactBookImportType.p074v1.C1973ContactBookImportType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.OperationResult.p166v1.C2452OperationResult;
import com.airbnb.jitney.event.logging.ViralityEntryPoint.p287v2.C2804ViralityEntryPoint;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Rolodex.v2.RolodexContactImportActionEvent */
public final class RolodexContactImportActionEvent implements Struct {
    public static final Adapter<RolodexContactImportActionEvent, Object> ADAPTER = new RolodexContactImportActionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C1973ContactBookImportType import_type;
    public final C2451Operation operation;
    public final C2452OperationResult operation_result;
    public final String schema;
    public final String target;
    public final C2804ViralityEntryPoint virality_entry_point;

    /* renamed from: com.airbnb.jitney.event.logging.Rolodex.v2.RolodexContactImportActionEvent$RolodexContactImportActionEventAdapter */
    private static final class RolodexContactImportActionEventAdapter implements Adapter<RolodexContactImportActionEvent, Object> {
        private RolodexContactImportActionEventAdapter() {
        }

        public void write(Protocol protocol, RolodexContactImportActionEvent struct) throws IOException {
            protocol.writeStructBegin("RolodexContactImportActionEvent");
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
            protocol.writeFieldBegin("virality_entry_point", 3, 8);
            protocol.writeI32(struct.virality_entry_point.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("import_type", 5, 8);
            protocol.writeI32(struct.import_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("operation_result", 7, 8);
            protocol.writeI32(struct.operation_result.value);
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
        if (!(other instanceof RolodexContactImportActionEvent)) {
            return false;
        }
        RolodexContactImportActionEvent that = (RolodexContactImportActionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.virality_entry_point == that.virality_entry_point || this.virality_entry_point.equals(that.virality_entry_point)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.import_type == that.import_type || this.import_type.equals(that.import_type)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.operation_result == that.operation_result || this.operation_result.equals(that.operation_result))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.virality_entry_point.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.import_type.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.operation_result.hashCode()) * -2128831035;
    }

    public String toString() {
        return "RolodexContactImportActionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", virality_entry_point=" + this.virality_entry_point + ", target=" + this.target + ", import_type=" + this.import_type + ", operation=" + this.operation + ", operation_result=" + this.operation_result + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
