package android.support.p000v4.media;

import android.annotation.TargetApi;

@TargetApi(21)
/* renamed from: android.support.v4.media.MediaBrowserCompatApi21 */
class MediaBrowserCompatApi21 {

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi21$MediaItem */
    static class MediaItem {
        public static int getFlags(Object itemObj) {
            return ((android.media.browse.MediaBrowser.MediaItem) itemObj).getFlags();
        }

        public static Object getDescription(Object itemObj) {
            return ((android.media.browse.MediaBrowser.MediaItem) itemObj).getDescription();
        }
    }
}
