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
import com.airbnb.p027n2.components.ButtonBar;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ButtonBarEpoxyModel_ extends ButtonBarEpoxyModel implements GeneratedModel<ButtonBar> {
    private OnModelBoundListener<ButtonBarEpoxyModel_, ButtonBar> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ButtonBarEpoxyModel_, ButtonBar> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ButtonBar object, int position) {
        if (this.listener1 instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.listener1).bind(holder, object);
        }
        if (this.listener2 instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.listener2).bind(holder, object);
        }
        if (this.listener3 instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.listener3).bind(holder, object);
        }
        if (this.listener4 instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.listener4).bind(holder, object);
        }
    }

    public void handlePostBind(ButtonBar object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ButtonBarEpoxyModel_ onBind(OnModelBoundListener<ButtonBarEpoxyModel_, ButtonBar> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ButtonBar object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ButtonBarEpoxyModel_ onUnbind(OnModelUnboundListener<ButtonBarEpoxyModel_, ButtonBar> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ButtonBarEpoxyModel_ numberOfButtons(int numberOfButtons) {
        onMutation();
        this.numberOfButtons = numberOfButtons;
        return this;
    }

    public int numberOfButtons() {
        return this.numberOfButtons;
    }

    public ButtonBarEpoxyModel_ label1(int label1) {
        onMutation();
        this.label1 = label1;
        return this;
    }

    public int label1() {
        return this.label1;
    }

    public ButtonBarEpoxyModel_ label2(int label2) {
        onMutation();
        this.label2 = label2;
        return this;
    }

    public int label2() {
        return this.label2;
    }

    public ButtonBarEpoxyModel_ label3(int label3) {
        onMutation();
        this.label3 = label3;
        return this;
    }

    public int label3() {
        return this.label3;
    }

    public ButtonBarEpoxyModel_ label4(int label4) {
        onMutation();
        this.label4 = label4;
        return this;
    }

    public int label4() {
        return this.label4;
    }

    public ButtonBarEpoxyModel_ icon1(int icon1) {
        onMutation();
        this.icon1 = icon1;
        return this;
    }

    public int icon1() {
        return this.icon1;
    }

    public ButtonBarEpoxyModel_ icon2(int icon2) {
        onMutation();
        this.icon2 = icon2;
        return this;
    }

    public int icon2() {
        return this.icon2;
    }

    public ButtonBarEpoxyModel_ icon3(int icon3) {
        onMutation();
        this.icon3 = icon3;
        return this;
    }

    public int icon3() {
        return this.icon3;
    }

    public ButtonBarEpoxyModel_ icon4(int icon4) {
        onMutation();
        this.icon4 = icon4;
        return this;
    }

    public int icon4() {
        return this.icon4;
    }

    public ButtonBarEpoxyModel_ listener1(OnModelClickListener<ButtonBarEpoxyModel_, ButtonBar> listener1) {
        onMutation();
        if (listener1 == null) {
            this.listener1 = null;
        } else {
            this.listener1 = new WrappedEpoxyModelClickListener(this, listener1);
        }
        return this;
    }

    public ButtonBarEpoxyModel_ listener1(OnClickListener listener1) {
        onMutation();
        this.listener1 = listener1;
        return this;
    }

    public OnClickListener listener1() {
        return this.listener1;
    }

    public ButtonBarEpoxyModel_ listener2(OnModelClickListener<ButtonBarEpoxyModel_, ButtonBar> listener2) {
        onMutation();
        if (listener2 == null) {
            this.listener2 = null;
        } else {
            this.listener2 = new WrappedEpoxyModelClickListener(this, listener2);
        }
        return this;
    }

    public ButtonBarEpoxyModel_ listener2(OnClickListener listener2) {
        onMutation();
        this.listener2 = listener2;
        return this;
    }

    public OnClickListener listener2() {
        return this.listener2;
    }

    public ButtonBarEpoxyModel_ listener3(OnModelClickListener<ButtonBarEpoxyModel_, ButtonBar> listener3) {
        onMutation();
        if (listener3 == null) {
            this.listener3 = null;
        } else {
            this.listener3 = new WrappedEpoxyModelClickListener(this, listener3);
        }
        return this;
    }

    public ButtonBarEpoxyModel_ listener3(OnClickListener listener3) {
        onMutation();
        this.listener3 = listener3;
        return this;
    }

    public OnClickListener listener3() {
        return this.listener3;
    }

    public ButtonBarEpoxyModel_ listener4(OnModelClickListener<ButtonBarEpoxyModel_, ButtonBar> listener4) {
        onMutation();
        if (listener4 == null) {
            this.listener4 = null;
        } else {
            this.listener4 = new WrappedEpoxyModelClickListener(this, listener4);
        }
        return this;
    }

    public ButtonBarEpoxyModel_ listener4(OnClickListener listener4) {
        onMutation();
        this.listener4 = listener4;
        return this;
    }

    public OnClickListener listener4() {
        return this.listener4;
    }

    public ButtonBarEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ButtonBarEpoxyModel_ addButton(int label, int icon, OnClickListener listener) {
        super.addButton(label, icon, listener);
        return this;
    }

    public ButtonBarEpoxyModel_ clearButtons() {
        super.clearButtons();
        return this;
    }

    public ButtonBarEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ButtonBarEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ButtonBarEpoxyModel_ m4411id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ButtonBarEpoxyModel_ m4416id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ButtonBarEpoxyModel_ m4412id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ButtonBarEpoxyModel_ m4413id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ButtonBarEpoxyModel_ m4415id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ButtonBarEpoxyModel_ m4414id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ButtonBarEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ButtonBarEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ButtonBarEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ButtonBarEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ButtonBarEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_button_bar;
    }

    public ButtonBarEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.numberOfButtons = 0;
        this.label1 = 0;
        this.label2 = 0;
        this.label3 = 0;
        this.label4 = 0;
        this.icon1 = 0;
        this.icon2 = 0;
        this.icon3 = 0;
        this.icon4 = 0;
        this.listener1 = null;
        this.listener2 = null;
        this.listener3 = null;
        this.listener4 = null;
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
        boolean z5;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ButtonBarEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ButtonBarEpoxyModel_ that = (ButtonBarEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (that.onModelUnboundListener_epoxyGeneratedModel == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z != z2 || this.numberOfButtons != that.numberOfButtons || this.label1 != that.label1 || this.label2 != that.label2 || this.label3 != that.label3 || this.label4 != that.label4 || this.icon1 != that.icon1 || this.icon2 != that.icon2 || this.icon3 != that.icon3 || this.icon4 != that.icon4) {
            return false;
        }
        if ((this.listener1 == null) != (that.listener1 == null)) {
            return false;
        }
        if (this.listener2 == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.listener2 == null)) {
            return false;
        }
        if (this.listener3 == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.listener3 == null)) {
            return false;
        }
        if (this.listener4 == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.listener4 == null)) {
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
        int i8 = (((((((((((((((((((hashCode + i) * 31) + this.numberOfButtons) * 31) + this.label1) * 31) + this.label2) * 31) + this.label3) * 31) + this.label4) * 31) + this.icon1) * 31) + this.icon2) * 31) + this.icon3) * 31) + this.icon4) * 31;
        if (this.listener1 != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.listener2 != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.listener3 != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.listener4 == null) {
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
        return "ButtonBarEpoxyModel_{numberOfButtons=" + this.numberOfButtons + ", label1=" + this.label1 + ", label2=" + this.label2 + ", label3=" + this.label3 + ", label4=" + this.label4 + ", icon1=" + this.icon1 + ", icon2=" + this.icon2 + ", icon3=" + this.icon3 + ", icon4=" + this.icon4 + ", listener1=" + this.listener1 + ", listener2=" + this.listener2 + ", listener3=" + this.listener3 + ", listener4=" + this.listener4 + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
