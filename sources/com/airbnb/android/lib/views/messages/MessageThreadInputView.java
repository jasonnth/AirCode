package com.airbnb.android.lib.views.messages;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.MagicalTripAttachment;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.TripStatus;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.MessageInputOneRow;
import com.airbnb.p027n2.components.MessageInputRow;
import com.airbnb.p027n2.components.MessageInputTwoRows;
import com.airbnb.p027n2.primitives.messaging.MessageInputListener;
import icepick.State;

public class MessageThreadInputView extends LinearLayout {
    private MessageInputRow currentRow;
    @State
    String imagePath;
    @BindView
    MessageInputOneRow oneRowView;
    @BindView
    MessageInputTwoRows twoRowsView;

    public MessageThreadInputView(Context context) {
        super(context);
        init();
    }

    public MessageThreadInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageThreadInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0880R.layout.message_input, this);
        ButterKnife.bind((View) this);
    }

    public void setupMessageInput(Context context, MessageInputListener listener, InboxType inboxType, Thread thread) {
        switch (inboxType) {
            case Guest:
            case GuestArchived:
            case ExperienceHost:
                setupButtonForInbox(context, listener, thread, inboxType);
                return;
            case Host:
            case HostArchived:
                if (thread.getThreadType().isCohostingThread()) {
                    setupButtonForInbox(context, listener, thread, inboxType);
                    return;
                } else {
                    setupButtonForHomeHostInbox(context, listener, thread);
                    return;
                }
            default:
                throw new IllegalStateException("Unknown inbox type");
        }
    }

    public String getInputText() {
        return this.currentRow.getInputText();
    }

    public void clearInputText() {
        this.currentRow.clearInputText();
    }

    public void setInputText(String text) {
        this.currentRow.setInputText(text);
    }

    public void setImagePreview(String imagePath2) {
        this.imagePath = imagePath2;
        this.currentRow.setImagePreview(imagePath2);
    }

    public void clearImagePreview() {
        this.imagePath = null;
        this.currentRow.clearImagePreview();
    }

    public String getImagePath() {
        return this.currentRow.getImagePath();
    }

    public ImageView getSavedMessageIcon() {
        return this.currentRow.getSavedMessageIcon();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
        if (!TextUtils.isEmpty(this.imagePath)) {
            setImagePreview(this.imagePath);
        }
    }

    private void setupButtonForHomeHostInbox(Context context, MessageInputListener listener, Thread thread) {
        if (isSendingImageEnabledForHomeHost(thread)) {
            this.oneRowView.setVisibility(8);
            this.twoRowsView.setVisibility(0);
            this.currentRow = this.twoRowsView;
            this.currentRow.setMessageInputListener(listener);
            setupMessageHintAndSendButton(this.twoRowsView, context, listener);
            return;
        }
        this.oneRowView.setVisibility(0);
        this.twoRowsView.setVisibility(8);
        this.currentRow = this.oneRowView;
        this.currentRow.setMessageInputListener(listener);
        this.oneRowView.setupSavedMessages();
        setupMessageHintAndSendButton(this.oneRowView, context, listener);
    }

    private void setupButtonForInbox(Context context, MessageInputListener listener, Thread thread, InboxType inboxType) {
        this.oneRowView.setVisibility(0);
        this.twoRowsView.setVisibility(8);
        this.currentRow = this.oneRowView;
        this.currentRow.setMessageInputListener(listener);
        setupMessageHintAndSendButton(this.oneRowView, context, listener);
        if (isSendingImageEnabled(thread, inboxType)) {
            this.oneRowView.setupCamera();
        } else {
            this.oneRowView.hideIcon();
        }
    }

    private void setupMessageHintAndSendButton(MessageInputRow row, Context context, MessageInputListener listener) {
        row.setMessageHint(context.getString(C0880R.string.thread_input_hint));
        row.setButtonText(context.getString(C0880R.string.send));
        row.setButtonClickListener(MessageThreadInputView$$Lambda$1.lambdaFactory$(listener));
    }

    private boolean isSendingImageEnabled(Thread thread, InboxType inboxType) {
        if (inboxType == InboxType.ExperienceHost) {
            return isTripAccepted(thread);
        }
        switch (thread.getThreadType()) {
            case PlaceBooking:
                if (thread.getReservationStatus() != ReservationStatus.Accepted) {
                    return false;
                }
                return true;
            case TripDirect:
            case TripGroup:
                return isTripAccepted(thread);
            case Cohost:
                return true;
            default:
                return false;
        }
    }

    private boolean isTripAccepted(Thread thread) {
        MagicalTripAttachment attachment = thread.getAttachment();
        if (attachment == null) {
            return false;
        }
        TripStatus status = attachment.getStatus();
        if (status == TripStatus.Accepted || status == TripStatus.Active) {
            return true;
        }
        return false;
    }

    private boolean isSendingImageEnabledForHomeHost(Thread thread) {
        return thread.getThreadType().isCohostingThread() || thread.getReservationStatus() == ReservationStatus.Accepted;
    }
}
