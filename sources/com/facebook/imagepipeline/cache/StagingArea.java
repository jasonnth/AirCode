package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StagingArea {
    private static final Class<?> TAG = StagingArea.class;
    private Map<CacheKey, EncodedImage> mMap = new HashMap();

    private StagingArea() {
    }

    public static StagingArea getInstance() {
        return new StagingArea();
    }

    public synchronized void put(CacheKey key, EncodedImage encodedImage) {
        Preconditions.checkNotNull(key);
        Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
        EncodedImage.closeSafely((EncodedImage) this.mMap.put(key, EncodedImage.cloneOrNull(encodedImage)));
        logStats();
    }

    public void clearAll() {
        List<EncodedImage> old;
        synchronized (this) {
            old = new ArrayList<>(this.mMap.values());
            this.mMap.clear();
        }
        for (int i = 0; i < old.size(); i++) {
            EncodedImage encodedImage = (EncodedImage) old.get(i);
            if (encodedImage != null) {
                encodedImage.close();
            }
        }
    }

    public boolean remove(CacheKey key) {
        EncodedImage encodedImage;
        Preconditions.checkNotNull(key);
        synchronized (this) {
            encodedImage = (EncodedImage) this.mMap.remove(key);
        }
        if (encodedImage == null) {
            return false;
        }
        try {
            return encodedImage.isValid();
        } finally {
            encodedImage.close();
        }
    }

    /* JADX INFO: finally extract failed */
    public synchronized boolean remove(CacheKey key, EncodedImage encodedImage) {
        boolean z = false;
        synchronized (this) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(encodedImage);
            Preconditions.checkArgument(EncodedImage.isValid(encodedImage));
            EncodedImage oldValue = (EncodedImage) this.mMap.get(key);
            if (oldValue != null) {
                CloseableReference<PooledByteBuffer> oldRef = oldValue.getByteBufferRef();
                CloseableReference<PooledByteBuffer> ref = encodedImage.getByteBufferRef();
                if (!(oldRef == null || ref == null)) {
                    try {
                        if (oldRef.get() == ref.get()) {
                            this.mMap.remove(key);
                            CloseableReference.closeSafely(ref);
                            CloseableReference.closeSafely(oldRef);
                            EncodedImage.closeSafely(oldValue);
                            logStats();
                            z = true;
                        }
                    } catch (Throwable th) {
                        CloseableReference.closeSafely(ref);
                        CloseableReference.closeSafely(oldRef);
                        EncodedImage.closeSafely(oldValue);
                        throw th;
                    }
                }
                CloseableReference.closeSafely(ref);
                CloseableReference.closeSafely(oldRef);
                EncodedImage.closeSafely(oldValue);
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        r0 = r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.facebook.imagepipeline.image.EncodedImage get(com.facebook.cache.common.CacheKey r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.facebook.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x0052 }
            java.util.Map<com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage> r2 = r7.mMap     // Catch:{ all -> 0x0052 }
            java.lang.Object r0 = r2.get(r8)     // Catch:{ all -> 0x0052 }
            com.facebook.imagepipeline.image.EncodedImage r0 = (com.facebook.imagepipeline.image.EncodedImage) r0     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x004c
            monitor-enter(r0)     // Catch:{ all -> 0x0052 }
            boolean r2 = com.facebook.imagepipeline.image.EncodedImage.isValid(r0)     // Catch:{ all -> 0x004e }
            if (r2 != 0) goto L_0x0046
            java.util.Map<com.facebook.cache.common.CacheKey, com.facebook.imagepipeline.image.EncodedImage> r2 = r7.mMap     // Catch:{ all -> 0x004e }
            r2.remove(r8)     // Catch:{ all -> 0x004e }
            java.lang.Class<?> r2 = TAG     // Catch:{ all -> 0x004e }
            java.lang.String r3 = "Found closed reference %d for key %s (%d)"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x004e }
            r5 = 0
            int r6 = java.lang.System.identityHashCode(r0)     // Catch:{ all -> 0x004e }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x004e }
            r4[r5] = r6     // Catch:{ all -> 0x004e }
            r5 = 1
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x004e }
            r4[r5] = r6     // Catch:{ all -> 0x004e }
            r5 = 2
            int r6 = java.lang.System.identityHashCode(r8)     // Catch:{ all -> 0x004e }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x004e }
            r4[r5] = r6     // Catch:{ all -> 0x004e }
            com.facebook.common.logging.FLog.m1845w(r2, r3, r4)     // Catch:{ all -> 0x004e }
            r2 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
        L_0x0044:
            monitor-exit(r7)
            return r2
        L_0x0046:
            com.facebook.imagepipeline.image.EncodedImage r1 = com.facebook.imagepipeline.image.EncodedImage.cloneOrNull(r0)     // Catch:{ all -> 0x004e }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            r0 = r1
        L_0x004c:
            r2 = r0
            goto L_0x0044
        L_0x004e:
            r2 = move-exception
            r1 = r0
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r2     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r2 = move-exception
            monitor-exit(r7)
            throw r2
        L_0x0055:
            r2 = move-exception
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.StagingArea.get(com.facebook.cache.common.CacheKey):com.facebook.imagepipeline.image.EncodedImage");
    }

    public synchronized boolean containsKey(CacheKey key) {
        boolean z = false;
        synchronized (this) {
            Preconditions.checkNotNull(key);
            if (this.mMap.containsKey(key)) {
                EncodedImage storedEncodedImage = (EncodedImage) this.mMap.get(key);
                synchronized (storedEncodedImage) {
                    if (!EncodedImage.isValid(storedEncodedImage)) {
                        this.mMap.remove(key);
                        FLog.m1845w(TAG, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(storedEncodedImage)), key.toString(), Integer.valueOf(System.identityHashCode(key)));
                    } else {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private synchronized void logStats() {
        FLog.m1828v(TAG, "Count = %d", (Object) Integer.valueOf(this.mMap.size()));
    }
}
