package p315io.branch.referral;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.BranchStrongMatchHelper */
class BranchStrongMatchHelper {
    private static int StrongMatchUrlHitDelay = MiSnapApiConstants.DEFAULT_SOLID_BACKGROUND;
    private static BranchStrongMatchHelper branchStrongMatchHelper_;
    Class<?> CustomServiceTabConnectionClass;
    Class<?> CustomTabsCallbackClass;
    Class<?> CustomTabsClientClass;
    Class<?> CustomTabsSessionClass;
    Class<?> ICustomTabsServiceClass;
    private boolean isCustomTabsAvailable_ = true;
    boolean isStrongMatchUrlLaunched = false;
    Object mClient_ = null;
    private final Handler timeOutHandler_;

    /* renamed from: io.branch.referral.BranchStrongMatchHelper$MockCustomTabServiceConnection */
    private abstract class MockCustomTabServiceConnection implements ServiceConnection {
        public abstract void onCustomTabsServiceConnected(ComponentName componentName, Object obj);

        public MockCustomTabServiceConnection() {
        }

        public final void onServiceConnected(ComponentName name, IBinder service) {
            try {
                Constructor<?> customTabClientConstructor = BranchStrongMatchHelper.this.CustomTabsClientClass.getDeclaredConstructor(new Class[]{BranchStrongMatchHelper.this.ICustomTabsServiceClass, ComponentName.class});
                customTabClientConstructor.setAccessible(true);
                onCustomTabsServiceConnected(name, customTabClientConstructor.newInstance(new Object[]{Class.forName("android.support.customtabs.ICustomTabsService$Stub").getMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{service}), name}));
            } catch (Throwable th) {
                onCustomTabsServiceConnected(null, null);
            }
        }
    }

    /* renamed from: io.branch.referral.BranchStrongMatchHelper$StrongMatchCheckEvents */
    interface StrongMatchCheckEvents {
        void onStrongMatchCheckFinished();
    }

    private BranchStrongMatchHelper() {
        try {
            this.CustomTabsClientClass = Class.forName("android.support.customtabs.CustomTabsClient");
            this.CustomServiceTabConnectionClass = Class.forName("android.support.customtabs.CustomTabsServiceConnection");
            this.CustomTabsCallbackClass = Class.forName("android.support.customtabs.CustomTabsCallback");
            this.CustomTabsSessionClass = Class.forName("android.support.customtabs.CustomTabsSession");
            this.ICustomTabsServiceClass = Class.forName("android.support.customtabs.ICustomTabsService");
        } catch (Throwable th) {
            this.isCustomTabsAvailable_ = false;
        }
        this.timeOutHandler_ = new Handler();
    }

    public static BranchStrongMatchHelper getInstance() {
        if (branchStrongMatchHelper_ == null) {
            branchStrongMatchHelper_ = new BranchStrongMatchHelper();
        }
        return branchStrongMatchHelper_;
    }

    public void checkForStrongMatch(Context context, String cookieMatchDomain, DeviceInfo deviceInfo, PrefHelper prefHelper, SystemObserver systemObserver, StrongMatchCheckEvents callback) {
        this.isStrongMatchUrlLaunched = false;
        if (System.currentTimeMillis() - prefHelper.getLastStrongMatchTime() < 2592000000L) {
            updateStrongMatchCheckFinished(callback, this.isStrongMatchUrlLaunched);
        } else if (!this.isCustomTabsAvailable_) {
            updateStrongMatchCheckFinished(callback, this.isStrongMatchUrlLaunched);
        } else {
            try {
                if (deviceInfo.getHardwareID() != null) {
                    final Uri strongMatchUri = buildStrongMatchUrl(cookieMatchDomain, deviceInfo, prefHelper, systemObserver, context);
                    if (strongMatchUri != null) {
                        final StrongMatchCheckEvents strongMatchCheckEvents = callback;
                        this.timeOutHandler_.postDelayed(new Runnable() {
                            public void run() {
                                BranchStrongMatchHelper.this.updateStrongMatchCheckFinished(strongMatchCheckEvents, BranchStrongMatchHelper.this.isStrongMatchUrlLaunched);
                            }
                        }, 500);
                        Method method = this.CustomTabsClientClass.getMethod("bindCustomTabsService", new Class[]{Context.class, String.class, this.CustomServiceTabConnectionClass});
                        final Method warmupMethod = this.CustomTabsClientClass.getMethod("warmup", new Class[]{Long.TYPE});
                        final Method newSessionMethod = this.CustomTabsClientClass.getMethod("newSession", new Class[]{this.CustomTabsCallbackClass});
                        final Method mayLaunchUrlMethod = this.CustomTabsSessionClass.getMethod("mayLaunchUrl", new Class[]{Uri.class, Bundle.class, List.class});
                        Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
                        intent.setPackage("com.android.chrome");
                        final PrefHelper prefHelper2 = prefHelper;
                        final StrongMatchCheckEvents strongMatchCheckEvents2 = callback;
                        context.bindService(intent, new MockCustomTabServiceConnection() {
                            public void onCustomTabsServiceConnected(ComponentName var1, Object clientObj) {
                                BranchStrongMatchHelper.this.mClient_ = BranchStrongMatchHelper.this.CustomTabsClientClass.cast(clientObj);
                                if (BranchStrongMatchHelper.this.mClient_ != null) {
                                    try {
                                        warmupMethod.invoke(BranchStrongMatchHelper.this.mClient_, new Object[]{Integer.valueOf(0)});
                                        Object customTabsSessionObj = newSessionMethod.invoke(BranchStrongMatchHelper.this.mClient_, new Object[]{null});
                                        if (customTabsSessionObj != null) {
                                            PrefHelper.Debug("BranchSDK", "Strong match request " + strongMatchUri);
                                            mayLaunchUrlMethod.invoke(customTabsSessionObj, new Object[]{strongMatchUri, null, null});
                                            prefHelper2.saveLastStrongMatchTime(System.currentTimeMillis());
                                            BranchStrongMatchHelper.this.isStrongMatchUrlLaunched = true;
                                        }
                                    } catch (Throwable th) {
                                        BranchStrongMatchHelper.this.mClient_ = null;
                                        BranchStrongMatchHelper.this.updateStrongMatchCheckFinished(strongMatchCheckEvents2, BranchStrongMatchHelper.this.isStrongMatchUrlLaunched);
                                    }
                                }
                            }

                            public void onServiceDisconnected(ComponentName name) {
                                BranchStrongMatchHelper.this.mClient_ = null;
                                BranchStrongMatchHelper.this.updateStrongMatchCheckFinished(strongMatchCheckEvents2, BranchStrongMatchHelper.this.isStrongMatchUrlLaunched);
                            }
                        }, 33);
                        return;
                    }
                    updateStrongMatchCheckFinished(callback, this.isStrongMatchUrlLaunched);
                    return;
                }
                updateStrongMatchCheckFinished(callback, this.isStrongMatchUrlLaunched);
                Log.d("BranchSDK", "Cannot use cookie-based matching since device id is not available");
            } catch (Throwable th) {
                updateStrongMatchCheckFinished(callback, this.isStrongMatchUrlLaunched);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateStrongMatchCheckFinished(final StrongMatchCheckEvents callback, boolean isStrongMatchUriLaunched) {
        if (callback == null) {
            return;
        }
        if (isStrongMatchUriLaunched) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    callback.onStrongMatchCheckFinished();
                }
            }, (long) StrongMatchUrlHitDelay);
        } else {
            callback.onStrongMatchCheckFinished();
        }
    }

    private Uri buildStrongMatchUrl(String matchDomain, DeviceInfo deviceInfo, PrefHelper prefHelper, SystemObserver systemObserver, Context context) {
        if (TextUtils.isEmpty(matchDomain)) {
            return null;
        }
        String uriString = (("https://" + matchDomain + "/_strong_match?os=" + deviceInfo.getOsName()) + "&" + Jsonkey.HardwareID.getKey() + "=" + deviceInfo.getHardwareID()) + "&" + Jsonkey.HardwareIDType.getKey() + "=" + (deviceInfo.isHardwareIDReal() ? Jsonkey.HardwareIDTypeVendor.getKey() : Jsonkey.HardwareIDTypeRandom.getKey());
        if (systemObserver.GAIDString_ != null && !BranchUtil.isTestModeEnabled(context)) {
            uriString = uriString + "&" + Jsonkey.GoogleAdvertisingID.getKey() + "=" + systemObserver.GAIDString_;
        }
        if (!prefHelper.getDeviceFingerPrintID().equals("bnc_no_value")) {
            uriString = uriString + "&" + Jsonkey.DeviceFingerprintID.getKey() + "=" + prefHelper.getDeviceFingerPrintID();
        }
        if (!deviceInfo.getAppVersion().equals("bnc_no_value")) {
            uriString = uriString + "&" + Jsonkey.AppVersion.getKey() + "=" + deviceInfo.getAppVersion();
        }
        if (!prefHelper.getBranchKey().equals("bnc_no_value")) {
            uriString = uriString + "&" + Jsonkey.BranchKey.getKey() + "=" + prefHelper.getBranchKey();
        }
        return Uri.parse(uriString + "&sdk=android2.12.0");
    }
}
