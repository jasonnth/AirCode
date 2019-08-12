package p005cn.jpush.android.api;

import android.app.Activity;
import android.app.Application;
import android.app.TabActivity;
import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.DateUtils;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.ReportUtils;
import p005cn.jpush.android.util.SpfHelper;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.api.JPushSA */
public class JPushSA {
    public static final String CACHE_PATH = "jpush_stat_cache.json";
    private static final String KEY_ACTIVITIES = "activities";
    private static final String KEY_SESSION_ID = "session_id";
    private static final String SESSION_END_MILLIS = "cur_seesion_end";
    private static final String SESSION_START_MILLIS = "cur_session_start";
    private static final String TAG = "JPushSA";
    private static JPushSA instance = null;
    public static boolean isOnPauseInvoke = false;
    public static boolean isOnResumeInvoke = false;
    private boolean activitySwitch = false;
    private String cur_activity = null;
    private ArrayList<ActivityFlowItem> cur_flow = new ArrayList<>();
    private Object cur_session_file_lock = new Object();
    private String cur_session_id = null;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private boolean firstPause = true;
    private boolean firstResume = true;
    private JSONObject flow_cache = null;
    private long interval = 30;
    private boolean isNewSession = false;
    private long latestPauseTime = 0;
    private long latestResumeTime = 0;
    private boolean stat_enable = false;
    private long tempTimelong = 0;
    private WeakReference<JSONObject> weak_cache = null;

    private JPushSA() {
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long interval2) {
        this.interval = interval2;
    }

    public boolean isStatEnable() {
        return this.stat_enable;
    }

    public void setStatEnable(boolean enable) {
        this.stat_enable = enable;
    }

    public static JPushSA getInstance() {
        if (instance == null) {
            synchronized (JPushSA.class) {
                instance = new JPushSA();
            }
        }
        return instance;
    }

    public void onFragmentResume(final Context context, String pageName) {
        if (this.activitySwitch) {
            Logger.m1422ee(TAG, "JPushInterface.onResume() must be called after called JPushInterface.onPause() in last Activity or Fragment");
            return;
        }
        this.activitySwitch = true;
        this.cur_activity = pageName;
        this.latestResumeTime = System.currentTimeMillis();
        try {
            this.executor.execute(new Runnable() {
                public void run() {
                    JPushSA.this.sendLogRoutine(context);
                }
            });
        } catch (Exception e) {
        }
    }

    public void onFragmentPause(final Context context, String pageName) {
        if (!this.activitySwitch) {
            Logger.m1422ee(TAG, "JPushInterface.onPause() must be called after called JPushInterface.onResume() in this Activity or Fragment");
            return;
        }
        this.activitySwitch = false;
        if (this.cur_activity == null || !this.cur_activity.equals(pageName)) {
            Logger.m1422ee(TAG, "page name didn't match the last one passed by onResume");
            return;
        }
        this.latestPauseTime = System.currentTimeMillis();
        this.cur_flow.add(new ActivityFlowItem(this.cur_activity, (this.latestPauseTime - this.latestResumeTime) / 1000));
        try {
            this.executor.execute(new Runnable() {
                public void run() {
                    JPushSA.this.saveLogRoutine(context);
                }
            });
        } catch (Exception e) {
        }
    }

