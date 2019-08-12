package com.airbnb.android.contentframework.fragments;

import com.devbrackets.android.exomedia.listener.OnCompletionListener;

final /* synthetic */ class PlayVideoFragment$$Lambda$2 implements OnCompletionListener {
    private final PlayVideoFragment arg$1;

    private PlayVideoFragment$$Lambda$2(PlayVideoFragment playVideoFragment) {
        this.arg$1 = playVideoFragment;
    }

    public static OnCompletionListener lambdaFactory$(PlayVideoFragment playVideoFragment) {
        return new PlayVideoFragment$$Lambda$2(playVideoFragment);
    }

    public void onCompletion() {
        this.arg$1.getActivity().finish();
    }
}
