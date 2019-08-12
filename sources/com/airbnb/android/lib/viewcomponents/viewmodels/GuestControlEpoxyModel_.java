package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GuestControlToggleView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class GuestControlEpoxyModel_ extends GuestControlEpoxyModel implements GeneratedModel<GuestControlToggleView> {
    private OnModelBoundListener<GuestControlEpoxyModel_, GuestControlToggleView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<GuestControlEpoxyModel_, GuestControlToggleView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, GuestControlToggleView object, int position) {
        if (this.yesClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.yesClickListener).bind(holder, object);
        }
        if (this.noClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.noClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(GuestControlToggleView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public GuestControlEpoxyModel_ onBind(OnModelBoundListener<GuestControlEpoxyModel_, GuestControlToggleView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(GuestControlToggleView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public GuestControlEpoxyModel_ onUnbind(OnModelUnboundListener<GuestControlEpoxyModel_, GuestControlToggleView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public GuestControlEpoxyModel_ type(GuestControlType type) {
        onMutation();
        this.type = type;
        return this;
    }

    public GuestControlType type() {
        return this.type;
    }

    public GuestControlEpoxyModel_ isAllowed(Boolean isAllowed) {
        onMutation();
        this.isAllowed = isAllowed;
        return this;
    }

    public Boolean isAllowed() {
        return this.isAllowed;
    }

    public GuestControlEpoxyModel_ yesClickListener(OnModelClickListener<GuestControlEpoxyModel_, GuestControlToggleView> yesClickListener) {
        onMutation();
        if (yesClickListener == null) {
            this.yesClickListener = null;
        } else {
            this.yesClickListener = new WrappedEpoxyModelClickListener(this, yesClickListener);
        }
        return this;
    }

    public GuestControlEpoxyModel_ yesClickListener(OnClickListener yesClickListener) {
        onMutation();
        this.yesClickListener = yesClickListener;
        return this;
    }

    public OnClickListener yesClickListener() {
        return this.yesClickListener;
    }

    public GuestControlEpoxyModel_ noClickListener(OnModelClickListener<GuestControlEpoxyModel_, GuestControlToggleView> noClickListener) {
        onMutation();
        if (noClickListener == null) {
            this.noClickListener = null;
        } else {
            this.noClickListener = new WrappedEpoxyModelClickListener(this, noClickListener);
        }
        return this;
    }

    public GuestControlEpoxyModel_ noClickListener(OnClickListener noClickListener) {
        onMutation();
        this.noClickListener = noClickListener;
        return this;
    }

    public OnClickListener noClickListener() {
        return this.noClickListener;
    }

    public GuestControlEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public GuestControlEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public GuestControlEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public GuestControlEpoxyModel_ m6119id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public GuestControlEpoxyModel_ m6124id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public GuestControlEpoxyModel_ m6120id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public GuestControlEpoxyModel_ m6121id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public GuestControlEpoxyModel_ m6123id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public GuestControlEpoxyModel_ m6122id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public GuestControlEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public GuestControlEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public GuestControlEpoxyModel_ show() {
        super.show();
        return this;
    }

    public GuestControlEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public GuestControlEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_guest_control_toggle_view;
    }

    public GuestControlEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.type = null;
        this.isAllowed = null;
        this.yesClickListener = null;
        this.noClickListener = null;
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
        if (!(o instanceof GuestControlEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        GuestControlEpoxyModel_ that = (GuestControlEpoxyModel_) o;
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
        if (this.type != null) {
            if (!this.type.equals(that.type)) {
                return false;
            }
        } else if (that.type != null) {
            return false;
        }
        if (this.isAllowed != null) {
            if (!this.isAllowed.equals(that.isAllowed)) {
                return false;
            }
        } else if (that.isAllowed != null) {
            return false;
        }
        if (this.yesClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.yesClickListener == null)) {
            return false;
        }
        if (this.noClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.noClickListener == null)) {
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
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.type != null) {
            i2 = this.type.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.isAllowed != null) {
            i3 = this.isAllowed.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.yesClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.noClickListener == null) {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.numCarouselItemsShown != null) {
            i7 = this.numCarouselItemsShown.hashCode();
        }
        return i13 + i7;
    }

    public String toString() {
        return "GuestControlEpoxyModel_{type=" + this.type + ", isAllowed=" + this.isAllowed + ", yesClickListener=" + this.yesClickListener + ", noClickListener=" + this.noClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
