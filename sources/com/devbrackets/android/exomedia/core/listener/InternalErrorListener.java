package com.devbrackets.android.exomedia.core.listener;

import android.media.MediaCodec.CryptoException;
import com.google.android.exoplayer.MediaCodecTrackRenderer.DecoderInitializationException;
import com.google.android.exoplayer.audio.AudioTrack.InitializationException;
import com.google.android.exoplayer.audio.AudioTrack.WriteException;
import java.io.IOException;

public interface InternalErrorListener {
    void onAudioTrackInitializationError(InitializationException initializationException);

    void onAudioTrackUnderrun(int i, long j, long j2);

    void onAudioTrackWriteError(WriteException writeException);

    void onCryptoError(CryptoException cryptoException);

    void onDecoderInitializationError(DecoderInitializationException decoderInitializationException);

    void onDrmSessionManagerError(Exception exc);

    void onLoadError(int i, IOException iOException);

    void onRendererInitializationError(Exception exc);
}
