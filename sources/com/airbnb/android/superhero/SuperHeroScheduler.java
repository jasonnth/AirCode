package com.airbnb.android.superhero;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.SystemClock;
import android.support.p000v4.app.NotificationCompat;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.superhero.SuperHeroMessage.Status;
import p032rx.Completable;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.functions.Action0;
import p032rx.schedulers.Schedulers;

class SuperHeroScheduler {
    private final AlarmManager alarmManager;
    private final Context context;
    private final SuperHeroJitneyLogger jitneyLogger;
    private final MessageDiff messageDiff;
    private final SuperHeroTableOpenHelper tableOpenHelper;

    SuperHeroScheduler(Context context2, MessageDiff messageDiff2, SuperHeroTableOpenHelper tableOpenHelper2, SuperHeroJitneyLogger jitneyLogger2) {
        this.context = context2;
        this.messageDiff = messageDiff2;
        this.tableOpenHelper = tableOpenHelper2;
        this.alarmManager = (AlarmManager) context2.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.jitneyLogger = jitneyLogger2;
    }

    static /* synthetic */ void lambda$persistAndScheduleMessages$0() {
    }

    /* access modifiers changed from: 0000 */
    public void persistAndScheduleMessages(SuperHeroMessagesResponse response) {
        persistAndScheduleMessages(response, SuperHeroScheduler$$Lambda$1.lambdaFactory$());
    }

    /* access modifiers changed from: 0000 */
    public void persistAndScheduleMessages(SuperHeroMessagesResponse response, Action0 onCompleted) {
        Completable.fromAction(SuperHeroScheduler$$Lambda$2.lambdaFactory$(this, response)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).doOnCompleted(onCompleted).subscribe();
    }

    /* access modifiers changed from: private */
    public synchronized void _persistAndScheduleMessages(SuperHeroMessagesResponse response) {
        for (MessageDiffResult diffResult : this.messageDiff.diffResults(response.heroMessages())) {
            processDiffResult(diffResult);
        }
    }

    private void processDiffResult(MessageDiffResult diffResult) {
        switch (diffResult.diffResult) {
            case ADD:
                processNewMessage(diffResult.serverMessage);
                return;
            case REMOVE:
                processMessageRemoval(diffResult.localMessage);
                return;
            case CONFLICT:
                processMessageConflict(diffResult.localMessage, diffResult.serverMessage);
                return;
            default:
                throw new IllegalStateException("Invalid diff result status");
        }
    }

    private void processMessageConflict(SuperHeroMessage localMessage, SuperHeroMessage serverMessage) {
        Status finalStatus;
        Status localStatus = localMessage.statusEnum();
        Status serverStatus = serverMessage.statusEnum();
        if (localStatus == Status.PRESENTED || serverStatus == Status.PRESENTED) {
            finalStatus = Status.PRESENTED;
        } else if (localStatus == Status.TRIGGERED || serverStatus == Status.TRIGGERED) {
            finalStatus = Status.TRIGGERED;
        } else {
            finalStatus = Status.SCHEDULED;
        }
        SuperHeroMessage serverMessageWithUpdatedStatus = serverMessage.toBuilder().status((long) finalStatus.value).build();
        if (finalStatus != serverStatus) {
            SuperHeroMessageRequest.forStatusUpdate(serverMessage.mo11531id(), finalStatus).execute(NetworkUtil.singleFireExecutor());
        }
        if (localMessage.shouldBeScheduled()) {
            scheduleNotification(serverMessageWithUpdatedStatus);
        }
        this.tableOpenHelper.update(serverMessageWithUpdatedStatus);
        this.jitneyLogger.cacheHero(serverMessageWithUpdatedStatus);
    }

    private void processMessageRemoval(SuperHeroMessage messageToRemove) {
        this.tableOpenHelper.delete(messageToRemove);
        this.alarmManager.cancel(messageToRemove.buildPendingIntent(this.context));
    }

    private void processNewMessage(SuperHeroMessage messageToAdd) {
        if (messageToAdd.shouldBeScheduled()) {
            scheduleNotification(messageToAdd);
            if (messageToAdd.statusEnum() == Status.ACTIVE) {
                messageToAdd = messageToAdd.toBuilder().status((long) Status.SCHEDULED.value).build();
            }
            SuperHeroMessageRequest.forStatusUpdate(messageToAdd.mo11531id(), Status.SCHEDULED).execute(NetworkUtil.singleFireExecutor());
        }
        this.tableOpenHelper.insert(messageToAdd);
        this.jitneyLogger.cacheHero(messageToAdd);
    }

    private void scheduleNotification(SuperHeroMessage message) {
        PendingIntent pendingIntent = message.buildPendingIntent(this.context);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        AirDateTime startsAt = message.starts_at();
        if (startsAt == null) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Tried to schedule SuperHero but startsAt was null"));
            return;
        }
        this.alarmManager.set(2, (startsAt.getMillis() - AirDateTime.now().getMillis()) + elapsedRealtime, pendingIntent);
        this.jitneyLogger.scheduleHero(message);
    }
}
