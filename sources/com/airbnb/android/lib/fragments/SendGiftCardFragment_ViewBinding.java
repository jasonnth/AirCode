package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedCell;
import com.airbnb.android.lib.views.GroupedEditTextContentCell;
import com.airbnb.p027n2.collections.Carousel;

public class SendGiftCardFragment_ViewBinding implements Unbinder {
    private SendGiftCardFragment target;
    private View view2131755019;
    private View view2131755020;
    private View view2131755021;
    private View view2131755022;
    private View view2131756747;
    private View view2131756748;
    private View view2131756749;
    private View view2131756750;
    private View view2131756752;

    public SendGiftCardFragment_ViewBinding(final SendGiftCardFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.et_gift_card_recipient_name_input_field, "field 'nameInput' and method 'onRecipientDetailsFocusChanged'");
        target2.nameInput = (EditText) Utils.castView(view, C0880R.C0882id.et_gift_card_recipient_name_input_field, "field 'nameInput'", EditText.class);
        this.view2131756749 = view;
        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View p0, boolean p1) {
                target2.onRecipientDetailsFocusChanged(p1);
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.et_gift_card_recipient_email_input_field, "field 'emailInput' and method 'onRecipientDetailsFocusChanged'");
        target2.emailInput = (EditText) Utils.castView(view2, C0880R.C0882id.et_gift_card_recipient_email_input_field, "field 'emailInput'", EditText.class);
        this.view2131756750 = view2;
        view2.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View p0, boolean p1) {
                target2.onRecipientDetailsFocusChanged(p1);
            }
        });
        target2.messageInput = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.et_gift_card_recipient_message_input_field, "field 'messageInput'", EditText.class);
        target2.giftCardsCarousel = (Carousel) Utils.findRequiredViewAsType(source, C0880R.C0882id.gift_card_view_pager, "field 'giftCardsCarousel'", Carousel.class);
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btn_gift_card_checkout_continue, "field 'giftCardCheckoutButton' and method 'createGiftCard'");
        target2.giftCardCheckoutButton = (Button) Utils.castView(view3, C0880R.C0882id.btn_gift_card_checkout_continue, "field 'giftCardCheckoutButton'", Button.class);
        this.view2131756752 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.createGiftCard();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.gc_select_amount_cell, "field 'selectAmountGroupedCell' and method 'toggleGiftAmountCellVisibility'");
        target2.selectAmountGroupedCell = (GroupedEditTextContentCell) Utils.castView(view4, C0880R.C0882id.gc_select_amount_cell, "field 'selectAmountGroupedCell'", GroupedEditTextContentCell.class);
        this.view2131756747 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.toggleGiftAmountCellVisibility();
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.gc_amount_1_cell, "field 'giftAmount1Cell' and method 'displaySelectedGiftAmount'");
        target2.giftAmount1Cell = (GroupedCell) Utils.castView(view5, C0880R.C0882id.gc_amount_1_cell, "field 'giftAmount1Cell'", GroupedCell.class);
        this.view2131755019 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.displaySelectedGiftAmount(p0);
            }
        });
        View view6 = Utils.findRequiredView(source, C0880R.C0882id.gc_amount_2_cell, "field 'giftAmount2Cell' and method 'displaySelectedGiftAmount'");
        target2.giftAmount2Cell = (GroupedCell) Utils.castView(view6, C0880R.C0882id.gc_amount_2_cell, "field 'giftAmount2Cell'", GroupedCell.class);
        this.view2131755020 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.displaySelectedGiftAmount(p0);
            }
        });
        View view7 = Utils.findRequiredView(source, C0880R.C0882id.gc_amount_3_cell, "field 'giftAmount3Cell' and method 'displaySelectedGiftAmount'");
        target2.giftAmount3Cell = (GroupedCell) Utils.castView(view7, C0880R.C0882id.gc_amount_3_cell, "field 'giftAmount3Cell'", GroupedCell.class);
        this.view2131755021 = view7;
        view7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.displaySelectedGiftAmount(p0);
            }
        });
        View view8 = Utils.findRequiredView(source, C0880R.C0882id.gc_amount_4_cell, "field 'giftAmount4Cell' and method 'displaySelectedGiftAmount'");
        target2.giftAmount4Cell = (GroupedCell) Utils.castView(view8, C0880R.C0882id.gc_amount_4_cell, "field 'giftAmount4Cell'", GroupedCell.class);
        this.view2131755022 = view8;
        view8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.displaySelectedGiftAmount(p0);
            }
        });
        View view9 = Utils.findRequiredView(source, C0880R.C0882id.gc_amount_other_cell, "field 'giftAmountOtherCell' and method 'inputOtherGiftAmount'");
        target2.giftAmountOtherCell = (GroupedEditTextContentCell) Utils.castView(view9, C0880R.C0882id.gc_amount_other_cell, "field 'giftAmountOtherCell'", GroupedEditTextContentCell.class);
        this.view2131756748 = view9;
        view9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.inputOtherGiftAmount();
            }
        });
    }

    public void unbind() {
        SendGiftCardFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.nameInput = null;
        target2.emailInput = null;
        target2.messageInput = null;
        target2.giftCardsCarousel = null;
        target2.giftCardCheckoutButton = null;
        target2.selectAmountGroupedCell = null;
        target2.giftAmount1Cell = null;
        target2.giftAmount2Cell = null;
        target2.giftAmount3Cell = null;
        target2.giftAmount4Cell = null;
        target2.giftAmountOtherCell = null;
        this.view2131756749.setOnFocusChangeListener(null);
        this.view2131756749 = null;
        this.view2131756750.setOnFocusChangeListener(null);
        this.view2131756750 = null;
        this.view2131756752.setOnClickListener(null);
        this.view2131756752 = null;
        this.view2131756747.setOnClickListener(null);
        this.view2131756747 = null;
        this.view2131755019.setOnClickListener(null);
        this.view2131755019 = null;
        this.view2131755020.setOnClickListener(null);
        this.view2131755020 = null;
        this.view2131755021.setOnClickListener(null);
        this.view2131755021 = null;
        this.view2131755022.setOnClickListener(null);
        this.view2131755022 = null;
        this.view2131756748.setOnClickListener(null);
        this.view2131756748 = null;
    }
}
