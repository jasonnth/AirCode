package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.AppIntroFtueActivity;
import com.airbnb.android.lib.activities.WhyHostFtueActivity;
import com.airbnb.android.lib.adapters.settings.AboutEpoxyController;
import com.airbnb.android.lib.adapters.settings.AboutEpoxyController.Listener;
import com.airbnb.p027n2.components.AirToolbar;

public class AboutFragment extends AirFragment {
    private AboutEpoxyController epoxyController;
    private Listener listener = new Listener() {
        public void onHowItWorksClicked() {
            AboutFragment.this.startActivity(AppIntroFtueActivity.intentForDefault(AboutFragment.this.getActivity()));
        }

        public void onWhyHostClicked() {
            AboutFragment.this.startActivity(WhyHostFtueActivity.intentForDefault(AboutFragment.this.getActivity()));
        }

        public void onTermsOfServiceClicked() {
            AboutFragment.this.startWebLink(AboutFragment.this.getString(C0880R.string.tos_url_terms), C0880R.string.terms_of_service);
        }

        public void onNonDiscrimationPolicyClicked() {
            AboutFragment.this.startWebLink(AboutFragment.this.getString(C0880R.string.tos_url_anti_discrimination), C0880R.string.about_screen_anti_discrimination);
        }

        public void onPrivacyPolicyClicked() {
            AboutFragment.this.startWebLink(AboutFragment.this.getString(C0880R.string.tos_url_privacy), C0880R.string.privacy_policy);
        }

        public void onPaymentTermsOfServiceClicked() {
            AboutFragment.this.startWebLink(AboutFragment.this.getString(C0880R.string.tos_url_payments_terms), C0880R.string.payments_terms_of_service);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new AboutFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_recycler_view_with_toolbar, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject((AirFragment) this);
        setToolbar(this.toolbar);
        this.toolbar.setTitle(C0880R.string.account_settings);
        this.epoxyController = new AboutEpoxyController(this.listener);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        return view;
    }

    /* access modifiers changed from: private */
    public void startWebLink(String url, int title) {
        startActivity(WebViewIntentBuilder.newBuilder(getActivity(), url).title(title).toIntent());
    }
}
