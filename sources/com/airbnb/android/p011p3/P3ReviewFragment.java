package com.airbnb.android.p011p3;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.ReviewSearchJitneyLogger;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.p011p3.P3Component.Builder;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.P3ReviewFragment */
public class P3ReviewFragment extends P3BaseFragment {
    private static final String FLAGGABLE_ID_KEY = "flaggableId";
    private static final String PAYLOAD_KEY = "payload";
    public static final int REQUEST_CODE_REPORT_REVIEW = 42;
    @BindView
    AirToolbar airToolbar;
    private P3ReviewsEpoxyController epoxyController;
    ReviewSearchJitneyLogger jitneyLogger;
    @BindView
    RecyclerView recyclerView;
    private ReviewsController reviewsController;

    public static P3ReviewFragment newInstance() {
        return new P3ReviewFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_p3_reviews, container, false);
        bindViews(view);
        ((Builder) ((P3Bindings) CoreApplication.instance(getActivity()).componentProvider()).p3ComponentProvider().get()).build().inject(this);
        setToolbar(this.airToolbar);
        setHasOptionsMenu(true);
        return view;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem searchMenuItem = menu.findItem(C7532R.C7534id.action_search);
        searchMenuItem.setVisible(Experiments.showReviewsSearch());
        searchMenuItem.getActionView().setOnClickListener(P3ReviewFragment$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onPrepareOptionsMenu$0(P3ReviewFragment p3ReviewFragment, View view) {
        p3ReviewFragment.jitneyLogger.logClickReviewSearchMenuItem(p3ReviewFragment.controller.getState().listingId());
        p3ReviewFragment.controller.launchP3ReviewSearch();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.reviewsController = this.controller.getReviewsController();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        this.epoxyController = new P3ReviewsEpoxyController(this, this.reviewsController);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.epoxyController.requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void onFragmentExited() {
        this.reviewsController.trimToOnePage();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ListingReviews;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 42:
                    Map payload = (Map) data.getExtras().get(PAYLOAD_KEY);
                    if (payload.containsKey(FLAGGABLE_ID_KEY)) {
                        this.reviewsController.refreshFlagForReview(((Double) payload.get(FLAGGABLE_ID_KEY)).longValue(), this.mAccountManager.getCurrentUserId());
                        break;
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onStateChanged() {
        this.epoxyController.requestModelBuild();
    }
}
