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

/* renamed from: com.airbnb.n2.components.InfoActionRowModel_ */
public class InfoActionRowModel_ extends DefaultDividerBaseModel<InfoActionRow> implements GeneratedModel<InfoActionRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(7);
    private StringAttributeData info_StringAttributeData = new StringAttributeData();
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<InfoActionRowModel_, InfoActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InfoActionRowModel_, InfoActionRow> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData subtitleText_StringAttributeData = new StringAttributeData(null);
    private StringAttributeData title_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, InfoActionRow object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(InfoActionRow object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setSubtitleText(this.subtitleText_StringAttributeData.toString(object.getContext()));
        object.setInfo(this.info_StringAttributeData.toString(object.getContext()));
    }

    public void bind(InfoActionRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = true;
        if (!(previousModel instanceof InfoActionRowModel_)) {
            bind(object);
            return;
        }
        InfoActionRowModel_ that = (InfoActionRowModel_) previousModel;
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
        if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
            object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        }
        if (!this.subtitleText_StringAttributeData.equals(that.subtitleText_StringAttributeData)) {
            object.setSubtitleText(this.subtitleText_StringAttributeData.toString(object.getContext()));
        }
        if (!this.info_StringAttributeData.equals(that.info_StringAttributeData)) {
            object.setInfo(this.info_StringAttributeData.toString(object.getContext()));
        }
    }

    public void handlePostBind(InfoActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(InfoActionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public InfoActionRowModel_ onClickListener(OnClickListener onClickListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        onMutation();
        this.onClickListener_OnClickListener = onClickListener;
        return this;
    }

    public InfoActionRowModel_ title(CharSequence title) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.title_StringAttributeData.setValue(title);
        return this;
    }

    public InfoActionRowModel_ title(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.title_StringAttributeData.setValue(stringRes);
        return this;
    }

    public InfoActionRowModel_ subtitleText(CharSequence subtitleText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        this.subtitleText_StringAttributeData.setValue(subtitleText);
        return this;
    }

    public InfoActionRowModel_ subtitleText(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        this.subtitleText_StringAttributeData.setValue(stringRes);
        return this;
    }

    public InfoActionRowModel_ info(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(4);
        this.info_StringAttributeData.setValue(stringRes);
        return this;
    }

    public InfoActionRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(6);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InfoActionRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InfoActionRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InfoActionRowModel_ m1506id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InfoActionRowModel_ m1511id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InfoActionRowModel_ m1507id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InfoActionRowModel_ m1508id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InfoActionRowModel_ m1510id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InfoActionRowModel_ m1509id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InfoActionRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InfoActionRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InfoActionRowModel_ show() {
        super.show();
        return this;
    }

    public InfoActionRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InfoActionRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_info_action_row;
    }

    public InfoActionRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.onClickListener_OnClickListener = null;
        this.onLongClickListener_OnLongClickListener = null;
        this.title_StringAttributeData = new StringAttributeData();
        this.subtitleText_StringAttributeData = new StringAttributeData(null);
        this.info_StringAttributeData = new StringAttributeData();
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
        if (!(o instanceof InfoActionRowModel_) || !super.equals(o)) {
            return false;
        }
        InfoActionRowModel_ that = (InfoActionRowModel_) o;
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
        if (this.info_StringAttributeData != null) {
            if (!this.info_StringAttributeData.equals(that.info_StringAttributeData)) {
                return false;
            }
        } else if (that.info_StringAttributeData != null) {
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
        if (this.onClickListener_OnClickListener != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i7 = 0;
        }
        int i11 = (i10 + i7) * 31;
        if (this.title_StringAttributeData != null) {
            i3 = this.title_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.subtitleText_StringAttributeData != null) {
            i4 = this.subtitleText_StringAttributeData.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.info_StringAttributeData != null) {
            i5 = this.info_StringAttributeData.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
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
        return "InfoActionRowModel_{onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", title_StringAttributeData=" + this.title_StringAttributeData + ", subtitleText_StringAttributeData=" + this.subtitleText_StringAttributeData + ", info_StringAttributeData=" + this.info_StringAttributeData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
