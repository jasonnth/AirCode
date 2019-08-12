package com.airbnb.android.lib.tripassistant.amenities;

import android.view.View.OnClickListener;
import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PhotoModel_ extends PhotoModel implements GeneratedModel<HTPhotoView> {
    private OnModelBoundListener<PhotoModel_, HTPhotoView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PhotoModel_, HTPhotoView> onModelUnboundListener_epoxyGeneratedModel;

    public /* bridge */ /* synthetic */ void bind(HTPhotoView hTPhotoView) {
        super.bind(hTPhotoView);
    }

    public /* bridge */ /* synthetic */ boolean hasPhoto(HelpThreadPhoto helpThreadPhoto) {
        return super.hasPhoto(helpThreadPhoto);
    }

    PhotoModel_() {
    }

    public void handlePreBind(EpoxyViewHolder holder, HTPhotoView object, int position) {
        if (this.deleteListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.deleteListener).bind(holder, object);
        }
    }

    public void handlePostBind(HTPhotoView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PhotoModel_ onBind(OnModelBoundListener<PhotoModel_, HTPhotoView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HTPhotoView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PhotoModel_ onUnbind(OnModelUnboundListener<PhotoModel_, HTPhotoView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PhotoModel_ photo(HelpThreadPhoto photo) {
        onMutation();
        this.photo = photo;
        return this;
    }

    public HelpThreadPhoto photo() {
        return this.photo;
    }

    public PhotoModel_ deleteListener(OnModelClickListener<PhotoModel_, HTPhotoView> deleteListener) {
        onMutation();
        if (deleteListener == null) {
            this.deleteListener = null;
        } else {
            this.deleteListener = new WrappedEpoxyModelClickListener(this, deleteListener);
        }
        return this;
    }

    public PhotoModel_ deleteListener(OnClickListener deleteListener) {
        onMutation();
        this.deleteListener = deleteListener;
        return this;
    }

    public OnClickListener deleteListener() {
        return this.deleteListener;
    }

    public PhotoModel_ showLoader(boolean showLoader) {
        onMutation();
        this.showLoader = showLoader;
        return this;
    }

    public boolean showLoader() {
        return this.showLoader;
    }

    public PhotoModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PhotoModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PhotoModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PhotoModel_ m6083id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PhotoModel_ m6088id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PhotoModel_ m6084id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PhotoModel_ m6085id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PhotoModel_ m6087id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PhotoModel_ m6086id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PhotoModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PhotoModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PhotoModel_ show() {
        super.show();
        return this;
    }

    public PhotoModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PhotoModel_ hide() {
        super.hide();
        return this;
    }

    public PhotoModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.photo = null;
        this.deleteListener = null;
        this.showLoader = false;
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
        if (!(o instanceof PhotoModel_) || !super.equals(o)) {
            return false;
        }
        PhotoModel_ that = (PhotoModel_) o;
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
        if (this.photo != null) {
            if (!this.photo.equals(that.photo)) {
                return false;
            }
        } else if (that.photo != null) {
            return false;
        }
        if (this.deleteListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.deleteListener == null) || this.showLoader != that.showLoader) {
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
        if (this.photo != null) {
            i2 = this.photo.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.deleteListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (!this.showLoader) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
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
        return "PhotoModel_{photo=" + this.photo + ", deleteListener=" + this.deleteListener + ", showLoader=" + this.showLoader + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
