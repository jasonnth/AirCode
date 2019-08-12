package com.airbnb.p027n2.components.decide.select;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.StringAttributeData;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.n2.R;
import com.airbnb.p027n2.epoxy.DefaultDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.imaging.Image;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHostRowModel_ */
public class SelectHostRowModel_ extends DefaultDividerBaseModel<SelectHostRow> implements GeneratedModel<SelectHostRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(9);
    private OnClickListener contactHostButtonClickListener_OnClickListener = null;
    private StringAttributeData contactHostButtonText_StringAttributeData = new StringAttributeData();
    private StringAttributeData descriptionText_StringAttributeData = new StringAttributeData();
    private Image hostImage_Image = null;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<SelectHostRowModel_, SelectHostRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SelectHostRowModel_, SelectHostRow> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData title_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, SelectHostRow object, int position) {
        if (this.contactHostButtonClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.contactHostButtonClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(SelectHostRow object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setContactHostButtonClickListener(this.contactHostButtonClickListener_OnClickListener);
        object.setContactHostButtonText(this.contactHostButtonText_StringAttributeData.toString(object.getContext()));
        object.setHostImage(this.hostImage_Image);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setDescriptionText(this.descriptionText_StringAttributeData.toString(object.getContext()));
    }

    public void bind(SelectHostRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (!(previousModel instanceof SelectHostRowModel_)) {
            bind(object);
            return;
        }
        SelectHostRowModel_ that = (SelectHostRowModel_) previousModel;
        super.bind(object);
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (this.contactHostButtonClickListener_OnClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.contactHostButtonClickListener_OnClickListener == null)) {
            object.setContactHostButtonClickListener(this.contactHostButtonClickListener_OnClickListener);
        }
        if (!this.contactHostButtonText_StringAttributeData.equals(that.contactHostButtonText_StringAttributeData)) {
            object.setContactHostButtonText(this.contactHostButtonText_StringAttributeData.toString(object.getContext()));
        }
        if (this.hostImage_Image == null ? that.hostImage_Image != null : !this.hostImage_Image.equals(that.hostImage_Image)) {
            object.setHostImage(this.hostImage_Image);
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (that.onLongClickListener_OnLongClickListener != null) {
            z3 = false;
        }
        if (z2 != z3) {
            object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        }
        if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
            object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        }
        if (!this.descriptionText_StringAttributeData.equals(that.descriptionText_StringAttributeData)) {
            object.setDescriptionText(this.descriptionText_StringAttributeData.toString(object.getContext()));
        }
    }

    public void handlePostBind(SelectHostRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(SelectHostRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setHostImage(null);
        object.setContactHostButtonClickListener(null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public SelectHostRowModel_ title(CharSequence title) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.title_StringAttributeData.setValue(title);
        return this;
    }

    public SelectHostRowModel_ descriptionText(CharSequence descriptionText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.descriptionText_StringAttributeData.setValue(descriptionText);
        return this;
    }

    public SelectHostRowModel_ contactHostButtonText(CharSequence contactHostButtonText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.contactHostButtonText_StringAttributeData.setValue(contactHostButtonText);
        return this;
    }

    public SelectHostRowModel_ hostImage(Image hostImage) {
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        onMutation();
        this.hostImage_Image = hostImage;
        return this;
    }

    public SelectHostRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(8);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SelectHostRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SelectHostRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SelectHostRowModel_ m1638id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SelectHostRowModel_ m1643id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SelectHostRowModel_ m1639id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SelectHostRowModel_ m1640id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SelectHostRowModel_ m1642id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SelectHostRowModel_ m1641id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SelectHostRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SelectHostRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SelectHostRowModel_ show() {
        super.show();
        return this;
    }

    public SelectHostRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SelectHostRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_select_host_row;
    }

    public SelectHostRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.title_StringAttributeData = new StringAttributeData();
        this.descriptionText_StringAttributeData = new StringAttributeData();
        this.contactHostButtonText_StringAttributeData = new StringAttributeData();
        this.hostImage_Image = null;
        this.contactHostButtonClickListener_OnClickListener = null;
        this.onClickListener_OnClickListener = null;
        this.onLongClickListener_OnLongClickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof SelectHostRowModel_) || !super.equals(o)) {
            return false;
        }
        SelectHostRowModel_ that = (SelectHostRowModel_) o;
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
        if (this.title_StringAttributeData != null) {
            if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
                return false;
            }
        } else if (that.title_StringAttributeData != null) {
            return false;
        }
        if (this.descriptionText_StringAttributeData != null) {
            if (!this.descriptionText_StringAttributeData.equals(that.descriptionText_StringAttributeData)) {
                return false;
            }
        } else if (that.descriptionText_StringAttributeData != null) {
            return false;
        }
        if (this.contactHostButtonText_StringAttributeData != null) {
            if (!this.contactHostButtonText_StringAttributeData.equals(that.contactHostButtonText_StringAttributeData)) {
                return false;
            }
        } else if (that.contactHostButtonText_StringAttributeData != null) {
            return false;
        }
        if (this.hostImage_Image != null) {
            if (!this.hostImage_Image.equals(that.hostImage_Image)) {
                return false;
            }
        } else if (that.hostImage_Image != null) {
            return false;
        }
        if (this.contactHostButtonClickListener_OnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.contactHostButtonClickListener_OnClickListener == null)) {
            return false;
        }
        if (this.onClickListener_OnClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.onClickListener_OnClickListener == null)) {
            return false;
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.onLongClickListener_OnLongClickListener == null)) {
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
        int i7;
        int i8;
        int i9 = 1;
        int i10 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i11 = (hashCode + i) * 31;
        if (this.title_StringAttributeData != null) {
            i2 = this.title_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i12 = (i11 + i2) * 31;
        if (this.descriptionText_StringAttributeData != null) {
            i3 = this.descriptionText_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i13 = (i12 + i3) * 31;
        if (this.contactHostButtonText_StringAttributeData != null) {
            i4 = this.contactHostButtonText_StringAttributeData.hashCode();
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.hostImage_Image != null) {
            i5 = this.hostImage_Image.hashCode();
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.contactHostButtonClickListener_OnClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i9 = 0;
        }
        int i18 = (i17 + i9) * 31;
        if (this.showDivider != null) {
            i8 = this.showDivider.hashCode();
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (this.numCarouselItemsShown != null) {
            i10 = this.numCarouselItemsShown.hashCode();
        }
        return i19 + i10;
    }

    public String toString() {
        return "SelectHostRowModel_{title_StringAttributeData=" + this.title_StringAttributeData + ", descriptionText_StringAttributeData=" + this.descriptionText_StringAttributeData + ", contactHostButtonText_StringAttributeData=" + this.contactHostButtonText_StringAttributeData + ", hostImage_Image=" + this.hostImage_Image + ", contactHostButtonClickListener_OnClickListener=" + this.contactHostButtonClickListener_OnClickListener + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
