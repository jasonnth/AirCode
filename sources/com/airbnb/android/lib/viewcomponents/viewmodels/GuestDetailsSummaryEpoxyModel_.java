package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
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

public class GuestDetailsSummaryEpoxyModel_ extends GuestDetailsSummaryEpoxyModel implements GeneratedModel<UserDetailsActionRow> {
    private OnModelBoundListener<GuestDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<GuestDetailsSummaryEpoxyModel_, UserDetailsActionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, UserDetailsActionRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(UserDetailsActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public GuestDetailsSummaryEpoxyModel_ onBind(OnModelBoundListener<GuestDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
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

    public GuestDetailsSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<GuestDetailsSummaryEpoxyModel_, UserDetailsActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ guest(User guest) {
        onMutation();
        this.guest = guest;
        return this;
    }

    public User guest() {
        return this.guest;
    }

    public GuestDetailsSummaryEpoxyModel_ clickListener(OnModelClickListener<GuestDetailsSummaryEpoxyModel_, UserDetailsActionRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public GuestDetailsSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public GuestDetailsSummaryEpoxyModel_ m6131id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public GuestDetailsSummaryEpoxyModel_ m6136id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public GuestDetailsSummaryEpoxyModel_ m6132id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public GuestDetailsSummaryEpoxyModel_ m6133id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public GuestDetailsSummaryEpoxyModel_ m6135id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public GuestDetailsSummaryEpoxyModel_ m6134id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public GuestDetailsSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.n2_view_holder_user_details_action_row;
    }

    public GuestDetailsSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.guest = null;
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
        if (!(o instanceof GuestDetailsSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        GuestDetailsSummaryEpoxyModel_ that = (GuestDetailsSummaryEpoxyModel_) o;
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
        if (this.guest != null) {
            if (!this.guest.equals(that.guest)) {
                return false;
            }
        } else if (that.guest != null) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.guest != null) {
            i2 = this.guest.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.clickListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
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
        return "GuestDetailsSummaryEpoxyModel_{guest=" + this.guest + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
