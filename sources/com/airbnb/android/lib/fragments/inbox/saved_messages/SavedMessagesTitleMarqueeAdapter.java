package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.lib.C0880R;

public class SavedMessagesTitleMarqueeAdapter extends AirEpoxyAdapter {
    SavedMessagesTitleMarqueeAdapter() {
        this.models.add(new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.canned_messages_education_title).captionRes(C0880R.string.canned_messages_education_caption));
    }
}
