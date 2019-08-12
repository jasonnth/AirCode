package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.PromotionMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PromotionMarqueeEpoxyModel_ extends PromotionMarqueeEpoxyModel implements GeneratedModel<PromotionMarquee> {
    private OnModelBoundListener<PromotionMarqueeEpoxyModel_, PromotionMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PromotionMarqueeEpoxyModel_, PromotionMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PromotionMarquee object, int position) {
    }

    public void handlePostBind(PromotionMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PromotionMarqueeEpoxyModel_ onBind(OnModelBoundListener<PromotionMarqueeEpoxyModel_, PromotionMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PromotionMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PromotionMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<PromotionMarqueeEpoxyModel_, PromotionMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PromotionMarqueeEpoxyModel_ banner(CharSequence banner) {
        onMutation();
        this.banner = banner;
        return this;
    }

    public CharSequence banner() {
        return this.banner;
    }

    public PromotionMarqueeEpoxyModel_ bannerRes(int bannerRes) {
        onMutation();
        this.bannerRes = bannerRes;
        return this;
    }

    public int bannerRes() {
        return this.bannerRes;
    }

    public PromotionMarqueeEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public PromotionMarqueeEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public PromotionMarqueeEpoxyModel_ caption(CharSequence caption) {
        onMutation();
        this.caption = caption;
        return this;
    }

    public CharSequence caption() {
        return this.caption;
    }

    public PromotionMarqueeEpoxyModel_ captionRes(int captionRes) {
        onMutation();
        this.captionRes = captionRes;
        return this;
    }

    public int captionRes() {
        return this.captionRes;
    }

    public PromotionMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PromotionMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PromotionMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PromotionMarqueeEpoxyModel_ m5338id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PromotionMarqueeEpoxyModel_ m5343id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PromotionMarqueeEpoxyModel_ m5339id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PromotionMarqueeEpoxyModel_ m5340id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PromotionMarqueeEpoxyModel_ m5342id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PromotionMarqueeEpoxyModel_ m5341id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PromotionMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PromotionMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PromotionMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PromotionMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PromotionMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_promotion_marquee;
    }

    public PromotionMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.banner = null;
        this.bannerRes = 0;
        this.title = null;
        this.titleRes = 0;
        this.caption = null;
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
        if (!(o instanceof PromotionMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PromotionMarqueeEpoxyModel_ that = (PromotionMarqueeEpoxyModel_) o;
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
        if (this.banner != null) {
            if (!this.banner.equals(that.banner)) {
                return false;
            }
        } else if (that.banner != null) {
            return false;
        }
        if (this.bannerRes != that.bannerRes) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.caption != null) {
            if (!this.caption.equals(that.caption)) {
                return false;
            }
        } else if (that.caption != null) {
            return false;
        }
        if (this.captionRes != that.captionRes) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i5 = 0;
        }
        int i7 = (hashCode + i5) * 31;
        if (this.banner != null) {
            i = this.banner.hashCode();
        } else {
            i = 0;
        }
        int i8 = (((i7 + i) * 31) + this.bannerRes) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((i8 + i2) * 31) + this.titleRes) * 31;
        if (this.caption != null) {
            i3 = this.caption.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((i9 + i3) * 31) + this.captionRes) * 31;
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
        return "PromotionMarqueeEpoxyModel_{banner=" + this.banner + ", bannerRes=" + this.bannerRes + ", title=" + this.title + ", titleRes=" + this.titleRes + ", caption=" + this.caption + ", captionRes=" + this.captionRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
