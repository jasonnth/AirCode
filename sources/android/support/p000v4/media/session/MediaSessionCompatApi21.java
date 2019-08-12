package android.support.p000v4.media.session;

import android.annotation.TargetApi;
import android.media.MediaDescription;
import android.media.session.MediaSession.Token;

@TargetApi(21)
/* renamed from: android.support.v4.media.session.MediaSessionCompatApi21 */
class MediaSessionCompatApi21 {

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi21$QueueItem */
    static class QueueItem {
        public static Object createItem(Object mediaDescription, long id) {
            return new android.media.session.MediaSession.QueueItem((MediaDescription) mediaDescription, id);
        }

        public static Object getDescription(Object queueItem) {
            return ((android.media.session.MediaSession.QueueItem) queueItem).getDescription();
        }

        public static long getQueueId(Object queueItem) {
            return ((android.media.session.MediaSession.QueueItem) queueItem).getQueueId();
        }
    }

    public static Object verifyToken(Object token) {
        if (token instanceof Token) {
            return token;
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }
}
