package com.crashlytics.android.answers.shim;

import android.util.Log;
import com.crashlytics.android.answers.Answers;

class AnswersKitEventLogger implements KitEventLogger {
    private final Answers answers;

    private AnswersKitEventLogger(Answers answers2) {
        this.answers = answers2;
    }

    public static AnswersKitEventLogger create() throws NoClassDefFoundError, IllegalStateException {
        return create(Answers.getInstance());
    }

    static AnswersKitEventLogger create(Answers answers2) throws IllegalStateException {
        if (answers2 != null) {
            return new AnswersKitEventLogger(answers2);
        }
        throw new IllegalStateException("Answers must be initialized before logging kit events");
    }

    public void logKitEvent(KitEvent kitEvent) {
        try {
            this.answers.logCustom(kitEvent.toCustomEvent());
        } catch (Throwable t) {
            Log.w("AnswersKitEventLogger", "Unexpected error sending Answers event", t);
        }
    }
}
