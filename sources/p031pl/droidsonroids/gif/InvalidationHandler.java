package p031pl.droidsonroids.gif;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* renamed from: pl.droidsonroids.gif.InvalidationHandler */
class InvalidationHandler extends Handler {
    private final WeakReference<GifDrawable> mDrawableRef;

    public void handleMessage(Message msg) {
        GifDrawable gifDrawable = (GifDrawable) this.mDrawableRef.get();
        if (gifDrawable != null) {
            if (msg.what == -1) {
                gifDrawable.invalidateSelf();
                return;
            }
            Iterator it = gifDrawable.mListeners.iterator();
            while (it.hasNext()) {
                ((AnimationListener) it.next()).onAnimationCompleted(msg.what);
            }
        }
    }
}
