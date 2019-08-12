package com.airbnb.android.ibdeactivation;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ReviewBulletRowEpoxyModel_;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class ReviewAllRequestsFragment extends AirFragment {
    private static final String IMPROVEMENT_IDEA = "improvement_idea";
    private static final String LISTING = "listing";
    @BindView
    AirButton cancelButton;
    @State
    String hostReason;
    @State
    Listing listing;
    @BindView
    SheetMarquee marquee;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton reviewRequestsButton;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    AirToolbar toolbar;

    public class ReviewRequestsReasonsAdapter extends AirEpoxyAdapter {
        public ReviewRequestsReasonsAdapter() {
            super(true);
            addModel(new ReviewBulletRowEpoxyModel_().title(C6454R.string.deactivate_ib_review_all_requests_invisible));
            addModel(new ReviewBulletRowEpoxyModel_().title(C6454R.string.deactivate_ib_review_all_requests_cancel_penalty));
            addModel(new ReviewBulletRowEpoxyModel_().title(C6454R.string.deactivate_ib_review_all_requests_no_response_penalty));
        }
    }

    public static ReviewAllRequestsFragment newInstance(Listing listing2, String hostReason2) {
        return (ReviewAllRequestsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReviewAllRequestsFragment()).putParcelable("listing", listing2)).putString(IMPROVEMENT_IDEA, hostReason2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6454R.layout.fragment_review_all_requests, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setVerticalScrollBarEnabled(false);
        this.recyclerView.setAdapter(new ReviewRequestsReasonsAdapter());
        this.hostReason = getArguments().getString(IMPROVEMENT_IDEA);
        this.listing = (Listing) getArguments().getParcelable("listing");
        return view;
    }

    @OnClick
    public void clickReviewRequests() {
        DeactivateIBAnalytics.trackLoveToKnowMore(this.hostReason, Long.valueOf(this.listing.getId()), Long.valueOf(this.listing.getHost().getId()));
        getActivity().setResult(-1);
        getActivity().finish();
    }

    @OnClick
    public void clickCancel() {
        DeactivateIBAnalytics.trackCloseModal(Long.valueOf(this.listing.getId()), Long.valueOf(this.listing.getHost().getId()));
        getActivity().setResult(0);
        getActivity().finish();
    }
}
