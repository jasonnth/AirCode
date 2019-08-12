package com.airbnb.android.wishlists;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.Strap;
import java.util.ArrayList;
import java.util.List;

public class WishListAnalytics extends BaseAnalytics {
    private static final String PAGE_WISH_LIST = "wishlist";
    private static final String ROLE_COLLABORATOR = "collaborator";
    private static final String ROLE_OWNER = "owner";
    private static final String ROLE_PUBLIC = "public";
    private final AirbnbAccountManager accountManager;
    private List<User> members;

    public WishListAnalytics(AirbnbAccountManager accountManager2, ArrayList<User> wishListMembers) {
        this.members = wishListMembers;
        this.accountManager = accountManager2;
    }

    public void setMembers(List<User> members2) {
        this.members = members2;
    }

    public void trackListingClicked(WishList wishList, Listing listing) {
        track(buildBaseParams(wishList).mo11639kv("page", "p3").mo11639kv("action", "show").mo11638kv("listing_id", listing.getId()).mo11639kv("came_from", "wishlist_feed"));
    }

    public void trackDatesSet(WishList wishList) {
        track(buildBaseParams(wishList).mo11639kv("page", "wishlist").mo11639kv(BaseAnalytics.SUBEVENT, "set_dates"));
    }

    public void trackGuestsSet(WishList wishList) {
        track(buildBaseParams(wishList).mo11639kv("page", "wishlist").mo11639kv(BaseAnalytics.SUBEVENT, "set_guests"));
    }

    private Strap buildBaseParams(WishList wishList) {
        String str;
        String str2 = null;
        GuestDetails guestFilters = wishList.getGuestDetails();
        Strap kv = Strap.make().mo11639kv("role", getRole(wishList)).mo11638kv("wishlist_id", wishList.getId()).mo11637kv("num_guests", guestFilters.totalGuestCount()).mo11637kv(FindTweenAnalytics.ADULTS, guestFilters.adultsCount()).mo11637kv(FindTweenAnalytics.CHILDREN, guestFilters.childrenCount()).mo11637kv(FindTweenAnalytics.INFANTS, guestFilters.infantsCount()).mo11640kv(FindTweenAnalytics.PETS, guestFilters.isBringingPets());
        String str3 = "check_in";
        if (wishList.hasDates()) {
            str = wishList.getCheckin().getIsoDateString();
        } else {
            str = null;
        }
        Strap kv2 = kv.mo11639kv(str3, str);
        String str4 = "check_out";
        if (wishList.hasDates()) {
            str2 = wishList.getCheckout().getIsoDateString();
        }
        return kv2.mo11639kv(str4, str2);
    }

    private String getRole(WishList wishList) {
        Check.notNull(this.members, "Members must be set before tracking");
        User currentUser = this.accountManager.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getId() == wishList.getUserId()) {
                return ROLE_OWNER;
            }
            if (this.members.contains(currentUser)) {
                return ROLE_COLLABORATOR;
            }
        }
        return ROLE_PUBLIC;
    }

    private void track(Strap strap) {
        AirbnbEventLogger.track("trip_collaboration", strap);
    }
}
