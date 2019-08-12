package com.airbnb.android.places.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.places.C7627R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.AddToPlanButton;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class AddToPlanButtonEpoxyModel_ extends AddToPlanButtonEpoxyModel implements GeneratedModel<AddToPlanButton> {
    private OnModelBoundListener<AddToPlanButtonEpoxyModel_, AddToPlanButton> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<AddToPlanButtonEpoxyModel_, AddToPlanButton> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, AddToPlanButton object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(AddToPlanButton object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public AddToPlanButtonEpoxyModel_ onBind(OnModelBoundListener<AddToPlanButtonEpoxyModel_, AddToPlanButton> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(AddToPlanButton object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public AddToPlanButtonEpoxyModel_ onUnbind(OnModelUnboundListener<AddToPlanButtonEpoxyModel_, AddToPlanButton> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public AddToPlanButtonEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public AddToPlanButtonEpoxyModel_ subtitleText(CharSequence subtitleText) {
        onMutation();
        this.subtitleText = subtitleText;
        return this;
    }

    public CharSequence subtitleText() {
        return this.subtitleText;
    }

    public AddToPlanButtonEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public AddToPlanButtonEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public AddToPlanButtonEpoxyModel_ selected(boolean selected) {
        onMutation();
        this.selected = selected;
        return this;
    }

    public boolean selected() {
        return this.selected;
    }

    public AddToPlanButtonEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public AddToPlanButtonEpoxyModel_ clickListener(OnModelClickListener<AddToPlanButtonEpoxyModel_, AddToPlanButton> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public AddToPlanButtonEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public AddToPlanButtonEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public AddToPlanButtonEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public AddToPlanButtonEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public AddToPlanButtonEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public AddToPlanButtonEpoxyModel_ m6379id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public AddToPlanButtonEpoxyModel_ m6384id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public AddToPlanButtonEpoxyModel_ m6380id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public AddToPlanButtonEpoxyModel_ m6381id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public AddToPlanButtonEpoxyModel_ m6383id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public AddToPlanButtonEpoxyModel_ m6382id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public AddToPlanButtonEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public AddToPlanButtonEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public AddToPlanButtonEpoxyModel_ show() {
        super.show();
        return this;
    }

    public AddToPlanButtonEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public AddToPlanButtonEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.n2_view_holder_add_to_plan_button;
    }

    public AddToPlanButtonEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleText = null;
        this.subtitleText = null;
        this.titleRes = 0;
        this.subtitleRes = 0;
        this.selected = false;
        this.enabled = false;
        this.clickListener = null;
        this.displayOptions = null;
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
        if (!(o instanceof AddToPlanButtonEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        AddToPlanButtonEpoxyModel_ that = (AddToPlanButtonEpoxyModel_) o;
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
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.subtitleText != null) {
            if (!this.subtitleText.equals(that.subtitleText)) {
                return false;
            }
        } else if (that.subtitleText != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.subtitleRes != that.subtitleRes || this.selected != that.selected || this.enabled != that.enabled) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null)) {
            return false;
        }
        if (this.displayOptions == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.displayOptions == null)) {
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
        if (this.titleText != null) {
            i2 = this.titleText.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.subtitleText != null) {
            i3 = this.subtitleText.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (((((i11 + i3) * 31) + this.titleRes) * 31) + this.subtitleRes) * 31;
        if (this.selected) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.enabled) {
            i5 = 1;
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
        if (this.displayOptions == null) {
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
        return "AddToPlanButtonEpoxyModel_{titleText=" + this.titleText + ", subtitleText=" + this.subtitleText + ", titleRes=" + this.titleRes + ", subtitleRes=" + this.subtitleRes + ", selected=" + this.selected + ", enabled=" + this.enabled + ", clickListener=" + this.clickListener + ", displayOptions=" + this.displayOptions + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
