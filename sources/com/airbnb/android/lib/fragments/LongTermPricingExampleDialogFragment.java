package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.LongTermPricingExample;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.google.common.primitives.Ints;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class LongTermPricingExampleDialogFragment extends ZenDialog {
    private static final String ARG_CURRENCY_CODE = "currency_code";
    private static final String ARG_EXAMPLE = "long_term_pricing_example";
    private String currencyCode;
    @BindView
    LinearLayout dailyPriceList;
    private LongTermPricingExample example;
    @BindView
    LinearLayout priceBeforeFeesLayout;
    @BindView
    LinearLayout priceWithoutDiscountLayout;
    @BindView
    LinearLayout weeklyDiscountLayout;

    public static LongTermPricingExampleDialogFragment newInstance(String dialogTitleRaw, String dateFormatString, String currencyCode2, LongTermPricingExample example2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_EXAMPLE, example2);
        bundle.putString(ARG_CURRENCY_CODE, currencyCode2);
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        return (LongTermPricingExampleDialogFragment) new ZenBuilder(new LongTermPricingExampleDialogFragment()).withTitle(String.format(dialogTitleRaw, new Object[]{example2.getStartDate().formatDate((DateFormat) dateFormat), example2.getStartDate().plusDays(6).formatDate((DateFormat) dateFormat)})).withArguments(bundle).withSingleButton(C0880R.string.okay, 0, (Fragment) null).setCustomLayout(C0880R.layout.dialog_fragment_long_term_pricing_example).create();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        this.example = (LongTermPricingExample) getArguments().getParcelable(ARG_EXAMPLE);
        this.currencyCode = getArguments().getString(ARG_CURRENCY_CODE);
        setUpBottomRows();
        setUpDailyRowsAndPrice(inflater, Ints.asList(this.example.getSamples()));
        return rootView;
    }

    private void setUpDailyRowsAndPrice(LayoutInflater inflater, List<Integer> dailyPrices) {
        AirDate startAirDate = this.example.getStartDate();
        SimpleDateFormat dayFormat = new SimpleDateFormat(getString(C0880R.string.hh_day_week_date_name_format));
        for (int i = 0; i < 7; i++) {
            View listItem = inflater.inflate(C0880R.layout.list_item_long_term_pricing_example, null);
            TextView priceText = (TextView) listItem.findViewById(C0880R.C0882id.numerical_value);
            ((TextView) listItem.findViewById(C0880R.C0882id.caption_text)).setText(startAirDate.formatDate((DateFormat) dayFormat));
            startAirDate = startAirDate.plusDays(1);
            priceText.setText(CurrencyUtils.formatCurrencyAmount((double) ((Integer) dailyPrices.get(i)).intValue(), this.currencyCode));
            this.dailyPriceList.addView(listItem);
        }
    }

    private void setUpBottomRows() {
        TextView priceWithoutDiscountCaption = (TextView) this.priceWithoutDiscountLayout.findViewById(C0880R.C0882id.caption_text);
        ((TextView) this.priceWithoutDiscountLayout.findViewById(C0880R.C0882id.numerical_value)).setText(CurrencyUtils.formatCurrencyAmount((double) this.example.getTotalBeforeDiscount(), this.currencyCode));
        priceWithoutDiscountCaption.setText(C0880R.string.lt_example_price_without_discount);
        TextView discountValue = (TextView) this.weeklyDiscountLayout.findViewById(C0880R.C0882id.numerical_value);
        TextView discountCaption = (TextView) this.weeklyDiscountLayout.findViewById(C0880R.C0882id.caption_text);
        discountValue.setText(NumberFormat.getPercentInstance().format(1.0d - this.example.getDiscount()));
        discountValue.setTextColor(getResources().getColor(C0880R.color.c_kazan));
        discountCaption.setText(C0880R.string.lt_example_discount);
        discountCaption.setTextColor(getResources().getColor(C0880R.color.c_kazan));
        AirTextView totalPriceText = (AirTextView) this.priceBeforeFeesLayout.findViewById(C0880R.C0882id.numerical_value);
        AirTextView totalPriceCaption = (AirTextView) this.priceBeforeFeesLayout.findViewById(C0880R.C0882id.caption_text);
        totalPriceText.setText(CurrencyUtils.formatCurrencyAmount((double) this.example.getTotal(), this.currencyCode));
        totalPriceText.setFont(Font.CircularBold);
        totalPriceCaption.setText(C0880R.string.lt_example_total_price);
        totalPriceCaption.setFont(Font.CircularBold);
    }
}
