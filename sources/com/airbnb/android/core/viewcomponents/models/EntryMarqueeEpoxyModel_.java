package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class EntryMarqueeEpoxyModel_ extends EntryMarqueeEpoxyModel implements GeneratedModel<EntryMarquee> {
    private OnModelBoundListener<EntryMarqueeEpoxyModel_, EntryMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<EntryMarqueeEpoxyModel_, EntryMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, EntryMarquee object, int position) {
    }

    public void handlePostBind(EntryMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public EntryMarqueeEpoxyModel_ onBind(OnModelBoundListener<EntryMarqueeEpoxyModel_, EntryMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(EntryMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public EntryMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<EntryMarqueeEpoxyModel_, EntryMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public EntryMarqueeEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public EntryMarqueeEpoxyModel_ captionText(CharSequence captionText) {
        onMutation();
        this.captionText = captionText;
        return this;
    }

    public CharSequence captionText() {
        return this.captionText;
    }

    public EntryMarqueeEpoxyModel_ topPadding(boolean topPadding) {
        onMutation();
        this.topPadding = topPadding;
        return this;
    }

    public boolean topPadding() {
        return this.topPadding;
    }

    public EntryMarqueeEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public EntryMarqueeEpoxyModel_ captionRes(int captionRes) {
        onMutation();
        this.captionRes = captionRes;
        return this;
    }

    public int captionRes() {
        return this.captionRes;
    }

    public EntryMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public EntryMarqueeEpoxyModel_ title(CharSequence string) {
        super.title(string);
        return this;
    }

    public EntryMarqueeEpoxyModel_ title(int stringRes) {
        super.title(stringRes);
        return this;
    }

    public EntryMarqueeEpoxyModel_ caption(CharSequence string) {
        super.caption(string);
        return this;
    }

    public EntryMarqueeEpoxyModel_ caption(int stringRes) {
        super.caption(stringRes);
        return this;
    }

    public EntryMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public EntryMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public EntryMarqueeEpoxyModel_ m4570id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public EntryMarqueeEpoxyModel_ m4575id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public EntryMarqueeEpoxyModel_ m4571id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public EntryMarqueeEpoxyModel_ m4572id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public EntryMarqueeEpoxyModel_ m4574id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public EntryMarqueeEpoxyModel_ m4573id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public EntryMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public EntryMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public EntryMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public EntryMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public EntryMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public EntryMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleText = null;
        this.captionText = null;
        this.topPadding = false;
        this.titleRes = 0;
        this.captionRes = 0;
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
        if (!(o instanceof EntryMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        EntryMarqueeEpoxyModel_ that = (EntryMarqueeEpoxyModel_) o;
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
        if (this.captionText != null) {
            if (!this.captionText.equals(that.captionText)) {
                return false;
            }
        } else if (that.captionText != null) {
            return false;
        }
        if (this.topPadding != that.topPadding || this.titleRes != that.titleRes || this.captionRes != that.captionRes) {
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
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.titleText != null) {
            i2 = this.titleText.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.captionText != null) {
            i3 = this.captionText.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (!this.topPadding) {
            i5 = 0;
        }
        int i10 = (((((i9 + i5) * 31) + this.titleRes) * 31) + this.captionRes) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.numCarouselItemsShown != null) {
            i6 = this.numCarouselItemsShown.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "EntryMarqueeEpoxyModel_{titleText=" + this.titleText + ", captionText=" + this.captionText + ", topPadding=" + this.topPadding + ", titleRes=" + this.titleRes + ", captionRes=" + this.captionRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
