package com.airbnb.android.core.adapters;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.HouseRulesAndExpectationsUtils;
import com.airbnb.android.core.utils.ListingUtils;
import com.airbnb.android.core.viewcomponents.models.KickerMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseRulesEpoxyController extends AirEpoxyController {
    private static final Integer[] HOMES_NOT_HOTELS_HARD_TREATMENT = {Integer.valueOf(C0716R.string.homes_not_hotels_harder_1), Integer.valueOf(C0716R.string.homes_not_hotels_harder_2), Integer.valueOf(C0716R.string.homes_not_hotels_harder_3)};
    private static final Integer[] HOMES_NOT_HOTELS_SOFT_TREATMENT = {Integer.valueOf(C0716R.string.homes_not_hotels_softer_1), Integer.valueOf(C0716R.string.homes_not_hotels_softer_2), Integer.valueOf(C0716R.string.homes_not_hotels_softer_3)};
    private static final int MAX_LINES_ROW_TITLES = 5;
    private static final int MAX_LINES_UNSTRUCTURED_HOUSE_RULES = 3;
    private final Context context;
    MicroSectionHeaderEpoxyModel_ expectationsHeaderModel;
    private final boolean forBooking;
    MicroSectionHeaderEpoxyModel_ homesNotHotelsHeaderModel;
    private final List<String> houseRules;
    SubsectionDividerEpoxyModel_ houseRulesDividerModel;
    TextRowEpoxyModel_ houseRulesModel;
    private final String kickerText;
    private final Listing listing;
    @State
    ArrayList<ListingExpectation> listingExpectations;
    KickerMarqueeEpoxyModel_ marqueeEpoxyModel;
    private final boolean showExpectations;
    private final boolean showHomesNotHotels;
    @State
    boolean showingTranslation;
    private final String subtitle;
    private final String title;
    MicroRowEpoxyModel_ translateLinkModel;
    @State
    String translatedHouseRules;
    @State
    ArrayList<ListingExpectation> translatedListingExpectations;
    private final Listener translationListener;

    public interface Listener {
        void fetchTranslation();

        void onTranslationToggle(boolean z);
    }

    public HouseRulesEpoxyController(Context context2, String kickerText2, Listing listing2, boolean forBooking2, boolean hasPastBookings, boolean isForLongTermStay, Listener translationListener2, Bundle savedInstanceState) {
        boolean z;
        this.context = (Context) Check.notNull(context2);
        this.translationListener = (Listener) Check.notNull(translationListener2);
        this.listing = (Listing) Check.notNull(listing2);
        this.forBooking = forBooking2;
        this.kickerText = kickerText2;
        onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            this.showingTranslation = false;
            this.translatedHouseRules = listing2.getLocalizedAdditionalHouseRules();
            this.listingExpectations = new ArrayList<>(HouseRulesAndExpectationsUtils.getCheckedExpectations(listing2.getListingExpectations()));
            this.translatedListingExpectations = new ArrayList<>(HouseRulesAndExpectationsUtils.getCheckedExpectations(listing2.getLocalizedListingExpectations()));
        }
        this.title = HouseRulesAndExpectationsUtils.getHouseRulesAndExpectationsTitle(context2, this.listingExpectations, forBooking2, listing2.getPrimaryHost());
        this.subtitle = HouseRulesAndExpectationsUtils.getHouseRulesAndExpectationsDescription(context2, this.listingExpectations, forBooking2, listing2.getPrimaryHost());
        this.showExpectations = HouseRulesAndExpectationsUtils.showExpectations(this.listingExpectations);
        if (hasPastBookings || !forBooking2 || !FeatureToggles.showHomesNotHotels()) {
            z = false;
        } else {
            z = true;
        }
        this.showHomesNotHotels = z;
        this.houseRules = isForLongTermStay ? listing2.getGuestControls().getLongTermHouseRules() : listing2.getGuestControls().getStructuredHouseRules();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeEpoxyModel.titleText(this.title).subTitleText(this.subtitle).kickerText(this.kickerText).isSubtitleBold(false);
        setupTranslateLinkRow();
        setupHouseRulesRows();
        setupAdditionalHouseRulesRow();
        setupListingExpectations();
        setupHomesNotHotelContent();
    }

    private void setupTranslateLinkRow() {
        boolean hasLocalizedExpectations;
        boolean hasLocalizedHouseRules;
        boolean needsToTranslateHouseRules;
        boolean isTranslatable;
        String text;
        if (!this.showExpectations || !HouseRulesAndExpectationsUtils.hasAnyAddedDetails(this.translatedListingExpectations)) {
            hasLocalizedExpectations = false;
        } else {
            hasLocalizedExpectations = true;
        }
        if (TextUtils.isEmpty(this.listing.getLocalizedAdditionalHouseRules()) || this.listing.getLocalizedAdditionalHouseRules().equals(this.listing.getAdditionalHouseRules())) {
            hasLocalizedHouseRules = false;
        } else {
            hasLocalizedHouseRules = true;
        }
        if (hasLocalizedHouseRules || ListingUtils.needsTranslation(this.listing)) {
            needsToTranslateHouseRules = true;
        } else {
            needsToTranslateHouseRules = false;
        }
        if (hasLocalizedExpectations || needsToTranslateHouseRules) {
            isTranslatable = true;
        } else {
            isTranslatable = false;
        }
        if (this.showingTranslation) {
            text = this.context.getString(C0716R.string.translated_via_google_after_translation);
        } else {
            text = this.context.getString(C0716R.string.translated_via_google_before_translation, new Object[]{LocaleUtil.getDeviceDisplayLanguage(this.context)});
        }
        this.translateLinkModel.text(TextUtil.fromHtmlSafe(text)).clickListener(HouseRulesEpoxyController$$Lambda$1.lambdaFactory$(this)).addIf(isTranslatable, (EpoxyController) this);
    }

    private void setupHouseRulesRows() {
        for (String rule : this.houseRules) {
            add(buildRowModel(rule));
        }
    }

    private void setupAdditionalHouseRulesRow() {
        boolean truncateHouseRules;
        int i;
        boolean z = true;
        if (this.showExpectations || this.showHomesNotHotels) {
            truncateHouseRules = true;
        } else {
            truncateHouseRules = false;
        }
        String rules = (!this.showingTranslation || TextUtils.isEmpty(this.translatedHouseRules)) ? this.listing.getAdditionalHouseRules() : this.translatedHouseRules;
        TextRowEpoxyModel_ readMoreText = this.houseRulesModel.readMoreText(this.context.getString(C0716R.string.read_more_sentence_cased));
        if (truncateHouseRules) {
            i = 3;
        } else {
            i = 0;
        }
        TextRowEpoxyModel_ text = readMoreText.maxLines(i).text(rules);
        if (TextUtils.isEmpty(rules)) {
            z = false;
        }
        text.addIf(z, (EpoxyController) this);
        this.houseRulesDividerModel.addIf(truncateHouseRules, (EpoxyController) this);
    }

    private void setupListingExpectations() {
        if (this.showExpectations) {
            this.expectationsHeaderModel.titleRes(HouseRulesAndExpectationsUtils.getExpectationsHeaderStringId(this.forBooking)).addTo(this);
            add((List<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) this.showingTranslation ? this.translatedListingExpectations : this.listingExpectations).transform(HouseRulesEpoxyController$$Lambda$2.lambdaFactory$(this)).toList());
        }
    }

    private void setupHomesNotHotelContent() {
        if (this.showHomesNotHotels) {
            this.homesNotHotelsHeaderModel.title(this.context.getString(C0716R.string.homes_not_hotels_title)).addTo(this);
            add((List<? extends EpoxyModel<?>>) FluentIterable.from((Iterable<E>) Arrays.asList(Experiments.showHomesNotHotelsHarderCopy() ? HOMES_NOT_HOTELS_HARD_TREATMENT : HOMES_NOT_HOTELS_SOFT_TREATMENT)).transform(HouseRulesEpoxyController$$Lambda$3.lambdaFactory$(this)).toList());
        }
    }

    private void updateTranslationState(boolean toShowTranslation) {
        this.showingTranslation = toShowTranslation;
        requestModelBuild();
    }

    public void setTranslatedHouseRules(String translatedHouseRules2) {
        this.translatedHouseRules = translatedHouseRules2;
        updateTranslationState(true);
    }

    /* access modifiers changed from: private */
    public EpoxyModel<?> buildRowModel(CharSequence title2) {
        return new StandardRowEpoxyModel_().titleMaxLine(5).title(title2).m5604id(title2);
    }

    /* access modifiers changed from: private */
    public StandardRowEpoxyModel_ buildExpectationRowModel(ListingExpectation expectation) {
        return new StandardRowEpoxyModel_().title((CharSequence) expectation.getTitle()).subtitle((CharSequence) expectation.getAddedDetails()).subTitleMaxLine(10).m5604id((CharSequence) expectation.getType());
    }

    public void onTranslateClicked(View v) {
        boolean shouldShowTranslation;
        boolean shouldRequestTranslation = true;
        if (!this.showingTranslation) {
            shouldShowTranslation = true;
        } else {
            shouldShowTranslation = false;
        }
        if (!shouldShowTranslation || TextUtils.isEmpty(this.listing.getAdditionalHouseRules()) || !TextUtils.isEmpty(this.translatedHouseRules)) {
            shouldRequestTranslation = false;
        }
        if (shouldRequestTranslation) {
            this.translationListener.fetchTranslation();
        } else {
            updateTranslationState(shouldShowTranslation);
        }
        this.translationListener.onTranslationToggle(shouldShowTranslation);
    }
}
