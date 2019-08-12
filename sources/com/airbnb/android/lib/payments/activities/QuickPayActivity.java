package com.airbnb.android.lib.payments.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.QuickPayActivityIntents;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayArguments;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.quickpay.fragments.GiftCardQuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.HomesQuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.HomesQuickPayFragment.BillPriceQuoteListener;
import com.airbnb.android.lib.payments.quickpay.fragments.MagicalTripsQuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.PaidAmenityQuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.QuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.ResyQuickPayFragment;
import icepick.State;

public class QuickPayActivity extends AirActivity implements BillPriceQuoteListener {
    private static final String TAG = QuickPayActivity.class.getSimpleName();
    @State
    BillPriceQuote billPriceQuote;
    @State
    CartItem cartItem;
    @State
    QuickPayClientType clientType;
    @BindView
    FrameLayout container;
    @State
    Price price;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            QuickPayArguments arguments = (QuickPayArguments) getIntent().getParcelableExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT);
            if (arguments == null) {
            }
            this.cartItem = arguments.getCartItem();
            this.price = arguments.getPrice();
            this.clientType = arguments.getClientType();
            Check.notNull(this.cartItem);
            Check.notNull(this.clientType);
            overrideEnterAnimation();
            displayQuickPay();
        }
    }

    public void finish() {
        super.finish();
        overrideExitAnimation();
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        switch (this.clientType) {
            case Homes:
                returnBillPriceQuote();
                return;
            default:
                super.onHomeActionPressed();
                return;
        }
    }

    public void onBackPressed() {
        switch (this.clientType) {
            case Homes:
                returnBillPriceQuote();
                return;
            default:
                super.onBackPressed();
                return;
        }
    }

    public void onBillPriceQuoteChanged(BillPriceQuote billPriceQuote2) {
        this.billPriceQuote = billPriceQuote2;
    }

    private void displayQuickPay() {
        QuickPayFragment quickPayFragment;
        FragmentTransitionType transitionType = FragmentTransitionType.SlideFromBottom;
        switch (this.clientType) {
            case Homes:
                quickPayFragment = HomesQuickPayFragment.instanceForCartItem(this.cartItem, this.clientType, (Reservation) getIntent().getParcelableExtra(HomesQuickPayFragment.ARG_RESERVATION));
                transitionType = FragmentTransitionType.SlideInFromSide;
                break;
            case Trip:
                quickPayFragment = MagicalTripsQuickPayFragment.instanceForCartItem(this.cartItem, this.clientType);
                break;
            case GiftCard:
                quickPayFragment = GiftCardQuickPayFragment.instanceForCartItem(this.cartItem, this.clientType);
                break;
            case PaidAmenity:
                quickPayFragment = PaidAmenityQuickPayFragment.instanceForCartItem(this.cartItem, this.clientType);
                break;
            case ResyReservation:
                quickPayFragment = ResyQuickPayFragment.instanceForCartItem(this.cartItem, this.clientType);
                break;
            default:
                quickPayFragment = QuickPayFragment.instanceForCartItem(this.cartItem, this.clientType);
                break;
        }
        showFragment(quickPayFragment, C0880R.C0882id.content_container, transitionType, false, TAG);
    }

    private void returnBillPriceQuote() {
        Intent intent = new Intent();
        intent.putExtra(QuickPayActivityIntents.RESULT_EXTRA_BILL_PRICE_QUOTE, this.billPriceQuote);
        setResult(0, intent);
        finish();
    }

    private void overrideEnterAnimation() {
        switch (this.clientType) {
            case Homes:
                overridePendingTransition(C0880R.anim.activity_transition_slide_in_new, 0);
                return;
            default:
                overridePendingTransition(C0880R.anim.enter_bottom, 0);
                return;
        }
    }

    private void overrideExitAnimation() {
        switch (this.clientType) {
            case Homes:
                overridePendingTransition(0, C0880R.anim.activity_transition_slide_out_new);
                return;
            default:
                overridePendingTransition(0, C0880R.anim.exit_bottom);
                return;
        }
    }
}
