package com.airbnb.android.explore.controllers;

import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.utils.geocoder.models.Autocompletable;
import com.airbnb.erf.Experiments;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet.Builder;
import java.util.Map;
import java.util.Set;

public class SearchSuggestionUtils {
    private static final Set<String> SATORI_USER_MARKETS = new Builder().add((Object) "Dallas").add((Object) "Atlanta").add((Object) "D.C.").add((Object) "New York").add((Object) "Toronto").build();
    private static final Map<String, Boolean> TRIP_CITIES = new ImmutableMap.Builder().put("ChIJIQBpAG2ahYAR_6128GcTUEo", Boolean.valueOf(true)).put("ChIJE9on3F3HwoAR9AhGJW_fL-I", Boolean.valueOf(true)).put("ChIJ51cu8IcbXWARiRtXIothAS4", Boolean.valueOf(true)).put("ChIJdd4hrwug2EcRmSrV3Vo6llI", Boolean.valueOf(true)).put("ChIJrdbSgKZWKhMRAyrH7xd51ZM", Boolean.valueOf(false)).put("ChIJzWXFYYuifDUR64Pq5LTtioU", Boolean.valueOf(true)).put("ChIJD7fiBh9u5kcRYJSMaMOCCwQ", Boolean.valueOf(true)).put("ChIJEcHIDqKw2YgRZU-t3XHylv8", Boolean.valueOf(false)).put("ChIJ4QD2vUx3zYgRYA13Gn5NKU4", Boolean.valueOf(true)).put("ChIJ1-4miA9QzB0Rh6ooKPzhf2g", Boolean.valueOf(false)).put("ChIJdR3LEAHKJIgR0sS5NU6Gdlc", Boolean.valueOf(true)).put("ChIJp0lN2HIRLxgRTJKXslQCz_c", Boolean.valueOf(true)).put("ChIJ5TCOcRaYpBIRCmZHTz37sEQ", Boolean.valueOf(false)).put("ChIJu46S-ZZhLxMROG5lkwZ3D7k", Boolean.valueOf(false)).put("ChIJ13DMKmvoAGARbVkfgUj_maM", Boolean.valueOf(false)).put("ChIJP5iLHkCuEmsRwMwyFmh9AQU", Boolean.valueOf(true)).put("ChIJpTvG15DL1IkRd8S0KlBVNTI", Boolean.valueOf(false)).put("ChIJVTPokywQkFQRmtVEaUZlJRA", Boolean.valueOf(false)).put("ChIJMSKJ1lzmj4ARV0D2joR8Mjs", Boolean.valueOf(false)).put("ChIJJ3SpfQsLlVQRkYXR9ua5Nhw", Boolean.valueOf(false)).put("ChIJ82ENKDJgHTERIEjiXbIAAQE", Boolean.valueOf(false)).put("ChIJPZDrEzLsZIgRoNrpodC5P30", Boolean.valueOf(false)).put("ChIJyY4rtGcX2jERIKTarqz3AAQ", Boolean.valueOf(false)).put("ChIJrVP5ihlothIRp9EWPSaQFrc", Boolean.valueOf(false)).put("ChIJOwg_06VPwokRYv534QaPC8g", Boolean.valueOf(false)).put("ChIJL_P_CXMEDTkRw0ZdG-0GVvw", Boolean.valueOf(false)).put("ChIJX96o1_Ed1akRAKZ5hIbvAAU", Boolean.valueOf(false)).put("ChIJ0T2NLikpdTERKxE8d61aX_E", Boolean.valueOf(false)).put("ChIJMzz1sUBwsjURoWTDI5QSlQI", Boolean.valueOf(false)).put("ChIJn6KIIW72wokRLnDiCe-vCLQ", Boolean.valueOf(false)).build();

    public static boolean hasTripVerticals(Autocompletable prediction) {
        return prediction.getPlaceId() != null && TRIP_CITIES.containsKey(prediction.getPlaceId());
    }

    public static boolean hasPlaces(Autocompletable prediction) {
        return ((Boolean) TRIP_CITIES.get(prediction.getPlaceId())).booleanValue();
    }

    public static boolean useSatoriAutocomplete(String userMarket) {
        if (!Trebuchet.launch(TrebuchetKeys.SATORI_AUTOCOMPLETE_FORCE)) {
            CoreApplication.instance().component().debugSettings();
            if (!DebugSettings.USE_SATORI.isEnabled() && (!SATORI_USER_MARKETS.contains(userMarket) || !Experiments.useSatoriAutocomplete())) {
                return false;
            }
        }
        return true;
    }
}
