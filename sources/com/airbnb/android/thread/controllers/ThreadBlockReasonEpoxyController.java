package com.airbnb.android.thread.controllers;

import android.view.View;
import com.airbnb.android.core.models.UserFlagDetail;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.thread.C1729R;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThreadBlockReasonEpoxyController extends AirEpoxyController {
    DocumentMarqueeEpoxyModel_ docMarquee;
    private final ThreadBlockReasonListener listener;
    @State
    String selectedReason;
    @State
    ArrayList<UserFlagDetail> userFlagDetails;

    public interface ThreadBlockReasonListener {
        void enableSubmit(String str);
    }

    public ThreadBlockReasonEpoxyController(List<UserFlagDetail> userFlagDetails2, String selectedReason2, ThreadBlockReasonListener listener2) {
        this.userFlagDetails = new ArrayList<>(userFlagDetails2);
        this.selectedReason = selectedReason2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.docMarquee.titleRes(C1729R.string.message_block_what_happened_title).captionRes(C1729R.string.message_block_what_happened_caption);
        int id = 0;
        Iterator it = this.userFlagDetails.iterator();
        while (it.hasNext()) {
            addReasonToggle((long) id, (UserFlagDetail) it.next());
            id++;
        }
    }

    private void addReasonToggle(long id, UserFlagDetail userFlagDetail) {
        new ToggleActionRowEpoxyModel_().m5698id(id).title(userFlagDetail.getReasonNameLocalized()).checked(userFlagDetail.getReasonName().equals(this.selectedReason)).clickListener(ThreadBlockReasonEpoxyController$$Lambda$1.lambdaFactory$(this, userFlagDetail)).addTo(this);
    }

    static /* synthetic */ void lambda$addReasonToggle$0(ThreadBlockReasonEpoxyController threadBlockReasonEpoxyController, UserFlagDetail userFlagDetail, View v) {
        threadBlockReasonEpoxyController.selectedReason = userFlagDetail.getReasonName();
        threadBlockReasonEpoxyController.listener.enableSubmit(threadBlockReasonEpoxyController.selectedReason);
        threadBlockReasonEpoxyController.requestModelBuild();
    }
}
