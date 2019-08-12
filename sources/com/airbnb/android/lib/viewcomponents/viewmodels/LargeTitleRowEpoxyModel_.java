package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.BigNumberRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class LargeTitleRowEpoxyModel_ extends LargeTitleRowEpoxyModel implements GeneratedModel<BigNumberRow> {
    private OnModelBoundListener<LargeTitleRowEpoxyModel_, BigNumberRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<LargeTitleRowEpoxyModel_, BigNumberRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, BigNumberRow object, int position) {
    }

    public void handlePostBind(BigNumberRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public LargeTitleRowEpoxyModel_ onBind(OnModelBoundListener<LargeTitleRowEpoxyModel_, BigNumberRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(BigNumberRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public LargeTitleRowEpoxyModel_ onUnbind(OnModelUnboundListener<LargeTitleRowEpoxyModel_, BigNumberRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public LargeTitleRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public LargeTitleRowEpoxyModel_ primarySubtitle(CharSequence primarySubtitle) {
        onMutation();
        this.primarySubtitle = primarySubtitle;
        return this;
    }

    public CharSequence primarySubtitle() {
        return this.primarySubtitle;
    }

    public LargeTitleRowEpoxyModel_ secondarySubtitle(CharSequence secondarySubtitle) {
        onMutation();
        this.secondarySubtitle = secondarySubtitle;
        return this;
    }

    public CharSequence secondarySubtitle() {
        return this.secondarySubtitle;
    }

    public LargeTitleRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public LargeTitleRowEpoxyModel_ primarySubtitleRes(int primarySubtitleRes) {
        onMutation();
        this.primarySubtitleRes = primarySubtitleRes;
        return this;
    }

    public int primarySubtitleRes() {
        return this.primarySubtitleRes;
    }

    public LargeTitleRowEpoxyModel_ secondarySubtitleRes(int secondarySubtitleRes) {
        onMutation();
        this.secondarySubtitleRes = secondarySubtitleRes;
        return this;
    }

    public int secondarySubtitleRes() {
        return this.secondarySubtitleRes;
    }

    public LargeTitleRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public LargeTitleRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public LargeTitleRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public LargeTitleRowEpoxyModel_ m6143id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public LargeTitleRowEpoxyModel_ m6148id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public LargeTitleRowEpoxyModel_ m6144id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public LargeTitleRowEpoxyModel_ m6145id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public LargeTitleRowEpoxyModel_ m6147id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public LargeTitleRowEpoxyModel_ m6146id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public LargeTitleRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public LargeTitleRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public LargeTitleRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public LargeTitleRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public LargeTitleRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_large_title_row;
    }

    public LargeTitleRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.primarySubtitle = null;
        this.secondarySubtitle = null;
        this.titleRes = 0;
        this.primarySubtitleRes = 0;
        this.secondarySubtitleRes = 0;
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
        if (!(o instanceof LargeTitleRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        LargeTitleRowEpoxyModel_ that = (LargeTitleRowEpoxyModel_) o;
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
        if (this.primarySubtitle != null) {
            if (!this.primarySubtitle.equals(that.primarySubtitle)) {
                return false;
            }
        } else if (that.primarySubtitle != null) {
            return false;
        }
        if (this.secondarySubtitle != null) {
            if (!this.secondarySubtitle.equals(that.secondarySubtitle)) {
                return false;
            }
        } else if (that.secondarySubtitle != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.primarySubtitleRes != that.primarySubtitleRes || this.secondarySubtitleRes != that.secondarySubtitleRes) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i5 = 0;
        }
        int i7 = (hashCode + i5) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.primarySubtitle != null) {
            i2 = this.primarySubtitle.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.secondarySubtitle != null) {
            i3 = this.secondarySubtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((((((i9 + i3) * 31) + this.titleRes) * 31) + this.primarySubtitleRes) * 31) + this.secondarySubtitleRes) * 31;
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
        return "LargeTitleRowEpoxyModel_{title=" + this.title + ", primarySubtitle=" + this.primarySubtitle + ", secondarySubtitle=" + this.secondarySubtitle + ", titleRes=" + this.titleRes + ", primarySubtitleRes=" + this.primarySubtitleRes + ", secondarySubtitleRes=" + this.secondarySubtitleRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
