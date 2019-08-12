package p005cn.jpush.android;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import p005cn.jpush.android.IDataShare.Stub;
import p005cn.jpush.android.api.LifeCycleCallbacks;
import p005cn.jpush.android.helpers.VersionHelper;
import p005cn.jpush.android.service.PushProtocol;
import p005cn.jpush.android.service.PushService;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.BasePreferenceManager;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.JPush */
public class JPush {
    public static String APP_KEY = null;
    public static String CURRENT_PWD = "";
    public static long CURRENT_UID = 0;
    public static boolean DEBUG_MODE = true;
    public static final boolean IMSDK_ENABLED = false;
    public static final boolean INTERNAL_USE = false;
    public static final String KEY_APP_KEY = "JPUSH_APPKEY";
    public static final String KEY_JPUSH_CHANNEL = "JPUSH_CHANNEL";
    public static String PKG_NAME = null;
    private static final String TAG = "JPushGlobal";
    public static final boolean UPDATE_CONFIG_ENABLED = false;
    public static String VERSION_NAME;
    public static int VERSION_NUM;
    private static AtomicBoolean _isInited = new AtomicBoolean(false);
    public static boolean canLaunchedStoppedService = false;
    public static boolean isMultiProcess = true;
    public static Context mApplicationContext;
    public static String mApplicationName;
    public static int mPackageIconId;
    private static ServiceConnection mRemoteConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Logger.m1416d(JPush.TAG, "action - onServiceConnected, ComponentName:" + className.toString());
            Logger.m1424i(JPush.TAG, "Remote Service bind success.");
            JPush.mRemoteService = Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Logger.m1416d(JPush.TAG, "action - onServiceDisconnected, ComponentName:" + className.toString());
            JPush.mRemoteService = null;
        }
    };
    public static IDataShare mRemoteService;
    public static boolean runInPushProcess = false;
    public static boolean useLifeCycleCallbacks = false;

    public static synchronized boolean init(Context context) {
        boolean z = false;
        synchronized (JPush.class) {
            if (_isInited.get()) {
                z = true;
            } else {
                Logger.m1416d(TAG, "action:init - Service");
                BasePreferenceManager.init(context.getApplicationContext());
                VersionHelper.handleUpgrade(context);
                if (!checkSoVersion()) {
                    Logger.m1422ee(TAG, "JPush .so file do not match JPush .jar file in the project, Failed to init JPush");
                } else {
                    PKG_NAME = context.getPackageName();
                    mApplicationContext = context.getApplicationContext();
                    CURRENT_UID = Configs.getUid();
                    CURRENT_PWD = Configs.getPassword();
                    ApplicationInfo appInfo = getAppInfo(context);
                    if (appInfo == null) {
                        Logger.m1422ee(TAG, "JPush cannot be initialized completely due to NULL appInfo.");
                    } else {
                        mPackageIconId = appInfo.icon;
                        if (mPackageIconId == 0) {
                            Logger.m1422ee(TAG, "metadata: Can not get Application icon, you will be not able to show notification due to the application icon is null.");
                        }
                        mApplicationName = context.getPackageManager().getApplicationLabel(appInfo).toString();
                        getVersionForApp(context);
                        if (getMetadata(context)) {
                            if (VERSION.SDK_INT >= 14 && (context instanceof Application)) {
                                LifeCycleCallbacks.isDebug = AndroidUtil.isDebuggable(context);
                                if (LifeCycleCallbacks.isDebug) {
                                    LifeCycleCallbacks.registerCallback((Application) context.getApplicationContext());
                                }
                            }
                            initNetSetting();
                            _isInited.set(true);
                            bindService(context);
                            z = true;
                        }
                    }
                }
            }
        }
        return z;
    }

    private static boolean getMetadata(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (ai != null) {
                Bundle metaData = ai.metaData;
                if (metaData != null) {
                    APP_KEY = metaData.getString(KEY_APP_KEY);
                    if (StringUtils.isEmpty(APP_KEY)) {
                        Logger.m1422ee(TAG, "metadata: JPush appKey - not defined in manifest");
                        return false;
                    } else if (APP_KEY.length() != 24) {
                        Logger.m1422ee(TAG, "Invalid appKey : " + APP_KEY + ", Please get your Appkey from JPush web console!");
                        Configs.setRegisterCode(context, 1008);
                        return false;
                    } else {
                        APP_KEY = APP_KEY.toLowerCase(Locale.getDefault());
                        Logger.m1418dd(TAG, "metadata: appKey - " + APP_KEY);
                        Configs.setAppKey(APP_KEY);
                        String channel = StringUtils.filterSpecialCharacter(metaData.getString(KEY_JPUSH_CHANNEL));
                        if (StringUtils.isEmpty(channel)) {
                            Logger.m1418dd(TAG, "metadata: channel - not defined in manifest");
                        } else {
                            Logger.m1418dd(TAG, "metadata: channel - " + channel);
                            Configs.setChannel(channel);
                        }
                        return true;
                    }
                } else {
                    Logger.m1418dd(TAG, "NO meta data defined in manifest.");
                    return false;
                }
            } else {
                Logger.m1418dd(TAG, "metadata: Can not get metaData from ApplicationInfo");
                return false;
            }
        } catch (NameNotFoundException e) {
            Logger.m1435ww(TAG, "Unexpected: failed to get current application info", e);
            return false;
        }
    }

    public static boolean checkSoVersion() {
        int soVersion = 0;
        try {
            soVersion = PushProtocol.GetSdkVersion();
            Logger.m1428v(TAG, "soVersion:" + soVersion);
        } catch (UnsatisfiedLinkError e) {
            Logger.m1422ee(TAG, "Get sdk version fail![获取sdk版本失败!]");
            e.printStackTrace();
        }
        return soVersion >= 200;
    }

    public static void getVersionForApp(Context context) {
        try {
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            VERSION_NUM = pinfo.versionCode;
            VERSION_NAME = pinfo.versionName;
            if (VERSION_NAME.length() > 30) {
                VERSION_NAME = VERSION_NAME.substring(0, 30);
            }
        } catch (Exception e) {
            Logger.m1418dd(TAG, "NO versionCode or versionName defined in manifest.");
        }
    }

    private static void initNetSetting() {
        if (VERSION.SDK_INT == 8) {
            System.setProperty("java.net.preferIPv4Stack", "true");
            System.setProperty("java.net.preferIPv6Addresses", InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        }
    }

    private static ApplicationInfo getAppInfo(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            Logger.m1423ee(TAG, "Unexpected: failed to get current application info", e);
            return null;
        }
    }

    public static void bindService(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PushService.class);
        if (context.bindService(intent, mRemoteConnection, 1)) {
            Logger.m1428v(TAG, "Remote Service on binding...");
        } else {
            Logger.m1428v(TAG, "Remote Service bind failed");
        }
    }

    public static void unbindService(Context context) {
        Logger.m1416d(TAG, "action - unbindService");
        try {
            context.unbindService(mRemoteConnection);
        } catch (IllegalArgumentException e) {
            Logger.m1420e(TAG, "java.lang.IllegalArgumentException: service not registered");
        } catch (Exception e2) {
            Logger.m1421e(TAG, "unexpected exception", e2);
        }
    }
}
