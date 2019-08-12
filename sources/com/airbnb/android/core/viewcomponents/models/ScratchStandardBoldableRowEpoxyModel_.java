package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelLongClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.ScratchStandardBoldableRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ScratchStandardBoldableRowEpoxyModel_ extends ScratchStandardBoldableRowEpoxyModel implements GeneratedModel<ScratchStandardBoldableRow> {
    private OnModelBoundListener<ScratchStandardBoldableRowEpoxyModel_, ScratchStandardBoldableRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ScratchStandardBoldableRowEpoxyModel_, ScratchStandardBoldableRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ScratchStandardBoldableRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
        if (this.longClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.longClickListener).bind(holder, object);
        }
        if (this.rowDrawableClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.rowDrawableClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ScratchStandardBoldableRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ScratchStandardBoldableRowEpoxyModel_ onBind(OnModelBoundListener<ScratchStandardBoldableRowEpoxyModel_, ScratchStandardBoldableRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ScratchStandardBoldableRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ScratchStandardBoldableRowEpoxyModel_ onUnbind(OnModelUnboundListener<ScratchStandardBoldableRowEpoxyModel_, ScratchStandardBoldableRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        super.title(title);
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public ScratchStandardBoldableRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ScratchStandardBoldableRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        super.subtitle(subtitle);
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public ScratchStandardBoldableRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public ScratchStandardBoldableRowEpoxyModel_ clickListener(OnModelClickListener<ScratchStandardBoldableRowEpoxyModel_, ScratchStandardBoldableRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ScratchStandardBoldableRowEpoxyModel_ longClickListener(OnModelLongClickListener<ScratchStandardBoldableRowEpoxyModel_, ScratchStandardBoldableRow> longClickListener) {
        onMutation();
        if (longClickListener == null) {
            this.longClickListener = null;
        } else {
            this.longClickListener = new WrappedEpoxyModelClickListener(this, longClickListener);
        }
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ longClickListener(OnLongClickListener longClickListener) {
        onMutation();
        this.longClickListener = longClickListener;
        return this;
    }

    public OnLongClickListener longClickListener() {
        return this.longClickListener;
    }

    public ScratchStandardBoldableRowEpoxyModel_ rowDrawableClickListener(OnModelClickListener<ScratchStandardBoldableRowEpoxyModel_, ScratchStandardBoldableRow> rowDrawableClickListener) {
        onMutation();
        if (rowDrawableClickListener == null) {
            this.rowDrawableClickListener = null;
        } else {
            this.rowDrawableClickListener = new WrappedEpoxyModelClickListener(this, rowDrawableClickListener);
        }
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ rowDrawableClickListener(OnClickListener rowDrawableClickListener) {
        onMutation();
        this.rowDrawableClickListener = rowDrawableClickListener;
        return this;
    }

    public OnClickListener rowDrawableClickListener() {
        return this.rowDrawableClickListener;
    }

    public ScratchStandardBoldableRowEpoxyModel_ rowDrawableRes(int rowDrawableRes) {
        onMutation();
        this.rowDrawableRes = rowDrawableRes;
        return this;
    }

    public int rowDrawableRes() {
        return this.rowDrawableRes;
    }

    public ScratchStandardBoldableRowEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public ScratchStandardBoldableRowEpoxyModel_ bold(boolean bold) {
        onMutation();
        this.bold = bold;
        return this;
    }

    public boolean bold() {
        return this.bold;
    }

    public ScratchStandardBoldableRowEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public ScratchStandardBoldableRowEpoxyModel_ textMode(int textMode) {
        onMutation();
        this.textMode = textMode;
        return this;
    }

    public int textMode() {
        return this.textMode;
    }

    public ScratchStandardBoldableRowEpoxyModel_ hideRowDrawable(boolean hideRowDrawable) {
        onMutation();
        this.hideRowDrawable = hideRowDrawable;
        return this;
    }

    public boolean hideRowDrawable() {
        return this.hideRowDrawable;
    }

    public ScratchStandardBoldableRowEpoxyModel_ subtitleMaxLine(int subtitleMaxLine) {
        onMutation();
        this.subtitleMaxLine = subtitleMaxLine;
        return this;
    }

    public int subtitleMaxLine() {
        return this.subtitleMaxLine;
    }

    public ScratchStandardBoldableRowEpoxyModel_ titleMaxLine(int titleMaxLine) {
        onMutation();
        this.titleMaxLine = titleMaxLine;
        return this;
    }

    public int titleMaxLine() {
        return this.titleMaxLine;
    }

    public ScratchStandardBoldableRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public ScratchStandardBoldableRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ actionText(int actionTextRes) {
        super.actionText(actionTextRes);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ actionText(CharSequence actionText) {
        super.actionText(actionText);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ infoText(int infoTextRes) {
        super.infoText(infoTextRes);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ infoText(CharSequence infoText) {
        super.infoText(infoText);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ placeholderText(int placeholderTextRes) {
        super.placeholderText(placeholderTextRes);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ placeholderText(CharSequence placeholderText) {
        super.placeholderText(placeholderText);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ subtitle(int subtitleRes) {
        super.subtitle(subtitleRes);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ disclosure() {
        super.disclosure();
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ScratchStandardBoldableRowEpoxyModel_ m5494id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ScratchStandardBoldableRowEpoxyModel_ m5499id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ScratchStandardBoldableRowEpoxyModel_ m5495id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ScratchStandardBoldableRowEpoxyModel_ m5496id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ScratchStandardBoldableRowEpoxyModel_ m5498id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ScratchStandardBoldableRowEpoxyModel_ m5497id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_standard_boldable_row;
    }

    public ScratchStandardBoldableRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.clickListener = null;
        this.longClickListener = null;
        this.rowDrawableClickListener = null;
        this.rowDrawableRes = 0;
        this.textRes = 0;
        this.bold = false;
        this.text = null;
        this.textMode = 0;
        this.hideRowDrawable = false;
        this.subtitleMaxLine = 0;
        this.titleMaxLine = 0;
        this.enabled = false;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ScratchStandardBoldableRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ScratchStandardBoldableRowEpoxyModel_ that = (ScratchStandardBoldableRowEpoxyModel_) o;
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
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
            return false;
        }
        if (this.longClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.longClickListener == null)) {
            return false;
        }
        if (this.rowDrawableClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (that.rowDrawableClickListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z4 != z5 || this.rowDrawableRes != that.rowDrawableRes || this.textRes != that.textRes || this.bold != that.bold) {
            return false;
        }
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.textMode != that.textMode || this.hideRowDrawable != that.hideRowDrawable || this.subtitleMaxLine != that.subtitleMaxLine || this.titleMaxLine != that.titleMaxLine || this.enabled != that.enabled) {
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
        int i10;
        int i11 = 1;
        int i12 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i13 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i14 = (((i13 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i15 = (((i14 + i3) * 31) + this.subtitleRes) * 31;
        if (this.clickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i16 = (i15 + i4) * 31;
        if (this.longClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i17 = (i16 + i5) * 31;
        if (this.rowDrawableClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i18 = (((((i17 + i6) * 31) + this.rowDrawableRes) * 31) + this.textRes) * 31;
        if (this.bold) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i19 = (i18 + i7) * 31;
        if (this.text != null) {
            i8 = this.text.hashCode();
        } else {
            i8 = 0;
        }
        int i20 = (((i19 + i8) * 31) + this.textMode) * 31;
        if (this.hideRowDrawable) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i21 = (((((i20 + i9) * 31) + this.subtitleMaxLine) * 31) + this.titleMaxLine) * 31;
        if (!this.enabled) {
            i11 = 0;
        }
        int i22 = (i21 + i11) * 31;
        if (this.showDivider != null) {
            i10 = this.showDivider.hashCode();
        } else {
            i10 = 0;
        }
        int i23 = (i22 + i10) * 31;
        if (this.numCarouselItemsShown != null) {
            i12 = this.numCarouselItemsShown.hashCode();
        }
        return i23 + i12;
    }

    public String toString() {
        return "ScratchStandardBoldableRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", clickListener=" + this.clickListener + ", longClickListener=" + this.longClickListener + ", rowDrawableClickListener=" + this.rowDrawableClickListener + ", rowDrawableRes=" + this.rowDrawableRes + ", textRes=" + this.textRes + ", bold=" + this.bold + ", text=" + this.text + ", textMode=" + this.textMode + ", hideRowDrawable=" + this.hideRowDrawable + ", subtitleMaxLine=" + this.subtitleMaxLine + ", titleMaxLine=" + this.titleMaxLine + ", enabled=" + this.enabled + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
