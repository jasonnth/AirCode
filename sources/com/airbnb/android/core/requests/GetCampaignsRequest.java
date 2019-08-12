package com.airbnb.android.core.requests;

import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.net.ConnectivityManager;
import android.support.p000v4.net.ConnectivityManagerCompat;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.responses.CampaignsResponse;
import com.airbnb.android.core.responses.CampaignsResponse.Campaign;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import retrofit2.Query;

public class GetCampaignsRequest extends BaseRequestV2<CampaignsResponse> {
    private final Strap params;

    public static GetCampaignsRequest forLocation(Location location) {
        Strap params2 = Strap.make();
        if (location != null) {
            params2.mo11635kv("lat", location.getLatitude()).mo11635kv("lng", location.getLongitude());
        }
        return new GetCampaignsRequest(params2);
    }

    private GetCampaignsRequest(Strap params2) {
        this.params = params2;
    }

    public String getPath() {
        return "campaigns";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public AirResponse<CampaignsResponse> transformResponse(AirResponse<CampaignsResponse> response) {
        trackEvent();
        CampaignsResponse data = (CampaignsResponse) response.body();
        Editor editor = Trebuchet.getLegacyTrebuchetPrefs(CoreApplication.appContext()).edit().clear();
        for (Campaign campaign : data.campaigns) {
            for (Entry<String, Object> entry : campaign.statuses.entrySet()) {
                editor.putString(Trebuchet.buildKey(campaign.key, (String) entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        editor.apply();
        AirbnbEventLogger.event().name("campaign_request").mo8238kv("page", "mobile_launch").mo8238kv("action", "campaign_request").mo8238kv("campaigns", new JSONArray(data.campaigns).toString()).track();
        Trebuchet.clearLoggedExperiments();
        return response;
    }

    private void trackEvent() {
        boolean isLowEndDevice = MemoryUtils.isLowMemoryDevice();
        boolean isMeteredNetwork = ConnectivityManagerCompat.isActiveNetworkMetered((ConnectivityManager) CoreApplication.appContext().getSystemService("connectivity"));
        AirbnbEventLogger.track("android_eng", Strap.make().mo11640kv("is_metered_network", isMeteredNetwork).mo11640kv("is_low_end_device", isLowEndDevice).mo11640kv("oom_in_past_week", CoreApplication.instance().component().memoryUtils().hasOomOccurredInLastWeek()));
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Type successResponseType() {
        return CampaignsResponse.class;
    }
}
