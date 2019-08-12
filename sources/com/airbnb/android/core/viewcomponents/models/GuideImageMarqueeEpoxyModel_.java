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
import com.airbnb.p027n2.components.GuideImageMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class GuideImageMarqueeEpoxyModel_ extends GuideImageMarqueeEpoxyModel implements GeneratedModel<GuideImageMarquee> {
    private OnModelBoundListener<GuideImageMarqueeEpoxyModel_, GuideImageMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<GuideImageMarqueeEpoxyModel_, GuideImageMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, GuideImageMarquee object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(GuideImageMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public GuideImageMarqueeEpoxyModel_ onBind(OnModelBoundListener<GuideImageMarqueeEpoxyModel_, GuideImageMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(GuideImageMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public GuideImageMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<GuideImageMarqueeEpoxyModel_, GuideImageMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public GuideImageMarqueeEpoxyModel_ imageDrawableRes(int imageDrawableRes) {
        onMutation();
        this.imageDrawableRes = imageDrawableRes;
        return this;
    }

    public int imageDrawableRes() {
        return this.imageDrawableRes;
    }

    public GuideImageMarqueeEpoxyModel_ clickListener(OnModelClickListener<GuideImageMarqueeEpoxyModel_, GuideImageMarquee> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public GuideImageMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public GuideImageMarqueeEpoxyModel_ m4666id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public GuideImageMarqueeEpoxyModel_ m4671id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public GuideImageMarqueeEpoxyModel_ m4667id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public GuideImageMarqueeEpoxyModel_ m4668id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public GuideImageMarqueeEpoxyModel_ m4670id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public GuideImageMarqueeEpoxyModel_ m4669id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public GuideImageMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_guide_image_marquee;
    }

    public GuideImageMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.imageUrl = null;
        this.imageDrawableRes = 0;
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
        if (!(o instanceof GuideImageMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        GuideImageMarqueeEpoxyModel_ that = (GuideImageMarqueeEpoxyModel_) o;
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
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.imageDrawableRes != that.imageDrawableRes) {
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
        if (this.imageUrl != null) {
            i2 = this.imageUrl.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (((i6 + i2) * 31) + this.imageDrawableRes) * 31;
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
        return "GuideImageMarqueeEpoxyModel_{imageUrl=" + this.imageUrl + ", imageDrawableRes=" + this.imageDrawableRes + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
