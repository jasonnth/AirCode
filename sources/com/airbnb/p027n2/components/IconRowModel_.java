package com.airbnb.p027n2.components;

import android.graphics.drawable.Drawable;
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

/* renamed from: com.airbnb.n2.components.IconRowModel_ */
public class IconRowModel_ extends DefaultDividerBaseModel<IconRow> implements GeneratedModel<IconRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(8);
    private Drawable icon_Drawable = null;
    private int icon_Int = 0;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<IconRowModel_, IconRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<IconRowModel_, IconRow> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData subtitleText_StringAttributeData = new StringAttributeData(null);
    private StringAttributeData title_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, IconRow object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(IconRow object) {
        super.bind(object);
        if (this.assignedAttributes_epoxyGeneratedModel.get(2)) {
            object.setIcon(this.icon_Drawable);
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(3)) {
            object.setIcon(this.icon_Int);
        } else {
            object.setIcon((Drawable) null);
        }
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setSubtitleText(this.subtitleText_StringAttributeData.toString(object.getContext()));
    }

    public void bind(IconRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (!(previousModel instanceof IconRowModel_)) {
            bind(object);
            return;
        }
        IconRowModel_ that = (IconRowModel_) previousModel;
        super.bind(object);
        if (this.assignedAttributes_epoxyGeneratedModel.equals(that.assignedAttributes_epoxyGeneratedModel)) {
            if (this.assignedAttributes_epoxyGeneratedModel.get(2)) {
                if (this.icon_Drawable != null) {
                }
                object.setIcon(this.icon_Drawable);
            } else if (this.assignedAttributes_epoxyGeneratedModel.get(3) && this.icon_Int != that.icon_Int) {
                object.setIcon(this.icon_Int);
            }
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(2) && !that.assignedAttributes_epoxyGeneratedModel.get(2)) {
            object.setIcon(this.icon_Drawable);
        } else if (!this.assignedAttributes_epoxyGeneratedModel.get(3) || that.assignedAttributes_epoxyGeneratedModel.get(3)) {
            object.setIcon((Drawable) null);
        } else {
            object.setIcon(this.icon_Int);
        }
        if (this.onClickListener_OnClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
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
        if (!this.subtitleText_StringAttributeData.equals(that.subtitleText_StringAttributeData)) {
            object.setSubtitleText(this.subtitleText_StringAttributeData.toString(object.getContext()));
        }
    }

    public void handlePostBind(IconRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(IconRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setSubtitleText((CharSequence) null);
        object.setIcon((Drawable) null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public IconRowModel_ title(CharSequence title) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.title_StringAttributeData.setValue(title);
        return this;
    }

    public IconRowModel_ subtitleText(CharSequence subtitleText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.subtitleText_StringAttributeData.setValue(subtitleText);
        return this;
    }

    public IconRowModel_ icon(int icon) {
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        this.assignedAttributes_epoxyGeneratedModel.clear(2);
        this.icon_Drawable = null;
        onMutation();
        this.icon_Int = icon;
        return this;
    }

    public IconRowModel_ onClickListener(OnClickListener onClickListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(4);
        onMutation();
        this.onClickListener_OnClickListener = onClickListener;
        return this;
    }

    public IconRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(7);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public IconRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public IconRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public IconRowModel_ m1494id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public IconRowModel_ m1499id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public IconRowModel_ m1495id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public IconRowModel_ m1496id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public IconRowModel_ m1498id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public IconRowModel_ m1497id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public IconRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public IconRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public IconRowModel_ show() {
        super.show();
        return this;
    }

    public IconRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public IconRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_icon_row;
    }

    public IconRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.title_StringAttributeData = new StringAttributeData();
        this.subtitleText_StringAttributeData = new StringAttributeData(null);
        this.icon_Drawable = null;
        this.icon_Int = 0;
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
        if (!(o instanceof IconRowModel_) || !super.equals(o)) {
            return false;
        }
        IconRowModel_ that = (IconRowModel_) o;
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
        if (this.subtitleText_StringAttributeData != null) {
            if (!this.subtitleText_StringAttributeData.equals(that.subtitleText_StringAttributeData)) {
                return false;
            }
        } else if (that.subtitleText_StringAttributeData != null) {
            return false;
        }
        if (this.icon_Drawable != null) {
            if (!this.icon_Drawable.equals(that.icon_Drawable)) {
                return false;
            }
        } else if (that.icon_Drawable != null) {
            return false;
        }
        if (this.icon_Int != that.icon_Int) {
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
        if (this.title_StringAttributeData != null) {
            i2 = this.title_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.subtitleText_StringAttributeData != null) {
            i3 = this.subtitleText_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.icon_Drawable != null) {
            i4 = this.icon_Drawable.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (((i11 + i4) * 31) + this.icon_Int) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i5 = 1;
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
        return "IconRowModel_{title_StringAttributeData=" + this.title_StringAttributeData + ", subtitleText_StringAttributeData=" + this.subtitleText_StringAttributeData + ", icon_Drawable=" + this.icon_Drawable + ", icon_Int=" + this.icon_Int + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
