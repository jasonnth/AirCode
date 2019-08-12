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

/* renamed from: com.airbnb.n2.components.ProfileLinkRowModel_ */
public class ProfileLinkRowModel_ extends DefaultDividerBaseModel<ProfileLinkRow> implements GeneratedModel<ProfileLinkRow> {
    private final BitSet assignedAttributes_epoxyGeneratedModel = new BitSet(8);
    private StringAttributeData linkText_StringAttributeData = new StringAttributeData(null);
    private OnClickListener onClickLinkListener_OnClickListener = null;
    private OnClickListener onClickListener_OnClickListener = null;
    private OnLongClickListener onLongClickListener_OnLongClickListener = null;
    private OnModelBoundListener<ProfileLinkRowModel_, ProfileLinkRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ProfileLinkRowModel_, ProfileLinkRow> onModelUnboundListener_epoxyGeneratedModel;
    private StringAttributeData subtitle_StringAttributeData = new StringAttributeData(null);
    private StringAttributeData title_StringAttributeData = new StringAttributeData();

    public void handlePreBind(EpoxyViewHolder holder, ProfileLinkRow object, int position) {
        if (this.onClickLinkListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickLinkListener_OnClickListener).bind(holder, object);
        }
        if (this.onClickListener_OnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener_OnClickListener).bind(holder, object);
        }
        if (this.onLongClickListener_OnLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onLongClickListener_OnLongClickListener).bind(holder, object);
        }
    }

    public void bind(ProfileLinkRow object) {
        super.bind(object);
        object.setOnClickLinkListener(this.onClickLinkListener_OnClickListener);
        object.setOnClickListener(this.onClickListener_OnClickListener);
        object.setOnLongClickListener(this.onLongClickListener_OnLongClickListener);
        object.setTitle(this.title_StringAttributeData.toString(object.getContext()));
        object.setLinkText(this.linkText_StringAttributeData.toString(object.getContext()));
        object.setSubtitle(this.subtitle_StringAttributeData.toString(object.getContext()));
    }

    public void bind(ProfileLinkRow object, EpoxyModel previousModel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (!(previousModel instanceof ProfileLinkRowModel_)) {
            bind(object);
            return;
        }
        ProfileLinkRowModel_ that = (ProfileLinkRowModel_) previousModel;
        super.bind(object);
        if ((this.onClickLinkListener_OnClickListener == null) != (that.onClickLinkListener_OnClickListener == null)) {
            object.setOnClickLinkListener(this.onClickLinkListener_OnClickListener);
        }
        if (this.onClickListener_OnClickListener == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onClickListener_OnClickListener == null)) {
            object.setOnClickListener(this.onClickListener_OnClickListener);
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
        if (!this.linkText_StringAttributeData.equals(that.linkText_StringAttributeData)) {
            object.setLinkText(this.linkText_StringAttributeData.toString(object.getContext()));
        }
        if (!this.subtitle_StringAttributeData.equals(that.subtitle_StringAttributeData)) {
            object.setSubtitle(this.subtitle_StringAttributeData.toString(object.getContext()));
        }
    }

    public void handlePostBind(ProfileLinkRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public void unbind(ProfileLinkRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
        object.setOnClickLinkListener(null);
        object.setOnClickListener(null);
        object.setOnLongClickListener(null);
    }

    public ProfileLinkRowModel_ title(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(0);
        this.title_StringAttributeData.setValue(stringRes);
        return this;
    }

    public ProfileLinkRowModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(1);
        this.subtitle_StringAttributeData.setValue(subtitle);
        return this;
    }

    public ProfileLinkRowModel_ linkText(int stringRes) {
        onMutation();
        this.assignedAttributes_epoxyGeneratedModel.set(2);
        this.linkText_StringAttributeData.setValue(stringRes);
        return this;
    }

    public ProfileLinkRowModel_ onClickLinkListener(OnClickListener onClickLinkListener) {
        this.assignedAttributes_epoxyGeneratedModel.set(3);
        onMutation();
        this.onClickLinkListener_OnClickListener = onClickLinkListener;
        return this;
    }

    public ProfileLinkRowModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        this.assignedAttributes_epoxyGeneratedModel.set(7);
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ProfileLinkRowModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ProfileLinkRowModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ProfileLinkRowModel_ m1566id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ProfileLinkRowModel_ m1571id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ProfileLinkRowModel_ m1567id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ProfileLinkRowModel_ m1568id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ProfileLinkRowModel_ m1570id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ProfileLinkRowModel_ m1569id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ProfileLinkRowModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ProfileLinkRowModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ProfileLinkRowModel_ show() {
        super.show();
        return this;
    }

    public ProfileLinkRowModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ProfileLinkRowModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return R.layout.n2_view_holder_profile_link_row;
    }

    public ProfileLinkRowModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.assignedAttributes_epoxyGeneratedModel.clear();
        this.title_StringAttributeData = new StringAttributeData();
        this.subtitle_StringAttributeData = new StringAttributeData(null);
        this.linkText_StringAttributeData = new StringAttributeData(null);
        this.onClickLinkListener_OnClickListener = null;
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
        if (!(o instanceof ProfileLinkRowModel_) || !super.equals(o)) {
            return false;
        }
        ProfileLinkRowModel_ that = (ProfileLinkRowModel_) o;
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
        if (this.linkText_StringAttributeData != null) {
            if (!this.linkText_StringAttributeData.equals(that.linkText_StringAttributeData)) {
                return false;
            }
        } else if (that.linkText_StringAttributeData != null) {
            return false;
        }
        if (this.onClickLinkListener_OnClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickLinkListener_OnClickListener == null)) {
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
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.title_StringAttributeData != null) {
            i2 = this.title_StringAttributeData.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.subtitle_StringAttributeData != null) {
            i3 = this.subtitle_StringAttributeData.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.linkText_StringAttributeData != null) {
            i4 = this.linkText_StringAttributeData.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.onClickLinkListener_OnClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.onClickListener_OnClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.onLongClickListener_OnLongClickListener == null) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "ProfileLinkRowModel_{title_StringAttributeData=" + this.title_StringAttributeData + ", subtitle_StringAttributeData=" + this.subtitle_StringAttributeData + ", linkText_StringAttributeData=" + this.linkText_StringAttributeData + ", onClickLinkListener_OnClickListener=" + this.onClickLinkListener_OnClickListener + ", onClickListener_OnClickListener=" + this.onClickListener_OnClickListener + ", onLongClickListener_OnLongClickListener=" + this.onLongClickListener_OnLongClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
