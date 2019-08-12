package com.airbnb.android.core.analytics;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.NavigationLoggingElement;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.Navigation.p162v1.ImpressionEvent.Builder;

public final class NavigationLogging extends BaseAnalytics {
    private static final String APPLICATION_MODE = "app_mode";
    private static final String EVENT_NAME_NAVIGATION = "navigation";
    private static final String FRAGMENT_NAME = "fragment";
    public static final String LISTING_ID = "listing_id";
    private static final String REFERRER = "referrer";
    public static final String TITLE = "title";
    protected final Context context;
    protected final DebugSettings debugSettings;
    protected final LoggingContextFactory loggingContextFactory;
    private String previousImpressionKey;
    protected final SharedPrefsHelper sharedPrefsHelper;

    public NavigationLogging(Context context2, DebugSettings debugSettings2, SharedPrefsHelper sharedPrefsHelper2, LoggingContextFactory loggingContextFactory2) {
        this.context = context2;
        this.debugSettings = debugSettings2;
        this.sharedPrefsHelper = sharedPrefsHelper2;
        this.loggingContextFactory = loggingContextFactory2;
    }

    public void trackImpression(NavigationLoggingElement element) {
        trackImpressionImpl(element.getNavigationTrackingTag(), addElementNameToParams(element));
    }

    public void trackImpressionExplicitly(NavigationTag tag, Strap parameters) {
        trackImpressionImpl(tag, parameters);
    }

    private void trackImpressionImpl(NavigationTag tag, Strap parameters) {
        String str;
        if (parameters == null) {
            parameters = Strap.make();
        }
        if (!tag.shouldSkipLogging()) {
            handleDebugging(tag, parameters);
            addAppropriateDlsLogging(parameters, tag.modeType);
            String appMode = this.sharedPrefsHelper.isGuestMode() ? TripRole.ROLE_KEY_GUEST : TripRole.ROLE_KEY_HOST;
            JitneyPublisher.publish(new Builder(this.loggingContextFactory.newInstance(), tag.trackingName, TextUtils.isEmpty(this.previousImpressionKey) ? "unknown" : this.previousImpressionKey).info(Strap.make().mo11639kv(APPLICATION_MODE, appMode).mix(MiscUtils.sanitize(parameters))));
            AirbnbEventLogger.track(EVENT_NAME_NAVIGATION, parameters.mo11639kv("page", tag.trackingName).mo11639kv(BaseAnalytics.OPERATION, "impression").mo11639kv(APPLICATION_MODE, appMode).mo11639kv(REFERRER, this.previousImpressionKey));
            if (tag == NavigationTag.Unknown) {
                str = getElementNameFromParams(parameters);
            } else {
                str = tag.trackingName;
            }
            this.previousImpressionKey = str;
        }
    }

    private static Strap addElementNameToParams(NavigationLoggingElement element) {
        return element.getNavigationTrackingParams().mo11639kv(FRAGMENT_NAME, element.getClass().getSimpleName());
    }

    private static String getElementNameFromParams(Strap parameters) {
        return parameters.getString(FRAGMENT_NAME);
    }

    private void handleDebugging(NavigationTag tag, Strap parameters) {
        if (DebugSettings.NAVIGATION_LOGGING_VIEW.isEnabled()) {
            Toast.makeText(this.context, this.context.getString(C0716R.string.debug_display_all_navigation_logs, new Object[]{tag.trackingName, parameters.toJsonString()}), 1).show();
        }
    }
}
