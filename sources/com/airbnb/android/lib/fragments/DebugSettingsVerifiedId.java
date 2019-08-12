package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.DebugSettings.DebugVerification;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedCheck;

public class DebugSettingsVerifiedId extends AirFragment {
    private DebugSettings mDebugSettings;
    @BindView
    GroupedCheck mEmailGroupedCheck;
    @BindView
    GroupedCheck mOfflineGroupedCheck;
    @BindView
    GroupedCheck mOnlineGroupedCheck;
    @BindView
    GroupedCheck mPhoneGroupedCheck;
    @BindView
    GroupedCheck mProfilePictureGroupedCheck;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_debug_settings_verified_id, container, false);
        bindViews(view);
        this.mDebugSettings = AirbnbApplication.instance(getContext()).component().debugSettings();
        this.mProfilePictureGroupedCheck.setOnCheckedChangeListener(DebugSettingsVerifiedId$$Lambda$1.lambdaFactory$(this));
        this.mProfilePictureGroupedCheck.setChecked(this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.PROFILE_PICTURE));
        this.mEmailGroupedCheck.setOnCheckedChangeListener(DebugSettingsVerifiedId$$Lambda$2.lambdaFactory$(this));
        this.mEmailGroupedCheck.setChecked(this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.EMAIL));
        this.mPhoneGroupedCheck.setOnCheckedChangeListener(DebugSettingsVerifiedId$$Lambda$3.lambdaFactory$(this));
        this.mPhoneGroupedCheck.setChecked(this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.PHONE));
        this.mOfflineGroupedCheck.setOnCheckedChangeListener(DebugSettingsVerifiedId$$Lambda$4.lambdaFactory$(this));
        this.mOfflineGroupedCheck.setChecked(this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.OFFLINE_ID));
        this.mOnlineGroupedCheck.setOnCheckedChangeListener(DebugSettingsVerifiedId$$Lambda$5.lambdaFactory$(this));
        this.mOnlineGroupedCheck.setChecked(this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.ONLINE_ID));
        return view;
    }
}
