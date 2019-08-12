package com.airbnb.android.core.modules;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.AnalyticsRegistry;
import com.airbnb.android.core.DeviceInfo;
import com.airbnb.android.core.DynamicStringsResources;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.N2Callbacks;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.PostInteractiveInitializer;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarStoreCache;
import com.airbnb.android.core.calendar.CalendarStoreConfig;
import com.airbnb.android.core.contentproviders.ViewedListingsDatabaseHelper;
import com.airbnb.android.core.controllers.DummyGoogleAppIndexingController;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.controllers.GoogleAppIndexingController;
import com.airbnb.android.core.controllers.GoogleAppIndexingControllerImpl;
import com.airbnb.android.core.controllers.SplashScreenController;
import com.airbnb.android.core.controllers.TrebuchetController;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.erf.ErfAnalytics;
import com.airbnb.android.core.erf.ErfCallbacks;
import com.airbnb.android.core.erf.ErfExperimentFactory;
import com.airbnb.android.core.erf.ErfExperimentsTableOpenHelper;
import com.airbnb.android.core.erf.ExperimentAssignments;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.identity.ChooseProfilePhotoController;
import com.airbnb.android.core.instant_promo.InstantPromotionManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.location.LocationClientFacade.Factory;
import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import com.airbnb.android.core.messaging.MessageStore;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.messaging.SyncRequestFactory;
import com.airbnb.android.core.messaging.p007db.MessageStoreTableOpenHelper;
import com.airbnb.android.core.messaging.p007db.ThreadDataMapper;
import com.airbnb.android.core.persist.DomainStore;
import com.airbnb.android.core.security.AppIdentityVerifier;
import com.airbnb.android.core.utils.AirCookieManager;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.ClientSessionManager;
import com.airbnb.android.core.utils.ClientSessionValidator;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PullStringsScheduler;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListenerImpl;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.erf.Erf;
import com.airbnb.p027n2.C0977N2.Callbacks;
import com.airbnb.p027n2.internal.ComponentManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.analytics.Tracker;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.squareup.otto.Bus;
import dagger.Lazy;
import okhttp3.Cache;
import p030in.uncod.android.bypass.Bypass;

public class CoreModule {
    private final Application application;

    public interface Declarations {
        PostApplicationCreatedInitializer bindMessageStoreRequestFactory(MessagingRequestFactory messagingRequestFactory);

        PostInteractiveInitializer bindWishListManager(WishListManager wishListManager);
    }

    public CoreModule(Application application2) {
        this.application = application2;
    }

    /* access modifiers changed from: 0000 */
    public Context provideContext() {
        return this.application;
    }

    /* access modifiers changed from: 0000 */
    public Application provideApplication() {
        return this.application;
    }

    static ShakeFeedbackSensorListener provideShakeFeedbackhelper(AirbnbPreferences preferences) {
        return new ShakeFeedbackSensorListenerImpl(preferences);
    }

    static PostApplicationCreatedInitializer provideAppIdentityVerifier(Context context) {
        return new AppIdentityVerifier(context);
    }

    static AirbnbPreferences provideAirbnbPreferences(Context context) {
        return new AirbnbPreferences(context);
    }

    public AirbnbApi provideAirbnbApi(Context context, AirbnbPreferences sharedPreferences, MessageStore messageStore, AirbnbAccountManager accountManager, Bus bus, Cache cache, DeviceInfo deviceInfo) {
        return _provideAirbnbApi(context, sharedPreferences, messageStore, accountManager, bus, cache, deviceInfo);
    }

    public AirbnbApi _provideAirbnbApi(Context context, AirbnbPreferences sharedPreferences, MessageStore messageStore, AirbnbAccountManager accountManager, Bus bus, Cache cache, DeviceInfo deviceInfo) {
        return new AirbnbApi(context, sharedPreferences, messageStore, accountManager, bus, cache, deviceInfo);
    }

    public AirbnbAccountManager provideAccountManager(AccountManager accountManager, AirbnbPreferences preferences) {
        return new AirbnbAccountManager(accountManager, preferences);
    }

    public ClientSessionManager provideClientSessionManager(AirbnbPreferences preferences) {
        return new ClientSessionManager(preferences);
    }

    public ClientSessionValidator provideClientSessionValidator(AirbnbPreferences preferences, LoggingContextFactory loggingContextFactory) {
        return new ClientSessionValidator(preferences, loggingContextFactory);
    }

    static AccountManager provideAndroidAccountManager(Context context) {
        return AccountManager.get(context);
    }

