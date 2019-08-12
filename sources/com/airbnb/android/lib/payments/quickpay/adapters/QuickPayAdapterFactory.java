package com.airbnb.android.lib.payments.quickpay.adapters;

import android.content.Context;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.quickpay.adapters.MagicalTripsQuickPayAdapter.Builder;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.GiftCardQuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.HomesQuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.MagicalTripsQuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.PaidAmenityQuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.QuickPayClickListener;

public class QuickPayAdapterFactory {
    private final Context context;
    private final QuickPayRowFactory quickPayRowFactory;

    public QuickPayAdapterFactory(Context context2, QuickPayRowFactory quickPayRowFactory2) {
        this.context = context2;
        this.quickPayRowFactory = quickPayRowFactory2;
    }

    public BaseQuickPayAdapter createAdapter(QuickPayClientType clientType, CartItem cartItem, QuickPayClickListener clickListener) {
        switch (clientType) {
            case Trip:
                return createMagicalTripsAdapter(cartItem, clickListener);
            case GiftCard:
                return createGiftCardAdapter(cartItem, clickListener);
            case PaidAmenity:
                return createPaidAmenityAdapter(cartItem, clickListener);
            case ResyReservation:
                return createResyReservationAdapter(cartItem, clickListener);
            case Homes:
                return createHomesQuickPayAdapter(cartItem, clickListener);
            default:
                return createDefaultQuickPayAdapter(cartItem, clickListener);
        }
    }

    private BaseQuickPayAdapter createMagicalTripsAdapter(CartItem cartItem, QuickPayClickListener clickListener) {
        return new Builder(this.context).marqueeRowModel(this.quickPayRowFactory.marqueeRowModel()).loadingRowModel(this.quickPayRowFactory.loadingRowModel()).cartPosterRowModel(this.quickPayRowFactory.cartPosterRowModelPortrait(cartItem)).cartInfoRowModel(this.quickPayRowFactory.cartInfoRowModelMagicalTrips((MagicalTripsClientParameters) cartItem.quickPayParameters())).paymentRowModel(this.quickPayRowFactory.paymentRowModelWithLogo(clickListener)).pricingRowModel(this.quickPayRowFactory.pricingRowModelMagicalTrips((MagicalTripsQuickPayClickListener) clickListener)).fxRowModel(this.quickPayRowFactory.fxRowModel()).buttonSpacerRowModel(this.quickPayRowFactory.buttonSpacerRow()).build();
    }

    private BaseQuickPayAdapter createGiftCardAdapter(CartItem cartItem, QuickPayClickListener clickListener) {
        return new GiftCardQuickPayAdapter.Builder(this.context).marqueeRow(this.quickPayRowFactory.marqueeRowModel()).loadingRowModel(this.quickPayRowFactory.loadingRowModel()).cartPosterRow(this.quickPayRowFactory.cartPosterRowModelPortrait(cartItem)).paymentRowModel(this.quickPayRowFactory.paymentRowModel(clickListener)).pricingRowModel(this.quickPayRowFactory.pricingRowModel()).fxRowModel(this.quickPayRowFactory.fxRowModel()).giftCardQuickPayListener((GiftCardQuickPayClickListener) clickListener).buttonSpacerRowModel(this.quickPayRowFactory.buttonSpacerRow()).build();
    }

    private BaseQuickPayAdapter createPaidAmenityAdapter(CartItem cartItem, QuickPayClickListener clickListener) {
        return new PaidAmenityQuickPayAdapter.Builder(this.context).marqueeRow(this.quickPayRowFactory.marqueeRowModel()).loadingRowModel(this.quickPayRowFactory.loadingRowModel()).cartInfoRowModel(this.quickPayRowFactory.cartInfoRowModelPaidAmenities(cartItem)).paymentRowModel(this.quickPayRowFactory.paymentRowModelWithLogo(clickListener)).pricingRowModel(this.quickPayRowFactory.pricingRowModelPaidAmenities((PaidAmenityQuickPayClickListener) clickListener)).fxRowModel(this.quickPayRowFactory.fxRowModel()).buttonSpacerRowModel(this.quickPayRowFactory.buttonSpacerRow()).build();
    }

    private BaseQuickPayAdapter createResyReservationAdapter(CartItem cartItem, QuickPayClickListener clickListener) {
        return new ResyQuickPayAdapter.Builder(this.context).marqueeRow(this.quickPayRowFactory.marqueeRowModel(C0880R.string.quick_pay_title_resy_reservation)).loadingRowModel(this.quickPayRowFactory.loadingRowModel()).cartPosterRow(this.quickPayRowFactory.cartPosterRowModelPortrait(cartItem)).paymentRowModel(this.quickPayRowFactory.paymentRowModel(clickListener)).pricingRowModel(this.quickPayRowFactory.pricingRowModel()).cancellationPolicyRowModel(this.quickPayRowFactory.linkableLegalTextModel()).termsAndFxRowModel(this.quickPayRowFactory.linkableLegalTextModel()).buttonSpacerRowModel(this.quickPayRowFactory.buttonSpacerRow()).build();
    }

    private BaseQuickPayAdapter createHomesQuickPayAdapter(CartItem cartItem, QuickPayClickListener clickListener) {
        return new HomesQuickPayAdapter.Builder(this.context).marqueeRowModel(this.quickPayRowFactory.kickerMarqueeRowModel(C0880R.string.quick_pay_title_homes, cartItem)).loadingRowModel(this.quickPayRowFactory.loadingRowModel()).cartPosterRowModel(this.quickPayRowFactory.cartPosterRowModelLandscape(cartItem)).paymentRowModel(this.quickPayRowFactory.paymentRowModelWithLogo(clickListener)).pricingRowModel(this.quickPayRowFactory.pricingRowModelHomes((HomesQuickPayClickListener) clickListener)).installmentsRowAboveModel(this.quickPayRowFactory.installmentsRowModel()).installmentsRowBelowModel(this.quickPayRowFactory.installmentsRowModel()).cancellationRefundPolicyModel(this.quickPayRowFactory.linkableLegalTextModel()).termsAndFxRowModel(this.quickPayRowFactory.linkableLegalTextModel()).buttonSpacerRowModel(this.quickPayRowFactory.buttonSpacerRow()).build();
    }

    private BaseQuickPayAdapter createDefaultQuickPayAdapter(CartItem cartItem, QuickPayClickListener clickListener) {
        return new DefaultQuickPayAdapter.Builder(this.context).marqueeRow(this.quickPayRowFactory.marqueeRowModel()).cartPosterRow(this.quickPayRowFactory.cartPosterRowModelPortrait(cartItem)).loadingRowModel(this.quickPayRowFactory.loadingRowModel()).paymentRowModel(this.quickPayRowFactory.paymentRowModel(clickListener)).pricingRowModel(this.quickPayRowFactory.pricingRowModel()).fxRowModel(this.quickPayRowFactory.fxRowModel()).buttonSpacerRowModel(this.quickPayRowFactory.buttonSpacerRow()).build();
    }
}
