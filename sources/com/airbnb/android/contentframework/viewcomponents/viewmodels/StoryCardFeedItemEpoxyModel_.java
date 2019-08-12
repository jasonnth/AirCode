package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.views.StoryCardFeedItem;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Article;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class StoryCardFeedItemEpoxyModel_ extends StoryCardFeedItemEpoxyModel implements GeneratedModel<StoryCardFeedItem> {
    private OnModelBoundListener<StoryCardFeedItemEpoxyModel_, StoryCardFeedItem> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StoryCardFeedItemEpoxyModel_, StoryCardFeedItem> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StoryCardFeedItem object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(StoryCardFeedItem object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StoryCardFeedItemEpoxyModel_ onBind(OnModelBoundListener<StoryCardFeedItemEpoxyModel_, StoryCardFeedItem> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StoryCardFeedItem object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StoryCardFeedItemEpoxyModel_ onUnbind(OnModelUnboundListener<StoryCardFeedItemEpoxyModel_, StoryCardFeedItem> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ article(Article article) {
        onMutation();
        this.article = article;
        return this;
    }

    public Article article() {
        return this.article;
    }

    public StoryCardFeedItemEpoxyModel_ onClickListener(OnModelClickListener<StoryCardFeedItemEpoxyModel_, StoryCardFeedItem> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public StoryCardFeedItemEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public StoryCardFeedItemEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StoryCardFeedItemEpoxyModel_ m4140id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StoryCardFeedItemEpoxyModel_ m4145id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StoryCardFeedItemEpoxyModel_ m4141id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StoryCardFeedItemEpoxyModel_ m4142id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StoryCardFeedItemEpoxyModel_ m4144id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StoryCardFeedItemEpoxyModel_ m4143id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_story_card_feed_item;
    }

    public StoryCardFeedItemEpoxyModel_ withGridPaddingLayout() {
        layout(C5709R.layout.view_holder_story_card_feed_item_grid_padding);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ withNoBottomPaddingLayout() {
        layout(C5709R.layout.view_holder_story_card_feed_item_no_bottom_padding);
        return this;
    }

    public StoryCardFeedItemEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.article = null;
        this.onClickListener = null;
        this.displayOptions = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof StoryCardFeedItemEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StoryCardFeedItemEpoxyModel_ that = (StoryCardFeedItemEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.article != null) {
            if (!this.article.equals(that.article)) {
                return false;
            }
        } else if (that.article != null) {
            return false;
        }
        if (this.onClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener == null)) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        if (this.numCarouselItemsShown != null) {
            if (!this.numCarouselItemsShown.equals(that.numCarouselItemsShown)) {
                return false;
            }
        } else if (that.numCarouselItemsShown != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.article != null) {
            i2 = this.article.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.onClickListener == null) {
            i5 = 0;
        }
        int i9 = (i8 + i5) * 31;
        if (this.displayOptions != null) {
            i3 = this.displayOptions.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.numCarouselItemsShown != null) {
            i6 = this.numCarouselItemsShown.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "StoryCardFeedItemEpoxyModel_{article=" + this.article + ", onClickListener=" + this.onClickListener + ", displayOptions=" + this.displayOptions + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
