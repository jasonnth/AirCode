package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.SearchSuggestionNearbyView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class SearchSuggestionNearbyChinaEpoxyModel_ extends SearchSuggestionNearbyChinaEpoxyModel implements GeneratedModel<SearchSuggestionNearbyView> {
    private OnModelBoundListener<SearchSuggestionNearbyChinaEpoxyModel_, SearchSuggestionNearbyView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SearchSuggestionNearbyChinaEpoxyModel_, SearchSuggestionNearbyView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SearchSuggestionNearbyView object, int position) {
        if (this.nearbyClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.nearbyClickListener).bind(holder, object);
        }
        if (this.cityClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.cityClickListener).bind(holder, object);
        }
        if (this.anywhereClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.anywhereClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(SearchSuggestionNearbyView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ onBind(OnModelBoundListener<SearchSuggestionNearbyChinaEpoxyModel_, SearchSuggestionNearbyView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SearchSuggestionNearbyView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ onUnbind(OnModelUnboundListener<SearchSuggestionNearbyChinaEpoxyModel_, SearchSuggestionNearbyView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ cityName(String cityName) {
        onMutation();
        this.cityName = cityName;
        return this;
    }

    public String cityName() {
        return this.cityName;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ showNearBy(boolean showNearBy) {
        onMutation();
        this.showNearBy = showNearBy;
        return this;
    }

    public boolean showNearBy() {
        return this.showNearBy;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ nearbyClickListener(OnModelClickListener<SearchSuggestionNearbyChinaEpoxyModel_, SearchSuggestionNearbyView> nearbyClickListener) {
        onMutation();
        if (nearbyClickListener == null) {
            this.nearbyClickListener = null;
        } else {
            this.nearbyClickListener = new WrappedEpoxyModelClickListener(this, nearbyClickListener);
        }
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ nearbyClickListener(OnClickListener nearbyClickListener) {
        onMutation();
        this.nearbyClickListener = nearbyClickListener;
        return this;
    }

    public OnClickListener nearbyClickListener() {
        return this.nearbyClickListener;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ cityClickListener(OnModelClickListener<SearchSuggestionNearbyChinaEpoxyModel_, SearchSuggestionNearbyView> cityClickListener) {
        onMutation();
        if (cityClickListener == null) {
            this.cityClickListener = null;
        } else {
            this.cityClickListener = new WrappedEpoxyModelClickListener(this, cityClickListener);
        }
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ cityClickListener(OnClickListener cityClickListener) {
        onMutation();
        this.cityClickListener = cityClickListener;
        return this;
    }

    public OnClickListener cityClickListener() {
        return this.cityClickListener;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ anywhereClickListener(OnModelClickListener<SearchSuggestionNearbyChinaEpoxyModel_, SearchSuggestionNearbyView> anywhereClickListener) {
        onMutation();
        if (anywhereClickListener == null) {
            this.anywhereClickListener = null;
        } else {
            this.anywhereClickListener = new WrappedEpoxyModelClickListener(this, anywhereClickListener);
        }
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ anywhereClickListener(OnClickListener anywhereClickListener) {
        onMutation();
        this.anywhereClickListener = anywhereClickListener;
        return this;
    }

    public OnClickListener anywhereClickListener() {
        return this.anywhereClickListener;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionNearbyChinaEpoxyModel_ m5518id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionNearbyChinaEpoxyModel_ m5523id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionNearbyChinaEpoxyModel_ m5519id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionNearbyChinaEpoxyModel_ m5520id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionNearbyChinaEpoxyModel_ m5522id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionNearbyChinaEpoxyModel_ m5521id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_search_suggestion_nearby_china;
    }

    public SearchSuggestionNearbyChinaEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.cityName = null;
        this.showNearBy = false;
        this.nearbyClickListener = null;
        this.cityClickListener = null;
        this.anywhereClickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof SearchSuggestionNearbyChinaEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SearchSuggestionNearbyChinaEpoxyModel_ that = (SearchSuggestionNearbyChinaEpoxyModel_) o;
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
        if (this.cityName != null) {
            if (!this.cityName.equals(that.cityName)) {
                return false;
            }
        } else if (that.cityName != null) {
            return false;
        }
        if (this.showNearBy != that.showNearBy) {
            return false;
        }
        if (this.nearbyClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.nearbyClickListener == null)) {
            return false;
        }
        if (this.cityClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.cityClickListener == null)) {
            return false;
        }
        if (this.anywhereClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.anywhereClickListener == null)) {
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
        if (this.cityName != null) {
            i2 = this.cityName.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.showNearBy) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.nearbyClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.cityClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.anywhereClickListener == null) {
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
        return "SearchSuggestionNearbyChinaEpoxyModel_{cityName=" + this.cityName + ", showNearBy=" + this.showNearBy + ", nearbyClickListener=" + this.nearbyClickListener + ", cityClickListener=" + this.cityClickListener + ", anywhereClickListener=" + this.anywhereClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
