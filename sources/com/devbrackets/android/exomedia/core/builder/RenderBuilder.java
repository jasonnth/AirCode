package com.devbrackets.android.exomedia.core.builder;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.devbrackets.android.exomedia.core.renderer.EMMediaCodecAudioTrackRenderer;
import com.devbrackets.android.exomedia.util.MediaUtil;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.upstream.TransferListener;

@TargetApi(16)
public class RenderBuilder {
    protected final Context context;
    protected final int streamType;
    protected final String uri;
    protected final String userAgent;

    public RenderBuilder(Context context2, String userAgent2, String uri2) {
        this(context2, userAgent2, uri2, 3);
    }

    public RenderBuilder(Context context2, String userAgent2, String uri2, int streamType2) {
        this.uri = uri2;
        this.userAgent = userAgent2;
        this.context = context2;
        this.streamType = streamType2;
    }

    public Context getContext() {
        return this.context;
    }

    public void buildRenderers(EMExoPlayer player) {
        Allocator allocator = new DefaultAllocator(65536);
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter(player.getMainHandler(), player);
        ExtractorSampleSource sampleSource = new ExtractorSampleSource(Uri.parse(MediaUtil.getUriWithProtocol(this.uri)), createDataSource(this.context, defaultBandwidthMeter, this.userAgent), allocator, 16777216, player.getMainHandler(), player, 0, new Extractor[0]);
        MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(this.context, sampleSource, MediaCodecSelector.DEFAULT, 1, 5000, player.getMainHandler(), player, 50);
        EMMediaCodecAudioTrackRenderer audioRenderer = new EMMediaCodecAudioTrackRenderer((SampleSource) sampleSource, MediaCodecSelector.DEFAULT, (DrmSessionManager) null, true, player.getMainHandler(), (EventListener) player, AudioCapabilities.getCapabilities(this.context), this.streamType);
        TextTrackRenderer textTrackRenderer = new TextTrackRenderer((SampleSource) sampleSource, (TextRenderer) player, player.getMainHandler().getLooper(), new SubtitleParser[0]);
        TrackRenderer[] renderers = new TrackRenderer[4];
        renderers[0] = videoRenderer;
        renderers[1] = audioRenderer;
        renderers[2] = textTrackRenderer;
        player.onRenderers(renderers, defaultBandwidthMeter);
    }

    public void cancel() {
    }

    /* access modifiers changed from: protected */
    public DataSource createDataSource(Context context2, TransferListener transferListener, String userAgent2) {
        return new DefaultUriDataSource(context2, transferListener, userAgent2, true);
    }
}
