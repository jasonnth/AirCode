package com.airbnb.android.lib.payments.quickpay.adapters;

import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillPriceQuote.CancellationInfo;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PosterRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.QuickPayButtonSpacerModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.lib.viewcomponents.viewmodels.LinkableLegalTextModel_;
import com.airbnb.android.lib.viewcomponents.viewmodels.PricingRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;

public class ResyQuickPayAdapter extends BaseQuickPayAdapter {
    private final QuickPayButtonSpacerModel_ buttonSpacerModel;
    private final LinkableLegalTextModel_ cancellationInfoRowModel;
    private final PosterRowEpoxyModel_ cartPosterModel;
    private final Context context;
    private final LoadingRowEpoxyModel_ loadingRowModel;
    private final DocumentMarqueeEpoxyModel_ marqueeModel;
    private final StandardRowEpoxyModel_ paymentRowModel;
    private final PricingRowEpoxyModel_ pricingRowModel;
    private final LinkableLegalTextModel_ termsAndFxRowModel;

    public static class Builder {
        /* access modifiers changed from: private */
        public QuickPayButtonSpacerModel_ buttonSpacerModel;
        /* access modifiers changed from: private */
        public LinkableLegalTextModel_ cancellationPolicyRowModel;
        /* access modifiers changed from: private */
        public PosterRowEpoxyModel_ cartPosterModel;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public LoadingRowEpoxyModel_ loadingRowModel;
        /* access modifiers changed from: private */
        public DocumentMarqueeEpoxyModel_ marqueeModel;
        /* access modifiers changed from: private */
        public StandardRowEpoxyModel_ paymentRowModel;
        /* access modifiers changed from: private */
        public PricingRowEpoxyModel_ pricingRowModel;
        /* access modifiers changed from: private */
        public LinkableLegalTextModel_ termsAndFxRowModel;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder marqueeRow(DocumentMarqueeEpoxyModel_ model) {
            this.marqueeModel = model;
            return this;
        }

        public Builder loadingRowModel(LoadingRowEpoxyModel_ model) {
            this.loadingRowModel = model;
            return this;
        }

        public Builder cartPosterRow(PosterRowEpoxyModel_ model) {
            this.cartPosterModel = model;
            return this;
        }

        public Builder paymentRowModel(StandardRowEpoxyModel_ model) {
            this.paymentRowModel = model;
            return this;
        }

        public Builder pricingRowModel(PricingRowEpoxyModel_ model) {
            this.pricingRowModel = model;
            return this;
        }

        public Builder cancellationPolicyRowModel(LinkableLegalTextModel_ model) {
            this.cancellationPolicyRowModel = model;
            return this;
        }

        public Builder termsAndFxRowModel(LinkableLegalTextModel_ model) {
            this.termsAndFxRowModel = model;
            return this;
        }

        public Builder buttonSpacerRowModel(QuickPayButtonSpacerModel_ model) {
            this.buttonSpacerModel = model;
            return this;
        }

        public BaseQuickPayAdapter build() {
            return new ResyQuickPayAdapter(this);
        }
    }

    private ResyQuickPayAdapter(Builder builder) {
        super(true);
        this.context = builder.context;
        this.marqueeModel = builder.marqueeModel;
        this.loadingRowModel = builder.loadingRowModel;
        this.cartPosterModel = builder.cartPosterModel;
        this.paymentRowModel = builder.paymentRowModel;
        this.pricingRowModel = builder.pricingRowModel;
        this.cancellationInfoRowModel = builder.cancellationPolicyRowModel;
        this.termsAndFxRowModel = builder.termsAndFxRowModel;
        this.buttonSpacerModel = builder.buttonSpacerModel;
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.loadingRowModel, this.cartPosterModel, this.paymentRowModel, this.pricingRowModel, this.cancellationInfoRowModel, this.termsAndFxRowModel, this.buttonSpacerModel});
        hideModel(this.loadingRowModel);
    }

    public void setPriceQuote(BillPriceQuote priceQuote) {
        if (priceQuote.getPrice().getTotal().isZero()) {
            removePaymentAndPricingRows();
            return;
        }
        this.pricingRowModel.price(priceQuote.getPrice());
        notifyModelChanged(this.pricingRowModel);
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        if (PaymentUtils.isValidPaymentOption(paymentOption)) {
            this.paymentRowModel.title((CharSequence) paymentOption.getDisplayName(this.context));
            notifyModelChanged(this.paymentRowModel);
        }
    }

    public void toggleLoadingState(boolean showLoading) {
        if (showLoading) {
            showModel(this.loadingRowModel);
            hideModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartPosterModel, this.paymentRowModel, this.pricingRowModel, this.cancellationInfoRowModel, this.termsAndFxRowModel});
            return;
        }
        hideModel(this.loadingRowModel);
        showModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartPosterModel, this.paymentRowModel, this.pricingRowModel, this.cancellationInfoRowModel, this.termsAndFxRowModel});
    }

    public void updateLegalAndFxRow(BillPriceQuote billPriceQuote) {
        setTermsAndFx(billPriceQuote);
        setCancellationInfo(billPriceQuote);
    }

    private void setTermsAndFx(BillPriceQuote billPriceQuote) {
        if (billPriceQuote.getTermsAndConditions() != null) {
            this.termsAndFxRowModel.termsTitle(billPriceQuote.getTermsAndConditions().title());
            this.termsAndFxRowModel.termsBody(billPriceQuote.getTermsAndConditions().text());
            showModel(this.termsAndFxRowModel);
        } else {
            hideModel(this.termsAndFxRowModel);
        }
        notifyModelChanged(this.termsAndFxRowModel);
    }

    private void setCancellationInfo(BillPriceQuote billPriceQuote) {
        CancellationInfo cancellationInfo = billPriceQuote.getCancellationInfo();
        if (cancellationInfo != null) {
            this.cancellationInfoRowModel.termsTitle(cancellationInfo.title());
            if (cancellationInfo.subtitles().isEmpty()) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("[Resy] Cancellation Policy subtitles cannot be empty"));
            } else {
                this.cancellationInfoRowModel.termsBody((CharSequence) cancellationInfo.subtitles().get(0));
            }
            showModel(this.cancellationInfoRowModel);
        } else {
            hideModel(this.cancellationInfoRowModel);
        }
        notifyModelChanged(this.cancellationInfoRowModel);
    }

    private void removePaymentAndPricingRows() {
        removeModel(this.paymentRowModel);
        removeModel(this.pricingRowModel);
    }
}
