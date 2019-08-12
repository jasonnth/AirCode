package com.devbrackets.android.exomedia.core.builder;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
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
import com.google.android.exoplayer.dash.DashChunkSource;
import com.google.android.exoplayer.dash.DefaultDashTrackSelector;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescriptionParser;
import com.google.android.exoplayer.dash.mpd.Period;
import com.google.android.exoplayer.dash.mpd.UtcTimingElement;
import com.google.android.exoplayer.dash.mpd.UtcTimingElementResolver;
import com.google.android.exoplayer.dash.mpd.UtcTimingElementResolver.UtcTimingCallback;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

@TargetApi(16)
public class DashRenderBuilder extends RenderBuilder {
    protected AsyncRendererBuilder currentAsyncBuilder;

    protected final class AsyncRendererBuilder implements UtcTimingCallback, ManifestCallback<MediaPresentationDescription> {
        protected boolean canceled;
        protected final Context context;
        protected MediaPresentationDescription currentManifest;
        protected long elapsedRealtimeOffset;
        protected final UriDataSource manifestDataSource;
        protected final ManifestFetcher<MediaPresentationDescription> manifestFetcher;
        protected final EMExoPlayer player;
        protected final int streamType;
        protected final String userAgent;

        public AsyncRendererBuilder(Context context2, String userAgent2, String url, EMExoPlayer player2, int streamType2) {
            this.context = context2;
            this.userAgent = userAgent2;
            this.streamType = streamType2;
            this.player = player2;
            MediaPresentationDescriptionParser parser = new MediaPresentationDescriptionParser();
            this.manifestDataSource = DashRenderBuilder.this.createManifestDataSource(context2, userAgent2);
            this.manifestFetcher = new ManifestFetcher<>(url, this.manifestDataSource, parser);
        }

        public void init() {
            this.manifestFetcher.singleLoad(this.player.getMainHandler().getLooper(), this);
        }

        public void cancel() {
            this.canceled = true;
        }

        public void onSingleManifest(MediaPresentationDescription manifest) {
            if (!this.canceled) {
                this.currentManifest = manifest;
                if (!manifest.dynamic || manifest.utcTiming == null) {
                    buildRenderers();
                } else {
                    UtcTimingElementResolver.resolveTimingElement(this.manifestDataSource, manifest.utcTiming, this.manifestFetcher.getManifestLoadCompleteTimestamp(), this);
                }
            }
        }

        public void onSingleManifestError(IOException e) {
            if (!this.canceled) {
                this.player.onRenderersError(e);
            }
        }

        public void onTimestampResolved(UtcTimingElement utcTiming, long elapsedRealtimeOffset2) {
            if (!this.canceled) {
                this.elapsedRealtimeOffset = elapsedRealtimeOffset2;
                buildRenderers();
            }
        }

        public void onTimestampError(UtcTimingElement utcTiming, IOException e) {
            if (!this.canceled) {
                Log.e("DashRendererBuilder", "Failed to resolve UtcTiming element [" + utcTiming + "]", e);
                buildRenderers();
            }
        }

        /* access modifiers changed from: protected */
        public void buildRenderers() {
            boolean filterHdContent = false;
            boolean hasContentProtection = false;
            Period period = this.currentManifest.getPeriod(0);
            StreamingDrmSessionManager drmSessionManager = null;
            for (int i = 0; i < period.adaptationSets.size(); i++) {
                AdaptationSet adaptationSet = (AdaptationSet) period.adaptationSets.get(i);
                if (adaptationSet.type != -1) {
                    hasContentProtection |= adaptationSet.hasContentProtection();
                }
            }
            if (hasContentProtection) {
                if (Util.SDK_INT < 18) {
                    this.player.onRenderersError(new UnsupportedDrmException(1));
                    return;
                }
                try {
                    drmSessionManager = StreamingDrmSessionManager.newWidevineInstance(this.player.getPlaybackLooper(), null, null, this.player.getMainHandler(), this.player);
                    filterHdContent = getWidevineSecurityLevel(drmSessionManager) != 1;
                } catch (UnsupportedDrmException e) {
                    this.player.onRenderersError(e);
                    return;
                }
            }
            buildTrackRenderers(drmSessionManager, filterHdContent);
        }

        /* access modifiers changed from: protected */
        public void buildTrackRenderers(DrmSessionManager drmSessionManager, boolean filterHdContent) {
            Handler mainHandler = this.player.getMainHandler();
            DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(65536));
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter(mainHandler, this.player);
            ChunkSampleSource sampleSourceVideo = new ChunkSampleSource(new DashChunkSource(this.manifestFetcher, DefaultDashTrackSelector.newVideoInstance(this.context, true, filterHdContent), DashRenderBuilder.this.createDataSource(this.context, bandwidthMeter, this.userAgent), new AdaptiveEvaluator(bandwidthMeter), 30000, this.elapsedRealtimeOffset, mainHandler, this.player, 0), defaultLoadControl, 13107200, mainHandler, this.player, 0);
            ChunkSampleSource sampleSourceAudio = new ChunkSampleSource(new DashChunkSource(this.manifestFetcher, DefaultDashTrackSelector.newAudioInstance(), DashRenderBuilder.this.createDataSource(this.context, bandwidthMeter, this.userAgent), null, 30000, this.elapsedRealtimeOffset, mainHandler, this.player, 1), defaultLoadControl, 3538944, mainHandler, this.player, 1);
            ChunkSampleSource sampleSourceCC = new ChunkSampleSource(new DashChunkSource(this.manifestFetcher, DefaultDashTrackSelector.newAudioInstance(), DashRenderBuilder.this.createDataSource(this.context, bandwidthMeter, this.userAgent), null, 30000, this.elapsedRealtimeOffset, mainHandler, this.player, 2), defaultLoadControl, 131072, mainHandler, this.player, 2);
            MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(this.context, sampleSourceVideo, MediaCodecSelector.DEFAULT, 1, 5000, drmSessionManager, true, mainHandler, this.player, 50);
            EMMediaCodecAudioTrackRenderer audioRenderer = new EMMediaCodecAudioTrackRenderer((SampleSource) sampleSourceAudio, MediaCodecSelector.DEFAULT, drmSessionManager, true, mainHandler, (EventListener) this.player, AudioCapabilities.getCapabilities(this.context), this.streamType);
            TextTrackRenderer textTrackRenderer = new TextTrackRenderer((SampleSource) sampleSourceCC, (TextRenderer) this.player, mainHandler.getLooper(), new SubtitleParser[0]);
            TrackRenderer[] renderers = new TrackRenderer[4];
            renderers[0] = videoRenderer;
            renderers[1] = audioRenderer;
            renderers[2] = textTrackRenderer;
            this.player.onRenderers(renderers, bandwidthMeter);
        }

        /* access modifiers changed from: protected */
        public int getWidevineSecurityLevel(StreamingDrmSessionManager sessionManager) {
            String securityLevelProperty = sessionManager.getPropertyString("securityLevel");
            if (securityLevelProperty.equals("L1")) {
                return 1;
            }
            return securityLevelProperty.equals("L3") ? 3 : -1;
        }
    }

    public DashRenderBuilder(Context context, String userAgent, String url) {
        this(context, userAgent, url, 3);
    }

    public DashRenderBuilder(Context context, String userAgent, String url, int streamType) {
        super(context, userAgent, url, streamType);
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
        return new DefaultUriDataSource(context, userAgent);
    }
}
