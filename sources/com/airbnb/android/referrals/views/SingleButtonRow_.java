package com.airbnb.android.referrals.views;

import android.view.View.OnClickListener;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;

public class SingleButtonRow_ extends SingleButtonRow implements GeneratedModel<Holder> {
    private OnModelBoundListener<SingleButtonRow_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SingleButtonRow_, Holder> onModelUnboundListener_epoxyGeneratedModel;

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

    public SingleButtonRow_ onBind(OnModelBoundListener<SingleButtonRow_, Holder> listener) {
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

    public SingleButtonRow_ onUnbind(OnModelUnboundListener<SingleButtonRow_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SingleButtonRow_ text(String text) {
        onMutation();
        this.text = text;
        return this;
    }

    public String text() {
        return this.text;
    }

    public SingleButtonRow_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public SingleButtonRow_ clickListener(OnModelClickListener<SingleButtonRow_, Holder> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public SingleButtonRow_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public SingleButtonRow_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public SingleButtonRow_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public SingleButtonRow_ m1359id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SingleButtonRow_ m1364id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SingleButtonRow_ m1360id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SingleButtonRow_ m1361id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SingleButtonRow_ m1363id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SingleButtonRow_ m1362id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SingleButtonRow_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SingleButtonRow_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SingleButtonRow_ show() {
        super.show();
        return this;
    }

    public SingleButtonRow_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SingleButtonRow_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C1532R.layout.single_button_row;
    }

    public SingleButtonRow_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.text = null;
        this.textRes = 0;
        this.clickListener = null;
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
        if (!(o instanceof SingleButtonRow_) || !super.equals(o)) {
            return false;
        }
        SingleButtonRow_ that = (SingleButtonRow_) o;
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
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
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
        if (this.text != null) {
            i2 = this.text.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (((i5 + i2) * 31) + this.textRes) * 31;
        if (this.clickListener == null) {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "SingleButtonRow_{text=" + this.text + ", textRes=" + this.textRes + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
