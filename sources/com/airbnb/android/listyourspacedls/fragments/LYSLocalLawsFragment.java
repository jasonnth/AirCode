package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import p032rx.Observer;

public class LYSLocalLawsFragment extends LYSBaseFragment {
    private final AirWebViewCallbacks callbacks = new AirWebViewCallbacks() {
        public void onPageStarted(WebView webView, String url) {
            if (LYSLocalLawsFragment.this.nextButton != null) {
                LYSLocalLawsFragment.this.nextButton.setEnabled(false);
            }
        }

        public void onPageFinished(WebView view, String url) {
            if (LYSLocalLawsFragment.this.nextButton != null) {
                LYSLocalLawsFragment.this.nextButton.setEnabled(true);
            }
        }
    };
    LYSJitneyLogger lysJitneyLogger;
    @BindView
    AirButton nextButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSLocalLawsFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSLocalLawsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSLocalLawsFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    AirWebView webView;

    public static LYSLocalLawsFragment newInstance() {
        return new LYSLocalLawsFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSLocalLawsFragment lYSLocalLawsFragment, SimpleListingResponse response) {
        lYSLocalLawsFragment.controller.setListing(response.listing);
        lYSLocalLawsFragment.navigateInFlow(LYSStep.LocalLaws);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.fragment_listing_web_view_with_next, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.webView.addCallbacks(this.callbacks);
        String url = this.controller.getListing().getStyledLocalLawsUrl(getContext(), this.mAccountManager.getCurrentUser());
        this.webView.shouldLoadAirbnbUrlsInExternalBrowser(true);
        this.webView.loadUrl(url);
        return view;
    }

    public void onResume() {
        super.onResume();
        if (this.nextButton != null) {
            this.nextButton.setEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
    }

    public void onDestroyView() {
        this.webView.removeCallbacks(this.callbacks);
        this.webView.onDestroy();
        super.onDestroyView();
    }

    @OnClick
    public void onNext() {
        this.userAction = UserAction.GoToNext;
        this.lysJitneyLogger.logLocalLawNextClick(Long.valueOf(this.controller.getListing().getId()), LYSStep.isPhotoStepCompleted(this.controller.getListing()));
        if (this.controller.getListing().hasAgreedToLegalTerms()) {
            navigateInFlow(LYSStep.LocalLaws);
            return;
        }
        setLoading(null);
        UpdateListingRequest.forFieldLYSWithStepId(this.controller.getListing().getId(), ListingRequestConstants.JSON_HAS_AGREED_TO_LEGAL_TERMS, Boolean.valueOf(true), this.controller.getMaxReachedStep().stepId).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSLocalLaws;
    }
}
