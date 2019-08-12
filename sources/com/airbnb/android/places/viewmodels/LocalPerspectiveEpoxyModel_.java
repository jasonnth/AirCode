package com.airbnb.android.places.viewmodels;

import com.airbnb.android.core.models.User;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.views.LocalPerspectiveView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class LocalPerspectiveEpoxyModel_ extends LocalPerspectiveEpoxyModel implements GeneratedModel<LocalPerspectiveView> {
    private OnModelBoundListener<LocalPerspectiveEpoxyModel_, LocalPerspectiveView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<LocalPerspectiveEpoxyModel_, LocalPerspectiveView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, LocalPerspectiveView object, int position) {
    }

    public void handlePostBind(LocalPerspectiveView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public LocalPerspectiveEpoxyModel_ onBind(OnModelBoundListener<LocalPerspectiveEpoxyModel_, LocalPerspectiveView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(LocalPerspectiveView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public LocalPerspectiveEpoxyModel_ onUnbind(OnModelUnboundListener<LocalPerspectiveEpoxyModel_, LocalPerspectiveView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public LocalPerspectiveEpoxyModel_ description(String description) {
        onMutation();
        this.description = description;
        return this;
    }

    public String description() {
        return this.description;
    }

    public LocalPerspectiveEpoxyModel_ sectionTitle(String sectionTitle) {
        onMutation();
        this.sectionTitle = sectionTitle;
        return this;
    }

    public String sectionTitle() {
        return this.sectionTitle;
    }

    public LocalPerspectiveEpoxyModel_ user(User user) {
        onMutation();
        this.user = user;
        return this;
    }

    public User user() {
        return this.user;
    }

    public LocalPerspectiveEpoxyModel_ tagline(String tagline) {
        onMutation();
        this.tagline = tagline;
        return this;
    }

    public String tagline() {
        return this.tagline;
    }

    public LocalPerspectiveEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public LocalPerspectiveEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public LocalPerspectiveEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public LocalPerspectiveEpoxyModel_ m6391id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public LocalPerspectiveEpoxyModel_ m6396id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public LocalPerspectiveEpoxyModel_ m6392id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public LocalPerspectiveEpoxyModel_ m6393id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public LocalPerspectiveEpoxyModel_ m6395id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public LocalPerspectiveEpoxyModel_ m6394id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public LocalPerspectiveEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public LocalPerspectiveEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public LocalPerspectiveEpoxyModel_ show() {
        super.show();
        return this;
    }

    public LocalPerspectiveEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public LocalPerspectiveEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C7627R.layout.view_holder_local_perspective;
    }

    public LocalPerspectiveEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.description = null;
        this.sectionTitle = null;
        this.user = null;
        this.tagline = null;
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
        if (!(o instanceof LocalPerspectiveEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        LocalPerspectiveEpoxyModel_ that = (LocalPerspectiveEpoxyModel_) o;
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
        if (this.description != null) {
            if (!this.description.equals(that.description)) {
                return false;
            }
        } else if (that.description != null) {
            return false;
        }
        if (this.sectionTitle != null) {
            if (!this.sectionTitle.equals(that.sectionTitle)) {
                return false;
            }
        } else if (that.sectionTitle != null) {
            return false;
        }
        if (this.user != null) {
            if (!this.user.equals(that.user)) {
                return false;
            }
        } else if (that.user != null) {
            return false;
        }
        if (this.tagline != null) {
            if (!this.tagline.equals(that.tagline)) {
                return false;
            }
        } else if (that.tagline != null) {
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
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i6 = 0;
        }
        int i8 = (hashCode + i6) * 31;
        if (this.description != null) {
            i = this.description.hashCode();
        } else {
            i = 0;
        }
        int i9 = (i8 + i) * 31;
        if (this.sectionTitle != null) {
            i2 = this.sectionTitle.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.user != null) {
            i3 = this.user.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.tagline != null) {
            i4 = this.tagline.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
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
        return "LocalPerspectiveEpoxyModel_{description=" + this.description + ", sectionTitle=" + this.sectionTitle + ", user=" + this.user + ", tagline=" + this.tagline + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
