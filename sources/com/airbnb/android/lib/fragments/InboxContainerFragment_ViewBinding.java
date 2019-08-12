package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.EmptyResultsCardView;

public class InboxContainerFragment_ViewBinding implements Unbinder {
    private InboxContainerFragment target;

    public InboxContainerFragment_ViewBinding(InboxContainerFragment target2, View source) {
        this.target = target2;
        target2.messagingContentContainer = source.findViewById(C0880R.C0882id.content_container);
        target2.emptyResultsCard = (EmptyResultsCardView) Utils.findOptionalViewAsType(source, C0880R.C0882id.empty_results_card, "field 'emptyResultsCard'", EmptyResultsCardView.class);
    }

    public void unbind() {
        InboxContainerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.messagingContentContainer = null;
        target2.emptyResultsCard = null;
    }
}
