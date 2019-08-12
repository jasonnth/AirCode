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
import com.airbnb.p027n2.epoxy.NoDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.LabelDocumentMarqueeModel_ */
public class LabelDocumentMarqueeModel_ extends NoDividerBaseModel<LabelDocumentMarquee> implements GeneratedModel<LabelDocumentMarquee> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(7);
    private StringAttributeData captionText_StringAttributeData = new StringAttributeData();
    private StringAttributeData labelText_StringAttributeData = new StringAttributeData(null);
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<LabelDocumentMarqueeModel_, LabelDocumentMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<LabelDocumentMarqueeModel_, LabelDocumentMarquee> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData titleText_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, LabelDocumentMarquee object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(LabelDocumentMarquee object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setCaptionText(this.captionText_StringAttributeData.toString(object.getContext()));
        object.setTitleText(this.titleText_StringAttributeData.toString(object.getContext()));
        object.setLabelText(this.labelText_StringAttributeData.toString(object.getContext()));
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
    }

    public void bind(LabelDocumentMarquee object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = true;
        if (!(previousModel instanceof LabelDocumentMarqueeModel_)) {
            bind(object);
            return;
        }
        LabelDocumentMarqueeModel_ that = (LabelDocumentMarqueeModel_) previousModel;
        super.bind(object);
        boolean z3 = this.onClickListener_OnClickListener == null;
        if (that.onClickListener_OnClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (z3 != z) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (!this.captionText_StringAttributeData.equals(that.captionText_StringAttributeData)) {
            object.setCaptionText(this.captionText_StringAttributeData.toString(object.getContext()));
        }
        if (!this.titleText_StringAttributeData.equals(that.titleText_StringAttributeData)) {
            object.setTitleText(this.titleText_StringAttributeData.toString(object.getContext()));
        }
        if (!this.labelText_StringAttributeData.equals(that.labelText_StringAttributeData)) {
            object.setLabelText(this.labelText_StringAttributeData.toString(object.getContext()));
        }
        boolean z4 = this.onLongClickListener_OnLongClickListener == null;
        if (that.onLongClickListener_OnLongClickListener != null) {
            z2 = false;
        }
        if (z4 != z2) {
            object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        }
    }

    public void handlePostBind(LabelDocumentMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(LabelDocumentMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setLabelText(null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public LabelDocumentMarqueeModel_ titleText(CharSequence titleText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.titleText_StringAttributeData.setValue(titleText);
        return this;
    }

    public LabelDocumentMarqueeModel_ captionText(CharSequence captionText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.captionText_StringAttributeData.setValue(captionText);
        return this;
    }

    public LabelDocumentMarqueeModel_ labelText(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.labelText_StringAttributeData.setValue(stringRes);
        return this;
    }

    public LabelDocumentMarqueeModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(6);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public LabelDocumentMarqueeModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public LabelDocumentMarqueeModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public LabelDocumentMarqueeModel_ m1518id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public LabelDocumentMarqueeModel_ m1523id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public LabelDocumentMarqueeModel_ m1519id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public LabelDocumentMarqueeModel_ m1520id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public LabelDocumentMarqueeModel_ m1522id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public LabelDocumentMarqueeModel_ m1521id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public LabelDocumentMarqueeModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public LabelDocumentMarqueeModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public LabelDocumentMarqueeModel_ show() {
        super.show();
        return this;
    }

    public LabelDocumentMarqueeModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public LabelDocumentMarqueeModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_label_document_marquee;
    }

    public LabelDocumentMarqueeModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.titleText_StringAttributeData = new StringAttributeData();
        this.captionText_StringAttributeData = new StringAttributeData();
        this.labelText_StringAttributeData = new StringAttributeData(null);
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
        if (!(o instanceof LabelDocumentMarqueeModel_) || !super.equals(o)) {
            return false;
        }
        LabelDocumentMarqueeModel_ that = (LabelDocumentMarqueeModel_) o;
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
        if (this.titleText_StringAttributeData != null) {
            if (!this.titleText_StringAttributeData.equals(that.titleText_StringAttributeData)) {
                return false;
            }
        } else if (that.titleText_StringAttributeData != null) {
            return false;
        }
        if (this.captionText_StringAttributeData != null) {
            if (!this.captionText_StringAttributeData.equals(that.captionText_StringAttributeData)) {
                return false;
            }
        } else if (that.captionText_StringAttributeData != null) {
            return false;
        }
        if (this.labelText_StringAttributeData != null) {
            if (!this.labelText_StringAttributeData.equals(that.labelText_StringAttributeData)) {
                return false;
            }
        } else if (that.labelText_StringAttributeData != null) {
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
        if (this.titleText_StringAttributeData != null) {
            i2 = this.titleText_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.captionText_StringAttributeData != null) {
            i3 = this.captionText_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.labelText_StringAttributeData != null) {
            i4 = this.labelText_StringAttributeData.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
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
        return "LabelDocumentMarqueeModel_{titleText_StringAttributeData=" + this.titleText_StringAttributeData + ", captionText_StringAttributeData=" + this.captionText_StringAttributeData + ", labelText_StringAttributeData=" + this.labelText_StringAttributeData + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
