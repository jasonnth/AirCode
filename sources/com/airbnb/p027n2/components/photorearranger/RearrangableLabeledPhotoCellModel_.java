package com.airbnb.p027n2.components.photorearranger;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.StringAttributeData;
import com.airbnb.n2.R;
import com.airbnb.p027n2.epoxy.NoDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangableLabeledPhotoCellModel_ */
public class RearrangableLabeledPhotoCellModel_ extends NoDividerBaseModel<RearrangableLabeledPhotoCell> implements GeneratedModel<RearrangableLabeledPhotoCell> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(5);
    private CharSequence image_CharSequence = null;
    private StringAttributeData label_StringAttributeData = new StringAttributeData(null);
    private OnModelBoundListener<RearrangableLabeledPhotoCellModel_, RearrangableLabeledPhotoCell> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<RearrangableLabeledPhotoCellModel_, RearrangableLabeledPhotoCell> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData placeholderText_StringAttributeData = new StringAttributeData(null);

    public void handlePreBind(EpoxyViewHolder holder, RearrangableLabeledPhotoCell object, int position) {
    }

    public void bind(RearrangableLabeledPhotoCell object) {
        super.bind(object);
        object.setLabel(this.label_StringAttributeData.toString(object.getContext()));
        object.setPlaceholderText(this.placeholderText_StringAttributeData.toString(object.getContext()));
        object.setImage(this.image_CharSequence);
    }

    public void bind(RearrangableLabeledPhotoCell object, EpoxyModel previousModel) {
        if (!(previousModel instanceof RearrangableLabeledPhotoCellModel_)) {
            bind(object);
            return;
        }
        RearrangableLabeledPhotoCellModel_ that = (RearrangableLabeledPhotoCellModel_) previousModel;
        super.bind(object);
        if (!this.label_StringAttributeData.equals(that.label_StringAttributeData)) {
            object.setLabel(this.label_StringAttributeData.toString(object.getContext()));
        }
        if (!this.placeholderText_StringAttributeData.equals(that.placeholderText_StringAttributeData)) {
            object.setPlaceholderText(this.placeholderText_StringAttributeData.toString(object.getContext()));
        }
        if (this.image_CharSequence != null) {
            if (this.image_CharSequence.equals(that.image_CharSequence)) {
                return;
            }
        } else if (that.image_CharSequence == null) {
            return;
        }
        object.setImage(this.image_CharSequence);
    }

    public void handlePostBind(RearrangableLabeledPhotoCell object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(RearrangableLabeledPhotoCell object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setImage(null);
    }

    public RearrangableLabeledPhotoCellModel_ image(CharSequence image) {
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        onMutation();
        this.image_CharSequence = image;
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ label(CharSequence label) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.label_StringAttributeData.setValue(label);
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(4);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoCellModel_ m1686id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoCellModel_ m1691id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoCellModel_ m1687id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoCellModel_ m1688id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoCellModel_ m1690id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public RearrangableLabeledPhotoCellModel_ m1689id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ show() {
        super.show();
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public RearrangableLabeledPhotoCellModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_rearrangable_labeled_photo_cell;
    }

    public RearrangableLabeledPhotoCellModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.image_CharSequence = null;
        this.label_StringAttributeData = new StringAttributeData(null);
        this.placeholderText_StringAttributeData = new StringAttributeData(null);
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
        if (!(o instanceof RearrangableLabeledPhotoCellModel_) || !super.equals(o)) {
            return false;
        }
        RearrangableLabeledPhotoCellModel_ that = (RearrangableLabeledPhotoCellModel_) o;
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
        if (this.image_CharSequence != null) {
            if (!this.image_CharSequence.equals(that.image_CharSequence)) {
                return false;
            }
        } else if (that.image_CharSequence != null) {
            return false;
        }
        if (this.label_StringAttributeData != null) {
            if (!this.label_StringAttributeData.equals(that.label_StringAttributeData)) {
                return false;
            }
        } else if (that.label_StringAttributeData != null) {
            return false;
        }
        if (this.placeholderText_StringAttributeData != null) {
            if (!this.placeholderText_StringAttributeData.equals(that.placeholderText_StringAttributeData)) {
                return false;
            }
        } else if (that.placeholderText_StringAttributeData != null) {
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
        if (this.image_CharSequence != null) {
            i = this.image_CharSequence.hashCode();
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.label_StringAttributeData != null) {
            i2 = this.label_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.placeholderText_StringAttributeData != null) {
            i3 = this.placeholderText_StringAttributeData.hashCode();
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
        return "RearrangableLabeledPhotoCellModel_{image_CharSequence=" + this.image_CharSequence + ", label_StringAttributeData=" + this.label_StringAttributeData + ", placeholderText_StringAttributeData=" + this.placeholderText_StringAttributeData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
