package com.airbnb.jitney.event.logging.core.context.p025v2;

import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.airbnb.android.utils.AirbnbConstants;
import com.facebook.internal.AnalyticsEvents;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;
import p005cn.jpush.android.JPushConstants.PushService;

/* renamed from: com.airbnb.jitney.event.logging.core.context.v2.Context */
public final class Context implements Struct {
    public static final Adapter<Context, Builder> ADAPTER = new ContextAdapter();
    public final String affiliate_id;
    public final String bev;
    public final String bev_expiry;
    public final String campaign;
    public final String client_session_id;
    public final Map<String, String> extra_data;
    public final String hash_user_id;
    public final String language;
    public final String locale;
    public final MobileContext mobile;
    public final MonorailContext monorail;
    public final String platform;
    public final Long screen_height;
    public final Long screen_width;
    public final ServiceContext service_context;
    public final String source;
    public final Long timestamp;
    public final String user_agent;
    public final Long user_id;
    public final String version;
    public final WebContext web;

    /* renamed from: com.airbnb.jitney.event.logging.core.context.v2.Context$Builder */
    public static final class Builder implements StructBuilder<Context> {
        /* access modifiers changed from: private */
        public String affiliate_id;
        /* access modifiers changed from: private */
        public String bev;
        /* access modifiers changed from: private */
        public String bev_expiry;
        /* access modifiers changed from: private */
        public String campaign;
        /* access modifiers changed from: private */
        public String client_session_id;
        /* access modifiers changed from: private */
        public Map<String, String> extra_data;
        /* access modifiers changed from: private */
        public String hash_user_id;
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public String locale;
        /* access modifiers changed from: private */
        public MobileContext mobile;
        /* access modifiers changed from: private */
        public MonorailContext monorail;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public Long screen_height;
        /* access modifiers changed from: private */
        public Long screen_width;
        /* access modifiers changed from: private */
        public ServiceContext service_context;
        /* access modifiers changed from: private */
        public String source;
        /* access modifiers changed from: private */
        public Long timestamp;
        /* access modifiers changed from: private */
        public String user_agent;
        /* access modifiers changed from: private */
        public Long user_id;
        /* access modifiers changed from: private */
        public String version;
        /* access modifiers changed from: private */
        public WebContext web;

        private Builder() {
        }

        public Builder(Long timestamp2, String source2, String platform2, String user_agent2) {
            this.timestamp = timestamp2;
            this.source = source2;
            this.platform = platform2;
            this.user_agent = user_agent2;
        }

        public Builder version(String version2) {
            this.version = version2;
            return this;
        }

        public Builder user_id(Long user_id2) {
            this.user_id = user_id2;
            return this;
        }

        public Builder language(String language2) {
            this.language = language2;
            return this;
        }

        public Builder locale(String locale2) {
            this.locale = locale2;
            return this;
        }

        public Builder campaign(String campaign2) {
            this.campaign = campaign2;
            return this;
        }

        public Builder affiliate_id(String affiliate_id2) {
            this.affiliate_id = affiliate_id2;
            return this;
        }

        public Builder screen_width(Long screen_width2) {
            this.screen_width = screen_width2;
            return this;
        }

        public Builder screen_height(Long screen_height2) {
            this.screen_height = screen_height2;
            return this;
        }

        public Builder mobile(MobileContext mobile2) {
            this.mobile = mobile2;
            return this;
        }

        public Builder extra_data(Map<String, String> extra_data2) {
            this.extra_data = extra_data2;
            return this;
        }

        public Builder client_session_id(String client_session_id2) {
            this.client_session_id = client_session_id2;
            return this;
        }

