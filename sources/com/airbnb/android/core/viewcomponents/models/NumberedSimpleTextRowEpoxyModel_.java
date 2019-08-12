package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.NumberedSimpleTextRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class NumberedSimpleTextRowEpoxyModel_ extends NumberedSimpleTextRowEpoxyModel implements GeneratedModel<NumberedSimpleTextRow> {
    private OnModelBoundListener<NumberedSimpleTextRowEpoxyModel_, NumberedSimpleTextRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<NumberedSimpleTextRowEpoxyModel_, NumberedSimpleTextRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, NumberedSimpleTextRow object, int position) {
    }

    public void handlePostBind(NumberedSimpleTextRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public NumberedSimpleTextRowEpoxyModel_ onBind(OnModelBoundListener<NumberedSimpleTextRowEpoxyModel_, NumberedSimpleTextRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(NumberedSimpleTextRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public NumberedSimpleTextRowEpoxyModel_ onUnbind(OnModelUnboundListener<NumberedSimpleTextRowEpoxyModel_, NumberedSimpleTextRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ content(CharSequence content) {
        onMutation();
        this.content = content;
        return this;
    }

    public CharSequence content() {
        return this.content;
    }

    public NumberedSimpleTextRowEpoxyModel_ contentRes(int contentRes) {
        onMutation();
        this.contentRes = contentRes;
        return this;
    }

    public int contentRes() {
        return this.contentRes;
    }

    public NumberedSimpleTextRowEpoxyModel_ stepNumber(int stepNumber) {
        onMutation();
        this.stepNumber = stepNumber;
        return this;
    }

    public int stepNumber() {
        return this.stepNumber;
    }

    public NumberedSimpleTextRowEpoxyModel_ stepColorRes(int stepColorRes) {
        onMutation();
        this.stepColorRes = stepColorRes;
        return this;
    }

    public int stepColorRes() {
        return this.stepColorRes;
    }

    public NumberedSimpleTextRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ tightPadding() {
        super.tightPadding();
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ paddingBottom() {
        super.paddingBottom();
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public NumberedSimpleTextRowEpoxyModel_ m5242id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public NumberedSimpleTextRowEpoxyModel_ m5247id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public NumberedSimpleTextRowEpoxyModel_ m5243id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public NumberedSimpleTextRowEpoxyModel_ m5244id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public NumberedSimpleTextRowEpoxyModel_ m5246id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public NumberedSimpleTextRowEpoxyModel_ m5245id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_numbered_simple_text_row;
    }

    public NumberedSimpleTextRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.content = null;
        this.contentRes = 0;
        this.stepNumber = 0;
        this.stepColorRes = 0;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof NumberedSimpleTextRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        NumberedSimpleTextRowEpoxyModel_ that = (NumberedSimpleTextRowEpoxyModel_) o;
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
        if (this.content != null) {
            if (!this.content.equals(that.content)) {
                return false;
            }
        } else if (that.content != null) {
            return false;
        }
        if (this.contentRes != that.contentRes || this.stepNumber != that.stepNumber || this.stepColorRes != that.stepColorRes) {
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i5 = (hashCode + i3) * 31;
        if (this.content != null) {
            i = this.content.hashCode();
        } else {
            i = 0;
        }
        int i6 = (((((((i5 + i) * 31) + this.contentRes) * 31) + this.stepNumber) * 31) + this.stepColorRes) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "NumberedSimpleTextRowEpoxyModel_{content=" + this.content + ", contentRes=" + this.contentRes + ", stepNumber=" + this.stepNumber + ", stepColorRes=" + this.stepColorRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
