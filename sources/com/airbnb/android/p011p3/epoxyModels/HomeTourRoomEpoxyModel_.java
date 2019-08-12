package com.airbnb.android.p011p3.epoxyModels;

import android.view.View.OnClickListener;
import com.airbnb.android.p011p3.C7532R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.primitives.imaging.Image;

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomEpoxyModel_ */
public class HomeTourRoomEpoxyModel_ extends HomeTourRoomEpoxyModel implements GeneratedModel<RoomViewHolder> {
    private OnModelBoundListener<HomeTourRoomEpoxyModel_, RoomViewHolder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeTourRoomEpoxyModel_, RoomViewHolder> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RoomViewHolder object, int position) {
        if (this.onImageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onImageClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(RoomViewHolder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeTourRoomEpoxyModel_ onBind(OnModelBoundListener<HomeTourRoomEpoxyModel_, RoomViewHolder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RoomViewHolder object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeTourRoomEpoxyModel_ onUnbind(OnModelUnboundListener<HomeTourRoomEpoxyModel_, RoomViewHolder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeTourRoomEpoxyModel_ image(Image image) {
        onMutation();
        this.image = image;
        return this;
    }

    public Image image() {
        return this.image;
    }

    public HomeTourRoomEpoxyModel_ name(String name) {
        onMutation();
        this.name = name;
        return this;
    }

    public String name() {
        return this.name;
    }

    public HomeTourRoomEpoxyModel_ subtitle(String subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public String subtitle() {
        return this.subtitle;
    }

    public HomeTourRoomEpoxyModel_ onImageClickListener(OnModelClickListener<HomeTourRoomEpoxyModel_, RoomViewHolder> onImageClickListener) {
        onMutation();
        if (onImageClickListener == null) {
            this.onImageClickListener = null;
        } else {
            this.onImageClickListener = new WrappedEpoxyModelClickListener(this, onImageClickListener);
        }
        return this;
    }

    public HomeTourRoomEpoxyModel_ onImageClickListener(OnClickListener onImageClickListener) {
        onMutation();
        this.onImageClickListener = onImageClickListener;
        return this;
    }

    public OnClickListener onImageClickListener() {
        return this.onImageClickListener;
    }

    public HomeTourRoomEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public HomeTourRoomEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomEpoxyModel_ m6347id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomEpoxyModel_ m6352id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomEpoxyModel_ m6348id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomEpoxyModel_ m6349id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomEpoxyModel_ m6351id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeTourRoomEpoxyModel_ m6350id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeTourRoomEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeTourRoomEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeTourRoomEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeTourRoomEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeTourRoomEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public RoomViewHolder createNewHolder() {
        return new RoomViewHolder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7532R.layout.home_tour_room_item;
    }

    public HomeTourRoomEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.image = null;
        this.name = null;
        this.subtitle = null;
        this.onImageClickListener = null;
        this.showDivider = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomeTourRoomEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeTourRoomEpoxyModel_ that = (HomeTourRoomEpoxyModel_) o;
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
        if (this.image != null) {
            if (!this.image.equals(that.image)) {
                return false;
            }
        } else if (that.image != null) {
            return false;
        }
        if (this.name != null) {
            if (!this.name.equals(that.name)) {
                return false;
            }
        } else if (that.name != null) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.onImageClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onImageClickListener == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
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
        if (this.image != null) {
            i2 = this.image.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.name != null) {
            i3 = this.name.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.subtitle != null) {
            i4 = this.subtitle.hashCode();
        } else {
            i4 = 0;
        }
        int i10 = (i9 + i4) * 31;
        if (this.onImageClickListener == null) {
            i5 = 0;
        }
        int i11 = (i10 + i5) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "HomeTourRoomEpoxyModel_{image=" + this.image + ", name=" + this.name + ", subtitle=" + this.subtitle + ", onImageClickListener=" + this.onImageClickListener + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
