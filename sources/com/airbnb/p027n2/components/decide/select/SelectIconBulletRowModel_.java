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
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.decide.select.SelectIconBulletRowModel_ */
public class SelectIconBulletRowModel_ extends DefaultDividerBaseModel<SelectIconBulletRow> implements GeneratedModel<SelectIconBulletRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(6);
    private int drawableResource_Int = 0;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<SelectIconBulletRowModel_, SelectIconBulletRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SelectIconBulletRowModel_, SelectIconBulletRow> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData text_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, SelectIconBulletRow object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(SelectIconBulletRow object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setDrawableResource(this.drawableResource_Int);
        object.setText(this.text_StringAttributeData.toString(object.getContext()));
    }

    public void bind(SelectIconBulletRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = true;
        if (!(previousModel instanceof SelectIconBulletRowModel_)) {
            bind(object);
            return;
        }
        SelectIconBulletRowModel_ that = (SelectIconBulletRowModel_) previousModel;
        super.bind(object);
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
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
        if (this.drawableResource_Int != that.drawableResource_Int) {
            object.setDrawableResource(this.drawableResource_Int);
        }
        if (!this.text_StringAttributeData.equals(that.text_StringAttributeData)) {
            object.setText(this.text_StringAttributeData.toString(object.getContext()));
        }
    }

    public void handlePostBind(SelectIconBulletRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(SelectIconBulletRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public SelectIconBulletRowModel_ text(CharSequence text) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.text_StringAttributeData.setValue(text);
        return this;
    }

    public SelectIconBulletRowModel_ drawableResource(int drawableResource) {
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        onMutation();
        this.drawableResource_Int = drawableResource;
        return this;
    }

    public SelectIconBulletRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(5);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SelectIconBulletRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SelectIconBulletRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SelectIconBulletRowModel_ m1650id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SelectIconBulletRowModel_ m1655id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SelectIconBulletRowModel_ m1651id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SelectIconBulletRowModel_ m1652id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SelectIconBulletRowModel_ m1654id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SelectIconBulletRowModel_ m1653id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SelectIconBulletRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SelectIconBulletRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SelectIconBulletRowModel_ show() {
        super.show();
        return this;
    }

    public SelectIconBulletRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SelectIconBulletRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_select_icon_bullet_row;
    }

    public SelectIconBulletRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.text_StringAttributeData = new StringAttributeData();
        this.drawableResource_Int = 0;
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof SelectIconBulletRowModel_) || !super.equals(o)) {
            return false;
        }
        SelectIconBulletRowModel_ that = (SelectIconBulletRowModel_) o;
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
        if (this.text_StringAttributeData != null) {
            if (!this.text_StringAttributeData.equals(that.text_StringAttributeData)) {
                return false;
            }
        } else if (that.text_StringAttributeData != null) {
            return false;
        }
        if (this.drawableResource_Int != that.drawableResource_Int) {
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
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.text_StringAttributeData != null) {
            i2 = this.text_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((i7 + i2) * 31) + this.drawableResource_Int) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
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
        return "SelectIconBulletRowModel_{text_StringAttributeData=" + this.text_StringAttributeData + ", drawableResource_Int=" + this.drawableResource_Int + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}