package com.airbnb.android.lib.tripassistant;

import android.os.Bundle;
import android.support.p000v4.util.LongSparseArray;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.requests.GetAttachmentsRequest;
import com.airbnb.android.core.responses.AttachmentsResponse;
import icepick.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AttachmentsProvider {
    final RequestListener<AttachmentsResponse> attachmentListener = new C0699RL().onResponse(AttachmentsProvider$$Lambda$1.lambdaFactory$(this)).build();
    private final LongSparseArray<List<Attachment>> attachments = new LongSparseArray<>();
    private final Set<OnAttachmentsUpdatedListener> listeners = new HashSet();
    private final RequestManager requestManager;
    @State
    HelpThread thread;

    public interface OnAttachmentsUpdatedListener {
        void onAttachmentsUpdated();
    }

    public AttachmentsProvider(HelpThread thread2, RequestManager requestManager2, Bundle savedInstanceState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        this.requestManager = requestManager2;
        requestManager2.subscribe(this);
        if (thread2 != null) {
            setThread(thread2);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void setThread(HelpThread thread2) {
        if (!thread2.equals(this.thread)) {
            this.thread = thread2;
            fetchAttachments();
        }
    }

    private void fetchAttachments() {
        for (HelpThreadIssue issue : this.thread.getIssues()) {
            if (issue.hasNodeWithAttachments() && !hasAttachmentsForIssue(issue) && !this.requestManager.hasRequest((BaseRequestListener<?>) this.attachmentListener, requestTagForIssue(issue))) {
                fetchAttachmentForIssue(issue);
            }
        }
    }

    public void setAttachmentsForIssue(HelpThreadIssue issue, List<Attachment> attachments2) {
        this.attachments.put(issue.getId(), new ArrayList(attachments2));
        fetchAttachmentForIssue(issue);
        notifyListeners();
    }

    public boolean hasAttachmentsForIssue(HelpThreadIssue issue) {
        return !getAttachmentsForIssue(issue).isEmpty();
    }

    public List<Attachment> getAttachmentsForIssue(HelpThreadIssue issue) {
        return getAttachmentsForIssueId(issue.getId());
    }

    private List<Attachment> getAttachmentsForIssueId(long issueId) {
        List<Attachment> attachments2 = (List) this.attachments.get(issueId);
        return attachments2 != null ? attachments2 : Collections.emptyList();
    }

    public void registerListener(OnAttachmentsUpdatedListener listener) {
        this.listeners.add(listener);
    }

    public void unregiserListener(OnAttachmentsUpdatedListener listener) {
        this.listeners.remove(listener);
    }

    private void fetchAttachmentForIssue(HelpThreadIssue issue) {
        this.requestManager.executeWithTag(GetAttachmentsRequest.forHelpThreadIssue(issue).doubleResponse().withListener(this.attachmentListener), requestTagForIssue(issue));
    }

    static /* synthetic */ void lambda$new$0(AttachmentsProvider attachmentsProvider, AttachmentsResponse data) {
        long issueId = ((GetAttachmentsRequest) data.metadata.request()).getAttachableId();
        List<Attachment> oldAttachments = attachmentsProvider.getAttachmentsForIssueId(issueId);
        List<Attachment> newAttachments = data.attachments;
        if (!newAttachments.equals(oldAttachments)) {
            attachmentsProvider.attachments.put(issueId, newAttachments);
            attachmentsProvider.notifyListeners();
        }
    }

    private static String requestTagForIssue(HelpThreadIssue issue) {
        return String.valueOf(issue.getId());
    }

    private void notifyListeners() {
        for (OnAttachmentsUpdatedListener listener : this.listeners) {
            listener.onAttachmentsUpdated();
        }
    }
}
