package com.airbnb.android.lib.payments.creditcard.brazil.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.creditcard.brazil.fragments.BrazilCreditCardDetailsFragment;
import com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep;
import icepick.State;

public class BrazilCreditCardDetailsActivity extends AirActivity {
    private static final String EXTRA_BRAZIL_CEP = "extra_brazil_cep";
    private static final String EXTRA_CREDIT_CARD = "extra_credit_card";
    public static final String RESULT_EXTRA_BRAZIL_PAYMENT_INSTRUMENT = "result_extra_brazil_payment_instrument";
    public static final String RESULT_EXTRA_BRAZIL_PAYMENT_INSTRUMENT_CVV_PAYLOAD = "result_extra_brazil_payment_instrument_cvv";
    @State
    BrazilCep brazilCep;
    @State
    DigitalRiverCreditCard creditCard;

    public static Intent intentForBrazil(Context context, BrazilCep brazilCep2, DigitalRiverCreditCard creditCard2) {
        return new Intent(context, BrazilCreditCardDetailsActivity.class).putExtra(EXTRA_BRAZIL_CEP, brazilCep2).putExtra(EXTRA_CREDIT_CARD, creditCard2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        overridePendingTransition(C0880R.anim.activity_transition_slide_in_new, 0);
        if (savedInstanceState == null) {
            this.brazilCep = (BrazilCep) getIntent().getParcelableExtra(EXTRA_BRAZIL_CEP);
            this.creditCard = (DigitalRiverCreditCard) getIntent().getSerializableExtra(EXTRA_CREDIT_CARD);
            showFragment(BrazilCreditCardDetailsFragment.newInstance(this.brazilCep, this.creditCard), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }
}
