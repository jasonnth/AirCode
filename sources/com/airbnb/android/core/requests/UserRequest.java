package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class UserRequest extends BaseRequestV2<UserResponse> {
    private static final String FOR_DASHBOARD_ALERTS = "for_dashboard_alerts";
    private static final String FOR_MOBILE_PROFILES = "for_mobile_profiles";
    private static final String FOR_NESTED_LISTINGS = "for_nested_listings";
    private static final String FOR_PROFILE_COMPLETION = "for_profile_completion";
    private static final String FOR_VERIFICATIONS = "for_verifications";
    private static final String V1_LEGACY_SHOW = "v1_legacy_show";
    private static final String WITH_STORIES = "with_content_framework_articles";
    private final String format;
    private final long userId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Format {
    }

    public static UserRequest newRequestWithStories(long userId2) {
        return new UserRequest(userId2, WITH_STORIES);
    }

    public static UserRequest newRequestForVerifications(long userId2) {
        return new UserRequest(userId2, FOR_VERIFICATIONS);
    }

    public static UserRequest newRequestForMobileProfiles(long userId2) {
        return new UserRequest(userId2, FOR_MOBILE_PROFILES);
    }

    public static UserRequest newRequestForProfileCompletion(long userId2) {
        return new UserRequest(userId2, FOR_PROFILE_COMPLETION);
    }

    public static UserRequest newRequestForNestedListingsEligibility(long userId2) {
        return new UserRequest(userId2, FOR_NESTED_LISTINGS);
    }

    public static UserRequest newRequestForDashboardAlerts(long userId2) {
        return new UserRequest(userId2, FOR_DASHBOARD_ALERTS);
    }

    public UserRequest(long userId2, BaseRequestListener<UserResponse> listener) {
        withListener((Observer) listener);
        this.userId = userId2;
        this.format = V1_LEGACY_SHOW;
    }

    private UserRequest(long userId2, String format2) {
        this.userId = userId2;
        this.format = format2;
    }

    public String getPath() {
        return "users/" + this.userId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.format);
    }

    public long getCacheTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return UserResponse.class;
    }
}
