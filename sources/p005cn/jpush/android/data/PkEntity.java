package p005cn.jpush.android.data;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.CollectionUtil;
import p005cn.jpush.android.util.DirectoryUtils;
import p005cn.jpush.android.util.FileUtil;
import p005cn.jpush.android.util.HttpHelper;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.data.PkEntity */
public class PkEntity extends Entity {
    public static final int DOWNLAOD_NETWORK_ALL = 0;
    public static final int DOWNLAOD_NETWORK_WIFI = 1;
    public static final String KEY_A_AUTO_INSTALL = "apk_auto_install";
    public static final String KEY_A_FINISHED_SHOW_NOTI = "apk_show_finished_noti";
    public static final String KEY_A_MD5 = "apk_md5";
    public static final String KEY_A_SHOW_CONTENT = "apk_econ";
    public static final String KEY_A_SHOW_MODE = "apk_show";
    public static final String KEY_DOWNLAOD_URL = "apk_url";
    public static final String KEY_MAIN_ACTIVITY = "auto_rc";
    public static final String KEY_NEED_AUTO_RUNUP = "auto_r";
    public static final String KEY_NEED_S_DOWNLOAD = "auto_m";
    public static final String KEY_NEED_UPDATE_IF_INSTALLED = "apk_u";
    public static final String KEY_PACKAGE_NAME = "apk_n";
    public static final String KEY_S_DOWNLOAD_NETWORK_OPTION = "auto_n";
    public static final String MAIN_ACTIVITY_CONST_NOT_AUTO_RUN = "not_autorun";
    public static final int SHOW_MODE_HTML = 1;
    public static final int SHOW_MODE_VIEW = 0;
    private static final String TAG = "PkEntity";
    private static final long serialVersionUID = -3631353784569699030L;
    public String _aSavedPath;
    public boolean _isAPreloadSucceed;
    public String aDownloadUrl;
    public String aMainActivity;
    public String aMd5;
    public String aPackageName;
    public AShowInfo aShowInfo;
    public int aShowMode;
    public boolean needAutoInstall;
    public boolean needAutoRunup;
    public boolean needSDownload;
    public boolean needShowFinishedNotification;
    public boolean needUpdateIfInstalled;
    public int sDownloadNetworkOption;

    /* renamed from: cn.jpush.android.data.PkEntity$AShowInfo */
    public class AShowInfo implements Serializable {
        public static final String KEY_A_SHOW_DESCRIPTION = "a_info";
        public static final String KEY_A_SHOW_ICON_URL = "a_icon_url";
        public static final String KEY_A_SHOW_INFO_TYPE = "a_type";
        public static final String KEY_A_SHOW_NEED_SCORE = "a_score";
        public static final String KEY_A_SHOW_PRELOAD_RESOURCE = "a_res";
        public static final String KEY_A_SHOW_RESOURCES = "a_eres";
        public static final String KEY_A_SHOW_SCREENSHOT_URL = "a_image_url";
        public static final String KEY_A_SHOW_SIZE = "a_size";
        public static final String KEY_A_SHOW_TITLE = "a_title";
        public static final String KEY_A_SHOW_VERSION = "a_ver";
        public static final String KEY_A_SHOW_WEBPAGE_GOBROSWER = "a_broswer";
        public static final String KEY_A_SHOW_WEBPAGE_URL = "a_eurl";
        private static final long serialVersionUID = 4552490194271788142L;
        public String _iconPath;
        public String _imagePath;
        public String _webPagePath;
        public String description;
        public String iconUrl;
        public String infoType;
        public boolean needPreloadResources;
        public boolean needShowScore;
        public String screenShotUrl;
        public ArrayList<String> showResourceList = new ArrayList<>();
        public String size;
        public String title;
        public String version;
        public String webPageUrl;

        public AShowInfo() {
        }

