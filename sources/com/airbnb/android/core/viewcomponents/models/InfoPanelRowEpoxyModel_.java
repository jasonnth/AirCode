package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.InfoPanelRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

public class InfoPanelRowEpoxyModel_ extends InfoPanelRowEpoxyModel implements GeneratedModel<InfoPanelRow> {
    private OnModelBoundListener<InfoPanelRowEpoxyModel_, InfoPanelRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InfoPanelRowEpoxyModel_, InfoPanelRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InfoPanelRow object, int position) {
    }

    public void handlePostBind(InfoPanelRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InfoPanelRowEpoxyModel_ onBind(OnModelBoundListener<InfoPanelRowEpoxyModel_, InfoPanelRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InfoPanelRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InfoPanelRowEpoxyModel_ onUnbind(OnModelUnboundListener<InfoPanelRowEpoxyModel_, InfoPanelRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InfoPanelRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InfoPanelRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InfoPanelRowEpoxyModel_ content(CharSequence content) {
        onMutation();
        this.content = content;
        return this;
    }

    public CharSequence content() {
        return this.content;
    }

    public InfoPanelRowEpoxyModel_ contentRes(int contentRes) {
        onMutation();
        this.contentRes = contentRes;
        return this;
    }

    public int contentRes() {
        return this.contentRes;
    }

    public InfoPanelRowEpoxyModel_ linkText(String linkText) {
        onMutation();
        this.linkText = linkText;
        return this;
    }

    public String linkText() {
        return this.linkText;
    }

    public InfoPanelRowEpoxyModel_ linkOnClickListener(LinkOnClickListener linkOnClickListener) {
        onMutation();
        this.linkOnClickListener = linkOnClickListener;
        return this;
    }

    public LinkOnClickListener linkOnClickListener() {
        return this.linkOnClickListener;
    }

    public InfoPanelRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InfoPanelRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InfoPanelRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InfoPanelRowEpoxyModel_ m4822id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InfoPanelRowEpoxyModel_ m4827id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InfoPanelRowEpoxyModel_ m4823id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InfoPanelRowEpoxyModel_ m4824id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InfoPanelRowEpoxyModel_ m4826id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InfoPanelRowEpoxyModel_ m4825id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InfoPanelRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InfoPanelRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InfoPanelRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InfoPanelRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InfoPanelRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_info_panel_row;
    }

    public InfoPanelRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.content = null;
        this.contentRes = 0;
        this.linkText = null;
        this.linkOnClickListener = null;
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
        if (!(o instanceof InfoPanelRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InfoPanelRowEpoxyModel_ that = (InfoPanelRowEpoxyModel_) o;
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
        if (this.content != null) {
            if (!this.content.equals(that.content)) {
                return false;
            }
        } else if (that.content != null) {
            return false;
        }
        if (this.contentRes != that.contentRes) {
            return false;
        }
        if (this.linkText != null) {
            if (!this.linkText.equals(that.linkText)) {
                return false;
            }
        } else if (that.linkText != null) {
            return false;
        }
        if (this.linkOnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.linkOnClickListener == null)) {
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
        if (this.content != null) {
            i3 = this.content.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((i9 + i3) * 31) + this.contentRes) * 31;
        if (this.linkText != null) {
            i4 = this.linkText.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.linkOnClickListener == null) {
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
        return "InfoPanelRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", content=" + this.content + ", contentRes=" + this.contentRes + ", linkText=" + this.linkText + ", linkOnClickListener=" + this.linkOnClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
