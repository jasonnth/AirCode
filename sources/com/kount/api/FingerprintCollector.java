package com.kount.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

class FingerprintCollector extends CollectorTaskBase {
    protected Context context = null;

    enum DeviceIDType {
        ANDROID_ID("ANDROID_ID"),
        ANDROID_SERIAL("ANDROID_SERIAL"),
        MAC_HASH("MAC_HASH"),
        UID("UID");
        
        private final String name;

        private DeviceIDType(String name2) {
            this.name = name2;
        }

        public String toString() {
            return this.name;
        }
    }

    FingerprintCollector(Object debugHandler, Context ctx) {
        super(debugHandler);
        this.context = ctx;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return "Fingerprint Collector";
    }

    /* access modifiers changed from: 0000 */
    public String getInternalName() {
        return internalName();
    }

    static String internalName() {
        return "collector_device_cookie";
    }

    /* access modifiers changed from: 0000 */
    public void collect() {
        HashMap<String, String> deviceCookies = new HashMap<>();
        String androidID = Secure.getString(this.context.getContentResolver(), JPushReportInterface.ANDROID_ID);
        debugMessage(String.format("Android ID = %s", new Object[]{androidID}));
        deviceCookies.put(DeviceIDType.ANDROID_ID.toString(), HashUtils.convertToSha256Hash(DeviceIDType.ANDROID_ID.toString() + androidID));
        deviceCookies.put(DeviceIDType.ANDROID_SERIAL.toString(), HashUtils.convertToSha256Hash(DeviceIDType.ANDROID_SERIAL.toString() + Build.SERIAL));
        String macArray = getMACAddresses();
        if (macArray != null) {
            deviceCookies.put(DeviceIDType.MAC_HASH.toString(), HashUtils.convertToSha256Hash(DeviceIDType.MAC_HASH.toString() + macArray));
        }
        String oldCookies = readCookies();
        String uid = null;
        if (oldCookies != null && oldCookies.contains(DeviceIDType.UID.toString())) {
            try {
                int start = oldCookies.indexOf(DeviceIDType.UID.toString()) + DeviceIDType.UID.toString().length() + 3;
                uid = oldCookies.substring(start, oldCookies.indexOf(34, start));
            } catch (IndexOutOfBoundsException e) {
            }
        }
        if (uid == null) {
            uid = HashUtils.convertToSha256Hash(DeviceIDType.UID.toString() + UUID.randomUUID().toString());
        }
        deviceCookies.put(DeviceIDType.UID.toString(), uid);
        JSONObject deviceCookiesJSON = new JSONObject(deviceCookies);
        addDataPoint(PostKey.DEVICE_COOKIE.toString(), deviceCookiesJSON.toString());
        if (oldCookies != null) {
            addDataPoint(PostKey.OLD_DEVICE_COOKIE.toString(), oldCookies);
        }
        saveCookies(deviceCookiesJSON.toString());
        callCompletionHandler(Boolean.valueOf(true), null);
    }

    /* access modifiers changed from: protected */
    public String readCookies() {
        SharedPreferences sp = this.context.getSharedPreferences("k_prefs", 0);
        if (sp != null) {
            return sp.getString("lic", null);
        }
        return null;
    }

    private void saveCookies(String cookies) {
        Editor editor = this.context.getSharedPreferences("k_prefs", 0).edit();
        editor.putString("lic", cookies);
        editor.apply();
    }

    private String getMACAddresses() {
        ArrayList<String> addresses = new ArrayList<>();
        try {
            WifiInfo wifi = ((WifiManager) this.context.getSystemService("wifi")).getConnectionInfo();
            if (wifi != null) {
                String macAddress = wifi.getMacAddress();
                if (macAddress != null) {
                    addresses.add(macAddress.replace(":", ""));
                }
            }
            debugMessage("Wi-Fi not enabled, skipping.");
        } catch (SecurityException e) {
            debugMessage("Wi-Fi permission denied.");
        }
        try {
            Enumeration<NetworkInterface> niEnum = NetworkInterface.getNetworkInterfaces();
            while (niEnum.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) niEnum.nextElement();
                if (ni.isUp()) {
                    String str = "Network:%s, virtual:%s.";
                    Object[] objArr = new Object[2];
                    objArr[0] = ni.getName();
                    objArr[1] = ni.isVirtual() ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
                    debugMessage(String.format(str, objArr));
                    byte[] mac = ni.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : mac) {
                            sb.append(String.format("%02X", new Object[]{Byte.valueOf(b)}));
                        }
                        addresses.add(sb.toString());
                    }
                }
            }
        } catch (SocketException e2) {
            debugMessage("Bad socket connection, skipping.");
            addSoftError(SoftError.SERVICE_UNAVAILABLE.toString());
        } catch (SecurityException e3) {
            debugMessage("Permission denied, skipping.");
            addSoftError(SoftError.PERMISSION_DENIED.toString());
        }
        Collections.sort(addresses);
        if (addresses.size() <= 0) {
            return null;
        }
        StringBuilder macList = new StringBuilder("{");
        Iterator it = addresses.iterator();
        while (it.hasNext()) {
            macList.append((String) it.next());
            macList.append(",");
        }
        macList.append("}");
        return macList.toString();
    }
}
