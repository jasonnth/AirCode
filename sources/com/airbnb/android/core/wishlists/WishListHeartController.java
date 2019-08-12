package com.airbnb.android.core.wishlists;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.intents.PickWishListActivityIntents;
import com.airbnb.android.core.models.WishList;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface.OnWishListedStatusSetListener;
import java.util.List;

public class WishListHeartController extends WishListHeartInterface implements WishListsChangedListener {
    private boolean autoAddedLastItem;
    private final Context context;
    private final WishListableData data;
    WishListManager manager;
    WishListLogger wlLogger;

    public WishListHeartController(Context context2, WishListableData data2) {
        ((CoreGraph) CoreApplication.instance(context2).component()).inject(this);
        this.context = context2;
        this.data = data2;
    }

    public void onHeartClicked() {
        this.autoAddedLastItem = false;
        if (isWishListed()) {
            this.wlLogger.heartClickedToRemove(this.data);
            this.manager.deleteItemFromAllWishLists(this.data);
            return;
        }
        this.wlLogger.heartClickedToAdd(this.data);
        if (this.data.allowAutoAdd() && this.manager.getLastUpdatedWishList() != null) {
            this.autoAddedLastItem = true;
            this.manager.saveItemToWishList(this.data, this.manager.getLastUpdatedWishList());
        } else if (TextUtils.isEmpty(this.data.suggestedWishListName()) || !Experiments.isOneClickWishListEnabled()) {
            this.context.startActivity(PickWishListActivityIntents.forData(this.context, this.data));
        } else {
            this.autoAddedLastItem = true;
            this.manager.saveItemToSuggestedWishList(this.data);
        }
    }

    public void onWishListsChanged(List<WishList> list, WishListChangeInfo changeInfo) {
        setIsWishListed(this.manager.isItemWishListed(this.data));
    }

    public void addStatusListener(OnWishListedStatusSetListener listener) {
        if (this.statusListeners.isEmpty()) {
            this.manager.addWishListsChangedListener(this);
            setIsWishListed(this.manager.isItemWishListed(this.data));
        }
        super.addStatusListener(listener);
    }

    public void removeStatusListener(OnWishListedStatusSetListener statusSetListener) {
        super.removeStatusListener(statusSetListener);
        if (this.statusListeners.isEmpty()) {
            this.manager.removeWishListsChangedListener(this);
        }
    }

    public int getWishListedDrawableRes() {
        return C0716R.C0717drawable.n2_heart_red_fill;
    }

    public int getUnWishListedDrawableRes() {
        return C0716R.C0717drawable.n2_heart_light_outline;
    }

    public String getWishListedAnimationAsset() {
        return "heart-on.json";
    }

    public String getUnWishListedAnimationAsset() {
        return "heart-off.json";
    }

    public long getWishListedAnimationStartDelay() {
        return this.autoAddedLastItem ? 0 : 400;
    }

    public long getUnWishListedAnimationStartDelay() {
        return 0;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof WishListHeartController)) {
            return false;
        }
        WishListHeartController that = (WishListHeartController) object;
        if (this.data.type() == that.data.type() && this.data.itemId() == that.data.itemId()) {
            return true;
        }
        return false;
    }
}
