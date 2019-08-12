package com.airbnb.android.contentframework;

import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.Experiments;

public class StoriesExperimentUtil {
    public static boolean isStoryFeatureEnabled() {
        return shouldReplaceItineraryWithStoriesTab();
    }

    public static boolean shouldShowComposerPillOnStoryFeed() {
        boolean z = false;
        DebugSettings debugSettings = CoreApplication.instance().component().debugSettings();
        if (DebugSettings.STORY_COMPOSER_ON_FEED.isEnabled()) {
            return true;
        }
        if (Trebuchet.launch(TrebuchetKeys.FORCE_SHOW_STORY_COMPOSER, false) || Trebuchet.launch(TrebuchetKeys.ENABLE_STORY_COMPOSER, false)) {
            z = true;
        }
        return z;
    }

    public static boolean shouldMoveItineraryToProfile() {
        if (forceShowStories()) {
            return true;
        }
        if (!userInChineseLocale() || inHoldoutGroup()) {
            return false;
        }
        if (inStableGroup()) {
            return Trebuchet.launch(TrebuchetKeys.FORCE_STORIES_V0_TO_STABLE, false);
        }
        if (Experiments.removeTripsTab() || Experiments.enableStoryTab()) {
            return true;
        }
        return false;
    }

    public static boolean shouldReplaceItineraryWithStoriesTab() {
        if (forceShowStories()) {
            return true;
        }
        if (!userInChineseLocale() || inHoldoutGroup()) {
            return false;
        }
        if (inStableGroup()) {
            return Trebuchet.launch(TrebuchetKeys.FORCE_STORIES_V0_TO_STABLE, false);
        }
        return Experiments.enableStoryTab();
    }

    private static boolean userInChineseLocale() {
        return ChinaUtils.isUserUsingSimplifiedChinese();
    }

    private static boolean inHoldoutGroup() {
        return Experiments.isInStoriesHoldoutGroup();
    }

    private static boolean inStableGroup() {
        return Experiments.isInStoriesStableGroup();
    }

    private static boolean forceShowStories() {
        DebugSettings debugSettings = CoreApplication.instance().component().debugSettings();
        if (DebugSettings.STORY_TAB.isEnabled()) {
            return true;
        }
        return Trebuchet.launch(TrebuchetKeys.FORCE_SHOW_STORY_TAB, false);
    }
}
