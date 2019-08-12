package com.airbnb.android.core.wishlists;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.beta.models.guidebook.GuidebookPlace;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.models.TripTemplateMarket;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;

public abstract class WishListableData implements Parcelable {

    public static abstract class Builder {
        public abstract Builder allowAutoAdd(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract WishListableData autoBuild();

        /* access modifiers changed from: 0000 */
        public abstract AirDate checkIn();

        public abstract Builder checkIn(AirDate airDate);

        /* access modifiers changed from: 0000 */
        public abstract AirDate checkOut();

        public abstract Builder checkOut(AirDate airDate);

        public abstract Builder guestDetails(GuestDetails guestDetails);

        /* access modifiers changed from: protected */
        public abstract Builder itemId(long j);

        public abstract Builder searchSessionId(String str);

        /* access modifiers changed from: 0000 */
        public abstract String searchSessionId();

        public abstract Builder source(C2813WishlistSource wishlistSource);

        public abstract Builder suggestedWishListName(String str);

        /* access modifiers changed from: protected */
        public abstract Builder type(WishListableType wishListableType);

        public WishListableData build() {
            if (searchSessionId() == null) {
                searchSessionId("");
            }
            if (checkIn() == null || checkOut() == null || checkIn().isBefore(AirDate.today()) || checkIn().isSameDayOrAfter(checkOut())) {
                checkIn(null);
                checkOut(null);
            }
            return autoBuild();
        }
    }

    public abstract boolean allowAutoAdd();

    public abstract AirDate checkIn();

    public abstract AirDate checkOut();

    public abstract GuestDetails guestDetails();

    public abstract long itemId();

    public abstract String searchSessionId();

    public abstract C2813WishlistSource source();

    public abstract String suggestedWishListName();

    public abstract WishListableType type();

    public boolean hasDates() {
        return (checkIn() == null || checkOut() == null) ? false : true;
    }

    public boolean hasGuests() {
        return guestDetails() != null;
    }

    public static Builder forHome(Listing listing) {
        return forType(WishListableType.Home, listing.getId()).suggestedWishListName(listing.getCity());
    }

    public static Builder forTrip(TripTemplate tripTemplate) {
        Builder builder = forType(WishListableType.Trip, tripTemplate.getId());
        TripTemplateMarket market = tripTemplate.getMarket();
        if (market != null) {
            builder.suggestedWishListName(market.getName());
        }
        return builder;
    }

    public static Builder forPlaceActivity(PlaceActivity placeActivity) {
        return forType(WishListableType.PlaceActivity, (long) placeActivity.getId()).suggestedWishListName(placeActivity.getPlace().getCity());
    }

    public static Builder forStoryArticle(Article article) {
        return forType(WishListableType.StoryArticle, article.getId()).suggestedWishListName(article.getLocalizedArticleLocation());
    }

    public static Builder forPlace(GuidebookPlace guidebookPlace) {
        return forType(WishListableType.Place, guidebookPlace.getPrimaryPlace().getId()).suggestedWishListName(guidebookPlace.getPrimaryPlace().getCity());
    }

    public static Builder forType(WishListableType type, long id) {
        return new Builder().type(type).itemId(id).allowAutoAdd(false);
    }
}
