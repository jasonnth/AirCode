package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingLocalLawsFragment extends ManageListingBaseFragment {
    AirbnbAccountManager accountManager;
    @BindView
    AirToolbar toolbar;
    @BindView
    AirWebView webView;

    public static ManageListingLocalLawsFragment create() {
        return new ManageListingLocalLawsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_web_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        String url = this.controller.getListing().getStyledLocalLawsUrl(getContext(), this.accountManager.getCurrentUser());
        this.webView.shouldLoadAirbnbUrlsInExternalBrowser(true);
        this.webView.loadUrl(url);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void onDestroyView() {
        this.webView.onDestroy();
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingLocalLaws;
    }
}
