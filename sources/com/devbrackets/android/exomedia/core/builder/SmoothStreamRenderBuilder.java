package com.devbrackets.android.exomedia.core.builder;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.devbrackets.android.exomedia.core.renderer.EMMediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.chunk.FormatEvaluator.AdaptiveEvaluator;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.smoothstreaming.DefaultSmoothStreamingTrackSelector;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingChunkSource;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifest;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

@TargetApi(16)
public class SmoothStreamRenderBuilder extends RenderBuilder {
    protected AsyncRendererBuilder currentAsyncBuilder;

    protected final class AsyncRendererBuilder implements ManifestCallback<SmoothStreamingManifest> {
        protected boolean canceled;
        protected final Context context;
        protected final ManifestFetcher<SmoothStreamingManifest> manifestFetcher;
        protected final EMExoPlayer player;
        protected final int streamType;
        protected final String userAgent;

        public AsyncRendererBuilder(Context context2, String userAgent2, String url, EMExoPlayer player2, int streamType2) {
            this.context = context2;
            this.userAgent = userAgent2;
            this.streamType = streamType2;
            this.player = player2;
            this.manifestFetcher = new ManifestFetcher<>(url, SmoothStreamRenderBuilder.this.createManifestDataSource(null, userAgent2), new SmoothStreamingManifestParser());
        }

        public void init() {
            this.manifestFetcher.singleLoad(this.player.getMainHandler().getLooper(), this);
        }

        public void cancel() {
            this.canceled = true;
        }

        public void onSingleManifestError(IOException exception) {
            if (!this.canceled) {
                this.player.onRenderersError(exception);
            }
        }

        public void onSingleManifest(SmoothStreamingManifest manifest) {
            buildRenderers(manifest);
        }

        /* access modifiers changed from: protected */
        public void buildRenderers(SmoothStreamingManifest manifest) {
            if (!this.canceled) {
                DrmSessionManager drmSessionManager = null;
                if (manifest.protectionElement != null) {
                    if (VERSION.SDK_INT < 18) {
                        this.player.onRenderersError(new UnsupportedDrmException(1));
                        return;
                    }
                    try {
                        drmSessionManager = StreamingDrmSessionManager.newFrameworkInstance(manifest.protectionElement.uuid, this.player.getPlaybackLooper(), null, null, this.player.getMainHandler(), this.player);
                    } catch (UnsupportedDrmException e) {
                        this.player.onRenderersError(e);
                        return;
                    }
                }
                buildTrackRenderers(drmSessionManager);
            }
        }

        /* access modifiers changed from: protected */
        public void buildTrackRenderers(DrmSessionManager drmSessionManager) {
            Handler mainHandler = this.player.getMainHandler();
            DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(65536));
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter(mainHandler, this.player);
            DataSource dataSourceVideo = SmoothStreamRenderBuilder.this.createDataSource(this.context, bandwidthMeter, this.userAgent);
            ChunkSampleSource sampleSourceVideo = new ChunkSampleSource(new SmoothStreamingChunkSource(this.manifestFetcher, DefaultSmoothStreamingTrackSelector.newVideoInstance(this.context, true, false), dataSourceVideo, new AdaptiveEvaluator(bandwidthMeter), 30000), defaultLoadControl, 13107200, mainHandler, this.player, 0);
            DataSource dataSourceAudio = SmoothStreamRenderBuilder.this.createDataSource(this.context, bandwidthMeter, this.userAgent);
            ChunkSampleSource sampleSourceAudio = new ChunkSampleSource(new SmoothStreamingChunkSource(this.manifestFetcher, DefaultSmoothStreamingTrackSelector.newAudioInstance(), dataSourceAudio, null, 30000), defaultLoadControl, 3538944, mainHandler, this.player, 1);
            DataSource dataSourceCC = SmoothStreamRenderBuilder.this.createDataSource(this.context, bandwidthMeter, this.userAgent);
            ChunkSampleSource sampleSourceCC = new ChunkSampleSource(new SmoothStreamingChunkSource(this.manifestFetcher, DefaultSmoothStreamingTrackSelector.newTextInstance(), dataSourceCC, null, 30000), defaultLoadControl, 131072, mainHandler, this.player, 2);
            MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(this.context, sampleSourceVideo, MediaCodecSelector.DEFAULT, 1, 5000, drmSessionManager, true, mainHandler, this.player, 50);
            EMMediaCodecAudioTrackRenderer audioRenderer = new EMMediaCodecAudioTrackRenderer((SampleSource) sampleSourceAudio, MediaCodecSelector.DEFAULT, drmSessionManager, true, mainHandler, (EventListener) this.player, AudioCapabilities.getCapabilities(this.context), this.streamType);
            TextTrackRenderer textTrackRenderer = new TextTrackRenderer((SampleSource) sampleSourceCC, (TextRenderer) this.player, mainHandler.getLooper(), new SubtitleParser[0]);
            TrackRenderer[] renderers = new TrackRenderer[4];
            renderers[0] = videoRenderer;
            renderers[1] = audioRenderer;
            renderers[2] = textTrackRenderer;
            this.player.onRenderers(renderers, bandwidthMeter);
        }
    }

    public SmoothStreamRenderBuilder(Context context, String userAgent, String url) {
        this(context, userAgent, url, 3);
    }

    public SmoothStreamRenderBuilder(Context context, String userAgent, String url, int streamType) {
        super(context, userAgent, getManifestUri(url), streamType);
    }

    public void buildRenderers(EMExoPlayer player) {
        this.currentAsyncBuilder = new AsyncRendererBuilder(this.context, this.userAgent, this.uri, player, this.streamType);
        this.currentAsyncBuilder.init();
    }

    public void cancel() {
        if (this.currentAsyncBuilder != null) {
            this.currentAsyncBuilder.cancel();
            this.currentAsyncBuilder = null;
        }
    }

    /* access modifiers changed from: protected */
    public UriDataSource createManifestDataSource(Context context, String userAgent) {
        return new DefaultHttpDataSource(userAgent, null);
    }

    protected static String getManifestUri(String url) {
        return Util.toLowerInvariant(url).endsWith("/manifest") ? url : url + "/Manifest";
    }
}
