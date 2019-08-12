package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.photorearranger.RearrangablePhotoRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.imaging.Image;

public class RearrangablePhotoRowEpoxyModel_ extends RearrangablePhotoRowEpoxyModel implements GeneratedModel<RearrangablePhotoRow> {
    private OnModelBoundListener<RearrangablePhotoRowEpoxyModel_, RearrangablePhotoRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<RearrangablePhotoRowEpoxyModel_, RearrangablePhotoRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RearrangablePhotoRow object, int position) {
    }

    public void handlePostBind(RearrangablePhotoRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public RearrangablePhotoRowEpoxyModel_ onBind(OnModelBoundListener<RearrangablePhotoRowEpoxyModel_, RearrangablePhotoRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RearrangablePhotoRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public RearrangablePhotoRowEpoxyModel_ onUnbind(OnModelUnboundListener<RearrangablePhotoRowEpoxyModel_, RearrangablePhotoRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ image(Image image) {
        onMutation();
        this.image = image;
        return this;
    }

    public Image image() {
        return this.image;
    }

    public RearrangablePhotoRowEpoxyModel_ labelRes(int labelRes) {
        onMutation();
        this.labelRes = labelRes;
        return this;
    }

    public int labelRes() {
        return this.labelRes;
    }

    public RearrangablePhotoRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public RearrangablePhotoRowEpoxyModel_ m5386id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public RearrangablePhotoRowEpoxyModel_ m5391id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public RearrangablePhotoRowEpoxyModel_ m5387id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public RearrangablePhotoRowEpoxyModel_ m5388id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public RearrangablePhotoRowEpoxyModel_ m5390id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public RearrangablePhotoRowEpoxyModel_ m5389id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public RearrangablePhotoRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_rearrangable_photo_row;
    }

    public RearrangablePhotoRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.image = null;
        this.labelRes = 0;
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
        if (!(o instanceof RearrangablePhotoRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        RearrangablePhotoRowEpoxyModel_ that = (RearrangablePhotoRowEpoxyModel_) o;
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
        if (this.image != null) {
            if (!this.image.equals(that.image)) {
                return false;
            }
        } else if (that.image != null) {
            return false;
        }
        if (this.labelRes != that.labelRes) {
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i5 = (hashCode + i3) * 31;
        if (this.image != null) {
            i = this.image.hashCode();
        } else {
            i = 0;
        }
        int i6 = (((i5 + i) * 31) + this.labelRes) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "RearrangablePhotoRowEpoxyModel_{image=" + this.image + ", labelRes=" + this.labelRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
