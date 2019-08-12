package com.airbnb.jitney.event.logging.core.context.p025v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.core.context.v2.ServiceContext */
public final class ServiceContext implements Struct {
    public static final Adapter<ServiceContext, Object> ADAPTER = new ServiceContextAdapter();
    public final String endpoint_name;
    public final String global_request_chain_id;
    public final HTTPMethodType http_method_type;
    public final String local_request_id;
    public final String service_name;

    /* renamed from: com.airbnb.jitney.event.logging.core.context.v2.ServiceContext$ServiceContextAdapter */
    private static final class ServiceContextAdapter implements Adapter<ServiceContext, Object> {
        private ServiceContextAdapter() {
        }

        public void write(Protocol protocol, ServiceContext struct) throws IOException {
            protocol.writeStructBegin("ServiceContext");
            protocol.writeFieldBegin("service_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.service_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("local_request_id", 2, PassportService.SF_DG11);
            protocol.writeString(struct.local_request_id);
            protocol.writeFieldEnd();
            if (struct.http_method_type != null) {
                protocol.writeFieldBegin("http_method_type", 3, 8);
                protocol.writeI32(struct.http_method_type.value);
                protocol.writeFieldEnd();
            }
            if (struct.endpoint_name != null) {
                protocol.writeFieldBegin("endpoint_name", 4, PassportService.SF_DG11);
                protocol.writeString(struct.endpoint_name);
                protocol.writeFieldEnd();
            }
            if (struct.global_request_chain_id != null) {
                protocol.writeFieldBegin("global_request_chain_id", 5, PassportService.SF_DG11);
                protocol.writeString(struct.global_request_chain_id);
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
        if (!(other instanceof ServiceContext)) {
            return false;
        }
        ServiceContext that = (ServiceContext) other;
        if ((this.service_name == that.service_name || this.service_name.equals(that.service_name)) && ((this.local_request_id == that.local_request_id || this.local_request_id.equals(that.local_request_id)) && ((this.http_method_type == that.http_method_type || (this.http_method_type != null && this.http_method_type.equals(that.http_method_type))) && (this.endpoint_name == that.endpoint_name || (this.endpoint_name != null && this.endpoint_name.equals(that.endpoint_name)))))) {
            if (this.global_request_chain_id == that.global_request_chain_id) {
                return true;
            }
            if (this.global_request_chain_id != null && this.global_request_chain_id.equals(that.global_request_chain_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((16777619 ^ this.service_name.hashCode()) * -2128831035) ^ this.local_request_id.hashCode()) * -2128831035) ^ (this.http_method_type == null ? 0 : this.http_method_type.hashCode())) * -2128831035) ^ (this.endpoint_name == null ? 0 : this.endpoint_name.hashCode())) * -2128831035;
        if (this.global_request_chain_id != null) {
            i = this.global_request_chain_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ServiceContext{service_name=" + this.service_name + ", local_request_id=" + this.local_request_id + ", http_method_type=" + this.http_method_type + ", endpoint_name=" + this.endpoint_name + ", global_request_chain_id=" + this.global_request_chain_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
