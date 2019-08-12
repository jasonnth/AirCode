package p005cn.jpush.android.util;

import android.content.Context;
import android.database.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.data.StatisticsDB;
import p005cn.jpush.android.data.StatisticsDBData;

/* renamed from: cn.jpush.android.util.IndexStats */
public class IndexStats {
    private static final String TAG = "IndexStats";

    /* renamed from: cn.jpush.android.util.IndexStats$StatisticsReport */
    public interface StatisticsReport {
        public static final String REPORT_DATE = "date";
    }

    public static String getStatistics(Context context) {
        if (context == null) {
            return "";
        }
        return "时间： " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Configs.getLastReportIndex())) + "------" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())) + "\r\n";
    }

    private static synchronized void buildStatisticsAndReport(Context context) {
        synchronized (IndexStats.class) {
            if (Configs.isValidRegistered()) {
                JSONObject jsonObject = new JSONObject();
                Cursor cursor = null;
                StatisticsDB db = StatisticsDB.getInstance(context);
                try {
                    jsonObject.put(JPushReportInterface.ITIME, Configs.getReportTime());
                    jsonObject.put("type", "sdk_index");
                    jsonObject.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date(Configs.getLastReportIndex())));
                    if (!db.open(false)) {
                        Logger.m1420e(TAG, "load writeable database fial");
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (db != null) {
                            db.close();
                        }
                    } else {
                        jsonObject.put("login_total", db.getCountTotalOrFailed(true));
                        jsonObject.put("login_failed", db.getCountTotalOrFailed(false));
                        JSONArray jsonFailedTopData = new JSONArray();
                        cursor = db.getFailedTop3Data();
                        if (cursor != null) {
                            do {
                                StatisticsDBData dbData = db.getStatisticsData(cursor);
                                if (dbData == null || StringUtils.isEmpty(dbData.getSt_sort())) {
                                    Logger.m1416d(TAG, "statisticsData is null ");
                                } else {
                                    jsonFailedTopData.put(getLoginFailedData(dbData));
                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                        }
                        jsonObject.put("failed_top", jsonFailedTopData);
                        JSONArray jsonSuccessTopData = new JSONArray();
                        cursor = db.getSucceedTop3Data();
                        if (cursor != null) {
                            do {
                                StatisticsDBData dbData2 = db.getStatisticsData(cursor);
                                if (dbData2 == null || StringUtils.isEmpty(dbData2.getSt_sort())) {
                                    Logger.m1416d(TAG, "statisticsData is null ");
                                } else {
                                    jsonSuccessTopData.put(getLoginSuccessData(dbData2));
                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                        }
                        jsonObject.put("succeed_top", jsonSuccessTopData);
                        db.close();
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (db != null) {
                            db.close();
                        }
                    }
                } catch (JSONException e) {
                    Logger.m1420e(TAG, "");
                    e.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }
                } catch (Exception e2) {
                    Logger.m1420e(TAG, "");
                    e2.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }
                    throw th;
                }
            }
        }
    }

    private static synchronized void clearStatisticsData(Context context) {
        synchronized (IndexStats.class) {
            StatisticsDB db = StatisticsDB.getInstance(context);
            if (db != null) {
                db.open(true);
                db.deleteTable();
                db.close();
            }
        }
    }

    private static JSONObject getLoginSuccessData(StatisticsDBData data) {
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("net", data.getSt_net());
            jsonData.put("conn_ip", data.getSt_conn_ip());
            jsonData.put(JPushReportInterface.LOCAL_DNS, data.getSt_local_dns());
            jsonData.put("source", data.getSt_source());
            jsonData.put("times", data.getTotal());
            JSONObject succeedData = new JSONObject();
            succeedData.put("count_0_1", data.getCount_1());
            succeedData.put("count_1_3", data.getCount_1_3());
            succeedData.put("count_3_", data.getCount_3_10());
            jsonData.put("success_details", succeedData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    private static JSONObject getLoginFailedData(StatisticsDBData data) {
        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("net", data.getSt_net());
            jsonData.put("conn_ip", data.getSt_conn_ip());
            jsonData.put(JPushReportInterface.LOCAL_DNS, data.getSt_local_dns());
            jsonData.put("source", data.getSt_source());
            jsonData.put("times", data.getTotal());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static synchronized void insertData(Context context, int ret, long loginCostime, int st_failed) {
        synchronized (IndexStats.class) {
            Logger.m1424i(TAG, "statisticsHelper");
            String netType = AndroidUtil.getCurrentNetType(context);
            String lastConnIP = Configs.getLastGoodConnIp() == null ? "" : Configs.getLastGoodConnIp();
            String sort_key = "" + netType + "_" + ret + "_" + lastConnIP + "_" + AndroidUtil.getPhoneIp();
            Logger.m1416d(TAG, "sort_key : " + sort_key + "    login_costime: " + loginCostime);
            int st_count_1 = 0;
            int st_count_1_3 = 0;
            int st_count_3_10 = 0;
            if (st_failed == 0) {
                if (loginCostime <= 1000) {
                    st_count_1 = 1;
                }
                if (loginCostime > 1000 && loginCostime <= 3000) {
                    st_count_1_3 = 1;
                }
                if (loginCostime > 3000) {
                    st_count_3_10 = 1;
                }
            }
            StatisticsDB db = StatisticsDB.getInstance(context);
            if (db != null) {
                if (!db.open(true)) {
                    Logger.m1420e(TAG, "load writeable database fial");
                } else {
                    if (db.isExist(sort_key)) {
                        Logger.m1424i(TAG, "sort_key:" + sort_key + " is exists : update");
                        Cursor cursor = null;
                        try {
                            cursor = db.getDataBySortkey(sort_key);
                            if (cursor == null) {
                                Logger.m1432w(TAG, "Nerver performed!!! cursor is null");
                            }
                            StatisticsDBData stData = db.getStatisticsData(cursor);
                            if (stData != null) {
                                db.updateData(sort_key, netType, lastConnIP, AndroidUtil.getPhoneIp(), "" + ret, stData.getFailed() + st_failed, stData.getTotal() + 1, st_count_1 + stData.getCount_1(), st_count_1_3 + stData.getCount_1_3(), st_count_3_10 + stData.getCount_3_10(), 0 + stData.getCount_10());
                            } else {
                                Logger.m1432w(TAG, "Nerver performed!!! StatisticsDBData is null");
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                        } catch (Exception e) {
                            if (cursor != null) {
                                cursor.close();
                            }
                        } catch (Throwable th) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    } else {
                        Logger.m1424i(TAG, "sort_key:" + sort_key + " not exists : insert");
                        db.insertData(sort_key, netType, lastConnIP, AndroidUtil.getPhoneIp(), "" + ret, st_failed, 1, st_count_1, st_count_1_3, st_count_3_10, 0);
                    }
                    db.close();
                }
            }
        }
    }
}
