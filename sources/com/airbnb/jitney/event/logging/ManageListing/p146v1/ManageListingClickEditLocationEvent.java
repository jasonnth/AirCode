package com.airbnb.jitney.event.logging.ManageListing.p146v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.RegistrationManageListingPagesType.p223v1.C2607RegistrationManageListingPagesType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ManageListing.v1.ManageListingClickEditLocationEvent */
public final class ManageListingClickEditLocationEvent implements Struct {
    public static final Adapter<ManageListingClickEditLocationEvent, Object> ADAPTER = new ManageListingClickEditLocationEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final C2607RegistrationManageListingPagesType page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.ManageListing.v1.ManageListingClickEditLocationEvent$ManageListingClickEditLocationEventAdapter */
    private static final class ManageListingClickEditLocationEventAdapter implements Adapter<ManageListingClickEditLocationEvent, Object> {
        private ManageListingClickEditLocationEventAdapter() {
        }

        public void write(Protocol protocol, ManageListingClickEditLocationEvent struct) throws IOException {
            protocol.writeStructBegin("ManageListingClickEditLocationEvent");
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
            protocol.writeFieldBegin("page", 3, 8);
            protocol.writeI32(struct.page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 5, PassportService.SF_DG11);
            protocol.writeString(struct.target);
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
        if (!(other instanceof ManageListingClickEditLocationEvent)) {
            return false;
        }
        ManageListingClickEditLocationEvent that = (ManageListingClickEditLocationEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.target == that.target || this.target.equals(that.target))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ManageListingClickEditLocationEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", target=" + this.target + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
