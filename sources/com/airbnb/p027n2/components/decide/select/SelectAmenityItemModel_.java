package com.airbnb.p027n2.components.decide.select;

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
import com.airbnb.p027n2.primitives.imaging.Image;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.decide.select.SelectAmenityItemModel_ */
public class SelectAmenityItemModel_ extends NoDividerBaseModel<SelectAmenityItem> implements GeneratedModel<SelectAmenityItem> {
    private StringAttributeData amenityName_StringAttributeData = new StringAttributeData();
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(4);
    private Image image_Image = null;
    private OnModelBoundListener<SelectAmenityItemModel_, SelectAmenityItem> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SelectAmenityItemModel_, SelectAmenityItem> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SelectAmenityItem object, int position) {
    }

    public void bind(SelectAmenityItem object) {
        super.bind(object);
        object.setAmenityName(this.amenityName_StringAttributeData.toString(object.getContext()));
        object.setImage(this.image_Image);
    }

    public void bind(SelectAmenityItem object, EpoxyModel previousModel) {
        if (!(previousModel instanceof SelectAmenityItemModel_)) {
            bind(object);
            return;
        }
        SelectAmenityItemModel_ that = (SelectAmenityItemModel_) previousModel;
        super.bind(object);
        if (!this.amenityName_StringAttributeData.equals(that.amenityName_StringAttributeData)) {
            object.setAmenityName(this.amenityName_StringAttributeData.toString(object.getContext()));
        }
        if (this.image_Image != null) {
            if (this.image_Image.equals(that.image_Image)) {
                return;
            }
        } else if (that.image_Image == null) {
            return;
        }
        object.setImage(this.image_Image);
    }

    public void handlePostBind(SelectAmenityItem object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(SelectAmenityItem object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setImage(null);
    }

    public SelectAmenityItemModel_ amenityName(CharSequence amenityName) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.amenityName_StringAttributeData.setValue(amenityName);
        return this;
    }

    public SelectAmenityItemModel_ image(Image image) {
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        onMutation();
        this.image_Image = image;
        return this;
    }

    public SelectAmenityItemModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SelectAmenityItemModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SelectAmenityItemModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SelectAmenityItemModel_ m1590id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SelectAmenityItemModel_ m1595id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SelectAmenityItemModel_ m1591id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SelectAmenityItemModel_ m1592id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SelectAmenityItemModel_ m1594id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SelectAmenityItemModel_ m1593id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SelectAmenityItemModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SelectAmenityItemModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SelectAmenityItemModel_ show() {
        super.show();
        return this;
    }

    public SelectAmenityItemModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SelectAmenityItemModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_select_amenity_item;
    }

    public SelectAmenityItemModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.amenityName_StringAttributeData = new StringAttributeData();
        this.image_Image = null;
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
        if (!(o instanceof SelectAmenityItemModel_) || !super.equals(o)) {
            return false;
        }
        SelectAmenityItemModel_ that = (SelectAmenityItemModel_) o;
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
        if (this.amenityName_StringAttributeData != null) {
            if (!this.amenityName_StringAttributeData.equals(that.amenityName_StringAttributeData)) {
                return false;
            }
        } else if (that.amenityName_StringAttributeData != null) {
            return false;
        }
        if (this.image_Image != null) {
            if (!this.image_Image.equals(that.image_Image)) {
                return false;
            }
        } else if (that.image_Image != null) {
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
        int i6 = (hashCode + i4) * 31;
        if (this.amenityName_StringAttributeData != null) {
            i = this.amenityName_StringAttributeData.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.image_Image != null) {
            i2 = this.image_Image.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
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
        return "SelectAmenityItemModel_{amenityName_StringAttributeData=" + this.amenityName_StringAttributeData + ", image_Image=" + this.image_Image + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
