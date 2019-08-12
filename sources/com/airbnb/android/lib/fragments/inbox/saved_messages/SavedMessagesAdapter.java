package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.TemplateMessage;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;

class SavedMessagesAdapter extends AirEpoxyAdapter {
    private final SavedMessageSelectedListener listener;
    @State
    ArrayList<TemplateMessage> savedMessages;

    SavedMessagesAdapter(SavedMessageSelectedListener listener2, Bundle savedInstanceState) {
        this.listener = listener2;
        onRestoreInstanceState(savedInstanceState);
        updateAllModels(false);
    }

    /* access modifiers changed from: 0000 */
    public void setSavedMessages(ArrayList<TemplateMessage> savedMessages2) {
        this.savedMessages = savedMessages2;
        updateAllModels(false);
    }

    /* access modifiers changed from: 0000 */
    public void displaySavedMessages(boolean isEditMode) {
        updateAllModels(isEditMode);
    }

    static /* synthetic */ boolean lambda$deleteSavedMessage$0(long messageId, EpoxyModel m) {
        return m.mo11715id() == messageId;
    }

    /* access modifiers changed from: 0000 */
    public void deleteSavedMessage(long messageId) {
        Optional<EpoxyModel<?>> match = FluentIterable.from((Iterable<E>) this.models).firstMatch(SavedMessagesAdapter$$Lambda$1.lambdaFactory$(messageId));
        if (match.isPresent()) {
            EpoxyModel<?> model = (EpoxyModel) match.get();
            int position = this.models.indexOf(model);
            this.models.remove(model);
            notifyItemRemoved(position);
        }
    }

    private void updateAllModels(boolean isEditMode) {
        this.models.clear();
        if (this.savedMessages != null) {
            this.models.addAll(FluentIterable.from((Iterable<E>) this.savedMessages).transform(SavedMessagesAdapter$$Lambda$2.lambdaFactory$(this, isEditMode)).toList());
        }
        notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public StandardRowEpoxyModel_ savedMessageToEpoxyModel(TemplateMessage message, boolean isEditMode) {
        StandardRowEpoxyModel_ model = new StandardRowEpoxyModel_().title((CharSequence) message.getTitle()).subtitle((CharSequence) message.getMessage()).titleMaxLine(1).subTitleMaxLine(5).m5602id(message.getId());
        if (!isEditMode) {
            return model.disclosure().clickListener(SavedMessagesAdapter$$Lambda$3.lambdaFactory$(this, message)).longClickListener((OnLongClickListener) null);
        }
        if (message.getIsEditable()) {
            return model.actionText(C0880R.string.edit).clickListener(SavedMessagesAdapter$$Lambda$4.lambdaFactory$(this, message)).longClickListener(SavedMessagesAdapter$$Lambda$5.lambdaFactory$(this, message));
        }
        return model.hideRowDrawable(true).actionText((CharSequence) null).clickListener((OnClickListener) null);
    }
}
