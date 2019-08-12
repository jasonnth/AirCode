package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.views.StoryCreationAddPhotoView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class StoryCreationAddPhotoEpoxyModel_ extends StoryCreationAddPhotoEpoxyModel implements GeneratedModel<StoryCreationAddPhotoView> {
    private OnModelBoundListener<StoryCreationAddPhotoEpoxyModel_, StoryCreationAddPhotoView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StoryCreationAddPhotoEpoxyModel_, StoryCreationAddPhotoView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StoryCreationAddPhotoView object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(StoryCreationAddPhotoView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StoryCreationAddPhotoEpoxyModel_ onBind(OnModelBoundListener<StoryCreationAddPhotoEpoxyModel_, StoryCreationAddPhotoView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StoryCreationAddPhotoView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StoryCreationAddPhotoEpoxyModel_ onUnbind(OnModelUnboundListener<StoryCreationAddPhotoEpoxyModel_, StoryCreationAddPhotoView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ onClickListener(OnModelClickListener<StoryCreationAddPhotoEpoxyModel_, StoryCreationAddPhotoView> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public StoryCreationAddPhotoEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StoryCreationAddPhotoEpoxyModel_ m4152id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StoryCreationAddPhotoEpoxyModel_ m4157id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StoryCreationAddPhotoEpoxyModel_ m4153id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StoryCreationAddPhotoEpoxyModel_ m4154id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StoryCreationAddPhotoEpoxyModel_ m4156id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StoryCreationAddPhotoEpoxyModel_ m4155id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StoryCreationAddPhotoEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_story_creation_add_image;
    }

    public StoryCreationAddPhotoEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.onClickListener = null;
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
        if (!(o instanceof StoryCreationAddPhotoEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StoryCreationAddPhotoEpoxyModel_ that = (StoryCreationAddPhotoEpoxyModel_) o;
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        if (this.onClickListener == null) {
            i3 = 0;
        }
        int i6 = (i5 + i3) * 31;
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
        return "StoryCreationAddPhotoEpoxyModel_{onClickListener=" + this.onClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
