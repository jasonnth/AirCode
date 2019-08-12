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
import com.airbnb.p027n2.components.InlineTipRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InlineTipRowEpoxyModel_ extends InlineTipRowEpoxyModel implements GeneratedModel<InlineTipRow> {
    private OnModelBoundListener<InlineTipRowEpoxyModel_, InlineTipRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InlineTipRowEpoxyModel_, InlineTipRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InlineTipRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
        if (this.closeListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.closeListener).bind(holder, object);
        }
    }

    public void handlePostBind(InlineTipRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InlineTipRowEpoxyModel_ onBind(OnModelBoundListener<InlineTipRowEpoxyModel_, InlineTipRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InlineTipRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InlineTipRowEpoxyModel_ onUnbind(OnModelUnboundListener<InlineTipRowEpoxyModel_, InlineTipRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InlineTipRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InlineTipRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InlineTipRowEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public InlineTipRowEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public InlineTipRowEpoxyModel_ link(CharSequence link) {
        onMutation();
        this.link = link;
        return this;
    }

    public CharSequence link() {
        return this.link;
    }

    public InlineTipRowEpoxyModel_ linkRes(int linkRes) {
        onMutation();
        this.linkRes = linkRes;
        return this;
    }

    public int linkRes() {
        return this.linkRes;
    }

    public InlineTipRowEpoxyModel_ clickListener(OnModelClickListener<InlineTipRowEpoxyModel_, InlineTipRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public InlineTipRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public InlineTipRowEpoxyModel_ closeListener(OnModelClickListener<InlineTipRowEpoxyModel_, InlineTipRow> closeListener) {
        onMutation();
        if (closeListener == null) {
            this.closeListener = null;
        } else {
            this.closeListener = new WrappedEpoxyModelClickListener(this, closeListener);
        }
        return this;
    }

    public InlineTipRowEpoxyModel_ closeListener(OnClickListener closeListener) {
        onMutation();
        this.closeListener = closeListener;
        return this;
    }

    public OnClickListener closeListener() {
        return this.closeListener;
    }

    public InlineTipRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InlineTipRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InlineTipRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InlineTipRowEpoxyModel_ m4918id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InlineTipRowEpoxyModel_ m4923id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InlineTipRowEpoxyModel_ m4919id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InlineTipRowEpoxyModel_ m4920id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InlineTipRowEpoxyModel_ m4922id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InlineTipRowEpoxyModel_ m4921id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InlineTipRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InlineTipRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InlineTipRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InlineTipRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InlineTipRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_inline_tip_row;
    }

    public InlineTipRowEpoxyModel_ withDefaultLayout() {
        layout(C0716R.layout.n2_view_holder_inline_tip_row_default);
        return this;
    }

    public InlineTipRowEpoxyModel_ withNoTopPaddingLayout() {
        layout(C0716R.layout.n2_view_holder_inline_tip_row_no_top_padding);
        return this;
    }

    public InlineTipRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.text = null;
        this.textRes = 0;
        this.link = null;
        this.linkRes = 0;
        this.clickListener = null;
        this.closeListener = null;
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
        if (!(o instanceof InlineTipRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InlineTipRowEpoxyModel_ that = (InlineTipRowEpoxyModel_) o;
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
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.textRes != that.textRes) {
            return false;
        }
        if (this.link != null) {
            if (!this.link.equals(that.link)) {
                return false;
            }
        } else if (that.link != null) {
            return false;
        }
        if (this.linkRes != that.linkRes) {
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
        if (this.closeListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.closeListener == null)) {
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
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (((i9 + i2) * 31) + this.titleRes) * 31;
        if (this.text != null) {
            i3 = this.text.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (((i10 + i3) * 31) + this.textRes) * 31;
        if (this.link != null) {
            i4 = this.link.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (((i11 + i4) * 31) + this.linkRes) * 31;
        if (this.clickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.closeListener == null) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "InlineTipRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", text=" + this.text + ", textRes=" + this.textRes + ", link=" + this.link + ", linkRes=" + this.linkRes + ", clickListener=" + this.clickListener + ", closeListener=" + this.closeListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
