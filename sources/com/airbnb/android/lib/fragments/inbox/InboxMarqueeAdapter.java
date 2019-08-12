package com.airbnb.android.lib.fragments.inbox;

import android.content.Context;
import android.content.res.Resources;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EntryMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;

public final class InboxMarqueeAdapter extends AirEpoxyAdapter {
    private DocumentMarqueeEpoxyModel_ archiveHeaderViewModel = null;
    private final MicroRowEpoxyModel_ detailsRowModel = new MicroRowEpoxyModel_().showDivider(false).hide();
    private DocumentMarqueeEpoxyModel_ inboxCountViewModel = null;
    private final LoadingRowEpoxyModel loadingModel = new LoadingRowEpoxyModel_().hide();
    private long unreadCount;

    InboxMarqueeAdapter(InboxType inboxType, Context context) {
        switch (inboxType) {
            case Host:
                this.inboxCountViewModel = new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.greeting_inbox);
                addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.inboxCountViewModel});
                break;
            case ExperienceHost:
                addModels((EpoxyModel<?>[]) new EpoxyModel[]{new EntryMarqueeEpoxyModel_().title(C0880R.string.inbox_title_experience_host)});
                break;
            case GuestArchived:
            case HostArchived:
                this.archiveHeaderViewModel = new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.archived_threads);
                addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.archiveHeaderViewModel});
                break;
            case Guest:
                addModels((EpoxyModel<?>[]) new EpoxyModel[]{new EntryMarqueeEpoxyModel_().title(C0880R.string.title_messages)});
                break;
            default:
                throw new UnhandledStateException(inboxType);
        }
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.loadingModel, this.detailsRowModel});
    }

    /* access modifiers changed from: 0000 */
    public void setLoading(boolean loading) {
        this.loadingModel.show(loading);
        notifyModelChanged(this.loadingModel);
    }

    /* access modifiers changed from: 0000 */
    public void showError() {
        this.loadingModel.hide();
        this.detailsRowModel.textRes(C0880R.string.message_fetch_failed).show();
        notifyDataSetChanged();
    }

    /* access modifiers changed from: 0000 */
    public void setIsEmpty(Resources resources, boolean isEmpty, InboxType inboxType) {
        if (isEmpty) {
            this.loadingModel.hide();
            if (this.inboxCountViewModel != null) {
                this.inboxCountViewModel.captionText((CharSequence) null);
            }
        } else {
            setUnreadCount(resources, this.unreadCount);
        }
        if (this.archiveHeaderViewModel != null) {
            this.archiveHeaderViewModel.captionRes(isEmpty ? C0880R.string.explanation_of_archived_messages_none : C0880R.string.explanation_of_archived_messages_some);
        }
        this.detailsRowModel.textRes(FeatureToggles.shouldEnableSwipeToArchive(inboxType) && inboxType.archived ? C0880R.string.no_messages_archive_instruction_text : C0880R.string.no_messages_title_text).show(isEmpty);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: 0000 */
    public void setUnreadCount(Resources resources, long unreadCount2) {
        this.unreadCount = unreadCount2;
        if (this.inboxCountViewModel != null) {
            if (unreadCount2 > 0) {
                this.inboxCountViewModel.captionText((CharSequence) resources.getString(C0880R.string.number_of_threads_that_have_unread_messages, new Object[]{Long.valueOf(unreadCount2)}));
            } else {
                this.inboxCountViewModel.captionRes(C0880R.string.number_of_threads_that_have_unread_messages_none);
            }
            notifyModelChanged(this.inboxCountViewModel);
        }
    }

    /* access modifiers changed from: 0000 */
    public void incrementUnreadCount(Resources resources) {
        setUnreadCount(resources, this.unreadCount + 1);
    }

    /* access modifiers changed from: 0000 */
    public void decrementUnreadCount(Resources resources) {
        setUnreadCount(resources, this.unreadCount - 1);
    }
}
