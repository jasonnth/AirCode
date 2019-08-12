package com.airbnb.p027n2.components;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
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

/* renamed from: com.airbnb.n2.components.EmptyStateCardModel_ */
public class EmptyStateCardModel_ extends NoDividerBaseModel<EmptyStateCard> implements GeneratedModel<EmptyStateCard> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(7);
    private Image image_Image = null;
    private int image_Int = 0;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<EmptyStateCardModel_, EmptyStateCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<EmptyStateCardModel_, EmptyStateCard> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData text_StringAttributeData = new StringAttributeData(null);

    public void handlePreBind(EpoxyViewHolder holder, EmptyStateCard object, int position) {
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(EmptyStateCard object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setText(this.text_StringAttributeData.toString(object.getContext()));
        if (this.assignedAttributes_epoxyGeneratedModel.get(1)) {
            object.setImage(this.image_Int);
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(2)) {
            object.setImage(this.image_Image);
        } else {
            object.setImage((Image) null);
        }
    }

    public void bind(EmptyStateCard object, EpoxyModel previousModel) {
        boolean z;
        boolean z2 = false;
        if (!(previousModel instanceof EmptyStateCardModel_)) {
            bind(object);
            return;
        }
        EmptyStateCardModel_ that = (EmptyStateCardModel_) previousModel;
        super.bind(object);
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (that.onLongClickListener_OnLongClickListener == null) {
            z2 = true;
        }
        if (z != z2) {
            object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        }
        if (!this.text_StringAttributeData.equals(that.text_StringAttributeData)) {
            object.setText(this.text_StringAttributeData.toString(object.getContext()));
        }
        if (this.assignedAttributes_epoxyGeneratedModel.equals(that.assignedAttributes_epoxyGeneratedModel)) {
            if (this.assignedAttributes_epoxyGeneratedModel.get(1)) {
                if (this.image_Int != that.image_Int) {
                    object.setImage(this.image_Int);
                }
            } else if (this.assignedAttributes_epoxyGeneratedModel.get(2)) {
                if (this.image_Image != null) {
                    if (this.image_Image.equals(that.image_Image)) {
                        return;
                    }
                } else if (that.image_Image == null) {
                    return;
                }
                object.setImage(this.image_Image);
            }
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(1) && !that.assignedAttributes_epoxyGeneratedModel.get(1)) {
            object.setImage(this.image_Int);
        } else if (!this.assignedAttributes_epoxyGeneratedModel.get(2) || that.assignedAttributes_epoxyGeneratedModel.get(2)) {
            object.setImage((Image) null);
        } else {
            object.setImage(this.image_Image);
        }
    }

    public void handlePostBind(EmptyStateCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(EmptyStateCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setText((CharSequence) null);
        object.setImage((Image) null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public EmptyStateCardModel_ text(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.text_StringAttributeData.setValue(stringRes);
        return this;
    }

    public EmptyStateCardModel_ image(int image) {
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.assignedAttributes_epoxyGeneratedModel.clear(2);
        this.image_Image = null;
        onMutation();
        this.image_Int = image;
        return this;
    }

    public EmptyStateCardModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(6);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public EmptyStateCardModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public EmptyStateCardModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public EmptyStateCardModel_ m1482id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public EmptyStateCardModel_ m1487id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public EmptyStateCardModel_ m1483id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public EmptyStateCardModel_ m1484id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public EmptyStateCardModel_ m1486id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public EmptyStateCardModel_ m1485id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public EmptyStateCardModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public EmptyStateCardModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public EmptyStateCardModel_ show() {
        super.show();
        return this;
    }

    public EmptyStateCardModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public EmptyStateCardModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_empty_state_card;
    }

    public EmptyStateCardModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.text_StringAttributeData = new StringAttributeData(null);
        this.image_Int = 0;
        this.image_Image = null;
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
        if (!(o instanceof EmptyStateCardModel_) || !super.equals(o)) {
            return false;
        }
        EmptyStateCardModel_ that = (EmptyStateCardModel_) o;
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
        if (this.text_StringAttributeData != null) {
            if (!this.text_StringAttributeData.equals(that.text_StringAttributeData)) {
                return false;
            }
        } else if (that.text_StringAttributeData != null) {
            return false;
        }
        if (this.image_Int != that.image_Int) {
            return false;
        }
        if (this.image_Image != null) {
            if (!this.image_Image.equals(that.image_Image)) {
                return false;
            }
        } else if (that.image_Image != null) {
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
        int i5;
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.text_StringAttributeData != null) {
            i2 = this.text_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((i8 + i2) * 31) + this.image_Int) * 31;
        if (this.image_Image != null) {
            i3 = this.image_Image.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
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
        return "EmptyStateCardModel_{text_StringAttributeData=" + this.text_StringAttributeData + ", image_Int=" + this.image_Int + ", image_Image=" + this.image_Image + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
