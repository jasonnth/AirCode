package com.airbnb.android.mythbusters;

import android.view.View.OnClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;

public class TrueFalseButtonRowEpoxyModel_ extends TrueFalseButtonRowEpoxyModel implements GeneratedModel<Holder> {
    private OnModelBoundListener<TrueFalseButtonRowEpoxyModel_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<TrueFalseButtonRowEpoxyModel_, Holder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Holder object, int position) {
        if (this.trueClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.trueClickListener).bind(holder, object);
        }
        if (this.falseClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.falseClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(Holder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public TrueFalseButtonRowEpoxyModel_ onBind(OnModelBoundListener<TrueFalseButtonRowEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(Holder object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public TrueFalseButtonRowEpoxyModel_ onUnbind(OnModelUnboundListener<TrueFalseButtonRowEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ trueClickListener(OnModelClickListener<TrueFalseButtonRowEpoxyModel_, Holder> trueClickListener) {
        onMutation();
        if (trueClickListener == null) {
            this.trueClickListener = null;
        } else {
            this.trueClickListener = new WrappedEpoxyModelClickListener(this, trueClickListener);
        }
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ trueClickListener(OnClickListener trueClickListener) {
        onMutation();
        this.trueClickListener = trueClickListener;
        return this;
    }

    public OnClickListener trueClickListener() {
        return this.trueClickListener;
    }

    public TrueFalseButtonRowEpoxyModel_ falseClickListener(OnModelClickListener<TrueFalseButtonRowEpoxyModel_, Holder> falseClickListener) {
        onMutation();
        if (falseClickListener == null) {
            this.falseClickListener = null;
        } else {
            this.falseClickListener = new WrappedEpoxyModelClickListener(this, falseClickListener);
        }
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ falseClickListener(OnClickListener falseClickListener) {
        onMutation();
        this.falseClickListener = falseClickListener;
        return this;
    }

    public OnClickListener falseClickListener() {
        return this.falseClickListener;
    }

    public TrueFalseButtonRowEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public TrueFalseButtonRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public TrueFalseButtonRowEpoxyModel_ m6299id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public TrueFalseButtonRowEpoxyModel_ m6304id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public TrueFalseButtonRowEpoxyModel_ m6300id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public TrueFalseButtonRowEpoxyModel_ m6301id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public TrueFalseButtonRowEpoxyModel_ m6303id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public TrueFalseButtonRowEpoxyModel_ m6302id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public TrueFalseButtonRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7485R.layout.true_false_button_row;
    }

    public TrueFalseButtonRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.trueClickListener = null;
        this.falseClickListener = null;
        this.showDivider = null;
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
        if (!(o instanceof TrueFalseButtonRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        TrueFalseButtonRowEpoxyModel_ that = (TrueFalseButtonRowEpoxyModel_) o;
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
        if (this.trueClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.trueClickListener == null)) {
            return false;
        }
        if (this.falseClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.falseClickListener == null)) {
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        if (this.trueClickListener != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        if (this.falseClickListener == null) {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "TrueFalseButtonRowEpoxyModel_{trueClickListener=" + this.trueClickListener + ", falseClickListener=" + this.falseClickListener + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
