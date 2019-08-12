package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

/* renamed from: com.airbnb.n2.components.PricingBreakdownRow */
public class PricingBreakdownRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    LinearLayout pricingItemContainer;
    @BindView
    AirTextView totalPriceInfoText;
    @BindView
    AirTextView totalPriceTitle;

    /* renamed from: com.airbnb.n2.components.PricingBreakdownRow$ActionableItem */
    public static class ActionableItem {
        public final String localizedTitle;
        public final OnClickListener onClickListener;

        public ActionableItem(String localizedTitle2, OnClickListener onClickListener2) {
            this.localizedTitle = localizedTitle2;
            this.onClickListener = onClickListener2;
        }
    }

    /* renamed from: com.airbnb.n2.components.PricingBreakdownRow$PriceItem */
    public static class PriceItem {
        public final String localizedPrice;
        public final String localizedTitle;

        public PriceItem(String localizedTitle2, String localizedPrice2) {
            this.localizedTitle = localizedTitle2;
            this.localizedPrice = localizedPrice2;
        }
    }

    public PricingBreakdownRow(Context context) {
        super(context);
        init();
    }

    public PricingBreakdownRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PricingBreakdownRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_pricing_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public LinearLayout setPriceItemRows(List<PriceItem> priceItems) {
        this.pricingItemContainer.removeAllViews();
        for (PriceItem priceItem : priceItems) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(this.pricingItemContainer.getContext()).inflate(R.layout.n2_pricing_row_item, this.pricingItemContainer, false);
            ((AirTextView) view.findViewById(R.id.price_item_title)).setText(priceItem.localizedTitle);
            ((AirTextView) view.findViewById(R.id.price_item_info)).setText(priceItem.localizedPrice);
            this.pricingItemContainer.addView(view);
        }
        return this.pricingItemContainer;
    }

    public LinearLayout setGiftCreditRow(ActionableItem actionableItem) {
        if (actionableItem.localizedTitle != null) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(this.pricingItemContainer.getContext()).inflate(R.layout.n2_pricing_row_item_actionable, this.pricingItemContainer, false);
            AirTextView titleView = (AirTextView) view.findViewById(R.id.price_item_title);
            titleView.setText(actionableItem.localizedTitle);
            titleView.setOnClickListener(actionableItem.onClickListener);
            this.pricingItemContainer.addView(view);
        }
        return this.pricingItemContainer;
    }

    public LinearLayout setCouponRows(PriceItem couponPriceItem, ActionableItem actionableItem) {
        if (couponPriceItem != null) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(this.pricingItemContainer.getContext()).inflate(R.layout.n2_pricing_row_item, this.pricingItemContainer, false);
            ((AirTextView) view.findViewById(R.id.price_item_title)).setText(couponPriceItem.localizedTitle);
            ((AirTextView) view.findViewById(R.id.price_item_info)).setText(couponPriceItem.localizedPrice);
            this.pricingItemContainer.addView(view);
        }
        if (actionableItem.localizedTitle != null) {
            LinearLayout view2 = (LinearLayout) LayoutInflater.from(this.pricingItemContainer.getContext()).inflate(R.layout.n2_pricing_row_item_actionable, this.pricingItemContainer, false);
            AirTextView titleView = (AirTextView) view2.findViewById(R.id.price_item_title);
            titleView.setText(actionableItem.localizedTitle);
            titleView.setOnClickListener(actionableItem.onClickListener);
            this.pricingItemContainer.addView(view2);
        }
        return this.pricingItemContainer;
    }

    public void setTotalPriceTitle(CharSequence text) {
        this.totalPriceTitle.setText(text);
    }

    public void setTotalPriceInfoText(CharSequence text) {
        this.totalPriceInfoText.setText(text);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(PricingBreakdownRow row) {
        row.setTotalPriceTitle("Total Price Title");
        row.setTotalPriceInfoText("Total Price Info");
    }
}
