package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.explore.C0857R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ExploreInlineFiltersToggleRowEpoxyModel_ extends ExploreInlineFiltersToggleRowEpoxyModel implements GeneratedModel<FindInlineFiltersToggleRow> {
    private OnModelBoundListener<ExploreInlineFiltersToggleRowEpoxyModel_, FindInlineFiltersToggleRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ExploreInlineFiltersToggleRowEpoxyModel_, FindInlineFiltersToggleRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, FindInlineFiltersToggleRow object, int position) {
    }

    public void handlePostBind(FindInlineFiltersToggleRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ onBind(OnModelBoundListener<ExploreInlineFiltersToggleRowEpoxyModel_, FindInlineFiltersToggleRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(FindInlineFiltersToggleRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ onUnbind(OnModelUnboundListener<ExploreInlineFiltersToggleRowEpoxyModel_, FindInlineFiltersToggleRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ checked(boolean checked) {
        onMutation();
        this.checked = checked;
        return this;
    }

    public boolean checked() {
        return this.checked;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ checkChangedListener(OnCheckChangedListener checkChangedListener) {
        onMutation();
        this.checkChangedListener = checkChangedListener;
        return this;
    }

    public OnCheckChangedListener checkChangedListener() {
        return this.checkChangedListener;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ExploreInlineFiltersToggleRowEpoxyModel_ m5855id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ExploreInlineFiltersToggleRowEpoxyModel_ m5860id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ExploreInlineFiltersToggleRowEpoxyModel_ m5856id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ExploreInlineFiltersToggleRowEpoxyModel_ m5857id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ExploreInlineFiltersToggleRowEpoxyModel_ m5859id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ExploreInlineFiltersToggleRowEpoxyModel_ m5858id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.view_holder_toggle_row;
    }

    public ExploreInlineFiltersToggleRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.checked = false;
        this.title = null;
        this.titleRes = 0;
        this.subtitleRes = 0;
        this.checkChangedListener = null;
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
        if (!(o instanceof ExploreInlineFiltersToggleRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ExploreInlineFiltersToggleRowEpoxyModel_ that = (ExploreInlineFiltersToggleRowEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.checked != that.checked) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.subtitleRes != that.subtitleRes) {
            return false;
        }
        if (this.checkChangedListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.checkChangedListener == null)) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.checked) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.title != null) {
            i3 = this.title.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (((((i8 + i3) * 31) + this.titleRes) * 31) + this.subtitleRes) * 31;
        if (this.checkChangedListener == null) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
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
        return "ExploreInlineFiltersToggleRowEpoxyModel_{checked=" + this.checked + ", title=" + this.title + ", titleRes=" + this.titleRes + ", subtitleRes=" + this.subtitleRes + ", checkChangedListener=" + this.checkChangedListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
