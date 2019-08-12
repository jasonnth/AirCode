package com.google.android.exoplayer.extractor.flv;

import android.util.Pair;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.extractor.flv.TagPayloadReader.UnsupportedFormatException;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import java.util.Collections;

final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int[] AUDIO_SAMPLING_RATE_TABLE = {MiSnapApiConstants.IMAGE_HORIZONTAL_PIXELS_PARAM_MAX_VALUE, 11000, 22000, 44000};
    private boolean hasOutputFormat;
    private boolean hasParsedAudioDataHeader;

    public AudioTagPayloadReader(TrackOutput output) {
        super(output);
    }

    /* access modifiers changed from: protected */
    public boolean parseHeader(ParsableByteArray data) throws UnsupportedFormatException {
        if (!this.hasParsedAudioDataHeader) {
            int header = data.readUnsignedByte();
            int audioFormat = (header >> 4) & 15;
            int sampleRateIndex = (header >> 2) & 3;
            if (sampleRateIndex < 0 || sampleRateIndex >= AUDIO_SAMPLING_RATE_TABLE.length) {
                throw new UnsupportedFormatException("Invalid sample rate index: " + sampleRateIndex);
            } else if (audioFormat != 10) {
                throw new UnsupportedFormatException("Audio format not supported: " + audioFormat);
            } else {
                this.hasParsedAudioDataHeader = true;
            }
        } else {
            data.skipBytes(1);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void parsePayload(ParsableByteArray data, long timeUs) {
        int packetType = data.readUnsignedByte();
        if (packetType == 0 && !this.hasOutputFormat) {
            byte[] audioSpecifiConfig = new byte[data.bytesLeft()];
            data.readBytes(audioSpecifiConfig, 0, audioSpecifiConfig.length);
            Pair<Integer, Integer> audioParams = CodecSpecificDataUtil.parseAacAudioSpecificConfig(audioSpecifiConfig);
            this.output.format(MediaFormat.createAudioFormat(null, "audio/mp4a-latm", -1, -1, getDurationUs(), ((Integer) audioParams.second).intValue(), ((Integer) audioParams.first).intValue(), Collections.singletonList(audioSpecifiConfig), null));
            this.hasOutputFormat = true;
        } else if (packetType == 1) {
            int bytesToWrite = data.bytesLeft();
            this.output.sampleData(data, bytesToWrite);
            this.output.sampleMetadata(timeUs, 1, bytesToWrite, 0, null);
        }
    }
}
