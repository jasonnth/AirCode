package com.braintreepayments.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.internal.UUIDHelper;
import com.braintreepayments.api.models.Configuration;
import com.kount.api.DataCollector.CompletionHandler;
import com.kount.api.DataCollector.Error;
import com.kount.api.DataCollector.LocationConfig;
import com.paypal.android.sdk.data.collector.PayPalDataCollector;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;
import org.json.JSONException;
import org.json.JSONObject;

public class DataCollector {
    public static void collectDeviceData(BraintreeFragment fragment, BraintreeResponseListener<String> listener) {
        collectDeviceData(fragment, null, listener);
    }

    public static void collectDeviceData(final BraintreeFragment fragment, final String merchantId, final BraintreeResponseListener<String> listener) {
        fragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                final String id;
                final JSONObject deviceData = new JSONObject();
                try {
                    String clientMetadataId = DataCollector.getPayPalClientMetadataId(fragment.getApplicationContext());
                    if (!TextUtils.isEmpty(clientMetadataId)) {
                        deviceData.put("correlation_id", clientMetadataId);
                    }
                } catch (JSONException e) {
                }
                if (configuration.getKount().isEnabled()) {
                    if (merchantId != null) {
                        id = merchantId;
                    } else {
                        id = configuration.getKount().getKountMerchantId();
                    }
                    try {
                        final String deviceSessionId = UUIDHelper.getFormattedUUID();
                        DataCollector.startDeviceCollector(fragment, id, deviceSessionId, new BraintreeResponseListener<String>() {
                            public void onResponse(String sessionId) {
                                try {
                                    deviceData.put("device_session_id", deviceSessionId);
                                    deviceData.put("fraud_merchant_id", id);
                                } catch (JSONException e) {
                                }
                                listener.onResponse(deviceData.toString());
                            }
                        });
                    } catch (ClassNotFoundException | NoClassDefFoundError | NumberFormatException e2) {
                        listener.onResponse(deviceData.toString());
                    }
                } else {
                    listener.onResponse(deviceData.toString());
                }
            }
        });
    }

    @Deprecated
    public static String collectDeviceData(BraintreeFragment fragment) {
        return collectDeviceData(fragment, "600000");
    }

    @Deprecated
    public static String collectDeviceData(BraintreeFragment fragment, String merchantId) {
        JSONObject deviceData = new JSONObject();
        try {
            String deviceSessionId = UUIDHelper.getFormattedUUID();
            startDeviceCollector(fragment, merchantId, deviceSessionId, null);
            deviceData.put("device_session_id", deviceSessionId);
            deviceData.put("fraud_merchant_id", merchantId);
        } catch (ClassNotFoundException | NoClassDefFoundError | NumberFormatException | JSONException e) {
        }
        try {
            String clientMetadataId = getPayPalClientMetadataId(fragment.getApplicationContext());
            if (!TextUtils.isEmpty(clientMetadataId)) {
                deviceData.put("correlation_id", clientMetadataId);
            }
        } catch (JSONException e2) {
        }
        return deviceData.toString();
    }

    public static String getPayPalClientMetadataId(Context context) {
        try {
            return PayPalOneTouchCore.getClientMetadataId(context);
        } catch (NoClassDefFoundError e) {
            try {
                return PayPalDataCollector.getClientMetadataId(context);
            } catch (NoClassDefFoundError e2) {
                return "";
            }
        }
    }

    /* access modifiers changed from: private */
    public static void startDeviceCollector(final BraintreeFragment fragment, final String merchantId, final String deviceSessionId, final BraintreeResponseListener<String> listener) throws ClassNotFoundException, NumberFormatException {
        fragment.sendAnalyticsEvent("data-collector.kount.started");
        Class.forName(com.kount.api.DataCollector.class.getName());
        fragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                final com.kount.api.DataCollector dataCollector = com.kount.api.DataCollector.getInstance();
                dataCollector.setContext(fragment.getApplicationContext());
                dataCollector.setMerchantID(Integer.parseInt(merchantId));
                dataCollector.setLocationCollectorConfig(LocationConfig.COLLECT);
                dataCollector.setEnvironment(DataCollector.getDeviceCollectorEnvironment(configuration.getEnvironment()));
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        dataCollector.collectForSession(deviceSessionId, new CompletionHandler() {
                            public void completed(String sessionID) {
                                fragment.sendAnalyticsEvent("data-collector.kount.succeeded");
                                if (listener != null) {
                                    listener.onResponse(sessionID);
                                }
                            }

                            public void failed(String sessionID, Error error) {
                                fragment.sendAnalyticsEvent("data-collector.kount.failed");
                                if (listener != null) {
                                    listener.onResponse(sessionID);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    static int getDeviceCollectorEnvironment(String environment) {
        if ("production".equalsIgnoreCase(environment)) {
            return 2;
        }
        return 1;
    }
}
