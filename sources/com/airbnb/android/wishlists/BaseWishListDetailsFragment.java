package com.airbnb.android.wishlists;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.wishlists.WishListDetailsParentFragment.OnWishListChangedListener;
import java.util.List;

public class BaseWishListDetailsFragment extends AirFragment implements OnWishListChangedListener {
    BottomBarController bottomBarController;
    protected WishListDetailsParentFragment parentFragment;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.parentFragment = (WishListDetailsParentFragment) getParentFragment();
    }

    public void onDetach() {
        super.onDetach();
        this.parentFragment = null;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WishListDetailsGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.parentFragment.registerOnWishListChangedListener(this);
    }

    public void onResume() {
        super.onResume();
        this.bottomBarController.setShowBottomBar(shouldShowBottomBar(), true);
    }

    public void onDestroyView() {
        this.parentFragment.unregisterOnWishListChangedListener(this);
        if (isRemoving()) {
            onFragmentClosed();
        }
        super.onDestroyView();
    }

    /* access modifiers changed from: protected */
    public void onFragmentClosed() {
    }

    public boolean hasLoadedWishList() {
        return this.parentFragment.getWishList() != null;
    }

    public List<User> getWishListMembers() {
        return this.parentFragment.getWishListMembers();
    }

    public WishList getWishList() {
        return this.parentFragment.getWishList();
    }

    public boolean isCurrentUserWishListOwner() {
        return this.parentFragment.isCurrentUserWishListOwner();
    }

    public void onMembersChanged() {
    }

    public void onWishListChanged() {
    }

    public boolean isCurrentUserAMember() {
        return this.parentFragment.isCurrentUserAMember();
    }

    /* access modifiers changed from: protected */
    public boolean canCurrentUserInviteFriends() {
        return hasLoadedWishList() && (isCurrentUserWishListOwner() || isCurrentUserAMember());
    }

    public WishListAnalytics getAnalytics() {
        return this.parentFragment.getWishListAnalytics();
    }

    public void showNetworkError(NetworkException e) {
        this.parentFragment.showNetworkError(e);
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowBottomBar() {
        return false;
    }
}
