package com.airbnb.android.identity;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.enums.VerificationFlowText;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.ChooseProfilePhotoController;
import com.airbnb.android.core.identity.ChooseProfilePhotoController.ChooseProfilePhotoListener;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.collections.ProfilePhotoSheet;
import com.airbnb.p027n2.collections.ProfilePhotoSheet.Delegate;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import java.io.File;

public class AccountVerificationProfilePhoto extends ScrollView implements ChooseProfilePhotoListener, Delegate {
    @BindView
    LinkActionRow albumButton;
    @BindView
    View bottomToolbar;
    @BindView
    LinkActionRow cancelButton;
    @BindView
    AirTextView choiceInfoText;
    private ChooseProfilePhotoController chooseProfilePhotoController;
    @BindView
    LinkActionRow facebookButton;
    private IdentityStyle identityStyle;
    @BindView
    JellyfishView jellyfishView;
    private IdentityJitneyLogger jitneyLogger;
    private AccountVerificationProfilePhotoListener listener;
    private NavigationLogging navigationAnalytics;
    @BindView
    ViewGroup photoSelectionLayout;
    @State
    int photoSelectionLayoutVisibility;
    @BindView
    AirButton primaryButton;
    @BindView
    AirButton primaryWhiteButton;
    @State
    String profilePhotoFilePath;
    @BindView
    ProfilePhotoSheet profilePhotoSheet;
    @State
    ProfilePhotoState profilePhotoState;
    @State
    String profilePhotoUrl;
    @BindView
    AirButton secondaryButton;
    @BindView
    AirButton secondaryWhiteButton;
    private ProfilePhotoState startState;
    @BindView
    LinkActionRow takePhotoButton;
    private User user;
    @State
    VerificationFlow verificationFlow;

    public enum ProfilePhotoState {
        Start(C6533R.string.account_verification_profile_photo_header, C6533R.string.account_verification_profile_photo_button),
        Success(C6533R.string.account_verification_profile_photo_sucess_header, C6533R.string.account_verification_profile_photo_change_photo),
        Error(C6533R.string.account_verification_profile_photo_error_header, C6533R.string.account_verification_profile_photo_change_photo),
        ErrorForCurrentPhoto(C6533R.string.account_verification_profile_photo_confirm_header, C6533R.string.account_verification_profile_photo_change_photo);
        
        final int buttonResId;
        final int titleResId;

        private ProfilePhotoState(int titleResId2, int buttonResId2) {
            this.titleResId = titleResId2;
            this.buttonResId = buttonResId2;
        }

        public boolean isError() {
            return name().startsWith(Error.name());
        }

        public int getSubTitleResId(VerificationFlow flow) {
            VerificationFlowText text = flow.getText();
            if (flow == VerificationFlow.ContactHost) {
                return C6533R.string.account_verification_profile_photo_desc_contact_host;
            }
            if (this == Error || this == ErrorForCurrentPhoto) {
                return C6533R.string.account_verification_profile_photo_error_desc;
            }
            if (this == Start) {
                return text.getProfilePhotoStartSubtitle();
            }
            if (this == Success) {
                return text.getProfilePhotoSuccessSubtitle();
            }
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Unhandled ProfilePhotoState " + this + " with flow " + flow));
            return -1;
        }
    }

    public AccountVerificationProfilePhoto(Context context) {
        this(context, null);
    }

