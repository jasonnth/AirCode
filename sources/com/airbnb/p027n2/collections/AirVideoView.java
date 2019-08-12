package com.airbnb.p027n2.collections;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.airbnb.p027n2.N2Context;
import com.danikula.videocache.HttpProxyCacheServer;
import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.p306ui.widget.EMVideoView;

/* renamed from: com.airbnb.n2.collections.AirVideoView */
public class AirVideoView extends EMVideoView implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private boolean shouldRepeat = true;
    private HttpProxyCacheServer videoCache;
    private String videoUrl = null;

    public AirVideoView(Context context) {
        super(context);
        init();
    }

    public AirVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AirVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.videoCache = N2Context.instance().graph().videoCache();
        setScaleType(ScaleType.CENTER_CROP);
        setOnCompletionListener(this);
        setKeepScreenOn(true);
        setOnPreparedListener(this);
        setOnErrorListener(this);
    }

    public void setSrc(String url) {
        this.videoUrl = url;
        if (!TextUtils.isEmpty(this.videoUrl)) {
            setVideoURI(Uri.parse(this.videoCache.getProxyUrl(this.videoUrl)));
        }
    }

    public void setShouldRepeat(boolean repeat) {
        this.shouldRepeat = repeat;
    }

    /* access modifiers changed from: protected */
    public void onPlaybackEnded() {
        seekTo(0);
        if (!this.shouldRepeat && isPlaying()) {
            pause();
        }
        if (this.shouldRepeat && !isPlaying()) {
            start();
        }
    }

    public boolean onError() {
        return true;
    }

    public void onCompletion() {
        restart();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setSrc(this.videoUrl);
    }

    public void onPrepared() {
    }
}
