package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;

public final class UserProfileIntents {
    public static final String ARG_HIDE_PROFILE_PHOTO = "show_no_profile_photo";
    public static final String ARG_HIDE_REVIEWS = "hide_reviews";
    public static final String ARG_IS_SELF = "is_self";
    public static final String ARG_USER = "user";
    public static final String ARG_USER_ID = "user_id";

    private UserProfileIntents() {
    }

    public static Intent intentForUserId(Context context, long userId) {
        Check.state(userId > 0, "User id is invalid: " + userId);
        return createIntent(context).putExtra("user_id", userId);
    }

    public static Intent intentForUser(Context context, User user) {
        Check.notNull(user, "User cannot be null");
        return createIntent(context).putExtra("user", user);
    }

    public static Intent intentForUserAndReservationStatus(Context context, User user, ReservationStatus status) {
        return createIntent(context).putExtra("user", user).putExtra(ARG_HIDE_PROFILE_PHOTO, FeatureToggles.hideGuestProfilePhotoOnProfile(status));
    }

    public static Intent intentForCurrentUser(Context context) {
        return createIntent(context).putExtra(ARG_IS_SELF, true);
    }

    private static Intent createIntent(Context context) {
        return new Intent(context, Activities.userProfile());
    }

    public static Intent intentForAccountDeeplink(Context context) {
        return intentForCurrentUser(context);
    }
}
