package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.explore.C0857R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.PriceFilterButtons;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PriceFilterButtonsEpoxyModel_ extends PriceFilterButtonsEpoxyModel implements GeneratedModel<PriceFilterButtons> {
    private OnModelBoundListener<PriceFilterButtonsEpoxyModel_, PriceFilterButtons> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PriceFilterButtonsEpoxyModel_, PriceFilterButtons> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PriceFilterButtons object, int position) {
        if (this.button1ClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.button1ClickListener).bind(holder, object);
        }
        if (this.button2ClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.button2ClickListener).bind(holder, object);
        }
        if (this.button3ClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.button3ClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(PriceFilterButtons object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PriceFilterButtonsEpoxyModel_ onBind(OnModelBoundListener<PriceFilterButtonsEpoxyModel_, PriceFilterButtons> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PriceFilterButtons object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PriceFilterButtonsEpoxyModel_ onUnbind(OnModelUnboundListener<PriceFilterButtonsEpoxyModel_, PriceFilterButtons> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ selection(int selection) {
        onMutation();
        this.selection = selection;
        return this;
    }

    public int selection() {
        return this.selection;
    }

    public PriceFilterButtonsEpoxyModel_ button1ClickListener(OnModelClickListener<PriceFilterButtonsEpoxyModel_, PriceFilterButtons> button1ClickListener) {
        onMutation();
        if (button1ClickListener == null) {
            this.button1ClickListener = null;
        } else {
            this.button1ClickListener = new WrappedEpoxyModelClickListener(this, button1ClickListener);
        }
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ button1ClickListener(OnClickListener button1ClickListener) {
        onMutation();
        this.button1ClickListener = button1ClickListener;
        return this;
    }

    public OnClickListener button1ClickListener() {
        return this.button1ClickListener;
    }

    public PriceFilterButtonsEpoxyModel_ button2ClickListener(OnModelClickListener<PriceFilterButtonsEpoxyModel_, PriceFilterButtons> button2ClickListener) {
        onMutation();
        if (button2ClickListener == null) {
            this.button2ClickListener = null;
        } else {
            this.button2ClickListener = new WrappedEpoxyModelClickListener(this, button2ClickListener);
        }
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ button2ClickListener(OnClickListener button2ClickListener) {
        onMutation();
        this.button2ClickListener = button2ClickListener;
        return this;
    }

    public OnClickListener button2ClickListener() {
        return this.button2ClickListener;
    }

    public PriceFilterButtonsEpoxyModel_ button3ClickListener(OnModelClickListener<PriceFilterButtonsEpoxyModel_, PriceFilterButtons> button3ClickListener) {
        onMutation();
        if (button3ClickListener == null) {
            this.button3ClickListener = null;
        } else {
            this.button3ClickListener = new WrappedEpoxyModelClickListener(this, button3ClickListener);
        }
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ button3ClickListener(OnClickListener button3ClickListener) {
        onMutation();
        this.button3ClickListener = button3ClickListener;
        return this;
    }

    public OnClickListener button3ClickListener() {
        return this.button3ClickListener;
    }

    public PriceFilterButtonsEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PriceFilterButtonsEpoxyModel_ m5963id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PriceFilterButtonsEpoxyModel_ m5968id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PriceFilterButtonsEpoxyModel_ m5964id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PriceFilterButtonsEpoxyModel_ m5965id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PriceFilterButtonsEpoxyModel_ m5967id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PriceFilterButtonsEpoxyModel_ m5966id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PriceFilterButtonsEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.n2_view_holder_price_filter_buttons;
    }

    public PriceFilterButtonsEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.selection = 0;
        this.button1ClickListener = null;
        this.button2ClickListener = null;
        this.button3ClickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PriceFilterButtonsEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PriceFilterButtonsEpoxyModel_ that = (PriceFilterButtonsEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.selection != that.selection) {
            return false;
        }
        if (this.button1ClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.button1ClickListener == null)) {
            return false;
        }
        if (this.button2ClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.button2ClickListener == null)) {
            return false;
        }
        if (this.button3ClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.button3ClickListener == null)) {
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
        int i7 = (((hashCode + i) * 31) + this.selection) * 31;
        if (this.button1ClickListener != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.button2ClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.button3ClickListener == null) {
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
        return "PriceFilterButtonsEpoxyModel_{selection=" + this.selection + ", button1ClickListener=" + this.button1ClickListener + ", button2ClickListener=" + this.button2ClickListener + ", button3ClickListener=" + this.button3ClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
