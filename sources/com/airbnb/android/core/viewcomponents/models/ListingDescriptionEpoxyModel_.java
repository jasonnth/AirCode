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
import com.airbnb.p027n2.components.ListingDescription;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ListingDescriptionEpoxyModel_ extends ListingDescriptionEpoxyModel implements GeneratedModel<ListingDescription> {
    private OnModelBoundListener<ListingDescriptionEpoxyModel_, ListingDescription> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ListingDescriptionEpoxyModel_, ListingDescription> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ListingDescription object, int position) {
        if (this.readMoreClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.readMoreClickListener).bind(holder, object);
        }
        if (this.translateClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.translateClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ListingDescription object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ListingDescriptionEpoxyModel_ onBind(OnModelBoundListener<ListingDescriptionEpoxyModel_, ListingDescription> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ListingDescription object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ListingDescriptionEpoxyModel_ onUnbind(OnModelUnboundListener<ListingDescriptionEpoxyModel_, ListingDescription> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ListingDescriptionEpoxyModel_ summary(String summary) {
        onMutation();
        this.summary = summary;
        return this;
    }

    public String summary() {
        return this.summary;
    }

    public ListingDescriptionEpoxyModel_ summaryHighlight(String summaryHighlight) {
        onMutation();
        this.summaryHighlight = summaryHighlight;
        return this;
    }

    public String summaryHighlight() {
        return this.summaryHighlight;
    }

    public ListingDescriptionEpoxyModel_ space(String space) {
        onMutation();
        this.space = space;
        return this;
    }

    public String space() {
        return this.space;
    }

    public ListingDescriptionEpoxyModel_ readMoreClickListener(OnModelClickListener<ListingDescriptionEpoxyModel_, ListingDescription> readMoreClickListener) {
        onMutation();
        if (readMoreClickListener == null) {
            this.readMoreClickListener = null;
        } else {
            this.readMoreClickListener = new WrappedEpoxyModelClickListener(this, readMoreClickListener);
        }
        return this;
    }

    public ListingDescriptionEpoxyModel_ readMoreClickListener(OnClickListener readMoreClickListener) {
        onMutation();
        this.readMoreClickListener = readMoreClickListener;
        return this;
    }

    public OnClickListener readMoreClickListener() {
        return this.readMoreClickListener;
    }

    public ListingDescriptionEpoxyModel_ translateClickListener(OnModelClickListener<ListingDescriptionEpoxyModel_, ListingDescription> translateClickListener) {
        onMutation();
        if (translateClickListener == null) {
            this.translateClickListener = null;
        } else {
            this.translateClickListener = new WrappedEpoxyModelClickListener(this, translateClickListener);
        }
        return this;
    }

    public ListingDescriptionEpoxyModel_ translateClickListener(OnClickListener translateClickListener) {
        onMutation();
        this.translateClickListener = translateClickListener;
        return this;
    }

    public OnClickListener translateClickListener() {
        return this.translateClickListener;
    }

    public ListingDescriptionEpoxyModel_ translatedText(String translatedText) {
        onMutation();
        this.translatedText = translatedText;
        return this;
    }

    public String translatedText() {
        return this.translatedText;
    }

    public ListingDescriptionEpoxyModel_ isTranslatable(boolean isTranslatable) {
        onMutation();
        this.isTranslatable = isTranslatable;
        return this;
    }

    public boolean isTranslatable() {
        return this.isTranslatable;
    }

    public ListingDescriptionEpoxyModel_ descriptionLocale(String descriptionLocale) {
        onMutation();
        this.descriptionLocale = descriptionLocale;
        return this;
    }

    public String descriptionLocale() {
        return this.descriptionLocale;
    }

    public ListingDescriptionEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ListingDescriptionEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ListingDescriptionEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ListingDescriptionEpoxyModel_ m5062id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ListingDescriptionEpoxyModel_ m5067id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ListingDescriptionEpoxyModel_ m5063id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ListingDescriptionEpoxyModel_ m5064id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ListingDescriptionEpoxyModel_ m5066id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ListingDescriptionEpoxyModel_ m5065id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ListingDescriptionEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ListingDescriptionEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ListingDescriptionEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ListingDescriptionEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ListingDescriptionEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_listing_description;
    }

    public ListingDescriptionEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.summary = null;
        this.summaryHighlight = null;
        this.space = null;
        this.readMoreClickListener = null;
        this.translateClickListener = null;
        this.translatedText = null;
        this.isTranslatable = false;
        this.descriptionLocale = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListingDescriptionEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ListingDescriptionEpoxyModel_ that = (ListingDescriptionEpoxyModel_) o;
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
        if (this.summary != null) {
            if (!this.summary.equals(that.summary)) {
                return false;
            }
        } else if (that.summary != null) {
            return false;
        }
        if (this.summaryHighlight != null) {
            if (!this.summaryHighlight.equals(that.summaryHighlight)) {
                return false;
            }
        } else if (that.summaryHighlight != null) {
            return false;
        }
        if (this.space != null) {
            if (!this.space.equals(that.space)) {
                return false;
            }
        } else if (that.space != null) {
            return false;
        }
        if (this.readMoreClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.readMoreClickListener == null)) {
            return false;
        }
        if (this.translateClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.translateClickListener == null)) {
            return false;
        }
        if (this.translatedText != null) {
            if (!this.translatedText.equals(that.translatedText)) {
                return false;
            }
        } else if (that.translatedText != null) {
            return false;
        }
        if (this.isTranslatable != that.isTranslatable) {
            return false;
        }
        if (this.descriptionLocale != null) {
            if (!this.descriptionLocale.equals(that.descriptionLocale)) {
                return false;
            }
        } else if (that.descriptionLocale != null) {
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
        int i10 = 1;
        int i11 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i12 = (hashCode + i) * 31;
        if (this.summary != null) {
            i2 = this.summary.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (i12 + i2) * 31;
        if (this.summaryHighlight != null) {
            i3 = this.summaryHighlight.hashCode();
        } else {
            i3 = 0;
        }
        int i14 = (i13 + i3) * 31;
        if (this.space != null) {
            i4 = this.space.hashCode();
        } else {
            i4 = 0;
        }
        int i15 = (i14 + i4) * 31;
        if (this.readMoreClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        if (this.translateClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i17 = (i16 + i6) * 31;
        if (this.translatedText != null) {
            i7 = this.translatedText.hashCode();
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
        if (!this.isTranslatable) {
            i10 = 0;
        }
        int i19 = (i18 + i10) * 31;
        if (this.descriptionLocale != null) {
            i8 = this.descriptionLocale.hashCode();
        } else {
            i8 = 0;
        }
        int i20 = (i19 + i8) * 31;
        if (this.showDivider != null) {
            i9 = this.showDivider.hashCode();
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.numCarouselItemsShown != null) {
            i11 = this.numCarouselItemsShown.hashCode();
        }
        return i21 + i11;
    }

    public String toString() {
        return "ListingDescriptionEpoxyModel_{summary=" + this.summary + ", summaryHighlight=" + this.summaryHighlight + ", space=" + this.space + ", readMoreClickListener=" + this.readMoreClickListener + ", translateClickListener=" + this.translateClickListener + ", translatedText=" + this.translatedText + ", isTranslatable=" + this.isTranslatable + ", descriptionLocale=" + this.descriptionLocale + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
