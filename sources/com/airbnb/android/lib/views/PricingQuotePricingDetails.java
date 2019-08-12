package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.OldPricingQuote;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class PricingQuotePricingDetails extends LinearLayout {
    private boolean mCaretCollapsed;
    @BindView
    GroupedCell mCleaningFeeCell;
    private boolean mCollapsed;
    CurrencyFormatter mCurrencyHelper;
    private PricingDetailsListener mListener;
    private int mNumNights;
    @BindView
    ViewGroup mPriceBreakdown;
    @BindView
    GroupedCell mPriceNativeCell;
    private OldPricingQuote mPricingQuote;
    @BindView
    GroupedCell mServiceFeeCell;
    @BindView
    GroupedCell mTaxesCell;
    @BindView
    GroupedCell mTotalPriceCell;

    public interface PricingDetailsListener {
        void onCollapseStateChange(boolean z);

        void showDialog(ZenDialog zenDialog);
    }

    public PricingQuotePricingDetails(Context context) {
        this(context, null);
    }

    public PricingQuotePricingDetails(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PricingQuotePricingDetails(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        }
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(C0880R.layout.pricing_quote_pricing_details, this, true);
        if (!isInEditMode()) {
            ButterKnife.bind((View) this);
        }
        setupPriceFields();
    }

    private void setupPriceFields() {
        if (isInEditMode()) {
            this.mCollapsed = true;
            this.mCaretCollapsed = true;
            return;
        }
        TextView title = this.mTotalPriceCell.getTitle();
        title.setTypeface(title.getTypeface(), 1);
        this.mTotalPriceCell.getTooltip().setTooltipIcon(C0880R.C0881drawable.icon_expand_collapse_caret, C0880R.color.c_rausch, false);
        this.mCollapsed = true;
        this.mCaretCollapsed = true;
        this.mServiceFeeCell.getTooltip().setOnClickListener(PricingQuotePricingDetails$$Lambda$1.lambdaFactory$(this));
        this.mTotalPriceCell.setOnClickListener(PricingQuotePricingDetails$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupPriceFields$1(PricingQuotePricingDetails pricingQuotePricingDetails, View v) {
        pricingQuotePricingDetails.setCollapseState(!pricingQuotePricingDetails.mCollapsed);
    }

    public void setCollapseState(boolean collapse) {
        this.mCollapsed = collapse;
        updatePriceState();
        this.mListener.onCollapseStateChange(this.mCollapsed);
    }

    private void updatePriceState() {
        boolean z;
        boolean z2 = true;
        this.mTotalPriceCell.setContent((CharSequence) this.mCurrencyHelper.formatNativeCurrency((double) this.mPricingQuote.getTotalPriceNative(), true));
        this.mPriceNativeCell.setTitle((CharSequence) getResources().getQuantityString(C0880R.plurals.per_night_price_x_nights, this.mNumNights, new Object[]{this.mCurrencyHelper.formatNativeCurrency((double) (this.mPricingQuote.getBasePriceNative() / this.mNumNights), true), Integer.valueOf(this.mNumNights)}));
        this.mPriceNativeCell.setContent((CharSequence) this.mCurrencyHelper.formatNativeCurrency((double) this.mPricingQuote.getBasePriceNative(), true));
        if (this.mPricingQuote.getCleaningFeeNative() > 0) {
            this.mCleaningFeeCell.setContent((CharSequence) this.mCurrencyHelper.formatNativeCurrency((double) this.mPricingQuote.getCleaningFeeNative(), true));
        } else {
            this.mCleaningFeeCell.setVisibility(8);
        }
        this.mServiceFeeCell.setContent((CharSequence) this.mCurrencyHelper.formatNativeCurrency((double) this.mPricingQuote.getServiceFeeNative(), true));
        if (this.mPricingQuote.getTaxAmountNative() > 0) {
            this.mTaxesCell.setVisibility(0);
            this.mTaxesCell.setContent((CharSequence) this.mCurrencyHelper.formatNativeCurrency((double) this.mPricingQuote.getTaxAmountNative(), true));
        } else {
            this.mTaxesCell.setVisibility(8);
        }
        ViewUtils.setGoneIf(this.mPriceBreakdown, this.mCollapsed);
        if (this.mCollapsed != this.mCaretCollapsed) {
            if (!this.mCaretCollapsed) {
                z = true;
            } else {
                z = false;
            }
            this.mCaretCollapsed = z;
            this.mTotalPriceCell.getTooltip().startAnimation(AnimationUtils.loadAnimation(getContext(), this.mCollapsed ? C0880R.anim.rotate_counter_clockwise : C0880R.anim.rotate_clockwise));
        }
        GroupedCell groupedCell = this.mTotalPriceCell;
        if (this.mCollapsed) {
            z2 = false;
        }
        groupedCell.showTopBorder(z2);
    }

    public boolean isCollapsed() {
        return this.mCollapsed;
    }

    public void showDetails(OldPricingQuote pricingQuote, int numNights) {
        this.mPricingQuote = pricingQuote;
        this.mNumNights = numNights;
        updatePriceState();
    }

    public void setPricingDetailsListener(PricingDetailsListener listener) {
        this.mListener = listener;
    }
}
