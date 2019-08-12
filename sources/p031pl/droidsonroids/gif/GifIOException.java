package p031pl.droidsonroids.gif;

import java.io.IOException;

/* renamed from: pl.droidsonroids.gif.GifIOException */
public class GifIOException extends IOException {
    public final GifError reason;

    private GifIOException(GifError reason2) {
        super(reason2.getFormattedDescription());
        this.reason = reason2;
    }

    GifIOException(int errorCode) {
        this(GifError.fromCode(errorCode));
    }
}
