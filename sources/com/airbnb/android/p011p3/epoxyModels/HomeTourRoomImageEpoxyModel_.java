package com.airbnb.android.p011p3.epoxyModels;

import android.view.View.OnClickListener;
import com.airbnb.android.p011p3.C7532R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.primitives.imaging.Image;

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomImageEpoxyModel_ */
public class HomeTourRoomImageEpoxyModel_ extends HomeTourRoomImageEpoxyModel implements GeneratedModel<HomeTourRoomImageHolder> {
    private OnModelBoundListener<HomeTourRoomImageEpoxyModel_, HomeTourRoomImageHolder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeTourRoomImageEpoxyModel_, HomeTourRoomImageHolder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HomeTourRoomImageHolder object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HomeTourRoomImageHolder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeTourRoomImageEpoxyModel_ onBind(OnModelBoundListener<HomeTourRoomImageEpoxyModel_, HomeTourRoomImageHolder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HomeTourRoomImageHolder object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeTourRoomImageEpoxyModel_ onUnbind(OnModelUnboundListener<HomeTourRoomImageEpoxyModel_, HomeTourRoomImageHolder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeTourRoomImageEpoxyModel_ image(Image image) {
        onMutation();
        this.image = image;
        return this;
    }

    public Image image() {
        return this.image;
    }

    public HomeTourRoomImageEpoxyModel_ onClickListener(OnModelClickListener<HomeTourRoomImageEpoxyModel_, HomeTourRoomImageHolder> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public HomeTourRoomImageEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public HomeTourRoomImageEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public HomeTourRoomImageEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomImageEpoxyModel_ m6359id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomImageEpoxyModel_ m6364id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomImageEpoxyModel_ m6360id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomImageEpoxyModel_ m6361id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomImageEpoxyModel_ m6363id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomImageEpoxyModel_ m6362id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeTourRoomImageEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeTourRoomImageEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeTourRoomImageEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeTourRoomImageEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeTourRoomImageEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public HomeTourRoomImageHolder createNewHolder() {
        return new HomeTourRoomImageHolder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7532R.layout.home_tour_image_item;
    }

    public HomeTourRoomImageEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.image = null;
        this.onClickListener = null;
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
        if (!(o instanceof HomeTourRoomImageEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeTourRoomImageEpoxyModel_ that = (HomeTourRoomImageEpoxyModel_) o;
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
        if (this.image != null) {
            if (!this.image.equals(that.image)) {
                return false;
            }
        } else if (that.image != null) {
            return false;
        }
        if (this.onClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener == null)) {
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
        int i5 = (hashCode + i) * 31;
        if (this.image != null) {
            i2 = this.image.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        if (this.onClickListener == null) {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "HomeTourRoomImageEpoxyModel_{image=" + this.image + ", onClickListener=" + this.onClickListener + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
