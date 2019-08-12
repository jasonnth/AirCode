package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.Thread;

public interface ThreadClickListener {
    void onClick(Thread thread);

    boolean onLongClick(Thread thread);

    void onReviewButtonClick(Thread thread);

    void onSwipe(Thread thread);
}
