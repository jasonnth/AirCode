package com.devbrackets.android.exomedia.core.builder;

import android.annotation.TargetApi;
import android.content.Context;
import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylistParser;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback;
import java.io.IOException;

@TargetApi(16)
public class HlsRenderBuilder extends RenderBuilder {
    protected AsyncRendererBuilder currentAsyncBuilder;

    protected final class AsyncRendererBuilder implements ManifestCallback<HlsPlaylist> {
        protected boolean canceled;
        protected final Context context;
        protected final EMExoPlayer player;
        protected final ManifestFetcher<HlsPlaylist> playlistFetcher;
        protected final int streamType;
        protected final String userAgent;

        public AsyncRendererBuilder(Context context2, String userAgent2, String url, EMExoPlayer player2, int streamType2) {
            this.context = context2;
            this.userAgent = userAgent2;
            this.streamType = streamType2;
            this.player = player2;
            this.playlistFetcher = new ManifestFetcher<>(url, HlsRenderBuilder.this.createManifestDataSource(context2, userAgent2), new HlsPlaylistParser());
        }

        public void init() {
            this.playlistFetcher.singleLoad(this.player.getMainHandler().getLooper(), this);
        }

        public void cancel() {
            this.canceled = true;
        }

        public void onSingleManifestError(IOException e) {
            if (!this.canceled) {
                this.player.onRenderersError(e);
            }
        }

        public void onSingleManifest(HlsPlaylist playlist) {
            if (!this.canceled) {
                buildRenderers(playlist);
            }
        }

        /* access modifiers changed from: protected */
        public void buildRenderers(HlsPlaylist playlist) {
            if (!this.canceled) {
                boolean hasClosedCaptions = false;
                boolean hasMultipleAudioTracks = false;
                if (playlist instanceof HlsMasterPlaylist) {
                    HlsMasterPlaylist masterPlaylist = (HlsMasterPlaylist) playlist;
                    if (!masterPlaylist.subtitles.isEmpty()) {
                        hasClosedCaptions = true;
                    } else {
                        hasClosedCaptions = false;
                    }
                    if (!masterPlaylist.audios.isEmpty()) {
                        hasMultipleAudioTracks = true;
                    } else {
                        hasMultipleAudioTracks = false;
                    }
                }
                buildTrackRenderers(playlist, hasClosedCaptions, hasMultipleAudioTracks);
            }
        }

