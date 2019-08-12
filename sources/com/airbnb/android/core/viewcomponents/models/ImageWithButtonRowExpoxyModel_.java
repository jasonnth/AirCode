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
import com.airbnb.p027n2.components.ImageWithButtonRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ImageWithButtonRowExpoxyModel_ extends ImageWithButtonRowExpoxyModel implements GeneratedModel<ImageWithButtonRow> {
    private OnModelBoundListener<ImageWithButtonRowExpoxyModel_, ImageWithButtonRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ImageWithButtonRowExpoxyModel_, ImageWithButtonRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ImageWithButtonRow object, int position) {
        if (this.buttonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.buttonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ImageWithButtonRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ImageWithButtonRowExpoxyModel_ onBind(OnModelBoundListener<ImageWithButtonRowExpoxyModel_, ImageWithButtonRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ImageWithButtonRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ImageWithButtonRowExpoxyModel_ onUnbind(OnModelUnboundListener<ImageWithButtonRowExpoxyModel_, ImageWithButtonRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public ImageWithButtonRowExpoxyModel_ imageDrawableRes(int imageDrawableRes) {
        onMutation();
        this.imageDrawableRes = imageDrawableRes;
        return this;
    }

    public int imageDrawableRes() {
        return this.imageDrawableRes;
    }

    public ImageWithButtonRowExpoxyModel_ buttonTextRes(int buttonTextRes) {
        onMutation();
        this.buttonTextRes = buttonTextRes;
        return this;
    }

    public int buttonTextRes() {
        return this.buttonTextRes;
    }

    public ImageWithButtonRowExpoxyModel_ buttonText(CharSequence buttonText) {
        onMutation();
        this.buttonText = buttonText;
        return this;
    }

    public CharSequence buttonText() {
        return this.buttonText;
    }

    public ImageWithButtonRowExpoxyModel_ buttonClickListener(OnModelClickListener<ImageWithButtonRowExpoxyModel_, ImageWithButtonRow> buttonClickListener) {
        onMutation();
        if (buttonClickListener == null) {
            this.buttonClickListener = null;
        } else {
            this.buttonClickListener = new WrappedEpoxyModelClickListener(this, buttonClickListener);
        }
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ buttonClickListener(OnClickListener buttonClickListener) {
        onMutation();
        this.buttonClickListener = buttonClickListener;
        return this;
    }

    public OnClickListener buttonClickListener() {
        return this.buttonClickListener;
    }

    public ImageWithButtonRowExpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ImageWithButtonRowExpoxyModel_ m4786id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ImageWithButtonRowExpoxyModel_ m4791id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ImageWithButtonRowExpoxyModel_ m4787id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ImageWithButtonRowExpoxyModel_ m4788id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ImageWithButtonRowExpoxyModel_ m4790id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ImageWithButtonRowExpoxyModel_ m4789id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ show() {
        super.show();
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ImageWithButtonRowExpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_image_with_button_row;
    }

    public ImageWithButtonRowExpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.imageUrl = null;
        this.imageDrawableRes = 0;
        this.buttonTextRes = 0;
        this.buttonText = null;
        this.buttonClickListener = null;
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
        if (!(o instanceof ImageWithButtonRowExpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ImageWithButtonRowExpoxyModel_ that = (ImageWithButtonRowExpoxyModel_) o;
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
        if (this.imageDrawableRes != that.imageDrawableRes || this.buttonTextRes != that.buttonTextRes) {
            return false;
        }
        if (this.buttonText != null) {
            if (!this.buttonText.equals(that.buttonText)) {
                return false;
            }
        } else if (that.buttonText != null) {
            return false;
        }
        if (this.buttonClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.buttonClickListener == null)) {
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
        if (this.imageUrl != null) {
            i2 = this.imageUrl.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((((i7 + i2) * 31) + this.imageDrawableRes) * 31) + this.buttonTextRes) * 31;
        if (this.buttonText != null) {
            i3 = this.buttonText.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.buttonClickListener == null) {
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
        return "ImageWithButtonRowExpoxyModel_{imageUrl=" + this.imageUrl + ", imageDrawableRes=" + this.imageDrawableRes + ", buttonTextRes=" + this.buttonTextRes + ", buttonText=" + this.buttonText + ", buttonClickListener=" + this.buttonClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
