package android.support.p000v4.media.session;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession.QueueItem;
import android.media.session.MediaSession.Token;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.ResultReceiver;
import java.util.List;

@TargetApi(21)
/* renamed from: android.support.v4.media.session.MediaControllerCompatApi21 */
class MediaControllerCompatApi21 {

    /* renamed from: android.support.v4.media.session.MediaControllerCompatApi21$Callback */
    public interface Callback {
        void onAudioInfoChanged(int i, int i2, int i3, int i4, int i5);

        void onExtrasChanged(Bundle bundle);

        void onMetadataChanged(Object obj);

        void onPlaybackStateChanged(Object obj);

        void onQueueChanged(List<?> list);

        void onQueueTitleChanged(CharSequence charSequence);

        void onSessionDestroyed();

        void onSessionEvent(String str, Bundle bundle);
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompatApi21$CallbackProxy */
    static class CallbackProxy<T extends Callback> extends android.media.session.MediaController.Callback {
        protected final T mCallback;

        public CallbackProxy(T callback) {
            this.mCallback = callback;
        }

        public void onSessionDestroyed() {
            this.mCallback.onSessionDestroyed();
        }

        public void onSessionEvent(String event, Bundle extras) {
            this.mCallback.onSessionEvent(event, extras);
        }

        public void onPlaybackStateChanged(PlaybackState state) {
            this.mCallback.onPlaybackStateChanged(state);
        }

        public void onMetadataChanged(MediaMetadata metadata) {
            this.mCallback.onMetadataChanged(metadata);
        }

        public void onQueueChanged(List<QueueItem> queue) {
            this.mCallback.onQueueChanged(queue);
        }

        public void onQueueTitleChanged(CharSequence title) {
            this.mCallback.onQueueTitleChanged(title);
        }

        public void onExtrasChanged(Bundle extras) {
            this.mCallback.onExtrasChanged(extras);
        }

        public void onAudioInfoChanged(android.media.session.MediaController.PlaybackInfo info) {
            this.mCallback.onAudioInfoChanged(info.getPlaybackType(), PlaybackInfo.getLegacyAudioStream(info), info.getVolumeControl(), info.getMaxVolume(), info.getCurrentVolume());
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompatApi21$PlaybackInfo */
    public static class PlaybackInfo {
        public static AudioAttributes getAudioAttributes(Object volumeInfoObj) {
            return ((android.media.session.MediaController.PlaybackInfo) volumeInfoObj).getAudioAttributes();
        }

        public static int getLegacyAudioStream(Object volumeInfoObj) {
            return toLegacyStreamType(getAudioAttributes(volumeInfoObj));
        }

        private static int toLegacyStreamType(AudioAttributes aa) {
            if ((aa.getFlags() & 1) == 1) {
                return 7;
            }
            if ((aa.getFlags() & 4) == 4) {
                return 6;
            }
            switch (aa.getUsage()) {
                case 2:
                    return 0;
                case 3:
                    return 8;
                case 4:
                    return 4;
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                    return 5;
                case 6:
                    return 2;
                case 13:
                    return 1;
                default:
                    return 3;
            }
        }
    }

    public static Object fromToken(Context context, Object sessionToken) {
        return new MediaController(context, (Token) sessionToken);
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    public static void setMediaController(Activity activity, Object controllerObj) {
        activity.setMediaController((MediaController) controllerObj);
    }

    public static Object getMediaController(Activity activity) {
        return activity.getMediaController();
    }

    public static Object getSessionToken(Object controllerObj) {
        return ((MediaController) controllerObj).getSessionToken();
    }

    public static void sendCommand(Object controllerObj, String command, Bundle params, ResultReceiver cb) {
        ((MediaController) controllerObj).sendCommand(command, params, cb);
    }
}
