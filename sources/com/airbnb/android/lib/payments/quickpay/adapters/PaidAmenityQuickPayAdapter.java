package com.airbnb.android.lib.payments.quickpay.adapters;

import android.content.Context;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentMethodEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.QuickPayButtonSpacerModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.utils.PaymentImageUtils;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.lib.viewcomponents.viewmodels.PricingRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;

public class PaidAmenityQuickPayAdapter extends BaseQuickPayAdapter {
    private final QuickPayButtonSpacerModel_ buttonSpacerModel;
    private final StandardRowEpoxyModel_ cartInfoRowModel;
    private final Context context;
    private final SimpleTextRowEpoxyModel_ fxRowModel;
    private final LoadingRowEpoxyModel_ loadingRowModel;
    private final DocumentMarqueeEpoxyModel_ marqueeModel;
    private final PaymentMethodEpoxyModel_ paymentRowModel;
    private final PricingRowEpoxyModel_ pricingRowModel;

    public static class Builder {
        /* access modifiers changed from: private */
        public QuickPayButtonSpacerModel_ buttonSpacerModel;
        /* access modifiers changed from: private */
        public StandardRowEpoxyModel_ cartInfoRowModel;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public SimpleTextRowEpoxyModel_ fxRowModel;
        /* access modifiers changed from: private */
        public LoadingRowEpoxyModel_ loadingRowModel;
        /* access modifiers changed from: private */
        public DocumentMarqueeEpoxyModel_ marqueeModel;
        /* access modifiers changed from: private */
        public PaymentMethodEpoxyModel_ paymentRowModel;
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

        public Builder cartInfoRowModel(StandardRowEpoxyModel_ model) {
            this.cartInfoRowModel = model;
            return this;
        }

        public Builder paymentRowModel(PaymentMethodEpoxyModel_ model) {
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
            return new PaidAmenityQuickPayAdapter(this);
        }
    }

    private PaidAmenityQuickPayAdapter(Builder builder) {
        super(true);
        this.context = builder.context;
        this.marqueeModel = builder.marqueeModel;
        this.loadingRowModel = builder.loadingRowModel;
        this.cartInfoRowModel = builder.cartInfoRowModel;
        this.paymentRowModel = builder.paymentRowModel;
        this.pricingRowModel = builder.pricingRowModel;
        this.fxRowModel = builder.fxRowModel;
        this.buttonSpacerModel = builder.buttonSpacerModel;
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.loadingRowModel, this.cartInfoRowModel, this.paymentRowModel, this.pricingRowModel, this.fxRowModel, this.buttonSpacerModel});
        hideModel(this.loadingRowModel);
    }

    public void setPriceQuote(BillPriceQuote priceQuote) {
        String string;
        Price price = priceQuote.getPrice();
        this.pricingRowModel.price(price);
        if (priceQuote.getApplicableAirbnbCredit().isPositive()) {
            PricingRowEpoxyModel_ pricingRowEpoxyModel_ = this.pricingRowModel;
            if (price.hasGiftCredit()) {
                string = this.context.getString(C0880R.string.quick_pay_remove_credit);
            } else {
                string = this.context.getString(C0880R.string.quick_pay_apply_gift_credit, new Object[]{priceQuote.getApplicableAirbnbCredit().formattedForDisplay()});
            }
            pricingRowEpoxyModel_.giftCreditTitle(string);
        }
        notifyModelChanged(this.pricingRowModel);
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        if (PaymentUtils.isValidPaymentOption(paymentOption)) {
            this.paymentRowModel.title(paymentOption.getDisplayName(this.context));
            this.paymentRowModel.drawableRes(PaymentImageUtils.getPaymentImageRes(paymentOption));
            notifyModelChanged(this.paymentRowModel);
        }
    }

    public void toggleLoadingState(boolean showLoading) {
        if (showLoading) {
            showModel(this.loadingRowModel);
            hideModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartInfoRowModel, this.paymentRowModel, this.pricingRowModel, this.fxRowModel});
            return;
        }
        hideModel(this.loadingRowModel);
        showModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartInfoRowModel, this.paymentRowModel, this.pricingRowModel, this.fxRowModel});
    }

    public void updateLegalAndFxRow(BillPriceQuote billPriceQuote) {
        String legalCopy = this.context.getString(C0880R.string.paid_amenities_quickpay_fx_copy);
        String fxCopy = billPriceQuote.getFxMessage();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(legalCopy);
        stringBuilder.append(" ");
        if (fxCopy != null) {
            stringBuilder.append(fxCopy);
        }
        this.fxRowModel.text(stringBuilder.toString());
        notifyModelChanged(this.fxRowModel);
    }
}
