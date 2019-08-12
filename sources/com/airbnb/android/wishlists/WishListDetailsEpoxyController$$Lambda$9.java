package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishListedArticle;

final /* synthetic */ class WishListDetailsEpoxyController$$Lambda$9 implements OnClickListener {
    private final WishListDetailsEpoxyController arg$1;
    private final WishListedArticle arg$2;

    private WishListDetailsEpoxyController$$Lambda$9(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedArticle wishListedArticle) {
        this.arg$1 = wishListDetailsEpoxyController;
        this.arg$2 = wishListedArticle;
    }

    public static OnClickListener lambdaFactory$(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedArticle wishListedArticle) {
        return new WishListDetailsEpoxyController$$Lambda$9(wishListDetailsEpoxyController, wishListedArticle);
    }

    public void onClick(View view) {
        this.arg$1.onStoryArticleClicked(this.arg$2);
    }
}
