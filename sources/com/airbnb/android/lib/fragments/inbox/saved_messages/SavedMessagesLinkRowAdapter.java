package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View.OnClickListener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;

public class SavedMessagesLinkRowAdapter extends AirEpoxyAdapter {
    public SavedMessagesLinkRowAdapter(OnClickListener clickNewSavedMessageListener) {
        this.models.add(new LinkActionRowEpoxyModel_().textRes(C0880R.string.canned_messages_create_new_saved_message_text).clickListener(clickNewSavedMessageListener));
    }
}
