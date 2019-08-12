package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class SavedMessagesFragment$$Lambda$2 implements Action1 {
    private final SavedMessagesFragment arg$1;

    private SavedMessagesFragment$$Lambda$2(SavedMessagesFragment savedMessagesFragment) {
        this.arg$1 = savedMessagesFragment;
    }

    public static Action1 lambdaFactory$(SavedMessagesFragment savedMessagesFragment) {
        return new SavedMessagesFragment$$Lambda$2(savedMessagesFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
