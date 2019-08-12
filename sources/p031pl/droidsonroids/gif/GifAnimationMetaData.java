package p031pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Locale;

/* renamed from: pl.droidsonroids.gif.GifAnimationMetaData */
public class GifAnimationMetaData implements Parcelable, Serializable {
    public static final Creator<GifAnimationMetaData> CREATOR = new Creator<GifAnimationMetaData>() {
        public GifAnimationMetaData createFromParcel(Parcel source) {
            return new GifAnimationMetaData(source);
        }

        public GifAnimationMetaData[] newArray(int size) {
            return new GifAnimationMetaData[size];
        }
    };
    private static final long serialVersionUID = 5692363926580237325L;
    private final int mDuration;
    private final int mHeight;
    private final int mImageCount;
    private final int mLoopCount;
    private final int mWidth;

    public GifAnimationMetaData(Resources res, int id) throws NotFoundException, IOException {
        this(res.openRawResourceFd(id));
    }

    public GifAnimationMetaData(AssetManager assets, String assetName) throws IOException {
        this(assets.openFd(assetName));
    }

    public GifAnimationMetaData(String filePath) throws IOException {
        this(new GifInfoHandle(filePath, true));
    }

    public GifAnimationMetaData(File file) throws IOException {
        this(file.getPath());
    }

    public GifAnimationMetaData(InputStream stream) throws IOException {
        this(new GifInfoHandle(stream, true));
    }

    public GifAnimationMetaData(AssetFileDescriptor afd) throws IOException {
        this(new GifInfoHandle(afd, true));
    }

    public GifAnimationMetaData(FileDescriptor fd) throws IOException {
        this(new GifInfoHandle(fd, 0, true));
    }

    public GifAnimationMetaData(byte[] bytes) throws IOException {
        this(new GifInfoHandle(bytes, true));
    }

    public GifAnimationMetaData(ByteBuffer buffer) throws IOException {
        this(new GifInfoHandle(buffer, true));
    }

    public GifAnimationMetaData(ContentResolver resolver, Uri uri) throws IOException {
        this(GifInfoHandle.openUri(resolver, uri, true));
    }

    private GifAnimationMetaData(GifInfoHandle gifInfoHandle) {
        this.mLoopCount = gifInfoHandle.getLoopCount();
        this.mDuration = gifInfoHandle.getDuration();
        this.mWidth = gifInfoHandle.getWidth();
        this.mHeight = gifInfoHandle.getHeight();
        this.mImageCount = gifInfoHandle.getNumberOfFrames();
        gifInfoHandle.recycle();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getNumberOfFrames() {
        return this.mImageCount;
    }

    public int getLoopCount() {
        return this.mLoopCount;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public boolean isAnimated() {
        return this.mImageCount > 1 && this.mDuration > 0;
    }

    public String toString() {
        String suffix = String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, loops: %s, duration: %d", new Object[]{Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mImageCount), this.mLoopCount == 0 ? "Infinity" : Integer.toString(this.mLoopCount), Integer.valueOf(this.mDuration)});
        return isAnimated() ? "Animated " + suffix : suffix;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mLoopCount);
        dest.writeInt(this.mDuration);
        dest.writeInt(this.mHeight);
        dest.writeInt(this.mWidth);
        dest.writeInt(this.mImageCount);
    }

    private GifAnimationMetaData(Parcel in) {
        this.mLoopCount = in.readInt();
        this.mDuration = in.readInt();
        this.mHeight = in.readInt();
        this.mWidth = in.readInt();
        this.mImageCount = in.readInt();
    }
}
