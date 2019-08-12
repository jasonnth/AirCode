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
import com.airbnb.p027n2.components.MessageTranslationRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class TranslationExpoyModel_ extends TranslationExpoyModel implements GeneratedModel<MessageTranslationRow> {
    private OnModelBoundListener<TranslationExpoyModel_, MessageTranslationRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<TranslationExpoyModel_, MessageTranslationRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MessageTranslationRow object, int position) {
        if (this.messageItemListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.messageItemListener).bind(holder, object);
        }
    }

    public void handlePostBind(MessageTranslationRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public TranslationExpoyModel_ onBind(OnModelBoundListener<TranslationExpoyModel_, MessageTranslationRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MessageTranslationRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public TranslationExpoyModel_ onUnbind(OnModelUnboundListener<TranslationExpoyModel_, MessageTranslationRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public TranslationExpoyModel_ statusText(CharSequence statusText) {
        onMutation();
        this.statusText = statusText;
        return this;
    }

    public CharSequence statusText() {
        return this.statusText;
    }

    public TranslationExpoyModel_ messageItemListener(OnModelClickListener<TranslationExpoyModel_, MessageTranslationRow> messageItemListener) {
        onMutation();
        if (messageItemListener == null) {
            this.messageItemListener = null;
        } else {
            this.messageItemListener = new WrappedEpoxyModelClickListener(this, messageItemListener);
        }
        return this;
    }

    public TranslationExpoyModel_ messageItemListener(OnClickListener messageItemListener) {
        onMutation();
        this.messageItemListener = messageItemListener;
        return this;
    }

    public OnClickListener messageItemListener() {
        return this.messageItemListener;
    }

    public TranslationExpoyModel_ showImageCaption(boolean showImageCaption) {
        onMutation();
        this.showImageCaption = showImageCaption;
        super.showImageCaption(showImageCaption);
        return this;
    }

    public boolean showImageCaption() {
        return this.showImageCaption;
    }

    public TranslationExpoyModel_ loadingState(boolean loadingState) {
        onMutation();
        this.loadingState = loadingState;
        return this;
    }

    public boolean loadingState() {
        return this.loadingState;
    }

    public TranslationExpoyModel_ showStatusText(boolean showStatusText) {
        onMutation();
        this.showStatusText = showStatusText;
        return this;
    }

    public boolean showStatusText() {
        return this.showStatusText;
    }

    public TranslationExpoyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public TranslationExpoyModel_ status(CharSequence s) {
        super.status(s);
        return this;
    }

    public TranslationExpoyModel_ clickListener(OnClickListener listener) {
        super.clickListener(listener);
        return this;
    }

    public TranslationExpoyModel_ toggleTranslationView(String text, boolean shouldShowImagecaption, boolean loadingState) {
        super.toggleTranslationView(text, shouldShowImagecaption, loadingState);
        return this;
    }

    public TranslationExpoyModel_ setIsLoading(boolean isLoading) {
        super.setIsLoading(isLoading);
        return this;
    }

    public TranslationExpoyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public TranslationExpoyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public TranslationExpoyModel_ m5722id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public TranslationExpoyModel_ m5727id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public TranslationExpoyModel_ m5723id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public TranslationExpoyModel_ m5724id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public TranslationExpoyModel_ m5726id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public TranslationExpoyModel_ m5725id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public TranslationExpoyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public TranslationExpoyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public TranslationExpoyModel_ show() {
        super.show();
        return this;
    }

    public TranslationExpoyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public TranslationExpoyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_message_translation_row;
    }

    public TranslationExpoyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.statusText = null;
        this.messageItemListener = null;
        this.showImageCaption = false;
        this.loadingState = false;
        this.showStatusText = false;
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
        if (!(o instanceof TranslationExpoyModel_) || !super.equals(o)) {
            return false;
        }
        TranslationExpoyModel_ that = (TranslationExpoyModel_) o;
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
        if (this.statusText != null) {
            if (!this.statusText.equals(that.statusText)) {
                return false;
            }
        } else if (that.statusText != null) {
            return false;
        }
        if (this.messageItemListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (that.messageItemListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z2 != z3 || this.showImageCaption != that.showImageCaption || this.loadingState != that.loadingState || this.showStatusText != that.showStatusText) {
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
        if (this.statusText != null) {
            i2 = this.statusText.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.messageItemListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.showImageCaption) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.loadingState) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (!this.showStatusText) {
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
        return "TranslationExpoyModel_{statusText=" + this.statusText + ", messageItemListener=" + this.messageItemListener + ", showImageCaption=" + this.showImageCaption + ", loadingState=" + this.loadingState + ", showStatusText=" + this.showStatusText + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
