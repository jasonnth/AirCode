package com.airbnb.android.thread.controllers;

import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.UserBlock;

public interface ThreadBlockController {
    void finishOk();

    Thread getThread();

    void onBackPressed();

    void showFlagUserConfirmFragment();

    void showThreadBlockConfirmFragment(UserBlock userBlock);

    void showThreadBlockFragment();
}
