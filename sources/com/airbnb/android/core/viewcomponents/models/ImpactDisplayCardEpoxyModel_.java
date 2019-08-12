package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.ImpactDisplayCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ImpactDisplayCardEpoxyModel_ extends ImpactDisplayCardEpoxyModel implements GeneratedModel<ImpactDisplayCard> {
    private OnModelBoundListener<ImpactDisplayCardEpoxyModel_, ImpactDisplayCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ImpactDisplayCardEpoxyModel_, ImpactDisplayCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ImpactDisplayCard object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ImpactDisplayCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ImpactDisplayCardEpoxyModel_ onBind(OnModelBoundListener<ImpactDisplayCardEpoxyModel_, ImpactDisplayCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ImpactDisplayCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ImpactDisplayCardEpoxyModel_ onUnbind(OnModelUnboundListener<ImpactDisplayCardEpoxyModel_, ImpactDisplayCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public ImpactDisplayCardEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public ImpactDisplayCardEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public ImpactDisplayCardEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public ImpactDisplayCardEpoxyModel_ clickListener(OnModelClickListener<ImpactDisplayCardEpoxyModel_, ImpactDisplayCard> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ImpactDisplayCardEpoxyModel_ backgroundColor(int backgroundColor) {
        onMutation();
        this.backgroundColor = backgroundColor;
        return this;
    }

    public int backgroundColor() {
        return this.backgroundColor;
    }

    public ImpactDisplayCardEpoxyModel_ labelRes(int labelRes) {
        onMutation();
        this.labelRes = labelRes;
        return this;
    }

    public int labelRes() {
        return this.labelRes;
    }

    public ImpactDisplayCardEpoxyModel_ style(int style) {
        onMutation();
        this.style = style;
        super.style(style);
        return this;
    }

    public int style() {
        return this.style;
    }

    public ImpactDisplayCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ImpactDisplayCardEpoxyModel_ m4798id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ImpactDisplayCardEpoxyModel_ m4803id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ImpactDisplayCardEpoxyModel_ m4799id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ImpactDisplayCardEpoxyModel_ m4800id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ImpactDisplayCardEpoxyModel_ m4802id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ImpactDisplayCardEpoxyModel_ m4801id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ImpactDisplayCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_impact_display_card;
    }

    public ImpactDisplayCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.loading = false;
        this.imageUrl = null;
        this.title = null;
        this.subtitle = null;
        this.clickListener = null;
        this.backgroundColor = 0;
        this.labelRes = 0;
        this.style = 0;
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
        if (!(o instanceof ImpactDisplayCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ImpactDisplayCardEpoxyModel_ that = (ImpactDisplayCardEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.loading != that.loading) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
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
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (that.clickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z2 != z3 || this.backgroundColor != that.backgroundColor || this.labelRes != that.labelRes || this.style != that.style) {
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
        int i6;
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.loading) {
            i2 = 1;
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
        if (this.title != null) {
            i4 = this.title.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.subtitle != null) {
            i5 = this.subtitle.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.clickListener == null) {
            i7 = 0;
        }
        int i14 = (((((((i13 + i7) * 31) + this.backgroundColor) * 31) + this.labelRes) * 31) + this.style) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "ImpactDisplayCardEpoxyModel_{loading=" + this.loading + ", imageUrl=" + this.imageUrl + ", title=" + this.title + ", subtitle=" + this.subtitle + ", clickListener=" + this.clickListener + ", backgroundColor=" + this.backgroundColor + ", labelRes=" + this.labelRes + ", style=" + this.style + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
