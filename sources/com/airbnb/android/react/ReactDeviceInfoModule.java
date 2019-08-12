package com.airbnb.android.react;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import com.airbnb.android.core.net.NetworkMonitor;
import com.airbnb.android.core.utils.MiscUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;

public class ReactDeviceInfoModule extends VersionedReactModuleBase {
    private static final String DEVICE_INFO_UPDATED = "airbnb.deviceInfoUpdated";
    private static final String LANDSCAPE = "LANDSCAPE";
    private static final String NETWORK_INFO = "networkInfo";
    private static final String ORIENTATION = "orientation";
    private static final String ORIENTATION_UPDATED = "airbnb.orientationUpdated";
    private static final String PORTRAIT = "PORTRAIT";
    private static final int VERSION = 4;
    private static final String VOLUME_UPDATED = "airbnb.volumeUpdated";
    /* access modifiers changed from: private */
    public boolean isLandscape = MiscUtils.isLandscape(getReactApplicationContext());
    private final NetworkMonitor networkMonitor;

    private class VolumeContentObserver extends ContentObserver {
        Context context;
        int currentVolume;

        public VolumeContentObserver(Context context2, Handler handler) {
            super(handler);
            this.context = context2;
            this.currentVolume = ((AudioManager) context2.getSystemService("audio")).getStreamVolume(3);
        }

        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            int nextVolume = ((AudioManager) this.context.getSystemService("audio")).getStreamVolume(3);
            int delta = nextVolume - this.currentVolume;
            if (delta != 0) {
                ReactNativeUtils.maybeEmitEvent(ReactDeviceInfoModule.this.getReactApplicationContext(), ReactDeviceInfoModule.VOLUME_UPDATED, ConversionUtil.toWritableMap(ImmutableMap.builder().put("volume", Integer.valueOf(nextVolume)).put("increasing", Boolean.valueOf(delta > 0)).build()));
                this.currentVolume = nextVolume;
            }
        }
    }

    ReactDeviceInfoModule(ReactApplicationContext context, NetworkMonitor networkMonitor2) {
        super(context, 4);
        this.networkMonitor = networkMonitor2;
        IntentFilter connectivityChangeFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        connectivityChangeFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        context.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                ReactNativeUtils.maybeEmitEvent(ReactDeviceInfoModule.this.getReactApplicationContext(), ReactDeviceInfoModule.DEVICE_INFO_UPDATED, ConversionUtil.toWritableMap(Collections.singletonMap(ReactDeviceInfoModule.NETWORK_INFO, ReactDeviceInfoModule.this.getNetworkInfo())));
            }
        }, connectivityChangeFilter);
        context.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                boolean isLandscapeNext = MiscUtils.isLandscape(ReactDeviceInfoModule.this.getReactApplicationContext());
                if (ReactDeviceInfoModule.this.isLandscape != isLandscapeNext) {
                    ReactDeviceInfoModule.this.isLandscape = isLandscapeNext;
                    ReactNativeUtils.maybeEmitEvent(ReactDeviceInfoModule.this.getReactApplicationContext(), ReactDeviceInfoModule.ORIENTATION_UPDATED, ConversionUtil.toWritableMap(Collections.singletonMap(ReactDeviceInfoModule.ORIENTATION, ReactDeviceInfoModule.this.isLandscape ? ReactDeviceInfoModule.LANDSCAPE : ReactDeviceInfoModule.PORTRAIT)));
                }
            }
        }, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        context.getContentResolver().registerContentObserver(System.CONTENT_URI, true, new VolumeContentObserver(context, new Handler(Looper.getMainLooper())));
    }

    public String getName() {
        return "AirbnbDeviceInfoModule";
    }

    public Map<String, Object> getConstants() {
        return ImmutableMap.builder().putAll(super.getConstants()).put("isTablet", Boolean.valueOf(MiscUtils.isTabletScreen(getReactApplicationContext()))).put(NETWORK_INFO, getNetworkInfo()).put("hasGooglePlayServices", Boolean.valueOf(MiscUtils.hasGooglePlayServices(getReactApplicationContext()))).put(ORIENTATION, this.isLandscape ? LANDSCAPE : PORTRAIT).build();
    }

    @ReactMethod
    public void toggleSoundActive(boolean soundOn) {
    }

    /* access modifiers changed from: private */
    public String getNetworkInfo() {
        return this.networkMonitor.getNetworkClass().description.toLowerCase();
    }
}
