package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.core.fragments.AirFragment;

public class QuickPayGiftCardLandingFragment extends AirFragment {
    public static QuickPayGiftCardLandingFragment newInstance() {
        return new QuickPayGiftCardLandingFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_quick_pay_gift_card_landing, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        ((LegacyAddPaymentMethodActivity) getActivity()).doneWithGiftCardLanding();
    }
}
