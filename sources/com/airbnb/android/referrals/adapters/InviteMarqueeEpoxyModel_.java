package com.airbnb.android.referrals.adapters;

import com.airbnb.android.referrals.C1532R;
import com.airbnb.android.referrals.adapters.InviteMarqueeEpoxyModel.Holder;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

public class InviteMarqueeEpoxyModel_ extends InviteMarqueeEpoxyModel implements GeneratedModel<Holder> {
    private OnModelBoundListener<InviteMarqueeEpoxyModel_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InviteMarqueeEpoxyModel_, Holder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Holder object, int position) {
    }

    public void handlePostBind(Holder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InviteMarqueeEpoxyModel_ onBind(OnModelBoundListener<InviteMarqueeEpoxyModel_, Holder> listener) {
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

    public InviteMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<InviteMarqueeEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InviteMarqueeEpoxyModel_ titleText(String titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public String titleText() {
        return this.titleText;
    }

    public InviteMarqueeEpoxyModel_ subtitleText(String subtitleText) {
        onMutation();
        this.subtitleText = subtitleText;
        return this;
    }

    public String subtitleText() {
        return this.subtitleText;
    }

    public InviteMarqueeEpoxyModel_ subtitleLinkText(String subtitleLinkText) {
        onMutation();
        this.subtitleLinkText = subtitleLinkText;
        return this;
    }

    public String subtitleLinkText() {
        return this.subtitleLinkText;
    }

    public InviteMarqueeEpoxyModel_ subtitleLinkClickListener(LinkOnClickListener subtitleLinkClickListener) {
        onMutation();
        this.subtitleLinkClickListener = subtitleLinkClickListener;
        return this;
    }

    public LinkOnClickListener subtitleLinkClickListener() {
        return this.subtitleLinkClickListener;
    }

    public InviteMarqueeEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public InviteMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public InviteMarqueeEpoxyModel_ m1311id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InviteMarqueeEpoxyModel_ m1316id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InviteMarqueeEpoxyModel_ m1312id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InviteMarqueeEpoxyModel_ m1313id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InviteMarqueeEpoxyModel_ m1315id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InviteMarqueeEpoxyModel_ m1314id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InviteMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InviteMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InviteMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InviteMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InviteMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C1532R.layout.invite_marquee;
    }

    public InviteMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleText = null;
        this.subtitleText = null;
        this.subtitleLinkText = null;
        this.subtitleLinkClickListener = null;
        this.showDivider = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof InviteMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InviteMarqueeEpoxyModel_ that = (InviteMarqueeEpoxyModel_) o;
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
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.subtitleText != null) {
            if (!this.subtitleText.equals(that.subtitleText)) {
                return false;
            }
        } else if (that.subtitleText != null) {
            return false;
        }
        if (this.subtitleLinkText != null) {
            if (!this.subtitleLinkText.equals(that.subtitleLinkText)) {
                return false;
            }
        } else if (that.subtitleLinkText != null) {
            return false;
        }
        if (this.subtitleLinkClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.subtitleLinkClickListener == null)) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.titleText != null) {
            i2 = this.titleText.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.subtitleText != null) {
            i3 = this.subtitleText.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.subtitleLinkText != null) {
            i4 = this.subtitleLinkText.hashCode();
        } else {
            i4 = 0;
        }
        int i10 = (i9 + i4) * 31;
        if (this.subtitleLinkClickListener == null) {
            i5 = 0;
        }
        int i11 = (i10 + i5) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "InviteMarqueeEpoxyModel_{titleText=" + this.titleText + ", subtitleText=" + this.subtitleText + ", subtitleLinkText=" + this.subtitleLinkText + ", subtitleLinkClickListener=" + this.subtitleLinkClickListener + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
