package com.airbnb.android.wishlists;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.presenters.WishListPresenter;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MosaicCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class WishListsController extends Typed2AirEpoxyController<List<WishList>, Boolean> {
    private final Context context;
    SimpleTextRowEpoxyModel_ emptyMessageModel;
    private final OnWishListSelectedListener listener;
    EpoxyControllerLoadingModel_ loadingModel;
    DocumentMarqueeEpoxyModel_ marqueeEpoxyModel;
    LinkActionRowEpoxyModel_ startExploringLinkRow;

    interface OnWishListSelectedListener {
        void onWishListSelected(WishList wishList);
    }

    public WishListsController(Context context2, OnWishListSelectedListener listener2) {
        disableAutoDividers();
        this.context = context2;
        this.listener = listener2;
    }

    public void setData(List<WishList> wishlists, Boolean loadingMore) {
        super.setData(wishlists, Check.notNull(loadingMore));
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<WishList> wishLists, Boolean loadingMore) {
        this.marqueeEpoxyModel.titleRes(C1758R.string.wish_list_branding_title);
        for (WishList wishList : wishLists) {
            if (!WishListManager.isTemporaryWishList(wishList)) {
                add(buildWishListModel(wishList));
            }
        }
        if (loadingMore.booleanValue()) {
            add((EpoxyModel<?>) this.loadingModel);
        } else if (wishLists.isEmpty()) {
            this.emptyMessageModel.textRes(C1758R.string.wish_lists_empty_state_message).showDivider(false);
            this.startExploringLinkRow.textRes(C1758R.string.start_exploring_call_to_action).clickListener(WishListsController$$Lambda$1.lambdaFactory$()).showDivider(false);
        }
    }

    static /* synthetic */ void lambda$buildModels$0(View v) {
        Context context2 = v.getContext();
        context2.startActivity(SearchActivityIntents.intent(context2));
    }

    private EpoxyModel<?> buildWishListModel(WishList wishList) {
        List<String> pictureUrls;
        if (MiscUtils.isTabletScreen(this.context)) {
            pictureUrls = FluentIterable.from((Iterable<E>) wishList.getPictures()).transform(WishListsController$$Lambda$2.lambdaFactory$()).toList();
        } else {
            pictureUrls = wishList.getPictureUrls();
        }
        return new MosaicCardEpoxyModel_().photoUrls(pictureUrls).boldText(wishList.getName()).regularText(WishListPresenter.formatItemCounts(this.context, wishList)).cardClickListener(WishListsController$$Lambda$3.lambdaFactory$(this, wishList)).m5182id(wishList.getId()).showDivider(false).emptyStateDrawableRes(C1758R.C1759drawable.n2_wish_list_card_empty_state).displayOptions(DisplayOptions.forMosaicCard(this.context, DisplayType.Vertical));
    }
}
