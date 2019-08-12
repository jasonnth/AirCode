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
import com.airbnb.p027n2.components.LeftAlignedImageRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class LeftAlignedImageRowEpoxyModel_ extends LeftAlignedImageRowEpoxyModel implements GeneratedModel<LeftAlignedImageRow> {
    private OnModelBoundListener<LeftAlignedImageRowEpoxyModel_, LeftAlignedImageRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<LeftAlignedImageRowEpoxyModel_, LeftAlignedImageRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, LeftAlignedImageRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(LeftAlignedImageRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public LeftAlignedImageRowEpoxyModel_ onBind(OnModelBoundListener<LeftAlignedImageRowEpoxyModel_, LeftAlignedImageRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(LeftAlignedImageRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public LeftAlignedImageRowEpoxyModel_ onUnbind(OnModelUnboundListener<LeftAlignedImageRowEpoxyModel_, LeftAlignedImageRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public LeftAlignedImageRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public LeftAlignedImageRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public LeftAlignedImageRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public LeftAlignedImageRowEpoxyModel_ titleStyle(int titleStyle) {
        onMutation();
        this.titleStyle = titleStyle;
        return this;
    }

    public int titleStyle() {
        return this.titleStyle;
    }

    public LeftAlignedImageRowEpoxyModel_ subtitleStyle(int subtitleStyle) {
        onMutation();
        this.subtitleStyle = subtitleStyle;
        return this;
    }

    public int subtitleStyle() {
        return this.subtitleStyle;
    }

    public LeftAlignedImageRowEpoxyModel_ imageDrawableRes(int imageDrawableRes) {
        onMutation();
        this.imageDrawableRes = imageDrawableRes;
        return this;
    }

    public int imageDrawableRes() {
        return this.imageDrawableRes;
    }

    public LeftAlignedImageRowEpoxyModel_ clickListener(OnModelClickListener<LeftAlignedImageRowEpoxyModel_, LeftAlignedImageRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public LeftAlignedImageRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public LeftAlignedImageRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public LeftAlignedImageRowEpoxyModel_ m5026id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public LeftAlignedImageRowEpoxyModel_ m5031id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public LeftAlignedImageRowEpoxyModel_ m5027id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public LeftAlignedImageRowEpoxyModel_ m5028id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public LeftAlignedImageRowEpoxyModel_ m5030id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public LeftAlignedImageRowEpoxyModel_ m5029id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public LeftAlignedImageRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_left_aligned_image_row;
    }

    public LeftAlignedImageRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.titleStyle = 0;
        this.subtitleStyle = 0;
        this.imageDrawableRes = 0;
        this.clickListener = null;
        this.enabled = false;
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
        if (!(o instanceof LeftAlignedImageRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        LeftAlignedImageRowEpoxyModel_ that = (LeftAlignedImageRowEpoxyModel_) o;
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
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.subtitleRes != that.subtitleRes || this.titleStyle != that.titleStyle || this.subtitleStyle != that.subtitleStyle || this.imageDrawableRes != that.imageDrawableRes) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null) || this.enabled != that.enabled) {
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
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((i8 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((((((((i9 + i3) * 31) + this.subtitleRes) * 31) + this.titleStyle) * 31) + this.subtitleStyle) * 31) + this.imageDrawableRes) * 31;
        if (this.clickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (!this.enabled) {
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
        return "LeftAlignedImageRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", titleStyle=" + this.titleStyle + ", subtitleStyle=" + this.subtitleStyle + ", imageDrawableRes=" + this.imageDrawableRes + ", clickListener=" + this.clickListener + ", enabled=" + this.enabled + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
