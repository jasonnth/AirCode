package com.airbnb.android.p011p3.epoxyModels;

import com.airbnb.android.p011p3.C7532R;
import com.airbnb.android.p011p3.HomeTourController;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.primitives.imaging.Image;

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourGalleryImageEpoxyModel_ */
public class HomeTourGalleryImageEpoxyModel_ extends HomeTourGalleryImageEpoxyModel implements GeneratedModel<GalleryImageHolder> {
    private OnModelBoundListener<HomeTourGalleryImageEpoxyModel_, GalleryImageHolder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeTourGalleryImageEpoxyModel_, GalleryImageHolder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, GalleryImageHolder object, int position) {
    }

    public void handlePostBind(GalleryImageHolder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeTourGalleryImageEpoxyModel_ onBind(OnModelBoundListener<HomeTourGalleryImageEpoxyModel_, GalleryImageHolder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(GalleryImageHolder object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeTourGalleryImageEpoxyModel_ onUnbind(OnModelUnboundListener<HomeTourGalleryImageEpoxyModel_, GalleryImageHolder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeTourGalleryImageEpoxyModel_ imagePosition(int imagePosition) {
        onMutation();
        this.imagePosition = imagePosition;
        return this;
    }

    public int imagePosition() {
        return this.imagePosition;
    }

    public HomeTourGalleryImageEpoxyModel_ image(Image image) {
        onMutation();
        this.image = image;
        return this;
    }

    public Image image() {
        return this.image;
    }

    public HomeTourGalleryImageEpoxyModel_ controller(HomeTourController controller) {
        onMutation();
        this.controller = controller;
        return this;
    }

    public HomeTourController controller() {
        return this.controller;
    }

    public HomeTourGalleryImageEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public HomeTourGalleryImageEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public HomeTourGalleryImageEpoxyModel_ m6323id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeTourGalleryImageEpoxyModel_ m6328id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeTourGalleryImageEpoxyModel_ m6324id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeTourGalleryImageEpoxyModel_ m6325id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeTourGalleryImageEpoxyModel_ m6327id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeTourGalleryImageEpoxyModel_ m6326id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeTourGalleryImageEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeTourGalleryImageEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeTourGalleryImageEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeTourGalleryImageEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeTourGalleryImageEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public GalleryImageHolder createNewHolder() {
        return new GalleryImageHolder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7532R.layout.home_tour_gallery_image_item;
    }

    public HomeTourGalleryImageEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.imagePosition = 0;
        this.image = null;
        this.controller = null;
        this.showDivider = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomeTourGalleryImageEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeTourGalleryImageEpoxyModel_ that = (HomeTourGalleryImageEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.imagePosition != that.imagePosition) {
            return false;
        }
        if (this.image != null) {
            if (!this.image.equals(that.image)) {
                return false;
            }
        } else if (that.image != null) {
            return false;
        }
        if (this.controller == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.controller == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i5 = (((hashCode + i) * 31) + this.imagePosition) * 31;
        if (this.image != null) {
            i2 = this.image.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        if (this.controller == null) {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "HomeTourGalleryImageEpoxyModel_{imagePosition=" + this.imagePosition + ", image=" + this.image + ", controller=" + this.controller + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
