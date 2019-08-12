package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.explore.C0857R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.InputSuggestionSubRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InputSuggestionSubRowEpoxyModel_ extends InputSuggestionSubRowEpoxyModel implements GeneratedModel<InputSuggestionSubRow> {
    private OnModelBoundListener<InputSuggestionSubRowEpoxyModel_, InputSuggestionSubRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InputSuggestionSubRowEpoxyModel_, InputSuggestionSubRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InputSuggestionSubRow object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InputSuggestionSubRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InputSuggestionSubRowEpoxyModel_ onBind(OnModelBoundListener<InputSuggestionSubRowEpoxyModel_, InputSuggestionSubRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InputSuggestionSubRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InputSuggestionSubRowEpoxyModel_ onUnbind(OnModelUnboundListener<InputSuggestionSubRowEpoxyModel_, InputSuggestionSubRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public InputSuggestionSubRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InputSuggestionSubRowEpoxyModel_ boldText(String boldText) {
        onMutation();
        this.boldText = boldText;
        return this;
    }

    public String boldText() {
        return this.boldText;
    }

    public InputSuggestionSubRowEpoxyModel_ boldTextRes(int boldTextRes) {
        onMutation();
        this.boldTextRes = boldTextRes;
        return this;
    }

    public int boldTextRes() {
        return this.boldTextRes;
    }

    public InputSuggestionSubRowEpoxyModel_ onClickListener(OnModelClickListener<InputSuggestionSubRowEpoxyModel_, InputSuggestionSubRow> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public InputSuggestionSubRowEpoxyModel_ invertColors(boolean invertColors) {
        onMutation();
        this.invertColors = invertColors;
        return this;
    }

    public boolean invertColors() {
        return this.invertColors;
    }

    public InputSuggestionSubRowEpoxyModel_ lastSubRow(boolean lastSubRow) {
        onMutation();
        this.lastSubRow = lastSubRow;
        return this;
    }

    public boolean lastSubRow() {
        return this.lastSubRow;
    }

    public InputSuggestionSubRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionSubRowEpoxyModel_ m5951id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionSubRowEpoxyModel_ m5956id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionSubRowEpoxyModel_ m5952id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionSubRowEpoxyModel_ m5953id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionSubRowEpoxyModel_ m5955id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionSubRowEpoxyModel_ m5954id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InputSuggestionSubRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.n2_view_holder_input_suggestion_sub_row;
    }

    public InputSuggestionSubRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.boldText = null;
        this.boldTextRes = 0;
        this.onClickListener = null;
        this.invertColors = false;
        this.lastSubRow = false;
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
        if (!(o instanceof InputSuggestionSubRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InputSuggestionSubRowEpoxyModel_ that = (InputSuggestionSubRowEpoxyModel_) o;
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
        if (this.boldText != null) {
            if (!this.boldText.equals(that.boldText)) {
                return false;
            }
        } else if (that.boldText != null) {
            return false;
        }
        if (this.boldTextRes != that.boldTextRes) {
            return false;
        }
        if (this.onClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener == null) || this.invertColors != that.invertColors || this.lastSubRow != that.lastSubRow) {
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
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (((i9 + i2) * 31) + this.titleRes) * 31;
        if (this.boldText != null) {
            i3 = this.boldText.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (((i10 + i3) * 31) + this.boldTextRes) * 31;
        if (this.onClickListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.invertColors) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (!this.lastSubRow) {
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
        return "InputSuggestionSubRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", boldText=" + this.boldText + ", boldTextRes=" + this.boldTextRes + ", onClickListener=" + this.onClickListener + ", invertColors=" + this.invertColors + ", lastSubRow=" + this.lastSubRow + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
