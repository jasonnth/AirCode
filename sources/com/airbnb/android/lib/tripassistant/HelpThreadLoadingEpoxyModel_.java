package com.airbnb.android.lib.tripassistant;

import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.messaging.MessageItem;

public class HelpThreadLoadingEpoxyModel_ extends HelpThreadLoadingEpoxyModel implements GeneratedModel<MessageItem> {
    private OnModelBoundListener<HelpThreadLoadingEpoxyModel_, MessageItem> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HelpThreadLoadingEpoxyModel_, MessageItem> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MessageItem object, int position) {
    }

    public void handlePostBind(MessageItem object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HelpThreadLoadingEpoxyModel_ onBind(OnModelBoundListener<HelpThreadLoadingEpoxyModel_, MessageItem> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MessageItem object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HelpThreadLoadingEpoxyModel_ onUnbind(OnModelUnboundListener<HelpThreadLoadingEpoxyModel_, MessageItem> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HelpThreadLoadingEpoxyModel_ m6071id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HelpThreadLoadingEpoxyModel_ m6076id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HelpThreadLoadingEpoxyModel_ m6072id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HelpThreadLoadingEpoxyModel_ m6073id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HelpThreadLoadingEpoxyModel_ m6075id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HelpThreadLoadingEpoxyModel_ m6074id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HelpThreadLoadingEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_message_loading;
    }

    public HelpThreadLoadingEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
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
        if (!(o instanceof HelpThreadLoadingEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HelpThreadLoadingEpoxyModel_ that = (HelpThreadLoadingEpoxyModel_) o;
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = super.hashCode() * 31;
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i6 = (i5 + i3) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "HelpThreadLoadingEpoxyModel_{showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
