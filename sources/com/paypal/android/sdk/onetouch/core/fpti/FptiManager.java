package com.paypal.android.sdk.onetouch.core.fpti;

import android.os.Handler;
import android.os.Looper;
import com.paypal.android.sdk.data.collector.InstallationIdentifier;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.base.DeviceInspector;
import com.paypal.android.sdk.onetouch.core.base.URLEncoderHelper;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import com.paypal.android.sdk.onetouch.core.network.PayPalHttpClient;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class FptiManager {
    private final ContextInspector mContextInspector;
    /* access modifiers changed from: private */
    public final PayPalHttpClient mHttpClient;
    private FptiToken mToken;

    public FptiManager(ContextInspector contextInspector, PayPalHttpClient httpClient) {
        this.mContextInspector = contextInspector;
        this.mHttpClient = httpClient;
    }

    public void trackFpti(TrackingPoint point, String environmentName, Map<String, String> fptiDataBundle, Protocol protocol) {
        if (!EnvironmentManager.isMock(environmentName)) {
            if (this.mToken == null || !this.mToken.isValid()) {
                this.mToken = new FptiToken();
            }
            long currentTimeInMillis = System.currentTimeMillis();
            String deviceId = URLEncoderHelper.encode(InstallationIdentifier.getInstallationGUID(this.mContextInspector.getContext()));
            String abcde = "mobile:otc:" + point.getCd() + ":" + (protocol != null ? protocol.name() : "");
            String abcdexyz_error = abcde + ":" + ("Android:" + environmentName + ":") + (point.hasError() ? "|error" : "");
            Map<String, String> params = new HashMap<>(fptiDataBundle);
            params.put("apid", DeviceInspector.getApplicationInfoName(this.mContextInspector.getContext()) + "|" + "2.5.2" + "|" + this.mContextInspector.getContext().getPackageName());
            params.put("bchn", "otc");
            params.put("bzsr", "mobile");
            params.put("dsid", deviceId);
            params.put("e", "im");
            params.put("g", getGmtOffsetInMinutes());
            params.put("lgin", "out");
            params.put("mapv", "2.5.2");
            params.put("mcar", DeviceInspector.getSimOperatorName(this.mContextInspector.getContext()));
            params.put("mdvs", DeviceInspector.getDeviceName());
            params.put("mosv", DeviceInspector.getOs());
            params.put("page", abcdexyz_error);
            params.put("pgrp", abcde);
            params.put("rsta", Locale.getDefault().toString());
            params.put("srce", "otc");
            params.put("sv", "mobile");
            params.put("t", Long.toString(currentTimeInMillis - ((long) getGMTOffset())));
            params.put("vers", "Android:" + environmentName + ":");
            params.put("vid", this.mToken.mToken);
            try {
                JSONObject actor = new JSONObject();
                actor.accumulate("tracking_visitor_id", deviceId);
                actor.accumulate("tracking_visit_id", this.mToken.mToken);
                JSONObject events = new JSONObject();
                events.accumulate("actor", actor);
                events.accumulate("channel", "mobile");
                events.accumulate("tracking_event", Long.toString(currentTimeInMillis));
                events.accumulate("event_params", getEventParams(params));
                sendRequest(new JSONObject().accumulate("events", events).toString());
            } catch (JSONException e) {
            }
        }
    }

    private JSONObject getEventParams(Map<String, String> params) throws JSONException {
        JSONObject ret = new JSONObject();
        for (String key : params.keySet()) {
            ret.accumulate(key, params.get(key));
        }
        return ret;
    }

    /* access modifiers changed from: 0000 */
    public void sendRequest(final String data) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                FptiManager.this.mHttpClient.post("tracking/events", data, null);
            }
        }, (long) ((new Random().nextInt(190) + 10) * 1000));
    }

    private int getGMTOffset() {
        return new GregorianCalendar().getTimeZone().getRawOffset();
    }

    private String getGmtOffsetInMinutes() {
        return Integer.toString((getGMTOffset() / 1000) / 60);
    }
}
