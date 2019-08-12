package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.User;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.ParticipantRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ParticipantRowModel_ extends ParticipantRowModel implements GeneratedModel<ParticipantRow> {
    private OnModelBoundListener<ParticipantRowModel_, ParticipantRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ParticipantRowModel_, ParticipantRow> onModelUnboundListener_epoxyGeneratedModel;

    public ParticipantRowModel_(User user) {
        super(user);
    }

    public void handlePreBind(EpoxyViewHolder holder, ParticipantRow object, int position) {
        if (this.imageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.imageClickListener).bind(holder, object);
        }
        if (this.removeClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.removeClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ParticipantRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ParticipantRowModel_ onBind(OnModelBoundListener<ParticipantRowModel_, ParticipantRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ParticipantRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ParticipantRowModel_ onUnbind(OnModelUnboundListener<ParticipantRowModel_, ParticipantRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ParticipantRowModel_ name(CharSequence name) {
        onMutation();
        this.name = name;
        return this;
    }

    public CharSequence name() {
        return this.name;
    }

    public ParticipantRowModel_ removable(boolean removable) {
        onMutation();
        this.removable = removable;
        return this;
    }

    public boolean removable() {
        return this.removable;
    }

    public ParticipantRowModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public ParticipantRowModel_ imageClickListener(OnModelClickListener<ParticipantRowModel_, ParticipantRow> imageClickListener) {
        onMutation();
        if (imageClickListener == null) {
            this.imageClickListener = null;
        } else {
            this.imageClickListener = new WrappedEpoxyModelClickListener(this, imageClickListener);
        }
        return this;
    }

    public ParticipantRowModel_ imageClickListener(OnClickListener imageClickListener) {
        onMutation();
        this.imageClickListener = imageClickListener;
        return this;
    }

    public OnClickListener imageClickListener() {
        return this.imageClickListener;
    }

    public ParticipantRowModel_ removeClickListener(OnModelClickListener<ParticipantRowModel_, ParticipantRow> removeClickListener) {
        onMutation();
        if (removeClickListener == null) {
            this.removeClickListener = null;
        } else {
            this.removeClickListener = new WrappedEpoxyModelClickListener(this, removeClickListener);
        }
        return this;
    }

    public ParticipantRowModel_ removeClickListener(OnClickListener removeClickListener) {
        onMutation();
        this.removeClickListener = removeClickListener;
        return this;
    }

    public OnClickListener removeClickListener() {
        return this.removeClickListener;
    }

    public ParticipantRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ParticipantRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ParticipantRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ParticipantRowModel_ m5266id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ParticipantRowModel_ m5271id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ParticipantRowModel_ m5267id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ParticipantRowModel_ m5268id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ParticipantRowModel_ m5270id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ParticipantRowModel_ m5269id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ParticipantRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ParticipantRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ParticipantRowModel_ show() {
        super.show();
        return this;
    }

    public ParticipantRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ParticipantRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_participant_row;
    }

    public ParticipantRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.name = null;
        this.removable = false;
        this.imageUrl = null;
        this.imageClickListener = null;
        this.removeClickListener = null;
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
        if (!(o instanceof ParticipantRowModel_) || !super.equals(o)) {
            return false;
        }
        ParticipantRowModel_ that = (ParticipantRowModel_) o;
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
        if (this.name != null) {
            if (!this.name.equals(that.name)) {
                return false;
            }
        } else if (that.name != null) {
            return false;
        }
        if (this.removable != that.removable) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.imageClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.imageClickListener == null)) {
            return false;
        }
        if (this.removeClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.removeClickListener == null)) {
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
        if (this.name != null) {
            i2 = this.name.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.removable) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.imageUrl != null) {
            i4 = this.imageUrl.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.imageClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.removeClickListener == null) {
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
        return "ParticipantRowModel_{name=" + this.name + ", removable=" + this.removable + ", imageUrl=" + this.imageUrl + ", imageClickListener=" + this.imageClickListener + ", removeClickListener=" + this.removeClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
