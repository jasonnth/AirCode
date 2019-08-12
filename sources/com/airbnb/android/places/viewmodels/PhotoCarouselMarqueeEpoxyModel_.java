package com.airbnb.android.places.viewmodels;

import com.airbnb.android.places.C7627R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ScratchPhotoCarouselMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class PhotoCarouselMarqueeEpoxyModel_ extends PhotoCarouselMarqueeEpoxyModel implements GeneratedModel<ScratchPhotoCarouselMarquee> {
    private OnModelBoundListener<PhotoCarouselMarqueeEpoxyModel_, ScratchPhotoCarouselMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PhotoCarouselMarqueeEpoxyModel_, ScratchPhotoCarouselMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ScratchPhotoCarouselMarquee object, int position) {
    }

    public void handlePostBind(ScratchPhotoCarouselMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PhotoCarouselMarqueeEpoxyModel_ onBind(OnModelBoundListener<PhotoCarouselMarqueeEpoxyModel_, ScratchPhotoCarouselMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ScratchPhotoCarouselMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PhotoCarouselMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<PhotoCarouselMarqueeEpoxyModel_, ScratchPhotoCarouselMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ photoUrls(List<String> photoUrls) {
        onMutation();
        this.photoUrls = photoUrls;
        return this;
    }

    public List<String> photoUrls() {
        return this.photoUrls;
    }

    public PhotoCarouselMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PhotoCarouselMarqueeEpoxyModel_ m6403id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PhotoCarouselMarqueeEpoxyModel_ m6408id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PhotoCarouselMarqueeEpoxyModel_ m6404id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PhotoCarouselMarqueeEpoxyModel_ m6405id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PhotoCarouselMarqueeEpoxyModel_ m6407id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PhotoCarouselMarqueeEpoxyModel_ m6406id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PhotoCarouselMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.view_holder_photo_carousel;
    }

    public PhotoCarouselMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.photoUrls = null;
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
        if (!(o instanceof PhotoCarouselMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PhotoCarouselMarqueeEpoxyModel_ that = (PhotoCarouselMarqueeEpoxyModel_) o;
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
        if (this.photoUrls != null) {
            if (!this.photoUrls.equals(that.photoUrls)) {
                return false;
            }
        } else if (that.photoUrls != null) {
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
        if (this.photoUrls != null) {
            i = this.photoUrls.hashCode();
        } else {
            i = 0;
        }
        int i6 = (i5 + i) * 31;
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
        return "PhotoCarouselMarqueeEpoxyModel_{photoUrls=" + this.photoUrls + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}