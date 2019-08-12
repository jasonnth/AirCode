package org.slf4j.event;

import com.airbnb.android.core.responses.OfficialIdStatusResponse;

public enum Level {
    ERROR(40, OfficialIdStatusResponse.ERROR),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");
    
    private int levelInt;
    private String levelStr;

    private Level(int i, String s) {
        this.levelInt = i;
        this.levelStr = s;
    }

    public String toString() {
        return this.levelStr;
    }
}
