package com.airbnb.android.lib.fragments.completeprofile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.utils.Strap;
import com.facebook.internal.AnalyticsEvents;

public class CompleteProfilePhotoSelectChildFragment extends CompleteProfileBaseFragment implements VerifiedIdStrapper {
    public static final String TAG = CompleteProfilePhotoSelectChildFragment.class.getSimpleName();

    public static CompleteProfilePhotoSelectChildFragment newInstance() {
        return new CompleteProfilePhotoSelectChildFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "Load fragment");
        View view = inflater.inflate(C0880R.layout.fragment_child_complete_profile_photo_select, container, false);
        ((CompleteProfileActivity) getActivity()).showProgressBar();
        ((Button) view.findViewById(C0880R.C0882id.complete_profile_take_photo_button)).setOnClickListener(CompleteProfilePhotoSelectChildFragment$$Lambda$1.lambdaFactory$(this));
        ((Button) view.findViewById(C0880R.C0882id.complete_profile_choose_photo_button)).setOnClickListener(CompleteProfilePhotoSelectChildFragment$$Lambda$2.lambdaFactory$(this));
        if (isVerifiedIdFlow() && savedInstanceState == null) {
            VerifiedIdAnalytics.trackPhotoStartView(getVerifiedIdAnalyticsStrap());
            VerifiedIdAnalytics.trackHealth(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, "start");
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(CompleteProfilePhotoSelectChildFragment completeProfilePhotoSelectChildFragment, View v) {
        if (completeProfilePhotoSelectChildFragment.isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhotoStartTakePhoto(completeProfilePhotoSelectChildFragment.getVerifiedIdAnalyticsStrap());
        }
        completeProfilePhotoSelectChildFragment.startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).setSource(1).create(v.getContext()), 1);
    }

    static /* synthetic */ void lambda$onCreateView$1(CompleteProfilePhotoSelectChildFragment completeProfilePhotoSelectChildFragment, View v) {
        if (completeProfilePhotoSelectChildFragment.isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhotoStartChoosePhoto(completeProfilePhotoSelectChildFragment.getVerifiedIdAnalyticsStrap());
        }
        completeProfilePhotoSelectChildFragment.startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).setSource(2).create(v.getContext()), 2);
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((CompleteProfileActivity) getActivity()).getReservationId());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
