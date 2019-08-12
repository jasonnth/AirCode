package com.airbnb.android.core.aireventlogger;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.airbnb.android.core.data.AffiliateData;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@JsonSerialize
public final class AirbnbEvent {
    private static final Pattern EVENT_NAME_REGEX = Pattern.compile("^[A-Za-z_][A-Za-z_0-9]*$");
    @JsonProperty
    public EventData eventData = new EventData();
    @JsonProperty
    public String eventName;
    @JsonProperty
    public String uuid;

    public static class EventData {
        @JsonProperty
        public String affiliateCampaign;
        @JsonProperty
        public Integer affiliateId;
        @JsonProperty
        public String androidId;
        @JsonProperty
        public String buildConfiguration;
        @JsonProperty
        public String cellularType;
        @JsonProperty
        public float density;
        @JsonIgnore
        public Map<String, Object> extraInfo;
        @JsonProperty
        public int height;
        @JsonProperty
        public String ipCountryCode;
        @JsonProperty
        public boolean isGoogleServiceAvailable;
        @JsonProperty
        public boolean isLoggedIn;
        @JsonProperty
        public final String language = Locale.getDefault().getLanguage();
        @JsonProperty
        public String localAfClick;
        @JsonProperty
        public final Locale locale = Locale.getDefault();
        @JsonProperty
        public String networkType;
        @JsonProperty
        public String[] onResumeHistory;
        @JsonProperty
        public final int osVersion = VERSION.SDK_INT;
        @JsonProperty
        public final String source = "android";
        @JsonProperty
        public final long timestamp = Calendar.getInstance().getTimeInMillis();
        @JsonProperty
        public Long userId;
        @JsonProperty
        public String version;
        @JsonProperty
        public int width;

        @JsonAnyGetter
        public Map<String, Object> extraInfo() {
            return this.extraInfo;
        }

        @JsonAnySetter
        public void setExtraInfo(String key, Object value) {
            if (this.extraInfo == null) {
                this.extraInfo = new HashMap();
            }
            this.extraInfo.put(key, value);
        }
    }

    public AirbnbEvent() {
    }

    public AirbnbEvent(Context context, String eventName2, Map<String, Object> data, User currentUser, AffiliateData affiliateData, String[] onResumeHistory) {
        Long l;
        this.eventName = (String) Check.notNull(eventName2, "eventName == null");
        this.eventData.extraInfo = (Map) Check.notNull(data, "eventData == null");
        this.uuid = UUID.randomUUID().toString();
        if (!EVENT_NAME_REGEX.matcher(eventName2).matches()) {
            throw new IllegalArgumentException("Invalid event name. Event name must fit regular expression: " + EVENT_NAME_REGEX);
        }
        this.eventData.isLoggedIn = currentUser != null;
        this.eventData.version = BuildHelper.versionName();
        this.eventData.androidId = MiscUtils.getAndroidId(context);
        this.eventData.networkType = NetworkUtil.getNetworkType(context);
        this.eventData.ipCountryCode = AppLaunchUtils.getCountryOfIPAddress();
        this.eventData.isGoogleServiceAvailable = MiscUtils.hasGooglePlayServices(context);
        this.eventData.buildConfiguration = BuildHelper.buildType();
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
        this.eventData.width = metrics.widthPixels;
        this.eventData.height = metrics.heightPixels;
        this.eventData.density = metrics.density;
        if (this.eventData.networkType.equals(NetworkUtil.NETWORK_TYPE_CELLULAR)) {
            this.eventData.cellularType = NetworkUtil.getCellularType(context);
        } else {
            this.eventData.cellularType = null;
        }
        EventData eventData2 = this.eventData;
        if (this.eventData.isLoggedIn) {
            l = Long.valueOf(currentUser.getId());
        } else {
            l = null;
        }
        eventData2.userId = l;
        if (affiliateData != null) {
            this.eventData.affiliateCampaign = affiliateData.campaign();
            this.eventData.affiliateId = Integer.valueOf(affiliateData.affiliateId());
            this.eventData.localAfClick = affiliateData.localAfClick();
        } else {
            this.eventData.affiliateCampaign = null;
            this.eventData.affiliateId = null;
            this.eventData.localAfClick = null;
        }
        this.eventData.onResumeHistory = onResumeHistory;
    }
}
