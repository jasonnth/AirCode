package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.SuperHeroActionView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class SuperHeroActionEpoxyModel_ extends SuperHeroActionEpoxyModel implements GeneratedModel<SuperHeroActionView> {
    private OnModelBoundListener<SuperHeroActionEpoxyModel_, SuperHeroActionView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SuperHeroActionEpoxyModel_, SuperHeroActionView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SuperHeroActionView object, int position) {
        if (this.actionOnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.actionOnClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(SuperHeroActionView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SuperHeroActionEpoxyModel_ onBind(OnModelBoundListener<SuperHeroActionEpoxyModel_, SuperHeroActionView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SuperHeroActionView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SuperHeroActionEpoxyModel_ onUnbind(OnModelUnboundListener<SuperHeroActionEpoxyModel_, SuperHeroActionView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SuperHeroActionEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public SuperHeroActionEpoxyModel_ actionOnClickListener(OnModelClickListener<SuperHeroActionEpoxyModel_, SuperHeroActionView> actionOnClickListener) {
        onMutation();
        if (actionOnClickListener == null) {
            this.actionOnClickListener = null;
        } else {
            this.actionOnClickListener = new WrappedEpoxyModelClickListener(this, actionOnClickListener);
        }
        return this;
    }

    public SuperHeroActionEpoxyModel_ actionOnClickListener(OnClickListener actionOnClickListener) {
        onMutation();
        this.actionOnClickListener = actionOnClickListener;
        return this;
    }

    public OnClickListener actionOnClickListener() {
        return this.actionOnClickListener;
    }

    public SuperHeroActionEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SuperHeroActionEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SuperHeroActionEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SuperHeroActionEpoxyModel_ m5662id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SuperHeroActionEpoxyModel_ m5667id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SuperHeroActionEpoxyModel_ m5663id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SuperHeroActionEpoxyModel_ m5664id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SuperHeroActionEpoxyModel_ m5666id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SuperHeroActionEpoxyModel_ m5665id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SuperHeroActionEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SuperHeroActionEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SuperHeroActionEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SuperHeroActionEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SuperHeroActionEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_super_hero_action;
    }

    public SuperHeroActionEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.text = null;
        this.actionOnClickListener = null;
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
        if (!(o instanceof SuperHeroActionEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SuperHeroActionEpoxyModel_ that = (SuperHeroActionEpoxyModel_) o;
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
        if (this.actionOnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.actionOnClickListener == null)) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.text != null) {
            i2 = this.text.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.actionOnClickListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
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
        return "SuperHeroActionEpoxyModel_{text=" + this.text + ", actionOnClickListener=" + this.actionOnClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
