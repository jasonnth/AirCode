package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.RangeDisplay;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class RangeDisplayEpoxyModel_ extends RangeDisplayEpoxyModel implements GeneratedModel<RangeDisplay> {
    private OnModelBoundListener<RangeDisplayEpoxyModel_, RangeDisplay> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<RangeDisplayEpoxyModel_, RangeDisplay> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RangeDisplay object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(RangeDisplay object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public RangeDisplayEpoxyModel_ onBind(OnModelBoundListener<RangeDisplayEpoxyModel_, RangeDisplay> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RangeDisplay object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public RangeDisplayEpoxyModel_ onUnbind(OnModelUnboundListener<RangeDisplayEpoxyModel_, RangeDisplay> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public RangeDisplayEpoxyModel_ endDate(AirDate endDate) {
        onMutation();
        this.endDate = endDate;
        return this;
    }

    public AirDate endDate() {
        return this.endDate;
    }

    public RangeDisplayEpoxyModel_ startDate(AirDate startDate) {
        onMutation();
        this.startDate = startDate;
        return this;
    }

    public AirDate startDate() {
        return this.startDate;
    }

    public RangeDisplayEpoxyModel_ startTitleRes(int startTitleRes) {
        onMutation();
        this.startTitleRes = startTitleRes;
        return this;
    }

    public int startTitleRes() {
        return this.startTitleRes;
    }

    public RangeDisplayEpoxyModel_ startSubTitleRes(int startSubTitleRes) {
        onMutation();
        this.startSubTitleRes = startSubTitleRes;
        return this;
    }

    public int startSubTitleRes() {
        return this.startSubTitleRes;
    }

    public RangeDisplayEpoxyModel_ endTitleRes(int endTitleRes) {
        onMutation();
        this.endTitleRes = endTitleRes;
        return this;
    }

    public int endTitleRes() {
        return this.endTitleRes;
    }

    public RangeDisplayEpoxyModel_ endSubTitleRes(int endSubTitleRes) {
        onMutation();
        this.endSubTitleRes = endSubTitleRes;
        return this;
    }

    public int endSubTitleRes() {
        return this.endSubTitleRes;
    }

    public RangeDisplayEpoxyModel_ startTitle(CharSequence startTitle) {
        onMutation();
        this.startTitle = startTitle;
        return this;
    }

    public CharSequence startTitle() {
        return this.startTitle;
    }

    public RangeDisplayEpoxyModel_ startSubtitle(CharSequence startSubtitle) {
        onMutation();
        this.startSubtitle = startSubtitle;
        return this;
    }

    public CharSequence startSubtitle() {
        return this.startSubtitle;
    }

    public RangeDisplayEpoxyModel_ endTitle(CharSequence endTitle) {
        onMutation();
        this.endTitle = endTitle;
        return this;
    }

    public CharSequence endTitle() {
        return this.endTitle;
    }

    public RangeDisplayEpoxyModel_ endSubtitle(CharSequence endSubtitle) {
        onMutation();
        this.endSubtitle = endSubtitle;
        return this;
    }

    public CharSequence endSubtitle() {
        return this.endSubtitle;
    }

    public RangeDisplayEpoxyModel_ startTitleHintRes(int startTitleHintRes) {
        onMutation();
        this.startTitleHintRes = startTitleHintRes;
        return this;
    }

    public int startTitleHintRes() {
        return this.startTitleHintRes;
    }

    public RangeDisplayEpoxyModel_ startSubTitleHintRes(int startSubTitleHintRes) {
        onMutation();
        this.startSubTitleHintRes = startSubTitleHintRes;
        return this;
    }

    public int startSubTitleHintRes() {
        return this.startSubTitleHintRes;
    }

    public RangeDisplayEpoxyModel_ endTitleHintRes(int endTitleHintRes) {
        onMutation();
        this.endTitleHintRes = endTitleHintRes;
        return this;
    }

    public int endTitleHintRes() {
        return this.endTitleHintRes;
    }

    public RangeDisplayEpoxyModel_ endSubTitleHintRes(int endSubTitleHintRes) {
        onMutation();
        this.endSubTitleHintRes = endSubTitleHintRes;
        return this;
    }

    public int endSubTitleHintRes() {
        return this.endSubTitleHintRes;
    }

    public RangeDisplayEpoxyModel_ startTitleHint(CharSequence startTitleHint) {
        onMutation();
        this.startTitleHint = startTitleHint;
        return this;
    }

    public CharSequence startTitleHint() {
        return this.startTitleHint;
    }

    public RangeDisplayEpoxyModel_ startSubtitleHint(CharSequence startSubtitleHint) {
        onMutation();
        this.startSubtitleHint = startSubtitleHint;
        return this;
    }

    public CharSequence startSubtitleHint() {
        return this.startSubtitleHint;
    }

    public RangeDisplayEpoxyModel_ endTitleHint(CharSequence endTitleHint) {
        onMutation();
        this.endTitleHint = endTitleHint;
        return this;
    }

    public CharSequence endTitleHint() {
        return this.endTitleHint;
    }

    public RangeDisplayEpoxyModel_ endSubtitleHint(CharSequence endSubtitleHint) {
        onMutation();
        this.endSubtitleHint = endSubtitleHint;
        return this;
    }

    public CharSequence endSubtitleHint() {
        return this.endSubtitleHint;
    }

    public RangeDisplayEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public RangeDisplayEpoxyModel_ formatWithYear(boolean formatWithYear) {
        onMutation();
        this.formatWithYear = formatWithYear;
        return this;
    }

    public boolean formatWithYear() {
        return this.formatWithYear;
    }

    public RangeDisplayEpoxyModel_ clickListener(OnModelClickListener<RangeDisplayEpoxyModel_, RangeDisplay> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public RangeDisplayEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public RangeDisplayEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public RangeDisplayEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public RangeDisplayEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public RangeDisplayEpoxyModel_ m5362id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public RangeDisplayEpoxyModel_ m5367id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public RangeDisplayEpoxyModel_ m5363id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public RangeDisplayEpoxyModel_ m5364id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public RangeDisplayEpoxyModel_ m5366id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public RangeDisplayEpoxyModel_ m5365id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public RangeDisplayEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public RangeDisplayEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public RangeDisplayEpoxyModel_ show() {
        super.show();
        return this;
    }

    public RangeDisplayEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public RangeDisplayEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_range_display;
    }

    public RangeDisplayEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.endDate = null;
        this.startDate = null;
        this.startTitleRes = 0;
        this.startSubTitleRes = 0;
        this.endTitleRes = 0;
        this.endSubTitleRes = 0;
        this.startTitle = null;
        this.startSubtitle = null;
        this.endTitle = null;
        this.endSubtitle = null;
        this.startTitleHintRes = 0;
        this.startSubTitleHintRes = 0;
        this.endTitleHintRes = 0;
        this.endSubTitleHintRes = 0;
        this.startTitleHint = null;
        this.startSubtitleHint = null;
        this.endTitleHint = null;
        this.endSubtitleHint = null;
        this.enabled = false;
        this.formatWithYear = false;
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
        if (!(o instanceof RangeDisplayEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        RangeDisplayEpoxyModel_ that = (RangeDisplayEpoxyModel_) o;
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
        if (this.endDate != null) {
            if (!this.endDate.equals(that.endDate)) {
                return false;
            }
        } else if (that.endDate != null) {
            return false;
        }
        if (this.startDate != null) {
            if (!this.startDate.equals(that.startDate)) {
                return false;
            }
        } else if (that.startDate != null) {
            return false;
        }
        if (this.startTitleRes != that.startTitleRes || this.startSubTitleRes != that.startSubTitleRes || this.endTitleRes != that.endTitleRes || this.endSubTitleRes != that.endSubTitleRes) {
            return false;
        }
        if (this.startTitle != null) {
            if (!this.startTitle.equals(that.startTitle)) {
                return false;
            }
        } else if (that.startTitle != null) {
            return false;
        }
        if (this.startSubtitle != null) {
            if (!this.startSubtitle.equals(that.startSubtitle)) {
                return false;
            }
        } else if (that.startSubtitle != null) {
            return false;
        }
        if (this.endTitle != null) {
            if (!this.endTitle.equals(that.endTitle)) {
                return false;
            }
        } else if (that.endTitle != null) {
            return false;
        }
        if (this.endSubtitle != null) {
            if (!this.endSubtitle.equals(that.endSubtitle)) {
                return false;
            }
        } else if (that.endSubtitle != null) {
            return false;
        }
        if (this.startTitleHintRes != that.startTitleHintRes || this.startSubTitleHintRes != that.startSubTitleHintRes || this.endTitleHintRes != that.endTitleHintRes || this.endSubTitleHintRes != that.endSubTitleHintRes) {
            return false;
        }
        if (this.startTitleHint != null) {
            if (!this.startTitleHint.equals(that.startTitleHint)) {
                return false;
            }
        } else if (that.startTitleHint != null) {
            return false;
        }
        if (this.startSubtitleHint != null) {
            if (!this.startSubtitleHint.equals(that.startSubtitleHint)) {
                return false;
            }
        } else if (that.startSubtitleHint != null) {
            return false;
        }
        if (this.endTitleHint != null) {
            if (!this.endTitleHint.equals(that.endTitleHint)) {
                return false;
            }
        } else if (that.endTitleHint != null) {
            return false;
        }
        if (this.endSubtitleHint != null) {
            if (!this.endSubtitleHint.equals(that.endSubtitleHint)) {
                return false;
            }
        } else if (that.endSubtitleHint != null) {
            return false;
        }
        if (this.enabled != that.enabled || this.formatWithYear != that.formatWithYear) {
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
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15 = 1;
        int i16 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i17 = (hashCode + i) * 31;
        if (this.endDate != null) {
            i2 = this.endDate.hashCode();
        } else {
            i2 = 0;
        }
        int i18 = (i17 + i2) * 31;
        if (this.startDate != null) {
            i3 = this.startDate.hashCode();
        } else {
            i3 = 0;
        }
        int i19 = (((((((((i18 + i3) * 31) + this.startTitleRes) * 31) + this.startSubTitleRes) * 31) + this.endTitleRes) * 31) + this.endSubTitleRes) * 31;
        if (this.startTitle != null) {
            i4 = this.startTitle.hashCode();
        } else {
            i4 = 0;
        }
        int i20 = (i19 + i4) * 31;
        if (this.startSubtitle != null) {
            i5 = this.startSubtitle.hashCode();
        } else {
            i5 = 0;
        }
        int i21 = (i20 + i5) * 31;
        if (this.endTitle != null) {
            i6 = this.endTitle.hashCode();
        } else {
            i6 = 0;
        }
        int i22 = (i21 + i6) * 31;
        if (this.endSubtitle != null) {
            i7 = this.endSubtitle.hashCode();
        } else {
            i7 = 0;
        }
        int i23 = (((((((((i22 + i7) * 31) + this.startTitleHintRes) * 31) + this.startSubTitleHintRes) * 31) + this.endTitleHintRes) * 31) + this.endSubTitleHintRes) * 31;
        if (this.startTitleHint != null) {
            i8 = this.startTitleHint.hashCode();
        } else {
            i8 = 0;
        }
        int i24 = (i23 + i8) * 31;
        if (this.startSubtitleHint != null) {
            i9 = this.startSubtitleHint.hashCode();
        } else {
            i9 = 0;
        }
        int i25 = (i24 + i9) * 31;
        if (this.endTitleHint != null) {
            i10 = this.endTitleHint.hashCode();
        } else {
            i10 = 0;
        }
        int i26 = (i25 + i10) * 31;
        if (this.endSubtitleHint != null) {
            i11 = this.endSubtitleHint.hashCode();
        } else {
            i11 = 0;
        }
        int i27 = (i26 + i11) * 31;
        if (this.enabled) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i28 = (i27 + i12) * 31;
        if (this.formatWithYear) {
            i13 = 1;
        } else {
            i13 = 0;
        }
        int i29 = (i28 + i13) * 31;
        if (this.clickListener == null) {
            i15 = 0;
        }
        int i30 = (i29 + i15) * 31;
        if (this.showDivider != null) {
            i14 = this.showDivider.hashCode();
        } else {
            i14 = 0;
        }
        int i31 = (i30 + i14) * 31;
        if (this.numCarouselItemsShown != null) {
            i16 = this.numCarouselItemsShown.hashCode();
        }
        return i31 + i16;
    }

    public String toString() {
        return "RangeDisplayEpoxyModel_{endDate=" + this.endDate + ", startDate=" + this.startDate + ", startTitleRes=" + this.startTitleRes + ", startSubTitleRes=" + this.startSubTitleRes + ", endTitleRes=" + this.endTitleRes + ", endSubTitleRes=" + this.endSubTitleRes + ", startTitle=" + this.startTitle + ", startSubtitle=" + this.startSubtitle + ", endTitle=" + this.endTitle + ", endSubtitle=" + this.endSubtitle + ", startTitleHintRes=" + this.startTitleHintRes + ", startSubTitleHintRes=" + this.startSubTitleHintRes + ", endTitleHintRes=" + this.endTitleHintRes + ", endSubTitleHintRes=" + this.endSubTitleHintRes + ", startTitleHint=" + this.startTitleHint + ", startSubtitleHint=" + this.startSubtitleHint + ", endTitleHint=" + this.endTitleHint + ", endSubtitleHint=" + this.endSubtitleHint + ", enabled=" + this.enabled + ", formatWithYear=" + this.formatWithYear + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
