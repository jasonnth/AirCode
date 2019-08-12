package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.MosaicCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class MosaicCardEpoxyModel_ extends MosaicCardEpoxyModel implements GeneratedModel<MosaicCard> {
    private OnModelBoundListener<MosaicCardEpoxyModel_, MosaicCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MosaicCardEpoxyModel_, MosaicCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MosaicCard object, int position) {
        if (this.cardClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.cardClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(MosaicCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public MosaicCardEpoxyModel_ onBind(OnModelBoundListener<MosaicCardEpoxyModel_, MosaicCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MosaicCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public MosaicCardEpoxyModel_ onUnbind(OnModelUnboundListener<MosaicCardEpoxyModel_, MosaicCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public MosaicCardEpoxyModel_ photoUrls(List<String> photoUrls) {
        onMutation();
        this.photoUrls = photoUrls;
        return this;
    }

    public List<String> photoUrls() {
        return this.photoUrls;
    }

    public MosaicCardEpoxyModel_ regularText(String regularText) {
        onMutation();
        this.regularText = regularText;
        return this;
    }

    public String regularText() {
        return this.regularText;
    }

    public MosaicCardEpoxyModel_ boldText(String boldText) {
        onMutation();
        this.boldText = boldText;
        return this;
    }

    public String boldText() {
        return this.boldText;
    }

    public MosaicCardEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public MosaicCardEpoxyModel_ cardClickListener(OnModelClickListener<MosaicCardEpoxyModel_, MosaicCard> cardClickListener) {
        onMutation();
        if (cardClickListener == null) {
            this.cardClickListener = null;
        } else {
            this.cardClickListener = new WrappedEpoxyModelClickListener(this, cardClickListener);
        }
        return this;
    }

    public MosaicCardEpoxyModel_ cardClickListener(OnClickListener cardClickListener) {
        onMutation();
        this.cardClickListener = cardClickListener;
        return this;
    }

    public OnClickListener cardClickListener() {
        return this.cardClickListener;
    }

    public MosaicCardEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public MosaicCardEpoxyModel_ emptyStateDrawableRes(int emptyStateDrawableRes) {
        onMutation();
        this.emptyStateDrawableRes = emptyStateDrawableRes;
        return this;
    }

    public int emptyStateDrawableRes() {
        return this.emptyStateDrawableRes;
    }

    public MosaicCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MosaicCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MosaicCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MosaicCardEpoxyModel_ m5182id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MosaicCardEpoxyModel_ m5187id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MosaicCardEpoxyModel_ m5183id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MosaicCardEpoxyModel_ m5184id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MosaicCardEpoxyModel_ m5186id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MosaicCardEpoxyModel_ m5185id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MosaicCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MosaicCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MosaicCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public MosaicCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MosaicCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public MosaicCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.photoUrls = null;
        this.regularText = null;
        this.boldText = null;
        this.loading = false;
        this.cardClickListener = null;
        this.displayOptions = null;
        this.emptyStateDrawableRes = 0;
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
        if (!(o instanceof MosaicCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        MosaicCardEpoxyModel_ that = (MosaicCardEpoxyModel_) o;
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
        if (this.regularText != null) {
            if (!this.regularText.equals(that.regularText)) {
                return false;
            }
        } else if (that.regularText != null) {
            return false;
        }
        if (this.boldText != null) {
            if (!this.boldText.equals(that.boldText)) {
                return false;
            }
        } else if (that.boldText != null) {
            return false;
        }
        if (this.loading != that.loading) {
            return false;
        }
        if (this.cardClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.cardClickListener == null)) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
            return false;
        }
        if (this.emptyStateDrawableRes != that.emptyStateDrawableRes) {
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
        int i5;
        int i6;
        int i7;
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.photoUrls != null) {
            i2 = this.photoUrls.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.regularText != null) {
            i3 = this.regularText.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.boldText != null) {
            i4 = this.boldText.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.loading) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.cardClickListener == null) {
            i8 = 0;
        }
        int i15 = (i14 + i8) * 31;
        if (this.displayOptions != null) {
            i6 = this.displayOptions.hashCode();
        } else {
            i6 = 0;
        }
        int i16 = (((i15 + i6) * 31) + this.emptyStateDrawableRes) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "MosaicCardEpoxyModel_{photoUrls=" + this.photoUrls + ", regularText=" + this.regularText + ", boldText=" + this.boldText + ", loading=" + this.loading + ", cardClickListener=" + this.cardClickListener + ", displayOptions=" + this.displayOptions + ", emptyStateDrawableRes=" + this.emptyStateDrawableRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
