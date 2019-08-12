package com.airbnb.android.lib.tripassistant;

import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.messaging.MessageItem;

public abstract class HelpThreadLoadingEpoxyModel extends AirEpoxyModel<MessageItem> {
    public void bind(MessageItem view) {
        super.bind(view);
        view.showLoading(true);
        view.setProfileDrawable(C0880R.C0881drawable.trip_assistant_icon);
    }
}
