package p005cn.jpush.android.data;

import android.content.Context;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.CollectionUtil;
import p005cn.jpush.android.util.DirectoryUtils;
import p005cn.jpush.android.util.FileUtil;
import p005cn.jpush.android.util.HttpHelper;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.data.ShowEntity */
public class ShowEntity extends Entity {
    public static final int JUMP_MODE_NONE = 0;
    public static final int JUMP_MODE_ONE = 1;
    public static final int JUMP_MODE_TWO = 2;
    public static final String KEY_FROM_NUM = "from_num";
    public static final String KEY_JUMP_MODE = "e_jump_mode";
    public static final String KEY_RICH_TYPE = "e_rich_type";
    public static final String KEY_SHOW_MODE = "e_show";
    public static final String KEY_SHOW_RESOURCES = "e_eres";
    public static final String KEY_SHOW_TITLE = "e_title";
    public static final String KEY_SHOW_URL = "e_url";
    public static final String KEY_TO_NUM = "to_num";
    public static final int RICH_TYPE_LANDING_PAGE = 1;
    public static final int RICH_TYPE_MSG_FLOW = 3;
    public static final int RICH_TYPE_NONE = 0;
    public static final int RICH_TYPE_POP_WINDOW = 2;
    public static final int RICH_TYPE_URL = 4;
    public static final int SHOW_MODE_BROWSER = 1;
    public static final int SHOW_MODE_LOAD = 0;
    public static final int SHOW_MODE_SYSTEM_ALERT = 2;
    public static final int SHOW_MODE_UNLOAD = 1;
    public static final int SHOW_MODE_WEBVIEW = 0;
    private static final String TAG = "ShowEntity";
    private static final long serialVersionUID = 2748721849169550485L;
    public String _webPagePath;
    public String fromNum;
    public int jumpMode;
    public int richType;
    public int showMode;
    public ArrayList<String> showResourceList;
    public String showTitle;
    public String showUrl;
    public String toNum;

    public ShowEntity() {
        this.showResourceList = new ArrayList<>();
        this.fromNum = "";
        this.toNum = "";
        this.type = 0;
    }

    public JSONObject msgContentToJson() {
        JSONObject third = new JSONObject();
        try {
            third.put(KEY_SHOW_URL, this.showUrl);
            third.put(KEY_SHOW_TITLE, this.showTitle);
            third.put(KEY_RICH_TYPE, this.richType);
            third.put(KEY_JUMP_MODE, this.jumpMode);
            third.put(KEY_SHOW_MODE, this.showMode);
            third.put(KEY_SHOW_RESOURCES, CollectionUtil.getJsonArrayFromList(this.showResourceList));
        } catch (JSONException e) {
        }
        return third;
    }

    public boolean parseContent(Context context, JSONObject thirdJsonObject) {
        Logger.m1428v(TAG, "action: parse - content");
        this.showUrl = thirdJsonObject.optString(KEY_SHOW_URL, "").trim();
        this.showTitle = thirdJsonObject.optString(KEY_SHOW_TITLE, "").trim();
        if (!ProtocolHelper.checkValidUrl(this.showUrl)) {
            this.showUrl = JPushConstants.HTTP_PRE + this.showUrl;
            Logger.m1424i(TAG, "Add http to non-prefix url: " + this.showUrl);
        }
        this.richType = thirdJsonObject.optInt(KEY_RICH_TYPE, 0);
        this.jumpMode = thirdJsonObject.optInt(KEY_JUMP_MODE, 0);
        this.showMode = thirdJsonObject.optInt(KEY_SHOW_MODE, 0);
        if (3 == this.richType || 2 == this.richType || 1 == this.richType) {
            this.showResourceList = CollectionUtil.getListForJSONArray(thirdJsonObject.optJSONArray(KEY_SHOW_RESOURCES));
        }
        this.fromNum = thirdJsonObject.optString(KEY_FROM_NUM, "");
        this.toNum = thirdJsonObject.optString(KEY_TO_NUM, "");
        return true;
    }

    public void process(final Context context) {
        Logger.m1428v(TAG, "action:process");
        new Thread() {
            public void run() {
                Logger.m1428v(ShowEntity.TAG, "showEntity process start run! showMode = " + ShowEntity.this.showMode + ", richType = " + ShowEntity.this.richType + ", jumpMode = " + ShowEntity.this.jumpMode);
                if (ShowEntity.this.showMode != 0) {
                    Logger.m1416d(ShowEntity.TAG, "Unexpected: unknown show  mode - " + ShowEntity.this.showMode);
                    return;
                }
                String msgId = this.messageId;
                String showUrl = this.showUrl;
                if (this.richType == 0) {
                    ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_JSON_LOAD_SUC, context);
                    NotificationHelper.showNotification(context, this);
                } else if (4 == this.richType) {
                    this._webPagePath = showUrl;
                    ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_JSON_LOAD_SUC, context);
                    NotificationHelper.showNotification(context, this);
                } else if (!AndroidUtil.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    Logger.m1422ee(ShowEntity.TAG, "Rich-push needs the permission of WRITE_EXTERNAL_STORAGE, please request it.");
                    ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                } else {
                    String webPageContent = "";
                    boolean resourceGetSucceed = false;
                    int i = 0;
                    while (true) {
                        if (i >= 4) {
                            break;
                        }
                        webPageContent = HttpHelper.httpSimpleGet(showUrl, 5, 5000);
                        if (!HttpHelper.checkHttpIsError(webPageContent)) {
                            resourceGetSucceed = true;
                            break;
                        }
                        i++;
                    }
                    String str = "";
                    String webPagePathPrefix = DirectoryUtils.getDirectoryRichPush(context, msgId);
                    if (resourceGetSucceed) {
                        String webPagePath = webPagePathPrefix + msgId + ".html";
                        String urlPrefix = showUrl.substring(0, showUrl.lastIndexOf("/") + 1);
                        if (this.showResourceList.isEmpty()) {
                            this._webPagePath = this.showUrl;
                            NotificationHelper.showNotification(context, this);
                        } else if (!Entity.loadHtmlImageResources(this.showResourceList, context, urlPrefix, msgId, this.isRichPush())) {
                            Logger.m1416d(ShowEntity.TAG, "Loads rich push resources failed!");
                            ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                        } else if (FileUtil.createHtmlFile(webPagePath, webPageContent.replaceAll("img src=\"" + urlPrefix, "img src=\"" + webPagePathPrefix), context)) {
                            this._webPagePath = "file://" + webPagePath;
                            ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_JSON_LOAD_SUC, context);
                            NotificationHelper.showNotification(context, this);
                        } else {
                            ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                        }
                    } else {
                        Logger.m1432w(ShowEntity.TAG, "NOTE: failed to download html page. Give up this.");
                        ReportHelper.reportMsgResult(msgId, StatusCode.RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED, context);
                        ReportHelper.reportMsgActionResult(msgId, StatusCode.RESULT_TYPE_HTML_LOAD_FAIL, AndroidUtil.getDownloadFailedClientInfo(context, showUrl), context);
                    }
                }
            }
        }.start();
    }
}
