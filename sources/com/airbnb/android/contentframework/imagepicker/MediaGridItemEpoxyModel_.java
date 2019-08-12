package com.airbnb.android.contentframework.imagepicker;

import android.net.Uri;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.imagepicker.MediaGridItemView.OnMediaItemClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class MediaGridItemEpoxyModel_ extends MediaGridItemEpoxyModel implements GeneratedModel<MediaGridItemView> {
    private OnModelBoundListener<MediaGridItemEpoxyModel_, MediaGridItemView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MediaGridItemEpoxyModel_, MediaGridItemView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MediaGridItemView object, int position) {
    }

    public void handlePostBind(MediaGridItemView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public MediaGridItemEpoxyModel_ onBind(OnModelBoundListener<MediaGridItemEpoxyModel_, MediaGridItemView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MediaGridItemView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public MediaGridItemEpoxyModel_ onUnbind(OnModelUnboundListener<MediaGridItemEpoxyModel_, MediaGridItemView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public MediaGridItemEpoxyModel_ uri(Uri uri) {
        onMutation();
        this.uri = uri;
        return this;
    }

    public Uri uri() {
        return this.uri;
    }

    public MediaGridItemEpoxyModel_ selectedItems(List<Uri> selectedItems) {
        onMutation();
        this.selectedItems = selectedItems;
        return this;
    }

    public List<Uri> selectedItems() {
        return this.selectedItems;
    }

    public MediaGridItemEpoxyModel_ onMediaItemClickListener(OnMediaItemClickListener onMediaItemClickListener) {
        onMutation();
        this.onMediaItemClickListener = onMediaItemClickListener;
        return this;
    }

    public OnMediaItemClickListener onMediaItemClickListener() {
        return this.onMediaItemClickListener;
    }

    public MediaGridItemEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MediaGridItemEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MediaGridItemEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MediaGridItemEpoxyModel_ m4068id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MediaGridItemEpoxyModel_ m4073id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MediaGridItemEpoxyModel_ m4069id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MediaGridItemEpoxyModel_ m4070id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MediaGridItemEpoxyModel_ m4072id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MediaGridItemEpoxyModel_ m4071id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MediaGridItemEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MediaGridItemEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MediaGridItemEpoxyModel_ show() {
        super.show();
        return this;
    }

    public MediaGridItemEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MediaGridItemEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_media_grid_item;
    }

    public MediaGridItemEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.uri = null;
        this.selectedItems = null;
        this.onMediaItemClickListener = null;
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
        if (!(o instanceof MediaGridItemEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        MediaGridItemEpoxyModel_ that = (MediaGridItemEpoxyModel_) o;
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
        if (this.uri != null) {
            if (!this.uri.equals(that.uri)) {
                return false;
            }
        } else if (that.uri != null) {
            return false;
        }
        if (this.selectedItems != null) {
            if (!this.selectedItems.equals(that.selectedItems)) {
                return false;
            }
        } else if (that.selectedItems != null) {
            return false;
        }
        if (this.onMediaItemClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onMediaItemClickListener == null)) {
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
        if (this.uri != null) {
            i2 = this.uri.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.selectedItems != null) {
            i3 = this.selectedItems.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.onMediaItemClickListener == null) {
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
        return "MediaGridItemEpoxyModel_{uri=" + this.uri + ", selectedItems=" + this.selectedItems + ", onMediaItemClickListener=" + this.onMediaItemClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
