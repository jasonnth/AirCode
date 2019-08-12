package com.airbnb.p027n2.components.decide.select;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.n2.R;
import com.airbnb.p027n2.epoxy.DefaultDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.utils.MapOptions;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.decide.select.SelectMapInterstitialModel_ */
public class SelectMapInterstitialModel_ extends DefaultDividerBaseModel<SelectMapInterstitial> implements GeneratedModel<SelectMapInterstitial> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(5);
    private MapOptions mapOptions_MapOptions;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<SelectMapInterstitialModel_, SelectMapInterstitial> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SelectMapInterstitialModel_, SelectMapInterstitial> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SelectMapInterstitial object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(SelectMapInterstitial object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setMapOptions(this.mapOptions_MapOptions);
    }

    public void bind(SelectMapInterstitial object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = true;
        if (!(previousModel instanceof SelectMapInterstitialModel_)) {
            bind(object);
            return;
        }
        SelectMapInterstitialModel_ that = (SelectMapInterstitialModel_) previousModel;
        super.bind(object);
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (that.onLongClickListener_OnLongClickListener != null) {
            z2 = false;
        }
        if (z != z2) {
            object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        }
        if (this.mapOptions_MapOptions != null) {
            if (this.mapOptions_MapOptions.equals(that.mapOptions_MapOptions)) {
                return;
            }
        } else if (that.mapOptions_MapOptions == null) {
            return;
        }
        object.setMapOptions(this.mapOptions_MapOptions);
    }

    public void handlePostBind(SelectMapInterstitial object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(SelectMapInterstitial object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
        object.clear();
    }

    public SelectMapInterstitialModel_ mapOptions(MapOptions mapOptions) {
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        onMutation();
        this.mapOptions_MapOptions = mapOptions;
        return this;
    }

    public SelectMapInterstitialModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(4);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SelectMapInterstitialModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SelectMapInterstitialModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SelectMapInterstitialModel_ m1662id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SelectMapInterstitialModel_ m1667id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SelectMapInterstitialModel_ m1663id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SelectMapInterstitialModel_ m1664id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SelectMapInterstitialModel_ m1666id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SelectMapInterstitialModel_ m1665id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SelectMapInterstitialModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SelectMapInterstitialModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SelectMapInterstitialModel_ show() {
        super.show();
        return this;
    }

    public SelectMapInterstitialModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SelectMapInterstitialModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_select_map_interstitial;
    }

    public SelectMapInterstitialModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.mapOptions_MapOptions = null;
        this.onClickListener_OnClickListener = null;
        this.onLongClickListener_OnLongClickListener = null;
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
        if (!(o instanceof SelectMapInterstitialModel_) || !super.equals(o)) {
            return false;
        }
        SelectMapInterstitialModel_ that = (SelectMapInterstitialModel_) o;
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
        if (this.mapOptions_MapOptions != null) {
            if (!this.mapOptions_MapOptions.equals(that.mapOptions_MapOptions)) {
                return false;
            }
        } else if (that.mapOptions_MapOptions != null) {
            return false;
        }
        if (this.onClickListener_OnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener_OnClickListener == null)) {
            return false;
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.onLongClickListener_OnLongClickListener == null)) {
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
        if (this.mapOptions_MapOptions != null) {
            i2 = this.mapOptions_MapOptions.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
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
        return "SelectMapInterstitialModel_{mapOptions_MapOptions=" + this.mapOptions_MapOptions + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
