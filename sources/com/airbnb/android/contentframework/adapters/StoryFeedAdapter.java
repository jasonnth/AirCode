package com.airbnb.android.contentframework.adapters;

import android.content.Context;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment.Mode;
import com.airbnb.android.contentframework.interfaces.StoryFeedCardContainer;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCardFeedItemEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryFeedTopTileRowEpoxyModel_;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public class StoryFeedAdapter extends AirEpoxyAdapter implements StoryFeedCardContainer {
    private static final int STORY_CARD_OFFSET = 1;
    private final ListSpacerEpoxyModel_ composerButtonSpacerModel = new ListSpacerEpoxyModel_();
    private final AirEpoxyModel headerModel;
    private final Listener listener;
    private final Mode mode;
    private final LoadingRowEpoxyModel_ paginationLoader = new LoadingRowEpoxyModel_();
    private StoryFeedTopTileRowEpoxyModel_ topTileRowEpoxyModel;

    public interface Listener {
        void onPaginationLoaderDisplayed();

        void onStoryClicked(Article article, int i);
    }

    public StoryFeedAdapter(Context context, Listener listener2, Mode mode2) {
        this.listener = listener2;
        this.mode = mode2;
        if (mode2 == Mode.Feed) {
            this.headerModel = new DocumentMarqueeEpoxyModel_().titleRes(C5709R.string.story_feed_page_title).captionRes(C5709R.string.story_feed_page_subtitle);
            this.composerButtonSpacerModel.spaceHeight(context.getResources().getDimensionPixelOffset(C5709R.dimen.story_feed_bottom_spacer));
        } else {
            this.headerModel = new ListSpacerEpoxyModel_().spaceHeight(ViewUtils.getActionBarHeight(context) + context.getResources().getDimensionPixelOffset(C5709R.dimen.n2_vertical_padding_medium));
        }
        this.models.add(this.headerModel);
    }

    public void setStories(List<StoryFeedTopTile> topTiles, List<Article> articles, boolean hasNextPage) {
        removeAllAfterModel(this.headerModel);
        addTopTilesRow(topTiles);
        appendStories(articles, hasNextPage);
    }

    private void addTopTilesRow(List<StoryFeedTopTile> topTiles) {
        if (topTiles == null || topTiles.isEmpty()) {
            this.topTileRowEpoxyModel = null;
        } else if (topTiles.size() < 3) {
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Not enough top tiles in server response."));
            this.topTileRowEpoxyModel = null;
        } else {
            this.topTileRowEpoxyModel = new StoryFeedTopTileRowEpoxyModel_().topTileList(topTiles);
            this.models.add(this.topTileRowEpoxyModel);
        }
    }

    public void deleteStoryWithId(long storyId) {
        StoryCardFeedItemEpoxyModel_ deletedStory = getModel(storyId);
        if (deletedStory != null) {
            this.models.remove(deletedStory);
            notifyItemRemoved(getModelPosition(deletedStory));
        }
    }

    public void appendStories(List<Article> articles, boolean hasNextPage) {
        this.models.remove(this.paginationLoader);
        this.models.remove(this.composerButtonSpacerModel);
        for (Article article : articles) {
            StoryCardFeedItemEpoxyModel_ model = new StoryCardFeedItemEpoxyModel_().article(article);
            model.onClickListener(StoryFeedAdapter$$Lambda$1.lambdaFactory$(this, article, model));
            this.models.add(model);
        }
        if (hasNextPage) {
            this.models.add(this.paginationLoader);
        }
        if (this.mode == Mode.Feed) {
            this.models.add(this.composerButtonSpacerModel);
        }
        notifyDataSetChanged();
    }

    public void onStoryCommentChanged(long storyId, int commentCount) {
        StoryCardFeedItemEpoxyModel_ model = getModel(storyId);
        if (model != null && model.article().getCommentCount() != commentCount) {
            model.article().setCommentCount(commentCount);
            notifyModelChanged(model);
        }
    }

    public void onStoryLikeChanged(long storyId, int likeDelta) {
        StoryCardFeedItemEpoxyModel_ model = getModel(storyId);
        if (model != null) {
            Article article = model.article();
            article.setLikeCount(Math.max(0, article.getLikeCount() + likeDelta));
            notifyModelChanged(model);
        }
    }

    public void onStoryEngagementChanged(long storyId, int likeCount, int commentCount) {
        StoryCardFeedItemEpoxyModel_ model = getModel(storyId);
        if (model != null) {
            Article article = model.article();
            if (article.getLikeCount() != likeCount || article.getCommentCount() != commentCount) {
                article.setCommentCount(commentCount);
                article.setLikeCount(likeCount);
                notifyModelChanged(model);
            }
        }
    }

    private StoryCardFeedItemEpoxyModel_ getModel(long storyId) {
        for (EpoxyModel model : this.models) {
            if ((model instanceof StoryCardFeedItemEpoxyModel_) && ((StoryCardFeedItemEpoxyModel_) model).article().getId() == storyId) {
                return (StoryCardFeedItemEpoxyModel_) model;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position) {
        if (model == this.paginationLoader) {
            this.listener.onPaginationLoaderDisplayed();
        }
    }

    public int getStoryCardOffset() {
        return (this.topTileRowEpoxyModel == null ? 0 : 1) + 1;
    }

    public boolean isFullSpanRow(int position) {
        EpoxyModel model = (EpoxyModel) this.models.get(position);
        return model == this.headerModel || model == this.paginationLoader || model == this.composerButtonSpacerModel || model == this.topTileRowEpoxyModel;
    }
}
