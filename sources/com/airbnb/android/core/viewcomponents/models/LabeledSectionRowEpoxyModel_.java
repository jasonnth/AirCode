package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.LabeledSectionRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class LabeledSectionRowEpoxyModel_ extends LabeledSectionRowEpoxyModel implements GeneratedModel<LabeledSectionRow> {
    private OnModelBoundListener<LabeledSectionRowEpoxyModel_, LabeledSectionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<LabeledSectionRowEpoxyModel_, LabeledSectionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, LabeledSectionRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(LabeledSectionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public LabeledSectionRowEpoxyModel_ onBind(OnModelBoundListener<LabeledSectionRowEpoxyModel_, LabeledSectionRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(LabeledSectionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public LabeledSectionRowEpoxyModel_ onUnbind(OnModelUnboundListener<LabeledSectionRowEpoxyModel_, LabeledSectionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public LabeledSectionRowEpoxyModel_ labelText(CharSequence labelText) {
        onMutation();
        this.labelText = labelText;
        return this;
    }

    public CharSequence labelText() {
        return this.labelText;
    }

    public LabeledSectionRowEpoxyModel_ labelRes(int labelRes) {
        onMutation();
        this.labelRes = labelRes;
        return this;
    }

    public int labelRes() {
        return this.labelRes;
    }

    public LabeledSectionRowEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public LabeledSectionRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public LabeledSectionRowEpoxyModel_ bodyText(CharSequence bodyText) {
        onMutation();
        this.bodyText = bodyText;
        return this;
    }

    public CharSequence bodyText() {
        return this.bodyText;
    }

    public LabeledSectionRowEpoxyModel_ bodyRes(int bodyRes) {
        onMutation();
        this.bodyRes = bodyRes;
        return this;
    }

    public int bodyRes() {
        return this.bodyRes;
    }

    public LabeledSectionRowEpoxyModel_ actionText(CharSequence actionText) {
        onMutation();
        this.actionText = actionText;
        return this;
    }

    public CharSequence actionText() {
        return this.actionText;
    }

    public LabeledSectionRowEpoxyModel_ actionRes(int actionRes) {
        onMutation();
        this.actionRes = actionRes;
        return this;
    }

    public int actionRes() {
        return this.actionRes;
    }

    public LabeledSectionRowEpoxyModel_ labelIconRes(int labelIconRes) {
        onMutation();
        this.labelIconRes = labelIconRes;
        return this;
    }

    public int labelIconRes() {
        return this.labelIconRes;
    }

    public LabeledSectionRowEpoxyModel_ clickListener(OnModelClickListener<LabeledSectionRowEpoxyModel_, LabeledSectionRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public LabeledSectionRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public LabeledSectionRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public LabeledSectionRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public LabeledSectionRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public LabeledSectionRowEpoxyModel_ m5002id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public LabeledSectionRowEpoxyModel_ m5007id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public LabeledSectionRowEpoxyModel_ m5003id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public LabeledSectionRowEpoxyModel_ m5004id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public LabeledSectionRowEpoxyModel_ m5006id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public LabeledSectionRowEpoxyModel_ m5005id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public LabeledSectionRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public LabeledSectionRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public LabeledSectionRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public LabeledSectionRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public LabeledSectionRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_labeled_section_row;
    }

    public LabeledSectionRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.labelText = null;
        this.labelRes = 0;
        this.titleText = null;
        this.titleRes = 0;
        this.bodyText = null;
        this.bodyRes = 0;
        this.actionText = null;
        this.actionRes = 0;
        this.labelIconRes = 0;
        this.clickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof LabeledSectionRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        LabeledSectionRowEpoxyModel_ that = (LabeledSectionRowEpoxyModel_) o;
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
        if (this.labelText != null) {
            if (!this.labelText.equals(that.labelText)) {
                return false;
            }
        } else if (that.labelText != null) {
            return false;
        }
        if (this.labelRes != that.labelRes) {
            return false;
        }
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.bodyText != null) {
            if (!this.bodyText.equals(that.bodyText)) {
                return false;
            }
        } else if (that.bodyText != null) {
            return false;
        }
        if (this.bodyRes != that.bodyRes) {
            return false;
        }
        if (this.actionText != null) {
            if (!this.actionText.equals(that.actionText)) {
                return false;
            }
        } else if (that.actionText != null) {
            return false;
        }
        if (this.actionRes != that.actionRes || this.labelIconRes != that.labelIconRes) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
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
        if (this.labelText != null) {
            i2 = this.labelText.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (((i9 + i2) * 31) + this.labelRes) * 31;
        if (this.titleText != null) {
            i3 = this.titleText.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (((i10 + i3) * 31) + this.titleRes) * 31;
        if (this.bodyText != null) {
            i4 = this.bodyText.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (((i11 + i4) * 31) + this.bodyRes) * 31;
        if (this.actionText != null) {
            i5 = this.actionText.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (((((i12 + i5) * 31) + this.actionRes) * 31) + this.labelIconRes) * 31;
        if (this.clickListener == null) {
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
        return "LabeledSectionRowEpoxyModel_{labelText=" + this.labelText + ", labelRes=" + this.labelRes + ", titleText=" + this.titleText + ", titleRes=" + this.titleRes + ", bodyText=" + this.bodyText + ", bodyRes=" + this.bodyRes + ", actionText=" + this.actionText + ", actionRes=" + this.actionRes + ", labelIconRes=" + this.labelIconRes + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
