package com.airbnb.android.lib.fragments.completeprofile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.requests.SetProfilePhotoRequest;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.SimpleCompressionCallback;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.lib.utils.DialogUtils;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.CropUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.rxgroups.TaggedObserver;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImage.ActivityResult;
import icepick.State;
import java.io.File;

public class CompleteProfilePhotoFragment extends CompleteProfileBaseFragment implements VerifiedIdStrapper {
    public static final int GALLERY_REQUEST = 2;
    public static final int PHOTO_REQUEST = 1;
    @State
    Uri croppedPhotoUri;
    private LinearLayout mPhotoConfirmationButtons;
    private ScrollView mScrollView;
    PhotoCompressor photoCompressor;
    final RequestListener<UserWrapperResponse> uploadRequestListener = new C0699RL().onResponse(CompleteProfilePhotoFragment$$Lambda$1.lambdaFactory$(this)).onError(CompleteProfilePhotoFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static CompleteProfilePhotoFragment newInstance() {
        return new CompleteProfilePhotoFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public void onResume() {
        super.onResume();
        if (isPhotoRequestInFlight()) {
            DialogUtils.showProgressDialog(getContext(), getFragmentManager(), C0880R.string.loading, C0880R.string.uploading_profile_photo);
            this.requestManager.resubscribe((TaggedObserver<T>) this.uploadRequestListener, SetProfilePhotoRequest.class);
        }
    }

    public void onPause() {
        super.onPause();
        DialogUtils.hideProgressDialog(getFragmentManager());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_complete_profile_photo, container, false);
        setHasOptionsMenu(true);
        ((CircleBadgeView) view.findViewById(C0880R.C0882id.circle_badge_view)).initializeAsInactiveBadge();
        this.mScrollView = (ScrollView) view.findViewById(C0880R.C0882id.complete_profile_scrollView);
        this.mPhotoConfirmationButtons = (LinearLayout) view.findViewById(C0880R.C0882id.photo_confirmation_buttons);
        getCompleteProfileActivity().showProgressBar();
        if (savedInstanceState == null || this.croppedPhotoUri == null) {
            showChildFragment(CompleteProfilePhotoSelectChildFragment.newInstance());
        } else {
            showConfirmationFragment();
        }
        Button changeProfilePhoto = (Button) view.findViewById(C0880R.C0882id.change_profile_photo);
        changeProfilePhoto.setOnClickListener(CompleteProfilePhotoFragment$$Lambda$3.lambdaFactory$(this));
        Button completePhotoSelection = (Button) view.findViewById(C0880R.C0882id.submit_photo);
        completePhotoSelection.setOnClickListener(CompleteProfilePhotoFragment$$Lambda$4.lambdaFactory$(this, completePhotoSelection, changeProfilePhoto));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(CompleteProfilePhotoFragment completeProfilePhotoFragment, View v) {
        if (completeProfilePhotoFragment.isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhotoApprovalChangePhoto(completeProfilePhotoFragment.getVerifiedIdAnalyticsStrap());
        }
        completeProfilePhotoFragment.getCompleteProfileActivity().requestVerification();
    }

    static /* synthetic */ void lambda$onCreateView$1(CompleteProfilePhotoFragment completeProfilePhotoFragment, Button completePhotoSelection, Button changeProfilePhoto, View v) {
        if (completeProfilePhotoFragment.isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackPhotoApprovalApprovePhoto(completeProfilePhotoFragment.getVerifiedIdAnalyticsStrap());
        }
        completePhotoSelection.setVisibility(8);
        changeProfilePhoto.setVisibility(8);
        completeProfilePhotoFragment.uploadPhoto();
    }

    public void onDestroyView() {
        this.photoCompressor.cancelCompressionJobs();
        super.onDestroyView();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
            case 2:
                if (resultCode == -1) {
                    cropPhoto(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH))));
                    return;
                }
                return;
            case 203:
                handleCrop(resultCode, data);
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    public void showConfirmationFragment() {
        getCompleteProfileActivity().showProgressBar();
        this.mPhotoConfirmationButtons.setVisibility(0);
        this.mScrollView.setVisibility(0);
        Fragment f = getChildFragmentManager().findFragmentById(C0880R.C0882id.complete_profile_child_fragment);
        if (f == null || !(f instanceof CompleteProfilePhotoConfirmChildFragment)) {
            getChildFragmentManager().beginTransaction().replace(C0880R.C0882id.complete_profile_child_fragment, CompleteProfilePhotoConfirmChildFragment.newInstance(this.croppedPhotoUri)).addToBackStack(null).commitAllowingStateLoss();
        }
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((CompleteProfileActivity) getActivity()).getReservationId());
    }

    private void uploadPhoto() {
        DialogUtils.showProgressDialog(getContext(), getFragmentManager(), C0880R.string.loading, C0880R.string.uploading_profile_photo);
        this.photoCompressor.compressPhoto(this.croppedPhotoUri, new SimpleCompressionCallback(getActivity()) {
            public void onPhotoCompressed(String filePath) {
                new SetProfilePhotoRequest(CompleteProfilePhotoFragment.this.getContext(), new File(filePath)).withListener(CompleteProfilePhotoFragment.this.uploadRequestListener).execute(CompleteProfilePhotoFragment.this.requestManager);
            }
        });
    }

    private void cropPhoto(Uri source) {
        CropUtil.square(source).start((Context) getActivity(), (Fragment) this);
    }

    private void handleCrop(int resultCode, Intent data) {
        ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == -1) {
            this.croppedPhotoUri = result.getUri();
            showConfirmationFragment();
        } else if (resultCode == 204) {
            Toast.makeText(getActivity(), C0880R.string.crop_photo_error, 0).show();
        }
    }

    private boolean isPhotoRequestInFlight() {
        return this.requestManager.hasRequest((BaseRequestListener<T>) this.uploadRequestListener, SetProfilePhotoRequest.class);
    }

    static /* synthetic */ void lambda$new$2(CompleteProfilePhotoFragment completeProfilePhotoFragment, UserWrapperResponse response) {
        DialogUtils.hideProgressDialog(completeProfilePhotoFragment.getFragmentManager());
        completeProfilePhotoFragment.mAccountManager.getCurrentUser().setHasProfilePic(true);
        completeProfilePhotoFragment.getCompleteProfileActivity().completeVerification();
    }

    static /* synthetic */ void lambda$new$3(CompleteProfilePhotoFragment completeProfilePhotoFragment, AirRequestNetworkException error) {
        DialogUtils.hideProgressDialog(completeProfilePhotoFragment.getFragmentManager());
        Toast.makeText(completeProfilePhotoFragment.getActivity(), C0880R.string.upload_profile_photo_error, 0).show();
    }
}
