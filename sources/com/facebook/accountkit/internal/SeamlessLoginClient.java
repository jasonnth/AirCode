package com.facebook.accountkit.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.internal.NativeProtocol;

final class SeamlessLoginClient implements ServiceConnection {
    private static final int MIN_PROTOCOL_VERSION = 20161017;
    private static final int REPLY_MESSAGE = 65545;
    private static final int REQUEST_MESSAGE = 65544;
    private final String applicationId;
    private final Context context;
    private final Handler handler = new Handler() {
        public void handleMessage(Message message) {
            SeamlessLoginClient.this.handleMessage(message);
        }
    };
    private CompletedListener listener;
    private final InternalLogger logger;
    private boolean running;
    private Messenger sender;

    public interface CompletedListener {
        void completed(Bundle bundle);
    }

    public SeamlessLoginClient(Context context2, String applicationId2, InternalLogger internalLogger) {
        this.context = context2;
        this.applicationId = applicationId2;
        this.logger = internalLogger;
    }

    public void setCompletedListener(CompletedListener listener2) {
        this.listener = listener2;
    }

    public boolean start() {
        if (this.running) {
            return false;
        }
        if (!NativeProtocol.validateApplicationForService()) {
            this.logger.logFetchEventError(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalAccountKitError.NO_NATIVE_APP_INSTALLED);
            return false;
        } else if (!NativeProtocol.validateProtocolVersionForService(MIN_PROTOCOL_VERSION)) {
            this.logger.logFetchEventError(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalAccountKitError.UNSUPPORTED_NATIVE_APP_VERSION);
            return false;
        } else {
            Intent intent = NativeProtocol.createPlatformServiceIntent(this.context);
            if (intent == null) {
                return false;
            }
            this.running = true;
            this.context.bindService(intent, this, 1);
            return true;
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        this.sender = new Messenger(service);
        sendMessage();
    }

    public void onServiceDisconnected(ComponentName name) {
        this.sender = null;
        try {
            this.context.unbindService(this);
        } catch (IllegalArgumentException e) {
        }
        callback(null);
    }

    private void sendMessage() {
        Bundle data = new Bundle();
        data.putString(NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
        Message request = Message.obtain(null, 65544);
        request.arg1 = MIN_PROTOCOL_VERSION;
        request.setData(data);
        request.replyTo = new Messenger(this.handler);
        try {
            this.sender.send(request);
        } catch (RemoteException e) {
            callback(null);
        }
    }

    /* access modifiers changed from: private */
    public void handleMessage(Message message) {
        if (message.what == 65545) {
            Bundle extras = message.getData();
            if (extras.getString(NativeProtocol.STATUS_ERROR_TYPE) != null) {
                callback(null);
            } else {
                callback(extras);
            }
            try {
                this.context.unbindService(this);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    private void callback(Bundle result) {
        if (this.running) {
            this.running = false;
            if (this.listener != null) {
                this.listener.completed(result);
            }
        }
    }
}
