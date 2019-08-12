package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.InputSuggestionActionRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InputSuggestionActionRowEpoxyModel_ extends InputSuggestionActionRowEpoxyModel implements GeneratedModel<InputSuggestionActionRow> {
    private OnModelBoundListener<InputSuggestionActionRowEpoxyModel_, InputSuggestionActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InputSuggestionActionRowEpoxyModel_, InputSuggestionActionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InputSuggestionActionRow object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InputSuggestionActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InputSuggestionActionRowEpoxyModel_ onBind(OnModelBoundListener<InputSuggestionActionRowEpoxyModel_, InputSuggestionActionRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InputSuggestionActionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InputSuggestionActionRowEpoxyModel_ onUnbind(OnModelUnboundListener<InputSuggestionActionRowEpoxyModel_, InputSuggestionActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InputSuggestionActionRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InputSuggestionActionRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public InputSuggestionActionRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public InputSuggestionActionRowEpoxyModel_ label(CharSequence label) {
        onMutation();
        this.label = label;
        return this;
    }

    public CharSequence label() {
        return this.label;
    }

    public InputSuggestionActionRowEpoxyModel_ iconRes(int iconRes) {
        onMutation();
        this.iconRes = iconRes;
        return this;
    }

    public int iconRes() {
        return this.iconRes;
    }

    public InputSuggestionActionRowEpoxyModel_ onClickListener(OnModelClickListener<InputSuggestionActionRowEpoxyModel_, InputSuggestionActionRow> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public InputSuggestionActionRowEpoxyModel_ hasSubRows(boolean hasSubRows) {
        onMutation();
        this.hasSubRows = hasSubRows;
        return this;
    }

    public boolean hasSubRows() {
        return this.hasSubRows;
    }

    public InputSuggestionActionRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowEpoxyModel_ m4954id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowEpoxyModel_ m4959id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowEpoxyModel_ m4955id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowEpoxyModel_ m4956id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowEpoxyModel_ m4958id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowEpoxyModel_ m4957id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_input_suggestion_action_row;
    }

    public InputSuggestionActionRowEpoxyModel_ withChinaLayout() {
        layout(C0716R.layout.n2_view_holder_input_suggestion_action_row_china);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ withInverseLayout() {
        layout(C0716R.layout.n2_view_holder_input_suggestion_action_row_inverse);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ withRegularLayout() {
        layout(C0716R.layout.n2_view_holder_input_suggestion_action_row_regular);
        return this;
    }

    public InputSuggestionActionRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.label = null;
        this.iconRes = 0;
        this.onClickListener = null;
        this.hasSubRows = false;
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
        if (!(o instanceof InputSuggestionActionRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InputSuggestionActionRowEpoxyModel_ that = (InputSuggestionActionRowEpoxyModel_) o;
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
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.subtitleRes != that.subtitleRes) {
            return false;
        }
        if (this.label != null) {
            if (!this.label.equals(that.label)) {
                return false;
            }
        } else if (that.label != null) {
            return false;
        }
        if (this.iconRes != that.iconRes) {
            return false;
        }
        if (this.onClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener == null) || this.hasSubRows != that.hasSubRows) {
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
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (((i10 + i3) * 31) + this.subtitleRes) * 31;
        if (this.label != null) {
            i4 = this.label.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (((i11 + i4) * 31) + this.iconRes) * 31;
        if (this.onClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (!this.hasSubRows) {
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
        return "InputSuggestionActionRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", label=" + this.label + ", iconRes=" + this.iconRes + ", onClickListener=" + this.onClickListener + ", hasSubRows=" + this.hasSubRows + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