    static BusinessTravelAccountManager provideBusinessTravelAccountManager(AirbnbAccountManager airbnbAccountManager, Bus bus) {
        return new BusinessTravelAccountManager(airbnbAccountManager, bus);
    }

    static ErfCallbacks provideErfCallbacks(AirbnbAccountManager accountManager, ErfAnalytics erfAnalytics, ExperimentsProvider experimentsProvider) {
        return new ErfCallbacks(accountManager, erfAnalytics, experimentsProvider);
    }

    static Erf provideErf(ExperimentsProvider experimentsProvider, ErfCallbacks callbacks) {
        return new Erf(experimentsProvider, callbacks);
    }

    /* access modifiers changed from: 0000 */
    public ErfExperimentsTableOpenHelper provideErfExperimentsTableOpenHelper(Context context, ObjectMapper objectMapper) {
        return new ErfExperimentsTableOpenHelper(context, new ErfExperimentFactory(objectMapper));
    }

    /* access modifiers changed from: 0000 */
    public LocalPushNotificationManager provideLocalPushNotificationManager(Context context, AirbnbPreferences sharedPref, AirbnbApi airbnbApi, AirbnbAccountManager accountManager) {
        return new LocalPushNotificationManager(context, sharedPref, airbnbApi, accountManager);
    }

    /* access modifiers changed from: 0000 */
    public ExperimentsProvider provideExperimentsProvider(Context context, Bus bus, ErfExperimentsTableOpenHelper tableOpenHelper) {
        return _provideExperimentsProvider(context, bus, tableOpenHelper);
    }

    /* access modifiers changed from: 0000 */
    public ExperimentsProvider _provideExperimentsProvider(Context context, Bus bus, ErfExperimentsTableOpenHelper tableOpenHelper) {
        return new ExperimentsProvider(context, bus, tableOpenHelper);
    }

    /* access modifiers changed from: 0000 */
    public TrebuchetController provideTrebuchetController(Context context) {
        return _provideTrebuchetController(context);
    }

    /* access modifiers changed from: protected */
    public TrebuchetController _provideTrebuchetController(Context context) {
        return new TrebuchetController(Trebuchet.getTrebuchetPrefs(context));
    }

    static ExperimentConfigController provideExperimentConfigController(Context context, AirRequestInitializer initializer, ExperimentsProvider experimentsProvider, Bus bus, PerformanceLogger performanceLogger, TrebuchetController trebuchetController) {
        return new ExperimentConfigController(context, initializer, experimentsProvider, bus, performanceLogger, trebuchetController);
    }

    static AppLaunchUtils provideAppLaunchUtils(AirbnbPreferences preferences, AffiliateInfo affiliateInfo, DomainStore domainStore, AirbnbAccountManager accountManager, Tracker tracker, LoggingContextFactory loggingContextFactory) {
        return new AppLaunchUtils(preferences, affiliateInfo, domainStore, accountManager, tracker, loggingContextFactory);
    }

    static AppLaunchAnalytics provideAppLaunchAnalytics(PerformanceLogger performanceLogger, AirbnbPreferences preferences) {
        return new AppLaunchAnalytics(performanceLogger, preferences);
    }

    static SharedPrefsHelper provideSharedPrefsHelper(AirbnbPreferences preferences) {
        return new SharedPrefsHelper(preferences);
    }

    static SplashScreenController provideSplashScreenController(AirbnbPreferences airbnbPreferences) {
        return new SplashScreenController(airbnbPreferences);
    }

    static GoogleAppIndexingController provideGoogleAppIndexingController(Context context) {
        if (MiscUtils.hasGooglePlayServices(context)) {
            return new GoogleAppIndexingControllerImpl(context);
        }
        return new DummyGoogleAppIndexingController();
    }

    static MemoryUtils provideMemoryUtils(AirbnbPreferences preferences) {
        return new MemoryUtils(preferences);
    }

    /* access modifiers changed from: 0000 */
    public DomainStore provideDomainStore(Context context) {
        return _provideDomainStore(context);
    }

    /* access modifiers changed from: 0000 */
    public DomainStore _provideDomainStore(Context context) {
        return new DomainStore(context);
    }

    public ExperimentAssignments provideExperimentAssigments(Bus bus, Erf erf) {
        return _provideExperimentAssignments(bus, erf);
    }

    public ExperimentAssignments _provideExperimentAssignments(Bus bus, Erf erf) {
        return new ExperimentAssignments(bus, erf, MiscUtils.isTabletScreen(this.application));
    }

