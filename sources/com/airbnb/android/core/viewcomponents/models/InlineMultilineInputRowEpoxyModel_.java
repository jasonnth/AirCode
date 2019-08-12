package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;
import com.airbnb.p027n2.components.InlineMultilineInputRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InlineMultilineInputRowEpoxyModel_ extends InlineMultilineInputRowEpoxyModel implements GeneratedModel<InlineMultilineInputRow> {
    private OnModelBoundListener<InlineMultilineInputRowEpoxyModel_, InlineMultilineInputRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InlineMultilineInputRowEpoxyModel_, InlineMultilineInputRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InlineMultilineInputRow object, int position) {
    }

    public void handlePostBind(InlineMultilineInputRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InlineMultilineInputRowEpoxyModel_ onBind(OnModelBoundListener<InlineMultilineInputRowEpoxyModel_, InlineMultilineInputRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InlineMultilineInputRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InlineMultilineInputRowEpoxyModel_ onUnbind(OnModelUnboundListener<InlineMultilineInputRowEpoxyModel_, InlineMultilineInputRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InlineMultilineInputRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InlineMultilineInputRowEpoxyModel_ hint(CharSequence hint) {
        onMutation();
        this.hint = hint;
        return this;
    }

    public CharSequence hint() {
        return this.hint;
    }

    public InlineMultilineInputRowEpoxyModel_ hintRes(int hintRes) {
        onMutation();
        this.hintRes = hintRes;
        return this;
    }

    public int hintRes() {
        return this.hintRes;
    }

    public InlineMultilineInputRowEpoxyModel_ input(CharSequence input) {
        onMutation();
        this.input = input;
        return this;
    }

    public CharSequence input() {
        return this.input;
    }

    public InlineMultilineInputRowEpoxyModel_ inputRes(int inputRes) {
        onMutation();
        this.inputRes = inputRes;
        return this;
    }

    public int inputRes() {
        return this.inputRes;
    }

    public InlineMultilineInputRowEpoxyModel_ inputChangedListener(OnInputChangedListener inputChangedListener) {
        onMutation();
        this.inputChangedListener = inputChangedListener;
        return this;
    }

    public OnInputChangedListener inputChangedListener() {
        return this.inputChangedListener;
    }

    public InlineMultilineInputRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InlineMultilineInputRowEpoxyModel_ m4906id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InlineMultilineInputRowEpoxyModel_ m4911id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InlineMultilineInputRowEpoxyModel_ m4907id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InlineMultilineInputRowEpoxyModel_ m4908id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InlineMultilineInputRowEpoxyModel_ m4910id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InlineMultilineInputRowEpoxyModel_ m4909id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_inline_multiline_input_row;
    }

    public InlineMultilineInputRowEpoxyModel_ withNoDividerLayout() {
        layout(C0716R.layout.n2_view_holder_inline_multiline_input_row_no_divider);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ withOneLineLayout() {
        layout(C0716R.layout.n2_view_holder_inline_multiline_input_row_one_line);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ withRegularLayout() {
        layout(C0716R.layout.n2_view_holder_inline_multiline_input_row_regular);
        return this;
    }

    public InlineMultilineInputRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.hint = null;
        this.hintRes = 0;
        this.input = null;
        this.inputRes = 0;
        this.inputChangedListener = null;
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
        if (!(o instanceof InlineMultilineInputRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InlineMultilineInputRowEpoxyModel_ that = (InlineMultilineInputRowEpoxyModel_) o;
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
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.hint != null) {
            if (!this.hint.equals(that.hint)) {
                return false;
            }
        } else if (that.hint != null) {
            return false;
        }
        if (this.hintRes != that.hintRes) {
            return false;
        }
        if (this.input != null) {
            if (!this.input.equals(that.input)) {
                return false;
            }
        } else if (that.input != null) {
            return false;
        }
        if (this.inputRes != that.inputRes) {
            return false;
        }
        if (this.inputChangedListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.inputChangedListener == null)) {
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
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((i8 + i2) * 31) + this.titleRes) * 31;
        if (this.hint != null) {
            i3 = this.hint.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((i9 + i3) * 31) + this.hintRes) * 31;
        if (this.input != null) {
            i4 = this.input.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (((i10 + i4) * 31) + this.inputRes) * 31;
        if (this.inputChangedListener == null) {
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
        return "InlineMultilineInputRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", hint=" + this.hint + ", hintRes=" + this.hintRes + ", input=" + this.input + ", inputRes=" + this.inputRes + ", inputChangedListener=" + this.inputChangedListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
