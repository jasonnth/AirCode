package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.StepperRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

public class StepperRowEpoxyModel_ extends StepperRowEpoxyModel implements GeneratedModel<StepperRow> {
    private OnModelBoundListener<StepperRowEpoxyModel_, StepperRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StepperRowEpoxyModel_, StepperRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StepperRow object, int position) {
    }

    public void handlePostBind(StepperRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StepperRowEpoxyModel_ onBind(OnModelBoundListener<StepperRowEpoxyModel_, StepperRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StepperRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StepperRowEpoxyModel_ onUnbind(OnModelUnboundListener<StepperRowEpoxyModel_, StepperRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StepperRowEpoxyModel_ defaultText(CharSequence defaultText) {
        onMutation();
        this.defaultText = defaultText;
        return this;
    }

    public CharSequence defaultText() {
        return this.defaultText;
    }

    public StepperRowEpoxyModel_ descriptionText(CharSequence descriptionText) {
        onMutation();
        this.descriptionText = descriptionText;
        return this;
    }

    public CharSequence descriptionText() {
        return this.descriptionText;
    }

    public StepperRowEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public StepperRowEpoxyModel_ pluralsRes(int pluralsRes) {
        onMutation();
        this.pluralsRes = pluralsRes;
        return this;
    }

    public int pluralsRes() {
        return this.pluralsRes;
    }

    public StepperRowEpoxyModel_ minValueRes(int minValueRes) {
        onMutation();
        this.minValueRes = minValueRes;
        return this;
    }

    public int minValueRes() {
        return this.minValueRes;
    }

    public StepperRowEpoxyModel_ minValue(int minValue) {
        onMutation();
        this.minValue = minValue;
        return this;
    }

    public int minValue() {
        return this.minValue;
    }

    public StepperRowEpoxyModel_ maxValueRes(int maxValueRes) {
        onMutation();
        this.maxValueRes = maxValueRes;
        return this;
    }

    public int maxValueRes() {
        return this.maxValueRes;
    }

    public StepperRowEpoxyModel_ maxValue(int maxValue) {
        onMutation();
        this.maxValue = maxValue;
        return this;
    }

    public int maxValue() {
        return this.maxValue;
    }

    public StepperRowEpoxyModel_ value(int value) {
        onMutation();
        this.value = value;
        return this;
    }

    public int value() {
        return this.value;
    }

    public StepperRowEpoxyModel_ valueChangedListener(OnValueChangedListener valueChangedListener) {
        onMutation();
        this.valueChangedListener = valueChangedListener;
        return this;
    }

    public OnValueChangedListener valueChangedListener() {
        return this.valueChangedListener;
    }

    public StepperRowEpoxyModel_ useOldDesign(boolean useOldDesign) {
        onMutation();
        this.useOldDesign = useOldDesign;
        return this;
    }

    public boolean useOldDesign() {
        return this.useOldDesign;
    }

    public StepperRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StepperRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StepperRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StepperRowEpoxyModel_ m5626id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StepperRowEpoxyModel_ m5631id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StepperRowEpoxyModel_ m5627id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StepperRowEpoxyModel_ m5628id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StepperRowEpoxyModel_ m5630id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StepperRowEpoxyModel_ m5629id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StepperRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StepperRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StepperRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StepperRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StepperRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_stepper_row;
    }

    public StepperRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.defaultText = null;
        this.descriptionText = null;
        this.textRes = 0;
        this.pluralsRes = 0;
        this.minValueRes = 0;
        this.minValue = 0;
        this.maxValueRes = 0;
        this.maxValue = 0;
        this.value = 0;
        this.valueChangedListener = null;
        this.useOldDesign = false;
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
        if (!(o instanceof StepperRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StepperRowEpoxyModel_ that = (StepperRowEpoxyModel_) o;
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
        if (this.defaultText != null) {
            if (!this.defaultText.equals(that.defaultText)) {
                return false;
            }
        } else if (that.defaultText != null) {
            return false;
        }
        if (this.descriptionText != null) {
            if (!this.descriptionText.equals(that.descriptionText)) {
                return false;
            }
        } else if (that.descriptionText != null) {
            return false;
        }
        if (this.textRes != that.textRes || this.pluralsRes != that.pluralsRes || this.minValueRes != that.minValueRes || this.minValue != that.minValue || this.maxValueRes != that.maxValueRes || this.maxValue != that.maxValue || this.value != that.value) {
            return false;
        }
        if ((this.valueChangedListener == null) != (that.valueChangedListener == null) || this.useOldDesign != that.useOldDesign) {
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
        if (this.defaultText != null) {
            i2 = this.defaultText.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.descriptionText != null) {
            i3 = this.descriptionText.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((((((((((((((i9 + i3) * 31) + this.textRes) * 31) + this.pluralsRes) * 31) + this.minValueRes) * 31) + this.minValue) * 31) + this.maxValueRes) * 31) + this.maxValue) * 31) + this.value) * 31;
        if (this.valueChangedListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (!this.useOldDesign) {
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
        return "StepperRowEpoxyModel_{defaultText=" + this.defaultText + ", descriptionText=" + this.descriptionText + ", textRes=" + this.textRes + ", pluralsRes=" + this.pluralsRes + ", minValueRes=" + this.minValueRes + ", minValue=" + this.minValue + ", maxValueRes=" + this.maxValueRes + ", maxValue=" + this.maxValue + ", value=" + this.value + ", valueChangedListener=" + this.valueChangedListener + ", useOldDesign=" + this.useOldDesign + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
