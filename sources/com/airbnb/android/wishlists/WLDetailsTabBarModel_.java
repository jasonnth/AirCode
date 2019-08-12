package com.airbnb.android.wishlists;

import android.support.design.widget.TabLayout.OnTabSelectedListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class WLDetailsTabBarModel_ extends WLDetailsTabBarModel implements GeneratedModel<WLDetailsTabBar> {
    private OnModelBoundListener<WLDetailsTabBarModel_, WLDetailsTabBar> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<WLDetailsTabBarModel_, WLDetailsTabBar> onModelUnboundListener_epoxyGeneratedModel;

    public /* bridge */ /* synthetic */ void bind(WLDetailsTabBar wLDetailsTabBar) {
        super.bind(wLDetailsTabBar);
    }

    public /* bridge */ /* synthetic */ int getDividerViewType() {
        return super.getDividerViewType();
    }

    WLDetailsTabBarModel_() {
    }

    public void handlePreBind(EpoxyViewHolder holder, WLDetailsTabBar object, int position) {
    }

    public void handlePostBind(WLDetailsTabBar object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public WLDetailsTabBarModel_ onBind(OnModelBoundListener<WLDetailsTabBarModel_, WLDetailsTabBar> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(WLDetailsTabBar object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public WLDetailsTabBarModel_ onUnbind(OnModelUnboundListener<WLDetailsTabBarModel_, WLDetailsTabBar> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public WLDetailsTabBarModel_ selectedPosition(int selectedPosition) {
        onMutation();
        this.selectedPosition = selectedPosition;
        return this;
    }

    public int selectedPosition() {
        return this.selectedPosition;
    }

    public WLDetailsTabBarModel_ tabs(List<WLTab> tabs) {
        onMutation();
        this.tabs = tabs;
        return this;
    }

    public List<WLTab> tabs() {
        return this.tabs;
    }

    public WLDetailsTabBarModel_ onTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        onMutation();
        this.onTabSelectedListener = onTabSelectedListener;
        return this;
    }

    public OnTabSelectedListener onTabSelectedListener() {
        return this.onTabSelectedListener;
    }

    public WLDetailsTabBarModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public WLDetailsTabBarModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public WLDetailsTabBarModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public WLDetailsTabBarModel_ m1458id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public WLDetailsTabBarModel_ m1463id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public WLDetailsTabBarModel_ m1459id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public WLDetailsTabBarModel_ m1460id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public WLDetailsTabBarModel_ m1462id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public WLDetailsTabBarModel_ m1461id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public WLDetailsTabBarModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public WLDetailsTabBarModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public WLDetailsTabBarModel_ show() {
        super.show();
        return this;
    }

    public WLDetailsTabBarModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public WLDetailsTabBarModel_ hide() {
        super.hide();
        return this;
    }

    public WLDetailsTabBarModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.selectedPosition = 0;
        this.tabs = null;
        this.onTabSelectedListener = null;
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
        if (!(o instanceof WLDetailsTabBarModel_) || !super.equals(o)) {
            return false;
        }
        WLDetailsTabBarModel_ that = (WLDetailsTabBarModel_) o;
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
        if (this.tabs != null) {
            if (!this.tabs.equals(that.tabs)) {
                return false;
            }
        } else if (that.tabs != null) {
            return false;
        }
        if (this.onTabSelectedListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onTabSelectedListener == null)) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.tabs != null) {
            i2 = this.tabs.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.onTabSelectedListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "WLDetailsTabBarModel_{selectedPosition=" + this.selectedPosition + ", tabs=" + this.tabs + ", onTabSelectedListener=" + this.onTabSelectedListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