    /* access modifiers changed from: 0000 */
    public LocationClientFacade provideLocationHelper(Context context) {
        return _provideLocationHelper(context);
    }

    /* access modifiers changed from: 0000 */
    public LocationClientFacade _provideLocationHelper(Context context) {
        return Factory.get(context, null);
    }

    static Bus provideBus() {
        return new Bus();
    }

    static Bypass provideBypass(Context context) {
        return new Bypass(context);
    }

    static CurrencyFormatter provideCurrencyHelper(Context context, AirbnbAccountManager accountManager, AirbnbPreferences preferences) {
        return new CurrencyFormatter(context, accountManager, preferences);
    }

    /* access modifiers changed from: 0000 */
    public AirbnbEventLogger provideAirbnbEventLogger(AirbnbAccountManager accountManager, AffiliateInfo affiliateInfo, TimeSkewAnalytics timeSkewAnalytics, LoggingContextFactory loggingContextFactory) {
        return _provideAirbnbEventLogger(accountManager, affiliateInfo, timeSkewAnalytics, loggingContextFactory);
    }

    /* access modifiers changed from: 0000 */
    public AirbnbEventLogger _provideAirbnbEventLogger(AirbnbAccountManager accountManager, AffiliateInfo affiliateInfo, TimeSkewAnalytics timeSkewAnalytics, LoggingContextFactory loggingContextFactory) {
        return new AirbnbEventLogger(accountManager, affiliateInfo, timeSkewAnalytics, loggingContextFactory);
    }

    /* access modifiers changed from: 0000 */
    public LoggingContextFactory provideLoggingContextFactory(Context context, DeviceInfo deviceInfo, AirbnbAccountManager accountManager, AffiliateInfo affiliateInfo, AirbnbPreferences airbnbPreferences, TimeSkewAnalytics timeSkewAnalytics, ClientSessionManager clientSessionManager) {
        return _provideLoggingContextFactory(context, deviceInfo, accountManager, affiliateInfo, airbnbPreferences, timeSkewAnalytics, clientSessionManager);
    }

    public LoggingContextFactory _provideLoggingContextFactory(Context context, DeviceInfo deviceInfo, AirbnbAccountManager accountManager, AffiliateInfo affiliateInfo, AirbnbPreferences airbnbPreferences, TimeSkewAnalytics timeSkewAnalytics, ClientSessionManager clientSessionManager) {
        return new LoggingContextFactory(context, deviceInfo, accountManager, affiliateInfo, clientSessionManager, airbnbPreferences, timeSkewAnalytics);
    }

    public PerformanceLogger providePerformanceLogger(LoggingContextFactory loggingContextFactory, SharedPrefsHelper prefsHelper) {
        return new PerformanceLogger(loggingContextFactory, prefsHelper);
    }

    static AirCookieManager provideAirCookieManager(Context context) {
        return new AirCookieManager(context);
    }

    static ViewedListingsDatabaseHelper provideViewedListingsDatabaseHelper(Context context) {
        return new ViewedListingsDatabaseHelper(context);
    }

    static WishListManager provideWishListManager(Context context, AirbnbAccountManager accountManager, Bus bus, AirRequestInitializer airRequestInitializer) {
        return new WishListManager(context, accountManager, bus, airRequestInitializer);
    }

    static PhoneUtil providePhoneUtil(Context context) {
        return new PhoneUtil(context);
    }

    static PhoneNumberUtil providePhoneNumberUtil() {
        return PhoneNumberUtil.getInstance();
    }

    static ChooseProfilePhotoController provideChooseProfilePhotoController(Context context, PhotoCompressor photoCompressor) {
        return new ChooseProfilePhotoController(context, photoCompressor);
    }

    /* access modifiers changed from: 0000 */
    public DebugSettings provideDebugSettings(Context context, Bus bus) {
        return new DebugSettings(context, bus);
    }

    /* access modifiers changed from: 0000 */
    public ResourceManager provideResourceManager(Context context) {
        return new ResourceManager(context);
    }

    static Callbacks provideN2Callbacks(AirbnbPreferences preferences) {
        return new N2Callbacks(preferences);
    }

    static ThreadDataMapper provideThreadDataMapper() {
        return new ThreadDataMapper();
    }

    static MessageStoreTableOpenHelper provideMessageStoreTableOpenHelper(Context context, Lazy<ThreadDataMapper> threadDataMapper, InboxUnreadCountManager inboxUnreadCountManager) {
        return new MessageStoreTableOpenHelper(context, threadDataMapper, inboxUnreadCountManager);
    }

