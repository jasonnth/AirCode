package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ImpactMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ImpactMarqueeEpoxyModel_ extends ImpactMarqueeEpoxyModel implements GeneratedModel<ImpactMarquee> {
    private OnModelBoundListener<ImpactMarqueeEpoxyModel_, ImpactMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ImpactMarqueeEpoxyModel_, ImpactMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ImpactMarquee object, int position) {
    }

    public void handlePostBind(ImpactMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ImpactMarqueeEpoxyModel_ onBind(OnModelBoundListener<ImpactMarqueeEpoxyModel_, ImpactMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ImpactMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ImpactMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<ImpactMarqueeEpoxyModel_, ImpactMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ImpactMarqueeEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ImpactMarqueeEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public ImpactMarqueeEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public ImpactMarqueeEpoxyModel_ backgroundColor(int backgroundColor) {
        onMutation();
        this.backgroundColor = backgroundColor;
        return this;
    }

    public int backgroundColor() {
        return this.backgroundColor;
    }

    public ImpactMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ImpactMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ImpactMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ImpactMarqueeEpoxyModel_ m4810id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ImpactMarqueeEpoxyModel_ m4815id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ImpactMarqueeEpoxyModel_ m4811id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ImpactMarqueeEpoxyModel_ m4812id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ImpactMarqueeEpoxyModel_ m4814id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ImpactMarqueeEpoxyModel_ m4813id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ImpactMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ImpactMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ImpactMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ImpactMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ImpactMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_impact_marquee;
    }

    public ImpactMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleRes = 0;
        this.title = null;
        this.subtitle = null;
        this.backgroundColor = 0;
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
        if (!(o instanceof ImpactMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ImpactMarqueeEpoxyModel_ that = (ImpactMarqueeEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.titleRes != that.titleRes) {
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
        if (this.backgroundColor != that.backgroundColor) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (((hashCode + i4) * 31) + this.titleRes) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.subtitle != null) {
            i2 = this.subtitle.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((i7 + i2) * 31) + this.backgroundColor) * 31;
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
        return "ImpactMarqueeEpoxyModel_{titleRes=" + this.titleRes + ", title=" + this.title + ", subtitle=" + this.subtitle + ", backgroundColor=" + this.backgroundColor + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
