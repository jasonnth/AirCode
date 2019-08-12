package com.devbrackets.android.exomedia.core.exoplayer;

import android.media.MediaCodec.CryptoException;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager.WakeLock;
import android.support.p000v4.util.ArrayMap;
import android.view.Surface;
import com.devbrackets.android.exomedia.core.builder.RenderBuilder;
import com.devbrackets.android.exomedia.core.listener.CaptionListener;
import com.devbrackets.android.exomedia.core.listener.ExoPlayerListener;
import com.devbrackets.android.exomedia.core.listener.Id3MetadataListener;
import com.devbrackets.android.exomedia.core.listener.InfoListener;
import com.devbrackets.android.exomedia.core.listener.InternalErrorListener;
import com.devbrackets.android.exomedia.listener.OnBufferUpdateListener;
import com.devbrackets.android.exomedia.util.Repeater;
import com.devbrackets.android.exomedia.util.Repeater.RepeatListener;
import com.google.android.exoplayer.C4016TimeRange;
import com.google.android.exoplayer.DummyTrackRenderer;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.ExoPlayer.Factory;
import com.google.android.exoplayer.ExoPlayer.Listener;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener;
import com.google.android.exoplayer.MediaCodecTrackRenderer.DecoderInitializationException;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.audio.AudioCapabilitiesReceiver;
import com.google.android.exoplayer.audio.AudioTrack.InitializationException;
import com.google.android.exoplayer.audio.AudioTrack.WriteException;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.dash.DashChunkSource;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.hls.HlsSampleSource;
import com.google.android.exoplayer.metadata.MetadataTrackRenderer.MetadataRenderer;
import com.google.android.exoplayer.metadata.id3.Id3Frame;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class EMExoPlayer implements Listener, EventListener, MediaCodecVideoTrackRenderer.EventListener, AudioCapabilitiesReceiver.Listener, ChunkSampleSource.EventListener, DashChunkSource.EventListener, StreamingDrmSessionManager.EventListener, ExtractorSampleSource.EventListener, HlsSampleSource.EventListener, MetadataRenderer<List<Id3Frame>>, TextRenderer, BandwidthMeter.EventListener {
    private AudioCapabilities audioCapabilities;
    private AudioCapabilitiesReceiver audioCapabilitiesReceiver;
    private TrackRenderer audioRenderer;
    private Repeater bufferRepeater;
    /* access modifiers changed from: private */
    public OnBufferUpdateListener bufferUpdateListener;
    private CaptionListener captionListener;
    private Id3MetadataListener id3MetadataListener;
    private InfoListener infoListener;
    private InternalErrorListener internalErrorListener;
    private final CopyOnWriteArrayList<ExoPlayerListener> listeners;
    private final Handler mainHandler;
    private final ExoPlayer player;
    private boolean prepared;
    private RenderBuilder rendererBuilder;
    private RenderBuildingState rendererBuildingState;
    private StateStore stateStore;
    private final AtomicBoolean stopped;
    private Surface surface;
    private TrackRenderer videoRenderer;
    private WakeLock wakeLock;

    private class BufferRepeatListener implements RepeatListener {
        private BufferRepeatListener() {
        }

        public void onRepeat() {
            if (EMExoPlayer.this.bufferUpdateListener != null) {
                EMExoPlayer.this.bufferUpdateListener.onBufferingUpdate(EMExoPlayer.this.getBufferedPercentage());
            }
        }
    }

    public enum RenderBuildingState {
        IDLE,
        BUILDING,
        BUILT
    }

    private static class StateStore {
        private int[] prevStates;

        private StateStore() {
            this.prevStates = new int[]{1, 1, 1, 1};
        }

        public void setMostRecentState(boolean playWhenReady, int state) {
            if (this.prevStates[3] != getState(playWhenReady, state)) {
                this.prevStates[0] = this.prevStates[1];
                this.prevStates[1] = this.prevStates[2];
                this.prevStates[2] = this.prevStates[3];
                this.prevStates[3] = state;
            }
        }

        public int getState(boolean playWhenReady, int state) {
            return (playWhenReady ? -268435456 : 0) | state;
        }

        public int getMostRecentState() {
            return this.prevStates[3];
        }

        public boolean isLastReportedPlayWhenReady() {
            return (this.prevStates[3] & -268435456) != 0;
        }

        public boolean matchesHistory(int[] states, boolean ignorePlayWhenReady) {
            boolean flag = true;
            int andFlag = ignorePlayWhenReady ? 268435455 : -1;
            int startIndex = this.prevStates.length - states.length;
            for (int i = startIndex; i < this.prevStates.length; i++) {
                flag &= (this.prevStates[i] & andFlag) == (states[i - startIndex] & andFlag);
            }
            return flag;
        }
    }

    public EMExoPlayer() {
        this(null);
    }

    public EMExoPlayer(RenderBuilder rendererBuilder2) {
        this.stopped = new AtomicBoolean();
        this.stateStore = new StateStore();
        this.bufferRepeater = new Repeater();
        this.prepared = false;
        this.wakeLock = null;
        this.bufferRepeater.setRepeaterDelay(1000);
        this.bufferRepeater.setRepeatListener(new BufferRepeatListener());
        this.player = Factory.newInstance(4, 1000, 5000);
        this.player.addListener(this);
        this.mainHandler = new Handler();
        this.listeners = new CopyOnWriteArrayList<>();
        this.rendererBuildingState = RenderBuildingState.IDLE;
        this.player.setSelectedTrack(2, -1);
        replaceRenderBuilder(rendererBuilder2);
    }

    public void replaceRenderBuilder(RenderBuilder renderBuilder) {
        this.rendererBuilder = renderBuilder;
        if (this.rendererBuilder != null && this.audioCapabilities == null) {
            this.audioCapabilitiesReceiver = new AudioCapabilitiesReceiver(this.rendererBuilder.getContext(), this);
            this.audioCapabilitiesReceiver.register();
        }
        this.prepared = false;
        prepare();
    }

    public void addListener(ExoPlayerListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        }
    }

    public void setBufferUpdateListener(OnBufferUpdateListener listener) {
        this.bufferUpdateListener = listener;
        setBufferRepeaterStarted(listener != null);
    }

    public void setMetadataListener(Id3MetadataListener listener) {
        this.id3MetadataListener = listener;
    }

    public void setSurface(Surface surface2) {
        this.surface = surface2;
        pushSurface(false);
    }

    public void blockingClearSurface() {
        if (this.surface != null) {
            this.surface.release();
        }
        this.surface = null;
        pushSurface(true);
    }

    public Map<Integer, List<MediaFormat>> getAvailableTracks() {
        int[] trackTypes;
        if (getPlaybackState() == 1) {
            return null;
        }
        Map<Integer, List<MediaFormat>> trackMap = new ArrayMap<>();
        for (int type : new int[]{1, 0, 2, 3}) {
            int trackCount = getTrackCount(type);
            List<MediaFormat> tracks = new ArrayList<>(trackCount);
            trackMap.put(Integer.valueOf(type), tracks);
            for (int i = 0; i < trackCount; i++) {
                tracks.add(getTrackFormat(type, i));
            }
        }
        return trackMap;
    }

    public int getTrackCount(int type) {
        return this.player.getTrackCount(type);
    }

    public MediaFormat getTrackFormat(int type, int index) {
        return this.player.getTrackFormat(type, index);
    }

    public int getSelectedTrack(int type) {
        return this.player.getSelectedTrack(type);
    }

    public void setSelectedTrack(int type, int index) {
        this.player.setSelectedTrack(type, index);
        if (type == 2 && index == -1 && this.captionListener != null) {
            this.captionListener.onCues(Collections.emptyList());
        }
    }

    public void setVolume(float volume) {
        this.player.sendMessage(this.audioRenderer, 1, Float.valueOf(volume));
    }

    public void forcePrepare() {
        this.prepared = false;
    }

    public void prepare() {
        if (!this.prepared && this.rendererBuilder != null) {
            if (this.rendererBuildingState == RenderBuildingState.BUILT) {
                this.player.stop();
            }
            this.videoRenderer = null;
            this.rendererBuildingState = RenderBuildingState.BUILDING;
            reportPlayerState();
            this.rendererBuilder.buildRenderers(this);
            this.prepared = true;
            this.stopped.set(false);
        }
    }

    public void onRenderers(TrackRenderer[] renderers, BandwidthMeter bandwidthMeter) {
        for (int i = 0; i < 4; i++) {
            if (renderers[i] == null) {
                renderers[i] = new DummyTrackRenderer();
            }
        }
        this.videoRenderer = renderers[0];
        this.audioRenderer = renderers[1];
        pushSurface(false);
        this.player.prepare(renderers);
        this.rendererBuildingState = RenderBuildingState.BUILT;
    }

    public void onRenderersError(Exception e) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onRendererInitializationError(e);
        }
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((ExoPlayerListener) it.next()).onError(this, e);
        }
        this.rendererBuildingState = RenderBuildingState.IDLE;
        reportPlayerState();
    }

    public void stop() {
        if (!this.stopped.getAndSet(true)) {
            this.player.setPlayWhenReady(false);
            this.player.stop();
        }
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        this.player.setPlayWhenReady(playWhenReady);
        stayAwake(playWhenReady);
    }

    public void seekTo(long positionMs) {
        this.player.seekTo(positionMs);
        this.stateStore.setMostRecentState(this.stateStore.isLastReportedPlayWhenReady(), 100);
    }

    public boolean restart() {
        int playbackState = getPlaybackState();
        if (playbackState != 1 && playbackState != 5) {
            return false;
        }
        seekTo(0);
        setPlayWhenReady(true);
        forcePrepare();
        prepare();
        return true;
    }

    public void release() {
        if (this.rendererBuilder != null) {
            this.rendererBuilder.cancel();
        }
        if (this.audioCapabilitiesReceiver != null) {
            this.audioCapabilitiesReceiver.unregister();
            this.audioCapabilitiesReceiver = null;
        }
        setBufferRepeaterStarted(false);
        this.rendererBuildingState = RenderBuildingState.IDLE;
        this.listeners.clear();
        this.surface = null;
        this.player.release();
        stayAwake(false);
    }

    public int getPlaybackState() {
        if (this.rendererBuildingState == RenderBuildingState.BUILDING) {
            return 2;
        }
        return this.player.getPlaybackState();
    }

    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    public long getDuration() {
        return this.player.getDuration();
    }

    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    public Looper getPlaybackLooper() {
        return this.player.getPlaybackLooper();
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    /* access modifiers changed from: protected */
    public void stayAwake(boolean awake) {
        if (this.wakeLock != null) {
            if (awake && !this.wakeLock.isHeld()) {
                this.wakeLock.acquire();
            } else if (!awake && this.wakeLock.isHeld()) {
                this.wakeLock.release();
            }
        }
    }

    public void onPlayerStateChanged(boolean playWhenReady, int state) {
        reportPlayerState();
    }

    public void onPlayerError(ExoPlaybackException exception) {
        this.rendererBuildingState = RenderBuildingState.IDLE;
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((ExoPlayerListener) it.next()).onError(this, exception);
        }
    }

    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((ExoPlayerListener) it.next()).onVideoSizeChanged(width, height, unappliedRotationDegrees, pixelWidthHeightRatio);
        }
    }

    public void onDroppedFrames(int count, long elapsed) {
        if (this.infoListener != null) {
            this.infoListener.onDroppedFrames(count, elapsed);
        }
    }

    public void onBandwidthSample(int elapsedMs, long bytes, long bitrateEstimate) {
        if (this.infoListener != null) {
            this.infoListener.onBandwidthSample(elapsedMs, bytes, bitrateEstimate);
        }
    }

    public void onDownstreamFormatChanged(int sourceId, Format format, int trigger, long mediaTimeMs) {
        if (this.infoListener != null) {
            if (sourceId == 0) {
                this.infoListener.onVideoFormatEnabled(format, trigger, mediaTimeMs);
            } else if (sourceId == 1) {
                this.infoListener.onAudioFormatEnabled(format, trigger, mediaTimeMs);
            }
        }
    }

    public void onDrmKeysLoaded() {
    }

    public void onDrmSessionManagerError(Exception e) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onDrmSessionManagerError(e);
        }
    }

    public void onDecoderInitializationError(DecoderInitializationException e) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onDecoderInitializationError(e);
        }
    }

    public void onDecoderInitialized(String decoderName, long elapsedRealtimeMs, long initializationDurationMs) {
        if (this.infoListener != null) {
            this.infoListener.onDecoderInitialized(decoderName, elapsedRealtimeMs, initializationDurationMs);
        }
    }

    public void onAudioTrackInitializationError(InitializationException e) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onAudioTrackInitializationError(e);
        }
    }

    public void onAudioTrackWriteError(WriteException e) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onAudioTrackWriteError(e);
        }
    }

    public void onAudioTrackUnderrun(int bufferSize, long bufferSizeMs, long elapsedSinceLastFeedMs) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onAudioTrackUnderrun(bufferSize, bufferSizeMs, elapsedSinceLastFeedMs);
        }
    }

    public void onCryptoError(CryptoException e) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onCryptoError(e);
        }
    }

    public void onLoadError(int sourceId, IOException e) {
        if (this.internalErrorListener != null) {
            this.internalErrorListener.onLoadError(sourceId, e);
        }
    }

    public void onCues(List<Cue> cues) {
        if (this.captionListener != null && getSelectedTrack(2) != -1) {
            this.captionListener.onCues(cues);
        }
    }

    public void onMetadata(List<Id3Frame> metadata) {
        if (this.id3MetadataListener != null && getSelectedTrack(3) != -1) {
            this.id3MetadataListener.onId3Metadata(metadata);
        }
    }

    public void onAvailableRangeChanged(int sourceId, C4016TimeRange availableRange) {
        if (this.infoListener != null) {
            this.infoListener.onAvailableRangeChanged(sourceId, availableRange);
        }
    }

    public void onPlayWhenReadyCommitted() {
    }

    public void onDrawnToSurface(Surface surface2) {
    }

    public void onLoadStarted(int sourceId, long length, int type, int trigger, Format format, long mediaStartTimeMs, long mediaEndTimeMs) {
        if (this.infoListener != null) {
            this.infoListener.onLoadStarted(sourceId, length, type, trigger, format, mediaStartTimeMs, mediaEndTimeMs);
        }
    }

    public void onLoadCompleted(int sourceId, long bytesLoaded, int type, int trigger, Format format, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs) {
        if (this.infoListener != null) {
            this.infoListener.onLoadCompleted(sourceId, bytesLoaded, type, trigger, format, mediaStartTimeMs, mediaEndTimeMs, elapsedRealtimeMs, loadDurationMs);
        }
    }

    public void onLoadCanceled(int sourceId, long bytesLoaded) {
    }

    public void onUpstreamDiscarded(int sourceId, long mediaStartTimeMs, long mediaEndTimeMs) {
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities2) {
        if (!audioCapabilities2.equals(this.audioCapabilities)) {
            this.audioCapabilities = audioCapabilities2;
            if (this.rendererBuilder != null) {
                boolean playWhenReady = getPlayWhenReady();
                long currentPosition = getCurrentPosition();
                replaceRenderBuilder(this.rendererBuilder);
                this.player.seekTo(currentPosition);
                this.player.setPlayWhenReady(playWhenReady);
            }
        }
    }

    private void reportPlayerState() {
        boolean playWhenReady = this.player.getPlayWhenReady();
        int playbackState = getPlaybackState();
        int newState = this.stateStore.getState(playWhenReady, playbackState);
        if (newState != this.stateStore.getMostRecentState()) {
            this.stateStore.setMostRecentState(playWhenReady, playbackState);
            if (newState == 4) {
                setBufferRepeaterStarted(true);
            } else if (newState == 1 || newState == 5 || newState == 2) {
                setBufferRepeaterStarted(false);
            }
            boolean informSeekCompletion = this.stateStore.matchesHistory(new int[]{100, 3, 4}, true) | this.stateStore.matchesHistory(new int[]{100, 4, 3, 4}, true);
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ExoPlayerListener listener = (ExoPlayerListener) it.next();
                listener.onStateChanged(playWhenReady, playbackState);
                if (informSeekCompletion) {
                    listener.onSeekComplete();
                }
            }
        }
    }

    private void pushSurface(boolean blockForSurfacePush) {
        if (this.videoRenderer != null) {
            if (blockForSurfacePush) {
                this.player.blockingSendMessage(this.videoRenderer, 1, this.surface);
            } else {
                this.player.sendMessage(this.videoRenderer, 1, this.surface);
            }
        }
    }

    private void setBufferRepeaterStarted(boolean start) {
        if (!start || this.bufferUpdateListener == null) {
            this.bufferRepeater.stop();
        } else {
            this.bufferRepeater.start();
        }
    }
}
