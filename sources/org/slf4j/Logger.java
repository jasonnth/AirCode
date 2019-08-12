package org.slf4j;

public interface Logger {
    void debug(String str);

    void error(String str);

    void error(String str, Throwable th);

    void info(String str);

    void warn(String str);

    void warn(String str, Object obj);

    void warn(String str, Object obj, Object obj2);
}
