package com.airbnb.jitney.event.logging.core.context.p025v2;

import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.core.context.v2.WebContext */
public final class WebContext implements Struct {
    public static final Adapter<WebContext, Object> ADAPTER = new WebContextAdapter();
    public final String action;
    public final String canonical_url;
    public final String controller;
    public final String local_time;
    public final String page;
    public final String page_referrer;
    public final String page_uri;
    public final String rendered_on;
    public final String req_uuid;
    public final String shardset;
    public final String viewport;

    /* renamed from: com.airbnb.jitney.event.logging.core.context.v2.WebContext$WebContextAdapter */
    private static final class WebContextAdapter implements Adapter<WebContext, Object> {
        private WebContextAdapter() {
        }

        public void write(Protocol protocol, WebContext struct) throws IOException {
            protocol.writeStructBegin("WebContext");
            if (struct.controller != null) {
                protocol.writeFieldBegin("controller", 1, PassportService.SF_DG11);
                protocol.writeString(struct.controller);
                protocol.writeFieldEnd();
            }
            if (struct.page != null) {
                protocol.writeFieldBegin("page", 2, PassportService.SF_DG11);
                protocol.writeString(struct.page);
                protocol.writeFieldEnd();
            }
            if (struct.action != null) {
                protocol.writeFieldBegin("action", 3, PassportService.SF_DG11);
                protocol.writeString(struct.action);
                protocol.writeFieldEnd();
            }
            if (struct.rendered_on != null) {
                protocol.writeFieldBegin("rendered_on", 4, PassportService.SF_DG11);
                protocol.writeString(struct.rendered_on);
                protocol.writeFieldEnd();
            }
            if (struct.viewport != null) {
                protocol.writeFieldBegin("viewport", 5, PassportService.SF_DG11);
                protocol.writeString(struct.viewport);
                protocol.writeFieldEnd();
            }
            if (struct.req_uuid != null) {
                protocol.writeFieldBegin("req_uuid", 6, PassportService.SF_DG11);
                protocol.writeString(struct.req_uuid);
                protocol.writeFieldEnd();
            }
            if (struct.page_uri != null) {
                protocol.writeFieldBegin("page_uri", 7, PassportService.SF_DG11);
                protocol.writeString(struct.page_uri);
                protocol.writeFieldEnd();
            }
            if (struct.page_referrer != null) {
                protocol.writeFieldBegin("page_referrer", 8, PassportService.SF_DG11);
                protocol.writeString(struct.page_referrer);
                protocol.writeFieldEnd();
            }
            if (struct.canonical_url != null) {
                protocol.writeFieldBegin("canonical_url", 9, PassportService.SF_DG11);
                protocol.writeString(struct.canonical_url);
                protocol.writeFieldEnd();
            }
            if (struct.shardset != null) {
                protocol.writeFieldBegin("shardset", 10, PassportService.SF_DG11);
                protocol.writeString(struct.shardset);
                protocol.writeFieldEnd();
            }
            if (struct.local_time != null) {
                protocol.writeFieldBegin("local_time", 11, PassportService.SF_DG11);
                protocol.writeString(struct.local_time);
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
        if (!(other instanceof WebContext)) {
            return false;
        }
        WebContext that = (WebContext) other;
        if ((this.controller == that.controller || (this.controller != null && this.controller.equals(that.controller))) && ((this.page == that.page || (this.page != null && this.page.equals(that.page))) && ((this.action == that.action || (this.action != null && this.action.equals(that.action))) && ((this.rendered_on == that.rendered_on || (this.rendered_on != null && this.rendered_on.equals(that.rendered_on))) && ((this.viewport == that.viewport || (this.viewport != null && this.viewport.equals(that.viewport))) && ((this.req_uuid == that.req_uuid || (this.req_uuid != null && this.req_uuid.equals(that.req_uuid))) && ((this.page_uri == that.page_uri || (this.page_uri != null && this.page_uri.equals(that.page_uri))) && ((this.page_referrer == that.page_referrer || (this.page_referrer != null && this.page_referrer.equals(that.page_referrer))) && ((this.canonical_url == that.canonical_url || (this.canonical_url != null && this.canonical_url.equals(that.canonical_url))) && (this.shardset == that.shardset || (this.shardset != null && this.shardset.equals(that.shardset)))))))))))) {
            if (this.local_time == that.local_time) {
                return true;
            }
            if (this.local_time != null && this.local_time.equals(that.local_time)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((16777619 ^ (this.controller == null ? 0 : this.controller.hashCode())) * -2128831035) ^ (this.page == null ? 0 : this.page.hashCode())) * -2128831035) ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035) ^ (this.rendered_on == null ? 0 : this.rendered_on.hashCode())) * -2128831035) ^ (this.viewport == null ? 0 : this.viewport.hashCode())) * -2128831035) ^ (this.req_uuid == null ? 0 : this.req_uuid.hashCode())) * -2128831035) ^ (this.page_uri == null ? 0 : this.page_uri.hashCode())) * -2128831035) ^ (this.page_referrer == null ? 0 : this.page_referrer.hashCode())) * -2128831035) ^ (this.canonical_url == null ? 0 : this.canonical_url.hashCode())) * -2128831035) ^ (this.shardset == null ? 0 : this.shardset.hashCode())) * -2128831035;
        if (this.local_time != null) {
            i = this.local_time.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "WebContext{controller=" + this.controller + ", page=" + this.page + ", action=" + this.action + ", rendered_on=" + this.rendered_on + ", viewport=" + this.viewport + ", req_uuid=" + this.req_uuid + ", page_uri=" + this.page_uri + ", page_referrer=" + this.page_referrer + ", canonical_url=" + this.canonical_url + ", shardset=" + this.shardset + ", local_time=" + this.local_time + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
