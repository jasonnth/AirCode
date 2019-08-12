package com.airbnb.android.referrals.adapters;

import android.view.View.OnClickListener;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.InviteRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InviteRowEpoxyModel_ extends InviteRowEpoxyModel implements GeneratedModel<InviteRow> {
    private OnModelBoundListener<InviteRowEpoxyModel_, InviteRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InviteRowEpoxyModel_, InviteRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InviteRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InviteRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InviteRowEpoxyModel_ onBind(OnModelBoundListener<InviteRowEpoxyModel_, InviteRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InviteRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InviteRowEpoxyModel_ onUnbind(OnModelUnboundListener<InviteRowEpoxyModel_, InviteRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InviteRowEpoxyModel_ photoUri(String photoUri) {
        onMutation();
        this.photoUri = photoUri;
        return this;
    }

    public String photoUri() {
        return this.photoUri;
    }

    public InviteRowEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public InviteRowEpoxyModel_ descriptionText(CharSequence descriptionText) {
        onMutation();
        this.descriptionText = descriptionText;
        return this;
    }

    public CharSequence descriptionText() {
        return this.descriptionText;
    }

    public InviteRowEpoxyModel_ buttonText(CharSequence buttonText) {
        onMutation();
        this.buttonText = buttonText;
        return this;
    }

    public CharSequence buttonText() {
        return this.buttonText;
    }

    public InviteRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InviteRowEpoxyModel_ descriptionRes(int descriptionRes) {
        onMutation();
        this.descriptionRes = descriptionRes;
        return this;
    }

    public int descriptionRes() {
        return this.descriptionRes;
    }

    public InviteRowEpoxyModel_ buttonRes(int buttonRes) {
        onMutation();
        this.buttonRes = buttonRes;
        return this;
    }

    public int buttonRes() {
        return this.buttonRes;
    }

    public InviteRowEpoxyModel_ clickListener(OnModelClickListener<InviteRowEpoxyModel_, InviteRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public InviteRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public InviteRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InviteRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InviteRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InviteRowEpoxyModel_ m1323id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InviteRowEpoxyModel_ m1328id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InviteRowEpoxyModel_ m1324id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InviteRowEpoxyModel_ m1325id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InviteRowEpoxyModel_ m1327id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InviteRowEpoxyModel_ m1326id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InviteRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InviteRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InviteRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InviteRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InviteRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C1532R.layout.n2_view_holder_invite_row;
    }

    public InviteRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.photoUri = null;
        this.titleText = null;
        this.descriptionText = null;
        this.buttonText = null;
        this.titleRes = 0;
        this.descriptionRes = 0;
        this.buttonRes = 0;
        this.clickListener = null;
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
        if (!(o instanceof InviteRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InviteRowEpoxyModel_ that = (InviteRowEpoxyModel_) o;
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
        if (this.photoUri != null) {
            if (!this.photoUri.equals(that.photoUri)) {
                return false;
            }
        } else if (that.photoUri != null) {
            return false;
        }
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.descriptionText != null) {
            if (!this.descriptionText.equals(that.descriptionText)) {
                return false;
            }
        } else if (that.descriptionText != null) {
            return false;
        }
        if (this.buttonText != null) {
            if (!this.buttonText.equals(that.buttonText)) {
                return false;
            }
        } else if (that.buttonText != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.descriptionRes != that.descriptionRes || this.buttonRes != that.buttonRes) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null)) {
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
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.photoUri != null) {
            i2 = this.photoUri.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.titleText != null) {
            i3 = this.titleText.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.descriptionText != null) {
            i4 = this.descriptionText.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.buttonText != null) {
            i5 = this.buttonText.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (((((((i12 + i5) * 31) + this.titleRes) * 31) + this.descriptionRes) * 31) + this.buttonRes) * 31;
        if (this.clickListener == null) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "InviteRowEpoxyModel_{photoUri=" + this.photoUri + ", titleText=" + this.titleText + ", descriptionText=" + this.descriptionText + ", buttonText=" + this.buttonText + ", titleRes=" + this.titleRes + ", descriptionRes=" + this.descriptionRes + ", buttonRes=" + this.buttonRes + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
