package com.facebook.accountkit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class LoggingBehaviorCollection {
    private final HashSet<LoggingBehavior> loggingBehaviors = new HashSet<>(Collections.singleton(LoggingBehavior.DEVELOPER_ERRORS));

    public void add(LoggingBehavior behavior) {
        synchronized (this.loggingBehaviors) {
            this.loggingBehaviors.add(behavior);
        }
    }

    public void clear() {
        synchronized (this.loggingBehaviors) {
            this.loggingBehaviors.clear();
        }
    }

    public Set<LoggingBehavior> get() {
        Set<LoggingBehavior> unmodifiableSet;
        synchronized (this.loggingBehaviors) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(this.loggingBehaviors));
        }
        return unmodifiableSet;
    }

    public boolean isEnabled(LoggingBehavior behavior) {
        boolean contains;
        synchronized (this.loggingBehaviors) {
            contains = this.loggingBehaviors.contains(behavior);
        }
        return contains;
    }

    public void remove(LoggingBehavior behavior) {
        synchronized (this.loggingBehaviors) {
            this.loggingBehaviors.remove(behavior);
        }
    }
}
