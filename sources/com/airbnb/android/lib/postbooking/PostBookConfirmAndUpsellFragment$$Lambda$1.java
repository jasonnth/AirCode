package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.TripTemplate;
import java.util.List;

final /* synthetic */ class PostBookConfirmAndUpsellFragment$$Lambda$1 implements OnClickListener {
    private final PostBookConfirmAndUpsellFragment arg$1;
    private final TripTemplate arg$2;
    private final List arg$3;

    private PostBookConfirmAndUpsellFragment$$Lambda$1(PostBookConfirmAndUpsellFragment postBookConfirmAndUpsellFragment, TripTemplate tripTemplate, List list) {
        this.arg$1 = postBookConfirmAndUpsellFragment;
        this.arg$2 = tripTemplate;
        this.arg$3 = list;
    }

    public static OnClickListener lambdaFactory$(PostBookConfirmAndUpsellFragment postBookConfirmAndUpsellFragment, TripTemplate tripTemplate, List list) {
        return new PostBookConfirmAndUpsellFragment$$Lambda$1(postBookConfirmAndUpsellFragment, tripTemplate, list);
    }

    public void onClick(View view) {
        PostBookConfirmAndUpsellFragment.lambda$getTripPostCardModels$0(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
