package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ValueRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ValueRowEpoxyModel_ extends ValueRowEpoxyModel implements GeneratedModel<ValueRow> {
    private OnModelBoundListener<ValueRowEpoxyModel_, ValueRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ValueRowEpoxyModel_, ValueRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ValueRow object, int position) {
    }

    public void handlePostBind(ValueRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ValueRowEpoxyModel_ onBind(OnModelBoundListener<ValueRowEpoxyModel_, ValueRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ValueRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ValueRowEpoxyModel_ onUnbind(OnModelUnboundListener<ValueRowEpoxyModel_, ValueRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ValueRowEpoxyModel_ valueText(CharSequence valueText) {
        onMutation();
        this.valueText = valueText;
        return this;
    }

    public CharSequence valueText() {
        return this.valueText;
    }

    public ValueRowEpoxyModel_ valueRes(int valueRes) {
        onMutation();
        this.valueRes = valueRes;
        return this;
    }

    public int valueRes() {
        return this.valueRes;
    }

    public ValueRowEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public ValueRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ValueRowEpoxyModel_ subtitleText(CharSequence subtitleText) {
        onMutation();
        this.subtitleText = subtitleText;
        return this;
    }

    public CharSequence subtitleText() {
        return this.subtitleText;
    }

    public ValueRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public ValueRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ValueRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ValueRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ValueRowEpoxyModel_ m5770id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ValueRowEpoxyModel_ m5775id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ValueRowEpoxyModel_ m5771id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ValueRowEpoxyModel_ m5772id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ValueRowEpoxyModel_ m5774id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ValueRowEpoxyModel_ m5773id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ValueRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ValueRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ValueRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ValueRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ValueRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_value_row;
    }

    public ValueRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.valueText = null;
        this.valueRes = 0;
        this.titleText = null;
        this.titleRes = 0;
        this.subtitleText = null;
        this.subtitleRes = 0;
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
        if (!(o instanceof ValueRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ValueRowEpoxyModel_ that = (ValueRowEpoxyModel_) o;
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
        if (this.valueText != null) {
            if (!this.valueText.equals(that.valueText)) {
                return false;
            }
        } else if (that.valueText != null) {
            return false;
        }
        if (this.valueRes != that.valueRes) {
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
        if (this.subtitleText != null) {
            if (!this.subtitleText.equals(that.subtitleText)) {
                return false;
            }
        } else if (that.subtitleText != null) {
            return false;
        }
        if (this.subtitleRes != that.subtitleRes) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i5 = 0;
        }
        int i7 = (hashCode + i5) * 31;
        if (this.valueText != null) {
            i = this.valueText.hashCode();
        } else {
            i = 0;
        }
        int i8 = (((i7 + i) * 31) + this.valueRes) * 31;
        if (this.titleText != null) {
            i2 = this.titleText.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((i8 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitleText != null) {
            i3 = this.subtitleText.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((i9 + i3) * 31) + this.subtitleRes) * 31;
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
        return "ValueRowEpoxyModel_{valueText=" + this.valueText + ", valueRes=" + this.valueRes + ", titleText=" + this.titleText + ", titleRes=" + this.titleRes + ", subtitleText=" + this.subtitleText + ", subtitleRes=" + this.subtitleRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
