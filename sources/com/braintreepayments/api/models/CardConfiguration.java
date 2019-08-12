package com.braintreepayments.api.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class CardConfiguration {
    private final Set<String> mSupportedCardTypes = new HashSet();

    public static CardConfiguration fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        CardConfiguration cardConfiguration = new CardConfiguration();
        JSONArray jsonArray = json.optJSONArray("supportedCardTypes");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                cardConfiguration.mSupportedCardTypes.add(jsonArray.optString(i, ""));
            }
        }
        return cardConfiguration;
    }

    public Set<String> getSupportedCardTypes() {
        return Collections.unmodifiableSet(this.mSupportedCardTypes);
    }
}
