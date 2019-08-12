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
import com.airbnb.p027n2.components.DestinationCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class DestinationCardEpoxyModel_ extends DestinationCardEpoxyModel implements GeneratedModel<DestinationCard> {
    private OnModelBoundListener<DestinationCardEpoxyModel_, DestinationCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<DestinationCardEpoxyModel_, DestinationCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, DestinationCard object, int position) {
        if (this.cardClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.cardClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(DestinationCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public DestinationCardEpoxyModel_ onBind(OnModelBoundListener<DestinationCardEpoxyModel_, DestinationCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(DestinationCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public DestinationCardEpoxyModel_ onUnbind(OnModelUnboundListener<DestinationCardEpoxyModel_, DestinationCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public DestinationCardEpoxyModel_ photoUrl(String photoUrl) {
        onMutation();
        this.photoUrl = photoUrl;
        return this;
    }

    public String photoUrl() {
        return this.photoUrl;
    }

    public DestinationCardEpoxyModel_ titleText(String titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public String titleText() {
        return this.titleText;
    }

    public DestinationCardEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public DestinationCardEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public DestinationCardEpoxyModel_ cardClickListener(OnModelClickListener<DestinationCardEpoxyModel_, DestinationCard> cardClickListener) {
        onMutation();
        if (cardClickListener == null) {
            this.cardClickListener = null;
        } else {
            this.cardClickListener = new WrappedEpoxyModelClickListener(this, cardClickListener);
        }
        return this;
    }

    public DestinationCardEpoxyModel_ cardClickListener(OnClickListener cardClickListener) {
        onMutation();
        this.cardClickListener = cardClickListener;
        return this;
    }

    public OnClickListener cardClickListener() {
        return this.cardClickListener;
    }

    public DestinationCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public DestinationCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public DestinationCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public DestinationCardEpoxyModel_ m4522id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public DestinationCardEpoxyModel_ m4527id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public DestinationCardEpoxyModel_ m4523id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public DestinationCardEpoxyModel_ m4524id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public DestinationCardEpoxyModel_ m4526id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public DestinationCardEpoxyModel_ m4525id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public DestinationCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public DestinationCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public DestinationCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public DestinationCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public DestinationCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public DestinationCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.photoUrl = null;
        this.titleText = null;
        this.loading = false;
        this.displayOptions = null;
        this.cardClickListener = null;
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
        if (!(o instanceof DestinationCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        DestinationCardEpoxyModel_ that = (DestinationCardEpoxyModel_) o;
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
        if (this.photoUrl != null) {
            if (!this.photoUrl.equals(that.photoUrl)) {
                return false;
            }
        } else if (that.photoUrl != null) {
            return false;
        }
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.loading != that.loading) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
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
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.photoUrl != null) {
            i2 = this.photoUrl.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.titleText != null) {
            i3 = this.titleText.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.loading) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.displayOptions != null) {
            i5 = this.displayOptions.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.cardClickListener == null) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "DestinationCardEpoxyModel_{photoUrl=" + this.photoUrl + ", titleText=" + this.titleText + ", loading=" + this.loading + ", displayOptions=" + this.displayOptions + ", cardClickListener=" + this.cardClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
