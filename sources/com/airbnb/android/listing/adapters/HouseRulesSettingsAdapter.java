package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TriStateSwitchRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.TriStateSwitch.TriStateSwitchStyle;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.Collection;
import java.util.List;

public class HouseRulesSettingsAdapter extends AirEpoxyAdapter implements InputAdapter {
    public static final int LISTING_EXPECTATIONS_SUBTITLE_MAX_LINES = 6;
    public static final int LISTING_EXPECTATIONS_TITLE_MAX_LINES = 2;
    private final StandardRowEpoxyModel_ additionalRules;
    private final TriStateSwitchRowEpoxyModel_ childrenRule = buildHouseRuleModel(GuestControlType.Children, C7213R.string.ml_house_rule_children);
    @State
    GuestControls guestControls;
    private final TriStateSwitchRowEpoxyModel_ infantsRule = buildHouseRuleModel(GuestControlType.Infants, C7213R.string.ml_house_rule_infants);
    private final StandardRowEpoxyModel_ listingExpectations;
    private final TriStateSwitchRowEpoxyModel_ partiesRule = buildHouseRuleModel(GuestControlType.Events, C7213R.string.ml_house_rule_parties);
    private final TriStateSwitchRowEpoxyModel_ petsRule = buildHouseRuleModel(GuestControlType.Pets, C7213R.string.ml_house_rule_pets);
    private final TriStateSwitchRowEpoxyModel_ smokingRule = buildHouseRuleModel(GuestControlType.Smoking, C7213R.string.ml_house_rule_smoking);

    public interface Listener {
        void onAdditionalHouseRulesClicked();

        void onGuestExpectationsClicked();

        void onLearnMoreClicked(View view);
    }

    public HouseRulesSettingsAdapter(Context context, Listing listing, GuestControls guestControls2, Listener listener, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        this.guestControls = GuestControls.copy(guestControls2);
        onRestoreInstanceState(savedInstanceState);
        DocumentMarqueeEpoxyModel_ captionText = new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.house_rules).captionText((CharSequence) TextUtil.fromHtmlSafe(context.getString(C7213R.string.house_rules_description)));
        listener.getClass();
        addModel(captionText.clickListener(HouseRulesSettingsAdapter$$Lambda$1.lambdaFactory$(listener)));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.childrenRule, this.infantsRule, this.petsRule, this.smokingRule, this.partiesRule});
        this.additionalRules = new StandardRowEpoxyModel_().title(C7213R.string.ml_additional_rules).clickListener(HouseRulesSettingsAdapter$$Lambda$2.lambdaFactory$(listener));
        addModel(this.additionalRules);
        this.listingExpectations = new StandardRowEpoxyModel_().title(C7213R.string.ml_listing_expectations).titleMaxLine(2).subTitleMaxLine(6).clickListener(HouseRulesSettingsAdapter$$Lambda$3.lambdaFactory$(listener));
        if (FeatureToggles.showListingExpectationsSettings()) {
            addModel(this.listingExpectations);
        }
        updateAdditionalRulesAndExpectationsRows(listing);
    }

    public GuestControls getGuestControls() {
        return this.guestControls;
    }

    public boolean hasChanged(GuestControls guestControls2) {
        return !this.guestControls.equals(guestControls2);
    }

    private TriStateSwitchRowEpoxyModel_ buildHouseRuleModel(GuestControlType guestControlType, int titleRes) {
        return new TriStateSwitchRowEpoxyModel_().titleRes(titleRes).style(TriStateSwitchStyle.Outlined).toggleState(getToggleState(guestControlType)).checkedChangeListener(HouseRulesSettingsAdapter$$Lambda$4.lambdaFactory$(this, guestControlType));
    }

    public void setInputEnabled(boolean enabled) {
        this.childrenRule.enabled(enabled);
        this.infantsRule.enabled(enabled);
        this.petsRule.enabled(enabled);
        this.smokingRule.enabled(enabled);
        this.partiesRule.enabled(enabled);
        this.additionalRules.enabled(enabled);
        notifyModelsChanged();
    }

    public void updateAdditionalRulesAndExpectationsRows(Listing listing) {
        updateAdditionalRulesRow(listing);
        if (FeatureToggles.showListingExpectationsSettings()) {
            updateListingExpectationsRow(listing);
        }
        notifyModelsChanged();
    }

    private void updateAdditionalRulesRow(Listing listing) {
        this.additionalRules.textRes(ListingTextUtils.getActionTextForTextSetting(listing.getHouseRules()));
        if (TextUtils.isEmpty(listing.getHouseRules())) {
            this.additionalRules.subtitleRes(C7213R.string.house_rules_additional_rules_placeholder);
        } else {
            this.additionalRules.subtitle((CharSequence) listing.getHouseRules()).subTitleMaxLine(2);
        }
    }

    private void updateListingExpectationsRow(Listing listing) {
        List<String> checkedExpectationTitles = FluentIterable.from((Iterable<E>) listing.getListingExpectations()).filter(HouseRulesSettingsAdapter$$Lambda$5.lambdaFactory$()).transform(HouseRulesSettingsAdapter$$Lambda$6.lambdaFactory$()).toList();
        boolean hasExpectations = !ListUtils.isEmpty((Collection<?>) checkedExpectationTitles);
        this.listingExpectations.textRes(hasExpectations ? C7213R.string.edit : C7213R.string.add);
        if (hasExpectations) {
            this.listingExpectations.subtitle((CharSequence) TextUtils.join("\n", checkedExpectationTitles));
        } else {
            this.listingExpectations.subtitleRes(C7213R.string.ml_listing_expectations_description);
        }
    }

    private ToggleState getToggleState(GuestControlType type) {
        Boolean isAllowed = this.guestControls.isGuestControlTypeAllowed(type);
        if (isAllowed == Boolean.TRUE) {
            return ToggleState.ON;
        }
        if (isAllowed == Boolean.FALSE) {
            return ToggleState.OFF;
        }
        return ToggleState.NEITHER;
    }

    /* access modifiers changed from: private */
    public void onCheckedChanged(GuestControlType type, ToggleState toggleState) {
        this.guestControls.setGuestControlTypeAllowed(type, Boolean.valueOf(toggleState == ToggleState.ON));
    }
}
