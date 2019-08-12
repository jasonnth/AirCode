package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class RedeemGiftCardFragment_ViewBinding implements Unbinder {
    private RedeemGiftCardFragment target;
    private View view2131756672;

    public RedeemGiftCardFragment_ViewBinding(final RedeemGiftCardFragment target2, View source) {
        this.target = target2;
        target2.giftRedeemImg = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.iv_gift_card_redeem_img, "field 'giftRedeemImg'", AirImageView.class);
        target2.giftCardCodeInput = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.et_gift_card_code_input_field, "field 'giftCardCodeInput'", EditText.class);
        target2.giftCardPinInput = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.et_gift_card_pin_input_field, "field 'giftCardPinInput'", EditText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_gift_card_redeem_continue, "field 'redeemContinueBtn' and method 'onGiftCardRedeemContinue'");
        target2.redeemContinueBtn = (StickyButton) Utils.castView(view, C0880R.C0882id.btn_gift_card_redeem_continue, "field 'redeemContinueBtn'", StickyButton.class);
        this.view2131756672 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onGiftCardRedeemContinue();
            }
        });
    }

    public void unbind() {
        RedeemGiftCardFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.giftRedeemImg = null;
        target2.giftCardCodeInput = null;
        target2.giftCardPinInput = null;
        target2.redeemContinueBtn = null;
        this.view2131756672.setOnClickListener(null);
        this.view2131756672 = null;
    }
}
