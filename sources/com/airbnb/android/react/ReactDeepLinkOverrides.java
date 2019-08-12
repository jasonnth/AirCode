package com.airbnb.android.react;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;

public class ReactDeepLinkOverrides {

    private enum OverrideScene {
        Itinerary,
        ItineraryTimelineScreen,
        Unknown;

        static OverrideScene getScene(String sceneName) {
            char c = 65535;
            switch (sceneName.hashCode()) {
                case -848039701:
                    if (sceneName.equals("Itinerary")) {
                        c = 0;
                        break;
                    }
                    break;
                case 718187700:
                    if (sceneName.equals("Itinerary/ItineraryTimelineScreen")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return Itinerary;
                case 1:
                    return ItineraryTimelineScreen;
                default:
                    return Unknown;
            }
        }
    }

    private ReactDeepLinkOverrides() {
    }

    public static boolean hasOverride(String sceneName) {
        return OverrideScene.getScene(sceneName) != OverrideScene.Unknown;
    }

    public static Intent getIntent(Context context, String sceneName, Bundle props) {
        switch (OverrideScene.getScene(sceneName)) {
            case Itinerary:
                return HomeActivityIntents.intentForTrips(context, props);
            case ItineraryTimelineScreen:
                return HomeActivityIntents.intentForTrips(context);
            default:
                throw new IllegalStateException("Tried to get intent override, but not properly set up");
        }
    }
}
