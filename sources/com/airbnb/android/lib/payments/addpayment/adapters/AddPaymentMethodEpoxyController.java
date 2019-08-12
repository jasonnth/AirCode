package com.airbnb.android.lib.payments.addpayment.adapters;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentMethodEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.addpayment.clicklisteners.AddPaymentMethodListener;
import com.airbnb.android.lib.payments.utils.PaymentImageUtils;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.List;

public class AddPaymentMethodEpoxyController extends AirEpoxyController {
    private final String billingCountry;
    private final AddPaymentMethodListener listener;
    EpoxyControllerLoadingModel_ loadingRowModel;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    List<PaymentOption> paymentOptions;
    private boolean showLoading = false;

    public AddPaymentMethodEpoxyController(List<PaymentOption> paymentOptions2, AddPaymentMethodListener listener2, String billingCountry2) {
        this.paymentOptions = paymentOptions2;
        this.listener = listener2;
        this.billingCountry = billingCountry2;
        requestModelBuild();
    }

    public void setLoading(boolean showLoading2) {
        this.showLoading = showLoading2;
        requestModelBuild();
    }

    public void setData(List<PaymentOption> paymentOptions2) {
        this.paymentOptions = paymentOptions2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C0880R.string.add_payment_method_marquee_text).captionRes(C0880R.string.add_payment_method_billing_country_subtitle).linkText(this.billingCountry).linkClickListener(AddPaymentMethodEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        if (this.showLoading) {
            add((EpoxyModel<?>) this.loadingRowModel);
        } else {
            addPaymentMethodTypes(this.paymentOptions);
        }
    }

    private void addPaymentMethodTypes(List<PaymentOption> paymentOptions2) {
        for (PaymentMethodType paymentMethodType : PaymentUtils.getPaymentMethodTypesFromPaymentOptions(paymentOptions2)) {
            add((EpoxyModel<?>) createPaymentMethodModel(paymentMethodType, this.listener));
        }
    }

    private PaymentMethodEpoxyModel_ createPaymentMethodModel(PaymentMethodType paymentMethod, AddPaymentMethodListener listener2) {
        return new PaymentMethodEpoxyModel_().m5278id((long) paymentMethod.hashCode()).showDivider(true).titleRes(PaymentMethodType.getNameResource(paymentMethod)).drawableRes(PaymentImageUtils.getPaymentImageRes(paymentMethod)).onClickListener(AddPaymentMethodEpoxyController$$Lambda$2.lambdaFactory$(listener2, paymentMethod));
    }
}
