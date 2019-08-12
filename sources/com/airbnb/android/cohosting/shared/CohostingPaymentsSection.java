package com.airbnb.android.cohosting.shared;

import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingPaymentsBaseEpoxyController;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.components.SwitchStyle;
import java.util.Currency;

public class CohostingPaymentsSection {

    public interface Listener {
        void changeFixedAmount(Integer num);

        void changeMinimumFee(Integer num);

        void changePercent(Integer num);

        void choosePaymentType();

        void enableCleaningFee(boolean z);

        void onShareEarningsToggled(boolean z);
    }

    public static void addPaymentSectionToEpoxyController(CohostingPaymentsBaseEpoxyController epoxyController, CohostingPaymentSettings data) {
        boolean z;
        boolean z2;
        boolean enableShareEarnings = data.shareEarnings();
        FeeType feeType = data.feeType();
        new SwitchRowEpoxyModel_().m5676id((CharSequence) "share_earnings_row").titleRes(C5658R.string.cohosting_share_earnings_text).style(SwitchStyle.Filled).checked(enableShareEarnings).updateModelWithCheckedState(false).checkedChangeListener(CohostingPaymentsSection$$Lambda$1.lambdaFactory$(epoxyController)).showDivider(true).addTo(epoxyController);
        new StandardRowEpoxyModel_().m5604id((CharSequence) "fee_type_row").titleRes(feeType == FeeType.Percent ? C5658R.string.cohosting_payment_type_percentage_title : C5658R.string.cohosting_payment_type_fixed_amount_title).disclosure().clickListener(CohostingPaymentsSection$$Lambda$2.lambdaFactory$(epoxyController)).showDivider(true).addIf(enableShareEarnings, (EpoxyController) epoxyController);
        InlineFormattedIntegerInputRowEpoxyModel_ inputAmount = InlineFormattedIntegerInputRowEpoxyModel_.forIntegerPercentage().m4872id((CharSequence) "percentage_row").titleRes(C5658R.string.cohosting_invite_a_friend_percent_row_fee_option).inputAmount(Integer.valueOf(data.percentage()));
        epoxyController.getClass();
        inputAmount.amountChangedListener(CohostingPaymentsSection$$Lambda$3.lambdaFactory$(epoxyController)).updateModelData(false).addIf(enableShareEarnings && feeType == FeeType.Percent, (EpoxyController) epoxyController);
        InlineFormattedIntegerInputRowEpoxyModel_ inputAmount2 = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(Currency.getInstance(data.amountCurrency())).m4872id((CharSequence) "minimum_fee_row").titleRes(C5658R.string.cohosting_payment_type_min_fee_title).subTitleRes(C5658R.string.cohosting_payment_type_min_fee_subtitle).inputAmount(Integer.valueOf(data.minimumFee()));
        epoxyController.getClass();
        InlineFormattedIntegerInputRowEpoxyModel_ updateModelData = inputAmount2.amountChangedListener(CohostingPaymentsSection$$Lambda$4.lambdaFactory$(epoxyController)).updateModelData(false);
        if (!enableShareEarnings || feeType != FeeType.Percent || data.percentage() <= 0) {
            z = false;
        } else {
            z = true;
        }
        updateModelData.addIf(z, (EpoxyController) epoxyController);
        InlineFormattedIntegerInputRowEpoxyModel_ inputAmount3 = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(Currency.getInstance(data.amountCurrency())).m4872id((CharSequence) "fixed_fee_row").titleRes(C5658R.string.cohosting_invite_a_friend_percent_row_fee_option).inputAmount(Integer.valueOf(data.fixedAmount()));
        epoxyController.getClass();
        InlineFormattedIntegerInputRowEpoxyModel_ updateModelData2 = inputAmount3.amountChangedListener(CohostingPaymentsSection$$Lambda$5.lambdaFactory$(epoxyController)).updateModelData(false);
        if (!enableShareEarnings || feeType != FeeType.FixedAmount) {
            z2 = false;
        } else {
            z2 = true;
        }
        updateModelData2.addIf(z2, (EpoxyController) epoxyController);
        new SwitchRowEpoxyModel_().m5676id((CharSequence) "switch_row").titleRes(C5658R.string.cohosting_invite_a_friend_switch_row).style(SwitchStyle.Filled).checked(data.includeCleaningFee()).updateModelWithCheckedState(false).checkedChangeListener(CohostingPaymentsSection$$Lambda$6.lambdaFactory$(epoxyController)).showDivider(true).addTo(epoxyController);
    }
}
