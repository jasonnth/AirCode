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
import com.airbnb.p027n2.epoxy.NoDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.imaging.Image;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHomeRoomItemModel_ */
public class SelectHomeRoomItemModel_ extends NoDividerBaseModel<SelectHomeRoomItem> implements GeneratedModel<SelectHomeRoomItem> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(7);
    private Image image_Image = null;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<SelectHomeRoomItemModel_, SelectHomeRoomItem> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SelectHomeRoomItemModel_, SelectHomeRoomItem> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData roomDescription_StringAttributeData = new StringAttributeData();
    private StringAttributeData roomTitle_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, SelectHomeRoomItem object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(SelectHomeRoomItem object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setRoomDescription(this.roomDescription_StringAttributeData.toString(object.getContext()));
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setRoomTitle(this.roomTitle_StringAttributeData.toString(object.getContext()));
        object.setImage(this.image_Image);
    }

    public void bind(SelectHomeRoomItem object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = true;
        if (!(previousModel instanceof SelectHomeRoomItemModel_)) {
            bind(object);
            return;
        }
        SelectHomeRoomItemModel_ that = (SelectHomeRoomItemModel_) previousModel;
        super.bind(object);
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (!this.roomDescription_StringAttributeData.equals(that.roomDescription_StringAttributeData)) {
            object.setRoomDescription(this.roomDescription_StringAttributeData.toString(object.getContext()));
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (that.onLongClickListener_OnLongClickListener != null) {
            z2 = false;
        }
        if (z != z2) {
            object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        }
        if (!this.roomTitle_StringAttributeData.equals(that.roomTitle_StringAttributeData)) {
            object.setRoomTitle(this.roomTitle_StringAttributeData.toString(object.getContext()));
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

    public void handlePostBind(SelectHomeRoomItem object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(SelectHomeRoomItem object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setImage(null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public SelectHomeRoomItemModel_ image(Image image) {
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        onMutation();
        this.image_Image = image;
        return this;
    }

    public SelectHomeRoomItemModel_ onClickListener(OnClickListener onClickListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        onMutation();
        this.onClickListener_OnClickListener = onClickListener;
        return this;
    }

    public SelectHomeRoomItemModel_ roomTitle(CharSequence roomTitle) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.roomTitle_StringAttributeData.setValue(roomTitle);
        return this;
    }

    public SelectHomeRoomItemModel_ roomDescription(CharSequence roomDescription) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        this.roomDescription_StringAttributeData.setValue(roomDescription);
        return this;
    }

    public SelectHomeRoomItemModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(6);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SelectHomeRoomItemModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SelectHomeRoomItemModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SelectHomeRoomItemModel_ m1614id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SelectHomeRoomItemModel_ m1619id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SelectHomeRoomItemModel_ m1615id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SelectHomeRoomItemModel_ m1616id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SelectHomeRoomItemModel_ m1618id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SelectHomeRoomItemModel_ m1617id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SelectHomeRoomItemModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SelectHomeRoomItemModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SelectHomeRoomItemModel_ show() {
        super.show();
        return this;
    }

    public SelectHomeRoomItemModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SelectHomeRoomItemModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_select_home_room_item;
    }

    public SelectHomeRoomItemModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.image_Image = null;
        this.onClickListener_OnClickListener = null;
        this.roomTitle_StringAttributeData = new StringAttributeData();
        this.roomDescription_StringAttributeData = new StringAttributeData();
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof SelectHomeRoomItemModel_) || !super.equals(o)) {
            return false;
        }
        SelectHomeRoomItemModel_ that = (SelectHomeRoomItemModel_) o;
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
        if (this.image_Image != null) {
            if (!this.image_Image.equals(that.image_Image)) {
                return false;
            }
        } else if (that.image_Image != null) {
            return false;
        }
        if (this.onClickListener_OnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener_OnClickListener == null)) {
            return false;
        }
        if (this.roomTitle_StringAttributeData != null) {
            if (!this.roomTitle_StringAttributeData.equals(that.roomTitle_StringAttributeData)) {
                return false;
            }
        } else if (that.roomTitle_StringAttributeData != null) {
            return false;
        }
        if (this.roomDescription_StringAttributeData != null) {
            if (!this.roomDescription_StringAttributeData.equals(that.roomDescription_StringAttributeData)) {
                return false;
            }
        } else if (that.roomDescription_StringAttributeData != null) {
            return false;
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.onLongClickListener_OnLongClickListener == null)) {
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
        if (this.image_Image != null) {
            i2 = this.image_Image.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.roomTitle_StringAttributeData != null) {
            i4 = this.roomTitle_StringAttributeData.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.roomDescription_StringAttributeData != null) {
            i5 = this.roomDescription_StringAttributeData.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
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
        return "SelectHomeRoomItemModel_{image_Image=" + this.image_Image + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", roomTitle_StringAttributeData=" + this.roomTitle_StringAttributeData + ", roomDescription_StringAttributeData=" + this.roomDescription_StringAttributeData + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
