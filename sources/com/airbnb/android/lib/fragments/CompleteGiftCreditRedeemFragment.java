package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import com.airbnb.android.lib.views.GroupedEditTextContentCell;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import icepick.State;

public class CompleteGiftCreditRedeemFragment extends AirFragment {
    private static final String ARG_FORMATTED_GIFT_CREDIT_BALANCE = "formatted_gift_credit_balance";
    @State
    protected String formattedGiftCreditBalance;
    @BindView
    GroupedEditTextContentCell giftTotalBalanceCell;
    @State
    protected int redeemedGiftCreditAmount;

    public static CompleteGiftCreditRedeemFragment newInstance(String formattedGiftCreditBalance2) {
        return (CompleteGiftCreditRedeemFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CompleteGiftCreditRedeemFragment()).putString(ARG_FORMATTED_GIFT_CREDIT_BALANCE, formattedGiftCreditBalance2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(C0880R.layout.fragment_complete_gift_credit_redeem, container, false);
        bindViews(contentView);
        if (savedInstanceState == null) {
            this.formattedGiftCreditBalance = getArguments().getString(ARG_FORMATTED_GIFT_CREDIT_BALANCE);
        }
        this.giftTotalBalanceCell.setContent(this.formattedGiftCreditBalance);
        return contentView;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void onStartExploringButtonClick() {
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void onRedeemAnotherButtonClick() {
        ((GiftCardsActivity) getActivity()).startRedeemGiftCard();
    }
}
