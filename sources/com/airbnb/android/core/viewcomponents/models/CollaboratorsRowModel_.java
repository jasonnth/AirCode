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
import com.airbnb.p027n2.components.CollaboratorsRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class CollaboratorsRowModel_ extends CollaboratorsRowModel implements GeneratedModel<CollaboratorsRow> {
    private OnModelBoundListener<CollaboratorsRowModel_, CollaboratorsRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<CollaboratorsRowModel_, CollaboratorsRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, CollaboratorsRow object, int position) {
        if (this.friendsClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.friendsClickListener).bind(holder, object);
        }
        if (this.inviteClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.inviteClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(CollaboratorsRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public CollaboratorsRowModel_ onBind(OnModelBoundListener<CollaboratorsRowModel_, CollaboratorsRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(CollaboratorsRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public CollaboratorsRowModel_ onUnbind(OnModelUnboundListener<CollaboratorsRowModel_, CollaboratorsRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public CollaboratorsRowModel_ friendsClickListener(OnModelClickListener<CollaboratorsRowModel_, CollaboratorsRow> friendsClickListener) {
        onMutation();
        if (friendsClickListener == null) {
            this.friendsClickListener = null;
        } else {
            this.friendsClickListener = new WrappedEpoxyModelClickListener(this, friendsClickListener);
        }
        return this;
    }

    public CollaboratorsRowModel_ friendsClickListener(OnClickListener friendsClickListener) {
        onMutation();
        this.friendsClickListener = friendsClickListener;
        return this;
    }

    public OnClickListener friendsClickListener() {
        return this.friendsClickListener;
    }

    public CollaboratorsRowModel_ inviteClickListener(OnModelClickListener<CollaboratorsRowModel_, CollaboratorsRow> inviteClickListener) {
        onMutation();
        if (inviteClickListener == null) {
            this.inviteClickListener = null;
        } else {
            this.inviteClickListener = new WrappedEpoxyModelClickListener(this, inviteClickListener);
        }
        return this;
    }

    public CollaboratorsRowModel_ inviteClickListener(OnClickListener inviteClickListener) {
        onMutation();
        this.inviteClickListener = inviteClickListener;
        return this;
    }

    public OnClickListener inviteClickListener() {
        return this.inviteClickListener;
    }

    public CollaboratorsRowModel_ imageUrls(List<String> imageUrls) {
        onMutation();
        this.imageUrls = imageUrls;
        return this;
    }

    public List<String> imageUrls() {
        return this.imageUrls;
    }

    public CollaboratorsRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public CollaboratorsRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public CollaboratorsRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public CollaboratorsRowModel_ m4495id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public CollaboratorsRowModel_ m4500id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public CollaboratorsRowModel_ m4496id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public CollaboratorsRowModel_ m4497id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public CollaboratorsRowModel_ m4499id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public CollaboratorsRowModel_ m4498id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public CollaboratorsRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public CollaboratorsRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public CollaboratorsRowModel_ show() {
        super.show();
        return this;
    }

    public CollaboratorsRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public CollaboratorsRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_model_collaborator_row;
    }

    public CollaboratorsRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.friendsClickListener = null;
        this.inviteClickListener = null;
        this.imageUrls = null;
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
        if (!(o instanceof CollaboratorsRowModel_) || !super.equals(o)) {
            return false;
        }
        CollaboratorsRowModel_ that = (CollaboratorsRowModel_) o;
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
        if (this.friendsClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.friendsClickListener == null)) {
            return false;
        }
        if (this.inviteClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.inviteClickListener == null)) {
            return false;
        }
        if (this.imageUrls != null) {
            if (!this.imageUrls.equals(that.imageUrls)) {
                return false;
            }
        } else if (that.imageUrls != null) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.friendsClickListener != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.inviteClickListener == null) {
            i5 = 0;
        }
        int i9 = (i8 + i5) * 31;
        if (this.imageUrls != null) {
            i3 = this.imageUrls.hashCode();
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
        return "CollaboratorsRowModel_{friendsClickListener=" + this.friendsClickListener + ", inviteClickListener=" + this.inviteClickListener + ", imageUrls=" + this.imageUrls + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
