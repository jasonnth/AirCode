package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.HomeHighlights;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class HomeHighlightsEpoxyModel_ extends HomeHighlightsEpoxyModel implements GeneratedModel<HomeHighlights> {
    private OnModelBoundListener<HomeHighlightsEpoxyModel_, HomeHighlights> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeHighlightsEpoxyModel_, HomeHighlights> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HomeHighlights object, int position) {
    }

    public void handlePostBind(HomeHighlights object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeHighlightsEpoxyModel_ onBind(OnModelBoundListener<HomeHighlightsEpoxyModel_, HomeHighlights> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HomeHighlights object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeHighlightsEpoxyModel_ onUnbind(OnModelUnboundListener<HomeHighlightsEpoxyModel_, HomeHighlights> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeHighlightsEpoxyModel_ personCapacity(int personCapacity) {
        onMutation();
        this.personCapacity = personCapacity;
        return this;
    }

    public int personCapacity() {
        return this.personCapacity;
    }

    public HomeHighlightsEpoxyModel_ bedroomCount(int bedroomCount) {
        onMutation();
        this.bedroomCount = bedroomCount;
        return this;
    }

    public int bedroomCount() {
        return this.bedroomCount;
    }

    public HomeHighlightsEpoxyModel_ bedCount(int bedCount) {
        onMutation();
        this.bedCount = bedCount;
        return this;
    }

    public int bedCount() {
        return this.bedCount;
    }

    public HomeHighlightsEpoxyModel_ bathroomType(BathroomType bathroomType) {
        onMutation();
        this.bathroomType = bathroomType;
        return this;
    }

    public BathroomType bathroomType() {
        return this.bathroomType;
    }

    public HomeHighlightsEpoxyModel_ roomType(SpaceType roomType) {
        onMutation();
        this.roomType = roomType;
        return this;
    }

    public SpaceType roomType() {
        return this.roomType;
    }

    public HomeHighlightsEpoxyModel_ bathroomCount(float bathroomCount) {
        onMutation();
        this.bathroomCount = bathroomCount;
        return this;
    }

    public float bathroomCount() {
        return this.bathroomCount;
    }

    public HomeHighlightsEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HomeHighlightsEpoxyModel_ listing(Listing listing) {
        super.listing(listing);
        return this;
    }

    public HomeHighlightsEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HomeHighlightsEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HomeHighlightsEpoxyModel_ m4738id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeHighlightsEpoxyModel_ m4743id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeHighlightsEpoxyModel_ m4739id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeHighlightsEpoxyModel_ m4740id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeHighlightsEpoxyModel_ m4742id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeHighlightsEpoxyModel_ m4741id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeHighlightsEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeHighlightsEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeHighlightsEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeHighlightsEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeHighlightsEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_home_highlights;
    }

    public HomeHighlightsEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.personCapacity = 0;
        this.bedroomCount = 0;
        this.bedCount = 0;
        this.bathroomType = null;
        this.roomType = null;
        this.bathroomCount = 0.0f;
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
        if (!(o instanceof HomeHighlightsEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeHighlightsEpoxyModel_ that = (HomeHighlightsEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (that.onModelUnboundListener_epoxyGeneratedModel == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z != z2 || this.personCapacity != that.personCapacity || this.bedroomCount != that.bedroomCount || this.bedCount != that.bedCount) {
            return false;
        }
        if (this.bathroomType != null) {
            if (!this.bathroomType.equals(that.bathroomType)) {
                return false;
            }
        } else if (that.bathroomType != null) {
            return false;
        }
        if (this.roomType != null) {
            if (!this.roomType.equals(that.roomType)) {
                return false;
            }
        } else if (that.roomType != null) {
            return false;
        }
        if (Float.compare(that.bathroomCount, this.bathroomCount) != 0) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i5 = 0;
        }
        int i7 = (((((((hashCode + i5) * 31) + this.personCapacity) * 31) + this.bedroomCount) * 31) + this.bedCount) * 31;
        if (this.bathroomType != null) {
            i = this.bathroomType.hashCode();
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.roomType != null) {
            i2 = this.roomType.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.bathroomCount != 0.0f) {
            i3 = Float.floatToIntBits(this.bathroomCount);
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
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
        return "HomeHighlightsEpoxyModel_{personCapacity=" + this.personCapacity + ", bedroomCount=" + this.bedroomCount + ", bedCount=" + this.bedCount + ", bathroomType=" + this.bathroomType + ", roomType=" + this.roomType + ", bathroomCount=" + this.bathroomCount + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