        public JSONObject getAShowInfoObject() {
            int i = 0;
            JSONObject fourth = new JSONObject();
            try {
                fourth.put(KEY_A_SHOW_TITLE, this.title);
                fourth.put(KEY_A_SHOW_ICON_URL, this.iconUrl);
                fourth.put(KEY_A_SHOW_VERSION, this.version);
                fourth.put(KEY_A_SHOW_INFO_TYPE, this.infoType);
                fourth.put(KEY_A_SHOW_NEED_SCORE, this.needShowScore ? 0 : 1);
                fourth.put(KEY_A_SHOW_SIZE, this.size);
                fourth.put(KEY_A_SHOW_DESCRIPTION, this.description);
                fourth.put(KEY_A_SHOW_SCREENSHOT_URL, this.screenShotUrl);
                fourth.put(KEY_A_SHOW_WEBPAGE_URL, this.webPageUrl);
                String str = KEY_A_SHOW_PRELOAD_RESOURCE;
                if (!this.needPreloadResources) {
                    i = 1;
                }
                fourth.put(str, i);
                fourth.put(KEY_A_SHOW_RESOURCES, CollectionUtil.getJsonArrayFromList(this.showResourceList));
            } catch (JSONException e) {
            }
            return fourth;
        }

        public void parse(JSONObject fourthJsonObject) {
            boolean z;
            boolean z2 = true;
            Logger.m1428v(PkEntity.TAG, "-[ShowInfo] action: parse - econ");
            this.title = fourthJsonObject.optString(KEY_A_SHOW_TITLE, "");
            this.iconUrl = fourthJsonObject.optString(KEY_A_SHOW_ICON_URL, "").trim();
            this.version = fourthJsonObject.optString(KEY_A_SHOW_VERSION, "");
            this.infoType = fourthJsonObject.optString(KEY_A_SHOW_INFO_TYPE, "");
            this.needShowScore = fourthJsonObject.optInt(KEY_A_SHOW_NEED_SCORE, 0) == 0;
            this.size = fourthJsonObject.optString(KEY_A_SHOW_SIZE, "");
            this.description = fourthJsonObject.optString(KEY_A_SHOW_DESCRIPTION, "");
            this.screenShotUrl = fourthJsonObject.optString(KEY_A_SHOW_SCREENSHOT_URL, "").trim();
            this.webPageUrl = fourthJsonObject.optString(KEY_A_SHOW_WEBPAGE_URL, "").trim();
            PkEntity pkEntity = PkEntity.this;
            if (fourthJsonObject.optInt("a_broswer", 0) == 1) {
                z = true;
            } else {
                z = false;
            }
            pkEntity.isGoBroswer = z;
            if (fourthJsonObject.optInt(KEY_A_SHOW_PRELOAD_RESOURCE, 0) != 0) {
                z2 = false;
            }
            this.needPreloadResources = z2;
            if (this.needPreloadResources) {
                this.showResourceList = CollectionUtil.getListForJSONArray(fourthJsonObject.optJSONArray(KEY_A_SHOW_RESOURCES));
            }
            if (StringUtils.isEmpty(this._imagePath)) {
                this._imagePath = fourthJsonObject.optString("_imagePath", "").trim();
            }
            if (StringUtils.isEmpty(this._iconPath)) {
                this._iconPath = fourthJsonObject.optString("_iconPath", "").trim();
            }
            if (StringUtils.isEmpty(this._iconPath)) {
                this._iconPath = fourthJsonObject.optString("_webPagePath", "").trim();
            }
        }

        public String toString() {
            JSONObject js = getAShowInfoObject();
            try {
                js.put("_iconPath", this._iconPath);
                js.put("_imagePath", this._imagePath);
                js.put("_webPagePath", this._webPagePath);
            } catch (JSONException e) {
            }
            return js.toString();
        }
    }

    public PkEntity() {
        this.needAutoInstall = true;
        this.type = 1;
        this.aShowInfo = new AShowInfo();
    }

