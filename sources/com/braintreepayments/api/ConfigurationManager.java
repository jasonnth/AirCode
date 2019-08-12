package com.braintreepayments.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Base64;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.TokenizationKey;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

class ConfigurationManager {
    static final long TTL = TimeUnit.MINUTES.toMillis(5);
    static boolean sFetchingConfiguration = false;

    static boolean isFetchingConfiguration() {
        return sFetchingConfiguration;
    }

    static void getConfiguration(BraintreeFragment fragment, ConfigurationListener listener, BraintreeResponseListener<Exception> errorListener) {
        final String authorization;
        if (fragment.getAuthorization() instanceof ClientToken) {
            authorization = ((ClientToken) fragment.getAuthorization()).getAuthorizationFingerprint();
        } else if (fragment.getAuthorization() instanceof TokenizationKey) {
            authorization = fragment.getAuthorization().toString();
        } else {
            authorization = "";
        }
        final String configUrl = Uri.parse(fragment.getAuthorization().getConfigUrl()).buildUpon().appendQueryParameter("configVersion", "3").build().toString();
        Configuration cachedConfig = getCachedConfiguration(fragment.getApplicationContext(), configUrl + authorization);
        if (cachedConfig != null) {
            listener.onConfigurationFetched(cachedConfig);
            return;
        }
        sFetchingConfiguration = true;
        final BraintreeFragment braintreeFragment = fragment;
        final ConfigurationListener configurationListener = listener;
        final BraintreeResponseListener<Exception> braintreeResponseListener = errorListener;
        fragment.getHttpClient().get(configUrl, new HttpResponseCallback() {
            public void success(String responseBody) {
                try {
                    Configuration configuration = Configuration.fromJson(responseBody);
                    ConfigurationManager.cacheConfiguration(braintreeFragment.getApplicationContext(), configUrl + authorization, configuration);
                    ConfigurationManager.sFetchingConfiguration = false;
                    configurationListener.onConfigurationFetched(configuration);
                } catch (JSONException e) {
                    ConfigurationManager.sFetchingConfiguration = false;
                    braintreeResponseListener.onResponse(e);
                }
            }

            public void failure(Exception exception) {
                ConfigurationManager.sFetchingConfiguration = false;
                braintreeResponseListener.onResponse(exception);
            }
        });
    }

    private static Configuration getCachedConfiguration(Context context, String configUrl) {
        Configuration configuration = null;
        SharedPreferences prefs = BraintreeSharedPreferences.getSharedPreferences(context);
        String configUrl2 = Base64.encodeToString(configUrl.getBytes(), 0);
        if (System.currentTimeMillis() - prefs.getLong(configUrl2 + "_timestamp", 0) > TTL) {
            return configuration;
        }
        try {
            return Configuration.fromJson(prefs.getString(configUrl2, ""));
        } catch (JSONException e) {
            return configuration;
        }
    }

    /* access modifiers changed from: private */
    public static void cacheConfiguration(Context context, String configUrl, Configuration configuration) {
        String configUrl2 = Base64.encodeToString(configUrl.getBytes(), 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString(configUrl2, configuration.toJson()).putLong(configUrl2 + "_timestamp", System.currentTimeMillis()).apply();
    }
}
