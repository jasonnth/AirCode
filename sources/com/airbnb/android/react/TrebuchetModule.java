package com.airbnb.android.react;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.android.core.events.TrebuchetUpdatedEvent;
import com.airbnb.android.core.utils.Trebuchet;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.Map;

class TrebuchetModule extends VersionedReactModuleBase {
    private static final String CAMPAIGNS_UPDATED_EVENT = "airbnb.campaignsUpdated";
    private static final int VERSION = 1;

    TrebuchetModule(ReactApplicationContext reactContext, Bus bus) {
        super(reactContext, 1);
        new Handler(Looper.getMainLooper()).post(TrebuchetModule$$Lambda$1.lambdaFactory$(this, bus));
    }

    public String getName() {
        return "TrebuchetBridge";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = super.getConstants();
        constants.put("initialCampaignData", getCampaignData().get("campaignData"));
        return constants;
    }

    private Map<String, Object> getCampaignData() {
        return MapBuilder.m1882of("campaignData", Trebuchet.getTrebuchetPrefs(getReactApplicationContext()).getAll());
    }

    @Subscribe
    public void onTrebuchetUpdated(TrebuchetUpdatedEvent e) {
        ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), CAMPAIGNS_UPDATED_EVENT, ConversionUtil.toWritableMap((Map) getCampaignData().get("campaignData")));
    }
}
