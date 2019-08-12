package com.airbnb.android.lib.fragments.managelisting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.constants.ManageListingArgConstants;
import com.airbnb.android.core.enums.ManageListingPriceType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnEditPriceDoneListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.AutoAirModalLargeActivity;
import com.airbnb.android.lib.views.PriceEditText;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.TextUtil;
import icepick.State;

public class EditPriceFragment extends AirFragment {
    public static final String RESULT_PRICE = "price";
    @BindView
    protected TextView editPriceHeader;
    @State
    int existingPrice;
    @State
    boolean forActionCard;
    @State
    protected Listing listing;
    @BindView
    PriceEditText mPriceText;
    @State
    ManageListingPriceType priceTypeBeingEdited;
    @State
    int suggestedPrice;
    @BindView
    protected TextView textViewBelowPriceField;

    public static Intent getIntent(Context context, Listing listing2, ManageListingPriceType priceType, int existingPrice2, int suggestedPrice2) {
        Bundle args = getBundle(listing2, priceType, existingPrice2, suggestedPrice2);
        if (priceType == ManageListingPriceType.DemandBasedMinimum || priceType == ManageListingPriceType.DemandBasedMaximum) {
            return AutoAirModalLargeActivity.intentForFragment(context, EditPriceFragment.class, args, C0880R.string.ml_demand_based_pricing);
        }
        return AutoAirModalLargeActivity.intentForFragment(context, EditPriceFragment.class, args);
    }

    public static EditPriceFragment newInstance() {
        return new EditPriceFragment();
    }

    public static EditPriceFragment getFragmentForActionCard(Listing listing2, ManageListingPriceType priceType, int existingPrice2, int suggestedPrice2) {
        Bundle args = getBundle(listing2, priceType, existingPrice2, suggestedPrice2);
        args.putBoolean(ManageListingArgConstants.ARG_FOR_ACTION_CARD, true);
        EditPriceFragment f = new EditPriceFragment();
        f.setArguments(args);
        return f;
    }

