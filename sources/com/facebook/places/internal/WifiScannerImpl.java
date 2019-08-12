package com.facebook.places.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WifiScannerImpl implements WifiScanner {
    private ScanResultBroadcastReceiver broadcastReceiver;
    private Context context;
    private final LocationPackageRequestParams params;
    /* access modifiers changed from: private */
    public final Object scanLock = new Object();
    private WifiManager wifiManager;

    private class ScanResultBroadcastReceiver extends BroadcastReceiver {
        private ScanResultBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                synchronized (WifiScannerImpl.this.scanLock) {
                    WifiScannerImpl.this.scanLock.notify();
                }
                WifiScannerImpl.this.unregisterBroadcastReceiver();
            }
        }
    }

    WifiScannerImpl(Context context2, LocationPackageRequestParams params2) {
        this.context = context2;
        this.params = params2;
    }

    public void initAndCheckEligibility() throws ScannerException {
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.wifi")) {
            throw new ScannerException(Type.NOT_SUPPORTED);
        } else if (!Validate.hasWiFiPermission(this.context)) {
            throw new ScannerException(Type.PERMISSION_DENIED);
        } else {
            if (this.wifiManager == null) {
                this.wifiManager = (WifiManager) this.context.getSystemService("wifi");
            }
            if (!isWifiScanningAlwaysOn() && !this.wifiManager.isWifiEnabled()) {
                throw new ScannerException(Type.DISABLED);
            }
        }
    }

    public WifiScanResult getConnectedWifi() throws ScannerException {
        try {
            WifiInfo wifiInfo = this.wifiManager.getConnectionInfo();
            if (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || wifiInfo.getSupplicantState() != SupplicantState.COMPLETED) {
                return null;
            }
            WifiScanResult wifiScanResult = new WifiScanResult();
            wifiScanResult.bssid = wifiInfo.getBSSID();
            wifiScanResult.ssid = wifiInfo.getSSID();
            wifiScanResult.rssi = wifiInfo.getRssi();
            if (VERSION.SDK_INT < 21) {
                return wifiScanResult;
            }
            wifiScanResult.frequency = wifiInfo.getFrequency();
            return wifiScanResult;
        } catch (Exception e) {
            throw new ScannerException(Type.UNKNOWN_ERROR, e);
        }
    }

    public boolean isWifiScanningEnabled() {
        try {
            initAndCheckEligibility();
            if (Validate.hasLocationPermission(this.context)) {
                return true;
            }
        } catch (ScannerException e) {
        }
        return false;
    }

    private boolean isWifiScanningAlwaysOn() {
        if (VERSION.SDK_INT >= 18) {
            return this.wifiManager.isScanAlwaysAvailable();
        }
        return false;
    }

    private List<WifiScanResult> getCachedScanResults() throws ScannerException {
        try {
            List<ScanResult> scanResults = filterWifiScanResultsByMaxAge(this.wifiManager.getScanResults(), this.params.getWifiScanMaxAgeMs());
            filterResults(scanResults, this.params.getWifiMaxScanResults());
            List<WifiScanResult> wifiScanResults = new ArrayList<>(scanResults.size());
            for (ScanResult scanResult : scanResults) {
                WifiScanResult wifiScanResult = new WifiScanResult();
                wifiScanResult.bssid = scanResult.BSSID;
                wifiScanResult.ssid = scanResult.SSID;
                wifiScanResult.rssi = scanResult.level;
                wifiScanResult.frequency = scanResult.frequency;
                wifiScanResults.add(wifiScanResult);
            }
            return wifiScanResults;
        } catch (Exception e) {
            throw new ScannerException(Type.UNKNOWN_ERROR, e);
        }
    }

    private static void filterResults(List<ScanResult> scanResults, int maxResults) {
        if (scanResults.size() > maxResults) {
            Collections.sort(scanResults, new Comparator<ScanResult>() {
                public int compare(ScanResult lhs, ScanResult rhs) {
                    return rhs.level - lhs.level;
                }
            });
            scanResults.subList(maxResults, scanResults.size()).clear();
        }
    }

    private static List<ScanResult> filterWifiScanResultsByMaxAge(List<ScanResult> scanResults, long maxAgeMs) {
        List<ScanResult> filtered = new ArrayList<>();
        if (scanResults != null) {
            if (VERSION.SDK_INT < 17) {
                filtered.addAll(scanResults);
            } else {
                long nowSinceBootMs = SystemClock.elapsedRealtime();
                for (ScanResult result : scanResults) {
                    long ageMs = nowSinceBootMs - (result.timestamp / 1000);
                    if (ageMs < 0) {
                        ageMs = System.currentTimeMillis() - result.timestamp;
                    }
                    if (ageMs < maxAgeMs) {
                        filtered.add(result);
                    }
                }
            }
        }
        return filtered;
    }

    public synchronized List<WifiScanResult> getWifiScans() throws ScannerException {
        List<WifiScanResult> wifiScanResults;
        wifiScanResults = null;
        if (!this.params.isWifiActiveScanForced()) {
            wifiScanResults = getCachedScanResults();
        }
        boolean isListEmpty = wifiScanResults == null || wifiScanResults.isEmpty();
        if (this.params.isWifiActiveScanForced() || (this.params.isWifiActiveScanAllowed() && isListEmpty)) {
            wifiScanResults = getActiveScanResults();
        }
        return wifiScanResults;
    }

    private List<WifiScanResult> getActiveScanResults() throws ScannerException {
        List<WifiScanResult> wifiScanResults = null;
        try {
            if (Validate.hasChangeWifiStatePermission(this.context)) {
                registerBroadcastReceiver();
                if (this.wifiManager.startScan()) {
                    try {
                        synchronized (this.scanLock) {
                            this.scanLock.wait(this.params.getWifiScanTimeoutMs());
                        }
                    } catch (InterruptedException e) {
                    }
                    wifiScanResults = getCachedScanResults();
                }
            }
        } catch (Exception e2) {
        } finally {
            unregisterBroadcastReceiver();
        }
        return wifiScanResults;
    }

    private void registerBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            unregisterBroadcastReceiver();
        }
        this.broadcastReceiver = new ScanResultBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.context.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    public void unregisterBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            try {
                this.context.unregisterReceiver(this.broadcastReceiver);
            } catch (Exception e) {
            }
            this.broadcastReceiver = null;
        }
    }
}
