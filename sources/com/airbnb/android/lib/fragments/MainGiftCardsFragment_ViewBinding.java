package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class MainGiftCardsFragment_ViewBinding implements Unbinder {
    private MainGiftCardsFragment target;
    private View view2131756332;
    private View view2131756333;

    public MainGiftCardsFragment_ViewBinding(final MainGiftCardsFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_send_gift_credit, "field 'sendGiftCredit' and method 'onSendCardClick'");
        target2.sendGiftCredit = (Button) Utils.castView(view, C0880R.C0882id.btn_send_gift_credit, "field 'sendGiftCredit'", Button.class);
        this.view2131756332 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSendCardClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_redeem_gift_credit, "field 'redeemGiftCredit' and method 'redeemGiftCard'");
        target2.redeemGiftCredit = (Button) Utils.castView(view2, C0880R.C0882id.btn_redeem_gift_credit, "field 'redeemGiftCredit'", Button.class);
        this.view2131756333 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.redeemGiftCard();
            }
        });
    }

    public void unbind() {
        MainGiftCardsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sendGiftCredit = null;
        target2.redeemGiftCredit = null;
        this.view2131756332.setOnClickListener(null);
        this.view2131756332 = null;
        this.view2131756333.setOnClickListener(null);
        this.view2131756333 = null;
    }
}
