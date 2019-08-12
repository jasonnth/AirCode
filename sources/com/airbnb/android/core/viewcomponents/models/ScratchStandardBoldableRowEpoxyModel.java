package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p002v7.content.res.AppCompatResources;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.ScratchStandardBoldableRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ScratchStandardBoldableRowEpoxyModel extends AirEpoxyModel<ScratchStandardBoldableRow> {
    public static final int ACTION = 0;
    public static final int INFO = 1;
    private static final int MAX_LINES_SUB_TITLE = 3;
    private static final int MAX_LINES_TITLE = 1;
    public static final int PLACEHOLDER = 2;
    boolean bold;
    OnClickListener clickListener;
    boolean enabled = true;
    boolean hideRowDrawable;
    OnLongClickListener longClickListener;
    OnClickListener rowDrawableClickListener;
    int rowDrawableRes;
    CharSequence subtitle;
    int subtitleMaxLine = 3;
    int subtitleRes;
    CharSequence text;
    int textMode;
    int textRes;
    CharSequence title;
    int titleMaxLine = 1;
    int titleRes;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TextMode {
    }

    public void bind(ScratchStandardBoldableRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubtitle = this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle;
        CharSequence actualText = this.textRes != 0 ? context.getString(this.textRes) : this.text;
        Drawable actualRowDrawable = this.rowDrawableRes != 0 ? AppCompatResources.getDrawable(context, this.rowDrawableRes) : null;
        view.setTitle(actualTitle);
        view.setSubtitleText(actualSubtitle);
        switch (this.textMode) {
            case 0:
                view.setActionText(actualText);
                break;
            case 1:
                view.setInfoText(actualText);
                break;
            case 2:
                view.setPlaceholderText(actualText);
                break;
        }
        view.setTitleMaxLine(this.titleMaxLine);
        view.setSubtitleMaxLine(this.subtitleMaxLine);
        view.setRowDrawable(actualRowDrawable);
        if (actualRowDrawable != null) {
            view.hideRowDrawable(this.hideRowDrawable);
        }
        view.setOnClickListener(this.clickListener);
        view.setOnLongClickListener(this.longClickListener);
        view.setClickable((this.clickListener == null && this.longClickListener == null) ? false : true);
        view.setRowDrawableClickListener(this.rowDrawableClickListener);
        view.setEnabled(this.enabled);
        view.setIsBold(this.bold);
    }

    public void unbind(ScratchStandardBoldableRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
        view.setRowDrawableClickListener(null);
    }

    public AirEpoxyModel<ScratchStandardBoldableRow> reset() {
        this.titleMaxLine = 1;
        this.subtitleMaxLine = 3;
        this.enabled = true;
        return super.reset();
    }

    public ScratchStandardBoldableRowEpoxyModel title(CharSequence title2) {
        this.titleRes = 0;
        this.title = title2;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel actionText(int actionTextRes) {
        this.textRes = actionTextRes;
        this.textMode = 0;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel actionText(CharSequence actionText) {
        this.textRes = 0;
        this.text = actionText;
        this.textMode = 0;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel infoText(int infoTextRes) {
        this.textRes = infoTextRes;
        this.textMode = 1;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel infoText(CharSequence infoText) {
        this.textRes = 0;
        this.text = infoText;
        this.textMode = 1;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel placeholderText(int placeholderTextRes) {
        this.textRes = placeholderTextRes;
        this.textMode = 2;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel placeholderText(CharSequence placeholderText) {
        this.textRes = 0;
        this.text = placeholderText;
        this.textMode = 2;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel subtitle(CharSequence subtitle2) {
        this.subtitleRes = 0;
        this.subtitle = subtitle2;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel subtitle(int subtitleRes2) {
        this.subtitleRes = subtitleRes2;
        return this;
    }

    public ScratchStandardBoldableRowEpoxyModel disclosure() {
        this.rowDrawableRes = C0716R.C0717drawable.n2_standard_row_right_caret_gray;
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
