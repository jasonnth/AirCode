package com.airbnb.p027n2.components;

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

/* renamed from: com.airbnb.n2.components.MultiLineSplitRowModel_ */
public class MultiLineSplitRowModel_ extends DefaultDividerBaseModel<MultiLineSplitRow> implements GeneratedModel<MultiLineSplitRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(6);
    private StringAttributeData infoText_StringAttributeData = new StringAttributeData();
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<MultiLineSplitRowModel_, MultiLineSplitRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MultiLineSplitRowModel_, MultiLineSplitRow> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData title_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, MultiLineSplitRow object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(MultiLineSplitRow object) {
        super.bind(object);
        object.setInfoText(this.infoText_StringAttributeData.toString(object.getContext()));
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
    }

    public void bind(MultiLineSplitRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = true;
        if (!(previousModel instanceof MultiLineSplitRowModel_)) {
            bind(object);
            return;
        }
        MultiLineSplitRowModel_ that = (MultiLineSplitRowModel_) previousModel;
        super.bind(object);
        if (!this.infoText_StringAttributeData.equals(that.infoText_StringAttributeData)) {
            object.setInfoText(this.infoText_StringAttributeData.toString(object.getContext()));
        }
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
        if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
            object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        }
    }

    public void handlePostBind(MultiLineSplitRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(MultiLineSplitRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public MultiLineSplitRowModel_ title(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.title_StringAttributeData.setValue(stringRes);
        return this;
    }

    public MultiLineSplitRowModel_ infoText(CharSequence infoText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.infoText_StringAttributeData.setValue(infoText);
        return this;
    }

    public MultiLineSplitRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(5);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MultiLineSplitRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MultiLineSplitRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MultiLineSplitRowModel_ m1542id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MultiLineSplitRowModel_ m1547id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MultiLineSplitRowModel_ m1543id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MultiLineSplitRowModel_ m1544id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MultiLineSplitRowModel_ m1546id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MultiLineSplitRowModel_ m1545id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MultiLineSplitRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MultiLineSplitRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MultiLineSplitRowModel_ show() {
        super.show();
        return this;
    }

    public MultiLineSplitRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MultiLineSplitRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_multi_line_split_row;
    }

    public MultiLineSplitRowModel_ withNoTopPaddingLayout() {
        layout(R.layout.n2_view_holder_multi_line_split_row_no_top_padding);
        return this;
    }

    public MultiLineSplitRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.title_StringAttributeData = new StringAttributeData();
        this.infoText_StringAttributeData = new StringAttributeData();
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
        if (!(o instanceof MultiLineSplitRowModel_) || !super.equals(o)) {
            return false;
        }
        MultiLineSplitRowModel_ that = (MultiLineSplitRowModel_) o;
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
        if (this.infoText_StringAttributeData != null) {
            if (!this.infoText_StringAttributeData.equals(that.infoText_StringAttributeData)) {
                return false;
            }
        } else if (that.infoText_StringAttributeData != null) {
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
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.title_StringAttributeData != null) {
            i2 = this.title_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.infoText_StringAttributeData != null) {
            i3 = this.infoText_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
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
        return "MultiLineSplitRowModel_{title_StringAttributeData=" + this.title_StringAttributeData + ", infoText_StringAttributeData=" + this.infoText_StringAttributeData + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
