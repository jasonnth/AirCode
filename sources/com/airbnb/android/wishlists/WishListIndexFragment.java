package com.airbnb.android.wishlists;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.utils.LayoutManagerUtils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.epoxy.EpoxyController;
import java.util.List;

public class WishListIndexFragment extends AirFragment implements WishListsChangedListener, OnWishListSelectedListener {
    BottomBarController bottomBarController;
    private WishListsFragment parentFragment;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    private WishListsController wishListsController;
    WishListLogger wlLogger;

    public static WishListIndexFragment newInstance() {
        return (WishListIndexFragment) FragmentBundler.make(new WishListIndexFragment()).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1758R.layout.fragment_wish_list_index, container, false);
        bindViews(view);
        ((WishListDetailsGraph) CoreApplication.instance(getContext()).component()).inject(this);
        initializeList();
        this.wishListManager.addWishListsChangedListener(this);
        updateUI();
        return view;
    }

    public void onDestroyView() {
        this.swipeRefreshLayout.setOnRefreshListener(null);
        this.wishListManager.removeWishListsChangedListener(this);
        super.onDestroyView();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.parentFragment = (WishListsFragment) getParentFragment();
    }

    public void onDetach() {
        super.onDetach();
        this.parentFragment = null;
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.setShowBottomBar(true, true);
    }

    private void initializeList() {
        this.wishListsController = new WishListsController(getContext(), this);
        this.recyclerView.setAdapter(this.wishListsController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        if (isTabletScreen()) {
            LayoutManagerUtils.setGridLayout((EpoxyController) this.wishListsController, this.recyclerView, 2);
        }
        this.swipeRefreshLayout.setScrollableChild(this.recyclerView);
        this.swipeRefreshLayout.setOnRefreshListener(WishListIndexFragment$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initializeList$0(WishListIndexFragment wishListIndexFragment) {
        wishListIndexFragment.wishListManager.refreshWishLists(false);
        wishListIndexFragment.updateUI();
    }

    public void onWishListsChanged(List<WishList> list, WishListChangeInfo changeInfo) {
        updateUI();
    }

    /* access modifiers changed from: protected */
    public void updateUI() {
        boolean z = false;
        this.swipeRefreshLayout.setRefreshing(false);
        AirSwipeRefreshLayout airSwipeRefreshLayout = this.swipeRefreshLayout;
        if (!this.wishListManager.isLoadingWishLists()) {
            z = true;
        }
        airSwipeRefreshLayout.setEnabled(z);
        this.wishListsController.setData(this.wishListManager.getWishLists(), Boolean.valueOf(this.wishListManager.isLoadingWishLists()));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.WishListCollections;
    }

    public void onWishListSelected(WishList wishList) {
        this.wlLogger.clickWishListFromIndex(wishList);
        this.parentFragment.showUserSelectedWishList(wishList.getId());
    }
}
