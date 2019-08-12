package com.airbnb.android.core.aireventlogger;

import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.JPushConstants.PushService;

@JsonSerialize
public final class JitneyJSONEvent {
    Map<String, Object> eventData;
    @JsonProperty
    String uuid;

    public JitneyJSONEvent() {
    }

    public JitneyJSONEvent(Map<String, Object> rootEventData, Context jitneyContext) {
        Check.notNull(rootEventData, "rootEventData == null");
        Map<String, Object> coreContext = new ArrayMap<>();
        coreContext.put(ErfExperimentsModel.TIMESTAMP, jitneyContext.timestamp);
        coreContext.put("source", jitneyContext.source);
        coreContext.put(PushService.PARAM_PLATFORM, jitneyContext.platform);
        coreContext.put("user_agent", jitneyContext.user_agent);
        coreContext.put("version", jitneyContext.version);
        coreContext.put("bev", jitneyContext.bev);
        coreContext.put("user_id", jitneyContext.user_id);
        coreContext.put("hash_user_id", jitneyContext.hash_user_id);
        coreContext.put("language", jitneyContext.language);
        coreContext.put(AccountKitGraphConstants.PARAMETER_LOCALE, jitneyContext.locale);
        coreContext.put("campaign", jitneyContext.campaign);
        coreContext.put(AirbnbConstants.PREFS_AFFILIATE_ID, jitneyContext.affiliate_id);
        coreContext.put("screen_width", jitneyContext.screen_width);
        coreContext.put("screen_height", jitneyContext.screen_height);
        coreContext.put("extra_data", jitneyContext.extra_data);
        Map<String, Object> mobileContext = new ArrayMap<>();
        mobileContext.put("device_type", jitneyContext.mobile.device_type);
        mobileContext.put("device_id", jitneyContext.mobile.device_id);
        mobileContext.put("version_code", jitneyContext.mobile.version_code);
        mobileContext.put("screen_orientation", Integer.valueOf(jitneyContext.mobile.screen_orientation.value));
        mobileContext.put(JPushReportInterface.NETWORK_TYPE, jitneyContext.mobile.network_type);
        mobileContext.put("carrier_name", jitneyContext.mobile.carrier_name);
        mobileContext.put("carrier_country", jitneyContext.mobile.carrier_country);
        coreContext.put("mobile", mobileContext);
        coreContext.put("extra_data", jitneyContext.extra_data);
        coreContext.put("client_session_id", jitneyContext.client_session_id);
        Map<String, Object> mergedContext = new ArrayMap<>();
        mergedContext.putAll(coreContext);
        Map<String, Object> mergedEventData = (Map) rootEventData.get("event_data");
        Map<String, Object> providedContext = (Map) mergedEventData.get(PlaceFields.CONTEXT);
        if (providedContext != null) {
            mergedContext.putAll(providedContext);
        }
        mergedContext.put(ErfExperimentsModel.TIMESTAMP, jitneyContext.timestamp);
        mergedEventData.put(PlaceFields.CONTEXT, mergedContext);
        this.uuid = UUID.randomUUID().toString();
        this.eventData = Collections.singletonMap("event_data", mergedEventData);
    }

    @JsonAnyGetter
    public Map<String, Object> eventData() {
        return this.eventData;
    }

    @JsonAnySetter
    public void setEventData(String key, Object value) {
        if (this.eventData == null) {
            this.eventData = new HashMap();
        }
        this.eventData.put(key, value);
    }
}
