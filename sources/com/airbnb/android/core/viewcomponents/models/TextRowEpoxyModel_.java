package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class TextRowEpoxyModel_ extends TextRowEpoxyModel implements GeneratedModel<TextRow> {
    private OnModelBoundListener<TextRowEpoxyModel_, TextRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<TextRowEpoxyModel_, TextRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, TextRow object, int position) {
    }

    public void handlePostBind(TextRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public TextRowEpoxyModel_ onBind(OnModelBoundListener<TextRowEpoxyModel_, TextRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(TextRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public TextRowEpoxyModel_ onUnbind(OnModelUnboundListener<TextRowEpoxyModel_, TextRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public TextRowEpoxyModel_ readMoreText(CharSequence readMoreText) {
        onMutation();
        this.readMoreText = readMoreText;
        return this;
    }

    public CharSequence readMoreText() {
        return this.readMoreText;
    }

    public TextRowEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public TextRowEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public TextRowEpoxyModel_ maxLines(int maxLines) {
        onMutation();
        this.maxLines = maxLines;
        return this;
    }

    public int maxLines() {
        return this.maxLines;
    }

    public TextRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public TextRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public TextRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public TextRowEpoxyModel_ m5686id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public TextRowEpoxyModel_ m5691id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public TextRowEpoxyModel_ m5687id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public TextRowEpoxyModel_ m5688id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public TextRowEpoxyModel_ m5690id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public TextRowEpoxyModel_ m5689id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public TextRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public TextRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public TextRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public TextRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public TextRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_text_row;
    }

    public TextRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.readMoreText = null;
        this.text = null;
        this.textRes = 0;
        this.maxLines = 0;
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
        if (!(o instanceof TextRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        TextRowEpoxyModel_ that = (TextRowEpoxyModel_) o;
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
        if (this.readMoreText != null) {
            if (!this.readMoreText.equals(that.readMoreText)) {
                return false;
            }
        } else if (that.readMoreText != null) {
            return false;
        }
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.textRes != that.textRes || this.maxLines != that.maxLines) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.readMoreText != null) {
            i = this.readMoreText.hashCode();
        } else {
            i = 0;
        }
        int i7 = (i6 + i) * 31;
        if (this.text != null) {
            i2 = this.text.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((((i7 + i2) * 31) + this.textRes) * 31) + this.maxLines) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "TextRowEpoxyModel_{readMoreText=" + this.readMoreText + ", text=" + this.text + ", textRes=" + this.textRes + ", maxLines=" + this.maxLines + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
