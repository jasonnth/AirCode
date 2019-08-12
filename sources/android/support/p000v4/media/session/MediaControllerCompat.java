package android.support.p000v4.media.session;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.app.SupportActivity;
import android.support.p000v4.app.SupportActivity.ExtraData;
import android.support.p000v4.media.MediaMetadataCompat;
import android.support.p000v4.media.session.IMediaControllerCallback.Stub;
import android.support.p000v4.media.session.MediaSessionCompat.QueueItem;
import android.support.p000v4.media.session.MediaSessionCompat.Token;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaControllerCompat */
public final class MediaControllerCompat {
    private final MediaControllerImpl mImpl;
    private final Token mToken;

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback */
    public static abstract class Callback implements DeathRecipient {
        private final Object mCallbackObj;
        MessageHandler mHandler;
        boolean mHasExtraCallback;
        boolean mRegistered = false;

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$MessageHandler */
        private class MessageHandler extends Handler {
            final /* synthetic */ Callback this$0;

            public void handleMessage(Message msg) {
                if (this.this$0.mRegistered) {
                    switch (msg.what) {
                        case 1:
                            this.this$0.onSessionEvent((String) msg.obj, msg.getData());
                            return;
                        case 2:
                            this.this$0.onPlaybackStateChanged((PlaybackStateCompat) msg.obj);
                            return;
                        case 3:
                            this.this$0.onMetadataChanged((MediaMetadataCompat) msg.obj);
                            return;
                        case 4:
                            this.this$0.onAudioInfoChanged((PlaybackInfo) msg.obj);
                            return;
                        case 5:
                            this.this$0.onQueueChanged((List) msg.obj);
                            return;
                        case 6:
                            this.this$0.onQueueTitleChanged((CharSequence) msg.obj);
                            return;
                        case 7:
                            this.this$0.onExtrasChanged((Bundle) msg.obj);
                            return;
                        case 8:
                            this.this$0.onSessionDestroyed();
                            return;
                        case 9:
                            this.this$0.onRepeatModeChanged(((Integer) msg.obj).intValue());
                            return;
                        case 10:
                            this.this$0.onShuffleModeChanged(((Boolean) msg.obj).booleanValue());
                            return;
                        case 11:
                            this.this$0.onCaptioningEnabledChanged(((Boolean) msg.obj).booleanValue());
                            return;
                        default:
                            return;
                    }
                }
            }

            public void post(int what, Object obj, Bundle data) {
                Message msg = obtainMessage(what, obj);
                msg.setData(data);
                msg.sendToTarget();
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$StubApi21 */
        private class StubApi21 implements android.support.p000v4.media.session.MediaControllerCompatApi21.Callback {
            StubApi21() {
            }

            public void onSessionDestroyed() {
                Callback.this.onSessionDestroyed();
            }

            public void onSessionEvent(String event, Bundle extras) {
                if (!Callback.this.mHasExtraCallback || VERSION.SDK_INT >= 23) {
                    Callback.this.onSessionEvent(event, extras);
                }
            }

            public void onPlaybackStateChanged(Object stateObj) {
                if (!Callback.this.mHasExtraCallback) {
                    Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(stateObj));
                }
            }

            public void onMetadataChanged(Object metadataObj) {
                Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(metadataObj));
            }

            public void onQueueChanged(List<?> queue) {
                Callback.this.onQueueChanged(QueueItem.fromQueueItemList(queue));
            }

            public void onQueueTitleChanged(CharSequence title) {
                Callback.this.onQueueTitleChanged(title);
            }

            public void onExtrasChanged(Bundle extras) {
                Callback.this.onExtrasChanged(extras);
            }

            public void onAudioInfoChanged(int type, int stream, int control, int max, int current) {
                Callback.this.onAudioInfoChanged(new PlaybackInfo(type, stream, control, max, current));
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat */
        private class StubCompat extends Stub {
            StubCompat() {
            }

            public void onEvent(String event, Bundle extras) throws RemoteException {
                Callback.this.mHandler.post(1, event, extras);
            }

            public void onSessionDestroyed() throws RemoteException {
                Callback.this.mHandler.post(8, null, null);
            }

            public void onPlaybackStateChanged(PlaybackStateCompat state) throws RemoteException {
                Callback.this.mHandler.post(2, state, null);
            }

            public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
                Callback.this.mHandler.post(3, metadata, null);
            }

            public void onQueueChanged(List<QueueItem> queue) throws RemoteException {
                Callback.this.mHandler.post(5, queue, null);
            }

            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                Callback.this.mHandler.post(6, title, null);
            }

