package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.PosterRow;
import com.airbnb.p027n2.components.PosterRow.PosterOrientation;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PosterRowEpoxyModel_ extends PosterRowEpoxyModel implements GeneratedModel<PosterRow> {
    private OnModelBoundListener<PosterRowEpoxyModel_, PosterRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PosterRowEpoxyModel_, PosterRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PosterRow object, int position) {
    }

    public void handlePostBind(PosterRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PosterRowEpoxyModel_ onBind(OnModelBoundListener<PosterRowEpoxyModel_, PosterRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PosterRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PosterRowEpoxyModel_ onUnbind(OnModelUnboundListener<PosterRowEpoxyModel_, PosterRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PosterRowEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public PosterRowEpoxyModel_ subtitle(String subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public String subtitle() {
        return this.subtitle;
    }

    public PosterRowEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public PosterRowEpoxyModel_ posterOrientation(PosterOrientation posterOrientation) {
        onMutation();
        this.posterOrientation = posterOrientation;
        return this;
    }

    public PosterOrientation posterOrientation() {
        return this.posterOrientation;
    }

    public PosterRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PosterRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PosterRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PosterRowEpoxyModel_ m5314id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PosterRowEpoxyModel_ m5319id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PosterRowEpoxyModel_ m5315id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PosterRowEpoxyModel_ m5316id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PosterRowEpoxyModel_ m5318id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PosterRowEpoxyModel_ m5317id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PosterRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PosterRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PosterRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PosterRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PosterRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_poster_row;
    }

    public PosterRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.imageUrl = null;
        this.posterOrientation = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PosterRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PosterRowEpoxyModel_ that = (PosterRowEpoxyModel_) o;
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
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.posterOrientation != null) {
            if (!this.posterOrientation.equals(that.posterOrientation)) {
                return false;
            }
        } else if (that.posterOrientation != null) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i6 = 0;
        }
        int i8 = (hashCode + i6) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i9 = (i8 + i) * 31;
        if (this.subtitle != null) {
            i2 = this.subtitle.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.imageUrl != null) {
            i3 = this.imageUrl.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.posterOrientation != null) {
            i4 = this.posterOrientation.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
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
        return "PosterRowEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", imageUrl=" + this.imageUrl + ", posterOrientation=" + this.posterOrientation + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
