package com.google.android.exoplayer.dash.mpd;

import android.net.Uri;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.FormatWrapper;
import com.google.android.exoplayer.dash.DashSegmentIndex;
import com.google.android.exoplayer.dash.mpd.SegmentBase.MultiSegmentBase;
import com.google.android.exoplayer.dash.mpd.SegmentBase.SingleSegmentBase;

public abstract class Representation implements FormatWrapper {
    private final String cacheKey;
    public final String contentId;
    public final Format format;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;

    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        private final MultiSegmentBase segmentBase;

        public MultiSegmentRepresentation(String contentId, long revisionId, Format format, MultiSegmentBase segmentBase2, String customCacheKey) {
            super(contentId, revisionId, format, segmentBase2, customCacheKey);
            this.segmentBase = segmentBase2;
        }

        public RangedUri getIndexUri() {
            return null;
        }

        public DashSegmentIndex getIndex() {
            return this;
        }

        public RangedUri getSegmentUrl(int segmentIndex) {
            return this.segmentBase.getSegmentUrl(this, segmentIndex);
        }

        public int getSegmentNum(long timeUs, long periodDurationUs) {
            return this.segmentBase.getSegmentNum(timeUs, periodDurationUs);
        }

        public long getTimeUs(int segmentIndex) {
            return this.segmentBase.getSegmentTimeUs(segmentIndex);
        }

        public long getDurationUs(int segmentIndex, long periodDurationUs) {
            return this.segmentBase.getSegmentDurationUs(segmentIndex, periodDurationUs);
        }

        public int getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        public int getLastSegmentNum(long periodDurationUs) {
            return this.segmentBase.getLastSegmentNum(periodDurationUs);
        }

        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }

    public static class SingleSegmentRepresentation extends Representation {
        public final long contentLength;
        private final RangedUri indexUri;
        private final DashSingleSegmentIndex segmentIndex;
        public final Uri uri;

        public SingleSegmentRepresentation(String contentId, long revisionId, Format format, SingleSegmentBase segmentBase, String customCacheKey, long contentLength2) {
            super(contentId, revisionId, format, segmentBase, customCacheKey);
            this.uri = Uri.parse(segmentBase.uri);
            this.indexUri = segmentBase.getIndex();
            this.contentLength = contentLength2;
            this.segmentIndex = this.indexUri != null ? null : new DashSingleSegmentIndex(new RangedUri(segmentBase.uri, null, 0, contentLength2));
        }

        public RangedUri getIndexUri() {
            return this.indexUri;
        }

        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }
    }

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public static Representation newInstance(String contentId2, long revisionId2, Format format2, SegmentBase segmentBase) {
        return newInstance(contentId2, revisionId2, format2, segmentBase, null);
    }

    public static Representation newInstance(String contentId2, long revisionId2, Format format2, SegmentBase segmentBase, String customCacheKey) {
        if (segmentBase instanceof SingleSegmentBase) {
            return new SingleSegmentRepresentation(contentId2, revisionId2, format2, (SingleSegmentBase) segmentBase, customCacheKey, -1);
        } else if (segmentBase instanceof MultiSegmentBase) {
            return new MultiSegmentRepresentation(contentId2, revisionId2, format2, (MultiSegmentBase) segmentBase, customCacheKey);
        } else {
            throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
        }
    }

    private Representation(String contentId2, long revisionId2, Format format2, SegmentBase segmentBase, String customCacheKey) {
        this.contentId = contentId2;
        this.revisionId = revisionId2;
        this.format = format2;
        if (customCacheKey == null) {
            customCacheKey = contentId2 + "." + format2.f3148id + "." + revisionId2;
        }
        this.cacheKey = customCacheKey;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
    }

    public Format getFormat() {
        return this.format;
    }

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }
}
