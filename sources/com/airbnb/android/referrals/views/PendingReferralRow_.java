package com.airbnb.android.referrals.views;

import com.airbnb.android.referrals.C1532R;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;

public class PendingReferralRow_ extends PendingReferralRow implements GeneratedModel<Holder> {
    private OnModelBoundListener<PendingReferralRow_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PendingReferralRow_, Holder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Holder object, int position) {
    }

    public void handlePostBind(Holder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PendingReferralRow_ onBind(OnModelBoundListener<PendingReferralRow_, Holder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(Holder object) {
        super.unbind((EpoxyHolder) object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PendingReferralRow_ onUnbind(OnModelUnboundListener<PendingReferralRow_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PendingReferralRow_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public PendingReferralRow_ status(String status) {
        onMutation();
        this.status = status;
        return this;
    }

    public String status() {
        return this.status;
    }

    public PendingReferralRow_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public PendingReferralRow_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public PendingReferralRow_ m1347id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PendingReferralRow_ m1352id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PendingReferralRow_ m1348id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PendingReferralRow_ m1349id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PendingReferralRow_ m1351id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PendingReferralRow_ m1350id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PendingReferralRow_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PendingReferralRow_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PendingReferralRow_ show() {
        super.show();
        return this;
    }

    public PendingReferralRow_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PendingReferralRow_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C1532R.layout.pending_referral_row;
    }

    public PendingReferralRow_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.imageUrl = null;
        this.status = null;
        this.showDivider = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PendingReferralRow_) || !super.equals(o)) {
            return false;
        }
        PendingReferralRow_ that = (PendingReferralRow_) o;
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
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.status != null) {
            if (!this.status.equals(that.status)) {
                return false;
            }
        } else if (that.status != null) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
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
        if (this.imageUrl != null) {
            i = this.imageUrl.hashCode();
        } else {
            i = 0;
        }
        int i6 = (i5 + i) * 31;
        if (this.status != null) {
            i2 = this.status.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "PendingReferralRow_{imageUrl=" + this.imageUrl + ", status=" + this.status + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
