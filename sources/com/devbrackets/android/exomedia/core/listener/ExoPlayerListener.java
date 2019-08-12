package com.devbrackets.android.exomedia.core.listener;

import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.devbrackets.android.exomedia.listener.OnSeekCompletionListener;

public interface ExoPlayerListener extends OnSeekCompletionListener {
    void onError(EMExoPlayer eMExoPlayer, Exception exc);

    void onStateChanged(boolean z, int i);

    void onVideoSizeChanged(int i, int i2, int i3, float f);
}
