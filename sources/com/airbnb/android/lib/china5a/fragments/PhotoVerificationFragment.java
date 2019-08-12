package com.airbnb.android.lib.china5a.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.core.identity.ChooseProfilePhotoController;
import com.airbnb.android.core.identity.ChooseProfilePhotoController.ChooseProfilePhotoListener;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.china5a.BaseVerificationFragment;
import com.airbnb.android.lib.china5a.FiveAxiomRepository;
import com.airbnb.android.lib.china5a.photo.PhotoVerificationPresenter;
import com.airbnb.android.lib.china5a.photo.PhotoVerificationView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.ProfilePhotoSheet;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.SheetMarquee.Style;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import java.io.File;

public class PhotoVerificationFragment extends BaseVerificationFragment<PhotoVerificationPresenter> implements PhotoVerificationView {
    private static final int ERROR = 2;
    private static final int START = 0;
    private static final int SUCCESS = 1;
    @BindView
    LinkActionRow albumButton;
    @BindView
    LinkActionRow cancelButton;
    @BindView
    AirTextView choiceInfoText;
    ChooseProfilePhotoController chooseProfilePhotoController;
    @State
    int currentState = 0;
    @BindView
    LinkActionRow facebookButton;
    @BindView
    AirButton nextButton;
    @State
    String photoFilePath;
    @BindView
    ViewGroup photoSelectionLayout;
    @BindView
    ProfilePhotoSheet photoSheet;
    @State
    String photoUrl;
    @State
    boolean showLoader = false;
    @BindView
    LinkActionRow takePhotoButton;
    @BindView
    AirButton uploadButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_5a_china_photo_verification, container, false);
        bindViews(view);
        this.photoSheet.setMarqueeStyle(Style.WHITE);
        this.photoSheet.setDelegate(PhotoVerificationFragment$$Lambda$1.lambdaFactory$(this));
        this.photoSheet.setDefaultPhoto();
        this.choiceInfoText.setText(C0880R.string.verifications_photo_choose_clear_photo);
        this.facebookButton.setText(C0880R.string.account_verification_photo_choice_facebook);
        this.takePhotoButton.setText(C0880R.string.account_verification_photo_camera);
        this.albumButton.setText(C0880R.string.account_verification_photo_album);
        this.cancelButton.setText(C0880R.string.cancel);
        this.photoSelectionLayout.setVisibility(8);
        this.chooseProfilePhotoController.init(this, new ChooseProfilePhotoListener() {
            public void onProfilePhotoCompressStart() {
            }

            public void onProfilePhotoCompressFailed() {
                ErrorUtils.showErrorUsingSnackbar(PhotoVerificationFragment.this.getView(), C0880R.string.profile_photo_error);
            }

            public void onProfilePhotoReady(String filePath) {
                PhotoVerificationFragment.this.photoFilePath = filePath;
                PhotoVerificationFragment.this.setState(0, true);
                ((PhotoVerificationPresenter) PhotoVerificationFragment.this.presenter).uploadPhoto(filePath, true);
            }
        });
        setState(this.currentState, true);
        showLoader(this.showLoader);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(PhotoVerificationFragment photoVerificationFragment) {
        photoVerificationFragment.photoSelectionLayout.setVisibility(0);
        FiveAxiomAnalytics.trackClick("photo_upload_sheet");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            ((PhotoVerificationPresenter) this.presenter).verifyCurrentPhoto();
        }
    }

    /* access modifiers changed from: protected */
    public PhotoVerificationPresenter createPresenter(FiveAxiomRepository repo) {
        return new PhotoVerificationPresenter(repo.getPhotoModel(), this);
    }

    public void showLoading(boolean show) {
        showLoader(show);
    }

    public void showPicture(String pictureUrl) {
        this.photoUrl = pictureUrl;
        setState(1, TextUtils.isEmpty(this.photoFilePath));
    }

    public void showError() {
        setState(2, false);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.chooseProfilePhotoController.destroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.chooseProfilePhotoController.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick
    public void onClickUpload() {
        this.photoSelectionLayout.setVisibility(0);
        FiveAxiomAnalytics.trackClick("phone_upload");
    }

    @OnClick
    public void onClickNext() {
        switch (this.currentState) {
            case 1:
                ((PhotoVerificationPresenter) this.presenter).finish();
                FiveAxiomAnalytics.trackClick("photo_success_next");
                return;
            case 2:
                if (TextUtils.isEmpty(this.photoFilePath)) {
                    ((PhotoVerificationPresenter) this.presenter).finish();
                } else {
                    ((PhotoVerificationPresenter) this.presenter).uploadPhoto(this.photoFilePath, false);
                }
                FiveAxiomAnalytics.trackClick("photo_error_next");
                return;
            default:
                return;
        }
    }

    @OnClick
    public void onClickFacebookLink() {
        this.chooseProfilePhotoController.startFacebookLoginIntent();
        this.photoSelectionLayout.setVisibility(8);
        FiveAxiomAnalytics.trackClick("photo_facebook");
    }

    @OnClick
    public void onClickCameraLink() {
        this.chooseProfilePhotoController.startPhotoPickerIntent(Integer.valueOf(1));
        this.photoSelectionLayout.setVisibility(8);
        FiveAxiomAnalytics.trackClick("photo_camera");
    }

    @OnClick
    public void onClickAlbumLink() {
        this.chooseProfilePhotoController.startPhotoPickerIntent(Integer.valueOf(2));
        this.photoSelectionLayout.setVisibility(8);
        FiveAxiomAnalytics.trackClick("photo_album");
    }

    @OnClick
    public void onClickChoiceCancel() {
        this.photoSelectionLayout.setVisibility(8);
        FiveAxiomAnalytics.trackClick("photo_cancel");
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        if (this.photoSelectionLayout.getVisibility() != 0) {
            return super.onBackPressed();
        }
        this.photoSelectionLayout.setVisibility(8);
        FiveAxiomAnalytics.trackClick("photo_cancel");
        return true;
    }

    public void showLoader(boolean show) {
        this.showLoader = show;
        this.nextButton.setState(show ? AirButton.State.Loading : AirButton.State.Normal);
    }

    /* access modifiers changed from: private */
    public void setState(int state, boolean refreshPhoto) {
        this.currentState = state;
        switch (this.currentState) {
            case 0:
                updateUI(refreshPhoto, false, false, C0880R.string.china_photo_verification_start_title, C0880R.string.china_photo_verification_start_body, C0880R.string.account_verification_profile_photo_button);
                return;
            case 1:
                updateUI(refreshPhoto, false, true, C0880R.string.china_photo_verification_success_title, C0880R.string.china_photo_verification_success_body, C0880R.string.account_verification_profile_photo_change_photo);
                return;
            case 2:
                updateUI(refreshPhoto, true, true, C0880R.string.china_photo_verification_error_title, C0880R.string.china_photo_verification_error_body, C0880R.string.account_verification_profile_photo_change_photo);
                return;
            default:
                return;
        }
    }

    private void updateUI(boolean refreshPhoto, boolean showError, boolean enableNext, int title, int subTitle, int uploadBtn) {
        if (refreshPhoto) {
            if (!TextUtils.isEmpty(this.photoFilePath)) {
                this.photoSheet.setProfilePhoto(Uri.fromFile(new File(this.photoFilePath)));
                setupPhotoBorder();
            } else if (!TextUtils.isEmpty(this.photoUrl)) {
                this.photoSheet.setProfilePhoto(Uri.parse(this.photoUrl));
                setupPhotoBorder();
            } else {
                this.photoSheet.setDefaultPhoto();
            }
        }
        this.photoSheet.updateErrorVisibility(showError);
        this.nextButton.setEnabled(enableNext);
        this.photoSheet.setTitle(title);
        this.photoSheet.setSubtitle(subTitle);
        this.uploadButton.setText(uploadBtn);
    }

    private void setupPhotoBorder() {
        this.photoSheet.setProfilePhotoBorder(ContextCompat.getColor(getContext(), R.color.n2_black_20), getResources().getDimension(C0880R.dimen.verifications_halo_border_thickness));
    }
}
