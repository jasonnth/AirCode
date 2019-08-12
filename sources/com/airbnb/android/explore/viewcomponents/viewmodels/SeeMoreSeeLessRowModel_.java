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
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class SeeMoreSeeLessRowModel_ extends SeeMoreSeeLessRowModel implements GeneratedModel<LinkActionRow> {
    private OnModelBoundListener<SeeMoreSeeLessRowModel_, LinkActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SeeMoreSeeLessRowModel_, LinkActionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, LinkActionRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(LinkActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SeeMoreSeeLessRowModel_ onBind(OnModelBoundListener<SeeMoreSeeLessRowModel_, LinkActionRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(LinkActionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SeeMoreSeeLessRowModel_ onUnbind(OnModelUnboundListener<SeeMoreSeeLessRowModel_, LinkActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SeeMoreSeeLessRowModel_ expanded(boolean expanded) {
        onMutation();
        this.expanded = expanded;
        return this;
    }

    public boolean expanded() {
        return this.expanded;
    }

    public SeeMoreSeeLessRowModel_ expandedText(int expandedText) {
        onMutation();
        this.expandedText = expandedText;
        return this;
    }

    public int expandedText() {
        return this.expandedText;
    }

    public SeeMoreSeeLessRowModel_ collapsedText(int collapsedText) {
        onMutation();
        this.collapsedText = collapsedText;
        return this;
    }

    public int collapsedText() {
        return this.collapsedText;
    }

    public SeeMoreSeeLessRowModel_ clickListener(OnModelClickListener<SeeMoreSeeLessRowModel_, LinkActionRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public SeeMoreSeeLessRowModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public SeeMoreSeeLessRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SeeMoreSeeLessRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SeeMoreSeeLessRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SeeMoreSeeLessRowModel_ m5975id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SeeMoreSeeLessRowModel_ m5980id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SeeMoreSeeLessRowModel_ m5976id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SeeMoreSeeLessRowModel_ m5977id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SeeMoreSeeLessRowModel_ m5979id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SeeMoreSeeLessRowModel_ m5978id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SeeMoreSeeLessRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SeeMoreSeeLessRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SeeMoreSeeLessRowModel_ show() {
        super.show();
        return this;
    }

    public SeeMoreSeeLessRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SeeMoreSeeLessRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.n2_view_holder_link_action_row;
    }

    public SeeMoreSeeLessRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.expanded = false;
        this.expandedText = 0;
        this.collapsedText = 0;
        this.clickListener = null;
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
        if (!(o instanceof SeeMoreSeeLessRowModel_) || !super.equals(o)) {
            return false;
        }
        SeeMoreSeeLessRowModel_ that = (SeeMoreSeeLessRowModel_) o;
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
        if (z != z2 || this.expanded != that.expanded || this.expandedText != that.expandedText || this.collapsedText != that.collapsedText) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null)) {
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
        if (this.expanded) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i7 = (((((i6 + i2) * 31) + this.expandedText) * 31) + this.collapsedText) * 31;
        if (this.clickListener == null) {
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
        return "SeeMoreSeeLessRowModel_{expanded=" + this.expanded + ", expandedText=" + this.expandedText + ", collapsedText=" + this.collapsedText + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
