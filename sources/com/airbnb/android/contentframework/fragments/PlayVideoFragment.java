package com.airbnb.android.contentframework.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.fragments.AirFragment;
import com.devbrackets.android.exomedia.p306ui.widget.EMVideoView;

public final class PlayVideoFragment extends AirFragment {
    private static final String ARG_VIDEO_URL = "video_url";
    @BindView
    EMVideoView videoView;

    public static Bundle bundleWithVideoUrl(String videoUrl) {
        Bundle args = new Bundle();
        args.putString(ARG_VIDEO_URL, videoUrl);
        return args;
    }

    public static Fragment newInstance(Bundle bundle) {
        Fragment fragment = new PlayVideoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5709R.layout.fragment_video, container, false);
        bindViews(view);
        this.videoView.setVideoURI(Uri.parse(getArguments().getString(ARG_VIDEO_URL)));
        this.videoView.start();
        this.videoView.setOnPreparedListener(PlayVideoFragment$$Lambda$1.lambdaFactory$(this));
        this.videoView.setOnCompletionListener(PlayVideoFragment$$Lambda$2.lambdaFactory$(this));
        return view;
    }
}
