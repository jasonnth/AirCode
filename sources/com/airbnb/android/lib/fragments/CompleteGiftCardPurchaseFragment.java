package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class CompleteGiftCardPurchaseFragment extends AirFragment {
    private static final String SENT_GIFT_CREDIT_AMOUNT = "sent_gift_credit_amount";
    private static final String SENT_GIFT_CREDIT_TO_USER_NAME = "sent_gift_credit_to_user_name";
    private static final String SENT_GIFT_CREIDT_TO_USER_EMAIL = "sent_gift_credit_to_user_email";
    @BindView
    TextView headerText;
    @BindView
    TextView subHeaderText;

    public static CompleteGiftCardPurchaseFragment newInstance(String name, String email, int purchaseGiftCreditAmount) {
        return (CompleteGiftCardPurchaseFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CompleteGiftCardPurchaseFragment()).putInt(SENT_GIFT_CREDIT_AMOUNT, purchaseGiftCreditAmount)).putString(SENT_GIFT_CREDIT_TO_USER_NAME, name)).putString(SENT_GIFT_CREIDT_TO_USER_EMAIL, email)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(C0880R.layout.fragment_complete_gift_card_purchase, container, false);
        bindViews(contentView);
        formatGiftCreditSentTexts();
        return contentView;
    }

    private void formatGiftCreditSentTexts() {
        Bundle args = getArguments();
        String formattedAmount = this.mCurrencyHelper.formatNativeCurrency((double) args.getInt(SENT_GIFT_CREDIT_AMOUNT), true);
        String userName = args.getString(SENT_GIFT_CREDIT_TO_USER_NAME);
        String userEmail = args.getString(SENT_GIFT_CREIDT_TO_USER_EMAIL);
        this.headerText.setText(getString(C0880R.string.gift_credit_sent_to_user_header, userName));
        this.subHeaderText.setText(getString(C0880R.string.gift_credit_sent_subtext, formattedAmount, userEmail));
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void onStartExploringButtonClick() {
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void onSendAnotherGiftCardButtonClick() {
        ((GiftCardsActivity) getActivity()).startSendGiftCard();
    }
}
