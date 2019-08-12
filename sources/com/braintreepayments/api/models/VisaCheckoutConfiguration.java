package com.braintreepayments.api.models;

import com.braintreepayments.api.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

public class VisaCheckoutConfiguration {
    private String mApiKey;
    private List<String> mCardBrands;
    private String mExternalClientId;
    private boolean mIsEnabled;

    private static boolean isVisaCheckoutSDKAvailable() {
        try {
            Class.forName("com.visa.checkout.VisaCheckoutSdk");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    static VisaCheckoutConfiguration fromJson(JSONObject json) {
        VisaCheckoutConfiguration visaCheckoutConfiguration = new VisaCheckoutConfiguration();
        if (json == null) {
            json = new JSONObject();
        }
        visaCheckoutConfiguration.mApiKey = Json.optString(json, "apikey", "");
        visaCheckoutConfiguration.mIsEnabled = isVisaCheckoutSDKAvailable() && visaCheckoutConfiguration.mApiKey != "";
        visaCheckoutConfiguration.mExternalClientId = Json.optString(json, "externalClientId", "");
        visaCheckoutConfiguration.mCardBrands = supportedCardTypesToAcceptedCardBrands(CardConfiguration.fromJson(json).getSupportedCardTypes());
        return visaCheckoutConfiguration;
    }

    private static List<String> supportedCardTypesToAcceptedCardBrands(Set<String> supportedCardTypes) {
        List<String> acceptedCardBrands = new ArrayList<>();
        for (String supportedCardType : supportedCardTypes) {
            String lowerCase = supportedCardType.toLowerCase();
            char c = 65535;
            switch (lowerCase.hashCode()) {
                case -2038717326:
                    if (lowerCase.equals("mastercard")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1120637072:
                    if (lowerCase.equals("american express")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3619905:
                    if (lowerCase.equals("visa")) {
                        c = 0;
                        break;
                    }
                    break;
                case 273184745:
                    if (lowerCase.equals("discover")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    acceptedCardBrands.add("VISA");
                    break;
                case 1:
                    acceptedCardBrands.add("MASTERCARD");
                    break;
                case 2:
                    acceptedCardBrands.add("DISCOVER");
                    break;
                case 3:
                    acceptedCardBrands.add("AMEX");
                    break;
            }
        }
        return acceptedCardBrands;
    }
}
