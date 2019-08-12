package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.InlineContext;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InlineContextEpoxyModel_ extends InlineContextEpoxyModel implements GeneratedModel<InlineContext> {
    private OnModelBoundListener<InlineContextEpoxyModel_, InlineContext> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InlineContextEpoxyModel_, InlineContext> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InlineContext object, int position) {
    }

    public void handlePostBind(InlineContext object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InlineContextEpoxyModel_ onBind(OnModelBoundListener<InlineContextEpoxyModel_, InlineContext> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InlineContext object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InlineContextEpoxyModel_ onUnbind(OnModelUnboundListener<InlineContextEpoxyModel_, InlineContext> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InlineContextEpoxyModel_ status(CharSequence status) {
        onMutation();
        this.status = status;
        return this;
    }

    public CharSequence status() {
        return this.status;
    }

    public InlineContextEpoxyModel_ statusDetails(CharSequence statusDetails) {
        onMutation();
        this.statusDetails = statusDetails;
        return this;
    }

    public CharSequence statusDetails() {
        return this.statusDetails;
    }

    public InlineContextEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InlineContextEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InlineContextEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InlineContextEpoxyModel_ m4858id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InlineContextEpoxyModel_ m4863id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InlineContextEpoxyModel_ m4859id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InlineContextEpoxyModel_ m4860id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InlineContextEpoxyModel_ m4862id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InlineContextEpoxyModel_ m4861id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InlineContextEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InlineContextEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InlineContextEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InlineContextEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InlineContextEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_inline_context;
    }

    public InlineContextEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.status = null;
        this.statusDetails = null;
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
        if (!(o instanceof InlineContextEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InlineContextEpoxyModel_ that = (InlineContextEpoxyModel_) o;
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
        if (this.status != null) {
            if (!this.status.equals(that.status)) {
                return false;
            }
        } else if (that.status != null) {
            return false;
        }
        if (this.statusDetails != null) {
            if (!this.statusDetails.equals(that.statusDetails)) {
                return false;
            }
        } else if (that.statusDetails != null) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.status != null) {
            i = this.status.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.statusDetails != null) {
            i2 = this.statusDetails.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
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
        return "InlineContextEpoxyModel_{status=" + this.status + ", statusDetails=" + this.statusDetails + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