    protected static Bundle getBundle(Listing listing2, ManageListingPriceType priceType, int existingPrice2, int suggestedPrice2) {
        Bundle args = new Bundle();
        args.putParcelable("listing", listing2);
        args.putInt(ManageListingArgConstants.ARG_PRICE_TYPE, priceType.ordinal());
        args.putInt(ManageListingArgConstants.ARG_EXISTING_PRICE, existingPrice2);
        args.putInt(ManageListingArgConstants.ARG_SUGGESTED_PRICE, suggestedPrice2);
        return args;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_base_price, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.listing = (Listing) getArguments().getParcelable("listing");
            this.priceTypeBeingEdited = ManageListingPriceType.values()[getArguments().getInt(ManageListingArgConstants.ARG_PRICE_TYPE)];
            this.existingPrice = getArguments().getInt(ManageListingArgConstants.ARG_EXISTING_PRICE, 0);
            this.suggestedPrice = getArguments().getInt(ManageListingArgConstants.ARG_SUGGESTED_PRICE, 0);
            this.forActionCard = getArguments().getBoolean(ManageListingArgConstants.ARG_FOR_ACTION_CARD, false);
        }
        this.mPriceText.hidePriceIfZero(true);
        this.mPriceText.setPrice(this.existingPrice, this.listing.getListingNativeCurrency(), Long.valueOf(this.listing.getId()));
        setUpSubTextAndHeader();
        getActivity().getWindow().setSoftInputMode(16);
        return view;
    }

    @OnClick
    public void onTooltipClick() {
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
        switch (this.priceTypeBeingEdited) {
            case DemandBasedMaximum:
                showMaxPriceToolTip();
                return;
            case DemandBasedMinimum:
                showMinPriceToolTip();
                return;
            case Base:
            case CustomDailyPrice:
                showBasePriceToolTip();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void setUpSubTextAndHeader() {
        CharSequence string;
        if (isSmartPricingType()) {
            this.editPriceHeader.setText(this.priceTypeBeingEdited == ManageListingPriceType.DemandBasedMaximum ? C0880R.string.ml_max_price : C0880R.string.ml_min_price);
            setUpDemandBasedPriceSuggestions();
            return;
        }
        boolean isSmartPricingExtended = FeatureToggles.shouldApplyExtendedSmartPricingCopy(this.listing);
        TextView textView = this.editPriceHeader;
        if (isSmartPricingExtended) {
            string = TextUtil.fromHtmlSafe(getString(C0880R.string.smart_pricing_default_nightly_learn_more));
        } else {
            string = getString(C0880R.string.lys_price_per_night);
        }
        textView.setText(string);
        setSmartPricingExtendedOnClick(isSmartPricingExtended);
        setUpBasePriceSuggestion();
    }

    private void setSmartPricingExtendedOnClick(boolean isSmartPricingExtended) {
        if (isSmartPricingExtended) {
            this.editPriceHeader.setOnClickListener(EditPriceFragment$$Lambda$1.lambdaFactory$(this));
        }
    }

    private boolean isSmartPricingType() {
        return this.priceTypeBeingEdited == ManageListingPriceType.DemandBasedMaximum || this.priceTypeBeingEdited == ManageListingPriceType.DemandBasedMinimum;
    }

    private void setUpBasePriceSuggestion() {
        if (this.suggestedPrice <= 0) {
            this.textViewBelowPriceField.setVisibility(4);
            return;
        }
        String priceFormatted = CurrencyUtils.formatCurrencyAmount((double) this.suggestedPrice, this.listing.getListingNativeCurrency());
        String autoPrice = getString(C0880R.string.lys_base_price_info, priceFormatted);
        Spannable sb = new SpannableString(autoPrice);
        ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(C0880R.color.c_babu));
        int index = autoPrice.indexOf(priceFormatted);
        sb.setSpan(fcs, index, priceFormatted.length() + index, 17);
        this.textViewBelowPriceField.setText(sb);
    }

    private void setUpDemandBasedPriceSuggestions() {
        this.textViewBelowPriceField.setTextColor(getResources().getColor(C0880R.color.c_babu));
        if (this.suggestedPrice <= 0) {
            this.textViewBelowPriceField.setText(FeatureToggles.shouldApplyExtendedSmartPricingCopy(this.listing) ? C0880R.string.why_it_is_important : C0880R.string.see_how_it_works);
            return;
        }
        this.textViewBelowPriceField.setText(getString(C0880R.string.price_tip, CurrencyUtils.formatCurrencyAmount((double) this.suggestedPrice, this.listing.getListingNativeCurrency())));
    }

    public void onResume() {
        super.onResume();
        showTooltipIfNeeded();
        this.mPriceText.setOnClickListener(EditPriceFragment$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onResume$2(EditPriceFragment editPriceFragment, View v) {
        editPriceFragment.mPriceText.requestFocus();
        editPriceFragment.mPriceText.post(EditPriceFragment$$Lambda$4.lambdaFactory$(editPriceFragment));
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
    }

    public void onStart() {
        super.onStart();
        ActionBar actionbar = getActionBar();
        actionbar.setCustomView(C0880R.layout.editor_actionbar_layout);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.getCustomView().setOnClickListener(EditPriceFragment$$Lambda$3.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void onDonePressed() {
        boolean setNewPrice;
        if (this.mPriceText.isValueEmpty() || this.mPriceText.getValue() <= 0) {
            setNewPrice = false;
        } else {
            setNewPrice = true;
        }
        Activity activity = getActivity();
        if (this.forActionCard) {
            updateField();
            return;
        }
        if (setNewPrice) {
            int newPrice = this.mPriceText.getValue();
            Intent intent = new Intent();
            intent.putExtra(RESULT_PRICE, newPrice);
            activity.setResult(-1, intent);
        } else {
            activity.setResult(0);
        }
        activity.finish();
    }

    private void updateField() {
        ((OnEditPriceDoneListener) getActivity()).onEditPriceDone(this.priceTypeBeingEdited, this.mPriceText.isValueEmpty() ? 0 : this.mPriceText.getValue());
    }

    public void onStop() {
        super.onStop();
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowCustomEnabled(false);
        actionbar.setDisplayShowTitleEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
    }

    private void showTooltipIfNeeded() {
        switch (this.priceTypeBeingEdited) {
            case DemandBasedMaximum:
                if (this.mPrefsHelper.shouldShowMaxPriceToolTip()) {
                    showMaxPriceToolTip();
                    this.mPrefsHelper.markMaxPriceToolTipAsSeen();
                    return;
                }
                return;
            case DemandBasedMinimum:
                if (this.mPrefsHelper.shouldShowMinPriceToolTip()) {
                    showMinPriceToolTip();
                    this.mPrefsHelper.markMinPriceToolTipAsSeen();
                    return;
                }
                return;
            case Base:
                if (this.mPrefsHelper.shouldShowBasePriceToolTip()) {
                    showBasePriceToolTip();
                    this.mPrefsHelper.markBasePriceToolTipAsSeen();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void showBasePriceToolTip() {
        TooltipDialogFragment.newInstance(C0880R.string.tooltip_base_price_title, C0880R.string.tooltip_base_price_help).show(getChildFragmentManager(), (String) null);
    }

    private void showMaxPriceToolTip() {
        TooltipDialogFragment.newInstance(C0880R.string.tooltip_max_price_title, getString(C0880R.string.dynamic_pricing_max_price_help, getString(C0880R.string.max_min_price_disclaimer))).show(getChildFragmentManager(), (String) null);
    }

    private void showMinPriceToolTip() {
        TooltipDialogFragment.newInstance(C0880R.string.tooltip_min_price_title, getString(C0880R.string.dynamic_pricing_min_price_help, getString(C0880R.string.max_min_price_disclaimer))).show(getChildFragmentManager(), (String) null);
    }
}
