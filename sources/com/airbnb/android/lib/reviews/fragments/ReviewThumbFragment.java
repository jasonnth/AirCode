package com.airbnb.android.lib.reviews.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;

public class ReviewThumbFragment extends BaseWriteReviewFragment {
    @BindView
    SheetMarquee marquee;

    public static ReviewThumbFragment newInstance() {
        return new ReviewThumbFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_review_thumb, null);
        bindViews(view);
        switch (getReview().getReviewRole()) {
            case Guest:
                this.marquee.setTitle(C0880R.string.review_would_you_recommend_reviewing_host);
                this.marquee.setSubtitle(C0880R.string.review_anonymous_answer_reviewing_host);
                break;
            case Host:
                this.marquee.setTitle(C0880R.string.review_would_you_recommend_reviewing_guest);
                this.marquee.setSubtitle(C0880R.string.review_anonymous_answer_reviewing_guest);
                break;
            default:
                throw new IllegalArgumentException("Cannot handle role: " + getReview().getReviewRole());
        }
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickRecommendNo() {
        getReview().setRecommended(Boolean.valueOf(false));
        moveToNext();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickRecommendYes() {
        getReview().setRecommended(Boolean.valueOf(true));
        moveToNext();
    }

    private void moveToNext() {
        new Handler().postDelayed(ReviewThumbFragment$$Lambda$1.lambdaFactory$(this), 500);
    }

    /* access modifiers changed from: 0000 */
    public SheetTheme getTheme() {
        return SheetTheme.BABU;
    }
}
