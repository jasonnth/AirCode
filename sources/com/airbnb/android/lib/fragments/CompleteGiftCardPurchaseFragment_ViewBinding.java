package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class CompleteGiftCardPurchaseFragment_ViewBinding implements Unbinder {
    private CompleteGiftCardPurchaseFragment target;
    private View view2131756217;
    private View view2131756218;

    public CompleteGiftCardPurchaseFragment_ViewBinding(final CompleteGiftCardPurchaseFragment target2, View source) {
        this.target = target2;
        target2.headerText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.gift_credit_sent_header_text, "field 'headerText'", TextView.class);
        target2.subHeaderText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.gift_credit_sent_header_subtext, "field 'subHeaderText'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.gift_credit_start_exploring_btn, "method 'onStartExploringButtonClick'");
        this.view2131756217 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStartExploringButtonClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.gift_credit_send_another_gift_card_btn, "method 'onSendAnotherGiftCardButtonClick'");
        this.view2131756218 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSendAnotherGiftCardButtonClick();
            }
        });
    }

    public void unbind() {
        CompleteGiftCardPurchaseFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.headerText = null;
        target2.subHeaderText = null;
        this.view2131756217.setOnClickListener(null);
        this.view2131756217 = null;
        this.view2131756218.setOnClickListener(null);
        this.view2131756218 = null;
    }
}
