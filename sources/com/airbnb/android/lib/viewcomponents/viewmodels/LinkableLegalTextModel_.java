package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.LinkableLegalTextRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class LinkableLegalTextModel_ extends LinkableLegalTextModel implements GeneratedModel<LinkableLegalTextRow> {
    private OnModelBoundListener<LinkableLegalTextModel_, LinkableLegalTextRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<LinkableLegalTextModel_, LinkableLegalTextRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, LinkableLegalTextRow object, int position) {
    }

    public void handlePostBind(LinkableLegalTextRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public LinkableLegalTextModel_ onBind(OnModelBoundListener<LinkableLegalTextModel_, LinkableLegalTextRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(LinkableLegalTextRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public LinkableLegalTextModel_ onUnbind(OnModelUnboundListener<LinkableLegalTextModel_, LinkableLegalTextRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public LinkableLegalTextModel_ termsTitle(CharSequence termsTitle) {
        onMutation();
        this.termsTitle = termsTitle;
        return this;
    }

    public CharSequence termsTitle() {
        return this.termsTitle;
    }

    public LinkableLegalTextModel_ termsBody(CharSequence termsBody) {
        onMutation();
        this.termsBody = termsBody;
        return this;
    }

    public CharSequence termsBody() {
        return this.termsBody;
    }

    public LinkableLegalTextModel_ fxBody(CharSequence fxBody) {
        onMutation();
        this.fxBody = fxBody;
        return this;
    }

    public CharSequence fxBody() {
        return this.fxBody;
    }

    public LinkableLegalTextModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public LinkableLegalTextModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public LinkableLegalTextModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public LinkableLegalTextModel_ m6155id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public LinkableLegalTextModel_ m6160id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public LinkableLegalTextModel_ m6156id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public LinkableLegalTextModel_ m6157id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public LinkableLegalTextModel_ m6159id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public LinkableLegalTextModel_ m6158id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public LinkableLegalTextModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public LinkableLegalTextModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public LinkableLegalTextModel_ show() {
        super.show();
        return this;
    }

    public LinkableLegalTextModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public LinkableLegalTextModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_linkable_legal_text_row;
    }

    public LinkableLegalTextModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.termsTitle = null;
        this.termsBody = null;
        this.fxBody = null;
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
        if (!(o instanceof LinkableLegalTextModel_) || !super.equals(o)) {
            return false;
        }
        LinkableLegalTextModel_ that = (LinkableLegalTextModel_) o;
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
        if (this.termsTitle != null) {
            if (!this.termsTitle.equals(that.termsTitle)) {
                return false;
            }
        } else if (that.termsTitle != null) {
            return false;
        }
        if (this.termsBody != null) {
            if (!this.termsBody.equals(that.termsBody)) {
                return false;
            }
        } else if (that.termsBody != null) {
            return false;
        }
        if (this.fxBody != null) {
            if (!this.fxBody.equals(that.fxBody)) {
                return false;
            }
        } else if (that.fxBody != null) {
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
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i5 = 0;
        }
        int i7 = (hashCode + i5) * 31;
        if (this.termsTitle != null) {
            i = this.termsTitle.hashCode();
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.termsBody != null) {
            i2 = this.termsBody.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.fxBody != null) {
            i3 = this.fxBody.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.numCarouselItemsShown != null) {
            i6 = this.numCarouselItemsShown.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "LinkableLegalTextModel_{termsTitle=" + this.termsTitle + ", termsBody=" + this.termsBody + ", fxBody=" + this.fxBody + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
