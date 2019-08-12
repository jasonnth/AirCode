package com.paypal.android.sdk.onetouch.core.config;

import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OtcConfiguration {
    private final ArrayList<BillingAgreementRecipe> mBillingAgreementRecipesInDecreasingPriorityOrder = new ArrayList<>();
    private final ArrayList<CheckoutRecipe> mCheckoutRecipesInDecreasingPriorityOrder = new ArrayList<>();
    private String mFileTimestamp;
    private final ArrayList<OAuth2Recipe> mOauth2RecipesInDecreasingPriorityOrder = new ArrayList<>();

    public OtcConfiguration withOauth2Recipe(OAuth2Recipe recipe) {
        this.mOauth2RecipesInDecreasingPriorityOrder.add(recipe);
        return this;
    }

    public OtcConfiguration fileTimestamp(String fileTimestamp) {
        this.mFileTimestamp = fileTimestamp;
        return this;
    }

    public OAuth2Recipe getBrowserOauth2Config(Set<String> scopes) {
        Iterator it = this.mOauth2RecipesInDecreasingPriorityOrder.iterator();
        while (it.hasNext()) {
            OAuth2Recipe recipe = (OAuth2Recipe) it.next();
            if (recipe.getTarget() == RequestTarget.browser && recipe.isValidForScopes(scopes)) {
                return recipe;
            }
        }
        return null;
    }

    public CheckoutRecipe getBrowserCheckoutConfig() {
        Iterator it = this.mCheckoutRecipesInDecreasingPriorityOrder.iterator();
        while (it.hasNext()) {
            CheckoutRecipe recipe = (CheckoutRecipe) it.next();
            if (recipe.getTarget() == RequestTarget.browser) {
                return recipe;
            }
        }
        return null;
    }

    public List<OAuth2Recipe> getOauth2Recipes() {
        return new ArrayList(this.mOauth2RecipesInDecreasingPriorityOrder);
    }

    public void withCheckoutRecipe(CheckoutRecipe recipe) {
        this.mCheckoutRecipesInDecreasingPriorityOrder.add(recipe);
    }

    public List<CheckoutRecipe> getCheckoutRecipes() {
        return new ArrayList(this.mCheckoutRecipesInDecreasingPriorityOrder);
    }

    public void withBillingAgreementRecipe(BillingAgreementRecipe recipe) {
        this.mBillingAgreementRecipesInDecreasingPriorityOrder.add(recipe);
    }

    public List<BillingAgreementRecipe> getBillingAgreementRecipes() {
        return new ArrayList(this.mBillingAgreementRecipesInDecreasingPriorityOrder);
    }
}