    public JSONObject msgContentToJson() {
        int i;
        int i2;
        int i3;
        int i4 = 1;
        JSONObject third = new JSONObject();
        try {
            third.put(KEY_PACKAGE_NAME, this.aPackageName);
            third.put(KEY_NEED_UPDATE_IF_INSTALLED, this.needUpdateIfInstalled ? 1 : 0);
            String str = KEY_NEED_S_DOWNLOAD;
            if (this.needSDownload) {
                i = 1;
            } else {
                i = 0;
            }
            third.put(str, i);
            third.put(KEY_S_DOWNLOAD_NETWORK_OPTION, this.sDownloadNetworkOption);
            String str2 = KEY_NEED_AUTO_RUNUP;
            if (this.needAutoRunup) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            third.put(str2, i2);
            third.put(KEY_A_SHOW_MODE, this.aShowMode);
            third.put(KEY_DOWNLAOD_URL, this.aDownloadUrl);
            third.put(KEY_A_MD5, this.aMd5);
            third.put(KEY_MAIN_ACTIVITY, this.aMainActivity);
            String str3 = KEY_A_FINISHED_SHOW_NOTI;
            if (this.needShowFinishedNotification) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            third.put(str3, i3);
            String str4 = KEY_A_AUTO_INSTALL;
            if (!this.needAutoInstall) {
                i4 = 0;
            }
            third.put(str4, i4);
            if (this.aShowInfo != null) {
                third.put(KEY_A_SHOW_CONTENT, this.aShowInfo.getAShowInfoObject());
            }
        } catch (JSONException e) {
        }
        return third;
    }

