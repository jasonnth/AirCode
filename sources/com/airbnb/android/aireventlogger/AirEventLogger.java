package com.airbnb.android.aireventlogger;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.Executor;

class AirEventLogger {
    private static final String TAG = AirEventLogger.class.getSimpleName();
    private final AirEventUpload airEventUpload;
    private final long alarmTimeStartMills;
    private final long alarmWindowMillis;
    /* access modifiers changed from: private */
    public final Context context;
    private final EventHandlerRegistry eventHandlerRegistry;
    private final int eventsToFetch;
    private final Executor executor;
    private boolean isScheduled = false;

    AirEventLogger(Context context2, long alarmTimeStartMills2, long alarmWindowMillis2, int eventsToFetch2, Executor executor2, AirEventUpload airEventUpload2, EventHandlerRegistry eventHandlerRegistry2) {
        this.context = context2;
        this.alarmTimeStartMills = alarmTimeStartMills2;
        this.alarmWindowMillis = alarmWindowMillis2;
        this.eventsToFetch = eventsToFetch2;
        this.executor = executor2;
        this.airEventUpload = airEventUpload2;
        this.eventHandlerRegistry = eventHandlerRegistry2;
    }

    /* access modifiers changed from: 0000 */
    public <T> void track(AirEvent<T> event) {
        track(event, false);
    }

    /* access modifiers changed from: 0000 */
    public <T> void track(final AirEvent<T> event, final boolean immediatelyFlush) {
        Utils.throwIfNull(event, "event == null");
        final EventHandler handler = this.eventHandlerRegistry.get(event);
        this.executor.execute(new Runnable() {
            public void run() {
                handler.saveEvent(event);
                if (immediatelyFlush) {
                    AirEventLogger.this.uploadEvents();
                }
            }
        });
        if (!this.isScheduled) {
            AlarmManagerUtils.scheduleUploadJob(this.context, this.alarmTimeStartMills, this.alarmWindowMillis);
            this.isScheduled = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void stop() {
        AlarmManagerUtils.cancelNextUploadJob(this.context);
    }

    /* access modifiers changed from: 0000 */
    public void uploadEventsAsync() {
        AlarmManagerUtils.scheduleUploadJob(this.context, this.alarmTimeStartMills, this.alarmWindowMillis);
        this.executor.execute(new Runnable() {
            public void run() {
                AirEventLogger.this.uploadEvents();
                AirEventLogger.this.context.stopService(AirEventUploadService.createIntent(AirEventLogger.this.context));
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadEvents() {
        boolean attemptedUpload = false;
        for (EventHandler handler : this.eventHandlerRegistry.getAllHandlers()) {
            if (uploadEventsForHandler(handler)) {
                attemptedUpload = true;
            }
        }
        if (!attemptedUpload) {
            AlarmManagerUtils.cancelNextUploadJob(this.context);
            this.isScheduled = false;
        }
    }

    private boolean uploadEventsForHandler(EventHandler handler) {
        PendingEvents pendingEvents = handler.getPendingEvents(this.eventsToFetch);
        if (pendingEvents == null || pendingEvents.length() <= 0) {
            return false;
        }
        try {
            Log.d(TAG, "Uploading events from " + handler.getClass().getSimpleName() + "...");
            this.airEventUpload.uploadData(pendingEvents);
            handler.deleteEvents(pendingEvents.firstId(), pendingEvents.lastId());
            return true;
        } catch (AirEventUploadException e) {
            Log.w(TAG, "Failed to upload events.", e);
            if (e.status < 0) {
                return false;
            }
            return true;
        }
    }
}
