package com.airbnb.android.lib.payments.quickpay.paymentredirect;

import android.app.Activity;
import com.airbnb.android.core.payments.models.RedirectSettings;
import com.airbnb.android.lib.payments.activities.PaymentRedirectWebViewActivity;

public class PaymentRedirectCoordinator {
    public boolean shouldRedirectPayment(RedirectSettings redirectSettings) {
        return (redirectSettings == null || redirectSettings.url() == null) ? false : true;
    }

    public void launchRedirectPaymentFlow(Activity activity, String url, int requestCode) {
        activity.startActivityForResult(PaymentRedirectWebViewActivity.intentForRedirect(activity, url), requestCode);
    }
}
