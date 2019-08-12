package com.airbnb.android.core.viewcomponents.models;

import android.text.method.MovementMethod;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

public class SimpleTextRowEpoxyModel_ extends SimpleTextRowEpoxyModel implements GeneratedModel<SimpleTextRow> {
    private OnModelBoundListener<SimpleTextRowEpoxyModel_, SimpleTextRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SimpleTextRowEpoxyModel_, SimpleTextRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SimpleTextRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(SimpleTextRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SimpleTextRowEpoxyModel_ onBind(OnModelBoundListener<SimpleTextRowEpoxyModel_, SimpleTextRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SimpleTextRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SimpleTextRowEpoxyModel_ onUnbind(OnModelUnboundListener<SimpleTextRowEpoxyModel_, SimpleTextRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SimpleTextRowEpoxyModel_ text(CharSequence text) {
        onMutation();
        this.text = text;
        return this;
    }

    public CharSequence text() {
        return this.text;
    }

    public SimpleTextRowEpoxyModel_ textRes(int textRes) {
        onMutation();
        this.textRes = textRes;
        return this;
    }

    public int textRes() {
        return this.textRes;
    }

    public SimpleTextRowEpoxyModel_ textIsSelectable(boolean textIsSelectable) {
        onMutation();
        this.textIsSelectable = textIsSelectable;
        return this;
    }

    public boolean textIsSelectable() {
        return this.textIsSelectable;
    }

    public SimpleTextRowEpoxyModel_ movementMethod(MovementMethod movementMethod) {
        onMutation();
        this.movementMethod = movementMethod;
        return this;
    }

    public MovementMethod movementMethod() {
        return this.movementMethod;
    }

    public SimpleTextRowEpoxyModel_ coloredText(CharSequence coloredText) {
        onMutation();
        this.coloredText = coloredText;
        return this;
    }

    public CharSequence coloredText() {
        return this.coloredText;
    }

    public SimpleTextRowEpoxyModel_ coloredTextRes(int coloredTextRes) {
        onMutation();
        this.coloredTextRes = coloredTextRes;
        return this;
    }

    public int coloredTextRes() {
        return this.coloredTextRes;
    }

    public SimpleTextRowEpoxyModel_ description(CharSequence description) {
        onMutation();
        this.description = description;
        return this;
    }

    public CharSequence description() {
        return this.description;
    }

    public SimpleTextRowEpoxyModel_ descriptionRes(int descriptionRes) {
        onMutation();
        this.descriptionRes = descriptionRes;
        return this;
    }

    public int descriptionRes() {
        return this.descriptionRes;
    }

    public SimpleTextRowEpoxyModel_ color(int color) {
        onMutation();
        this.color = color;
        return this;
    }

    public int color() {
        return this.color;
    }

    public SimpleTextRowEpoxyModel_ linkListener(LinkOnClickListener linkListener) {
        onMutation();
        this.linkListener = linkListener;
        return this;
    }

    public LinkOnClickListener linkListener() {
        return this.linkListener;
    }

    public SimpleTextRowEpoxyModel_ clickListener(OnModelClickListener<SimpleTextRowEpoxyModel_, SimpleTextRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public SimpleTextRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public SimpleTextRowEpoxyModel_ hasColoredText(boolean hasColoredText) {
        onMutation();
        this.hasColoredText = hasColoredText;
        return this;
    }

    public boolean hasColoredText() {
        return this.hasColoredText;
    }

    public SimpleTextRowEpoxyModel_ hasLinkedText(boolean hasLinkedText) {
        onMutation();
        this.hasLinkedText = hasLinkedText;
        return this;
    }

    public boolean hasLinkedText() {
        return this.hasLinkedText;
    }

    public SimpleTextRowEpoxyModel_ linkifyMask(int linkifyMask) {
        onMutation();
        this.linkifyMask = linkifyMask;
        return this;
    }

    public int linkifyMask() {
        return this.linkifyMask;
    }

    public SimpleTextRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SimpleTextRowEpoxyModel_ plus() {
        super.plus();
        return this;
    }

    public SimpleTextRowEpoxyModel_ large() {
        super.large();
        return this;
    }

    public SimpleTextRowEpoxyModel_ largeAndInverse() {
        super.largeAndInverse();
        return this;
    }

    public SimpleTextRowEpoxyModel_ small() {
        super.small();
        return this;
    }

    public SimpleTextRowEpoxyModel_ smallAndMuted() {
        super.smallAndMuted();
        return this;
    }

    public SimpleTextRowEpoxyModel_ plusAndTightPadding() {
        super.plusAndTightPadding();
        return this;
    }

    public SimpleTextRowEpoxyModel_ plusAndTopPadding() {
        super.plusAndTopPadding();
        return this;
    }

    public SimpleTextRowEpoxyModel_ smallPadding() {
        super.smallPadding();
        return this;
    }

    public SimpleTextRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SimpleTextRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SimpleTextRowEpoxyModel_ m5578id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SimpleTextRowEpoxyModel_ m5583id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SimpleTextRowEpoxyModel_ m5579id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SimpleTextRowEpoxyModel_ m5580id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SimpleTextRowEpoxyModel_ m5582id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SimpleTextRowEpoxyModel_ m5581id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SimpleTextRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SimpleTextRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SimpleTextRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SimpleTextRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SimpleTextRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_simple_text_row;
    }

    public SimpleTextRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.text = null;
        this.textRes = 0;
        this.textIsSelectable = false;
        this.movementMethod = null;
        this.coloredText = null;
        this.coloredTextRes = 0;
        this.description = null;
        this.descriptionRes = 0;
        this.color = 0;
        this.linkListener = null;
        this.clickListener = null;
        this.hasColoredText = false;
        this.hasLinkedText = false;
        this.linkifyMask = 0;
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
        boolean z5;
        if (o == this) {
            return true;
        }
        if (!(o instanceof SimpleTextRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SimpleTextRowEpoxyModel_ that = (SimpleTextRowEpoxyModel_) o;
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
        if (this.textRes != that.textRes || this.textIsSelectable != that.textIsSelectable) {
            return false;
        }
        if (this.movementMethod == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.movementMethod == null)) {
            return false;
        }
        if (this.coloredText != null) {
            if (!this.coloredText.equals(that.coloredText)) {
                return false;
            }
        } else if (that.coloredText != null) {
            return false;
        }
        if (this.coloredTextRes != that.coloredTextRes) {
            return false;
        }
        if (this.description != null) {
            if (!this.description.equals(that.description)) {
                return false;
            }
        } else if (that.description != null) {
            return false;
        }
        if (this.descriptionRes != that.descriptionRes || this.color != that.color) {
            return false;
        }
        if (this.linkListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.linkListener == null)) {
            return false;
        }
        if (this.clickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (that.clickListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z4 != z5 || this.hasColoredText != that.hasColoredText || this.hasLinkedText != that.hasLinkedText || this.linkifyMask != that.linkifyMask) {
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
        int i11 = 1;
        int i12 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i13 = (hashCode + i) * 31;
        if (this.text != null) {
            i2 = this.text.hashCode();
        } else {
            i2 = 0;
        }
        int i14 = (((i13 + i2) * 31) + this.textRes) * 31;
        if (this.textIsSelectable) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i15 = (i14 + i3) * 31;
        if (this.movementMethod != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i16 = (i15 + i4) * 31;
        if (this.coloredText != null) {
            i5 = this.coloredText.hashCode();
        } else {
            i5 = 0;
        }
        int i17 = (((i16 + i5) * 31) + this.coloredTextRes) * 31;
        if (this.description != null) {
            i6 = this.description.hashCode();
        } else {
            i6 = 0;
        }
        int i18 = (((((i17 + i6) * 31) + this.descriptionRes) * 31) + this.color) * 31;
        if (this.linkListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i19 = (i18 + i7) * 31;
        if (this.clickListener != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i20 = (i19 + i8) * 31;
        if (this.hasColoredText) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (!this.hasLinkedText) {
            i11 = 0;
        }
        int i22 = (((i21 + i11) * 31) + this.linkifyMask) * 31;
        if (this.showDivider != null) {
            i10 = this.showDivider.hashCode();
        } else {
            i10 = 0;
        }
        int i23 = (i22 + i10) * 31;
        if (this.numCarouselItemsShown != null) {
            i12 = this.numCarouselItemsShown.hashCode();
        }
        return i23 + i12;
    }

    public String toString() {
        return "SimpleTextRowEpoxyModel_{text=" + this.text + ", textRes=" + this.textRes + ", textIsSelectable=" + this.textIsSelectable + ", movementMethod=" + this.movementMethod + ", coloredText=" + this.coloredText + ", coloredTextRes=" + this.coloredTextRes + ", description=" + this.description + ", descriptionRes=" + this.descriptionRes + ", color=" + this.color + ", linkListener=" + this.linkListener + ", clickListener=" + this.clickListener + ", hasColoredText=" + this.hasColoredText + ", hasLinkedText=" + this.hasLinkedText + ", linkifyMask=" + this.linkifyMask + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
