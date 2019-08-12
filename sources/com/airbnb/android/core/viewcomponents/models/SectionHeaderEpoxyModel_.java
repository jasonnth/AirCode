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
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class SectionHeaderEpoxyModel_ extends SectionHeaderEpoxyModel implements GeneratedModel<SectionHeader> {
    private OnModelBoundListener<SectionHeaderEpoxyModel_, SectionHeader> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SectionHeaderEpoxyModel_, SectionHeader> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SectionHeader object, int position) {
        if (this.buttonOnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.buttonOnClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(SectionHeader object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SectionHeaderEpoxyModel_ onBind(OnModelBoundListener<SectionHeaderEpoxyModel_, SectionHeader> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SectionHeader object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SectionHeaderEpoxyModel_ onUnbind(OnModelUnboundListener<SectionHeaderEpoxyModel_, SectionHeader> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SectionHeaderEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public SectionHeaderEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public SectionHeaderEpoxyModel_ description(CharSequence description) {
        onMutation();
        this.description = description;
        return this;
    }

    public CharSequence description() {
        return this.description;
    }

    public SectionHeaderEpoxyModel_ descriptionRes(int descriptionRes) {
        onMutation();
        this.descriptionRes = descriptionRes;
        return this;
    }

    public int descriptionRes() {
        return this.descriptionRes;
    }

    public SectionHeaderEpoxyModel_ buttonText(CharSequence buttonText) {
        onMutation();
        this.buttonText = buttonText;
        return this;
    }

    public CharSequence buttonText() {
        return this.buttonText;
    }

    public SectionHeaderEpoxyModel_ buttonTextRes(int buttonTextRes) {
        onMutation();
        this.buttonTextRes = buttonTextRes;
        return this;
    }

    public int buttonTextRes() {
        return this.buttonTextRes;
    }

    public SectionHeaderEpoxyModel_ buttonOnClickListener(OnModelClickListener<SectionHeaderEpoxyModel_, SectionHeader> buttonOnClickListener) {
        onMutation();
        if (buttonOnClickListener == null) {
            this.buttonOnClickListener = null;
        } else {
            this.buttonOnClickListener = new WrappedEpoxyModelClickListener(this, buttonOnClickListener);
        }
        return this;
    }

    public SectionHeaderEpoxyModel_ buttonOnClickListener(OnClickListener buttonOnClickListener) {
        onMutation();
        this.buttonOnClickListener = buttonOnClickListener;
        return this;
    }

    public OnClickListener buttonOnClickListener() {
        return this.buttonOnClickListener;
    }

    public SectionHeaderEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SectionHeaderEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SectionHeaderEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SectionHeaderEpoxyModel_ m5554id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SectionHeaderEpoxyModel_ m5559id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SectionHeaderEpoxyModel_ m5555id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SectionHeaderEpoxyModel_ m5556id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SectionHeaderEpoxyModel_ m5558id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SectionHeaderEpoxyModel_ m5557id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SectionHeaderEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SectionHeaderEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SectionHeaderEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SectionHeaderEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SectionHeaderEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_section_header;
    }

    public SectionHeaderEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.description = null;
        this.descriptionRes = 0;
        this.buttonText = null;
        this.buttonTextRes = 0;
        this.buttonOnClickListener = null;
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
        if (!(o instanceof SectionHeaderEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SectionHeaderEpoxyModel_ that = (SectionHeaderEpoxyModel_) o;
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
        if (this.buttonTextRes != that.buttonTextRes) {
            return false;
        }
        if (this.buttonOnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.buttonOnClickListener == null)) {
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
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((i8 + i2) * 31) + this.titleRes) * 31;
        if (this.description != null) {
            i3 = this.description.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((i9 + i3) * 31) + this.descriptionRes) * 31;
        if (this.buttonText != null) {
            i4 = this.buttonText.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (((i10 + i4) * 31) + this.buttonTextRes) * 31;
        if (this.buttonOnClickListener == null) {
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
        return "SectionHeaderEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", description=" + this.description + ", descriptionRes=" + this.descriptionRes + ", buttonText=" + this.buttonText + ", buttonTextRes=" + this.buttonTextRes + ", buttonOnClickListener=" + this.buttonOnClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
