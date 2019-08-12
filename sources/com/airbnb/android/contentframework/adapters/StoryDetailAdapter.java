package com.airbnb.android.contentframework.adapters;

import android.content.Context;
import android.view.View;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.activities.StorySearchResultActivity;
import com.airbnb.android.contentframework.controller.CommentActionController;
import com.airbnb.android.contentframework.interfaces.StoryFeedCardContainer;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleImageEpoxyModel.ArticleImageClickListener;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleImageEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleSectionHeaderEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleTextEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.SeeAllStoriesEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCardFeedItemEpoxyModel;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCardFeedItemEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryHeaderRowEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryProductLinkElementEpoxyModel.StoryProductLinkClickDelegate;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryProductLinkElementEpoxyModel_;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryReportRowEpoxyModel_;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.interfaces.Parallaxable;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.models.StoryElement;
import com.airbnb.android.core.models.StoryProductLinkDetails;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListableType;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class StoryDetailAdapter extends AirEpoxyAdapter implements StoryFeedCardContainer {
    private Article article;
    private List<EpoxyModel<?>> articleCommentModels;
    private final CommentActionController commentActionController;
    private int commentSectionIndex;
    private final Context context;
    private final Delegate delegate;
    private final List<String> imageElementUrls = new LinkedList();
    private final LoadingRowEpoxyModel_ loadingArticalModel = new LoadingRowEpoxyModel_();
    private final List<StoryProductLinkElementEpoxyModel_> productLinkElementEpoxyModels = new LinkedList();
    private final List<EpoxyModel<?>> relatedStoryModels = new ArrayList();
    private StoryReportRowEpoxyModel_ reportRowEpoxyModel;

    public interface Delegate extends ArticleImageClickListener, StoryProductLinkClickDelegate {
        void onAuthorRowClicked();

        void onClickRelatedArticle(Article article);

        void onReportStoryClicked();
    }

    public StoryDetailAdapter(Context context2, Article article2, Delegate delegate2, CommentActionController controller) {
        this.context = context2;
        this.delegate = delegate2;
        this.article = article2;
        this.commentActionController = controller;
        addHeaderElements();
        if (article2.getElements() == null) {
            this.models.add(this.loadingArticalModel);
        } else {
            loadArticleContent();
        }
    }

    public int getStoryCardOffset() {
        if (this.relatedStoryModels.isEmpty()) {
            return getItemCount();
        }
        return getModelPosition((EpoxyModel) this.relatedStoryModels.get(0));
    }

    public void setFullArticle(Article article2) {
        this.article = article2;
        int originalSize = this.models.size();
        this.models.remove(this.loadingArticalModel);
        loadArticleContent();
        notifyItemRangeChanged(originalSize - 1, this.models.size());
    }

    public void updateCommentSection(List<ArticleComment> topComments, int totalCommentCount) {
        Check.argument((this.article == null || this.article.getElements() == null) ? false : true);
        if (this.articleCommentModels != null && !this.articleCommentModels.isEmpty()) {
            this.models.removeAll(this.articleCommentModels);
            notifyItemRangeRemoved(this.commentSectionIndex, this.articleCommentModels.size());
        }
        addCommentSectionModels(topComments, totalCommentCount);
        notifyItemRangeInserted(this.commentSectionIndex, this.articleCommentModels.size());
    }

    public void onScroll() {
        Iterator it = getBoundViewHolders().iterator();
        while (it.hasNext()) {
            EpoxyModel viewModel = ((EpoxyViewHolder) it.next()).getModel();
            if (viewModel instanceof Parallaxable) {
                ((Parallaxable) viewModel).updateParallax();
            }
        }
    }

    public List<String> getImageElementUrls() {
        return this.imageElementUrls;
    }

    public void notifyCommentChange(ArticleCommentRowEpoxyModel model) {
        notifyModelChanged(model);
    }

    public void notifyWishListsChanged(WishListChangeInfo changeInfo) {
        if (changeInfo != null) {
            WishListableType type = changeInfo.getType();
            for (StoryProductLinkElementEpoxyModel_ model : this.productLinkElementEpoxyModels) {
                StoryProductLinkDetails details = model.details();
                if (type == details.getWishListableType() && changeInfo.getId() == details.getObjectId()) {
                    notifyModelChanged(model);
                    return;
                }
            }
        }
    }

    private void addHeaderElements() {
        this.models.add(new ToolbarSpacerEpoxyModel_());
        this.models.add(new StoryHeaderRowEpoxyModel_().onClickListener(StoryDetailAdapter$$Lambda$1.lambdaFactory$(this)).article(this.article));
    }

    private void loadArticleContent() {
        addBodyElementModels(this.article.getElements());
        addReportStoryRow();
        addAppendixSection();
        initializeCommentSection();
        addRelatedArticles();
    }

    private void addBodyElementModels(List<StoryElement> elements) {
        for (StoryElement element : elements) {
            if (element.getType() != null) {
                this.models.addAll(buildElementModels(element, false));
            }
        }
    }

    private void initializeCommentSection() {
        this.commentSectionIndex = this.models.size();
        this.models.add(new LinkActionRowEpoxyModel_().textRes(C5709R.string.story_add_comment).clickListener(StoryDetailAdapter$$Lambda$2.lambdaFactory$(this)).showDivider(true));
    }

    private void addReportStoryRow() {
        if (this.article.getUserFlag() == null) {
            this.reportRowEpoxyModel = new StoryReportRowEpoxyModel_().textRes(C5709R.string.story_action_report).clickListener(StoryDetailAdapter$$Lambda$3.lambdaFactory$(this));
        } else {
            this.reportRowEpoxyModel = new StoryReportRowEpoxyModel_().textRes(C5709R.string.story_action_reported).enabled(false);
        }
        this.models.add(this.reportRowEpoxyModel);
    }

    private void addAppendixSection() {
        StoryElement appendix = this.article.getAppendix();
        if (appendix != null) {
            this.models.addAll(buildElementModels(appendix, false));
        }
    }

    private List<AirEpoxyModel<?>> buildElementModels(StoryElement element, boolean isSubElement) {
        List<AirEpoxyModel<?>> modelList = new ArrayList<>();
        switch (element.getType()) {
            case Text:
                modelList.add(new ArticleTextEpoxyModel_(element));
                break;
            case Image:
                ArticleImageEpoxyModel_ viewModel = new ArticleImageEpoxyModel_(element.getImageDetails()).callback(this.delegate).articleId(this.article.getId()).photoIdx(this.imageElementUrls.size()).hasSubElement(element.hasSubElement());
                this.imageElementUrls.add(element.getImageDetails().getImageUrl());
                modelList.add(viewModel);
                break;
            case SectionHeader:
                modelList.add(new ArticleSectionHeaderEpoxyModel_(element.getText()));
                break;
            case ProductLink:
                StoryProductLinkElementEpoxyModel_ productLinkElementEpoxyModel_ = new StoryProductLinkElementEpoxyModel_().details(element.getProductLinkDetails()).isSubElement(isSubElement).delegate(this.delegate);
                this.productLinkElementEpoxyModels.add(productLinkElementEpoxyModel_);
                modelList.add(productLinkElementEpoxyModel_);
                break;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("unknown element type: " + element.getType()));
                break;
        }
        if (element.getSubElements() != null) {
            for (StoryElement subelement : element.getSubElements()) {
                modelList.addAll(buildElementModels(subelement, true));
            }
        }
        return modelList;
    }

    private void addCommentSectionModels(List<ArticleComment> topComments, int totalCommentCount) {
        this.articleCommentModels = new ArrayList();
        SectionHeaderEpoxyModel_ headerModel = new SectionHeaderEpoxyModel_().showDivider(true);
        if (totalCommentCount == 0) {
            headerModel.titleRes(C5709R.string.story_top_comments_empty_state_title);
        } else if (totalCommentCount == topComments.size()) {
            headerModel.title(this.context.getResources().getQuantityString(C5709R.plurals.comments, totalCommentCount, new Object[]{Integer.valueOf(totalCommentCount)}));
        } else {
            headerModel.titleRes(C5709R.string.content_framework_hot_comments).buttonTextRes(C5709R.string.see_all).buttonOnClickListener(StoryDetailAdapter$$Lambda$4.lambdaFactory$(this, totalCommentCount));
        }
        this.articleCommentModels.add(headerModel);
        this.articleCommentModels.add(new ListSpacerEpoxyModel_().spaceHeight(this.context.getResources().getDimensionPixelSize(C5709R.dimen.n2_vertical_padding_medium)));
        if (!topComments.isEmpty()) {
            List<ArticleCommentRowEpoxyModel_> commentModels = new ArrayList<>();
            for (ArticleComment comment : topComments) {
                commentModels.add(new ArticleCommentRowEpoxyModel_().comment(comment).showDivider(false).commentActionListener(this.commentActionController).m4080id(comment.getId()));
            }
            ((ArticleCommentRowEpoxyModel_) commentModels.get(commentModels.size() - 1)).showDivider(true);
            this.articleCommentModels.addAll(commentModels);
        }
        this.models.addAll(this.commentSectionIndex, this.articleCommentModels);
    }

    private void addRelatedArticles() {
        if (this.article.getRelatedArticles() != null && !this.article.getRelatedArticles().isEmpty()) {
            for (Article relatedArticle : this.article.getRelatedArticles()) {
                this.relatedStoryModels.add(new StoryCardFeedItemEpoxyModel_().article(relatedArticle).displayOptions(DisplayOptions.forStoryCard(this.context, DisplayType.Grid)).onClickListener(StoryDetailAdapter$$Lambda$5.lambdaFactory$(this, relatedArticle)));
            }
            if (this.article.getRelatedArticlesSeeAll() != null) {
                this.relatedStoryModels.add(new SeeAllStoriesEpoxyModel_().storySeeAllTile(this.article.getRelatedArticlesSeeAll()).onClickListener(StoryDetailAdapter$$Lambda$6.lambdaFactory$(this)));
            }
            this.models.add(new SectionHeaderEpoxyModel_().showDivider(true).titleRes(C5709R.string.related_stories_cluster_title));
            this.models.add(new ListSpacerEpoxyModel_().spaceHeight(this.context.getResources().getDimensionPixelSize(C5709R.dimen.story_element_inner_vertical_padding)));
            this.models.addAll(this.relatedStoryModels);
        }
    }

    static /* synthetic */ void lambda$addRelatedArticles$5(StoryDetailAdapter storyDetailAdapter, View v) {
        String searchTerm = storyDetailAdapter.article.getRelatedArticlesSeeAll().getSearchTerm();
        ContentFrameworkAnalytics.trackSeeAllRelatedTileClicked(storyDetailAdapter.article.getId(), searchTerm);
        storyDetailAdapter.context.startActivity(StorySearchResultActivity.intentForSearchTerm(storyDetailAdapter.context, searchTerm));
    }

    public void deleteStoryIfPresentInRelatedStories(long storyId) {
        for (EpoxyModel model : this.relatedStoryModels) {
            if ((model instanceof StoryCardFeedItemEpoxyModel) && ((StoryCardFeedItemEpoxyModel_) model).article().getId() == storyId) {
                this.models.remove(model);
                notifyItemChanged(getModelPosition(model));
            }
        }
    }

    public void onReportSuccess() {
        this.reportRowEpoxyModel.textRes(C5709R.string.story_action_reported).enabled(false);
        notifyModelChanged(this.reportRowEpoxyModel);
    }

    public boolean isRelatedArticleRow(int position) {
        return this.relatedStoryModels.contains(this.models.get(position));
    }
}
