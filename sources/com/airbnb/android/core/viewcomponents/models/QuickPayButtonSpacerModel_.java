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
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class QuickPayButtonSpacerModel_ extends QuickPayButtonSpacerModel implements GeneratedModel<FixedActionFooter> {
    private OnModelBoundListener<QuickPayButtonSpacerModel_, FixedActionFooter> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<QuickPayButtonSpacerModel_, FixedActionFooter> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, FixedActionFooter object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(FixedActionFooter object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public QuickPayButtonSpacerModel_ onBind(OnModelBoundListener<QuickPayButtonSpacerModel_, FixedActionFooter> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(FixedActionFooter object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public QuickPayButtonSpacerModel_ onUnbind(OnModelUnboundListener<QuickPayButtonSpacerModel_, FixedActionFooter> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public QuickPayButtonSpacerModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public QuickPayButtonSpacerModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public QuickPayButtonSpacerModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public QuickPayButtonSpacerModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public QuickPayButtonSpacerModel_ buttonEnabled(boolean buttonEnabled) {
        onMutation();
        this.buttonEnabled = buttonEnabled;
        return this;
    }

    public boolean buttonEnabled() {
        return this.buttonEnabled;
    }

    public QuickPayButtonSpacerModel_ clickListener(OnModelClickListener<QuickPayButtonSpacerModel_, FixedActionFooter> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public QuickPayButtonSpacerModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public QuickPayButtonSpacerModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public QuickPayButtonSpacerModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public QuickPayButtonSpacerModel_ m5350id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public QuickPayButtonSpacerModel_ m5355id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public QuickPayButtonSpacerModel_ m5351id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public QuickPayButtonSpacerModel_ m5352id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public QuickPayButtonSpacerModel_ m5354id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public QuickPayButtonSpacerModel_ m5353id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public QuickPayButtonSpacerModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public QuickPayButtonSpacerModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public QuickPayButtonSpacerModel_ show() {
        super.show();
        return this;
    }

    public QuickPayButtonSpacerModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public QuickPayButtonSpacerModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_fixed_action_footer;
    }

    public QuickPayButtonSpacerModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        this.text = null;
        this.textRes = 0;
        this.loading = false;
        this.buttonEnabled = false;
        this.clickListener = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof QuickPayButtonSpacerModel_) || !super.equals(o)) {
            return false;
        }
        QuickPayButtonSpacerModel_ that = (QuickPayButtonSpacerModel_) o;
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
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.textRes != that.textRes || this.loading != that.loading || this.buttonEnabled != that.buttonEnabled) {
            return false;
        }
        if ((this.clickListener == null) == (that.clickListener == null)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = 1;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i3 = this.numCarouselItemsShown.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.text != null) {
            i4 = this.text.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (((i10 + i4) * 31) + this.textRes) * 31;
        if (this.loading) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i12 = (i11 + i5) * 31;
        if (this.buttonEnabled) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i13 = (i12 + i6) * 31;
        if (this.clickListener == null) {
            i7 = 0;
        }
        return i13 + i7;
    }

    public String toString() {
        return "QuickPayButtonSpacerModel_{showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + ", text=" + this.text + ", textRes=" + this.textRes + ", loading=" + this.loading + ", buttonEnabled=" + this.buttonEnabled + ", clickListener=" + this.clickListener + "}" + super.toString();
    }
}
