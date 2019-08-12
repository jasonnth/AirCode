package com.facebook.places.internal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings.Builder;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@TargetApi(21)
public class BleScannerImpl implements BleScanner {
    private static final String TAG = "BleScannerImpl";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private Context context;
    /* access modifiers changed from: private */
    public int errorCode;
    private boolean isScanInProgress;
    private LocationPackageRequestParams params;
    private ScanCallBackImpl scanCallBack;
    /* access modifiers changed from: private */
    public final List<BluetoothScanResult> scanResults = new ArrayList();

    private class ScanCallBackImpl extends ScanCallback {
        private ScanCallBackImpl() {
        }

        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            BleScannerImpl.this.errorCode = errorCode;
        }

        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    for (ScanResult result : results) {
                        BluetoothScanResult bluetoothScanResult = BleScannerImpl.newBluetoothScanResult(result);
                        if (bluetoothScanResult != null) {
                            BleScannerImpl.this.scanResults.add(bluetoothScanResult);
                        }
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }

        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    BluetoothScanResult bluetoothScanResult = BleScannerImpl.newBluetoothScanResult(result);
                    if (bluetoothScanResult != null) {
                        BleScannerImpl.this.scanResults.add(bluetoothScanResult);
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }
    }

    BleScannerImpl(Context context2, LocationPackageRequestParams params2) {
        this.context = context2;
        this.params = params2;
    }

    public synchronized void initAndCheckEligibility() throws ScannerException {
        if (VERSION.SDK_INT < 21) {
            throw new ScannerException(Type.NOT_SUPPORTED);
        } else if (!Validate.hasBluetoothPermission(this.context)) {
            throw new ScannerException(Type.PERMISSION_DENIED);
        } else if (!Validate.hasLocationPermission(this.context)) {
            throw new ScannerException(Type.PERMISSION_DENIED);
        } else {
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (this.bluetoothAdapter == null || !this.bluetoothAdapter.isEnabled()) {
                throw new ScannerException(Type.DISABLED);
            }
            this.bluetoothLeScanner = this.bluetoothAdapter.getBluetoothLeScanner();
            if (this.bluetoothLeScanner == null) {
                throw new ScannerException(Type.UNKNOWN_ERROR);
            }
        }
    }

    public synchronized void startScanning() throws ScannerException {
        if (this.isScanInProgress) {
            throw new ScannerException(Type.SCAN_ALREADY_IN_PROGRESS);
        }
        this.scanCallBack = new ScanCallBackImpl();
        this.isScanInProgress = true;
        this.errorCode = 0;
        synchronized (this.scanResults) {
            this.scanResults.clear();
        }
        if (this.bluetoothLeScanner == null) {
            throw new ScannerException(Type.UNKNOWN_ERROR);
        }
        try {
            Builder builder = new Builder();
            builder.setScanMode(2);
            builder.setReportDelay(0);
            this.bluetoothLeScanner.startScan(null, builder.build(), this.scanCallBack);
            this.isScanInProgress = true;
        } catch (Exception e) {
            throw new ScannerException(Type.UNKNOWN_ERROR);
        }
    }

    public synchronized void stopScanning() {
        this.bluetoothLeScanner.flushPendingScanResults(this.scanCallBack);
        this.bluetoothLeScanner.stopScan(this.scanCallBack);
        waitForMainLooper(this.params.getBluetoothFlushResultsTimeoutMs());
        this.isScanInProgress = false;
    }

    private void waitForMainLooper(long maxWaitTimeoutMs) {
        try {
            final Object lock = new Object();
            synchronized (lock) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        try {
                            synchronized (lock) {
                                lock.notify();
                            }
                        } catch (Exception e) {
                            BleScannerImpl.logException("Exception waiting for main looper", e);
                        }
                    }
                });
                lock.wait(maxWaitTimeoutMs);
            }
        } catch (Exception e) {
            logException("Exception waiting for main looper", e);
        }
    }

    public synchronized int getErrorCode() {
        return this.errorCode;
    }

    public synchronized List<BluetoothScanResult> getScanResults() {
        List<BluetoothScanResult> output;
        synchronized (this.scanResults) {
            int maxSanResults = this.params.getBluetoothMaxScanResults();
            if (this.scanResults.size() > maxSanResults) {
                output = new ArrayList<>(maxSanResults);
                Collections.sort(this.scanResults, new Comparator<BluetoothScanResult>() {
                    public int compare(BluetoothScanResult lhs, BluetoothScanResult rhs) {
                        return rhs.rssi - lhs.rssi;
                    }
                });
                output.addAll(this.scanResults.subList(0, maxSanResults));
            } else {
                output = new ArrayList<>(this.scanResults.size());
                output.addAll(this.scanResults);
            }
        }
        return output;
    }

    /* access modifiers changed from: private */
    public static BluetoothScanResult newBluetoothScanResult(ScanResult scanResult) {
        return new BluetoothScanResult(formatPayload(scanResult.getScanRecord().getBytes()), scanResult.getRssi());
    }

    private static String formatPayload(byte[] payload) {
        if (payload == null || payload.length == 0) {
            return null;
        }
        return toHexString(payload, getPayloadLength(payload));
    }

    private static int getPayloadLength(byte[] payload) {
        int offset = 0;
        while (offset < payload.length) {
            byte length = payload[offset];
            if (length == 0) {
                return offset;
            }
            if (length < 0) {
                return payload.length;
            }
            offset += length + 1;
        }
        return payload.length;
    }

    private static String toHexString(byte[] bytes, int length) {
        StringBuffer sb = new StringBuffer();
        if (length < 0 || length > bytes.length) {
            length = bytes.length;
        }
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public static void logException(String message, Exception e) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, message, e);
        }
    }
}