    public void onResume(final Context context) {
        if (invokeCheck(context, "onResume")) {
            isOnResumeInvoke = true;
            try {
                if (Class.forName(context.getClass().getName()).newInstance() instanceof TabActivity) {
                    this.activitySwitch = false;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (this.activitySwitch) {
                Logger.m1422ee(TAG, "JPushInterface.onResume() must be called after called JPushInterface.onPause() and JPushInterface.onResume should not be called more time in last Activity or Fragment  ");
                return;
            }
            this.activitySwitch = true;
            this.latestResumeTime = System.currentTimeMillis();
            this.cur_activity = context.getClass().getName();
            try {
                this.executor.execute(new Runnable() {
                    public void run() {
                        JPushSA.this.sendLogRoutine(context);
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    public void onPause(final Context context) {
        if (invokeCheck(context, "onPause")) {
            isOnPauseInvoke = true;
            try {
                if (Class.forName(context.getClass().getName()).newInstance() instanceof TabActivity) {
                    this.activitySwitch = true;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (!this.activitySwitch) {
                Logger.m1422ee(TAG, "JPushInterface.onPause() must be called after called JPushInterface.onResume() and JPushInterface.onPause should not be called more time in this Activity or Fragment ; ");
                return;
            }
            this.activitySwitch = false;
            if (this.cur_activity == null || !this.cur_activity.equals(context.getClass().getName())) {
                Logger.m1424i(TAG, "the activity pass by onPause didn't match last one passed by onResume");
                return;
            }
            this.latestPauseTime = System.currentTimeMillis();
            this.cur_flow.add(new ActivityFlowItem(this.cur_activity, (this.latestPauseTime - this.latestResumeTime) / 1000));
            this.tempTimelong = this.latestResumeTime;
            try {
                this.executor.execute(new Runnable() {
                    public void run() {
                        JPushSA.this.saveLogRoutine(context);
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    public void onKillProcess(final Context context) {
        try {
            if (this.cur_activity != null && this.activitySwitch) {
                this.latestPauseTime = System.currentTimeMillis();
                this.cur_flow.add(new ActivityFlowItem(this.cur_activity, (this.latestPauseTime - this.latestResumeTime) / 1000));
                try {
                    this.executor.execute(new Runnable() {
                        public void run() {
                            JPushSA.this.saveLogRoutine(context);
                        }
                    });
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
    }

    public void onEvent(final Context context, final String eventId) {
        if (context != null && !StringUtils.isEmpty(eventId)) {
            try {
                this.executor.execute(new Runnable() {
                    public void run() {
                        JPushSA.this.saveEventRoutine(context, eventId, -1);
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    public void onEvent(Context context, String eventId, String label) {
    }

    public void onEvent(Context context, String eventId, Map<String, String> map) {
    }

    public void onEvent(Context context, String eventId, int duration) {
    }

    public void onEventStart(Context context, String eventId) {
    }

    public void onEventEnd(Context context, String eventId) {
    }

    public void onPageStart(Context context, String pageName) {
    }

    public void onPageEnd(Context context, String pageName) {
    }

    private boolean invokeCheck(Context context, String method) {
        if (!isStatEnable()) {
            Logger.m1424i(TAG, "stat function has been disabled");
            return false;
        } else if (context == null) {
            Logger.m1424i(TAG, "context is null");
            return false;
        } else if (context instanceof Application) {
            Logger.m1422ee(TAG, "Context should be an Activity on this method : " + method);
            return false;
        } else if (isCallIn(method)) {
            return true;
        } else {
            throw new SecurityException("JPushSA.onResume(Context) must be invoked in Activity.onResume()");
        }
    }

    private void wrapDate(JSONObject obj) throws JSONException {
        String date_format = DateUtils.getTodayDateTimeForReport();
        String date = date_format.split("_")[0];
        String time = date_format.split("_")[1];
        obj.put("date", date);
        obj.put("time", time);
    }

    private JSONObject createNewSession(Context context, long time) {
        SpfHelper.getHelper().putLong(context, SESSION_START_MILLIS, this.latestResumeTime);
        this.cur_session_id = generateSessionID(context, time);
        SpfHelper.getHelper().putString(context, KEY_SESSION_ID, this.cur_session_id);
        JSONObject launchItem = new JSONObject();
        try {
            if (!Configs.isValidRegistered()) {
                Logger.m1432w(TAG, "invalid registered device: cannot report active_launch");
                return null;
            }
            wrapDate(launchItem);
            launchItem.put(JPushReportInterface.ITIME, Configs.getReportTime());
            launchItem.put(KEY_SESSION_ID, this.cur_session_id);
            launchItem.put("type", "active_launch");
            return launchItem;
        } catch (JSONException e) {
            return null;
        }
    }

    private String generateSessionID(Context context, long time) {
        StringBuilder sbd = new StringBuilder();
        String app_key = AndroidUtil.getAppKey(context);
        if (!StringUtils.isEmpty(app_key)) {
            sbd.append(app_key);
        }
        String device_id = AndroidUtil.getDeviceId(context);
        if (!StringUtils.isEmpty(device_id)) {
            sbd.append(device_id);
        }
        sbd.append(time);
        return AndroidUtil.getMD5String(sbd.toString());
    }

    private boolean isNewSession(Context context) {
        if (this.firstResume) {
            this.firstResume = false;
            Logger.m1416d(TAG, "statistics start");
            long lastPause = SpfHelper.getHelper().getLong(context, "last_pause", -1);
            long interMiddle = this.latestResumeTime - lastPause;
            if (lastPause == -1 || interMiddle > this.interval * 1000) {
                return true;
            }
            return false;
        } else if (this.latestResumeTime - this.latestPauseTime <= this.interval * 1000) {
            return false;
        } else {
            return true;
        }
    }

    private JSONObject getFlowCache(Context context) {
        if (this.flow_cache == null) {
            this.flow_cache = readFlowFromFile(context);
        }
        return this.flow_cache;
    }

    private JSONObject getWeakCache(Context context) {
        if (this.weak_cache == null || this.weak_cache.get() == null) {
            this.weak_cache = new WeakReference<>(readFlowFromFile(context));
        }
        return (JSONObject) this.weak_cache.get();
    }

    private void updateWeakCache(JSONObject new_cache) {
        if (this.weak_cache != null) {
            this.weak_cache.clear();
        }
        this.weak_cache = new WeakReference<>(new_cache);
    }

    private void updateFlowCache(JSONObject new_cache) {
        this.flow_cache = new_cache;
    }

    private JSONObject readFlowFromFile(Context context) {
        return ReportUtils.readLog(context, CACHE_PATH);
    }

    /* access modifiers changed from: private */
    public void sendLogRoutine(Context context) {
        JSONObject flow;
        if (!Configs.isValidRegistered()) {
            Logger.m1432w(TAG, "Unexpected, JPush unregistered");
            return;
        }
        this.isNewSession = isNewSession(context);
        if (this.isNewSession) {
            Logger.m1416d(TAG, "new statistics session");
            JSONArray items = new JSONArray();
            JSONObject launch = createNewSession(context, this.latestResumeTime);
            if (launch != null) {
                items.put(launch);
            }
            synchronized (this.cur_session_file_lock) {
                flow = getFlowCache(context);
                if (flow != null && flow.length() > 0) {
                    try {
                        wrapFlowInfo(flow);
                    } catch (Exception e) {
                    }
                    clearCurrentLogFile(context);
                    this.flow_cache = null;
                    this.cur_flow = new ArrayList<>();
                }
            }
            if (flow != null && flow.length() > 0) {
                items.put(flow);
                return;
            }
            return;
        }
        this.cur_session_id = SpfHelper.getHelper().getString(context, KEY_SESSION_ID, null);
    }

    private void clearCurrentLogFile(Context context) {
        ReportUtils.writeLogFile(context, CACHE_PATH, null);
    }

    /* access modifiers changed from: private */
    public void saveLogRoutine(Context context) {
        if (context != null) {
            synchronized (this.cur_session_file_lock) {
                SpfHelper.getHelper().putLong(context, "last_pause", this.latestPauseTime);
                SpfHelper.getHelper().putLong(context, SESSION_END_MILLIS, this.latestPauseTime);
                if (this.firstPause) {
                    this.firstPause = false;
                    if (!this.isNewSession && getFlowCache(context) != null) {
                        JSONArray temp = getFlowCache(context).optJSONArray(KEY_ACTIVITIES);
                        if (temp != null) {
                            parseFlowElementToCurFlow(temp);
                        }
                    }
                }
                JSONObject flow = getFlowCache(context);
                if (flow == null) {
                    flow = new JSONObject();
                }
                JSONArray activities = new JSONArray();
                for (int i = 0; i < this.cur_flow.size(); i++) {
                    ActivityFlowItem item = (ActivityFlowItem) this.cur_flow.get(i);
                    JSONObject act_item = new JSONObject();
                    try {
                        act_item.put(item.label, item.duration);
                        activities.put(act_item);
                    } catch (JSONException e) {
                    }
                }
                if (activities.length() > 0) {
                    try {
                        flow.put(KEY_ACTIVITIES, activities);
                    } catch (JSONException e2) {
                    }
                }
                if (flow.length() > 0) {
                    try {
                        updateFlowInfo(flow, context);
                    } catch (Exception e3) {
                    }
                    updateFlowCache(flow);
                    saveCurrentLog(context, flow);
                }
            }
        }
    }

    private void parseFlowElementToCurFlow(JSONArray activities) {
        ArrayList<ActivityFlowItem> temp = new ArrayList<>();
        for (int i = 0; i < activities.length(); i++) {
            try {
                JSONObject obj = activities.optJSONObject(i);
                if (obj != null) {
                    Iterator<String> it = obj.keys();
                    if (it.hasNext()) {
                        String name = (String) it.next();
                        temp.add(new ActivityFlowItem(name, obj.getLong(name)));
                    }
                }
            } catch (Exception e) {
                Logger.m1420e(TAG, e.getMessage());
            }
        }
        temp.addAll(this.cur_flow);
        this.cur_flow = new ArrayList<>();
        this.cur_flow.addAll(temp);
    }

    /* access modifiers changed from: private */
    public void saveEventRoutine(Context context, String eventId, int duration) {
        if (Configs.isValidRegistered()) {
            if (this.firstResume) {
                if (this.latestResumeTime == 0) {
                    this.latestResumeTime = System.currentTimeMillis();
                }
                if (isNewSession(context)) {
                    createNewSession(context, this.latestResumeTime);
                } else {
                    this.cur_session_id = SpfHelper.getHelper().getString(context, KEY_SESSION_ID, null);
                }
            } else {
                this.cur_session_id = SpfHelper.getHelper().getString(context, KEY_SESSION_ID, null);
            }
            synchronized (this.cur_session_file_lock) {
                JSONObject flow = getFlowCache(context);
                if (flow == null) {
                    flow = new JSONObject();
                }
                if (flow.optJSONArray("events") == null) {
                    new JSONArray();
                }
                JSONObject event_item = new JSONObject();
                try {
                    event_item.put(JPushReportInterface.EVENT_ID, eventId);
                    if (duration != -1) {
                        event_item.put("duration", duration);
                    }
                    event_item.put(KEY_SESSION_ID, this.cur_session_id);
                    wrapEventItem(context, event_item);
                } catch (Exception e) {
                }
            }
        }
    }

    private void wrapEventItem(Context context, JSONObject event_item) throws Exception {
        wrapDate(event_item);
    }

    private void saveCurrentLog(Context context, JSONObject flow) {
        if (this.flow_cache != null) {
            try {
                ReportUtils.rearrangeHistoryFile(context, this.flow_cache.toString().getBytes("utf-8").length);
            } catch (UnsupportedEncodingException e) {
            }
        }
        ReportUtils.writeLogFile(context, CACHE_PATH, flow);
    }

    private void updateFlowInfo(JSONObject flow_to_wrap, Context context) throws Exception {
        long session_start = SpfHelper.getHelper().getLong(context, SESSION_START_MILLIS, 0);
        long duration = 10;
        if (session_start == 0) {
            long interMiddle = this.latestPauseTime - this.tempTimelong;
            if (interMiddle > 0) {
                duration = interMiddle / 1000;
            }
            SpfHelper.getHelper().putLong(context, SESSION_START_MILLIS, this.tempTimelong);
        } else {
            duration = (this.latestPauseTime - session_start) / 1000;
        }
        flow_to_wrap.put("duration", duration);
        flow_to_wrap.put(JPushReportInterface.ITIME, Configs.getReportTime());
        flow_to_wrap.put(KEY_SESSION_ID, this.cur_session_id);
        wrapDate(flow_to_wrap);
    }

    private void wrapFlowInfo(JSONObject flow) throws Exception {
        flow.put("type", "active_terminate");
        flow.put(JPushReportInterface.ITIME, Configs.getReportTime());
    }

    private boolean isCallIn(String method) {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        boolean bool = false;
        if (stack.length >= 2) {
            int i = 0;
            while (i < stack.length) {
                try {
                    StackTraceElement flame = stack[i];
                    if (flame.getMethodName().equals(method)) {
                        Class<?> clazz = Class.forName(flame.getClassName());
                        while (true) {
                            if (clazz.getSuperclass() == null) {
                                break;
                            } else if (clazz.getSuperclass() == Activity.class) {
                                bool = true;
                                break;
                            } else {
                                clazz = clazz.getSuperclass();
                            }
                        }
                    }
                    i++;
                } catch (Exception e) {
                }
            }
        }
        return bool;
    }
}
