package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.views.UrgencyView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class UrgencyEpoxyModel_ extends UrgencyEpoxyModel implements GeneratedModel<UrgencyView> {
    private OnModelBoundListener<UrgencyEpoxyModel_, UrgencyView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<UrgencyEpoxyModel_, UrgencyView> onModelUnboundListener_epoxyGeneratedModel;

    public UrgencyEpoxyModel_(long id) {
        super(id);
    }

    public UrgencyEpoxyModel_() {
    }

    public void handlePreBind(EpoxyViewHolder holder, UrgencyView object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(UrgencyView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public UrgencyEpoxyModel_ onBind(OnModelBoundListener<UrgencyEpoxyModel_, UrgencyView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(UrgencyView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public UrgencyEpoxyModel_ onUnbind(OnModelUnboundListener<UrgencyEpoxyModel_, UrgencyView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public UrgencyEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public UrgencyEpoxyModel_ subtitle(String subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public String subtitle() {
        return this.subtitle;
    }

    public UrgencyEpoxyModel_ contextualMessage(String contextualMessage) {
        onMutation();
        this.contextualMessage = contextualMessage;
        return this;
    }

    public String contextualMessage() {
        return this.contextualMessage;
    }

    public UrgencyEpoxyModel_ type(UrgencyMessageType type) {
        onMutation();
        this.type = type;
        return this;
    }

    public UrgencyMessageType type() {
        return this.type;
    }

    public UrgencyEpoxyModel_ clickListener(OnModelClickListener<UrgencyEpoxyModel_, UrgencyView> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public UrgencyEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public UrgencyEpoxyModel_ shouldAnimate(boolean shouldAnimate) {
        onMutation();
        this.shouldAnimate = shouldAnimate;
        return this;
    }

    public boolean shouldAnimate() {
        return this.shouldAnimate;
    }

    public UrgencyEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public UrgencyEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public UrgencyEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public UrgencyEpoxyModel_ m5746id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public UrgencyEpoxyModel_ m5751id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public UrgencyEpoxyModel_ m5747id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public UrgencyEpoxyModel_ m5748id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public UrgencyEpoxyModel_ m5750id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public UrgencyEpoxyModel_ m5749id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public UrgencyEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public UrgencyEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public UrgencyEpoxyModel_ show() {
        super.show();
        return this;
    }

    public UrgencyEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public UrgencyEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_model_urgency;
    }

    public UrgencyEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.contextualMessage = null;
        this.type = null;
        this.clickListener = null;
        this.shouldAnimate = false;
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
        if (!(o instanceof UrgencyEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        UrgencyEpoxyModel_ that = (UrgencyEpoxyModel_) o;
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
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.contextualMessage != null) {
            if (!this.contextualMessage.equals(that.contextualMessage)) {
                return false;
            }
        } else if (that.contextualMessage != null) {
            return false;
        }
        if (this.type != null) {
            if (!this.type.equals(that.type)) {
                return false;
            }
        } else if (that.type != null) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null) || this.shouldAnimate != that.shouldAnimate) {
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
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.contextualMessage != null) {
            i4 = this.contextualMessage.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.type != null) {
            i5 = this.type.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.clickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (!this.shouldAnimate) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "UrgencyEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", contextualMessage=" + this.contextualMessage + ", type=" + this.type + ", clickListener=" + this.clickListener + ", shouldAnimate=" + this.shouldAnimate + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
