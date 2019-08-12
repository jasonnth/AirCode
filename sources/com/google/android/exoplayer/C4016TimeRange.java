package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Clock;

/* renamed from: com.google.android.exoplayer.TimeRange */
public interface C4016TimeRange {

    /* renamed from: com.google.android.exoplayer.TimeRange$DynamicTimeRange */
    public static final class DynamicTimeRange implements C4016TimeRange {
        private final long bufferDepthUs;
        private final long elapsedRealtimeAtStartUs;
        private final long maxEndTimeUs;
        private final long minStartTimeUs;
        private final Clock systemClock;

        public DynamicTimeRange(long minStartTimeUs2, long maxEndTimeUs2, long elapsedRealtimeAtStartUs2, long bufferDepthUs2, Clock systemClock2) {
            this.minStartTimeUs = minStartTimeUs2;
            this.maxEndTimeUs = maxEndTimeUs2;
            this.elapsedRealtimeAtStartUs = elapsedRealtimeAtStartUs2;
            this.bufferDepthUs = bufferDepthUs2;
            this.systemClock = systemClock2;
        }

        public long[] getCurrentBoundsUs(long[] out) {
            if (out == null || out.length < 2) {
                out = new long[2];
            }
            long currentEndTimeUs = Math.min(this.maxEndTimeUs, (this.systemClock.elapsedRealtime() * 1000) - this.elapsedRealtimeAtStartUs);
            long currentStartTimeUs = this.minStartTimeUs;
            if (this.bufferDepthUs != -1) {
                currentStartTimeUs = Math.max(currentStartTimeUs, currentEndTimeUs - this.bufferDepthUs);
            }
            out[0] = currentStartTimeUs;
            out[1] = currentEndTimeUs;
            return out;
        }

        public int hashCode() {
            return ((((((((int) this.minStartTimeUs) + 527) * 31) + ((int) this.maxEndTimeUs)) * 31) + ((int) this.elapsedRealtimeAtStartUs)) * 31) + ((int) this.bufferDepthUs);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DynamicTimeRange other = (DynamicTimeRange) obj;
            if (other.minStartTimeUs == this.minStartTimeUs && other.maxEndTimeUs == this.maxEndTimeUs && other.elapsedRealtimeAtStartUs == this.elapsedRealtimeAtStartUs && other.bufferDepthUs == this.bufferDepthUs) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.google.android.exoplayer.TimeRange$StaticTimeRange */
    public static final class StaticTimeRange implements C4016TimeRange {
        private final long endTimeUs;
        private final long startTimeUs;

        public StaticTimeRange(long startTimeUs2, long endTimeUs2) {
            this.startTimeUs = startTimeUs2;
            this.endTimeUs = endTimeUs2;
        }

        public long[] getCurrentBoundsUs(long[] out) {
            if (out == null || out.length < 2) {
                out = new long[2];
            }
            out[0] = this.startTimeUs;
            out[1] = this.endTimeUs;
            return out;
        }

        public int hashCode() {
            return ((((int) this.startTimeUs) + 527) * 31) + ((int) this.endTimeUs);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            StaticTimeRange other = (StaticTimeRange) obj;
            if (other.startTimeUs == this.startTimeUs && other.endTimeUs == this.endTimeUs) {
                return true;
            }
            return false;
        }
    }

    long[] getCurrentBoundsUs(long[] jArr);
}
