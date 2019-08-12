package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.PricingRule;
import com.airbnb.android.core.models.PricingRule.PriceChangeType;
import com.airbnb.android.core.models.PricingRule.RuleType;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LengthOfStayDiscountsEpoxyController extends AirEpoxyController {
    public static final int LENGTH_OF_STAY_MONTH = 28;
    public static final int LENGTH_OF_STAY_WEEK = 7;
    private final List<Integer> allowedLengthOfStaySettings = Arrays.asList(new Integer[]{Integer.valueOf(7), Integer.valueOf(28)});
    private final int averageMonthlyPrice;
    private final int averageWeeklyPrice;
    private final Context context;
    private final String currencyCode;
    @State
    boolean inputEnabled;
    private final Listener listener;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    @State
    HashMap<Integer, LengthOfStayRuleState> modelStates = new HashMap<>();

    public interface Listener {
        void onTipClicked(int i);

        void showLengthOfStayDiscountLearnMore();
    }

    public LengthOfStayDiscountsEpoxyController(Context context2, Listener listener2, String currencyCode2, int averageWeeklyPrice2, int averageMonthlyPrice2, Bundle savedInstanceState) {
        this.context = context2;
        this.listener = listener2;
        this.currencyCode = currencyCode2;
        this.averageWeeklyPrice = averageWeeklyPrice2;
        this.averageMonthlyPrice = averageMonthlyPrice2;
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    public void initLengthOfStayRules(List<PricingRule> rules, Integer weeklyTip, Integer monthlyTip) {
        setInitialState(7, 0, weeklyTip);
        setInitialState(28, 0, monthlyTip);
        for (PricingRule rule : rules) {
            switch (rule.getThresholdOne().intValue()) {
                case 7:
                    setInitialState(7, rule.getPriceChange().intValue(), weeklyTip);
                    break;
                case 28:
                    setInitialState(28, rule.getPriceChange().intValue(), monthlyTip);
                    break;
                default:
                    setInitialState(rule.getThresholdOne().intValue(), rule.getPriceChange().intValue(), null);
                    break;
            }
        }
        this.inputEnabled = true;
        requestModelBuild();
    }

    private void setInitialState(int lengthOfStayNights, int value, Integer tip) {
        this.modelStates.put(Integer.valueOf(lengthOfStayNights), new LengthOfStayRuleState(lengthOfStayNights, value, tip, true));
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C7213R.string.manage_listing_length_of_stay_discounts_title).captionRes(C7213R.string.manage_listing_length_of_stay_discounts_intro).linkRes(C7213R.string.learn_more_info_text).linkClickListener(LengthOfStayDiscountsEpoxyController$$Lambda$1.lambdaFactory$(this));
        if (this.modelStates.size() > 0) {
            for (Integer lengthOfStayNights : this.allowedLengthOfStaySettings) {
                LengthOfStayRuleState ruleState = (LengthOfStayRuleState) this.modelStates.get(lengthOfStayNights);
                if (ruleState != null && ruleState.show()) {
                    buildModelForState(ruleState).addTo(this);
                }
            }
            return;
        }
        new EpoxyControllerLoadingModel_().m4584id((CharSequence) "loading").addTo(this);
    }

    public List<PricingRule> getRules() {
        List<PricingRule> rules = new ArrayList<>();
        for (LengthOfStayRuleState ruleState : this.modelStates.values()) {
            if (ruleState.show()) {
                rules.add(new PricingRule(RuleType.LengthOfStay, ruleState.getLengthOfStayNights(), ruleState.getCurrentValue().intValue(), PriceChangeType.Percent));
            }
        }
        return rules;
    }

    private InlineFormattedIntegerInputRowEpoxyModel_ buildModelForState(LengthOfStayRuleState ruleState) {
        int los = ruleState.getLengthOfStayNights();
        InlineFormattedIntegerInputRowEpoxyModel_ epoxyModel = InlineFormattedIntegerInputRowEpoxyModel_.forIntegerPercentage().m4873id((CharSequence) "discount row", (long) los).titleRes(titleResourceFor(los)).amountChangedListener(LengthOfStayDiscountsEpoxyController$$Lambda$2.lambdaFactory$(this, los)).tipClickListener(LengthOfStayDiscountsEpoxyController$$Lambda$3.lambdaFactory$(this, los)).enabled(this.inputEnabled).showError(ruleState.showError()).updateModelData(false);
        if (ruleState.getCurrentValue() != null) {
            epoxyModel.subTitle(subtitleFor(los, ruleState.getCurrentValue().intValue())).inputAmount(ruleState.getCurrentValue());
        }
        if (ruleState.getTip() != null && ruleState.getTip().intValue() > 0 && ruleState.getTip().intValue() < 100) {
            epoxyModel.tip(this.context.getString(ListingTextUtils.getTipRes(), new Object[]{PercentageUtils.formatDiscountPercentage(ruleState.getTip().intValue())})).tipAmount(ruleState.getTip());
        }
        return epoxyModel;
    }

    public void setInputEnabled(boolean enabled, boolean rebuild) {
        this.inputEnabled = enabled;
        if (rebuild) {
            requestModelBuild();
        }
    }

    public void markErrors(boolean showError, boolean rebuild) {
        boolean hasChanged = false;
        for (LengthOfStayRuleState ruleState : this.modelStates.values()) {
            hasChanged = ruleState.setShowError(showError) || hasChanged;
        }
        if (rebuild && hasChanged) {
            requestModelBuild();
        }
    }

    public boolean showWeeklyPriceHigherError() {
        Integer currentWeeklyValue = getCurrentValueFor(7);
        Integer currentMonthlyValue = getCurrentValueFor(28);
        return currentWeeklyValue == null || currentMonthlyValue == null || currentMonthlyValue.intValue() < currentWeeklyValue.intValue();
    }

    private Integer getCurrentValueFor(int lengthOfStayNights) {
        LengthOfStayRuleState ruleState = (LengthOfStayRuleState) this.modelStates.get(Integer.valueOf(lengthOfStayNights));
        if (ruleState != null) {
            return ruleState.getCurrentValue();
        }
        return null;
    }

    public boolean hasValueChanged() {
        for (LengthOfStayRuleState ruleState : this.modelStates.values()) {
            if (ruleState.hasChanged()) {
                return true;
            }
        }
        return false;
    }

    private String modelIdFor(int lengthOfStayNights) {
        return "discountRow" + lengthOfStayNights;
    }

    private int titleResourceFor(int lengthOfStayNights) {
        switch (lengthOfStayNights) {
            case 7:
                return C7213R.string.manage_listing_long_term_discounts_weekly_discount_title;
            case 28:
                return C7213R.string.manage_listing_long_term_discounts_monthly_discount_title;
            default:
                return 0;
        }
    }

    private String subtitleFor(int lengthOfStayNights, int discountPercentage) {
        switch (lengthOfStayNights) {
            case 7:
                return getPricingSubtitle(C7213R.string.manage_listing_long_term_discounts_weekly_discount_info, Integer.valueOf(discountPercentage), this.averageWeeklyPrice);
            case 28:
                return getPricingSubtitle(C7213R.string.manage_listing_long_term_discounts_monthly_discount_info, Integer.valueOf(discountPercentage), this.averageMonthlyPrice);
            default:
                return "";
        }
    }

    private String getPricingSubtitle(int strRes, Integer discountPercentage, int price) {
        if (price <= 0 || discountPercentage == null || discountPercentage.intValue() < 0 || discountPercentage.intValue() >= 100) {
            return null;
        }
        int usedPercentage = SanitizeUtils.zeroIfNull(discountPercentage);
        String percentString = PercentageUtils.formatDiscountPercentage(usedPercentage);
        String priceString = getDiscountedPriceString(usedPercentage, price);
        return this.context.getString(strRes, new Object[]{percentString, priceString});
    }

    /* access modifiers changed from: private */
    public void onAmountChanged(int lengthOfStayNights, Integer value) {
        LengthOfStayRuleState ruleState = (LengthOfStayRuleState) this.modelStates.get(Integer.valueOf(lengthOfStayNights));
        if (ruleState != null) {
            ruleState.setCurrentValue(value);
            markErrors(false, false);
            requestModelBuild();
            return;
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException(MessageFormat.format("length-of-stay rule should never be null; lengthOfStayNights = {0}", new Object[]{Integer.valueOf(lengthOfStayNights)})));
    }

    /* access modifiers changed from: private */
    public void onTipClicked(int lengthOfStayNights) {
        this.listener.onTipClicked(lengthOfStayNights);
    }

    private String getDiscountedPriceString(int discountPercentage, int averagePrice) {
        return CurrencyUtils.formatCurrencyAmount((double) Math.round(((double) averagePrice) * PercentageUtils.discountIntToDiscountedDouble(discountPercentage)), this.currencyCode);
    }
}
