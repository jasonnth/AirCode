package com.airbnb.android.pickwishlist;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.presenters.WishListPresenter;
import com.airbnb.android.core.viewcomponents.models.MosaicCardEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PickWishListController extends AirEpoxyController {
    private final Context context;
    private boolean itemsEnabled = true;
    private final OnWishListSelectedListener onWishListSelectedListener;
    private final ArrayList<WishList> wishLists = new ArrayList<>();

    public interface OnWishListSelectedListener {
        void onWishListSelected(WishList wishList);
    }

    public PickWishListController(Context context2, OnWishListSelectedListener onWishListSelectedListener2) {
        disableAutoDividers();
        this.context = context2;
        this.onWishListSelectedListener = onWishListSelectedListener2;
    }

    public void setItemsEnabled(boolean itemsEnabled2) {
        this.itemsEnabled = itemsEnabled2;
        requestModelBuild();
    }

    public void setWishLists(List<WishList> newWishLists) {
        this.wishLists.retainAll(newWishLists);
        int numWishListsToAdd = newWishLists.size();
        this.wishLists.ensureCapacity(numWishListsToAdd);
        for (int i = 0; i < numWishListsToAdd; i++) {
            WishList wishListToAdd = (WishList) newWishLists.get(i);
            int existingIndex = this.wishLists.indexOf(wishListToAdd);
            if (existingIndex != -1) {
                this.wishLists.set(existingIndex, wishListToAdd);
            } else {
                this.wishLists.add(i, wishListToAdd);
            }
        }
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        Iterator it = this.wishLists.iterator();
        while (it.hasNext()) {
            WishList wishList = (WishList) it.next();
            if (!WishListManager.isTemporaryWishList(wishList)) {
                new MosaicCardEpoxyModel_().photoUrls(wishList.getPictureUrls()).boldText(wishList.getName()).regularText(WishListPresenter.formatItemCounts(this.context, wishList)).cardClickListener(PickWishListController$$Lambda$1.lambdaFactory$(this, wishList)).m5182id(wishList.getId()).emptyStateDrawableRes(C7614R.C7615drawable.n2_wish_list_card_empty_state).displayOptions(DisplayOptions.forMosaicCard(this.context, DisplayType.Horizontal)).addTo(this);
            }
        }
    }

    static /* synthetic */ void lambda$buildModels$0(PickWishListController pickWishListController, WishList wishList, View v) {
        if (pickWishListController.itemsEnabled) {
            pickWishListController.onWishListSelectedListener.onWishListSelected(wishList);
        }
    }
}
