package com.airbnb.android.insights;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.insights.InsightEpoxyModel.LoadingState;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InsightEpoxyModel_ extends InsightEpoxyModel implements GeneratedModel<InsightView> {
    private OnModelBoundListener<InsightEpoxyModel_, InsightView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InsightEpoxyModel_, InsightView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InsightView object, int position) {
        if (this.secondaryButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.secondaryButtonClickListener).bind(holder, object);
        }
        if (this.primaryButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.primaryButtonClickListener).bind(holder, object);
        }
        if (this.dismissButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.dismissButtonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InsightView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InsightEpoxyModel_ onBind(OnModelBoundListener<InsightEpoxyModel_, InsightView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InsightView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InsightEpoxyModel_ onUnbind(OnModelUnboundListener<InsightEpoxyModel_, InsightView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InsightEpoxyModel_ insight(Insight insight) {
        onMutation();
        this.insight = insight;
        return this;
    }

    public Insight insight() {
        return this.insight;
    }

    public InsightEpoxyModel_ secondaryButtonClickListener(OnModelClickListener<InsightEpoxyModel_, InsightView> secondaryButtonClickListener) {
        onMutation();
        if (secondaryButtonClickListener == null) {
            this.secondaryButtonClickListener = null;
        } else {
            this.secondaryButtonClickListener = new WrappedEpoxyModelClickListener(this, secondaryButtonClickListener);
        }
        return this;
    }

    public InsightEpoxyModel_ secondaryButtonClickListener(OnClickListener secondaryButtonClickListener) {
        onMutation();
        this.secondaryButtonClickListener = secondaryButtonClickListener;
        return this;
    }

    public OnClickListener secondaryButtonClickListener() {
        return this.secondaryButtonClickListener;
    }

    public InsightEpoxyModel_ primaryButtonClickListener(OnModelClickListener<InsightEpoxyModel_, InsightView> primaryButtonClickListener) {
        onMutation();
        if (primaryButtonClickListener == null) {
            this.primaryButtonClickListener = null;
        } else {
            this.primaryButtonClickListener = new WrappedEpoxyModelClickListener(this, primaryButtonClickListener);
        }
        return this;
    }

    public InsightEpoxyModel_ primaryButtonClickListener(OnClickListener primaryButtonClickListener) {
        onMutation();
        this.primaryButtonClickListener = primaryButtonClickListener;
        return this;
    }

    public OnClickListener primaryButtonClickListener() {
        return this.primaryButtonClickListener;
    }

    public InsightEpoxyModel_ dismissButtonClickListener(OnModelClickListener<InsightEpoxyModel_, InsightView> dismissButtonClickListener) {
        onMutation();
        if (dismissButtonClickListener == null) {
            this.dismissButtonClickListener = null;
        } else {
            this.dismissButtonClickListener = new WrappedEpoxyModelClickListener(this, dismissButtonClickListener);
        }
        return this;
    }

    public InsightEpoxyModel_ dismissButtonClickListener(OnClickListener dismissButtonClickListener) {
        onMutation();
        this.dismissButtonClickListener = dismissButtonClickListener;
        return this;
    }

    public OnClickListener dismissButtonClickListener() {
        return this.dismissButtonClickListener;
    }

    public InsightEpoxyModel_ loadingState(LoadingState loadingState) {
        onMutation();
        this.loadingState = loadingState;
        return this;
    }

    public LoadingState loadingState() {
        return this.loadingState;
    }

    public InsightEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InsightEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InsightEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InsightEpoxyModel_ m5999id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InsightEpoxyModel_ m6004id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InsightEpoxyModel_ m6000id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InsightEpoxyModel_ m6001id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InsightEpoxyModel_ m6003id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InsightEpoxyModel_ m6002id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InsightEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InsightEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InsightEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InsightEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InsightEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C6552R.layout.insight_view_holder;
    }

    public InsightEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.insight = null;
        this.secondaryButtonClickListener = null;
        this.primaryButtonClickListener = null;
        this.dismissButtonClickListener = null;
        this.loadingState = null;
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
        if (!(o instanceof InsightEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InsightEpoxyModel_ that = (InsightEpoxyModel_) o;
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
        if (this.insight != null) {
            if (!this.insight.equals(that.insight)) {
                return false;
            }
        } else if (that.insight != null) {
            return false;
        }
        if (this.secondaryButtonClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.secondaryButtonClickListener == null)) {
            return false;
        }
        if (this.primaryButtonClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.primaryButtonClickListener == null)) {
            return false;
        }
        if (this.dismissButtonClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.dismissButtonClickListener == null)) {
            return false;
        }
        if (this.loadingState != null) {
            if (!this.loadingState.equals(that.loadingState)) {
                return false;
            }
        } else if (that.loadingState != null) {
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
        if (this.insight != null) {
            i2 = this.insight.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.secondaryButtonClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.primaryButtonClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.dismissButtonClickListener == null) {
            i7 = 0;
        }
        int i13 = (i12 + i7) * 31;
        if (this.loadingState != null) {
            i5 = this.loadingState.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
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
        return "InsightEpoxyModel_{insight=" + this.insight + ", secondaryButtonClickListener=" + this.secondaryButtonClickListener + ", primaryButtonClickListener=" + this.primaryButtonClickListener + ", dismissButtonClickListener=" + this.dismissButtonClickListener + ", loadingState=" + this.loadingState + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
