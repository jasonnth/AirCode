package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.CheckInGuideStepCard;
import com.airbnb.p027n2.components.CheckInGuideStepCard.LoadingState;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class CheckInStepCardEpoxyModel_ extends CheckInStepCardEpoxyModel implements GeneratedModel<CheckInGuideStepCard> {
    private OnModelBoundListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, CheckInGuideStepCard object, int position) {
        if (this.imageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.imageClickListener).bind(holder, object);
        }
        if (this.errorStateClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.errorStateClickListener).bind(holder, object);
        }
        if (this.noteClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.noteClickListener).bind(holder, object);
        }
        if (this.buttonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.buttonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(CheckInGuideStepCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public CheckInStepCardEpoxyModel_ onBind(OnModelBoundListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(CheckInGuideStepCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public CheckInStepCardEpoxyModel_ onUnbind(OnModelUnboundListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public CheckInStepCardEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public CheckInStepCardEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public CheckInStepCardEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public CheckInStepCardEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public CheckInStepCardEpoxyModel_ buttonText(CharSequence buttonText) {
        onMutation();
        this.buttonText = buttonText;
        return this;
    }

    public CharSequence buttonText() {
        return this.buttonText;
    }

    public CheckInStepCardEpoxyModel_ buttonTextRes(int buttonTextRes) {
        onMutation();
        this.buttonTextRes = buttonTextRes;
        return this;
    }

    public int buttonTextRes() {
        return this.buttonTextRes;
    }

    public CheckInStepCardEpoxyModel_ errorStateText(CharSequence errorStateText) {
        onMutation();
        this.errorStateText = errorStateText;
        return this;
    }

    public CharSequence errorStateText() {
        return this.errorStateText;
    }

    public CheckInStepCardEpoxyModel_ errorStateTextRes(int errorStateTextRes) {
        onMutation();
        this.errorStateTextRes = errorStateTextRes;
        return this;
    }

    public int errorStateTextRes() {
        return this.errorStateTextRes;
    }

    public CheckInStepCardEpoxyModel_ note(CharSequence note) {
        onMutation();
        this.note = note;
        return this;
    }

    public CharSequence note() {
        return this.note;
    }

    public CheckInStepCardEpoxyModel_ notePromptRes(int notePromptRes) {
        onMutation();
        this.notePromptRes = notePromptRes;
        return this;
    }

    public int notePromptRes() {
        return this.notePromptRes;
    }

    public CheckInStepCardEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public CheckInStepCardEpoxyModel_ imageLoadingState(LoadingState imageLoadingState) {
        onMutation();
        this.imageLoadingState = imageLoadingState;
        return this;
    }

    public LoadingState imageLoadingState() {
        return this.imageLoadingState;
    }

    public CheckInStepCardEpoxyModel_ imageClickListener(OnModelClickListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> imageClickListener) {
        onMutation();
        if (imageClickListener == null) {
            this.imageClickListener = null;
        } else {
            this.imageClickListener = new WrappedEpoxyModelClickListener(this, imageClickListener);
        }
        return this;
    }

    public CheckInStepCardEpoxyModel_ imageClickListener(OnClickListener imageClickListener) {
        onMutation();
        this.imageClickListener = imageClickListener;
        return this;
    }

    public OnClickListener imageClickListener() {
        return this.imageClickListener;
    }

    public CheckInStepCardEpoxyModel_ errorStateClickListener(OnModelClickListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> errorStateClickListener) {
        onMutation();
        if (errorStateClickListener == null) {
            this.errorStateClickListener = null;
        } else {
            this.errorStateClickListener = new WrappedEpoxyModelClickListener(this, errorStateClickListener);
        }
        return this;
    }

    public CheckInStepCardEpoxyModel_ errorStateClickListener(OnClickListener errorStateClickListener) {
        onMutation();
        this.errorStateClickListener = errorStateClickListener;
        return this;
    }

    public OnClickListener errorStateClickListener() {
        return this.errorStateClickListener;
    }

    public CheckInStepCardEpoxyModel_ noteClickListener(OnModelClickListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> noteClickListener) {
        onMutation();
        if (noteClickListener == null) {
            this.noteClickListener = null;
        } else {
            this.noteClickListener = new WrappedEpoxyModelClickListener(this, noteClickListener);
        }
        return this;
    }

    public CheckInStepCardEpoxyModel_ noteClickListener(OnClickListener noteClickListener) {
        onMutation();
        this.noteClickListener = noteClickListener;
        return this;
    }

    public OnClickListener noteClickListener() {
        return this.noteClickListener;
    }

    public CheckInStepCardEpoxyModel_ buttonClickListener(OnModelClickListener<CheckInStepCardEpoxyModel_, CheckInGuideStepCard> buttonClickListener) {
        onMutation();
        if (buttonClickListener == null) {
            this.buttonClickListener = null;
        } else {
            this.buttonClickListener = new WrappedEpoxyModelClickListener(this, buttonClickListener);
        }
        return this;
    }

    public CheckInStepCardEpoxyModel_ buttonClickListener(OnClickListener buttonClickListener) {
        onMutation();
        this.buttonClickListener = buttonClickListener;
        return this;
    }

    public OnClickListener buttonClickListener() {
        return this.buttonClickListener;
    }

    public CheckInStepCardEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public CheckInStepCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public CheckInStepCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public CheckInStepCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public CheckInStepCardEpoxyModel_ m4447id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public CheckInStepCardEpoxyModel_ m4452id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public CheckInStepCardEpoxyModel_ m4448id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public CheckInStepCardEpoxyModel_ m4449id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public CheckInStepCardEpoxyModel_ m4451id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public CheckInStepCardEpoxyModel_ m4450id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public CheckInStepCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public CheckInStepCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public CheckInStepCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public CheckInStepCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public CheckInStepCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public CheckInStepCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.buttonText = null;
        this.buttonTextRes = 0;
        this.errorStateText = null;
        this.errorStateTextRes = 0;
        this.note = null;
        this.notePromptRes = 0;
        this.imageUrl = null;
        this.imageLoadingState = null;
        this.imageClickListener = null;
        this.errorStateClickListener = null;
        this.noteClickListener = null;
        this.buttonClickListener = null;
        this.displayOptions = null;
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
        if (!(o instanceof CheckInStepCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        CheckInStepCardEpoxyModel_ that = (CheckInStepCardEpoxyModel_) o;
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
        if (this.buttonText != null) {
            if (!this.buttonText.equals(that.buttonText)) {
                return false;
            }
        } else if (that.buttonText != null) {
            return false;
        }
        if (this.buttonTextRes != that.buttonTextRes) {
            return false;
        }
        if (this.errorStateText != null) {
            if (!this.errorStateText.equals(that.errorStateText)) {
                return false;
            }
        } else if (that.errorStateText != null) {
            return false;
        }
        if (this.errorStateTextRes != that.errorStateTextRes) {
            return false;
        }
        if (this.note != null) {
            if (!this.note.equals(that.note)) {
                return false;
            }
        } else if (that.note != null) {
            return false;
        }
        if (this.notePromptRes != that.notePromptRes) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.imageLoadingState != null) {
            if (!this.imageLoadingState.equals(that.imageLoadingState)) {
                return false;
            }
        } else if (that.imageLoadingState != null) {
            return false;
        }
        if (this.imageClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.imageClickListener == null)) {
            return false;
        }
        if (this.errorStateClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.errorStateClickListener == null)) {
            return false;
        }
        if (this.noteClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.noteClickListener == null)) {
            return false;
        }
        if (this.buttonClickListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.buttonClickListener == null)) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
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
        int i12;
        int i13;
        int i14 = 1;
        int i15 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i16 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i17 = (((i16 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i18 = (((i17 + i3) * 31) + this.subtitleRes) * 31;
        if (this.buttonText != null) {
            i4 = this.buttonText.hashCode();
        } else {
            i4 = 0;
        }
        int i19 = (((i18 + i4) * 31) + this.buttonTextRes) * 31;
        if (this.errorStateText != null) {
            i5 = this.errorStateText.hashCode();
        } else {
            i5 = 0;
        }
        int i20 = (((i19 + i5) * 31) + this.errorStateTextRes) * 31;
        if (this.note != null) {
            i6 = this.note.hashCode();
        } else {
            i6 = 0;
        }
        int i21 = (((i20 + i6) * 31) + this.notePromptRes) * 31;
        if (this.imageUrl != null) {
            i7 = this.imageUrl.hashCode();
        } else {
            i7 = 0;
        }
        int i22 = (i21 + i7) * 31;
        if (this.imageLoadingState != null) {
            i8 = this.imageLoadingState.hashCode();
        } else {
            i8 = 0;
        }
        int i23 = (i22 + i8) * 31;
        if (this.imageClickListener != null) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i24 = (i23 + i9) * 31;
        if (this.errorStateClickListener != null) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i25 = (i24 + i10) * 31;
        if (this.noteClickListener != null) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i26 = (i25 + i11) * 31;
        if (this.buttonClickListener == null) {
            i14 = 0;
        }
        int i27 = (i26 + i14) * 31;
        if (this.displayOptions != null) {
            i12 = this.displayOptions.hashCode();
        } else {
            i12 = 0;
        }
        int i28 = (i27 + i12) * 31;
        if (this.showDivider != null) {
            i13 = this.showDivider.hashCode();
        } else {
            i13 = 0;
        }
        int i29 = (i28 + i13) * 31;
        if (this.numCarouselItemsShown != null) {
            i15 = this.numCarouselItemsShown.hashCode();
        }
        return i29 + i15;
    }

    public String toString() {
        return "CheckInStepCardEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", buttonText=" + this.buttonText + ", buttonTextRes=" + this.buttonTextRes + ", errorStateText=" + this.errorStateText + ", errorStateTextRes=" + this.errorStateTextRes + ", note=" + this.note + ", notePromptRes=" + this.notePromptRes + ", imageUrl=" + this.imageUrl + ", imageLoadingState=" + this.imageLoadingState + ", imageClickListener=" + this.imageClickListener + ", errorStateClickListener=" + this.errorStateClickListener + ", noteClickListener=" + this.noteClickListener + ", buttonClickListener=" + this.buttonClickListener + ", displayOptions=" + this.displayOptions + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
