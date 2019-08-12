package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.MessageTranslationRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class TranslationExpoyModel extends AirEpoxyModel<MessageTranslationRow> {
    private final int imageCaptionResource = C0716R.C0717drawable.n2_translated_by_google;
    boolean loadingState;
    OnClickListener messageItemListener;
    boolean showImageCaption;
    boolean showStatusText;
    CharSequence statusText;

    public void bind(MessageTranslationRow c) {
        super.bind(c);
        c.setStatusText(this.statusText);
        c.setOnClickListener(this.messageItemListener);
        c.setImageCaption(this.imageCaptionResource);
        c.showImageCaption(this.showImageCaption);
        c.setLoadingState(this.loadingState);
        c.setStatusTextVisibility(this.showStatusText);
    }

    public TranslationExpoyModel status(CharSequence s) {
        this.statusText = s;
        return this;
    }

    public TranslationExpoyModel clickListener(OnClickListener listener) {
        this.messageItemListener = listener;
        return this;
    }

    public TranslationExpoyModel showImageCaption(boolean shouldShowImageCaption) {
        this.showImageCaption = shouldShowImageCaption;
        return this;
    }

    private TranslationExpoyModel toggleStatusTextVisibility(boolean shouldShow) {
        this.showStatusText = shouldShow;
        return this;
    }

    public TranslationExpoyModel toggleTranslationView(String text, boolean shouldShowImagecaption, boolean loadingState2) {
        status(text);
        showImageCaption(shouldShowImagecaption);
        setIsLoading(loadingState2);
        return this;
    }

    public TranslationExpoyModel setIsLoading(boolean isLoading) {
        this.loadingState = isLoading;
        toggleStatusTextVisibility(!isLoading);
        return this;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.messageItemListener = listener;
    }
}
