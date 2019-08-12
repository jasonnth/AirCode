package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.ReviewsActivity;
import com.airbnb.android.lib.analytics.ReviewsAnalytics;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.android.utils.BundleBuilder;

public class NPSFragment extends AirFragment {
    public static final String ARG_REVIEW = "review";
    @BindView
    RadioGroup mGroup;
    private Review mReview;
    @BindView
    StickyButton mSubmit;

    public static Intent intentForDefault(Context context, Review review) {
        return AutoAirActivity.intentForFragment(context, NPSFragment.class, ((BundleBuilder) new BundleBuilder().putParcelable(ARG_REVIEW, review)).toBundle());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_nps, container, false);
        ((AirActivity) getActivity()).setupActionBar(C0880R.string.nps_title, new Object[0]);
        bindViews(view);
        this.mReview = (Review) getArguments().getParcelable(ARG_REVIEW);
        this.mSubmit.setTitle(C0880R.string.submit);
        this.mSubmit.setOnClickListener(NPSFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(NPSFragment nPSFragment, View v) {
        int selected = nPSFragment.mGroup.getCheckedRadioButtonId();
        if (selected == -1) {
            Toast.makeText(nPSFragment.getActivity(), nPSFragment.getString(C0880R.string.nps_unselected), 0).show();
            return;
        }
        ReviewsAnalytics.trackSubmitNPS(nPSFragment.mReview, nPSFragment.mGroup.indexOfChild(nPSFragment.mGroup.findViewById(selected)));
        ReviewInfoDialogFragment f = ReviewInfoDialogFragment.newInstanceForPostReview(nPSFragment.mReview, nPSFragment);
        f.setTargetFragment(nPSFragment, 1012);
        f.show(nPSFragment.getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ReviewInfoDialogFragment.REQUEST_CODE_SEE_REVIEW /*1010*/:
                ReviewsAnalytics.trackViewReview(this.mReview);
                startActivity(ReviewsActivity.intentForUser(getActivity(), this.mReview.getAuthor(), ReviewsMode.MODE_ALL));
                getActivity().finish();
                return;
            case 1011:
                ReviewsAnalytics.trackDismissPostDoubleBlindDialog(this.mReview);
                getActivity().finish();
                return;
            case 1012:
                getActivity().finish();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }
}
