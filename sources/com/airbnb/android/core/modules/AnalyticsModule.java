package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.LogAirInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.AlipayAnalytics;
import com.airbnb.android.core.analytics.AppForegroundAnalytics;
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
import com.airbnb.android.core.analytics.PaidAmenityJitneyLogger;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.analytics.ReservationCancellationLogger;
import com.airbnb.android.core.analytics.ReviewSearchJitneyLogger;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.core.services.NetworkTimeProvider;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import dagger.Lazy;

public class AnalyticsModule {
    /* access modifiers changed from: 0000 */
    public Tracker provideAnalyticsTracker(Context context) {
        GoogleAnalytics ga = GoogleAnalytics.getInstance(context);
        ga.setDryRun(BuildHelper.isDevelopmentBuild());
        Tracker tracker = ga.newTracker(context.getString(C0716R.string.ga_trackingId));
        tracker.enableAutoActivityTracking(true);
        return tracker;
    }

    /* access modifiers changed from: 0000 */
    public AppForegroundDetector provideAppForegroundDetector() {
        return new AppForegroundDetector();
    }

    /* access modifiers changed from: 0000 */
    public VerifiedIdAnalytics provideVerifiedIdAnalytics() {
        return new VerifiedIdAnalytics();
    }

    /* access modifiers changed from: 0000 */
    public AlipayAnalytics provideAlipayAnalytics() {
        return new AlipayAnalytics();
    }

    /* access modifiers changed from: 0000 */
    public LogAirInitializer provideLogAirInitializer(Context context, Lazy<AirbnbApi> airbnbApi, MemoryUtils memoryUtils, ConverterFactory converterFactory) {
        return new LogAirInitializer(context, airbnbApi, memoryUtils, converterFactory);
    }

    /* access modifiers changed from: 0000 */
    public TimeSkewAnalytics provideTimeSkewAnalytics(AirbnbPreferences airbnbPreferences, NetworkTimeProvider networkTimeProvider) {
        return new TimeSkewAnalytics(airbnbPreferences, networkTimeProvider);
    }

    /* access modifiers changed from: 0000 */
    public AppForegroundAnalytics provideAppForegroundAnalytics(TimeSkewAnalytics timeSkewAnalytics) {
        return new AppForegroundAnalytics(timeSkewAnalytics);
    }

    static CalendarJitneyLogger provideCalendarJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new CalendarJitneyLogger(loggingContextFactory);
    }

    static AvailabilityCalendarJitneyLogger provideAvailabilityCalendarJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new AvailabilityCalendarJitneyLogger(loggingContextFactory);
    }

    static HostReservationObjectJitneyLogger provideHostReservationObjectJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new HostReservationObjectJitneyLogger(loggingContextFactory);
    }

    static ReviewSearchJitneyLogger provideReviewSearchJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new ReviewSearchJitneyLogger(loggingContextFactory);
    }

    static HostStatsJitneyLogger provideHostStatsJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new HostStatsJitneyLogger(loggingContextFactory);
    }

    static QuickPayJitneyLogger provideQuickPayJitneyEventLogger(LoggingContextFactory loggingContextFactory) {
        return new QuickPayJitneyLogger(loggingContextFactory);
    }

    static BookingJitneyLogger provideBookingJitneyEventLogger(LoggingContextFactory loggingContextFactory) {
        return new BookingJitneyLogger(loggingContextFactory);
    }

    static IdentityJitneyLogger provideIdentityJitneyEventLogger(LoggingContextFactory loggingContextFactory, ObjectMapper objectMapper) {
        return new IdentityJitneyLogger(loggingContextFactory, objectMapper);
    }

    static CommunityCommitmentJitneyLogger provideCommunityCommitmentJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new CommunityCommitmentJitneyLogger(loggingContextFactory);
    }

    static PaidAmenityJitneyLogger providePaidAmenityJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new PaidAmenityJitneyLogger(loggingContextFactory);
    }

    static BusinessTravelJitneyLogger provideBusinessTravelJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new BusinessTravelJitneyLogger(loggingContextFactory);
    }

    static CohostingManagementJitneyLogger provideCohostingManagementJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new CohostingManagementJitneyLogger(loggingContextFactory);
    }

    static CohostingInvitationJitneyLogger provideCohostingInvitationJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new CohostingInvitationJitneyLogger(loggingContextFactory);
    }

    static CohostingReusableFlowJitneyLogger provideCohostingReusableFlowJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new CohostingReusableFlowJitneyLogger(loggingContextFactory);
    }

    static WishListLogger provideWishListLogger(LoggingContextFactory loggingContextFactory) {
        return new WishListLogger(loggingContextFactory);
    }

    static MessagingJitneyLogger provideMessagingJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new MessagingJitneyLogger(loggingContextFactory);
    }

    static ReservationResponseLogger provideReservationResponseLogger(LoggingContextFactory loggingContextFactory) {
        return new ReservationResponseLogger(loggingContextFactory);
    }

    static ReservationCancellationLogger provideReservationCancellationLogger(LoggingContextFactory loggingContextFactory) {
        return new ReservationCancellationLogger(loggingContextFactory);
    }
}
