package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.lib.views.LinearListView;

public class VerificationsFragment extends AirFragment {
    public static final String ARG_USER = "user";
    @BindView
    TextView mNoVerificationsText;
    @BindView
    LinearListView mVerificationsListView;
    @BindView
    TextView mVerifyYourIdentity;
    @BindView
    TextView mVerifyYourIdentityInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_verifications, container, false);
        bindViews(view);
        User user = (User) getArguments().getParcelable("user");
        if (user.isVerifiedId()) {
            this.mVerifyYourIdentity.setVisibility(8);
            this.mVerifyYourIdentityInfo.setVisibility(8);
        } else if (this.mAccountManager.isCurrentUser(user.getId())) {
            this.mVerifyYourIdentity.setVisibility(0);
            this.mVerifyYourIdentityInfo.setVisibility(0);
        }
        if (user.hasVerifications()) {
            this.mVerificationsListView.setAdapter(new VerificationsAdapter(getActivity(), user));
        } else {
            this.mVerificationsListView.setVisibility(8);
            this.mNoVerificationsText.setVisibility(0);
        }
        return view;
    }

    @OnClick
    public void onClickVerifyYourIdentity() {
        startActivity(VerifiedIdActivityIntents.intentForVerifiedId(getActivity()));
    }
}
