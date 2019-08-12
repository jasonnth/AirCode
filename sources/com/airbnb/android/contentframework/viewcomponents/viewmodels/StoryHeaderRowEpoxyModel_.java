package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.views.StoryHeaderRowView;
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

public class StoryHeaderRowEpoxyModel_ extends StoryHeaderRowEpoxyModel implements GeneratedModel<StoryHeaderRowView> {
    private OnModelBoundListener<StoryHeaderRowEpoxyModel_, StoryHeaderRowView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StoryHeaderRowEpoxyModel_, StoryHeaderRowView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StoryHeaderRowView object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(StoryHeaderRowView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StoryHeaderRowEpoxyModel_ onBind(OnModelBoundListener<StoryHeaderRowEpoxyModel_, StoryHeaderRowView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StoryHeaderRowView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StoryHeaderRowEpoxyModel_ onUnbind(OnModelUnboundListener<StoryHeaderRowEpoxyModel_, StoryHeaderRowView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StoryHeaderRowEpoxyModel_ article(Article article) {
        onMutation();
        this.article = article;
        return this;
    }

    public Article article() {
        return this.article;
    }

    public StoryHeaderRowEpoxyModel_ onClickListener(OnModelClickListener<StoryHeaderRowEpoxyModel_, StoryHeaderRowView> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public StoryHeaderRowEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public StoryHeaderRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StoryHeaderRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StoryHeaderRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StoryHeaderRowEpoxyModel_ m4200id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StoryHeaderRowEpoxyModel_ m4205id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StoryHeaderRowEpoxyModel_ m4201id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StoryHeaderRowEpoxyModel_ m4202id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StoryHeaderRowEpoxyModel_ m4204id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StoryHeaderRowEpoxyModel_ m4203id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StoryHeaderRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StoryHeaderRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StoryHeaderRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StoryHeaderRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StoryHeaderRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_story_header_row;
    }

    public StoryHeaderRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.article = null;
        this.onClickListener = null;
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
        if (!(o instanceof StoryHeaderRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StoryHeaderRowEpoxyModel_ that = (StoryHeaderRowEpoxyModel_) o;
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.article != null) {
            i2 = this.article.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.onClickListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "StoryHeaderRowEpoxyModel_{article=" + this.article + ", onClickListener=" + this.onClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
