package com.airbnb.android.core.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.System;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.airbnb.airrequest.BaseUrl;
import com.airbnb.airrequest.DefaultErrorResponse;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.SingleFireRequestExecutor;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.models.ListingWirelessInfo.WirelessTypes;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.net.NetworkClass;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.NetworkType.p163v1.C2449NetworkType;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import permissions.dispatcher.PermissionUtils;
import retrofit2.CallAdapter.Factory;
import retrofit2.Retrofit.Builder;

public final class NetworkUtil {
    private static final String NETWORK_TYPE_AIRPLANE = "airplane";
    public static final String NETWORK_TYPE_CELLULAR = "cellular";
    private static final String NETWORK_TYPE_UNKNOWN = "unknown";
    private static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String[] PERMISSION_SETUPANDCONNECTLOACTIONCLIENT = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final String TAG = NetworkUtil.class.getSimpleName();
    private static long lastOfflineToast;

    public interface hostWifiNetworkAddedCallback {
        void onHostWifiNetworkAdded();
    }

    private NetworkUtil() {
    }

    public static void toastNetworkError(Context context, int string) {
        toastNetworkError(context, context.getString(string));
    }

    public static void toastNetworkError(Context context, String message) {
        if (context != null) {
            if (!isConnectedOrConnecting(context) || TextUtils.isEmpty(message)) {
                toastGenericNetworkError(context);
            } else {
                Toast.makeText(context, message, 0).show();
            }
        }
    }

    public static void toastNetworkError(Context context, NetworkException e) {
        if (TextUtils.isEmpty(errorMessage(e))) {
            toastGenericNetworkError(context);
        } else {
            toastNetworkError(context, errorMessage(e));
        }
    }

    public static void toastGenericNetworkError(Context context) {
        if (context != null) {
            if (isConnectedOrConnecting(context)) {
                Toast.makeText(context, C0716R.string.error_request, 0).show();
                return;
            }
            long nowTime = System.currentTimeMillis();
            if (nowTime - lastOfflineToast > 5000) {
                Toast.makeText(context, C0716R.string.help_currently_offline, 0).show();
                lastOfflineToast = nowTime;
            }
        }
    }

    public static int getGenericNetworkError(Context context) {
        if (isConnectedOrConnecting(context)) {
            return C0716R.string.error_request;
        }
        return C0716R.string.help_currently_offline;
    }

    public static Snackbar tryShowRetryableErrorWithSnackbar(View view, NetworkException e, OnClickListener retryAction) {
        if (view != null) {
            return createNetworkErrorSnackbar(view, e, C0716R.string.error).action(C0716R.string.retry, retryAction).duration(-2).buildAndShow();
        }
        C0715L.m1198w(TAG, "Tried to toast network error but view has been disposed");
        return null;
    }

    public static Snackbar tryShowRetryableErrorWithSnackbar(View view, String errorMessage, OnClickListener retryAction) {
        if (view != null) {
            return createNetworkErrorSnackbar(view, errorMessage, C0716R.string.error).action(C0716R.string.retry, retryAction).duration(-2).buildAndShow();
        }
        C0715L.m1198w(TAG, "Tried to toast network error but view has been disposed");
        return null;
    }

    public static String getErrorMessage(Context context, NetworkException e) {
        return getErrorMessage(context, e, C0716R.string.error_request);
    }

    public static Snackbar tryShowRetryableErrorWithSnackbar(View view, OnClickListener retryAction) {
        if (view != null) {
            return new SnackbarWrapper().view(view).title(C0716R.string.error, true).body(C0716R.string.error_request).duration(-2).action(C0716R.string.retry, retryAction).buildAndShow();
        }
        C0715L.m1198w(TAG, "Tried to toast network error but view has been disposed");
        return null;
    }

    public static Snackbar tryShowErrorWithSnackbar(View view, NetworkException e) {
        return tryShowErrorWithSnackbar(view, e, C0716R.string.error, C0716R.string.error_request);
    }

    public static Snackbar tryShowErrorWithSnackbar(View view, NetworkException error, int titleRes, int defaultErrorRes) {
        if (view != null) {
            return createNetworkErrorSnackbar(view, error, titleRes).buildAndShow();
        }
        C0715L.m1198w(TAG, "Tried to toast network error but view has been disposed");
        return null;
    }

    public static SnackbarWrapper createNetworkErrorSnackbar(View view, NetworkException error, int titleRes) {
        return createNetworkErrorSnackbar(view, getErrorMessage(view.getContext(), error), titleRes);
    }

