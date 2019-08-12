package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import p005cn.jpush.android.JPushConstants.PushActivity;

final class NativeProtocol {
    static final String CONTENT_SCHEME = "content://";
    static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
    static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    static final String EXTRA_SEAMLESS_LOGIN_TOKEN = "com.facebook.platform.extra.SEAMLESS_LOGIN_TOKEN";
    private static final String INTENT_ACTION_FBLITE_PLATFORM_SERVICE = "com.facebook.lite.platform.PLATFORM_SERVICE";
    private static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
    static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REPLY = 65545;
    static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REQUEST = 65544;
    static final String PLATFORM_PROVIDER = ".provider.PlatformProvider";
    static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
    static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
    static final int PROTOCOL_VERSION_20161017 = 20161017;
    static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
    /* access modifiers changed from: private */
    public static List<NativeAppInfo> facebookAppInfoList = Arrays.asList(new NativeAppInfo[]{new KatanaAppInfo(), new WakizashiAppInfo(), new FBLiteAppInfo()});
    /* access modifiers changed from: private */
    public static AtomicBoolean protocolVersionsAsyncUpdating = new AtomicBoolean(false);

    private static class FBLiteAppInfo extends NativeAppInfo {
        private static final String FBLITE_PACKAGE = "com.facebook.lite";

        private FBLiteAppInfo() {
        }

        /* access modifiers changed from: protected */
        public String getPackage() {
            return FBLITE_PACKAGE;
        }

        /* access modifiers changed from: protected */
        public Intent getPlatformServiceIntent() {
            return new Intent(NativeProtocol.INTENT_ACTION_FBLITE_PLATFORM_SERVICE).setPackage(getPackage());
        }
    }

    private static class KatanaAppInfo extends NativeAppInfo {
        private static final String KATANA_PACKAGE = "com.facebook.katana";

        private KatanaAppInfo() {
        }

        /* access modifiers changed from: protected */
        public String getPackage() {
            return "com.facebook.katana";
        }

        /* access modifiers changed from: protected */
        public Intent getPlatformServiceIntent() {
            return new Intent(NativeProtocol.INTENT_ACTION_PLATFORM_SERVICE).setPackage(getPackage());
        }
    }

    private static class WakizashiAppInfo extends NativeAppInfo {
        private static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";

        private WakizashiAppInfo() {
        }

        /* access modifiers changed from: protected */
        public String getPackage() {
            return WAKIZASHI_PACKAGE;
        }

        /* access modifiers changed from: protected */
        public Intent getPlatformServiceIntent() {
            return new Intent(NativeProtocol.INTENT_ACTION_PLATFORM_SERVICE).setPackage(getPackage());
        }
    }

    NativeProtocol() {
    }

    static boolean validateApplicationForService() {
        for (NativeAppInfo appInfo : facebookAppInfoList) {
            if (appInfo.isAppInstalled()) {
                return true;
            }
        }
        return false;
    }

    static boolean validateProtocolVersionForService(int version) {
        for (NativeAppInfo appInfo : facebookAppInfoList) {
            if (appInfo.getAvailableVersions().contains(Integer.valueOf(version))) {
                return true;
            }
        }
        return false;
    }

    static void updateAllAvailableProtocolVersionsAsync() {
        if (protocolVersionsAsyncUpdating.compareAndSet(false, true)) {
            Utility.getThreadPoolExecutor().execute(new Runnable() {
                public void run() {
                    try {
                        for (NativeAppInfo appInfo : NativeProtocol.facebookAppInfoList) {
                            appInfo.fetchAvailableVersions(true);
                        }
                    } finally {
                        NativeProtocol.protocolVersionsAsyncUpdating.set(false);
                    }
                }
            });
        }
    }

    static Intent createPlatformServiceIntent(Context context) {
        for (NativeAppInfo appInfo : facebookAppInfoList) {
            Intent intent = validateServiceIntent(context, appInfo.getPlatformServiceIntent().addCategory(PushActivity.CATEGORY_1), appInfo);
            if (intent != null) {
                return intent;
            }
        }
        return null;
    }

    private static Intent validateServiceIntent(Context context, Intent intent, NativeAppInfo appInfo) {
        ResolveInfo resolveInfo = context.getPackageManager().resolveService(intent, 0);
        if (resolveInfo == null) {
            return null;
        }
        if (!appInfo.validateSignature(context, resolveInfo.serviceInfo.packageName)) {
            return null;
        }
        return intent;
    }
}
