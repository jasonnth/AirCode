package com.airbnb.android.core.viewcomponents.models;

import android.graphics.drawable.Drawable;
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
import com.airbnb.p027n2.components.StandardRowWithLabel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.fonts.Font;

public class StandardRowWithLabelEpoxyModel_ extends StandardRowWithLabelEpoxyModel implements GeneratedModel<StandardRowWithLabel> {
    private OnModelBoundListener<StandardRowWithLabelEpoxyModel_, StandardRowWithLabel> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StandardRowWithLabelEpoxyModel_, StandardRowWithLabel> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StandardRowWithLabel object, int position) {
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

    public void handlePostBind(StandardRowWithLabel object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StandardRowWithLabelEpoxyModel_ onBind(OnModelBoundListener<StandardRowWithLabelEpoxyModel_, StandardRowWithLabel> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StandardRowWithLabel object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StandardRowWithLabelEpoxyModel_ onUnbind(OnModelUnboundListener<StandardRowWithLabelEpoxyModel_, StandardRowWithLabel> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        super.title(title);
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public StandardRowWithLabelEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public StandardRowWithLabelEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        super.subtitle(subtitle);
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public StandardRowWithLabelEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public StandardRowWithLabelEpoxyModel_ label(CharSequence label) {
        onMutation();
        this.label = label;
        super.label(label);
        return this;
    }

    public CharSequence label() {
        return this.label;
    }

    public StandardRowWithLabelEpoxyModel_ labelRes(int labelRes) {
        onMutation();
        this.labelRes = labelRes;
        return this;
    }

    public int labelRes() {
        return this.labelRes;
    }

    public StandardRowWithLabelEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public StandardRowWithLabelEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public StandardRowWithLabelEpoxyModel_ rowDrawableRes(int rowDrawableRes) {
        onMutation();
        this.rowDrawableRes = rowDrawableRes;
        return this;
    }

    public int rowDrawableRes() {
        return this.rowDrawableRes;
    }

    public StandardRowWithLabelEpoxyModel_ labelBackgroundRes(int labelBackgroundRes) {
        onMutation();
        this.labelBackgroundRes = labelBackgroundRes;
        return this;
    }

    public int labelBackgroundRes() {
        return this.labelBackgroundRes;
    }

    public StandardRowWithLabelEpoxyModel_ rowDrawable(Drawable rowDrawable) {
        onMutation();
        this.rowDrawable = rowDrawable;
        super.rowDrawable(rowDrawable);
        return this;
    }

    public Drawable rowDrawable() {
        return this.rowDrawable;
    }

    public StandardRowWithLabelEpoxyModel_ clickListener(OnModelClickListener<StandardRowWithLabelEpoxyModel_, StandardRowWithLabel> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public StandardRowWithLabelEpoxyModel_ longClickListener(OnModelLongClickListener<StandardRowWithLabelEpoxyModel_, StandardRowWithLabel> longClickListener) {
        onMutation();
        if (longClickListener == null) {
            this.longClickListener = null;
        } else {
            this.longClickListener = new WrappedEpoxyModelClickListener(this, longClickListener);
        }
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ longClickListener(OnLongClickListener longClickListener) {
        onMutation();
        this.longClickListener = longClickListener;
        return this;
    }

    public OnLongClickListener longClickListener() {
        return this.longClickListener;
    }

    public StandardRowWithLabelEpoxyModel_ rowDrawableClickListener(OnModelClickListener<StandardRowWithLabelEpoxyModel_, StandardRowWithLabel> rowDrawableClickListener) {
        onMutation();
        if (rowDrawableClickListener == null) {
            this.rowDrawableClickListener = null;
        } else {
            this.rowDrawableClickListener = new WrappedEpoxyModelClickListener(this, rowDrawableClickListener);
        }
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ rowDrawableClickListener(OnClickListener rowDrawableClickListener) {
        onMutation();
        this.rowDrawableClickListener = rowDrawableClickListener;
        return this;
    }

    public OnClickListener rowDrawableClickListener() {
        return this.rowDrawableClickListener;
    }

    public StandardRowWithLabelEpoxyModel_ font(Font font) {
        onMutation();
        this.font = font;
        return this;
    }

    public Font font() {
        return this.font;
    }

    public StandardRowWithLabelEpoxyModel_ hideRowDrawable(boolean hideRowDrawable) {
        onMutation();
        this.hideRowDrawable = hideRowDrawable;
        return this;
    }

    public boolean hideRowDrawable() {
        return this.hideRowDrawable;
    }

    public StandardRowWithLabelEpoxyModel_ showRowBadge(boolean showRowBadge) {
        onMutation();
        this.showRowBadge = showRowBadge;
        return this;
    }

    public boolean showRowBadge() {
        return this.showRowBadge;
    }

    public StandardRowWithLabelEpoxyModel_ subTitleMaxLine(int subTitleMaxLine) {
        onMutation();
        this.subTitleMaxLine = subTitleMaxLine;
        return this;
    }

    public int subTitleMaxLine() {
        return this.subTitleMaxLine;
    }

    public StandardRowWithLabelEpoxyModel_ titleMaxLine(int titleMaxLine) {
        onMutation();
        this.titleMaxLine = titleMaxLine;
        return this;
    }

    public int titleMaxLine() {
        return this.titleMaxLine;
    }

    public StandardRowWithLabelEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public StandardRowWithLabelEpoxyModel_ textMode(int textMode) {
        onMutation();
        this.textMode = textMode;
        return this;
    }

    public int textMode() {
        return this.textMode;
    }

    public StandardRowWithLabelEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ title(int titleRes) {
        super.title(titleRes);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ actionText(int actionTextRes) {
        super.actionText(actionTextRes);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ actionText(CharSequence actionText) {
        super.actionText(actionText);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ infoText(int infoTextRes) {
        super.infoText(infoTextRes);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ infoText(CharSequence infoText) {
        super.infoText(infoText);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ placeholderText(int placeholderTextRes) {
        super.placeholderText(placeholderTextRes);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ placeholderText(CharSequence placeholderText) {
        super.placeholderText(placeholderText);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ subtitle(int subtitleRes) {
        super.subtitle(subtitleRes);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ label(int labelRes) {
        super.label(labelRes);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ disclosure() {
        super.disclosure();
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StandardRowWithLabelEpoxyModel_ m5614id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StandardRowWithLabelEpoxyModel_ m5619id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StandardRowWithLabelEpoxyModel_ m5615id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StandardRowWithLabelEpoxyModel_ m5616id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StandardRowWithLabelEpoxyModel_ m5618id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StandardRowWithLabelEpoxyModel_ m5617id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StandardRowWithLabelEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_standard_row_with_label;
    }

    public StandardRowWithLabelEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.label = null;
        this.labelRes = 0;
        this.text = null;
        this.textRes = 0;
        this.rowDrawableRes = 0;
        this.labelBackgroundRes = 0;
        this.rowDrawable = null;
        this.clickListener = null;
        this.longClickListener = null;
        this.rowDrawableClickListener = null;
        this.font = null;
        this.hideRowDrawable = false;
        this.showRowBadge = false;
        this.subTitleMaxLine = 0;
        this.titleMaxLine = 0;
        this.enabled = false;
        this.textMode = 0;
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof StandardRowWithLabelEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StandardRowWithLabelEpoxyModel_ that = (StandardRowWithLabelEpoxyModel_) o;
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
        if (this.label != null) {
            if (!this.label.equals(that.label)) {
                return false;
            }
        } else if (that.label != null) {
            return false;
        }
        if (this.labelRes != that.labelRes) {
            return false;
        }
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.textRes != that.textRes || this.rowDrawableRes != that.rowDrawableRes || this.labelBackgroundRes != that.labelBackgroundRes) {
            return false;
        }
        if (this.rowDrawable != null) {
            if (!this.rowDrawable.equals(that.rowDrawable)) {
                return false;
            }
        } else if (that.rowDrawable != null) {
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
        if (z4 != (that.rowDrawableClickListener == null)) {
            return false;
        }
        if (this.font != null) {
            if (!this.font.equals(that.font)) {
                return false;
            }
        } else if (that.font != null) {
            return false;
        }
        if (this.hideRowDrawable != that.hideRowDrawable || this.showRowBadge != that.showRowBadge || this.subTitleMaxLine != that.subTitleMaxLine || this.titleMaxLine != that.titleMaxLine || this.enabled != that.enabled || this.textMode != that.textMode) {
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
        int i11;
        int i12;
        int i13;
        int i14 = 1;
        int i15 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i16 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i17 = (((i16 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i18 = (((i17 + i3) * 31) + this.subtitleRes) * 31;
        if (this.label != null) {
            i4 = this.label.hashCode();
        } else {
            i4 = 0;
        }
        int i19 = (((i18 + i4) * 31) + this.labelRes) * 31;
        if (this.text != null) {
            i5 = this.text.hashCode();
        } else {
            i5 = 0;
        }
        int i20 = (((((((i19 + i5) * 31) + this.textRes) * 31) + this.rowDrawableRes) * 31) + this.labelBackgroundRes) * 31;
        if (this.rowDrawable != null) {
            i6 = this.rowDrawable.hashCode();
        } else {
            i6 = 0;
        }
        int i21 = (i20 + i6) * 31;
        if (this.clickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i22 = (i21 + i7) * 31;
        if (this.longClickListener != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i23 = (i22 + i8) * 31;
        if (this.rowDrawableClickListener != null) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i24 = (i23 + i9) * 31;
        if (this.font != null) {
            i10 = this.font.hashCode();
        } else {
            i10 = 0;
        }
        int i25 = (i24 + i10) * 31;
        if (this.hideRowDrawable) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i26 = (i25 + i11) * 31;
        if (this.showRowBadge) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i27 = (((((i26 + i12) * 31) + this.subTitleMaxLine) * 31) + this.titleMaxLine) * 31;
        if (!this.enabled) {
            i14 = 0;
        }
        int i28 = (((i27 + i14) * 31) + this.textMode) * 31;
        if (this.showDivider != null) {
            i13 = this.showDivider.hashCode();
        } else {
            i13 = 0;
        }
        int i29 = (i28 + i13) * 31;
        if (this.numCarouselItemsShown != null) {
            i15 = this.numCarouselItemsShown.hashCode();
        }
        return i29 + i15;
    }

    public String toString() {
        return "StandardRowWithLabelEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", label=" + this.label + ", labelRes=" + this.labelRes + ", text=" + this.text + ", textRes=" + this.textRes + ", rowDrawableRes=" + this.rowDrawableRes + ", labelBackgroundRes=" + this.labelBackgroundRes + ", rowDrawable=" + this.rowDrawable + ", clickListener=" + this.clickListener + ", longClickListener=" + this.longClickListener + ", rowDrawableClickListener=" + this.rowDrawableClickListener + ", font=" + this.font + ", hideRowDrawable=" + this.hideRowDrawable + ", showRowBadge=" + this.showRowBadge + ", subTitleMaxLine=" + this.subTitleMaxLine + ", titleMaxLine=" + this.titleMaxLine + ", enabled=" + this.enabled + ", textMode=" + this.textMode + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
