package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;

final /* synthetic */ class LocalAttractionsFragment$$Lambda$3 implements OnClickListener {
    private final LocalAttractionsFragment arg$1;

    private LocalAttractionsFragment$$Lambda$3(LocalAttractionsFragment localAttractionsFragment) {
        this.arg$1 = localAttractionsFragment;
    }

    public static OnClickListener lambdaFactory$(LocalAttractionsFragment localAttractionsFragment) {
        return new LocalAttractionsFragment$$Lambda$3(localAttractionsFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(AutoAirActivity.intentForFragment(this.arg$1.getActivity(), LocalAttractionDetailFragment.class, LocalAttractionDetailFragment.bundleWithLocalAttraction(this.arg$1.mListing, (LocalAttraction) this.arg$1.mAttractions.get(this.arg$1.mLoopingListingPosition))));
    }
}
