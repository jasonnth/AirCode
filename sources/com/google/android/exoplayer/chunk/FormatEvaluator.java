package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.upstream.BandwidthMeter;
import java.util.List;

public interface FormatEvaluator {

    public static final class AdaptiveEvaluator implements FormatEvaluator {
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final long maxDurationForQualityDecreaseUs;
        private final int maxInitialBitrate;
        private final long minDurationForQualityIncreaseUs;
        private final long minDurationToRetainAfterDiscardUs;

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter2) {
            this(bandwidthMeter2, 800000, 10000, 25000, 25000, 0.75f);
        }

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter2, int maxInitialBitrate2, int minDurationForQualityIncreaseMs, int maxDurationForQualityDecreaseMs, int minDurationToRetainAfterDiscardMs, float bandwidthFraction2) {
            this.bandwidthMeter = bandwidthMeter2;
            this.maxInitialBitrate = maxInitialBitrate2;
            this.minDurationForQualityIncreaseUs = ((long) minDurationForQualityIncreaseMs) * 1000;
            this.maxDurationForQualityDecreaseUs = ((long) maxDurationForQualityDecreaseMs) * 1000;
            this.minDurationToRetainAfterDiscardUs = ((long) minDurationToRetainAfterDiscardMs) * 1000;
            this.bandwidthFraction = bandwidthFraction2;
        }

        public void enable() {
        }

        public void disable() {
        }

        public void evaluate(List<? extends MediaChunk> queue, long playbackPositionUs, Format[] formats, Evaluation evaluation) {
            long bufferedDurationUs;
            if (queue.isEmpty()) {
                bufferedDurationUs = 0;
            } else {
                bufferedDurationUs = ((MediaChunk) queue.get(queue.size() - 1)).endTimeUs - playbackPositionUs;
            }
            Format current = evaluation.format;
            Format ideal = determineIdealFormat(formats, this.bandwidthMeter.getBitrateEstimate());
            boolean isHigher = (ideal == null || current == null || ideal.bitrate <= current.bitrate) ? false : true;
            boolean isLower = (ideal == null || current == null || ideal.bitrate >= current.bitrate) ? false : true;
            if (isHigher) {
                if (bufferedDurationUs < this.minDurationForQualityIncreaseUs) {
                    ideal = current;
                } else if (bufferedDurationUs >= this.minDurationToRetainAfterDiscardUs) {
                    int i = 1;
                    while (true) {
                        if (i >= queue.size()) {
                            break;
                        }
                        MediaChunk thisChunk = (MediaChunk) queue.get(i);
                        if (thisChunk.startTimeUs - playbackPositionUs >= this.minDurationToRetainAfterDiscardUs && thisChunk.format.bitrate < ideal.bitrate && thisChunk.format.height < ideal.height && thisChunk.format.height < 720 && thisChunk.format.width < 1280) {
                            evaluation.queueSize = i;
                            break;
                        }
                        i++;
                    }
                }
            } else if (isLower && current != null && bufferedDurationUs >= this.maxDurationForQualityDecreaseUs) {
                ideal = current;
            }
            if (!(current == null || ideal == current)) {
                evaluation.trigger = 3;
            }
            evaluation.format = ideal;
        }

        private Format determineIdealFormat(Format[] formats, long bitrateEstimate) {
            long effectiveBitrate = bitrateEstimate == -1 ? (long) this.maxInitialBitrate : (long) (((float) bitrateEstimate) * this.bandwidthFraction);
            for (Format format : formats) {
                if (((long) format.bitrate) <= effectiveBitrate) {
                    return format;
                }
            }
            return formats[formats.length - 1];
        }
    }

    public static final class Evaluation {
        public Format format;
        public int queueSize;
        public int trigger = 1;
    }

    void disable();

    void enable();

    void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation);
}
