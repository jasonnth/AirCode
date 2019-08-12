package com.airbnb.android.contentframework.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.devbrackets.android.exomedia.p306ui.widget.EMVideoView;

public final class PlayVideoFragment_ViewBinding implements Unbinder {
    private PlayVideoFragment target;

    public PlayVideoFragment_ViewBinding(PlayVideoFragment target2, View source) {
        this.target = target2;
        target2.videoView = (EMVideoView) Utils.findRequiredViewAsType(source, C5709R.C5711id.video_view, "field 'videoView'", EMVideoView.class);
    }

    public void unbind() {
        PlayVideoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.videoView = null;
    }
}
