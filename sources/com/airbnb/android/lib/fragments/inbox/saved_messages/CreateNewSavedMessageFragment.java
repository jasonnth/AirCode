package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.TemplateMessage;
import com.airbnb.android.core.requests.AddCannedMessageRequest;
import com.airbnb.android.core.requests.UpdateCannedMessageRequest;
import com.airbnb.android.core.responses.AddCannedMessageResponse;
import com.airbnb.android.core.responses.UpdateCannedMessageResponse;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.ThemeUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.google.common.base.Strings;
import p032rx.Observer;

public class CreateNewSavedMessageFragment extends AirFragment {
    final RequestListener<AddCannedMessageResponse> addSavedMessagesRequestListener = new C0699RL().onResponse(CreateNewSavedMessageFragment$$Lambda$1.lambdaFactory$(this)).onError(CreateNewSavedMessageFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    DocumentMarquee documentMarquee;
    @BindView
    AirEditTextView editTitleText;
    @BindView
    View fullLoader;
    MessagingJitneyLogger jitneyLogger;
    private String messageBody;
    private long messageId = -1;
    @BindView
    AirTextView messagePreviewBubble;
    private String messageTitle;
    private long threadId;
    final RequestListener<UpdateCannedMessageResponse> updateSavedMessagesRequestListener = new C0699RL().onResponse(CreateNewSavedMessageFragment$$Lambda$3.lambdaFactory$(this)).onError(CreateNewSavedMessageFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    TextView writeMessageBodyButton;

    static /* synthetic */ void lambda$new$0(CreateNewSavedMessageFragment createNewSavedMessageFragment, AddCannedMessageResponse response) {
        createNewSavedMessageFragment.getActivity().setResult(-1);
        createNewSavedMessageFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$2(CreateNewSavedMessageFragment createNewSavedMessageFragment, UpdateCannedMessageResponse response) {
        createNewSavedMessageFragment.getActivity().setResult(-1);
        createNewSavedMessageFragment.getActivity().finish();
    }

    public static CreateNewSavedMessageFragment newInstanceForPreview(String messageTitle2, String messageBody2, long messageId2, long threadId2) {
        return (CreateNewSavedMessageFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CreateNewSavedMessageFragment()).putSerializable(SavedMessageConstants.SAVED_MESSAGE_BODY, messageBody2)).putSerializable(SavedMessageConstants.SAVED_MESSAGE_TITLE, messageTitle2)).putSerializable(SavedMessageConstants.SAVED_MESSAGE_ID, Long.valueOf(messageId2))).putSerializable("thread_id", Long.valueOf(threadId2))).build();
    }

    public static CreateNewSavedMessageFragment newInstanceForEdit(TemplateMessage message, long threadId2) {
        return (CreateNewSavedMessageFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CreateNewSavedMessageFragment()).putSerializable(SavedMessageConstants.SAVED_MESSAGE_BODY, message.getMessage())).putSerializable(SavedMessageConstants.SAVED_MESSAGE_TITLE, message.getTitle())).putSerializable(SavedMessageConstants.SAVED_MESSAGE_ID, Long.valueOf(message.getId()))).putSerializable("thread_id", Long.valueOf(threadId2))).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (getArguments() != null) {
            this.messageBody = (String) getArguments().getSerializable(SavedMessageConstants.SAVED_MESSAGE_BODY);
            this.messageTitle = (String) getArguments().getSerializable(SavedMessageConstants.SAVED_MESSAGE_TITLE);
            this.messageId = ((Long) getArguments().getSerializable(SavedMessageConstants.SAVED_MESSAGE_ID)).longValue();
            this.threadId = ((Long) getArguments().getSerializable("thread_id")).longValue();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ThemeUtils.inflaterForPhonePadding(inflater).inflate(C0880R.layout.fragment_create_new_saved_message, container, false);
        bindViews(view);
        this.documentMarquee.setTitle(!Strings.isNullOrEmpty(this.messageTitle) ? C0880R.string.canned_messages_edit_saved_message_header_title : C0880R.string.canned_messages_create_new_saved_message_header_title);
        this.documentMarquee.setCaption(C0880R.string.canned_messages_create_new_saved_message_header_caption);
        this.writeMessageBodyButton.setOnClickListener(CreateNewSavedMessageFragment$$Lambda$5.lambdaFactory$(this));
        setHasOptionsMenu(true);
        if (!Strings.isNullOrEmpty(this.messageTitle) && this.editTitleText.isEmpty()) {
            this.editTitleText.setText(this.messageTitle);
        }
        this.messagePreviewBubble.setVisibility(8);
        this.writeMessageBodyButton.setText(C0880R.string.canned_messages_create_new_saved_message_write_message_body);
        if (!Strings.isNullOrEmpty(this.messageBody)) {
            this.messagePreviewBubble.setVisibility(0);
            this.messagePreviewBubble.setText(this.messageBody);
            this.writeMessageBodyButton.setText(C0880R.string.canned_messages_create_new_saved_message_edit_message_body);
        }
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.done, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.done) {
            return false;
        }
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
        String messageTitle2 = this.editTitleText.getText().toString();
        if (Strings.isNullOrEmpty(messageTitle2)) {
            return false;
        }
        this.fullLoader.setVisibility(0);
        if (isUnsavedMessage()) {
            this.jitneyLogger.savedMessageCreated();
            new AddCannedMessageRequest(messageTitle2, this.messageBody, this.threadId).withListener((Observer) this.addSavedMessagesRequestListener).execute(this.requestManager);
        } else {
            new UpdateCannedMessageRequest(this.messageId, messageTitle2, this.messageBody, this.threadId).withListener((Observer) this.updateSavedMessagesRequestListener).execute(this.requestManager);
        }
        return true;
    }

    private boolean isUnsavedMessage() {
        return this.messageId == -1;
    }

    public NavigationTag getNavigationTrackingTag() {
        return isUnsavedMessage() ? NavigationTag.SavedMessagesNew : NavigationTag.SavedMessagesEdit;
    }
}
