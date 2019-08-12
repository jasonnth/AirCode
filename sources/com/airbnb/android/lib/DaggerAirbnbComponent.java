package com.airbnb.android.lib;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.BaseUrl;
import com.airbnb.airrequest.SingleFireRequestExecutor;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.apprater.AppRaterDialogFragment;
import com.airbnb.android.apprater.AppRaterDialogFragment_MembersInjector;
import com.airbnb.android.apprater.AppRaterModule;
import com.airbnb.android.apprater.AppRaterModule_ProvideAppRaterControllerFactory;
import com.airbnb.android.booking.fragments.BookingSummaryFragment;
import com.airbnb.android.booking.fragments.BookingSummaryFragment_MembersInjector;
import com.airbnb.android.booking.fragments.CreditCardBaseFragment;
import com.airbnb.android.booking.fragments.CreditCardBaseFragment_MembersInjector;
import com.airbnb.android.booking.fragments.SelectCountryFragment;
import com.airbnb.android.booking.fragments.SelectCountryFragment_MembersInjector;
import com.airbnb.android.booking.fragments.SelectPaymentMethodFragment;
import com.airbnb.android.booking.fragments.SelectPaymentMethodFragment_MembersInjector;
import com.airbnb.android.booking.fragments.alipay.BaseAlipayFragment;
import com.airbnb.android.booking.fragments.alipay.BaseAlipayFragment_MembersInjector;
import com.airbnb.android.checkin.CheckInIntroFragment;
import com.airbnb.android.checkin.CheckInIntroFragment_MembersInjector;
import com.airbnb.android.checkin.CheckinStepFragment;
import com.airbnb.android.checkin.CheckinStepFragment_MembersInjector;
import com.airbnb.android.checkin.CheckinStepPagerFragment;
import com.airbnb.android.checkin.CheckinStepPagerFragment_MembersInjector;
import com.airbnb.android.checkin.GuestCheckInJitneyLogger;
import com.airbnb.android.checkin.ImageViewerActivity;
import com.airbnb.android.checkin.ImageViewerActivity_MembersInjector;
import com.airbnb.android.checkin.ViewCheckinActivity;
import com.airbnb.android.checkin.ViewCheckinActivity_MembersInjector;
import com.airbnb.android.checkin.data.CheckInComponent;
import com.airbnb.android.checkin.data.CheckInDataSyncService;
import com.airbnb.android.checkin.data.CheckInDataSyncService_MembersInjector;
import com.airbnb.android.checkin.data.CheckInDataTableOpenHelper;
import com.airbnb.android.checkin.data.CheckInModule;
import com.airbnb.android.checkin.data.CheckInModule_JitneyLoggerFactory;
import com.airbnb.android.checkin.data.CheckInModule_ProvideCheckInDataTableOpenHelperFactory;
import com.airbnb.android.cohosting.CohostingModule;
import com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity;
import com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity_MembersInjector;
import com.airbnb.android.cohosting.activities.CohostManagementActivity;
import com.airbnb.android.cohosting.activities.CohostManagementActivity_MembersInjector;
import com.airbnb.android.cohosting.activities.CohostReasonSelectionActivity;
import com.airbnb.android.cohosting.activities.CohostReasonSelectionActivity_MembersInjector;
import com.airbnb.android.cohosting.activities.CohostUpsellActivity;
import com.airbnb.android.cohosting.activities.CohostUpsellActivity_MembersInjector;
import com.airbnb.android.cohosting.adapters.CohostingInviteFriendAdapter;
import com.airbnb.android.cohosting.adapters.CohostingInviteFriendAdapter_MembersInjector;
import com.airbnb.android.cohosting.adapters.CohostingListingPickerAdapter;
import com.airbnb.android.cohosting.adapters.CohostingListingPickerAdapter_MembersInjector;
import com.airbnb.android.cohosting.adapters.CohostingShareEarningsAdapter;
import com.airbnb.android.cohosting.adapters.CohostingShareEarningsAdapter_MembersInjector;
import com.airbnb.android.cohosting.adapters.ListingManagerDetailsAdapter;
import com.airbnb.android.cohosting.adapters.ListingManagerDetailsAdapter_MembersInjector;
import com.airbnb.android.cohosting.adapters.ListingManagersPickerAdapter;
import com.airbnb.android.cohosting.adapters.ListingManagersPickerAdapter_MembersInjector;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController_MembersInjector;
import com.airbnb.android.cohosting.epoxycontrollers.CohostUpsellEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostUpsellEpoxyController_MembersInjector;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInviteFriendEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInviteFriendEpoxyController_MembersInjector;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingShareEarningsEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingShareEarningsEpoxyController_MembersInjector;
import com.airbnb.android.cohosting.fragments.AcceptCohostInvitationFragment;
import com.airbnb.android.cohosting.fragments.AcceptCohostInvitationFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostReasonMessageTextInputFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonMessageTextInputFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostReasonPrivateFeedbackTextInputFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonPrivateFeedbackTextInputFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostReasonSelectionFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonSelectionFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostUpsellFragment;
import com.airbnb.android.cohosting.fragments.CohostUpsellFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingContractFragment;
import com.airbnb.android.cohosting.fragments.CohostingContractFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingInvitationErrorFragment;
import com.airbnb.android.cohosting.fragments.CohostingInvitationErrorFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingInvitationExpiredFragment;
import com.airbnb.android.cohosting.fragments.CohostingInvitationExpiredFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendConfirmationFragment;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendConfirmationFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendWithFeeOptionFragment;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendWithFeeOptionFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingListingLevelNotificationSettingFragment;
import com.airbnb.android.cohosting.fragments.CohostingListingLevelNotificationSettingFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingMakePrimaryHostFragment;
import com.airbnb.android.cohosting.fragments.CohostingMakePrimaryHostFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingServicesIntroFragment;
import com.airbnb.android.cohosting.fragments.CohostingServicesIntroFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingShareEarningsWithFeeOptionFragment;
import com.airbnb.android.cohosting.fragments.CohostingShareEarningsWithFeeOptionFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.CohostingStopShareEarningsFragment;
import com.airbnb.android.cohosting.fragments.CohostingStopShareEarningsFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.ConfirmInvitationAcceptedFragment;
import com.airbnb.android.cohosting.fragments.ConfirmInvitationAcceptedFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.ListingManagersPickerFragment;
import com.airbnb.android.cohosting.fragments.ListingManagersPickerFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.PendingCohostDetailsFragment;
import com.airbnb.android.cohosting.fragments.PendingCohostDetailsFragment_MembersInjector;
import com.airbnb.android.cohosting.fragments.RemoveCohostFragment;
import com.airbnb.android.cohosting.fragments.RemoveCohostFragment_MembersInjector;
import com.airbnb.android.contentframework.ContentFrameworkComponent;
import com.airbnb.android.contentframework.ContentFrameworkModule;
import com.airbnb.android.contentframework.ContentFrameworkModule_ProvideStoryPublishControllerFactory;
import com.airbnb.android.contentframework.controller.StoryPublishController;
import com.airbnb.android.contentframework.fragments.StoryCreationComposerFragment;
import com.airbnb.android.contentframework.fragments.StoryCreationComposerFragment_MembersInjector;
import com.airbnb.android.contentframework.fragments.StoryCreationPickTripFragment;
import com.airbnb.android.contentframework.fragments.StoryCreationPickTripFragment_MembersInjector;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment_MembersInjector;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.AnalyticsRegistry;
import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.ButtonPartnership;
import com.airbnb.android.core.ButtonPartnership_MembersInjector;
import com.airbnb.android.core.DeviceInfo;
import com.airbnb.android.core.DynamicStringsResources;
import com.airbnb.android.core.JPushInitializer;
import com.airbnb.android.core.LogAirInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.PostInteractiveInitializer;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ResourceManager_MembersInjector;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.activities.AirActivity_MembersInjector;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AlipayAnalytics;
import com.airbnb.android.core.analytics.AppForegroundAnalytics;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.AvailabilityCalendarJitneyLogger;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.analytics.CohostingInvitationJitneyLogger;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.analytics.CohostingReusableFlowJitneyLogger;
import com.airbnb.android.core.analytics.CommunityCommitmentJitneyLogger;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.PaidAmenityJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.analytics.ReservationCancellationLogger;
import com.airbnb.android.core.analytics.ReviewSearchJitneyLogger;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper_MembersInjector;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.contentproviders.ViewedListingsDatabaseHelper;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.controllers.BottomBarController_Factory;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.controllers.GoogleAppIndexingController;
import com.airbnb.android.core.controllers.SplashScreenController;
import com.airbnb.android.core.controllers.TrebuchetController;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.data.AffiliateInfo_Factory;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.data.DTKPartnerTask;
import com.airbnb.android.core.data.DTKPartnerTask_MembersInjector;
import com.airbnb.android.core.data.SFRPartnerTask;
import com.airbnb.android.core.data.SFRPartnerTask_MembersInjector;
import com.airbnb.android.core.dls.DLSJitneyLogger;
import com.airbnb.android.core.dls.DLSJitneyLogger_Factory;
import com.airbnb.android.core.erf.ErfAnalytics;
import com.airbnb.android.core.erf.ErfCallbacks;
import com.airbnb.android.core.erf.ErfExperimentsTableOpenHelper;
import com.airbnb.android.core.erf.ExperimentAssignments;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.filters.LocationTypeaheadFilterForChina;
import com.airbnb.android.core.filters.LocationTypeaheadFilterForChina_MembersInjector;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.fragments.AirDialogFragment_MembersInjector;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.AirFragment_MembersInjector;
import com.airbnb.android.core.fragments.DLSCancellationPolicyFragment;
import com.airbnb.android.core.fragments.DLSCancellationPolicyFragment_MembersInjector;
import com.airbnb.android.core.fragments.LottieNuxViewPagerFragment;
import com.airbnb.android.core.fragments.LottieNuxViewPagerFragment_MembersInjector;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog_MembersInjector;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment_MembersInjector;
import com.airbnb.android.core.graphql.GraphistClient;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.core.identity.ChooseProfilePhotoController;
import com.airbnb.android.core.identity.IdentityClient;
import com.airbnb.android.core.identity.ReplaceVerifiedIdWithIdentityActivity;
import com.airbnb.android.core.identity.ReplaceVerifiedIdWithIdentityActivity_MembersInjector;
import com.airbnb.android.core.instant_promo.InstantPromotionManager;
import com.airbnb.android.core.interfaces.WebIntentMatcher;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager_MembersInjector;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import com.airbnb.android.core.messaging.MessageStore;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.messaging.SyncRequestFactory;
import com.airbnb.android.core.messaging.p007db.MessageStoreTableOpenHelper;
import com.airbnb.android.core.models.AuthorizedAccount;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.modules.AnalyticsModule;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideAlipayAnalyticsFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideAnalyticsTrackerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideAppForegroundAnalyticsFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideAppForegroundDetectorFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideAvailabilityCalendarJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideBookingJitneyEventLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideBusinessTravelJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideCalendarJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideCohostingInvitationJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideCohostingManagementJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideCohostingReusableFlowJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideCommunityCommitmentJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideHostReservationObjectJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideHostStatsJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideIdentityJitneyEventLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideLogAirInitializerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideMessagingJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvidePaidAmenityJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideQuickPayJitneyEventLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideReservationCancellationLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideReservationResponseLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideReviewSearchJitneyLoggerFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideTimeSkewAnalyticsFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideVerifiedIdAnalyticsFactory;
import com.airbnb.android.core.modules.AnalyticsModule_ProvideWishListLoggerFactory;
import com.airbnb.android.core.modules.CoreModule;
import com.airbnb.android.core.modules.CoreModule_ProvideAccountManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAirCookieManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAirbnbApiFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAirbnbEventLoggerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAirbnbPreferencesFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAnalyticsRegistryFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAndroidAccountManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAppIdentityVerifierFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAppLaunchAnalyticsFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideAppLaunchUtilsFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideApplicationFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideBusFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideBusinessTravelAccountManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideBypassFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideCalendarStoreCacheFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideCalendarStoreConfigFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideCalendarStoreFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideChooseProfilePhotoControllerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideClientSessionManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideClientSessionValidatorFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideComponentManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideContextFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideCurrencyHelperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideDebugSettingsFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideDeviceIDFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideDomainStoreFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideDynamicStringsResourcesFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideErfAnalyticsFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideErfCallbacksFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideErfExperimentsTableOpenHelperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideErfFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideExperimentAssigmentsFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideExperimentConfigControllerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideExperimentsProviderFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideGoogleAppIndexingControllerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideInboxUnreadCountManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideInstantBookUpsellManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideInstantPromotionManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideKonaNavigationAnalyticsFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideLocalPushNotificationManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideLocationHelperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideLoggingContextFactoryFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideMemoryUtilsFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideMessageStoreFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideMessageStoreRequestFactoryFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideMessageStoreTableOpenHelperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideN2CallbacksFactory;
import com.airbnb.android.core.modules.CoreModule_ProvidePerformanceLoggerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvidePhoneNumberUtilFactory;
import com.airbnb.android.core.modules.CoreModule_ProvidePhoneUtilFactory;
import com.airbnb.android.core.modules.CoreModule_ProvidePromoControllerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideResourceManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideShakeFeedbackhelperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideSharedPrefsHelperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideSplashScreenControllerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideSyncRequestFactoryFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideThreadDataMapperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideTrebuchetControllerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideViewBreadcrumbManagerFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideViewedListingsDatabaseHelperFactory;
import com.airbnb.android.core.modules.CoreModule_ProvideWishListManagerFactory;
import com.airbnb.android.core.modules.DataModule;
import com.airbnb.android.core.modules.DataModule_ProvideConverterFactoryFactory;
import com.airbnb.android.core.modules.DataModule_ProvideObjectMapperFactory;
import com.airbnb.android.core.modules.ImageModule;
import com.airbnb.android.core.modules.ImageModule_ProvideImageUtilsFactory;
import com.airbnb.android.core.modules.InitModule;
import com.airbnb.android.core.modules.InitModule_ProvideJPushInitializerFactory;
import com.airbnb.android.core.modules.NetworkModule;
import com.airbnb.android.core.modules.NetworkModule_ProvideAirRequestInitializerFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideAirlockErrorHandlerFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideApiRequestHeadersInterceptorFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideApolloClientFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideApplicationInterceptorsFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideCacheFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideCallAdapterFactoryFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideEndpointFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideGeocoderRequestBaseUrlFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideGraphistClientFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideLowBandwidthUtilsFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideNetworkInterceptorsFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideNetworkMonitorFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideNetworkTimeProviderFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideObservableManagerFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideOkHttpClientFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideQueryParamsProviderFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideRequestCallbackExecutorFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideRequestHeadersFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideRestAdapterFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideRetrofitBuilderFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideSingleFireRequestExecutorFactory;
import com.airbnb.android.core.modules.NetworkModule_ProvideUrlMatcherFactory;
import com.airbnb.android.core.net.AirbnbApiUrlMatcher;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.net.ApplicationInterceptorsProvider;
import com.airbnb.android.core.net.LowBandwidthManager;
import com.airbnb.android.core.net.NetworkInterceptorsProvider;
import com.airbnb.android.core.net.NetworkMonitor;
import com.airbnb.android.core.persist.DomainStore;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.AirBatchRequestObserver;
import com.airbnb.android.core.requests.AirBatchRequestObserver_MembersInjector;
import com.airbnb.android.core.requests.AirBatchRequest_MembersInjector;
import com.airbnb.android.core.requests.AuthorizeServiceRequest;
import com.airbnb.android.core.requests.AuthorizeServiceRequest_MembersInjector;
import com.airbnb.android.core.requests.CountriesRequest;
import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.requests.CurrenciesRequest_MembersInjector;
import com.airbnb.android.core.requests.DeleteOauthTokenRequest;
import com.airbnb.android.core.requests.DeleteOauthTokenRequest_MembersInjector;
import com.airbnb.android.core.requests.GetActiveAccountRequest;
import com.airbnb.android.core.requests.GetActiveAccountRequest_MembersInjector;
import com.airbnb.android.core.requests.SetProfilePhotoRequest;
import com.airbnb.android.core.requests.SetProfilePhotoRequest_MembersInjector;
import com.airbnb.android.core.requests.UpdateThreadRequest;
import com.airbnb.android.core.requests.base.AirRequestHeadersInterceptor;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.requests.base.ApiRequestQueryParamsInterceptor;
import com.airbnb.android.core.security.ThreatMetrixClient;
import com.airbnb.android.core.services.NetworkTimeProvider;
import com.airbnb.android.core.services.OldManageListingPhotoUploadService;
import com.airbnb.android.core.services.OldManageListingPhotoUploadService_MembersInjector;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.PushNotificationBuilder_MembersInjector;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.AirCookieManager;
import com.airbnb.android.core.utils.AirCookieManager_MembersInjector;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.ClientSessionManager;
import com.airbnb.android.core.utils.ClientSessionValidator;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.GCMHelper;
import com.airbnb.android.core.utils.GCMHelper_MembersInjector;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.InstantBookUpsellManager_MembersInjector;
import com.airbnb.android.core.utils.JPushHelper;
import com.airbnb.android.core.utils.JPushHelper_MembersInjector;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.geocoder.GeocoderBaseUrl;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.AirWebView_MembersInjector;
import com.airbnb.android.core.views.AnimatedDrawableView;
import com.airbnb.android.core.views.AnimatedDrawableView_MembersInjector;
import com.airbnb.android.core.views.ListingsTray;
import com.airbnb.android.core.views.ListingsTray_MembersInjector;
import com.airbnb.android.core.views.LoopingViewPager;
import com.airbnb.android.core.views.LoopingViewPager_MembersInjector;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.PhoneNumberInputSheet_MembersInjector;
import com.airbnb.android.core.views.calendar.VerticalCalendarAdapter;
import com.airbnb.android.core.views.calendar.VerticalCalendarAdapter_MembersInjector;
import com.airbnb.android.core.wishlists.WishListHeartController;
import com.airbnb.android.core.wishlists.WishListHeartController_MembersInjector;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.explore.ExploreComponent;
import com.airbnb.android.explore.ExploreModule;
import com.airbnb.android.explore.ExploreModule_ProvideExploreDataRepositoryFactory;
import com.airbnb.android.explore.ExploreModule_ProvideExplorePerformanceAnalyticsFactory;
import com.airbnb.android.explore.adapters.BaseExploreAdapter;
import com.airbnb.android.explore.adapters.BaseExploreAdapter_MembersInjector;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreDataRepository;
import com.airbnb.android.explore.controllers.ExplorePerformanceAnalytics;
import com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment;
import com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment_MembersInjector;
import com.airbnb.android.explore.fragments.MTExploreFragment;
import com.airbnb.android.explore.fragments.MTExploreFragment_MembersInjector;
import com.airbnb.android.explore.fragments.MTExploreParentFragment;
import com.airbnb.android.explore.fragments.MTExploreParentFragment_MembersInjector;
import com.airbnb.android.explore.fragments.MTHomesTabFragment;
import com.airbnb.android.explore.fragments.MTHomesTabFragment_MembersInjector;
import com.airbnb.android.explore.fragments.MTLocationChinaFragment;
import com.airbnb.android.explore.fragments.MTLocationChinaFragment_MembersInjector;
import com.airbnb.android.explore.fragments.MTLocationFragment;
import com.airbnb.android.explore.fragments.MTLocationFragment_MembersInjector;
import com.airbnb.android.explore.fragments.MTTabFragment;
import com.airbnb.android.explore.fragments.MTTabFragment_MembersInjector;
import com.airbnb.android.explore.views.ExplorePriceHistogramRow;
import com.airbnb.android.guestrecovery.GuestRecoveryComponent;
import com.airbnb.android.guestrecovery.GuestRecoveryModule_ProvideGuestRecoveryLoggerFactory;
import com.airbnb.android.guestrecovery.fragments.GuestRecoveryFragment;
import com.airbnb.android.guestrecovery.fragments.GuestRecoveryFragment_MembersInjector;
import com.airbnb.android.guestrecovery.logging.GuestRecoveryLogger;
import com.airbnb.android.host_referrals.HostReferralsModule;
import com.airbnb.android.host_referrals.HostReferralsModule_ProvideHostReferralsLoggerFactory;
import com.airbnb.android.host_referrals.fragments.HostReferralsFragment;
import com.airbnb.android.host_referrals.fragments.HostReferralsFragment_MembersInjector;
import com.airbnb.android.host_referrals.logging.HostReferralLogger;
import com.airbnb.android.hostcalendar.adapters.CalendarAgendaAdapter;
import com.airbnb.android.hostcalendar.adapters.CalendarAgendaAdapter_MembersInjector;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter_MembersInjector;
import com.airbnb.android.hostcalendar.fragments.AgendaCalendarFragment;
import com.airbnb.android.hostcalendar.fragments.AgendaCalendarFragment_MembersInjector;
import com.airbnb.android.hostcalendar.fragments.CalendarFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarFragment_MembersInjector;
import com.airbnb.android.hostcalendar.fragments.CalendarNestedBusyDayFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarNestedBusyDayFragment_MembersInjector;
import com.airbnb.android.hostcalendar.fragments.CalendarUpdateNotesFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarUpdateNotesFragment_MembersInjector;
import com.airbnb.android.hostcalendar.fragments.CalendarWithPriceTipsUpdateFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarWithPriceTipsUpdateFragment_MembersInjector;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarBaseFragment;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarBaseFragment_MembersInjector;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragment;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragment_MembersInjector;
import com.airbnb.android.identity.AccountVerificationActivity;
import com.airbnb.android.identity.AccountVerificationActivity_MembersInjector;
import com.airbnb.android.identity.AccountVerificationPhoneNumberConfirmationFragment;
import com.airbnb.android.identity.AccountVerificationPhoneNumberInputFragment;
import com.airbnb.android.identity.AccountVerificationPhoneNumberInputFragment_MembersInjector;
import com.airbnb.android.identity.AccountVerificationProfilePhotoFragment;
import com.airbnb.android.identity.AccountVerificationProfilePhotoFragment_MembersInjector;
import com.airbnb.android.identity.AccountVerificationSelfieConfirmFragment;
import com.airbnb.android.identity.AccountVerificationSelfieConfirmFragment_MembersInjector;
import com.airbnb.android.identity.AccountVerificationSelfieFragment;
import com.airbnb.android.identity.AccountVerificationSelfieFragment_MembersInjector;
import com.airbnb.android.identity.AccountVerificationStartActivity;
import com.airbnb.android.identity.AccountVerificationStartActivity_MembersInjector;
import com.airbnb.android.identity.AirbnbTakeSelfieActivity;
import com.airbnb.android.identity.AirbnbTakeSelfieActivity_MembersInjector;
import com.airbnb.android.identity.C6488x2d7e29c2;
import com.airbnb.android.identity.IdentityComponent;
import com.airbnb.android.identity.IdentitySelfieCaptureFragment;
import com.airbnb.android.identity.IdentitySelfieCaptureFragment_MembersInjector;
import com.airbnb.android.insights.InsightsDataController;
import com.airbnb.android.insights.InsightsDataController_MembersInjector;
import com.airbnb.android.insights.fragments.details.InsightsNightlyPriceFragment;
import com.airbnb.android.insights.fragments.details.InsightsNightlyPriceFragment_MembersInjector;
import com.airbnb.android.internal.InternalModule;
import com.airbnb.android.internal.InternalModule_ProvideDebugNotificationControllerFactory;
import com.airbnb.android.internal.bugreporter.DebugNotificationController;
import com.airbnb.android.internal.bugreporter.InternalBugReportFragment;
import com.airbnb.android.internal.bugreporter.InternalBugReportFragment_MembersInjector;
import com.airbnb.android.itinerary.ItineraryModule;
import com.airbnb.android.itinerary.ItineraryModule_ProvideItineraryManagerFactory;
import com.airbnb.android.itinerary.ItineraryModule_ProvideItineraryTableOpenHelperFactory;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import com.airbnb.android.itinerary.fragments.ItineraryParentFragment;
import com.airbnb.android.itinerary.fragments.ItineraryParentFragment_MembersInjector;
import com.airbnb.android.lib.activities.DebugMenuActivity;
import com.airbnb.android.lib.activities.DebugMenuActivity_MembersInjector;
import com.airbnb.android.lib.activities.EntryActivity;
import com.airbnb.android.lib.activities.EntryActivity_MembersInjector;
import com.airbnb.android.lib.activities.HomeActivity;
import com.airbnb.android.lib.activities.HomeActivity_MembersInjector;
import com.airbnb.android.lib.activities.InboxActivity;
import com.airbnb.android.lib.activities.InboxActivity_MembersInjector;
import com.airbnb.android.lib.activities.PayWithAlipayActivity;
import com.airbnb.android.lib.activities.PayWithAlipayActivity_MembersInjector;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.lib.activities.ReservationResponseActivity_MembersInjector;
import com.airbnb.android.lib.activities.SearchIntentActivity;
import com.airbnb.android.lib.activities.SearchIntentActivity_MembersInjector;
import com.airbnb.android.lib.activities.SpecialOfferActivity;
import com.airbnb.android.lib.activities.SpecialOfferActivity_MembersInjector;
import com.airbnb.android.lib.activities.SplashScreenActivity;
import com.airbnb.android.lib.activities.SplashScreenActivity_MembersInjector;
import com.airbnb.android.lib.activities.TrebuchetOverrideActivity;
import com.airbnb.android.lib.activities.TrebuchetOverrideActivity_MembersInjector;
import com.airbnb.android.lib.activities.UserProfileActivity;
import com.airbnb.android.lib.activities.UserProfileActivity_MembersInjector;
import com.airbnb.android.lib.activities.booking.BookingActivity;
import com.airbnb.android.lib.activities.booking.BookingActivity_MembersInjector;
import com.airbnb.android.lib.activities.booking.BookingV2Activity;
import com.airbnb.android.lib.activities.booking.BookingV2Activity_MembersInjector;
import com.airbnb.android.lib.adapters.EditProfileDetailsAdapter;
import com.airbnb.android.lib.adapters.EditProfileDetailsAdapter_MembersInjector;
import com.airbnb.android.lib.adapters.HHBaseAdapter;
import com.airbnb.android.lib.adapters.HHBaseAdapter_MembersInjector;
import com.airbnb.android.lib.adapters.HostReservationObjectAdapter;
import com.airbnb.android.lib.adapters.HostReservationObjectAdapter_MembersInjector;
import com.airbnb.android.lib.adapters.ReservationObjectAdapter;
import com.airbnb.android.lib.adapters.ReservationObjectAdapter_MembersInjector;
import com.airbnb.android.lib.adapters.SearchCalendarAdapter;
import com.airbnb.android.lib.adapters.ThreadAdapter;
import com.airbnb.android.lib.adapters.ThreadAdapter_MembersInjector;
import com.airbnb.android.lib.adapters.settings.AdvancedSettingsEpoxyController;
import com.airbnb.android.lib.adapters.settings.AdvancedSettingsEpoxyController_MembersInjector;
import com.airbnb.android.lib.businesstravel.BusinessTravelInterstitialFragment;
import com.airbnb.android.lib.businesstravel.BusinessTravelInterstitialFragment_MembersInjector;
import com.airbnb.android.lib.businesstravel.BusinessTravelWelcomeFragment;
import com.airbnb.android.lib.businesstravel.BusinessTravelWelcomeFragment_MembersInjector;
import com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment;
import com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment_MembersInjector;
import com.airbnb.android.lib.businesstravel.WorkEmailActivity;
import com.airbnb.android.lib.businesstravel.WorkEmailActivity_MembersInjector;
import com.airbnb.android.lib.businesstravel.WorkEmailFragment;
import com.airbnb.android.lib.businesstravel.WorkEmailFragment_MembersInjector;
import com.airbnb.android.lib.china5a.fragments.PhoneVerificationFragment_MembersInjector;
import com.airbnb.android.lib.china5a.photo.PhotoVerificationPresenter;
import com.airbnb.android.lib.china5a.photo.PhotoVerificationPresenter_MembersInjector;
import com.airbnb.android.lib.coldstart.PreloadExecutor;
import com.airbnb.android.lib.contentproviders.HostHomeWidgetProvider;
import com.airbnb.android.lib.contentproviders.HostHomeWidgetProvider_MembersInjector;
import com.airbnb.android.lib.fragments.AccountPageFragment;
import com.airbnb.android.lib.fragments.AccountPageFragment_MembersInjector;
import com.airbnb.android.lib.fragments.AccountSettingsFragment;
import com.airbnb.android.lib.fragments.AccountSettingsFragment_MembersInjector;
import com.airbnb.android.lib.fragments.AdvancedSettingsFragment;
import com.airbnb.android.lib.fragments.AdvancedSettingsFragment_MembersInjector;
import com.airbnb.android.lib.fragments.AppUpgradeDialogFragment;
import com.airbnb.android.lib.fragments.AppUpgradeDialogFragment_MembersInjector;
import com.airbnb.android.lib.fragments.DLSReservationObjectFragment;
import com.airbnb.android.lib.fragments.DLSReservationObjectFragment_MembersInjector;
import com.airbnb.android.lib.fragments.EditProfileFragment;
import com.airbnb.android.lib.fragments.EditProfileFragment_MembersInjector;
import com.airbnb.android.lib.fragments.EndpointSelectorDialogFragment.EndpointAdapter;
import com.airbnb.android.lib.fragments.EndpointSelectorDialogFragment_EndpointAdapter_MembersInjector;
import com.airbnb.android.lib.fragments.HostReservationObjectFragment;
import com.airbnb.android.lib.fragments.HostReservationObjectFragment_MembersInjector;
import com.airbnb.android.lib.fragments.InboxContainerFragment;
import com.airbnb.android.lib.fragments.InboxContainerFragment_MembersInjector;
import com.airbnb.android.lib.fragments.PayoutSelectFragment;
import com.airbnb.android.lib.fragments.PayoutSelectFragment_MembersInjector;
import com.airbnb.android.lib.fragments.PreapproveInquiryFragment;
import com.airbnb.android.lib.fragments.PreapproveInquiryFragment_MembersInjector;
import com.airbnb.android.lib.fragments.ReasonPickerFragment;
import com.airbnb.android.lib.fragments.ReasonPickerFragment_MembersInjector;
import com.airbnb.android.lib.fragments.RemovePreapprovalFragment;
import com.airbnb.android.lib.fragments.RemovePreapprovalFragment_MembersInjector;
import com.airbnb.android.lib.fragments.ReservationCanceledFragment;
import com.airbnb.android.lib.fragments.ReservationCanceledFragment_MembersInjector;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment_MembersInjector;
import com.airbnb.android.lib.fragments.ReservationPickerFragment;
import com.airbnb.android.lib.fragments.ReservationPickerFragment_MembersInjector;
import com.airbnb.android.lib.fragments.SearchSettingsFragment;
import com.airbnb.android.lib.fragments.SearchSettingsFragment_MembersInjector;
import com.airbnb.android.lib.fragments.ShakeFeedbackDialog;
import com.airbnb.android.lib.fragments.ShakeFeedbackDialog_MembersInjector;
import com.airbnb.android.lib.fragments.TOSDialogFragment;
import com.airbnb.android.lib.fragments.TOSDialogFragment_MembersInjector;
import com.airbnb.android.lib.fragments.ThreadFragment;
import com.airbnb.android.lib.fragments.ThreadFragment_MembersInjector;
import com.airbnb.android.lib.fragments.UserProfileFragment;
import com.airbnb.android.lib.fragments.UserProfileFragment_MembersInjector;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentCancelAccountFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentCancelAccountFragment_MembersInjector;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentFragment_MembersInjector;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentLearnMoreFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentLearnMoreFragment_MembersInjector;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentWriteFeedbackFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentWriteFeedbackFragment_MembersInjector;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneChildFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneChildFragment_MembersInjector;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneCodeChildFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneCodeChildFragment_MembersInjector;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhotoFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhotoFragment_MembersInjector;
import com.airbnb.android.lib.fragments.inbox.BottomBarBadgeInboxHandler;
import com.airbnb.android.lib.fragments.inbox.BottomBarBadgeInboxHandler_MembersInjector;
import com.airbnb.android.lib.fragments.inbox.InboxAdapter;
import com.airbnb.android.lib.fragments.inbox.InboxAdapter_MembersInjector;
import com.airbnb.android.lib.fragments.inbox.InboxFragment;
import com.airbnb.android.lib.fragments.inbox.InboxFragment_MembersInjector;
import com.airbnb.android.lib.fragments.inbox.saved_messages.CreateNewSavedMessageFragment;
import com.airbnb.android.lib.fragments.inbox.saved_messages.CreateNewSavedMessageFragment_MembersInjector;
import com.airbnb.android.lib.fragments.inbox.saved_messages.SavedMessagesFragment;
import com.airbnb.android.lib.fragments.inbox.saved_messages.SavedMessagesFragment_MembersInjector;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLandingFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLandingFragment_MembersInjector;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLogger;
import com.airbnb.android.lib.fragments.verifications.PhoneVerificationFragment;
import com.airbnb.android.lib.fragments.verifications.PhotoVerificationFragment;
import com.airbnb.android.lib.fragments.verifications.PhotoVerificationFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdCountryFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdErrorFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdErrorFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdTypeFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfflineIdChildFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfflineIdChildFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.OnlineIdChildFragment;
import com.airbnb.android.lib.fragments.verifiedid.OnlineIdChildFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.SesameVerificationChildFragment;
import com.airbnb.android.lib.fragments.verifiedid.SesameVerificationChildFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.SesameVerificationConnectFragment;
import com.airbnb.android.lib.fragments.verifiedid.SesameVerificationConnectFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdCompletedFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdCompletedFragment_MembersInjector;
import com.airbnb.android.lib.fragments.verifiedid.WelcomeScreenFragment;
import com.airbnb.android.lib.host.stats.HostDemandsDetailFragment;
import com.airbnb.android.lib.host.stats.HostDemandsDetailFragment_MembersInjector;
import com.airbnb.android.lib.host.stats.HostListingSelectorFragment;
import com.airbnb.android.lib.host.stats.HostListingSelectorFragment_MembersInjector;
import com.airbnb.android.lib.host.stats.HostReviewDetailAdapter;
import com.airbnb.android.lib.host.stats.HostReviewDetailAdapter_MembersInjector;
import com.airbnb.android.lib.host.stats.HostReviewDetailsFragment;
import com.airbnb.android.lib.host.stats.HostReviewDetailsFragment_MembersInjector;
import com.airbnb.android.lib.host.stats.HostStatsFragment;
import com.airbnb.android.lib.host.stats.HostStatsFragment_MembersInjector;
import com.airbnb.android.lib.identity.psb.CreateIdentificationActivity;
import com.airbnb.android.lib.identity.psb.CreateIdentificationActivity_MembersInjector;
import com.airbnb.android.lib.identity.psb.GuestIdentificationAdapter;
import com.airbnb.android.lib.identity.psb.GuestIdentificationAdapter_MembersInjector;
import com.airbnb.android.lib.identity.psb.IdentificationNameFragment;
import com.airbnb.android.lib.identity.psb.IdentificationNameFragment_MembersInjector;
import com.airbnb.android.lib.paidamenities.fragments.create.BaseCreateAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.BaseCreateAmenityFragment_MembersInjector;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment_MembersInjector;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment_MembersInjector;
import com.airbnb.android.lib.paidamenities.fragments.purchase.BasePurchaseAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.purchase.BasePurchaseAmenityFragment_MembersInjector;
import com.airbnb.android.lib.payments.activities.QuickPayActivity;
import com.airbnb.android.lib.payments.activities.QuickPayActivity_MembersInjector;
import com.airbnb.android.lib.payments.addpayment.fragments.AddPaymentMethodFragment;
import com.airbnb.android.lib.payments.addpayment.fragments.AddPaymentMethodFragment_MembersInjector;
import com.airbnb.android.lib.payments.addpayment.fragments.SelectBillingCountryFragment;
import com.airbnb.android.lib.payments.addpayment.fragments.SelectBillingCountryFragment_MembersInjector;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.creditcard.brazil.fragments.BrazilCreditCardDetailsFragment;
import com.airbnb.android.lib.payments.creditcard.brazil.fragments.BrazilCreditCardDetailsFragment_MembersInjector;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilPaymentInputFormatter;
import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import com.airbnb.android.lib.payments.dagger.QuickPayModule;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideBraintreeFactoryFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideBrazilPaymentInputFormatterFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideCreditCardValidatorFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideDigitalRiverApiFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideIdentityClientFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvidePaymentOptionFactoryFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvidePaymentOptionsAdapterFactoryFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvidePaymentRedirectCoordinatorFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideQuickPayAdapterFactoryFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideQuickPayRequestsFactoryFactory;
import com.airbnb.android.lib.payments.dagger.QuickPayModule_ProvideQuickPayRowFactoryFactory;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverApi;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestFactory;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.lib.payments.fragments.AddCouponCodeFragment;
import com.airbnb.android.lib.payments.fragments.AddCouponCodeFragment_MembersInjector;
import com.airbnb.android.lib.payments.fragments.CreditCardDetailsFragment;
import com.airbnb.android.lib.payments.fragments.CreditCardDetailsFragment_MembersInjector;
import com.airbnb.android.lib.payments.fragments.PaymentOptionsFragment;
import com.airbnb.android.lib.payments.fragments.PaymentOptionsFragment_MembersInjector;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapterFactory;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayAdapterFactory;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayRowFactory;
import com.airbnb.android.lib.payments.quickpay.fragments.HomesQuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.HomesQuickPayFragment_MembersInjector;
import com.airbnb.android.lib.payments.quickpay.fragments.QuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.QuickPayFragment_MembersInjector;
import com.airbnb.android.lib.payments.quickpay.paymentredirect.PaymentRedirectCoordinator;
import com.airbnb.android.lib.postbooking.PostBookingActivity;
import com.airbnb.android.lib.postbooking.PostBookingActivity_MembersInjector;
import com.airbnb.android.lib.postbooking.PostBookingBusinessTravelPromoFragment;
import com.airbnb.android.lib.postbooking.PostBookingBusinessTravelPromoFragment_MembersInjector;
import com.airbnb.android.lib.receivers.AppUpgradeReceiver;
import com.airbnb.android.lib.receivers.AppUpgradeReceiver_MembersInjector;
import com.airbnb.android.lib.receivers.LocaleChangedReceiver;
import com.airbnb.android.lib.receivers.LocaleChangedReceiver_MembersInjector;
import com.airbnb.android.lib.receivers.WifiAlarmReceiver;
import com.airbnb.android.lib.receivers.WifiAlarmReceiver_MembersInjector;
import com.airbnb.android.lib.reservationresponse.AcceptReservationConfirmationFragment;
import com.airbnb.android.lib.reservationresponse.AcceptReservationConfirmationFragment_MembersInjector;
import com.airbnb.android.lib.reservationresponse.AcceptReservationFragment;
import com.airbnb.android.lib.reservationresponse.AcceptReservationFragment_MembersInjector;
import com.airbnb.android.lib.reviews.fragments.FeedbackIntroFragment;
import com.airbnb.android.lib.reviews.fragments.FeedbackIntroFragment_MembersInjector;
import com.airbnb.android.lib.reviews.fragments.FeedbackSummaryFragment;
import com.airbnb.android.lib.reviews.fragments.FeedbackSummaryFragment_MembersInjector;
import com.airbnb.android.lib.services.HHListRemoteViewsFactory;
import com.airbnb.android.lib.services.HHListRemoteViewsFactory_MembersInjector;
import com.airbnb.android.lib.services.OfficialIdIntentService;
import com.airbnb.android.lib.services.OfficialIdIntentService_MembersInjector;
import com.airbnb.android.lib.services.PushIntentService;
import com.airbnb.android.lib.services.PushIntentService_MembersInjector;
import com.airbnb.android.lib.services.TripsReservationsSyncService;
import com.airbnb.android.lib.services.TripsReservationsSyncService_MembersInjector;
import com.airbnb.android.lib.services.ViewedListingsPersistenceService;
import com.airbnb.android.lib.services.ViewedListingsPersistenceService_MembersInjector;
import com.airbnb.android.lib.services.push_notifications.MessagePushNotification;
import com.airbnb.android.lib.tasks.LocalBitmapForDisplayScalingTask;
import com.airbnb.android.lib.tasks.LocalBitmapForDisplayScalingTask_MembersInjector;
import com.airbnb.android.lib.tripassistant.HelpThreadDialogActivity;
import com.airbnb.android.lib.tripassistant.HelpThreadDialogActivity_MembersInjector;
import com.airbnb.android.lib.tripassistant.HelpThreadFragment;
import com.airbnb.android.lib.tripassistant.HelpThreadFragment_MembersInjector;
import com.airbnb.android.lib.utils.erf.ErfOverrideActivity;
import com.airbnb.android.lib.utils.erf.ErfOverrideActivity_MembersInjector;
import com.airbnb.android.lib.utils.webintent.WebIntentDispatch;
import com.airbnb.android.lib.utils.webintent.WebIntentDispatch_MembersInjector;
import com.airbnb.android.lib.utils.webintent.WebIntentMatcherModule;
import com.airbnb.android.lib.utils.webintent.WebIntentMatcherModule_ProvideWebIntentMatcherFactory;
import com.airbnb.android.lib.views.DateAndGuestCountView;
import com.airbnb.android.lib.views.EditableCell;
import com.airbnb.android.lib.views.EditableCell_MembersInjector;
import com.airbnb.android.lib.views.EmptyResultsCardView;
import com.airbnb.android.lib.views.EmptyResultsCardView_MembersInjector;
import com.airbnb.android.lib.views.PriceGroupedCell;
import com.airbnb.android.lib.views.PriceGroupedCell_MembersInjector;
import com.airbnb.android.lib.views.PricingQuotePricingDetails;
import com.airbnb.android.lib.views.PricingQuotePricingDetails_MembersInjector;
import com.airbnb.android.lib.wishlists.WLDetailsDeeplinkInterceptorActivity;
import com.airbnb.android.lib.wishlists.WLDetailsDeeplinkInterceptorActivity_MembersInjector;
import com.airbnb.android.listing.ListingModule;
import com.airbnb.android.listing.ListingModule_PhotoUploadManagerFactory;
import com.airbnb.android.listing.ListingModule_ProvideAddressAutoCompleteLoggerFactory;
import com.airbnb.android.listing.ListingModule_ProvideWhatsMyPlaceWorthLoggerFactory;
import com.airbnb.android.listing.adapters.BasePriceAdapter;
import com.airbnb.android.listing.adapters.BasePriceAdapter_MembersInjector;
import com.airbnb.android.listing.adapters.LongTermDiscountsAdapter;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter;
import com.airbnb.android.listing.adapters.NightlyPriceAdapter_MembersInjector;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment_MembersInjector;
import com.airbnb.android.listing.fragments.WhatsMyPlaceWorthFragment;
import com.airbnb.android.listing.fragments.WhatsMyPlaceWorthFragment_MembersInjector;
import com.airbnb.android.listing.logging.LYSAddressAutoCompleteLogger;
import com.airbnb.android.listing.logging.WhatsMyPlaceWorthLogger;
import com.airbnb.android.listyourspacedls.LYSDataController;
import com.airbnb.android.listyourspacedls.LYSDataController_MembersInjector;
import com.airbnb.android.listyourspacedls.LYSHomeActivity;
import com.airbnb.android.listyourspacedls.LYSHomeActivity_MembersInjector;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSModule;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSModule_LysJitneyLoggerFactory;
import com.airbnb.android.listyourspacedls.fragments.LYSAddressFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSAddressFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSBasePriceFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSBasePriceFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSCalendarFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCalendarFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSCharacterCountMarqueeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCharacterCountMarqueeFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSDiscountsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSDiscountsFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSGuestBookFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSGuestBookFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSHostingFrequencyFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSHostingFrequencyFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSLandingFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSLandingFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSLocalLawsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSLocalLawsFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoDetailFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoDetailFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoManagerFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoManagerFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoStartFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoStartFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSPublishFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPublishFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSRTBChecklistFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRTBChecklistFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSRentHistoryFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRentHistoryFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSRoomsAndGuestsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRoomsAndGuestsFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSSelectPricingTypeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSelectPricingTypeFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSSmartPricingFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSmartPricingFragment_MembersInjector;
import com.airbnb.android.listyourspacedls.fragments.LYSSpaceTypeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSpaceTypeFragment_MembersInjector;
import com.airbnb.android.login.LoginComponent;
import com.airbnb.android.login.p339ui.LoginActivity;
import com.airbnb.android.login.p339ui.LoginActivity_MembersInjector;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordConfirmSMSCodeFragment;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordConfirmSMSCodeFragment_MembersInjector;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordFragment;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordFragment_MembersInjector;
import com.airbnb.android.login.requests.UserLoginRequest;
import com.airbnb.android.login.requests.UserLoginRequest_MembersInjector;
import com.airbnb.android.managelisting.ManageListingModule;
import com.airbnb.android.managelisting.ManageListingModule_CheckInJitneyLoggerFactory;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;
import com.airbnb.android.managelisting.picker.ManageListingPickerFragment;
import com.airbnb.android.managelisting.picker.ManageListingPickerFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.CheckinNoteTextSettingFragment;
import com.airbnb.android.managelisting.settings.CheckinNoteTextSettingFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.DlsManageListingActivity;
import com.airbnb.android.managelisting.settings.DlsManageListingActivity_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingAllCheckinMethodsFragment;
import com.airbnb.android.managelisting.settings.ManageListingAllCheckinMethodsFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingBookingsAdapter;
import com.airbnb.android.managelisting.settings.ManageListingBookingsAdapter_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingCheckInGuideFragment;
import com.airbnb.android.managelisting.settings.ManageListingCheckInGuideFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingCheckinTypeTextSettingFragment;
import com.airbnb.android.managelisting.settings.ManageListingCheckinTypeTextSettingFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingDataController;
import com.airbnb.android.managelisting.settings.ManageListingDataController_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingDetailsEpoxyController;
import com.airbnb.android.managelisting.settings.ManageListingDetailsEpoxyController_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingDiscountsSettingsFragment;
import com.airbnb.android.managelisting.settings.ManageListingDiscountsSettingsFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingEarlyBirdDiscountFragment;
import com.airbnb.android.managelisting.settings.ManageListingEarlyBirdDiscountFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingLastMinuteDiscountFragment;
import com.airbnb.android.managelisting.settings.ManageListingLastMinuteDiscountFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingLocalLawsFragment;
import com.airbnb.android.managelisting.settings.ManageListingLocalLawsFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingNightlyPriceSettingsFragment;
import com.airbnb.android.managelisting.settings.ManageListingNightlyPriceSettingsFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.ManageListingSettingsTabFragment;
import com.airbnb.android.managelisting.settings.ManageListingSettingsTabFragment_MembersInjector;
import com.airbnb.android.managelisting.settings.photos.ManagePhotosFragment;
import com.airbnb.android.managelisting.settings.photos.ManagePhotosFragment_MembersInjector;
import com.airbnb.android.misnap.MiSnapIdentityCaptureActivity;
import com.airbnb.android.misnap.MiSnapIdentityCaptureActivity_MembersInjector;
import com.airbnb.android.misnap.MiSnapTakeSelfieActivity;
import com.airbnb.android.misnap.MiSnapTakeSelfieActivity_MembersInjector;
import com.airbnb.android.misnap.MisnapModule;
import com.airbnb.android.misnap.MisnapModule_ProvidePhotoCompressorFactory;
import com.airbnb.android.misnap.MisnapModule_ProvideTakeSelfieControllerFactory;
import com.airbnb.android.misnap.TakeSelfieController;
import com.airbnb.android.p011p3.P3Activity;
import com.airbnb.android.p011p3.P3Activity_MembersInjector;
import com.airbnb.android.p011p3.P3AdditionalPriceFragment;
import com.airbnb.android.p011p3.P3AdditionalPriceFragment_MembersInjector;
import com.airbnb.android.p011p3.P3Component;
import com.airbnb.android.p011p3.P3Fragment;
import com.airbnb.android.p011p3.P3Fragment_MembersInjector;
import com.airbnb.android.p011p3.P3Module;
import com.airbnb.android.p011p3.P3ReviewFragment;
import com.airbnb.android.p011p3.P3ReviewFragment_MembersInjector;
import com.airbnb.android.p011p3.P3ReviewSearchFragment;
import com.airbnb.android.p011p3.P3ReviewSearchFragment_MembersInjector;
import com.airbnb.android.payout.PayoutModule;
import com.airbnb.android.payout.PayoutModule_AddPayoutMethodJitneyLoggerFactory;
import com.airbnb.android.payout.create.PayoutRedirectWebviewActivity;
import com.airbnb.android.payout.create.PayoutRedirectWebviewActivity_MembersInjector;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController_MembersInjector;
import com.airbnb.android.payout.create.fragments.BaseAddPayoutMethodFragment;
import com.airbnb.android.payout.create.fragments.BaseAddPayoutMethodFragment_MembersInjector;
import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;
import com.airbnb.android.payout.manage.EditPayoutFragment;
import com.airbnb.android.payout.manage.EditPayoutFragment_MembersInjector;
import com.airbnb.android.payout.manage.SelectPayoutCountryActivity;
import com.airbnb.android.payout.manage.SelectPayoutCountryActivity_MembersInjector;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.android.photouploadmanager.PhotoUploadRetryBroadcastReceiver;
import com.airbnb.android.photouploadmanager.PhotoUploadRetryBroadcastReceiver_MembersInjector;
import com.airbnb.android.photouploadmanager.PhotoUploadService;
import com.airbnb.android.photouploadmanager.PhotoUploadService_MembersInjector;
import com.airbnb.android.pickwishlist.CreateWishListActivity;
import com.airbnb.android.pickwishlist.CreateWishListActivity_MembersInjector;
import com.airbnb.android.pickwishlist.PickWishListComponent;
import com.airbnb.android.pickwishlist.PickWishListFragment;
import com.airbnb.android.pickwishlist.PickWishListFragment_MembersInjector;
import com.airbnb.android.places.ResyController;
import com.airbnb.android.places.ResyController_MembersInjector;
import com.airbnb.android.profile_completion.ProfileCompletionActivity;
import com.airbnb.android.profile_completion.ProfileCompletionActivity_MembersInjector;
import com.airbnb.android.profile_completion.ProfileCompletionManager;
import com.airbnb.android.profile_completion.ProfileCompletionModule;
import com.airbnb.android.profile_completion.ProfileCompletionModule_ProfileCompletionJitneyLoggerFactory;
import com.airbnb.android.profile_completion.ProfileCompletionModule_ProfileCompletionManagerFactory;
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;
import com.airbnb.android.react.ReactDeepLinkParser;
import com.airbnb.android.react.ReactDeepLinkRegistry;
import com.airbnb.android.react.ReactModule;
import com.airbnb.android.react.ReactModule_ProvideReactCookieJarContainerFactory;
import com.airbnb.android.react.ReactModule_ProvideReactDeepLinkParserFactory;
import com.airbnb.android.react.ReactModule_ProvideReactDeepLinkRegistryFactory;
import com.airbnb.android.react.ReactModule_ProvideReactInstanceManagerFactory;
import com.airbnb.android.react.ReactModule_ProvideReactNativeCoordinatorFactory;
import com.airbnb.android.react.ReactModule_ProvideReactNativeModuleFactoryFactory;
import com.airbnb.android.react.ReactNativeActivity;
import com.airbnb.android.react.ReactNativeActivity_MembersInjector;
import com.airbnb.android.react.ReactNativeFragment;
import com.airbnb.android.react.ReactNativeFragment_MembersInjector;
import com.airbnb.android.react.ReactNativeModulesProvider;
import com.airbnb.android.react.ReactNavigationCoordinator;
import com.airbnb.android.referrals.ReferralsComponent;
import com.airbnb.android.referrals.ReferralsFragment;
import com.airbnb.android.referrals.ReferralsFragment_MembersInjector;
import com.airbnb.android.referrals.ReferralsOneClickFragment;
import com.airbnb.android.referrals.ReferralsOneClickFragment_MembersInjector;
import com.airbnb.android.referrals.SentReferralsFragment;
import com.airbnb.android.referrals.SentReferralsFragment_MembersInjector;
import com.airbnb.android.referrals.rolodex.ContactUploadIntentService;
import com.airbnb.android.referrals.rolodex.ContactUploadIntentService_MembersInjector;
import com.airbnb.android.registration.PhoneNumberRegistrationConfirmationFragment;
import com.airbnb.android.registration.PhoneNumberRegistrationConfirmationFragment_MembersInjector;
import com.airbnb.android.registration.PhoneNumberRegistrationFragment;
import com.airbnb.android.registration.PhoneNumberRegistrationFragment_MembersInjector;
import com.airbnb.android.registration.RegistrationComponent;
import com.airbnb.android.sharing.referral.SharingManager;
import com.airbnb.android.sharing.referral.SharingManager_MembersInjector;
import com.airbnb.android.sharing.shareables.Shareable;
import com.airbnb.android.sharing.shareables.Shareable_MembersInjector;
import com.airbnb.android.sms.SMSMonitor;
import com.airbnb.android.sms.SMSMonitorModule;
import com.airbnb.android.sms.SMSMonitorModule_ProvideSMSMonitorFactory;
import com.airbnb.android.superhero.SuperHeroAlarmReceiver;
import com.airbnb.android.superhero.SuperHeroAlarmReceiver_MembersInjector;
import com.airbnb.android.superhero.SuperHeroModule;
import com.airbnb.android.superhero.SuperHeroModule_ProvideSuperHeroManagerFactory;
import com.airbnb.android.superhero.SuperHeroModule_ProvideSuperHeroTableOpenHelperFactory;
import com.airbnb.android.superhero.SuperHeroTableOpenHelper;
import com.airbnb.android.superhero.SuperHeroThreadFragment;
import com.airbnb.android.superhero.SuperHeroThreadFragment_MembersInjector;
import com.airbnb.android.wishlists.BaseWishListDetailsFragment;
import com.airbnb.android.wishlists.BaseWishListDetailsFragment_MembersInjector;
import com.airbnb.android.wishlists.WishListDetailsParentFragment;
import com.airbnb.android.wishlists.WishListDetailsParentFragment_MembersInjector;
import com.airbnb.android.wishlists.WishListIndexFragment;
import com.airbnb.android.wishlists.WishListIndexFragment_MembersInjector;
import com.airbnb.android.wishlists.WishListsFragment;
import com.airbnb.android.wishlists.WishListsFragment_MembersInjector;
import com.airbnb.erf.Erf;
import com.airbnb.p027n2.C0977N2;
import com.airbnb.p027n2.C0977N2.Callbacks;
import com.airbnb.p027n2.N2Component;
import com.airbnb.p027n2.N2Context;
import com.airbnb.p027n2.N2Module;
import com.airbnb.p027n2.N2Module_ProvideN2ContextFactory;
import com.airbnb.p027n2.N2Module_ProvideN2Factory;
import com.airbnb.p027n2.N2Module_ProvideVideoCacheFactory;
import com.airbnb.p027n2.internal.ComponentManager;
import com.airbnb.p027n2.primitives.AirAutoCompleteTextView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.rxgroups.ObservableManager;
import com.apollographql.apollo.ApolloClient;
import com.danikula.videocache.HttpProxyCacheServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.analytics.Tracker;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.squareup.otto.Bus;
import com.threatmetrix.TrustDefender.Config;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import dagger.internal.SetFactory;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import p030in.uncod.android.bypass.Bypass;
import retrofit2.CallAdapter.Factory;
import retrofit2.Retrofit;

