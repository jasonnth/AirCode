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

/* renamed from: com.airbnb.n2.components.NuxCoverCardModel_ */
public class NuxCoverCardModel_ extends NoDividerBaseModel<NuxCoverCard> implements GeneratedModel<NuxCoverCard> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(10);
    private OnClickListener buttonClickListener_OnClickListener = null;
    private StringAttributeData button_StringAttributeData = new StringAttributeData(null);
    private Image image_Image = null;
    private int image_Int = 0;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<NuxCoverCardModel_, NuxCoverCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<NuxCoverCardModel_, NuxCoverCard> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData subtitle_StringAttributeData = new StringAttributeData(null);
    private StringAttributeData title_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, NuxCoverCard object, int position) {
        if (this.buttonClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.buttonClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(NuxCoverCard object) {
        super.bind(object);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setButtonClickListener(this.buttonClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setButton(this.button_StringAttributeData.toString(object.getContext()));
        object.setSubtitle(this.subtitle_StringAttributeData.toString(object.getContext()));
        if (this.assignedAttributes_epoxyGeneratedModel.get(2)) {
            object.setImage(this.image_Int);
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(3)) {
            object.setImage(this.image_Image);
        } else {
            object.setImage((Image) null);
        }
    }

    public void bind(NuxCoverCard object, EpoxyModel previousModel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (!(previousModel instanceof NuxCoverCardModel_)) {
            bind(object);
            return;
        }
        NuxCoverCardModel_ that = (NuxCoverCardModel_) previousModel;
        super.bind(object);
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (this.buttonClickListener_OnClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.buttonClickListener_OnClickListener == null)) {
            object.setButtonClickListener(this.buttonClickListener_OnClickListener);
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (that.onLongClickListener_OnLongClickListener != null) {
            z3 = false;
        }
        if (z2 != z3) {
            object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        }
        if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
            object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        }
        if (!this.button_StringAttributeData.equals(that.button_StringAttributeData)) {
            object.setButton(this.button_StringAttributeData.toString(object.getContext()));
        }
        if (!this.subtitle_StringAttributeData.equals(that.subtitle_StringAttributeData)) {
            object.setSubtitle(this.subtitle_StringAttributeData.toString(object.getContext()));
        }
        if (this.assignedAttributes_epoxyGeneratedModel.equals(that.assignedAttributes_epoxyGeneratedModel)) {
            if (this.assignedAttributes_epoxyGeneratedModel.get(2)) {
                if (this.image_Int != that.image_Int) {
                    object.setImage(this.image_Int);
                }
            } else if (this.assignedAttributes_epoxyGeneratedModel.get(3)) {
                if (this.image_Image != null) {
                    if (this.image_Image.equals(that.image_Image)) {
                        return;
                    }
                } else if (that.image_Image == null) {
                    return;
                }
                object.setImage(this.image_Image);
            }
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(2) && !that.assignedAttributes_epoxyGeneratedModel.get(2)) {
            object.setImage(this.image_Int);
        } else if (!this.assignedAttributes_epoxyGeneratedModel.get(3) || that.assignedAttributes_epoxyGeneratedModel.get(3)) {
            object.setImage((Image) null);
        } else {
            object.setImage(this.image_Image);
        }
    }

    public void handlePostBind(NuxCoverCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(NuxCoverCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setSubtitle((CharSequence) null);
        object.setImage((Image) null);
        object.setButton((CharSequence) null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public NuxCoverCardModel_ title(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.title_StringAttributeData.setValue(stringRes);
        return this;
    }

    public NuxCoverCardModel_ subtitle(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.subtitle_StringAttributeData.setValue(stringRes);
        return this;
    }

    public NuxCoverCardModel_ image(int image) {
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.assignedAttributes_epoxyGeneratedModel.clear(3);
        this.image_Image = null;
        onMutation();
        this.image_Int = image;
        return this;
    }

    public NuxCoverCardModel_ button(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(4);
        this.button_StringAttributeData.setValue(stringRes);
        return this;
    }

    public NuxCoverCardModel_ buttonClickListener(OnClickListener buttonClickListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(5);
        onMutation();
        this.buttonClickListener_OnClickListener = buttonClickListener;
        return this;
    }

    public NuxCoverCardModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(9);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public NuxCoverCardModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public NuxCoverCardModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public NuxCoverCardModel_ m1554id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public NuxCoverCardModel_ m1559id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public NuxCoverCardModel_ m1555id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public NuxCoverCardModel_ m1556id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public NuxCoverCardModel_ m1558id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public NuxCoverCardModel_ m1557id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public NuxCoverCardModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public NuxCoverCardModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public NuxCoverCardModel_ show() {
        super.show();
        return this;
    }

    public NuxCoverCardModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public NuxCoverCardModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_nux_cover_card;
    }

    public NuxCoverCardModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.title_StringAttributeData = new StringAttributeData();
        this.subtitle_StringAttributeData = new StringAttributeData(null);
        this.image_Int = 0;
        this.image_Image = null;
        this.button_StringAttributeData = new StringAttributeData(null);
        this.buttonClickListener_OnClickListener = null;
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
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof NuxCoverCardModel_) || !super.equals(o)) {
            return false;
        }
        NuxCoverCardModel_ that = (NuxCoverCardModel_) o;
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
        if (this.title_StringAttributeData != null) {
            if (!this.title_StringAttributeData.equals(that.title_StringAttributeData)) {
                return false;
            }
        } else if (that.title_StringAttributeData != null) {
            return false;
        }
        if (this.subtitle_StringAttributeData != null) {
            if (!this.subtitle_StringAttributeData.equals(that.subtitle_StringAttributeData)) {
                return false;
            }
        } else if (that.subtitle_StringAttributeData != null) {
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
        if (this.button_StringAttributeData != null) {
            if (!this.button_StringAttributeData.equals(that.button_StringAttributeData)) {
                return false;
            }
        } else if (that.button_StringAttributeData != null) {
            return false;
        }
        if (this.buttonClickListener_OnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.buttonClickListener_OnClickListener == null)) {
            return false;
        }
        if (this.onClickListener_OnClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.onClickListener_OnClickListener == null)) {
            return false;
        }
        if (this.onLongClickListener_OnLongClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.onLongClickListener_OnLongClickListener == null)) {
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
        int i7;
        int i8;
        int i9 = 1;
        int i10 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i11 = (hashCode + i) * 31;
        if (this.title_StringAttributeData != null) {
            i2 = this.title_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i12 = (i11 + i2) * 31;
        if (this.subtitle_StringAttributeData != null) {
            i3 = this.subtitle_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i13 = (((i12 + i3) * 31) + this.image_Int) * 31;
        if (this.image_Image != null) {
            i4 = this.image_Image.hashCode();
        } else {
            i4 = 0;
        }
        int i14 = (i13 + i4) * 31;
        if (this.button_StringAttributeData != null) {
            i5 = this.button_StringAttributeData.hashCode();
        } else {
            i5 = 0;
        }
        int i15 = (i14 + i5) * 31;
        if (this.buttonClickListener_OnClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i16 = (i15 + i6) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i9 = 0;
        }
        int i18 = (i17 + i9) * 31;
        if (this.showDivider != null) {
            i8 = this.showDivider.hashCode();
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (this.numCarouselItemsShown != null) {
            i10 = this.numCarouselItemsShown.hashCode();
        }
        return i19 + i10;
    }

    public String toString() {
        return "NuxCoverCardModel_{title_StringAttributeData=" + this.title_StringAttributeData + ", subtitle_StringAttributeData=" + this.subtitle_StringAttributeData + ", image_Int=" + this.image_Int + ", image_Image=" + this.image_Image + ", button_StringAttributeData=" + this.button_StringAttributeData + ", buttonClickListener_OnClickListener=" + this.buttonClickListener_OnClickListener + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
