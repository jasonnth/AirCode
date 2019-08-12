package com.airbnb.android.wishlists;

import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListManager;
import java.util.List;

public interface WLDetailsFragmentInterface {
    WishListAnalytics getAnalytics();

    User getCurrentUser();

    RequestManager getRequestManager();

    WishListLogger getWLLogger();

    WishList getWishList();

    WishListManager getWishListManager();

    List<User> getWishListMembers();

    boolean hasLoadedWishList();

    boolean isCurrentUserAMember();

    boolean isCurrentUserWishListOwner();

    boolean isShowingMapPill();

    void onInviteFriendClicked();

    void onMembersRowClicked();

    void onTabShown(WLTab wLTab);

    void showFilters();

    void showNetworkError(NetworkException networkException);

    void showVotesForItem(WishListItem wishListItem);
}
