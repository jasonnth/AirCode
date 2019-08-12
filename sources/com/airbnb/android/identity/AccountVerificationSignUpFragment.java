package com.airbnb.android.identity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;

public class AccountVerificationSignUpFragment extends AirFragment {
    public static AccountVerificationSignUpFragment newInstance() {
        return new AccountVerificationSignUpFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_sign_up, container, false);
        bindViews(view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationSignUpStart;
    }

    @OnClick
    public void onProvideIdClick() {
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "provide_id");
        ((ProvideIdListener) getActivity()).onProvideIdClick();
    }

    @OnClick
    public void onCancel() {
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), BaseAnalytics.CANCEL);
        ((ProvideIdListener) getActivity()).onCancelClick();
    }
}
