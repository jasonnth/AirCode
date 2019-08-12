package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.utils.Strap;
import com.squareup.otto.Bus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class DebugSettings {
    public static final BooleanDebugSetting APP_RATER = new Builder("enable_app_rater", C0716R.string.enable_app_rater).build();
    public static final BooleanDebugSetting AUSTRALIA_SIMULATION = new Builder("simulate_in_australia", C0716R.string.debug_menu_australia_title).build();
    public static final BooleanDebugSetting BED_DETAILS = new Builder("enable_bed_details", C0716R.string.debug_menu_enable_bed_details).build();
    public static final BooleanDebugSetting CHECK_IN_GUIDE = new Builder("enable_checkin_guide", C0716R.string.debug_menu_enable_checkin_guide).build();
    public static final BooleanDebugSetting CHECK_IN_GUIDE_OFFLINE_SUPPORT = new Builder("enable_checkin_guide_offline_support", C0716R.string.debug_menu_enable_checkin_guide_offline_support).build();
    public static final BooleanDebugSetting CHINA_SIMLATION = new Builder("simulate_in_china", C0716R.string.debug_menu_china_title).build();
    public static final BooleanDebugSetting COHOSTING_STATS = new Builder("enable_cohosting_stats", C0716R.string.enable_cohosting_stats).build();
    public static final BooleanDebugSetting DEFAULT_HOME_TAB_FOR_FAMILIES = new Builder("enable_default_home_tab_for_families", C0716R.string.debug_menu_enable_default_home_tab_for_families).build();
    public static final BooleanDebugSetting DELIVER_LOCAL_PUSH_IN_TWENTY_SECONDS = new Builder("deliver_local_push_in_20", C0716R.string.debug_menu_deliver_local_push_20_seconds).build();
    public static final BooleanDebugSetting DISABLE_IDENTITY_FLOW = new Builder("disable_identity_flow", C0716R.string.debug_menu_disable_identity_flow).build();
    public static final BooleanDebugSetting DISABLE_LOCAL_PUSH = new Builder("internal_settings_disable_local_push_notification", C0716R.string.debug_menu_disable_local_push).build();
    public static final BooleanDebugSetting DISPLAY_FLIGHT_RESERVATIONS = new Builder("enable_flight_reservations", C0716R.string.debug_menu_flight_reservations).build();
    public static final BooleanDebugSetting DLS_COMPONENT_OVERLAYS = new Builder("dls_component_overlays_enabled", C0716R.string.debug_menu_dls_component_overlays).setCanEnableIfInternalSettingsEnabled().build();
    public static final BooleanDebugSetting DLS_INSIGHTS = new Builder("enable_dls_insights", C0716R.string.debug_menu_enable_dls_insights).build();
    public static final BooleanDebugSetting ENABLE_COHOSTING_MOBILE_DASHBOARD = new Builder("enable_cohosting_mobile_dashboard", C0716R.string.enable_cohosting_mobile_dashboard).build();
    public static final BooleanDebugSetting ENABLE_COHOSTING_NOTIFICATION = new Builder("enable_cohosting_notification", C0716R.string.enable_cohosting_notification).build();
    public static final BooleanDebugSetting ENABLE_DYNAMIC_STRINGS = new Builder("enable_dynamic_strings", C0716R.string.enable_dynamic_strings).build();
    public static final BooleanDebugSetting ENABLE_FILTER_SUGGESTION_BAR = new Builder("enable_filter_suggestion_bar", C0716R.string.debug_menu_enable_filter_suggestion_bar).build();
    public static final BooleanDebugSetting ENABLE_SELECT_ML = new Builder("enable_select_ml", C0716R.string.debug_menu_enable_select_ml).build();
    public static final BooleanDebugSetting EVENT_LOG_VIEW = new Builder("display_all_event_logging", C0716R.string.debug_menu_show_debug_logging_all).build();
    public static final BooleanDebugSetting FAMILY_AMENITIES = new Builder("enable_family_amenities", C0716R.string.debug_menu_enable_family_amenities_ml).build();
    public static final BooleanDebugSetting FORCE_DEEPLINK = new Builder("enable_force_deeplink", C0716R.string.debug_menu_force_dld).build();
    public static final BooleanDebugSetting FUTURE_MODE = new Builder("future_enabled", C0716R.string.debug_menu_future_mode).setDefaultValue(BuildHelper.isDevelopmentBuild()).setOnChangeAction(DebugSettings$$Lambda$1.lambdaFactory$()).build();
    public static final BooleanDebugSetting GERMANY_SIMLATION = new Builder("simulate_in_germany", C0716R.string.debug_menu_germany_title).build();
    public static final BooleanDebugSetting GUEST_REVIEW_RATINGS = new Builder("enable_guest_review_ratings", C0716R.string.enable_guest_review_ratings).build();
    public static final BooleanDebugSetting HOST_PRICE_RULES = new Builder("enable_host_price_rules", C0716R.string.enable_host_price_rules).build();
    public static final BooleanDebugSetting IDENTITY_FLOW = new Builder("enable_identity_flow", C0716R.string.debug_menu_enable_identity_flow).build();
    public static final BooleanDebugSetting INFINITE_LOCAL_PUSH = new Builder("enable_infinite_local_push_notification", C0716R.string.debug_menu_enable_infinite_local_push).build();
    private static final String KEY_ENABLE_DLS_LIST_YOUR_SPACE = "enable_dls_list_your_space";
    public static final BooleanDebugSetting LAUNCH_COHOSTING_LISTING_PICKER_FROM_THREADS = new Builder("enable_co_hosting", C0716R.string.enable_co_hosting_listing_picker_from_threads).build();
    public static final BooleanDebugSetting LOCATION_AMENITIES_ML = new Builder("enable_listing_attributes_ml", C0716R.string.debug_menu_enable_location_amenities_ml).build();
    public static final BooleanDebugSetting NAVIGATION_LOGGING_VIEW = new Builder("display_all_navigation_logging", C0716R.string.debug_display_all_navigation_logs_setting).build();
    public static final BooleanDebugSetting NESTED_LISTINGS = new Builder("enable_host_nested_listings", C0716R.string.enable_host_nested_listings).build();
    public static final BooleanDebugSetting P4_REDESIGN = new Builder("enable_p4_redesign", C0716R.string.debug_p4_redesign).build();
    public static final BooleanDebugSetting REACT_NATIVE_GUIDEBOOKS = new Builder("enable_react_native_guidebooks", C0716R.string.debug_menu_enable_rn_guidebooks).build();
    public static final BooleanDebugSetting SELECT_PDP = new Builder("enable_select_pdp", C0716R.string.enable_select_pdp).build();
    public static final BooleanDebugSetting SIMULATE_NO_GOOGLE_PLAY_SERVICES = new Builder("simulate_no_play_services", C0716R.string.debug_menu_no_play_services_title).build();
    public static final BooleanDebugSetting STORY_COMPOSER_ON_FEED = new Builder("enable_story_pill_on_feed", C0716R.string.debug_show_story_composer_on_feed).build();
    public static final BooleanDebugSetting STORY_TAB = new Builder("enable_story_tab", C0716R.string.debug_show_story_feed).build();
    public static final BooleanDebugSetting SWIPE_TO_ARCHIVE = new Builder("enable_swipe_to_toggle_message_archive_state", C0716R.string.enable_swipe_to_toggle_message_archive_state).build();
    public static final BooleanDebugSetting UNFINISHED_LISTING_PROFILE = new Builder("enable_unfinished_listing_profile", C0716R.string.debug_menu_enable_unfinished_listing_profile).build();
    public static final BooleanDebugSetting USE_SATORI = new Builder("use_satori_autocomplete", C0716R.string.debug_menu_use_satori_autocomplete_title).build();
    public static final List<BooleanDebugSetting> debugSettingsList = new ArrayList();
    private final Bus bus;
    /* access modifiers changed from: private */
    public final SharedPreferences debugPreferences;
    private final SharedPreferences legacyTrebuchetPrefs;
    private final SharedPreferences trebuchetPrefs;

    public static class BooleanDebugSetting {
        private final boolean canEnableIfInternalSettingsEnabled;
        private final boolean defaultValue;
        private final SettingsAction onChangeAction;
        private final String settingKey;
        public final int titleRes;

        public static class Builder {
            /* access modifiers changed from: private */
            public boolean canEnableIfInternalSettingsEnabled;
            /* access modifiers changed from: private */
            public boolean defaultValue;
            /* access modifiers changed from: private */
            public SettingsAction onChangeAction;
            /* access modifiers changed from: private */
            public final String settingKey;
            /* access modifiers changed from: private */
            public final int titleRes;

            public Builder(String settingKey2, int titleRes2) {
                this.settingKey = settingKey2;
                this.titleRes = titleRes2;
            }

            public Builder setDefaultValue(boolean defaultValue2) {
                this.defaultValue = defaultValue2;
                return this;
            }

            public Builder setOnChangeAction(SettingsAction onChangeAction2) {
                this.onChangeAction = onChangeAction2;
                return this;
            }

            public Builder setCanEnableIfInternalSettingsEnabled() {
                this.canEnableIfInternalSettingsEnabled = true;
                return this;
            }

            public BooleanDebugSetting build() {
                BooleanDebugSetting setting = new BooleanDebugSetting(this);
                DebugSettings.debugSettingsList.add(setting);
                return setting;
            }
        }

        private BooleanDebugSetting(Builder builder) {
            this.settingKey = builder.settingKey;
            this.defaultValue = builder.defaultValue;
            this.titleRes = builder.titleRes;
            this.onChangeAction = builder.onChangeAction;
            this.canEnableIfInternalSettingsEnabled = builder.canEnableIfInternalSettingsEnabled;
        }

        public void setEnabled(boolean value) {
            getDebugPreferences().edit().putBoolean(this.settingKey, value).apply();
            if (this.onChangeAction != null) {
                this.onChangeAction.call();
            }
        }

        public boolean isEnabled() {
            boolean canEnable;
            if (BuildHelper.isDebugFeaturesEnabled() || (this.canEnableIfInternalSettingsEnabled && BuildHelper.isInternalSettingsEnabled())) {
                canEnable = true;
            } else {
                canEnable = false;
            }
            if (!canEnable || !getDebugPreferences().getBoolean(this.settingKey, this.defaultValue)) {
                return false;
            }
            return true;
        }

        private static SharedPreferences getDebugPreferences() {
            return CoreApplication.instance().component().debugSettings().debugPreferences;
        }
    }

    public enum DebugVerification {
        PROFILE_PICTURE,
        EMAIL,
        PHONE,
        OFFLINE_ID,
        ONLINE_ID
    }

    public interface SettingsAction {
        void call();
    }

    public DebugSettings(Context context, Bus bus2) {
        this.bus = bus2;
        this.debugPreferences = context.getSharedPreferences("debug_settings", 0);
        this.legacyTrebuchetPrefs = Trebuchet.getLegacyTrebuchetPrefs(context);
        this.trebuchetPrefs = Trebuchet.getTrebuchetPrefs(context);
    }

    public Strap getAllTrebuchetFlags() {
        Strap strap = new Strap();
        for (Entry<String, ?> entry : this.legacyTrebuchetPrefs.getAll().entrySet()) {
            if (((String) entry.getKey()).startsWith(Trebuchet.TREBUCHET_PREFIX)) {
                strap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        for (Entry<String, ?> entry2 : this.trebuchetPrefs.getAll().entrySet()) {
            strap.put(entry2.getKey(), String.valueOf(entry2.getValue()));
        }
        return strap;
    }

    private boolean isV2Trebuchet(String key) {
        return !key.startsWith(Trebuchet.TREBUCHET_PREFIX);
    }

    public String getTrebuchetFlag(String key) {
        if (isV2Trebuchet(key)) {
            return String.valueOf(this.trebuchetPrefs.getBoolean(key, false));
        }
        return this.legacyTrebuchetPrefs.getString(key, "");
    }

    public void setTrebuchetFlag(String key, String value) {
        if (isV2Trebuchet(key)) {
            this.trebuchetPrefs.edit().putBoolean(key, Boolean.valueOf(value).booleanValue()).apply();
        } else {
            this.legacyTrebuchetPrefs.edit().putString(key, value).apply();
        }
    }

    public boolean isUserVerifiedWithVerification(DebugVerification verification) {
        return this.debugPreferences.getBoolean(verification.toString(), false);
    }

    public void setUserVerifiedWithVerification(DebugVerification verification, boolean verified) {
        save(verification.toString(), verified);
    }

    private void save(String key, boolean value) {
        this.debugPreferences.edit().putBoolean(key, value).apply();
    }
}
