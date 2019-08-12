package p005cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import p005cn.jpush.android.JPushException;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.util.HttpHelper;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.DownloadControl */
public class DownloadControl {
    private static final int CODE_DOWNLOAD_404 = -3;
    private static final int CODE_DOWNLOAD_AGAIN = 0;
    private static final int CODE_DOWNLOAD_FAIL = -2;
    private static final int CODE_DOWNLOAD_SUCCESS = 1;
    private static final int CODE_INVALID_FILE_ERROR = 2;
    private static final int CODE_TIME_OUT_TRY_AGAIN = -1;
    private static final double DOWNLAOD_TIMES_RATE = 1.1d;
    public static final int FAIL_TYPE_ALERT_AUTO_CONTINUE = 1;
    public static final int FAIL_TYPE_ALERT_CLICK_CONTINUE_404 = 3;
    public static final int FAIL_TYPE_ALERT_CLICK_CONTINUE_NORMAL = 2;
    public static final int FAIL_TYPE_NOALERT_AUTO_CONTINUE = 0;
    private static final int REFRESH_TIME = 2000;
    private static final String TAG = "DownloadControl";
    private static final int TRY_AGAIN_WHILE_CHECK_MD5_ERROR_TIMES = 3;
    public static boolean isNetworkAvailable = true;
    private Bundle downloadInfos;
    /* access modifiers changed from: private */
    public long hadDownLength = 0;
    public boolean isNeedStopDownload = false;
    /* access modifiers changed from: private */
    public DownloadHandler mDownloadProgressHandler = null;
    /* access modifiers changed from: private */
    public long totalDownLength = 0;

    /* renamed from: cn.jpush.android.service.DownloadControl$DownloadHandler */
    private class DownloadHandler extends Handler {
        private DownloadListener mDownloadListener = null;

        public DownloadHandler(Looper looper, DownloadListener downloadListener) {
            super(looper);
            this.mDownloadListener = downloadListener;
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (DownloadControl.this.isNeedStopDownload) {
                Logger.m1428v(DownloadControl.TAG, "stop refresh download progress.");
                return;
            }
            if (this.mDownloadListener != null) {
                this.mDownloadListener.onDownloading(DownloadControl.this.hadDownLength, DownloadControl.this.totalDownLength);
            }
            DownloadControl.this.mDownloadProgressHandler.sendEmptyMessageDelayed(0, 2000);
        }
    }

    /* renamed from: cn.jpush.android.service.DownloadControl$DownloadListener */
    public interface DownloadListener {
        void onDownloadFailed(int i);

        void onDownloadSucceed(String str, boolean z);

        void onDownloading(long j, long j2);
    }

