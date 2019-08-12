package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.Loader.Loadable;
import com.google.android.exoplayer.util.Assertions;

public abstract class Chunk implements Loadable {
    protected final DataSource dataSource;
    public final DataSpec dataSpec;
    public final Format format;
    public final int parentId;
    public final int trigger;
    public final int type;

    public abstract long bytesLoaded();

    public Chunk(DataSource dataSource2, DataSpec dataSpec2, int type2, int trigger2, Format format2, int parentId2) {
        this.dataSource = (DataSource) Assertions.checkNotNull(dataSource2);
        this.dataSpec = (DataSpec) Assertions.checkNotNull(dataSpec2);
        this.type = type2;
        this.trigger = trigger2;
        this.format = format2;
        this.parentId = parentId2;
    }
}
