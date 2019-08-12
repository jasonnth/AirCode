package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.RequirementChecklistRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class RequirementChecklistRowEpoxyModel_ extends RequirementChecklistRowEpoxyModel implements GeneratedModel<RequirementChecklistRow> {
    private OnModelBoundListener<RequirementChecklistRowEpoxyModel_, RequirementChecklistRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<RequirementChecklistRowEpoxyModel_, RequirementChecklistRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RequirementChecklistRow object, int position) {
    }

    public void handlePostBind(RequirementChecklistRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public RequirementChecklistRowEpoxyModel_ onBind(OnModelBoundListener<RequirementChecklistRowEpoxyModel_, RequirementChecklistRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RequirementChecklistRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public RequirementChecklistRowEpoxyModel_ onUnbind(OnModelUnboundListener<RequirementChecklistRowEpoxyModel_, RequirementChecklistRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ rowDrawableRes(int rowDrawableRes) {
        onMutation();
        this.rowDrawableRes = rowDrawableRes;
        return this;
    }

    public int rowDrawableRes() {
        return this.rowDrawableRes;
    }

    public RequirementChecklistRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        super.title(title);
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public RequirementChecklistRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public RequirementChecklistRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ title(int titleRes) {
        super.title(titleRes);
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public RequirementChecklistRowEpoxyModel_ m5446id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public RequirementChecklistRowEpoxyModel_ m5451id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public RequirementChecklistRowEpoxyModel_ m5447id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public RequirementChecklistRowEpoxyModel_ m5448id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public RequirementChecklistRowEpoxyModel_ m5450id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public RequirementChecklistRowEpoxyModel_ m5449id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public RequirementChecklistRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_requirement_checklist_row;
    }

    public RequirementChecklistRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.rowDrawableRes = 0;
        this.title = null;
        this.titleRes = 0;
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
        if (!(o instanceof RequirementChecklistRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        RequirementChecklistRowEpoxyModel_ that = (RequirementChecklistRowEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.rowDrawableRes != that.rowDrawableRes) {
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i5 = (((hashCode + i3) * 31) + this.rowDrawableRes) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i6 = (((i5 + i) * 31) + this.titleRes) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "RequirementChecklistRowEpoxyModel_{rowDrawableRes=" + this.rowDrawableRes + ", title=" + this.title + ", titleRes=" + this.titleRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
