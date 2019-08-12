package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoggingBehavior;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.appevents.internal.Constants;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

final class AppEventsLogger {
    private static final Object APP_EVENTS_LOGGER_LOCK = new Object();
    private static final String APP_EVENT_PREFERENCES = "com.facebook.accountkit.sdk.appEventPreferences";
    private static final int FLUSH_PERIOD_IN_SECONDS = 15;
    private static final int MAX_POOL_SIZE = 4;
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 30;
    /* access modifiers changed from: private */
    public static final String TAG = AppEventsLogger.class.getCanonicalName();
    private static String anonymousAppDeviceGUID;
    private static boolean requestInFlight;
    private static final Executor sAppEventExecutor = new ThreadPoolExecutor(1, 4, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256), new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "App Event Thread #" + this.mCount.getAndIncrement());
        }
    }, new DiscardPolicy() {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            super.rejectedExecution(r, e);
            Log.e(AppEventsLogger.TAG, "App Event Dropped");
        }
    });
    private static final Map<SessionEventsStateKey, SessionEventsState> stateMap = new ConcurrentHashMap();
    private final Context applicationContext;
    /* access modifiers changed from: private */
    public final SessionEventsStateKey stateKey;

    private static class AppEvent implements Serializable {
        private static final String IDENTIFIER_REGEX = "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$";
        private static final int MAX_IDENTIFIER_LENGTH = 40;
        private static final HashSet<String> VALIDATED_IDENTIFIERS = new HashSet<>();
        private static final long serialVersionUID = 1;
        final boolean isImplicit;
        final JSONObject jsonObject;

        private static class SerializationProxyV1 implements Serializable {
            private static final long serialVersionUID = -2488473066578201069L;
            private final boolean isImplicit;
            private final String jsonString;

            private SerializationProxyV1(String jsonString2, boolean isImplicit2) {
                this.jsonString = jsonString2;
                this.isImplicit = isImplicit2;
            }

            private Object readResolve() throws JSONException {
                return new AppEvent(this.jsonString, this.isImplicit);
            }
        }

        AppEvent(String eventName, Double valueToSum, Bundle parameters, boolean isImplicit2) {
            JSONObject jsonObject2;
            this.isImplicit = isImplicit2;
            try {
                validateIdentifier(eventName);
                jsonObject2 = new JSONObject();
                jsonObject2.put(Constants.EVENT_NAME_EVENT_KEY, eventName);
                jsonObject2.put(Constants.LOG_TIME_APP_EVENT_KEY, System.currentTimeMillis() / 1000);
                if (valueToSum != null) {
                    jsonObject2.put("_valueToSum", valueToSum.doubleValue());
                }
                if (isImplicit2) {
                    jsonObject2.put("_implicitlyLogged", "1");
                }
                if (parameters != null) {
                    for (String key : parameters.keySet()) {
                        validateIdentifier(key);
                        Object value = parameters.get(key);
                        if ((value instanceof String) || (value instanceof Number)) {
                            jsonObject2.put(key, value.toString());
                        } else {
                            throw new AccountKitException(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_PARAMETER_TYPE, value, key));
                        }
                    }
                }
                if (!isImplicit2) {
                    ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", jsonObject2.toString());
                }
            } catch (JSONException jsonException) {
                ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", jsonException.toString());
                jsonObject2 = null;
            } catch (AccountKitException e) {
                ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", e.toString());
                jsonObject2 = null;
            }
            this.jsonObject = jsonObject2;
        }

        private AppEvent(String jsonString, boolean isImplicit2) throws JSONException {
            this.jsonObject = new JSONObject(jsonString);
            this.isImplicit = isImplicit2;
        }

        private void validateIdentifier(String identifier) throws AccountKitException {
            boolean alreadyValidated;
            if (identifier == null || identifier.length() == 0 || identifier.length() > 40) {
                if (identifier == null) {
                    identifier = "<None Provided>";
                }
                throw new AccountKitException(Type.INTERNAL_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_PARAMETER_TYPE, identifier, Integer.valueOf(40)));
            }
            synchronized (VALIDATED_IDENTIFIERS) {
                alreadyValidated = VALIDATED_IDENTIFIERS.contains(identifier);
            }
            if (alreadyValidated) {
                return;
            }
            if (identifier.matches(IDENTIFIER_REGEX)) {
                synchronized (VALIDATED_IDENTIFIERS) {
                    VALIDATED_IDENTIFIERS.add(identifier);
                }
                return;
            }
            throw new AccountKitException(Type.INTERNAL_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_PARAMETER_TYPE, identifier));
        }

        private Object writeReplace() {
            return new SerializationProxyV1(this.jsonObject.toString(), this.isImplicit);
        }

        public String toString() {
            return String.format("\"%s\", implicit: %b, json: %s", new Object[]{this.jsonObject.optString(Constants.EVENT_NAME_EVENT_KEY), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()});
        }
    }

    private enum FlushReason {
        EXPLICIT,
        TIMER,
        SESSION_CHANGE,
        PERSISTED_EVENTS,
        EVENT_THRESHOLD,
        EAGER_FLUSHING_EVENT
    }

    private enum FlushResult {
        SUCCESS,
        SERVER_ERROR,
        NO_CONNECTIVITY,
        UNKNOWN_ERROR
    }

    private static class FlushStatistics {
        int numEvents;
        public FlushResult result;

        private FlushStatistics() {
            this.numEvents = 0;
            this.result = FlushResult.SUCCESS;
        }
    }

    private static class PersistedEvents {
        private static final String PERSISTED_EVENTS_FILENAME = "AccountKitAppEventsLogger.persistedevents";
        private static final Object PERSISTED_EVENTS_LOCK = new Object();
        private final Context context;
        private HashMap<SessionEventsStateKey, List<AppEvent>> persistedEvents = new HashMap<>();

        private PersistedEvents(Context context2) {
            this.context = context2;
        }

        static PersistedEvents readAndClearStore(Context context2) {
            PersistedEvents persistedEvents2;
            synchronized (PERSISTED_EVENTS_LOCK) {
                persistedEvents2 = new PersistedEvents(context2);
                persistedEvents2.readAndClearStore();
            }
            return persistedEvents2;
        }

        static void persistEvents(Context context2, SessionEventsStateKey stateKey, SessionEventsState state) {
            List<AppEvent> events = state.getEventsToPersist();
            if (events.size() != 0) {
                synchronized (PERSISTED_EVENTS_LOCK) {
                    PersistedEvents persistedEvents2 = readAndClearStore(context2);
                    persistedEvents2.addEvents(stateKey, events);
                    persistedEvents2.write();
                }
            }
        }

        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v1, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r2v2, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r2v3 */
        /* JADX WARNING: type inference failed for: r2v4, types: [java.io.OutputStream, java.io.FileOutputStream] */
        /* JADX WARNING: type inference failed for: r3v0, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
        /* JADX WARNING: type inference failed for: r2v5 */
        /* JADX WARNING: type inference failed for: r2v6 */
        /* JADX WARNING: type inference failed for: r1v0, types: [java.io.ObjectOutputStream] */
        /* JADX WARNING: type inference failed for: r2v7, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r2v8 */
        /* JADX WARNING: type inference failed for: r2v9 */
        /* JADX WARNING: type inference failed for: r2v10 */
        /* JADX WARNING: type inference failed for: r2v11 */
        /* JADX WARNING: type inference failed for: r2v12 */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v3
          assigns: []
          uses: []
          mth insns count: 35
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 8 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void write() {
            /*
                r7 = this;
                r2 = 0
                android.content.Context r4 = r7.context     // Catch:{ Exception -> 0x001f }
                java.lang.String r5 = "AccountKitAppEventsLogger.persistedevents"
                r6 = 0
                java.io.FileOutputStream r2 = r4.openFileOutput(r5, r6)     // Catch:{ Exception -> 0x001f }
                java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x001f }
                r3.<init>(r2)     // Catch:{ Exception -> 0x001f }
                java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x004b, all -> 0x0048 }
                r1.<init>(r3)     // Catch:{ Exception -> 0x004b, all -> 0x0048 }
                r2 = r1
                java.util.HashMap<com.facebook.accountkit.internal.AppEventsLogger$SessionEventsStateKey, java.util.List<com.facebook.accountkit.internal.AppEventsLogger$AppEvent>> r4 = r7.persistedEvents     // Catch:{ Exception -> 0x001f }
                r1.writeObject(r4)     // Catch:{ Exception -> 0x001f }
                com.facebook.accountkit.internal.Utility.closeQuietly(r2)
            L_0x001e:
                return
            L_0x001f:
                r0 = move-exception
            L_0x0020:
                java.lang.String r4 = com.facebook.accountkit.internal.AppEventsLogger.TAG     // Catch:{ all -> 0x0043 }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0043 }
                r5.<init>()     // Catch:{ all -> 0x0043 }
                java.lang.String r6 = "Got unexpected exception: "
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0043 }
                java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0043 }
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0043 }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0043 }
                android.util.Log.d(r4, r5)     // Catch:{ all -> 0x0043 }
                com.facebook.accountkit.internal.Utility.closeQuietly(r2)
                goto L_0x001e
            L_0x0043:
                r4 = move-exception
            L_0x0044:
                com.facebook.accountkit.internal.Utility.closeQuietly(r2)
                throw r4
            L_0x0048:
                r4 = move-exception
                r2 = r3
                goto L_0x0044
            L_0x004b:
                r0 = move-exception
                r2 = r3
                goto L_0x0020
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AppEventsLogger.PersistedEvents.write():void");
        }

        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r1v1, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r1v2, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r1v3 */
        /* JADX WARNING: type inference failed for: r1v4, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r1v5 */
        /* JADX WARNING: type inference failed for: r2v0, types: [java.io.BufferedInputStream, java.io.InputStream] */
        /* JADX WARNING: type inference failed for: r1v6 */
        /* JADX WARNING: type inference failed for: r1v7 */
        /* JADX WARNING: type inference failed for: r1v8 */
        /* JADX WARNING: type inference failed for: r4v0, types: [java.io.ObjectInputStream] */
        /* JADX WARNING: type inference failed for: r1v9, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r1v10 */
        /* JADX WARNING: type inference failed for: r1v11 */
        /* JADX WARNING: type inference failed for: r1v12 */
        /* JADX WARNING: type inference failed for: r1v13 */
        /* JADX WARNING: type inference failed for: r1v14 */
        /* JADX WARNING: type inference failed for: r1v15 */
        /* JADX WARNING: type inference failed for: r1v16 */
        /* JADX WARNING: type inference failed for: r1v17 */
        /* JADX WARNING: type inference failed for: r1v18 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v3
          assigns: []
          uses: []
          mth insns count: 52
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 9 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void readAndClearStore() {
            /*
                r8 = this;
                r1 = 0
                java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                android.content.Context r5 = r8.context     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                java.lang.String r6 = "AccountKitAppEventsLogger.persistedevents"
                java.io.FileInputStream r5 = r5.openFileInput(r6)     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ FileNotFoundException -> 0x006e, Exception -> 0x006b, all -> 0x0068 }
                r4.<init>(r2)     // Catch:{ FileNotFoundException -> 0x006e, Exception -> 0x006b, all -> 0x0068 }
                r1 = r4
                java.lang.Object r3 = r4.readObject()     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                java.util.HashMap r3 = (java.util.HashMap) r3     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                android.content.Context r5 = r8.context     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                java.lang.String r6 = "AccountKitAppEventsLogger.persistedevents"
                java.io.File r5 = r5.getFileStreamPath(r6)     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                boolean r5 = r5.delete()     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                if (r5 != 0) goto L_0x0034
                java.lang.String r5 = com.facebook.accountkit.internal.AppEventsLogger.TAG     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                java.lang.String r6 = "Error deleting file: AccountKitAppEventsLogger.persistedevents"
                android.util.Log.d(r5, r6)     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
            L_0x0034:
                r8.persistedEvents = r3     // Catch:{ FileNotFoundException -> 0x003a, Exception -> 0x003f }
                com.facebook.accountkit.internal.Utility.closeQuietly(r1)
            L_0x0039:
                return
            L_0x003a:
                r5 = move-exception
            L_0x003b:
                com.facebook.accountkit.internal.Utility.closeQuietly(r1)
                goto L_0x0039
            L_0x003f:
                r0 = move-exception
            L_0x0040:
                java.lang.String r5 = com.facebook.accountkit.internal.AppEventsLogger.TAG     // Catch:{ all -> 0x0063 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0063 }
                r6.<init>()     // Catch:{ all -> 0x0063 }
                java.lang.String r7 = "Got unexpected exception: "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0063 }
                java.lang.String r7 = r0.toString()     // Catch:{ all -> 0x0063 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0063 }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0063 }
                android.util.Log.d(r5, r6)     // Catch:{ all -> 0x0063 }
                com.facebook.accountkit.internal.Utility.closeQuietly(r1)
                goto L_0x0039
            L_0x0063:
                r5 = move-exception
            L_0x0064:
                com.facebook.accountkit.internal.Utility.closeQuietly(r1)
                throw r5
            L_0x0068:
                r5 = move-exception
                r1 = r2
                goto L_0x0064
            L_0x006b:
                r0 = move-exception
                r1 = r2
                goto L_0x0040
            L_0x006e:
                r5 = move-exception
                r1 = r2
                goto L_0x003b
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AppEventsLogger.PersistedEvents.readAndClearStore():void");
        }

        /* access modifiers changed from: 0000 */
        public void addEvents(SessionEventsStateKey stateKey, List<AppEvent> eventsToPersist) {
            if (!this.persistedEvents.containsKey(stateKey)) {
                this.persistedEvents.put(stateKey, new ArrayList());
            }
            ((List) this.persistedEvents.get(stateKey)).addAll(eventsToPersist);
        }
    }

    private static class SessionEventsState {
        private static final int MAX_ACCUMULATED_LOG_EVENTS = 1000;
        private List<AppEvent> accumulatedEvents = new ArrayList();
        private final String anonymousAppDeviceGUID;
        private final Context applicationContext;
        private final List<AppEvent> inFlightEvents = new ArrayList();
        private int numSkippedEventsDueToFullBuffer;

        SessionEventsState(Context applicationContext2, String anonymousGUID) {
            this.applicationContext = applicationContext2;
            this.anonymousAppDeviceGUID = anonymousGUID;
        }

        /* access modifiers changed from: 0000 */
        public synchronized void addEvent(AppEvent event) {
            if (this.accumulatedEvents.size() + this.inFlightEvents.size() >= 1000) {
                this.numSkippedEventsDueToFullBuffer++;
            } else {
                this.accumulatedEvents.add(event);
            }
        }

        /* access modifiers changed from: 0000 */
        public synchronized int getAccumulatedEventCount() {
            return this.accumulatedEvents.size();
        }

        /* access modifiers changed from: 0000 */
        public synchronized void clearInFlightAndStats(boolean moveToAccumulated) {
            if (moveToAccumulated) {
                this.accumulatedEvents.addAll(this.inFlightEvents);
            }
            this.inFlightEvents.clear();
            this.numSkippedEventsDueToFullBuffer = 0;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r5 = getJSONObject();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
            if (r9.numSkippedEventsDueToFullBuffer <= 0) goto L_0x0047;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
            r5.put("num_skipped_events", r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0071, code lost:
            r5 = new org.json.JSONObject();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int populateRequest(com.facebook.accountkit.internal.AccountKitGraphRequest r10) {
            /*
                r9 = this;
                monitor-enter(r9)
                int r4 = r9.numSkippedEventsDueToFullBuffer     // Catch:{ all -> 0x002c }
                java.util.List<com.facebook.accountkit.internal.AppEventsLogger$AppEvent> r7 = r9.inFlightEvents     // Catch:{ all -> 0x002c }
                java.util.List<com.facebook.accountkit.internal.AppEventsLogger$AppEvent> r8 = r9.accumulatedEvents     // Catch:{ all -> 0x002c }
                r7.addAll(r8)     // Catch:{ all -> 0x002c }
                java.util.List<com.facebook.accountkit.internal.AppEventsLogger$AppEvent> r7 = r9.accumulatedEvents     // Catch:{ all -> 0x002c }
                r7.clear()     // Catch:{ all -> 0x002c }
                org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ all -> 0x002c }
                r2.<init>()     // Catch:{ all -> 0x002c }
                java.util.List<com.facebook.accountkit.internal.AppEventsLogger$AppEvent> r7 = r9.inFlightEvents     // Catch:{ all -> 0x002c }
                java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x002c }
            L_0x001a:
                boolean r8 = r7.hasNext()     // Catch:{ all -> 0x002c }
                if (r8 == 0) goto L_0x002f
                java.lang.Object r1 = r7.next()     // Catch:{ all -> 0x002c }
                com.facebook.accountkit.internal.AppEventsLogger$AppEvent r1 = (com.facebook.accountkit.internal.AppEventsLogger.AppEvent) r1     // Catch:{ all -> 0x002c }
                org.json.JSONObject r8 = r1.jsonObject     // Catch:{ all -> 0x002c }
                r2.put(r8)     // Catch:{ all -> 0x002c }
                goto L_0x001a
            L_0x002c:
                r7 = move-exception
                monitor-exit(r9)     // Catch:{ all -> 0x002c }
                throw r7
            L_0x002f:
                int r7 = r2.length()     // Catch:{ all -> 0x002c }
                if (r7 != 0) goto L_0x0038
                r7 = 0
                monitor-exit(r9)     // Catch:{ all -> 0x002c }
            L_0x0037:
                return r7
            L_0x0038:
                monitor-exit(r9)     // Catch:{ all -> 0x002c }
                org.json.JSONObject r5 = r9.getJSONObject()     // Catch:{ JSONException -> 0x0070 }
                int r7 = r9.numSkippedEventsDueToFullBuffer     // Catch:{ JSONException -> 0x0070 }
                if (r7 <= 0) goto L_0x0047
                java.lang.String r7 = "num_skipped_events"
                r5.put(r7, r4)     // Catch:{ JSONException -> 0x0070 }
            L_0x0047:
                r10.setRequestObject(r5)
                android.os.Bundle r6 = r10.getParameters()
                if (r6 != 0) goto L_0x0055
                android.os.Bundle r6 = new android.os.Bundle
                r6.<init>()
            L_0x0055:
                java.lang.String r3 = r2.toString()
                if (r3 == 0) goto L_0x0068
                java.lang.String r7 = "events_file"
                byte[] r8 = r9.getStringAsByteArray(r3)
                r6.putByteArray(r7, r8)
                r10.setTag(r3)
            L_0x0068:
                r10.setParameters(r6)
                int r7 = r2.length()
                goto L_0x0037
            L_0x0070:
                r0 = move-exception
                org.json.JSONObject r5 = new org.json.JSONObject
                r5.<init>()
                goto L_0x0047
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AppEventsLogger.SessionEventsState.populateRequest(com.facebook.accountkit.internal.AccountKitGraphRequest):int");
        }

        /* access modifiers changed from: 0000 */
        public synchronized List<AppEvent> getEventsToPersist() {
            List<AppEvent> result;
            result = this.accumulatedEvents;
            this.accumulatedEvents = new ArrayList();
            return result;
        }

        public JSONObject getJSONObject() throws JSONException {
            JSONObject publishParams = new JSONObject();
            Utility.setAppEventAttributionParameters(publishParams, this.anonymousAppDeviceGUID);
            try {
                Utility.setAppEventExtendedDeviceInfoParameters(publishParams, this.applicationContext);
            } catch (Exception e) {
                ConsoleLogger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Fetching extended device info parameters failed: '%s'", e.toString());
            }
            return publishParams;
        }

        private byte[] getStringAsByteArray(String jsonString) {
            byte[] jsonUtf8 = null;
            try {
                return jsonString.getBytes(JPushConstants.ENCODING_UTF_8);
            } catch (UnsupportedEncodingException e) {
                Utility.logd("Encoding exception: ", e);
                return jsonUtf8;
            }
        }
    }

    private static class SessionEventsStateKey implements Serializable {
        private static final long serialVersionUID = 1;
        private final String accessTokenString;
        public final String applicationId;

        private static class SerializationProxyV1 implements Serializable {
            private static final long serialVersionUID = -1;
            private final String accessTokenString;
            private final String appId;

            private SerializationProxyV1(String accessTokenString2, String appId2) {
                this.accessTokenString = accessTokenString2;
                this.appId = appId2;
            }

            private Object readResolve() {
                return new SessionEventsStateKey(this.accessTokenString, this.appId);
            }
        }

        SessionEventsStateKey(AccessToken accessToken) {
            this(accessToken.getToken(), AccountKit.getApplicationId());
        }

        SessionEventsStateKey(String accessTokenString2, String applicationId2) {
            if (Utility.isNullOrEmpty(accessTokenString2)) {
                accessTokenString2 = null;
            }
            this.accessTokenString = accessTokenString2;
            this.applicationId = applicationId2;
        }

        public int hashCode() {
            return Utility.getHashCode(this.accessTokenString) ^ Utility.getHashCode(this.applicationId);
        }

        public boolean equals(Object o) {
            if (!(o instanceof SessionEventsStateKey)) {
                return false;
            }
            SessionEventsStateKey p = (SessionEventsStateKey) o;
            if (!Utility.areObjectsEqual(p.accessTokenString, this.accessTokenString) || !Utility.areObjectsEqual(p.applicationId, this.applicationId)) {
                return false;
            }
            return true;
        }

        private Object writeReplace() {
            return new SerializationProxyV1(this.accessTokenString, this.applicationId);
        }
    }

    AppEventsLogger(Context applicationContext2, String applicationId) {
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken == null || (applicationId != null && !applicationId.equals(accessToken.getApplicationId()))) {
            if (applicationId == null) {
                applicationId = Utility.getMetadataApplicationId();
            }
            this.stateKey = new SessionEventsStateKey(null, applicationId);
        } else {
            this.stateKey = new SessionEventsStateKey(accessToken);
        }
        this.applicationContext = applicationContext2;
        initializeTimersIfNeeded();
    }

    /* access modifiers changed from: 0000 */
    public void logSdkEvent(String eventName, Double valueToSum, Bundle parameters) {
        final AppEvent event = new AppEvent(eventName, valueToSum, parameters, true);
        sAppEventExecutor.execute(new Runnable() {
            public void run() {
                AppEventsLogger.this.getSessionEventsState(AppEventsLogger.this.stateKey).addEvent(event);
                AppEventsLogger.this.flushIfNecessary();
            }
        });
    }

    public String getApplicationId() {
        return this.stateKey.applicationId;
    }

    private void initializeTimersIfNeeded() {
        Utility.getBackgroundExecutor().scheduleAtFixedRate(new Runnable() {
            public void run() {
                AppEventsLogger.this.flushAndWait(FlushReason.TIMER);
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    private static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (APP_EVENTS_LOGGER_LOCK) {
                if (anonymousAppDeviceGUID == null) {
                    SharedPreferences preferences = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0);
                    anonymousAppDeviceGUID = preferences.getString("anonymousAppDeviceGUID", null);
                    if (anonymousAppDeviceGUID == null) {
                        anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        preferences.edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }

    /* access modifiers changed from: private */
    public void flushIfNecessary() {
        synchronized (APP_EVENTS_LOGGER_LOCK) {
            if (getAccumulatedEventCount() > 30) {
                flush(FlushReason.EVENT_THRESHOLD);
            }
        }
    }

    private static int getAccumulatedEventCount() {
        int result;
        synchronized (APP_EVENTS_LOGGER_LOCK) {
            result = 0;
            for (SessionEventsState state : stateMap.values()) {
                result += state.getAccumulatedEventCount();
            }
        }
        return result;
    }

    /* access modifiers changed from: private */
    public SessionEventsState getSessionEventsState(SessionEventsStateKey stateKey2) {
        SessionEventsState state = (SessionEventsState) stateMap.get(stateKey2);
        if (state == null) {
            synchronized (APP_EVENTS_LOGGER_LOCK) {
                try {
                    state = (SessionEventsState) stateMap.get(stateKey2);
                    if (state == null) {
                        SessionEventsState state2 = new SessionEventsState(this.applicationContext, getAnonymousAppDeviceGUID(this.applicationContext));
                        try {
                            stateMap.put(stateKey2, state2);
                            state = state2;
                        } catch (Throwable th) {
                            th = th;
                            SessionEventsState sessionEventsState = state2;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        }
        return state;
    }

    private void flush(final FlushReason reason) {
        sAppEventExecutor.execute(new Runnable() {
            public void run() {
                AppEventsLogger.this.flushAndWait(reason);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0029, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002a, code lost:
        com.facebook.accountkit.internal.Utility.logd(TAG, "Caught unexpected exception while flushing: ", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        buildAndExecuteRequests(r5, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void flushAndWait(com.facebook.accountkit.internal.AppEventsLogger.FlushReason r5) {
        /*
            r4 = this;
            java.lang.Object r3 = APP_EVENTS_LOGGER_LOCK
            monitor-enter(r3)
            boolean r2 = requestInFlight     // Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x0009
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
        L_0x0008:
            return
        L_0x0009:
            r2 = 1
            requestInFlight = r2     // Catch:{ all -> 0x0026 }
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x0026 }
            java.util.Map<com.facebook.accountkit.internal.AppEventsLogger$SessionEventsStateKey, com.facebook.accountkit.internal.AppEventsLogger$SessionEventsState> r2 = stateMap     // Catch:{ all -> 0x0026 }
            java.util.Set r2 = r2.keySet()     // Catch:{ all -> 0x0026 }
            r1.<init>(r2)     // Catch:{ all -> 0x0026 }
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
            r4.buildAndExecuteRequests(r5, r1)     // Catch:{ Exception -> 0x0029 }
        L_0x001b:
            java.lang.Object r3 = APP_EVENTS_LOGGER_LOCK
            monitor-enter(r3)
            r2 = 0
            requestInFlight = r2     // Catch:{ all -> 0x0023 }
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            goto L_0x0008
        L_0x0023:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            throw r2
        L_0x0026:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
            throw r2
        L_0x0029:
            r0 = move-exception
            java.lang.String r2 = TAG
            java.lang.String r3 = "Caught unexpected exception while flushing: "
            com.facebook.accountkit.internal.Utility.logd(r2, r3, r0)
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AppEventsLogger.flushAndWait(com.facebook.accountkit.internal.AppEventsLogger$FlushReason):void");
    }

    private void buildAndExecuteRequests(FlushReason reason, Set<SessionEventsStateKey> keysToFlush) {
        FlushStatistics flushResults = new FlushStatistics();
        List<AccountKitGraphRequestAsyncTask> tasksToExecute = new ArrayList<>();
        for (SessionEventsStateKey key : keysToFlush) {
            SessionEventsState sessionEventsState = getSessionEventsState(key);
            if (sessionEventsState != null) {
                AccountKitGraphRequestAsyncTask task = buildRequestForSession(key, sessionEventsState, flushResults);
                if (task != null) {
                    tasksToExecute.add(task);
                }
            }
        }
        if (tasksToExecute.size() > 0) {
            ConsoleLogger.log(LoggingBehavior.APP_EVENTS, TAG, "Flushing %d events due to %s.", Integer.valueOf(flushResults.numEvents), reason.toString());
            for (AccountKitGraphRequestAsyncTask task2 : tasksToExecute) {
                task2.executeOnExecutor(sAppEventExecutor, new Void[0]);
            }
        }
    }

    private AccountKitGraphRequestAsyncTask buildRequestForSession(SessionEventsStateKey stateKey2, SessionEventsState sessionEventsState, FlushStatistics flushState) {
        AccountKitGraphRequest postRequest = new AccountKitGraphRequest(null, String.format("%s/events", new Object[]{stateKey2.applicationId}), null, false, HttpMethod.POST);
        int numEvents = sessionEventsState.populateRequest(postRequest);
        if (numEvents == 0) {
            return null;
        }
        flushState.numEvents += numEvents;
        final SessionEventsStateKey sessionEventsStateKey = stateKey2;
        final AccountKitGraphRequest accountKitGraphRequest = postRequest;
        final SessionEventsState sessionEventsState2 = sessionEventsState;
        final FlushStatistics flushStatistics = flushState;
        return new AccountKitGraphRequestAsyncTask(postRequest, new Callback() {
            public void onCompleted(AccountKitGraphResponse response) {
                AppEventsLogger.this.handleResponse(sessionEventsStateKey, accountKitGraphRequest, response, sessionEventsState2, flushStatistics);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleResponse(SessionEventsStateKey stateKey2, AccountKitGraphRequest request, AccountKitGraphResponse response, SessionEventsState sessionEventsState, FlushStatistics flushState) {
        String prettyPrintedEvents;
        AccountKitRequestError error = response == null ? null : response.getError();
        String resultDescription = "Success";
        FlushResult flushResult = FlushResult.SUCCESS;
        if (error != null) {
            if (error.getErrorCode() == -1) {
                resultDescription = "Failed: No Connectivity";
                flushResult = FlushResult.NO_CONNECTIVITY;
            } else {
                resultDescription = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{response.toString(), error.toString()});
                flushResult = FlushResult.SERVER_ERROR;
            }
        }
        if (AccountKit.getLoggingBehaviors().isEnabled(LoggingBehavior.APP_EVENTS)) {
            try {
                prettyPrintedEvents = new JSONArray((String) request.getTag()).toString(2);
            } catch (JSONException e) {
                prettyPrintedEvents = "<Can't encode events for debug logging>";
            }
            ConsoleLogger.log(LoggingBehavior.APP_EVENTS, TAG, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", request.getRequestObject().toString(), resultDescription, prettyPrintedEvents);
        }
        sessionEventsState.clearInFlightAndStats(error != null);
        if (flushResult == FlushResult.NO_CONNECTIVITY) {
            PersistedEvents.persistEvents(this.applicationContext, stateKey2, sessionEventsState);
        }
        if (flushResult != FlushResult.SUCCESS && flushState.result != FlushResult.NO_CONNECTIVITY) {
            flushState.result = flushResult;
        }
    }
}
