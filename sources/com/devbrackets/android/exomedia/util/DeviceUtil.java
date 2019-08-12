package com.devbrackets.android.exomedia.util;

import android.app.UiModeManager;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import java.util.LinkedList;
import java.util.List;

public class DeviceUtil {
    protected static final List<NonCompatibleDevice> NON_COMPATIBLE_DEVICES = new LinkedList();

    public static class NonCompatibleDevice {
        private boolean ignoreModel = true;
        private final String manufacturer;
        private final String model = null;

        public NonCompatibleDevice(String manufacturer2) {
            this.manufacturer = manufacturer2;
        }

        public boolean ignoreModel() {
            return this.ignoreModel;
        }

        public String getModel() {
            return this.model;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }
    }

    static {
        NON_COMPATIBLE_DEVICES.add(new NonCompatibleDevice("Amazon"));
    }

    public boolean supportsExoPlayer(Context context) {
        if (VERSION.SDK_INT >= 16 && !isNotCompatible(NON_COMPATIBLE_DEVICES)) {
            return true;
        }
        if (!Build.MANUFACTURER.equalsIgnoreCase("Amazon") || (!isDeviceTV(context) && VERSION.SDK_INT < 21)) {
            return false;
        }
        return true;
    }

    public boolean isNotCompatible(List<NonCompatibleDevice> nonCompatibleDevices) {
        for (NonCompatibleDevice device : nonCompatibleDevices) {
            if (Build.MANUFACTURER.equalsIgnoreCase(device.getManufacturer()) && (device.ignoreModel() || Build.DEVICE.equalsIgnoreCase(device.getModel()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isDeviceTV(Context context) {
        if (VERSION.SDK_INT < 21) {
            return false;
        }
        UiModeManager uiManager = (UiModeManager) context.getSystemService("uimode");
        if (uiManager == null || uiManager.getCurrentModeType() != 4) {
            return false;
        }
        return true;
    }
}