        public Context build() {
            if (this.timestamp == null) {
                throw new IllegalStateException("Required field 'timestamp' is missing");
            } else if (this.source == null) {
                throw new IllegalStateException("Required field 'source' is missing");
            } else if (this.platform == null) {
                throw new IllegalStateException("Required field 'platform' is missing");
            } else if (this.user_agent != null) {
                return new Context(this);
            } else {
                throw new IllegalStateException("Required field 'user_agent' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.core.context.v2.Context$ContextAdapter */
    private static final class ContextAdapter implements Adapter<Context, Builder> {
        private ContextAdapter() {
        }

        public void write(Protocol protocol, Context struct) throws IOException {
            protocol.writeStructBegin("Context");
            protocol.writeFieldBegin(ErfExperimentsModel.TIMESTAMP, 1, 10);
            protocol.writeI64(struct.timestamp.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source", 2, PassportService.SF_DG11);
            protocol.writeString(struct.source);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PushService.PARAM_PLATFORM, 3, PassportService.SF_DG11);
            protocol.writeString(struct.platform);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_agent", 4, PassportService.SF_DG11);
            protocol.writeString(struct.user_agent);
            protocol.writeFieldEnd();
            if (struct.version != null) {
                protocol.writeFieldBegin("version", 5, PassportService.SF_DG11);
                protocol.writeString(struct.version);
                protocol.writeFieldEnd();
            }
            if (struct.bev != null) {
                protocol.writeFieldBegin("bev", 6, PassportService.SF_DG11);
                protocol.writeString(struct.bev);
                protocol.writeFieldEnd();
            }
            if (struct.user_id != null) {
                protocol.writeFieldBegin("user_id", 7, 10);
                protocol.writeI64(struct.user_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.hash_user_id != null) {
                protocol.writeFieldBegin("hash_user_id", 8, PassportService.SF_DG11);
                protocol.writeString(struct.hash_user_id);
                protocol.writeFieldEnd();
            }
            if (struct.language != null) {
                protocol.writeFieldBegin("language", 9, PassportService.SF_DG11);
                protocol.writeString(struct.language);
                protocol.writeFieldEnd();
            }
            if (struct.locale != null) {
                protocol.writeFieldBegin(AccountKitGraphConstants.PARAMETER_LOCALE, 10, PassportService.SF_DG11);
                protocol.writeString(struct.locale);
                protocol.writeFieldEnd();
            }
            if (struct.campaign != null) {
                protocol.writeFieldBegin("campaign", 11, PassportService.SF_DG11);
                protocol.writeString(struct.campaign);
                protocol.writeFieldEnd();
            }
            if (struct.affiliate_id != null) {
                protocol.writeFieldBegin(AirbnbConstants.PREFS_AFFILIATE_ID, 12, PassportService.SF_DG11);
                protocol.writeString(struct.affiliate_id);
                protocol.writeFieldEnd();
            }
            if (struct.screen_width != null) {
                protocol.writeFieldBegin("screen_width", 13, 10);
                protocol.writeI64(struct.screen_width.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.screen_height != null) {
                protocol.writeFieldBegin("screen_height", 14, 10);
                protocol.writeI64(struct.screen_height.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.web != null) {
                protocol.writeFieldBegin(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB, 15, PassportService.SF_DG12);
                WebContext.ADAPTER.write(protocol, struct.web);
                protocol.writeFieldEnd();
            }
            if (struct.monorail != null) {
                protocol.writeFieldBegin("monorail", 16, PassportService.SF_DG12);
                MonorailContext.ADAPTER.write(protocol, struct.monorail);
                protocol.writeFieldEnd();
            }
            if (struct.mobile != null) {
                protocol.writeFieldBegin("mobile", 17, PassportService.SF_DG12);
                MobileContext.ADAPTER.write(protocol, struct.mobile);
                protocol.writeFieldEnd();
            }
            if (struct.extra_data != null) {
                protocol.writeFieldBegin("extra_data", 18, 13);
                protocol.writeMapBegin(PassportService.SF_DG11, PassportService.SF_DG11, struct.extra_data.size());
                for (Entry<String, String> entry0 : struct.extra_data.entrySet()) {
                    String value0 = (String) entry0.getValue();
                    protocol.writeString((String) entry0.getKey());
                    protocol.writeString(value0);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
            if (struct.client_session_id != null) {
                protocol.writeFieldBegin("client_session_id", 19, PassportService.SF_DG11);
                protocol.writeString(struct.client_session_id);
                protocol.writeFieldEnd();
            }
            if (struct.bev_expiry != null) {
                protocol.writeFieldBegin("bev_expiry", 20, PassportService.SF_DG11);
                protocol.writeString(struct.bev_expiry);
                protocol.writeFieldEnd();
            }
            if (struct.service_context != null) {
                protocol.writeFieldBegin("service_context", 21, PassportService.SF_DG12);
                ServiceContext.ADAPTER.write(protocol, struct.service_context);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private Context(Builder builder) {
        this.timestamp = builder.timestamp;
        this.source = builder.source;
        this.platform = builder.platform;
        this.user_agent = builder.user_agent;
        this.version = builder.version;
        this.bev = builder.bev;
        this.user_id = builder.user_id;
        this.hash_user_id = builder.hash_user_id;
        this.language = builder.language;
        this.locale = builder.locale;
        this.campaign = builder.campaign;
        this.affiliate_id = builder.affiliate_id;
        this.screen_width = builder.screen_width;
        this.screen_height = builder.screen_height;
        this.web = builder.web;
        this.monorail = builder.monorail;
        this.mobile = builder.mobile;
        this.extra_data = builder.extra_data == null ? null : Collections.unmodifiableMap(builder.extra_data);
        this.client_session_id = builder.client_session_id;
        this.bev_expiry = builder.bev_expiry;
        this.service_context = builder.service_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Context)) {
            return false;
        }
        Context that = (Context) other;
        if ((this.timestamp == that.timestamp || this.timestamp.equals(that.timestamp)) && ((this.source == that.source || this.source.equals(that.source)) && ((this.platform == that.platform || this.platform.equals(that.platform)) && ((this.user_agent == that.user_agent || this.user_agent.equals(that.user_agent)) && ((this.version == that.version || (this.version != null && this.version.equals(that.version))) && ((this.bev == that.bev || (this.bev != null && this.bev.equals(that.bev))) && ((this.user_id == that.user_id || (this.user_id != null && this.user_id.equals(that.user_id))) && ((this.hash_user_id == that.hash_user_id || (this.hash_user_id != null && this.hash_user_id.equals(that.hash_user_id))) && ((this.language == that.language || (this.language != null && this.language.equals(that.language))) && ((this.locale == that.locale || (this.locale != null && this.locale.equals(that.locale))) && ((this.campaign == that.campaign || (this.campaign != null && this.campaign.equals(that.campaign))) && ((this.affiliate_id == that.affiliate_id || (this.affiliate_id != null && this.affiliate_id.equals(that.affiliate_id))) && ((this.screen_width == that.screen_width || (this.screen_width != null && this.screen_width.equals(that.screen_width))) && ((this.screen_height == that.screen_height || (this.screen_height != null && this.screen_height.equals(that.screen_height))) && ((this.web == that.web || (this.web != null && this.web.equals(that.web))) && ((this.monorail == that.monorail || (this.monorail != null && this.monorail.equals(that.monorail))) && ((this.mobile == that.mobile || (this.mobile != null && this.mobile.equals(that.mobile))) && ((this.extra_data == that.extra_data || (this.extra_data != null && this.extra_data.equals(that.extra_data))) && ((this.client_session_id == that.client_session_id || (this.client_session_id != null && this.client_session_id.equals(that.client_session_id))) && (this.bev_expiry == that.bev_expiry || (this.bev_expiry != null && this.bev_expiry.equals(that.bev_expiry)))))))))))))))))))))) {
            if (this.service_context == that.service_context) {
                return true;
            }
            if (this.service_context != null && this.service_context.equals(that.service_context)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((((((((((((((((((((16777619 ^ this.timestamp.hashCode()) * -2128831035) ^ this.source.hashCode()) * -2128831035) ^ this.platform.hashCode()) * -2128831035) ^ this.user_agent.hashCode()) * -2128831035) ^ (this.version == null ? 0 : this.version.hashCode())) * -2128831035) ^ (this.bev == null ? 0 : this.bev.hashCode())) * -2128831035) ^ (this.user_id == null ? 0 : this.user_id.hashCode())) * -2128831035) ^ (this.hash_user_id == null ? 0 : this.hash_user_id.hashCode())) * -2128831035) ^ (this.language == null ? 0 : this.language.hashCode())) * -2128831035) ^ (this.locale == null ? 0 : this.locale.hashCode())) * -2128831035) ^ (this.campaign == null ? 0 : this.campaign.hashCode())) * -2128831035) ^ (this.affiliate_id == null ? 0 : this.affiliate_id.hashCode())) * -2128831035) ^ (this.screen_width == null ? 0 : this.screen_width.hashCode())) * -2128831035) ^ (this.screen_height == null ? 0 : this.screen_height.hashCode())) * -2128831035) ^ (this.web == null ? 0 : this.web.hashCode())) * -2128831035) ^ (this.monorail == null ? 0 : this.monorail.hashCode())) * -2128831035) ^ (this.mobile == null ? 0 : this.mobile.hashCode())) * -2128831035) ^ (this.extra_data == null ? 0 : this.extra_data.hashCode())) * -2128831035) ^ (this.client_session_id == null ? 0 : this.client_session_id.hashCode())) * -2128831035) ^ (this.bev_expiry == null ? 0 : this.bev_expiry.hashCode())) * -2128831035;
        if (this.service_context != null) {
            i = this.service_context.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "Context{timestamp=" + this.timestamp + ", source=" + this.source + ", platform=" + this.platform + ", user_agent=" + this.user_agent + ", version=" + this.version + ", bev=" + this.bev + ", user_id=" + this.user_id + ", hash_user_id=" + this.hash_user_id + ", language=" + this.language + ", locale=" + this.locale + ", campaign=" + this.campaign + ", affiliate_id=" + this.affiliate_id + ", screen_width=" + this.screen_width + ", screen_height=" + this.screen_height + ", web=" + this.web + ", monorail=" + this.monorail + ", mobile=" + this.mobile + ", extra_data=" + this.extra_data + ", client_session_id=" + this.client_session_id + ", bev_expiry=" + this.bev_expiry + ", service_context=" + this.service_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
