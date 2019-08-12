package com.bugsnag.android;

import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

class ThreadState implements Streamable {
    final Configuration config;

    ThreadState(Configuration config2) {
        this.config = config2;
    }

    public void toStream(JsonStream writer) throws IOException {
        long currentId = Thread.currentThread().getId();
        Map<Thread, StackTraceElement[]> liveThreads = Thread.getAllStackTraces();
        Object[] keys = liveThreads.keySet().toArray();
        Arrays.sort(keys, new Comparator<Object>() {
            public int compare(Object a, Object b) {
                return Long.valueOf(((Thread) a).getId()).compareTo(Long.valueOf(((Thread) b).getId()));
            }
        });
        writer.beginArray();
        for (Object obj : keys) {
            Thread thread = (Thread) obj;
            if (thread.getId() != currentId) {
                StackTraceElement[] stacktrace = (StackTraceElement[]) liveThreads.get(thread);
                writer.beginObject();
                writer.name("id").value(thread.getId());
                writer.name("name").value(thread.getName());
                writer.name("type").value("android");
                writer.name("stacktrace").value((Streamable) new Stacktrace(this.config, stacktrace));
                writer.endObject();
            }
        }
        writer.endArray();
    }
}
