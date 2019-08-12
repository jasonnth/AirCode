package com.facebook.react.common;

import com.facebook.infer.annotation.Assertions;

public class SingleThreadAsserter {
    private Thread mThread = null;

    public void assertNow() {
        Thread current = Thread.currentThread();
        if (this.mThread == null) {
            this.mThread = current;
        }
        Assertions.assertCondition(this.mThread == current);
    }
}
