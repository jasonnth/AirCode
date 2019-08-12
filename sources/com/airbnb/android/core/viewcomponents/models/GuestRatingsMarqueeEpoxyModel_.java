package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.GuestRatingsMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class GuestRatingsMarqueeEpoxyModel_ extends GuestRatingsMarqueeEpoxyModel implements GeneratedModel<GuestRatingsMarquee> {
    private OnModelBoundListener<GuestRatingsMarqueeEpoxyModel_, GuestRatingsMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<GuestRatingsMarqueeEpoxyModel_, GuestRatingsMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, GuestRatingsMarquee object, int position) {
    }

    public void handlePostBind(GuestRatingsMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public GuestRatingsMarqueeEpoxyModel_ onBind(OnModelBoundListener<GuestRatingsMarqueeEpoxyModel_, GuestRatingsMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(GuestRatingsMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public GuestRatingsMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<GuestRatingsMarqueeEpoxyModel_, GuestRatingsMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ guestName(String guestName) {
        onMutation();
        this.guestName = guestName;
        return this;
    }

    public String guestName() {
        return this.guestName;
    }

    public GuestRatingsMarqueeEpoxyModel_ numStars(float numStars) {
        onMutation();
        this.numStars = numStars;
        return this;
    }

    public float numStars() {
        return this.numStars;
    }

    public GuestRatingsMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public GuestRatingsMarqueeEpoxyModel_ m4642id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public GuestRatingsMarqueeEpoxyModel_ m4647id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public GuestRatingsMarqueeEpoxyModel_ m4643id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public GuestRatingsMarqueeEpoxyModel_ m4644id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public GuestRatingsMarqueeEpoxyModel_ m4646id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public GuestRatingsMarqueeEpoxyModel_ m4645id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public GuestRatingsMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_guest_ratings_marquee;
    }

    public GuestRatingsMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.guestName = null;
        this.numStars = 0.0f;
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
        if (!(o instanceof GuestRatingsMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        GuestRatingsMarqueeEpoxyModel_ that = (GuestRatingsMarqueeEpoxyModel_) o;
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
        if (this.guestName != null) {
            if (!this.guestName.equals(that.guestName)) {
                return false;
            }
        } else if (that.guestName != null) {
            return false;
        }
        if (Float.compare(that.numStars, this.numStars) != 0) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.guestName != null) {
            i = this.guestName.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.numStars != 0.0f) {
            i2 = Float.floatToIntBits(this.numStars);
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "GuestRatingsMarqueeEpoxyModel_{guestName=" + this.guestName + ", numStars=" + this.numStars + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
