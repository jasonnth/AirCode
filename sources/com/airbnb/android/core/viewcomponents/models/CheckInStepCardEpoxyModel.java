package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.p027n2.components.CheckInGuideStepCard;
import com.airbnb.p027n2.components.CheckInGuideStepCard.LoadingState;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class CheckInStepCardEpoxyModel extends AirEpoxyModel<CheckInGuideStepCard> {
    OnClickListener buttonClickListener;
    CharSequence buttonText;
    int buttonTextRes;
    DisplayOptions displayOptions;
    OnClickListener errorStateClickListener;
    CharSequence errorStateText;
    int errorStateTextRes;
    OnClickListener imageClickListener;
    LoadingState imageLoadingState = LoadingState.None;
    String imageUrl;
    CharSequence note;
    OnClickListener noteClickListener;
    int notePromptRes;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public int getDefaultLayout() {
        if (isCarousel()) {
            return C0716R.layout.view_holder_checkin_step_card_carousel;
        }
        return C0716R.layout.n2_view_holder_check_in_guide_step_card;
    }

    private boolean isCarousel() {
        return this.displayOptions != null && this.displayOptions.isCarousel();
    }

    public void bind(CheckInGuideStepCard card) {
        super.bind(card);
        Context context = card.getContext();
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(card);
        }
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubTitle = this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle;
        CharSequence actionButtonText = this.buttonTextRes != 0 ? context.getString(this.buttonTextRes) : this.buttonText;
        CharSequence actualErrorStateText = this.errorStateTextRes != 0 ? context.getString(this.errorStateTextRes) : this.errorStateText;
        card.setTitle(actualTitle);
        card.setSubtitle(actualSubTitle);
        card.setButtonText(actionButtonText);
        card.setErrorStateText(actualErrorStateText);
        card.setImageUrl(this.imageUrl);
        card.setImageLoadingState(this.imageLoadingState);
        if (!TextUtils.isEmpty(this.note)) {
            card.setNoteText(this.note);
        } else {
            card.setNoteText(SpannableUtils.makeColoredString(context.getString(this.notePromptRes), ContextCompat.getColor(context, C0716R.color.n2_babu)));
        }
        card.setImageClickListener(this.imageClickListener);
        card.setErrorStateClickListener(this.errorStateClickListener);
        card.setNoteClickListener(this.noteClickListener);
        card.setButtonClickListener(this.buttonClickListener);
    }

    public void unbind(CheckInGuideStepCard card) {
        super.unbind(card);
        card.setImageClickListener(null);
        card.setErrorStateClickListener(null);
        card.setNoteClickListener(null);
        card.setButtonClickListener(null);
    }

    public AirEpoxyModel<CheckInGuideStepCard> reset() {
        this.imageLoadingState = LoadingState.None;
        return super.reset();
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }

    public int getDividerViewType() {
        return -1;
    }
}
