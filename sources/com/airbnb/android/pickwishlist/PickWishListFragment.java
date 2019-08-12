package com.airbnb.android.pickwishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import com.airbnb.android.pickwishlist.PickWishListComponent.Builder;
import com.airbnb.android.pickwishlist.PickWishListController.OnWishListSelectedListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.Carousel;
import java.util.List;

public class PickWishListFragment extends AirFragment implements WishListsChangedListener {
    private static final String KEY_WISHLISTABLE_DATA = "key_wishlistable_data";
    private static final int REQUEST_CODE_CREATE_WL = 23413;
    private static final int SLIDE_OUT_ANIMATION_TIME_MILLIS = 300;
    AppRaterController appRaterController;
    /* access modifiers changed from: private */
    public WishListableData data;
    private final OnWishListSelectedListener onWishListSelectedListener = new OnWishListSelectedListener() {
        public void onWishListSelected(WishList wishList) {
            PickWishListFragment.this.appRaterController.incrementSignificantEvent();
            PickWishListFragment.this.wishListSelected = true;
            PickWishListFragment.this.wishListController.setItemsEnabled(false);
            PickWishListFragment.this.wlLogger.clickWishListInPicker(PickWishListFragment.this.data, wishList);
            if (!PickWishListFragment.this.wishListManager.isItemInWishList(PickWishListFragment.this.data, wishList)) {
                PickWishListFragment.this.wishListManager.deleteItemFromAllWishLists(PickWishListFragment.this.data);
                PickWishListFragment.this.wishListManager.saveItemToWishList(PickWishListFragment.this.data, wishList);
            }
            View view = PickWishListFragment.this.getView();
            view.animate().setDuration(300).translationY((float) view.getHeight());
            PickWishListFragment.this.wishListRecyclerView.postDelayed(PickWishListFragment$1$$Lambda$1.lambdaFactory$(this), 300);
        }

        static /* synthetic */ void lambda$onWishListSelected$0(C76121 r1) {
            if (PickWishListFragment.this.isResumed()) {
                PickWishListFragment.this.getActivity().finish();
            }
        }
    };
    /* access modifiers changed from: private */
    public PickWishListController wishListController;
    @BindView
    Carousel wishListRecyclerView;
    /* access modifiers changed from: private */
    public boolean wishListSelected;
    WishListLogger wlLogger;

    public static Fragment instance(WishListableData data2) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new PickWishListFragment()).putParcelable("key_wishlistable_data", data2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((PickWishListBindings) CoreApplication.instance(getContext()).componentProvider()).pickWishListComponentProvider().get()).build().inject(this);
        this.data = (WishListableData) getArguments().getParcelable("key_wishlistable_data");
        if (savedInstanceState == null && this.wishListManager.isEmpty()) {
            launchCreateWishList();
            getActivity().finish();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7614R.layout.fragment_new_pick_wish_list, container, false);
        bindViews(view);
        initRecyclerView();
        this.wishListManager.addWishListsChangedListener(this);
        return view;
    }

    private void initRecyclerView() {
        this.wishListController = new PickWishListController(getContext(), this.onWishListSelectedListener);
        this.wishListController.setWishLists(this.wishListManager.getWishLists());
        this.wishListRecyclerView.setHasFixedSize(true);
        this.wishListRecyclerView.setAdapter(this.wishListController.getAdapter());
    }

    public void onPause() {
        super.onPause();
        if (this.wishListSelected && !getActivity().isFinishing()) {
            getActivity().finish();
        }
    }

    public void onDestroyView() {
        this.wishListManager.removeWishListsChangedListener(this);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void launchCreateWishList() {
        startActivityForResult(CreateWishListActivity.intent(getContext(), this.data), REQUEST_CODE_CREATE_WL);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data2) {
        switch (requestCode) {
            case REQUEST_CODE_CREATE_WL /*23413*/:
                if (resultCode == -1) {
                    getActivity().finish();
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data2);
                return;
        }
    }

    public void onWishListsChanged(List<WishList> wishLists, WishListChangeInfo changeInfo) {
        this.wishListController.setWishLists(wishLists);
    }
}
