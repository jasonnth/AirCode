package com.airbnb.jitney.event.logging.core.context.p025v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.core.context.v2.MonorailContext */
public final class MonorailContext implements Struct {
    public static final Adapter<MonorailContext, Object> ADAPTER = new MonorailContextAdapter();
    public final String action;
    public final String api_client_id;
    public final String api_client_name;
    public final String api_device_id;
    public final String api_format;
    public final String api_intents;
    public final String api_protocol;
    public final String api_resource;
    public final String api_version;
    public final String controller;

    /* renamed from: com.airbnb.jitney.event.logging.core.context.v2.MonorailContext$MonorailContextAdapter */
    private static final class MonorailContextAdapter implements Adapter<MonorailContext, Object> {
        private MonorailContextAdapter() {
        }

        public void write(Protocol protocol, MonorailContext struct) throws IOException {
            protocol.writeStructBegin("MonorailContext");
            protocol.writeFieldBegin("controller", 1, PassportService.SF_DG11);
            protocol.writeString(struct.controller);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("action", 2, PassportService.SF_DG11);
            protocol.writeString(struct.action);
            protocol.writeFieldEnd();
            if (struct.api_client_id != null) {
                protocol.writeFieldBegin("api_client_id", 3, PassportService.SF_DG11);
                protocol.writeString(struct.api_client_id);
                protocol.writeFieldEnd();
            }
            if (struct.api_device_id != null) {
                protocol.writeFieldBegin("api_device_id", 4, PassportService.SF_DG11);
                protocol.writeString(struct.api_device_id);
                protocol.writeFieldEnd();
            }
            if (struct.api_client_name != null) {
                protocol.writeFieldBegin("api_client_name", 5, PassportService.SF_DG11);
                protocol.writeString(struct.api_client_name);
                protocol.writeFieldEnd();
            }
            if (struct.api_protocol != null) {
                protocol.writeFieldBegin("api_protocol", 6, PassportService.SF_DG11);
                protocol.writeString(struct.api_protocol);
                protocol.writeFieldEnd();
            }
            if (struct.api_version != null) {
                protocol.writeFieldBegin("api_version", 7, PassportService.SF_DG11);
                protocol.writeString(struct.api_version);
                protocol.writeFieldEnd();
            }
            if (struct.api_resource != null) {
                protocol.writeFieldBegin("api_resource", 8, PassportService.SF_DG11);
                protocol.writeString(struct.api_resource);
                protocol.writeFieldEnd();
            }
            if (struct.api_format != null) {
                protocol.writeFieldBegin("api_format", 9, PassportService.SF_DG11);
                protocol.writeString(struct.api_format);
                protocol.writeFieldEnd();
            }
            if (struct.api_intents != null) {
                protocol.writeFieldBegin("api_intents", 10, PassportService.SF_DG11);
                protocol.writeString(struct.api_intents);
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
        if (!(other instanceof MonorailContext)) {
            return false;
        }
        MonorailContext that = (MonorailContext) other;
        if ((this.controller == that.controller || this.controller.equals(that.controller)) && ((this.action == that.action || this.action.equals(that.action)) && ((this.api_client_id == that.api_client_id || (this.api_client_id != null && this.api_client_id.equals(that.api_client_id))) && ((this.api_device_id == that.api_device_id || (this.api_device_id != null && this.api_device_id.equals(that.api_device_id))) && ((this.api_client_name == that.api_client_name || (this.api_client_name != null && this.api_client_name.equals(that.api_client_name))) && ((this.api_protocol == that.api_protocol || (this.api_protocol != null && this.api_protocol.equals(that.api_protocol))) && ((this.api_version == that.api_version || (this.api_version != null && this.api_version.equals(that.api_version))) && ((this.api_resource == that.api_resource || (this.api_resource != null && this.api_resource.equals(that.api_resource))) && (this.api_format == that.api_format || (this.api_format != null && this.api_format.equals(that.api_format))))))))))) {
            if (this.api_intents == that.api_intents) {
                return true;
            }
            if (this.api_intents != null && this.api_intents.equals(that.api_intents)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((16777619 ^ this.controller.hashCode()) * -2128831035) ^ this.action.hashCode()) * -2128831035) ^ (this.api_client_id == null ? 0 : this.api_client_id.hashCode())) * -2128831035) ^ (this.api_device_id == null ? 0 : this.api_device_id.hashCode())) * -2128831035) ^ (this.api_client_name == null ? 0 : this.api_client_name.hashCode())) * -2128831035) ^ (this.api_protocol == null ? 0 : this.api_protocol.hashCode())) * -2128831035) ^ (this.api_version == null ? 0 : this.api_version.hashCode())) * -2128831035) ^ (this.api_resource == null ? 0 : this.api_resource.hashCode())) * -2128831035) ^ (this.api_format == null ? 0 : this.api_format.hashCode())) * -2128831035;
        if (this.api_intents != null) {
            i = this.api_intents.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "MonorailContext{controller=" + this.controller + ", action=" + this.action + ", api_client_id=" + this.api_client_id + ", api_device_id=" + this.api_device_id + ", api_client_name=" + this.api_client_name + ", api_protocol=" + this.api_protocol + ", api_version=" + this.api_version + ", api_resource=" + this.api_resource + ", api_format=" + this.api_format + ", api_intents=" + this.api_intents + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
