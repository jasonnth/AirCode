package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.Map;

public class AccountVerificationOfflineIdInfoFragment extends BaseAccountVerificationFragment {
    private static final String COUNTRY_SELECTION_RESPONSE_ID_COUNTRY_CODE = "selectedCountryCode";
    private static final String COUNTRY_SELECTION_RESPONSE_ID_TYPE = "selectedIDType";
    private static final String COUNTRY_SELECTION_RESPONSE_ID_VENDOR = "idVendors";
    private static final String COUNTRY_SELECTION_RESPONSE_PAYLOAD = "payload";
    private static final int REQUEST_ID_TYPE_COUNTRY_SELECTION = 900;
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
    SheetMarquee sheetMarquee;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controller.initOfflineIdController(savedInstanceState, this, this.requestManager, this.navigationAnalytics);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_id, container, false);
        bindViews(view);
        this.sheetMarquee.setSubtitle(getVerificationFlow().getText().getOfflineIdSubtitleOneDotOne());
        this.secondaryButton.setText(C6533R.string.read_how_it_works_button_label);
        this.secondaryButtonWhite.setText(C6533R.string.read_how_it_works_button_label);
        setHasOptionsMenu(false);
        this.controller.getOfflineIdController().setView(view);
        setIdentityStyle(view);
        this.controller.getIdentityJitneyLogger().logImpression(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.GOVERNMENT_ID, Page.id_intro);
        return view;
    }

    private void setIdentityStyle(View view) {
        IdentityStyle style = IdentityStyle.getStyle(getVerificationFlow());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.sheetMarquee);
        this.image.setColorFilter(ContextCompat.getColor(getContext(), style.imageTint));
        this.nextButton.setText(C6533R.string.next);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf((View) this.secondaryButton, style.secondaryButtonVisible);
        ViewUtils.setVisibleIf((View) this.secondaryButtonWhite, style.secondaryButtonWhiteVisible);
        ViewUtils.setVisibleIf((View) this.primaryButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.nextButton, style.whiteNextButtonVisible);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_ID_TYPE_COUNTRY_SELECTION) {
            this.controller.getOfflineIdController().onActivityResult(requestCode, resultCode, data);
        } else if (data != null) {
            Map<String, String> payload = (Map) data.getExtras().get(COUNTRY_SELECTION_RESPONSE_PAYLOAD);
            if (payload != null && payload.containsKey(COUNTRY_SELECTION_RESPONSE_ID_COUNTRY_CODE) && payload.containsKey(COUNTRY_SELECTION_RESPONSE_ID_TYPE)) {
                this.controller.getOfflineIdController().setCountryCode((String) payload.get(COUNTRY_SELECTION_RESPONSE_ID_COUNTRY_CODE));
                this.controller.getOfflineIdController().startIdCaptureFlow(GovernmentIdType.valueOf((String) payload.get(COUNTRY_SELECTION_RESPONSE_ID_TYPE)));
            }
        }
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

    public void onResume() {
        super.onResume();
        this.controller.getOfflineIdController().onResume();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        this.controller.getOfflineIdController().onRequestPermissionsResult(requestCode, permissions2, grantResults);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickDocs() {
        startActivity(HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.VERIFIED_ID_LEARN_MORE).toIntent());
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.GOVERNMENT_ID, Page.id_intro, Element.button_help);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickDocsWhite() {
        onClickDocs();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onContinueClicked() {
        Intent intentForVerifications;
        if (FeatureToggles.showJumioIdTypeCountrySelector()) {
            this.controller.getOfflineIdController().startIdCaptureFlow(GovernmentIdType.UNKNOWN);
        } else if (Trebuchet.launch(TrebuchetKeys.IDENTITY_VERIFICATION_REACT_NATIVE_ID_TYPE_SELECTION)) {
            if (getVerificationFlow().isWhiteBackground()) {
                intentForVerifications = ReactNativeIntents.intentForVerificationsLight(getContext());
            } else {
                intentForVerifications = ReactNativeIntents.intentForVerifications(getContext());
            }
            startActivityForResult(intentForVerifications, REQUEST_ID_TYPE_COUNTRY_SELECTION);
        } else {
            this.controller.showFragmentForStep(AccountVerificationOfflineIdFragment.newInstance(getVerificationFlow()), AccountVerificationStep.OfflineId);
        }
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "verify_id_continue_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.GOVERNMENT_ID, Page.id_intro, Element.navigation_button_continue);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClicked() {
        onContinueClicked();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.getOfflineIdController().onSaveInstanceState(outState);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationScanIdInfo;
    }
}
