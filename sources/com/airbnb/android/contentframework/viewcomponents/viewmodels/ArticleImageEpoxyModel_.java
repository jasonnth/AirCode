package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleImageEpoxyModel.ArticleImageClickListener;
import com.airbnb.android.core.models.StoryImageDetails;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class ArticleImageEpoxyModel_ extends ArticleImageEpoxyModel implements GeneratedModel<AirImageView> {
    private OnModelBoundListener<ArticleImageEpoxyModel_, AirImageView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ArticleImageEpoxyModel_, AirImageView> onModelUnboundListener_epoxyGeneratedModel;

    public ArticleImageEpoxyModel_(StoryImageDetails details) {
        super(details);
    }

    public void handlePreBind(EpoxyViewHolder holder, AirImageView object, int position) {
    }

    public void handlePostBind(AirImageView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ArticleImageEpoxyModel_ onBind(OnModelBoundListener<ArticleImageEpoxyModel_, AirImageView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(AirImageView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ArticleImageEpoxyModel_ onUnbind(OnModelUnboundListener<ArticleImageEpoxyModel_, AirImageView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ArticleImageEpoxyModel_ callback(ArticleImageClickListener callback) {
        onMutation();
        this.callback = callback;
        return this;
    }

    public ArticleImageClickListener callback() {
        return this.callback;
    }

    public ArticleImageEpoxyModel_ articleId(long articleId) {
        onMutation();
        this.articleId = articleId;
        return this;
    }

    public long articleId() {
        return this.articleId;
    }

    public ArticleImageEpoxyModel_ photoIdx(int photoIdx) {
        onMutation();
        this.photoIdx = photoIdx;
        return this;
    }

    public int photoIdx() {
        return this.photoIdx;
    }

    public ArticleImageEpoxyModel_ hasSubElement(boolean hasSubElement) {
        onMutation();
        this.hasSubElement = hasSubElement;
        return this;
    }

    public boolean hasSubElement() {
        return this.hasSubElement;
    }

    public ArticleImageEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ArticleImageEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ArticleImageEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ArticleImageEpoxyModel_ m4092id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ArticleImageEpoxyModel_ m4097id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ArticleImageEpoxyModel_ m4093id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ArticleImageEpoxyModel_ m4094id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ArticleImageEpoxyModel_ m4096id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ArticleImageEpoxyModel_ m4095id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ArticleImageEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ArticleImageEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ArticleImageEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ArticleImageEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ArticleImageEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_article_image;
    }

    public ArticleImageEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.callback = null;
        this.articleId = 0;
        this.photoIdx = 0;
        this.hasSubElement = false;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArticleImageEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ArticleImageEpoxyModel_ that = (ArticleImageEpoxyModel_) o;
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
        if (this.callback == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (that.callback == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z2 != z3 || this.articleId != that.articleId || this.photoIdx != that.photoIdx || this.hasSubElement != that.hasSubElement) {
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
        if (this.callback != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i7 = (((((i6 + i2) * 31) + ((int) (this.articleId ^ (this.articleId >>> 32)))) * 31) + this.photoIdx) * 31;
        if (!this.hasSubElement) {
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
        return "ArticleImageEpoxyModel_{callback=" + this.callback + ", articleId=" + this.articleId + ", photoIdx=" + this.photoIdx + ", hasSubElement=" + this.hasSubElement + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
