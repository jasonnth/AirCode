package com.airbnb.android.lib.fragments.verifiedid;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.lib.tasks.LocalBitmapForDisplayScalingTask;
import com.airbnb.android.utils.Strap;

public class OfficialIdPhotoConfirmationFragment extends AirFragment implements VerifiedIdStrapper {
    private LocalBitmapForDisplayScalingTask mBmTask;
    /* access modifiers changed from: private */
    public ImageView mIdPhotoPreview;

    public static OfficialIdPhotoConfirmationFragment newInstance() {
        return new OfficialIdPhotoConfirmationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_official_id_photo_confirmation, container, false);
        this.mIdPhotoPreview = (ImageView) view.findViewById(C0880R.C0882id.photo_preview);
        if (getIsFrontId()) {
            VerifiedIdAnalytics.trackOfflinePhotoFrontConfirmView(getVerifiedIdAnalyticsStrap());
        } else {
            VerifiedIdAnalytics.trackOfflinePhotoBackConfirmView(getVerifiedIdAnalyticsStrap());
        }
        return view;
    }

    @TargetApi(11)
    public void onResume() {
        String uriString;
        super.onResume();
        OfficialIdActivity activity = (OfficialIdActivity) getActivity();
        activity.showConfirmationButtons(true);
        String idFrontUriString = activity.getIdFrontUriString();
        String idBackUriString = activity.getIdBackUriString();
        this.mBmTask = new LocalBitmapForDisplayScalingTask() {
            /* access modifiers changed from: protected */
            public void onPostExecute(Bitmap result) {
                if (OfficialIdPhotoConfirmationFragment.this.getActivity() != null) {
                    OfficialIdPhotoConfirmationFragment.this.mIdPhotoPreview.setImageBitmap(result);
                }
            }
        };
        if (idBackUriString != null) {
            uriString = idBackUriString;
        } else {
            uriString = idFrontUriString;
        }
        this.mBmTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{uriString});
    }

    public void onPause() {
        super.onPause();
        ((OfficialIdActivity) getActivity()).showConfirmationButtons(false);
        if (this.mBmTask != null && !this.mBmTask.isCancelled()) {
            this.mBmTask.cancel(true);
            this.mBmTask = null;
        }
        this.mIdPhotoPreview.setImageBitmap(null);
    }

    private boolean getIsFrontId() {
        return ((OfficialIdActivity) getActivity()).isCapturingFrontId();
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((OfficialIdActivity) getActivity()).getReservationId());
    }
}