    public static SnackbarWrapper createNetworkErrorSnackbar(View view, String errorMessageBody, int titleRes) {
        return new SnackbarWrapper().view(view).title(getErrorTitle(view.getContext(), titleRes), true).body(errorMessageBody).duration(0);
    }

    public static String getErrorMessage(Context context, NetworkException e, int defaultErrorMessageRes) {
        String errorMessage = context.getString(defaultErrorMessageRes);
        if (!isConnectedOrConnecting(context)) {
            return context.getString(C0716R.string.help_currently_offline);
        }
        if (!TextUtils.isEmpty(errorMessage(e))) {
            return trimHREF(errorMessage(e));
        }
        if (!TextUtils.isEmpty(errorDetails(e))) {
            return errorDetails(e);
        }
        return errorMessage;
    }

    private static String trimHREF(String errorMessage) {
        return errorMessage.replaceAll("<a href.*?a>", "");
    }

    public static String getErrorTitle(Context context, int titleRes) {
        String errorTitle = context.getString(titleRes);
        if (TextUtils.isEmpty(errorTitle)) {
            return context.getString(C0716R.string.error);
        }
        return errorTitle;
    }

    public static String getNetworkType(Context context) {
        switch (CoreApplication.instance(context).component().networkMonitor().getNetworkClass()) {
            case TYPE_2G:
            case TYPE_3G:
            case TYPE_4G:
            case TYPE_ROAMING:
                return NETWORK_TYPE_CELLULAR;
            case TYPE_WIFI:
                return NETWORK_TYPE_WIFI;
            case Unknown:
                if (isAirplaneMode(context)) {
                    return NETWORK_TYPE_AIRPLANE;
                }
                break;
        }
        return "unknown";
    }

