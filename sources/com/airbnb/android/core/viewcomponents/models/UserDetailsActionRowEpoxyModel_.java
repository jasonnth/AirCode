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
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class UserDetailsActionRowEpoxyModel_ extends UserDetailsActionRowEpoxyModel implements GeneratedModel<UserDetailsActionRow> {
    private OnModelBoundListener<UserDetailsActionRowEpoxyModel_, UserDetailsActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<UserDetailsActionRowEpoxyModel_, UserDetailsActionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, UserDetailsActionRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(UserDetailsActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public UserDetailsActionRowEpoxyModel_ onBind(OnModelBoundListener<UserDetailsActionRowEpoxyModel_, UserDetailsActionRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(UserDetailsActionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public UserDetailsActionRowEpoxyModel_ onUnbind(OnModelUnboundListener<UserDetailsActionRowEpoxyModel_, UserDetailsActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public UserDetailsActionRowEpoxyModel_ subTitle(String subTitle) {
        onMutation();
        this.subTitle = subTitle;
        return this;
    }

    public String subTitle() {
        return this.subTitle;
    }

    public UserDetailsActionRowEpoxyModel_ label(String label) {
        onMutation();
        this.label = label;
        return this;
    }

    public String label() {
        return this.label;
    }

    public UserDetailsActionRowEpoxyModel_ extraText(String extraText) {
        onMutation();
        this.extraText = extraText;
        return this;
    }

    public String extraText() {
        return this.extraText;
    }

    public UserDetailsActionRowEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public UserDetailsActionRowEpoxyModel_ imageDrawableRes(int imageDrawableRes) {
        onMutation();
        this.imageDrawableRes = imageDrawableRes;
        return this;
    }

    public int imageDrawableRes() {
        return this.imageDrawableRes;
    }

    public UserDetailsActionRowEpoxyModel_ clickListener(OnModelClickListener<UserDetailsActionRowEpoxyModel_, UserDetailsActionRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public UserDetailsActionRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public UserDetailsActionRowEpoxyModel_ m5758id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public UserDetailsActionRowEpoxyModel_ m5763id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public UserDetailsActionRowEpoxyModel_ m5759id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public UserDetailsActionRowEpoxyModel_ m5760id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public UserDetailsActionRowEpoxyModel_ m5762id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public UserDetailsActionRowEpoxyModel_ m5761id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public UserDetailsActionRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_user_details_action_row;
    }

    public UserDetailsActionRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subTitle = null;
        this.label = null;
        this.extraText = null;
        this.imageUrl = null;
        this.imageDrawableRes = 0;
        this.clickListener = null;
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
        if (!(o instanceof UserDetailsActionRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        UserDetailsActionRowEpoxyModel_ that = (UserDetailsActionRowEpoxyModel_) o;
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
        if (this.subTitle != null) {
            if (!this.subTitle.equals(that.subTitle)) {
                return false;
            }
        } else if (that.subTitle != null) {
            return false;
        }
        if (this.label != null) {
            if (!this.label.equals(that.label)) {
                return false;
            }
        } else if (that.label != null) {
            return false;
        }
        if (this.extraText != null) {
            if (!this.extraText.equals(that.extraText)) {
                return false;
            }
        } else if (that.extraText != null) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.imageDrawableRes != that.imageDrawableRes) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
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
        int i11 = (i10 + i2) * 31;
        if (this.subTitle != null) {
            i3 = this.subTitle.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.label != null) {
            i4 = this.label.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.extraText != null) {
            i5 = this.extraText.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.imageUrl != null) {
            i6 = this.imageUrl.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (((i14 + i6) * 31) + this.imageDrawableRes) * 31;
        if (this.clickListener == null) {
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
        return "UserDetailsActionRowEpoxyModel_{title=" + this.title + ", subTitle=" + this.subTitle + ", label=" + this.label + ", extraText=" + this.extraText + ", imageUrl=" + this.imageUrl + ", imageDrawableRes=" + this.imageDrawableRes + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
