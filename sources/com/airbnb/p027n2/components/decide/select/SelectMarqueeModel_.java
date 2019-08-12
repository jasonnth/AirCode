package com.airbnb.p027n2.components.decide.select;

import android.view.View.OnClickListener;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.StringAttributeData;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.n2.R;
import com.airbnb.p027n2.epoxy.NoDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.imaging.Image;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.decide.select.SelectMarqueeModel_ */
public class SelectMarqueeModel_ extends NoDividerBaseModel<SelectMarquee> implements GeneratedModel<SelectMarquee> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(5);
    private OnClickListener homeTourClickListener_OnClickListener = null;
    private Image image_Image = null;
    private OnModelBoundListener<SelectMarqueeModel_, SelectMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SelectMarqueeModel_, SelectMarquee> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData title_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, SelectMarquee object, int position) {
        if (this.homeTourClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.homeTourClickListener_OnClickListener).bind(holder, object);
        }
    }

    public void bind(SelectMarquee object) {
        super.bind(object);
        object.setHomeTourClickListener(this.homeTourClickListener_OnClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setImage(this.image_Image);
    }

    public void bind(SelectMarquee object, EpoxyModel previousModel) {
        boolean z = true;
        if (!(previousModel instanceof SelectMarqueeModel_)) {
            bind(object);
            return;
        }
        SelectMarqueeModel_ that = (SelectMarqueeModel_) previousModel;
        super.bind(object);
        boolean z2 = this.homeTourClickListener_OnClickListener == null;
        if (that.homeTourClickListener_OnClickListener != null) {
            z = false;
        }
        if (z2 != z) {
            object.setHomeTourClickListener(this.homeTourClickListener_OnClickListener);
        }
        if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
            object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        }
        if (this.image_Image != null) {
            if (this.image_Image.equals(that.image_Image)) {
                return;
            }
        } else if (that.image_Image == null) {
            return;
        }
        object.setImage(this.image_Image);
    }

    public void handlePostBind(SelectMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(SelectMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setImage(null);
        object.setHomeTourClickListener(null);
    }

    public SelectMarqueeModel_ image(Image image) {
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        onMutation();
        this.image_Image = image;
        return this;
    }

    public SelectMarqueeModel_ title(CharSequence title) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.title_StringAttributeData.setValue(title);
        return this;
    }

    public SelectMarqueeModel_ homeTourClickListener(OnClickListener homeTourClickListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        onMutation();
        this.homeTourClickListener_OnClickListener = homeTourClickListener;
        return this;
    }

    public SelectMarqueeModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(4);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SelectMarqueeModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SelectMarqueeModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SelectMarqueeModel_ m1674id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SelectMarqueeModel_ m1679id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SelectMarqueeModel_ m1675id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SelectMarqueeModel_ m1676id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SelectMarqueeModel_ m1678id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SelectMarqueeModel_ m1677id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SelectMarqueeModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SelectMarqueeModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SelectMarqueeModel_ show() {
        super.show();
        return this;
    }

    public SelectMarqueeModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SelectMarqueeModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_select_marquee;
    }

    public SelectMarqueeModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.image_Image = null;
        this.title_StringAttributeData = new StringAttributeData();
        this.homeTourClickListener_OnClickListener = null;
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
        if (!(o instanceof SelectMarqueeModel_) || !super.equals(o)) {
            return false;
        }
        SelectMarqueeModel_ that = (SelectMarqueeModel_) o;
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
        if (this.image_Image != null) {
            if (!this.image_Image.equals(that.image_Image)) {
                return false;
            }
        } else if (that.image_Image != null) {
            return false;
        }
        if (this.title_StringAttributeData != null) {
            if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
                return false;
            }
        } else if (that.title_StringAttributeData != null) {
            return false;
        }
        if (this.homeTourClickListener_OnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.homeTourClickListener_OnClickListener == null)) {
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
        if (this.image_Image != null) {
            i2 = this.image_Image.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.title_StringAttributeData != null) {
            i3 = this.title_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.homeTourClickListener_OnClickListener == null) {
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
        return "SelectMarqueeModel_{image_Image=" + this.image_Image + ", title_StringAttributeData=" + this.title_StringAttributeData + ", homeTourClickListener_OnClickListener=" + this.homeTourClickListener_OnClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
