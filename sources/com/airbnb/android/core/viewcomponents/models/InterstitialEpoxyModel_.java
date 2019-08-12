package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.ExplorePromotionStyle;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.Interstitial;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InterstitialEpoxyModel_ extends InterstitialEpoxyModel implements GeneratedModel<Interstitial> {
    private OnModelBoundListener<InterstitialEpoxyModel_, Interstitial> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InterstitialEpoxyModel_, Interstitial> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, Interstitial object, int position) {
        if (this.buttonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.buttonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(Interstitial object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InterstitialEpoxyModel_ onBind(OnModelBoundListener<InterstitialEpoxyModel_, Interstitial> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(Interstitial object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InterstitialEpoxyModel_ onUnbind(OnModelUnboundListener<InterstitialEpoxyModel_, Interstitial> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InterstitialEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public InterstitialEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public InterstitialEpoxyModel_ caption(CharSequence caption) {
        onMutation();
        this.caption = caption;
        return this;
    }

    public CharSequence caption() {
        return this.caption;
    }

    public InterstitialEpoxyModel_ captionRes(int captionRes) {
        onMutation();
        this.captionRes = captionRes;
        return this;
    }

    public int captionRes() {
        return this.captionRes;
    }

    public InterstitialEpoxyModel_ buttonText(CharSequence buttonText) {
        onMutation();
        this.buttonText = buttonText;
        return this;
    }

    public CharSequence buttonText() {
        return this.buttonText;
    }

    public InterstitialEpoxyModel_ buttonTextRes(int buttonTextRes) {
        onMutation();
        this.buttonTextRes = buttonTextRes;
        return this;
    }

    public int buttonTextRes() {
        return this.buttonTextRes;
    }

    public InterstitialEpoxyModel_ buttonClickListener(OnModelClickListener<InterstitialEpoxyModel_, Interstitial> buttonClickListener) {
        onMutation();
        if (buttonClickListener == null) {
            this.buttonClickListener = null;
        } else {
            this.buttonClickListener = new WrappedEpoxyModelClickListener(this, buttonClickListener);
        }
        return this;
    }

    public InterstitialEpoxyModel_ buttonClickListener(OnClickListener buttonClickListener) {
        onMutation();
        this.buttonClickListener = buttonClickListener;
        return this;
    }

    public OnClickListener buttonClickListener() {
        return this.buttonClickListener;
    }

    public InterstitialEpoxyModel_ jellyFishPallete(int jellyFishPallete) {
        onMutation();
        this.jellyFishPallete = jellyFishPallete;
        return this;
    }

    public int jellyFishPallete() {
        return this.jellyFishPallete;
    }

    public InterstitialEpoxyModel_ withPadding(boolean withPadding) {
        onMutation();
        this.withPadding = withPadding;
        return this;
    }

    public boolean withPadding() {
        return this.withPadding;
    }

    public InterstitialEpoxyModel_ style(ExplorePromotionStyle style) {
        onMutation();
        this.style = style;
        return this;
    }

    public ExplorePromotionStyle style() {
        return this.style;
    }

    public InterstitialEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InterstitialEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InterstitialEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InterstitialEpoxyModel_ m4966id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InterstitialEpoxyModel_ m4971id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InterstitialEpoxyModel_ m4967id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InterstitialEpoxyModel_ m4968id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InterstitialEpoxyModel_ m4970id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InterstitialEpoxyModel_ m4969id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InterstitialEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InterstitialEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InterstitialEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InterstitialEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InterstitialEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_interstitial;
    }

    public InterstitialEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.text = null;
        this.textRes = 0;
        this.caption = null;
        this.captionRes = 0;
        this.buttonText = null;
        this.buttonTextRes = 0;
        this.buttonClickListener = null;
        this.jellyFishPallete = 0;
        this.withPadding = false;
        this.style = null;
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
        if (!(o instanceof InterstitialEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InterstitialEpoxyModel_ that = (InterstitialEpoxyModel_) o;
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
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.textRes != that.textRes) {
            return false;
        }
        if (this.caption != null) {
            if (!this.caption.equals(that.caption)) {
                return false;
            }
        } else if (that.caption != null) {
            return false;
        }
        if (this.captionRes != that.captionRes) {
            return false;
        }
        if (this.buttonText != null) {
            if (!this.buttonText.equals(that.buttonText)) {
                return false;
            }
        } else if (that.buttonText != null) {
            return false;
        }
        if (this.buttonTextRes != that.buttonTextRes) {
            return false;
        }
        if (this.buttonClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.buttonClickListener == null) || this.jellyFishPallete != that.jellyFishPallete || this.withPadding != that.withPadding) {
            return false;
        }
        if (this.style != null) {
            if (!this.style.equals(that.style)) {
                return false;
            }
        } else if (that.style != null) {
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
        if (this.text != null) {
            i2 = this.text.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (((i10 + i2) * 31) + this.textRes) * 31;
        if (this.caption != null) {
            i3 = this.caption.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (((i11 + i3) * 31) + this.captionRes) * 31;
        if (this.buttonText != null) {
            i4 = this.buttonText.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (((i12 + i4) * 31) + this.buttonTextRes) * 31;
        if (this.buttonClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i14 = (((i13 + i5) * 31) + this.jellyFishPallete) * 31;
        if (!this.withPadding) {
            i8 = 0;
        }
        int i15 = (i14 + i8) * 31;
        if (this.style != null) {
            i6 = this.style.hashCode();
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
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
        return "InterstitialEpoxyModel_{text=" + this.text + ", textRes=" + this.textRes + ", caption=" + this.caption + ", captionRes=" + this.captionRes + ", buttonText=" + this.buttonText + ", buttonTextRes=" + this.buttonTextRes + ", buttonClickListener=" + this.buttonClickListener + ", jellyFishPallete=" + this.jellyFishPallete + ", withPadding=" + this.withPadding + ", style=" + this.style + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
