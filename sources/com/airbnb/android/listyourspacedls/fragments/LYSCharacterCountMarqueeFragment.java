package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.constants.LYSConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.base.Objects;
import p032rx.Observer;

public class LYSCharacterCountMarqueeFragment extends LYSBaseFragment {
    @BindView
    AirEditTextPageView editTextPage;
    ListingPromoController listingPromoController;
    @BindView
    AirButton previewButton;
    private Setting setting;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSCharacterCountMarqueeFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSCharacterCountMarqueeFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSCharacterCountMarqueeFragment$$Lambda$3.lambdaFactory$(this)).build();

    public enum Setting {
        Title("name", LYSStep.TitleStep, "TITLE", 50, C7251R.string.lys_dls_title_of_title_screen, 0, C7251R.string.lys_dls_title_of_title_screen_tip, C7251R.string.lys_dls_title_of_title_screen_tip_title, C7251R.string.lys_dls_title_of_title_screen_tip_text, true, NavigationTag.LYSTitle, NavigationTag.LYSTitleTip),
        Description("summary", LYSStep.DescriptionStep, "SUMMARY", 500, C7251R.string.lys_dls_title_of_description_screen, C7251R.string.lys_dls_hint_text_of_description_screen, C7251R.string.lys_dls_title_of_description_screen_tip, C7251R.string.lys_dls_title_of_description_screen_tip_title, C7251R.string.lys_dls_title_of_description_screen_tip_text, false, NavigationTag.LYSSummary, NavigationTag.LYSSummaryTip);
        
        final int charLimit;
        final String fieldKey;
        final int hintTextRes;
        final NavigationTag navigationTag;
        final boolean singleLine;
        final LYSStep step;
        final String stepId;
        final NavigationTag tipNavigationTag;
        final int tipRes;
        final int tipTextRes;
        final int tipTitleRes;
        final int titleRes;

        private Setting(String fieldKey2, LYSStep step2, String stepId2, int charLimit2, int titleRes2, int hintTextRes2, int tipRes2, int tipTitleRes2, int tipTextRes2, boolean singleLine2, NavigationTag navigationTag2, NavigationTag tipNavigationTag2) {
            this.charLimit = charLimit2;
            this.step = step2;
            this.titleRes = titleRes2;
            this.hintTextRes = hintTextRes2;
            this.tipRes = tipRes2;
            this.tipTitleRes = tipTitleRes2;
            this.tipTextRes = tipTextRes2;
            this.fieldKey = fieldKey2;
            this.stepId = stepId2;
            this.singleLine = singleLine2;
            this.navigationTag = navigationTag2;
            this.tipNavigationTag = tipNavigationTag2;
        }

        public boolean hasHint() {
            return this.hintTextRes != 0;
        }
    }

    public static Fragment newInstance(Setting setting2) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new LYSCharacterCountMarqueeFragment()).putSerializable(LYSConstants.PARAM_SETTING, setting2)).build();
    }

    static /* synthetic */ void lambda$new$0(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment, SimpleListingResponse response) {
        lYSCharacterCountMarqueeFragment.controller.setListing(response.listing);
        lYSCharacterCountMarqueeFragment.navigateInFlow(lYSCharacterCountMarqueeFragment.setting.step);
    }

    static /* synthetic */ void lambda$new$2(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment, Boolean success) {
        lYSCharacterCountMarqueeFragment.setLoadingFinished(success.booleanValue(), null);
        lYSCharacterCountMarqueeFragment.editTextPage.setEnabled(true);
        if (lYSCharacterCountMarqueeFragment.setting == Setting.Title) {
            lYSCharacterCountMarqueeFragment.listingPromoController.refreshListingsInfo();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_char_count_marquee, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.setting = (Setting) getArguments().getSerializable(LYSConstants.PARAM_SETTING);
        if (savedInstanceState == null) {
            this.editTextPage.setText(getTextForSetting(this.controller.getListing(), this.setting));
        }
        this.editTextPage.setTitle(this.setting.titleRes);
        this.editTextPage.setListener(LYSCharacterCountMarqueeFragment$$Lambda$4.lambdaFactory$(this));
        this.editTextPage.setMaxLength(this.setting.charLimit);
        this.editTextPage.setMinLength(1);
        this.editTextPage.setSingleLine(this.setting.singleLine);
        if (this.setting.hasHint()) {
            this.editTextPage.setHint(this.setting.hintTextRes);
        }
        updateButtons(this.editTextPage.isValid());
        showTip();
        return view;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        updateTitleDescription();
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        updateTitleDescription();
    }

    private void updateTitleDescription() {
        if (!canSaveChanges()) {
            navigateInFlow(this.setting.step);
        } else {
            sendRequest();
        }
    }

    private void sendRequest() {
        setLoading(null);
        this.editTextPage.setEnabled(false);
        UpdateListingRequest.forFieldLYSWithStepId(this.controller.getListing().getId(), this.setting.fieldKey, this.editTextPage.getText(), this.controller.getMaxReachedStep().stepId).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        Listing listing = this.controller.getListing();
        return this.editTextPage.isValid() && !Objects.equal(this.editTextPage.getText().toString(), this.setting == Setting.Title ? listing.getUnscrubbedName() : listing.getUnscrubbedSummary());
    }

    @OnClick
    public void onClickPreview() {
        this.userAction = UserAction.Preview;
        updateTitleDescription();
    }

    private static String getTextForSetting(Listing listing, Setting setting2) {
        switch (setting2) {
            case Title:
                return listing.getUnscrubbedName();
            case Description:
                return listing.getUnscrubbedSummary();
            default:
                throw new UnhandledStateException(setting2);
        }
    }

    private void showTip() {
        showTip(this.setting.tipRes, LYSCharacterCountMarqueeFragment$$Lambda$5.lambdaFactory$(this));
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.setting.navigationTag;
    }

    /* access modifiers changed from: private */
    public void updateButtons(boolean valid) {
        ((AirButton) Check.notNull(this.nextButton)).setEnabled(valid);
        this.previewButton.setEnabled(valid);
    }
}
