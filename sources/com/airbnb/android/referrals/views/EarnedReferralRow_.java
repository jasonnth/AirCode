package com.airbnb.android.referrals.views;

import com.airbnb.android.referrals.C1532R;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;

public class EarnedReferralRow_ extends EarnedReferralRow implements GeneratedModel<Holder> {
    private OnModelBoundListener<EarnedReferralRow_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<EarnedReferralRow_, Holder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Holder object, int position) {
    }

    public void handlePostBind(Holder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public EarnedReferralRow_ onBind(OnModelBoundListener<EarnedReferralRow_, Holder> listener) {
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

    public EarnedReferralRow_ onUnbind(OnModelUnboundListener<EarnedReferralRow_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public EarnedReferralRow_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public EarnedReferralRow_ name(String name) {
        onMutation();
        this.name = name;
        return this;
    }

    public String name() {
        return this.name;
    }

    public EarnedReferralRow_ status(String status) {
        onMutation();
        this.status = status;
        return this;
    }

    public String status() {
        return this.status;
    }

    public EarnedReferralRow_ amountEarned(String amountEarned) {
        onMutation();
        this.amountEarned = amountEarned;
        return this;
    }

    public String amountEarned() {
        return this.amountEarned;
    }

    public EarnedReferralRow_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public EarnedReferralRow_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public EarnedReferralRow_ m1335id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public EarnedReferralRow_ m1340id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public EarnedReferralRow_ m1336id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public EarnedReferralRow_ m1337id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public EarnedReferralRow_ m1339id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public EarnedReferralRow_ m1338id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public EarnedReferralRow_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public EarnedReferralRow_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public EarnedReferralRow_ show() {
        super.show();
        return this;
    }

    public EarnedReferralRow_ show(boolean show) {
        super.show(show);
        return this;
    }

    public EarnedReferralRow_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C1532R.layout.earned_referral_row;
    }

    public EarnedReferralRow_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.imageUrl = null;
        this.name = null;
        this.status = null;
        this.amountEarned = null;
        this.showDivider = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof EarnedReferralRow_) || !super.equals(o)) {
            return false;
        }
        EarnedReferralRow_ that = (EarnedReferralRow_) o;
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
        if (this.name != null) {
            if (!this.name.equals(that.name)) {
                return false;
            }
        } else if (that.name != null) {
            return false;
        }
        if (this.status != null) {
            if (!this.status.equals(that.status)) {
                return false;
            }
        } else if (that.status != null) {
            return false;
        }
        if (this.amountEarned != null) {
            if (!this.amountEarned.equals(that.amountEarned)) {
                return false;
            }
        } else if (that.amountEarned != null) {
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
        int i3;
        int i4;
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i5 = 0;
        }
        int i7 = (hashCode + i5) * 31;
        if (this.imageUrl != null) {
            i = this.imageUrl.hashCode();
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.name != null) {
            i2 = this.name.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.status != null) {
            i3 = this.status.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.amountEarned != null) {
            i4 = this.amountEarned.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "EarnedReferralRow_{imageUrl=" + this.imageUrl + ", name=" + this.name + ", status=" + this.status + ", amountEarned=" + this.amountEarned + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
