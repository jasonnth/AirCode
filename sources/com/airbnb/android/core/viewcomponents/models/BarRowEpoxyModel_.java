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
import com.airbnb.p027n2.components.BarRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class BarRowEpoxyModel_ extends BarRowEpoxyModel implements GeneratedModel<BarRow> {
    private OnModelBoundListener<BarRowEpoxyModel_, BarRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<BarRowEpoxyModel_, BarRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, BarRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(BarRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public BarRowEpoxyModel_ onBind(OnModelBoundListener<BarRowEpoxyModel_, BarRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(BarRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public BarRowEpoxyModel_ onUnbind(OnModelUnboundListener<BarRowEpoxyModel_, BarRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public BarRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        super.title(title);
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public BarRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public BarRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        super.subtitle(subtitle);
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public BarRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public BarRowEpoxyModel_ progressLabel(CharSequence progressLabel) {
        onMutation();
        this.progressLabel = progressLabel;
        super.progressLabel(progressLabel);
        return this;
    }

    public CharSequence progressLabel() {
        return this.progressLabel;
    }

    public BarRowEpoxyModel_ progressLabelRes(int progressLabelRes) {
        onMutation();
        this.progressLabelRes = progressLabelRes;
        return this;
    }

    public int progressLabelRes() {
        return this.progressLabelRes;
    }

    public BarRowEpoxyModel_ progressLabelVisible(boolean progressLabelVisible) {
        onMutation();
        this.progressLabelVisible = progressLabelVisible;
        return this;
    }

    public boolean progressLabelVisible() {
        return this.progressLabelVisible;
    }

    public BarRowEpoxyModel_ value(float value) {
        onMutation();
        this.value = value;
        super.value(value);
        return this;
    }

    public float value() {
        return this.value;
    }

    public BarRowEpoxyModel_ threshold(float threshold) {
        onMutation();
        this.threshold = threshold;
        super.threshold(threshold);
        return this;
    }

    public float threshold() {
        return this.threshold;
    }

    public BarRowEpoxyModel_ thresholdIndicatorVisible(boolean thresholdIndicatorVisible) {
        onMutation();
        this.thresholdIndicatorVisible = thresholdIndicatorVisible;
        return this;
    }

    public boolean thresholdIndicatorVisible() {
        return this.thresholdIndicatorVisible;
    }

    public BarRowEpoxyModel_ filledSectionColor(int filledSectionColor) {
        onMutation();
        this.filledSectionColor = filledSectionColor;
        return this;
    }

    public int filledSectionColor() {
        return this.filledSectionColor;
    }

    public BarRowEpoxyModel_ clickListener(OnModelClickListener<BarRowEpoxyModel_, BarRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public BarRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public BarRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public BarRowEpoxyModel_ title(int titleRes) {
        super.title(titleRes);
        return this;
    }

    public BarRowEpoxyModel_ subtitle(int subtitleRes) {
        super.subtitle(subtitleRes);
        return this;
    }

    public BarRowEpoxyModel_ progressLabel(int progressLabelRes) {
        super.progressLabel(progressLabelRes);
        return this;
    }

    public BarRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public BarRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public BarRowEpoxyModel_ m4339id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public BarRowEpoxyModel_ m4344id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public BarRowEpoxyModel_ m4340id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public BarRowEpoxyModel_ m4341id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public BarRowEpoxyModel_ m4343id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public BarRowEpoxyModel_ m4342id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public BarRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public BarRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public BarRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public BarRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public BarRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_bar_row;
    }

    public BarRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.progressLabel = null;
        this.progressLabelRes = 0;
        this.progressLabelVisible = false;
        this.value = 0.0f;
        this.threshold = 0.0f;
        this.thresholdIndicatorVisible = false;
        this.filledSectionColor = 0;
        this.clickListener = null;
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
        if (!(o instanceof BarRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        BarRowEpoxyModel_ that = (BarRowEpoxyModel_) o;
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
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.subtitleRes != that.subtitleRes) {
            return false;
        }
        if (this.progressLabel != null) {
            if (!this.progressLabel.equals(that.progressLabel)) {
                return false;
            }
        } else if (that.progressLabel != null) {
            return false;
        }
        if (this.progressLabelRes != that.progressLabelRes || this.progressLabelVisible != that.progressLabelVisible || Float.compare(that.value, this.value) != 0 || Float.compare(that.threshold, this.threshold) != 0 || this.thresholdIndicatorVisible != that.thresholdIndicatorVisible || this.filledSectionColor != that.filledSectionColor) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null)) {
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
        int i8;
        int i9;
        int i10 = 1;
        int i11 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i12 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (((i12 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i14 = (((i13 + i3) * 31) + this.subtitleRes) * 31;
        if (this.progressLabel != null) {
            i4 = this.progressLabel.hashCode();
        } else {
            i4 = 0;
        }
        int i15 = (((i14 + i4) * 31) + this.progressLabelRes) * 31;
        if (this.progressLabelVisible) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        if (this.value != 0.0f) {
            i6 = Float.floatToIntBits(this.value);
        } else {
            i6 = 0;
        }
        int i17 = (i16 + i6) * 31;
        if (this.threshold != 0.0f) {
            i7 = Float.floatToIntBits(this.threshold);
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
        if (this.thresholdIndicatorVisible) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i19 = (((i18 + i8) * 31) + this.filledSectionColor) * 31;
        if (this.clickListener == null) {
            i10 = 0;
        }
        int i20 = (i19 + i10) * 31;
        if (this.showDivider != null) {
            i9 = this.showDivider.hashCode();
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.numCarouselItemsShown != null) {
            i11 = this.numCarouselItemsShown.hashCode();
        }
        return i21 + i11;
    }

    public String toString() {
        return "BarRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", progressLabel=" + this.progressLabel + ", progressLabelRes=" + this.progressLabelRes + ", progressLabelVisible=" + this.progressLabelVisible + ", value=" + this.value + ", threshold=" + this.threshold + ", thresholdIndicatorVisible=" + this.thresholdIndicatorVisible + ", filledSectionColor=" + this.filledSectionColor + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
