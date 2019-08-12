package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class KickerMarqueeEpoxyModel_ extends KickerMarqueeEpoxyModel implements GeneratedModel<KickerMarquee> {
    private OnModelBoundListener<KickerMarqueeEpoxyModel_, KickerMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<KickerMarqueeEpoxyModel_, KickerMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, KickerMarquee object, int position) {
    }

    public void handlePostBind(KickerMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public KickerMarqueeEpoxyModel_ onBind(OnModelBoundListener<KickerMarqueeEpoxyModel_, KickerMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(KickerMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public KickerMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<KickerMarqueeEpoxyModel_, KickerMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public KickerMarqueeEpoxyModel_ titleText(String titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public String titleText() {
        return this.titleText;
    }

    public KickerMarqueeEpoxyModel_ subTitleText(String subTitleText) {
        onMutation();
        this.subTitleText = subTitleText;
        return this;
    }

    public String subTitleText() {
        return this.subTitleText;
    }

    public KickerMarqueeEpoxyModel_ kickerText(String kickerText) {
        onMutation();
        this.kickerText = kickerText;
        return this;
    }

    public String kickerText() {
        return this.kickerText;
    }

    public KickerMarqueeEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public KickerMarqueeEpoxyModel_ subTitleRes(int subTitleRes) {
        onMutation();
        this.subTitleRes = subTitleRes;
        return this;
    }

    public int subTitleRes() {
        return this.subTitleRes;
    }

    public KickerMarqueeEpoxyModel_ kickerRes(int kickerRes) {
        onMutation();
        this.kickerRes = kickerRes;
        return this;
    }

    public int kickerRes() {
        return this.kickerRes;
    }

    public KickerMarqueeEpoxyModel_ isSubtitleBold(boolean isSubtitleBold) {
        onMutation();
        this.isSubtitleBold = isSubtitleBold;
        return this;
    }

    public boolean isSubtitleBold() {
        return this.isSubtitleBold;
    }

    public KickerMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public KickerMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public KickerMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public KickerMarqueeEpoxyModel_ m4978id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public KickerMarqueeEpoxyModel_ m4983id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public KickerMarqueeEpoxyModel_ m4979id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public KickerMarqueeEpoxyModel_ m4980id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public KickerMarqueeEpoxyModel_ m4982id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public KickerMarqueeEpoxyModel_ m4981id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public KickerMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public KickerMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public KickerMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public KickerMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public KickerMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public KickerMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleText = null;
        this.subTitleText = null;
        this.kickerText = null;
        this.titleRes = 0;
        this.subTitleRes = 0;
        this.kickerRes = 0;
        this.isSubtitleBold = false;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof KickerMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        KickerMarqueeEpoxyModel_ that = (KickerMarqueeEpoxyModel_) o;
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
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.subTitleText != null) {
            if (!this.subTitleText.equals(that.subTitleText)) {
                return false;
            }
        } else if (that.subTitleText != null) {
            return false;
        }
        if (this.kickerText != null) {
            if (!this.kickerText.equals(that.kickerText)) {
                return false;
            }
        } else if (that.kickerText != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.subTitleRes != that.subTitleRes || this.kickerRes != that.kickerRes || this.isSubtitleBold != that.isSubtitleBold) {
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
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.titleText != null) {
            i2 = this.titleText.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.subTitleText != null) {
            i3 = this.subTitleText.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.kickerText != null) {
            i4 = this.kickerText.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (((((((i10 + i4) * 31) + this.titleRes) * 31) + this.subTitleRes) * 31) + this.kickerRes) * 31;
        if (!this.isSubtitleBold) {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.numCarouselItemsShown != null) {
            i7 = this.numCarouselItemsShown.hashCode();
        }
        return i13 + i7;
    }

    public String toString() {
        return "KickerMarqueeEpoxyModel_{titleText=" + this.titleText + ", subTitleText=" + this.subTitleText + ", kickerText=" + this.kickerText + ", titleRes=" + this.titleRes + ", subTitleRes=" + this.subTitleRes + ", kickerRes=" + this.kickerRes + ", isSubtitleBold=" + this.isSubtitleBold + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
