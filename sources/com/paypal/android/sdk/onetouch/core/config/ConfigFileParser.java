package com.paypal.android.sdk.onetouch.core.config;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ConfigFileParser {
    ConfigFileParser() {
    }

    /* access modifiers changed from: 0000 */
    public OtcConfiguration getParsedConfig(JSONObject rootObject) throws JSONException {
        OtcConfiguration otcConfiguration = new OtcConfiguration();
        otcConfiguration.fileTimestamp(rootObject.getString("file_timestamp"));
        JSONObject oneDotZeroConfig = rootObject.getJSONObject("1.0");
        JSONArray oauth2RecipesInDecreasingPriorityOrder = oneDotZeroConfig.getJSONArray("oauth2_recipes_in_decreasing_priority_order");
        for (int i = 0; i < oauth2RecipesInDecreasingPriorityOrder.length(); i++) {
            JSONObject oauth2Recipe = oauth2RecipesInDecreasingPriorityOrder.getJSONObject(i);
            if (oauth2Recipe != null) {
                otcConfiguration.withOauth2Recipe(getOAuth2Recipe(oauth2Recipe));
            }
        }
        JSONArray checkoutRecipesInDecreasingPriorityOrder = oneDotZeroConfig.getJSONArray("checkout_recipes_in_decreasing_priority_order");
        for (int i2 = 0; i2 < checkoutRecipesInDecreasingPriorityOrder.length(); i2++) {
            JSONObject checkoutRecipe = checkoutRecipesInDecreasingPriorityOrder.getJSONObject(i2);
            if (checkoutRecipe != null) {
                otcConfiguration.withCheckoutRecipe(getCheckoutRecipe(checkoutRecipe));
            }
        }
        JSONArray billingAgreementRecipesInDecreasingPriorityOrder = oneDotZeroConfig.getJSONArray("billing_agreement_recipes_in_decreasing_priority_order");
        for (int i3 = 0; i3 < billingAgreementRecipesInDecreasingPriorityOrder.length(); i3++) {
            JSONObject billingAgreementRecipe = billingAgreementRecipesInDecreasingPriorityOrder.getJSONObject(i3);
            if (billingAgreementRecipe != null) {
                otcConfiguration.withBillingAgreementRecipe(getBillingAgreementRecipe(billingAgreementRecipe));
            }
        }
        return otcConfiguration;
    }

    private CheckoutRecipe getCheckoutRecipe(JSONObject checkoutRecipe) throws JSONException {
        CheckoutRecipe recipe = new CheckoutRecipe();
        populateCommonData(recipe, checkoutRecipe);
        return recipe;
    }

    private BillingAgreementRecipe getBillingAgreementRecipe(JSONObject billingAgreementRecipe) throws JSONException {
        BillingAgreementRecipe recipe = new BillingAgreementRecipe();
        populateCommonData(recipe, billingAgreementRecipe);
        return recipe;
    }

    private void populateCommonData(Recipe<?> recipe, JSONObject jsonRecipe) throws JSONException {
        recipe.target(RequestTarget.valueOf(jsonRecipe.getString(BaseAnalytics.TARGET))).protocol(jsonRecipe.getString("protocol"));
        if (jsonRecipe.has("intent_action")) {
            recipe.targetIntentAction(jsonRecipe.getString("intent_action"));
        }
        JSONArray packagesArray = jsonRecipe.getJSONArray("packages");
        for (int j = 0; j < packagesArray.length(); j++) {
            recipe.targetPackage(packagesArray.getString(j));
        }
        if (jsonRecipe.has("supported_locales")) {
            JSONArray supportedLocalesArray = jsonRecipe.getJSONArray("supported_locales");
            for (int j2 = 0; j2 < supportedLocalesArray.length(); j2++) {
                recipe.supportedLocale(supportedLocalesArray.getString(j2));
            }
        }
    }

    private OAuth2Recipe getOAuth2Recipe(JSONObject jsonOauth2Recipe) throws JSONException {
        OAuth2Recipe recipe = new OAuth2Recipe();
        populateCommonData(recipe, jsonOauth2Recipe);
        JSONArray scopeArray = jsonOauth2Recipe.getJSONArray("scope");
        for (int j = 0; j < scopeArray.length(); j++) {
            String scopeValue = scopeArray.getString(j);
            if ("*".equals(scopeValue)) {
                recipe.validForAllScopes();
            } else {
                recipe.validForScope(scopeValue);
            }
        }
        if (jsonOauth2Recipe.has("endpoints")) {
            JSONObject endpoints = jsonOauth2Recipe.getJSONObject("endpoints");
            if (endpoints.has("live")) {
                addEnvironment(recipe, "live", endpoints.getJSONObject("live"));
            }
            if (endpoints.has("develop")) {
                addEnvironment(recipe, "develop", endpoints.getJSONObject("develop"));
            }
            if (endpoints.has("mock")) {
                addEnvironment(recipe, "mock", endpoints.getJSONObject("mock"));
            }
        }
        return recipe;
    }

    private void addEnvironment(OAuth2Recipe recipe, String name, JSONObject jsonEnvironment) throws JSONException {
        recipe.withEndpoint(name, new ConfigEndpoint(name, jsonEnvironment.getString("url"), jsonEnvironment.getString("certificate")));
    }
}
