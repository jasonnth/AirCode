package com.airbnb.android.payout.create.controllers;

import android.content.Context;
import android.content.res.Resources;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.InfoRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.create.AddPayoutMethodUtils;
import com.airbnb.android.payout.models.PayoutInfoForm;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.utils.AirTextBuilder;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class ChoosePayoutMethodEpoxyController extends AirEpoxyController {
    private List<PayoutInfoForm> availablePayoutInfoFormTypes;
    private final Context context;
    DocumentMarqueeEpoxyModel_ documentMarqueeModel;
    private final Listener listener;
    EpoxyControllerLoadingModel_ loaderModel;
    private String payoutCountry;
    BasicRowEpoxyModel_ payoutMethodFeeRowModel;
    SimpleTextRowEpoxyModel_ timingAndFeeRowModel;

    public interface Listener {
        void onClickChangeCountry();

        void onClickPaymentMethodTypeRow(PayoutInfoForm payoutInfoForm, String str);
    }

    public ChoosePayoutMethodEpoxyController(Context context2, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        if (this.availablePayoutInfoFormTypes == null) {
            add((EpoxyModel<?>) this.loaderModel);
        } else if (isSingleMethod()) {
            buildForSinglePayoutMethod();
        } else {
            buildForMultiplePayoutMethods();
        }
    }

    private boolean isSingleMethod() {
        return this.availablePayoutInfoFormTypes.size() == 1;
    }

    public void updateAvailablePayoutMethods(String payoutCountry2, List<PayoutInfoForm> availablePayoutInfoFormTypes2) {
        this.payoutCountry = payoutCountry2;
        this.availablePayoutInfoFormTypes = availablePayoutInfoFormTypes2;
        requestModelBuild();
    }

    public void setLoadingState() {
        this.availablePayoutInfoFormTypes = null;
        requestModelBuild();
    }

    private void buildForSinglePayoutMethod() {
        PayoutInfoForm method = (PayoutInfoForm) this.availablePayoutInfoFormTypes.get(0);
        this.documentMarqueeModel.titleText((CharSequence) method.displayName()).captionText(buildMarqueeCaptionWithLink(this.availablePayoutInfoFormTypes.size()));
        this.timingAndFeeRowModel.large().text(AddPayoutMethodUtils.getPayoutMethodTimelinessText(this.context, method));
        this.payoutMethodFeeRowModel.showDivider(true).titleText(method.transactionFeeInfo());
    }

    private void buildForMultiplePayoutMethods() {
        boolean isSingleCurrency = true;
        this.documentMarqueeModel.titleRes(C7552R.string.add_payout_choose_payout_method_title).captionText(buildMarqueeCaptionWithLink(this.availablePayoutInfoFormTypes.size()));
        List<String> supportedCurrencies = AddPayoutMethodUtils.getSupportedCurrencies(this.availablePayoutInfoFormTypes);
        if (supportedCurrencies.size() != 1) {
            isSingleCurrency = false;
        }
        for (String currency : supportedCurrencies) {
            add((EpoxyModel<?>[]) (EpoxyModel[]) FluentIterable.from((Iterable<E>) this.availablePayoutInfoFormTypes).filter(ChoosePayoutMethodEpoxyController$$Lambda$1.lambdaFactory$(currency)).transform(ChoosePayoutMethodEpoxyController$$Lambda$2.lambdaFactory$(this, currency, isSingleCurrency)).toArray(InfoRowEpoxyModel_.class));
        }
    }

    private CharSequence buildMarqueeCaptionWithLink(int numPayoutMethodTypes) {
        AirTextBuilder builder = new AirTextBuilder(this.context);
        Resources resources = this.context.getResources();
        builder.append(resources.getQuantityString(C7552R.plurals.add_payout_choose_payout_method_in_country, numPayoutMethodTypes, new Object[]{this.payoutCountry}));
        if (!Trebuchet.launch(TrebuchetKeys.NEW_PAYOUT_FLOW_V1_ENABLED, true)) {
            builder.append(" ");
            builder.appendLink(resources.getString(C7552R.string.add_payout_choose_payout_method_country_link), ChoosePayoutMethodEpoxyController$$Lambda$3.lambdaFactory$(this));
        }
        return builder.build();
    }

    /* access modifiers changed from: private */
    public InfoRowEpoxyModel_ buildPayoutMethodRow(PayoutInfoForm payoutInfoForm, String currencyCode, boolean isSingleCurrency) {
        String string;
        InfoRowEpoxyModel_ infoRowEpoxyModel_ = new InfoRowEpoxyModel_();
        if (isSingleCurrency) {
            string = payoutInfoForm.displayName();
        } else {
            string = this.context.getResources().getString(C7552R.string.choose_payout_method_in_currency, new Object[]{payoutInfoForm.displayName(), currencyCode});
        }
        return infoRowEpoxyModel_.title(string).subtitle(getPayoutMethodRowSubtitle(payoutInfoForm)).clickListener(ChoosePayoutMethodEpoxyController$$Lambda$4.lambdaFactory$(this, payoutInfoForm, currencyCode)).m4836id((CharSequence) payoutInfoForm.hashCode() + currencyCode).showDivider(true);
    }

    private String getPayoutMethodRowSubtitle(PayoutInfoForm payoutInfoForm) {
        StringBuilder subtitleBuilder = new StringBuilder();
        String bullet = this.context.getResources().getString(C7552R.string.bullet_with_space);
        subtitleBuilder.append(bullet);
        subtitleBuilder.append(payoutInfoForm.timelinessInfo());
        subtitleBuilder.append("\n");
        subtitleBuilder.append(bullet);
        subtitleBuilder.append(payoutInfoForm.transactionFeeInfo());
        return subtitleBuilder.toString();
    }
}
