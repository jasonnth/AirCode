package com.airbnb.android.lib.payments.braintree;

import android.app.Activity;
import android.content.Context;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.lib.payments.braintree.PayPalTokenizer.PayPalListener;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.exceptions.InvalidArgumentException;

public class BraintreeFactory {
    public static final String BRAINTREE_TOKENIZATION_KEY = "production_x2392hp5_pfpfmbt48yh4ht8c";

    public BraintreeFragment createBraintreeFragment(Activity activity) {
        BraintreeFragment braintreeFragment = null;
        try {
            return BraintreeFragment.newInstance(activity, BRAINTREE_TOKENIZATION_KEY);
        } catch (InvalidArgumentException e) {
            BugsnagWrapper.notify(new Throwable("[Braintree_Token]Unable to instantiate Braintree with tokenization key"));
            return braintreeFragment;
        }
    }

    public AndroidPayApi createAndroidPayApi(Context context, BraintreeFragment fragment, CurrencyFormatter currencyFormatter) {
        return new AndroidPayClient(context, fragment, currencyFormatter);
    }

    public BraintreeCreditCardApi createCreditCardApi(BraintreeFragment fragment) {
        return new BraintreeCreditCardTokenizer(fragment);
    }

    public PayPalApi createPayPalApi(BraintreeFragment fragment, RequestManager requestManager, PayPalListener listener) {
        return new PayPalTokenizer(fragment, requestManager, listener);
    }
}