    public static C2449NetworkType getJitneyNetworkType(Context context) {
        switch (CoreApplication.instance(context).component().networkMonitor().getNetworkClass()) {
            case TYPE_2G:
            case TYPE_3G:
            case TYPE_4G:
            case TYPE_ROAMING:
                return C2449NetworkType.Cellular;
            case TYPE_WIFI:
                return C2449NetworkType.Wifi;
            default:
                return C2449NetworkType.None;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static boolean connectToWifiNetwork(ListingWirelessInfo info, Context context, hostWifiNetworkAddedCallback callback, BroadcastReceiver broadcastReceiver) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(NETWORK_TYPE_WIFI);
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(context, C0716R.string.wifi_not_enabled, 0).show();
            return false;
        } else if (info.getWirelessSsid() == null) {
            Toast.makeText(context, C0716R.string.wifi_host_has_not_entered_wifi_info, 0).show();
            return false;
        } else {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            if (scanResults != null) {
                for (ScanResult r : scanResults) {
                    if (info.getWirelessSsid().equals(r.SSID)) {
                        WifiConfiguration conf = new WifiConfiguration();
                        conf.SSID = wrapInDoubleQuotes(info.getWirelessSsid());
                        if (r.capabilities.contains(WirelessTypes.WPA.type)) {
                            conf.preSharedKey = wrapInDoubleQuotes(info.getWirelessPassword());
                        } else if (r.capabilities.contains(WirelessTypes.WEP.type)) {
                            conf.wepKeys[0] = wrapInDoubleQuotes(info.getWirelessPassword());
                            conf.wepTxKeyIndex = 0;
                            conf.allowedKeyManagement.set(0);
                            conf.allowedGroupCiphers.set(0);
                        } else {
                            conf.allowedKeyManagement.set(0);
                        }
                        if (!doesNetworkConfigurationAlreadyExist(wifiManager, info)) {
                            wifiManager.addNetwork(conf);
                        }
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(conf.networkId, true);
                        wifiManager.reconnect();
                        Toast.makeText(context, C0716R.string.wifi_trying_to_connect, 0).show();
                        if (callback != null) {
                            callback.onHostWifiNetworkAdded();
                        }
                        if (broadcastReceiver == null) {
                            return false;
                        }
                        context.registerReceiver(broadcastReceiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
                        return true;
                    }
                }
            }
            Toast.makeText(context, C0716R.string.wifi_unable_to_find_network, 0).show();
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static String getWifiSsid(Context context) {
        WifiManager manager = (WifiManager) context.getSystemService(NETWORK_TYPE_WIFI);
        if (manager.isWifiEnabled()) {
            WifiInfo wifiInfo = manager.getConnectionInfo();
            if (wifiInfo != null) {
                DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == DetailedState.CONNECTED || state == DetailedState.OBTAINING_IPADDR) {
                    String name = wifiInfo.getSSID();
                    return name.substring(1, name.length() - 1);
                }
            }
        }
        return null;
    }

    public static boolean isUserUnauthorized(NetworkException e) {
        return new DefaultErrorResponse(e).isUserUnauthorized();
    }

    private static String wrapInDoubleQuotes(String s) {
        return "\"" + s + "\"";
    }

    @SuppressLint({"MissingPermission"})
    public static boolean doesNetworkConfigurationAlreadyExist(WifiManager manager, ListingWirelessInfo info) {
        List<WifiConfiguration> configurations = manager.getConfiguredNetworks();
        if (configurations == null) {
            return false;
        }
        for (WifiConfiguration configuration : configurations) {
            if (info != null && wrapInDoubleQuotes(info.getWirelessSsid()).equals(configuration.SSID)) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean isNetworkAvailable(WifiManager manager, ListingWirelessInfo info, Context context) {
        String targetSsid = info.getWirelessSsid();
        if (targetSsid == null || !PermissionUtils.hasSelfPermissions(context, PERMISSION_SETUPANDCONNECTLOACTIONCLIENT)) {
            return false;
        }
        List<ScanResult> scanResults = manager.getScanResults();
        if (scanResults == null) {
            return false;
        }
        for (ScanResult r : scanResults) {
            if (targetSsid.equals(r.SSID)) {
                return true;
            }
        }
        return false;
    }

    public static void setupWifiAlarmIfNecessary(Reservation reservation, Context context) {
        Listing listing = reservation != null ? reservation.getListing() : null;
        if (listing != null && !reservation.isUserHost(CoreApplication.instance(context).component().accountManager().getCurrentUser()) && listing.getWirelessInfo() != null && AirDate.today().compareTo(reservation.getCheckinDate()) != -1) {
            WifiAlarmUtils.scheduleWifiAlarm(context, reservation);
        }
    }

    public static String getCellularType(Context context) {
        NetworkClass networkClass = CoreApplication.instance(context).component().networkMonitor().getNetworkClass();
        if (networkClass == NetworkClass.TYPE_WIFI) {
            return "";
        }
        return networkClass.description;
    }

    public static boolean isConnectedOrConnecting(Context context) {
        try {
            NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean isConnected(Context context) {
        try {
            NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetwork == null || !activeNetwork.isConnected()) {
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static boolean isAirplaneMode(Context context) {
        if (AndroidVersion.isAtLeastJellyBeanMR1()) {
            if (System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0) {
                return true;
            }
            return false;
        } else if (System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static String errorDetails(NetworkException exception) {
        return new DefaultErrorResponse(exception).errorDetails();
    }

    public static String errorMessage(NetworkException exception) {
        return new DefaultErrorResponse(exception).errorMessage();
    }

    public static Integer errorCode(NetworkException exception) {
        return new DefaultErrorResponse(exception).errorCode();
    }

    public static String errorId(NetworkException exception) {
        return new DefaultErrorResponse(exception).errorId();
    }

    public static String error(NetworkException exception) {
        return new DefaultErrorResponse(exception).error();
    }

    public static Map<String, String> getNetworkErrorLoggingData(NetworkException error) {
        ErrorResponse errorResponse = (ErrorResponse) error.errorResponse();
        if (errorResponse != null) {
            return errorResponse.toMap();
        }
        return null;
    }

    public static Builder provideRetrofitBuilder(OkHttpClient client, Factory callAdapterFactory, Executor callbackExecutor, ConverterFactory converterFactory, BaseUrl baseUrl) {
        return new Builder().client(client).callbackExecutor(callbackExecutor).baseUrl(baseUrl.url()).addCallAdapterFactory(callAdapterFactory).addConverterFactory(converterFactory);
    }

    public static Request overrideHost(Request request, String newHost) {
        HttpUrl url = HttpUrl.parse(newHost);
        return request.newBuilder().url(request.url().newBuilder().scheme(url.scheme()).host(url.host()).port(url.port()).build()).build();
    }

    public static void startLoginActivityIfSessionExpired(Throwable error) {
        if ((error instanceof NetworkException) && new DefaultErrorResponse((NetworkException) error).isExpiredOauthError()) {
            AirbnbEventLogger.track(AirbnbEventLogger.EVENT_ENGINEERING_LOG_2, Strap.make().mo11639kv("sub_event", "active_account_auth_failed_v3"));
            Context appContext = CoreApplication.appContext();
            appContext.startActivity(new Intent(appContext, Activities.expiredOAuthToken()).setFlags(268435456));
        }
    }

    public static SingleFireRequestExecutor singleFireExecutor() {
        return CoreApplication.instance().component().singleFireRequestExecutor();
    }
}
