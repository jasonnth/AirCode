package com.airbnb.android.lib.payments.quickpay.adapters;

import android.content.Context;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PosterRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.QuickPayButtonSpacerModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.lib.viewcomponents.viewmodels.PricingRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;

public class DefaultQuickPayAdapter extends BaseQuickPayAdapter {
    private final QuickPayButtonSpacerModel_ buttonSpacerModel;
    private final PosterRowEpoxyModel_ cartPosterModel;
    private final Context context;
    private final SimpleTextRowEpoxyModel_ fxRowModel;
    private final LoadingRowEpoxyModel_ loadingRowModel;
    private final DocumentMarqueeEpoxyModel_ marqueeModel;
    private final StandardRowEpoxyModel_ paymentRowModel;
    private final PricingRowEpoxyModel_ pricingRowModel;

    public static class Builder {
        /* access modifiers changed from: private */
        public QuickPayButtonSpacerModel_ buttonSpacerModel;
        /* access modifiers changed from: private */
        public PosterRowEpoxyModel_ cartPosterModel;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public SimpleTextRowEpoxyModel_ fxRowModel;
        /* access modifiers changed from: private */
        public LoadingRowEpoxyModel_ loadingRowModel;
        /* access modifiers changed from: private */
        public DocumentMarqueeEpoxyModel_ marqueeModel;
        /* access modifiers changed from: private */
        public StandardRowEpoxyModel_ paymentRowModel;
        /* access modifiers changed from: private */
        public PricingRowEpoxyModel_ pricingRowModel;

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

        public Builder fxRowModel(SimpleTextRowEpoxyModel_ model) {
            this.fxRowModel = model;
            return this;
        }

        public Builder buttonSpacerRowModel(QuickPayButtonSpacerModel_ model) {
            this.buttonSpacerModel = model;
            return this;
        }

        public BaseQuickPayAdapter build() {
            return new DefaultQuickPayAdapter(this);
        }
    }

    private DefaultQuickPayAdapter(Builder builder) {
        super(true);
        this.context = builder.context;
        this.marqueeModel = builder.marqueeModel;
        this.loadingRowModel = builder.loadingRowModel;
        this.cartPosterModel = builder.cartPosterModel;
        this.paymentRowModel = builder.paymentRowModel;
        this.pricingRowModel = builder.pricingRowModel;
        this.fxRowModel = builder.fxRowModel;
        this.buttonSpacerModel = builder.buttonSpacerModel;
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.loadingRowModel, this.cartPosterModel, this.paymentRowModel, this.pricingRowModel, this.fxRowModel, this.buttonSpacerModel});
        hideModel(this.loadingRowModel);
    }

    public void setPriceQuote(BillPriceQuote priceQuote) {
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
            hideModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartPosterModel, this.paymentRowModel, this.pricingRowModel});
            return;
        }
        hideModel(this.loadingRowModel);
        showModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartPosterModel, this.paymentRowModel, this.pricingRowModel});
    }

    public void updateLegalAndFxRow(BillPriceQuote billPriceQuote) {
        String fxCopy = billPriceQuote.getFxMessage();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(" ");
        if (fxCopy != null) {
            stringBuilder.append(fxCopy);
        }
        this.fxRowModel.text(stringBuilder.toString());
        notifyModelChanged(this.fxRowModel);
    }
}
