package p032rx.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.TimeUnit;
import p032rx.Scheduler;
import p032rx.Scheduler.Worker;
import p032rx.Subscription;
import p032rx.android.plugins.RxAndroidPlugins;
import p032rx.android.plugins.RxAndroidSchedulersHook;
import p032rx.exceptions.OnErrorNotImplementedException;
import p032rx.functions.Action0;
import p032rx.plugins.RxJavaPlugins;
import p032rx.subscriptions.Subscriptions;

/* renamed from: rx.android.schedulers.LooperScheduler */
class LooperScheduler extends Scheduler {
    private final Handler handler;

    /* renamed from: rx.android.schedulers.LooperScheduler$HandlerWorker */
    static class HandlerWorker extends Worker {
        private final Handler handler;
        private final RxAndroidSchedulersHook hook = RxAndroidPlugins.getInstance().getSchedulersHook();
        private volatile boolean unsubscribed;

        HandlerWorker(Handler handler2) {
            this.handler = handler2;
        }

        public void unsubscribe() {
            this.unsubscribed = true;
            this.handler.removeCallbacksAndMessages(this);
        }

        public boolean isUnsubscribed() {
            return this.unsubscribed;
        }

        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            if (this.unsubscribed) {
                return Subscriptions.unsubscribed();
            }
            ScheduledAction scheduledAction = new ScheduledAction(this.hook.onSchedule(action), this.handler);
            Message message = Message.obtain(this.handler, scheduledAction);
            message.obj = this;
            this.handler.sendMessageDelayed(message, unit.toMillis(delayTime));
            if (!this.unsubscribed) {
                return scheduledAction;
            }
            this.handler.removeCallbacks(scheduledAction);
            return Subscriptions.unsubscribed();
        }

        public Subscription schedule(Action0 action) {
            return schedule(action, 0, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: rx.android.schedulers.LooperScheduler$ScheduledAction */
    static final class ScheduledAction implements Runnable, Subscription {
        private final Action0 action;
        private final Handler handler;
        private volatile boolean unsubscribed;

        ScheduledAction(Action0 action2, Handler handler2) {
            this.action = action2;
            this.handler = handler2;
        }

        public void run() {
            IllegalStateException ie;
            try {
                this.action.call();
            } catch (Throwable e) {
                if (e instanceof OnErrorNotImplementedException) {
                    ie = new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e);
                } else {
                    ie = new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", e);
                }
                RxJavaPlugins.getInstance().getErrorHandler().handleError(ie);
                Thread thread = Thread.currentThread();
                thread.getUncaughtExceptionHandler().uncaughtException(thread, ie);
            }
        }

        public void unsubscribe() {
            this.unsubscribed = true;
            this.handler.removeCallbacks(this);
        }

        public boolean isUnsubscribed() {
            return this.unsubscribed;
        }
    }

    LooperScheduler(Looper looper) {
        this.handler = new Handler(looper);
    }

    public Worker createWorker() {
        return new HandlerWorker(this.handler);
    }
}
