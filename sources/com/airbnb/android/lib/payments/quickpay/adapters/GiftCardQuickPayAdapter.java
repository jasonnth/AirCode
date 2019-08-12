package com.airbnb.android.lib.payments.quickpay.adapters;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PosterRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.QuickPayButtonSpacerModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.GiftCardQuickPayClickListener;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.lib.viewcomponents.viewmodels.PricingRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;

public class GiftCardQuickPayAdapter extends BaseQuickPayAdapter {
    private static final String GIFT_CARD_TERMS_URL = "https://www.airbnb.com/terms/gift_cards";
    private static final String TAG = GiftCardQuickPayAdapter.class.getSimpleName();
    private final QuickPayButtonSpacerModel_ buttonSpacerModel;
    private final PosterRowEpoxyModel_ cartPosterModel;
    private final GiftCardQuickPayClickListener clickListener;
    /* access modifiers changed from: private */
    public final Context context;
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
        public GiftCardQuickPayClickListener clickListener;
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

        public Builder giftCardQuickPayListener(GiftCardQuickPayClickListener clickListener2) {
            this.clickListener = clickListener2;
            return this;
        }

        public BaseQuickPayAdapter build() {
            return new GiftCardQuickPayAdapter(this);
        }
    }

    private GiftCardQuickPayAdapter(Builder builder) {
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
        this.clickListener = builder.clickListener;
    }

    public void setPriceQuote(BillPriceQuote priceQuote) {
        this.pricingRowModel.price(priceQuote.getPrice());
        notifyModelChanged(this.pricingRowModel);
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        if (PaymentUtils.isValidPaymentOption(paymentOption)) {
            this.paymentRowModel.title((CharSequence) paymentOption.getDisplayName(this.context));
            if (!paymentOption.isCvvVerified()) {
                this.paymentRowModel.rowDrawableRes(0);
                this.paymentRowModel.actionText((CharSequence) this.context.getString(C0880R.string.quick_pay_add_security_code));
                this.paymentRowModel.clickListener(GiftCardQuickPayAdapter$$Lambda$1.lambdaFactory$(this));
            } else {
                this.paymentRowModel.rowDrawableRes(C0880R.C0881drawable.n2_standard_row_right_caret_gray);
                this.paymentRowModel.actionText((CharSequence) null);
                this.paymentRowModel.clickListener(GiftCardQuickPayAdapter$$Lambda$2.lambdaFactory$(this));
            }
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
        String priceToPay = this.context.getString(C0880R.string.quick_pay_button_text, new Object[]{billPriceQuote.getPrice().getTotal().formattedForDisplay()});
        String termsText = this.context.getString(C0880R.string.quick_pay_gift_card_legal_copy_terms_with_link);
        String legalCopy = this.context.getString(C0880R.string.quick_pay_gift_card_legal_copy, new Object[]{priceToPay, termsText});
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(legalCopy);
        stringBuilder.append(" ");
        String fxCopy = billPriceQuote.getFxMessage();
        if (fxCopy != null) {
            stringBuilder.append(fxCopy);
        }
        this.fxRowModel.text(getClickableLegalAndFxCopy(stringBuilder.toString(), termsText));
        this.fxRowModel.movementMethod(LinkMovementMethod.getInstance());
        notifyModelChanged(this.fxRowModel);
    }

    private CharSequence getClickableLegalAndFxCopy(String legalAndFxCopy, String termsText) {
        SpannableString spannableString = new SpannableString(legalAndFxCopy);
        ClickableSpan clickableSpan = new ClickableSpan() {
            public void onClick(View widget) {
                GiftCardQuickPayAdapter.this.context.startActivity(WebViewIntentBuilder.newBuilder(GiftCardQuickPayAdapter.this.context, GiftCardQuickPayAdapter.GIFT_CARD_TERMS_URL).flags(268435456).toIntent());
            }
        };
        int indexOfTermsText = legalAndFxCopy.indexOf(termsText);
        spannableString.setSpan(clickableSpan, indexOfTermsText, termsText.length() + indexOfTermsText, 33);
        return spannableString;
    }
}
