package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.RecommendationItem;
import java.util.List;

final /* synthetic */ class MTPostHomeBookingAdapter$$Lambda$1 implements OnClickListener {
    private final MTPostHomeBookingAdapter arg$1;
    private final List arg$2;

    private MTPostHomeBookingAdapter$$Lambda$1(MTPostHomeBookingAdapter mTPostHomeBookingAdapter, List list) {
        this.arg$1 = mTPostHomeBookingAdapter;
        this.arg$2 = list;
    }

    public static OnClickListener lambdaFactory$(MTPostHomeBookingAdapter mTPostHomeBookingAdapter, List list) {
        return new MTPostHomeBookingAdapter$$Lambda$1(mTPostHomeBookingAdapter, list);
    }

    public void onClick(View view) {
        this.arg$1.handleRecommendationItemClick((RecommendationItem) this.arg$2.get(((Integer) view.getTag()).intValue()));
    }
}