        /* JADX WARNING: type inference failed for: r0v33, types: [com.google.android.exoplayer.text.eia608.Eia608TrackRenderer] */
        /* JADX WARNING: type inference failed for: r31v1 */
        /* JADX WARNING: type inference failed for: r0v37, types: [com.google.android.exoplayer.TrackRenderer[]] */
        /* JADX WARNING: type inference failed for: r33v0 */
        /* JADX WARNING: type inference failed for: r0v39, types: [com.google.android.exoplayer.TrackRenderer[]] */
        /* JADX WARNING: type inference failed for: r0v42, types: [com.google.android.exoplayer.text.TextTrackRenderer] */
        /* JADX WARNING: type inference failed for: r0v44, types: [com.google.android.exoplayer.text.eia608.Eia608TrackRenderer] */
        /* JADX WARNING: type inference failed for: r0v45, types: [com.google.android.exoplayer.text.TextTrackRenderer] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v44, types: [com.google.android.exoplayer.text.eia608.Eia608TrackRenderer]
          assigns: [com.google.android.exoplayer.text.eia608.Eia608TrackRenderer, com.google.android.exoplayer.text.TextTrackRenderer]
          uses: [com.google.android.exoplayer.text.eia608.Eia608TrackRenderer, ?[OBJECT, ARRAY], com.google.android.exoplayer.text.TextTrackRenderer]
          mth insns count: 141
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 6 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void buildTrackRenderers(com.google.android.exoplayer.hls.HlsPlaylist r36, boolean r37, boolean r38) {
            /*
                r35 = this;
                com.google.android.exoplayer.DefaultLoadControl r11 = new com.google.android.exoplayer.DefaultLoadControl
                com.google.android.exoplayer.upstream.DefaultAllocator r3 = new com.google.android.exoplayer.upstream.DefaultAllocator
                r5 = 65536(0x10000, float:9.18355E-41)
                r3.<init>(r5)
                r11.<init>(r3)
                com.google.android.exoplayer.upstream.DefaultBandwidthMeter r7 = new com.google.android.exoplayer.upstream.DefaultBandwidthMeter
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                android.os.Handler r3 = r3.getMainHandler()
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r5 = r0.player
                r7.<init>(r3, r5)
                com.google.android.exoplayer.hls.PtsTimestampAdjusterProvider r8 = new com.google.android.exoplayer.hls.PtsTimestampAdjusterProvider
                r8.<init>()
                r0 = r35
                com.devbrackets.android.exomedia.core.builder.HlsRenderBuilder r3 = com.devbrackets.android.exomedia.core.builder.HlsRenderBuilder.this
                r0 = r35
                android.content.Context r5 = r0.context
                r0 = r35
                java.lang.String r6 = r0.userAgent
                com.google.android.exoplayer.upstream.DataSource r4 = r3.createDataSource(r5, r7, r6)
                com.google.android.exoplayer.hls.HlsChunkSource r2 = new com.google.android.exoplayer.hls.HlsChunkSource
                r3 = 1
                r0 = r35
                android.content.Context r5 = r0.context
                com.google.android.exoplayer.hls.DefaultHlsTrackSelector r6 = com.google.android.exoplayer.hls.DefaultHlsTrackSelector.newDefaultInstance(r5)
                r5 = r36
                r2.<init>(r3, r4, r5, r6, r7, r8)
                com.google.android.exoplayer.hls.HlsSampleSource r9 = new com.google.android.exoplayer.hls.HlsSampleSource
                r12 = 16777216(0x1000000, float:2.3509887E-38)
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                android.os.Handler r13 = r3.getMainHandler()
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r14 = r0.player
                r15 = 0
                r10 = r2
                r9.<init>(r10, r11, r12, r13, r14, r15)
                com.google.android.exoplayer.upstream.DefaultUriDataSource r14 = new com.google.android.exoplayer.upstream.DefaultUriDataSource
                r0 = r35
                android.content.Context r3 = r0.context
                r0 = r35
                java.lang.String r5 = r0.userAgent
                r14.<init>(r3, r7, r5)
                com.google.android.exoplayer.hls.HlsChunkSource r12 = new com.google.android.exoplayer.hls.HlsChunkSource
                r13 = 0
                com.google.android.exoplayer.hls.DefaultHlsTrackSelector r16 = com.google.android.exoplayer.hls.DefaultHlsTrackSelector.newAudioInstance()
                r15 = r36
                r17 = r7
                r18 = r8
                r12.<init>(r13, r14, r15, r16, r17, r18)
                com.google.android.exoplayer.hls.HlsSampleSource r15 = new com.google.android.exoplayer.hls.HlsSampleSource
                r18 = 3538944(0x360000, float:4.959117E-39)
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                android.os.Handler r19 = r3.getMainHandler()
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r0 = r0.player
                r20 = r0
                r21 = 1
                r16 = r12
                r17 = r11
                r15.<init>(r16, r17, r18, r19, r20, r21)
                if (r38 == 0) goto L_0x0179
                r3 = 2
                com.google.android.exoplayer.SampleSource[] r0 = new com.google.android.exoplayer.SampleSource[r3]
                r34 = r0
                r3 = 0
                r34[r3] = r9
                r3 = 1
                r34[r3] = r15
            L_0x009c:
                r0 = r35
                com.devbrackets.android.exomedia.core.builder.HlsRenderBuilder r3 = com.devbrackets.android.exomedia.core.builder.HlsRenderBuilder.this
                r0 = r35
                android.content.Context r5 = r0.context
                r0 = r35
                java.lang.String r6 = r0.userAgent
                com.google.android.exoplayer.upstream.DataSource r18 = r3.createDataSource(r5, r7, r6)
                com.google.android.exoplayer.hls.HlsChunkSource r16 = new com.google.android.exoplayer.hls.HlsChunkSource
                r17 = 0
                com.google.android.exoplayer.hls.DefaultHlsTrackSelector r20 = com.google.android.exoplayer.hls.DefaultHlsTrackSelector.newSubtitleInstance()
                r19 = r36
                r21 = r7
                r22 = r8
                r16.<init>(r17, r18, r19, r20, r21, r22)
                com.google.android.exoplayer.hls.HlsSampleSource r19 = new com.google.android.exoplayer.hls.HlsSampleSource
                r22 = 131072(0x20000, float:1.83671E-40)
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                android.os.Handler r23 = r3.getMainHandler()
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r0 = r0.player
                r24 = r0
                r25 = 2
                r20 = r16
                r21 = r11
                r19.<init>(r20, r21, r22, r23, r24, r25)
                com.google.android.exoplayer.MediaCodecVideoTrackRenderer r21 = new com.google.android.exoplayer.MediaCodecVideoTrackRenderer
                r0 = r35
                android.content.Context r0 = r0.context
                r22 = r0
                com.google.android.exoplayer.MediaCodecSelector r24 = com.google.android.exoplayer.MediaCodecSelector.DEFAULT
                r25 = 1
                r26 = 5000(0x1388, double:2.4703E-320)
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                android.os.Handler r28 = r3.getMainHandler()
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r0 = r0.player
                r29 = r0
                r30 = 50
                r23 = r9
                r21.<init>(r22, r23, r24, r25, r26, r28, r29, r30)
                com.devbrackets.android.exomedia.core.renderer.EMMediaCodecAudioTrackRenderer r22 = new com.devbrackets.android.exomedia.core.renderer.EMMediaCodecAudioTrackRenderer
                com.google.android.exoplayer.MediaCodecSelector r24 = com.google.android.exoplayer.MediaCodecSelector.DEFAULT
                r25 = 0
                r26 = 1
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                android.os.Handler r27 = r3.getMainHandler()
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r0 = r0.player
                r28 = r0
                r0 = r35
                android.content.Context r3 = r0.context
                com.google.android.exoplayer.audio.AudioCapabilities r29 = com.google.android.exoplayer.audio.AudioCapabilities.getCapabilities(r3)
                r0 = r35
                int r0 = r0.streamType
                r30 = r0
                r23 = r34
                r22.<init>(r23, r24, r25, r26, r27, r28, r29, r30)
                if (r37 == 0) goto L_0x0183
                com.google.android.exoplayer.text.TextTrackRenderer r31 = new com.google.android.exoplayer.text.TextTrackRenderer
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r5 = r0.player
                android.os.Handler r5 = r5.getMainHandler()
                android.os.Looper r5 = r5.getLooper()
                r6 = 0
                com.google.android.exoplayer.text.SubtitleParser[] r6 = new com.google.android.exoplayer.text.SubtitleParser[r6]
                r0 = r31
                r1 = r19
                r0.<init>(r1, r3, r5, r6)
            L_0x0142:
                com.google.android.exoplayer.metadata.MetadataTrackRenderer r32 = new com.google.android.exoplayer.metadata.MetadataTrackRenderer
                com.google.android.exoplayer.metadata.id3.Id3Parser r3 = new com.google.android.exoplayer.metadata.id3.Id3Parser
                r3.<init>()
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r5 = r0.player
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r6 = r0.player
                android.os.Handler r6 = r6.getMainHandler()
                android.os.Looper r6 = r6.getLooper()
                r0 = r32
                r0.<init>(r9, r3, r5, r6)
                r3 = 4
                com.google.android.exoplayer.TrackRenderer[] r0 = new com.google.android.exoplayer.TrackRenderer[r3]
                r33 = r0
                r3 = 0
                r33[r3] = r21
                r3 = 1
                r33[r3] = r22
                r3 = 2
                r33[r3] = r31
                r3 = 3
                r33[r3] = r32
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                r0 = r33
                r3.onRenderers(r0, r7)
                return
            L_0x0179:
                r3 = 1
                com.google.android.exoplayer.SampleSource[] r0 = new com.google.android.exoplayer.SampleSource[r3]
                r34 = r0
                r3 = 0
                r34[r3] = r9
                goto L_0x009c
            L_0x0183:
                com.google.android.exoplayer.text.eia608.Eia608TrackRenderer r31 = new com.google.android.exoplayer.text.eia608.Eia608TrackRenderer
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r3 = r0.player
                r0 = r35
                com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer r5 = r0.player
                android.os.Handler r5 = r5.getMainHandler()
                android.os.Looper r5 = r5.getLooper()
                r0 = r31
                r0.<init>(r9, r3, r5)
                goto L_0x0142
            */
            throw new UnsupportedOperationException("Method not decompiled: com.devbrackets.android.exomedia.core.builder.HlsRenderBuilder.AsyncRendererBuilder.buildTrackRenderers(com.google.android.exoplayer.hls.HlsPlaylist, boolean, boolean):void");
        }
    }

    public HlsRenderBuilder(Context context, String userAgent, String url) {
        this(context, userAgent, url, 3);
    }

    public HlsRenderBuilder(Context context, String userAgent, String url, int streamType) {
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
