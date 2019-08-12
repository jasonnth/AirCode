package com.airbnb.android.explore.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.SearchResult;

final /* synthetic */ class HomesMode$$Lambda$2 implements OnClickListener {
    private final HomesMode arg$1;
    private final Listing arg$2;
    private final SearchResult arg$3;

    private HomesMode$$Lambda$2(HomesMode homesMode, Listing listing, SearchResult searchResult) {
        this.arg$1 = homesMode;
        this.arg$2 = listing;
        this.arg$3 = searchResult;
    }

    public static OnClickListener lambdaFactory$(HomesMode homesMode, Listing listing, SearchResult searchResult) {
        return new HomesMode$$Lambda$2(homesMode, listing, searchResult);
    }

    public void onClick(View view) {
        HomesMode.lambda$buildListingModel$1(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
