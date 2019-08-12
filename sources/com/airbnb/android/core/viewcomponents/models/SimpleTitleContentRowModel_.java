package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.SimpleTitleContentRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class SimpleTitleContentRowModel_ extends SimpleTitleContentRowModel implements GeneratedModel<SimpleTitleContentRow> {
    private OnModelBoundListener<SimpleTitleContentRowModel_, SimpleTitleContentRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SimpleTitleContentRowModel_, SimpleTitleContentRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SimpleTitleContentRow object, int position) {
    }

    public void handlePostBind(SimpleTitleContentRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SimpleTitleContentRowModel_ onBind(OnModelBoundListener<SimpleTitleContentRowModel_, SimpleTitleContentRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SimpleTitleContentRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SimpleTitleContentRowModel_ onUnbind(OnModelUnboundListener<SimpleTitleContentRowModel_, SimpleTitleContentRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SimpleTitleContentRowModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public SimpleTitleContentRowModel_ contentText(CharSequence contentText) {
        onMutation();
        this.contentText = contentText;
        return this;
    }

    public CharSequence contentText() {
        return this.contentText;
    }

    public SimpleTitleContentRowModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public SimpleTitleContentRowModel_ contentRes(int contentRes) {
        onMutation();
        this.contentRes = contentRes;
        return this;
    }

    public int contentRes() {
        return this.contentRes;
    }

    public SimpleTitleContentRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SimpleTitleContentRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SimpleTitleContentRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SimpleTitleContentRowModel_ m5590id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SimpleTitleContentRowModel_ m5595id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SimpleTitleContentRowModel_ m5591id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SimpleTitleContentRowModel_ m5592id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SimpleTitleContentRowModel_ m5594id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SimpleTitleContentRowModel_ m5593id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SimpleTitleContentRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SimpleTitleContentRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SimpleTitleContentRowModel_ show() {
        super.show();
        return this;
    }

    public SimpleTitleContentRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SimpleTitleContentRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_simple_title_content_row;
    }

    public SimpleTitleContentRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleText = null;
        this.contentText = null;
        this.titleRes = 0;
        this.contentRes = 0;
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
        if (!(o instanceof SimpleTitleContentRowModel_) || !super.equals(o)) {
            return false;
        }
        SimpleTitleContentRowModel_ that = (SimpleTitleContentRowModel_) o;
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
        if (this.contentText != null) {
            if (!this.contentText.equals(that.contentText)) {
                return false;
            }
        } else if (that.contentText != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.contentRes != that.contentRes) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.titleText != null) {
            i = this.titleText.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.contentText != null) {
            i2 = this.contentText.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((((i7 + i2) * 31) + this.titleRes) * 31) + this.contentRes) * 31;
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
        return "SimpleTitleContentRowModel_{titleText=" + this.titleText + ", contentText=" + this.contentText + ", titleRes=" + this.titleRes + ", contentRes=" + this.contentRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
