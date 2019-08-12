package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.core.activities.SolitAirActivity;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;

final /* synthetic */ class PlayVideoFragment$$Lambda$1 implements OnPreparedListener {
    private final PlayVideoFragment arg$1;

    private PlayVideoFragment$$Lambda$1(PlayVideoFragment playVideoFragment) {
        this.arg$1 = playVideoFragment;
    }

    public static OnPreparedListener lambdaFactory$(PlayVideoFragment playVideoFragment) {
        return new PlayVideoFragment$$Lambda$1(playVideoFragment);
    }

    public void onPrepared() {
        ((SolitAirActivity) this.arg$1.getActivity()).showLoader(false);
    }
}
