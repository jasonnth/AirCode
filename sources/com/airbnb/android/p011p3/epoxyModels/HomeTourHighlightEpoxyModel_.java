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

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourHighlightEpoxyModel_ */
public class HomeTourHighlightEpoxyModel_ extends HomeTourHighlightEpoxyModel implements GeneratedModel<HighlightViewHolder> {
    private OnModelBoundListener<HomeTourHighlightEpoxyModel_, HighlightViewHolder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeTourHighlightEpoxyModel_, HighlightViewHolder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HighlightViewHolder object, int position) {
        if (this.onImageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onImageClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HighlightViewHolder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeTourHighlightEpoxyModel_ onBind(OnModelBoundListener<HomeTourHighlightEpoxyModel_, HighlightViewHolder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HighlightViewHolder object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeTourHighlightEpoxyModel_ onUnbind(OnModelUnboundListener<HomeTourHighlightEpoxyModel_, HighlightViewHolder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeTourHighlightEpoxyModel_ image(Image image) {
        onMutation();
        this.image = image;
        return this;
    }

    public Image image() {
        return this.image;
    }

    public HomeTourHighlightEpoxyModel_ subtitle(String subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public String subtitle() {
        return this.subtitle;
    }

    public HomeTourHighlightEpoxyModel_ onImageClickListener(OnModelClickListener<HomeTourHighlightEpoxyModel_, HighlightViewHolder> onImageClickListener) {
        onMutation();
        if (onImageClickListener == null) {
            this.onImageClickListener = null;
        } else {
            this.onImageClickListener = new WrappedEpoxyModelClickListener(this, onImageClickListener);
        }
        return this;
    }

    public HomeTourHighlightEpoxyModel_ onImageClickListener(OnClickListener onImageClickListener) {
        onMutation();
        this.onImageClickListener = onImageClickListener;
        return this;
    }

    public OnClickListener onImageClickListener() {
        return this.onImageClickListener;
    }

    public HomeTourHighlightEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public HomeTourHighlightEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public HomeTourHighlightEpoxyModel_ m6335id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeTourHighlightEpoxyModel_ m6340id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeTourHighlightEpoxyModel_ m6336id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeTourHighlightEpoxyModel_ m6337id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeTourHighlightEpoxyModel_ m6339id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeTourHighlightEpoxyModel_ m6338id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeTourHighlightEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeTourHighlightEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeTourHighlightEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeTourHighlightEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeTourHighlightEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public HighlightViewHolder createNewHolder() {
        return new HighlightViewHolder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7532R.layout.home_tour_highlight_item;
    }

    public HomeTourHighlightEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.image = null;
        this.subtitle = null;
        this.onImageClickListener = null;
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
        if (!(o instanceof HomeTourHighlightEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeTourHighlightEpoxyModel_ that = (HomeTourHighlightEpoxyModel_) o;
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
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.onImageClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onImageClickListener == null)) {
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
        if (this.image != null) {
            i2 = this.image.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i8 = (i7 + i3) * 31;
        if (this.onImageClickListener == null) {
            i4 = 0;
        }
        int i9 = (i8 + i4) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "HomeTourHighlightEpoxyModel_{image=" + this.image + ", subtitle=" + this.subtitle + ", onImageClickListener=" + this.onImageClickListener + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
