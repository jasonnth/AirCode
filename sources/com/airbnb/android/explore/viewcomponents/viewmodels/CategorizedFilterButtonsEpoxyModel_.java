package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.CategorizedFilterButtons;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class CategorizedFilterButtonsEpoxyModel_ extends CategorizedFilterButtonsEpoxyModel implements GeneratedModel<CategorizedFilterButtons> {
    private OnModelBoundListener<CategorizedFilterButtonsEpoxyModel_, CategorizedFilterButtons> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<CategorizedFilterButtonsEpoxyModel_, CategorizedFilterButtons> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, CategorizedFilterButtons object, int position) {
        if (this.item1ClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.item1ClickListener).bind(holder, object);
        }
        if (this.item2ClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.item2ClickListener).bind(holder, object);
        }
        if (this.item3ClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.item3ClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(CategorizedFilterButtons object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public CategorizedFilterButtonsEpoxyModel_ onBind(OnModelBoundListener<CategorizedFilterButtonsEpoxyModel_, CategorizedFilterButtons> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(CategorizedFilterButtons object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public CategorizedFilterButtonsEpoxyModel_ onUnbind(OnModelUnboundListener<CategorizedFilterButtonsEpoxyModel_, CategorizedFilterButtons> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public CategorizedFilterButtonsEpoxyModel_ item1(FilterSuggestionItem item1) {
        onMutation();
        this.item1 = item1;
        return this;
    }

    public FilterSuggestionItem item1() {
        return this.item1;
    }

    public CategorizedFilterButtonsEpoxyModel_ item2(FilterSuggestionItem item2) {
        onMutation();
        this.item2 = item2;
        return this;
    }

    public FilterSuggestionItem item2() {
        return this.item2;
    }

    public CategorizedFilterButtonsEpoxyModel_ item3(FilterSuggestionItem item3) {
        onMutation();
        this.item3 = item3;
        return this;
    }

    public FilterSuggestionItem item3() {
        return this.item3;
    }

    public CategorizedFilterButtonsEpoxyModel_ item1ClickListener(OnModelClickListener<CategorizedFilterButtonsEpoxyModel_, CategorizedFilterButtons> item1ClickListener) {
        onMutation();
        if (item1ClickListener == null) {
            this.item1ClickListener = null;
        } else {
            this.item1ClickListener = new WrappedEpoxyModelClickListener(this, item1ClickListener);
        }
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ item1ClickListener(OnClickListener item1ClickListener) {
        onMutation();
        this.item1ClickListener = item1ClickListener;
        return this;
    }

    public OnClickListener item1ClickListener() {
        return this.item1ClickListener;
    }

    public CategorizedFilterButtonsEpoxyModel_ item2ClickListener(OnModelClickListener<CategorizedFilterButtonsEpoxyModel_, CategorizedFilterButtons> item2ClickListener) {
        onMutation();
        if (item2ClickListener == null) {
            this.item2ClickListener = null;
        } else {
            this.item2ClickListener = new WrappedEpoxyModelClickListener(this, item2ClickListener);
        }
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ item2ClickListener(OnClickListener item2ClickListener) {
        onMutation();
        this.item2ClickListener = item2ClickListener;
        return this;
    }

    public OnClickListener item2ClickListener() {
        return this.item2ClickListener;
    }

    public CategorizedFilterButtonsEpoxyModel_ item3ClickListener(OnModelClickListener<CategorizedFilterButtonsEpoxyModel_, CategorizedFilterButtons> item3ClickListener) {
        onMutation();
        if (item3ClickListener == null) {
            this.item3ClickListener = null;
        } else {
            this.item3ClickListener = new WrappedEpoxyModelClickListener(this, item3ClickListener);
        }
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ item3ClickListener(OnClickListener item3ClickListener) {
        onMutation();
        this.item3ClickListener = item3ClickListener;
        return this;
    }

    public OnClickListener item3ClickListener() {
        return this.item3ClickListener;
    }

    public CategorizedFilterButtonsEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public CategorizedFilterButtonsEpoxyModel_ m5831id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public CategorizedFilterButtonsEpoxyModel_ m5836id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public CategorizedFilterButtonsEpoxyModel_ m5832id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public CategorizedFilterButtonsEpoxyModel_ m5833id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public CategorizedFilterButtonsEpoxyModel_ m5835id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public CategorizedFilterButtonsEpoxyModel_ m5834id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ show() {
        super.show();
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public CategorizedFilterButtonsEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.view_holder_categorized_filter_buttons_carousel;
    }

    public CategorizedFilterButtonsEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.item1 = null;
        this.item2 = null;
        this.item3 = null;
        this.item1ClickListener = null;
        this.item2ClickListener = null;
        this.item3ClickListener = null;
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
        if (!(o instanceof CategorizedFilterButtonsEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        CategorizedFilterButtonsEpoxyModel_ that = (CategorizedFilterButtonsEpoxyModel_) o;
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
        if (this.item1 != null) {
            if (!this.item1.equals(that.item1)) {
                return false;
            }
        } else if (that.item1 != null) {
            return false;
        }
        if (this.item2 != null) {
            if (!this.item2.equals(that.item2)) {
                return false;
            }
        } else if (that.item2 != null) {
            return false;
        }
        if (this.item3 != null) {
            if (!this.item3.equals(that.item3)) {
                return false;
            }
        } else if (that.item3 != null) {
            return false;
        }
        if (this.item1ClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.item1ClickListener == null)) {
            return false;
        }
        if (this.item2ClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.item2ClickListener == null)) {
            return false;
        }
        if (this.item3ClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.item3ClickListener == null)) {
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
        int i9 = 1;
        int i10 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i11 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i12 = (i11 + i2) * 31;
        if (this.item1 != null) {
            i3 = this.item1.hashCode();
        } else {
            i3 = 0;
        }
        int i13 = (i12 + i3) * 31;
        if (this.item2 != null) {
            i4 = this.item2.hashCode();
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.item3 != null) {
            i5 = this.item3.hashCode();
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.item1ClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
        if (this.item2ClickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.item3ClickListener == null) {
            i9 = 0;
        }
        int i18 = (i17 + i9) * 31;
        if (this.showDivider != null) {
            i8 = this.showDivider.hashCode();
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (this.numCarouselItemsShown != null) {
            i10 = this.numCarouselItemsShown.hashCode();
        }
        return i19 + i10;
    }

    public String toString() {
        return "CategorizedFilterButtonsEpoxyModel_{title=" + this.title + ", item1=" + this.item1 + ", item2=" + this.item2 + ", item3=" + this.item3 + ", item1ClickListener=" + this.item1ClickListener + ", item2ClickListener=" + this.item2ClickListener + ", item3ClickListener=" + this.item3ClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
