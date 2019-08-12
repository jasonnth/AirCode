package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View;
import com.airbnb.android.contentframework.views.StoryProductLinkElementView;
import com.airbnb.android.core.models.StoryProductLinkDetails;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class StoryProductLinkElementEpoxyModel extends AirEpoxyModel<StoryProductLinkElementView> {
    StoryProductLinkClickDelegate delegate;
    StoryProductLinkDetails details;
    boolean isSubElement;

    public interface StoryProductLinkClickDelegate {
        WishListManager getWishlistManager();

        void onProductClicked(StoryProductLinkDetails storyProductLinkDetails);

        void onWishlistClicked(StoryProductLinkDetails storyProductLinkDetails);
    }

    public void bind(StoryProductLinkElementView view) {
        super.bind(view);
        view.setOptionalHeader(this.details.getHeader());
        view.setThumbnailImageUrl(this.details.getPictureUrl());
        view.setTitle(this.details.getTitle());
        view.setSubtitle(this.details.getSubtitle());
        view.setStarRating(this.details.getRating());
        view.showTopDivider(!this.isSubElement);
        view.setWishlisted(this.details.isWishlisted(this.delegate.getWishlistManager()));
        view.setOnClickWishlistListener(StoryProductLinkElementEpoxyModel$$Lambda$1.lambdaFactory$(this));
        view.setOnClickListener(StoryProductLinkElementEpoxyModel$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$bind$0(StoryProductLinkElementEpoxyModel storyProductLinkElementEpoxyModel, View v) {
        if (storyProductLinkElementEpoxyModel.delegate != null) {
            storyProductLinkElementEpoxyModel.delegate.onWishlistClicked(storyProductLinkElementEpoxyModel.details);
        }
    }

    static /* synthetic */ void lambda$bind$1(StoryProductLinkElementEpoxyModel storyProductLinkElementEpoxyModel, View v) {
        if (storyProductLinkElementEpoxyModel.delegate != null) {
            storyProductLinkElementEpoxyModel.delegate.onProductClicked(storyProductLinkElementEpoxyModel.details);
        }
    }
}
