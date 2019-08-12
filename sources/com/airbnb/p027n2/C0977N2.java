package com.airbnb.p027n2;

/* renamed from: com.airbnb.n2.N2 */
public class C0977N2 {
    private final Callbacks callbacks;

    /* renamed from: com.airbnb.n2.N2$Callbacks */
    public interface Callbacks {
        public static final Callbacks EMPTY = new Callbacks() {
            public void onImageLoaded(long loadTime, boolean fromMemoryCache) {
            }

            public void onImageLoadCleared(long loadTime) {
            }

            public void onImageLoadError(long loadTime, Exception exception) {
            }

            public boolean shouldOverrideFont() {
                return false;
            }

            public void onNotifyException(Exception exception) {
            }

            public void onThrowOrNotify(RuntimeException exception) {
            }

            public boolean isDevelopmentBuild() {
                return true;
            }
        };

        boolean isDevelopmentBuild();

        void onImageLoadCleared(long j);

        void onImageLoadError(long j, Exception exc);

        void onImageLoaded(long j, boolean z);

        void onNotifyException(Exception exc);

        void onThrowOrNotify(RuntimeException runtimeException);

        boolean shouldOverrideFont();
    }

    C0977N2(Callbacks callbacks2) {
        this.callbacks = callbacks2;
    }

    public void onImageLoaded(long loadTime, boolean fromMemoryCache) {
        this.callbacks.onImageLoaded(loadTime, fromMemoryCache);
    }

    public void onImageLoadCleared(long loadTime) {
        this.callbacks.onImageLoadCleared(loadTime);
    }

    public void onImageLoadError(long loadTime, Exception exception) {
        this.callbacks.onImageLoadError(loadTime, exception);
    }

    public boolean shouldOverrideFont() {
        return this.callbacks.shouldOverrideFont();
    }

    public void onNotifyException(Exception exception) {
        this.callbacks.onNotifyException(exception);
    }

    public void throwOrNotify(RuntimeException exception) {
        this.callbacks.onThrowOrNotify(exception);
    }

    public boolean isDebugBuild() {
        return this.callbacks.isDevelopmentBuild();
    }
}
