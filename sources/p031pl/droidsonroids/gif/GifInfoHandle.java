package p031pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.Surface;
import com.facebook.common.util.UriUtil;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: pl.droidsonroids.gif.GifInfoHandle */
final class GifInfoHandle {
    static final GifInfoHandle NULL_INFO = new GifInfoHandle();
    private volatile long gifInfoPtr;

    private static native void bindSurface(long j, Surface surface, long[] jArr, boolean z);

    private static native void free(long j);

    private static native long getAllocationByteCount(long j);

    private static native String getComment(long j);

    private static native int getCurrentFrameIndex(long j);

    private static native int getCurrentLoop(long j);

    private static native int getCurrentPosition(long j);

    private static native int getDuration(long j);

    private static native int getFrameDuration(long j, int i);

    private static native int getHeight(long j);

    private static native int getLoopCount(long j);

    private static native int getNativeErrorCode(long j);

    private static native int getNumberOfFrames(long j);

    private static native long[] getSavedState(long j);

    private static native long getSourceLength(long j);

    private static native int getWidth(long j);

    private static native boolean isAnimationCompleted(long j);

    static native long openByteArray(byte[] bArr, boolean z) throws GifIOException;

    static native long openDirectByteBuffer(ByteBuffer byteBuffer, boolean z) throws GifIOException;

    static native long openFd(FileDescriptor fileDescriptor, long j, boolean z) throws GifIOException;

    static native long openFile(String str, boolean z) throws GifIOException;

    static native long openStream(InputStream inputStream, boolean z) throws GifIOException;

    private static native void postUnbindSurface(long j);

    private static native long renderFrame(long j, Bitmap bitmap);

    private static native boolean reset(long j);

    private static native long restoreRemainder(long j);

    private static native int restoreSavedState(long j, long[] jArr, Bitmap bitmap);

    private static native void saveRemainder(long j);

    private static native void seekToFrame(long j, int i, Bitmap bitmap);

    private static native void seekToTime(long j, int i, Bitmap bitmap);

    private static native void setLoopCount(long j, int i);

    private static native int setSampleSize(long j, int i);

    private static native void setSpeedFactor(long j, float f);

    static {
        LibraryLoader.loadLibrary(null, "pl_droidsonroids_gif");
    }

    private GifInfoHandle() {
    }

    GifInfoHandle(FileDescriptor fd, long offset, boolean justDecodeMetaData) throws GifIOException {
        this.gifInfoPtr = openFd(fd, offset, justDecodeMetaData);
    }

    GifInfoHandle(byte[] bytes, boolean justDecodeMetaData) throws GifIOException {
        this.gifInfoPtr = openByteArray(bytes, justDecodeMetaData);
    }

    GifInfoHandle(ByteBuffer buffer, boolean justDecodeMetaData) throws GifIOException {
        this.gifInfoPtr = openDirectByteBuffer(buffer, justDecodeMetaData);
    }

    GifInfoHandle(String filePath, boolean justDecodeMetaData) throws GifIOException {
        this.gifInfoPtr = openFile(filePath, justDecodeMetaData);
    }

    GifInfoHandle(InputStream stream, boolean justDecodeMetaData) throws GifIOException {
        if (!stream.markSupported()) {
            throw new IllegalArgumentException("InputStream does not support marking");
        }
        this.gifInfoPtr = openStream(stream, justDecodeMetaData);
    }

    GifInfoHandle(AssetFileDescriptor afd, boolean justDecodeMetaData) throws IOException {
        try {
            this.gifInfoPtr = openFd(afd.getFileDescriptor(), afd.getStartOffset(), justDecodeMetaData);
        } finally {
            afd.close();
        }
    }

    static GifInfoHandle openUri(ContentResolver resolver, Uri uri, boolean justDecodeMetaData) throws IOException {
        if (UriUtil.LOCAL_FILE_SCHEME.equals(uri.getScheme())) {
            return new GifInfoHandle(uri.getPath(), justDecodeMetaData);
        }
        return new GifInfoHandle(resolver.openAssetFileDescriptor(uri, "r"), justDecodeMetaData);
    }

    /* access modifiers changed from: 0000 */
    public synchronized long renderFrame(Bitmap frameBuffer) {
        return renderFrame(this.gifInfoPtr, frameBuffer);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void recycle() {
        free(this.gifInfoPtr);
        this.gifInfoPtr = 0;
    }

    /* access modifiers changed from: 0000 */
    public synchronized long restoreRemainder() {
        return restoreRemainder(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean reset() {
        return reset(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void saveRemainder() {
        saveRemainder(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getLoopCount() {
        return getLoopCount(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getNativeErrorCode() {
        return getNativeErrorCode(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getDuration() {
        return getDuration(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getCurrentPosition() {
        return getCurrentPosition(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getCurrentFrameIndex() {
        return getCurrentFrameIndex(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getCurrentLoop() {
        return getCurrentLoop(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void seekToTime(int position, Bitmap buffer) {
        seekToTime(this.gifInfoPtr, position, buffer);
    }

    /* access modifiers changed from: 0000 */
    public synchronized boolean isRecycled() {
        return this.gifInfoPtr == 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            recycle();
        } finally {
            super.finalize();
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized long[] getSavedState() {
        return getSavedState(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int restoreSavedState(long[] savedState, Bitmap mBuffer) {
        return restoreSavedState(this.gifInfoPtr, savedState, mBuffer);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getWidth() {
        return getWidth(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getHeight() {
        return getHeight(this.gifInfoPtr);
    }

    /* access modifiers changed from: 0000 */
    public synchronized int getNumberOfFrames() {
        return getNumberOfFrames(this.gifInfoPtr);
    }
}