            public void onCaptioningEnabledChanged(boolean enabled) throws RemoteException {
                Callback.this.mHandler.post(11, Boolean.valueOf(enabled), null);
            }

            public void onRepeatModeChanged(int repeatMode) throws RemoteException {
                Callback.this.mHandler.post(9, Integer.valueOf(repeatMode), null);
            }

            public void onShuffleModeChanged(boolean enabled) throws RemoteException {
                Callback.this.mHandler.post(10, Boolean.valueOf(enabled), null);
            }

            public void onExtrasChanged(Bundle extras) throws RemoteException {
                Callback.this.mHandler.post(7, extras, null);
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
                PlaybackInfo pi = null;
                if (info != null) {
                    pi = new PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
                }
                Callback.this.mHandler.post(4, pi, null);
            }
        }

        public Callback() {
            if (VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21());
            } else {
                this.mCallbackObj = new StubCompat();
            }
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String event, Bundle extras) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat state) {
        }

        public void onMetadataChanged(MediaMetadataCompat metadata) {
        }

        public void onQueueChanged(List<QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence title) {
        }

        public void onExtrasChanged(Bundle extras) {
        }

        public void onAudioInfoChanged(PlaybackInfo info) {
        }

        public void onCaptioningEnabledChanged(boolean enabled) {
        }

        public void onRepeatModeChanged(int repeatMode) {
        }

        public void onShuffleModeChanged(boolean enabled) {
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerExtraData */
    private static class MediaControllerExtraData extends ExtraData {
        private final MediaControllerCompat mMediaController;

        MediaControllerExtraData(MediaControllerCompat mediaController) {
            this.mMediaController = mediaController;
        }

        /* access modifiers changed from: 0000 */
        public MediaControllerCompat getMediaController() {
            return this.mMediaController;
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImpl */
    interface MediaControllerImpl {
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21 */
    static class MediaControllerImplApi21 implements MediaControllerImpl {
        private HashMap<Callback, ExtraCallback> mCallbackMap = new HashMap<>();
        protected final Object mControllerObj;
        /* access modifiers changed from: private */
        public IMediaSession mExtraBinder;
        private List<Callback> mPendingCallbacks = new ArrayList();

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver */
        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> mMediaControllerImpl;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImpl, Handler handler) {
                super(handler);
                this.mMediaControllerImpl = new WeakReference<>(mediaControllerImpl);
            }

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                MediaControllerImplApi21 mediaControllerImpl = (MediaControllerImplApi21) this.mMediaControllerImpl.get();
                if (mediaControllerImpl != null && resultData != null) {
                    mediaControllerImpl.mExtraBinder = IMediaSession.Stub.asInterface(BundleCompat.getBinder(resultData, "android.support.v4.media.session.EXTRA_BINDER"));
                    mediaControllerImpl.processPendingCallbacks();
                }
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraCallback */
        private class ExtraCallback extends Stub {
            /* access modifiers changed from: private */
            public Callback mCallback;

            ExtraCallback(Callback callback) {
                this.mCallback = callback;
            }

            public void onEvent(final String event, final Bundle extras) throws RemoteException {
                this.mCallback.mHandler.post(new Runnable() {
                    public void run() {
                        ExtraCallback.this.mCallback.onSessionEvent(event, extras);
                    }
                });
            }

            public void onSessionDestroyed() throws RemoteException {
                throw new AssertionError();
            }

            public void onPlaybackStateChanged(final PlaybackStateCompat state) throws RemoteException {
                this.mCallback.mHandler.post(new Runnable() {
                    public void run() {
                        ExtraCallback.this.mCallback.onPlaybackStateChanged(state);
                    }
                });
            }

            public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueChanged(List<QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                throw new AssertionError();
            }

            public void onCaptioningEnabledChanged(final boolean enabled) throws RemoteException {
                this.mCallback.mHandler.post(new Runnable() {
                    public void run() {
                        ExtraCallback.this.mCallback.onCaptioningEnabledChanged(enabled);
                    }
                });
            }

            public void onRepeatModeChanged(final int repeatMode) throws RemoteException {
                this.mCallback.mHandler.post(new Runnable() {
                    public void run() {
                        ExtraCallback.this.mCallback.onRepeatModeChanged(repeatMode);
                    }
                });
            }

            public void onShuffleModeChanged(final boolean enabled) throws RemoteException {
                this.mCallback.mHandler.post(new Runnable() {
                    public void run() {
                        ExtraCallback.this.mCallback.onShuffleModeChanged(enabled);
                    }
                });
            }

            public void onExtrasChanged(Bundle extras) throws RemoteException {
                throw new AssertionError();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, Token sessionToken) throws RemoteException {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, sessionToken.getToken());
            if (this.mControllerObj == null) {
                throw new RemoteException();
            }
            this.mExtraBinder = sessionToken.getExtraBinder();
            if (this.mExtraBinder == null) {
                requestExtraBinder();
            }
        }

        public void sendCommand(String command, Bundle params, ResultReceiver cb) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, command, params, cb);
        }

        private void requestExtraBinder() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this, new Handler()));
        }

        /* access modifiers changed from: private */
        public void processPendingCallbacks() {
            if (this.mExtraBinder != null) {
                synchronized (this.mPendingCallbacks) {
                    for (Callback callback : this.mPendingCallbacks) {
                        ExtraCallback extraCallback = new ExtraCallback(callback);
                        this.mCallbackMap.put(callback, extraCallback);
                        callback.mHasExtraCallback = true;
                        try {
                            this.mExtraBinder.registerCallbackListener(extraCallback);
                        } catch (RemoteException e) {
                            Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                        }
                    }
                    this.mPendingCallbacks.clear();
                }
            }
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi23 */
    static class MediaControllerImplApi23 extends MediaControllerImplApi21 {
        public MediaControllerImplApi23(Context context, Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi24 */
    static class MediaControllerImplApi24 extends MediaControllerImplApi23 {
        public MediaControllerImplApi24(Context context, Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplBase */
    static class MediaControllerImplBase implements MediaControllerImpl {
        private IMediaSession mBinder;

        public MediaControllerImplBase(Token token) {
            this.mBinder = IMediaSession.Stub.asInterface((IBinder) token.getToken());
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$PlaybackInfo */
    public static final class PlaybackInfo {
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int type, int stream, int control, int max, int current) {
            this.mPlaybackType = type;
            this.mAudioStream = stream;
            this.mVolumeControl = control;
            this.mMaxVolume = max;
            this.mCurrentVolume = current;
        }
    }

    public static void setMediaController(Activity activity, MediaControllerCompat mediaController) {
        if (activity instanceof SupportActivity) {
            ((SupportActivity) activity).putExtraData(new MediaControllerExtraData(mediaController));
        }
        if (VERSION.SDK_INT >= 21) {
            Object controllerObj = null;
            if (mediaController != null) {
                controllerObj = MediaControllerCompatApi21.fromToken(activity, mediaController.getSessionToken().getToken());
            }
            MediaControllerCompatApi21.setMediaController(activity, controllerObj);
        }
    }

    public static MediaControllerCompat getMediaController(Activity activity) {
        if (activity instanceof SupportActivity) {
            MediaControllerExtraData extraData = (MediaControllerExtraData) ((SupportActivity) activity).getExtraData(MediaControllerExtraData.class);
            if (extraData != null) {
                return extraData.getMediaController();
            }
            return null;
        } else if (VERSION.SDK_INT < 21) {
            return null;
        } else {
            Object controllerObj = MediaControllerCompatApi21.getMediaController(activity);
            if (controllerObj == null) {
                return null;
            }
            try {
                return new MediaControllerCompat(activity, Token.fromToken(MediaControllerCompatApi21.getSessionToken(controllerObj)));
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getMediaController.", e);
                return null;
            }
        }
    }

    public MediaControllerCompat(Context context, Token sessionToken) throws RemoteException {
        if (sessionToken == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.mToken = sessionToken;
        if (VERSION.SDK_INT >= 24) {
            this.mImpl = new MediaControllerImplApi24(context, sessionToken);
        } else if (VERSION.SDK_INT >= 23) {
            this.mImpl = new MediaControllerImplApi23(context, sessionToken);
        } else if (VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerImplApi21(context, sessionToken);
        } else {
            this.mImpl = new MediaControllerImplBase(this.mToken);
        }
    }

    public Token getSessionToken() {
        return this.mToken;
    }
}
