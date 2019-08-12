package com.airbnb.android.lib.fragments.verifiedid;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.Strap;

public class OfficialIdPhotoSelectionFragment extends Fragment implements VerifiedIdStrapper {
    public static final int MAX_IMAGE_SIZE = 2000;
    private static final int OFFICIAL_ID_CHOOSE_PHOTO_BACK_REQUEST = 39005;
    private static final int OFFICIAL_ID_CHOOSE_PHOTO_FRONT_REQUEST = 39004;
    private static final int OFFICIAL_ID_TAKE_PHOTO_BACK_REQUEST = 39003;
    private static final int OFFICIAL_ID_TAKE_PHOTO_FRONT_REQUEST = 39002;
    private static final String TAG = OfficialIdPhotoSelectionFragment.class.getSimpleName();
    ImageUtils mImageUtils;

    public static OfficialIdPhotoSelectionFragment newInstance() {
        return new OfficialIdPhotoSelectionFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String instructions;
        String takePhotoButtonText;
        View view = inflater.inflate(C0880R.layout.fragment_official_id_photo_selection, container, false);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        OfficialIdActivity activity = (OfficialIdActivity) getActivity();
        TextView photoInstructions = (TextView) view.findViewById(C0880R.C0882id.official_id_offline_photo_instructions);
        if (!getIsFrontId()) {
            instructions = getString(C0880R.string.verified_id_offline_photo_instructions_back);
        } else if (OfficialIdActivity.ID_TYPE.equals(activity.getOfficialIdType())) {
            instructions = getString(C0880R.string.verified_id_offline_photo_instructions_government_id);
        } else {
            instructions = getString(C0880R.string.verified_id_offline_photo_instructions_front);
        }
        photoInstructions.setText(instructions);
        Button takePhotoButton = (Button) view.findViewById(C0880R.C0882id.official_id_take_photo_button);
        takePhotoButton.setOnClickListener(OfficialIdPhotoSelectionFragment$$Lambda$1.lambdaFactory$(this));
        if (getIsFrontId()) {
            takePhotoButtonText = getString(C0880R.string.verified_id_offline_take_photo_front);
        } else {
            takePhotoButtonText = getString(C0880R.string.verified_id_offline_take_photo_back);
        }
        takePhotoButton.setText(takePhotoButtonText);
        ((Button) view.findViewById(C0880R.C0882id.official_id_choose_photo_button)).setOnClickListener(OfficialIdPhotoSelectionFragment$$Lambda$2.lambdaFactory$(this));
        ((TextView) view.findViewById(C0880R.C0882id.official_id_text_types_of_government_ids)).setVisibility((!getIsFrontId() || !OfficialIdActivity.ID_TYPE.equals(activity.getOfficialIdType())) ? 8 : 0);
        if (getIsFrontId()) {
            VerifiedIdAnalytics.trackOfflinePhotoFrontView(getVerifiedIdAnalyticsStrap());
        } else {
            VerifiedIdAnalytics.trackOfflinePhotoBackView(getVerifiedIdAnalyticsStrap());
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(OfficialIdPhotoSelectionFragment officialIdPhotoSelectionFragment, View v) {
        int requestCode = officialIdPhotoSelectionFragment.getIsFrontId() ? OFFICIAL_ID_TAKE_PHOTO_FRONT_REQUEST : OFFICIAL_ID_TAKE_PHOTO_BACK_REQUEST;
        if (officialIdPhotoSelectionFragment.getIsFrontId()) {
            VerifiedIdAnalytics.trackOfflinePhotoFrontTakePhoto(officialIdPhotoSelectionFragment.getVerifiedIdAnalyticsStrap());
        } else {
            VerifiedIdAnalytics.trackOfflinePhotoBackTakePhoto(officialIdPhotoSelectionFragment.getVerifiedIdAnalyticsStrap());
        }
        officialIdPhotoSelectionFragment.startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(MAX_IMAGE_SIZE, MAX_IMAGE_SIZE).setSource(1).create(v.getContext()), requestCode);
    }

    static /* synthetic */ void lambda$onCreateView$1(OfficialIdPhotoSelectionFragment officialIdPhotoSelectionFragment, View v) {
        int requestCode = officialIdPhotoSelectionFragment.getIsFrontId() ? OFFICIAL_ID_CHOOSE_PHOTO_FRONT_REQUEST : OFFICIAL_ID_CHOOSE_PHOTO_BACK_REQUEST;
        if (officialIdPhotoSelectionFragment.getIsFrontId()) {
            VerifiedIdAnalytics.trackOfflinePhotoFrontChoosePhoto(officialIdPhotoSelectionFragment.getVerifiedIdAnalyticsStrap());
        } else {
            VerifiedIdAnalytics.trackOfflinePhotoBackChoosePhoto(officialIdPhotoSelectionFragment.getVerifiedIdAnalyticsStrap());
        }
        officialIdPhotoSelectionFragment.startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(MAX_IMAGE_SIZE, MAX_IMAGE_SIZE).setSource(2).create(v.getContext()), requestCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case OFFICIAL_ID_TAKE_PHOTO_FRONT_REQUEST /*39002*/:
                case OFFICIAL_ID_CHOOSE_PHOTO_FRONT_REQUEST /*39004*/:
                    String filePath = data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH);
                    if (getActivity() != null) {
                        ((OfficialIdActivity) getActivity()).setIdFrontUriString(filePath);
                        ((OfficialIdActivity) getActivity()).goToPhotoConfirmFront();
                        return;
                    }
                    return;
                case OFFICIAL_ID_TAKE_PHOTO_BACK_REQUEST /*39003*/:
                case OFFICIAL_ID_CHOOSE_PHOTO_BACK_REQUEST /*39005*/:
                    String filePath2 = data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH);
                    if (getActivity() != null) {
                        ((OfficialIdActivity) getActivity()).setIdBackUriString(filePath2);
                        ((OfficialIdActivity) getActivity()).goToPhotoConfirmBack();
                        return;
                    }
                    return;
                default:
                    Log.e(TAG, "Invalid Request Code");
                    return;
            }
        }
    }

    private boolean getIsFrontId() {
        return ((OfficialIdActivity) getActivity()).isCapturingFrontId();
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((OfficialIdActivity) getActivity()).getReservationId());
    }
}
