package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;

public class GiftCardRedemptionAddCreditCardFragment extends AirFragment {
    @BindView
    AirButton nextButton;

    public static GiftCardRedemptionAddCreditCardFragment newInstance() {
        return new GiftCardRedemptionAddCreditCardFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_gift_card_redemption_add_credit_card, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        getActivity().startActivityForResult(CreditCardActivity.intentForGiftCardRedeem(getActivity(), "US"), CreditCardActivity.REQUEST_CODE_ADD_CARD);
    }

    public void displayLoader() {
        this.nextButton.setState(State.Loading);
    }

    public void hideLoader() {
        this.nextButton.setState(State.Normal);
    }
}
