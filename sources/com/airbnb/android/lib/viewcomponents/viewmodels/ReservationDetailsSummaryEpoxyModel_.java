package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ReservationDetailsSummaryEpoxyModel_ extends ReservationDetailsSummaryEpoxyModel implements GeneratedModel<UserDetailsActionRow> {
    private OnModelBoundListener<ReservationDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ReservationDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, UserDetailsActionRow object, int position) {
    }

    public void handlePostBind(UserDetailsActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ReservationDetailsSummaryEpoxyModel_ onBind(OnModelBoundListener<ReservationDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
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

    public ReservationDetailsSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<ReservationDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ reservation(Reservation reservation) {
        onMutation();
        this.reservation = reservation;
        return this;
    }

    public Reservation reservation() {
        return this.reservation;
    }

    public ReservationDetailsSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ReservationDetailsSummaryEpoxyModel_ m6239id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ReservationDetailsSummaryEpoxyModel_ m6244id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ReservationDetailsSummaryEpoxyModel_ m6240id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ReservationDetailsSummaryEpoxyModel_ m6241id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ReservationDetailsSummaryEpoxyModel_ m6243id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ReservationDetailsSummaryEpoxyModel_ m6242id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ReservationDetailsSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.n2_view_holder_user_details_action_row;
    }

    public ReservationDetailsSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.reservation = null;
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
        if (!(o instanceof ReservationDetailsSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ReservationDetailsSummaryEpoxyModel_ that = (ReservationDetailsSummaryEpoxyModel_) o;
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
        if (this.reservation != null) {
            if (!this.reservation.equals(that.reservation)) {
                return false;
            }
        } else if (that.reservation != null) {
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
        int i5 = (hashCode + i3) * 31;
        if (this.reservation != null) {
            i = this.reservation.hashCode();
        } else {
            i = 0;
        }
        int i6 = (i5 + i) * 31;
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
        return "ReservationDetailsSummaryEpoxyModel_{reservation=" + this.reservation + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
