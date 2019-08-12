package com.airbnb.android.lib.fragments.verifications;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.SetProfilePhotoRequest;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.SimpleCompressionCallback;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.tasks.HaloImageScalingTask;
import com.airbnb.android.lib.utils.DialogUtils;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.AnimationUtils;
import com.airbnb.android.utils.CropUtil;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.rxgroups.TaggedObserver;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImage.ActivityResult;
import icepick.State;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PhotoVerificationFragment extends VerificationsFragment {
    private static final String ARGS_LISTING = "args_listing";
    private static final String ARGS_VERIFICATION_FLOW = "args_verification_flow";
    public static final int AddPhoto = 0;
    public static final int Error = 2;
    private static final int GALLERY_REQUEST = 2;
    private static final int PHOTO_REQUEST = 1;
    public static final int PhotoAdded = 1;
    @BindView
    View addPhotoContainer;
    @BindView
    TextView changePhotoButtonLink;
    @State
    Uri croppedPhotoUri;
    @BindView
    TextView headerTextView;
    @BindView
    HaloImageView hostHaloImageView;
    @State
    Listing listing;
    @BindView
    View photoAddedSuccessContainer;
    PhotoCompressor photoCompressor;
    @BindView
    View photoErrorContainer;
    final RequestListener<UserWrapperResponse> requestListener = new C0699RL().onResponse(PhotoVerificationFragment$$Lambda$1.lambdaFactory$(this)).onError(PhotoVerificationFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    ImageView singleEmptyHaloImageView;
    @BindView
    View skipButton;
    @BindView
    TextView subtextTextView;
    @BindView
    HaloImageView userHaloErrorPageImageView;
    @BindView
    HaloImageView userHaloImageView;
    @BindView
    View userHostHalosContainer;
    @State
    VerificationFlow verificationFlow;
    @State
    int viewState;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewState {
    }

    public static PhotoVerificationFragment newInstance(Listing listing2, VerificationFlow flow) {
        Bundle args = new Bundle();
        args.putInt(ARGS_VERIFICATION_FLOW, flow.ordinal());
        args.putParcelable(ARGS_LISTING, listing2);
        PhotoVerificationFragment fragment = new PhotoVerificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        View view = inflater.inflate(C0880R.layout.fragment_verifications_photo, container, false);
        bindViews(view);
        if (savedInstanceState != null) {
            initalizeContainers();
        } else {
            this.verificationFlow = VerificationFlow.values()[getArguments().getInt(ARGS_VERIFICATION_FLOW, -1)];
            this.listing = (Listing) getArguments().getParcelable(ARGS_LISTING);
        }
        if (this.verificationFlow == VerificationFlow.ContactHost) {
            z = true;
        } else {
            z = false;
        }
        setUpFields(z);
        showOrHideSkipButton(this.skipButton);
        setUpUserProfilePicture();
        return view;
    }

    public void onStart() {
        super.onStart();
        track("activate_account_user_photo", "no_photo", "impression");
    }

    public void onResume() {
        super.onResume();
        if (this.requestManager.hasRequest((BaseRequestListener<T>) this.requestListener, SetProfilePhotoRequest.class)) {
            this.requestManager.resubscribe((TaggedObserver<T>) this.requestListener, SetProfilePhotoRequest.class);
        }
    }

    public void onDestroyView() {
        this.photoCompressor.cancelCompressionJobs();
        getVerificationsActivity().deregisterVerificationsListener();
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

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickChooseFromLibrary() {
        track("activate_account_user_photo", "no_photo", "click", "library_photo_button");
        startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).setSource(2).create(getActivity()), 2);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickTakePhoto() {
        track("activate_account_user_photo", "no_photo", "click", "take_photo_button");
        startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).setSource(1).create(getActivity()), 1);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickNext() {
        track("activate_account_user_photo", "photo_added", "click", RegistrationAnalytics.NEXT_BUTTON);
        getVerificationsActivity().doneWithVerification(false);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void skip() {
        if (this.addPhotoContainer.getVisibility() == 0) {
            track("activate_account_user_photo", "no_photo", "click", "skip_button");
        } else if (this.photoAddedSuccessContainer.getVisibility() == 0) {
            track("activate_account_user_photo", "photo_added", "click", "skip_button");
        } else {
            track("activate_account_user_photo", "no_face_error", "click", "skip_button");
        }
        getVerificationsActivity().doneWithVerification(true);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void changePhoto() {
        track("activate_account_user_photo", "no_face_error", "click", "change_photo_button");
        if (this.viewState == 2) {
            this.viewState = 0;
            AnimationUtils.fadeInOut(this.addPhotoContainer, this.photoErrorContainer);
            track("activate_account_user_photo", "no_photo", "impression");
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void changePhotoButtonLink() {
        track("activate_account_user_photo", "photo_added", "click", "change_photo_button");
        if (this.viewState == 1) {
            this.viewState = 0;
            AnimationUtils.fadeInOut(this.addPhotoContainer, this.photoAddedSuccessContainer);
            track("activate_account_user_photo", "no_photo", "impression");
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void confirmPhoto() {
        track("activate_account_user_photo", "no_face_error", "click", "confirm_photo_button");
        uploadPhoto(true);
    }

    private void setUpFields(boolean isContactHostFlow) {
        boolean z = true;
        if (isContactHostFlow) {
            User host = this.listing.getHost();
            this.headerTextView.setText(getString(C0880R.string.verifications_photo_add_photo_to_message, host.getFirstName()));
            this.subtextTextView.setText(getString(C0880R.string.verifications_everyone_has_to_add_photo));
            this.hostHaloImageView.setImageUrl(host.getPictureUrlForThumbnail());
        }
        ViewUtils.setInvisibleIf(this.singleEmptyHaloImageView, isContactHostFlow);
        View view = this.userHostHalosContainer;
        if (isContactHostFlow) {
            z = false;
        }
        ViewUtils.setInvisibleIf(view, z);
    }

    private void initalizeContainers() {
        switch (this.viewState) {
            case 0:
                showView(this.addPhotoContainer);
                hideView(this.photoAddedSuccessContainer);
                hideView(this.photoErrorContainer);
                return;
            case 1:
                showView(this.photoAddedSuccessContainer);
                hideView(this.addPhotoContainer);
                hideView(this.photoErrorContainer);
                return;
            case 2:
                showView(this.photoErrorContainer);
                hideView(this.addPhotoContainer);
                hideView(this.photoAddedSuccessContainer);
                return;
            default:
                return;
        }
    }

    private void showView(View view) {
        view.setVisibility(0);
        view.setAlpha(1.0f);
    }

    private void hideView(View view) {
        view.setVisibility(4);
        view.setAlpha(0.0f);
    }

    private void cropPhoto(Uri source) {
        CropUtil.square(source).start((Context) getActivity(), (Fragment) this);
    }

    private void handleCrop(int resultCode, Intent data) {
        ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == -1) {
            this.croppedPhotoUri = result.getUri();
            uploadPhoto(false);
        } else if (resultCode == 204) {
            Toast.makeText(getActivity(), C0880R.string.crop_photo_error, 0).show();
        }
    }

    private void uploadPhoto(final boolean skipFaceDetection) {
        this.photoCompressor.compressPhoto(this.croppedPhotoUri, new SimpleCompressionCallback(getActivity()) {
            public void onPhotoCompressed(String filePath) {
                DialogUtils.showProgressDialog(PhotoVerificationFragment.this.getContext(), PhotoVerificationFragment.this.getFragmentManager(), C0880R.string.loading, C0880R.string.uploading_profile_photo);
                PhotoVerificationFragment.this.requestManager.executeOrResubscribe(new SetProfilePhotoRequest(PhotoVerificationFragment.this.getContext(), new File(filePath), skipFaceDetection).withListener(PhotoVerificationFragment.this.requestListener), PhotoVerificationFragment.this.requestListener);
            }
        });
    }

    private void setUpUserProfilePicture() {
        if (this.croppedPhotoUri != null) {
            new HaloImageScalingTask(this.croppedPhotoUri, this.viewState == 2 ? this.userHaloErrorPageImageView : this.userHaloImageView).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    static /* synthetic */ void lambda$new$0(PhotoVerificationFragment photoVerificationFragment, UserWrapperResponse response) {
        DialogUtils.hideProgressDialog(photoVerificationFragment.getFragmentManager());
        if (photoVerificationFragment.viewState == 2) {
            photoVerificationFragment.getVerificationsActivity().doneWithVerification(false);
            return;
        }
        photoVerificationFragment.viewState = 1;
        photoVerificationFragment.setUpUserProfilePicture();
        AnimationUtils.fadeInOut(photoVerificationFragment.photoAddedSuccessContainer, photoVerificationFragment.addPhotoContainer);
        track("activate_account_user_photo", "photo_added", "impression");
    }

    static /* synthetic */ void lambda$new$1(PhotoVerificationFragment photoVerificationFragment, AirRequestNetworkException e) {
        DialogUtils.hideProgressDialog(photoVerificationFragment.getFragmentManager());
        photoVerificationFragment.viewState = 2;
        photoVerificationFragment.setUpUserProfilePicture();
        AnimationUtils.fadeInOut(photoVerificationFragment.photoErrorContainer, photoVerificationFragment.addPhotoContainer);
        track("activate_account_user_photo", "no_face_error", "impression");
    }

    public void debugHandleResult(boolean success) {
        if (success) {
            getVerificationsActivity().doneWithVerification(false);
            return;
        }
        switch (this.viewState) {
            case 0:
                AnimationUtils.fadeInOut(this.photoErrorContainer, this.addPhotoContainer);
                break;
            case 1:
                AnimationUtils.fadeInOut(this.photoErrorContainer, this.photoAddedSuccessContainer);
                break;
        }
        this.viewState = 2;
        setUpUserProfilePicture();
    }
}
