package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.EditorialMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class EditorialMarqueeEpoxyModel_ extends EditorialMarqueeEpoxyModel implements GeneratedModel<EditorialMarquee> {
    private OnModelBoundListener<EditorialMarqueeEpoxyModel_, EditorialMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<EditorialMarqueeEpoxyModel_, EditorialMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, EditorialMarquee object, int position) {
    }

    public void handlePostBind(EditorialMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public EditorialMarqueeEpoxyModel_ onBind(OnModelBoundListener<EditorialMarqueeEpoxyModel_, EditorialMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(EditorialMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public EditorialMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<EditorialMarqueeEpoxyModel_, EditorialMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public EditorialMarqueeEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public EditorialMarqueeEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public EditorialMarqueeEpoxyModel_ description(CharSequence description) {
        onMutation();
        this.description = description;
        return this;
    }

    public CharSequence description() {
        return this.description;
    }

    public EditorialMarqueeEpoxyModel_ hideImage(boolean hideImage) {
        onMutation();
        this.hideImage = hideImage;
        return this;
    }

    public boolean hideImage() {
        return this.hideImage;
    }

    public EditorialMarqueeEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public EditorialMarqueeEpoxyModel_ videoUrl(String videoUrl) {
        onMutation();
        this.videoUrl = videoUrl;
        return this;
    }

    public String videoUrl() {
        return this.videoUrl;
    }

    public EditorialMarqueeEpoxyModel_ videoPosition(int videoPosition) {
        onMutation();
        this.videoPosition = videoPosition;
        return this;
    }

    public int videoPosition() {
        return this.videoPosition;
    }

    public EditorialMarqueeEpoxyModel_ kicker(String kicker) {
        onMutation();
        this.kicker = kicker;
        return this;
    }

    public String kicker() {
        return this.kicker;
    }

    public EditorialMarqueeEpoxyModel_ titleTextStyle(int titleTextStyle) {
        onMutation();
        this.titleTextStyle = titleTextStyle;
        return this;
    }

    public int titleTextStyle() {
        return this.titleTextStyle;
    }

    public EditorialMarqueeEpoxyModel_ descriptionTextStyle(int descriptionTextStyle) {
        onMutation();
        this.descriptionTextStyle = descriptionTextStyle;
        return this;
    }

    public int descriptionTextStyle() {
        return this.descriptionTextStyle;
    }

    public EditorialMarqueeEpoxyModel_ kickerStyle(int kickerStyle) {
        onMutation();
        this.kickerStyle = kickerStyle;
        return this;
    }

    public int kickerStyle() {
        return this.kickerStyle;
    }

    public EditorialMarqueeEpoxyModel_ scrimImage(boolean scrimImage) {
        onMutation();
        this.scrimImage = scrimImage;
        return this;
    }

    public boolean scrimImage() {
        return this.scrimImage;
    }

    public EditorialMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public EditorialMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public EditorialMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public EditorialMarqueeEpoxyModel_ m4546id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public EditorialMarqueeEpoxyModel_ m4551id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public EditorialMarqueeEpoxyModel_ m4547id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public EditorialMarqueeEpoxyModel_ m4548id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public EditorialMarqueeEpoxyModel_ m4550id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public EditorialMarqueeEpoxyModel_ m4549id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public EditorialMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public EditorialMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public EditorialMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public EditorialMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public EditorialMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_editorial_marquee;
    }

    public EditorialMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.description = null;
        this.hideImage = false;
        this.imageUrl = null;
        this.videoUrl = null;
        this.videoPosition = 0;
        this.kicker = null;
        this.titleTextStyle = 0;
        this.descriptionTextStyle = 0;
        this.kickerStyle = 0;
        this.scrimImage = false;
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
        if (!(o instanceof EditorialMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        EditorialMarqueeEpoxyModel_ that = (EditorialMarqueeEpoxyModel_) o;
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
        if (this.description != null) {
            if (!this.description.equals(that.description)) {
                return false;
            }
        } else if (that.description != null) {
            return false;
        }
        if (this.hideImage != that.hideImage) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.videoUrl != null) {
            if (!this.videoUrl.equals(that.videoUrl)) {
                return false;
            }
        } else if (that.videoUrl != null) {
            return false;
        }
        if (this.videoPosition != that.videoPosition) {
            return false;
        }
        if (this.kicker != null) {
            if (!this.kicker.equals(that.kicker)) {
                return false;
            }
        } else if (that.kicker != null) {
            return false;
        }
        if (this.titleTextStyle != that.titleTextStyle || this.descriptionTextStyle != that.descriptionTextStyle || this.kickerStyle != that.kickerStyle || this.scrimImage != that.scrimImage) {
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
        int i9 = 1;
        int i10 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i11 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i12 = (((i11 + i2) * 31) + this.titleRes) * 31;
        if (this.description != null) {
            i3 = this.description.hashCode();
        } else {
            i3 = 0;
        }
        int i13 = (i12 + i3) * 31;
        if (this.hideImage) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.imageUrl != null) {
            i5 = this.imageUrl.hashCode();
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.videoUrl != null) {
            i6 = this.videoUrl.hashCode();
        } else {
            i6 = 0;
        }
        int i16 = (((i15 + i6) * 31) + this.videoPosition) * 31;
        if (this.kicker != null) {
            i7 = this.kicker.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (((((((i16 + i7) * 31) + this.titleTextStyle) * 31) + this.descriptionTextStyle) * 31) + this.kickerStyle) * 31;
        if (!this.scrimImage) {
            i9 = 0;
        }
        int i18 = (i17 + i9) * 31;
        if (this.showDivider != null) {
            i8 = this.showDivider.hashCode();
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (this.numCarouselItemsShown != null) {
            i10 = this.numCarouselItemsShown.hashCode();
        }
        return i19 + i10;
    }

    public String toString() {
        return "EditorialMarqueeEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", description=" + this.description + ", hideImage=" + this.hideImage + ", imageUrl=" + this.imageUrl + ", videoUrl=" + this.videoUrl + ", videoPosition=" + this.videoPosition + ", kicker=" + this.kicker + ", titleTextStyle=" + this.titleTextStyle + ", descriptionTextStyle=" + this.descriptionTextStyle + ", kickerStyle=" + this.kickerStyle + ", scrimImage=" + this.scrimImage + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
