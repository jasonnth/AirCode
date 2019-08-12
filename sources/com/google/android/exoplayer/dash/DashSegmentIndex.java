package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.dash.mpd.RangedUri;

public interface DashSegmentIndex {
    long getDurationUs(int i, long j);

    int getFirstSegmentNum();

    int getLastSegmentNum(long j);

    int getSegmentNum(long j, long j2);

    RangedUri getSegmentUrl(int i);

    long getTimeUs(int i);

    boolean isExplicit();
}