public final class DaggerAirbnbComponent implements AirbnbComponent {
    static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
    private MembersInjector<AcceptCohostInvitationActivity> acceptCohostInvitationActivityMembersInjector;
    private MembersInjector<AcceptCohostInvitationFragment> acceptCohostInvitationFragmentMembersInjector;
    private MembersInjector<AcceptReservationConfirmationFragment> acceptReservationConfirmationFragmentMembersInjector;
    private MembersInjector<AcceptReservationFragment> acceptReservationFragmentMembersInjector;
    private MembersInjector<AccountPageFragment> accountPageFragmentMembersInjector;
    private MembersInjector<AccountSettingsFragment> accountSettingsFragmentMembersInjector;
    private MembersInjector<AccountVerificationActivity> accountVerificationActivityMembersInjector;
    private MembersInjector<AccountVerificationProfilePhotoFragment> accountVerificationProfilePhotoFragmentMembersInjector;
    private MembersInjector<AccountVerificationSelfieConfirmFragment> accountVerificationSelfieConfirmFragmentMembersInjector;
    private MembersInjector<AccountVerificationSelfieFragment> accountVerificationSelfieFragmentMembersInjector;
    private MembersInjector<AccountVerificationStartActivity> accountVerificationStartActivityMembersInjector;
    private MembersInjector<AddCouponCodeFragment> addCouponCodeFragmentMembersInjector;
    private MembersInjector<AddPaymentMethodFragment> addPaymentMethodFragmentMembersInjector;
    private MembersInjector<AddPayoutMethodDataController> addPayoutMethodDataControllerMembersInjector;
    private Provider<AddPayoutMethodJitneyLogger> addPayoutMethodJitneyLoggerProvider;
    private MembersInjector<AddressAutoCompleteFragment> addressAutoCompleteFragmentMembersInjector;
    private MembersInjector<AdvancedSettingsEpoxyController> advancedSettingsEpoxyControllerMembersInjector;
    private MembersInjector<AdvancedSettingsFragment> advancedSettingsFragmentMembersInjector;
    private Provider<AffiliateInfo> affiliateInfoProvider;
    private MembersInjector<AgendaCalendarFragment> agendaCalendarFragmentMembersInjector;
    private MembersInjector<AirActivity> airActivityMembersInjector;
    private MembersInjector<AirBatchRequest> airBatchRequestMembersInjector;
    private MembersInjector<AirBatchRequestObserver> airBatchRequestObserverMembersInjector;
    private MembersInjector<AirCookieManager> airCookieManagerMembersInjector;
    private MembersInjector<AirDialogFragment> airDialogFragmentMembersInjector;
    private MembersInjector<AirFragment> airFragmentMembersInjector;
    private MembersInjector<AirWebView> airWebViewMembersInjector;
    private MembersInjector<AirbnbApplication> airbnbApplicationMembersInjector;
    private MembersInjector<AirbnbTakeSelfieActivity> airbnbTakeSelfieActivityMembersInjector;
    private MembersInjector<AnimatedDrawableView> animatedDrawableViewMembersInjector;
    private Provider<com.airbnb.android.lib.AppRaterComponent.Builder> appRaterComponentBuilderProvider;
    /* access modifiers changed from: private */
    public final AppRaterModule appRaterModule;
    private MembersInjector<AppUpgradeDialogFragment> appUpgradeDialogFragmentMembersInjector;
    private MembersInjector<AppUpgradeReceiver> appUpgradeReceiverMembersInjector;
    private MembersInjector<AuthorizeServiceRequest> authorizeServiceRequestMembersInjector;
    private MembersInjector<AuthorizedAccountHelper> authorizedAccountHelperMembersInjector;
    private MembersInjector<BaseAddPayoutMethodFragment> baseAddPayoutMethodFragmentMembersInjector;
    private MembersInjector<BaseAlipayFragment> baseAlipayFragmentMembersInjector;
    private MembersInjector<BaseCreateAmenityFragment> baseCreateAmenityFragmentMembersInjector;
    private MembersInjector<BasePendingAmenityFragment> basePendingAmenityFragmentMembersInjector;
    private MembersInjector<BasePriceAdapter> basePriceAdapterMembersInjector;
    private MembersInjector<BasePurchaseAmenityFragment> basePurchaseAmenityFragmentMembersInjector;
    private MembersInjector<BaseWishListDetailsFragment> baseWishListDetailsFragmentMembersInjector;
    private Provider<PostApplicationCreatedInitializer> bindDebugNotificationControllerProvider;
    private Provider<PostApplicationCreatedInitializer> bindJPushInitializerProvider;
    private Provider<PostApplicationCreatedInitializer> bindMessageStoreRequestFactoryProvider;
    private Provider<PostApplicationCreatedInitializer> bindThreadMetrixClientProvider;
    private Provider<PostInteractiveInitializer> bindWishListManagerProvider;
    private MembersInjector<BookingSummaryFragment> bookingSummaryFragmentMembersInjector;
    private MembersInjector<BottomBarBadgeInboxHandler> bottomBarBadgeInboxHandlerMembersInjector;
    /* access modifiers changed from: private */
    public Provider<BottomBarController> bottomBarControllerProvider;
    private MembersInjector<BrazilCreditCardDetailsFragment> brazilCreditCardDetailsFragmentMembersInjector;
    private MembersInjector<BusinessTravelInterstitialFragment> businessTravelInterstitialFragmentMembersInjector;
    private MembersInjector<BusinessTravelWelcomeFragment> businessTravelWelcomeFragmentMembersInjector;
    private MembersInjector<ButtonPartnership> buttonPartnershipMembersInjector;
    private MembersInjector<CalendarAgendaAdapter> calendarAgendaAdapterMembersInjector;
    private MembersInjector<CalendarDetailAdapter> calendarDetailAdapterMembersInjector;
    private MembersInjector<CalendarFragment> calendarFragmentMembersInjector;
    private MembersInjector<CalendarNestedBusyDayFragment> calendarNestedBusyDayFragmentMembersInjector;
    private MembersInjector<CalendarUpdateNotesFragment> calendarUpdateNotesFragmentMembersInjector;
    private MembersInjector<CalendarWithPriceTipsUpdateFragment> calendarWithPriceTipsUpdateFragmentMembersInjector;
    private Provider<com.airbnb.android.checkin.data.CheckInComponent.Builder> checkInComponentBuilderProvider;
    private Provider<CheckInJitneyLogger> checkInJitneyLoggerProvider;
    /* access modifiers changed from: private */
    public final CheckInModule checkInModule;
    private MembersInjector<CheckinNoteTextSettingFragment> checkinNoteTextSettingFragmentMembersInjector;
    private MembersInjector<CohostManagementActivity> cohostManagementActivityMembersInjector;
    private MembersInjector<CohostManagementDataController> cohostManagementDataControllerMembersInjector;
    private MembersInjector<CohostReasonMessageTextInputFragment> cohostReasonMessageTextInputFragmentMembersInjector;
    private MembersInjector<CohostReasonPrivateFeedbackTextInputFragment> cohostReasonPrivateFeedbackTextInputFragmentMembersInjector;
    private MembersInjector<CohostReasonSelectionActivity> cohostReasonSelectionActivityMembersInjector;
    private MembersInjector<CohostReasonSelectionFragment> cohostReasonSelectionFragmentMembersInjector;
    private MembersInjector<CohostUpsellActivity> cohostUpsellActivityMembersInjector;
    private MembersInjector<CohostUpsellEpoxyController> cohostUpsellEpoxyControllerMembersInjector;
    private MembersInjector<CohostUpsellFragment> cohostUpsellFragmentMembersInjector;
    private MembersInjector<CohostingContractFragment> cohostingContractFragmentMembersInjector;
    private MembersInjector<CohostingInvitationErrorFragment> cohostingInvitationErrorFragmentMembersInjector;
    private MembersInjector<CohostingInvitationExpiredFragment> cohostingInvitationExpiredFragmentMembersInjector;
    private MembersInjector<CohostingInviteFriendAdapter> cohostingInviteFriendAdapterMembersInjector;
    private MembersInjector<CohostingInviteFriendConfirmationFragment> cohostingInviteFriendConfirmationFragmentMembersInjector;
    private MembersInjector<CohostingInviteFriendEpoxyController> cohostingInviteFriendEpoxyControllerMembersInjector;
    private MembersInjector<CohostingInviteFriendWithFeeOptionFragment> cohostingInviteFriendWithFeeOptionFragmentMembersInjector;
    private MembersInjector<CohostingListingLevelNotificationSettingFragment> cohostingListingLevelNotificationSettingFragmentMembersInjector;
    private MembersInjector<CohostingListingPickerAdapter> cohostingListingPickerAdapterMembersInjector;
    private MembersInjector<CohostingMakePrimaryHostFragment> cohostingMakePrimaryHostFragmentMembersInjector;
    private MembersInjector<CohostingServicesIntroFragment> cohostingServicesIntroFragmentMembersInjector;
    private MembersInjector<CohostingShareEarningsAdapter> cohostingShareEarningsAdapterMembersInjector;
    private MembersInjector<CohostingShareEarningsEpoxyController> cohostingShareEarningsEpoxyControllerMembersInjector;
    private MembersInjector<CohostingShareEarningsWithFeeOptionFragment> cohostingShareEarningsWithFeeOptionFragmentMembersInjector;
    private MembersInjector<CohostingStopShareEarningsFragment> cohostingStopShareEarningsFragmentMembersInjector;
    private MembersInjector<CommunityCommitmentCancelAccountFragment> communityCommitmentCancelAccountFragmentMembersInjector;
    private MembersInjector<CommunityCommitmentFragment> communityCommitmentFragmentMembersInjector;
    private MembersInjector<CommunityCommitmentLearnMoreFragment> communityCommitmentLearnMoreFragmentMembersInjector;
    private MembersInjector<CommunityCommitmentWriteFeedbackFragment> communityCommitmentWriteFeedbackFragmentMembersInjector;
    private MembersInjector<CompleteProfilePhoneChildFragment> completeProfilePhoneChildFragmentMembersInjector;
    private MembersInjector<CompleteProfilePhoneCodeChildFragment> completeProfilePhoneCodeChildFragmentMembersInjector;
    private MembersInjector<CompleteProfilePhotoFragment> completeProfilePhotoFragmentMembersInjector;
    private MembersInjector<ConfirmInvitationAcceptedFragment> confirmInvitationAcceptedFragmentMembersInjector;
    private Provider<com.airbnb.android.contentframework.ContentFrameworkComponent.Builder> contentFrameworkComponentBuilderProvider;
    private MembersInjector<CreateIdentificationActivity> createIdentificationActivityMembersInjector;
    private MembersInjector<CreateNewSavedMessageFragment> createNewSavedMessageFragmentMembersInjector;
    private MembersInjector<CreditCardBaseFragment> creditCardBaseFragmentMembersInjector;
    private MembersInjector<CreditCardDetailsFragment> creditCardDetailsFragmentMembersInjector;
    private MembersInjector<CurrenciesRequest> currenciesRequestMembersInjector;
    private MembersInjector<DLSCancellationPolicyFragment> dLSCancellationPolicyFragmentMembersInjector;
    /* access modifiers changed from: private */
    public Provider<DLSJitneyLogger> dLSJitneyLoggerProvider;
    private MembersInjector<DLSReservationObjectFragment> dLSReservationObjectFragmentMembersInjector;
    private MembersInjector<DTKPartnerTask> dTKPartnerTaskMembersInjector;
    private MembersInjector<DebugMenuActivity> debugMenuActivityMembersInjector;
    private MembersInjector<DeleteOauthTokenRequest> deleteOauthTokenRequestMembersInjector;
    private MembersInjector<DlsManageListingActivity> dlsManageListingActivityMembersInjector;
    private MembersInjector<EditPayoutFragment> editPayoutFragmentMembersInjector;
    private MembersInjector<EditProfileDetailsAdapter> editProfileDetailsAdapterMembersInjector;
    private MembersInjector<EditProfileFragment> editProfileFragmentMembersInjector;
    private MembersInjector<EditableCell> editableCellMembersInjector;
    private MembersInjector<EmptyResultsCardView> emptyResultsCardViewMembersInjector;
    private MembersInjector<EndpointAdapter> endpointAdapterMembersInjector;
    private MembersInjector<EntryActivity> entryActivityMembersInjector;
    private MembersInjector<ErfOverrideActivity> erfOverrideActivityMembersInjector;
    private Provider<com.airbnb.android.explore.ExploreComponent.Builder> exploreComponentBuilderProvider;
    private MembersInjector<FeedbackIntroFragment> feedbackIntroFragmentMembersInjector;
    private MembersInjector<FeedbackSummaryFragment> feedbackSummaryFragmentMembersInjector;
    private MembersInjector<GCMHelper> gCMHelperMembersInjector;
    private MembersInjector<GetActiveAccountRequest> getActiveAccountRequestMembersInjector;
    private MembersInjector<GuestIdentificationAdapter> guestIdentificationAdapterMembersInjector;
    private MembersInjector<GuestPickerFragment> guestPickerFragmentMembersInjector;
    private Provider<com.airbnb.android.guestrecovery.GuestRecoveryComponent.Builder> guestRecoveryComponentBuilderProvider;
    private MembersInjector<HHBaseAdapter> hHBaseAdapterMembersInjector;
    private MembersInjector<HHListRemoteViewsFactory> hHListRemoteViewsFactoryMembersInjector;
    private MembersInjector<HelpThreadDialogActivity> helpThreadDialogActivityMembersInjector;
    private MembersInjector<HelpThreadFragment> helpThreadFragmentMembersInjector;
    private MembersInjector<HomesQuickPayFragment> homesQuickPayFragmentMembersInjector;
    private MembersInjector<HostAmenityListFragment> hostAmenityListFragmentMembersInjector;
    private MembersInjector<HostDemandsDetailFragment> hostDemandsDetailFragmentMembersInjector;
    private MembersInjector<HostHomeWidgetProvider> hostHomeWidgetProviderMembersInjector;
    private MembersInjector<HostListingSelectorFragment> hostListingSelectorFragmentMembersInjector;
    private MembersInjector<HostReferralsFragment> hostReferralsFragmentMembersInjector;
    private MembersInjector<HostReservationObjectAdapter> hostReservationObjectAdapterMembersInjector;
    private MembersInjector<HostReservationObjectFragment> hostReservationObjectFragmentMembersInjector;
    private MembersInjector<HostReviewDetailAdapter> hostReviewDetailAdapterMembersInjector;
    private MembersInjector<HostReviewDetailsFragment> hostReviewDetailsFragmentMembersInjector;
    private MembersInjector<HostStatsFragment> hostStatsFragmentMembersInjector;
    private MembersInjector<IdentificationNameFragment> identificationNameFragmentMembersInjector;
    private Provider<com.airbnb.android.identity.IdentityComponent.Builder> identityComponentBuilderProvider;
    /* access modifiers changed from: private */
    public MembersInjector<IdentitySelfieCaptureFragment> identitySelfieCaptureFragmentMembersInjector;
    private MembersInjector<InboxActivity> inboxActivityMembersInjector;
    private MembersInjector<InboxAdapter> inboxAdapterMembersInjector;
    private MembersInjector<InboxContainerFragment> inboxContainerFragmentMembersInjector;
    private MembersInjector<InboxFragment> inboxFragmentMembersInjector;
    private MembersInjector<InsightsDataController> insightsDataControllerMembersInjector;
    private MembersInjector<InsightsNightlyPriceFragment> insightsNightlyPriceFragmentMembersInjector;
    private MembersInjector<InstantBookUpsellManager> instantBookUpsellManagerMembersInjector;
    private MembersInjector<InternalBugReportFragment> internalBugReportFragmentMembersInjector;
    private MembersInjector<ItineraryParentFragment> itineraryParentFragmentMembersInjector;
    private MembersInjector<JPushHelper> jPushHelperMembersInjector;
    private MembersInjector<LYSAddressFragment> lYSAddressFragmentMembersInjector;
    private MembersInjector<LYSBasePriceFragment> lYSBasePriceFragmentMembersInjector;
    private MembersInjector<LYSCalendarFragment> lYSCalendarFragmentMembersInjector;
    private MembersInjector<LYSCharacterCountMarqueeFragment> lYSCharacterCountMarqueeFragmentMembersInjector;
    private MembersInjector<LYSDataController> lYSDataControllerMembersInjector;
    private MembersInjector<LYSDiscountsFragment> lYSDiscountsFragmentMembersInjector;
    private MembersInjector<LYSGuestBookFragment> lYSGuestBookFragmentMembersInjector;
    private MembersInjector<LYSHomeActivity> lYSHomeActivityMembersInjector;
    private MembersInjector<LYSHostingFrequencyFragment> lYSHostingFrequencyFragmentMembersInjector;
    private MembersInjector<LYSLandingFragment> lYSLandingFragmentMembersInjector;
    private MembersInjector<LYSLocalLawsFragment> lYSLocalLawsFragmentMembersInjector;
    private MembersInjector<LYSPhotoDetailFragment> lYSPhotoDetailFragmentMembersInjector;
    private MembersInjector<LYSPhotoManagerFragment> lYSPhotoManagerFragmentMembersInjector;
    private MembersInjector<LYSPhotoStartFragment> lYSPhotoStartFragmentMembersInjector;
    private MembersInjector<LYSPublishFragment> lYSPublishFragmentMembersInjector;
    private MembersInjector<LYSRTBChecklistFragment> lYSRTBChecklistFragmentMembersInjector;
    private MembersInjector<LYSRentHistoryFragment> lYSRentHistoryFragmentMembersInjector;
    private MembersInjector<LYSRoomsAndGuestsFragment> lYSRoomsAndGuestsFragmentMembersInjector;
    private MembersInjector<LYSSelectPricingTypeFragment> lYSSelectPricingTypeFragmentMembersInjector;
    private MembersInjector<LYSSmartPricingFragment> lYSSmartPricingFragmentMembersInjector;
    private MembersInjector<LYSSpaceTypeFragment> lYSSpaceTypeFragmentMembersInjector;
    private Provider<com.airbnb.android.lib.LibComponent.Builder> libComponentBuilderProvider;
    private MembersInjector<ListingManagerDetailsAdapter> listingManagerDetailsAdapterMembersInjector;
    private MembersInjector<ListingManagersPickerAdapter> listingManagersPickerAdapterMembersInjector;
    private MembersInjector<ListingManagersPickerFragment> listingManagersPickerFragmentMembersInjector;
    private MembersInjector<ListingsTray> listingsTrayMembersInjector;
    private MembersInjector<LocalBitmapForDisplayScalingTask> localBitmapForDisplayScalingTaskMembersInjector;
    private MembersInjector<LocalPushNotificationManager> localPushNotificationManagerMembersInjector;
    private MembersInjector<LocaleChangedReceiver> localeChangedReceiverMembersInjector;
    private MembersInjector<LocationTypeaheadFilterForChina> locationTypeaheadFilterForChinaMembersInjector;
    private MembersInjector<LoginActivity> loginActivityMembersInjector;
    private Provider<com.airbnb.android.login.LoginComponent.Builder> loginComponentBuilderProvider;
    private MembersInjector<LoopingViewPager> loopingViewPagerMembersInjector;
    private MembersInjector<LottieNuxViewPagerFragment> lottieNuxViewPagerFragmentMembersInjector;
    private Provider<LYSJitneyLogger> lysJitneyLoggerProvider;
    private MembersInjector<ManageListingAllCheckinMethodsFragment> manageListingAllCheckinMethodsFragmentMembersInjector;
    private MembersInjector<ManageListingBookingsAdapter> manageListingBookingsAdapterMembersInjector;
    private MembersInjector<ManageListingCheckInGuideFragment> manageListingCheckInGuideFragmentMembersInjector;
    private MembersInjector<ManageListingCheckinTypeTextSettingFragment> manageListingCheckinTypeTextSettingFragmentMembersInjector;
    private MembersInjector<ManageListingDataController> manageListingDataControllerMembersInjector;
    private MembersInjector<ManageListingDetailsEpoxyController> manageListingDetailsEpoxyControllerMembersInjector;
    private MembersInjector<ManageListingDiscountsSettingsFragment> manageListingDiscountsSettingsFragmentMembersInjector;
    private MembersInjector<ManageListingEarlyBirdDiscountFragment> manageListingEarlyBirdDiscountFragmentMembersInjector;
    private MembersInjector<ManageListingLastMinuteDiscountFragment> manageListingLastMinuteDiscountFragmentMembersInjector;
    private MembersInjector<ManageListingLocalLawsFragment> manageListingLocalLawsFragmentMembersInjector;
    private MembersInjector<ManageListingNightlyPriceSettingsFragment> manageListingNightlyPriceSettingsFragmentMembersInjector;
    private MembersInjector<ManageListingPickerFragment> manageListingPickerFragmentMembersInjector;
    private MembersInjector<ManageListingSettingsTabFragment> manageListingSettingsTabFragmentMembersInjector;
    private MembersInjector<ManagePhotosFragment> managePhotosFragmentMembersInjector;
    private MembersInjector<MiSnapIdentityCaptureActivity> miSnapIdentityCaptureActivityMembersInjector;
    private MembersInjector<MiSnapTakeSelfieActivity> miSnapTakeSelfieActivityMembersInjector;
    /* access modifiers changed from: private */
    public Provider<com.airbnb.p027n2.N2Component.Builder> n2ComponentBuilderProvider;
    private MembersInjector<NightlyPriceAdapter> nightlyPriceAdapterMembersInjector;
    private MembersInjector<OfficialIdErrorFragment> officialIdErrorFragmentMembersInjector;
    private MembersInjector<OfficialIdIntentService> officialIdIntentServiceMembersInjector;
    private MembersInjector<OfficialIdPhotoSelectionFragment> officialIdPhotoSelectionFragmentMembersInjector;
    private MembersInjector<OfflineIdChildFragment> offlineIdChildFragmentMembersInjector;
    private MembersInjector<OldManageListingPhotoUploadService> oldManageListingPhotoUploadServiceMembersInjector;
    private MembersInjector<OnlineIdChildFragment> onlineIdChildFragmentMembersInjector;
    /* access modifiers changed from: private */
    public MembersInjector<P3AdditionalPriceFragment> p3AdditionalPriceFragmentMembersInjector;
    private Provider<com.airbnb.android.p011p3.P3Component.Builder> p3ComponentBuilderProvider;
    private MembersInjector<PayWithAlipayActivity> payWithAlipayActivityMembersInjector;
    private MembersInjector<PaymentOptionsFragment> paymentOptionsFragmentMembersInjector;
    private MembersInjector<PayoutRedirectWebviewActivity> payoutRedirectWebviewActivityMembersInjector;
    private MembersInjector<PayoutSelectFragment> payoutSelectFragmentMembersInjector;
    private MembersInjector<PendingCohostDetailsFragment> pendingCohostDetailsFragmentMembersInjector;
    private MembersInjector<PhoneNumberInputSheet> phoneNumberInputSheetMembersInjector;
    private MembersInjector<PhoneVerificationFragment> phoneVerificationFragmentMembersInjector;
    private Provider<PhotoUploadManager> photoUploadManagerProvider;
    private MembersInjector<PhotoUploadRetryBroadcastReceiver> photoUploadRetryBroadcastReceiverMembersInjector;
    private MembersInjector<PhotoUploadService> photoUploadServiceMembersInjector;
    private MembersInjector<PhotoVerificationFragment> photoVerificationFragmentMembersInjector;
    private MembersInjector<com.airbnb.android.lib.china5a.fragments.PhotoVerificationFragment> photoVerificationFragmentMembersInjector2;
    private MembersInjector<PhotoVerificationPresenter> photoVerificationPresenterMembersInjector;
    private Provider<com.airbnb.android.pickwishlist.PickWishListComponent.Builder> pickWishListComponentBuilderProvider;
    private MembersInjector<PostBookingActivity> postBookingActivityMembersInjector;
    private MembersInjector<PostBookingBusinessTravelPromoFragment> postBookingBusinessTravelPromoFragmentMembersInjector;
    private MembersInjector<PreapproveInquiryFragment> preapproveInquiryFragmentMembersInjector;
    private MembersInjector<PriceGroupedCell> priceGroupedCellMembersInjector;
    private MembersInjector<PricingQuotePricingDetails> pricingQuotePricingDetailsMembersInjector;
    private MembersInjector<ProfileCompletionActivity> profileCompletionActivityMembersInjector;
    private Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<ProfileCompletionManager> profileCompletionManagerProvider;
    /* access modifiers changed from: private */
    public Provider<AirbnbAccountManager> provideAccountManagerProvider;
    private Provider<LYSAddressAutoCompleteLogger> provideAddressAutoCompleteLoggerProvider;
    private Provider<AirCookieManager> provideAirCookieManagerProvider;
    /* access modifiers changed from: private */
    public Provider<AirRequestInitializer> provideAirRequestInitializerProvider;
    /* access modifiers changed from: private */
    public Provider<AirbnbApi> provideAirbnbApiProvider;
    /* access modifiers changed from: private */
    public Provider<AirbnbEventLogger> provideAirbnbEventLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<AirbnbPreferences> provideAirbnbPreferencesProvider;
    /* access modifiers changed from: private */
    public Provider<AirlockErrorHandler> provideAirlockErrorHandlerProvider;
    private Provider<AlipayAnalytics> provideAlipayAnalyticsProvider;
    /* access modifiers changed from: private */
    public Provider<AnalyticsRegistry> provideAnalyticsRegistryProvider;
    private Provider<Tracker> provideAnalyticsTrackerProvider;
    private Provider<AccountManager> provideAndroidAccountManagerProvider;
    private Provider<ApiRequestHeadersInterceptor> provideApiRequestHeadersInterceptorProvider;
    private Provider<ApolloClient> provideApolloClientProvider;
    private Provider<AppForegroundAnalytics> provideAppForegroundAnalyticsProvider;
    private Provider<AppForegroundDetector> provideAppForegroundDetectorProvider;
    private Provider<PostApplicationCreatedInitializer> provideAppIdentityVerifierProvider;
    /* access modifiers changed from: private */
    public Provider<AppLaunchAnalytics> provideAppLaunchAnalyticsProvider;
    /* access modifiers changed from: private */
    public Provider<AppLaunchUtils> provideAppLaunchUtilsProvider;
    private Provider<ApplicationInterceptorsProvider> provideApplicationInterceptorsProvider;
    private Provider<Application> provideApplicationProvider;
    private Provider<AvailabilityCalendarJitneyLogger> provideAvailabilityCalendarJitneyLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<BookingJitneyLogger> provideBookingJitneyEventLoggerProvider;
    private Provider<BraintreeFactory> provideBraintreeFactoryProvider;
    private Provider<BrazilPaymentInputFormatter> provideBrazilPaymentInputFormatterProvider;
    /* access modifiers changed from: private */
    public Provider<Bus> provideBusProvider;
    /* access modifiers changed from: private */
    public Provider<BusinessTravelAccountManager> provideBusinessTravelAccountManagerProvider;
    /* access modifiers changed from: private */
    public Provider<BusinessTravelJitneyLogger> provideBusinessTravelJitneyLoggerProvider;
    private Provider<Bypass> provideBypassProvider;
    private Provider<Cache> provideCacheProvider;
    private Provider<CalendarJitneyLogger> provideCalendarJitneyLoggerProvider;
    private Provider<CalendarStore> provideCalendarStoreProvider;
    private Provider<Factory> provideCallAdapterFactoryProvider;
    private Provider<ChooseProfilePhotoController> provideChooseProfilePhotoControllerProvider;
    private Provider<ClientSessionManager> provideClientSessionManagerProvider;
    /* access modifiers changed from: private */
    public Provider<ClientSessionValidator> provideClientSessionValidatorProvider;
    private Provider<CohostingInvitationJitneyLogger> provideCohostingInvitationJitneyLoggerProvider;
    private Provider<CohostingManagementJitneyLogger> provideCohostingManagementJitneyLoggerProvider;
    private Provider<CohostingReusableFlowJitneyLogger> provideCohostingReusableFlowJitneyLoggerProvider;
    private Provider<CommunityCommitmentJitneyLogger> provideCommunityCommitmentJitneyLoggerProvider;
    private Provider<Config> provideConfigProvider;
    /* access modifiers changed from: private */
    public Provider<Context> provideContextProvider;
    private Provider<ConverterFactory> provideConverterFactoryProvider;
    private Provider<CreditCardValidator> provideCreditCardValidatorProvider;
    /* access modifiers changed from: private */
    public Provider<CurrencyFormatter> provideCurrencyHelperProvider;
    private Provider<DebugNotificationController> provideDebugNotificationControllerProvider;
    /* access modifiers changed from: private */
    public Provider<DebugSettings> provideDebugSettingsProvider;
    private Provider<DeviceInfo> provideDeviceIDProvider;
    private Provider<DigitalRiverApi> provideDigitalRiverApiProvider;
    private Provider<DomainStore> provideDomainStoreProvider;
    /* access modifiers changed from: private */
    public Provider<DynamicStringsResources> provideDynamicStringsResourcesProvider;
    private Provider<BaseUrl> provideEndpointProvider;
    /* access modifiers changed from: private */
    public Provider<ErfAnalytics> provideErfAnalyticsProvider;
    private Provider<ErfCallbacks> provideErfCallbacksProvider;
    private Provider<ErfExperimentsTableOpenHelper> provideErfExperimentsTableOpenHelperProvider;
    /* access modifiers changed from: private */
    public Provider<Erf> provideErfProvider;
    /* access modifiers changed from: private */
    public Provider<ExperimentAssignments> provideExperimentAssigmentsProvider;
    /* access modifiers changed from: private */
    public Provider<ExperimentConfigController> provideExperimentConfigControllerProvider;
    /* access modifiers changed from: private */
    public Provider<ExperimentsProvider> provideExperimentsProvider;
    /* access modifiers changed from: private */
    public Provider<GeocoderBaseUrl> provideGeocoderRequestBaseUrlProvider;
    /* access modifiers changed from: private */
    public Provider<GoogleAppIndexingController> provideGoogleAppIndexingControllerProvider;
    private Provider<GraphistClient> provideGraphistClientProvider;
    private Provider<HostReferralLogger> provideHostReferralsLoggerProvider;
    private Provider<HostReservationObjectJitneyLogger> provideHostReservationObjectJitneyLoggerProvider;
    private Provider<HostStatsJitneyLogger> provideHostStatsJitneyLoggerProvider;
    private Provider<IdentityClient> provideIdentityClientProvider;
    /* access modifiers changed from: private */
    public Provider<IdentityJitneyLogger> provideIdentityJitneyEventLoggerProvider;
    private Provider<ImageUtils> provideImageUtilsProvider;
    private Provider<InboxUnreadCountManager> provideInboxUnreadCountManagerProvider;
    private Provider<InstantBookUpsellManager> provideInstantBookUpsellManagerProvider;
    /* access modifiers changed from: private */
    public Provider<InstantPromotionManager> provideInstantPromotionManagerProvider;
    /* access modifiers changed from: private */
    public Provider<ItineraryManager> provideItineraryManagerProvider;
    private Provider<ItineraryTableOpenHelper> provideItineraryTableOpenHelperProvider;
    private Provider<JPushInitializer> provideJPushInitializerProvider;
    /* access modifiers changed from: private */
    public Provider<NavigationLogging> provideKonaNavigationAnalyticsProvider;
    /* access modifiers changed from: private */
    public Provider<LocalPushNotificationManager> provideLocalPushNotificationManagerProvider;
    /* access modifiers changed from: private */
    public Provider<LocationClientFacade> provideLocationHelperProvider;
    private Provider<LogAirInitializer> provideLogAirInitializerProvider;
    /* access modifiers changed from: private */
    public Provider<LoggingContextFactory> provideLoggingContextFactoryProvider;
    /* access modifiers changed from: private */
    public Provider<LowBandwidthManager> provideLowBandwidthUtilsProvider;
    /* access modifiers changed from: private */
    public Provider<MemoryUtils> provideMemoryUtilsProvider;
    private Provider<MessageStore> provideMessageStoreProvider;
    private Provider<MessagingRequestFactory> provideMessageStoreRequestFactoryProvider;
    private Provider<MessageStoreTableOpenHelper> provideMessageStoreTableOpenHelperProvider;
    private Provider<MessagingJitneyLogger> provideMessagingJitneyLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<Callbacks> provideN2CallbacksProvider;
    private Provider<NetworkInterceptorsProvider> provideNetworkInterceptorsProvider;
    /* access modifiers changed from: private */
    public Provider<NetworkMonitor> provideNetworkMonitorProvider;
    private Provider<NetworkTimeProvider> provideNetworkTimeProvider;
    /* access modifiers changed from: private */
    public Provider<ObjectMapper> provideObjectMapperProvider;
    private Provider<ObservableManager> provideObservableManagerProvider;
    /* access modifiers changed from: private */
    public Provider<OkHttpClient> provideOkHttpClientProvider;
    private Provider<PaidAmenityJitneyLogger> providePaidAmenityJitneyLoggerProvider;
    private Provider<PaymentOptionFactory> providePaymentOptionFactoryProvider;
    private Provider<PaymentOptionsAdapterFactory> providePaymentOptionsAdapterFactoryProvider;
    private Provider<PaymentRedirectCoordinator> providePaymentRedirectCoordinatorProvider;
    /* access modifiers changed from: private */
    public Provider<PerformanceLogger> providePerformanceLoggerProvider;
    private Provider<PhoneNumberUtil> providePhoneNumberUtilProvider;
    private Provider<PhoneUtil> providePhoneUtilProvider;
    private Provider<PhotoCompressor> providePhotoCompressorProvider;
    /* access modifiers changed from: private */
    public Provider<ListingPromoController> providePromoControllerProvider;
    private Provider<ApiRequestQueryParamsInterceptor> provideQueryParamsProvider;
    private Provider<QuickPayAdapterFactory> provideQuickPayAdapterFactoryProvider;
    private Provider<QuickPayJitneyLogger> provideQuickPayJitneyEventLoggerProvider;
    private Provider<BillPriceQuoteRequestFactory> provideQuickPayRequestsFactoryProvider;
    private Provider<QuickPayRowFactory> provideQuickPayRowFactoryProvider;
    private Provider<CookieJar> provideReactCookieJarContainerProvider;
    private Provider<ReactDeepLinkParser> provideReactDeepLinkParserProvider;
    private Provider<ReactDeepLinkRegistry> provideReactDeepLinkRegistryProvider;
    /* access modifiers changed from: private */
    public Provider<AirReactInstanceManager> provideReactInstanceManagerProvider;
    private Provider<ReactNavigationCoordinator> provideReactNativeCoordinatorProvider;
    private Provider<ReactNativeModulesProvider> provideReactNativeModuleFactoryProvider;
    private Provider<Executor> provideRequestCallbackExecutorProvider;
    private Provider<AirRequestHeadersInterceptor> provideRequestHeadersProvider;
    private Provider<ReservationCancellationLogger> provideReservationCancellationLoggerProvider;
    private Provider<ReservationResponseLogger> provideReservationResponseLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<ResourceManager> provideResourceManagerProvider;
    private Provider<Retrofit> provideRestAdapterProvider;
    private Provider<retrofit2.Retrofit.Builder> provideRetrofitBuilderProvider;
    /* access modifiers changed from: private */
    public Provider<ReviewSearchJitneyLogger> provideReviewSearchJitneyLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<ShakeFeedbackSensorListener> provideShakeFeedbackhelperProvider;
    /* access modifiers changed from: private */
    public Provider<SharedPrefsHelper> provideSharedPrefsHelperProvider;
    /* access modifiers changed from: private */
    public Provider<SingleFireRequestExecutor> provideSingleFireRequestExecutorProvider;
    private Provider<SplashScreenController> provideSplashScreenControllerProvider;
    /* access modifiers changed from: private */
    public Provider<SuperHeroManager> provideSuperHeroManagerProvider;
    private Provider<SuperHeroTableOpenHelper> provideSuperHeroTableOpenHelperProvider;
    private Provider<SyncRequestFactory> provideSyncRequestFactoryProvider;
    private Provider<TakeSelfieController> provideTakeSelfieControllerProvider;
    private Provider<ThreatMetrixClient> provideThreatMetrixClientProvider;
    private Provider<TimeSkewAnalytics> provideTimeSkewAnalyticsProvider;
    private Provider<TrebuchetController> provideTrebuchetControllerProvider;
    private Provider<AirbnbApiUrlMatcher> provideUrlMatcherProvider;
    private Provider<VerifiedIdAnalytics> provideVerifiedIdAnalyticsProvider;
    /* access modifiers changed from: private */
    public Provider<ViewBreadcrumbManager> provideViewBreadcrumbManagerProvider;
    private Provider<ViewedListingsDatabaseHelper> provideViewedListingsDatabaseHelperProvider;
    private Provider<WebIntentMatcher> provideWebIntentMatcherProvider;
    private Provider<WhatsMyPlaceWorthLogger> provideWhatsMyPlaceWorthLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<WishListLogger> provideWishListLoggerProvider;
    /* access modifiers changed from: private */
    public Provider<WishListManager> provideWishListManagerProvider;
    private MembersInjector<PushIntentService> pushIntentServiceMembersInjector;
    private MembersInjector<PushNotificationBuilder> pushNotificationBuilderMembersInjector;
    private MembersInjector<QuickPayActivity> quickPayActivityMembersInjector;
    private MembersInjector<QuickPayFragment> quickPayFragmentMembersInjector;
    private MembersInjector<ReactNativeActivity> reactNativeActivityMembersInjector;
    private MembersInjector<ReactNativeFragment> reactNativeFragmentMembersInjector;
    private MembersInjector<ReasonPickerFragment> reasonPickerFragmentMembersInjector;
    private MembersInjector<ReferralBroadcastReceiver> referralBroadcastReceiverMembersInjector;
    private Provider<com.airbnb.android.referrals.ReferralsComponent.Builder> referralsComponentBuilderProvider;
    private Provider<com.airbnb.android.registration.RegistrationComponent.Builder> registrationComponentBuilderProvider;
    private MembersInjector<RemoveCohostFragment> removeCohostFragmentMembersInjector;
    private MembersInjector<RemovePreapprovalFragment> removePreapprovalFragmentMembersInjector;
    private MembersInjector<ReplaceVerifiedIdWithIdentityActivity> replaceVerifiedIdWithIdentityActivityMembersInjector;
    private MembersInjector<ReservationCanceledFragment> reservationCanceledFragmentMembersInjector;
    private MembersInjector<ReservationCancellationWithUserInputFragment> reservationCancellationWithUserInputFragmentMembersInjector;
    private MembersInjector<ReservationObjectAdapter> reservationObjectAdapterMembersInjector;
    private MembersInjector<ReservationPickerFragment> reservationPickerFragmentMembersInjector;
    private MembersInjector<ReservationResponseActivity> reservationResponseActivityMembersInjector;
    private MembersInjector<ReservationResponseLandingFragment> reservationResponseLandingFragmentMembersInjector;
    private MembersInjector<ResourceManager> resourceManagerMembersInjector;
    private MembersInjector<ResyController> resyControllerMembersInjector;
    private MembersInjector<SFRPartnerTask> sFRPartnerTaskMembersInjector;
    private MembersInjector<SavedMessagesFragment> savedMessagesFragmentMembersInjector;
    private MembersInjector<SearchIntentActivity> searchIntentActivityMembersInjector;
    private MembersInjector<SearchSettingsFragment> searchSettingsFragmentMembersInjector;
    private MembersInjector<SelectBillingCountryFragment> selectBillingCountryFragmentMembersInjector;
    private MembersInjector<SelectCountryFragment> selectCountryFragmentMembersInjector;
    private MembersInjector<SelectPaymentMethodFragment> selectPaymentMethodFragmentMembersInjector;
    private MembersInjector<SelectPayoutCountryActivity> selectPayoutCountryActivityMembersInjector;
    private MembersInjector<SesameVerificationChildFragment> sesameVerificationChildFragmentMembersInjector;
    private MembersInjector<SesameVerificationConnectFragment> sesameVerificationConnectFragmentMembersInjector;
    private Provider<Set<PostApplicationCreatedInitializer>> setOfPostApplicationCreatedInitializerProvider;
    private Provider<Set<PostInteractiveInitializer>> setOfPostInteractiveInitializerProvider;
    private MembersInjector<SetProfilePhotoRequest> setProfilePhotoRequestMembersInjector;
    private MembersInjector<ShakeFeedbackDialog> shakeFeedbackDialogMembersInjector;
    private MembersInjector<Shareable> shareableMembersInjector;
    private MembersInjector<SharingManager> sharingManagerMembersInjector;
    private MembersInjector<SingleCalendarBaseFragment> singleCalendarBaseFragmentMembersInjector;
    private MembersInjector<SingleCalendarFragment> singleCalendarFragmentMembersInjector;
    private MembersInjector<SpecialOfferActivity> specialOfferActivityMembersInjector;
    private MembersInjector<SplashScreenActivity> splashScreenActivityMembersInjector;
    private MembersInjector<SuperHeroAlarmReceiver> superHeroAlarmReceiverMembersInjector;
    private MembersInjector<SuperHeroThreadFragment> superHeroThreadFragmentMembersInjector;
    private MembersInjector<TOSDialogFragment> tOSDialogFragmentMembersInjector;
    private MembersInjector<ThreadAdapter> threadAdapterMembersInjector;
    private MembersInjector<ThreadFragment> threadFragmentMembersInjector;
    private MembersInjector<TrebuchetOverrideActivity> trebuchetOverrideActivityMembersInjector;
    private MembersInjector<TripsReservationsSyncService> tripsReservationsSyncServiceMembersInjector;
    private MembersInjector<UserLoginRequest> userLoginRequestMembersInjector;
    private MembersInjector<UserProfileActivity> userProfileActivityMembersInjector;
    private MembersInjector<UserProfileFragment> userProfileFragmentMembersInjector;
    private MembersInjector<VerifiedIdCompletedFragment> verifiedIdCompletedFragmentMembersInjector;
    private MembersInjector<VerifyWorkEmailFragment> verifyWorkEmailFragmentMembersInjector;
    private MembersInjector<VerticalCalendarAdapter> verticalCalendarAdapterMembersInjector;
    private MembersInjector<ViewedListingsPersistenceService> viewedListingsPersistenceServiceMembersInjector;
    private MembersInjector<WLDetailsDeeplinkInterceptorActivity> wLDetailsDeeplinkInterceptorActivityMembersInjector;
    private MembersInjector<WebIntentDispatch> webIntentDispatchMembersInjector;
    private MembersInjector<WhatsMyPlaceWorthFragment> whatsMyPlaceWorthFragmentMembersInjector;
    private MembersInjector<WifiAlarmReceiver> wifiAlarmReceiverMembersInjector;
    private MembersInjector<WishListDetailsParentFragment> wishListDetailsParentFragmentMembersInjector;
    private MembersInjector<WishListHeartController> wishListHeartControllerMembersInjector;
    private MembersInjector<WishListIndexFragment> wishListIndexFragmentMembersInjector;
    private MembersInjector<WishListsFragment> wishListsFragmentMembersInjector;
    private MembersInjector<WorkEmailActivity> workEmailActivityMembersInjector;
    private MembersInjector<WorkEmailFragment> workEmailFragmentMembersInjector;
    private MembersInjector<ZenDialog> zenDialogMembersInjector;

