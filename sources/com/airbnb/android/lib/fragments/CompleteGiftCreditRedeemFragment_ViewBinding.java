package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedEditTextContentCell;

public class CompleteGiftCreditRedeemFragment_ViewBinding implements Unbinder {
    private CompleteGiftCreditRedeemFragment target;
    private View view2131756220;
    private View view2131756221;

    public CompleteGiftCreditRedeemFragment_ViewBinding(final CompleteGiftCreditRedeemFragment target2, View source) {
        this.target = target2;
        target2.giftTotalBalanceCell = (GroupedEditTextContentCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.gc_total_balance_cell, "field 'giftTotalBalanceCell'", GroupedEditTextContentCell.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.gift_credit_redeemed_start_exploring_btn, "method 'onStartExploringButtonClick'");
        this.view2131756220 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStartExploringButtonClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.gift_credit_redeem_another_gift_card_btn, "method 'onRedeemAnotherButtonClick'");
        this.view2131756221 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onRedeemAnotherButtonClick();
            }
        });
    }

    public void unbind() {
        CompleteGiftCreditRedeemFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.giftTotalBalanceCell = null;
        this.view2131756220.setOnClickListener(null);
        this.view2131756220 = null;
        this.view2131756221.setOnClickListener(null);
        this.view2131756221 = null;
    }
}
