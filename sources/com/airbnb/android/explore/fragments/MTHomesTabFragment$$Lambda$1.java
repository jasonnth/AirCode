package com.airbnb.android.explore.fragments;

import android.view.View;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.adapters.CategorizedFiltersCarouselController.CarouselClickListener;

final /* synthetic */ class MTHomesTabFragment$$Lambda$1 implements CarouselClickListener {
    private final MTHomesTabFragment arg$1;

    private MTHomesTabFragment$$Lambda$1(MTHomesTabFragment mTHomesTabFragment) {
        this.arg$1 = mTHomesTabFragment;
    }

    public static CarouselClickListener lambdaFactory$(MTHomesTabFragment mTHomesTabFragment) {
        return new MTHomesTabFragment$$Lambda$1(mTHomesTabFragment);
    }

    public void onCarouselItemClicked(View view, FilterSuggestionItem filterSuggestionItem) {
        MTHomesTabFragment.lambda$setupFilterBar$0(this.arg$1, view, filterSuggestionItem);
    }
}
