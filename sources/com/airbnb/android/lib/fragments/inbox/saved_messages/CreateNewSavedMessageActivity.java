package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.models.TemplateMessage;

public class CreateNewSavedMessageActivity extends SheetFlowActivity {
    public static Intent newIntent(Context context, TemplateMessage message, long threadId) {
        Intent intent = new Intent(context, CreateNewSavedMessageActivity.class);
        intent.putExtra(SavedMessageConstants.SAVED_MESSAGE, message);
        intent.putExtra("thread_id", threadId);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        CreateNewSavedMessageFragment fragment;
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            TemplateMessage message = (TemplateMessage) getIntent().getParcelableExtra(SavedMessageConstants.SAVED_MESSAGE);
            long threadId = getIntent().getLongExtra("thread_id", -1);
            if (message != null) {
                fragment = CreateNewSavedMessageFragment.newInstanceForEdit(message, threadId);
            } else {
                fragment = new CreateNewSavedMessageFragment();
            }
            super.showFragment(fragment);
        }
    }

    public boolean useHomeAsBack() {
        return true;
    }

    public SheetTheme getDefaultTheme() {
        return SheetTheme.WHITE;
    }
}
