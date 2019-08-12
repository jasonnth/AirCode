package com.mparticle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebView;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.MPEvent.Builder;
import com.mparticle.commerce.Cart;
import com.mparticle.commerce.CommerceApi;
import com.mparticle.commerce.CommerceEvent;
import com.mparticle.commerce.Product;
import com.mparticle.commerce.ProductBagApi;
import com.mparticle.internal.AppStateManager;
import com.mparticle.internal.C4606d;
import com.mparticle.internal.C4618k;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.KitFrameworkWrapper;
import com.mparticle.internal.MPLocationListener;
import com.mparticle.internal.MPUtility;
import com.mparticle.internal.MessageManager;
import com.mparticle.internal.PushRegistrationHelper;
import com.mparticle.internal.PushRegistrationHelper.PushRegistration;
import com.mparticle.media.MPMediaAPI;
import com.mparticle.media.MediaCallbacks;
import com.mparticle.messaging.CloudAction;
import com.mparticle.messaging.MPCloudNotificationMessage;
import com.mparticle.messaging.MPMessagingAPI;
import com.mparticle.messaging.ProviderCloudMessage;
import com.mparticle.segmentation.SegmentListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MParticle {
    private static volatile boolean androidIdDisabled;
    private static volatile boolean devicePerformanceMetricsDisabled;
    private static volatile MParticle instance;
    private Context mAppContext;
    AppStateManager mAppStateManager;
    protected CommerceApi mCommerce;
    protected ConfigManager mConfigManager;
    protected volatile DeepLinkListener mDeepLinkListener;
    protected KitFrameworkWrapper mKitManager;
    protected MPLocationListener mLocationListener;
    protected MPMediaAPI mMedia;
    protected MessageManager mMessageManager;
    protected MPMessagingAPI mMessaging;
    protected SharedPreferences mPreferences;
    protected ProductBagApi mProductBags;

    public enum Environment {
        AutoDetect(0),
        Development(1),
        Production(2);
        
        private final int value;

        public int getValue() {
            return this.value;
        }

        private Environment(int value2) {
            this.value = value2;
        }
    }

    public enum EventType {
        Unknown,
        Navigation,
        Location,
        Search,
        Transaction,
        UserContent,
        UserPreference,
        Social,
        Other;

        public String toString() {
            return name();
        }
    }

    public enum IdentityType {
        Other(0),
        CustomerId(1),
        Facebook(2),
        Twitter(3),
        Google(4),
        Microsoft(5),
        Yahoo(6),
        Email(7),
        Alias(8),
        FacebookCustomAudienceId(9);
        
        /* access modifiers changed from: private */
        public final int value;

        private IdentityType(int value2) {
            this.value = value2;
        }

        public static IdentityType parseInt(int val) {
            switch (val) {
                case 1:
                    return CustomerId;
                case 2:
                    return Facebook;
                case 3:
                    return Twitter;
                case 4:
                    return Google;
                case 5:
                    return Microsoft;
                case 6:
                    return Yahoo;
                case 7:
                    return Email;
                case 8:
                    return Alias;
                case 9:
                    return FacebookCustomAudienceId;
                default:
                    return Other;
            }
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum InstallType {
        AutoDetect,
        KnownInstall,
        KnownUpgrade;

        public String toString() {
            return name();
        }
    }

    public enum LogLevel {
        NONE,
        ERROR,
        WARNING,
        DEBUG
    }

    public interface ServiceProviders {
        public static final int ADJUST = 68;
        public static final int APPBOY = 28;
        public static final int APPSFLYER = 92;
        public static final int APPTENTIVE = 97;
        public static final int APPTIMIZE = 105;
        public static final int BRANCH_METRICS = 80;
        public static final String BROADCAST_ACTIVE = "MPARTICLE_SERVICE_PROVIDER_ACTIVE_";
        public static final String BROADCAST_DISABLED = "MPARTICLE_SERVICE_PROVIDER_DISABLED_";
        public static final int BUTTON = 1022;
        public static final int COMSCORE = 39;
        public static final int CRITTERCISM = 86;
        public static final int FLURRY = 83;
        public static final int FORESEE_ID = 64;
        public static final int KAHUNA = 56;
        public static final int KOCHAVA = 37;
        public static final int LEANPLUM = 98;
        public static final int LOCALYTICS = 84;
        public static final int REVEAL_MOBILE = 112;
        public static final int TUNE = 32;
        public static final int URBAN_AIRSHIP = 25;
        public static final int WOOTRIC = 90;
    }

    public interface UserAttributes {
        public static final String ADDRESS = "$Address";
        public static final String AGE = "$Age";
        public static final String CITY = "$City";
        public static final String COUNTRY = "$Country";
        public static final String FIRSTNAME = "$FirstName";
        public static final String GENDER = "$Gender";
        public static final String LASTNAME = "$LastName";
        public static final String MOBILE_NUMBER = "$Mobile";
        public static final String STATE = "$State";
        public static final String ZIPCODE = "$Zip";
    }

    MParticle() {
    }

    public static void start(Context context) {
        start(context, InstallType.AutoDetect);
    }

    public static void start(Context context, String apiKey, String apiSecret) {
        start(context, InstallType.AutoDetect, apiKey, apiSecret);
    }

    public static void start(Context context, InstallType installType) {
        start(context, installType, Environment.AutoDetect);
    }

    public static void start(Context context, InstallType installType, String apiKey, String apiSecret) {
        start(context, installType, Environment.AutoDetect, apiKey, apiSecret);
    }

    public static void start(Context context, InstallType installType, Environment environment) {
        if (context == null) {
            throw new IllegalArgumentException("mParticle failed to start: context is required.");
        }
        getInstance(context.getApplicationContext(), installType, environment, null, null);
    }

    public static void start(Context context, InstallType installType, Environment environment, String apiKey, String apiSecret) {
        if (context == null) {
            throw new IllegalArgumentException("mParticle failed to start: context is required.");
        }
        getInstance(context.getApplicationContext(), installType, environment, apiKey, apiSecret);
    }

    private static MParticle getInstance(Context context, InstallType installType, Environment environment, String apiKey, String apiSecret) {
        if (instance == null) {
            synchronized (MParticle.class) {
                if (instance == null) {
                    if (!MPUtility.checkPermission(context, "android.permission.INTERNET")) {
                        Log.e("mParticle SDK", "mParticle requires android.permission.INTERNET permission");
                    }
                    ConfigManager configManager = new ConfigManager(context, environment, apiKey, apiSecret);
                    AppStateManager appStateManager = new AppStateManager(context);
                    appStateManager.setConfigManager(configManager);
                    instance = new MParticle();
                    instance.mAppContext = context;
                    instance.mConfigManager = configManager;
                    instance.mAppStateManager = appStateManager;
                    instance.mCommerce = new CommerceApi(context);
                    instance.mProductBags = new ProductBagApi(context);
                    instance.mMessageManager = new MessageManager(context, configManager, installType, appStateManager);
                    instance.mPreferences = context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
                    instance.mKitManager = new KitFrameworkWrapper(context, instance.mMessageManager, configManager, appStateManager);
                    instance.mMessageManager.mo44830o();
                    if (configManager.getLogUnhandledExceptions()) {
                        instance.enableUncaughtExceptionLogging();
                    }
                    instance.mMessageManager.mo44831p();
                    appStateManager.init(VERSION.SDK_INT);
                }
            }
        }
        return instance;
    }

    public KitFrameworkWrapper getKitManager() {
        return this.mKitManager;
    }

    public ConfigManager getConfigManager() {
        return this.mConfigManager;
    }

    public AppStateManager getAppStateManager() {
        return this.mAppStateManager;
    }

    public static MParticle getInstance() {
        if (instance != null) {
            return getInstance(null, null, null, null, null);
        }
        Log.e("mParticle SDK", "Failed to get MParticle instance, getInstance() called prior to start().");
        return null;
    }

    public static void setInstance(MParticle instance2) {
        instance = instance2;
    }

    public static boolean isAndroidIdDisabled() {
        return androidIdDisabled;
    }

    public static void setAndroidIdDisabled(boolean disable) {
        androidIdDisabled = disable;
    }

    public static void setDevicePerformanceMetricsDisabled(boolean disable) {
        devicePerformanceMetricsDisabled = disable;
    }

    public static boolean isDevicePerformanceMetricsDisabled() {
        return devicePerformanceMetricsDisabled;
    }

    public void activityStarted(Activity activity) {
        if (VERSION.SDK_INT < 14 && this.mConfigManager.isEnabled()) {
            this.mAppStateManager.ensureActiveSession();
            this.mAppStateManager.onActivityStarted(activity);
        }
    }

    public void activityStopped(Activity activity) {
        if (VERSION.SDK_INT < 14 && this.mConfigManager.isEnabled()) {
            this.mAppStateManager.ensureActiveSession();
            this.mAppStateManager.onActivityStopped(activity);
        }
    }

    private void endSession() {
        if (this.mConfigManager.isEnabled()) {
            this.mAppStateManager.getSession().f3730d = System.currentTimeMillis();
            this.mAppStateManager.endSession();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isSessionActive() {
        return this.mAppStateManager.getSession().mo44847a();
    }

    public void upload() {
        this.mMessageManager.mo44824k();
    }

    public void setInstallReferrer(String referrer) {
        ReferrerReceiver.setInstallReferrer(this.mAppContext, referrer);
    }

    public String getInstallReferrer() {
        return this.mPreferences.getString("mp::install_referrer", null);
    }

    public void logEvent(String eventName, EventType eventType) {
        logEvent(eventName, eventType, null, 0, null);
    }

    public void logEvent(String eventName, EventType eventType, String category) {
        logEvent(eventName, eventType, null, 0, category);
    }

    public void logEvent(String eventName, EventType eventType, long eventLength) {
        logEvent(eventName, eventType, null, eventLength);
    }

    public void logEvent(String eventName, EventType eventType, Map<String, String> eventInfo) {
        logEvent(eventName, eventType, eventInfo, 0);
    }

    public void logEvent(String eventName, EventType eventType, Map<String, String> eventInfo, String category) {
        logEvent(eventName, eventType, eventInfo, 0, category);
    }

    public void logEvent(String eventName, EventType eventType, Map<String, String> eventInfo, long eventLength) {
        logEvent(eventName, eventType, eventInfo, eventLength, null);
    }

    public void logEvent(String eventName, EventType eventType, Map<String, String> eventInfo, long eventLength, String category) {
        logEvent(new Builder(eventName, eventType).info(eventInfo).duration((double) eventLength).category(category).build());
    }

    public void logEvent(MPEvent event) {
        if (this.mConfigManager.isEnabled() && checkEventLimit().booleanValue()) {
            this.mAppStateManager.ensureActiveSession();
            this.mMessageManager.mo44789a(event, this.mAppStateManager.getCurrentActivityName());
            ConfigManager.log(LogLevel.DEBUG, "Logged event - \n", event.toString());
            this.mKitManager.logEvent(event);
        }
    }

    public void logEvent(CommerceEvent event) {
        if (this.mConfigManager.isEnabled() && checkEventLimit().booleanValue()) {
            Cart instance2 = Cart.getInstance(this.mAppContext);
            if (event.getProductAction() != null) {
                List<Product> products = event.getProducts();
                if (event.getProductAction().equalsIgnoreCase(Product.ADD_TO_CART)) {
                    if (products != null) {
                        for (Product add : products) {
                            instance2.add(add, false);
                        }
                    }
                } else if (event.getProductAction().equalsIgnoreCase(Product.REMOVE_FROM_CART) && products != null) {
                    for (Product remove : products) {
                        instance2.remove(remove, false);
                    }
                }
            }
            this.mAppStateManager.ensureActiveSession();
            this.mMessageManager.mo44791a(event);
            ConfigManager.log(LogLevel.DEBUG, "Logged commerce event - \n", event.toString());
            this.mKitManager.logCommerceEvent(event);
        }
    }

    public void logLtvIncrease(BigDecimal valueIncreased, String eventName, Map<String, String> contextInfo) {
        if (valueIncreased == null) {
            ConfigManager.log(LogLevel.ERROR, "ValueIncreased must not be null.");
            return;
        }
        if (contextInfo == null) {
            contextInfo = new HashMap<>();
        }
        contextInfo.put("$Amount", valueIncreased.toPlainString());
        contextInfo.put("$MethodName", "LogLTVIncrease");
        if (eventName == null) {
            eventName = "Increase LTV";
        }
        logEvent(eventName, EventType.Transaction, contextInfo);
    }

    public void logScreen(String screenName) {
        logScreen(screenName, null);
    }

    public void logScreen(String screenName, Map<String, String> eventData) {
        logScreen(new Builder(screenName).info(eventData).build().setScreenEvent(true));
    }

    public void logScreen(MPEvent screenEvent) {
        screenEvent.setScreenEvent(true);
        if (MPUtility.isEmpty(screenEvent.getEventName())) {
            ConfigManager.log(LogLevel.ERROR, "screenName is required for logScreen");
        } else if (screenEvent.getEventName().length() > 256) {
            ConfigManager.log(LogLevel.ERROR, "The screen name was too long. Discarding event.");
        } else if (checkEventLimit().booleanValue()) {
            this.mAppStateManager.ensureActiveSession();
            if (this.mConfigManager.isEnabled()) {
                this.mMessageManager.mo44790a(screenEvent, screenEvent.getNavigationDirection());
                if (screenEvent.getInfo() == null) {
                    ConfigManager.log(LogLevel.DEBUG, "Logged screen: ", screenEvent.toString());
                }
            }
            if (screenEvent.getNavigationDirection()) {
                this.mKitManager.logScreen(screenEvent);
            }
        }
    }

    public void leaveBreadcrumb(String breadcrumb) {
        if (!this.mConfigManager.isEnabled()) {
            return;
        }
        if (MPUtility.isEmpty(breadcrumb)) {
            ConfigManager.log(LogLevel.ERROR, "breadcrumb is required for leaveBreadcrumb");
        } else if (breadcrumb.length() > 256) {
            ConfigManager.log(LogLevel.ERROR, "The breadcrumb name was too long. Discarding event.");
        } else {
            this.mAppStateManager.ensureActiveSession();
            this.mMessageManager.mo44792a(breadcrumb);
            ConfigManager.log(LogLevel.DEBUG, "Logged breadcrumb: " + breadcrumb);
            this.mKitManager.leaveBreadcrumb(breadcrumb);
        }
    }

    public void logError(String message) {
        logError(message, null);
    }

    public void logError(String message, Map<String, String> errorAttributes) {
        String jSONObject;
        if (!this.mConfigManager.isEnabled()) {
            return;
        }
        if (MPUtility.isEmpty(message)) {
            ConfigManager.log(LogLevel.ERROR, "message is required for logErrorEvent");
            return;
        }
        this.mAppStateManager.ensureActiveSession();
        if (checkEventLimit().booleanValue()) {
            JSONObject enforceAttributeConstraints = MPUtility.enforceAttributeConstraints(errorAttributes);
            this.mMessageManager.mo44796a(message, (Throwable) null, enforceAttributeConstraints);
            LogLevel logLevel = LogLevel.DEBUG;
            String[] strArr = new String[1];
            StringBuilder append = new StringBuilder().append("Logged error with message: ").append(message == null ? "<none>" : message).append(" with data: ");
            if (enforceAttributeConstraints == null) {
                jSONObject = "<none>";
            } else {
                jSONObject = enforceAttributeConstraints.toString();
            }
            strArr[0] = append.append(jSONObject).toString();
            ConfigManager.log(logLevel, strArr);
        }
        this.mKitManager.logError(message, errorAttributes);
    }

    public void logNetworkPerformance(String url, long startTime, String method, long length, long bytesSent, long bytesReceived, String requestString, int responseCode) {
        if (this.mConfigManager.isEnabled()) {
            this.mAppStateManager.ensureActiveSession();
            if (checkEventLimit().booleanValue()) {
                this.mMessageManager.mo44787a(startTime, method, url, length, bytesSent, bytesReceived, requestString);
            }
            this.mKitManager.logNetworkPerformance(url, startTime, method, length, bytesSent, bytesReceived, requestString, responseCode);
        }
    }

    public void logException(Exception exception) {
        logException(exception, null, null);
    }

    public void logException(Exception exception, Map<String, String> eventData) {
        logException(exception, eventData, null);
    }

    public void checkForDeepLink(DeepLinkListener deepLinkListener) {
        setDeepLinkListener(deepLinkListener);
        checkForDeepLink();
    }

    private void checkForDeepLink() {
        if (this.mDeepLinkListener != null) {
            this.mKitManager.checkForDeepLink();
        }
    }

    public void setDeepLinkListener(DeepLinkListener deepLinkListener) {
        this.mDeepLinkListener = deepLinkListener;
    }

    public DeepLinkListener getDeepLinkListener() {
        return this.mDeepLinkListener;
    }

    public void logException(Exception exception, Map<String, String> eventData, String message) {
        String jSONObject;
        String message2;
        if (this.mConfigManager.isEnabled()) {
            this.mAppStateManager.ensureActiveSession();
            if (checkEventLimit().booleanValue()) {
                JSONObject enforceAttributeConstraints = MPUtility.enforceAttributeConstraints(eventData);
                this.mMessageManager.mo44796a(message, (Throwable) exception, enforceAttributeConstraints);
                LogLevel logLevel = LogLevel.DEBUG;
                String[] strArr = new String[1];
                StringBuilder append = new StringBuilder().append("Logged exception with message: ").append(message == null ? "<none>" : message).append(" with data: ");
                if (enforceAttributeConstraints == null) {
                    jSONObject = "<none>";
                } else {
                    jSONObject = enforceAttributeConstraints.toString();
                }
                StringBuilder append2 = append.append(jSONObject).append(" with exception: ");
                if (exception == null) {
                    message2 = "<none>";
                } else {
                    message2 = exception.getMessage();
                }
                strArr[0] = append2.append(message2).toString();
                ConfigManager.log(logLevel, strArr);
            }
            this.mKitManager.logException(exception, eventData, message);
        }
    }

    public void enableLocationTracking(String provider, long minTime, long minDistance) {
        if (this.mConfigManager.isEnabled()) {
            try {
                LocationManager locationManager = (LocationManager) this.mAppContext.getSystemService("location");
                if (!locationManager.isProviderEnabled(provider)) {
                    ConfigManager.log(LogLevel.ERROR, "That requested location provider is not available");
                    return;
                }
                if (this.mLocationListener == null) {
                    this.mLocationListener = new MPLocationListener(this);
                } else {
                    locationManager.removeUpdates(this.mLocationListener);
                }
                locationManager.requestLocationUpdates(provider, minTime, (float) minDistance, this.mLocationListener);
                this.mPreferences.edit().putString("mp::location:provider", provider).putLong("mp::location:mintime", minTime).putLong("mp::location:mindistance", minDistance).apply();
            } catch (SecurityException e) {
                ConfigManager.log(LogLevel.ERROR, "The app must require the appropriate permissions to track location using this provider");
            }
        }
    }

    public void disableLocationTracking() {
        disableLocationTracking(true);
    }

    private void disableLocationTracking(boolean userTriggered) {
        if (this.mLocationListener != null) {
            try {
                LocationManager locationManager = (LocationManager) this.mAppContext.getSystemService("location");
                if (MPUtility.checkPermission(this.mAppContext, "android.permission.ACCESS_FINE_LOCATION") || MPUtility.checkPermission(this.mAppContext, "android.permission.ACCESS_COARSE_LOCATION")) {
                    locationManager.removeUpdates(this.mLocationListener);
                }
                this.mLocationListener = null;
                if (userTriggered) {
                    this.mPreferences.edit().remove("mp::location:provider").remove("mp::location:mintime").remove("mp::location:mindistance").apply();
                }
            } catch (Exception e) {
            }
        }
    }

    public void setLocation(Location location) {
        this.mMessageManager.mo44802a(location);
        this.mKitManager.setLocation(location);
    }

    public void setSessionAttribute(String key, Object value) {
        if (key == null) {
            ConfigManager.log(LogLevel.WARNING, "setSessionAttribute called with null key. Ignoring...");
            return;
        }
        if (value != null) {
            value = value.toString();
        }
        if (this.mConfigManager.isEnabled()) {
            this.mAppStateManager.ensureActiveSession();
            ConfigManager.log(LogLevel.DEBUG, "Set session attribute: " + key + "=" + value);
            if (MPUtility.setCheckedAttribute(this.mAppStateManager.getSession().f3731e, key, value, false, false).booleanValue()) {
                this.mMessageManager.mo44822i();
            }
        }
    }

    public void incrementSessionAttribute(String key, int value) {
        if (key == null) {
            ConfigManager.log(LogLevel.WARNING, "incrementSessionAttribute called with null key. Ignoring...");
        } else if (this.mConfigManager.isEnabled()) {
            this.mAppStateManager.ensureActiveSession();
            ConfigManager.log(LogLevel.DEBUG, "Incrementing session attribute: " + key + "=" + value);
            if (MPUtility.setCheckedAttribute(this.mAppStateManager.getSession().f3731e, key, Integer.valueOf(value), true, true).booleanValue()) {
                this.mMessageManager.mo44822i();
            }
        }
    }

    public void logout() {
        if (this.mConfigManager.isEnabled()) {
            this.mAppStateManager.ensureActiveSession();
            ConfigManager.log(LogLevel.DEBUG, "Logging out.");
            this.mMessageManager.mo44815b("logout");
        }
        this.mKitManager.logout();
    }

    public boolean setUserAttribute(String key, Object value) {
        if (MPUtility.isEmpty(key)) {
            ConfigManager.log(LogLevel.WARNING, "setUserAttribute called with null key. This is a no-op.");
            return false;
        } else if (key.length() > 256) {
            ConfigManager.log(LogLevel.WARNING, "User attribute keys cannot be longer than 256 characters, attribute not set: " + key);
            return false;
        } else {
            if (value == null || !(value instanceof List)) {
                String str = null;
                if (value != null) {
                    str = value.toString();
                    if (str.length() > 4096) {
                        ConfigManager.log(LogLevel.WARNING, "setUserAttribute called with string-value longer than 4096 characters. Attribute not set.");
                        return false;
                    }
                    ConfigManager.log(LogLevel.DEBUG, "Set user attribute: " + key + " with value: " + str);
                } else {
                    ConfigManager.log(LogLevel.DEBUG, "Set user tag: " + key);
                }
                this.mMessageManager.mo44809a(key, (Object) str);
                this.mKitManager.setUserAttribute(key, str);
            } else {
                List list = (List) value;
                if (list.size() > 1000) {
                    ConfigManager.log(LogLevel.WARNING, "setUserAttribute called with list longer than 1000 elements, list not set.");
                    return false;
                }
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < list.size()) {
                    try {
                        if (list.get(i).toString().length() > 512) {
                            ConfigManager.log(LogLevel.WARNING, "setUserAttribute called with list containing element longer than 512 characters, dropping entire list.");
                            return false;
                        }
                        arrayList.add(list.get(i).toString());
                        i++;
                    } catch (Exception e) {
                        ConfigManager.log(LogLevel.DEBUG, "Error while setting attribute list: " + e.toString());
                        return false;
                    }
                }
                ConfigManager.log(LogLevel.DEBUG, "Set user attribute list: " + key + " with values: " + list.toString());
                this.mMessageManager.mo44809a(key, (Object) arrayList);
                this.mKitManager.setUserAttributeList(key, arrayList);
            }
            return true;
        }
    }

    public boolean setUserAttributeList(String key, List<String> attributeList) {
        if (attributeList != null) {
            return setUserAttribute(key, attributeList);
        }
        ConfigManager.log(LogLevel.WARNING, "setUserAttributeList called with null list, this is a no-op.");
        return false;
    }

    public boolean incrementUserAttribute(String key, int value) {
        if (key == null) {
            ConfigManager.log(LogLevel.WARNING, "incrementUserAttribute called with null key. Ignoring...");
            return false;
        }
        ConfigManager.log(LogLevel.DEBUG, "Incrementing user attribute: " + key + " with value " + value);
        this.mMessageManager.mo44808a(key, value);
        return true;
    }

    public boolean removeUserAttribute(String key) {
        if (MPUtility.isEmpty(key)) {
            ConfigManager.log(LogLevel.DEBUG, "removeUserAttribute called with empty key.");
            return false;
        }
        ConfigManager.log(LogLevel.DEBUG, "Removing user attribute: " + key);
        this.mMessageManager.mo44816c(key);
        this.mKitManager.removeUserAttribute(key);
        return true;
    }

    public boolean setUserTag(String tag) {
        return setUserAttribute(tag, null);
    }

    public boolean removeUserTag(String tag) {
        return removeUserAttribute(tag);
    }

    public synchronized void setUserIdentity(String id, IdentityType identityType) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        synchronized (this) {
            if (identityType != null) {
                if (id == null) {
                    ConfigManager.log(LogLevel.DEBUG, "Removing User Identity type: " + identityType.name());
                } else {
                    ConfigManager.log(LogLevel.DEBUG, "Setting User Identity: " + id);
                }
                if (MPUtility.isEmpty(id) || id.length() <= 256) {
                    JSONArray s = this.mMessageManager.mo44834s();
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        try {
                            if (i2 >= s.length()) {
                                jSONObject = null;
                                break;
                            } else if (identityType.value == s.getJSONObject(i2).optInt("n")) {
                                jSONObject = s.getJSONObject(i2);
                                i = i2;
                                break;
                            } else {
                                i2++;
                            }
                        } catch (JSONException e) {
                            ConfigManager.log(LogLevel.ERROR, "Error setting identity: " + id);
                        }
                    }
                    if (id == null && jSONObject == null) {
                        ConfigManager.log(LogLevel.DEBUG, "Attempted to remove ID type that didn't exist: " + identityType.name());
                    }
                    if (id != null) {
                        jSONObject2 = new JSONObject();
                        jSONObject2.put("n", identityType.value);
                        jSONObject2.put("i", id);
                        if (jSONObject != null) {
                            jSONObject2.put("dfs", jSONObject.optLong("dfs", System.currentTimeMillis()));
                            jSONObject2.put("f", false);
                            s.put(i, jSONObject2);
                        } else {
                            jSONObject2.put("dfs", System.currentTimeMillis());
                            jSONObject2.put("f", true);
                            s.put(jSONObject2);
                        }
                    } else if (VERSION.SDK_INT >= 19) {
                        C4606d.m2232a(s, i);
                    } else {
                        JSONArray jSONArray = new JSONArray();
                        for (int i3 = 0; i3 < s.length(); i3++) {
                            if (i3 != i) {
                                jSONArray.put(s.get(i3));
                            }
                        }
                        s = jSONArray;
                    }
                    this.mMessageManager.mo44799a(jSONObject2, jSONObject, s);
                    if (id == null) {
                        getKitManager().removeUserIdentity(identityType);
                    } else {
                        getKitManager().setUserIdentity(id, identityType);
                    }
                } else {
                    ConfigManager.log(LogLevel.WARNING, "User Identity value length exceeds limit. Will not set id: " + id);
                }
            }
        }
        return;
    }

    public Map<IdentityType, String> getUserIdentities() {
        JSONArray s = this.mMessageManager.mo44834s();
        HashMap hashMap = new HashMap(s.length());
        for (int i = 0; i < s.length(); i++) {
            try {
                JSONObject jSONObject = s.getJSONObject(i);
                hashMap.put(IdentityType.parseInt(jSONObject.getInt("n")), jSONObject.getString("i"));
            } catch (JSONException e) {
            }
        }
        return hashMap;
    }

    public synchronized void removeUserIdentity(String id) {
        IdentityType identityType = null;
        int i = 0;
        synchronized (this) {
            JSONArray s = this.mMessageManager.mo44834s();
            if (id != null && id.length() > 0) {
                while (true) {
                    try {
                        if (i >= s.length()) {
                            break;
                        } else if (id.equals(s.getJSONObject(i).getString("i"))) {
                            try {
                                identityType = IdentityType.parseInt(s.getJSONObject(i).getInt("n"));
                                break;
                            } catch (Exception e) {
                            }
                        } else {
                            i++;
                        }
                    } catch (JSONException e2) {
                        ConfigManager.log(LogLevel.WARNING, "Error removing identity: " + id);
                    }
                }
                if (identityType != null) {
                    setUserIdentity(null, identityType);
                }
            }
        }
        return;
    }

    public Boolean getOptOut() {
        return Boolean.valueOf(this.mConfigManager.getOptedOut());
    }

    public void setOptOut(Boolean optOutStatus) {
        if (optOutStatus != null) {
            if (optOutStatus.booleanValue() != this.mConfigManager.getOptedOut()) {
                if (!optOutStatus.booleanValue()) {
                    this.mAppStateManager.ensureActiveSession();
                }
                this.mMessageManager.mo44788a(System.currentTimeMillis(), optOutStatus.booleanValue());
                if (optOutStatus.booleanValue() && isSessionActive()) {
                    endSession();
                }
                this.mConfigManager.setOptOut(optOutStatus.booleanValue());
                ConfigManager.log(LogLevel.DEBUG, "Set opt-out: " + optOutStatus);
            }
            this.mKitManager.setOptOut(optOutStatus.booleanValue());
        }
    }

    public Uri getSurveyUrl(int kitId) {
        return this.mKitManager.getSurveyUrl(kitId, getUserAttributes(), getUserAttributeLists());
    }

    @Deprecated
    public void setEnvironment(Environment environment) {
        Log.w("mParticle SDK", "setEnvironment is deprecated and is a no-op. Use start() or XML configuration if you must customize environment.");
    }

    public Environment getEnvironment() {
        return ConfigManager.getEnvironment();
    }

    public void setUploadInterval(int uploadInterval) {
        this.mConfigManager.setUploadInterval(uploadInterval);
    }

    public void enableUncaughtExceptionLogging() {
        this.mConfigManager.enableUncaughtExceptionLogging(true);
    }

    public void disableUncaughtExceptionLogging() {
        this.mConfigManager.disableUncaughtExceptionLogging(true);
    }

    private Boolean checkEventLimit() {
        return this.mAppStateManager.getSession().mo44850c();
    }

    public Boolean isAutoTrackingEnabled() {
        return Boolean.valueOf(this.mConfigManager.isAutoTrackingEnabled());
    }

    public int getSessionTimeout() {
        return this.mConfigManager.getSessionTimeout() / 1000;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.mConfigManager.setSessionTimeout(sessionTimeout);
    }

    public void getUserSegments(long timeout, String endpointId, SegmentListener listener) {
        if (this.mMessageManager != null && this.mMessageManager.f3706a != null) {
            this.mMessageManager.f3706a.mo44925a(timeout, endpointId, listener);
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void registerWebView(WebView webView) {
        if (webView != null) {
            webView.addJavascriptInterface(new C4618k(), "mParticleAndroid");
        }
    }

    public void setLogLevel(LogLevel level) {
        if (level != null) {
            this.mConfigManager.setLogLevel(level);
        }
    }

    public MPMessagingAPI Messaging() {
        if (this.mMessaging == null) {
            this.mMessaging = new MPMessagingAPI(this.mAppContext);
        }
        return this.mMessaging;
    }

    public CommerceApi Commerce() {
        return this.mCommerce;
    }

    public ProductBagApi ProductBags() {
        return this.mProductBags;
    }

    public MPMediaAPI Media() {
        if (this.mMedia == null) {
            this.mMedia = new MPMediaAPI(this.mAppContext, new MediaCallbacks() {
                public void onAudioPlaying() {
                    MParticle.this.mAppStateManager.ensureActiveSession();
                }

                public void onAudioStopped() {
                    try {
                        MParticle.this.mAppStateManager.getSession().f3730d = System.currentTimeMillis();
                    } catch (Exception e) {
                    }
                }
            });
        }
        return this.mMedia;
    }

    public boolean isProviderActive(int serviceProviderId) {
        return isKitActive(serviceProviderId);
    }

    public boolean isKitActive(int serviceProviderId) {
        return this.mKitManager.isKitActive(serviceProviderId);
    }

    public Object getKitInstance(int kitId) {
        return this.mKitManager.getKitInstance(kitId);
    }

    /* access modifiers changed from: 0000 */
    public void saveGcmMessage(MPCloudNotificationMessage cloudMessage, String appState) {
        this.mMessageManager.mo44806a(cloudMessage, appState);
    }

    /* access modifiers changed from: 0000 */
    public void saveGcmMessage(ProviderCloudMessage cloudMessage, String appState) {
        this.mMessageManager.mo44814b(cloudMessage, appState);
    }

    public void logPushRegistration(String instanceId, String senderId) {
        this.mAppStateManager.ensureActiveSession();
        PushRegistration pushRegistration = new PushRegistration();
        pushRegistration.instanceId = instanceId;
        pushRegistration.senderId = senderId;
        PushRegistrationHelper.setInstanceId(this.mAppContext, pushRegistration);
        this.mMessageManager.mo44798a(instanceId, true);
        this.mKitManager.onPushRegistration(instanceId, senderId);
    }

    /* access modifiers changed from: 0000 */
    public void logNotification(MPCloudNotificationMessage cloudMessage, CloudAction action, boolean startSession, String appState, int behavior) {
        if (this.mConfigManager.isEnabled()) {
            if (startSession) {
                this.mAppStateManager.ensureActiveSession();
            }
            this.mMessageManager.mo44801a(cloudMessage.getId(), cloudMessage.getRedactedJsonPayload().toString(), action, appState, behavior);
        }
    }

    /* access modifiers changed from: 0000 */
    public void logNotification(ProviderCloudMessage cloudMessage, boolean startSession, String appState) {
        if (this.mConfigManager.isEnabled()) {
            if (startSession) {
                this.mAppStateManager.ensureActiveSession();
            }
            this.mMessageManager.mo44807a(cloudMessage, appState);
        }
    }

    /* access modifiers changed from: 0000 */
    public void refreshConfiguration() {
        ConfigManager.log(LogLevel.DEBUG, "Refreshing configuration...");
        this.mMessageManager.mo44830o();
    }

    public Map<String, String> getUserAttributes() {
        return this.mMessageManager.mo44811b((UserAttributeListener) null);
    }

    public Map<String, List<String>> getUserAttributeLists() {
        return this.mMessageManager.mo44832q();
    }

    public Map<String, Object> getAllUserAttributes() {
        return this.mMessageManager.mo44800a((UserAttributeListener) null);
    }

    public void getAllUserAttributes(UserAttributeListener listener) {
        this.mMessageManager.mo44800a(listener);
    }

    /* access modifiers changed from: 0000 */
    public void logUnhandledError(Throwable t) {
        if (this.mConfigManager.isEnabled()) {
            this.mMessageManager.mo44797a(t != null ? t.getMessage() : null, t, null, false);
            this.mAppStateManager.logStateTransition("app_exit", this.mAppStateManager.getCurrentActivityName());
            this.mAppStateManager.getSession().f3730d = System.currentTimeMillis();
            this.mAppStateManager.endSession();
        }
    }
}
