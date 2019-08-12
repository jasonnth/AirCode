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

/* renamed from: com.airbnb.n2.components.decide.select.SelectHomeSummaryRowModel_ */
public class SelectHomeSummaryRowModel_ extends DefaultDividerBaseModel<SelectHomeSummaryRow> implements GeneratedModel<SelectHomeSummaryRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(8);
    private StringAttributeData bathroomsText_StringAttributeData = new StringAttributeData();
    private StringAttributeData bedroomsText_StringAttributeData = new StringAttributeData();
    private StringAttributeData bedsText_StringAttributeData = new StringAttributeData();
    private StringAttributeData guestsText_StringAttributeData = new StringAttributeData();
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<SelectHomeSummaryRowModel_, SelectHomeSummaryRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SelectHomeSummaryRowModel_, SelectHomeSummaryRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SelectHomeSummaryRow object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(SelectHomeSummaryRow object) {
        super.bind(object);
        object.setGuestsText(this.guestsText_StringAttributeData.toString(object.getContext()));
        object.setBathroomsText(this.bathroomsText_StringAttributeData.toString(object.getContext()));
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setBedroomsText(this.bedroomsText_StringAttributeData.toString(object.getContext()));
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setBedsText(this.bedsText_StringAttributeData.toString(object.getContext()));
    }

    public void bind(SelectHomeSummaryRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = true;
        if (!(previousModel instanceof SelectHomeSummaryRowModel_)) {
            bind(object);
            return;
        }
        SelectHomeSummaryRowModel_ that = (SelectHomeSummaryRowModel_) previousModel;
        super.bind(object);
        if (!this.guestsText_StringAttributeData.equals(that.guestsText_StringAttributeData)) {
            object.setGuestsText(this.guestsText_StringAttributeData.toString(object.getContext()));
        }
        if (!this.bathroomsText_StringAttributeData.equals(that.bathroomsText_StringAttributeData)) {
            object.setBathroomsText(this.bathroomsText_StringAttributeData.toString(object.getContext()));
        }
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (!this.bedroomsText_StringAttributeData.equals(that.bedroomsText_StringAttributeData)) {
            object.setBedroomsText(this.bedroomsText_StringAttributeData.toString(object.getContext()));
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
        if (!this.bedsText_StringAttributeData.equals(that.bedsText_StringAttributeData)) {
            object.setBedsText(this.bedsText_StringAttributeData.toString(object.getContext()));
        }
    }

    public void handlePostBind(SelectHomeSummaryRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(SelectHomeSummaryRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public SelectHomeSummaryRowModel_ guestsText(CharSequence guestsText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.guestsText_StringAttributeData.setValue(guestsText);
        return this;
    }

    public SelectHomeSummaryRowModel_ bedroomsText(CharSequence bedroomsText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.bedroomsText_StringAttributeData.setValue(bedroomsText);
        return this;
    }

    public SelectHomeSummaryRowModel_ bedsText(CharSequence bedsText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.bedsText_StringAttributeData.setValue(bedsText);
        return this;
    }

    public SelectHomeSummaryRowModel_ bathroomsText(CharSequence bathroomsText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        this.bathroomsText_StringAttributeData.setValue(bathroomsText);
        return this;
    }

    public SelectHomeSummaryRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(7);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SelectHomeSummaryRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SelectHomeSummaryRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SelectHomeSummaryRowModel_ m1626id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SelectHomeSummaryRowModel_ m1631id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SelectHomeSummaryRowModel_ m1627id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SelectHomeSummaryRowModel_ m1628id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SelectHomeSummaryRowModel_ m1630id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SelectHomeSummaryRowModel_ m1629id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SelectHomeSummaryRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SelectHomeSummaryRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SelectHomeSummaryRowModel_ show() {
        super.show();
        return this;
    }

    public SelectHomeSummaryRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SelectHomeSummaryRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_select_home_summary_row;
    }

    public SelectHomeSummaryRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.guestsText_StringAttributeData = new StringAttributeData();
        this.bedroomsText_StringAttributeData = new StringAttributeData();
        this.bedsText_StringAttributeData = new StringAttributeData();
        this.bathroomsText_StringAttributeData = new StringAttributeData();
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
        if (!(o instanceof SelectHomeSummaryRowModel_) || !super.equals(o)) {
            return false;
        }
        SelectHomeSummaryRowModel_ that = (SelectHomeSummaryRowModel_) o;
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
        if (this.guestsText_StringAttributeData != null) {
            if (!this.guestsText_StringAttributeData.equals(that.guestsText_StringAttributeData)) {
                return false;
            }
        } else if (that.guestsText_StringAttributeData != null) {
            return false;
        }
        if (this.bedroomsText_StringAttributeData != null) {
            if (!this.bedroomsText_StringAttributeData.equals(that.bedroomsText_StringAttributeData)) {
                return false;
            }
        } else if (that.bedroomsText_StringAttributeData != null) {
            return false;
        }
        if (this.bedsText_StringAttributeData != null) {
            if (!this.bedsText_StringAttributeData.equals(that.bedsText_StringAttributeData)) {
                return false;
            }
        } else if (that.bedsText_StringAttributeData != null) {
            return false;
        }
        if (this.bathroomsText_StringAttributeData != null) {
            if (!this.bathroomsText_StringAttributeData.equals(that.bathroomsText_StringAttributeData)) {
                return false;
            }
        } else if (that.bathroomsText_StringAttributeData != null) {
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
        int i7;
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.guestsText_StringAttributeData != null) {
            i2 = this.guestsText_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.bedroomsText_StringAttributeData != null) {
            i3 = this.bedroomsText_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.bedsText_StringAttributeData != null) {
            i4 = this.bedsText_StringAttributeData.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.bathroomsText_StringAttributeData != null) {
            i5 = this.bathroomsText_StringAttributeData.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "SelectHomeSummaryRowModel_{guestsText_StringAttributeData=" + this.guestsText_StringAttributeData + ", bedroomsText_StringAttributeData=" + this.bedroomsText_StringAttributeData + ", bedsText_StringAttributeData=" + this.bedsText_StringAttributeData + ", bathroomsText_StringAttributeData=" + this.bathroomsText_StringAttributeData + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