    /* access modifiers changed from: 0000 */
    public MessageStore provideMessageStore(Lazy<MessageStoreTableOpenHelper> messageStoreTableOpenHelper) {
        return _provideMessageStore(messageStoreTableOpenHelper);
    }

    /* access modifiers changed from: protected */
    public MessageStore _provideMessageStore(Lazy<MessageStoreTableOpenHelper> messageStoreTableOpenHelper) {
        return new MessageStore(messageStoreTableOpenHelper);
    }

    /* access modifiers changed from: 0000 */
    public SyncRequestFactory provideSyncRequestFactory(MessageStore messageStore, AirRequestInitializer airRequestInitializer, Bus bus, MessagingJitneyLogger jitneyLogger) {
        return _provideSyncRequestFactory(messageStore, airRequestInitializer, bus, jitneyLogger);
    }

    /* access modifiers changed from: protected */
    public SyncRequestFactory _provideSyncRequestFactory(MessageStore messageStore, AirRequestInitializer airRequestInitializer, Bus bus, MessagingJitneyLogger jitneyLogger) {
        return new SyncRequestFactory(messageStore, airRequestInitializer, bus, jitneyLogger);
    }

    static MessagingRequestFactory provideMessageStoreRequestFactory(Context context, Bus bus, AirbnbAccountManager accountManager, MessageStore store, SyncRequestFactory syncRequestFactory, MessagingJitneyLogger jitneyLogger, PhotoCompressor photoCompressor, InboxUnreadCountManager inboxUnreadCountManager) {
        return new MessagingRequestFactory(context, bus, accountManager, store, syncRequestFactory, jitneyLogger, photoCompressor, inboxUnreadCountManager);
    }

    /* access modifiers changed from: 0000 */
    public NavigationLogging provideKonaNavigationAnalytics(Context context, DebugSettings debugSettings, SharedPrefsHelper sharedPrefsHelper, LoggingContextFactory loggingContextFactory) {
        return new NavigationLogging(context, debugSettings, sharedPrefsHelper, loggingContextFactory);
    }

    static CalendarStore provideCalendarStore(CalendarStoreCache calendarStoreCache, CalendarStoreConfig config, Bus bus) {
        return new CalendarStore(calendarStoreCache, config, bus);
    }

    static CalendarStoreCache provideCalendarStoreCache() {
        return new CalendarStoreCache();
    }

    static CalendarStoreConfig provideCalendarStoreConfig() {
        return new CalendarStoreConfig();
    }

    static ErfAnalytics provideErfAnalytics(DeviceInfo deviceInfo, AirbnbAccountManager accountManager) {
        return new ErfAnalytics(deviceInfo, accountManager);
    }

    static DynamicStringsResources provideDynamicStringsResources(Context context, AirbnbPreferences sharedPreferences) {
        return new DynamicStringsResources(context, sharedPreferences);
    }

    static PullStringsScheduler providePullStringsScheduler(Context context, AirbnbPreferences airbnbPreferences) {
        return new PullStringsScheduler(context, airbnbPreferences.getStringPreferences());
    }

    static DeviceInfo provideDeviceID(Context context) {
        return new DeviceInfo(context);
    }

    static AnalyticsRegistry provideAnalyticsRegistry() {
        return new AnalyticsRegistry();
    }

    static InstantBookUpsellManager provideInstantBookUpsellManager(Context context) {
        return new InstantBookUpsellManager(context);
    }

    /* access modifiers changed from: 0000 */
    public InboxUnreadCountManager provideInboxUnreadCountManager(Bus bus) {
        return _provideInboxUnreadCountManager(bus);
    }

    /* access modifiers changed from: protected */
    public InboxUnreadCountManager _provideInboxUnreadCountManager(Bus bus) {
        return new InboxUnreadCountManager(bus);
    }

    /* access modifiers changed from: 0000 */
    public ViewBreadcrumbManager provideViewBreadcrumbManager() {
        return new ViewBreadcrumbManager();
    }

    static InstantPromotionManager provideInstantPromotionManager(ErfAnalytics erfAnalytics, AirbnbAccountManager accountManager) {
        return new InstantPromotionManager(erfAnalytics, accountManager);
    }

    static ListingPromoController providePromoController(AirbnbAccountManager accountManager) {
        return new ListingPromoController(accountManager);
    }

    static ComponentManager provideComponentManager() {
        return new ComponentManager();
    }
}
