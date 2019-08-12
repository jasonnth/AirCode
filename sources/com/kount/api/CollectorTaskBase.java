package com.kount.api;

import android.util.Log;
import com.kount.api.DataCollector.Error;
import java.lang.reflect.Method;
import java.util.Hashtable;

abstract class CollectorTaskBase {
    private RequestHandler completionHandler = null;
    private final Hashtable<String, String> data = new Hashtable<>();
    private final Object debugHandler;
    protected boolean done = false;
    String sessionID = null;
    private final Hashtable<String, String> softErrors = new Hashtable<>();

    interface RequestHandler {
        void completed(Boolean bool, Error error, Hashtable<String, String> hashtable, Hashtable<String, String> hashtable2);
    }

    /* access modifiers changed from: 0000 */
    public abstract void collect();

    /* access modifiers changed from: 0000 */
    public abstract String getInternalName();

    /* access modifiers changed from: 0000 */
    public abstract String getName();

    CollectorTaskBase(Object debugHandler2) {
        this.debugHandler = debugHandler2;
    }

    /* access modifiers changed from: 0000 */
    public void collectForSession(String sessionID2, RequestHandler handler) {
        this.sessionID = sessionID2;
        this.completionHandler = handler;
        debugMessage("Starting");
        if (this.debugHandler != null) {
            try {
                Method method = this.debugHandler.getClass().getDeclaredMethod("collectorStarted", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(this.debugHandler, new Object[]{getName()});
            } catch (Exception e) {
                debugMessage(String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
        collect();
    }

    /* access modifiers changed from: 0000 */
    public void callCompletionHandler(Boolean success, Error error) {
        String str = "Completed with %s";
        Object[] objArr = new Object[1];
        objArr[0] = success.booleanValue() ? "Success" : "Failure";
        debugMessage(String.format(str, objArr));
        if (this.debugHandler != null) {
            try {
                Method method = this.debugHandler.getClass().getDeclaredMethod("collectorDone", new Class[]{String.class, Boolean.class, Error.class});
                method.setAccessible(true);
                method.invoke(this.debugHandler, new Object[]{getName(), success, error});
            } catch (Exception e) {
                debugMessage(String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
        this.done = true;
        this.completionHandler.completed(success, error, this.data, this.softErrors);
    }

    /* access modifiers changed from: 0000 */
    public void addDataPoint(String key, String value) {
        if (value != null) {
            this.data.put(key, value);
        }
    }

    /* access modifiers changed from: 0000 */
    public void addSoftError(String error) {
        this.softErrors.put(getInternalName(), error);
    }

    /* access modifiers changed from: 0000 */
    public void debugMessage(String message) {
        if (this.debugHandler != null) {
            String formattedMessage = String.format("(%s) <%s> %s", new Object[]{this.sessionID, getName(), message});
            Log.d("DataCollector", formattedMessage);
            try {
                Method method = this.debugHandler.getClass().getDeclaredMethod("collectorDebugMessage", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(this.debugHandler, new Object[]{formattedMessage});
            } catch (Exception e) {
                debugMessage(String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
    }
}
