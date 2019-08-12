package com.airbnb.jitney.event.logging.DeepLink.p082v1;

import com.airbnb.jitney.event.logging.DeepLinkOperationType.p083v1.C1984DeepLinkOperationType;
import com.airbnb.jitney.event.logging.DeepLinkOriginType.p084v1.C1985DeepLinkOriginType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.DeepLink.v1.DeepLinkServiceEvent */
public final class DeepLinkServiceEvent implements Struct {
    public static final Adapter<DeepLinkServiceEvent, Object> ADAPTER = new DeepLinkServiceEventAdapter();
    public final Context context;
    public final C1984DeepLinkOperationType deep_link_operation;
    public final C1985DeepLinkOriginType deep_link_origin;
    public final String error_message;
    public final String event_name;
    public final String referrer;
    public final String route;
    public final String schema;
    public final String url;

    /* renamed from: com.airbnb.jitney.event.logging.DeepLink.v1.DeepLinkServiceEvent$DeepLinkServiceEventAdapter */
    private static final class DeepLinkServiceEventAdapter implements Adapter<DeepLinkServiceEvent, Object> {
        private DeepLinkServiceEventAdapter() {
        }

        public void write(Protocol protocol, DeepLinkServiceEvent struct) throws IOException {
            protocol.writeStructBegin("DeepLinkServiceEvent");
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
            protocol.writeFieldBegin("deep_link_operation", 3, 8);
            protocol.writeI32(struct.deep_link_operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("deep_link_origin", 4, 8);
            protocol.writeI32(struct.deep_link_origin.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("url", 5, PassportService.SF_DG11);
            protocol.writeString(struct.url);
            protocol.writeFieldEnd();
            if (struct.route != null) {
                protocol.writeFieldBegin("route", 6, PassportService.SF_DG11);
                protocol.writeString(struct.route);
                protocol.writeFieldEnd();
            }
            if (struct.referrer != null) {
                protocol.writeFieldBegin("referrer", 7, PassportService.SF_DG11);
                protocol.writeString(struct.referrer);
                protocol.writeFieldEnd();
            }
            if (struct.error_message != null) {
                protocol.writeFieldBegin("error_message", 8, PassportService.SF_DG11);
                protocol.writeString(struct.error_message);
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
        if (!(other instanceof DeepLinkServiceEvent)) {
            return false;
        }
        DeepLinkServiceEvent that = (DeepLinkServiceEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.deep_link_operation == that.deep_link_operation || this.deep_link_operation.equals(that.deep_link_operation)) && ((this.deep_link_origin == that.deep_link_origin || this.deep_link_origin.equals(that.deep_link_origin)) && ((this.url == that.url || this.url.equals(that.url)) && ((this.route == that.route || (this.route != null && this.route.equals(that.route))) && (this.referrer == that.referrer || (this.referrer != null && this.referrer.equals(that.referrer)))))))))) {
            if (this.error_message == that.error_message) {
                return true;
            }
            if (this.error_message != null && this.error_message.equals(that.error_message)) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.deep_link_operation.hashCode()) * -2128831035) ^ this.deep_link_origin.hashCode()) * -2128831035) ^ this.url.hashCode()) * -2128831035) ^ (this.route == null ? 0 : this.route.hashCode())) * -2128831035) ^ (this.referrer == null ? 0 : this.referrer.hashCode())) * -2128831035;
        if (this.error_message != null) {
            i = this.error_message.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "DeepLinkServiceEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", deep_link_operation=" + this.deep_link_operation + ", deep_link_origin=" + this.deep_link_origin + ", url=" + this.url + ", route=" + this.route + ", referrer=" + this.referrer + ", error_message=" + this.error_message + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
