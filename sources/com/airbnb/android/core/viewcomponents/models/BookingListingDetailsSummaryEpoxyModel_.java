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

public class BookingListingDetailsSummaryEpoxyModel_ extends BookingListingDetailsSummaryEpoxyModel implements GeneratedModel<UserDetailsActionRow> {
    private OnModelBoundListener<BookingListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<BookingListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelUnboundListener_epoxyGeneratedModel;

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

    public BookingListingDetailsSummaryEpoxyModel_ onBind(OnModelBoundListener<BookingListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
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

    public BookingListingDetailsSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<BookingListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ listing(Listing listing) {
        onMutation();
        this.listing = listing;
        super.listing(listing);
        return this;
    }

    public Listing listing() {
        return this.listing;
    }

    public BookingListingDetailsSummaryEpoxyModel_ host(User host) {
        onMutation();
        this.host = host;
        return this;
    }

    public User host() {
        return this.host;
    }

    public BookingListingDetailsSummaryEpoxyModel_ clickListener(OnModelClickListener<BookingListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public BookingListingDetailsSummaryEpoxyModel_ hostImageClickListener(OnModelClickListener<BookingListingDetailsSummaryEpoxyModel_, UserDetailsActionRow> hostImageClickListener) {
        onMutation();
        if (hostImageClickListener == null) {
            this.hostImageClickListener = null;
        } else {
            this.hostImageClickListener = new WrappedEpoxyModelClickListener(this, hostImageClickListener);
        }
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ hostImageClickListener(OnClickListener hostImageClickListener) {
        onMutation();
        this.hostImageClickListener = hostImageClickListener;
        return this;
    }

    public OnClickListener hostImageClickListener() {
        return this.hostImageClickListener;
    }

    public BookingListingDetailsSummaryEpoxyModel_ businessReady(boolean businessReady) {
        onMutation();
        this.businessReady = businessReady;
        return this;
    }

    public boolean businessReady() {
        return this.businessReady;
    }

    public BookingListingDetailsSummaryEpoxyModel_ reservation(Reservation reservation) {
        super.reservation(reservation);
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public BookingListingDetailsSummaryEpoxyModel_ m4387id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public BookingListingDetailsSummaryEpoxyModel_ m4392id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public BookingListingDetailsSummaryEpoxyModel_ m4388id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public BookingListingDetailsSummaryEpoxyModel_ m4389id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public BookingListingDetailsSummaryEpoxyModel_ m4391id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public BookingListingDetailsSummaryEpoxyModel_ m4390id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public BookingListingDetailsSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_user_details_action_row;
    }

    public BookingListingDetailsSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        this.listing = null;
        this.host = null;
        this.clickListener = null;
        this.hostImageClickListener = null;
        this.businessReady = false;
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
        if (!(o instanceof BookingListingDetailsSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        BookingListingDetailsSummaryEpoxyModel_ that = (BookingListingDetailsSummaryEpoxyModel_) o;
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
        if (z3 == (that.hostImageClickListener == null) && this.businessReady == that.businessReady) {
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
        int i7;
        int i8 = 1;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i3 = this.numCarouselItemsShown.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.listing != null) {
            i4 = this.listing.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.host != null) {
            i5 = this.host.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.clickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i14 = (i13 + i6) * 31;
        if (this.hostImageClickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i15 = (i14 + i7) * 31;
        if (!this.businessReady) {
            i8 = 0;
        }
        return i15 + i8;
    }

    public String toString() {
        return "BookingListingDetailsSummaryEpoxyModel_{showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + ", listing=" + this.listing + ", host=" + this.host + ", clickListener=" + this.clickListener + ", hostImageClickListener=" + this.hostImageClickListener + ", businessReady=" + this.businessReady + "}" + super.toString();
    }
}
