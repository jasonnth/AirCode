package com.airbnb.android.insights;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ActionCardCopy;
import com.airbnb.android.core.models.Insight;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InsightEpoxyModel extends AirEpoxyModel<InsightView> {
    OnClickListener dismissButtonClickListener;
    Insight insight;
    LoadingState loadingState;
    OnClickListener primaryButtonClickListener;
    OnClickListener secondaryButtonClickListener;

    public enum LoadingState {
        DEFAULT,
        PRIMARY_ACTION_LOADING,
        UNDO_ACTION_LOADING,
        DONE;

        public boolean isLoading() {
            return this == PRIMARY_ACTION_LOADING || this == UNDO_ACTION_LOADING;
        }
    }

    public void bind(InsightView view) {
        super.bind(view);
        ActionCardCopy insightCopy = this.insight.getCopies();
        view.setPrimaryButtonClickListener(this.primaryButtonClickListener);
        view.setSecondaryButtonClickListener(this.secondaryButtonClickListener);
        view.setDismissButtonClickListener(this.dismissButtonClickListener);
        switch (this.loadingState) {
            case DEFAULT:
                view.setPrimaryButtonLoading(false);
                setDefaultText(view, insightCopy);
                return;
            case DONE:
                view.setPrimaryButtonLoading(false);
                setConfirmationText(view, insightCopy);
                return;
            case PRIMARY_ACTION_LOADING:
                setDefaultText(view, insightCopy);
                disableClickListeners(view);
                view.setPrimaryButtonLoading(true);
                return;
            case UNDO_ACTION_LOADING:
                setConfirmationText(view, insightCopy);
                disableClickListeners(view);
                view.setPrimaryButtonLoading(true);
                return;
            default:
                return;
        }
    }

    private void disableClickListeners(InsightView view) {
        view.setPrimaryButtonClickListener(null);
        view.setSecondaryButtonClickListener(null);
        view.setDismissButtonClickListener(null);
    }

    private void setDefaultText(InsightView view, ActionCardCopy insightCopy) {
        view.setTitle((CharSequence) insightCopy.getTitle());
        view.setDescription((CharSequence) insightCopy.getBody());
        view.setPrimaryButtonText((CharSequence) insightCopy.getPrimaryCta());
        int secondaryStringRes = this.insight.getStoryConversionType().secondaryButtonStringRes;
        if (secondaryStringRes != 0) {
            view.setSecondaryButtonText(secondaryStringRes);
        } else {
            view.setSecondaryButtonText((CharSequence) null);
        }
    }

    private void setConfirmationText(InsightView view, ActionCardCopy insightCopy) {
        view.setTitle((CharSequence) insightCopy.getConfirmationTitle());
        view.setDescription((CharSequence) insightCopy.getConfirmationBody());
        view.setPrimaryButtonText(C6552R.string.next_insight);
        view.setSecondaryButtonText((CharSequence) insightCopy.getUndo());
    }
}
