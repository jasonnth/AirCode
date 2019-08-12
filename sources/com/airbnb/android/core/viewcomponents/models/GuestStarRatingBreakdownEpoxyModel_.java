package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.GuestStarRatingBreakdown;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class GuestStarRatingBreakdownEpoxyModel_ extends GuestStarRatingBreakdownEpoxyModel implements GeneratedModel<GuestStarRatingBreakdown> {
    private OnModelBoundListener<GuestStarRatingBreakdownEpoxyModel_, GuestStarRatingBreakdown> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<GuestStarRatingBreakdownEpoxyModel_, GuestStarRatingBreakdown> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, GuestStarRatingBreakdown object, int position) {
    }

    public void handlePostBind(GuestStarRatingBreakdown object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public GuestStarRatingBreakdownEpoxyModel_ onBind(OnModelBoundListener<GuestStarRatingBreakdownEpoxyModel_, GuestStarRatingBreakdown> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(GuestStarRatingBreakdown object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public GuestStarRatingBreakdownEpoxyModel_ onUnbind(OnModelUnboundListener<GuestStarRatingBreakdownEpoxyModel_, GuestStarRatingBreakdown> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ ratingCleanliness(float ratingCleanliness) {
        onMutation();
        this.ratingCleanliness = ratingCleanliness;
        return this;
    }

    public float ratingCleanliness() {
        return this.ratingCleanliness;
    }

    public GuestStarRatingBreakdownEpoxyModel_ ratingCommunication(float ratingCommunication) {
        onMutation();
        this.ratingCommunication = ratingCommunication;
        return this;
    }

    public float ratingCommunication() {
        return this.ratingCommunication;
    }

    public GuestStarRatingBreakdownEpoxyModel_ ratingHouseRules(float ratingHouseRules) {
        onMutation();
        this.ratingHouseRules = ratingHouseRules;
        return this;
    }

    public float ratingHouseRules() {
        return this.ratingHouseRules;
    }

    public GuestStarRatingBreakdownEpoxyModel_ numCleanliness(int numCleanliness) {
        onMutation();
        this.numCleanliness = numCleanliness;
        return this;
    }

    public int numCleanliness() {
        return this.numCleanliness;
    }

    public GuestStarRatingBreakdownEpoxyModel_ numCommunication(int numCommunication) {
        onMutation();
        this.numCommunication = numCommunication;
        return this;
    }

    public int numCommunication() {
        return this.numCommunication;
    }

    public GuestStarRatingBreakdownEpoxyModel_ numHouseRules(int numHouseRules) {
        onMutation();
        this.numHouseRules = numHouseRules;
        return this;
    }

    public int numHouseRules() {
        return this.numHouseRules;
    }

    public GuestStarRatingBreakdownEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public GuestStarRatingBreakdownEpoxyModel_ m4654id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public GuestStarRatingBreakdownEpoxyModel_ m4659id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public GuestStarRatingBreakdownEpoxyModel_ m4655id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public GuestStarRatingBreakdownEpoxyModel_ m4656id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public GuestStarRatingBreakdownEpoxyModel_ m4658id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public GuestStarRatingBreakdownEpoxyModel_ m4657id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ show() {
        super.show();
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public GuestStarRatingBreakdownEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_guest_star_rating_breakdown;
    }

    public GuestStarRatingBreakdownEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.ratingCleanliness = 0.0f;
        this.ratingCommunication = 0.0f;
        this.ratingHouseRules = 0.0f;
        this.numCleanliness = 0;
        this.numCommunication = 0;
        this.numHouseRules = 0;
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
        if (!(o instanceof GuestStarRatingBreakdownEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        GuestStarRatingBreakdownEpoxyModel_ that = (GuestStarRatingBreakdownEpoxyModel_) o;
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
        if (z != z2 || Float.compare(that.ratingCleanliness, this.ratingCleanliness) != 0 || Float.compare(that.ratingCommunication, this.ratingCommunication) != 0 || Float.compare(that.ratingHouseRules, this.ratingHouseRules) != 0 || this.numCleanliness != that.numCleanliness || this.numCommunication != that.numCommunication || this.numHouseRules != that.numHouseRules) {
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
        int i7 = (hashCode + i5) * 31;
        if (this.ratingCleanliness != 0.0f) {
            i = Float.floatToIntBits(this.ratingCleanliness);
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.ratingCommunication != 0.0f) {
            i2 = Float.floatToIntBits(this.ratingCommunication);
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.ratingHouseRules != 0.0f) {
            i3 = Float.floatToIntBits(this.ratingHouseRules);
        } else {
            i3 = 0;
        }
        int i10 = (((((((i9 + i3) * 31) + this.numCleanliness) * 31) + this.numCommunication) * 31) + this.numHouseRules) * 31;
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
        return "GuestStarRatingBreakdownEpoxyModel_{ratingCleanliness=" + this.ratingCleanliness + ", ratingCommunication=" + this.ratingCommunication + ", ratingHouseRules=" + this.ratingHouseRules + ", numCleanliness=" + this.numCleanliness + ", numCommunication=" + this.numCommunication + ", numHouseRules=" + this.numHouseRules + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
