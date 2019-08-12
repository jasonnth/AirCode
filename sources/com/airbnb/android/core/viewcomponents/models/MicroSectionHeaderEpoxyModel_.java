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
import com.airbnb.p027n2.components.MicroSectionHeader;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class MicroSectionHeaderEpoxyModel_ extends MicroSectionHeaderEpoxyModel implements GeneratedModel<MicroSectionHeader> {
    private OnModelBoundListener<MicroSectionHeaderEpoxyModel_, MicroSectionHeader> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MicroSectionHeaderEpoxyModel_, MicroSectionHeader> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MicroSectionHeader object, int position) {
        if (this.buttonOnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.buttonOnClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(MicroSectionHeader object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public MicroSectionHeaderEpoxyModel_ onBind(OnModelBoundListener<MicroSectionHeaderEpoxyModel_, MicroSectionHeader> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MicroSectionHeader object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public MicroSectionHeaderEpoxyModel_ onUnbind(OnModelUnboundListener<MicroSectionHeaderEpoxyModel_, MicroSectionHeader> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public MicroSectionHeaderEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public MicroSectionHeaderEpoxyModel_ description(CharSequence description) {
        onMutation();
        this.description = description;
        return this;
    }

    public CharSequence description() {
        return this.description;
    }

    public MicroSectionHeaderEpoxyModel_ descriptionRes(int descriptionRes) {
        onMutation();
        this.descriptionRes = descriptionRes;
        return this;
    }

    public int descriptionRes() {
        return this.descriptionRes;
    }

    public MicroSectionHeaderEpoxyModel_ buttonText(CharSequence buttonText) {
        onMutation();
        this.buttonText = buttonText;
        return this;
    }

    public CharSequence buttonText() {
        return this.buttonText;
    }

    public MicroSectionHeaderEpoxyModel_ buttonTextRes(int buttonTextRes) {
        onMutation();
        this.buttonTextRes = buttonTextRes;
        return this;
    }

    public int buttonTextRes() {
        return this.buttonTextRes;
    }

    public MicroSectionHeaderEpoxyModel_ isBold(boolean isBold) {
        onMutation();
        this.isBold = isBold;
        return this;
    }

    public boolean isBold() {
        return this.isBold;
    }

    public MicroSectionHeaderEpoxyModel_ invertColors(boolean invertColors) {
        onMutation();
        this.invertColors = invertColors;
        return this;
    }

    public boolean invertColors() {
        return this.invertColors;
    }

    public MicroSectionHeaderEpoxyModel_ maxLines(int maxLines) {
        onMutation();
        this.maxLines = maxLines;
        return this;
    }

    public int maxLines() {
        return this.maxLines;
    }

    public MicroSectionHeaderEpoxyModel_ buttonOnClickListener(OnModelClickListener<MicroSectionHeaderEpoxyModel_, MicroSectionHeader> buttonOnClickListener) {
        onMutation();
        if (buttonOnClickListener == null) {
            this.buttonOnClickListener = null;
        } else {
            this.buttonOnClickListener = new WrappedEpoxyModelClickListener(this, buttonOnClickListener);
        }
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ buttonOnClickListener(OnClickListener buttonOnClickListener) {
        onMutation();
        this.buttonOnClickListener = buttonOnClickListener;
        return this;
    }

    public OnClickListener buttonOnClickListener() {
        return this.buttonOnClickListener;
    }

    public MicroSectionHeaderEpoxyModel_ sectionId(String sectionId) {
        onMutation();
        this.sectionId = sectionId;
        return this;
    }

    public String sectionId() {
        return this.sectionId;
    }

    public MicroSectionHeaderEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MicroSectionHeaderEpoxyModel_ m5170id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MicroSectionHeaderEpoxyModel_ m5175id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MicroSectionHeaderEpoxyModel_ m5171id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MicroSectionHeaderEpoxyModel_ m5172id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MicroSectionHeaderEpoxyModel_ m5174id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MicroSectionHeaderEpoxyModel_ m5173id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ show() {
        super.show();
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MicroSectionHeaderEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_micro_section_header;
    }

    public MicroSectionHeaderEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.description = null;
        this.descriptionRes = 0;
        this.buttonText = null;
        this.buttonTextRes = 0;
        this.isBold = false;
        this.invertColors = false;
        this.maxLines = 0;
        this.buttonOnClickListener = null;
        this.sectionId = null;
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
        if (!(o instanceof MicroSectionHeaderEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        MicroSectionHeaderEpoxyModel_ that = (MicroSectionHeaderEpoxyModel_) o;
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
        if (this.descriptionRes != that.descriptionRes) {
            return false;
        }
        if (this.buttonText != null) {
            if (!this.buttonText.equals(that.buttonText)) {
                return false;
            }
        } else if (that.buttonText != null) {
            return false;
        }
        if (this.buttonTextRes != that.buttonTextRes || this.isBold != that.isBold || this.invertColors != that.invertColors || this.maxLines != that.maxLines) {
            return false;
        }
        if ((this.buttonOnClickListener == null) != (that.buttonOnClickListener == null)) {
            return false;
        }
        if (this.sectionId != null) {
            if (!this.sectionId.equals(that.sectionId)) {
                return false;
            }
        } else if (that.sectionId != null) {
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
        int i13 = (((i12 + i3) * 31) + this.descriptionRes) * 31;
        if (this.buttonText != null) {
            i4 = this.buttonText.hashCode();
        } else {
            i4 = 0;
        }
        int i14 = (((i13 + i4) * 31) + this.buttonTextRes) * 31;
        if (this.isBold) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.invertColors) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i16 = (((i15 + i6) * 31) + this.maxLines) * 31;
        if (this.buttonOnClickListener == null) {
            i9 = 0;
        }
        int i17 = (i16 + i9) * 31;
        if (this.sectionId != null) {
            i7 = this.sectionId.hashCode();
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
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
        return "MicroSectionHeaderEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", description=" + this.description + ", descriptionRes=" + this.descriptionRes + ", buttonText=" + this.buttonText + ", buttonTextRes=" + this.buttonTextRes + ", isBold=" + this.isBold + ", invertColors=" + this.invertColors + ", maxLines=" + this.maxLines + ", buttonOnClickListener=" + this.buttonOnClickListener + ", sectionId=" + this.sectionId + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
