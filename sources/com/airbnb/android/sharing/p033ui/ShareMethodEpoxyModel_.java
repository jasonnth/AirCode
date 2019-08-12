package com.airbnb.android.sharing.p033ui;

import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;

/* renamed from: com.airbnb.android.sharing.ui.ShareMethodEpoxyModel_ */
public class ShareMethodEpoxyModel_ extends ShareMethodEpoxyModel implements GeneratedModel<Holder> {
    private OnModelBoundListener<ShareMethodEpoxyModel_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ShareMethodEpoxyModel_, Holder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Holder object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(Holder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ShareMethodEpoxyModel_ onBind(OnModelBoundListener<ShareMethodEpoxyModel_, Holder> listener) {
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

    public ShareMethodEpoxyModel_ onUnbind(OnModelUnboundListener<ShareMethodEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ShareMethodEpoxyModel_ name(String name) {
        onMutation();
        this.name = name;
        return this;
    }

    public String name() {
        return this.name;
    }

    public ShareMethodEpoxyModel_ clickListener(OnModelClickListener<ShareMethodEpoxyModel_, Holder> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ShareMethodEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ShareMethodEpoxyModel_ icon(Drawable icon) {
        onMutation();
        this.icon = icon;
        return this;
    }

    public Drawable icon() {
        return this.icon;
    }

    public ShareMethodEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public ShareMethodEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public ShareMethodEpoxyModel_ m1373id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ShareMethodEpoxyModel_ m1378id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ShareMethodEpoxyModel_ m1374id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ShareMethodEpoxyModel_ m1375id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ShareMethodEpoxyModel_ m1377id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ShareMethodEpoxyModel_ m1376id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ShareMethodEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ShareMethodEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ShareMethodEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ShareMethodEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ShareMethodEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0921R.layout.view_holder_share_method;
    }

    public ShareMethodEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.name = null;
        this.clickListener = null;
        this.icon = null;
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
        if (!(o instanceof ShareMethodEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ShareMethodEpoxyModel_ that = (ShareMethodEpoxyModel_) o;
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
        if (this.name != null) {
            if (!this.name.equals(that.name)) {
                return false;
            }
        } else if (that.name != null) {
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
        if (this.icon != null) {
            if (!this.icon.equals(that.icon)) {
                return false;
            }
        } else if (that.icon != null) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.name != null) {
            i2 = this.name.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.clickListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
        if (this.icon != null) {
            i3 = this.icon.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "ShareMethodEpoxyModel_{name=" + this.name + ", clickListener=" + this.clickListener + ", icon=" + this.icon + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
