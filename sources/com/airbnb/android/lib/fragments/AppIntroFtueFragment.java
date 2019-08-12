package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.C0880R;

public class AppIntroFtueFragment extends ViewPagerFtueFragment {
    public static AppIntroFtueFragment newInstance(Context context, int titleRes, int subtitleRes, boolean isFirstPage, int stickyButtonColorRes) {
        String subtitle = null;
        String title = titleRes == 0 ? null : context.getString(titleRes);
        if (subtitleRes != 0) {
            subtitle = context.getString(subtitleRes);
        }
        Bundle args = makeArgs(title, subtitle, isFirstPage, stickyButtonColorRes);
        AppIntroFtueFragment fragment = new AppIntroFtueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (MiscUtils.isTabletScreen(getActivity())) {
            Button signInButton = (Button) ButterKnife.findById(view, C0880R.C0882id.ftue_button);
            signInButton.setVisibility(0);
            signInButton.setText(C0880R.string.sign_in_title);
            signInButton.setOnClickListener(AppIntroFtueFragment$$Lambda$1.lambdaFactory$(this));
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(AppIntroFtueFragment appIntroFtueFragment, View v) {
        appIntroFtueFragment.startActivity(LoginActivityIntents.intent(appIntroFtueFragment.getActivity()));
        appIntroFtueFragment.getActivity().finish();
    }
}
