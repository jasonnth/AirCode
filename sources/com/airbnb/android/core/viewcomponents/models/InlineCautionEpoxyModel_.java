package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.InlineCaution;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InlineCautionEpoxyModel_ extends InlineCautionEpoxyModel implements GeneratedModel<InlineCaution> {
    private OnModelBoundListener<InlineCautionEpoxyModel_, InlineCaution> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InlineCautionEpoxyModel_, InlineCaution> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InlineCaution object, int position) {
        if (this.actionListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.actionListener).bind(holder, object);
        }
    }

    public void handlePostBind(InlineCaution object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InlineCautionEpoxyModel_ onBind(OnModelBoundListener<InlineCautionEpoxyModel_, InlineCaution> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InlineCaution object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InlineCautionEpoxyModel_ onUnbind(OnModelUnboundListener<InlineCautionEpoxyModel_, InlineCaution> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InlineCautionEpoxyModel_ contentText(String contentText) {
        onMutation();
        this.contentText = contentText;
        return this;
    }

    public String contentText() {
        return this.contentText;
    }

    public InlineCautionEpoxyModel_ actionText(String actionText) {
        onMutation();
        this.actionText = actionText;
        return this;
    }

    public String actionText() {
        return this.actionText;
    }

    public InlineCautionEpoxyModel_ actionListener(OnModelClickListener<InlineCautionEpoxyModel_, InlineCaution> actionListener) {
        onMutation();
        if (actionListener == null) {
            this.actionListener = null;
        } else {
            this.actionListener = new WrappedEpoxyModelClickListener(this, actionListener);
        }
        return this;
    }

    public InlineCautionEpoxyModel_ actionListener(OnClickListener actionListener) {
        onMutation();
        this.actionListener = actionListener;
        return this;
    }

    public OnClickListener actionListener() {
        return this.actionListener;
    }

    public InlineCautionEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InlineCautionEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InlineCautionEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InlineCautionEpoxyModel_ m4846id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InlineCautionEpoxyModel_ m4851id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InlineCautionEpoxyModel_ m4847id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InlineCautionEpoxyModel_ m4848id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InlineCautionEpoxyModel_ m4850id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InlineCautionEpoxyModel_ m4849id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InlineCautionEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InlineCautionEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InlineCautionEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InlineCautionEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InlineCautionEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_inline_caution;
    }

    public InlineCautionEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.contentText = null;
        this.actionText = null;
        this.actionListener = null;
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
        if (!(o instanceof InlineCautionEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InlineCautionEpoxyModel_ that = (InlineCautionEpoxyModel_) o;
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
        if (this.contentText != null) {
            if (!this.contentText.equals(that.contentText)) {
                return false;
            }
        } else if (that.contentText != null) {
            return false;
        }
        if (this.actionText != null) {
            if (!this.actionText.equals(that.actionText)) {
                return false;
            }
        } else if (that.actionText != null) {
            return false;
        }
        if (this.actionListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.actionListener == null)) {
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
        if (this.contentText != null) {
            i2 = this.contentText.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.actionText != null) {
            i3 = this.actionText.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.actionListener == null) {
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
        return "InlineCautionEpoxyModel_{contentText=" + this.contentText + ", actionText=" + this.actionText + ", actionListener=" + this.actionListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
