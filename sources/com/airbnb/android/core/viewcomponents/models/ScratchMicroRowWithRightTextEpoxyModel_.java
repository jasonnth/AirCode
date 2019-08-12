package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ScratchMicroRowWithRightText;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ScratchMicroRowWithRightTextEpoxyModel_ extends ScratchMicroRowWithRightTextEpoxyModel implements GeneratedModel<ScratchMicroRowWithRightText> {
    private OnModelBoundListener<ScratchMicroRowWithRightTextEpoxyModel_, ScratchMicroRowWithRightText> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ScratchMicroRowWithRightTextEpoxyModel_, ScratchMicroRowWithRightText> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ScratchMicroRowWithRightText object, int position) {
    }

    public void handlePostBind(ScratchMicroRowWithRightText object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ onBind(OnModelBoundListener<ScratchMicroRowWithRightTextEpoxyModel_, ScratchMicroRowWithRightText> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ScratchMicroRowWithRightText object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ onUnbind(OnModelUnboundListener<ScratchMicroRowWithRightTextEpoxyModel_, ScratchMicroRowWithRightText> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ScratchMicroRowWithRightTextEpoxyModel_ m5482id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ScratchMicroRowWithRightTextEpoxyModel_ m5487id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ScratchMicroRowWithRightTextEpoxyModel_ m5483id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ScratchMicroRowWithRightTextEpoxyModel_ m5484id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ScratchMicroRowWithRightTextEpoxyModel_ m5486id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ScratchMicroRowWithRightTextEpoxyModel_ m5485id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_scratch_micro_row_with_right_text;
    }

    public ScratchMicroRowWithRightTextEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.text = null;
        this.titleRes = 0;
        this.textRes = 0;
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
        if (!(o instanceof ScratchMicroRowWithRightTextEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ScratchMicroRowWithRightTextEpoxyModel_ that = (ScratchMicroRowWithRightTextEpoxyModel_) o;
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
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.textRes != that.textRes) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.text != null) {
            i2 = this.text.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((((i7 + i2) * 31) + this.titleRes) * 31) + this.textRes) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "ScratchMicroRowWithRightTextEpoxyModel_{title=" + this.title + ", text=" + this.text + ", titleRes=" + this.titleRes + ", textRes=" + this.textRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
