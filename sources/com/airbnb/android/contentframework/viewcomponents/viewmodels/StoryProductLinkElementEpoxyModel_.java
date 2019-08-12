package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryProductLinkElementEpoxyModel.StoryProductLinkClickDelegate;
import com.airbnb.android.contentframework.views.StoryProductLinkElementView;
import com.airbnb.android.core.models.StoryProductLinkDetails;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class StoryProductLinkElementEpoxyModel_ extends StoryProductLinkElementEpoxyModel implements GeneratedModel<StoryProductLinkElementView> {
    private OnModelBoundListener<StoryProductLinkElementEpoxyModel_, StoryProductLinkElementView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StoryProductLinkElementEpoxyModel_, StoryProductLinkElementView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StoryProductLinkElementView object, int position) {
    }

    public void handlePostBind(StoryProductLinkElementView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StoryProductLinkElementEpoxyModel_ onBind(OnModelBoundListener<StoryProductLinkElementEpoxyModel_, StoryProductLinkElementView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StoryProductLinkElementView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StoryProductLinkElementEpoxyModel_ onUnbind(OnModelUnboundListener<StoryProductLinkElementEpoxyModel_, StoryProductLinkElementView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ delegate(StoryProductLinkClickDelegate delegate) {
        onMutation();
        this.delegate = delegate;
        return this;
    }

    public StoryProductLinkClickDelegate delegate() {
        return this.delegate;
    }

    public StoryProductLinkElementEpoxyModel_ details(StoryProductLinkDetails details) {
        onMutation();
        this.details = details;
        return this;
    }

    public StoryProductLinkDetails details() {
        return this.details;
    }

    public StoryProductLinkElementEpoxyModel_ isSubElement(boolean isSubElement) {
        onMutation();
        this.isSubElement = isSubElement;
        return this;
    }

    public boolean isSubElement() {
        return this.isSubElement;
    }

    public StoryProductLinkElementEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StoryProductLinkElementEpoxyModel_ m4212id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StoryProductLinkElementEpoxyModel_ m4217id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StoryProductLinkElementEpoxyModel_ m4213id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StoryProductLinkElementEpoxyModel_ m4214id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StoryProductLinkElementEpoxyModel_ m4216id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StoryProductLinkElementEpoxyModel_ m4215id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StoryProductLinkElementEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_story_product_link_element;
    }

    public StoryProductLinkElementEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.delegate = null;
        this.details = null;
        this.isSubElement = false;
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
        if (!(o instanceof StoryProductLinkElementEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StoryProductLinkElementEpoxyModel_ that = (StoryProductLinkElementEpoxyModel_) o;
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
        if (this.delegate == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.delegate == null)) {
            return false;
        }
        if (this.details != null) {
            if (!this.details.equals(that.details)) {
                return false;
            }
        } else if (that.details != null) {
            return false;
        }
        if (this.isSubElement != that.isSubElement) {
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
        if (this.delegate != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.details != null) {
            i3 = this.details.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (!this.isSubElement) {
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
        return "StoryProductLinkElementEpoxyModel_{delegate=" + this.delegate + ", details=" + this.details + ", isSubElement=" + this.isSubElement + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
