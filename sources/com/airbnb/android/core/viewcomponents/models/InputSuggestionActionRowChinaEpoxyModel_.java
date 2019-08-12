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
import com.airbnb.p027n2.components.InputSuggestionActionRowChina;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InputSuggestionActionRowChinaEpoxyModel_ extends InputSuggestionActionRowChinaEpoxyModel implements GeneratedModel<InputSuggestionActionRowChina> {
    private OnModelBoundListener<InputSuggestionActionRowChinaEpoxyModel_, InputSuggestionActionRowChina> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InputSuggestionActionRowChinaEpoxyModel_, InputSuggestionActionRowChina> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InputSuggestionActionRowChina object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InputSuggestionActionRowChina object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InputSuggestionActionRowChinaEpoxyModel_ onBind(OnModelBoundListener<InputSuggestionActionRowChinaEpoxyModel_, InputSuggestionActionRowChina> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InputSuggestionActionRowChina object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InputSuggestionActionRowChinaEpoxyModel_ onUnbind(OnModelUnboundListener<InputSuggestionActionRowChinaEpoxyModel_, InputSuggestionActionRowChina> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ label(CharSequence label) {
        onMutation();
        this.label = label;
        return this;
    }

    public CharSequence label() {
        return this.label;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ onClickListener(OnModelClickListener<InputSuggestionActionRowChinaEpoxyModel_, InputSuggestionActionRowChina> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowChinaEpoxyModel_ m4942id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowChinaEpoxyModel_ m4947id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowChinaEpoxyModel_ m4943id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowChinaEpoxyModel_ m4944id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowChinaEpoxyModel_ m4946id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InputSuggestionActionRowChinaEpoxyModel_ m4945id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_input_suggestion_action_row_china;
    }

    public InputSuggestionActionRowChinaEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.label = null;
        this.onClickListener = null;
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
        if (!(o instanceof InputSuggestionActionRowChinaEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InputSuggestionActionRowChinaEpoxyModel_ that = (InputSuggestionActionRowChinaEpoxyModel_) o;
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
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.label != null) {
            if (!this.label.equals(that.label)) {
                return false;
            }
        } else if (that.label != null) {
            return false;
        }
        if (this.onClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener == null)) {
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
        int i9 = (i8 + i2) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.label != null) {
            i4 = this.label.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.onClickListener == null) {
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
        return "InputSuggestionActionRowChinaEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", label=" + this.label + ", onClickListener=" + this.onClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
