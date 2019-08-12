package com.airbnb.android.lib.payments.paymentoptions.adapters;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentMethodEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentOptionEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaymentOptionsAdapter extends AirEpoxyAdapter {
    private final LoadingRowEpoxyModel_ loadingRowModel;
    private final DocumentMarqueeEpoxyModel_ marqueeModel;
    private final PaymentMethodEpoxyModel_ paymentMethodModel;
    private final List<PaymentOptionEpoxyModel_> paymentOptionModels;
    private List<PaymentOption> paymentOptions;
    private PaymentOption selectedPaymentOption;

    public static class Builder {
        /* access modifiers changed from: private */
        public LoadingRowEpoxyModel_ loadingModel;
        /* access modifiers changed from: private */
        public DocumentMarqueeEpoxyModel_ marqueeModel;
        /* access modifiers changed from: private */
        public PaymentMethodEpoxyModel_ paymentMethodModel;
        /* access modifiers changed from: private */
        public List<PaymentOptionEpoxyModel_> paymentOptionModels;
        /* access modifiers changed from: private */
        public List<PaymentOption> paymentOptions;
        /* access modifiers changed from: private */
        public PaymentOption selectedPaymentOption;

        public Builder marqueeModel(DocumentMarqueeEpoxyModel_ marqueeModel2) {
            this.marqueeModel = marqueeModel2;
            return this;
        }

        public Builder loadingModel(LoadingRowEpoxyModel_ loadingModel2) {
            this.loadingModel = loadingModel2;
            return this;
        }

        public Builder paymentOptionModels(List<PaymentOptionEpoxyModel_> paymentOptionModels2) {
            this.paymentOptionModels = paymentOptionModels2;
            return this;
        }

        public Builder paymentMethodModel(PaymentMethodEpoxyModel_ paymentMethodModel2) {
            this.paymentMethodModel = paymentMethodModel2;
            return this;
        }

        public Builder paymentOptions(List<PaymentOption> paymentOptions2) {
            this.paymentOptions = paymentOptions2;
            return this;
        }

        public Builder selectedPaymentOption(PaymentOption selectedPaymentOption2) {
            this.selectedPaymentOption = selectedPaymentOption2;
            return this;
        }

        public PaymentOptionsAdapter build() {
            return new PaymentOptionsAdapter(this);
        }
    }

    public interface PaymentOptionsAdapterListener {
        void onAddPaymentMethodSelected();

        void onPaymentOptionSelected(PaymentOption paymentOption);
    }

    private PaymentOptionsAdapter(Builder builder) {
        enableDiffing();
        this.marqueeModel = builder.marqueeModel;
        this.loadingRowModel = builder.loadingModel;
        this.paymentOptionModels = builder.paymentOptionModels;
        this.paymentMethodModel = builder.paymentMethodModel;
        this.paymentOptions = builder.paymentOptions;
        this.selectedPaymentOption = builder.selectedPaymentOption;
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.loadingRowModel});
        addModels((Collection<? extends EpoxyModel<?>>) this.paymentOptionModels);
        addModel(this.paymentMethodModel);
        hideModel(this.loadingRowModel);
    }

    public void setSelectedPaymentOption(PaymentOption paymentOption) {
        int previousPaymentOptionIndex = this.paymentOptions.indexOf(this.selectedPaymentOption);
        int newPaymentOptionIndex = this.paymentOptions.indexOf(paymentOption);
        ((PaymentOptionEpoxyModel_) this.paymentOptionModels.get(previousPaymentOptionIndex)).isChecked(false);
        ((PaymentOptionEpoxyModel_) this.paymentOptionModels.get(newPaymentOptionIndex)).isChecked(true);
        this.selectedPaymentOption = paymentOption;
        notifyModelsChanged();
    }

    public void toggleLoadingState(boolean shouldShowLoadingState) {
        if (shouldShowLoadingState) {
            showModel(this.loadingRowModel);
            hideModels((Iterable<EpoxyModel<?>>) new ArrayList<EpoxyModel<?>>(this.paymentOptionModels));
            return;
        }
        hideModel(this.loadingRowModel);
        showModels((Iterable<EpoxyModel<?>>) new ArrayList<EpoxyModel<?>>(this.paymentOptionModels));
    }
}
