package com.airbnb.android.lib.payments.quickpay.adapters;

import android.content.Context;
import android.text.SpannableString;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillPriceQuote.Link;
import com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.utils.SpannableUtils.UrlText;
import com.airbnb.android.core.viewcomponents.models.KickerMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentMethodEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PosterRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.QuickPayButtonSpacerModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.utils.PaymentImageUtils;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.lib.viewcomponents.viewmodels.ExpandableCollectionRowEpoxyModel_;
import com.airbnb.android.lib.viewcomponents.viewmodels.LinkableLegalTextModel_;
import com.airbnb.android.lib.viewcomponents.viewmodels.PricingRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class HomesQuickPayAdapter extends BaseQuickPayAdapter {
    private final QuickPayButtonSpacerModel_ buttonSpacerModel;
    private final LinkableLegalTextModel_ cancellationRefundPolicyModel;
    private final PosterRowEpoxyModel_ cartPosterModel;
    private final Context context;
    private final HomesQuickPayInstallmentsHelper installmentsHelper;
    private final LoadingRowEpoxyModel_ loadingRowModel;
    private final KickerMarqueeEpoxyModel_ marqueeModel;
    private final PaymentMethodEpoxyModel_ paymentRowModel;
    private final PricingRowEpoxyModel_ pricingRowModel;
    private final LinkableLegalTextModel_ termsAndFxRowModel;

    public static class Builder {
        /* access modifiers changed from: private */
        public QuickPayButtonSpacerModel_ buttonSpacerModel;
        /* access modifiers changed from: private */
        public LinkableLegalTextModel_ cancellationRefundPolicyModel;
        /* access modifiers changed from: private */
        public PosterRowEpoxyModel_ cartPosterModel;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public ExpandableCollectionRowEpoxyModel_ installmentsRowAboveModel;
        /* access modifiers changed from: private */
        public ExpandableCollectionRowEpoxyModel_ installmentsRowBelowModel;
        /* access modifiers changed from: private */
        public LoadingRowEpoxyModel_ loadingRowModel;
        /* access modifiers changed from: private */
        public KickerMarqueeEpoxyModel_ marqueeModel;
        /* access modifiers changed from: private */
        public PaymentMethodEpoxyModel_ paymentRowModel;
        /* access modifiers changed from: private */
        public PricingRowEpoxyModel_ pricingRowModel;
        /* access modifiers changed from: private */
        public LinkableLegalTextModel_ termsAndFxRowModel;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder marqueeRowModel(KickerMarqueeEpoxyModel_ model) {
            this.marqueeModel = model;
            return this;
        }

        public Builder loadingRowModel(LoadingRowEpoxyModel_ model) {
            this.loadingRowModel = model;
            return this;
        }

        public Builder cartPosterRowModel(PosterRowEpoxyModel_ model) {
            this.cartPosterModel = model;
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

        public Builder installmentsRowAboveModel(ExpandableCollectionRowEpoxyModel_ model) {
            this.installmentsRowAboveModel = model;
            return this;
        }

        public Builder installmentsRowBelowModel(ExpandableCollectionRowEpoxyModel_ model) {
            this.installmentsRowBelowModel = model;
            return this;
        }

        public Builder cancellationRefundPolicyModel(LinkableLegalTextModel_ model_) {
            this.cancellationRefundPolicyModel = model_;
            return this;
        }

        public Builder termsAndFxRowModel(LinkableLegalTextModel_ model_) {
            this.termsAndFxRowModel = model_;
            return this;
        }

        public Builder buttonSpacerRowModel(QuickPayButtonSpacerModel_ model) {
            this.buttonSpacerModel = model;
            return this;
        }

        public HomesQuickPayAdapter build() {
            return new HomesQuickPayAdapter(this);
        }
    }

    private HomesQuickPayAdapter(Builder builder) {
        super(true);
        this.installmentsHelper = new HomesQuickPayInstallmentsHelper();
        enableDiffing();
        this.context = builder.context;
        this.marqueeModel = builder.marqueeModel;
        this.loadingRowModel = builder.loadingRowModel;
        this.cartPosterModel = builder.cartPosterModel;
        this.paymentRowModel = builder.paymentRowModel;
        this.pricingRowModel = builder.pricingRowModel;
        this.installmentsHelper.setInstallmentsRowAboveModel(builder.installmentsRowAboveModel);
        this.installmentsHelper.setInstallmentsRowBelowModel(builder.installmentsRowBelowModel);
        this.cancellationRefundPolicyModel = builder.cancellationRefundPolicyModel;
        this.termsAndFxRowModel = builder.termsAndFxRowModel;
        this.buttonSpacerModel = builder.buttonSpacerModel;
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.loadingRowModel, this.cartPosterModel, this.paymentRowModel, builder.installmentsRowAboveModel, this.pricingRowModel, builder.installmentsRowBelowModel, this.cancellationRefundPolicyModel, this.termsAndFxRowModel, this.buttonSpacerModel});
        hideModels((EpoxyModel<?>[]) new EpoxyModel[]{this.loadingRowModel, builder.installmentsRowAboveModel, builder.installmentsRowBelowModel});
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
        this.pricingRowModel.couponCodeTitle(price.hasCouponCode() ? this.context.getString(C0880R.string.quick_pay_remove_coupon_text) : this.context.getString(C0880R.string.quick_pay_add_coupon_text));
        notifyModelChanged(this.installmentsHelper.updateInstallmentsModel(priceQuote));
        notifyModelChanged(this.pricingRowModel);
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        if (PaymentUtils.isValidPaymentOption(paymentOption)) {
            updatePaymentRow(paymentOption);
        }
    }

    private void updatePaymentRow(PaymentOption paymentOption) {
        this.paymentRowModel.title(paymentOption.getDisplayName(this.context));
        this.paymentRowModel.drawableRes(PaymentImageUtils.getPaymentImageRes(paymentOption));
        notifyModelChanged(this.paymentRowModel);
    }

    public void toggleLoadingState(boolean showLoading) {
        if (showLoading) {
            showModel(this.loadingRowModel);
            hideModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartPosterModel, this.paymentRowModel, this.pricingRowModel, this.cancellationRefundPolicyModel, this.termsAndFxRowModel});
        } else {
            hideModel(this.loadingRowModel);
            showModels((EpoxyModel<?>[]) new EpoxyModel[]{this.cartPosterModel, this.paymentRowModel, this.pricingRowModel, this.cancellationRefundPolicyModel, this.termsAndFxRowModel});
        }
        toggleInstallmentsLoadingState(showLoading);
    }

    private void toggleInstallmentsLoadingState(boolean shouldShowLoadingState) {
        ExpandableCollectionRowEpoxyModel_ model = this.installmentsHelper.getVisibleRow();
        if (model == null) {
            return;
        }
        if (shouldShowLoadingState) {
            hideModel(model);
        } else {
            showModel(model);
        }
    }

    public void updateLegalAndFxRow(BillPriceQuote billPriceQuote) {
        setCancellationRefundPolicy(billPriceQuote);
        setTermsAndFx(billPriceQuote);
        notifyModelsChanged();
    }

    private void setCancellationRefundPolicy(BillPriceQuote billPriceQuote) {
        LinkableLegalText refundPolicy = billPriceQuote.getCancellationRefundPolicy();
        if (refundPolicy == null) {
            removeModel(this.cancellationRefundPolicyModel);
            return;
        }
        this.cancellationRefundPolicyModel.termsTitle(getLinkableTextTitle(refundPolicy));
        this.cancellationRefundPolicyModel.termsBody(getLinkableTextBody(refundPolicy));
    }

    private void setTermsAndFx(BillPriceQuote billPriceQuote) {
        String fxMessage = billPriceQuote.getFxMessage();
        LinkableLegalText termsAndConditions = billPriceQuote.getTermsAndConditions();
        if (termsAndConditions == null && fxMessage == null) {
            hideModel(this.termsAndFxRowModel);
            return;
        }
        this.termsAndFxRowModel.termsTitle(getLinkableTextTitle(termsAndConditions));
        this.termsAndFxRowModel.termsBody(getLinkableTextBody(termsAndConditions));
        this.termsAndFxRowModel.fxBody(fxMessage);
        showModel(this.termsAndFxRowModel);
    }

    private String getLinkableTextTitle(LinkableLegalText linkableLegalText) {
        if (linkableLegalText == null) {
            return null;
        }
        return linkableLegalText.title();
    }

    private SpannableString getLinkableTextBody(LinkableLegalText linkableLegalText) {
        if (linkableLegalText == null) {
            return null;
        }
        return SpannableUtils.createClickableUrls(this.context, linkableLegalText.text(), convertLinksToUrlTexts(linkableLegalText));
    }

    private List<UrlText> convertLinksToUrlTexts(LinkableLegalText linkableLegalText) {
        return FluentIterable.from((Iterable<E>) linkableLegalText.links()).transform(HomesQuickPayAdapter$$Lambda$1.lambdaFactory$(this)).toList();
    }

    static /* synthetic */ UrlText lambda$convertLinksToUrlTexts$0(HomesQuickPayAdapter homesQuickPayAdapter, Link link) {
        return new UrlText(link.text(), homesQuickPayAdapter.context.getString(C0880R.string.airbnb_base_url) + link.url());
    }
}
