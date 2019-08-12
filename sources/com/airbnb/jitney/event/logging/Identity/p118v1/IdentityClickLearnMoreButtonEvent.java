package com.airbnb.jitney.event.logging.Identity.p118v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.IdentityFlowPagesType.p121v1.C2232IdentityFlowPagesType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityClickLearnMoreButtonEvent */
public final class IdentityClickLearnMoreButtonEvent implements Struct {
    public static final Adapter<IdentityClickLearnMoreButtonEvent, Object> ADAPTER = new IdentityClickLearnMoreButtonEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final C2232IdentityFlowPagesType page;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityClickLearnMoreButtonEvent$IdentityClickLearnMoreButtonEventAdapter */
    private static final class IdentityClickLearnMoreButtonEventAdapter implements Adapter<IdentityClickLearnMoreButtonEvent, Object> {
        private IdentityClickLearnMoreButtonEventAdapter() {
        }

        public void write(Protocol protocol, IdentityClickLearnMoreButtonEvent struct) throws IOException {
            protocol.writeStructBegin("IdentityClickLearnMoreButtonEvent");
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
        if (!(other instanceof IdentityClickLearnMoreButtonEvent)) {
            return false;
        }
        IdentityClickLearnMoreButtonEvent that = (IdentityClickLearnMoreButtonEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && (this.operation == that.operation || this.operation.equals(that.operation)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
    }

    public String toString() {
        return "IdentityClickLearnMoreButtonEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