    public DownloadControl(Context context, Entity entity, Bundle downloadInfos2, DownloadListener downloadListener, int interval) {
        Logger.m1428v(TAG, "Create downloadControl");
        this.isNeedStopDownload = false;
        this.downloadInfos = downloadInfos2;
        this.mDownloadProgressHandler = new DownloadHandler(context.getMainLooper(), downloadListener);
        this.mDownloadProgressHandler.sendEmptyMessageDelayed(0, 2000);
        int reCheckFileTimes = 0;
        while (isNetworkAvailable) {
            if (this.isNeedStopDownload) {
                Logger.m1424i(TAG, "Download is already stopped. Dont start again.");
                this.mDownloadProgressHandler.removeCallbacksAndMessages(null);
                downloadListener.onDownloadFailed(1);
                return;
            } else if (entity._downloadRetryTimes == 0) {
                Logger.m1432w(TAG, "try to connect too much. stop download now.");
                if (downloadListener != null) {
                    this.isNeedStopDownload = true;
                    DownloadService.mDownladTasks.remove(entity);
                    this.mDownloadProgressHandler.removeCallbacksAndMessages(null);
                    downloadListener.onDownloadFailed(2);
                    return;
                }
                return;
            } else if (reCheckFileTimes >= 3) {
                Logger.m1432w(TAG, "check md5 error too much. stop download now.");
                if (downloadListener != null) {
                    this.isNeedStopDownload = true;
                    DownloadService.mDownladTasks.remove(entity);
                    this.mDownloadProgressHandler.removeCallbacksAndMessages(null);
                    downloadListener.onDownloadFailed(2);
                    return;
                }
                return;
            } else {
                int code = download(context, downloadListener, entity);
                entity._downloadRetryTimes--;
                if (code == -1) {
                    Logger.m1416d(TAG, "Connect time out, try rest - " + entity._downloadRetryTimes);
                    try {
                        Thread.sleep((long) interval);
                    } catch (InterruptedException e) {
                    }
                } else if (code == 0) {
                    Logger.m1416d(TAG, "Download again, try rest - " + entity._downloadRetryTimes);
                    try {
                        Thread.sleep((long) interval);
                    } catch (InterruptedException e2) {
                    }
                } else if (code == 1) {
                    Logger.m1416d(TAG, "Download succeed.");
                    this.mDownloadProgressHandler.removeCallbacksAndMessages(null);
                    this.isNeedStopDownload = true;
                    return;
                } else if (code == 2) {
                    Logger.m1416d(TAG, "md5 check error, try again - " + entity._downloadRetryTimes);
                    reCheckFileTimes++;
                } else if (code == -3) {
                    this.isNeedStopDownload = true;
                    DownloadService.mDownladTasks.remove(entity);
                    this.mDownloadProgressHandler.removeCallbacksAndMessages(null);
                    downloadListener.onDownloadFailed(3);
                    return;
                } else {
                    Logger.m1416d(TAG, "Other exception!!");
                    this.isNeedStopDownload = true;
                    DownloadService.mDownladTasks.remove(entity);
                    this.mDownloadProgressHandler.removeCallbacksAndMessages(null);
                    downloadListener.onDownloadFailed(2);
                    return;
                }
            }
        }
        Logger.m1424i(TAG, "Network is not available, dont download");
        this.mDownloadProgressHandler.removeCallbacksAndMessages(null);
        this.isNeedStopDownload = true;
        downloadListener.onDownloadFailed(1);
    }

    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r22v0 */
    /* JADX WARNING: type inference failed for: r22v1 */
    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: type inference failed for: r22v2 */
    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r22v3 */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r22v4, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v4, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v5 */
    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: type inference failed for: r22v6, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v6, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v7 */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r22v8, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v8, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v9 */
    /* JADX WARNING: type inference failed for: r5v9 */
    /* JADX WARNING: type inference failed for: r22v10, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v10, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v11 */
    /* JADX WARNING: type inference failed for: r5v11 */
    /* JADX WARNING: type inference failed for: r22v12, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v12, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v13 */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* JADX WARNING: type inference failed for: r22v14, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v14, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v15, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v15, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v16, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v16, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r11v10, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v17 */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r22v18 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r22v19 */
    /* JADX WARNING: type inference failed for: r5v19 */
    /* JADX WARNING: type inference failed for: r22v20, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v20, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v21 */
    /* JADX WARNING: type inference failed for: r5v21 */
    /* JADX WARNING: type inference failed for: r22v22, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v22, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v23 */
    /* JADX WARNING: type inference failed for: r5v23 */
    /* JADX WARNING: type inference failed for: r22v24, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v24, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v25 */
    /* JADX WARNING: type inference failed for: r5v25 */
    /* JADX WARNING: type inference failed for: r22v26, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v26, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v27 */
    /* JADX WARNING: type inference failed for: r5v27 */
    /* JADX WARNING: type inference failed for: r22v28, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v28, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v29 */
    /* JADX WARNING: type inference failed for: r5v29 */
    /* JADX WARNING: type inference failed for: r22v30, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v30, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v31, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v31, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r22v32, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v32, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r11v22, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v33 */
    /* JADX WARNING: type inference failed for: r5v34 */
    /* JADX WARNING: type inference failed for: r5v35 */
    /* JADX WARNING: type inference failed for: r5v36 */
    /* JADX WARNING: type inference failed for: r5v37 */
    /* JADX WARNING: type inference failed for: r5v38 */
    /* JADX WARNING: type inference failed for: r5v39 */
    /* JADX WARNING: type inference failed for: r5v40 */
    /* JADX WARNING: type inference failed for: r5v41 */
    /* JADX WARNING: type inference failed for: r5v42 */
    /* JADX WARNING: type inference failed for: r5v43 */
    /* JADX WARNING: type inference failed for: r5v44 */
    /* JADX WARNING: type inference failed for: r5v45 */
    /* JADX WARNING: type inference failed for: r5v46 */
    /* JADX WARNING: type inference failed for: r5v47 */
    /* JADX WARNING: type inference failed for: r5v48 */
    /* JADX WARNING: type inference failed for: r5v49 */
    /* JADX WARNING: type inference failed for: r5v50 */
    /* JADX WARNING: type inference failed for: r5v51 */
    /* JADX WARNING: type inference failed for: r5v52 */
    /* JADX WARNING: type inference failed for: r5v53 */
    /* JADX WARNING: type inference failed for: r5v54 */
    /* JADX WARNING: type inference failed for: r5v55 */
    /* JADX WARNING: type inference failed for: r5v56 */
    /* JADX WARNING: type inference failed for: r5v57 */
    /* JADX WARNING: type inference failed for: r5v58 */
    /* JADX WARNING: type inference failed for: r5v59 */
    /* JADX WARNING: type inference failed for: r5v60 */
    /* JADX WARNING: type inference failed for: r5v61 */
    /* JADX WARNING: type inference failed for: r5v62 */
    /* JADX WARNING: type inference failed for: r5v63 */
    /* JADX WARNING: type inference failed for: r5v64 */
    /* JADX WARNING: type inference failed for: r5v65 */
    /* JADX WARNING: type inference failed for: r5v66 */
    /* JADX WARNING: type inference failed for: r5v67 */
    /* JADX WARNING: type inference failed for: r5v68 */
    /* JADX WARNING: type inference failed for: r5v69 */
    /* JADX WARNING: type inference failed for: r5v70 */
    /* JADX WARNING: type inference failed for: r5v71 */
    /* JADX WARNING: type inference failed for: r5v72 */
    /* JADX WARNING: type inference failed for: r5v73 */
    /* JADX WARNING: type inference failed for: r5v74 */
    /* JADX WARNING: type inference failed for: r5v75 */
    /* JADX WARNING: type inference failed for: r5v76 */
    /* JADX WARNING: type inference failed for: r5v77 */
    /* JADX WARNING: type inference failed for: r5v78 */
    /* JADX WARNING: type inference failed for: r5v79 */
    /* JADX WARNING: type inference failed for: r5v80 */
    /* JADX WARNING: type inference failed for: r5v81 */
    /* JADX WARNING: type inference failed for: r5v82 */
    /* JADX WARNING: type inference failed for: r5v83 */
    /* JADX WARNING: type inference failed for: r5v84 */
    /* JADX WARNING: type inference failed for: r5v85 */
    /* JADX WARNING: type inference failed for: r5v86 */
    /* JADX WARNING: type inference failed for: r5v87 */
    /* JADX WARNING: type inference failed for: r5v88 */
    /* JADX WARNING: type inference failed for: r5v89 */
    /* JADX WARNING: type inference failed for: r5v90 */
    /* JADX WARNING: type inference failed for: r5v91 */
    /* JADX WARNING: type inference failed for: r5v92 */
    /* JADX WARNING: type inference failed for: r5v93 */
    /* JADX WARNING: type inference failed for: r5v94 */
    /* JADX WARNING: type inference failed for: r5v95 */
    /* JADX WARNING: type inference failed for: r5v96 */
    /* JADX WARNING: type inference failed for: r5v97 */
    /* JADX WARNING: type inference failed for: r5v98 */
    /* JADX WARNING: type inference failed for: r5v99 */
    /* JADX WARNING: type inference failed for: r5v100 */
    /* JADX WARNING: type inference failed for: r5v101 */
    /* JADX WARNING: type inference failed for: r5v102 */
    /* JADX WARNING: type inference failed for: r5v103 */
    /* JADX WARNING: type inference failed for: r5v104 */
    /* JADX WARNING: type inference failed for: r5v105 */
    /* JADX WARNING: type inference failed for: r5v106 */
    /* JADX WARNING: type inference failed for: r5v107 */
    /* JADX WARNING: type inference failed for: r5v108 */
    /* JADX WARNING: type inference failed for: r5v109 */
    /* JADX WARNING: type inference failed for: r5v110 */
    /* JADX WARNING: type inference failed for: r5v111 */
    /* JADX WARNING: type inference failed for: r5v112 */
    /* JADX WARNING: type inference failed for: r5v113 */
    /* JADX WARNING: type inference failed for: r5v114 */
    /* JADX WARNING: type inference failed for: r5v115 */
    /* JADX WARNING: type inference failed for: r5v116 */
    /* JADX WARNING: type inference failed for: r5v117 */
    /* JADX WARNING: type inference failed for: r5v118 */
    /* JADX WARNING: type inference failed for: r5v119 */
    /* JADX WARNING: type inference failed for: r5v120 */
    /* JADX WARNING: type inference failed for: r5v121 */
    /* JADX WARNING: type inference failed for: r5v122 */
    /* JADX WARNING: type inference failed for: r5v123 */
    /* JADX WARNING: type inference failed for: r5v124 */
    /* JADX WARNING: type inference failed for: r5v125 */
    /* JADX WARNING: type inference failed for: r5v126 */
    /* JADX WARNING: type inference failed for: r5v127 */
    /* JADX WARNING: type inference failed for: r5v128 */
    /* JADX WARNING: type inference failed for: r5v129 */
    /* JADX WARNING: type inference failed for: r5v130 */
    /* JADX WARNING: type inference failed for: r5v131 */
    /* JADX WARNING: type inference failed for: r5v132 */
    /* JADX WARNING: type inference failed for: r5v133 */
    /* JADX WARNING: type inference failed for: r5v134 */
    /* JADX WARNING: type inference failed for: r5v135 */
    /* JADX WARNING: type inference failed for: r5v136 */
    /* JADX WARNING: type inference failed for: r5v137 */
    /* JADX WARNING: type inference failed for: r5v138 */
    /* JADX WARNING: type inference failed for: r5v139 */
    /* JADX WARNING: type inference failed for: r5v140 */
    /* JADX WARNING: type inference failed for: r5v141 */
    /* JADX WARNING: type inference failed for: r5v142 */
    /* JADX WARNING: type inference failed for: r5v143 */
    /* JADX WARNING: type inference failed for: r5v144 */
    /* JADX WARNING: type inference failed for: r5v145 */
    /* JADX WARNING: type inference failed for: r5v146 */
    /* JADX WARNING: type inference failed for: r5v147 */
    /* JADX WARNING: type inference failed for: r5v148 */
    /* JADX WARNING: type inference failed for: r5v149 */
    /* JADX WARNING: type inference failed for: r5v150 */
    /* JADX WARNING: type inference failed for: r5v151 */
    /* JADX WARNING: type inference failed for: r5v152 */
    /* JADX WARNING: type inference failed for: r5v153 */
    /* JADX WARNING: type inference failed for: r5v154 */
    /* JADX WARNING: type inference failed for: r5v155 */
    /* JADX WARNING: type inference failed for: r5v156 */
    /* JADX WARNING: type inference failed for: r5v157 */
    /* JADX WARNING: type inference failed for: r5v158 */
    /* JADX WARNING: type inference failed for: r5v159 */
    /* JADX WARNING: type inference failed for: r5v160 */
    /* JADX WARNING: type inference failed for: r5v161 */
    /* JADX WARNING: type inference failed for: r5v162 */
    /* JADX WARNING: type inference failed for: r5v163 */
    /* JADX WARNING: type inference failed for: r5v164 */
    /* JADX WARNING: type inference failed for: r5v165 */
    /* JADX WARNING: type inference failed for: r5v166 */
    /* JADX WARNING: type inference failed for: r5v167 */
    /* JADX WARNING: type inference failed for: r5v168 */
    /* JADX WARNING: type inference failed for: r5v169 */
    /* JADX WARNING: type inference failed for: r5v170 */
    /* JADX WARNING: type inference failed for: r5v171 */
    /* JADX WARNING: type inference failed for: r5v172 */
    /* JADX WARNING: type inference failed for: r22v33 */
    /* JADX WARNING: type inference failed for: r22v34 */
    /* JADX WARNING: type inference failed for: r22v35 */
    /* JADX WARNING: type inference failed for: r22v36 */
    /* JADX WARNING: type inference failed for: r22v37 */
    /* JADX WARNING: type inference failed for: r22v38 */
    /* JADX WARNING: type inference failed for: r22v39 */
    /* JADX WARNING: type inference failed for: r22v40 */
    /* JADX WARNING: type inference failed for: r22v41 */
    /* JADX WARNING: type inference failed for: r22v42 */
    /* JADX WARNING: type inference failed for: r22v43 */
    /* JADX WARNING: type inference failed for: r22v44 */
    /* JADX WARNING: type inference failed for: r22v45 */
    /* JADX WARNING: type inference failed for: r22v46 */
    /* JADX WARNING: type inference failed for: r22v47 */
    /* JADX WARNING: type inference failed for: r5v173 */
    /* JADX WARNING: type inference failed for: r22v48 */
    /* JADX WARNING: type inference failed for: r5v174 */
    /* JADX WARNING: type inference failed for: r22v49 */
    /* JADX WARNING: type inference failed for: r5v175 */
    /* JADX WARNING: type inference failed for: r22v50 */
    /* JADX WARNING: type inference failed for: r5v176 */
    /* JADX WARNING: type inference failed for: r22v51 */
    /* JADX WARNING: type inference failed for: r22v52 */
    /* JADX WARNING: type inference failed for: r5v177 */
    /* JADX WARNING: type inference failed for: r5v178 */
    /* JADX WARNING: type inference failed for: r22v53 */
    /* JADX WARNING: type inference failed for: r5v179 */
    /* JADX WARNING: type inference failed for: r22v54 */
    /* JADX WARNING: type inference failed for: r22v55 */
    /* JADX WARNING: type inference failed for: r5v180 */
    /* JADX WARNING: type inference failed for: r5v181 */
    /* JADX WARNING: type inference failed for: r22v56 */
    /* JADX WARNING: type inference failed for: r5v182 */
    /* JADX WARNING: type inference failed for: r22v57 */
    /* JADX WARNING: type inference failed for: r22v58 */
    /* JADX WARNING: type inference failed for: r5v183 */
    /* JADX WARNING: type inference failed for: r5v184 */
    /* JADX WARNING: type inference failed for: r22v59 */
    /* JADX WARNING: type inference failed for: r5v185 */
    /* JADX WARNING: type inference failed for: r22v60 */
    /* JADX WARNING: type inference failed for: r22v61 */
    /* JADX WARNING: type inference failed for: r5v186 */
    /* JADX WARNING: type inference failed for: r5v187 */
    /* JADX WARNING: type inference failed for: r22v62 */
    /* JADX WARNING: type inference failed for: r5v188 */
    /* JADX WARNING: type inference failed for: r22v63 */
    /* JADX WARNING: type inference failed for: r22v64 */
    /* JADX WARNING: type inference failed for: r5v189 */
    /* JADX WARNING: type inference failed for: r5v190 */
    /* JADX WARNING: type inference failed for: r22v65 */
    /* JADX WARNING: type inference failed for: r22v66 */
    /* JADX WARNING: type inference failed for: r5v191 */
    /* JADX WARNING: type inference failed for: r5v192 */
    /* JADX WARNING: type inference failed for: r22v67 */
    /* JADX WARNING: type inference failed for: r22v68 */
    /* JADX WARNING: type inference failed for: r22v69 */
    /* JADX WARNING: type inference failed for: r22v70 */
    /* JADX WARNING: type inference failed for: r22v71 */
    /* JADX WARNING: type inference failed for: r22v72 */
    /* JADX WARNING: type inference failed for: r22v73 */
    /* JADX WARNING: type inference failed for: r22v74 */
    /* JADX WARNING: type inference failed for: r22v75 */
    /* JADX WARNING: type inference failed for: r22v76 */
    /* JADX WARNING: type inference failed for: r22v77 */
    /* JADX WARNING: type inference failed for: r22v78 */
    /* JADX WARNING: type inference failed for: r22v79 */
    /* JADX WARNING: type inference failed for: r22v80 */
    /* JADX WARNING: type inference failed for: r22v81 */
    /* JADX WARNING: type inference failed for: r22v82 */
    /* JADX WARNING: type inference failed for: r22v83 */
    /* JADX WARNING: type inference failed for: r22v84 */
    /* JADX WARNING: type inference failed for: r22v85 */
    /* JADX WARNING: type inference failed for: r22v86 */
    /* JADX WARNING: type inference failed for: r22v87 */
    /* JADX WARNING: type inference failed for: r22v88 */
    /* JADX WARNING: type inference failed for: r22v89 */
    /* JADX WARNING: type inference failed for: r22v90 */
    /* JADX WARNING: type inference failed for: r22v91 */
    /* JADX WARNING: type inference failed for: r22v92 */
    /* JADX WARNING: type inference failed for: r22v93 */
    /* JADX WARNING: type inference failed for: r22v94 */
    /* JADX WARNING: type inference failed for: r22v95 */
    /* JADX WARNING: type inference failed for: r22v96 */
    /* JADX WARNING: type inference failed for: r22v97 */
    /* JADX WARNING: type inference failed for: r22v98 */
    /* JADX WARNING: type inference failed for: r22v99 */
    /* JADX WARNING: type inference failed for: r22v100 */
    /* JADX WARNING: type inference failed for: r22v101 */
    /* JADX WARNING: type inference failed for: r22v102 */
    /* JADX WARNING: type inference failed for: r22v103 */
    /* JADX WARNING: type inference failed for: r22v104 */
    /* JADX WARNING: type inference failed for: r22v105 */
    /* JADX WARNING: type inference failed for: r22v106 */
    /* JADX WARNING: type inference failed for: r22v107 */
    /* JADX WARNING: type inference failed for: r22v108 */
    /* JADX WARNING: type inference failed for: r22v109 */
    /* JADX WARNING: type inference failed for: r22v110 */
    /* JADX WARNING: type inference failed for: r22v111 */
    /* JADX WARNING: type inference failed for: r22v112 */
    /* JADX WARNING: type inference failed for: r22v113 */
    /* JADX WARNING: type inference failed for: r22v114 */
    /* JADX WARNING: type inference failed for: r22v115 */
    /* JADX WARNING: type inference failed for: r22v116 */
    /* JADX WARNING: type inference failed for: r22v117 */
    /* JADX WARNING: type inference failed for: r22v118 */
    /* JADX WARNING: type inference failed for: r22v119 */
    /* JADX WARNING: type inference failed for: r22v120 */
    /* JADX WARNING: type inference failed for: r22v121 */
    /* JADX WARNING: type inference failed for: r22v122 */
    /* JADX WARNING: type inference failed for: r22v123 */
    /* JADX WARNING: type inference failed for: r22v124 */
    /* JADX WARNING: type inference failed for: r22v125 */
    /* JADX WARNING: type inference failed for: r22v126 */
    /* JADX WARNING: type inference failed for: r22v127 */
    /* JADX WARNING: type inference failed for: r22v128 */
    /* JADX WARNING: type inference failed for: r22v129 */
    /* JADX WARNING: type inference failed for: r22v130 */
    /* JADX WARNING: type inference failed for: r22v131 */
    /* JADX WARNING: type inference failed for: r22v132 */
    /* JADX WARNING: type inference failed for: r22v133 */
    /* JADX WARNING: type inference failed for: r22v134 */
    /* JADX WARNING: type inference failed for: r22v135 */
    /* JADX WARNING: type inference failed for: r22v136 */
    /* JADX WARNING: type inference failed for: r22v137 */
    /* JADX WARNING: type inference failed for: r22v138 */
    /* JADX WARNING: type inference failed for: r22v139 */
    /* JADX WARNING: type inference failed for: r22v140 */
    /* JADX WARNING: type inference failed for: r22v141 */
    /* JADX WARNING: type inference failed for: r22v142 */
    /* JADX WARNING: type inference failed for: r22v143 */
    /* JADX WARNING: type inference failed for: r22v144 */
    /* JADX WARNING: type inference failed for: r22v145 */
    /* JADX WARNING: type inference failed for: r22v146 */
    /* JADX WARNING: type inference failed for: r22v147 */
    /* JADX WARNING: type inference failed for: r22v148 */
    /* JADX WARNING: type inference failed for: r22v149 */
    /* JADX WARNING: type inference failed for: r22v150 */
    /* JADX WARNING: type inference failed for: r22v151 */
    /* JADX WARNING: type inference failed for: r22v152 */
    /* JADX WARNING: type inference failed for: r22v153 */
    /* JADX WARNING: type inference failed for: r22v154 */
    /* JADX WARNING: type inference failed for: r22v155 */
    /* JADX WARNING: type inference failed for: r22v156 */
    /* JADX WARNING: type inference failed for: r22v157 */
    /* JADX WARNING: type inference failed for: r22v158 */
    /* JADX WARNING: type inference failed for: r22v159 */
    /* JADX WARNING: type inference failed for: r22v160 */
    /* JADX WARNING: type inference failed for: r22v161 */
    /* JADX WARNING: type inference failed for: r22v162 */
    /* JADX WARNING: type inference failed for: r22v163 */
    /* JADX WARNING: type inference failed for: r22v164 */
    /* JADX WARNING: type inference failed for: r22v165 */
    /* JADX WARNING: type inference failed for: r22v166 */
    /* JADX WARNING: type inference failed for: r22v167 */
    /* JADX WARNING: type inference failed for: r22v168 */
    /* JADX WARNING: type inference failed for: r22v169 */
    /* JADX WARNING: type inference failed for: r22v170 */
    /* JADX WARNING: type inference failed for: r5v193 */
    /* JADX WARNING: type inference failed for: r5v194 */
    /* JADX WARNING: type inference failed for: r5v195 */
    /* JADX WARNING: type inference failed for: r5v196 */
    /* JADX WARNING: type inference failed for: r5v197 */
    /* JADX WARNING: type inference failed for: r5v198 */
    /* JADX WARNING: type inference failed for: r5v199 */
    /* JADX WARNING: type inference failed for: r5v200 */
    /* JADX WARNING: type inference failed for: r5v201 */
    /* JADX WARNING: type inference failed for: r5v202 */
    /* JADX WARNING: type inference failed for: r5v203 */
    /* JADX WARNING: type inference failed for: r5v204 */
    /* JADX WARNING: type inference failed for: r5v205 */
    /* JADX WARNING: type inference failed for: r5v206 */
    /* JADX WARNING: type inference failed for: r5v207 */
    /* JADX WARNING: type inference failed for: r5v208 */
    /* JADX WARNING: type inference failed for: r5v209 */
    /* JADX WARNING: type inference failed for: r5v210 */
    /* JADX WARNING: type inference failed for: r5v211 */
    /* JADX WARNING: type inference failed for: r5v212 */
    /* JADX WARNING: type inference failed for: r5v213 */
    /* JADX WARNING: type inference failed for: r5v214 */
    /* JADX WARNING: type inference failed for: r5v215 */
    /* JADX WARNING: type inference failed for: r5v216 */
    /* JADX WARNING: type inference failed for: r5v217 */
    /* JADX WARNING: type inference failed for: r5v218 */
    /* JADX WARNING: type inference failed for: r5v219 */
    /* JADX WARNING: type inference failed for: r5v220 */
    /* JADX WARNING: type inference failed for: r5v221 */
    /* JADX WARNING: type inference failed for: r5v222 */
    /* JADX WARNING: type inference failed for: r5v223 */
    /* JADX WARNING: type inference failed for: r5v224 */
    /* JADX WARNING: type inference failed for: r5v225 */
    /* JADX WARNING: type inference failed for: r5v226 */
    /* JADX WARNING: type inference failed for: r22v171 */
    /* JADX WARNING: type inference failed for: r5v227 */
    /* JADX WARNING: type inference failed for: r22v172 */
    /* JADX WARNING: type inference failed for: r5v228 */
    /* JADX WARNING: type inference failed for: r22v173 */
    /* JADX WARNING: type inference failed for: r5v229 */
    /* JADX WARNING: type inference failed for: r22v174 */
    /* JADX WARNING: type inference failed for: r5v230 */
    /* JADX WARNING: type inference failed for: r22v175 */
    /* JADX WARNING: type inference failed for: r22v176 */
    /* JADX WARNING: type inference failed for: r5v231 */
    /* JADX WARNING: type inference failed for: r5v232 */
    /* JADX WARNING: type inference failed for: r22v177 */
    /* JADX WARNING: type inference failed for: r5v233 */
    /* JADX WARNING: type inference failed for: r22v178 */
    /* JADX WARNING: type inference failed for: r22v179 */
    /* JADX WARNING: type inference failed for: r5v234 */
    /* JADX WARNING: type inference failed for: r5v235 */
    /* JADX WARNING: type inference failed for: r22v180 */
    /* JADX WARNING: type inference failed for: r5v236 */
    /* JADX WARNING: type inference failed for: r22v181 */
    /* JADX WARNING: type inference failed for: r22v182 */
    /* JADX WARNING: type inference failed for: r5v237 */
    /* JADX WARNING: type inference failed for: r5v238 */
    /* JADX WARNING: type inference failed for: r22v183 */
    /* JADX WARNING: type inference failed for: r5v239 */
    /* JADX WARNING: type inference failed for: r22v184 */
    /* JADX WARNING: type inference failed for: r22v185 */
    /* JADX WARNING: type inference failed for: r5v240 */
    /* JADX WARNING: type inference failed for: r5v241 */
    /* JADX WARNING: type inference failed for: r22v186 */
    /* JADX WARNING: type inference failed for: r5v242 */
    /* JADX WARNING: type inference failed for: r22v187 */
    /* JADX WARNING: type inference failed for: r22v188 */
    /* JADX WARNING: type inference failed for: r5v243 */
    /* JADX WARNING: type inference failed for: r5v244 */
    /* JADX WARNING: type inference failed for: r22v189 */
    /* JADX WARNING: type inference failed for: r22v190 */
    /* JADX WARNING: type inference failed for: r5v245 */
    /* JADX WARNING: type inference failed for: r5v246 */
    /* JADX WARNING: type inference failed for: r22v191 */
    /* JADX WARNING: type inference failed for: r22v192 */
    /* JADX WARNING: type inference failed for: r22v193 */
    /* JADX WARNING: type inference failed for: r22v194 */
    /* JADX WARNING: type inference failed for: r22v195 */
    /* JADX WARNING: type inference failed for: r22v196 */
    /* JADX WARNING: type inference failed for: r22v197 */
    /* JADX WARNING: type inference failed for: r22v198 */
    /* JADX WARNING: type inference failed for: r22v199 */
    /* JADX WARNING: type inference failed for: r22v200 */
    /* JADX WARNING: type inference failed for: r22v201 */
    /* JADX WARNING: type inference failed for: r22v202 */
    /* JADX WARNING: type inference failed for: r22v203 */
    /* JADX WARNING: type inference failed for: r22v204 */
    /* JADX WARNING: type inference failed for: r22v205 */
    /* JADX WARNING: type inference failed for: r22v206 */
    /* JADX WARNING: type inference failed for: r22v207 */
    /* JADX WARNING: type inference failed for: r22v208 */
    /* JADX WARNING: type inference failed for: r22v209 */
    /* JADX WARNING: type inference failed for: r22v210 */
    /* JADX WARNING: type inference failed for: r22v211 */
    /* JADX WARNING: type inference failed for: r22v212 */
    /* JADX WARNING: type inference failed for: r22v213 */
    /* JADX WARNING: type inference failed for: r22v214 */
    /* JADX WARNING: type inference failed for: r22v215 */
    /* JADX WARNING: type inference failed for: r22v216 */
    /* JADX WARNING: type inference failed for: r22v217 */
    /* JADX WARNING: type inference failed for: r22v218 */
    /* JADX WARNING: type inference failed for: r22v219 */
    /* JADX WARNING: type inference failed for: r22v220 */
    /* JADX WARNING: type inference failed for: r22v221 */
    /* JADX WARNING: type inference failed for: r22v222 */
    /* JADX WARNING: type inference failed for: r22v223 */
    /* JADX WARNING: type inference failed for: r22v224 */
    /* JADX WARNING: type inference failed for: r22v225 */
    /* JADX WARNING: type inference failed for: r22v226 */
    /* JADX WARNING: type inference failed for: r22v227 */
    /* JADX WARNING: type inference failed for: r22v228 */
    /* JADX WARNING: type inference failed for: r22v229 */
    /* JADX WARNING: type inference failed for: r22v230 */
    /* JADX WARNING: type inference failed for: r22v231 */
    /* JADX WARNING: type inference failed for: r22v232 */
    /* JADX WARNING: type inference failed for: r22v233 */
    /* JADX WARNING: type inference failed for: r22v234 */
    /* JADX WARNING: type inference failed for: r22v235 */
    /* JADX WARNING: type inference failed for: r22v236 */
    /* JADX WARNING: type inference failed for: r22v237 */
    /* JADX WARNING: type inference failed for: r22v238 */
    /* JADX WARNING: type inference failed for: r22v239 */
    /* JADX WARNING: type inference failed for: r22v240 */
    /* JADX WARNING: type inference failed for: r22v241 */
    /* JADX WARNING: type inference failed for: r22v242 */
    /* JADX WARNING: type inference failed for: r22v243 */
    /* JADX WARNING: type inference failed for: r22v244 */
    /* JADX WARNING: type inference failed for: r22v245 */
    /* JADX WARNING: type inference failed for: r22v246 */
    /* JADX WARNING: type inference failed for: r22v247 */
    /* JADX WARNING: type inference failed for: r22v248 */
    /* JADX WARNING: type inference failed for: r22v249 */
    /* JADX WARNING: type inference failed for: r22v250 */
    /* JADX WARNING: type inference failed for: r22v251 */
    /* JADX WARNING: type inference failed for: r22v252 */
    /* JADX WARNING: type inference failed for: r22v253 */
    /* JADX WARNING: type inference failed for: r22v254 */
    /* JADX WARNING: type inference failed for: r22v255 */
    /* JADX WARNING: type inference failed for: r22v256 */
    /* JADX WARNING: type inference failed for: r22v257 */
    /* JADX WARNING: type inference failed for: r22v258 */
    /* JADX WARNING: type inference failed for: r22v259 */
    /* JADX WARNING: type inference failed for: r22v260 */
    /* JADX WARNING: type inference failed for: r22v261 */
    /* JADX WARNING: type inference failed for: r22v262 */
    /* JADX WARNING: type inference failed for: r22v263 */
    /* JADX WARNING: type inference failed for: r22v264 */
    /* JADX WARNING: type inference failed for: r22v265 */
    /* JADX WARNING: type inference failed for: r22v266 */
    /* JADX WARNING: type inference failed for: r22v267 */
    /* JADX WARNING: type inference failed for: r22v268 */
    /* JADX WARNING: type inference failed for: r22v269 */
    /* JADX WARNING: type inference failed for: r22v270 */
    /* JADX WARNING: type inference failed for: r22v271 */
    /* JADX WARNING: type inference failed for: r22v272 */
    /* JADX WARNING: type inference failed for: r22v273 */
    /* JADX WARNING: type inference failed for: r22v274 */
    /* JADX WARNING: type inference failed for: r22v275 */
    /* JADX WARNING: type inference failed for: r22v276 */
    /* JADX WARNING: type inference failed for: r22v277 */
    /* JADX WARNING: type inference failed for: r22v278 */
    /* JADX WARNING: type inference failed for: r22v279 */
    /* JADX WARNING: type inference failed for: r22v280 */
    /* JADX WARNING: type inference failed for: r5v247 */
    /* JADX WARNING: type inference failed for: r5v248 */
    /* JADX WARNING: type inference failed for: r5v249 */
    /* JADX WARNING: type inference failed for: r5v250 */
    /* JADX WARNING: type inference failed for: r5v251 */
    /* JADX WARNING: type inference failed for: r5v252 */
    /* JADX WARNING: type inference failed for: r5v253 */
    /* JADX WARNING: type inference failed for: r5v254 */
    /* JADX WARNING: type inference failed for: r5v255 */
    /* JADX WARNING: type inference failed for: r5v256 */
    /* JADX WARNING: type inference failed for: r5v257 */
    /* JADX WARNING: type inference failed for: r5v258 */
    /* JADX WARNING: type inference failed for: r5v259 */
    /* JADX WARNING: type inference failed for: r5v260 */
    /* JADX WARNING: type inference failed for: r5v261 */
    /* JADX WARNING: type inference failed for: r5v262 */
    /* JADX WARNING: type inference failed for: r5v263 */
    /* JADX WARNING: type inference failed for: r5v264 */
    /* JADX WARNING: type inference failed for: r5v265 */
    /* JADX WARNING: type inference failed for: r5v266 */
    /* JADX WARNING: type inference failed for: r5v267 */
    /* JADX WARNING: type inference failed for: r5v268 */
    /* JADX WARNING: type inference failed for: r5v269 */
    /* JADX WARNING: type inference failed for: r5v270 */
    /* JADX WARNING: type inference failed for: r5v271 */
    /* JADX WARNING: type inference failed for: r5v272 */
    /* JADX WARNING: type inference failed for: r5v273 */
    /* JADX WARNING: type inference failed for: r5v274 */
    /* JADX WARNING: type inference failed for: r5v275 */
    /* JADX WARNING: type inference failed for: r5v276 */
    /* JADX WARNING: type inference failed for: r5v277 */
    /* JADX WARNING: type inference failed for: r5v278 */
    /* JADX WARNING: type inference failed for: r5v279 */
    /* JADX WARNING: type inference failed for: r5v280 */
    /* JADX WARNING: Code restructure failed: missing block: B:270:?, code lost:
        r8.flush();
        p005cn.jpush.android.util.Logger.m1416d(TAG, "Download finished");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0574, code lost:
        if (r0 == null) goto L_0x05ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x057c, code lost:
        if (r0.length() != r24) goto L_0x05ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x0586, code lost:
        if (p005cn.jpush.android.util.CheckSumUntil.checkMd5(r26, r0) == false) goto L_0x05ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0588, code lost:
        r42.downloadInfos.remove(r38);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x0591, code lost:
        if (r44 == null) goto L_0x059d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x0593, code lost:
        r44.onDownloadSucceed(r0.getAbsolutePath(), false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x059e, code lost:
        if (r22 == 0) goto L_0x05a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:?, code lost:
        r22.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:?, code lost:
        p005cn.jpush.android.util.Logger.m1432w(TAG, "The download file is not valid, download again");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x05b8, code lost:
        if (r0.delete() != false) goto L_0x05d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x05ba, code lost:
        p005cn.jpush.android.util.Logger.m1420e(TAG, "delete file fail !!!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x05c4, code lost:
        if (r22 == 0) goto L_0x05c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:?, code lost:
        r22.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x05d2, code lost:
        if (r22 == 0) goto L_0x05d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:?, code lost:
        r22.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01d2, code lost:
        if (r0 == null) goto L_0x0209;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01da, code lost:
        if (r0.length() != r32) goto L_0x0209;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01e4, code lost:
        if (p005cn.jpush.android.util.CheckSumUntil.checkMd5(r26, r0) == false) goto L_0x0209;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01e6, code lost:
        r42.downloadInfos.remove(r38);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01ef, code lost:
        if (r44 == null) goto L_0x01fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01f1, code lost:
        r44.onDownloadSucceed(r0.getAbsolutePath(), false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01fc, code lost:
        if (r22 == 0) goto L_0x0201;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r22.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        p005cn.jpush.android.util.Logger.m1432w(TAG, "The download file is not valid, download again");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0216, code lost:
        if (r0.delete() != false) goto L_0x022f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0218, code lost:
        p005cn.jpush.android.util.Logger.m1420e(TAG, "delete file fail !!!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0222, code lost:
        if (r22 == 0) goto L_0x0227;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r22.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0230, code lost:
        if (r22 == 0) goto L_0x0235;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r22.close();
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r22v1
      assigns: []
      uses: []
      mth insns count: 1343
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x02f3 A[SYNTHETIC, Splitter:B:145:0x02f3] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0310 A[SYNTHETIC, Splitter:B:155:0x0310] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x032d A[SYNTHETIC, Splitter:B:165:0x032d] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x034a A[SYNTHETIC, Splitter:B:175:0x034a] */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x035c A[SYNTHETIC, Splitter:B:181:0x035c] */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x050a A[SYNTHETIC, Splitter:B:249:0x050a] */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x055d A[SYNTHETIC, Splitter:B:266:0x055d] */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x06fb A[SYNTHETIC, Splitter:B:370:0x06fb] */
    /* JADX WARNING: Removed duplicated region for block: B:380:0x0718 A[SYNTHETIC, Splitter:B:380:0x0718] */
    /* JADX WARNING: Removed duplicated region for block: B:390:0x0735 A[SYNTHETIC, Splitter:B:390:0x0735] */
    /* JADX WARNING: Removed duplicated region for block: B:400:0x0752 A[SYNTHETIC, Splitter:B:400:0x0752] */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0764 A[SYNTHETIC, Splitter:B:406:0x0764] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0150 A[SYNTHETIC, Splitter:B:45:0x0150] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01c5 A[SYNTHETIC, Splitter:B:63:0x01c5] */
    /* JADX WARNING: Unknown variable types count: 68 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int download(android.content.Context r43, p005cn.jpush.android.service.DownloadControl.DownloadListener r44, p005cn.jpush.android.data.Entity r45) {
        /*
            r42 = this;
            java.lang.String r38 = r45.getDownloadUrl()
            java.lang.String r26 = r45.getMd5()
            java.lang.String r34 = ""
            java.lang.String r19 = ""
            boolean r4 = r45.isMsgTypeAAndUpdate()
            if (r4 == 0) goto L_0x015a
            java.lang.String r34 = p005cn.jpush.android.util.DirectoryUtils.getDirectoryAppPath(r43)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r0 = r45
            java.lang.String r10 = r0.messageId
            java.lang.StringBuilder r4 = r4.append(r10)
            java.lang.String r10 = ".apk"
            java.lang.StringBuilder r4 = r4.append(r10)
            java.lang.String r19 = r4.toString()
        L_0x0030:
            boolean r4 = android.text.TextUtils.isEmpty(r38)
            if (r4 != 0) goto L_0x076d
            boolean r4 = android.text.TextUtils.isEmpty(r34)
            if (r4 != 0) goto L_0x076d
            boolean r4 = android.text.TextUtils.isEmpty(r19)
            if (r4 != 0) goto L_0x076d
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "action:download - url:"
            java.lang.StringBuilder r10 = r10.append(r11)
            r0 = r38
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r11 = ", saveFilePath:"
            java.lang.StringBuilder r10 = r10.append(r11)
            r0 = r34
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r11 = ", fileName:"
            java.lang.StringBuilder r10 = r10.append(r11)
            r0 = r19
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            p005cn.jpush.android.util.Logger.m1424i(r4, r10)
            r0 = r42
            r1 = r34
            r0.createSavePath(r1)
            r0 = r42
            android.os.Bundle r4 = r0.downloadInfos
            r10 = -1
            r0 = r38
            long r24 = r4.getLong(r0, r10)
            r20 = 0
            r32 = 0
            r5 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r9 = 0
            r22 = 0
            r10 = 0
            int r4 = (r24 > r10 ? 1 : (r24 == r10 ? 0 : -1))
            if (r4 > 0) goto L_0x0427
            java.io.File r18 = new java.io.File
            r0 = r18
            r1 = r34
            r2 = r19
            r0.<init>(r1, r2)
            boolean r4 = r18.exists()
            if (r4 == 0) goto L_0x00b7
            long r10 = r18.length()
            r40 = 0
            int r4 = (r10 > r40 ? 1 : (r10 == r40 ? 0 : -1))
            if (r4 > 0) goto L_0x0365
        L_0x00b7:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "Download first."
            p005cn.jpush.android.util.Logger.m1428v(r4, r10)
            r10 = -1
            r0 = r42
            r1 = r38
            java.net.HttpURLConnection r9 = r0.getHttpURLConnection(r1, r10)
            java.io.InputStream r22 = r9.getInputStream()     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            if (r22 == 0) goto L_0x02c9
            int r35 = r9.getResponseCode()     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r4 = 200(0xc8, float:2.8E-43)
            r0 = r35
            if (r0 != r4) goto L_0x026d
            r23 = 1
            if (r23 == 0) goto L_0x0255
            r0 = r42
            long r32 = r0.getFileLengthFromHttp(r9)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r0 = r42
            android.os.Bundle r4 = r0.downloadInfos     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r0 = r38
            r1 = r32
            r4.putLong(r0, r1)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r0 = r42
            r1 = r32
            int r4 = r0.getBestRetryTimes(r1)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r0 = r45
            r0._downloadRetryTimes = r4     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r5 = r22
            if (r5 == 0) goto L_0x023d
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r6.<init>(r5)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r18.delete()     // Catch:{ NumberFormatException -> 0x08b7, ProtocolException -> 0x08a8, IllegalStateException -> 0x0896, FileNotFoundException -> 0x088a, IOException -> 0x087e, JPushException -> 0x0872, all -> 0x0863 }
            r18.createNewFile()     // Catch:{ NumberFormatException -> 0x08b7, ProtocolException -> 0x08a8, IllegalStateException -> 0x0896, FileNotFoundException -> 0x088a, IOException -> 0x087e, JPushException -> 0x0872, all -> 0x0863 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ NumberFormatException -> 0x08b7, ProtocolException -> 0x08a8, IllegalStateException -> 0x0896, FileNotFoundException -> 0x088a, IOException -> 0x087e, JPushException -> 0x0872, all -> 0x0863 }
            r0 = r18
            r7.<init>(r0)     // Catch:{ NumberFormatException -> 0x08b7, ProtocolException -> 0x08a8, IllegalStateException -> 0x0896, FileNotFoundException -> 0x088a, IOException -> 0x087e, JPushException -> 0x0872, all -> 0x0863 }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ NumberFormatException -> 0x08bc, ProtocolException -> 0x08ad, IllegalStateException -> 0x089b, FileNotFoundException -> 0x088f, IOException -> 0x0883, JPushException -> 0x0877, all -> 0x0869 }
            r8.<init>(r7)     // Catch:{ NumberFormatException -> 0x08bc, ProtocolException -> 0x08ad, IllegalStateException -> 0x089b, FileNotFoundException -> 0x088f, IOException -> 0x0883, JPushException -> 0x0877, all -> 0x0869 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r4]     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            r16 = r0
            r27 = 0
        L_0x011e:
            r0 = r16
            int r27 = r6.read(r0)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            r4 = -1
            r0 = r27
            if (r0 == r4) goto L_0x01cf
            r0 = r42
            boolean r4 = r0.isNeedStopDownload     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            if (r4 == 0) goto L_0x019b
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "stop download by user, throw Exception."
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            cn.jpush.android.JPushException r4 = new cn.jpush.android.JPushException     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            java.lang.String r10 = "stop download by user."
            r4.<init>(r10)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            throw r4     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
        L_0x0141:
            r17 = move-exception
        L_0x0142:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "NumberFormatException, get content length from http fail."
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x086e }
            r10 = -2
            if (r22 == 0) goto L_0x0153
            r22.close()     // Catch:{ IOException -> 0x07be }
        L_0x0153:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
        L_0x0159:
            return r4
        L_0x015a:
            boolean r4 = r45.isMsgTypeVideo()
            if (r4 == 0) goto L_0x016c
            java.lang.String r34 = p005cn.jpush.android.util.DirectoryUtils.getDirectoryVideoPath(r43)
            r0 = r45
            java.lang.String r0 = r0.messageId
            r19 = r0
            goto L_0x0030
        L_0x016c:
            boolean r4 = r45.isRichPush()
            if (r4 == 0) goto L_0x0030
            r0 = r45
            java.lang.String r4 = r0.messageId
            r0 = r43
            java.lang.String r34 = p005cn.jpush.android.util.DirectoryUtils.getDirectoryRichPush(r0, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r0 = r45
            java.lang.String r10 = r0.messageId
            java.lang.StringBuilder r4 = r4.append(r10)
            java.lang.String r10 = r45.getDownloadUrl()
            java.lang.String r10 = p005cn.jpush.android.util.FileUtil.getExtName(r10)
            java.lang.StringBuilder r4 = r4.append(r10)
            java.lang.String r19 = r4.toString()
            goto L_0x0030
        L_0x019b:
            r4 = 0
            r0 = r16
            r1 = r27
            r8.write(r0, r4, r1)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            r0 = r27
            long r10 = (long) r0     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            long r20 = r20 + r10
            r0 = r20
            r2 = r42
            r2.hadDownLength = r0     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            r0 = r32
            r2 = r42
            r2.totalDownLength = r0     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            goto L_0x011e
        L_0x01b6:
            r17 = move-exception
        L_0x01b7:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = ""
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x086e }
            r10 = -2
            if (r22 == 0) goto L_0x01c8
            r22.close()     // Catch:{ IOException -> 0x07c1 }
        L_0x01c8:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x01cf:
            r8.flush()     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            if (r18 == 0) goto L_0x0209
            long r10 = r18.length()     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            int r4 = (r10 > r32 ? 1 : (r10 == r32 ? 0 : -1))
            if (r4 != 0) goto L_0x0209
            r0 = r26
            r1 = r18
            boolean r4 = p005cn.jpush.android.util.CheckSumUntil.checkMd5(r0, r1)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            if (r4 == 0) goto L_0x0209
            r0 = r42
            android.os.Bundle r4 = r0.downloadInfos     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            r0 = r38
            r4.remove(r0)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            if (r44 == 0) goto L_0x01fb
            java.lang.String r4 = r18.getAbsolutePath()     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            r10 = 0
            r0 = r44
            r0.onDownloadSucceed(r4, r10)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
        L_0x01fb:
            r10 = 1
            if (r22 == 0) goto L_0x0201
            r22.close()     // Catch:{ IOException -> 0x07a6 }
        L_0x0201:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0209:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "The download file is not valid, download again"
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            boolean r4 = r18.delete()     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            if (r4 != 0) goto L_0x022f
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "delete file fail !!!"
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x0141, ProtocolException -> 0x01b6, IllegalStateException -> 0x089f, FileNotFoundException -> 0x0893, IOException -> 0x0887, JPushException -> 0x087b }
            r10 = -2
            if (r22 == 0) goto L_0x0227
            r22.close()     // Catch:{ IOException -> 0x07a9 }
        L_0x0227:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x022f:
            r10 = 2
            if (r22 == 0) goto L_0x0235
            r22.close()     // Catch:{ IOException -> 0x07ac }
        L_0x0235:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x023d:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "NULL response stream."
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r4 = 0
            if (r22 == 0) goto L_0x024c
            r22.close()     // Catch:{ IOException -> 0x07af }
        L_0x024c:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x0255:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "data mode from server is not stream."
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r4 = -2
            if (r22 == 0) goto L_0x0264
            r22.close()     // Catch:{ IOException -> 0x07b2 }
        L_0x0264:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x026d:
            r4 = 404(0x194, float:5.66E-43)
            r0 = r35
            if (r0 != r4) goto L_0x029e
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r10.<init>()     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            java.lang.String r11 = "The resource does not exist - "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r0 = r38
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            java.lang.String r10 = r10.toString()     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            p005cn.jpush.android.util.Logger.m1416d(r4, r10)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r4 = -3
            if (r22 == 0) goto L_0x0295
            r22.close()     // Catch:{ IOException -> 0x07b5 }
        L_0x0295:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x029e:
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r10.<init>()     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            java.lang.String r11 = "network connect status code unexpected - "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r0 = r35
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            java.lang.String r10 = r10.toString()     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r4 = -2
            if (r22 == 0) goto L_0x02c0
            r22.close()     // Catch:{ IOException -> 0x07b8 }
        L_0x02c0:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x02c9:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "NULL response"
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x08b1, ProtocolException -> 0x08a2, IllegalStateException -> 0x02e1, FileNotFoundException -> 0x02fe, IOException -> 0x031b, JPushException -> 0x0338, all -> 0x0355 }
            r4 = 0
            if (r22 == 0) goto L_0x02d8
            r22.close()     // Catch:{ IOException -> 0x07bb }
        L_0x02d8:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x02e1:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x02e5:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = ""
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x086e }
            r10 = -2
            if (r22 == 0) goto L_0x02f6
            r22.close()     // Catch:{ IOException -> 0x07c4 }
        L_0x02f6:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x02fe:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x0302:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = ""
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x086e }
            r10 = -2
            if (r22 == 0) goto L_0x0313
            r22.close()     // Catch:{ IOException -> 0x07c7 }
        L_0x0313:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x031b:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x031f:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = ""
            r0 = r17
            p005cn.jpush.android.util.Logger.m1417d(r4, r10, r0)     // Catch:{ all -> 0x086e }
            r10 = -1
            if (r22 == 0) goto L_0x0330
            r22.close()     // Catch:{ IOException -> 0x07ca }
        L_0x0330:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0338:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x033c:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "JPushException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1433w(r4, r10, r0)     // Catch:{ all -> 0x086e }
            r10 = -2
            if (r22 == 0) goto L_0x034d
            r22.close()     // Catch:{ IOException -> 0x07cd }
        L_0x034d:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0355:
            r4 = move-exception
            r10 = r4
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x035a:
            if (r22 == 0) goto L_0x035f
            r22.close()     // Catch:{ IOException -> 0x07d0 }
        L_0x035f:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            throw r10
        L_0x0365:
            long r10 = r18.length()
            r40 = 0
            int r4 = (r10 > r40 ? 1 : (r10 == r40 ? 0 : -1))
            if (r4 <= 0) goto L_0x041b
            r10 = -1
            r0 = r42
            r1 = r38
            java.net.HttpURLConnection r9 = r0.getHttpURLConnection(r1, r10)
            r9.getInputStream()     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            r0 = r42
            long r30 = r0.getFileLengthFromHttp(r9)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            long r10 = r18.length()     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            int r4 = (r10 > r30 ? 1 : (r10 == r30 ? 0 : -1))
            if (r4 != 0) goto L_0x03ac
            r0 = r26
            r1 = r18
            boolean r4 = p005cn.jpush.android.util.CheckSumUntil.checkMd5(r0, r1)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            if (r4 == 0) goto L_0x03ac
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "Existed file size is same with target. Use it directly."
            p005cn.jpush.android.util.Logger.m1416d(r4, r10)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            if (r44 == 0) goto L_0x03a9
            java.lang.String r4 = r18.getAbsolutePath()     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            r10 = 1
            r0 = r44
            r0.onDownloadSucceed(r4, r10)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
        L_0x03a9:
            r4 = 1
            goto L_0x0159
        L_0x03ac:
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            r10.<init>()     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            java.lang.String r11 = "Exsit file length:"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            long r40 = r18.length()     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            r0 = r40
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            java.lang.String r11 = ", fileTotalLength:"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            r0 = r30
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            java.lang.String r10 = r10.toString()     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            p005cn.jpush.android.util.Logger.m1424i(r4, r10)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            boolean r4 = r18.delete()     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            if (r4 != 0) goto L_0x03eb
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "delete file fail !!!"
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ ProtocolException -> 0x03ee, IOException -> 0x03fd, JPushException -> 0x040c }
            r4 = -2
            goto L_0x0159
        L_0x03eb:
            r4 = 2
            goto L_0x0159
        L_0x03ee:
            r17 = move-exception
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "ClientProtocolException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)
            r4 = -2
            goto L_0x0159
        L_0x03fd:
            r17 = move-exception
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "IOException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1417d(r4, r10, r0)
            r4 = -1
            goto L_0x0159
        L_0x040c:
            r17 = move-exception
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "JPushException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1433w(r4, r10, r0)
            r4 = -2
            goto L_0x0159
        L_0x041b:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "unexpected !!"
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)
            r4 = -2
            goto L_0x0159
        L_0x0427:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "Had record, keep download."
            p005cn.jpush.android.util.Logger.m1424i(r4, r10)
            r36 = 0
            java.io.File r18 = new java.io.File
            r0 = r18
            r1 = r34
            r2 = r19
            r0.<init>(r1, r2)
            boolean r4 = r18.exists()
            if (r4 == 0) goto L_0x0515
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "File exsit, getting the file length."
            p005cn.jpush.android.util.Logger.m1428v(r4, r10)
            long r36 = r18.length()
            r20 = r36
        L_0x0452:
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "startPostion: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r0 = r36
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            p005cn.jpush.android.util.Logger.m1424i(r4, r10)
            r0 = r45
            int r4 = r0._downloadRetryTimes
            r10 = -1
            if (r4 != r10) goto L_0x048a
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "Reset download retry times because it ever failed."
            p005cn.jpush.android.util.Logger.m1416d(r4, r10)
            r0 = r42
            r1 = r24
            int r4 = r0.getBestRetryTimes(r1)
            r0 = r45
            r0._downloadRetryTimes = r4
        L_0x048a:
            r0 = r42
            r1 = r38
            r2 = r36
            java.net.HttpURLConnection r9 = r0.getHttpURLConnection(r1, r2)
            java.io.InputStream r22 = r9.getInputStream()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            if (r22 == 0) goto L_0x06d1
            int r35 = r9.getResponseCode()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r4 = 200(0xc8, float:2.8E-43)
            r0 = r35
            if (r0 == r4) goto L_0x04aa
            r4 = 206(0xce, float:2.89E-43)
            r0 = r35
            if (r0 != r4) goto L_0x063f
        L_0x04aa:
            r23 = 1
            if (r23 == 0) goto L_0x0627
            r0 = r42
            long r28 = r0.getFileLengthFromHttp(r9)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            long r10 = r28 + r20
            int r4 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            if (r4 != 0) goto L_0x05f7
            r5 = r22
            if (r5 == 0) goto L_0x05df
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r6.<init>(r5)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ NumberFormatException -> 0x085a, ProtocolException -> 0x084b, IllegalStateException -> 0x0839, FileNotFoundException -> 0x082d, IOException -> 0x0821, JPushException -> 0x0815, all -> 0x0806 }
            r4 = 1
            r0 = r18
            r7.<init>(r0, r4)     // Catch:{ NumberFormatException -> 0x085a, ProtocolException -> 0x084b, IllegalStateException -> 0x0839, FileNotFoundException -> 0x082d, IOException -> 0x0821, JPushException -> 0x0815, all -> 0x0806 }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ NumberFormatException -> 0x085f, ProtocolException -> 0x0850, IllegalStateException -> 0x083e, FileNotFoundException -> 0x0832, IOException -> 0x0826, JPushException -> 0x081a, all -> 0x080c }
            r8.<init>(r7)     // Catch:{ NumberFormatException -> 0x085f, ProtocolException -> 0x0850, IllegalStateException -> 0x083e, FileNotFoundException -> 0x0832, IOException -> 0x0826, JPushException -> 0x081a, all -> 0x080c }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r4]     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            r16 = r0
            r27 = 0
        L_0x04d8:
            r0 = r16
            int r27 = r6.read(r0)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            r4 = -1
            r0 = r27
            if (r0 == r4) goto L_0x0568
            r0 = r42
            boolean r4 = r0.isNeedStopDownload     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            if (r4 == 0) goto L_0x0534
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "stop download by user, throw JPushException."
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            cn.jpush.android.JPushException r4 = new cn.jpush.android.JPushException     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            java.lang.String r10 = "stop download by user."
            r4.<init>(r10)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            throw r4     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
        L_0x04fb:
            r17 = move-exception
        L_0x04fc:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "NumberFormatException, get content length from http fail."
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x0811 }
            r10 = -2
            if (r22 == 0) goto L_0x050d
            r22.close()     // Catch:{ IOException -> 0x07f1 }
        L_0x050d:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0515:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "File had been delete, start from 0."
            p005cn.jpush.android.util.Logger.m1428v(r4, r10)
            r36 = 0
            r18.createNewFile()     // Catch:{ IOException -> 0x0525 }
            goto L_0x0452
        L_0x0525:
            r17 = move-exception
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "createNewFile fail."
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)
            r4 = -2
            goto L_0x0159
        L_0x0534:
            r4 = 0
            r0 = r16
            r1 = r27
            r8.write(r0, r4, r1)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            r0 = r27
            long r10 = (long) r0     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            long r20 = r20 + r10
            r0 = r20
            r2 = r42
            r2.hadDownLength = r0     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            r0 = r24
            r2 = r42
            r2.totalDownLength = r0     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            goto L_0x04d8
        L_0x054e:
            r17 = move-exception
        L_0x054f:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "ProtocolException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x0811 }
            r10 = -2
            if (r22 == 0) goto L_0x0560
            r22.close()     // Catch:{ IOException -> 0x07f4 }
        L_0x0560:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0568:
            r8.flush()     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "Download finished"
            p005cn.jpush.android.util.Logger.m1416d(r4, r10)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            if (r18 == 0) goto L_0x05ab
            long r10 = r18.length()     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            int r4 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            if (r4 != 0) goto L_0x05ab
            r0 = r26
            r1 = r18
            boolean r4 = p005cn.jpush.android.util.CheckSumUntil.checkMd5(r0, r1)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            if (r4 == 0) goto L_0x05ab
            r0 = r42
            android.os.Bundle r4 = r0.downloadInfos     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            r0 = r38
            r4.remove(r0)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            if (r44 == 0) goto L_0x059d
            java.lang.String r4 = r18.getAbsolutePath()     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            r10 = 0
            r0 = r44
            r0.onDownloadSucceed(r4, r10)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
        L_0x059d:
            r10 = 1
            if (r22 == 0) goto L_0x05a3
            r22.close()     // Catch:{ IOException -> 0x07d3 }
        L_0x05a3:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x05ab:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "The download file is not valid, download again"
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            boolean r4 = r18.delete()     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            if (r4 != 0) goto L_0x05d1
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "delete file fail !!!"
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x04fb, ProtocolException -> 0x054e, IllegalStateException -> 0x0842, FileNotFoundException -> 0x0836, IOException -> 0x082a, JPushException -> 0x081e }
            r10 = -2
            if (r22 == 0) goto L_0x05c9
            r22.close()     // Catch:{ IOException -> 0x07d6 }
        L_0x05c9:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x05d1:
            r10 = 2
            if (r22 == 0) goto L_0x05d7
            r22.close()     // Catch:{ IOException -> 0x07d9 }
        L_0x05d7:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x05df:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "NULL response stream"
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r4 = 0
            if (r22 == 0) goto L_0x05ee
            r22.close()     // Catch:{ IOException -> 0x07dc }
        L_0x05ee:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x05f7:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "File length between last and now were different."
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r0 = r42
            android.os.Bundle r4 = r0.downloadInfos     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r0 = r38
            r4.remove(r0)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            boolean r4 = r18.delete()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            if (r4 != 0) goto L_0x0618
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "delete file fail !!!"
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
        L_0x0618:
            r4 = 0
            if (r22 == 0) goto L_0x061e
            r22.close()     // Catch:{ IOException -> 0x07df }
        L_0x061e:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x0627:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "data mode from server is not stream."
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r4 = -2
            if (r22 == 0) goto L_0x0636
            r22.close()     // Catch:{ IOException -> 0x07e2 }
        L_0x0636:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x063f:
            r4 = 416(0x1a0, float:5.83E-43)
            r0 = r35
            if (r0 != r4) goto L_0x0675
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "server file length change at the same url, delete all info and download again at 0."
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r0 = r42
            android.os.Bundle r4 = r0.downloadInfos     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r0 = r38
            r4.remove(r0)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            boolean r4 = r18.delete()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            if (r4 != 0) goto L_0x0666
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "delete file fail !!!"
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
        L_0x0666:
            r4 = 0
            if (r22 == 0) goto L_0x066c
            r22.close()     // Catch:{ IOException -> 0x07e5 }
        L_0x066c:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x0675:
            r4 = 404(0x194, float:5.66E-43)
            r0 = r35
            if (r0 != r4) goto L_0x06a6
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r10.<init>()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            java.lang.String r11 = "The resource does not exist - "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r0 = r38
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            java.lang.String r10 = r10.toString()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            p005cn.jpush.android.util.Logger.m1416d(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r4 = -3
            if (r22 == 0) goto L_0x069d
            r22.close()     // Catch:{ IOException -> 0x07e8 }
        L_0x069d:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x06a6:
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r10.<init>()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            java.lang.String r11 = "network connect status code unexpected - "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r0 = r35
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            java.lang.String r10 = r10.toString()     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r4 = -2
            if (r22 == 0) goto L_0x06c8
            r22.close()     // Catch:{ IOException -> 0x07eb }
        L_0x06c8:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x06d1:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "NULL response"
            p005cn.jpush.android.util.Logger.m1432w(r4, r10)     // Catch:{ NumberFormatException -> 0x0854, ProtocolException -> 0x0845, IllegalStateException -> 0x06e9, FileNotFoundException -> 0x0706, IOException -> 0x0723, JPushException -> 0x0740, all -> 0x075d }
            r4 = 0
            if (r22 == 0) goto L_0x06e0
            r22.close()     // Catch:{ IOException -> 0x07ee }
        L_0x06e0:
            r10 = r42
            r11 = r5
            r15 = r9
            r10.closeResource(r11, r12, r13, r14, r15)
            goto L_0x0159
        L_0x06e9:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x06ed:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "ClientProtocolException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x0811 }
            r10 = -2
            if (r22 == 0) goto L_0x06fe
            r22.close()     // Catch:{ IOException -> 0x07f7 }
        L_0x06fe:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0706:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x070a:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "FileNotFoundException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1421e(r4, r10, r0)     // Catch:{ all -> 0x0811 }
            r10 = -2
            if (r22 == 0) goto L_0x071b
            r22.close()     // Catch:{ IOException -> 0x07fa }
        L_0x071b:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0723:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x0727:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "IOException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1417d(r4, r10, r0)     // Catch:{ all -> 0x0811 }
            r10 = -1
            if (r22 == 0) goto L_0x0738
            r22.close()     // Catch:{ IOException -> 0x07fd }
        L_0x0738:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x0740:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x0744:
            java.lang.String r4 = "DownloadControl"
            java.lang.String r10 = "JPushException"
            r0 = r17
            p005cn.jpush.android.util.Logger.m1433w(r4, r10, r0)     // Catch:{ all -> 0x0811 }
            r10 = -2
            if (r22 == 0) goto L_0x0755
            r22.close()     // Catch:{ IOException -> 0x0800 }
        L_0x0755:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            r4 = r10
            goto L_0x0159
        L_0x075d:
            r4 = move-exception
            r10 = r4
            r8 = r14
            r7 = r13
            r6 = r12
        L_0x0762:
            if (r22 == 0) goto L_0x0767
            r22.close()     // Catch:{ IOException -> 0x0803 }
        L_0x0767:
            r4 = r42
            r4.closeResource(r5, r6, r7, r8, r9)
            throw r10
        L_0x076d:
            java.lang.String r4 = "DownloadControl"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Param error !! url:"
            java.lang.StringBuilder r10 = r10.append(r11)
            r0 = r38
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r11 = " savefilePath:"
            java.lang.StringBuilder r10 = r10.append(r11)
            r0 = r34
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r11 = " fileName:"
            java.lang.StringBuilder r10 = r10.append(r11)
            r0 = r19
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            p005cn.jpush.android.util.Logger.m1420e(r4, r10)
            r4 = -2
            goto L_0x0159
        L_0x07a6:
            r4 = move-exception
            goto L_0x0201
        L_0x07a9:
            r4 = move-exception
            goto L_0x0227
        L_0x07ac:
            r4 = move-exception
            goto L_0x0235
        L_0x07af:
            r10 = move-exception
            goto L_0x024c
        L_0x07b2:
            r10 = move-exception
            goto L_0x0264
        L_0x07b5:
            r10 = move-exception
            goto L_0x0295
        L_0x07b8:
            r10 = move-exception
            goto L_0x02c0
        L_0x07bb:
            r10 = move-exception
            goto L_0x02d8
        L_0x07be:
            r4 = move-exception
            goto L_0x0153
        L_0x07c1:
            r4 = move-exception
            goto L_0x01c8
        L_0x07c4:
            r4 = move-exception
            goto L_0x02f6
        L_0x07c7:
            r4 = move-exception
            goto L_0x0313
        L_0x07ca:
            r4 = move-exception
            goto L_0x0330
        L_0x07cd:
            r4 = move-exception
            goto L_0x034d
        L_0x07d0:
            r4 = move-exception
            goto L_0x035f
        L_0x07d3:
            r4 = move-exception
            goto L_0x05a3
        L_0x07d6:
            r4 = move-exception
            goto L_0x05c9
        L_0x07d9:
            r4 = move-exception
            goto L_0x05d7
        L_0x07dc:
            r10 = move-exception
            goto L_0x05ee
        L_0x07df:
            r10 = move-exception
            goto L_0x061e
        L_0x07e2:
            r10 = move-exception
            goto L_0x0636
        L_0x07e5:
            r10 = move-exception
            goto L_0x066c
        L_0x07e8:
            r10 = move-exception
            goto L_0x069d
        L_0x07eb:
            r10 = move-exception
            goto L_0x06c8
        L_0x07ee:
            r10 = move-exception
            goto L_0x06e0
        L_0x07f1:
            r4 = move-exception
            goto L_0x050d
        L_0x07f4:
            r4 = move-exception
            goto L_0x0560
        L_0x07f7:
            r4 = move-exception
            goto L_0x06fe
        L_0x07fa:
            r4 = move-exception
            goto L_0x071b
        L_0x07fd:
            r4 = move-exception
            goto L_0x0738
        L_0x0800:
            r4 = move-exception
            goto L_0x0755
        L_0x0803:
            r4 = move-exception
            goto L_0x0767
        L_0x0806:
            r4 = move-exception
            r10 = r4
            r8 = r14
            r7 = r13
            goto L_0x0762
        L_0x080c:
            r4 = move-exception
            r10 = r4
            r8 = r14
            goto L_0x0762
        L_0x0811:
            r4 = move-exception
            r10 = r4
            goto L_0x0762
        L_0x0815:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x0744
        L_0x081a:
            r17 = move-exception
            r8 = r14
            goto L_0x0744
        L_0x081e:
            r17 = move-exception
            goto L_0x0744
        L_0x0821:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x0727
        L_0x0826:
            r17 = move-exception
            r8 = r14
            goto L_0x0727
        L_0x082a:
            r17 = move-exception
            goto L_0x0727
        L_0x082d:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x070a
        L_0x0832:
            r17 = move-exception
            r8 = r14
            goto L_0x070a
        L_0x0836:
            r17 = move-exception
            goto L_0x070a
        L_0x0839:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x06ed
        L_0x083e:
            r17 = move-exception
            r8 = r14
            goto L_0x06ed
        L_0x0842:
            r17 = move-exception
            goto L_0x06ed
        L_0x0845:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
            goto L_0x054f
        L_0x084b:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x054f
        L_0x0850:
            r17 = move-exception
            r8 = r14
            goto L_0x054f
        L_0x0854:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
            goto L_0x04fc
        L_0x085a:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x04fc
        L_0x085f:
            r17 = move-exception
            r8 = r14
            goto L_0x04fc
        L_0x0863:
            r4 = move-exception
            r10 = r4
            r8 = r14
            r7 = r13
            goto L_0x035a
        L_0x0869:
            r4 = move-exception
            r10 = r4
            r8 = r14
            goto L_0x035a
        L_0x086e:
            r4 = move-exception
            r10 = r4
            goto L_0x035a
        L_0x0872:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x033c
        L_0x0877:
            r17 = move-exception
            r8 = r14
            goto L_0x033c
        L_0x087b:
            r17 = move-exception
            goto L_0x033c
        L_0x087e:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x031f
        L_0x0883:
            r17 = move-exception
            r8 = r14
            goto L_0x031f
        L_0x0887:
            r17 = move-exception
            goto L_0x031f
        L_0x088a:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x0302
        L_0x088f:
            r17 = move-exception
            r8 = r14
            goto L_0x0302
        L_0x0893:
            r17 = move-exception
            goto L_0x0302
        L_0x0896:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x02e5
        L_0x089b:
            r17 = move-exception
            r8 = r14
            goto L_0x02e5
        L_0x089f:
            r17 = move-exception
            goto L_0x02e5
        L_0x08a2:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
            goto L_0x01b7
        L_0x08a8:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x01b7
        L_0x08ad:
            r17 = move-exception
            r8 = r14
            goto L_0x01b7
        L_0x08b1:
            r17 = move-exception
            r8 = r14
            r7 = r13
            r6 = r12
            goto L_0x0142
        L_0x08b7:
            r17 = move-exception
            r8 = r14
            r7 = r13
            goto L_0x0142
        L_0x08bc:
            r17 = move-exception
            r8 = r14
            goto L_0x0142
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.service.DownloadControl.download(android.content.Context, cn.jpush.android.service.DownloadControl$DownloadListener, cn.jpush.android.data.Entity):int");
    }

    private void closeResource(InputStream is, BufferedInputStream bis, FileOutputStream fos, BufferedOutputStream bos, HttpURLConnection httpURLConnection) {
        if (bos != null) {
            try {
                bos.close();
            } catch (IOException e) {
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e2) {
            }
        }
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e3) {
            }
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e4) {
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private HttpURLConnection getHttpURLConnection(String url, long startPosition) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestProperty("Connection", "Close");
            if (startPosition >= 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + startPosition + "-");
            }
            HttpHelper.addHttpURLConnectRequestProperty(httpURLConnection, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }

    private void createSavePath(String saveFilePath) {
        File file = new File(saveFilePath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }

    private long getFileLengthFromHttp(HttpURLConnection httpURLConnection) throws JPushException {
        long nowFileTotalLength = Long.valueOf(httpURLConnection.getHeaderField("Content-Length")).longValue();
        if (nowFileTotalLength > 0) {
            return nowFileTotalLength;
        }
        throw new JPushException("get the file total length from http is 0.");
    }

    private int getBestRetryTimes(long size) {
        int times;
        long msize = size / 10485760;
        if (msize < 1) {
            times = 10;
        } else if (msize > 5) {
            times = 50;
        } else {
            times = (int) (10 * msize);
        }
        return (int) (((double) times) * DOWNLAOD_TIMES_RATE);
    }

    public static boolean isRealFailed(int failType) {
        return 2 == failType || 3 == failType;
    }
}