    public AccountVerificationProfilePhoto(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountVerificationProfilePhoto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setData(AirFragment airFragment, AccountVerificationProfilePhotoListener listener2, ChooseProfilePhotoController chooseProfilePhotoController2, NavigationLogging navigationAnalytics2, IdentityJitneyLogger jitneyLogger2, VerificationFlow verificationFlow2, User user2) {
        this.startState = ProfilePhotoState.Start;
        this.listener = listener2;
        this.chooseProfilePhotoController = chooseProfilePhotoController2;
        this.navigationAnalytics = navigationAnalytics2;
        this.jitneyLogger = jitneyLogger2;
        this.verificationFlow = verificationFlow2;
        this.user = user2;
        this.identityStyle = IdentityStyle.getStyle(verificationFlow2);
        chooseProfilePhotoController2.init(airFragment, this);
        if (this.profilePhotoState == null) {
            this.profilePhotoState = this.startState;
        }
        getSecondaryButton().setText(C6533R.string.account_verification_profile_photo_button);
        getPrimaryButton().setOnClickListener(AccountVerificationProfilePhoto$$Lambda$1.lambdaFactory$(listener2));
        getSecondaryButton().setOnClickListener(AccountVerificationProfilePhoto$$Lambda$2.lambdaFactory$(this));
        setIdentityStyle();
        this.choiceInfoText.setText(C6533R.string.verifications_photo_choose_clear_photo);
        this.facebookButton.setText(C6533R.string.account_verification_photo_choice_facebook);
        this.takePhotoButton.setText(C6533R.string.account_verification_photo_camera);
        this.albumButton.setText(C6533R.string.account_verification_photo_album);
        this.cancelButton.setText(C6533R.string.cancel);
        this.primaryWhiteButton.setText(C6533R.string.next);
    }

    public Parcelable onSaveInstanceState() {
        this.photoSelectionLayoutVisibility = this.photoSelectionLayout.getVisibility();
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
        this.photoSelectionLayout.setVisibility(this.photoSelectionLayoutVisibility);
    }

    public void onProfilePhotoCompressStart() {
        setState(this.startState);
        showLoader(true);
    }

    public void onProfilePhotoCompressFailed() {
        this.listener.onProfilePhotoCompressFailed();
    }

    public void onProfilePhotoReady(String profilePhotoFilePath2) {
        this.profilePhotoFilePath = profilePhotoFilePath2;
        updateViewsForState();
        this.listener.uploadPhoto(true);
    }

    public void setState(ProfilePhotoState newState) {
        this.profilePhotoState = newState;
        updateViewsForState();
        this.jitneyLogger.logImpression(this.verificationFlow, this.user, IdentityVerificationType.PHOTO_WITH_FACE, getPage());
        this.navigationAnalytics.trackImpressionExplicitly(getAnalyticsTag(), null);
    }

    /* access modifiers changed from: protected */
    public void showLoader(boolean show) {
        getPrimaryButton().setState(show ? AirButton.State.Loading : AirButton.State.Normal);
    }

    /* access modifiers changed from: protected */
    public void setNextButtonEnabled(boolean enabled) {
        getPrimaryButton().setEnabled(enabled);
    }

    /* access modifiers changed from: protected */
    public AirButton getSecondaryButton() {
        return this.verificationFlow.isWhiteBackground() ? this.secondaryWhiteButton : this.secondaryButton;
    }

    /* access modifiers changed from: protected */
    public AirButton getPrimaryButton() {
        return this.verificationFlow.isWhiteBackground() ? this.primaryWhiteButton : this.primaryButton;
    }

    public void updateViewsForState() {
        this.profilePhotoSheet.setTitle(this.profilePhotoState.titleResId);
        this.profilePhotoSheet.setSubtitle(this.profilePhotoState.getSubTitleResId(this.verificationFlow));
        getSecondaryButton().setText(this.profilePhotoState.buttonResId);
        setNextButtonEnabled(this.profilePhotoState != this.startState);
        if (this.profilePhotoState == this.startState) {
            this.profilePhotoSheet.setDefaultPhoto();
            this.profilePhotoSheet.updateErrorVisibility(false);
            return;
        }
        if (!TextUtils.isEmpty(this.profilePhotoFilePath)) {
            File profilePhotoFile = new File(this.profilePhotoFilePath);
            if (profilePhotoFile.exists()) {
                this.profilePhotoSheet.setProfilePhoto(Uri.fromFile(profilePhotoFile));
                setPhotoBorder();
                return;
            }
        }
        if (!TextUtils.isEmpty(this.profilePhotoUrl)) {
            this.profilePhotoSheet.setProfilePhoto(Uri.parse(this.profilePhotoUrl));
            setPhotoBorder();
        }
    }

    private void setIdentityStyle() {
        setBackgroundColor(ContextCompat.getColor(getContext(), this.identityStyle.backgroundColorRes));
        this.profilePhotoSheet.setPlaceHolderImageRes(this.identityStyle.photoAddPlaceholderRes);
        setPhotoBorder();
        this.profilePhotoSheet.setMarqueeStyle(this.identityStyle.marqueeStyle);
        ViewUtils.setVisibleIf((View) this.jellyfishView, this.identityStyle.hasJellyFish);
        getSecondaryButton().setVisibility(0);
        getPrimaryButton().setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void setPhotoBorder() {
        this.profilePhotoSheet.setProfilePhotoBorder(ContextCompat.getColor(getContext(), this.identityStyle.photoBorderColorRes), getResources().getDimension(C6533R.dimen.verifications_halo_border_thickness));
        this.profilePhotoSheet.updateErrorVisibility(this.profilePhotoState.isError());
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        ButterKnife.bind(this, inflate(context, C6533R.layout.account_verification_profile_photo_v1_1, this));
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
        this.photoSelectionLayout.setVisibility(8);
    }

    public NavigationTag getAnalyticsTag() {
        switch (this.profilePhotoState) {
            case Start:
                return NavigationTag.VerificationProfilePhoto;
            case Success:
                return NavigationTag.VerificationProfilePhotoConfirm;
            case Error:
            case ErrorForCurrentPhoto:
                return NavigationTag.VerificationProfilePhotoError;
            default:
                return NavigationTag.Unknown;
        }
    }

    public String getProfilePhotoFilePath() {
        return this.profilePhotoFilePath;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl2) {
        this.profilePhotoUrl = profilePhotoUrl2;
    }

    public ProfilePhotoState getProfilePhotoState() {
        return this.profilePhotoState;
    }

    public void onProfilePhotoClick() {
        AccountVerificationAnalytics.trackButtonClick(getAnalyticsTag(), "change_photo_button");
        this.photoSelectionLayout.setVisibility(0);
        this.jitneyLogger.logClick(this.verificationFlow, this.user, IdentityVerificationType.PHOTO_WITH_FACE, getPage(), this.profilePhotoState == ProfilePhotoState.Start ? Element.button_add_photo : Element.button_change_photo);
    }

    private Page getPage() {
        return this.profilePhotoState == ProfilePhotoState.Start ? Page.provide_photo_upload : Page.profile_photo_review;
    }

    @OnClick
    public void onClickFacebookLink() {
        AccountVerificationAnalytics.trackProfilePhotoType("facebook");
        this.chooseProfilePhotoController.startFacebookLoginIntent();
        this.photoSelectionLayout.setVisibility(8);
        this.jitneyLogger.logClick(this.verificationFlow, this.user, IdentityVerificationType.PHOTO_WITH_FACE, getPage(), Element.option_use_facebook_profile_photo);
    }

    @OnClick
    public void onClickCameraLink() {
        AccountVerificationAnalytics.trackProfilePhotoType("camera");
        this.chooseProfilePhotoController.startPhotoPickerIntent(Integer.valueOf(1));
        this.photoSelectionLayout.setVisibility(8);
        this.jitneyLogger.logClick(this.verificationFlow, this.user, IdentityVerificationType.PHOTO_WITH_FACE, getPage(), Element.option_take_a_photo);
    }

    @OnClick
    public void onClickAlbumLink() {
        AccountVerificationAnalytics.trackProfilePhotoType("album");
        this.chooseProfilePhotoController.startPhotoPickerIntent(Integer.valueOf(2));
        this.photoSelectionLayout.setVisibility(8);
        this.jitneyLogger.logClick(this.verificationFlow, this.user, IdentityVerificationType.PHOTO_WITH_FACE, getPage(), Element.option_choose_from_library);
    }

    @OnClick
    public void onClickChoiceCancel() {
        this.photoSelectionLayout.setVisibility(8);
    }
}
