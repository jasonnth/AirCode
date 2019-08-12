package com.airbnb.android.lib.payments.quickpay.adapters;

import android.content.Context;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.KickerMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentMethodEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PosterRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.QuickPayButtonSpacerModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.HomesQuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.MagicalTripsQuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.PaidAmenityQuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.QuickPayClickListener;
import com.airbnb.android.lib.viewcomponents.viewmodels.ExpandableCollectionRowEpoxyModel_;
import com.airbnb.android.lib.viewcomponents.viewmodels.LinkableLegalTextModel_;
import com.airbnb.android.lib.viewcomponents.viewmodels.PricingRowEpoxyModel_;
import com.airbnb.p027n2.components.PosterRow.PosterOrientation;

public class QuickPayRowFactory {
    private final Context context;

    public QuickPayRowFactory(Context context2) {
        this.context = context2;
    }

    public DocumentMarqueeEpoxyModel_ marqueeRowModel() {
        return new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.quick_pay_title);
    }

    public DocumentMarqueeEpoxyModel_ marqueeRowModel(int titleRes) {
        return new DocumentMarqueeEpoxyModel_().titleRes(titleRes);
    }

    public KickerMarqueeEpoxyModel_ kickerMarqueeRowModel(int titleRes, CartItem cartItem) {
        return new KickerMarqueeEpoxyModel_().titleRes(titleRes).kickerText(((HomesClientParameters) cartItem.quickPayParameters()).p4Steps());
    }

    public LoadingRowEpoxyModel_ loadingRowModel() {
        return new LoadingRowEpoxyModel_();
    }

    public PosterRowEpoxyModel_ cartPosterRowModelPortrait(CartItem cartItem) {
        return new PosterRowEpoxyModel_().title(cartItem.title()).subtitle(cartItem.description()).imageUrl(cartItem.thumbnailUrl()).posterOrientation(PosterOrientation.PORTRAIT);
    }

    public PosterRowEpoxyModel_ cartPosterRowModelLandscape(CartItem cartItem) {
        return new PosterRowEpoxyModel_().title(cartItem.title()).subtitle(cartItem.description()).imageUrl(cartItem.thumbnailUrl()).posterOrientation(PosterOrientation.LANDSCAPE);
    }

    public StandardRowEpoxyModel_ paymentRowModel(QuickPayClickListener clickListener) {
        return new StandardRowEpoxyModel_().title(C0880R.string.quick_pay_add_payment).rowDrawableRes(C0880R.C0881drawable.n2_standard_row_right_caret_gray).clickListener(QuickPayRowFactory$$Lambda$1.lambdaFactory$(clickListener));
    }

    public PaymentMethodEpoxyModel_ paymentRowModelWithLogo(QuickPayClickListener clickListener) {
        return new PaymentMethodEpoxyModel_().titleRes(C0880R.string.quick_pay_add_payment).onClickListener(QuickPayRowFactory$$Lambda$2.lambdaFactory$(clickListener));
    }

    public StandardRowEpoxyModel_ cartInfoRowModelMagicalTrips(MagicalTripsClientParameters magicalTripsClientParameters) {
        int guestCount = magicalTripsClientParameters.guestCount();
        return new StandardRowEpoxyModel_().title((CharSequence) this.context.getResources().getQuantityString(C0880R.plurals.x_travelers, guestCount, new Object[]{Integer.valueOf(guestCount)}));
    }

    public StandardRowEpoxyModel_ cartInfoRowModelPaidAmenities(CartItem cartItem) {
        int orderCount = ((PaidAmenityClientParameters) cartItem.quickPayParameters()).numberOfUnits();
        return new StandardRowEpoxyModel_().title((CharSequence) cartItem.title()).subtitle((CharSequence) this.context.getResources().getQuantityString(C0880R.plurals.x_orders, orderCount, new Object[]{Integer.valueOf(orderCount)}));
    }

    public ExpandableCollectionRowEpoxyModel_ installmentsRowModel() {
        return new ExpandableCollectionRowEpoxyModel_();
    }

    public PricingRowEpoxyModel_ pricingRowModel() {
        return new PricingRowEpoxyModel_();
    }

    public PricingRowEpoxyModel_ pricingRowModelMagicalTrips(MagicalTripsQuickPayClickListener clickListener) {
        return new PricingRowEpoxyModel_().giftCreditClickListener(QuickPayRowFactory$$Lambda$3.lambdaFactory$(clickListener));
    }

    public PricingRowEpoxyModel_ pricingRowModelPaidAmenities(PaidAmenityQuickPayClickListener clickListener) {
        return new PricingRowEpoxyModel_().giftCreditClickListener(QuickPayRowFactory$$Lambda$4.lambdaFactory$(clickListener));
    }

    public PricingRowEpoxyModel_ pricingRowModelHomes(HomesQuickPayClickListener clickListener) {
        return new PricingRowEpoxyModel_().giftCreditClickListener(QuickPayRowFactory$$Lambda$5.lambdaFactory$(clickListener)).couponCodeClickListener(QuickPayRowFactory$$Lambda$6.lambdaFactory$(clickListener));
    }

    public SimpleTextRowEpoxyModel_ fxRowModel() {
        return new SimpleTextRowEpoxyModel_();
    }

    public LinkableLegalTextModel_ linkableLegalTextModel() {
        return new LinkableLegalTextModel_();
    }

    public QuickPayButtonSpacerModel_ buttonSpacerRow() {
        return new QuickPayButtonSpacerModel_();
    }
}
