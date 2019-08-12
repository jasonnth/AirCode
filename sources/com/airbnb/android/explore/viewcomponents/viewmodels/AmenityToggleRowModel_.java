package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Amenity;
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

public class AmenityToggleRowModel_ extends AmenityToggleRowModel implements GeneratedModel<FindInlineFiltersToggleRow> {
    private OnModelBoundListener<AmenityToggleRowModel_, FindInlineFiltersToggleRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<AmenityToggleRowModel_, FindInlineFiltersToggleRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, FindInlineFiltersToggleRow object, int position) {
    }

    public void handlePostBind(FindInlineFiltersToggleRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public AmenityToggleRowModel_ onBind(OnModelBoundListener<AmenityToggleRowModel_, FindInlineFiltersToggleRow> listener) {
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

    public AmenityToggleRowModel_ onUnbind(OnModelUnboundListener<AmenityToggleRowModel_, FindInlineFiltersToggleRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public AmenityToggleRowModel_ amenity(Amenity amenity) {
        onMutation();
        this.amenity = amenity;
        super.amenity(amenity);
        return this;
    }

    public Amenity amenity() {
        return this.amenity;
    }

    public AmenityToggleRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public AmenityToggleRowModel_ checked(boolean checked) {
        onMutation();
        this.checked = checked;
        return this;
    }

    public boolean checked() {
        return this.checked;
    }

    public AmenityToggleRowModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public AmenityToggleRowModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public AmenityToggleRowModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public AmenityToggleRowModel_ checkChangedListener(OnCheckChangedListener checkChangedListener) {
        onMutation();
        this.checkChangedListener = checkChangedListener;
        return this;
    }

    public OnCheckChangedListener checkChangedListener() {
        return this.checkChangedListener;
    }

    public AmenityToggleRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public AmenityToggleRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public AmenityToggleRowModel_ m5795id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public AmenityToggleRowModel_ m5800id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public AmenityToggleRowModel_ m5796id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public AmenityToggleRowModel_ m5797id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public AmenityToggleRowModel_ m5799id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public AmenityToggleRowModel_ m5798id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public AmenityToggleRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public AmenityToggleRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public AmenityToggleRowModel_ show() {
        super.show();
        return this;
    }

    public AmenityToggleRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public AmenityToggleRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.view_holder_toggle_row;
    }

    public AmenityToggleRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.amenity = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        this.checked = false;
        this.title = null;
        this.titleRes = 0;
        this.subtitleRes = 0;
        this.checkChangedListener = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof AmenityToggleRowModel_) || !super.equals(o)) {
            return false;
        }
        AmenityToggleRowModel_ that = (AmenityToggleRowModel_) o;
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
        if (this.amenity != null) {
            if (!this.amenity.equals(that.amenity)) {
                return false;
            }
        } else if (that.amenity != null) {
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
        if (this.checked != that.checked) {
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
        if (z2 == (that.checkChangedListener == null)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 1;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.amenity != null) {
            i2 = this.amenity.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.checked) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i12 = (i11 + i5) * 31;
        if (this.title != null) {
            i6 = this.title.hashCode();
        } else {
            i6 = 0;
        }
        int i13 = (((((i12 + i6) * 31) + this.titleRes) * 31) + this.subtitleRes) * 31;
        if (this.checkChangedListener == null) {
            i7 = 0;
        }
        return i13 + i7;
    }

    public String toString() {
        return "AmenityToggleRowModel_{amenity=" + this.amenity + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + ", checked=" + this.checked + ", title=" + this.title + ", titleRes=" + this.titleRes + ", subtitleRes=" + this.subtitleRes + ", checkChangedListener=" + this.checkChangedListener + "}" + super.toString();
    }
}
