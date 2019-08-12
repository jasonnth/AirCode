package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.ThemeUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.google.common.base.Strings;

public class WriteSavedMessageBodyFragment extends AirFragment {
    public static final int NUMBER_OF_BEGINNING_CHARACTERS_AS_TITLE = 10;
    @BindView
    AirEditTextView editText;

    public static WriteSavedMessageBodyFragment newInstance(String title, String body, long messageId, long threadId) {
        return (WriteSavedMessageBodyFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new WriteSavedMessageBodyFragment()).putSerializable(SavedMessageConstants.SAVED_MESSAGE_TITLE, title)).putSerializable(SavedMessageConstants.SAVED_MESSAGE_BODY, body)).putSerializable(SavedMessageConstants.SAVED_MESSAGE_ID, Long.valueOf(messageId))).putSerializable("thread_id", Long.valueOf(threadId))).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ThemeUtils.inflaterForPhonePadding(inflater).inflate(C0880R.layout.fragment_write_saved_message_body, container, false);
        bindViews(view);
        this.editText.requestFocus();
        this.editText.post(WriteSavedMessageBodyFragment$$Lambda$1.lambdaFactory$(this));
        String messageContent = (String) getArguments().getSerializable(SavedMessageConstants.SAVED_MESSAGE_BODY);
        if (!Strings.isNullOrEmpty(messageContent)) {
            this.editText.setText(messageContent);
        }
        setHasOptionsMenu(true);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.save, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
        String messageBody = this.editText.getText().toString();
        if (Strings.isNullOrEmpty(messageBody)) {
            getFragmentManager().popBackStack();
        } else {
            String messageTitle = (String) getArguments().getSerializable(SavedMessageConstants.SAVED_MESSAGE_TITLE);
            if (Strings.isNullOrEmpty(messageTitle)) {
                messageTitle = messageBody.substring(0, Math.min(messageBody.length(), 10));
            }
            ((CreateNewSavedMessageActivity) getActivity()).showFragment(CreateNewSavedMessageFragment.newInstanceForPreview(messageTitle, messageBody, ((Long) getArguments().getSerializable(SavedMessageConstants.SAVED_MESSAGE_ID)).longValue(), ((Long) getArguments().getSerializable("thread_id")).longValue()));
        }
        return true;
    }
}
