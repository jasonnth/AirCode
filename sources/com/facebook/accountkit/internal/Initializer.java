package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKit.InitializeCallback;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import java.lang.reflect.Method;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Iterator;

public final class Initializer {
    private final ArrayList<InitializeCallback> callbacks = new ArrayList<>();
    private volatile Data data = null;
    private volatile State state = State.UNINITIALIZED;

    private static final class Data {
        final AccessTokenManager accessTokenManager;
        final Context applicationContext;
        final String applicationId;
        final String applicationName;
        final String clientToken;
        final LocalBroadcastManager localBroadcastManager;
        final LoginManager loginManager;

        Data(Context applicationContext2, String applicationId2, String applicationName2, String clientToken2, AccessTokenManager accessTokenManager2, LocalBroadcastManager localBroadcastManager2, LoginManager loginManager2) {
            this.applicationContext = applicationContext2;
            this.applicationId = applicationId2;
            this.applicationName = applicationName2;
            this.clientToken = clientToken2;
            this.accessTokenManager = accessTokenManager2;
            this.localBroadcastManager = localBroadcastManager2;
            this.loginManager = loginManager2;
        }
    }

    private enum State {
        UNINITIALIZED,
        INITIALIZED,
        FAILED
    }

    public synchronized void initialize(Context context, InitializeCallback callback) throws AccountKitException {
        if (!isInitialized()) {
            if (callback != null) {
                this.callbacks.add(callback);
            }
            Validate.checkInternetPermissionAndThrow(context);
            Context applicationContext = context.getApplicationContext();
            fixSamsungClipboardUIManagerMemoryLeak(applicationContext);
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            } catch (NameNotFoundException e) {
            }
            if (applicationInfo != null) {
                if (applicationInfo.metaData != null) {
                    Bundle metaData = applicationInfo.metaData;
                    String applicationId = getRequiredString(metaData, "com.facebook.sdk.ApplicationId", InternalAccountKitError.INVALID_APP_ID);
                    String clientToken = getRequiredString(metaData, AccountKit.CLIENT_TOKEN_PROPERTY, InternalAccountKitError.INVALID_CLIENT_TOKEN);
                    String applicationName = getRequiredString(metaData, AccountKit.APPLICATION_NAME_PROPERTY, InternalAccountKitError.INVALID_APP_NAME);
                    boolean facebookAppEventsEnabled = metaData.getBoolean(AccountKit.FACEBOOK_APP_EVENTS_ENABLED_PROPERTY, true);
                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext);
                    InternalLogger internalLogger = new InternalLogger(context.getApplicationContext(), applicationId, facebookAppEventsEnabled);
                    AccessTokenManager accessTokenManager = new AccessTokenManager(applicationContext, localBroadcastManager);
                    LoginManager loginManager = new LoginManager(internalLogger, accessTokenManager, localBroadcastManager);
                    this.data = new Data(applicationContext, applicationId, applicationName, clientToken, accessTokenManager, localBroadcastManager, loginManager);
                    if (CookieManager.getDefault() == null) {
                        CookieManager.setDefault(new CookieManager(new AccountKitCookieStore(context), null));
                    }
                    loadAccessToken();
                    this.state = State.INITIALIZED;
                    loginManager.getLogger().logEvent(InternalLogger.EVENT_NAME_SDK_START);
                    NativeProtocol.updateAllAvailableProtocolVersionsAsync();
                }
            }
            this.state = State.FAILED;
        } else if (callback != null) {
            callback.onInitialized();
        }
    }

    /* access modifiers changed from: 0000 */
    public AccessTokenManager getAccessTokenManager() {
        Validate.sdkInitialized();
        return this.data.accessTokenManager;
    }

    public Context getApplicationContext() {
        Validate.sdkInitialized();
        return this.data.applicationContext;
    }

    public String getApplicationId() {
        Validate.sdkInitialized();
        return this.data.applicationId;
    }

    /* access modifiers changed from: 0000 */
    public String getApplicationName() {
        Validate.sdkInitialized();
        return this.data.applicationName;
    }

    /* access modifiers changed from: 0000 */
    public String getClientToken() {
        Validate.sdkInitialized();
        return this.data.clientToken;
    }

    public InternalLogger getLogger() {
        Validate.sdkInitialized();
        return this.data.loginManager.getLogger();
    }

    /* access modifiers changed from: 0000 */
    public LoginManager getLoginManager() {
        Validate.sdkInitialized();
        return this.data.loginManager;
    }

    public boolean isInitialized() {
        return this.state == State.INITIALIZED;
    }

    /* access modifiers changed from: 0000 */
    public boolean getAccountKitFacebookAppEventsEnabled() {
        return getLogger().getFacebookAppEventsEnabled();
    }

    private synchronized void loadAccessToken() {
        if (!isInitialized()) {
            this.data.accessTokenManager.loadCurrentAccessToken();
            Iterator it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((InitializeCallback) it.next()).onInitialized();
            }
            this.callbacks.clear();
        }
    }

    private static String getRequiredString(Bundle bundle, String key, InternalAccountKitError invalidValueError) throws AccountKitException {
        String value = bundle.getString(key);
        if (value != null) {
            return value;
        }
        throw new AccountKitException(Type.INITIALIZATION_ERROR, invalidValueError);
    }

    private static void fixSamsungClipboardUIManagerMemoryLeak(Context applicationContext) {
        if (VERSION.SDK_INT >= 21) {
            try {
                Method m = Class.forName("android.sec.clipboard.ClipboardUIManager").getDeclaredMethod("getInstance", new Class[]{Context.class});
                m.setAccessible(true);
                m.invoke(null, new Object[]{applicationContext});
            } catch (Exception e) {
            }
        }
    }
}
