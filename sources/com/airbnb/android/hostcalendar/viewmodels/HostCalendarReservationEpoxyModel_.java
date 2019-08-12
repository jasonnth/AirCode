package com.airbnb.android.hostcalendar.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.HostCalendarReservationView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class HostCalendarReservationEpoxyModel_ extends HostCalendarReservationEpoxyModel implements GeneratedModel<HostCalendarReservationView> {
    private OnModelBoundListener<HostCalendarReservationEpoxyModel_, HostCalendarReservationView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HostCalendarReservationEpoxyModel_, HostCalendarReservationView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HostCalendarReservationView object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HostCalendarReservationView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HostCalendarReservationEpoxyModel_ onBind(OnModelBoundListener<HostCalendarReservationEpoxyModel_, HostCalendarReservationView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HostCalendarReservationView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HostCalendarReservationEpoxyModel_ onUnbind(OnModelUnboundListener<HostCalendarReservationEpoxyModel_, HostCalendarReservationView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HostCalendarReservationEpoxyModel_ hideGuestProfilePhoto(boolean hideGuestProfilePhoto) {
        onMutation();
        this.hideGuestProfilePhoto = hideGuestProfilePhoto;
        return this;
    }

    public boolean hideGuestProfilePhoto() {
        return this.hideGuestProfilePhoto;
    }

    public HostCalendarReservationEpoxyModel_ profilePlaceholderText(CharSequence profilePlaceholderText) {
        onMutation();
        this.profilePlaceholderText = profilePlaceholderText;
        return this;
    }

    public CharSequence profilePlaceholderText() {
        return this.profilePlaceholderText;
    }

    public HostCalendarReservationEpoxyModel_ clickListener(OnModelClickListener<HostCalendarReservationEpoxyModel_, HostCalendarReservationView> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public HostCalendarReservationEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        super.clickListener(clickListener);
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public HostCalendarReservationEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ calendarDays(CalendarDays calendarDays) {
        super.calendarDays(calendarDays);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ requestEndDate(AirDate requestEndDate) {
        super.requestEndDate(requestEndDate);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ requestStartDate(AirDate requestStartDate) {
        super.requestStartDate(requestStartDate);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ guestPhotoUrl(String guestPhotoUrl) {
        super.guestPhotoUrl(guestPhotoUrl);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HostCalendarReservationEpoxyModel_ m5987id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HostCalendarReservationEpoxyModel_ m5992id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HostCalendarReservationEpoxyModel_ m5988id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HostCalendarReservationEpoxyModel_ m5989id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HostCalendarReservationEpoxyModel_ m5991id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HostCalendarReservationEpoxyModel_ m5990id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HostCalendarReservationEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HostCalendarReservationEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C6418R.layout.view_holder_host_calendar_reservation_view;
    }

    public HostCalendarReservationEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.hideGuestProfilePhoto = false;
        this.profilePlaceholderText = null;
        this.clickListener = null;
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
        if (!(o instanceof HostCalendarReservationEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HostCalendarReservationEpoxyModel_ that = (HostCalendarReservationEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.hideGuestProfilePhoto != that.hideGuestProfilePhoto) {
            return false;
        }
        if (this.profilePlaceholderText != null) {
            if (!this.profilePlaceholderText.equals(that.profilePlaceholderText)) {
                return false;
            }
        } else if (that.profilePlaceholderText != null) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.hideGuestProfilePhoto) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.profilePlaceholderText != null) {
            i3 = this.profilePlaceholderText.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.clickListener == null) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
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
        return "HostCalendarReservationEpoxyModel_{hideGuestProfilePhoto=" + this.hideGuestProfilePhoto + ", profilePlaceholderText=" + this.profilePlaceholderText + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
