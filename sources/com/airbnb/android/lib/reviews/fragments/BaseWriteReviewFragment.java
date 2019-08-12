package com.airbnb.android.lib.reviews.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;

public abstract class BaseWriteReviewFragment extends AirFragment {
    protected static final int AUTO_PROGRESS_DELAY = 500;
    protected WriteReviewActivity wrInterface;

    /* access modifiers changed from: 0000 */
    public abstract SheetTheme getTheme();

    public void onAttach(Context context) {
        super.onAttach(context);
        this.wrInterface = (WriteReviewActivity) context;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.wrInterface.setSheetTheme(getTheme());
    }

    public void onDetach() {
        this.wrInterface = null;
        super.onDetach();
    }

    /* access modifiers changed from: protected */
    public Review getReview() {
        return this.wrInterface.getReview();
    }
}
