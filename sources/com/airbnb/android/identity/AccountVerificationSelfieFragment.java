package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.misnap.TakeSelfieController;
import com.airbnb.android.misnap.TakeSelfieController.TakeSelfieListener;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.ArrayList;

public class AccountVerificationSelfieFragment extends BaseAccountVerificationFragment implements TakeSelfieListener {
    private final Handler handler = new Handler();
    @BindView
    AirImageView image;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    @BindView
    AirButton primaryButton;
    @BindView
    AirButton secondaryButton;
    @BindView
    AirButton secondaryButtonWhite;
    @BindView
    SheetMarquee selfieMarquee;
    TakeSelfieController takeSelfieController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((IdentityGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_selfie_v1_1, container, false);
        bindViews(view);
        this.secondaryButton.setText(C6533R.string.read_how_it_works_button_label);
        this.secondaryButtonWhite.setText(C6533R.string.read_how_it_works_button_label);
        this.selfieMarquee.setTitle(getVerificationFlow().getText().getSelfieTitle());
        this.selfieMarquee.setSubtitle(getVerificationFlow().getText().getSelfieSubtitle());
        setHasOptionsMenu(false);
        this.takeSelfieController.init(this);
        if (getVerificationFlow().isWhiteBackground()) {
            this.nextButton.setText(C6533R.string.next);
        }
        setIdentityStyle(view);
        this.controller.getIdentityJitneyLogger().logImpression(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.selfie_intro);
        return view;
    }

    private void setIdentityStyle(View view) {
        IdentityStyle style = IdentityStyle.getStyle(getVerificationFlow());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.selfieMarquee);
        this.image.setColorFilter(ContextCompat.getColor(getContext(), style.imageTint));
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf((View) this.secondaryButton, style.secondaryButtonVisible);
        ViewUtils.setVisibleIf((View) this.secondaryButtonWhite, style.secondaryButtonWhiteVisible);
        ViewUtils.setVisibleIf((View) this.primaryButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.nextButton, style.whiteNextButtonVisible);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C6533R.C6536menu.scan_id_why, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6533R.C6535id.menu_why) {
            return super.onOptionsItemSelected(item);
        }
        startActivity(HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.VERIFIED_ID_LEARN_MORE).toIntent());
        return true;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.takeSelfieController.destroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.takeSelfieController.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    @Optional
    public void onTakePhotoClicked() {
        this.takeSelfieController.startTakeSelfieIntent(getContext());
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "take_selfie_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.selfie_intro, Element.navigation_button_continue);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onTakePhotoClickedOneDotOne() {
        this.takeSelfieController.startTakeSelfieIntent(getContext());
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "take_selfie_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.selfie_intro, Element.navigation_button_continue);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onTakePhotoClickedNext() {
        onTakePhotoClickedOneDotOne();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onDocClick() {
        startActivity(HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.VERIFIED_ID_LEARN_MORE).toIntent());
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.selfie_intro, Element.button_help);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onDocClickWhite() {
        onDocClick();
    }

    public void onSelfieCompressStart() {
        showLoader(true);
    }

    public void onSelfieCompressFailed() {
        showLoader(false);
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.profile_photo_error);
        BugsnagWrapper.notify(new Throwable("airbnb selfie image compression failed"));
    }

    public void onSelfiePhotoReady(String filePath) {
        showLoader(false);
        ArrayList<String> selfiePaths = new ArrayList<>();
        selfiePaths.add(filePath);
        this.controller.setSelfieFilePathsForPreview(selfiePaths);
        this.controller.onStepFinished(AccountVerificationStep.Selfie, false);
    }

    public void onMultipleSelfiePhotosReady(ArrayList<String> selfiePaths) {
        showLoader(true);
        this.controller.setSelfieFilePathsForPreview(selfiePaths);
        this.handler.postDelayed(AccountVerificationSelfieFragment$$Lambda$1.lambdaFactory$(this), 0);
    }

    /* access modifiers changed from: private */
    public void onSetFinished() {
        showLoader(false);
        this.controller.onStepFinished(AccountVerificationStep.Selfie, false);
    }

    public void onNoCamera() {
        showLoader(false);
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.account_verification_not_supported_no_camera_title, C6533R.string.account_verification_not_supported_no_camera_body);
        BugsnagWrapper.notify(new Throwable("device has no camera, but reached airbnb selfie step"));
    }

    public void onCameraAccessError() {
        showLoader(false);
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.account_verification_cannot_access_camera_title, C6533R.string.account_verification_cannot_access_camera_body);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationSelfie;
    }

    public void showLoader(boolean show) {
        this.nextButton.setState(show ? State.Loading : State.Normal);
    }
}
