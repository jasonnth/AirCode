package com.mparticle.media;

import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public class MPMediaAPI {
    private AtomicBoolean mAudioPlaying;
    private final MediaCallbacks mCallbacks;
    private final Context mContext;

    private MPMediaAPI() {
        this.mAudioPlaying = new AtomicBoolean(false);
        this.mContext = null;
        this.mCallbacks = null;
    }

    public MPMediaAPI(Context context, MediaCallbacks callbacks) {
        this.mAudioPlaying = new AtomicBoolean(false);
        this.mContext = context;
        this.mCallbacks = callbacks;
    }

    public void setAudioPlaying(boolean playing) {
        this.mAudioPlaying.set(playing);
        if (playing) {
            this.mCallbacks.onAudioPlaying();
        } else {
            this.mCallbacks.onAudioStopped();
        }
    }

    public boolean getAudioPlaying() {
        return this.mAudioPlaying.get();
    }
}
