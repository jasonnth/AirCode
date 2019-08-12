package com.airbnb.android.lib.fragments.reviews;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class ReviewFragment extends AirFragment {
    private static final String ARG_REVIEW = "review";
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ReviewFragment forReview(Review review) {
        Check.notNull(review);
        return (ReviewFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ReviewFragment()).putParcelable("review", review)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_price_breakdown, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(new ReviewAdapter(getContext(), (Review) Check.notNull(getArguments().getParcelable("review")), this.mAccountManager.getCurrentUserId()));
        return view;
    }
}
