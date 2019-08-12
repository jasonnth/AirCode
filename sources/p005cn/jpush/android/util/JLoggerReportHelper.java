package p005cn.jpush.android.util;

import android.content.Context;
import java.util.ArrayList;
import org.jmrtd.lds.LDSFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.data.JLog;

/* renamed from: cn.jpush.android.util.JLoggerReportHelper */
public class JLoggerReportHelper {
    private static final long DEFAULT_PACKET_SIZE = 10240;
    private static final String JSON_KEY_LOG_CONTENT = "content";
    private static final String JSON_VALUE_TYPE = "jpush_logger";
    private static final int PACKETSIZE_MODE = 2;
    private static final int TIMEOUT_MODE = 1;
    protected boolean b_ReportEnabled = false;
    private int batchMode = 2;
    private int currentPacketSize = 0;
    private long lastReportTS = 0;
    private ArrayList<JLog> logCache = new ArrayList<>();
    private Context mContext;
    protected int reportLevels = 0;
    private long reportPeriod = 0;
    private long reportTime = 0;
    private long startUpTS = 0;

    protected JLoggerReportHelper() {
    }

    /* access modifiers changed from: protected */
    public void processLog(JLog log) {
        if (this.b_ReportEnabled) {
            synchronized (this.logCache) {
                this.currentPacketSize += log.getLogSize();
                Logger.m1418dd("JLoggerReportHelper", "日志大小：" + log.getLogSize());
                Logger.m1418dd("JLoggerReportHelper", "剩余时间：" + ((this.reportTime - (currentTS() - this.startUpTS)) / 1000) + "s");
                switch (this.batchMode) {
                    case 1:
                        timeoutModeProcess(log);
                        break;
                    case 2:
                        packetModeProcess(log);
                        break;
                }
            }
        }
    }

    private void timeoutModeProcess(JLog log) {
        if (currentTS() - this.startUpTS >= this.reportTime) {
            reportPacket();
            resetCurrentReportHelper();
            return;
        }
        if (currentTS() - this.lastReportTS > this.reportPeriod) {
            synchronized (this.logCache) {
                reportPacket();
                this.logCache.clear();
                this.currentPacketSize = 0;
                this.lastReportTS = currentTS();
            }
        }
        this.logCache.add(log);
    }

    private void packetModeProcess(JLog log) {
        if (currentTS() - this.startUpTS >= this.reportTime) {
            reportPacket();
            resetCurrentReportHelper();
            return;
        }
        if (((long) this.currentPacketSize) >= DEFAULT_PACKET_SIZE) {
            reportPacket();
            this.logCache.clear();
            this.currentPacketSize = log.getLogSize();
        }
        this.logCache.add(log);
    }

    /* access modifiers changed from: protected */
    public void batchReportByHeartBeats() {
        if (!this.b_ReportEnabled) {
            return;
        }
        if (currentTS() - this.startUpTS >= this.reportTime) {
            reportPacket();
            resetCurrentReportHelper();
        } else if (this.batchMode == 1 && currentTS() - this.lastReportTS >= this.reportPeriod) {
            synchronized (this.logCache) {
                reportPacket();
                this.logCache.clear();
                this.currentPacketSize = 0;
                this.lastReportTS = currentTS();
            }
        }
    }

    private long currentTS() {
        return System.currentTimeMillis();
    }

    private void reportPacket() {
        JSONObject packet = getLogPacket();
        if (packet != null) {
            Logger.m1418dd("JLoggerReportHelper", packet.toString());
        }
    }

    private void resetCurrentReportHelper() {
        this.b_ReportEnabled = false;
        this.currentPacketSize = 0;
        this.reportTime = 0;
        this.reportPeriod = 0;
        this.lastReportTS = 0;
        this.reportLevels = 0;
        this.startUpTS = 0;
        this.batchMode = 2;
        this.mContext = null;
        this.logCache.clear();
    }

    /* access modifiers changed from: protected */
    public void parseJsonCommand(Context context, String json) {
        Logger.m1418dd("JLoggerReportHelper", "收到服务下发指令：" + json);
        Logger.m1418dd("JLoggerReportHelper", "开始解析...");
        try {
            if (this.b_ReportEnabled) {
                this.b_ReportEnabled = false;
                reportPacket();
                resetCurrentReportHelper();
            }
            this.mContext = context;
            JSONObject command = new JSONObject(json);
            String mode = command.getString("mode");
            String levels = command.getString("levels");
            parseMode(mode);
            parseLevels(levels);
            this.reportTime = command.getLong("time") * 1000;
            Logger.m1418dd("JLoggerReportHelper", "上报模式：" + mode);
            Logger.m1418dd("JLoggerReportHelper", "日志等级：" + levels);
            if (this.batchMode == 1) {
                this.reportPeriod = command.getLong("period") * 1000;
                this.lastReportTS = currentTS();
                Logger.m1418dd("JLoggerReportHelper", "上报周期：" + (this.reportPeriod / 1000) + "s");
                if (this.reportTime < 300000) {
                    this.batchMode = 2;
                }
            } else {
                Logger.m1418dd("JLoggerReportHelper", "默认包大小：10240");
            }
            this.startUpTS = currentTS();
            this.b_ReportEnabled = true;
            Logger.m1418dd("JLoggerReportHelper", "解析成功");
        } catch (JSONException e) {
            resetCurrentReportHelper();
            Logger.m1418dd("JLoggerReportHelper", "解析失败：" + e.getMessage());
        }
    }

    private void parseMode(String mode) {
        if (mode == null) {
            return;
        }
        if (mode.equals("packet")) {
            this.batchMode = 2;
        } else if (mode.equals("period")) {
            this.batchMode = 1;
        }
    }

    private void parseLevels(String levels) {
        int levelCount = levels.length();
        while (levelCount > 0) {
            levelCount--;
            switch (levels.charAt(levelCount)) {
                case 'd':
                    this.reportLevels |= 2;
                    break;
                case 'e':
                    this.reportLevels |= 16;
                    break;
                case 'i':
                    this.reportLevels |= 4;
                    break;
                case LDSFile.EF_DG4_TAG /*118*/:
                    this.reportLevels |= 1;
                    break;
                case LDSFile.EF_SOD_TAG /*119*/:
                    this.reportLevels |= 8;
                    break;
            }
        }
    }

    private JSONObject getLogPacket() {
        if (this.logCache == null) {
            return null;
        }
        int len = this.logCache.size();
        if (len <= 0) {
            return null;
        }
        JSONArray logs = new JSONArray();
        for (int i = 0; i < len; i++) {
            logs.put(((JLog) this.logCache.get(i)).getJSONObjectLog());
        }
        if (logs.length() <= 0) {
            return null;
        }
        JSONObject jpushLogger = new JSONObject();
        try {
            jpushLogger.put("content", logs);
            jpushLogger.put("type", JSON_VALUE_TYPE);
            jpushLogger.put(JPushReportInterface.ITIME, Configs.getReportTime());
            return jpushLogger;
        } catch (JSONException e) {
            return null;
        }
    }
}