    public boolean parseContent(Context context, JSONObject thirdJsonObject) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Logger.m1428v(TAG, "action: parse - content");
        this.aPackageName = thirdJsonObject.optString(KEY_PACKAGE_NAME, "");
        this.needUpdateIfInstalled = thirdJsonObject.optInt(KEY_NEED_UPDATE_IF_INSTALLED, 0) >= 1;
        if (thirdJsonObject.optInt(KEY_NEED_S_DOWNLOAD, 0) >= 1) {
            z = true;
        } else {
            z = false;
        }
        this.needSDownload = z;
        this.sDownloadNetworkOption = thirdJsonObject.optInt(KEY_S_DOWNLOAD_NETWORK_OPTION, 0);
        if (thirdJsonObject.optInt(KEY_NEED_AUTO_RUNUP, 0) >= 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.needAutoRunup = z2;
        this.aShowMode = thirdJsonObject.optInt(KEY_A_SHOW_MODE, 1);
        this.aDownloadUrl = thirdJsonObject.optString(KEY_DOWNLAOD_URL, "").trim();
        this.aMd5 = thirdJsonObject.optString(KEY_A_MD5, "");
        this.aMainActivity = thirdJsonObject.optString(KEY_MAIN_ACTIVITY, "");
        if (thirdJsonObject.optInt(KEY_A_FINISHED_SHOW_NOTI, 0) >= 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.needShowFinishedNotification = z3;
        if (thirdJsonObject.optInt(KEY_A_AUTO_INSTALL, 1) == 1) {
            z4 = true;
        } else {
            z4 = false;
        }
        this.needAutoInstall = z4;
        if (this.type == 1) {
            JSONObject fourthJsonObject = ProtocolHelper.getJsonObject(context, this.messageId, thirdJsonObject, KEY_A_SHOW_CONTENT);
            if (fourthJsonObject == null) {
                return false;
            }
            this.aShowInfo.parse(fourthJsonObject);
        }
        return true;
    }

    public void process(Context context) {
        Logger.m1428v(TAG, "action:process");
        boolean pkExist = AndroidUtil.isPackageExist(context, this.aPackageName);
        int reportCode = StatusCode.RESULT_TYPE_JSON_LOAD_SUC;
        if (this.needUpdateIfInstalled || !pkExist) {
            if (this.needUpdateIfInstalled && pkExist) {
                Logger.m1416d(TAG, "The pk already exist, but need to update by param");
                reportCode = StatusCode.RESULT_TYPE_A_FOUND_RECEIVED;
            }
            if (this.aShowMode == 1) {
                preLoadHtmlResources(context, reportCode);
            } else if (this.aShowMode == 0) {
                preloadLocalViewResouces(context, reportCode);
            } else {
                Logger.m1432w(TAG, "Unexpected: unknown pk show mode - " + this.aShowMode);
            }
        } else {
            Logger.m1416d(TAG, "The pk already exist, NOT update");
            ReportHelper.reportMsgResult(this.messageId, StatusCode.RESULT_TYPE_A_FOUND_NOT_RECEIVED, context);
        }
    }

    private void preLoadHtmlResources(final Context context, final int reportCode) {
        new Thread() {
            public void run() {
                String webPagePath;
                String webPageUrl = this.aShowInfo.webPageUrl;
                String msgId = this.messageId;
                if (!ProtocolHelper.checkValidUrl(webPageUrl)) {
                    ReportHelper.reportMsgResult(this.messageId, 996, context);
                } else if (!this.aShowInfo.needPreloadResources) {
                    ReportHelper.reportMsgResult(msgId, reportCode, context);
                    PkEntity.this.postProcess(this, context);
                } else {
                    String pageContent = "";
                    boolean succeed = false;
                    int i = 0;
                    while (true) {
                        if (i >= 4) {
                            break;
                        }
                        pageContent = HttpHelper.httpSimpleGet(webPageUrl, 5, 5000);
                        if (!HttpHelper.checkHttpIsError(pageContent)) {
                            succeed = true;
                            break;
                        }
                        i++;
                    }
                    if (!succeed) {
                        ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                        ReportHelper.reportMsgActionResult(msgId, StatusCode.RESULT_TYPE_HTML_LOAD_FAIL, AndroidUtil.getDownloadFailedClientInfo(context, webPageUrl), context);
                        Logger.m1416d(PkEntity.TAG, "NOTE: failed to download html page. Give up this msg.");
                        return;
                    }
                    if (!Entity.loadHtmlImageResources(this.aShowInfo.showResourceList, context, webPageUrl.substring(0, webPageUrl.lastIndexOf("/") + 1), msgId, this.isRichPush())) {
                        Logger.m1416d(PkEntity.TAG, "Under fullscreen mode, all resources should load successfully");
                        ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                        return;
                    }
                    String str = "";
                    if (this.isRichPush()) {
                        webPagePath = DirectoryUtils.getDirectoryRichPush(context, msgId) + msgId + ".html";
                    } else {
                        webPagePath = DirectoryUtils.getStorageDir(context, msgId) + msgId;
                    }
                    if (FileUtil.createHtmlFile(webPagePath, pageContent, context)) {
                        this.aShowInfo._webPagePath = "file://" + webPagePath;
                        ReportHelper.reportMsgResult(msgId, reportCode, context);
                        PkEntity.this.postProcess(this, context);
                        return;
                    }
                    ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                }
            }
        }.start();
    }

    private void preloadLocalViewResouces(final Context context, final int reportCode) {
        new Thread() {
            public void run() {
                if (ProtocolHelper.checkValidUrl(PkEntity.this.aShowInfo.iconUrl)) {
                    PkEntity.this.aShowInfo._iconPath = Entity.loadImgRes(PkEntity.this.aShowInfo.iconUrl, PkEntity.this.messageId, JPushConstants.ICON_NAME, context);
                }
                if (ProtocolHelper.checkValidUrl(PkEntity.this.aShowInfo.screenShotUrl)) {
                    PkEntity.this.aShowInfo._imagePath = Entity.loadImgRes(PkEntity.this.aShowInfo.screenShotUrl, PkEntity.this.messageId, JPushConstants.IMAG_NAME, context);
                }
                if (StringUtils.isEmpty(PkEntity.this.aShowInfo._iconPath) || StringUtils.isEmpty(PkEntity.this.aShowInfo._imagePath)) {
                    ReportHelper.reportMsgResult(PkEntity.this.messageId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                    return;
                }
                ReportHelper.reportMsgResult(PkEntity.this.messageId, reportCode, context);
                PkEntity.this.postProcess(this, context);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public void postProcess(PkEntity entity, Context context) {
        if (!ProtocolHelper.isNeedSDownload(entity.needSDownload, entity.sDownloadNetworkOption, context)) {
            NotificationHelper.showNotification(context, entity);
        } else if (AndroidUtil.isConnected(context)) {
            ServiceInterface.executeDownload(context, entity);
        } else {
            entity.needSDownload = false;
            NotificationHelper.showNotification(context, entity);
        }
    }
}
