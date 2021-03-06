package com.danikula.videocache;

import com.danikula.videocache.file.DiskUsage;
import com.danikula.videocache.file.FileNameGenerator;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import java.io.File;

class Config {
    public final File cacheRoot;
    public final DiskUsage diskUsage;
    public final FileNameGenerator fileNameGenerator;
    public final SourceInfoStorage sourceInfoStorage;

    Config(File cacheRoot2, FileNameGenerator fileNameGenerator2, DiskUsage diskUsage2, SourceInfoStorage sourceInfoStorage2) {
        this.cacheRoot = cacheRoot2;
        this.fileNameGenerator = fileNameGenerator2;
        this.diskUsage = diskUsage2;
        this.sourceInfoStorage = sourceInfoStorage2;
    }

    /* access modifiers changed from: 0000 */
    public File generateCacheFile(String url) {
        return new File(this.cacheRoot, this.fileNameGenerator.generate(url));
    }
}
