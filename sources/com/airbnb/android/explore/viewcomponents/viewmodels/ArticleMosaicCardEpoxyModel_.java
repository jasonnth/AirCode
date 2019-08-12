package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Article;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.MosaicCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ArticleMosaicCardEpoxyModel_ extends ArticleMosaicCardEpoxyModel implements GeneratedModel<MosaicCard> {
    private OnModelBoundListener<ArticleMosaicCardEpoxyModel_, MosaicCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ArticleMosaicCardEpoxyModel_, MosaicCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MosaicCard object, int position) {
        if (this.cardClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.cardClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(MosaicCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ArticleMosaicCardEpoxyModel_ onBind(OnModelBoundListener<ArticleMosaicCardEpoxyModel_, MosaicCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MosaicCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ArticleMosaicCardEpoxyModel_ onUnbind(OnModelUnboundListener<ArticleMosaicCardEpoxyModel_, MosaicCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ article(Article article) {
        onMutation();
        this.article = article;
        return this;
    }

    public Article article() {
        return this.article;
    }

    public ArticleMosaicCardEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public ArticleMosaicCardEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public ArticleMosaicCardEpoxyModel_ cardClickListener(OnModelClickListener<ArticleMosaicCardEpoxyModel_, MosaicCard> cardClickListener) {
        onMutation();
        if (cardClickListener == null) {
            this.cardClickListener = null;
        } else {
            this.cardClickListener = new WrappedEpoxyModelClickListener(this, cardClickListener);
        }
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ cardClickListener(OnClickListener cardClickListener) {
        onMutation();
        this.cardClickListener = cardClickListener;
        return this;
    }

    public OnClickListener cardClickListener() {
        return this.cardClickListener;
    }

    public ArticleMosaicCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ArticleMosaicCardEpoxyModel_ m5807id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ArticleMosaicCardEpoxyModel_ m5812id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ArticleMosaicCardEpoxyModel_ m5808id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ArticleMosaicCardEpoxyModel_ m5809id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ArticleMosaicCardEpoxyModel_ m5811id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ArticleMosaicCardEpoxyModel_ m5810id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public ArticleMosaicCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.article = null;
        this.loading = false;
        this.displayOptions = null;
        this.cardClickListener = null;
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
        if (!(o instanceof ArticleMosaicCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ArticleMosaicCardEpoxyModel_ that = (ArticleMosaicCardEpoxyModel_) o;
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
        if (this.loading != that.loading) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
            return false;
        }
        if (this.cardClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.cardClickListener == null)) {
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
        int i5;
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.article != null) {
            i2 = this.article.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.loading) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.displayOptions != null) {
            i4 = this.displayOptions.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.cardClickListener == null) {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.numCarouselItemsShown != null) {
            i7 = this.numCarouselItemsShown.hashCode();
        }
        return i13 + i7;
    }

    public String toString() {
        return "ArticleMosaicCardEpoxyModel_{article=" + this.article + ", loading=" + this.loading + ", displayOptions=" + this.displayOptions + ", cardClickListener=" + this.cardClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
