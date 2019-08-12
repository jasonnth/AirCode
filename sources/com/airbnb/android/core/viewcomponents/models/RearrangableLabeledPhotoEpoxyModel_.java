package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.photorearranger.RearrangableLabeledPhotoCell;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class RearrangableLabeledPhotoEpoxyModel_ extends RearrangableLabeledPhotoEpoxyModel implements GeneratedModel<RearrangableLabeledPhotoCell> {
    private OnModelBoundListener<RearrangableLabeledPhotoEpoxyModel_, RearrangableLabeledPhotoCell> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<RearrangableLabeledPhotoEpoxyModel_, RearrangableLabeledPhotoCell> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RearrangableLabeledPhotoCell object, int position) {
    }

    public void handlePostBind(RearrangableLabeledPhotoCell object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public RearrangableLabeledPhotoEpoxyModel_ onBind(OnModelBoundListener<RearrangableLabeledPhotoEpoxyModel_, RearrangableLabeledPhotoCell> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RearrangableLabeledPhotoCell object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public RearrangableLabeledPhotoEpoxyModel_ onUnbind(OnModelUnboundListener<RearrangableLabeledPhotoEpoxyModel_, RearrangableLabeledPhotoCell> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ imageUrl(CharSequence imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public CharSequence imageUrl() {
        return this.imageUrl;
    }

    public RearrangableLabeledPhotoEpoxyModel_ label(CharSequence label) {
        onMutation();
        this.label = label;
        return this;
    }

    public CharSequence label() {
        return this.label;
    }

    public RearrangableLabeledPhotoEpoxyModel_ placeHolderText(CharSequence placeHolderText) {
        onMutation();
        this.placeHolderText = placeHolderText;
        return this;
    }

    public CharSequence placeHolderText() {
        return this.placeHolderText;
    }

    public RearrangableLabeledPhotoEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoEpoxyModel_ m5374id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoEpoxyModel_ m5379id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoEpoxyModel_ m5375id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoEpoxyModel_ m5376id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoEpoxyModel_ m5378id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoEpoxyModel_ m5377id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ show() {
        super.show();
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public RearrangableLabeledPhotoEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_rearrangable_labeled_photo;
    }

    public RearrangableLabeledPhotoEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.imageUrl = null;
        this.label = null;
        this.placeHolderText = null;
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
        if (!(o instanceof RearrangableLabeledPhotoEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        RearrangableLabeledPhotoEpoxyModel_ that = (RearrangableLabeledPhotoEpoxyModel_) o;
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
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.label != null) {
            if (!this.label.equals(that.label)) {
                return false;
            }
        } else if (that.label != null) {
            return false;
        }
        if (this.placeHolderText != null) {
            if (!this.placeHolderText.equals(that.placeHolderText)) {
                return false;
            }
        } else if (that.placeHolderText != null) {
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
        if (this.imageUrl != null) {
            i = this.imageUrl.hashCode();
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.label != null) {
            i2 = this.label.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.placeHolderText != null) {
            i3 = this.placeHolderText.hashCode();
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
        return "RearrangableLabeledPhotoEpoxyModel_{imageUrl=" + this.imageUrl + ", label=" + this.label + ", placeHolderText=" + this.placeHolderText + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
