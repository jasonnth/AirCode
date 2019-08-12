package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.Banner;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.collections.BannerContainer;
import com.airbnb.p027n2.collections.BannerContainer.BannerClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class BannerContainerEpoxyModel_ extends BannerContainerEpoxyModel implements GeneratedModel<BannerContainer> {
    private OnModelBoundListener<BannerContainerEpoxyModel_, BannerContainer> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<BannerContainerEpoxyModel_, BannerContainer> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, BannerContainer object, int position) {
    }

    public void handlePostBind(BannerContainer object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public BannerContainerEpoxyModel_ onBind(OnModelBoundListener<BannerContainerEpoxyModel_, BannerContainer> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(BannerContainer object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public BannerContainerEpoxyModel_ onUnbind(OnModelUnboundListener<BannerContainerEpoxyModel_, BannerContainer> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public BannerContainerEpoxyModel_ banners(List<Banner> banners) {
        onMutation();
        this.banners = banners;
        return this;
    }

    public List<Banner> banners() {
        return this.banners;
    }

    public BannerContainerEpoxyModel_ bannerClickListener(BannerClickListener bannerClickListener) {
        onMutation();
        this.bannerClickListener = bannerClickListener;
        return this;
    }

    public BannerClickListener bannerClickListener() {
        return this.bannerClickListener;
    }

    public BannerContainerEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public BannerContainerEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public BannerContainerEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public BannerContainerEpoxyModel_ m4327id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public BannerContainerEpoxyModel_ m4332id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public BannerContainerEpoxyModel_ m4328id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public BannerContainerEpoxyModel_ m4329id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public BannerContainerEpoxyModel_ m4331id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public BannerContainerEpoxyModel_ m4330id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public BannerContainerEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public BannerContainerEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public BannerContainerEpoxyModel_ show() {
        super.show();
        return this;
    }

    public BannerContainerEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public BannerContainerEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_banner_container;
    }

    public BannerContainerEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.banners = null;
        this.bannerClickListener = null;
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
        if (!(o instanceof BannerContainerEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        BannerContainerEpoxyModel_ that = (BannerContainerEpoxyModel_) o;
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
        if (this.banners != null) {
            if (!this.banners.equals(that.banners)) {
                return false;
            }
        } else if (that.banners != null) {
            return false;
        }
        if (this.bannerClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.bannerClickListener == null)) {
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
        if (this.banners != null) {
            i2 = this.banners.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.bannerClickListener == null) {
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
        return "BannerContainerEpoxyModel_{banners=" + this.banners + ", bannerClickListener=" + this.bannerClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
