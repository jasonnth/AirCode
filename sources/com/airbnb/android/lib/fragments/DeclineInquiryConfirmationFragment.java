package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;

public class DeclineInquiryConfirmationFragment extends AirFragment {
    public static final String ARG_USER_NAME = "user_name";
    @BindView
    SheetMarquee marquee;
    String userName;

    public static Intent newIntent(Context context, String userName2) {
        Bundle bundle = new Bundle();
        bundle.putString("user_name", userName2);
        Intent intent = AutoAirActivity.intentForFragment(context, DeclineInquiryConfirmationFragment.class, bundle);
        intent.putExtra(AutoAirActivity.EXTRA_ACTION_BAR, false);
        return intent;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_decline_inquiry_confirmation, container, false);
        bindViews(view);
        this.userName = getArguments().getString("user_name");
        this.marquee.setTitle(getString(C0880R.string.decline_inquiry_confirmation_title, this.userName));
        return view;
    }

    @OnClick
    public void onClickOkay() {
        getActivity().finish();
    }
}
