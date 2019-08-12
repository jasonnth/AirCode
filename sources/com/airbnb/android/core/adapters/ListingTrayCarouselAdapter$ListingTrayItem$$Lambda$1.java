package com.airbnb.android.core.adapters;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.google.common.base.Function;

final /* synthetic */ class ListingTrayCarouselAdapter$ListingTrayItem$$Lambda$1 implements Function {
    private final C2813WishlistSource arg$1;
    private final String arg$2;
    private final boolean arg$3;
    private final GuestDetails arg$4;
    private final AirDate arg$5;
    private final AirDate arg$6;

    private ListingTrayCarouselAdapter$ListingTrayItem$$Lambda$1(C2813WishlistSource wishlistSource, String str, boolean z, GuestDetails guestDetails, AirDate airDate, AirDate airDate2) {
        this.arg$1 = wishlistSource;
        this.arg$2 = str;
        this.arg$3 = z;
        this.arg$4 = guestDetails;
        this.arg$5 = airDate;
        this.arg$6 = airDate2;
    }

    public static Function lambdaFactory$(C2813WishlistSource wishlistSource, String str, boolean z, GuestDetails guestDetails, AirDate airDate, AirDate airDate2) {
        return new ListingTrayCarouselAdapter$ListingTrayItem$$Lambda$1(wishlistSource, str, z, guestDetails, airDate, airDate2);
    }

    public Object apply(Object obj) {
        return ListingTrayItem.lambda$fromSimilarListings$0(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, (SimilarListing) obj);
    }
}
