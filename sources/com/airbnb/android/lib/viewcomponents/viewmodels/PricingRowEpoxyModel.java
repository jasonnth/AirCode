package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Price.Type;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.PricingBreakdownRow;
import com.airbnb.p027n2.components.PricingBreakdownRow.ActionableItem;
import com.airbnb.p027n2.components.PricingBreakdownRow.PriceItem;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.google.common.collect.FluentIterable;
import java.util.List;

public abstract class PricingRowEpoxyModel extends AirEpoxyModel<PricingBreakdownRow> {
    OnClickListener couponCodeClickListener;
    String couponCodeTitle;
    OnClickListener giftCreditClickListener;
    String giftCreditTitle;
    Price price;

    public void bind(PricingBreakdownRow view) {
        super.bind(view);
        if (this.price != null) {
            view.setPriceItemRows(getPriceItems(this.price.getPriceItems()));
            view.setGiftCreditRow(new ActionableItem(this.giftCreditTitle, this.giftCreditClickListener));
            view.setCouponRows(getCouponCodePriceItem(this.price.getPriceItems()), new ActionableItem(this.couponCodeTitle, this.couponCodeClickListener));
            view.setTotalPriceTitle(view.getContext().getString(C0880R.string.quick_pay_price_total_with_currency, new Object[]{this.price.getLocalizedTitle(), this.price.getTotal().getCurrency()}));
            view.setTotalPriceInfoText(this.price.getTotal().formattedForDisplay());
        }
    }

    public int getDividerViewType() {
        return 0;
    }

    private List<PriceItem> getPriceItems(List<Price> prices) {
        return FluentIterable.from((Iterable<E>) prices).filter(PricingRowEpoxyModel$$Lambda$1.lambdaFactory$()).transform(PricingRowEpoxyModel$$Lambda$2.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getPriceItems$0(Price price2) {
        return price2.getType() != Type.Coupon;
    }

    static /* synthetic */ PriceItem lambda$getPriceItems$1(Price price2) {
        return new PriceItem(price2.getLocalizedTitle(), price2.getTotal().formattedForDisplay());
    }

    private PriceItem getCouponCodePriceItem(List<Price> prices) {
        return (PriceItem) FluentIterable.from((Iterable<E>) prices).firstMatch(PricingRowEpoxyModel$$Lambda$3.lambdaFactory$()).transform(PricingRowEpoxyModel$$Lambda$4.lambdaFactory$()).orNull();
    }

    static /* synthetic */ boolean lambda$getCouponCodePriceItem$2(Price price2) {
        return price2.getType() == Type.Coupon;
    }

    static /* synthetic */ PriceItem lambda$getCouponCodePriceItem$3(Price price2) {
        return new PriceItem(price2.getLocalizedTitle(), price2.getTotal().formattedForDisplay());
    }
}
