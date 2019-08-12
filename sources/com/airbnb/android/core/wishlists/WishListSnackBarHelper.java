package com.airbnb.android.core.wishlists;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.view.View;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.intents.PickWishListActivityIntents;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.utils.Check;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class WishListSnackBarHelper {
    /* access modifiers changed from: private */
    public static final List<Listener> registeredListeners = new ArrayList();

    private static class Listener implements WishListsChangedListener {
        /* access modifiers changed from: private */
        public final Fragment registeredClass;
        private final View view;
        /* access modifiers changed from: private */
        public final WishListManager wishListManager;

        private Listener(Fragment registeredClass2, WishListManager wishListManager2, View view2) {
            this.view = (View) Check.notNull(view2, "View is null");
            this.wishListManager = wishListManager2;
            this.registeredClass = registeredClass2;
        }

        public void onWishListsChanged(List<WishList> list, WishListChangeInfo changeInfo) {
            if (shouldShowSnackbar(changeInfo)) {
                boolean added = changeInfo.isAdded();
                new SnackbarWrapper().view(this.view).title(this.view.getResources().getString(added ? C0716R.string.wishlist_confirmation_item_saved : C0716R.string.wishlist_confirmation_item_removed, new Object[]{changeInfo.getWishList().getName()}), false).action(added ? C0716R.string.wishlist_confirmation_item_saved_action : C0716R.string.wishlist_confirmation_item_removed_action, WishListSnackBarHelper$Listener$$Lambda$1.lambdaFactory$(this, added, changeInfo)).duration(0).buildAndShow();
            }
        }

        static /* synthetic */ void lambda$onWishListsChanged$0(Listener listener, boolean added, WishListChangeInfo changeInfo, View v) {
            Context context = v.getContext();
            if (added) {
                context.startActivity(PickWishListActivityIntents.forData(context, changeInfo.getItem()));
            } else {
                listener.wishListManager.saveItemToWishList(changeInfo.getItem(), changeInfo.getWishList());
            }
        }

        private boolean shouldShowSnackbar(WishListChangeInfo changeInfo) {
            return changeInfo != null && changeInfo.wasDoneByUser() && isMostRecentListener();
        }

        private boolean isMostRecentListener() {
            return WishListSnackBarHelper.registeredListeners.get(WishListSnackBarHelper.registeredListeners.size() + -1) == this;
        }
    }

    public static void registerAndShowWithView(Fragment registeredClass, View view, WishListManager wishListManager) {
        unregister(registeredClass);
        Listener listener = new Listener(registeredClass, wishListManager, view);
        registeredListeners.add(listener);
        wishListManager.addWishListsChangedListener(listener);
    }

    public static void unregister(Fragment registeredClass) {
        Iterator<Listener> iterator = registeredListeners.iterator();
        while (iterator.hasNext()) {
            Listener listener = (Listener) iterator.next();
            if (listener.registeredClass == registeredClass) {
                listener.wishListManager.removeWishListsChangedListener(listener);
                iterator.remove();
            }
        }
    }
}