    public static final class Builder {
        /* access modifiers changed from: private */
        public AnalyticsModule analyticsModule;
        /* access modifiers changed from: private */
        public AppRaterModule appRaterModule;
        /* access modifiers changed from: private */
        public CheckInModule checkInModule;
        /* access modifiers changed from: private */
        public CoreModule coreModule;
        /* access modifiers changed from: private */
        public DataModule dataModule;
        /* access modifiers changed from: private */
        public ImageModule imageModule;
        /* access modifiers changed from: private */
        public InitModule initModule;
        /* access modifiers changed from: private */
        public InternalModule internalModule;
        /* access modifiers changed from: private */
        public ItineraryModule itineraryModule;
        /* access modifiers changed from: private */
        public MisnapModule misnapModule;
        /* access modifiers changed from: private */
        public NetworkModule networkModule;
        /* access modifiers changed from: private */
        public ProfileCompletionModule profileCompletionModule;
        /* access modifiers changed from: private */
        public QuickPayModule quickPayModule;
        /* access modifiers changed from: private */
        public ReactModule reactModule;
        /* access modifiers changed from: private */
        public RiskModule riskModule;
        /* access modifiers changed from: private */
        public SuperHeroModule superHeroModule;
        /* access modifiers changed from: private */
        public WebIntentMatcherModule webIntentMatcherModule;

