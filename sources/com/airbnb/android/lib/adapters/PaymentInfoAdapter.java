package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.EntryMarqueeEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.EntryMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;

public class PaymentInfoAdapter extends AirEpoxyAdapter {
    private final PaymentInfoAdapterInterface adapterInterface;

    public interface PaymentInfoAdapterInterface {
        void onPaymentMethodsClicked();

        void onPayoutsClicked();
    }

    public PaymentInfoAdapter(PaymentInfoAdapterInterface paymentInfoAdapterInterface) {
        super(true);
        this.adapterInterface = paymentInfoAdapterInterface;
        EntryMarqueeEpoxyModel marqueeModel = new EntryMarqueeEpoxyModel_();
        marqueeModel.title(C0880R.string.payment_info_title).caption(C0880R.string.payment_info_caption);
        StandardRowEpoxyModel_ paymentMethodsModel = new StandardRowEpoxyModel_();
        paymentMethodsModel.title(C0880R.string.payment_info_payment_methods_option).actionText(C0880R.string.payment_info_add);
        StandardRowEpoxyModel_ payoutsModel = new StandardRowEpoxyModel_();
        payoutsModel.title(C0880R.string.payment_info_payouts_option).actionText(C0880R.string.payment_info_add).clickListener(PaymentInfoAdapter$$Lambda$1.lambdaFactory$(this));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{marqueeModel, paymentMethodsModel, payoutsModel});
    }
}
