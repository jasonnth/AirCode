package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;

public class AccountVerificationWelcomeFragment extends AirFragment {
    @BindView
    Button mBeginButton;

    public static Fragment newInstance() {
        return new AccountVerificationWelcomeFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_account_verification_welcome, container, false);
        bindViews(view);
        this.mBeginButton.setOnClickListener(AccountVerificationWelcomeFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }
}
