package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.utils.GiftCardUtils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.PriceSummary */
public class PriceSummary extends LinearLayout implements DividerView {
    @BindView
    TextView currencyTextView;
    @BindView
    View divider;
    @BindView
    LoadingView loadingView;
    @BindView
    TextView priceBreakdownTextView;
    @BindView
    TextView priceTextView;

    public PriceSummary(Context context) {
        super(context);
        init(null);
    }

    public PriceSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PriceSummary(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_price_summary, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setPriceAndCurrency(CharSequence priceWithSymbol, CharSequence currencyCode) {
        this.priceTextView.setText(priceWithSymbol);
        this.currencyTextView.setText(currencyCode);
    }

    public void setPriceBreakdownText(CharSequence priceBreakdown) {
        ViewLibUtils.setGoneIf(this.priceBreakdownTextView, TextUtils.isEmpty(priceBreakdown));
        this.priceBreakdownTextView.setText(priceBreakdown);
    }

    public void setPriceBreakdownText(int resId) {
        this.priceBreakdownTextView.setVisibility(0);
        this.priceBreakdownTextView.setText(resId);
    }

    public void setLoading() {
        this.priceTextView.setVisibility(4);
        this.priceBreakdownTextView.setVisibility(4);
        this.loadingView.setVisibility(0);
    }

    public void setNormal() {
        boolean z = false;
        this.loadingView.setVisibility(8);
        this.priceTextView.setVisibility(0);
        this.priceBreakdownTextView.setVisibility(0);
        TextView textView = this.priceBreakdownTextView;
        if (!TextUtils.isEmpty(this.priceBreakdownTextView.getText())) {
            z = true;
        }
        ViewLibUtils.setVisibleIf(textView, z);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(PriceSummary summary) {
        summary.setPriceAndCurrency("$100", GiftCardUtils.SUPPORTED_CURRENCY_USD);
        summary.setPriceBreakdownText((CharSequence) "Price breakdown");
        summary.setNormal();
    }
}