        private Builder() {
        }

        public AirbnbComponent build() {
            if (this.coreModule == null) {
                throw new IllegalStateException(CoreModule.class.getCanonicalName() + " must be set");
            }
            if (this.networkModule == null) {
                this.networkModule = new NetworkModule();
            }
            if (this.reactModule == null) {
                this.reactModule = new ReactModule();
            }
            if (this.analyticsModule == null) {
                this.analyticsModule = new AnalyticsModule();
            }
            if (this.dataModule == null) {
                this.dataModule = new DataModule();
            }
            if (this.imageModule == null) {
                this.imageModule = new ImageModule();
            }
            if (this.superHeroModule == null) {
                this.superHeroModule = new SuperHeroModule();
            }
            if (this.itineraryModule == null) {
                this.itineraryModule = new ItineraryModule();
            }
            if (this.riskModule == null) {
                this.riskModule = new RiskModule();
            }
            if (this.misnapModule == null) {
                this.misnapModule = new MisnapModule();
            }
            if (this.profileCompletionModule == null) {
                this.profileCompletionModule = new ProfileCompletionModule();
            }
            if (this.initModule == null) {
                this.initModule = new InitModule();
            }
            if (this.internalModule == null) {
                this.internalModule = new InternalModule();
            }
            if (this.quickPayModule == null) {
                this.quickPayModule = new QuickPayModule();
            }
            if (this.webIntentMatcherModule == null) {
                this.webIntentMatcherModule = new WebIntentMatcherModule();
            }
            if (this.appRaterModule == null) {
                this.appRaterModule = new AppRaterModule();
            }
            if (this.checkInModule == null) {
                this.checkInModule = new CheckInModule();
            }
            return new DaggerAirbnbComponent(this);
        }

        @Deprecated
        public Builder rootModule(RootModule rootModule) {
            Preconditions.checkNotNull(rootModule);
            return this;
        }

        public Builder coreModule(CoreModule coreModule2) {
            this.coreModule = (CoreModule) Preconditions.checkNotNull(coreModule2);
            return this;
        }

        public Builder initModule(InitModule initModule2) {
            this.initModule = (InitModule) Preconditions.checkNotNull(initModule2);
            return this;
        }

        public Builder dataModule(DataModule dataModule2) {
            this.dataModule = (DataModule) Preconditions.checkNotNull(dataModule2);
            return this;
        }

        public Builder internalModule(InternalModule internalModule2) {
            this.internalModule = (InternalModule) Preconditions.checkNotNull(internalModule2);
            return this;
        }

        public Builder networkModule(NetworkModule networkModule2) {
            this.networkModule = (NetworkModule) Preconditions.checkNotNull(networkModule2);
            return this;
        }

        public Builder imageModule(ImageModule imageModule2) {
            this.imageModule = (ImageModule) Preconditions.checkNotNull(imageModule2);
            return this;
        }

        public Builder analyticsModule(AnalyticsModule analyticsModule2) {
            this.analyticsModule = (AnalyticsModule) Preconditions.checkNotNull(analyticsModule2);
            return this;
        }

        @Deprecated
        public Builder p3Module(P3Module p3Module) {
            Preconditions.checkNotNull(p3Module);
            return this;
        }

        public Builder appRaterModule(AppRaterModule appRaterModule2) {
            this.appRaterModule = (AppRaterModule) Preconditions.checkNotNull(appRaterModule2);
            return this;
        }

        public Builder checkInModule(CheckInModule checkInModule2) {
            this.checkInModule = (CheckInModule) Preconditions.checkNotNull(checkInModule2);
            return this;
        }

        public Builder reactModule(ReactModule reactModule2) {
            this.reactModule = (ReactModule) Preconditions.checkNotNull(reactModule2);
            return this;
        }

        public Builder riskModule(RiskModule riskModule2) {
            this.riskModule = (RiskModule) Preconditions.checkNotNull(riskModule2);
            return this;
        }

        public Builder quickPayModule(QuickPayModule quickPayModule2) {
            this.quickPayModule = (QuickPayModule) Preconditions.checkNotNull(quickPayModule2);
            return this;
        }

        public Builder superHeroModule(SuperHeroModule superHeroModule2) {
            this.superHeroModule = (SuperHeroModule) Preconditions.checkNotNull(superHeroModule2);
            return this;
        }

        public Builder misnapModule(MisnapModule misnapModule2) {
            this.misnapModule = (MisnapModule) Preconditions.checkNotNull(misnapModule2);
            return this;
        }

        @Deprecated
        public Builder listYourSpaceDLSModule(ListYourSpaceDLSModule listYourSpaceDLSModule) {
            Preconditions.checkNotNull(listYourSpaceDLSModule);
            return this;
        }

        @Deprecated
        public Builder listingModule(ListingModule listingModule) {
            Preconditions.checkNotNull(listingModule);
            return this;
        }

        public Builder webIntentMatcherModule(WebIntentMatcherModule webIntentMatcherModule2) {
            this.webIntentMatcherModule = (WebIntentMatcherModule) Preconditions.checkNotNull(webIntentMatcherModule2);
            return this;
        }

        public Builder itineraryModule(ItineraryModule itineraryModule2) {
            this.itineraryModule = (ItineraryModule) Preconditions.checkNotNull(itineraryModule2);
            return this;
        }

        @Deprecated
        public Builder cohostingModule(CohostingModule cohostingModule) {
            Preconditions.checkNotNull(cohostingModule);
            return this;
        }

        public Builder profileCompletionModule(ProfileCompletionModule profileCompletionModule2) {
            this.profileCompletionModule = (ProfileCompletionModule) Preconditions.checkNotNull(profileCompletionModule2);
            return this;
        }

        @Deprecated
        public Builder manageListingModule(ManageListingModule manageListingModule) {
            Preconditions.checkNotNull(manageListingModule);
            return this;
        }

        @Deprecated
        public Builder payoutModule(PayoutModule payoutModule) {
            Preconditions.checkNotNull(payoutModule);
            return this;
        }

        @Deprecated
        public Builder hostReferralsModule(HostReferralsModule hostReferralsModule) {
            Preconditions.checkNotNull(hostReferralsModule);
            return this;
        }
    }

    private final class AppRaterComponentBuilder implements com.airbnb.android.lib.AppRaterComponent.Builder {
        private AppRaterComponentBuilder() {
        }

        public AppRaterComponent build() {
            return new AppRaterComponentImpl(this);
        }
    }

    private final class AppRaterComponentImpl implements AppRaterComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<AppRaterDialogFragment> appRaterDialogFragmentMembersInjector;
        private Provider<AppRaterController> provideAppRaterControllerProvider;

        private AppRaterComponentImpl(AppRaterComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(AppRaterComponentBuilder builder) {
            this.provideAppRaterControllerProvider = DoubleCheck.provider(AppRaterModule_ProvideAppRaterControllerFactory.create(DaggerAirbnbComponent.this.appRaterModule, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider));
            this.appRaterDialogFragmentMembersInjector = AppRaterDialogFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, this.provideAppRaterControllerProvider);
        }

        public void inject(AppRaterDialogFragment arg0) {
            this.appRaterDialogFragmentMembersInjector.injectMembers(arg0);
        }

