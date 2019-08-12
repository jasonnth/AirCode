package p005cn.jpush.android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: cn.jpush.android.util.JRecorder */
public class JRecorder {
    public static final int INCREAMENTS = 0;
    private static final String JSON_KEY_DURATION = "duration";
    private static final String JSON_KEY_RANGE = "range";
    private static final String JSON_KEY_RECORDUNITS = "recordunits";
    private static final String JSON_KEY_RECORD_TYPE = "record_type";
    private static final String JSON_VALUE_RECORD_TYPE = "jrecorder";
    public static final int SUPERPOSITION = 1;
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public static Context mContext;
    private static ArrayList<Record> mGlobalDataList = new ArrayList<>();
    /* access modifiers changed from: private */
    public static volatile boolean reportEnabled = false;
    private static Handler timeoutHandler = null;
    private ArrayList<RecordUnit> data_ListData;

    /* renamed from: cn.jpush.android.util.JRecorder$Record */
    private class Record {
        public ArrayList<RecordUnit> data_List;
        public int recordType;

        private Record() {
        }
    }

    /* renamed from: cn.jpush.android.util.JRecorder$RecordUnit */
    private class RecordUnit {
        public double data;
        public long recordTime;

        private RecordUnit() {
        }
    }

    private JRecorder() {
        if (timeoutHandler == null) {
            timeoutHandler = new Handler(Looper.getMainLooper());
        }
    }

    private JRecorder(int record_type, Context context) {
        this();
        mContext = context.getApplicationContext();
        this.data_ListData = new ArrayList<>();
        Record rec = new Record();
        rec.recordType = record_type;
        rec.data_List = this.data_ListData;
        mGlobalDataList.add(rec);
    }

    public static JRecorder getIncreamentsRecorder(Context context) {
        return new JRecorder(0, context);
    }

    public static JRecorder getSuperpositionRecorder(Context context) {
        return new JRecorder(1, context);
    }

    public void record(final int data) {
        if (!reportEnabled) {
            Logger.m1416d("JRecorder", "report is not running");
        } else {
            executor.execute(new Runnable() {
                public void run() {
                    JRecorder.this.gatherData((double) data);
                }
            });
        }
    }

    public static void parseRecordCommand(String commandJson) {
        if (reportEnabled) {
            Logger.m1416d("JRecorder", "report already running");
            return;
        }
        try {
            int period = new JSONObject(commandJson).getInt("recordperiod");
            reportEnabled = true;
            Logger.m1416d("JRecorder", "report enabled :" + period + "s");
            if (timeoutHandler == null) {
                timeoutHandler = new Handler(Looper.getMainLooper());
            }
            timeoutHandler.postDelayed(new Runnable() {
                public void run() {
                    JRecorder.reportEnabled = false;
                    Logger.m1416d("JRecord", "report disabled");
                    JRecorder.shutDownReport(JRecorder.mContext);
                }
            }, (long) (period * 1000));
        } catch (JSONException e) {
            reportEnabled = false;
            Logger.m1416d("JRecorder", "report disabled by :" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public static void shutDownReport(Context context) {
        report(context);
    }

    private static void report(Context context) {
        try {
            JSONArray reportUnit = packingData();
            if (reportUnit != null && reportUnit.length() > 0) {
                final JSONObject recordProduct = new JSONObject();
                recordProduct.put("type", JSON_VALUE_RECORD_TYPE);
                recordProduct.put(JPushReportInterface.ITIME, Configs.getReportTime());
                recordProduct.put(JSON_KEY_RECORDUNITS, reportUnit);
                executor.execute(new Runnable() {
                    public void run() {
                        try {
                            Logger.m1426ii("JRecorder", "report log:" + recordProduct.toString(1));
                        } catch (JSONException e) {
                            Logger.m1426ii("JRecorder", "report log:" + recordProduct.toString());
                        }
                    }
                });
            }
        } catch (JSONException e) {
        }
    }

    /* access modifiers changed from: private */
    public void gatherData(double data) {
        synchronized (this.data_ListData) {
            RecordUnit unit = new RecordUnit();
            unit.data = data;
            unit.recordTime = System.currentTimeMillis();
            this.data_ListData.add(unit);
        }
    }

    private static JSONArray packingData() throws JSONException {
        if (mGlobalDataList == null || mGlobalDataList.size() <= 0) {
            return null;
        }
        JSONArray reportUnit = new JSONArray();
        Iterator it = mGlobalDataList.iterator();
        while (it.hasNext()) {
            Record rec = (Record) it.next();
            if (rec.recordType == 0) {
                reportUnit.put(packingIncreamentData(rec.data_List));
            } else if (rec.recordType == 1) {
                reportUnit.put(packingSuperpositionData(rec.data_List));
            }
        }
        cleanDataAfterPacking();
        return reportUnit;
    }

    private static JSONObject packingIncreamentData(ArrayList<RecordUnit> data_ListData2) throws JSONException {
        JSONObject reportUnit = null;
        if (data_ListData2 != null) {
            int size = data_ListData2.size();
            if (size > 0) {
                reportUnit = new JSONObject();
                long duration = ((RecordUnit) data_ListData2.get(size - 1)).recordTime - ((RecordUnit) data_ListData2.get(0)).recordTime;
                int lastRecordUnit = 0;
                for (int i = 0; i < size; i++) {
                    lastRecordUnit = (int) (((RecordUnit) data_ListData2.get(i)).data + ((double) lastRecordUnit));
                }
                reportUnit.put(JSON_KEY_RECORD_TYPE, "increament");
                reportUnit.put("duration", duration);
                reportUnit.put(JSON_KEY_RANGE, ((double) lastRecordUnit) - ((RecordUnit) data_ListData2.get(0)).data);
            }
        }
        return reportUnit;
    }

    private static JSONObject packingSuperpositionData(ArrayList<RecordUnit> data_ListData2) throws JSONException {
        if (data_ListData2 == null) {
            return null;
        }
        int size = data_ListData2.size();
        if (size <= 0) {
            return null;
        }
        long duration = ((RecordUnit) data_ListData2.get(size - 1)).recordTime - ((RecordUnit) data_ListData2.get(0)).recordTime;
        JSONObject reportUnit = new JSONObject();
        reportUnit.put(JSON_KEY_RECORD_TYPE, "overlay");
        reportUnit.put("duration", duration);
        reportUnit.put(JSON_KEY_RANGE, ((RecordUnit) data_ListData2.get(size - 1)).data - ((RecordUnit) data_ListData2.get(0)).data);
        return reportUnit;
    }

    private static void cleanDataAfterPacking() {
        Iterator it = mGlobalDataList.iterator();
        while (it.hasNext()) {
            ((Record) it.next()).data_List.clear();
        }
        mGlobalDataList.clear();
    }
}
