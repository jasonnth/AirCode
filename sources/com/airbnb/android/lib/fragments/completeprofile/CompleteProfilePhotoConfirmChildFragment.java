package com.airbnb.android.lib.fragments.completeprofile;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.lib.tasks.HaloImageScalingTask;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class CompleteProfilePhotoConfirmChildFragment extends CompleteProfileBaseFragment implements VerifiedIdStrapper {
    public static final String ARG_URI = "cropped_uri";
    @BindView
    HaloImageView photoPreview;

    public static CompleteProfilePhotoConfirmChildFragment newInstance(Uri uri) {
        CompleteProfilePhotoConfirmChildFragment f = new CompleteProfilePhotoConfirmChildFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.fragment_child_complete_profile_photo_confirm, container, false);
        bindViews(v);
        new HaloImageScalingTask((Uri) getArguments().getParcelable(ARG_URI), this.photoPreview).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        if (isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhotoApprovalView(getVerifiedIdAnalyticsStrap());
        }
        return v;
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((CompleteProfileActivity) getActivity()).getReservationId());
    }
}
