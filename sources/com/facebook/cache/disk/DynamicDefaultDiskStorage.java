package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfo;
import com.facebook.cache.disk.DiskStorage.Entry;
import com.facebook.cache.disk.DiskStorage.Inserter;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils;
import com.facebook.common.file.FileUtils.CreateDirectoryException;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DynamicDefaultDiskStorage implements DiskStorage {
    private static final Class<?> TAG = DynamicDefaultDiskStorage.class;
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    @VisibleForTesting
    volatile State mCurrentState = new State(null, null);
    private final int mVersion;

    @VisibleForTesting
    static class State {
        public final DiskStorage delegate;
        public final File rootDirectory;

        @VisibleForTesting
        State(File rootDirectory2, DiskStorage delegate2) {
            this.delegate = delegate2;
            this.rootDirectory = rootDirectory2;
        }
    }

    public DynamicDefaultDiskStorage(int version, Supplier<File> baseDirectoryPathSupplier, String baseDirectoryName, CacheErrorLogger cacheErrorLogger) {
        this.mVersion = version;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mBaseDirectoryPathSupplier = baseDirectoryPathSupplier;
        this.mBaseDirectoryName = baseDirectoryName;
    }

    public boolean isEnabled() {
        try {
            return get().isEnabled();
        } catch (IOException e) {
            return false;
        }
    }

    public BinaryResource getResource(String resourceId, Object debugInfo) throws IOException {
        return get().getResource(resourceId, debugInfo);
    }

    public boolean contains(String resourceId, Object debugInfo) throws IOException {
        return get().contains(resourceId, debugInfo);
    }

    public boolean touch(String resourceId, Object debugInfo) throws IOException {
        return get().touch(resourceId, debugInfo);
    }

    public void purgeUnexpectedResources() {
        try {
            get().purgeUnexpectedResources();
        } catch (IOException ioe) {
            FLog.m1804e(TAG, "purgeUnexpectedResources", (Throwable) ioe);
        }
    }

    public Inserter insert(String resourceId, Object debugInfo) throws IOException {
        return get().insert(resourceId, debugInfo);
    }

    public Collection<Entry> getEntries() throws IOException {
        return get().getEntries();
    }

    public long remove(Entry entry) throws IOException {
        return get().remove(entry);
    }

    public long remove(String resourceId) throws IOException {
        return get().remove(resourceId);
    }

    public void clearAll() throws IOException {
        get().clearAll();
    }

    public DiskDumpInfo getDumpInfo() throws IOException {
        return get().getDumpInfo();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized DiskStorage get() throws IOException {
        if (shouldCreateNewStorage()) {
            deleteOldStorageIfNecessary();
            createStorage();
        }
        return (DiskStorage) Preconditions.checkNotNull(this.mCurrentState.delegate);
    }

    private boolean shouldCreateNewStorage() {
        State currentState = this.mCurrentState;
        return currentState.delegate == null || currentState.rootDirectory == null || !currentState.rootDirectory.exists();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void deleteOldStorageIfNecessary() {
        if (this.mCurrentState.delegate != null && this.mCurrentState.rootDirectory != null) {
            FileTree.deleteRecursively(this.mCurrentState.rootDirectory);
        }
    }

    private void createStorage() throws IOException {
        File rootDirectory = new File((File) this.mBaseDirectoryPathSupplier.get(), this.mBaseDirectoryName);
        createRootDirectoryIfNecessary(rootDirectory);
        this.mCurrentState = new State(rootDirectory, new DefaultDiskStorage(rootDirectory, this.mVersion, this.mCacheErrorLogger));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void createRootDirectoryIfNecessary(File rootDirectory) throws IOException {
        try {
            FileUtils.mkdirs(rootDirectory);
            FLog.m1788d(TAG, "Created cache directory %s", (Object) rootDirectory.getAbsolutePath());
        } catch (CreateDirectoryException cde) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_DIR, TAG, "createRootDirectoryIfNecessary", cde);
            throw cde;
        }
    }
}
