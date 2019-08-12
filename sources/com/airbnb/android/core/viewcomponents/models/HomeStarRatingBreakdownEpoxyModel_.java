package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.HomeStarRatingBreakdown;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class HomeStarRatingBreakdownEpoxyModel_ extends HomeStarRatingBreakdownEpoxyModel implements GeneratedModel<HomeStarRatingBreakdown> {
    private OnModelBoundListener<HomeStarRatingBreakdownEpoxyModel_, HomeStarRatingBreakdown> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeStarRatingBreakdownEpoxyModel_, HomeStarRatingBreakdown> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HomeStarRatingBreakdown object, int position) {
    }

    public void handlePostBind(HomeStarRatingBreakdown object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeStarRatingBreakdownEpoxyModel_ onBind(OnModelBoundListener<HomeStarRatingBreakdownEpoxyModel_, HomeStarRatingBreakdown> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HomeStarRatingBreakdown object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeStarRatingBreakdownEpoxyModel_ onUnbind(OnModelUnboundListener<HomeStarRatingBreakdownEpoxyModel_, HomeStarRatingBreakdown> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ reviewStarRatingAccuracy(float reviewStarRatingAccuracy) {
        onMutation();
        this.reviewStarRatingAccuracy = reviewStarRatingAccuracy;
        return this;
    }

    public float reviewStarRatingAccuracy() {
        return this.reviewStarRatingAccuracy;
    }

    public HomeStarRatingBreakdownEpoxyModel_ reviewStarRatingCheckin(float reviewStarRatingCheckin) {
        onMutation();
        this.reviewStarRatingCheckin = reviewStarRatingCheckin;
        return this;
    }

    public float reviewStarRatingCheckin() {
        return this.reviewStarRatingCheckin;
    }

    public HomeStarRatingBreakdownEpoxyModel_ reviewStarRatingCleanliness(float reviewStarRatingCleanliness) {
        onMutation();
        this.reviewStarRatingCleanliness = reviewStarRatingCleanliness;
        return this;
    }

    public float reviewStarRatingCleanliness() {
        return this.reviewStarRatingCleanliness;
    }

    public HomeStarRatingBreakdownEpoxyModel_ reviewStarRatingCommunication(float reviewStarRatingCommunication) {
        onMutation();
        this.reviewStarRatingCommunication = reviewStarRatingCommunication;
        return this;
    }

    public float reviewStarRatingCommunication() {
        return this.reviewStarRatingCommunication;
    }

    public HomeStarRatingBreakdownEpoxyModel_ reviewStarRatingLocation(float reviewStarRatingLocation) {
        onMutation();
        this.reviewStarRatingLocation = reviewStarRatingLocation;
        return this;
    }

    public float reviewStarRatingLocation() {
        return this.reviewStarRatingLocation;
    }

    public HomeStarRatingBreakdownEpoxyModel_ reviewStarRatingValue(float reviewStarRatingValue) {
        onMutation();
        this.reviewStarRatingValue = reviewStarRatingValue;
        return this;
    }

    public float reviewStarRatingValue() {
        return this.reviewStarRatingValue;
    }

    public HomeStarRatingBreakdownEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HomeStarRatingBreakdownEpoxyModel_ m4774id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeStarRatingBreakdownEpoxyModel_ m4779id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeStarRatingBreakdownEpoxyModel_ m4775id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeStarRatingBreakdownEpoxyModel_ m4776id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeStarRatingBreakdownEpoxyModel_ m4778id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeStarRatingBreakdownEpoxyModel_ m4777id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeStarRatingBreakdownEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_home_star_rating_breakdown;
    }

    public HomeStarRatingBreakdownEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.reviewStarRatingAccuracy = 0.0f;
        this.reviewStarRatingCheckin = 0.0f;
        this.reviewStarRatingCleanliness = 0.0f;
        this.reviewStarRatingCommunication = 0.0f;
        this.reviewStarRatingLocation = 0.0f;
        this.reviewStarRatingValue = 0.0f;
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
        if (!(o instanceof HomeStarRatingBreakdownEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeStarRatingBreakdownEpoxyModel_ that = (HomeStarRatingBreakdownEpoxyModel_) o;
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
        if (z != z2 || Float.compare(that.reviewStarRatingAccuracy, this.reviewStarRatingAccuracy) != 0 || Float.compare(that.reviewStarRatingCheckin, this.reviewStarRatingCheckin) != 0 || Float.compare(that.reviewStarRatingCleanliness, this.reviewStarRatingCleanliness) != 0 || Float.compare(that.reviewStarRatingCommunication, this.reviewStarRatingCommunication) != 0 || Float.compare(that.reviewStarRatingLocation, this.reviewStarRatingLocation) != 0 || Float.compare(that.reviewStarRatingValue, this.reviewStarRatingValue) != 0) {
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
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i8 = 0;
        }
        int i10 = (hashCode + i8) * 31;
        if (this.reviewStarRatingAccuracy != 0.0f) {
            i = Float.floatToIntBits(this.reviewStarRatingAccuracy);
        } else {
            i = 0;
        }
        int i11 = (i10 + i) * 31;
        if (this.reviewStarRatingCheckin != 0.0f) {
            i2 = Float.floatToIntBits(this.reviewStarRatingCheckin);
        } else {
            i2 = 0;
        }
        int i12 = (i11 + i2) * 31;
        if (this.reviewStarRatingCleanliness != 0.0f) {
            i3 = Float.floatToIntBits(this.reviewStarRatingCleanliness);
        } else {
            i3 = 0;
        }
        int i13 = (i12 + i3) * 31;
        if (this.reviewStarRatingCommunication != 0.0f) {
            i4 = Float.floatToIntBits(this.reviewStarRatingCommunication);
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.reviewStarRatingLocation != 0.0f) {
            i5 = Float.floatToIntBits(this.reviewStarRatingLocation);
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.reviewStarRatingValue != 0.0f) {
            i6 = Float.floatToIntBits(this.reviewStarRatingValue);
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "HomeStarRatingBreakdownEpoxyModel_{reviewStarRatingAccuracy=" + this.reviewStarRatingAccuracy + ", reviewStarRatingCheckin=" + this.reviewStarRatingCheckin + ", reviewStarRatingCleanliness=" + this.reviewStarRatingCleanliness + ", reviewStarRatingCommunication=" + this.reviewStarRatingCommunication + ", reviewStarRatingLocation=" + this.reviewStarRatingLocation + ", reviewStarRatingValue=" + this.reviewStarRatingValue + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
