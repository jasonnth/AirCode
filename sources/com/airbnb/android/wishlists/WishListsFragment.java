package com.airbnb.android.wishlists;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.interfaces.OnHomeListener;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import icepick.State;
import java.util.UUID;

public class WishListsFragment extends AirFragment implements OnBackListener, OnHomeListener {
    private static final String EXTRA_SESSION_UUID = "extra_session_uuid";
    private static final String EXTRA_WISH_LIST_ID = "extra_wish_list";
    private static final String TAG_DETAILS_FRAGMENT = "details_fragment";
    @State
    int lastSessionUuid;
    @State
    long selectedWishListId;

    public static WishListsFragment instance(long wishListId) {
        Check.argument(wishListId > 0, "Invalid wish list id");
        return (WishListsFragment) ((FragmentBundleBuilder) baseInstance().putLong(EXTRA_WISH_LIST_ID, wishListId)).build();
    }

    public static WishListsFragment instance() {
        return (WishListsFragment) baseInstance().build();
    }

    private static FragmentBundleBuilder<WishListsFragment> baseInstance() {
        return (FragmentBundleBuilder) FragmentBundler.make(new WishListsFragment()).putInt(EXTRA_SESSION_UUID, UUID.randomUUID().hashCode());
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WishListDetailsGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C1758R.layout.fragment_wish_lists, container, false);
        bindViews(v);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            loadNewIndexFragment();
            getChildFragmentManager().executePendingTransactions();
            long wishListId = getArguments() == null ? 0 : getArguments().getLong(EXTRA_WISH_LIST_ID);
            if (wishListId > 0) {
                autoAdvanceToWishList(wishListId);
                return;
            } else if (this.wishListManager.getWishListCount() == 1) {
                autoAdvanceToWishList(((WishList) this.wishListManager.getWishLists().get(0)).getId());
                return;
            }
        }
        skipToDetailPageForLastUsedWishList();
        this.wishListManager.clearBadge();
    }

    private void skipToDetailPageForLastUsedWishList() {
        boolean isNewSession = this.lastSessionUuid != getArguments().getInt(EXTRA_SESSION_UUID);
        WishList lastUpdatedWishList = this.wishListManager.getLastUpdatedWishList();
        if (isNewSession && this.wishListManager.shouldShowBadge() && lastUpdatedWishList != null) {
            Fragment detailsFragment = getChildFragmentManager().findFragmentByTag(TAG_DETAILS_FRAGMENT);
            if (detailsFragment != null) {
                if (((WishListDetailsParentFragment) detailsFragment).getWishListId() != lastUpdatedWishList.getId()) {
                    getChildFragmentManager().popBackStackImmediate();
                } else {
                    return;
                }
            }
            autoAdvanceToWishList(lastUpdatedWishList.getId());
        }
    }

    private void autoAdvanceToWishList(long wishListId) {
        showWishList(wishListId, false);
    }

    /* access modifiers changed from: 0000 */
    public void showUserSelectedWishList(long wishListId) {
        showWishList(wishListId, true);
    }

    private void showWishList(long wishListId, boolean addToBackStack) {
        this.selectedWishListId = wishListId;
        showFragment(WishListDetailsParentFragment.instance(wishListId), C1758R.C1760id.content_container, FragmentTransitionType.SlideInFromSide, addToBackStack, TAG_DETAILS_FRAGMENT);
    }

    /* access modifiers changed from: 0000 */
    public void goToIndex() {
        if (!getChildFragmentManager().popBackStackImmediate()) {
            loadNewIndexFragment();
        }
    }

    private void loadNewIndexFragment() {
        showFragment(WishListIndexFragment.newInstance(), C1758R.C1760id.content_container, FragmentTransitionType.None, false);
    }

    public boolean onBackPressed() {
        Fragment currentFragment = getChildFragmentManager().findFragmentById(C1758R.C1760id.content_container);
        if (!(currentFragment instanceof OnBackListener) || !((OnBackListener) currentFragment).onBackPressed()) {
            return getChildFragmentManager().popBackStackImmediate();
        }
        return true;
    }

    public boolean onHomePressed() {
        Fragment currentFragment = getChildFragmentManager().findFragmentById(C1758R.C1760id.content_container);
        if (!(currentFragment instanceof OnHomeListener) || !((OnHomeListener) currentFragment).onHomePressed()) {
            goToIndex();
        }
        return true;
    }
}
