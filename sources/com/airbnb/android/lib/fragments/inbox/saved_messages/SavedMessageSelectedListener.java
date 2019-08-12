package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.android.core.models.TemplateMessage;

public interface SavedMessageSelectedListener {
    public static final String CHOSEN_SAVED_MESSAGE = "chosen_saved_message";

    boolean onLongClick(long j);

    void onMessageEdit(TemplateMessage templateMessage);

    void onMessageSelected(String str);
}
