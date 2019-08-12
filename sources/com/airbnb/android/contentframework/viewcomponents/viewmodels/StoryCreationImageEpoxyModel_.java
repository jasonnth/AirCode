package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnCreateContextMenuListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.views.StoryCreationImageViewWrapper;
import com.airbnb.android.contentframework.views.StoryCreationImageViewWrapper.OnOptionsSelectedListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class StoryCreationImageEpoxyModel_ extends StoryCreationImageEpoxyModel implements GeneratedModel<StoryCreationImageViewWrapper> {
    private OnModelBoundListener<StoryCreationImageEpoxyModel_, StoryCreationImageViewWrapper> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StoryCreationImageEpoxyModel_, StoryCreationImageViewWrapper> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StoryCreationImageViewWrapper object, int position) {
    }

    public void handlePostBind(StoryCreationImageViewWrapper object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StoryCreationImageEpoxyModel_ onBind(OnModelBoundListener<StoryCreationImageEpoxyModel_, StoryCreationImageViewWrapper> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StoryCreationImageViewWrapper object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StoryCreationImageEpoxyModel_ onUnbind(OnModelUnboundListener<StoryCreationImageEpoxyModel_, StoryCreationImageViewWrapper> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StoryCreationImageEpoxyModel_ image(StoryCreationImage image) {
        onMutation();
        this.image = image;
        return this;
    }

    public StoryCreationImage image() {
        return this.image;
    }

    public StoryCreationImageEpoxyModel_ onCreateContextMenuListener(OnCreateContextMenuListener onCreateContextMenuListener) {
        onMutation();
        this.onCreateContextMenuListener = onCreateContextMenuListener;
        return this;
    }

    public OnCreateContextMenuListener onCreateContextMenuListener() {
        return this.onCreateContextMenuListener;
    }

    public StoryCreationImageEpoxyModel_ onOptionsSelectedListener(OnOptionsSelectedListener onOptionsSelectedListener) {
        onMutation();
        this.onOptionsSelectedListener = onOptionsSelectedListener;
        return this;
    }

    public OnOptionsSelectedListener onOptionsSelectedListener() {
        return this.onOptionsSelectedListener;
    }

    public StoryCreationImageEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StoryCreationImageEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StoryCreationImageEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StoryCreationImageEpoxyModel_ m4164id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StoryCreationImageEpoxyModel_ m4169id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StoryCreationImageEpoxyModel_ m4165id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StoryCreationImageEpoxyModel_ m4166id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StoryCreationImageEpoxyModel_ m4168id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StoryCreationImageEpoxyModel_ m4167id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StoryCreationImageEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StoryCreationImageEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StoryCreationImageEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StoryCreationImageEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StoryCreationImageEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_story_creation_image;
    }

    public StoryCreationImageEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.image = null;
        this.onCreateContextMenuListener = null;
        this.onOptionsSelectedListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        if (o == this) {
            return true;
        }
        if (!(o instanceof StoryCreationImageEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StoryCreationImageEpoxyModel_ that = (StoryCreationImageEpoxyModel_) o;
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
        if (this.onCreateContextMenuListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onCreateContextMenuListener == null)) {
            return false;
        }
        if (this.onOptionsSelectedListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.onOptionsSelectedListener == null)) {
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
        if (this.image != null) {
            i2 = this.image.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.onCreateContextMenuListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.onOptionsSelectedListener == null) {
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
        return "StoryCreationImageEpoxyModel_{image=" + this.image + ", onCreateContextMenuListener=" + this.onCreateContextMenuListener + ", onOptionsSelectedListener=" + this.onOptionsSelectedListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
