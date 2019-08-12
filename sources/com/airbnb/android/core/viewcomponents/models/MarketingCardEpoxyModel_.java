package com.airbnb.android.core.viewcomponents.models;

import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.MarketingCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class MarketingCardEpoxyModel_ extends MarketingCardEpoxyModel implements GeneratedModel<MarketingCard> {
    private OnModelBoundListener<MarketingCardEpoxyModel_, MarketingCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MarketingCardEpoxyModel_, MarketingCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MarketingCard object, int position) {
        if (this.actionClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.actionClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(MarketingCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public MarketingCardEpoxyModel_ onBind(OnModelBoundListener<MarketingCardEpoxyModel_, MarketingCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MarketingCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public MarketingCardEpoxyModel_ onUnbind(OnModelUnboundListener<MarketingCardEpoxyModel_, MarketingCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public MarketingCardEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        super.title(title);
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public MarketingCardEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public MarketingCardEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        super.subtitle(subtitle);
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public MarketingCardEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public MarketingCardEpoxyModel_ action(CharSequence action) {
        onMutation();
        this.action = action;
        super.action(action);
        return this;
    }

    public CharSequence action() {
        return this.action;
    }

    public MarketingCardEpoxyModel_ actionRes(int actionRes) {
        onMutation();
        this.actionRes = actionRes;
        return this;
    }

    public int actionRes() {
        return this.actionRes;
    }

    public MarketingCardEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        super.imageUrl(imageUrl);
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public MarketingCardEpoxyModel_ imageRes(int imageRes) {
        onMutation();
        this.imageRes = imageRes;
        return this;
    }

    public int imageRes() {
        return this.imageRes;
    }

    public MarketingCardEpoxyModel_ image(Drawable image) {
        onMutation();
        this.image = image;
        super.image(image);
        return this;
    }

    public Drawable image() {
        return this.image;
    }

    public MarketingCardEpoxyModel_ actionClickListener(OnModelClickListener<MarketingCardEpoxyModel_, MarketingCard> actionClickListener) {
        onMutation();
        if (actionClickListener == null) {
            this.actionClickListener = null;
        } else {
            this.actionClickListener = new WrappedEpoxyModelClickListener(this, actionClickListener);
        }
        return this;
    }

    public MarketingCardEpoxyModel_ actionClickListener(OnClickListener actionClickListener) {
        onMutation();
        this.actionClickListener = actionClickListener;
        return this;
    }

    public OnClickListener actionClickListener() {
        return this.actionClickListener;
    }

    public MarketingCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MarketingCardEpoxyModel_ title(int titleRes) {
        super.title(titleRes);
        return this;
    }

    public MarketingCardEpoxyModel_ subtitle(int subtitleRes) {
        super.subtitle(subtitleRes);
        return this;
    }

    public MarketingCardEpoxyModel_ action(int actionRes) {
        super.action(actionRes);
        return this;
    }

    public MarketingCardEpoxyModel_ image(int imageRes) {
        super.image(imageRes);
        return this;
    }

    public MarketingCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MarketingCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MarketingCardEpoxyModel_ m5134id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MarketingCardEpoxyModel_ m5139id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MarketingCardEpoxyModel_ m5135id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MarketingCardEpoxyModel_ m5136id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MarketingCardEpoxyModel_ m5138id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MarketingCardEpoxyModel_ m5137id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MarketingCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MarketingCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MarketingCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public MarketingCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MarketingCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_marketing_card;
    }

    public MarketingCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.action = null;
        this.actionRes = 0;
        this.imageUrl = null;
        this.imageRes = 0;
        this.image = null;
        this.actionClickListener = null;
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
        if (!(o instanceof MarketingCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        MarketingCardEpoxyModel_ that = (MarketingCardEpoxyModel_) o;
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
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.subtitleRes != that.subtitleRes) {
            return false;
        }
        if (this.action != null) {
            if (!this.action.equals(that.action)) {
                return false;
            }
        } else if (that.action != null) {
            return false;
        }
        if (this.actionRes != that.actionRes) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.imageRes != that.imageRes) {
            return false;
        }
        if (this.image != null) {
            if (!this.image.equals(that.image)) {
                return false;
            }
        } else if (that.image != null) {
            return false;
        }
        if (this.actionClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.actionClickListener == null)) {
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
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (((i10 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (((i11 + i3) * 31) + this.subtitleRes) * 31;
        if (this.action != null) {
            i4 = this.action.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (((i12 + i4) * 31) + this.actionRes) * 31;
        if (this.imageUrl != null) {
            i5 = this.imageUrl.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (((i13 + i5) * 31) + this.imageRes) * 31;
        if (this.image != null) {
            i6 = this.image.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.actionClickListener == null) {
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
        return "MarketingCardEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", action=" + this.action + ", actionRes=" + this.actionRes + ", imageUrl=" + this.imageUrl + ", imageRes=" + this.imageRes + ", image=" + this.image + ", actionClickListener=" + this.actionClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
