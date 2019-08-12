package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.MapInterstitial;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.utils.MapOptions;

public class MapInterstitialEpoxyModel_ extends MapInterstitialEpoxyModel implements GeneratedModel<MapInterstitial> {
    private OnModelBoundListener<MapInterstitialEpoxyModel_, MapInterstitial> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MapInterstitialEpoxyModel_, MapInterstitial> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MapInterstitial object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(MapInterstitial object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public MapInterstitialEpoxyModel_ onBind(OnModelBoundListener<MapInterstitialEpoxyModel_, MapInterstitial> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MapInterstitial object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public MapInterstitialEpoxyModel_ onUnbind(OnModelUnboundListener<MapInterstitialEpoxyModel_, MapInterstitial> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public MapInterstitialEpoxyModel_ clickListener(OnModelClickListener<MapInterstitialEpoxyModel_, MapInterstitial> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public MapInterstitialEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public MapInterstitialEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public MapInterstitialEpoxyModel_ subtitle(String subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public String subtitle() {
        return this.subtitle;
    }

    public MapInterstitialEpoxyModel_ mapOptions(MapOptions mapOptions) {
        onMutation();
        this.mapOptions = mapOptions;
        return this;
    }

    public MapOptions mapOptions() {
        return this.mapOptions;
    }

    public MapInterstitialEpoxyModel_ hideText(boolean hideText) {
        onMutation();
        this.hideText = hideText;
        return this;
    }

    public boolean hideText() {
        return this.hideText;
    }

    public MapInterstitialEpoxyModel_ shortCard(boolean shortCard) {
        onMutation();
        this.shortCard = shortCard;
        return this;
    }

    public boolean shortCard() {
        return this.shortCard;
    }

    public MapInterstitialEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MapInterstitialEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MapInterstitialEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MapInterstitialEpoxyModel_ m5122id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MapInterstitialEpoxyModel_ m5127id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MapInterstitialEpoxyModel_ m5123id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MapInterstitialEpoxyModel_ m5124id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MapInterstitialEpoxyModel_ m5126id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MapInterstitialEpoxyModel_ m5125id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MapInterstitialEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MapInterstitialEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MapInterstitialEpoxyModel_ show() {
        super.show();
        return this;
    }

    public MapInterstitialEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MapInterstitialEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public MapInterstitialEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.clickListener = null;
        this.title = null;
        this.subtitle = null;
        this.mapOptions = null;
        this.hideText = false;
        this.shortCard = false;
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
        if (!(o instanceof MapInterstitialEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        MapInterstitialEpoxyModel_ that = (MapInterstitialEpoxyModel_) o;
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
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.mapOptions != null) {
            if (!this.mapOptions.equals(that.mapOptions)) {
                return false;
            }
        } else if (that.mapOptions != null) {
            return false;
        }
        if (this.hideText != that.hideText || this.shortCard != that.shortCard) {
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
        if (this.clickListener != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.title != null) {
            i3 = this.title.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.subtitle != null) {
            i4 = this.subtitle.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.mapOptions != null) {
            i5 = this.mapOptions.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.hideText) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (!this.shortCard) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
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
        return "MapInterstitialEpoxyModel_{clickListener=" + this.clickListener + ", title=" + this.title + ", subtitle=" + this.subtitle + ", mapOptions=" + this.mapOptions + ", hideText=" + this.hideText + ", shortCard=" + this.shortCard + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
