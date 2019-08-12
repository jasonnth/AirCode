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
import com.airbnb.p027n2.epoxy.DefaultDividerBaseModel;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.BitSet;

/* renamed from: com.airbnb.n2.components.ListingInfoRowModel_ */
public class ListingInfoRowModel_ extends DefaultDividerBaseModel<ListingInfoRow> implements GeneratedModel<ListingInfoRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(15);
    private OnClickListener buttonClickListener_OnClickListener = null;
    private StringAttributeData buttonText_StringAttributeData = new StringAttributeData(null);
    private boolean enabled_Boolean = true;
    private int image_Int = 0;
    private String image_String = null;
    private StringAttributeData label_StringAttributeData = new StringAttributeData(null);
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<ListingInfoRowModel_, ListingInfoRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ListingInfoRowModel_, ListingInfoRow> onModelUnboundListener_epoxyGeneratedModel;
    private int progressBarPercentage_Int = 0;
    private boolean progressBarVisible_Boolean = false;
    private StringAttributeData subtitleText_StringAttributeData = new StringAttributeData(null);
    private int titleMaxLine_Int = 0;
    private StringAttributeData title_StringAttributeData = new StringAttributeData(null);

    public void handlePreBind(EpoxyViewHolder holder, ListingInfoRow object, int position) {
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

    public void bind(ListingInfoRow object) {
        super.bind(object);
        object.setEnabled(this.enabled_Boolean);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setButtonText(this.buttonText_StringAttributeData.toString(object.getContext()));
        object.setButtonClickListener(this.buttonClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setSubtitleText(this.subtitleText_StringAttributeData.toString(object.getContext()));
        object.setLabel(this.label_StringAttributeData.toString(object.getContext()));
        object.setTitleMaxLine(this.titleMaxLine_Int);
        object.setProgressBarVisible(this.progressBarVisible_Boolean);
        object.setProgressBarPercentage(this.progressBarPercentage_Int);
        if (this.assignedAttributes_epoxyGeneratedModel.get(5)) {
            object.setImage(this.image_String);
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(6)) {
            object.setImage(this.image_Int);
        } else {
            object.setImage((String) null);
        }
    }

    public void bind(ListingInfoRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (!(previousModel instanceof ListingInfoRowModel_)) {
            bind(object);
            return;
        }
        ListingInfoRowModel_ that = (ListingInfoRowModel_) previousModel;
        super.bind(object);
        if (this.enabled_Boolean != that.enabled_Boolean) {
            object.setEnabled(this.enabled_Boolean);
        }
        if ((this.onClickListener_OnClickListener == null) != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
        }
        if (!this.buttonText_StringAttributeData.equals(that.buttonText_StringAttributeData)) {
            object.setButtonText(this.buttonText_StringAttributeData.toString(object.getContext()));
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
        if (!this.subtitleText_StringAttributeData.equals(that.subtitleText_StringAttributeData)) {
            object.setSubtitleText(this.subtitleText_StringAttributeData.toString(object.getContext()));
        }
        if (!this.label_StringAttributeData.equals(that.label_StringAttributeData)) {
            object.setLabel(this.label_StringAttributeData.toString(object.getContext()));
        }
        if (this.titleMaxLine_Int != that.titleMaxLine_Int) {
            object.setTitleMaxLine(this.titleMaxLine_Int);
        }
        if (this.progressBarVisible_Boolean != that.progressBarVisible_Boolean) {
            object.setProgressBarVisible(this.progressBarVisible_Boolean);
        }
        if (this.progressBarPercentage_Int != that.progressBarPercentage_Int) {
            object.setProgressBarPercentage(this.progressBarPercentage_Int);
        }
        if (this.assignedAttributes_epoxyGeneratedModel.equals(that.assignedAttributes_epoxyGeneratedModel)) {
            if (this.assignedAttributes_epoxyGeneratedModel.get(5)) {
                if (this.image_String != null) {
                    if (this.image_String.equals(that.image_String)) {
                        return;
                    }
                } else if (that.image_String == null) {
                    return;
                }
                object.setImage(this.image_String);
            } else if (this.assignedAttributes_epoxyGeneratedModel.get(6) && this.image_Int != that.image_Int) {
                object.setImage(this.image_Int);
            }
        } else if (this.assignedAttributes_epoxyGeneratedModel.get(5) && !that.assignedAttributes_epoxyGeneratedModel.get(5)) {
            object.setImage(this.image_String);
        } else if (!this.assignedAttributes_epoxyGeneratedModel.get(6) || that.assignedAttributes_epoxyGeneratedModel.get(6)) {
            object.setImage((String) null);
        } else {
            object.setImage(this.image_Int);
        }
    }

    public void handlePostBind(ListingInfoRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(ListingInfoRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setImage((String) null);
        object.setButtonClickListener(null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public ListingInfoRowModel_ title(CharSequence title) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.title_StringAttributeData.setValue(title);
        return this;
    }

    public ListingInfoRowModel_ titleMaxLine(int titleMaxLine) {
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        onMutation();
        this.titleMaxLine_Int = titleMaxLine;
        return this;
    }

    public ListingInfoRowModel_ subtitleText(CharSequence subtitleText) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.subtitleText_StringAttributeData.setValue(subtitleText);
        return this;
    }

    public ListingInfoRowModel_ buttonText(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        this.buttonText_StringAttributeData.setValue(stringRes);
        return this;
    }

    public ListingInfoRowModel_ label(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(4);
        this.label_StringAttributeData.setValue(stringRes);
        return this;
    }

    public ListingInfoRowModel_ image(String image) {
        this.assignedAttributes_epoxyGeneratedModel.set(5);
        this.assignedAttributes_epoxyGeneratedModel.clear(6);
        this.image_Int = 0;
        onMutation();
        this.image_String = image;
        return this;
    }

    public ListingInfoRowModel_ image(int image) {
        this.assignedAttributes_epoxyGeneratedModel.set(6);
        this.assignedAttributes_epoxyGeneratedModel.clear(5);
        this.image_String = null;
        onMutation();
        this.image_Int = image;
        return this;
    }

    public ListingInfoRowModel_ progressBarVisible(boolean progressBarVisible) {
        this.assignedAttributes_epoxyGeneratedModel.set(7);
        onMutation();
        this.progressBarVisible_Boolean = progressBarVisible;
        return this;
    }

    public ListingInfoRowModel_ progressBarPercentage(int progressBarPercentage) {
        this.assignedAttributes_epoxyGeneratedModel.set(8);
        onMutation();
        this.progressBarPercentage_Int = progressBarPercentage;
        return this;
    }

    public ListingInfoRowModel_ buttonClickListener(OnClickListener buttonClickListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(9);
        onMutation();
        this.buttonClickListener_OnClickListener = buttonClickListener;
        return this;
    }

    public ListingInfoRowModel_ onClickListener(OnClickListener onClickListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(11);
        onMutation();
        this.onClickListener_OnClickListener = onClickListener;
        return this;
    }

    public ListingInfoRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(14);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ListingInfoRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ListingInfoRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ListingInfoRowModel_ m1530id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ListingInfoRowModel_ m1535id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ListingInfoRowModel_ m1531id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ListingInfoRowModel_ m1532id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ListingInfoRowModel_ m1534id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ListingInfoRowModel_ m1533id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ListingInfoRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ListingInfoRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ListingInfoRowModel_ show() {
        super.show();
        return this;
    }

    public ListingInfoRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ListingInfoRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_listing_info_row;
    }

    public ListingInfoRowModel_ withHackberryLayout() {
        layout(R.layout.n2_view_holder_listing_info_row_hackberry);
        return this;
    }

    public ListingInfoRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.title_StringAttributeData = new StringAttributeData(null);
        this.titleMaxLine_Int = 0;
        this.subtitleText_StringAttributeData = new StringAttributeData(null);
        this.buttonText_StringAttributeData = new StringAttributeData(null);
        this.label_StringAttributeData = new StringAttributeData(null);
        this.image_String = null;
        this.image_Int = 0;
        this.progressBarVisible_Boolean = false;
        this.progressBarPercentage_Int = 0;
        this.buttonClickListener_OnClickListener = null;
        this.enabled_Boolean = true;
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
        if (!(o instanceof ListingInfoRowModel_) || !super.equals(o)) {
            return false;
        }
        ListingInfoRowModel_ that = (ListingInfoRowModel_) o;
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
        if (this.titleMaxLine_Int != that.titleMaxLine_Int) {
            return false;
        }
        if (this.subtitleText_StringAttributeData != null) {
            if (!this.subtitleText_StringAttributeData.equals(that.subtitleText_StringAttributeData)) {
                return false;
            }
        } else if (that.subtitleText_StringAttributeData != null) {
            return false;
        }
        if (this.buttonText_StringAttributeData != null) {
            if (!this.buttonText_StringAttributeData.equals(that.buttonText_StringAttributeData)) {
                return false;
            }
        } else if (that.buttonText_StringAttributeData != null) {
            return false;
        }
        if (this.label_StringAttributeData != null) {
            if (!this.label_StringAttributeData.equals(that.label_StringAttributeData)) {
                return false;
            }
        } else if (that.label_StringAttributeData != null) {
            return false;
        }
        if (this.image_String != null) {
            if (!this.image_String.equals(that.image_String)) {
                return false;
            }
        } else if (that.image_String != null) {
            return false;
        }
        if (this.image_Int != that.image_Int || this.progressBarVisible_Boolean != that.progressBarVisible_Boolean || this.progressBarPercentage_Int != that.progressBarPercentage_Int) {
            return false;
        }
        if ((this.buttonClickListener_OnClickListener == null) != (that.buttonClickListener_OnClickListener == null) || this.enabled_Boolean != that.enabled_Boolean) {
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
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12 = 1;
        int i13 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i14 = (hashCode + i) * 31;
        if (this.title_StringAttributeData != null) {
            i2 = this.title_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i15 = (((i14 + i2) * 31) + this.titleMaxLine_Int) * 31;
        if (this.subtitleText_StringAttributeData != null) {
            i3 = this.subtitleText_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i16 = (i15 + i3) * 31;
        if (this.buttonText_StringAttributeData != null) {
            i4 = this.buttonText_StringAttributeData.hashCode();
        } else {
            i4 = 0;
        }
        int i17 = (i16 + i4) * 31;
        if (this.label_StringAttributeData != null) {
            i5 = this.label_StringAttributeData.hashCode();
        } else {
            i5 = 0;
        }
        int i18 = (i17 + i5) * 31;
        if (this.image_String != null) {
            i6 = this.image_String.hashCode();
        } else {
            i6 = 0;
        }
        int i19 = (((i18 + i6) * 31) + this.image_Int) * 31;
        if (this.progressBarVisible_Boolean) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i20 = (((i19 + i7) * 31) + this.progressBarPercentage_Int) * 31;
        if (this.buttonClickListener_OnClickListener != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i21 = (i20 + i8) * 31;
        if (this.enabled_Boolean) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i22 = (i21 + i9) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i23 = (i22 + i10) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i12 = 0;
        }
        int i24 = (i23 + i12) * 31;
        if (this.showDivider != null) {
            i11 = this.showDivider.hashCode();
        } else {
            i11 = 0;
        }
        int i25 = (i24 + i11) * 31;
        if (this.numCarouselItemsShown != null) {
            i13 = this.numCarouselItemsShown.hashCode();
        }
        return i25 + i13;
    }

    public String toString() {
        return "ListingInfoRowModel_{title_StringAttributeData=" + this.title_StringAttributeData + ", titleMaxLine_Int=" + this.titleMaxLine_Int + ", subtitleText_StringAttributeData=" + this.subtitleText_StringAttributeData + ", buttonText_StringAttributeData=" + this.buttonText_StringAttributeData + ", label_StringAttributeData=" + this.label_StringAttributeData + ", image_String=" + this.image_String + ", image_Int=" + this.image_Int + ", progressBarVisible_Boolean=" + this.progressBarVisible_Boolean + ", progressBarPercentage_Int=" + this.progressBarPercentage_Int + ", buttonClickListener_OnClickListener=" + this.buttonClickListener_OnClickListener + ", enabled_Boolean=" + this.enabled_Boolean + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
