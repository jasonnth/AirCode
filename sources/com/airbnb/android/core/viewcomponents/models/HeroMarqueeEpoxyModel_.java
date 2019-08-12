package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class HeroMarqueeEpoxyModel_ extends HeroMarqueeEpoxyModel implements GeneratedModel<HeroMarquee> {
    private OnModelBoundListener<HeroMarqueeEpoxyModel_, HeroMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HeroMarqueeEpoxyModel_, HeroMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HeroMarquee object, int position) {
        if (this.firstButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.firstButtonClickListener).bind(holder, object);
        }
        if (this.secondButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.secondButtonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HeroMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HeroMarqueeEpoxyModel_ onBind(OnModelBoundListener<HeroMarqueeEpoxyModel_, HeroMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HeroMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HeroMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<HeroMarqueeEpoxyModel_, HeroMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HeroMarqueeEpoxyModel_ themeColorRes(int themeColorRes) {
        onMutation();
        this.themeColorRes = themeColorRes;
        return this;
    }

    public int themeColorRes() {
        return this.themeColorRes;
    }

    public HeroMarqueeEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public HeroMarqueeEpoxyModel_ imageRes(int imageRes) {
        onMutation();
        this.imageRes = imageRes;
        return this;
    }

    public int imageRes() {
        return this.imageRes;
    }

    public HeroMarqueeEpoxyModel_ iconDrawableRes(int iconDrawableRes) {
        onMutation();
        this.iconDrawableRes = iconDrawableRes;
        return this;
    }

    public int iconDrawableRes() {
        return this.iconDrawableRes;
    }

    public HeroMarqueeEpoxyModel_ backgroundDrawableRes(int backgroundDrawableRes) {
        onMutation();
        this.backgroundDrawableRes = backgroundDrawableRes;
        return this;
    }

    public int backgroundDrawableRes() {
        return this.backgroundDrawableRes;
    }

    public HeroMarqueeEpoxyModel_ iconUrl(String iconUrl) {
        onMutation();
        this.iconUrl = iconUrl;
        return this;
    }

    public String iconUrl() {
        return this.iconUrl;
    }

    public HeroMarqueeEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public HeroMarqueeEpoxyModel_ caption(CharSequence caption) {
        onMutation();
        this.caption = caption;
        return this;
    }

    public CharSequence caption() {
        return this.caption;
    }

    public HeroMarqueeEpoxyModel_ firstButtonTextRes(int firstButtonTextRes) {
        onMutation();
        this.firstButtonTextRes = firstButtonTextRes;
        return this;
    }

    public int firstButtonTextRes() {
        return this.firstButtonTextRes;
    }

    public HeroMarqueeEpoxyModel_ firstButtonText(CharSequence firstButtonText) {
        onMutation();
        this.firstButtonText = firstButtonText;
        return this;
    }

    public CharSequence firstButtonText() {
        return this.firstButtonText;
    }

    public HeroMarqueeEpoxyModel_ secondButtonTextRes(int secondButtonTextRes) {
        onMutation();
        this.secondButtonTextRes = secondButtonTextRes;
        return this;
    }

    public int secondButtonTextRes() {
        return this.secondButtonTextRes;
    }

    public HeroMarqueeEpoxyModel_ secondButtonText(CharSequence secondButtonText) {
        onMutation();
        this.secondButtonText = secondButtonText;
        return this;
    }

    public CharSequence secondButtonText() {
        return this.secondButtonText;
    }

    public HeroMarqueeEpoxyModel_ firstButtonClickListener(OnModelClickListener<HeroMarqueeEpoxyModel_, HeroMarquee> firstButtonClickListener) {
        onMutation();
        if (firstButtonClickListener == null) {
            this.firstButtonClickListener = null;
        } else {
            this.firstButtonClickListener = new WrappedEpoxyModelClickListener(this, firstButtonClickListener);
        }
        return this;
    }

    public HeroMarqueeEpoxyModel_ firstButtonClickListener(OnClickListener firstButtonClickListener) {
        onMutation();
        this.firstButtonClickListener = firstButtonClickListener;
        return this;
    }

    public OnClickListener firstButtonClickListener() {
        return this.firstButtonClickListener;
    }

    public HeroMarqueeEpoxyModel_ secondButtonClickListener(OnModelClickListener<HeroMarqueeEpoxyModel_, HeroMarquee> secondButtonClickListener) {
        onMutation();
        if (secondButtonClickListener == null) {
            this.secondButtonClickListener = null;
        } else {
            this.secondButtonClickListener = new WrappedEpoxyModelClickListener(this, secondButtonClickListener);
        }
        return this;
    }

    public HeroMarqueeEpoxyModel_ secondButtonClickListener(OnClickListener secondButtonClickListener) {
        onMutation();
        this.secondButtonClickListener = secondButtonClickListener;
        return this;
    }

    public OnClickListener secondButtonClickListener() {
        return this.secondButtonClickListener;
    }

    public HeroMarqueeEpoxyModel_ gradientEnabled(boolean gradientEnabled) {
        onMutation();
        this.gradientEnabled = gradientEnabled;
        return this;
    }

    public boolean gradientEnabled() {
        return this.gradientEnabled;
    }

    public HeroMarqueeEpoxyModel_ scrimEnabled(boolean scrimEnabled) {
        onMutation();
        this.scrimEnabled = scrimEnabled;
        return this;
    }

    public boolean scrimEnabled() {
        return this.scrimEnabled;
    }

    public HeroMarqueeEpoxyModel_ themeColor(int themeColor) {
        onMutation();
        this.themeColor = themeColor;
        return this;
    }

    public int themeColor() {
        return this.themeColor;
    }

    public HeroMarqueeEpoxyModel_ buttonBackgroundRes(int buttonBackgroundRes) {
        onMutation();
        this.buttonBackgroundRes = buttonBackgroundRes;
        return this;
    }

    public int buttonBackgroundRes() {
        return this.buttonBackgroundRes;
    }

    public HeroMarqueeEpoxyModel_ buttonTextColorRes(int buttonTextColorRes) {
        onMutation();
        this.buttonTextColorRes = buttonTextColorRes;
        return this;
    }

    public int buttonTextColorRes() {
        return this.buttonTextColorRes;
    }

    public HeroMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HeroMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HeroMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HeroMarqueeEpoxyModel_ m4690id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HeroMarqueeEpoxyModel_ m4695id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HeroMarqueeEpoxyModel_ m4691id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HeroMarqueeEpoxyModel_ m4692id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HeroMarqueeEpoxyModel_ m4694id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HeroMarqueeEpoxyModel_ m4693id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HeroMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HeroMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HeroMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HeroMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HeroMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_hero_marquee;
    }

    public HeroMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.themeColorRes = 0;
        this.imageUrl = null;
        this.imageRes = 0;
        this.iconDrawableRes = 0;
        this.backgroundDrawableRes = 0;
        this.iconUrl = null;
        this.title = null;
        this.caption = null;
        this.firstButtonTextRes = 0;
        this.firstButtonText = null;
        this.secondButtonTextRes = 0;
        this.secondButtonText = null;
        this.firstButtonClickListener = null;
        this.secondButtonClickListener = null;
        this.gradientEnabled = false;
        this.scrimEnabled = false;
        this.themeColor = 0;
        this.buttonBackgroundRes = 0;
        this.buttonTextColorRes = 0;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof HeroMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HeroMarqueeEpoxyModel_ that = (HeroMarqueeEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.themeColorRes != that.themeColorRes) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.imageRes != that.imageRes || this.iconDrawableRes != that.iconDrawableRes || this.backgroundDrawableRes != that.backgroundDrawableRes) {
            return false;
        }
        if (this.iconUrl != null) {
            if (!this.iconUrl.equals(that.iconUrl)) {
                return false;
            }
        } else if (that.iconUrl != null) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.caption != null) {
            if (!this.caption.equals(that.caption)) {
                return false;
            }
        } else if (that.caption != null) {
            return false;
        }
        if (this.firstButtonTextRes != that.firstButtonTextRes) {
            return false;
        }
        if (this.firstButtonText != null) {
            if (!this.firstButtonText.equals(that.firstButtonText)) {
                return false;
            }
        } else if (that.firstButtonText != null) {
            return false;
        }
        if (this.secondButtonTextRes != that.secondButtonTextRes) {
            return false;
        }
        if (this.secondButtonText != null) {
            if (!this.secondButtonText.equals(that.secondButtonText)) {
                return false;
            }
        } else if (that.secondButtonText != null) {
            return false;
        }
        if (this.firstButtonClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.firstButtonClickListener == null)) {
            return false;
        }
        if (this.secondButtonClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (that.secondButtonClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z3 != z4 || this.gradientEnabled != that.gradientEnabled || this.scrimEnabled != that.scrimEnabled || this.themeColor != that.themeColor || this.buttonBackgroundRes != that.buttonBackgroundRes || this.buttonTextColorRes != that.buttonTextColorRes) {
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
        int i8;
        int i9;
        int i10;
        int i11;
        int i12 = 1;
        int i13 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i14 = (((hashCode + i) * 31) + this.themeColorRes) * 31;
        if (this.imageUrl != null) {
            i2 = this.imageUrl.hashCode();
        } else {
            i2 = 0;
        }
        int i15 = (((((((i14 + i2) * 31) + this.imageRes) * 31) + this.iconDrawableRes) * 31) + this.backgroundDrawableRes) * 31;
        if (this.iconUrl != null) {
            i3 = this.iconUrl.hashCode();
        } else {
            i3 = 0;
        }
        int i16 = (i15 + i3) * 31;
        if (this.title != null) {
            i4 = this.title.hashCode();
        } else {
            i4 = 0;
        }
        int i17 = (i16 + i4) * 31;
        if (this.caption != null) {
            i5 = this.caption.hashCode();
        } else {
            i5 = 0;
        }
        int i18 = (((i17 + i5) * 31) + this.firstButtonTextRes) * 31;
        if (this.firstButtonText != null) {
            i6 = this.firstButtonText.hashCode();
        } else {
            i6 = 0;
        }
        int i19 = (((i18 + i6) * 31) + this.secondButtonTextRes) * 31;
        if (this.secondButtonText != null) {
            i7 = this.secondButtonText.hashCode();
        } else {
            i7 = 0;
        }
        int i20 = (i19 + i7) * 31;
        if (this.firstButtonClickListener != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i21 = (i20 + i8) * 31;
        if (this.secondButtonClickListener != null) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i22 = (i21 + i9) * 31;
        if (this.gradientEnabled) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i23 = (i22 + i10) * 31;
        if (!this.scrimEnabled) {
            i12 = 0;
        }
        int i24 = (((((((i23 + i12) * 31) + this.themeColor) * 31) + this.buttonBackgroundRes) * 31) + this.buttonTextColorRes) * 31;
        if (this.showDivider != null) {
            i11 = this.showDivider.hashCode();
        } else {
            i11 = 0;
        }
        int i25 = (i24 + i11) * 31;
        if (this.numCarouselItemsShown != null) {
            i13 = this.numCarouselItemsShown.hashCode();
        }
        return i25 + i13;
    }

    public String toString() {
        return "HeroMarqueeEpoxyModel_{themeColorRes=" + this.themeColorRes + ", imageUrl=" + this.imageUrl + ", imageRes=" + this.imageRes + ", iconDrawableRes=" + this.iconDrawableRes + ", backgroundDrawableRes=" + this.backgroundDrawableRes + ", iconUrl=" + this.iconUrl + ", title=" + this.title + ", caption=" + this.caption + ", firstButtonTextRes=" + this.firstButtonTextRes + ", firstButtonText=" + this.firstButtonText + ", secondButtonTextRes=" + this.secondButtonTextRes + ", secondButtonText=" + this.secondButtonText + ", firstButtonClickListener=" + this.firstButtonClickListener + ", secondButtonClickListener=" + this.secondButtonClickListener + ", gradientEnabled=" + this.gradientEnabled + ", scrimEnabled=" + this.scrimEnabled + ", themeColor=" + this.themeColor + ", buttonBackgroundRes=" + this.buttonBackgroundRes + ", buttonTextColorRes=" + this.buttonTextColorRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
