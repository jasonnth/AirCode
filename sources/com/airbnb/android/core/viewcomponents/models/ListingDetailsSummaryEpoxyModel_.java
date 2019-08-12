package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ListingDetailsSummaryEpoxyModel_ extends ListingDetailsSummaryEpoxyModel implements GeneratedModel<UserDetailsActionRow> {
    private OnModelBoundListener<ListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, UserDetailsActionRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
        if (this.hostImageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.hostImageClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(UserDetailsActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ListingDetailsSummaryEpoxyModel_ onBind(OnModelBoundListener<ListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(UserDetailsActionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ListingDetailsSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<ListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ listing(Listing listing) {
        onMutation();
        this.listing = listing;
        super.listing(listing);
        return this;
    }

    public Listing listing() {
        return this.listing;
    }

    public ListingDetailsSummaryEpoxyModel_ host(User host) {
        onMutation();
        this.host = host;
        return this;
    }

    public User host() {
        return this.host;
    }

    public ListingDetailsSummaryEpoxyModel_ clickListener(OnModelClickListener<ListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ListingDetailsSummaryEpoxyModel_ hostImageClickListener(OnModelClickListener<ListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> hostImageClickListener) {
        onMutation();
        if (hostImageClickListener == null) {
            this.hostImageClickListener = null;
        } else {
            this.hostImageClickListener = new WrappedEpoxyModelClickListener(this, hostImageClickListener);
        }
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ hostImageClickListener(OnClickListener hostImageClickListener) {
        onMutation();
        this.hostImageClickListener = hostImageClickListener;
        return this;
    }

    public OnClickListener hostImageClickListener() {
        return this.hostImageClickListener;
    }

    public ListingDetailsSummaryEpoxyModel_ businessReady(boolean businessReady) {
        onMutation();
        this.businessReady = businessReady;
        return this;
    }

    public boolean businessReady() {
        return this.businessReady;
    }

    public ListingDetailsSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ reservation(Reservation reservation) {
        super.reservation(reservation);
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ListingDetailsSummaryEpoxyModel_ m5074id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ListingDetailsSummaryEpoxyModel_ m5079id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ListingDetailsSummaryEpoxyModel_ m5075id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ListingDetailsSummaryEpoxyModel_ m5076id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ListingDetailsSummaryEpoxyModel_ m5078id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ListingDetailsSummaryEpoxyModel_ m5077id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ListingDetailsSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_user_details_action_row;
    }

    public ListingDetailsSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.listing = null;
        this.host = null;
        this.clickListener = null;
        this.hostImageClickListener = null;
        this.businessReady = false;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListingDetailsSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ListingDetailsSummaryEpoxyModel_ that = (ListingDetailsSummaryEpoxyModel_) o;
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
        if (this.listing != null) {
            if (!this.listing.equals(that.listing)) {
                return false;
            }
        } else if (that.listing != null) {
            return false;
        }
        if (this.host != null) {
            if (!this.host.equals(that.host)) {
                return false;
            }
        } else if (that.host != null) {
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
        if (this.hostImageClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.hostImageClickListener == null) || this.businessReady != that.businessReady) {
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
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.listing != null) {
            i2 = this.listing.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.host != null) {
            i3 = this.host.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.clickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.hostImageClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (!this.businessReady) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "ListingDetailsSummaryEpoxyModel_{listing=" + this.listing + ", host=" + this.host + ", clickListener=" + this.clickListener + ", hostImageClickListener=" + this.hostImageClickListener + ", businessReady=" + this.businessReady + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
