package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ArticleTag;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.MosaicCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;
import java.util.List;

public abstract class ArticleMosaicCardEpoxyModel extends AirEpoxyModel<MosaicCard> {
    Article article;
    OnClickListener cardClickListener;
    DisplayOptions displayOptions;
    boolean loading;

    public void bind(MosaicCard view) {
        String str = null;
        super.bind(view);
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view);
        }
        if (this.loading) {
            view.setupTitle(null, null);
            view.clearImages();
            view.setTag(null);
            view.setCommentCount(0);
            view.setLikeCount(0);
            view.showAccessory(false);
            return;
        }
        view.showAccessory(true);
        view.showCommentsAndLikes(true);
        view.setupTitle(this.article.getTitle(), null);
        if (!this.article.getTags().isEmpty()) {
            str = getTag();
        }
        view.setTag(str);
        view.setCommentCount(this.article.getCommentCount());
        view.setLikeCount(this.article.getLikeCount());
        view.setImages(getImagesForMosaicCard(this.article));
        view.setOnClickListener(this.cardClickListener);
    }

    public void unbind(MosaicCard view) {
        super.unbind(view);
        view.clearImages();
        view.setOnClickListener(null);
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        if (isCarousel()) {
            return C0857R.layout.model_mosaic_card_carousel;
        }
        if (isGrid()) {
            return C0857R.layout.model_mosaic_card_grid;
        }
        return C0857R.layout.model_mosaic_card;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }

    private boolean isGrid() {
        return this.displayOptions != null && this.displayOptions.isGrid();
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }

    private String getTag() {
        if (!TextUtils.isEmpty(this.article.getAuthor())) {
            return this.article.getAuthor();
        }
        if (this.article.getTags().isEmpty()) {
            return null;
        }
        return ((ArticleTag) this.article.getTags().get(0)).getText();
    }

    private List<String> getImagesForMosaicCard(Article article2) {
        List<String> images = new ArrayList<>();
        if (article2.getCoverImageUrl() != null) {
            images.add(article2.getCoverImageUrl());
        }
        List<String> additionalCovers = article2.getAdditionalCoverImages();
        if (additionalCovers.size() > 0) {
            images.add(additionalCovers.get(0));
        }
        if (additionalCovers.size() > 1) {
            images.add(additionalCovers.get(1));
        }
        return images;
    }
}
