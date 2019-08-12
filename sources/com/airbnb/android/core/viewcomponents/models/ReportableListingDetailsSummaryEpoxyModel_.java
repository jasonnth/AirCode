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
import com.airbnb.p027n2.components.ReportableDetailsSummary;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ReportableListingDetailsSummaryEpoxyModel_ extends ReportableListingDetailsSummaryEpoxyModel implements GeneratedModel<ReportableDetailsSummary> {
    private OnModelBoundListener<ReportableListingDetailsSummaryEpoxyModel_, ReportableDetailsSummary> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ReportableListingDetailsSummaryEpoxyModel_, ReportableDetailsSummary> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ReportableDetailsSummary object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
        if (this.hostImageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.hostImageClickListener).bind(holder, object);
        }
        if (this.reportTextClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.reportTextClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ReportableDetailsSummary object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ReportableListingDetailsSummaryEpoxyModel_ onBind(OnModelBoundListener<ReportableListingDetailsSummaryEpoxyModel_, ReportableDetailsSummary> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ReportableDetailsSummary object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ReportableListingDetailsSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<ReportableListingDetailsSummaryEpoxyModel_, ReportableDetailsSummary> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ listing(Listing listing) {
        onMutation();
        this.listing = listing;
        super.listing(listing);
        return this;
    }

    public Listing listing() {
        return this.listing;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ host(User host) {
        onMutation();
        this.host = host;
        return this;
    }

    public User host() {
        return this.host;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ showReport(boolean showReport) {
        onMutation();
        this.showReport = showReport;
        return this;
    }

    public boolean showReport() {
        return this.showReport;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ reported(boolean reported) {
        onMutation();
        this.reported = reported;
        return this;
    }

    public boolean reported() {
        return this.reported;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ clickListener(OnModelClickListener<ReportableListingDetailsSummaryEpoxyModel_, ReportableDetailsSummary> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ hostImageClickListener(OnModelClickListener<ReportableListingDetailsSummaryEpoxyModel_, ReportableDetailsSummary> hostImageClickListener) {
        onMutation();
        if (hostImageClickListener == null) {
            this.hostImageClickListener = null;
        } else {
            this.hostImageClickListener = new WrappedEpoxyModelClickListener(this, hostImageClickListener);
        }
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ hostImageClickListener(OnClickListener hostImageClickListener) {
        onMutation();
        this.hostImageClickListener = hostImageClickListener;
        return this;
    }

    public OnClickListener hostImageClickListener() {
        return this.hostImageClickListener;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ reportTextClickListener(OnModelClickListener<ReportableListingDetailsSummaryEpoxyModel_, ReportableDetailsSummary> reportTextClickListener) {
        onMutation();
        if (reportTextClickListener == null) {
            this.reportTextClickListener = null;
        } else {
            this.reportTextClickListener = new WrappedEpoxyModelClickListener(this, reportTextClickListener);
        }
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ reportTextClickListener(OnClickListener reportTextClickListener) {
        onMutation();
        this.reportTextClickListener = reportTextClickListener;
        return this;
    }

    public OnClickListener reportTextClickListener() {
        return this.reportTextClickListener;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ businessReady(boolean businessReady) {
        onMutation();
        this.businessReady = businessReady;
        return this;
    }

    public boolean businessReady() {
        return this.businessReady;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ reservation(Reservation reservation) {
        super.reservation(reservation);
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ReportableListingDetailsSummaryEpoxyModel_ m5434id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ReportableListingDetailsSummaryEpoxyModel_ m5439id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ReportableListingDetailsSummaryEpoxyModel_ m5435id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ReportableListingDetailsSummaryEpoxyModel_ m5436id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ReportableListingDetailsSummaryEpoxyModel_ m5438id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ReportableListingDetailsSummaryEpoxyModel_ m5437id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_reportable_details_summary;
    }

    public ReportableListingDetailsSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.listing = null;
        this.host = null;
        this.showReport = false;
        this.reported = false;
        this.clickListener = null;
        this.hostImageClickListener = null;
        this.reportTextClickListener = null;
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
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReportableListingDetailsSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ReportableListingDetailsSummaryEpoxyModel_ that = (ReportableListingDetailsSummaryEpoxyModel_) o;
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
        if (this.showReport != that.showReport || this.reported != that.reported) {
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
        if (z3 != (that.hostImageClickListener == null)) {
            return false;
        }
        if (this.reportTextClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.reportTextClickListener == null) || this.businessReady != that.businessReady) {
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
        int i9;
        int i10 = 1;
        int i11 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i12 = (hashCode + i) * 31;
        if (this.listing != null) {
            i2 = this.listing.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (i12 + i2) * 31;
        if (this.host != null) {
            i3 = this.host.hashCode();
        } else {
            i3 = 0;
        }
        int i14 = (i13 + i3) * 31;
        if (this.showReport) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i15 = (i14 + i4) * 31;
        if (this.reported) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        if (this.clickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i17 = (i16 + i6) * 31;
        if (this.hostImageClickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
        if (this.reportTextClickListener != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (!this.businessReady) {
            i10 = 0;
        }
        int i20 = (i19 + i10) * 31;
        if (this.showDivider != null) {
            i9 = this.showDivider.hashCode();
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.numCarouselItemsShown != null) {
            i11 = this.numCarouselItemsShown.hashCode();
        }
        return i21 + i11;
    }

    public String toString() {
        return "ReportableListingDetailsSummaryEpoxyModel_{listing=" + this.listing + ", host=" + this.host + ", showReport=" + this.showReport + ", reported=" + this.reported + ", clickListener=" + this.clickListener + ", hostImageClickListener=" + this.hostImageClickListener + ", reportTextClickListener=" + this.reportTextClickListener + ", businessReady=" + this.businessReady + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
