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
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.fonts.Font;

public class StandardRowEpoxyModel_ extends StandardRowEpoxyModel implements GeneratedModel<StandardRow> {
    private OnModelBoundListener<StandardRowEpoxyModel_, StandardRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StandardRowEpoxyModel_, StandardRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StandardRow object, int position) {
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

    public void handlePostBind(StandardRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StandardRowEpoxyModel_ onBind(OnModelBoundListener<StandardRowEpoxyModel_, StandardRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StandardRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StandardRowEpoxyModel_ onUnbind(OnModelUnboundListener<StandardRowEpoxyModel_, StandardRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StandardRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        super.title(title);
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public StandardRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public StandardRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        super.subtitle(subtitle);
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public StandardRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public StandardRowEpoxyModel_ clickListener(OnModelClickListener<StandardRowEpoxyModel_, StandardRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public StandardRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public StandardRowEpoxyModel_ longClickListener(OnModelLongClickListener<StandardRowEpoxyModel_, StandardRow> longClickListener) {
        onMutation();
        if (longClickListener == null) {
            this.longClickListener = null;
        } else {
            this.longClickListener = new WrappedEpoxyModelClickListener(this, longClickListener);
        }
        return this;
    }

    public StandardRowEpoxyModel_ longClickListener(OnLongClickListener longClickListener) {
        onMutation();
        this.longClickListener = longClickListener;
        return this;
    }

    public OnLongClickListener longClickListener() {
        return this.longClickListener;
    }

    public StandardRowEpoxyModel_ rowDrawableClickListener(OnModelClickListener<StandardRowEpoxyModel_, StandardRow> rowDrawableClickListener) {
        onMutation();
        if (rowDrawableClickListener == null) {
            this.rowDrawableClickListener = null;
        } else {
            this.rowDrawableClickListener = new WrappedEpoxyModelClickListener(this, rowDrawableClickListener);
        }
        return this;
    }

    public StandardRowEpoxyModel_ rowDrawableClickListener(OnClickListener rowDrawableClickListener) {
        onMutation();
        this.rowDrawableClickListener = rowDrawableClickListener;
        return this;
    }

    public OnClickListener rowDrawableClickListener() {
        return this.rowDrawableClickListener;
    }

    public StandardRowEpoxyModel_ rowDrawableRes(int rowDrawableRes) {
        onMutation();
        this.rowDrawableRes = rowDrawableRes;
        return this;
    }

    public int rowDrawableRes() {
        return this.rowDrawableRes;
    }

    public StandardRowEpoxyModel_ rowDrawable(Drawable rowDrawable) {
        onMutation();
        this.rowDrawable = rowDrawable;
        super.rowDrawable(rowDrawable);
        return this;
    }

    public Drawable rowDrawable() {
        return this.rowDrawable;
    }

    public StandardRowEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public StandardRowEpoxyModel_ font(Font font) {
        onMutation();
        this.font = font;
        return this;
    }

    public Font font() {
        return this.font;
    }

    public StandardRowEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public StandardRowEpoxyModel_ textMode(int textMode) {
        onMutation();
        this.textMode = textMode;
        return this;
    }

    public int textMode() {
        return this.textMode;
    }

    public StandardRowEpoxyModel_ hideRowDrawable(boolean hideRowDrawable) {
        onMutation();
        this.hideRowDrawable = hideRowDrawable;
        return this;
    }

    public boolean hideRowDrawable() {
        return this.hideRowDrawable;
    }

    public StandardRowEpoxyModel_ showRowBadge(boolean showRowBadge) {
        onMutation();
        this.showRowBadge = showRowBadge;
        return this;
    }

    public boolean showRowBadge() {
        return this.showRowBadge;
    }

    public StandardRowEpoxyModel_ subTitleMaxLine(int subTitleMaxLine) {
        onMutation();
        this.subTitleMaxLine = subTitleMaxLine;
        return this;
    }

    public int subTitleMaxLine() {
        return this.subTitleMaxLine;
    }

    public StandardRowEpoxyModel_ fullWidthSubtitle(boolean fullWidthSubtitle) {
        onMutation();
        this.fullWidthSubtitle = fullWidthSubtitle;
        return this;
    }

    public boolean fullWidthSubtitle() {
        return this.fullWidthSubtitle;
    }

    public StandardRowEpoxyModel_ titleMaxLine(int titleMaxLine) {
        onMutation();
        this.titleMaxLine = titleMaxLine;
        return this;
    }

    public int titleMaxLine() {
        return this.titleMaxLine;
    }

    public StandardRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public StandardRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StandardRowEpoxyModel_ title(int titleRes) {
        super.title(titleRes);
        return this;
    }

    public StandardRowEpoxyModel_ actionText(int actionTextRes) {
        super.actionText(actionTextRes);
        return this;
    }

    public StandardRowEpoxyModel_ actionText(CharSequence actionText) {
        super.actionText(actionText);
        return this;
    }

    public StandardRowEpoxyModel_ infoText(int infoTextRes) {
        super.infoText(infoTextRes);
        return this;
    }

    public StandardRowEpoxyModel_ infoText(CharSequence infoText) {
        super.infoText(infoText);
        return this;
    }

    public StandardRowEpoxyModel_ placeholderText(int placeholderTextRes) {
        super.placeholderText(placeholderTextRes);
        return this;
    }

    public StandardRowEpoxyModel_ placeholderText(CharSequence placeholderText) {
        super.placeholderText(placeholderText);
        return this;
    }

    public StandardRowEpoxyModel_ subtitle(int subtitleRes) {
        super.subtitle(subtitleRes);
        return this;
    }

    public StandardRowEpoxyModel_ disclosure() {
        super.disclosure();
        return this;
    }

    public StandardRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StandardRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StandardRowEpoxyModel_ m5602id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StandardRowEpoxyModel_ m5607id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StandardRowEpoxyModel_ m5603id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StandardRowEpoxyModel_ m5604id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StandardRowEpoxyModel_ m5606id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StandardRowEpoxyModel_ m5605id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StandardRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StandardRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StandardRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StandardRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StandardRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_standard_row;
    }

    public StandardRowEpoxyModel_ reset() {
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
        this.rowDrawable = null;
        this.textRes = 0;
        this.font = null;
        this.text = null;
        this.textMode = 0;
        this.hideRowDrawable = false;
        this.showRowBadge = false;
        this.subTitleMaxLine = 0;
        this.fullWidthSubtitle = false;
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof StandardRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StandardRowEpoxyModel_ that = (StandardRowEpoxyModel_) o;
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
        if (z4 != (that.rowDrawableClickListener == null) || this.rowDrawableRes != that.rowDrawableRes) {
            return false;
        }
        if (this.rowDrawable != null) {
            if (!this.rowDrawable.equals(that.rowDrawable)) {
                return false;
            }
        } else if (that.rowDrawable != null) {
            return false;
        }
        if (this.textRes != that.textRes) {
            return false;
        }
        if (this.font != null) {
            if (!this.font.equals(that.font)) {
                return false;
            }
        } else if (that.font != null) {
            return false;
        }
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.textMode != that.textMode || this.hideRowDrawable != that.hideRowDrawable || this.showRowBadge != that.showRowBadge || this.subTitleMaxLine != that.subTitleMaxLine || this.fullWidthSubtitle != that.fullWidthSubtitle || this.titleMaxLine != that.titleMaxLine || this.enabled != that.enabled) {
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
        if (this.clickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i19 = (i18 + i4) * 31;
        if (this.longClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i20 = (i19 + i5) * 31;
        if (this.rowDrawableClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i21 = (((i20 + i6) * 31) + this.rowDrawableRes) * 31;
        if (this.rowDrawable != null) {
            i7 = this.rowDrawable.hashCode();
        } else {
            i7 = 0;
        }
        int i22 = (((i21 + i7) * 31) + this.textRes) * 31;
        if (this.font != null) {
            i8 = this.font.hashCode();
        } else {
            i8 = 0;
        }
        int i23 = (i22 + i8) * 31;
        if (this.text != null) {
            i9 = this.text.hashCode();
        } else {
            i9 = 0;
        }
        int i24 = (((i23 + i9) * 31) + this.textMode) * 31;
        if (this.hideRowDrawable) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i25 = (i24 + i10) * 31;
        if (this.showRowBadge) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i26 = (((i25 + i11) * 31) + this.subTitleMaxLine) * 31;
        if (this.fullWidthSubtitle) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i27 = (((i26 + i12) * 31) + this.titleMaxLine) * 31;
        if (!this.enabled) {
            i14 = 0;
        }
        int i28 = (i27 + i14) * 31;
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
        return "StandardRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", clickListener=" + this.clickListener + ", longClickListener=" + this.longClickListener + ", rowDrawableClickListener=" + this.rowDrawableClickListener + ", rowDrawableRes=" + this.rowDrawableRes + ", rowDrawable=" + this.rowDrawable + ", textRes=" + this.textRes + ", font=" + this.font + ", text=" + this.text + ", textMode=" + this.textMode + ", hideRowDrawable=" + this.hideRowDrawable + ", showRowBadge=" + this.showRowBadge + ", subTitleMaxLine=" + this.subTitleMaxLine + ", fullWidthSubtitle=" + this.fullWidthSubtitle + ", titleMaxLine=" + this.titleMaxLine + ", enabled=" + this.enabled + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
