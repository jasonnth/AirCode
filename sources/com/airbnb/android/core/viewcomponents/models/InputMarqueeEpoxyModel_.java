package com.airbnb.android.core.viewcomponents.models;

import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.InputMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class InputMarqueeEpoxyModel_ extends InputMarqueeEpoxyModel implements GeneratedModel<InputMarquee> {
    private OnModelBoundListener<InputMarqueeEpoxyModel_, InputMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InputMarqueeEpoxyModel_, InputMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InputMarquee object, int position) {
        if (this.marqueeOnClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.marqueeOnClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InputMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InputMarqueeEpoxyModel_ onBind(OnModelBoundListener<InputMarqueeEpoxyModel_, InputMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InputMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InputMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<InputMarqueeEpoxyModel_, InputMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InputMarqueeEpoxyModel_ hint(int hint) {
        onMutation();
        this.hint = hint;
        return this;
    }

    public int hint() {
        return this.hint;
    }

    public InputMarqueeEpoxyModel_ inputEditViewDrawable(int inputEditViewDrawable) {
        onMutation();
        this.inputEditViewDrawable = inputEditViewDrawable;
        return this;
    }

    public int inputEditViewDrawable() {
        return this.inputEditViewDrawable;
    }

    public InputMarqueeEpoxyModel_ text(String text) {
        onMutation();
        this.text = text;
        return this;
    }

    public String text() {
        return this.text;
    }

    public InputMarqueeEpoxyModel_ editorActionListener(OnEditorActionListener editorActionListener) {
        onMutation();
        this.editorActionListener = editorActionListener;
        return this;
    }

    public OnEditorActionListener editorActionListener() {
        return this.editorActionListener;
    }

    public List<TextWatcher> textWatchers() {
        return this.textWatchers;
    }

    public InputMarqueeEpoxyModel_ marqueeOnClickListener(OnModelClickListener<InputMarqueeEpoxyModel_, InputMarquee> marqueeOnClickListener) {
        onMutation();
        if (marqueeOnClickListener == null) {
            this.marqueeOnClickListener = null;
        } else {
            this.marqueeOnClickListener = new WrappedEpoxyModelClickListener(this, marqueeOnClickListener);
        }
        return this;
    }

    public InputMarqueeEpoxyModel_ marqueeOnClickListener(OnClickListener marqueeOnClickListener) {
        onMutation();
        this.marqueeOnClickListener = marqueeOnClickListener;
        return this;
    }

    public OnClickListener marqueeOnClickListener() {
        return this.marqueeOnClickListener;
    }

    public InputMarqueeEpoxyModel_ forSearch(boolean forSearch) {
        onMutation();
        this.forSearch = forSearch;
        return this;
    }

    public boolean forSearch() {
        return this.forSearch;
    }

    public InputMarqueeEpoxyModel_ requestFocus(boolean requestFocus) {
        onMutation();
        this.requestFocus = requestFocus;
        return this;
    }

    public boolean requestFocus() {
        return this.requestFocus;
    }

    public InputMarqueeEpoxyModel_ showKeyboardOnFocus(boolean showKeyboardOnFocus) {
        onMutation();
        this.showKeyboardOnFocus = showKeyboardOnFocus;
        return this;
    }

    public boolean showKeyboardOnFocus() {
        return this.showKeyboardOnFocus;
    }

    public InputMarqueeEpoxyModel_ invertColors(boolean invertColors) {
        onMutation();
        this.invertColors = invertColors;
        return this;
    }

    public boolean invertColors() {
        return this.invertColors;
    }

    public InputMarqueeEpoxyModel_ backgroundColor(Integer backgroundColor) {
        onMutation();
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Integer backgroundColor() {
        return this.backgroundColor;
    }

    public InputMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InputMarqueeEpoxyModel_ addTextWatcher(TextWatcher textWatcher) {
        super.addTextWatcher(textWatcher);
        return this;
    }

    public InputMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InputMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InputMarqueeEpoxyModel_ m4930id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InputMarqueeEpoxyModel_ m4935id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InputMarqueeEpoxyModel_ m4931id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InputMarqueeEpoxyModel_ m4932id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InputMarqueeEpoxyModel_ m4934id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InputMarqueeEpoxyModel_ m4933id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InputMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InputMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InputMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InputMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InputMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_input_marquee;
    }

    public InputMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.hint = 0;
        this.inputEditViewDrawable = 0;
        this.text = null;
        this.editorActionListener = null;
        this.marqueeOnClickListener = null;
        this.forSearch = false;
        this.requestFocus = false;
        this.showKeyboardOnFocus = false;
        this.invertColors = false;
        this.backgroundColor = null;
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
        boolean z5;
        if (o == this) {
            return true;
        }
        if (!(o instanceof InputMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InputMarqueeEpoxyModel_ that = (InputMarqueeEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.hint != that.hint || this.inputEditViewDrawable != that.inputEditViewDrawable) {
            return false;
        }
        if (this.text != null) {
            if (!this.text.equals(that.text)) {
                return false;
            }
        } else if (that.text != null) {
            return false;
        }
        if (this.editorActionListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.editorActionListener == null)) {
            return false;
        }
        if (this.textWatchers == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.textWatchers == null)) {
            return false;
        }
        if (this.marqueeOnClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (that.marqueeOnClickListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z4 != z5 || this.forSearch != that.forSearch || this.requestFocus != that.requestFocus || this.showKeyboardOnFocus != that.showKeyboardOnFocus || this.invertColors != that.invertColors) {
            return false;
        }
        if (this.backgroundColor != null) {
            if (!this.backgroundColor.equals(that.backgroundColor)) {
                return false;
            }
        } else if (that.backgroundColor != null) {
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
        int i11 = 1;
        int i12 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i13 = (((((hashCode + i) * 31) + this.hint) * 31) + this.inputEditViewDrawable) * 31;
        if (this.text != null) {
            i2 = this.text.hashCode();
        } else {
            i2 = 0;
        }
        int i14 = (i13 + i2) * 31;
        if (this.editorActionListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i15 = (i14 + i3) * 31;
        if (this.textWatchers != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i16 = (i15 + i4) * 31;
        if (this.marqueeOnClickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i17 = (i16 + i5) * 31;
        if (this.forSearch) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i18 = (i17 + i6) * 31;
        if (this.requestFocus) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i19 = (i18 + i7) * 31;
        if (this.showKeyboardOnFocus) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i20 = (i19 + i8) * 31;
        if (!this.invertColors) {
            i11 = 0;
        }
        int i21 = (i20 + i11) * 31;
        if (this.backgroundColor != null) {
            i9 = this.backgroundColor.hashCode();
        } else {
            i9 = 0;
        }
        int i22 = (i21 + i9) * 31;
        if (this.showDivider != null) {
            i10 = this.showDivider.hashCode();
        } else {
            i10 = 0;
        }
        int i23 = (i22 + i10) * 31;
        if (this.numCarouselItemsShown != null) {
            i12 = this.numCarouselItemsShown.hashCode();
        }
        return i23 + i12;
    }

    public String toString() {
        return "InputMarqueeEpoxyModel_{hint=" + this.hint + ", inputEditViewDrawable=" + this.inputEditViewDrawable + ", text=" + this.text + ", editorActionListener=" + this.editorActionListener + ", textWatchers=" + this.textWatchers + ", marqueeOnClickListener=" + this.marqueeOnClickListener + ", forSearch=" + this.forSearch + ", requestFocus=" + this.requestFocus + ", showKeyboardOnFocus=" + this.showKeyboardOnFocus + ", invertColors=" + this.invertColors + ", backgroundColor=" + this.backgroundColor + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