        public AppRaterController appRaterController() {
            return (AppRaterController) this.provideAppRaterControllerProvider.get();
        }
    }

    private final class CheckInComponentBuilder implements com.airbnb.android.checkin.data.CheckInComponent.Builder {
        private CheckInComponentBuilder() {
        }

        public CheckInComponent build() {
            return new CheckInComponentImpl(this);
        }
    }

    private final class CheckInComponentImpl implements CheckInComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<CheckInDataSyncService> checkInDataSyncServiceMembersInjector;
        private MembersInjector<CheckInIntroFragment> checkInIntroFragmentMembersInjector;
        private MembersInjector<CheckinStepFragment> checkinStepFragmentMembersInjector;
        private MembersInjector<CheckinStepPagerFragment> checkinStepPagerFragmentMembersInjector;
        private MembersInjector<ImageViewerActivity> imageViewerActivityMembersInjector;
        private Provider<GuestCheckInJitneyLogger> jitneyLoggerProvider;
        private Provider<CheckInDataTableOpenHelper> provideCheckInDataTableOpenHelperProvider;
        private MembersInjector<ViewCheckinActivity> viewCheckinActivityMembersInjector;

        private CheckInComponentImpl(CheckInComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(CheckInComponentBuilder builder) {
            this.provideCheckInDataTableOpenHelperProvider = DoubleCheck.provider(CheckInModule_ProvideCheckInDataTableOpenHelperFactory.create(DaggerAirbnbComponent.this.checkInModule, DaggerAirbnbComponent.this.provideContextProvider));
            this.jitneyLoggerProvider = DoubleCheck.provider(CheckInModule_JitneyLoggerFactory.create(DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider));
            this.checkInDataSyncServiceMembersInjector = CheckInDataSyncService_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, this.provideCheckInDataTableOpenHelperProvider, this.jitneyLoggerProvider);
            this.viewCheckinActivityMembersInjector = ViewCheckinActivity_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideShakeFeedbackhelperProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideSuperHeroManagerProvider, DaggerAirbnbComponent.this.provideReactInstanceManagerProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideAppLaunchAnalyticsProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.dLSJitneyLoggerProvider, this.provideCheckInDataTableOpenHelperProvider, this.jitneyLoggerProvider);
            this.checkinStepFragmentMembersInjector = CheckinStepFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.jitneyLoggerProvider);
            this.checkInIntroFragmentMembersInjector = CheckInIntroFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.jitneyLoggerProvider);
            this.imageViewerActivityMembersInjector = ImageViewerActivity_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideShakeFeedbackhelperProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideSuperHeroManagerProvider, DaggerAirbnbComponent.this.provideReactInstanceManagerProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideAppLaunchAnalyticsProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.dLSJitneyLoggerProvider, this.jitneyLoggerProvider);
            this.checkinStepPagerFragmentMembersInjector = CheckinStepPagerFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.jitneyLoggerProvider);
        }

        public NetworkMonitor networkMonitor() {
            return (NetworkMonitor) DaggerAirbnbComponent.this.provideNetworkMonitorProvider.get();
        }

        public AffiliateInfo affiliateInfo() {
            return new AffiliateInfo((AirbnbPreferences) DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider.get());
        }

        public MemoryUtils memoryUtils() {
            return (MemoryUtils) DaggerAirbnbComponent.this.provideMemoryUtilsProvider.get();
        }

        public AnalyticsRegistry analyticsRegistry() {
            return (AnalyticsRegistry) DaggerAirbnbComponent.this.provideAnalyticsRegistryProvider.get();
        }

        public Lazy<DynamicStringsResources> dynamicStringsResources() {
            return DoubleCheck.lazy(DaggerAirbnbComponent.this.provideDynamicStringsResourcesProvider);
        }

        public AirbnbApi airbnbApi() {
            return (AirbnbApi) DaggerAirbnbComponent.this.provideAirbnbApiProvider.get();
        }

        public OkHttpClient okHttp() {
            return (OkHttpClient) DaggerAirbnbComponent.this.provideOkHttpClientProvider.get();
        }

        public AirbnbPreferences airbnbPreferences() {
            return (AirbnbPreferences) DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider.get();
        }

        public AirbnbAccountManager accountManager() {
            return (AirbnbAccountManager) DaggerAirbnbComponent.this.provideAccountManagerProvider.get();
        }

        public ResourceManager resourceManager() {
            return (ResourceManager) DaggerAirbnbComponent.this.provideResourceManagerProvider.get();
        }

        public GeocoderBaseUrl geocoderBaseUrl() {
            return (GeocoderBaseUrl) DaggerAirbnbComponent.this.provideGeocoderRequestBaseUrlProvider.get();
        }

        public CurrencyFormatter currencyHelper() {
            return (CurrencyFormatter) DaggerAirbnbComponent.this.provideCurrencyHelperProvider.get();
        }

        public AirbnbEventLogger airbnbEventLogger() {
            return (AirbnbEventLogger) DaggerAirbnbComponent.this.provideAirbnbEventLoggerProvider.get();
        }

        public ExperimentAssignments experimentAssigments() {
            return (ExperimentAssignments) DaggerAirbnbComponent.this.provideExperimentAssigmentsProvider.get();
        }

        public DebugSettings debugSettings() {
            return (DebugSettings) DaggerAirbnbComponent.this.provideDebugSettingsProvider.get();
        }

        public ObjectMapper objectMapper() {
            return (ObjectMapper) DaggerAirbnbComponent.this.provideObjectMapperProvider.get();
        }

        public SingleFireRequestExecutor singleFireRequestExecutor() {
            return (SingleFireRequestExecutor) DaggerAirbnbComponent.this.provideSingleFireRequestExecutorProvider.get();
        }

        public NavigationLogging navigationAnalytics() {
            return (NavigationLogging) DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider.get();
        }

        public LocalPushNotificationManager localPushNotificationManager() {
            return (LocalPushNotificationManager) DaggerAirbnbComponent.this.provideLocalPushNotificationManagerProvider.get();
        }

        public BottomBarController bottomBarController() {
            return (BottomBarController) DaggerAirbnbComponent.this.bottomBarControllerProvider.get();
        }

        public LowBandwidthManager lowBandwidthUtils() {
            return (LowBandwidthManager) DaggerAirbnbComponent.this.provideLowBandwidthUtilsProvider.get();
        }

        public ViewBreadcrumbManager viewBreadcrumbManager() {
            return (ViewBreadcrumbManager) DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider.get();
        }

        public SharedPrefsHelper sharedPrefsHelper() {
            return (SharedPrefsHelper) DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider.get();
        }

        public PerformanceLogger performanceLogger() {
            return (PerformanceLogger) DaggerAirbnbComponent.this.providePerformanceLoggerProvider.get();
        }

        public ExperimentsProvider experimentsProvider() {
            return (ExperimentsProvider) DaggerAirbnbComponent.this.provideExperimentsProvider.get();
        }

        public LoggingContextFactory loggingContextFactory() {
            return (LoggingContextFactory) DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider.get();
        }

        public Bus bus() {
            return (Bus) DaggerAirbnbComponent.this.provideBusProvider.get();
        }

        public Lazy<ComponentManager> componentManager() {
            return DoubleCheck.lazy(CoreModule_ProvideComponentManagerFactory.create());
        }

        public void inject(CheckInDataSyncService arg0) {
            this.checkInDataSyncServiceMembersInjector.injectMembers(arg0);
        }

        public void inject(ViewCheckinActivity arg0) {
            this.viewCheckinActivityMembersInjector.injectMembers(arg0);
        }

        public void inject(CheckinStepFragment arg0) {
            this.checkinStepFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(CheckInIntroFragment arg0) {
            this.checkInIntroFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(ImageViewerActivity arg0) {
            this.imageViewerActivityMembersInjector.injectMembers(arg0);
        }

        public void inject(CheckinStepPagerFragment arg0) {
            this.checkinStepPagerFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class ContentFrameworkComponentBuilder implements com.airbnb.android.contentframework.ContentFrameworkComponent.Builder {
        /* access modifiers changed from: private */
        public ContentFrameworkModule contentFrameworkModule;

        private ContentFrameworkComponentBuilder() {
        }

        public ContentFrameworkComponent build() {
            if (this.contentFrameworkModule == null) {
                this.contentFrameworkModule = new ContentFrameworkModule();
            }
            return new ContentFrameworkComponentImpl(this);
        }
    }

    private final class ContentFrameworkComponentImpl implements ContentFrameworkComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private Provider<AppRaterController> provideAppRaterControllerProvider;
        private Provider<StoryPublishController> provideStoryPublishControllerProvider;
        private MembersInjector<StoryCreationComposerFragment> storyCreationComposerFragmentMembersInjector;
        private MembersInjector<StoryCreationPickTripFragment> storyCreationPickTripFragmentMembersInjector;
        private MembersInjector<StoryDetailViewFragment> storyDetailViewFragmentMembersInjector;

        private ContentFrameworkComponentImpl(ContentFrameworkComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(ContentFrameworkComponentBuilder builder) {
            this.provideAppRaterControllerProvider = DoubleCheck.provider(AppRaterModule_ProvideAppRaterControllerFactory.create(DaggerAirbnbComponent.this.appRaterModule, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider));
            this.storyDetailViewFragmentMembersInjector = StoryDetailViewFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideAppRaterControllerProvider);
            this.storyCreationPickTripFragmentMembersInjector = StoryCreationPickTripFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.providePerformanceLoggerProvider);
            this.provideStoryPublishControllerProvider = DoubleCheck.provider(ContentFrameworkModule_ProvideStoryPublishControllerFactory.create(builder.contentFrameworkModule, DaggerAirbnbComponent.this.provideContextProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider));
            this.storyCreationComposerFragmentMembersInjector = StoryCreationComposerFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideStoryPublishControllerProvider);
        }

        public void inject(StoryDetailViewFragment arg0) {
            this.storyDetailViewFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(StoryCreationPickTripFragment arg0) {
            this.storyCreationPickTripFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(StoryCreationComposerFragment arg0) {
            this.storyCreationComposerFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class ExploreComponentBuilder implements com.airbnb.android.explore.ExploreComponent.Builder {
        /* access modifiers changed from: private */
        public ExploreModule exploreModule;

        private ExploreComponentBuilder() {
        }

        public ExploreComponent build() {
            if (this.exploreModule == null) {
                this.exploreModule = new ExploreModule();
            }
            return new ExploreComponentImpl(this);
        }
    }

    private final class ExploreComponentImpl implements ExploreComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<BaseExploreAdapter> baseExploreAdapterMembersInjector;
        private MembersInjector<ExploreHomesFiltersFragment> exploreHomesFiltersFragmentMembersInjector;
        private MembersInjector<MTExploreFragment> mTExploreFragmentMembersInjector;
        private MembersInjector<MTExploreParentFragment> mTExploreParentFragmentMembersInjector;
        private MembersInjector<MTHomesTabFragment> mTHomesTabFragmentMembersInjector;
        private MembersInjector<MTLocationChinaFragment> mTLocationChinaFragmentMembersInjector;
        private MembersInjector<MTLocationFragment> mTLocationFragmentMembersInjector;
        private MembersInjector<MTTabFragment> mTTabFragmentMembersInjector;
        private Provider<ExploreDataRepository> provideExploreDataRepositoryProvider;
        private Provider<ExplorePerformanceAnalytics> provideExplorePerformanceAnalyticsProvider;

        private ExploreComponentImpl(ExploreComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(ExploreComponentBuilder builder) {
            this.mTExploreFragmentMembersInjector = MTExploreFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider);
            this.exploreHomesFiltersFragmentMembersInjector = ExploreHomesFiltersFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelJitneyLoggerProvider);
            this.baseExploreAdapterMembersInjector = BaseExploreAdapter_MembersInjector.create(DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideInstantPromotionManagerProvider);
            this.provideExploreDataRepositoryProvider = DoubleCheck.provider(ExploreModule_ProvideExploreDataRepositoryFactory.create(builder.exploreModule));
            this.provideExplorePerformanceAnalyticsProvider = DoubleCheck.provider(ExploreModule_ProvideExplorePerformanceAnalyticsFactory.create(builder.exploreModule, DaggerAirbnbComponent.this.providePerformanceLoggerProvider));
            this.mTExploreParentFragmentMembersInjector = MTExploreParentFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideExploreDataRepositoryProvider, this.provideExplorePerformanceAnalyticsProvider, DaggerAirbnbComponent.this.provideBusinessTravelJitneyLoggerProvider, DaggerAirbnbComponent.this.provideErfAnalyticsProvider);
            this.mTTabFragmentMembersInjector = MTTabFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideDebugSettingsProvider);
            this.mTHomesTabFragmentMembersInjector = MTHomesTabFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideDebugSettingsProvider);
            this.mTLocationFragmentMembersInjector = MTLocationFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider);
            this.mTLocationChinaFragmentMembersInjector = MTLocationChinaFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider);
        }

        public NetworkMonitor networkMonitor() {
            return (NetworkMonitor) DaggerAirbnbComponent.this.provideNetworkMonitorProvider.get();
        }

        public AffiliateInfo affiliateInfo() {
            return new AffiliateInfo((AirbnbPreferences) DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider.get());
        }

        public MemoryUtils memoryUtils() {
            return (MemoryUtils) DaggerAirbnbComponent.this.provideMemoryUtilsProvider.get();
        }

        public AnalyticsRegistry analyticsRegistry() {
            return (AnalyticsRegistry) DaggerAirbnbComponent.this.provideAnalyticsRegistryProvider.get();
        }

        public Lazy<DynamicStringsResources> dynamicStringsResources() {
            return DoubleCheck.lazy(DaggerAirbnbComponent.this.provideDynamicStringsResourcesProvider);
        }

        public AirbnbApi airbnbApi() {
            return (AirbnbApi) DaggerAirbnbComponent.this.provideAirbnbApiProvider.get();
        }

        public OkHttpClient okHttp() {
            return (OkHttpClient) DaggerAirbnbComponent.this.provideOkHttpClientProvider.get();
        }

        public AirbnbPreferences airbnbPreferences() {
            return (AirbnbPreferences) DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider.get();
        }

        public AirbnbAccountManager accountManager() {
            return (AirbnbAccountManager) DaggerAirbnbComponent.this.provideAccountManagerProvider.get();
        }

        public ResourceManager resourceManager() {
            return (ResourceManager) DaggerAirbnbComponent.this.provideResourceManagerProvider.get();
        }

        public GeocoderBaseUrl geocoderBaseUrl() {
            return (GeocoderBaseUrl) DaggerAirbnbComponent.this.provideGeocoderRequestBaseUrlProvider.get();
        }

        public CurrencyFormatter currencyHelper() {
            return (CurrencyFormatter) DaggerAirbnbComponent.this.provideCurrencyHelperProvider.get();
        }

        public AirbnbEventLogger airbnbEventLogger() {
            return (AirbnbEventLogger) DaggerAirbnbComponent.this.provideAirbnbEventLoggerProvider.get();
        }

        public ExperimentAssignments experimentAssigments() {
            return (ExperimentAssignments) DaggerAirbnbComponent.this.provideExperimentAssigmentsProvider.get();
        }

        public DebugSettings debugSettings() {
            return (DebugSettings) DaggerAirbnbComponent.this.provideDebugSettingsProvider.get();
        }

        public ObjectMapper objectMapper() {
            return (ObjectMapper) DaggerAirbnbComponent.this.provideObjectMapperProvider.get();
        }

        public SingleFireRequestExecutor singleFireRequestExecutor() {
            return (SingleFireRequestExecutor) DaggerAirbnbComponent.this.provideSingleFireRequestExecutorProvider.get();
        }

        public NavigationLogging navigationAnalytics() {
            return (NavigationLogging) DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider.get();
        }

        public LocalPushNotificationManager localPushNotificationManager() {
            return (LocalPushNotificationManager) DaggerAirbnbComponent.this.provideLocalPushNotificationManagerProvider.get();
        }

        public BottomBarController bottomBarController() {
            return (BottomBarController) DaggerAirbnbComponent.this.bottomBarControllerProvider.get();
        }

        public LowBandwidthManager lowBandwidthUtils() {
            return (LowBandwidthManager) DaggerAirbnbComponent.this.provideLowBandwidthUtilsProvider.get();
        }

        public ViewBreadcrumbManager viewBreadcrumbManager() {
            return (ViewBreadcrumbManager) DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider.get();
        }

        public SharedPrefsHelper sharedPrefsHelper() {
            return (SharedPrefsHelper) DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider.get();
        }

        public PerformanceLogger performanceLogger() {
            return (PerformanceLogger) DaggerAirbnbComponent.this.providePerformanceLoggerProvider.get();
        }

        public ExperimentsProvider experimentsProvider() {
            return (ExperimentsProvider) DaggerAirbnbComponent.this.provideExperimentsProvider.get();
        }

        public LoggingContextFactory loggingContextFactory() {
            return (LoggingContextFactory) DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider.get();
        }

        public Bus bus() {
            return (Bus) DaggerAirbnbComponent.this.provideBusProvider.get();
        }

        public Lazy<ComponentManager> componentManager() {
            return DoubleCheck.lazy(CoreModule_ProvideComponentManagerFactory.create());
        }

        public void inject(MTExploreFragment arg0) {
            this.mTExploreFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(ExploreHomesFiltersFragment arg0) {
            this.exploreHomesFiltersFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(ExplorePriceHistogramRow arg0) {
            MembersInjectors.noOp().injectMembers(arg0);
        }

        public void inject(BaseExploreAdapter arg0) {
            this.baseExploreAdapterMembersInjector.injectMembers(arg0);
        }

        public void inject(MTExploreParentFragment arg0) {
            this.mTExploreParentFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(MTTabFragment arg0) {
            this.mTTabFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(MTHomesTabFragment arg0) {
            this.mTHomesTabFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(ExploreDataController arg0) {
            MembersInjectors.noOp().injectMembers(arg0);
        }

        public void inject(MTLocationFragment arg0) {
            this.mTLocationFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(MTLocationChinaFragment arg0) {
            this.mTLocationChinaFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class GuestRecoveryComponentBuilder implements com.airbnb.android.guestrecovery.GuestRecoveryComponent.Builder {
        private GuestRecoveryComponentBuilder() {
        }

        public GuestRecoveryComponent build() {
            return new GuestRecoveryComponentImpl(this);
        }
    }

    private final class GuestRecoveryComponentImpl implements GuestRecoveryComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<GuestRecoveryFragment> guestRecoveryFragmentMembersInjector;
        private Provider<GuestRecoveryLogger> provideGuestRecoveryLoggerProvider;

        private GuestRecoveryComponentImpl(GuestRecoveryComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(GuestRecoveryComponentBuilder builder) {
            this.provideGuestRecoveryLoggerProvider = DoubleCheck.provider(GuestRecoveryModule_ProvideGuestRecoveryLoggerFactory.create(DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider));
            this.guestRecoveryFragmentMembersInjector = GuestRecoveryFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideGuestRecoveryLoggerProvider);
        }

        public void inject(GuestRecoveryFragment arg0) {
            this.guestRecoveryFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class IdentityComponentBuilder implements com.airbnb.android.identity.IdentityComponent.Builder {
        /* access modifiers changed from: private */
        public SMSMonitorModule sMSMonitorModule;

        private IdentityComponentBuilder() {
        }

        public IdentityComponent build() {
            if (this.sMSMonitorModule == null) {
                this.sMSMonitorModule = new SMSMonitorModule();
            }
            return new IdentityComponentImpl(this);
        }
    }

    private final class IdentityComponentImpl implements IdentityComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());

        /* renamed from: accountVerificationPhoneNumberConfirmationFragmentMembersInjector */
        private MembersInjector<AccountVerificationPhoneNumberConfirmationFragment> f8338xe119ec39;
        private MembersInjector<AccountVerificationPhoneNumberInputFragment> accountVerificationPhoneNumberInputFragmentMembersInjector;
        private Provider<SMSMonitor> provideSMSMonitorProvider;

        private IdentityComponentImpl(IdentityComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(IdentityComponentBuilder builder) {
            this.provideSMSMonitorProvider = DoubleCheck.provider(SMSMonitorModule_ProvideSMSMonitorFactory.create(builder.sMSMonitorModule, DaggerAirbnbComponent.this.provideContextProvider));
            this.f8338xe119ec39 = C6488x2d7e29c2.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideSMSMonitorProvider);
            this.accountVerificationPhoneNumberInputFragmentMembersInjector = AccountVerificationPhoneNumberInputFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideSMSMonitorProvider);
        }

        public void inject(AccountVerificationPhoneNumberConfirmationFragment arg0) {
            this.f8338xe119ec39.injectMembers(arg0);
        }

        public void inject(AccountVerificationPhoneNumberInputFragment arg0) {
            this.accountVerificationPhoneNumberInputFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(IdentitySelfieCaptureFragment arg0) {
            DaggerAirbnbComponent.this.identitySelfieCaptureFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class LibComponentBuilder implements com.airbnb.android.lib.LibComponent.Builder {
        /* access modifiers changed from: private */
        public ExploreModule exploreModule;
        /* access modifiers changed from: private */
        public SMSMonitorModule sMSMonitorModule;

        private LibComponentBuilder() {
        }

        public LibComponent build() {
            if (this.exploreModule == null) {
                this.exploreModule = new ExploreModule();
            }
            if (this.sMSMonitorModule == null) {
                this.sMSMonitorModule = new SMSMonitorModule();
            }
            return new LibComponentImpl(this);
        }
    }

    private final class LibComponentImpl implements LibComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<BookingActivity> bookingActivityMembersInjector;
        private MembersInjector<BookingV2Activity> bookingV2ActivityMembersInjector;
        private MembersInjector<HomeActivity> homeActivityMembersInjector;
        private MembersInjector<com.airbnb.android.lib.china5a.fragments.PhoneVerificationFragment> phoneVerificationFragmentMembersInjector;
        private Provider<AppRaterController> provideAppRaterControllerProvider;
        private Provider<ExplorePerformanceAnalytics> provideExplorePerformanceAnalyticsProvider;
        private Provider<SMSMonitor> provideSMSMonitorProvider;

        private LibComponentImpl(LibComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(LibComponentBuilder builder) {
            this.provideAppRaterControllerProvider = DoubleCheck.provider(AppRaterModule_ProvideAppRaterControllerFactory.create(DaggerAirbnbComponent.this.appRaterModule, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider));
            this.homeActivityMembersInjector = HomeActivity_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideShakeFeedbackhelperProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideSuperHeroManagerProvider, DaggerAirbnbComponent.this.provideReactInstanceManagerProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideAppLaunchAnalyticsProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.dLSJitneyLoggerProvider, DaggerAirbnbComponent.this.provideExperimentConfigControllerProvider, DaggerAirbnbComponent.this.provideAppLaunchUtilsProvider, DaggerAirbnbComponent.this.provideLocationHelperProvider, DaggerAirbnbComponent.this.provideLocalPushNotificationManagerProvider, DaggerAirbnbComponent.this.bottomBarControllerProvider, DaggerAirbnbComponent.this.provideDebugSettingsProvider, this.provideAppRaterControllerProvider, DaggerAirbnbComponent.this.profileCompletionManagerProvider, DaggerAirbnbComponent.this.provideItineraryManagerProvider, DaggerAirbnbComponent.this.provideIdentityJitneyEventLoggerProvider, DaggerAirbnbComponent.this.providePromoControllerProvider, DaggerAirbnbComponent.this.provideLowBandwidthUtilsProvider);
            this.provideExplorePerformanceAnalyticsProvider = DoubleCheck.provider(ExploreModule_ProvideExplorePerformanceAnalyticsFactory.create(builder.exploreModule, DaggerAirbnbComponent.this.providePerformanceLoggerProvider));
            this.bookingActivityMembersInjector = BookingActivity_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideShakeFeedbackhelperProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideSuperHeroManagerProvider, DaggerAirbnbComponent.this.provideReactInstanceManagerProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideAppLaunchAnalyticsProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.dLSJitneyLoggerProvider, this.provideExplorePerformanceAnalyticsProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAirlockErrorHandlerProvider, DaggerAirbnbComponent.this.provideIdentityJitneyEventLoggerProvider);
            this.bookingV2ActivityMembersInjector = BookingV2Activity_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideShakeFeedbackhelperProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideSuperHeroManagerProvider, DaggerAirbnbComponent.this.provideReactInstanceManagerProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideAppLaunchAnalyticsProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.dLSJitneyLoggerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideBookingJitneyEventLoggerProvider, this.provideExplorePerformanceAnalyticsProvider, DaggerAirbnbComponent.this.provideIdentityJitneyEventLoggerProvider);
            this.provideSMSMonitorProvider = DoubleCheck.provider(SMSMonitorModule_ProvideSMSMonitorFactory.create(builder.sMSMonitorModule, DaggerAirbnbComponent.this.provideContextProvider));
            this.phoneVerificationFragmentMembersInjector = PhoneVerificationFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideSMSMonitorProvider);
        }

        public void inject(HomeActivity homeActivity) {
            this.homeActivityMembersInjector.injectMembers(homeActivity);
        }

        public void inject(BookingActivity bookingActivity) {
            this.bookingActivityMembersInjector.injectMembers(bookingActivity);
        }

        public void inject(BookingV2Activity bookingV2Activity) {
            this.bookingV2ActivityMembersInjector.injectMembers(bookingV2Activity);
        }

        public void inject(com.airbnb.android.lib.china5a.fragments.PhoneVerificationFragment phoneVerificationFragment) {
            this.phoneVerificationFragmentMembersInjector.injectMembers(phoneVerificationFragment);
        }
    }

    private final class LoginComponentBuilder implements com.airbnb.android.login.LoginComponent.Builder {
        /* access modifiers changed from: private */
        public SMSMonitorModule sMSMonitorModule;

        private LoginComponentBuilder() {
        }

        public LoginComponent build() {
            if (this.sMSMonitorModule == null) {
                this.sMSMonitorModule = new SMSMonitorModule();
            }
            return new LoginComponentImpl(this);
        }
    }

    private final class LoginComponentImpl implements LoginComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<PhoneForgotPasswordConfirmSMSCodeFragment> phoneForgotPasswordConfirmSMSCodeFragmentMembersInjector;
        private MembersInjector<PhoneForgotPasswordFragment> phoneForgotPasswordFragmentMembersInjector;
        private Provider<SMSMonitor> provideSMSMonitorProvider;

        private LoginComponentImpl(LoginComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(LoginComponentBuilder builder) {
            this.provideSMSMonitorProvider = DoubleCheck.provider(SMSMonitorModule_ProvideSMSMonitorFactory.create(builder.sMSMonitorModule, DaggerAirbnbComponent.this.provideContextProvider));
            this.phoneForgotPasswordConfirmSMSCodeFragmentMembersInjector = PhoneForgotPasswordConfirmSMSCodeFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideSMSMonitorProvider);
            this.phoneForgotPasswordFragmentMembersInjector = PhoneForgotPasswordFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideSMSMonitorProvider);
        }

        public void inject(PhoneForgotPasswordConfirmSMSCodeFragment arg0) {
            this.phoneForgotPasswordConfirmSMSCodeFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(PhoneForgotPasswordFragment arg0) {
            this.phoneForgotPasswordFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class N2ComponentBuilder implements com.airbnb.p027n2.N2Component.Builder {
        /* access modifiers changed from: private */
        public N2Module n2Module;

        private N2ComponentBuilder() {
        }

        public N2Component build() {
            if (this.n2Module == null) {
                this.n2Module = new N2Module();
            }
            return new N2ComponentImpl(this);
        }
    }

    private final class N2ComponentImpl implements N2Component {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private Provider<N2Context> provideN2ContextProvider;
        private Provider<C0977N2> provideN2Provider;
        private Provider<HttpProxyCacheServer> provideVideoCacheProvider;

        private N2ComponentImpl(N2ComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(N2ComponentBuilder builder) {
            this.provideN2Provider = N2Module_ProvideN2Factory.create(builder.n2Module, DaggerAirbnbComponent.this.provideN2CallbacksProvider);
            this.provideN2ContextProvider = N2Module_ProvideN2ContextFactory.create(builder.n2Module, DaggerAirbnbComponent.this.n2ComponentBuilderProvider);
            this.provideVideoCacheProvider = N2Module_ProvideVideoCacheFactory.create(builder.n2Module, DaggerAirbnbComponent.this.provideContextProvider);
        }

        /* renamed from: n2 */
        public C0977N2 mo11971n2() {
            return (C0977N2) this.provideN2Provider.get();
        }

        public N2Context createN2Context() {
            return (N2Context) this.provideN2ContextProvider.get();
        }

        public HttpProxyCacheServer videoCache() {
            return (HttpProxyCacheServer) this.provideVideoCacheProvider.get();
        }
    }

    private final class P3ComponentBuilder implements com.airbnb.android.p011p3.P3Component.Builder {
        private P3ComponentBuilder() {
        }

        public P3Component build() {
            return new P3ComponentImpl(this);
        }
    }

    private final class P3ComponentImpl implements P3Component {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<P3Activity> p3ActivityMembersInjector;
        private MembersInjector<P3Fragment> p3FragmentMembersInjector;
        private MembersInjector<P3ReviewFragment> p3ReviewFragmentMembersInjector;
        private MembersInjector<P3ReviewSearchFragment> p3ReviewSearchFragmentMembersInjector;
        private Provider<AppRaterController> provideAppRaterControllerProvider;

        private P3ComponentImpl(P3ComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(P3ComponentBuilder builder) {
            this.provideAppRaterControllerProvider = DoubleCheck.provider(AppRaterModule_ProvideAppRaterControllerFactory.create(DaggerAirbnbComponent.this.appRaterModule, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider));
            this.p3ActivityMembersInjector = P3Activity_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideShakeFeedbackhelperProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideSuperHeroManagerProvider, DaggerAirbnbComponent.this.provideReactInstanceManagerProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideAppLaunchAnalyticsProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.dLSJitneyLoggerProvider, DaggerAirbnbComponent.this.provideGoogleAppIndexingControllerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideLocalPushNotificationManagerProvider, DaggerAirbnbComponent.this.providePerformanceLoggerProvider, this.provideAppRaterControllerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideIdentityJitneyEventLoggerProvider);
            this.p3ReviewFragmentMembersInjector = P3ReviewFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideReviewSearchJitneyLoggerProvider);
            this.p3ReviewSearchFragmentMembersInjector = P3ReviewSearchFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideReviewSearchJitneyLoggerProvider);
            this.p3FragmentMembersInjector = P3Fragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider);
        }

        public void inject(P3Activity arg0) {
            this.p3ActivityMembersInjector.injectMembers(arg0);
        }

        public void inject(P3AdditionalPriceFragment arg0) {
            DaggerAirbnbComponent.this.p3AdditionalPriceFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(P3ReviewFragment arg0) {
            this.p3ReviewFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(P3ReviewSearchFragment arg0) {
            this.p3ReviewSearchFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(P3Fragment arg0) {
            this.p3FragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class PickWishListComponentBuilder implements com.airbnb.android.pickwishlist.PickWishListComponent.Builder {
        private PickWishListComponentBuilder() {
        }

        public PickWishListComponent build() {
            return new PickWishListComponentImpl(this);
        }
    }

    private final class PickWishListComponentImpl implements PickWishListComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<CreateWishListActivity> createWishListActivityMembersInjector;
        private MembersInjector<PickWishListFragment> pickWishListFragmentMembersInjector;
        private Provider<AppRaterController> provideAppRaterControllerProvider;

        private PickWishListComponentImpl(PickWishListComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(PickWishListComponentBuilder builder) {
            this.provideAppRaterControllerProvider = DoubleCheck.provider(AppRaterModule_ProvideAppRaterControllerFactory.create(DaggerAirbnbComponent.this.appRaterModule, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider));
            this.createWishListActivityMembersInjector = CreateWishListActivity_MembersInjector.create(DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideShakeFeedbackhelperProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideSuperHeroManagerProvider, DaggerAirbnbComponent.this.provideReactInstanceManagerProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideAppLaunchAnalyticsProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.dLSJitneyLoggerProvider, DaggerAirbnbComponent.this.provideWishListLoggerProvider, this.provideAppRaterControllerProvider);
            this.pickWishListFragmentMembersInjector = PickWishListFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, DaggerAirbnbComponent.this.provideWishListLoggerProvider, this.provideAppRaterControllerProvider);
        }

        public void inject(CreateWishListActivity arg0) {
            this.createWishListActivityMembersInjector.injectMembers(arg0);
        }

        public void inject(PickWishListFragment arg0) {
            this.pickWishListFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private final class ReferralsComponentBuilder implements com.airbnb.android.referrals.ReferralsComponent.Builder {
        private ReferralsComponentBuilder() {
        }

        public ReferralsComponent build() {
            return new ReferralsComponentImpl(this);
        }
    }

    private final class ReferralsComponentImpl implements ReferralsComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<ContactUploadIntentService> contactUploadIntentServiceMembersInjector;
        private Provider<AppRaterController> provideAppRaterControllerProvider;
        private MembersInjector<ReferralsFragment> referralsFragmentMembersInjector;
        private MembersInjector<ReferralsOneClickFragment> referralsOneClickFragmentMembersInjector;
        private MembersInjector<SentReferralsFragment> sentReferralsFragmentMembersInjector;

        private ReferralsComponentImpl(ReferralsComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(ReferralsComponentBuilder builder) {
            this.sentReferralsFragmentMembersInjector = SentReferralsFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider);
            this.provideAppRaterControllerProvider = DoubleCheck.provider(AppRaterModule_ProvideAppRaterControllerFactory.create(DaggerAirbnbComponent.this.appRaterModule, DaggerAirbnbComponent.this.provideDebugSettingsProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider));
            this.referralsOneClickFragmentMembersInjector = ReferralsOneClickFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideAppRaterControllerProvider);
            this.referralsFragmentMembersInjector = ReferralsFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideAppRaterControllerProvider);
            this.contactUploadIntentServiceMembersInjector = ContactUploadIntentService_MembersInjector.create(DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider);
        }

        public void inject(SentReferralsFragment arg0) {
            this.sentReferralsFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(ReferralsOneClickFragment arg0) {
            this.referralsOneClickFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(ReferralsFragment arg0) {
            this.referralsFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(ContactUploadIntentService arg0) {
            this.contactUploadIntentServiceMembersInjector.injectMembers(arg0);
        }
    }

    private final class RegistrationComponentBuilder implements com.airbnb.android.registration.RegistrationComponent.Builder {
        /* access modifiers changed from: private */
        public SMSMonitorModule sMSMonitorModule;

        private RegistrationComponentBuilder() {
        }

        public RegistrationComponent build() {
            if (this.sMSMonitorModule == null) {
                this.sMSMonitorModule = new SMSMonitorModule();
            }
            return new RegistrationComponentImpl(this);
        }
    }

    private final class RegistrationComponentImpl implements RegistrationComponent {
        static final /* synthetic */ boolean $assertionsDisabled = (!DaggerAirbnbComponent.class.desiredAssertionStatus());
        private MembersInjector<PhoneNumberRegistrationConfirmationFragment> phoneNumberRegistrationConfirmationFragmentMembersInjector;
        private MembersInjector<PhoneNumberRegistrationFragment> phoneNumberRegistrationFragmentMembersInjector;
        private Provider<SMSMonitor> provideSMSMonitorProvider;

        private RegistrationComponentImpl(RegistrationComponentBuilder builder) {
            if ($assertionsDisabled || builder != null) {
                initialize(builder);
                return;
            }
            throw new AssertionError();
        }

        private void initialize(RegistrationComponentBuilder builder) {
            this.provideSMSMonitorProvider = DoubleCheck.provider(SMSMonitorModule_ProvideSMSMonitorFactory.create(builder.sMSMonitorModule, DaggerAirbnbComponent.this.provideContextProvider));
            this.phoneNumberRegistrationConfirmationFragmentMembersInjector = PhoneNumberRegistrationConfirmationFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideSMSMonitorProvider);
            this.phoneNumberRegistrationFragmentMembersInjector = PhoneNumberRegistrationFragment_MembersInjector.create(DaggerAirbnbComponent.this.provideAirbnbApiProvider, DaggerAirbnbComponent.this.provideWishListManagerProvider, DaggerAirbnbComponent.this.provideBusinessTravelAccountManagerProvider, DaggerAirbnbComponent.this.provideAccountManagerProvider, DaggerAirbnbComponent.this.provideAirbnbPreferencesProvider, DaggerAirbnbComponent.this.provideErfProvider, DaggerAirbnbComponent.this.provideSharedPrefsHelperProvider, DaggerAirbnbComponent.this.provideMemoryUtilsProvider, DaggerAirbnbComponent.this.provideBusProvider, DaggerAirbnbComponent.this.provideCurrencyHelperProvider, DaggerAirbnbComponent.this.provideKonaNavigationAnalyticsProvider, DaggerAirbnbComponent.this.provideAirRequestInitializerProvider, DaggerAirbnbComponent.this.provideLoggingContextFactoryProvider, DaggerAirbnbComponent.this.provideClientSessionValidatorProvider, DaggerAirbnbComponent.this.provideViewBreadcrumbManagerProvider, DaggerAirbnbComponent.this.provideResourceManagerProvider, this.provideSMSMonitorProvider);
        }

        public void inject(PhoneNumberRegistrationConfirmationFragment arg0) {
            this.phoneNumberRegistrationConfirmationFragmentMembersInjector.injectMembers(arg0);
        }

        public void inject(PhoneNumberRegistrationFragment arg0) {
            this.phoneNumberRegistrationFragmentMembersInjector.injectMembers(arg0);
        }
    }

    private DaggerAirbnbComponent(Builder builder) {
        if ($assertionsDisabled || builder != null) {
            initialize(builder);
            initialize2(builder);
            initialize3(builder);
            initialize4(builder);
            initialize5(builder);
            this.appRaterModule = builder.appRaterModule;
            this.checkInModule = builder.checkInModule;
            return;
        }
        throw new AssertionError();
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideContextProvider = DoubleCheck.provider(CoreModule_ProvideContextFactory.create(builder.coreModule));
        this.provideNetworkMonitorProvider = DoubleCheck.provider(NetworkModule_ProvideNetworkMonitorFactory.create(builder.networkModule, this.provideContextProvider));
        this.provideAirbnbPreferencesProvider = DoubleCheck.provider(CoreModule_ProvideAirbnbPreferencesFactory.create(this.provideContextProvider));
        this.affiliateInfoProvider = AffiliateInfo_Factory.create(this.provideAirbnbPreferencesProvider);
        this.provideMemoryUtilsProvider = DoubleCheck.provider(CoreModule_ProvideMemoryUtilsFactory.create(this.provideAirbnbPreferencesProvider));
        this.provideAnalyticsRegistryProvider = DoubleCheck.provider(CoreModule_ProvideAnalyticsRegistryFactory.create());
        this.provideDynamicStringsResourcesProvider = DoubleCheck.provider(CoreModule_ProvideDynamicStringsResourcesFactory.create(this.provideContextProvider, this.provideAirbnbPreferencesProvider));
        this.provideCacheProvider = DoubleCheck.provider(NetworkModule_ProvideCacheFactory.create(builder.networkModule, this.provideContextProvider));
        this.provideAndroidAccountManagerProvider = DoubleCheck.provider(CoreModule_ProvideAndroidAccountManagerFactory.create(this.provideContextProvider));
        this.provideAccountManagerProvider = DoubleCheck.provider(CoreModule_ProvideAccountManagerFactory.create(builder.coreModule, this.provideAndroidAccountManagerProvider, this.provideAirbnbPreferencesProvider));
        this.provideBusProvider = DoubleCheck.provider(CoreModule_ProvideBusFactory.create());
        this.provideInboxUnreadCountManagerProvider = DoubleCheck.provider(CoreModule_ProvideInboxUnreadCountManagerFactory.create(builder.coreModule, this.provideBusProvider));
        this.provideMessageStoreTableOpenHelperProvider = CoreModule_ProvideMessageStoreTableOpenHelperFactory.create(this.provideContextProvider, CoreModule_ProvideThreadDataMapperFactory.create(), this.provideInboxUnreadCountManagerProvider);
        this.provideMessageStoreProvider = DoubleCheck.provider(CoreModule_ProvideMessageStoreFactory.create(builder.coreModule, this.provideMessageStoreTableOpenHelperProvider));
        this.provideDeviceIDProvider = DoubleCheck.provider(CoreModule_ProvideDeviceIDFactory.create(this.provideContextProvider));
        this.provideAirbnbApiProvider = DoubleCheck.provider(CoreModule_ProvideAirbnbApiFactory.create(builder.coreModule, this.provideContextProvider, this.provideAirbnbPreferencesProvider, this.provideMessageStoreProvider, this.provideAccountManagerProvider, this.provideBusProvider, this.provideCacheProvider, this.provideDeviceIDProvider));
        this.provideSharedPrefsHelperProvider = DoubleCheck.provider(CoreModule_ProvideSharedPrefsHelperFactory.create(this.provideAirbnbPreferencesProvider));
        this.provideEndpointProvider = DoubleCheck.provider(NetworkModule_ProvideEndpointFactory.create(builder.networkModule));
        this.provideUrlMatcherProvider = DoubleCheck.provider(NetworkModule_ProvideUrlMatcherFactory.create(this.provideEndpointProvider));
        this.provideApiRequestHeadersInterceptorProvider = DoubleCheck.provider(NetworkModule_ProvideApiRequestHeadersInterceptorFactory.create(builder.networkModule, this.provideContextProvider, this.provideAccountManagerProvider, this.provideAirbnbApiProvider, this.provideSharedPrefsHelperProvider, this.affiliateInfoProvider, this.provideUrlMatcherProvider));
        this.provideNetworkInterceptorsProvider = DoubleCheck.provider(NetworkModule_ProvideNetworkInterceptorsFactory.create(builder.networkModule, this.provideApiRequestHeadersInterceptorProvider));
        this.provideCurrencyHelperProvider = DoubleCheck.provider(CoreModule_ProvideCurrencyHelperFactory.create(this.provideContextProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider));
        this.provideQueryParamsProvider = DoubleCheck.provider(NetworkModule_ProvideQueryParamsProviderFactory.create(builder.networkModule, this.provideContextProvider, this.provideCurrencyHelperProvider, this.provideEndpointProvider));
        this.provideApplicationInterceptorsProvider = DoubleCheck.provider(NetworkModule_ProvideApplicationInterceptorsFactory.create(builder.networkModule, this.provideEndpointProvider, this.provideQueryParamsProvider));
        this.provideReactCookieJarContainerProvider = DoubleCheck.provider(ReactModule_ProvideReactCookieJarContainerFactory.create(builder.reactModule));
        this.provideOkHttpClientProvider = DoubleCheck.provider(NetworkModule_ProvideOkHttpClientFactory.create(builder.networkModule, this.provideCacheProvider, this.provideNetworkInterceptorsProvider, this.provideApplicationInterceptorsProvider, this.provideReactCookieJarContainerProvider));
        this.provideResourceManagerProvider = DoubleCheck.provider(CoreModule_ProvideResourceManagerFactory.create(builder.coreModule, this.provideContextProvider));
        this.provideGeocoderRequestBaseUrlProvider = DoubleCheck.provider(NetworkModule_ProvideGeocoderRequestBaseUrlFactory.create(builder.networkModule));
        this.provideNetworkTimeProvider = DoubleCheck.provider(NetworkModule_ProvideNetworkTimeProviderFactory.create(builder.networkModule));
        this.provideTimeSkewAnalyticsProvider = DoubleCheck.provider(AnalyticsModule_ProvideTimeSkewAnalyticsFactory.create(builder.analyticsModule, this.provideAirbnbPreferencesProvider, this.provideNetworkTimeProvider));
        this.provideClientSessionManagerProvider = DoubleCheck.provider(CoreModule_ProvideClientSessionManagerFactory.create(builder.coreModule, this.provideAirbnbPreferencesProvider));
        this.provideLoggingContextFactoryProvider = DoubleCheck.provider(CoreModule_ProvideLoggingContextFactoryFactory.create(builder.coreModule, this.provideContextProvider, this.provideDeviceIDProvider, this.provideAccountManagerProvider, this.affiliateInfoProvider, this.provideAirbnbPreferencesProvider, this.provideTimeSkewAnalyticsProvider, this.provideClientSessionManagerProvider));
        this.provideAirbnbEventLoggerProvider = DoubleCheck.provider(CoreModule_ProvideAirbnbEventLoggerFactory.create(builder.coreModule, this.provideAccountManagerProvider, this.affiliateInfoProvider, this.provideTimeSkewAnalyticsProvider, this.provideLoggingContextFactoryProvider));
        this.provideObjectMapperProvider = DoubleCheck.provider(DataModule_ProvideObjectMapperFactory.create(builder.dataModule));
        this.provideErfExperimentsTableOpenHelperProvider = DoubleCheck.provider(CoreModule_ProvideErfExperimentsTableOpenHelperFactory.create(builder.coreModule, this.provideContextProvider, this.provideObjectMapperProvider));
        this.provideExperimentsProvider = DoubleCheck.provider(CoreModule_ProvideExperimentsProviderFactory.create(builder.coreModule, this.provideContextProvider, this.provideBusProvider, this.provideErfExperimentsTableOpenHelperProvider));
        this.provideErfAnalyticsProvider = DoubleCheck.provider(CoreModule_ProvideErfAnalyticsFactory.create(this.provideDeviceIDProvider, this.provideAccountManagerProvider));
        this.provideErfCallbacksProvider = DoubleCheck.provider(CoreModule_ProvideErfCallbacksFactory.create(this.provideAccountManagerProvider, this.provideErfAnalyticsProvider, this.provideExperimentsProvider));
        this.provideErfProvider = DoubleCheck.provider(CoreModule_ProvideErfFactory.create(this.provideExperimentsProvider, this.provideErfCallbacksProvider));
        this.provideExperimentAssigmentsProvider = DoubleCheck.provider(CoreModule_ProvideExperimentAssigmentsFactory.create(builder.coreModule, this.provideBusProvider, this.provideErfProvider));
        this.provideCallAdapterFactoryProvider = DoubleCheck.provider(NetworkModule_ProvideCallAdapterFactoryFactory.create(builder.networkModule));
        this.provideRequestCallbackExecutorProvider = DoubleCheck.provider(NetworkModule_ProvideRequestCallbackExecutorFactory.create(builder.networkModule));
        this.provideConverterFactoryProvider = DoubleCheck.provider(DataModule_ProvideConverterFactoryFactory.create(builder.dataModule, this.provideObjectMapperProvider));
        this.provideRetrofitBuilderProvider = DoubleCheck.provider(NetworkModule_ProvideRetrofitBuilderFactory.create(builder.networkModule, this.provideOkHttpClientProvider, this.provideCallAdapterFactoryProvider, this.provideRequestCallbackExecutorProvider, this.provideConverterFactoryProvider, this.provideEndpointProvider));
        this.provideRestAdapterProvider = DoubleCheck.provider(NetworkModule_ProvideRestAdapterFactory.create(builder.networkModule, this.provideRetrofitBuilderProvider));
        this.provideRequestHeadersProvider = DoubleCheck.provider(NetworkModule_ProvideRequestHeadersFactory.create(builder.networkModule, this.provideSharedPrefsHelperProvider));
        this.provideObservableManagerProvider = DoubleCheck.provider(NetworkModule_ProvideObservableManagerFactory.create(builder.networkModule));
        this.provideAirlockErrorHandlerProvider = DoubleCheck.provider(NetworkModule_ProvideAirlockErrorHandlerFactory.create(builder.networkModule, this.provideContextProvider, this.provideAccountManagerProvider, this.provideObjectMapperProvider));
        this.provideAirRequestInitializerProvider = DoubleCheck.provider(NetworkModule_ProvideAirRequestInitializerFactory.create(builder.networkModule, this.provideContextProvider, this.provideRestAdapterProvider, this.provideRequestHeadersProvider, this.provideObservableManagerProvider, this.provideExperimentsProvider, this.provideAirlockErrorHandlerProvider, this.provideLoggingContextFactoryProvider, this.provideSharedPrefsHelperProvider));
        this.provideSingleFireRequestExecutorProvider = DoubleCheck.provider(NetworkModule_ProvideSingleFireRequestExecutorFactory.create(builder.networkModule, this.provideAirRequestInitializerProvider));
        this.provideDebugSettingsProvider = DoubleCheck.provider(CoreModule_ProvideDebugSettingsFactory.create(builder.coreModule, this.provideContextProvider, this.provideBusProvider));
        this.provideKonaNavigationAnalyticsProvider = DoubleCheck.provider(CoreModule_ProvideKonaNavigationAnalyticsFactory.create(builder.coreModule, this.provideContextProvider, this.provideDebugSettingsProvider, this.provideSharedPrefsHelperProvider, this.provideLoggingContextFactoryProvider));
        this.provideLowBandwidthUtilsProvider = DoubleCheck.provider(NetworkModule_ProvideLowBandwidthUtilsFactory.create(builder.networkModule, this.provideAirbnbPreferencesProvider, this.provideBusProvider, this.provideNetworkMonitorProvider));
        this.providePerformanceLoggerProvider = DoubleCheck.provider(CoreModule_ProvidePerformanceLoggerFactory.create(builder.coreModule, this.provideLoggingContextFactoryProvider, this.provideSharedPrefsHelperProvider));
        this.authorizedAccountHelperMembersInjector = AuthorizedAccountHelper_MembersInjector.create(this.provideAirbnbApiProvider, this.provideAirbnbPreferencesProvider, this.provideAccountManagerProvider);
        this.currenciesRequestMembersInjector = CurrenciesRequest_MembersInjector.create(this.provideCurrencyHelperProvider, this.provideAirbnbPreferencesProvider);
        this.getActiveAccountRequestMembersInjector = GetActiveAccountRequest_MembersInjector.create(this.provideCurrencyHelperProvider, this.provideAccountManagerProvider);
        this.authorizeServiceRequestMembersInjector = AuthorizeServiceRequest_MembersInjector.create(this.provideAccountManagerProvider, this.provideAirbnbApiProvider);
        this.setProfilePhotoRequestMembersInjector = SetProfilePhotoRequest_MembersInjector.create(this.provideAccountManagerProvider, this.provideBusProvider);
        this.sFRPartnerTaskMembersInjector = SFRPartnerTask_MembersInjector.create(this.affiliateInfoProvider);
        this.dTKPartnerTaskMembersInjector = DTKPartnerTask_MembersInjector.create(this.affiliateInfoProvider);
        this.provideWishListLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideWishListLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.provideWishListManagerProvider = DoubleCheck.provider(CoreModule_ProvideWishListManagerFactory.create(this.provideContextProvider, this.provideAccountManagerProvider, this.provideBusProvider, this.provideAirRequestInitializerProvider));
        this.wishListHeartControllerMembersInjector = WishListHeartController_MembersInjector.create(this.provideWishListLoggerProvider, this.provideWishListManagerProvider);
        this.airBatchRequestMembersInjector = AirBatchRequest_MembersInjector.create(this.provideConverterFactoryProvider);
        this.provideDomainStoreProvider = DoubleCheck.provider(CoreModule_ProvideDomainStoreFactory.create(builder.coreModule, this.provideContextProvider));
        this.airCookieManagerMembersInjector = AirCookieManager_MembersInjector.create(this.provideDomainStoreProvider);
        this.localPushNotificationManagerMembersInjector = LocalPushNotificationManager_MembersInjector.create(this.provideDebugSettingsProvider);
        this.deleteOauthTokenRequestMembersInjector = DeleteOauthTokenRequest_MembersInjector.create(this.provideAccountManagerProvider);
        this.airBatchRequestObserverMembersInjector = AirBatchRequestObserver_MembersInjector.create(this.provideConverterFactoryProvider);
        this.animatedDrawableViewMembersInjector = AnimatedDrawableView_MembersInjector.create(this.provideMemoryUtilsProvider);
        this.zenDialogMembersInjector = ZenDialog_MembersInjector.create(this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideAirbnbApiProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideResourceManagerProvider);
        this.providePhoneUtilProvider = DoubleCheck.provider(CoreModule_ProvidePhoneUtilFactory.create(this.provideContextProvider));
        this.providePhoneNumberUtilProvider = DoubleCheck.provider(CoreModule_ProvidePhoneNumberUtilFactory.create());
        this.phoneNumberInputSheetMembersInjector = PhoneNumberInputSheet_MembersInjector.create(this.providePhoneUtilProvider, this.providePhoneNumberUtilProvider);
        this.pushNotificationBuilderMembersInjector = PushNotificationBuilder_MembersInjector.create(this.provideAirbnbPreferencesProvider);
        this.airDialogFragmentMembersInjector = AirDialogFragment_MembersInjector.create(this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider);
        this.locationTypeaheadFilterForChinaMembersInjector = LocationTypeaheadFilterForChina_MembersInjector.create(this.provideObjectMapperProvider);
        this.listingsTrayMembersInjector = ListingsTray_MembersInjector.create(this.provideWishListManagerProvider);
        this.instantBookUpsellManagerMembersInjector = InstantBookUpsellManager_MembersInjector.create(this.provideSharedPrefsHelperProvider);
        this.provideImageUtilsProvider = DoubleCheck.provider(ImageModule_ProvideImageUtilsFactory.create(builder.imageModule));
        this.oldManageListingPhotoUploadServiceMembersInjector = OldManageListingPhotoUploadService_MembersInjector.create(this.provideAirbnbApiProvider, this.provideImageUtilsProvider, this.provideBusProvider);
        this.resourceManagerMembersInjector = ResourceManager_MembersInjector.create(this.provideAirbnbPreferencesProvider, this.provideErfAnalyticsProvider, this.provideAccountManagerProvider);
        this.loopingViewPagerMembersInjector = LoopingViewPager_MembersInjector.create(this.provideMemoryUtilsProvider);
        this.provideAvailabilityCalendarJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideAvailabilityCalendarJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.verticalCalendarAdapterMembersInjector = VerticalCalendarAdapter_MembersInjector.create(this.provideAvailabilityCalendarJitneyLoggerProvider);
        this.provideShakeFeedbackhelperProvider = DoubleCheck.provider(CoreModule_ProvideShakeFeedbackhelperFactory.create(this.provideAirbnbPreferencesProvider));
        this.provideSuperHeroTableOpenHelperProvider = DoubleCheck.provider(SuperHeroModule_ProvideSuperHeroTableOpenHelperFactory.create(builder.superHeroModule, this.provideContextProvider));
        this.provideSuperHeroManagerProvider = DoubleCheck.provider(SuperHeroModule_ProvideSuperHeroManagerFactory.create(builder.superHeroModule, this.provideContextProvider, this.provideSuperHeroTableOpenHelperProvider, this.provideLoggingContextFactoryProvider));
        this.provideApplicationProvider = DoubleCheck.provider(CoreModule_ProvideApplicationFactory.create(builder.coreModule));
        this.provideReactNativeCoordinatorProvider = DoubleCheck.provider(ReactModule_ProvideReactNativeCoordinatorFactory.create(builder.reactModule, this.provideObjectMapperProvider));
        this.provideItineraryTableOpenHelperProvider = DoubleCheck.provider(ItineraryModule_ProvideItineraryTableOpenHelperFactory.create(builder.itineraryModule, this.provideContextProvider));
        this.provideItineraryManagerProvider = DoubleCheck.provider(ItineraryModule_ProvideItineraryManagerFactory.create(builder.itineraryModule, this.provideItineraryTableOpenHelperProvider, this.providePerformanceLoggerProvider, this.provideSharedPrefsHelperProvider));
        this.provideReactNativeModuleFactoryProvider = DoubleCheck.provider(ReactModule_ProvideReactNativeModuleFactoryFactory.create(builder.reactModule, this.provideAccountManagerProvider, this.provideExperimentsProvider, this.provideCurrencyHelperProvider, this.provideBusProvider, this.provideOkHttpClientProvider, this.provideReactNativeCoordinatorProvider, this.provideWishListManagerProvider, this.provideSuperHeroManagerProvider, this.provideRestAdapterProvider, this.provideWishListLoggerProvider, this.provideNetworkMonitorProvider, this.provideItineraryManagerProvider, this.provideAirlockErrorHandlerProvider));
        this.provideReactInstanceManagerProvider = DoubleCheck.provider(ReactModule_ProvideReactInstanceManagerFactory.create(builder.reactModule, this.provideContextProvider, this.provideApplicationProvider, this.provideReactNativeModuleFactoryProvider, this.provideReactNativeCoordinatorProvider, this.providePerformanceLoggerProvider, this.provideLoggingContextFactoryProvider, this.provideSharedPrefsHelperProvider));
        this.provideViewBreadcrumbManagerProvider = DoubleCheck.provider(CoreModule_ProvideViewBreadcrumbManagerFactory.create(builder.coreModule));
        this.provideAppLaunchAnalyticsProvider = DoubleCheck.provider(CoreModule_ProvideAppLaunchAnalyticsFactory.create(this.providePerformanceLoggerProvider, this.provideAirbnbPreferencesProvider));
        this.dLSJitneyLoggerProvider = DLSJitneyLogger_Factory.create(MembersInjectors.noOp(), this.provideLoggingContextFactoryProvider);
        this.provideIdentityJitneyEventLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideIdentityJitneyEventLoggerFactory.create(this.provideLoggingContextFactoryProvider, this.provideObjectMapperProvider));
        this.replaceVerifiedIdWithIdentityActivityMembersInjector = ReplaceVerifiedIdWithIdentityActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideIdentityJitneyEventLoggerProvider);
    }

    private void initialize2(Builder builder) {
        this.provideBusinessTravelAccountManagerProvider = DoubleCheck.provider(CoreModule_ProvideBusinessTravelAccountManagerFactory.create(this.provideAccountManagerProvider, this.provideBusProvider));
        this.provideClientSessionValidatorProvider = DoubleCheck.provider(CoreModule_ProvideClientSessionValidatorFactory.create(builder.coreModule, this.provideAirbnbPreferencesProvider, this.provideLoggingContextFactoryProvider));
        this.lottieNuxViewPagerFragmentMembersInjector = LottieNuxViewPagerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.provideApolloClientProvider = DoubleCheck.provider(NetworkModule_ProvideApolloClientFactory.create(builder.networkModule, this.provideContextProvider, this.provideOkHttpClientProvider));
        this.provideGraphistClientProvider = DoubleCheck.provider(NetworkModule_ProvideGraphistClientFactory.create(builder.networkModule, this.provideApolloClientProvider));
        this.dLSCancellationPolicyFragmentMembersInjector = DLSCancellationPolicyFragment_MembersInjector.create(this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideGraphistClientProvider, this.providePerformanceLoggerProvider);
        this.resyControllerMembersInjector = ResyController_MembersInjector.create(this.provideLoggingContextFactoryProvider);
        this.reactNativeActivityMembersInjector = ReactNativeActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideReactNativeCoordinatorProvider, this.provideLoggingContextFactoryProvider);
        this.reactNativeFragmentMembersInjector = ReactNativeFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideReactNativeCoordinatorProvider, this.provideReactInstanceManagerProvider, this.providePerformanceLoggerProvider);
        this.shareableMembersInjector = Shareable_MembersInjector.create(this.provideAccountManagerProvider, this.provideAirbnbApiProvider);
        this.wishListDetailsParentFragmentMembersInjector = WishListDetailsParentFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideWishListLoggerProvider);
        this.wishListsFragmentMembersInjector = WishListsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.bottomBarControllerProvider = DoubleCheck.provider(BottomBarController_Factory.create());
        this.baseWishListDetailsFragmentMembersInjector = BaseWishListDetailsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.bottomBarControllerProvider);
        this.superHeroAlarmReceiverMembersInjector = SuperHeroAlarmReceiver_MembersInjector.create(this.provideSuperHeroManagerProvider);
        this.superHeroThreadFragmentMembersInjector = SuperHeroThreadFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideSuperHeroTableOpenHelperProvider, this.provideSuperHeroManagerProvider);
        this.provideQuickPayJitneyEventLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideQuickPayJitneyEventLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.selectCountryFragmentMembersInjector = SelectCountryFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayJitneyEventLoggerProvider);
        this.selectPaymentMethodFragmentMembersInjector = SelectPaymentMethodFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayJitneyEventLoggerProvider);
        this.creditCardBaseFragmentMembersInjector = CreditCardBaseFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayJitneyEventLoggerProvider);
        this.baseAlipayFragmentMembersInjector = BaseAlipayFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayJitneyEventLoggerProvider);
        this.bookingSummaryFragmentMembersInjector = BookingSummaryFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.provideConfigProvider = DoubleCheck.provider(RiskModule_ProvideConfigFactory.create(builder.riskModule, this.provideContextProvider));
        this.provideThreatMetrixClientProvider = DoubleCheck.provider(RiskModule_ProvideThreatMetrixClientFactory.create(builder.riskModule, this.provideAccountManagerProvider, this.provideConfigProvider));
        this.provideMessagingJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideMessagingJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.provideSyncRequestFactoryProvider = DoubleCheck.provider(CoreModule_ProvideSyncRequestFactoryFactory.create(builder.coreModule, this.provideMessageStoreProvider, this.provideAirRequestInitializerProvider, this.provideBusProvider, this.provideMessagingJitneyLoggerProvider));
        this.providePhotoCompressorProvider = MisnapModule_ProvidePhotoCompressorFactory.create(builder.misnapModule, this.provideContextProvider);
        this.provideMessageStoreRequestFactoryProvider = DoubleCheck.provider(CoreModule_ProvideMessageStoreRequestFactoryFactory.create(this.provideContextProvider, this.provideBusProvider, this.provideAccountManagerProvider, this.provideMessageStoreProvider, this.provideSyncRequestFactoryProvider, this.provideMessagingJitneyLoggerProvider, this.providePhotoCompressorProvider, this.provideInboxUnreadCountManagerProvider));
        this.accountVerificationActivityMembersInjector = AccountVerificationActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.accountVerificationStartActivityMembersInjector = AccountVerificationStartActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.airbnbTakeSelfieActivityMembersInjector = AirbnbTakeSelfieActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.provideChooseProfilePhotoControllerProvider = CoreModule_ProvideChooseProfilePhotoControllerFactory.create(this.provideContextProvider, this.providePhotoCompressorProvider);
        this.accountVerificationProfilePhotoFragmentMembersInjector = AccountVerificationProfilePhotoFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideChooseProfilePhotoControllerProvider);
        this.provideTakeSelfieControllerProvider = MisnapModule_ProvideTakeSelfieControllerFactory.create(builder.misnapModule, this.provideContextProvider, this.providePhotoCompressorProvider);
        this.accountVerificationSelfieFragmentMembersInjector = AccountVerificationSelfieFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideTakeSelfieControllerProvider);
        this.identitySelfieCaptureFragmentMembersInjector = IdentitySelfieCaptureFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideTakeSelfieControllerProvider);
        this.accountVerificationSelfieConfirmFragmentMembersInjector = AccountVerificationSelfieConfirmFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideTakeSelfieControllerProvider);
        this.miSnapTakeSelfieActivityMembersInjector = MiSnapTakeSelfieActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.miSnapIdentityCaptureActivityMembersInjector = MiSnapIdentityCaptureActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.manageListingDetailsEpoxyControllerMembersInjector = ManageListingDetailsEpoxyController_MembersInjector.create(this.provideSharedPrefsHelperProvider);
        this.manageListingPickerFragmentMembersInjector = ManageListingPickerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.bottomBarControllerProvider);
        this.manageListingBookingsAdapterMembersInjector = ManageListingBookingsAdapter_MembersInjector.create(this.provideDebugSettingsProvider);
        this.photoUploadManagerProvider = DoubleCheck.provider(ListingModule_PhotoUploadManagerFactory.create(this.provideContextProvider));
        this.managePhotosFragmentMembersInjector = ManagePhotosFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.photoUploadManagerProvider);
        this.manageListingDataControllerMembersInjector = ManageListingDataController_MembersInjector.create(this.photoUploadManagerProvider, this.provideAccountManagerProvider);
        this.provideCohostingManagementJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideCohostingManagementJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.checkInJitneyLoggerProvider = DoubleCheck.provider(ManageListingModule_CheckInJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.dlsManageListingActivityMembersInjector = DlsManageListingActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideCohostingManagementJitneyLoggerProvider, this.checkInJitneyLoggerProvider);
        this.manageListingSettingsTabFragmentMembersInjector = ManageListingSettingsTabFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.manageListingLocalLawsFragmentMembersInjector = ManageListingLocalLawsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.provideCalendarStoreProvider = DoubleCheck.provider(CoreModule_ProvideCalendarStoreFactory.create(CoreModule_ProvideCalendarStoreCacheFactory.create(), CoreModule_ProvideCalendarStoreConfigFactory.create(), this.provideBusProvider));
        this.manageListingNightlyPriceSettingsFragmentMembersInjector = ManageListingNightlyPriceSettingsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider);
        this.manageListingDiscountsSettingsFragmentMembersInjector = ManageListingDiscountsSettingsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.manageListingLastMinuteDiscountFragmentMembersInjector = ManageListingLastMinuteDiscountFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.manageListingEarlyBirdDiscountFragmentMembersInjector = ManageListingEarlyBirdDiscountFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.checkinNoteTextSettingFragmentMembersInjector = CheckinNoteTextSettingFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.checkInJitneyLoggerProvider);
        this.manageListingCheckInGuideFragmentMembersInjector = ManageListingCheckInGuideFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.checkInJitneyLoggerProvider, this.photoUploadManagerProvider);
        this.manageListingAllCheckinMethodsFragmentMembersInjector = ManageListingAllCheckinMethodsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.checkInJitneyLoggerProvider);
        this.manageListingCheckinTypeTextSettingFragmentMembersInjector = ManageListingCheckinTypeTextSettingFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.checkInJitneyLoggerProvider);
        this.lYSHomeActivityMembersInjector = LYSHomeActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.lysJitneyLoggerProvider = DoubleCheck.provider(ListYourSpaceDLSModule_LysJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.lYSDataControllerMembersInjector = LYSDataController_MembersInjector.create(this.photoUploadManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSPhotoStartFragmentMembersInjector = LYSPhotoStartFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.photoUploadManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSPhotoManagerFragmentMembersInjector = LYSPhotoManagerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.photoUploadManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSLocalLawsFragmentMembersInjector = LYSLocalLawsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSSmartPricingFragmentMembersInjector = LYSSmartPricingFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.lYSBasePriceFragmentMembersInjector = LYSBasePriceFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.lYSDiscountsFragmentMembersInjector = LYSDiscountsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.lYSRoomsAndGuestsFragmentMembersInjector = LYSRoomsAndGuestsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.providePromoControllerProvider = DoubleCheck.provider(CoreModule_ProvidePromoControllerFactory.create(this.provideAccountManagerProvider));
        this.lYSAddressFragmentMembersInjector = LYSAddressFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider, this.providePromoControllerProvider);
        this.lYSHostingFrequencyFragmentMembersInjector = LYSHostingFrequencyFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSRentHistoryFragmentMembersInjector = LYSRentHistoryFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSPublishFragmentMembersInjector = LYSPublishFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider, this.provideCalendarStoreProvider, this.providePromoControllerProvider);
        this.lYSSpaceTypeFragmentMembersInjector = LYSSpaceTypeFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSCalendarFragmentMembersInjector = LYSCalendarFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider);
        this.lYSGuestBookFragmentMembersInjector = LYSGuestBookFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSPhotoDetailFragmentMembersInjector = LYSPhotoDetailFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSRTBChecklistFragmentMembersInjector = LYSRTBChecklistFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSSelectPricingTypeFragmentMembersInjector = LYSSelectPricingTypeFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.lYSCharacterCountMarqueeFragmentMembersInjector = LYSCharacterCountMarqueeFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePromoControllerProvider);
        this.lYSLandingFragmentMembersInjector = LYSLandingFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.lysJitneyLoggerProvider);
        this.nightlyPriceAdapterMembersInjector = NightlyPriceAdapter_MembersInjector.create(this.provideLoggingContextFactoryProvider);
        this.basePriceAdapterMembersInjector = BasePriceAdapter_MembersInjector.create(this.provideLoggingContextFactoryProvider);
        this.provideWhatsMyPlaceWorthLoggerProvider = ListingModule_ProvideWhatsMyPlaceWorthLoggerFactory.create(this.provideLoggingContextFactoryProvider);
        this.whatsMyPlaceWorthFragmentMembersInjector = WhatsMyPlaceWorthFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideWhatsMyPlaceWorthLoggerProvider);
        this.provideAddressAutoCompleteLoggerProvider = ListingModule_ProvideAddressAutoCompleteLoggerFactory.create(this.provideLoggingContextFactoryProvider);
        this.addressAutoCompleteFragmentMembersInjector = AddressAutoCompleteFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideAddressAutoCompleteLoggerProvider);
        this.provideCalendarJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideCalendarJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.calendarWithPriceTipsUpdateFragmentMembersInjector = CalendarWithPriceTipsUpdateFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider, this.provideCalendarJitneyLoggerProvider);
        this.calendarUpdateNotesFragmentMembersInjector = CalendarUpdateNotesFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider, this.provideCalendarJitneyLoggerProvider);
        this.singleCalendarBaseFragmentMembersInjector = SingleCalendarBaseFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider, this.provideCalendarJitneyLoggerProvider);
        this.agendaCalendarFragmentMembersInjector = AgendaCalendarFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarJitneyLoggerProvider);
        this.calendarFragmentMembersInjector = CalendarFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.bottomBarControllerProvider);
        this.singleCalendarFragmentMembersInjector = SingleCalendarFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarJitneyLoggerProvider, this.provideCalendarStoreProvider);
        this.calendarAgendaAdapterMembersInjector = CalendarAgendaAdapter_MembersInjector.create(this.provideCalendarJitneyLoggerProvider);
        this.calendarDetailAdapterMembersInjector = CalendarDetailAdapter_MembersInjector.create(this.provideCalendarJitneyLoggerProvider);
        this.calendarNestedBusyDayFragmentMembersInjector = CalendarNestedBusyDayFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider, this.provideCalendarJitneyLoggerProvider);
        this.userLoginRequestMembersInjector = UserLoginRequest_MembersInjector.create(this.provideCurrencyHelperProvider, this.provideAccountManagerProvider, this.provideAirbnbApiProvider, this.provideBusProvider);
        this.provideTrebuchetControllerProvider = DoubleCheck.provider(CoreModule_ProvideTrebuchetControllerFactory.create(builder.coreModule, this.provideContextProvider));
    }

    private void initialize3(Builder builder) {
        this.provideExperimentConfigControllerProvider = DoubleCheck.provider(CoreModule_ProvideExperimentConfigControllerFactory.create(this.provideContextProvider, this.provideAirRequestInitializerProvider, this.provideExperimentsProvider, this.provideBusProvider, this.providePerformanceLoggerProvider, this.provideTrebuchetControllerProvider));
        this.loginActivityMembersInjector = LoginActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideExperimentConfigControllerProvider);
        this.insightsDataControllerMembersInjector = InsightsDataController_MembersInjector.create(this.provideCalendarStoreProvider);
        this.insightsNightlyPriceFragmentMembersInjector = InsightsNightlyPriceFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider);
        this.itineraryParentFragmentMembersInjector = ItineraryParentFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePerformanceLoggerProvider);
        this.internalBugReportFragmentMembersInjector = InternalBugReportFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideExperimentsProvider);
        this.cohostManagementActivityMembersInjector = CohostManagementActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.cohostUpsellActivityMembersInjector = CohostUpsellActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.acceptCohostInvitationActivityMembersInjector = AcceptCohostInvitationActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.provideCohostingInvitationJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideCohostingInvitationJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.acceptCohostInvitationFragmentMembersInjector = AcceptCohostInvitationFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingInvitationJitneyLoggerProvider);
        this.confirmInvitationAcceptedFragmentMembersInjector = ConfirmInvitationAcceptedFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingInvitationJitneyLoggerProvider);
        this.cohostingInvitationErrorFragmentMembersInjector = CohostingInvitationErrorFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingInvitationJitneyLoggerProvider);
        this.cohostingInvitationExpiredFragmentMembersInjector = CohostingInvitationExpiredFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingInvitationJitneyLoggerProvider);
        this.cohostManagementDataControllerMembersInjector = CohostManagementDataController_MembersInjector.create(this.provideAccountManagerProvider);
        this.cohostingInviteFriendAdapterMembersInjector = CohostingInviteFriendAdapter_MembersInjector.create(this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingShareEarningsAdapterMembersInjector = CohostingShareEarningsAdapter_MembersInjector.create(this.provideCohostingManagementJitneyLoggerProvider);
        this.listingManagerDetailsAdapterMembersInjector = ListingManagerDetailsAdapter_MembersInjector.create(this.provideCohostingManagementJitneyLoggerProvider);
        this.listingManagersPickerAdapterMembersInjector = ListingManagersPickerAdapter_MembersInjector.create(this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingListingPickerAdapterMembersInjector = CohostingListingPickerAdapter_MembersInjector.create(this.provideDebugSettingsProvider);
        this.cohostingContractFragmentMembersInjector = CohostingContractFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingMakePrimaryHostFragmentMembersInjector = CohostingMakePrimaryHostFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingServicesIntroFragmentMembersInjector = CohostingServicesIntroFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingStopShareEarningsFragmentMembersInjector = CohostingStopShareEarningsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.listingManagersPickerFragmentMembersInjector = ListingManagersPickerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.pendingCohostDetailsFragmentMembersInjector = PendingCohostDetailsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.removeCohostFragmentMembersInjector = RemoveCohostFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingInviteFriendWithFeeOptionFragmentMembersInjector = CohostingInviteFriendWithFeeOptionFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingInviteFriendEpoxyControllerMembersInjector = CohostingInviteFriendEpoxyController_MembersInjector.create(this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingShareEarningsWithFeeOptionFragmentMembersInjector = CohostingShareEarningsWithFeeOptionFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingShareEarningsEpoxyControllerMembersInjector = CohostingShareEarningsEpoxyController_MembersInjector.create(this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostUpsellEpoxyControllerMembersInjector = CohostUpsellEpoxyController_MembersInjector.create(this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostUpsellFragmentMembersInjector = CohostUpsellFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.cohostingInviteFriendConfirmationFragmentMembersInjector = CohostingInviteFriendConfirmationFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.provideCohostingReusableFlowJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideCohostingReusableFlowJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.cohostReasonSelectionActivityMembersInjector = CohostReasonSelectionActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideCohostingReusableFlowJitneyLoggerProvider);
        this.cohostReasonSelectionFragmentMembersInjector = CohostReasonSelectionFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingReusableFlowJitneyLoggerProvider);
        this.cohostReasonMessageTextInputFragmentMembersInjector = CohostReasonMessageTextInputFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingReusableFlowJitneyLoggerProvider);
        this.cohostReasonPrivateFeedbackTextInputFragmentMembersInjector = CohostReasonPrivateFeedbackTextInputFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingReusableFlowJitneyLoggerProvider);
        this.cohostingListingLevelNotificationSettingFragmentMembersInjector = CohostingListingLevelNotificationSettingFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCohostingManagementJitneyLoggerProvider);
        this.profileCompletionJitneyLoggerProvider = DoubleCheck.provider(ProfileCompletionModule_ProfileCompletionJitneyLoggerFactory.create(builder.profileCompletionModule, this.provideLoggingContextFactoryProvider));
        this.profileCompletionManagerProvider = DoubleCheck.provider(ProfileCompletionModule_ProfileCompletionManagerFactory.create(builder.profileCompletionModule, this.provideAccountManagerProvider, this.profileCompletionJitneyLoggerProvider));
        this.profileCompletionActivityMembersInjector = ProfileCompletionActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.profileCompletionManagerProvider, this.profileCompletionJitneyLoggerProvider);
        this.libComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.lib.LibComponent.Builder>() {
            public com.airbnb.android.lib.LibComponent.Builder get() {
                return new LibComponentBuilder();
            }
        };
        this.n2ComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.p027n2.N2Component.Builder>() {
            public com.airbnb.p027n2.N2Component.Builder get() {
                return new N2ComponentBuilder();
            }
        };
        this.p3ComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.p011p3.P3Component.Builder>() {
            public com.airbnb.android.p011p3.P3Component.Builder get() {
                return new P3ComponentBuilder();
            }
        };
        this.addPayoutMethodJitneyLoggerProvider = DoubleCheck.provider(PayoutModule_AddPayoutMethodJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.baseAddPayoutMethodFragmentMembersInjector = BaseAddPayoutMethodFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.addPayoutMethodJitneyLoggerProvider);
        this.selectPayoutCountryActivityMembersInjector = SelectPayoutCountryActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.addPayoutMethodJitneyLoggerProvider);
        this.editPayoutFragmentMembersInjector = EditPayoutFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.addPayoutMethodJitneyLoggerProvider);
        this.payoutRedirectWebviewActivityMembersInjector = PayoutRedirectWebviewActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.addPayoutMethodDataControllerMembersInjector = AddPayoutMethodDataController_MembersInjector.create(this.addPayoutMethodJitneyLoggerProvider);
        this.checkInComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.checkin.data.CheckInComponent.Builder>() {
            public com.airbnb.android.checkin.data.CheckInComponent.Builder get() {
                return new CheckInComponentBuilder();
            }
        };
        this.exploreComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.explore.ExploreComponent.Builder>() {
            public com.airbnb.android.explore.ExploreComponent.Builder get() {
                return new ExploreComponentBuilder();
            }
        };
        this.referralsComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.referrals.ReferralsComponent.Builder>() {
            public com.airbnb.android.referrals.ReferralsComponent.Builder get() {
                return new ReferralsComponentBuilder();
            }
        };
        this.appRaterComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.lib.AppRaterComponent.Builder>() {
            public com.airbnb.android.lib.AppRaterComponent.Builder get() {
                return new AppRaterComponentBuilder();
            }
        };
        this.guestRecoveryComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.guestrecovery.GuestRecoveryComponent.Builder>() {
            public com.airbnb.android.guestrecovery.GuestRecoveryComponent.Builder get() {
                return new GuestRecoveryComponentBuilder();
            }
        };
        this.pickWishListComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.pickwishlist.PickWishListComponent.Builder>() {
            public com.airbnb.android.pickwishlist.PickWishListComponent.Builder get() {
                return new PickWishListComponentBuilder();
            }
        };
        this.contentFrameworkComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.contentframework.ContentFrameworkComponent.Builder>() {
            public com.airbnb.android.contentframework.ContentFrameworkComponent.Builder get() {
                return new ContentFrameworkComponentBuilder();
            }
        };
        this.identityComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.identity.IdentityComponent.Builder>() {
            public com.airbnb.android.identity.IdentityComponent.Builder get() {
                return new IdentityComponentBuilder();
            }
        };
        this.registrationComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.registration.RegistrationComponent.Builder>() {
            public com.airbnb.android.registration.RegistrationComponent.Builder get() {
                return new RegistrationComponentBuilder();
            }
        };
        this.loginComponentBuilderProvider = new dagger.internal.Factory<com.airbnb.android.login.LoginComponent.Builder>() {
            public com.airbnb.android.login.LoginComponent.Builder get() {
                return new LoginComponentBuilder();
            }
        };
        this.provideAppForegroundDetectorProvider = DoubleCheck.provider(AnalyticsModule_ProvideAppForegroundDetectorFactory.create(builder.analyticsModule));
        this.provideAppForegroundAnalyticsProvider = DoubleCheck.provider(AnalyticsModule_ProvideAppForegroundAnalyticsFactory.create(builder.analyticsModule, this.provideTimeSkewAnalyticsProvider));
        this.bindWishListManagerProvider = this.provideWishListManagerProvider;
        this.setOfPostInteractiveInitializerProvider = SetFactory.builder(1, 0).addProvider(this.bindWishListManagerProvider).build();
        this.provideAppIdentityVerifierProvider = DoubleCheck.provider(CoreModule_ProvideAppIdentityVerifierFactory.create(this.provideContextProvider));
        this.bindMessageStoreRequestFactoryProvider = this.provideMessageStoreRequestFactoryProvider;
        this.provideJPushInitializerProvider = DoubleCheck.provider(InitModule_ProvideJPushInitializerFactory.create(builder.initModule, this.provideContextProvider));
        this.bindJPushInitializerProvider = this.provideJPushInitializerProvider;
        this.provideDebugNotificationControllerProvider = DoubleCheck.provider(InternalModule_ProvideDebugNotificationControllerFactory.create(builder.internalModule, this.provideContextProvider, this.provideAppForegroundDetectorProvider));
        this.bindDebugNotificationControllerProvider = this.provideDebugNotificationControllerProvider;
        this.bindThreadMetrixClientProvider = this.provideThreatMetrixClientProvider;
        this.setOfPostApplicationCreatedInitializerProvider = SetFactory.builder(5, 0).addProvider(this.provideAppIdentityVerifierProvider).addProvider(this.bindMessageStoreRequestFactoryProvider).addProvider(this.bindJPushInitializerProvider).addProvider(this.bindDebugNotificationControllerProvider).addProvider(this.bindThreadMetrixClientProvider).build();
        this.provideAnalyticsTrackerProvider = DoubleCheck.provider(AnalyticsModule_ProvideAnalyticsTrackerFactory.create(builder.analyticsModule, this.provideContextProvider));
        this.provideAppLaunchUtilsProvider = DoubleCheck.provider(CoreModule_ProvideAppLaunchUtilsFactory.create(this.provideAirbnbPreferencesProvider, this.affiliateInfoProvider, this.provideDomainStoreProvider, this.provideAccountManagerProvider, this.provideAnalyticsTrackerProvider, this.provideLoggingContextFactoryProvider));
        this.provideLocationHelperProvider = CoreModule_ProvideLocationHelperFactory.create(builder.coreModule, this.provideContextProvider);
        this.provideLocalPushNotificationManagerProvider = CoreModule_ProvideLocalPushNotificationManagerFactory.create(builder.coreModule, this.provideContextProvider, this.provideAirbnbPreferencesProvider, this.provideAirbnbApiProvider, this.provideAccountManagerProvider);
        this.photoUploadServiceMembersInjector = PhotoUploadService_MembersInjector.create(this.photoUploadManagerProvider);
        this.photoUploadRetryBroadcastReceiverMembersInjector = PhotoUploadRetryBroadcastReceiver_MembersInjector.create(this.photoUploadManagerProvider);
        this.provideHostReferralsLoggerProvider = HostReferralsModule_ProvideHostReferralsLoggerFactory.create(this.provideLoggingContextFactoryProvider);
        this.hostReferralsFragmentMembersInjector = HostReferralsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideHostReferralsLoggerProvider);
        this.provideLogAirInitializerProvider = DoubleCheck.provider(AnalyticsModule_ProvideLogAirInitializerFactory.create(builder.analyticsModule, this.provideContextProvider, this.provideAirbnbApiProvider, this.provideMemoryUtilsProvider, this.provideConverterFactoryProvider));
        this.airbnbApplicationMembersInjector = AirbnbApplication_MembersInjector.create(this.provideLogAirInitializerProvider);
        this.airActivityMembersInjector = AirActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.provideSplashScreenControllerProvider = DoubleCheck.provider(CoreModule_ProvideSplashScreenControllerFactory.create(this.provideAirbnbPreferencesProvider));
        this.provideReactDeepLinkParserProvider = DoubleCheck.provider(ReactModule_ProvideReactDeepLinkParserFactory.create(builder.reactModule, this.provideContextProvider, this.provideObjectMapperProvider));
        this.provideReactDeepLinkRegistryProvider = DoubleCheck.provider(ReactModule_ProvideReactDeepLinkRegistryFactory.create(builder.reactModule, this.provideReactDeepLinkParserProvider));
        this.entryActivityMembersInjector = EntryActivity_MembersInjector.create(this.provideSplashScreenControllerProvider, this.provideAccountManagerProvider, this.provideAppLaunchUtilsProvider, this.provideReactDeepLinkRegistryProvider, this.provideAppLaunchAnalyticsProvider, this.provideDebugSettingsProvider);
        this.searchIntentActivityMembersInjector = SearchIntentActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.affiliateInfoProvider);
        this.splashScreenActivityMembersInjector = SplashScreenActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideSplashScreenControllerProvider, this.provideExperimentConfigControllerProvider);
        this.inboxActivityMembersInjector = InboxActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.provideBookingJitneyEventLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideBookingJitneyEventLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.createIdentificationActivityMembersInjector = CreateIdentificationActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideBookingJitneyEventLoggerProvider);
        this.businessTravelInterstitialFragmentMembersInjector = BusinessTravelInterstitialFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.quickPayActivityMembersInjector = QuickPayActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.workEmailActivityMembersInjector = WorkEmailActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideBusinessTravelAccountManagerProvider);
        this.shakeFeedbackDialogMembersInjector = ShakeFeedbackDialog_MembersInjector.create(this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideAirbnbApiProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideResourceManagerProvider, this.provideAirbnbPreferencesProvider, this.provideSharedPrefsHelperProvider);
        this.appUpgradeDialogFragmentMembersInjector = AppUpgradeDialogFragment_MembersInjector.create(this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideAirbnbApiProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideResourceManagerProvider, this.provideAirbnbPreferencesProvider);
        this.airFragmentMembersInjector = AirFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
    }

    private void initialize4(Builder builder) {
        this.provideHostStatsJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideHostStatsJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.hostListingSelectorFragmentMembersInjector = HostListingSelectorFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideHostStatsJitneyLoggerProvider);
        this.feedbackSummaryFragmentMembersInjector = FeedbackSummaryFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessageStoreRequestFactoryProvider);
        this.savedMessagesFragmentMembersInjector = SavedMessagesFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessagingJitneyLoggerProvider);
        this.createNewSavedMessageFragmentMembersInjector = CreateNewSavedMessageFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessagingJitneyLoggerProvider);
        this.searchSettingsFragmentMembersInjector = SearchSettingsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.wLDetailsDeeplinkInterceptorActivityMembersInjector = WLDetailsDeeplinkInterceptorActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.affiliateInfoProvider);
        this.provideBypassProvider = DoubleCheck.provider(CoreModule_ProvideBypassFactory.create(this.provideContextProvider));
        this.helpThreadFragmentMembersInjector = HelpThreadFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBypassProvider);
        this.dLSReservationObjectFragmentMembersInjector = DLSReservationObjectFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideDebugSettingsProvider);
        this.provideHostReservationObjectJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideHostReservationObjectJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.provideInstantBookUpsellManagerProvider = CoreModule_ProvideInstantBookUpsellManagerFactory.create(this.provideContextProvider);
        this.hostReservationObjectFragmentMembersInjector = HostReservationObjectFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessageStoreRequestFactoryProvider, this.provideCalendarStoreProvider, this.provideHostReservationObjectJitneyLoggerProvider, this.provideInstantBookUpsellManagerProvider);
        this.advancedSettingsFragmentMembersInjector = AdvancedSettingsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideLowBandwidthUtilsProvider);
        this.wishListIndexFragmentMembersInjector = WishListIndexFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.bottomBarControllerProvider, this.provideWishListLoggerProvider);
        this.provideQuickPayRowFactoryProvider = QuickPayModule_ProvideQuickPayRowFactoryFactory.create(builder.quickPayModule, this.provideContextProvider);
        this.provideQuickPayAdapterFactoryProvider = DoubleCheck.provider(QuickPayModule_ProvideQuickPayAdapterFactoryFactory.create(builder.quickPayModule, this.provideContextProvider, this.provideQuickPayRowFactoryProvider));
        this.provideQuickPayRequestsFactoryProvider = DoubleCheck.provider(QuickPayModule_ProvideQuickPayRequestsFactoryFactory.create(builder.quickPayModule, this.provideAccountManagerProvider));
        this.provideBraintreeFactoryProvider = QuickPayModule_ProvideBraintreeFactoryFactory.create(builder.quickPayModule);
        this.providePaymentOptionFactoryProvider = QuickPayModule_ProvidePaymentOptionFactoryFactory.create(builder.quickPayModule);
        this.providePaymentRedirectCoordinatorProvider = QuickPayModule_ProvidePaymentRedirectCoordinatorFactory.create(builder.quickPayModule);
        this.quickPayFragmentMembersInjector = QuickPayFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayAdapterFactoryProvider, this.provideQuickPayRequestsFactoryProvider, this.provideQuickPayJitneyEventLoggerProvider, this.provideBraintreeFactoryProvider, this.providePaymentOptionFactoryProvider, this.providePaymentRedirectCoordinatorProvider, this.provideThreatMetrixClientProvider, this.provideAirlockErrorHandlerProvider);
        this.provideIdentityClientProvider = QuickPayModule_ProvideIdentityClientFactory.create(builder.quickPayModule);
        this.homesQuickPayFragmentMembersInjector = HomesQuickPayFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayAdapterFactoryProvider, this.provideQuickPayRequestsFactoryProvider, this.provideQuickPayJitneyEventLoggerProvider, this.provideBraintreeFactoryProvider, this.providePaymentOptionFactoryProvider, this.providePaymentRedirectCoordinatorProvider, this.provideThreatMetrixClientProvider, this.provideAirlockErrorHandlerProvider, this.provideIdentityClientProvider);
        this.providePaymentOptionsAdapterFactoryProvider = QuickPayModule_ProvidePaymentOptionsAdapterFactoryFactory.create(builder.quickPayModule, this.provideContextProvider);
        this.paymentOptionsFragmentMembersInjector = PaymentOptionsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayJitneyEventLoggerProvider, this.providePaymentOptionFactoryProvider, this.providePaymentOptionsAdapterFactoryProvider, this.provideBraintreeFactoryProvider);
        this.provideCreditCardValidatorProvider = QuickPayModule_ProvideCreditCardValidatorFactory.create(builder.quickPayModule);
        this.provideDigitalRiverApiProvider = QuickPayModule_ProvideDigitalRiverApiFactory.create(builder.quickPayModule, this.provideContextProvider, this.provideObjectMapperProvider);
        this.creditCardDetailsFragmentMembersInjector = CreditCardDetailsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBraintreeFactoryProvider, this.provideCreditCardValidatorProvider, this.provideQuickPayJitneyEventLoggerProvider, this.provideDigitalRiverApiProvider);
        this.addCouponCodeFragmentMembersInjector = AddCouponCodeFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideQuickPayRequestsFactoryProvider);
        this.addPaymentMethodFragmentMembersInjector = AddPaymentMethodFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBraintreeFactoryProvider);
        this.selectBillingCountryFragmentMembersInjector = SelectBillingCountryFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.provideBrazilPaymentInputFormatterProvider = QuickPayModule_ProvideBrazilPaymentInputFormatterFactory.create(builder.quickPayModule, this.providePhoneNumberUtilProvider);
        this.brazilCreditCardDetailsFragmentMembersInjector = BrazilCreditCardDetailsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBrazilPaymentInputFormatterProvider, this.provideDigitalRiverApiProvider, this.provideQuickPayJitneyEventLoggerProvider);
        this.referralBroadcastReceiverMembersInjector = ReferralBroadcastReceiver_MembersInjector.create(this.provideAirbnbPreferencesProvider, this.affiliateInfoProvider);
        this.provideAirCookieManagerProvider = DoubleCheck.provider(CoreModule_ProvideAirCookieManagerFactory.create(this.provideContextProvider));
        this.provideWebIntentMatcherProvider = WebIntentMatcherModule_ProvideWebIntentMatcherFactory.create(builder.webIntentMatcherModule, this.provideAccountManagerProvider);
        this.airWebViewMembersInjector = AirWebView_MembersInjector.create(this.provideAirbnbApiProvider, this.provideAccountManagerProvider, this.provideDomainStoreProvider, this.provideAirCookieManagerProvider, this.provideWebIntentMatcherProvider);
        this.jPushHelperMembersInjector = JPushHelper_MembersInjector.create(this.provideAirbnbApiProvider);
        this.debugMenuActivityMembersInjector = DebugMenuActivity_MembersInjector.create(this.provideAirbnbApiProvider, this.provideSharedPrefsHelperProvider, this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideMessageStoreRequestFactoryProvider, this.provideItineraryTableOpenHelperProvider, this.provideSuperHeroTableOpenHelperProvider, this.providePaymentOptionFactoryProvider);
        this.endpointAdapterMembersInjector = EndpointSelectorDialogFragment_EndpointAdapter_MembersInjector.create(this.provideAirbnbApiProvider);
        this.hHBaseAdapterMembersInjector = HHBaseAdapter_MembersInjector.create(this.provideAccountManagerProvider, this.provideCurrencyHelperProvider);
        this.hostHomeWidgetProviderMembersInjector = HostHomeWidgetProvider_MembersInjector.create(this.provideAccountManagerProvider, this.provideAirbnbApiProvider, this.provideAppLaunchAnalyticsProvider);
        this.payoutSelectFragmentMembersInjector = PayoutSelectFragment_MembersInjector.create(this.provideAirbnbApiProvider);
        this.tOSDialogFragmentMembersInjector = TOSDialogFragment_MembersInjector.create(this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideAirbnbApiProvider);
        this.completeProfilePhoneCodeChildFragmentMembersInjector = CompleteProfilePhoneCodeChildFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.tripsReservationsSyncServiceMembersInjector = TripsReservationsSyncService_MembersInjector.create(this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideAirbnbApiProvider);
        this.provideViewedListingsDatabaseHelperProvider = DoubleCheck.provider(CoreModule_ProvideViewedListingsDatabaseHelperFactory.create(this.provideContextProvider));
        this.viewedListingsPersistenceServiceMembersInjector = ViewedListingsPersistenceService_MembersInjector.create(this.provideViewedListingsDatabaseHelperProvider);
        this.pushIntentServiceMembersInjector = PushIntentService_MembersInjector.create(this.provideAccountManagerProvider, this.provideCalendarStoreProvider, this.provideBusProvider, this.provideAppLaunchAnalyticsProvider, this.provideMessageStoreRequestFactoryProvider, this.provideAppForegroundDetectorProvider);
        this.webIntentDispatchMembersInjector = WebIntentDispatch_MembersInjector.create(this.affiliateInfoProvider, this.provideAirbnbPreferencesProvider, this.provideAccountManagerProvider, this.provideOkHttpClientProvider, this.provideBusProvider);
        this.onlineIdChildFragmentMembersInjector = OnlineIdChildFragment_MembersInjector.create(this.provideSharedPrefsHelperProvider);
        this.hHListRemoteViewsFactoryMembersInjector = HHListRemoteViewsFactory_MembersInjector.create(this.provideAccountManagerProvider, this.provideAirbnbApiProvider);
        this.localeChangedReceiverMembersInjector = LocaleChangedReceiver_MembersInjector.create(this.provideAirbnbPreferencesProvider, this.provideBusProvider);
        this.officialIdIntentServiceMembersInjector = OfficialIdIntentService_MembersInjector.create(this.provideAirbnbApiProvider, this.provideBusProvider);
        this.verifiedIdCompletedFragmentMembersInjector = VerifiedIdCompletedFragment_MembersInjector.create(this.provideAccountManagerProvider, this.provideBusProvider);
        this.gCMHelperMembersInjector = GCMHelper_MembersInjector.create(this.provideAirbnbApiProvider);
        this.localBitmapForDisplayScalingTaskMembersInjector = LocalBitmapForDisplayScalingTask_MembersInjector.create(this.provideImageUtilsProvider);
        this.officialIdPhotoSelectionFragmentMembersInjector = OfficialIdPhotoSelectionFragment_MembersInjector.create(this.provideImageUtilsProvider);
        this.editProfileDetailsAdapterMembersInjector = EditProfileDetailsAdapter_MembersInjector.create(this.provideAccountManagerProvider);
        this.provideVerifiedIdAnalyticsProvider = DoubleCheck.provider(AnalyticsModule_ProvideVerifiedIdAnalyticsFactory.create(builder.analyticsModule));
        this.completeProfilePhoneChildFragmentMembersInjector = CompleteProfilePhoneChildFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideVerifiedIdAnalyticsProvider, this.providePhoneUtilProvider);
        this.offlineIdChildFragmentMembersInjector = OfflineIdChildFragment_MembersInjector.create(this.provideVerifiedIdAnalyticsProvider);
        this.sesameVerificationChildFragmentMembersInjector = SesameVerificationChildFragment_MembersInjector.create(this.provideVerifiedIdAnalyticsProvider);
        this.sesameVerificationConnectFragmentMembersInjector = SesameVerificationConnectFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideVerifiedIdAnalyticsProvider);
        this.officialIdErrorFragmentMembersInjector = OfficialIdErrorFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.pricingQuotePricingDetailsMembersInjector = PricingQuotePricingDetails_MembersInjector.create(this.provideCurrencyHelperProvider);
        this.appUpgradeReceiverMembersInjector = AppUpgradeReceiver_MembersInjector.create(this.provideAppLaunchUtilsProvider, this.provideAppLaunchAnalyticsProvider);
        this.reservationObjectAdapterMembersInjector = ReservationObjectAdapter_MembersInjector.create(this.provideAccountManagerProvider, this.provideCurrencyHelperProvider);
        this.hostReservationObjectAdapterMembersInjector = HostReservationObjectAdapter_MembersInjector.create(this.provideCurrencyHelperProvider, this.provideAccountManagerProvider, this.provideCalendarStoreProvider, this.provideSharedPrefsHelperProvider);
        this.priceGroupedCellMembersInjector = PriceGroupedCell_MembersInjector.create(this.provideCurrencyHelperProvider);
        this.sharingManagerMembersInjector = SharingManager_MembersInjector.create(this.provideAirbnbPreferencesProvider);
        this.emptyResultsCardViewMembersInjector = EmptyResultsCardView_MembersInjector.create(this.provideMemoryUtilsProvider);
        this.wifiAlarmReceiverMembersInjector = WifiAlarmReceiver_MembersInjector.create(this.provideAirbnbPreferencesProvider);
        this.accountSettingsFragmentMembersInjector = AccountSettingsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideShakeFeedbackhelperProvider);
        this.editProfileFragmentMembersInjector = EditProfileFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideImageUtilsProvider, this.providePhotoCompressorProvider, this.provideIdentityJitneyEventLoggerProvider);
        this.phoneVerificationFragmentMembersInjector = com.airbnb.android.lib.fragments.verifications.PhoneVerificationFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePhoneUtilProvider, this.providePhoneNumberUtilProvider);
        this.photoVerificationFragmentMembersInjector = PhotoVerificationFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePhotoCompressorProvider);
        this.helpThreadDialogActivityMembersInjector = HelpThreadDialogActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideBypassProvider);
        this.completeProfilePhotoFragmentMembersInjector = CompleteProfilePhotoFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePhotoCompressorProvider);
        this.buttonPartnershipMembersInjector = ButtonPartnership_MembersInjector.create(this.provideAirbnbPreferencesProvider, this.provideAccountManagerProvider);
        this.specialOfferActivityMembersInjector = SpecialOfferActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.provideAlipayAnalyticsProvider = AnalyticsModule_ProvideAlipayAnalyticsFactory.create(builder.analyticsModule);
        this.payWithAlipayActivityMembersInjector = PayWithAlipayActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideAlipayAnalyticsProvider);
        this.erfOverrideActivityMembersInjector = ErfOverrideActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideExperimentsProvider);
        this.editableCellMembersInjector = EditableCell_MembersInjector.create(this.provideCurrencyHelperProvider);
        this.threadAdapterMembersInjector = ThreadAdapter_MembersInjector.create(this.provideAccountManagerProvider, this.provideCurrencyHelperProvider, this.provideDebugSettingsProvider, this.provideSharedPrefsHelperProvider);
        this.hostStatsFragmentMembersInjector = HostStatsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideHostStatsJitneyLoggerProvider, this.bottomBarControllerProvider);
        this.inboxFragmentMembersInjector = InboxFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessageStoreRequestFactoryProvider, this.provideSuperHeroTableOpenHelperProvider, this.provideSuperHeroManagerProvider);
        this.threadFragmentMembersInjector = ThreadFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessageStoreRequestFactoryProvider, this.provideMessagingJitneyLoggerProvider);
        this.identificationNameFragmentMembersInjector = IdentificationNameFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.guestIdentificationAdapterMembersInjector = GuestIdentificationAdapter_MembersInjector.create(this.provideAccountManagerProvider);
        this.hostReviewDetailAdapterMembersInjector = HostReviewDetailAdapter_MembersInjector.create(this.provideHostStatsJitneyLoggerProvider);
        this.hostReviewDetailsFragmentMembersInjector = HostReviewDetailsFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideHostStatsJitneyLoggerProvider);
        this.hostDemandsDetailFragmentMembersInjector = HostDemandsDetailFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideHostStatsJitneyLoggerProvider);
        this.acceptReservationFragmentMembersInjector = AcceptReservationFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider, this.provideHostReservationObjectJitneyLoggerProvider, this.provideInstantBookUpsellManagerProvider);
        this.reservationPickerFragmentMembersInjector = ReservationPickerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessagingJitneyLoggerProvider);
        this.preapproveInquiryFragmentMembersInjector = PreapproveInquiryFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideHostReservationObjectJitneyLoggerProvider);
        this.removePreapprovalFragmentMembersInjector = RemovePreapprovalFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideHostReservationObjectJitneyLoggerProvider);
        this.provideReservationResponseLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideReservationResponseLoggerFactory.create(this.provideLoggingContextFactoryProvider));
    }

    private void initialize5(Builder builder) {
        this.reservationResponseActivityMembersInjector = ReservationResponseActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideReservationResponseLoggerProvider, this.provideInstantBookUpsellManagerProvider);
        this.reservationResponseLandingFragmentMembersInjector = ReservationResponseLandingFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCalendarStoreProvider, this.provideHostReservationObjectJitneyLoggerProvider, this.provideInstantBookUpsellManagerProvider);
        this.guestPickerFragmentMembersInjector = GuestPickerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.bottomBarControllerProvider);
        this.trebuchetOverrideActivityMembersInjector = TrebuchetOverrideActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider);
        this.feedbackIntroFragmentMembersInjector = FeedbackIntroFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideMessageStoreRequestFactoryProvider);
        this.provideBusinessTravelJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideBusinessTravelJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.accountPageFragmentMembersInjector = AccountPageFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.bottomBarControllerProvider, this.provideBusinessTravelJitneyLoggerProvider, this.profileCompletionManagerProvider, this.providePromoControllerProvider);
        this.inboxContainerFragmentMembersInjector = InboxContainerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.bottomBarControllerProvider);
        this.provideCommunityCommitmentJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideCommunityCommitmentJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.communityCommitmentFragmentMembersInjector = CommunityCommitmentFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCommunityCommitmentJitneyLoggerProvider);
        this.communityCommitmentCancelAccountFragmentMembersInjector = CommunityCommitmentCancelAccountFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCommunityCommitmentJitneyLoggerProvider);
        this.communityCommitmentWriteFeedbackFragmentMembersInjector = CommunityCommitmentWriteFeedbackFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCommunityCommitmentJitneyLoggerProvider);
        this.communityCommitmentLearnMoreFragmentMembersInjector = CommunityCommitmentLearnMoreFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideCommunityCommitmentJitneyLoggerProvider);
        this.acceptReservationConfirmationFragmentMembersInjector = AcceptReservationConfirmationFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideInstantBookUpsellManagerProvider);
        this.providePaidAmenityJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvidePaidAmenityJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.basePurchaseAmenityFragmentMembersInjector = BasePurchaseAmenityFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePaidAmenityJitneyLoggerProvider);
        this.basePendingAmenityFragmentMembersInjector = BasePendingAmenityFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePaidAmenityJitneyLoggerProvider);
        this.baseCreateAmenityFragmentMembersInjector = BaseCreateAmenityFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePaidAmenityJitneyLoggerProvider);
        this.hostAmenityListFragmentMembersInjector = HostAmenityListFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.providePaidAmenityJitneyLoggerProvider);
        this.inboxAdapterMembersInjector = InboxAdapter_MembersInjector.create(this.provideAccountManagerProvider, this.provideMessageStoreRequestFactoryProvider);
        this.provideReservationCancellationLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideReservationCancellationLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.reasonPickerFragmentMembersInjector = ReasonPickerFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideReservationCancellationLoggerProvider);
        this.reservationCanceledFragmentMembersInjector = ReservationCanceledFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideReservationCancellationLoggerProvider);
        this.reservationCancellationWithUserInputFragmentMembersInjector = ReservationCancellationWithUserInputFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideReservationCancellationLoggerProvider);
        this.workEmailFragmentMembersInjector = WorkEmailFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBusinessTravelJitneyLoggerProvider);
        this.bottomBarBadgeInboxHandlerMembersInjector = BottomBarBadgeInboxHandler_MembersInjector.create(this.provideInboxUnreadCountManagerProvider);
        this.p3AdditionalPriceFragmentMembersInjector = P3AdditionalPriceFragment_MembersInjector.create(this.provideAccountManagerProvider, this.provideDebugSettingsProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideCurrencyHelperProvider);
        this.postBookingBusinessTravelPromoFragmentMembersInjector = PostBookingBusinessTravelPromoFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBusinessTravelJitneyLoggerProvider);
        this.verifyWorkEmailFragmentMembersInjector = VerifyWorkEmailFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBusinessTravelJitneyLoggerProvider);
        this.userProfileFragmentMembersInjector = UserProfileFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider);
        this.userProfileActivityMembersInjector = UserProfileActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideBusinessTravelJitneyLoggerProvider);
        this.postBookingActivityMembersInjector = PostBookingActivity_MembersInjector.create(this.provideAccountManagerProvider, this.provideSharedPrefsHelperProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideErfProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirbnbPreferencesProvider, this.provideShakeFeedbackhelperProvider, this.provideAirRequestInitializerProvider, this.provideSuperHeroManagerProvider, this.provideReactInstanceManagerProvider, this.provideViewBreadcrumbManagerProvider, this.provideAppLaunchAnalyticsProvider, this.provideResourceManagerProvider, this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.dLSJitneyLoggerProvider, this.provideBusinessTravelAccountManagerProvider);
        this.businessTravelWelcomeFragmentMembersInjector = BusinessTravelWelcomeFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideBusinessTravelJitneyLoggerProvider);
        this.photoVerificationFragmentMembersInjector2 = com.airbnb.android.lib.china5a.fragments.PhotoVerificationFragment_MembersInjector.create(this.provideAirbnbApiProvider, this.provideWishListManagerProvider, this.provideBusinessTravelAccountManagerProvider, this.provideAccountManagerProvider, this.provideAirbnbPreferencesProvider, this.provideErfProvider, this.provideSharedPrefsHelperProvider, this.provideMemoryUtilsProvider, this.provideBusProvider, this.provideCurrencyHelperProvider, this.provideKonaNavigationAnalyticsProvider, this.provideAirRequestInitializerProvider, this.provideLoggingContextFactoryProvider, this.provideClientSessionValidatorProvider, this.provideViewBreadcrumbManagerProvider, this.provideResourceManagerProvider, this.provideChooseProfilePhotoControllerProvider);
        this.photoVerificationPresenterMembersInjector = PhotoVerificationPresenter_MembersInjector.create(this.provideAccountManagerProvider);
        this.advancedSettingsEpoxyControllerMembersInjector = AdvancedSettingsEpoxyController_MembersInjector.create(this.provideShakeFeedbackhelperProvider);
        this.provideN2CallbacksProvider = DoubleCheck.provider(CoreModule_ProvideN2CallbacksFactory.create(this.provideAirbnbPreferencesProvider));
        this.provideGoogleAppIndexingControllerProvider = CoreModule_ProvideGoogleAppIndexingControllerFactory.create(this.provideContextProvider);
        this.provideReviewSearchJitneyLoggerProvider = DoubleCheck.provider(AnalyticsModule_ProvideReviewSearchJitneyLoggerFactory.create(this.provideLoggingContextFactoryProvider));
        this.provideInstantPromotionManagerProvider = DoubleCheck.provider(CoreModule_ProvideInstantPromotionManagerFactory.create(this.provideErfAnalyticsProvider, this.provideAccountManagerProvider));
    }

    public NetworkMonitor networkMonitor() {
        return (NetworkMonitor) this.provideNetworkMonitorProvider.get();
    }

    public AffiliateInfo affiliateInfo() {
        return new AffiliateInfo((AirbnbPreferences) this.provideAirbnbPreferencesProvider.get());
    }

    public MemoryUtils memoryUtils() {
        return (MemoryUtils) this.provideMemoryUtilsProvider.get();
    }

    public AnalyticsRegistry analyticsRegistry() {
        return (AnalyticsRegistry) this.provideAnalyticsRegistryProvider.get();
    }

    public Lazy<DynamicStringsResources> dynamicStringsResources() {
        return DoubleCheck.lazy(this.provideDynamicStringsResourcesProvider);
    }

    public OkHttpClient okHttp() {
        return (OkHttpClient) this.provideOkHttpClientProvider.get();
    }

    public AirbnbPreferences airbnbPreferences() {
        return (AirbnbPreferences) this.provideAirbnbPreferencesProvider.get();
    }

    public AirbnbAccountManager accountManager() {
        return (AirbnbAccountManager) this.provideAccountManagerProvider.get();
    }

    public ResourceManager resourceManager() {
        return (ResourceManager) this.provideResourceManagerProvider.get();
    }

    public GeocoderBaseUrl geocoderBaseUrl() {
        return (GeocoderBaseUrl) this.provideGeocoderRequestBaseUrlProvider.get();
    }

    public CurrencyFormatter currencyHelper() {
        return (CurrencyFormatter) this.provideCurrencyHelperProvider.get();
    }

    public AirbnbEventLogger airbnbEventLogger() {
        return (AirbnbEventLogger) this.provideAirbnbEventLoggerProvider.get();
    }

    public ExperimentAssignments experimentAssigments() {
        return (ExperimentAssignments) this.provideExperimentAssigmentsProvider.get();
    }

    public ObjectMapper objectMapper() {
        return (ObjectMapper) this.provideObjectMapperProvider.get();
    }

    public SingleFireRequestExecutor singleFireRequestExecutor() {
        return (SingleFireRequestExecutor) this.provideSingleFireRequestExecutorProvider.get();
    }

    public NavigationLogging navigationAnalytics() {
        return (NavigationLogging) this.provideKonaNavigationAnalyticsProvider.get();
    }

    public LowBandwidthManager lowBandwidthUtils() {
        return (LowBandwidthManager) this.provideLowBandwidthUtilsProvider.get();
    }

    public SharedPrefsHelper sharedPrefsHelper() {
        return (SharedPrefsHelper) this.provideSharedPrefsHelperProvider.get();
    }

    public PerformanceLogger performanceLogger() {
        return (PerformanceLogger) this.providePerformanceLoggerProvider.get();
    }

    public ExperimentsProvider experimentsProvider() {
        return (ExperimentsProvider) this.provideExperimentsProvider.get();
    }

    public LoggingContextFactory loggingContextFactory() {
        return (LoggingContextFactory) this.provideLoggingContextFactoryProvider.get();
    }

    public Bus bus() {
        return (Bus) this.provideBusProvider.get();
    }

    public Lazy<ComponentManager> componentManager() {
        return DoubleCheck.lazy(CoreModule_ProvideComponentManagerFactory.create());
    }

    public void inject(Reservation arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(AirButton arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(AuthorizedAccountHelper arg0) {
        this.authorizedAccountHelperMembersInjector.injectMembers(arg0);
    }

    public void inject(AirEditTextView arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(AirAutoCompleteTextView arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(HaloImageView arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(Review arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(CurrenciesRequest arg0) {
        this.currenciesRequestMembersInjector.injectMembers(arg0);
    }

    public void inject(GetActiveAccountRequest arg0) {
        this.getActiveAccountRequestMembersInjector.injectMembers(arg0);
    }

    public void inject(AuthorizeServiceRequest arg0) {
        this.authorizeServiceRequestMembersInjector.injectMembers(arg0);
    }

    public void inject(SetProfilePhotoRequest arg0) {
        this.setProfilePhotoRequestMembersInjector.injectMembers(arg0);
    }

    public void inject(NavigationLogging arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(CountriesRequest arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(SFRPartnerTask arg0) {
        this.sFRPartnerTaskMembersInjector.injectMembers(arg0);
    }

    public void inject(DTKPartnerTask arg0) {
        this.dTKPartnerTaskMembersInjector.injectMembers(arg0);
    }

    public void inject(AirbnbEventLogger arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(WishListHeartController arg0) {
        this.wishListHeartControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(AuthorizedAccount arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(AirBatchRequest arg0) {
        this.airBatchRequestMembersInjector.injectMembers(arg0);
    }

    public void inject(AirCookieManager arg0) {
        this.airCookieManagerMembersInjector.injectMembers(arg0);
    }

    public void inject(AirbnbAccountManager arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(ImageUtils arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(UpdateThreadRequest arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(LocalPushNotificationManager arg0) {
        this.localPushNotificationManagerMembersInjector.injectMembers(arg0);
    }

    public void inject(DeleteOauthTokenRequest arg0) {
        this.deleteOauthTokenRequestMembersInjector.injectMembers(arg0);
    }

    public void inject(AirBatchRequestObserver arg0) {
        this.airBatchRequestObserverMembersInjector.injectMembers(arg0);
    }

    public void inject(AnimatedDrawableView arg0) {
        this.animatedDrawableViewMembersInjector.injectMembers(arg0);
    }

    public void inject(ZenDialog arg0) {
        this.zenDialogMembersInjector.injectMembers(arg0);
    }

    public void inject(PhoneNumberInputSheet arg0) {
        this.phoneNumberInputSheetMembersInjector.injectMembers(arg0);
    }

    public void inject(PushNotificationBuilder arg0) {
        this.pushNotificationBuilderMembersInjector.injectMembers(arg0);
    }

    public void inject(AirDialogFragment arg0) {
        this.airDialogFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LocationTypeaheadFilterForChina arg0) {
        this.locationTypeaheadFilterForChinaMembersInjector.injectMembers(arg0);
    }

    public void inject(ListingsTray arg0) {
        this.listingsTrayMembersInjector.injectMembers(arg0);
    }

    public void inject(InstantBookUpsellManager arg0) {
        this.instantBookUpsellManagerMembersInjector.injectMembers(arg0);
    }

    public void inject(OldManageListingPhotoUploadService arg0) {
        this.oldManageListingPhotoUploadServiceMembersInjector.injectMembers(arg0);
    }

    public void inject(ResourceManager arg0) {
        this.resourceManagerMembersInjector.injectMembers(arg0);
    }

    public void inject(LoopingViewPager arg0) {
        this.loopingViewPagerMembersInjector.injectMembers(arg0);
    }

    public void inject(VerticalCalendarAdapter arg0) {
        this.verticalCalendarAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(ReplaceVerifiedIdWithIdentityActivity arg0) {
        this.replaceVerifiedIdWithIdentityActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(InstantPromotionManager arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(LottieNuxViewPagerFragment arg0) {
        this.lottieNuxViewPagerFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(DLSCancellationPolicyFragment arg0) {
        this.dLSCancellationPolicyFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ResyController arg0) {
        this.resyControllerMembersInjector.injectMembers(arg0);
    }

    public AirReactInstanceManager reactInstanceManager() {
        return (AirReactInstanceManager) this.provideReactInstanceManagerProvider.get();
    }

    public void inject(ReactNativeActivity arg0) {
        this.reactNativeActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(ReactNativeFragment arg0) {
        this.reactNativeFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(Shareable arg0) {
        this.shareableMembersInjector.injectMembers(arg0);
    }

    public void inject(WishListDetailsParentFragment arg0) {
        this.wishListDetailsParentFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(WishListsFragment arg0) {
        this.wishListsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(BaseWishListDetailsFragment arg0) {
        this.baseWishListDetailsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(SuperHeroAlarmReceiver arg0) {
        this.superHeroAlarmReceiverMembersInjector.injectMembers(arg0);
    }

    public void inject(SuperHeroThreadFragment arg0) {
        this.superHeroThreadFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(SelectCountryFragment arg0) {
        this.selectCountryFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(SelectPaymentMethodFragment arg0) {
        this.selectPaymentMethodFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CreditCardBaseFragment arg0) {
        this.creditCardBaseFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(BaseAlipayFragment arg0) {
        this.baseAlipayFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(BookingSummaryFragment arg0) {
        this.bookingSummaryFragmentMembersInjector.injectMembers(arg0);
    }

    public ThreatMetrixClient threatMetrixClient() {
        return (ThreatMetrixClient) this.provideThreatMetrixClientProvider.get();
    }

    public MessagingRequestFactory messagingRequestFactory() {
        return (MessagingRequestFactory) this.provideMessageStoreRequestFactoryProvider.get();
    }

    public void inject(AccountVerificationActivity arg0) {
        this.accountVerificationActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(AccountVerificationStartActivity arg0) {
        this.accountVerificationStartActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(AirbnbTakeSelfieActivity arg0) {
        this.airbnbTakeSelfieActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(AccountVerificationProfilePhotoFragment arg0) {
        this.accountVerificationProfilePhotoFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(AccountVerificationSelfieFragment arg0) {
        this.accountVerificationSelfieFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(IdentitySelfieCaptureFragment arg0) {
        this.identitySelfieCaptureFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(AccountVerificationSelfieConfirmFragment arg0) {
        this.accountVerificationSelfieConfirmFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(MiSnapTakeSelfieActivity arg0) {
        this.miSnapTakeSelfieActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(MiSnapIdentityCaptureActivity arg0) {
        this.miSnapIdentityCaptureActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingDetailsEpoxyController arg0) {
        this.manageListingDetailsEpoxyControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingPickerFragment arg0) {
        this.manageListingPickerFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingBookingsAdapter arg0) {
        this.manageListingBookingsAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(ManagePhotosFragment arg0) {
        this.managePhotosFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingDataController arg0) {
        this.manageListingDataControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(DlsManageListingActivity arg0) {
        this.dlsManageListingActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingSettingsTabFragment arg0) {
        this.manageListingSettingsTabFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingLocalLawsFragment arg0) {
        this.manageListingLocalLawsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingNightlyPriceSettingsFragment arg0) {
        this.manageListingNightlyPriceSettingsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingDiscountsSettingsFragment arg0) {
        this.manageListingDiscountsSettingsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingLastMinuteDiscountFragment arg0) {
        this.manageListingLastMinuteDiscountFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingEarlyBirdDiscountFragment arg0) {
        this.manageListingEarlyBirdDiscountFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CheckinNoteTextSettingFragment arg0) {
        this.checkinNoteTextSettingFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingCheckInGuideFragment arg0) {
        this.manageListingCheckInGuideFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingAllCheckinMethodsFragment arg0) {
        this.manageListingAllCheckinMethodsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ManageListingCheckinTypeTextSettingFragment arg0) {
        this.manageListingCheckinTypeTextSettingFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSHomeActivity arg0) {
        this.lYSHomeActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSDataController arg0) {
        this.lYSDataControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSPhotoStartFragment arg0) {
        this.lYSPhotoStartFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSPhotoManagerFragment arg0) {
        this.lYSPhotoManagerFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSLocalLawsFragment arg0) {
        this.lYSLocalLawsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSSmartPricingFragment arg0) {
        this.lYSSmartPricingFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSBasePriceFragment arg0) {
        this.lYSBasePriceFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSDiscountsFragment arg0) {
        this.lYSDiscountsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSRoomsAndGuestsFragment arg0) {
        this.lYSRoomsAndGuestsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSAddressFragment arg0) {
        this.lYSAddressFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSHostingFrequencyFragment arg0) {
        this.lYSHostingFrequencyFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSRentHistoryFragment arg0) {
        this.lYSRentHistoryFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSPublishFragment arg0) {
        this.lYSPublishFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSSpaceTypeFragment arg0) {
        this.lYSSpaceTypeFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSCalendarFragment arg0) {
        this.lYSCalendarFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSGuestBookFragment arg0) {
        this.lYSGuestBookFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSPhotoDetailFragment arg0) {
        this.lYSPhotoDetailFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSRTBChecklistFragment arg0) {
        this.lYSRTBChecklistFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSSelectPricingTypeFragment arg0) {
        this.lYSSelectPricingTypeFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSCharacterCountMarqueeFragment arg0) {
        this.lYSCharacterCountMarqueeFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(LYSLandingFragment arg0) {
        this.lYSLandingFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(NightlyPriceAdapter arg0) {
        this.nightlyPriceAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(BasePriceAdapter arg0) {
        this.basePriceAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(LongTermDiscountsAdapter arg0) {
        MembersInjectors.noOp().injectMembers(arg0);
    }

    public void inject(WhatsMyPlaceWorthFragment arg0) {
        this.whatsMyPlaceWorthFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(AddressAutoCompleteFragment arg0) {
        this.addressAutoCompleteFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CalendarWithPriceTipsUpdateFragment arg0) {
        this.calendarWithPriceTipsUpdateFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CalendarUpdateNotesFragment arg0) {
        this.calendarUpdateNotesFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(SingleCalendarBaseFragment arg0) {
        this.singleCalendarBaseFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(AgendaCalendarFragment arg0) {
        this.agendaCalendarFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CalendarFragment arg0) {
        this.calendarFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(SingleCalendarFragment arg0) {
        this.singleCalendarFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CalendarAgendaAdapter arg0) {
        this.calendarAgendaAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(CalendarDetailAdapter arg0) {
        this.calendarDetailAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(CalendarNestedBusyDayFragment arg0) {
        this.calendarNestedBusyDayFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(UserLoginRequest arg0) {
        this.userLoginRequestMembersInjector.injectMembers(arg0);
    }

    public void inject(LoginActivity arg0) {
        this.loginActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(InsightsDataController arg0) {
        this.insightsDataControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(InsightsNightlyPriceFragment arg0) {
        this.insightsNightlyPriceFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ItineraryParentFragment arg0) {
        this.itineraryParentFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(InternalBugReportFragment arg0) {
        this.internalBugReportFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostManagementActivity arg0) {
        this.cohostManagementActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostUpsellActivity arg0) {
        this.cohostUpsellActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(AcceptCohostInvitationActivity arg0) {
        this.acceptCohostInvitationActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(AcceptCohostInvitationFragment arg0) {
        this.acceptCohostInvitationFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ConfirmInvitationAcceptedFragment arg0) {
        this.confirmInvitationAcceptedFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingInvitationErrorFragment arg0) {
        this.cohostingInvitationErrorFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingInvitationExpiredFragment arg0) {
        this.cohostingInvitationExpiredFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostManagementDataController arg0) {
        this.cohostManagementDataControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingInviteFriendAdapter arg0) {
        this.cohostingInviteFriendAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingShareEarningsAdapter arg0) {
        this.cohostingShareEarningsAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(ListingManagerDetailsAdapter arg0) {
        this.listingManagerDetailsAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(ListingManagersPickerAdapter arg0) {
        this.listingManagersPickerAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingListingPickerAdapter arg0) {
        this.cohostingListingPickerAdapterMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingContractFragment arg0) {
        this.cohostingContractFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingMakePrimaryHostFragment arg0) {
        this.cohostingMakePrimaryHostFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingServicesIntroFragment arg0) {
        this.cohostingServicesIntroFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingStopShareEarningsFragment arg0) {
        this.cohostingStopShareEarningsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(ListingManagersPickerFragment arg0) {
        this.listingManagersPickerFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(PendingCohostDetailsFragment arg0) {
        this.pendingCohostDetailsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(RemoveCohostFragment arg0) {
        this.removeCohostFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingInviteFriendWithFeeOptionFragment arg0) {
        this.cohostingInviteFriendWithFeeOptionFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingInviteFriendEpoxyController arg0) {
        this.cohostingInviteFriendEpoxyControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingShareEarningsWithFeeOptionFragment arg0) {
        this.cohostingShareEarningsWithFeeOptionFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingShareEarningsEpoxyController arg0) {
        this.cohostingShareEarningsEpoxyControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostUpsellEpoxyController arg0) {
        this.cohostUpsellEpoxyControllerMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostUpsellFragment arg0) {
        this.cohostUpsellFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingInviteFriendConfirmationFragment arg0) {
        this.cohostingInviteFriendConfirmationFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostReasonSelectionActivity arg0) {
        this.cohostReasonSelectionActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostReasonSelectionFragment arg0) {
        this.cohostReasonSelectionFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostReasonMessageTextInputFragment arg0) {
        this.cohostReasonMessageTextInputFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostReasonPrivateFeedbackTextInputFragment arg0) {
        this.cohostReasonPrivateFeedbackTextInputFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(CohostingListingLevelNotificationSettingFragment arg0) {
        this.cohostingListingLevelNotificationSettingFragmentMembersInjector.injectMembers(arg0);
    }

    public ProfileCompletionJitneyLogger profileCompletionJitneyLogger() {
        return (ProfileCompletionJitneyLogger) this.profileCompletionJitneyLoggerProvider.get();
    }

    public void inject(ProfileCompletionActivity arg0) {
        this.profileCompletionActivityMembersInjector.injectMembers(arg0);
    }

    public Provider<com.airbnb.android.lib.LibComponent.Builder> libComponentProvider() {
        return this.libComponentBuilderProvider;
    }

    public Provider<com.airbnb.p027n2.N2Component.Builder> n2ComponentProvider() {
        return this.n2ComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.p011p3.P3Component.Builder> p3ComponentProvider() {
        return this.p3ComponentBuilderProvider;
    }

    public void inject(BaseAddPayoutMethodFragment arg0) {
        this.baseAddPayoutMethodFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(SelectPayoutCountryActivity arg0) {
        this.selectPayoutCountryActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(EditPayoutFragment arg0) {
        this.editPayoutFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(PayoutRedirectWebviewActivity arg0) {
        this.payoutRedirectWebviewActivityMembersInjector.injectMembers(arg0);
    }

    public void inject(AddPayoutMethodDataController arg0) {
        this.addPayoutMethodDataControllerMembersInjector.injectMembers(arg0);
    }

    public Provider<com.airbnb.android.checkin.data.CheckInComponent.Builder> checkInComponentProvider() {
        return this.checkInComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.explore.ExploreComponent.Builder> exploreComponentProvider() {
        return this.exploreComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.referrals.ReferralsComponent.Builder> referralsComponentProvider() {
        return this.referralsComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.lib.AppRaterComponent.Builder> appRaterComponentProvider() {
        return this.appRaterComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.guestrecovery.GuestRecoveryComponent.Builder> guestRecoveryComponentProvider() {
        return this.guestRecoveryComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.pickwishlist.PickWishListComponent.Builder> pickWishListComponentProvider() {
        return this.pickWishListComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.contentframework.ContentFrameworkComponent.Builder> contentFrameworkComponentProvider() {
        return this.contentFrameworkComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.identity.IdentityComponent.Builder> identityComponentProvider() {
        return this.identityComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.registration.RegistrationComponent.Builder> registrationComponentProvider() {
        return this.registrationComponentBuilderProvider;
    }

    public Provider<com.airbnb.android.login.LoginComponent.Builder> loginComponentProvider() {
        return this.loginComponentBuilderProvider;
    }

    public Lazy<AppLaunchAnalytics> appLaunchAnalytics() {
        return DoubleCheck.lazy(this.provideAppLaunchAnalyticsProvider);
    }

    public Lazy<ClientSessionValidator> clientSessionValidator() {
        return DoubleCheck.lazy(this.provideClientSessionValidatorProvider);
    }

    public AppForegroundDetector appForegroundDetector() {
        return (AppForegroundDetector) this.provideAppForegroundDetectorProvider.get();
    }

    public Lazy<AppForegroundAnalytics> appForegroundAnalytics() {
        return DoubleCheck.lazy(this.provideAppForegroundAnalyticsProvider);
    }

    public ViewBreadcrumbManager viewBreadcrumbManager() {
        return (ViewBreadcrumbManager) this.provideViewBreadcrumbManagerProvider.get();
    }

    public Lazy<Set<PostInteractiveInitializer>> needsPostInteractiveInitialization() {
        return DoubleCheck.lazy(this.setOfPostInteractiveInitializerProvider);
    }

    public Lazy<Set<PostApplicationCreatedInitializer>> needsPostApplicationCreatedInitialization() {
        return DoubleCheck.lazy(this.setOfPostApplicationCreatedInitializerProvider);
    }

    public AppLaunchUtils appLaunchUtils() {
        return (AppLaunchUtils) this.provideAppLaunchUtilsProvider.get();
    }

    public AppLaunchAnalytics appLaunchAnalitycs() {
        return (AppLaunchAnalytics) this.provideAppLaunchAnalyticsProvider.get();
    }

    public DebugSettings debugSettings() {
        return (DebugSettings) this.provideDebugSettingsProvider.get();
    }

    public AirbnbApi airbnbApi() {
        return (AirbnbApi) this.provideAirbnbApiProvider.get();
    }

    public WishListManager wishListManager() {
        return (WishListManager) this.provideWishListManagerProvider.get();
    }

    public SuperHeroManager superHeroManager() {
        return (SuperHeroManager) this.provideSuperHeroManagerProvider.get();
    }

    public ExperimentConfigController experimentConfigController() {
        return (ExperimentConfigController) this.provideExperimentConfigControllerProvider.get();
    }

    public LocationClientFacade locationHelper() {
        return (LocationClientFacade) this.provideLocationHelperProvider.get();
    }

    public LocalPushNotificationManager localPushNotificationManager() {
        return (LocalPushNotificationManager) this.provideLocalPushNotificationManagerProvider.get();
    }

    public BottomBarController bottomBarController() {
        return (BottomBarController) this.bottomBarControllerProvider.get();
    }

    public ProfileCompletionManager profileCompletionManager() {
        return (ProfileCompletionManager) this.profileCompletionManagerProvider.get();
    }

    public ItineraryManager itineraryManager() {
        return (ItineraryManager) this.provideItineraryManagerProvider.get();
    }

    public IdentityJitneyLogger identityJitneyLogger() {
        return (IdentityJitneyLogger) this.provideIdentityJitneyEventLoggerProvider.get();
    }

    public void inject(PhotoUploadService arg0) {
        this.photoUploadServiceMembersInjector.injectMembers(arg0);
    }

    public void inject(PhotoUploadRetryBroadcastReceiver arg0) {
        this.photoUploadRetryBroadcastReceiverMembersInjector.injectMembers(arg0);
    }

    public ExperimentAssignments experimentAssignments() {
        return (ExperimentAssignments) this.provideExperimentAssigmentsProvider.get();
    }

    public void inject(HostReferralsFragment arg0) {
        this.hostReferralsFragmentMembersInjector.injectMembers(arg0);
    }

    public void inject(AirbnbApplication app) {
        this.airbnbApplicationMembersInjector.injectMembers(app);
    }

    public void inject(AirActivity airActivity) {
        this.airActivityMembersInjector.injectMembers(airActivity);
    }

    public void inject(EntryActivity entryActivity) {
        this.entryActivityMembersInjector.injectMembers(entryActivity);
    }

    public void inject(SearchIntentActivity searchIntentActivity) {
        this.searchIntentActivityMembersInjector.injectMembers(searchIntentActivity);
    }

    public void inject(SplashScreenActivity splashScreenActivity) {
        this.splashScreenActivityMembersInjector.injectMembers(splashScreenActivity);
    }

    public void inject(InboxActivity airActivity) {
        this.inboxActivityMembersInjector.injectMembers(airActivity);
    }

    public void inject(CreateIdentificationActivity createIdentificationActivity) {
        this.createIdentificationActivityMembersInjector.injectMembers(createIdentificationActivity);
    }

    public void inject(BusinessTravelInterstitialFragment businessTravelInterstitialFragment) {
        this.businessTravelInterstitialFragmentMembersInjector.injectMembers(businessTravelInterstitialFragment);
    }

    public void inject(QuickPayActivity quickPayActivity) {
        this.quickPayActivityMembersInjector.injectMembers(quickPayActivity);
    }

    public void inject(WorkEmailActivity workEmailActivity) {
        this.workEmailActivityMembersInjector.injectMembers(workEmailActivity);
    }

    public void inject(ShakeFeedbackDialog zenDialog) {
        this.shakeFeedbackDialogMembersInjector.injectMembers(zenDialog);
    }

    public void inject(AppUpgradeDialogFragment appUpgradeDialogFragment) {
        this.appUpgradeDialogFragmentMembersInjector.injectMembers(appUpgradeDialogFragment);
    }

    public void inject(AirFragment airFragment) {
        this.airFragmentMembersInjector.injectMembers(airFragment);
    }

    public void inject(HostListingSelectorFragment fragment) {
        this.hostListingSelectorFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(FeedbackSummaryFragment fragment) {
        this.feedbackSummaryFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(SavedMessagesFragment fragment) {
        this.savedMessagesFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(CreateNewSavedMessageFragment fragment) {
        this.createNewSavedMessageFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(SearchSettingsFragment fragment) {
        this.searchSettingsFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(WLDetailsDeeplinkInterceptorActivity wlDetailsDeeplinkInterceptorActivity) {
        this.wLDetailsDeeplinkInterceptorActivityMembersInjector.injectMembers(wlDetailsDeeplinkInterceptorActivity);
    }

    public void inject(HelpThreadFragment helpThreadFragment) {
        this.helpThreadFragmentMembersInjector.injectMembers(helpThreadFragment);
    }

    public void inject(DLSReservationObjectFragment fragment) {
        this.dLSReservationObjectFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(HostReservationObjectFragment fragment) {
        this.hostReservationObjectFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(AdvancedSettingsFragment airFragment) {
        this.advancedSettingsFragmentMembersInjector.injectMembers(airFragment);
    }

    public void inject(WishListIndexFragment wishListIndexFragment) {
        this.wishListIndexFragmentMembersInjector.injectMembers(wishListIndexFragment);
    }

    public void inject(QuickPayFragment quickPayFragment) {
        this.quickPayFragmentMembersInjector.injectMembers(quickPayFragment);
    }

    public void inject(HomesQuickPayFragment homesQuickPayFragment) {
        this.homesQuickPayFragmentMembersInjector.injectMembers(homesQuickPayFragment);
    }

    public void inject(PaymentOptionsFragment paymentOptionsFragment) {
        this.paymentOptionsFragmentMembersInjector.injectMembers(paymentOptionsFragment);
    }

    public void inject(CreditCardDetailsFragment creditCardDetailsFragment) {
        this.creditCardDetailsFragmentMembersInjector.injectMembers(creditCardDetailsFragment);
    }

    public void inject(AddCouponCodeFragment addCouponCodeFragment) {
        this.addCouponCodeFragmentMembersInjector.injectMembers(addCouponCodeFragment);
    }

    public void inject(AddPaymentMethodFragment addPaymentMethodFragment) {
        this.addPaymentMethodFragmentMembersInjector.injectMembers(addPaymentMethodFragment);
    }

    public void inject(SelectBillingCountryFragment selectBillingCountryFragment) {
        this.selectBillingCountryFragmentMembersInjector.injectMembers(selectBillingCountryFragment);
    }

    public void inject(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment) {
        this.brazilCreditCardDetailsFragmentMembersInjector.injectMembers(brazilCreditCardDetailsFragment);
    }

    public void inject(ReferralBroadcastReceiver referralBroadcastReceiver) {
        this.referralBroadcastReceiverMembersInjector.injectMembers(referralBroadcastReceiver);
    }

    public void inject(AirWebView airWebView) {
        this.airWebViewMembersInjector.injectMembers(airWebView);
    }

    public void inject(JPushHelper jPushHelper) {
        this.jPushHelperMembersInjector.injectMembers(jPushHelper);
    }

    public void inject(DebugMenuActivity debugMenuActivity) {
        this.debugMenuActivityMembersInjector.injectMembers(debugMenuActivity);
    }

    public void inject(EndpointAdapter endpointAdapter) {
        this.endpointAdapterMembersInjector.injectMembers(endpointAdapter);
    }

    public void inject(HHBaseAdapter hhBaseAdapter) {
        this.hHBaseAdapterMembersInjector.injectMembers(hhBaseAdapter);
    }

    public void inject(HostHomeWidgetProvider hostHomeWidgetProvider) {
        this.hostHomeWidgetProviderMembersInjector.injectMembers(hostHomeWidgetProvider);
    }

    public void inject(PayoutSelectFragment payoutSelectFragment) {
        this.payoutSelectFragmentMembersInjector.injectMembers(payoutSelectFragment);
    }

    public void inject(TOSDialogFragment tosDialogFragment) {
        this.tOSDialogFragmentMembersInjector.injectMembers(tosDialogFragment);
    }

    public void inject(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCoeChildFragment) {
        this.completeProfilePhoneCodeChildFragmentMembersInjector.injectMembers(completeProfilePhoneCoeChildFragment);
    }

    public void inject(TripsReservationsSyncService backgroundSyncIntentService) {
        this.tripsReservationsSyncServiceMembersInjector.injectMembers(backgroundSyncIntentService);
    }

    public void inject(ViewedListingsPersistenceService viewedListingsPersistenceService) {
        this.viewedListingsPersistenceServiceMembersInjector.injectMembers(viewedListingsPersistenceService);
    }

    public void inject(PushIntentService pushIntentService) {
        this.pushIntentServiceMembersInjector.injectMembers(pushIntentService);
    }

    public void inject(MessagePushNotification messagePushNotification) {
        MembersInjectors.noOp().injectMembers(messagePushNotification);
    }

    public void inject(WebIntentDispatch webIntentDispatch) {
        this.webIntentDispatchMembersInjector.injectMembers(webIntentDispatch);
    }

    public void inject(OnlineIdChildFragment onlineIdChildFragment) {
        this.onlineIdChildFragmentMembersInjector.injectMembers(onlineIdChildFragment);
    }

    public void inject(HHListRemoteViewsFactory hhListRemoteViewsFactory) {
        this.hHListRemoteViewsFactoryMembersInjector.injectMembers(hhListRemoteViewsFactory);
    }

    public void inject(LocaleChangedReceiver localeChangedReceiver) {
        this.localeChangedReceiverMembersInjector.injectMembers(localeChangedReceiver);
    }

    public void inject(OfficialIdIntentService officialIdIntentService) {
        this.officialIdIntentServiceMembersInjector.injectMembers(officialIdIntentService);
    }

    public void inject(VerifiedIdCompletedFragment verifiedIdCompletedFragment) {
        this.verifiedIdCompletedFragmentMembersInjector.injectMembers(verifiedIdCompletedFragment);
    }

    public void inject(GCMHelper gcmHelper) {
        this.gCMHelperMembersInjector.injectMembers(gcmHelper);
    }

    public void inject(LocalBitmapForDisplayScalingTask localBitmapForDisplayScalingTask) {
        this.localBitmapForDisplayScalingTaskMembersInjector.injectMembers(localBitmapForDisplayScalingTask);
    }

    public void inject(OfficialIdPhotoSelectionFragment officialIdPhotoSelectionFragment) {
        this.officialIdPhotoSelectionFragmentMembersInjector.injectMembers(officialIdPhotoSelectionFragment);
    }

    public void inject(EditProfileDetailsAdapter editProfileDetailsAdapter) {
        this.editProfileDetailsAdapterMembersInjector.injectMembers(editProfileDetailsAdapter);
    }

    public void inject(WelcomeScreenFragment welcomeScreenFragment) {
        MembersInjectors.noOp().injectMembers(welcomeScreenFragment);
    }

    public void inject(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        this.completeProfilePhoneChildFragmentMembersInjector.injectMembers(completeProfilePhoneChildFragment);
    }

    public void inject(OfflineIdChildFragment offlineIdChildFragment) {
        this.offlineIdChildFragmentMembersInjector.injectMembers(offlineIdChildFragment);
    }

    public void inject(SesameVerificationChildFragment sesameVerificationChildFragment) {
        this.sesameVerificationChildFragmentMembersInjector.injectMembers(sesameVerificationChildFragment);
    }

    public void inject(SesameVerificationConnectFragment sesameVerificationConnectFragment) {
        this.sesameVerificationConnectFragmentMembersInjector.injectMembers(sesameVerificationConnectFragment);
    }

    public void inject(OfficialIdCountryFragment officialIdCountryFragment) {
        MembersInjectors.noOp().injectMembers(officialIdCountryFragment);
    }

    public void inject(OfficialIdErrorFragment officialIdErrorFragment) {
        this.officialIdErrorFragmentMembersInjector.injectMembers(officialIdErrorFragment);
    }

    public void inject(OfficialIdTypeFragment officialIdTypeFragment) {
        MembersInjectors.noOp().injectMembers(officialIdTypeFragment);
    }

    public void inject(PricingQuotePricingDetails pricingQuotePricingDetails) {
        this.pricingQuotePricingDetailsMembersInjector.injectMembers(pricingQuotePricingDetails);
    }

    public void inject(SearchCalendarAdapter searchCalendarAdapter) {
        MembersInjectors.noOp().injectMembers(searchCalendarAdapter);
    }

    public void inject(AppUpgradeReceiver receiver) {
        this.appUpgradeReceiverMembersInjector.injectMembers(receiver);
    }

    public void inject(ReservationObjectAdapter reservationObjectAdapter) {
        this.reservationObjectAdapterMembersInjector.injectMembers(reservationObjectAdapter);
    }

    public void inject(HostReservationObjectAdapter hostReservationObjectAdapter) {
        this.hostReservationObjectAdapterMembersInjector.injectMembers(hostReservationObjectAdapter);
    }

    public void inject(PriceGroupedCell priceGroupedCell) {
        this.priceGroupedCellMembersInjector.injectMembers(priceGroupedCell);
    }

    public void inject(SharingManager sharingManager) {
        this.sharingManagerMembersInjector.injectMembers(sharingManager);
    }

    public void inject(EmptyResultsCardView emptyResultsCardView) {
        this.emptyResultsCardViewMembersInjector.injectMembers(emptyResultsCardView);
    }

    public void inject(WifiAlarmReceiver wifiAlarmReceiver) {
        this.wifiAlarmReceiverMembersInjector.injectMembers(wifiAlarmReceiver);
    }

    public void inject(AccountSettingsFragment accountSettingsFragment) {
        this.accountSettingsFragmentMembersInjector.injectMembers(accountSettingsFragment);
    }

    public void inject(EditProfileFragment editProfileFragment) {
        this.editProfileFragmentMembersInjector.injectMembers(editProfileFragment);
    }

    public void inject(PhoneVerificationFragment phoneVerificationFragment) {
        this.phoneVerificationFragmentMembersInjector.injectMembers(phoneVerificationFragment);
    }

    public void inject(PhotoVerificationFragment photoVerificationFragment) {
        this.photoVerificationFragmentMembersInjector.injectMembers(photoVerificationFragment);
    }

    public void inject(HelpThreadDialogActivity helpThreadDialogActivity) {
        this.helpThreadDialogActivityMembersInjector.injectMembers(helpThreadDialogActivity);
    }

    public void inject(CompleteProfilePhotoFragment completeProfilePhotoFragment) {
        this.completeProfilePhotoFragmentMembersInjector.injectMembers(completeProfilePhotoFragment);
    }

    public void inject(ButtonPartnership buttonPartnership) {
        this.buttonPartnershipMembersInjector.injectMembers(buttonPartnership);
    }

    public void inject(SpecialOfferActivity specialOfferActivity) {
        this.specialOfferActivityMembersInjector.injectMembers(specialOfferActivity);
    }

    public void inject(PayWithAlipayActivity payWithAlipayActivity) {
        this.payWithAlipayActivityMembersInjector.injectMembers(payWithAlipayActivity);
    }

    public void inject(DateAndGuestCountView DateAndGuestCountView) {
        MembersInjectors.noOp().injectMembers(DateAndGuestCountView);
    }

    public void inject(ErfOverrideActivity erfOverrideActivity) {
        this.erfOverrideActivityMembersInjector.injectMembers(erfOverrideActivity);
    }

    public void inject(EditableCell editableCell) {
        this.editableCellMembersInjector.injectMembers(editableCell);
    }

    public void inject(ThreadAdapter adapter) {
        this.threadAdapterMembersInjector.injectMembers(adapter);
    }

    public void inject(HostStatsFragment hostStatsFragment) {
        this.hostStatsFragmentMembersInjector.injectMembers(hostStatsFragment);
    }

    public void inject(InboxFragment fragment) {
        this.inboxFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(ThreadFragment fragment) {
        this.threadFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(IdentificationNameFragment identificationNameFragment) {
        this.identificationNameFragmentMembersInjector.injectMembers(identificationNameFragment);
    }

    public void inject(GuestIdentificationAdapter guestIdentificationAdapter) {
        this.guestIdentificationAdapterMembersInjector.injectMembers(guestIdentificationAdapter);
    }

    public void inject(HostReviewDetailAdapter adapter) {
        this.hostReviewDetailAdapterMembersInjector.injectMembers(adapter);
    }

    public void inject(HostReviewDetailsFragment fragment) {
        this.hostReviewDetailsFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(HostDemandsDetailFragment fragment) {
        this.hostDemandsDetailFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(AcceptReservationFragment acceptReservationFragment) {
        this.acceptReservationFragmentMembersInjector.injectMembers(acceptReservationFragment);
    }

    public void inject(ReservationPickerFragment reservationPickerFragment) {
        this.reservationPickerFragmentMembersInjector.injectMembers(reservationPickerFragment);
    }

    public void inject(PreapproveInquiryFragment preapproveInquiryFragment) {
        this.preapproveInquiryFragmentMembersInjector.injectMembers(preapproveInquiryFragment);
    }

    public void inject(RemovePreapprovalFragment removePreapprovalFragment) {
        this.removePreapprovalFragmentMembersInjector.injectMembers(removePreapprovalFragment);
    }

    public void inject(ReservationResponseActivity activity) {
        this.reservationResponseActivityMembersInjector.injectMembers(activity);
    }

    public void inject(ReservationResponseLandingFragment fragment) {
        this.reservationResponseLandingFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(GuestPickerFragment fragment) {
        this.guestPickerFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(TrebuchetOverrideActivity activity) {
        this.trebuchetOverrideActivityMembersInjector.injectMembers(activity);
    }

    public void inject(FeedbackIntroFragment fragment) {
        this.feedbackIntroFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(AccountPageFragment fragment) {
        this.accountPageFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(InboxContainerFragment fragment) {
        this.inboxContainerFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(CommunityCommitmentFragment fragment) {
        this.communityCommitmentFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(CommunityCommitmentCancelAccountFragment fragment) {
        this.communityCommitmentCancelAccountFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(CommunityCommitmentWriteFeedbackFragment fragment) {
        this.communityCommitmentWriteFeedbackFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(CommunityCommitmentLearnMoreFragment fragment) {
        this.communityCommitmentLearnMoreFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(AcceptReservationConfirmationFragment adapter) {
        this.acceptReservationConfirmationFragmentMembersInjector.injectMembers(adapter);
    }

    public void inject(BasePurchaseAmenityFragment fragment) {
        this.basePurchaseAmenityFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(BasePendingAmenityFragment fragment) {
        this.basePendingAmenityFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(BaseCreateAmenityFragment fragment) {
        this.baseCreateAmenityFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(HostAmenityListFragment fragment) {
        this.hostAmenityListFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(InboxAdapter adapter) {
        this.inboxAdapterMembersInjector.injectMembers(adapter);
    }

    public void inject(ReasonPickerFragment fragment) {
        this.reasonPickerFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(ReservationCanceledFragment fragment) {
        this.reservationCanceledFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(ReservationCancellationWithUserInputFragment fragment) {
        this.reservationCancellationWithUserInputFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(WorkEmailFragment fragment) {
        this.workEmailFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(BottomBarBadgeInboxHandler handler) {
        this.bottomBarBadgeInboxHandlerMembersInjector.injectMembers(handler);
    }

    public void inject(P3AdditionalPriceFragment fragment) {
        this.p3AdditionalPriceFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(PostBookingBusinessTravelPromoFragment fragment) {
        this.postBookingBusinessTravelPromoFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(VerifyWorkEmailFragment fragment) {
        this.verifyWorkEmailFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(UserProfileFragment fragment) {
        this.userProfileFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(UserProfileActivity activity) {
        this.userProfileActivityMembersInjector.injectMembers(activity);
    }

    public void inject(PostBookingActivity activity) {
        this.postBookingActivityMembersInjector.injectMembers(activity);
    }

    public void inject(BusinessTravelWelcomeFragment fragment) {
        this.businessTravelWelcomeFragmentMembersInjector.injectMembers(fragment);
    }

    public void inject(com.airbnb.android.lib.china5a.fragments.PhotoVerificationFragment fragment) {
        this.photoVerificationFragmentMembersInjector2.injectMembers(fragment);
    }

    public void inject(PhotoVerificationPresenter presenter) {
        this.photoVerificationPresenterMembersInjector.injectMembers(presenter);
    }

    public void inject(AdvancedSettingsEpoxyController controller) {
        this.advancedSettingsEpoxyControllerMembersInjector.injectMembers(controller);
    }

    public void inject(PreloadExecutor preloader) {
        MembersInjectors.noOp().injectMembers(preloader);
    }
}
