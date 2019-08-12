package com.airbnb.android.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatcherUtils {

    public interface IsEmptyTextWatcher {
        void textUpdated(boolean z);
    }

    public interface StringTextWatcher {
        void textUpdated(String str);
    }

    public static TextWatcher create(final StringTextWatcher watcher) {
        return new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                watcher.textUpdated(s.toString());
            }
        };
    }

    public static TextWatcher createEmptyWatcher(final IsEmptyTextWatcher watcher) {
        return new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                watcher.textUpdated(s.length() == 0);
            }
        };
    }
}
