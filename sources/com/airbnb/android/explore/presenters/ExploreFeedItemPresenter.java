package com.airbnb.android.explore.presenters;

import android.view.View;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.core.models.InlineSearchFeedItem;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.models.InterstitialEpoxyModel_;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public final class ExploreFeedItemPresenter {

    public static class GoldenTicketEpoxyModel extends InterstitialEpoxyModel_ {
        /* access modifiers changed from: private */
        public final InlineSearchFeedFilterItem filterItem;

        public GoldenTicketEpoxyModel(InlineSearchFeedFilterItem item, OnInlineFilterItemClickListener clickListener, ExploreJitneyLogger logger) {
            Check.notNull(item.getType(), "InlineSearchFeedFilterItem must have a type.");
            this.filterItem = item;
            buttonClickListener(ExploreFeedItemPresenter$GoldenTicketEpoxyModel$$Lambda$1.lambdaFactory$(logger, item, clickListener));
            text(item.getText());
            buttonText(item.getActionText());
            m4966id((long) item.hashCode());
        }

        static /* synthetic */ void lambda$new$0(ExploreJitneyLogger logger, InlineSearchFeedFilterItem item, OnInlineFilterItemClickListener clickListener, View v) {
            logger.clickGoldenTicket(item);
            clickListener.onInlineSearchFeedFilterItemClick(item);
        }
    }

    public interface OnInlineFilterItemClickListener {
        void onInlineSearchFeedFilterItemClick(InlineSearchFeedFilterItem inlineSearchFeedFilterItem);
    }

    private ExploreFeedItemPresenter() {
    }

    public static Optional<EpoxyModel<?>> buildModelForListPosition(Queue<InlineSearchFeedItem> itemQueue, int currentListIndex, OnInlineFilterItemClickListener clickListener, ExploreJitneyLogger logger) {
        InlineSearchFeedItem nextItem = (InlineSearchFeedItem) itemQueue.peek();
        if (nextItem == null) {
            return Optional.absent();
        }
        if (nextItem.getPosition() == currentListIndex) {
            itemQueue.remove();
            switch (nextItem.getType()) {
                case Filter:
                    InlineSearchFeedFilterItem filterItem = nextItem.getFilter();
                    if (filterItem.getType() != null && filterItem.getType().enabled) {
                        return Optional.m1897of(new GoldenTicketEpoxyModel(filterItem, clickListener, logger));
                    }
                case Message:
                    return Optional.m1897of(new InterstitialEpoxyModel_().text(nextItem.getMessage().getText()));
                default:
                    BugsnagWrapper.notify((Throwable) new UnsupportedOperationException("Unknown golden ticket type " + nextItem.getType()));
                    break;
            }
        }
        return Optional.absent();
    }

    public static void trackGoldenTicketImpression(EpoxyModel<?> model, ExploreJitneyLogger logger) {
        logger.goldenTicketImpression(((GoldenTicketEpoxyModel) model).filterItem);
    }

    public static void adjustPositionsForGrid(List<InlineSearchFeedItem> searchFeedItems, int listSize) {
        boolean indexIsEven;
        boolean itemHasEvenPosition;
        Collections.sort(new ArrayList(searchFeedItems), ExploreFeedItemPresenter$$Lambda$1.lambdaFactory$());
        for (int i = 0; i < searchFeedItems.size(); i++) {
            InlineSearchFeedItem item = (InlineSearchFeedItem) searchFeedItems.get(i);
            int originalPosition = item.getPosition();
            if ((i & 1) == 0) {
                indexIsEven = true;
            } else {
                indexIsEven = false;
            }
            if ((originalPosition & 1) == 0) {
                itemHasEvenPosition = true;
            } else {
                itemHasEvenPosition = false;
            }
            if (indexIsEven != itemHasEvenPosition) {
                if (originalPosition + 1 < listSize) {
                    item.setPosition(originalPosition + 1);
                } else {
                    item.setPosition(originalPosition - 1);
                }
            }
        }
    }

    static /* synthetic */ int lambda$adjustPositionsForGrid$0(InlineSearchFeedItem i1, InlineSearchFeedItem i2) {
        return i1.getPosition() - i2.getPosition();
    }
}
