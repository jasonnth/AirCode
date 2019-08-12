package com.airbnb.jitney.event.logging.core.request.p026v1;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.core.request.v1.Request */
public final class Request implements Struct {
    public static final Adapter<Request, Object> ADAPTER = new RequestAdapter();
    public final String akamai_botman;
    public final String bev;
    public final String ios_source;
    public final String req_accept_language;
    public final String req_method;
    public final String req_referrer;
    public final String req_remote_addr;
    public final String req_remote_host;
    public final String req_uri;
    public final String req_user_agent;
    public final String req_uuid;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.core.request.v1.Request$RequestAdapter */
    private static final class RequestAdapter implements Adapter<Request, Object> {
        private RequestAdapter() {
        }

        public void write(Protocol protocol, Request struct) throws IOException {
            protocol.writeStructBegin("Request");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("req_uuid", 1, PassportService.SF_DG11);
            protocol.writeString(struct.req_uuid);
            protocol.writeFieldEnd();
            if (struct.bev != null) {
                protocol.writeFieldBegin("bev", 2, PassportService.SF_DG11);
                protocol.writeString(struct.bev);
                protocol.writeFieldEnd();
            }
            if (struct.req_remote_host != null) {
                protocol.writeFieldBegin("req_remote_host", 3, PassportService.SF_DG11);
                protocol.writeString(struct.req_remote_host);
                protocol.writeFieldEnd();
            }
            if (struct.req_accept_language != null) {
                protocol.writeFieldBegin("req_accept_language", 4, PassportService.SF_DG11);
                protocol.writeString(struct.req_accept_language);
                protocol.writeFieldEnd();
            }
            if (struct.req_user_agent != null) {
                protocol.writeFieldBegin("req_user_agent", 5, PassportService.SF_DG11);
                protocol.writeString(struct.req_user_agent);
                protocol.writeFieldEnd();
            }
            if (struct.req_referrer != null) {
                protocol.writeFieldBegin("req_referrer", 6, PassportService.SF_DG11);
                protocol.writeString(struct.req_referrer);
                protocol.writeFieldEnd();
            }
            if (struct.akamai_botman != null) {
                protocol.writeFieldBegin("akamai_botman", 7, PassportService.SF_DG11);
                protocol.writeString(struct.akamai_botman);
                protocol.writeFieldEnd();
            }
            if (struct.ios_source != null) {
                protocol.writeFieldBegin("ios_source", 8, PassportService.SF_DG11);
                protocol.writeString(struct.ios_source);
                protocol.writeFieldEnd();
            }
            if (struct.req_uri != null) {
                protocol.writeFieldBegin("req_uri", 9, PassportService.SF_DG11);
                protocol.writeString(struct.req_uri);
                protocol.writeFieldEnd();
            }
            if (struct.req_remote_addr != null) {
                protocol.writeFieldBegin("req_remote_addr", 10, PassportService.SF_DG11);
                protocol.writeString(struct.req_remote_addr);
                protocol.writeFieldEnd();
            }
            if (struct.req_method != null) {
                protocol.writeFieldBegin("req_method", 11, PassportService.SF_DG11);
                protocol.writeString(struct.req_method);
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
        if (!(other instanceof Request)) {
            return false;
        }
        Request that = (Request) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.req_uuid == that.req_uuid || this.req_uuid.equals(that.req_uuid)) && ((this.bev == that.bev || (this.bev != null && this.bev.equals(that.bev))) && ((this.req_remote_host == that.req_remote_host || (this.req_remote_host != null && this.req_remote_host.equals(that.req_remote_host))) && ((this.req_accept_language == that.req_accept_language || (this.req_accept_language != null && this.req_accept_language.equals(that.req_accept_language))) && ((this.req_user_agent == that.req_user_agent || (this.req_user_agent != null && this.req_user_agent.equals(that.req_user_agent))) && ((this.req_referrer == that.req_referrer || (this.req_referrer != null && this.req_referrer.equals(that.req_referrer))) && ((this.akamai_botman == that.akamai_botman || (this.akamai_botman != null && this.akamai_botman.equals(that.akamai_botman))) && ((this.ios_source == that.ios_source || (this.ios_source != null && this.ios_source.equals(that.ios_source))) && ((this.req_uri == that.req_uri || (this.req_uri != null && this.req_uri.equals(that.req_uri))) && (this.req_remote_addr == that.req_remote_addr || (this.req_remote_addr != null && this.req_remote_addr.equals(that.req_remote_addr))))))))))))) {
            if (this.req_method == that.req_method) {
                return true;
            }
            if (this.req_method != null && this.req_method.equals(that.req_method)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.req_uuid.hashCode()) * -2128831035) ^ (this.bev == null ? 0 : this.bev.hashCode())) * -2128831035) ^ (this.req_remote_host == null ? 0 : this.req_remote_host.hashCode())) * -2128831035) ^ (this.req_accept_language == null ? 0 : this.req_accept_language.hashCode())) * -2128831035) ^ (this.req_user_agent == null ? 0 : this.req_user_agent.hashCode())) * -2128831035) ^ (this.req_referrer == null ? 0 : this.req_referrer.hashCode())) * -2128831035) ^ (this.akamai_botman == null ? 0 : this.akamai_botman.hashCode())) * -2128831035) ^ (this.ios_source == null ? 0 : this.ios_source.hashCode())) * -2128831035) ^ (this.req_uri == null ? 0 : this.req_uri.hashCode())) * -2128831035) ^ (this.req_remote_addr == null ? 0 : this.req_remote_addr.hashCode())) * -2128831035;
        if (this.req_method != null) {
            i = this.req_method.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "Request{schema=" + this.schema + ", req_uuid=" + this.req_uuid + ", bev=" + this.bev + ", req_remote_host=" + this.req_remote_host + ", req_accept_language=" + this.req_accept_language + ", req_user_agent=" + this.req_user_agent + ", req_referrer=" + this.req_referrer + ", akamai_botman=" + this.akamai_botman + ", ios_source=" + this.ios_source + ", req_uri=" + this.req_uri + ", req_remote_addr=" + this.req_remote_addr + ", req_method=" + this.req_method + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
