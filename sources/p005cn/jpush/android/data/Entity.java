package p005cn.jpush.android.data;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.DirectoryUtils;
import p005cn.jpush.android.util.FileUtil;
import p005cn.jpush.android.util.HttpHelper;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.data.Entity */
public abstract class Entity implements Serializable {
    public static final int DOWNLOAD_RETRY_TIMES_NOT_SET = -1;
    public static final String KEY_CONTENT_TYPE = "content_type";
    public static final String KEY_EXTRAS = "extras";
    public static final String KEY_FULL_SCREEN = "full_screen";
    public static final String KEY_IMAGE_URL_LIST = "image_url_list";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_MESSAGE_BODY_CONTENT = "m_content";
    public static final String KEY_MESSAGE_ID = "ad_id";
    public static final String KEY_MESSAGE_ID_V2 = "msg_id";
    public static final String KEY_MSG_CONTENT = "ad_content";
    public static final String KEY_MSG_TYPE = "ad_t";
    public static final String KEY_NOTIFICATION_EXTRAS = "n_extras";
    public static final String KEY_NOTIFICATION_ONLY = "n_only";
    public static final String KEY_NOTIFICATION_ONLY_ID = "n_builder_id";
    public static final String KEY_NOTIFICATION_TYPE = "notificaion_type";
    public static final String KEY_NOTI_CONTENT = "n_content";
    public static final String KEY_NOTI_REMOVE_MODE = "n_flag";
    public static final String KEY_NOTI_TITLE = "n_title";
    public static final String KEY_OVERRIDE_MESSAGE_ID = "override_msg_id";
    public static final String KEY_PROTOCOL_VERSION = "show_type";
    public static final String KEY_SHOW_WEBPAGE_GOBROSWER = "a_broswer";
    public static final String KEY_TITLE = "title";
    public static final int MESSAGE_V3 = 3;
    public static final int MESSAGE_V4 = 4;
    public static final int MSG_TYPE_PK = 1;
    public static final int MSG_TYPE_SHOW = 0;
    public static final int MSG_TYPE_UPDATE = 3;
    public static final int MSG_TYPE_VIDEO = 2;
    public static final int REMOVE_MODE_AUTO = 1;
    public static final int REMOVE_MODE_MANUAL = 0;
    public static final int REMOVE_MODE_MUST_CLICK = 2;
    protected static final int RESOURCE_RETRY_INTERVAL = 5000;
    protected static final int RESOURCE_RETRY_MORE = 4;
    protected static final int RESOURCE_RETRY_TIMES = 5;
    private static final String TAG = "Entity";
    public static final int VERSION_NEW = 1;
    public static final int VERSION_NEW_URL = 2;
    public static final int VERSION_OLD = 0;
    private static final long serialVersionUID = 8653272927271926594L;
    public int _downloadRetryTimes = -1;
    public String _fullImagePath;
    public boolean _isDownloadFinisehd = false;
    public boolean _isDownloadInterrupted = false;
    public boolean _isEverDownloadFailed = false;
    private boolean _isRichPush = false;
    public ArrayList<String> _systemViewImageFilePathList = null;
    public String appId;
    public String content;
    public String contentType;
    public String extras;
    public boolean isDeveloperMessage;
    public boolean isFullScreen;
    public boolean isGoBroswer = false;
    public String message;
    public String messageBodyContent;
    public String messageId;
    public int messageVersion;
    public List<Entity> messages;
    public int notificationBuilderId;
    public String notificationContent;
    public boolean notificationOnly;
    public int notificationRemoveMode;
    public String notificationTitle;
    public int notificationType = 0;
    public String overrideMessageId;
    public String richPushSavedPath;
    public String richPushType;
    public String richPushUrl;
    public String senderId;
    public List<String> systemViewImageUrlList = null;
    public String title;
    public int type;

    /* renamed from: cn.jpush.android.data.Entity$EntityKey */
    public class EntityKey {
        public String msg_id;
        public String override_msg_id;

        public EntityKey(Entity entity) {
            this.msg_id = entity.messageId;
            this.override_msg_id = entity.overrideMessageId;
        }

        public EntityKey() {
        }

        public EntityKey(String msgId, String overrideId) {
            this.msg_id = msgId;
            this.override_msg_id = overrideId;
        }

        public boolean equals(Object o) {
            if (!(o instanceof EntityKey)) {
                return false;
            }
            EntityKey entity = (EntityKey) o;
            if (StringUtils.isEmpty(this.msg_id) || StringUtils.isEmpty(entity.msg_id) || !StringUtils.equals(this.msg_id, entity.msg_id)) {
                return false;
            }
            if (StringUtils.isEmpty(this.override_msg_id) && StringUtils.isEmpty(entity.override_msg_id)) {
                return true;
            }
            if (StringUtils.isEmpty(this.override_msg_id) || StringUtils.isEmpty(entity.override_msg_id) || !StringUtils.equals(this.override_msg_id, entity.override_msg_id)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return "msg_id = " + this.msg_id + ",  override_msg_id = " + this.override_msg_id;
        }
    }

    public abstract JSONObject msgContentToJson();

    /* access modifiers changed from: protected */
    public abstract boolean parseContent(Context context, JSONObject jSONObject);

    public abstract void process(Context context);

    public boolean isMsgTypeAAndUpdate() {
        return this.type == 3 || this.type == 1;
    }

    public boolean isMsgTypeVideo() {
        return this.type == 2;
    }

    public boolean isMsgTypeUpdate() {
        return this.type == 3;
    }

    public boolean isMsgTypeShow() {
        return this.type == 0;
    }

    public String getRichPushUrl() {
        return this.richPushUrl;
    }

    public void setRichPushUrl(String richPushUrl2) {
        this.richPushUrl = richPushUrl2;
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public String getDownloadUrl() {
        if (isMsgTypeAAndUpdate()) {
            return ((PkEntity) this).aDownloadUrl;
        }
        if (isMsgTypeVideo()) {
            return ((VideoEntity) this).videoPlayUrl;
        }
        if (isRichPush()) {
            return getRichPushUrl();
        }
        return "";
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public String getDownloadedSavedPath() {
        if (isMsgTypeAAndUpdate()) {
            return ((PkEntity) this)._aSavedPath;
        }
        if (isMsgTypeVideo()) {
            return ((VideoEntity) this)._videoSavedPath;
        }
        return "";
    }

    public boolean parse(Context context, JSONObject secondJsonObject) {
        Logger.m1428v(TAG, "action: parse - base entity");
        this.isFullScreen = secondJsonObject.optInt(KEY_FULL_SCREEN, 0) >= 1;
        this.notificationRemoveMode = secondJsonObject.optInt(KEY_NOTI_REMOVE_MODE, 0);
        this.notificationTitle = secondJsonObject.optString(KEY_NOTI_TITLE, "");
        this.notificationContent = secondJsonObject.optString(KEY_NOTI_CONTENT, "");
        this.extras = secondJsonObject.optString(KEY_NOTIFICATION_EXTRAS, "");
        if (StringUtils.isEmpty(this.notificationTitle)) {
            if (!this.isDeveloperMessage) {
                Logger.m1416d(TAG, "Invalid - empty notification title for internal");
                ReportHelper.reportMsgResult(this.messageId, 996, context);
                return false;
            }
            Logger.m1416d(TAG, "Not found notificaiton title for developer mode. Use the application name.");
            this.notificationTitle = JPush.mApplicationName;
        }
        JSONObject thirdObject = ProtocolHelper.getJsonObject(context, this.messageId, secondJsonObject, KEY_MSG_CONTENT);
        if (thirdObject != null) {
            if (this.isDeveloperMessage && this.notificationOnly) {
                this._isRichPush = true;
            }
            return parseContent(context, thirdObject);
        } else if (!this.isDeveloperMessage || !this.notificationOnly) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isRichPush() {
        return this._isRichPush;
    }

    public void setRichPush(boolean flag) {
        this._isRichPush = flag;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        p005cn.jpush.android.util.Logger.m1420e(TAG, "download success，but failed to save the file");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a0, code lost:
        r6 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p005cn.jpush.android.data.Entity preLoadSystemViewResources(android.content.Context r12, p005cn.jpush.android.data.Entity r13) {
        /*
            r6 = 1
            java.util.List<java.lang.String> r7 = r13.systemViewImageUrlList
            if (r7 == 0) goto L_0x00d4
            java.util.List<java.lang.String> r7 = r13.systemViewImageUrlList
            int r7 = r7.size()
            if (r7 <= 0) goto L_0x00d4
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r13._systemViewImageFilePathList = r7
            java.util.List<java.lang.String> r7 = r13.systemViewImageUrlList
            java.util.Iterator r7 = r7.iterator()
        L_0x001a:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x008b
            java.lang.Object r4 = r7.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r8 = p005cn.jpush.android.util.StringUtils.isEmpty(r4)
            if (r8 != 0) goto L_0x00c9
            r8 = 5
            r10 = 5000(0x1388, double:2.4703E-320)
            r9 = 4
            byte[] r3 = p005cn.jpush.android.util.HttpHelper.httpGet(r4, r8, r10, r9)
            if (r3 == 0) goto L_0x00a2
            java.lang.String r8 = "/"
            int r5 = r4.lastIndexOf(r8)     // Catch:{ IOException -> 0x0080 }
            int r8 = r5 + 1
            java.lang.String r1 = r4.substring(r8)     // Catch:{ IOException -> 0x0080 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0080 }
            r8.<init>()     // Catch:{ IOException -> 0x0080 }
            java.lang.String r9 = r13.messageId     // Catch:{ IOException -> 0x0080 }
            java.lang.String r9 = p005cn.jpush.android.util.DirectoryUtils.getStorageDir(r12, r9)     // Catch:{ IOException -> 0x0080 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0080 }
            java.lang.StringBuilder r8 = r8.append(r1)     // Catch:{ IOException -> 0x0080 }
            java.lang.String r2 = r8.toString()     // Catch:{ IOException -> 0x0080 }
            boolean r8 = p005cn.jpush.android.util.FileUtil.createImgFile(r2, r3, r12)     // Catch:{ IOException -> 0x0080 }
            if (r8 == 0) goto L_0x0097
            java.util.ArrayList<java.lang.String> r8 = r13._systemViewImageFilePathList     // Catch:{ IOException -> 0x0080 }
            r8.add(r2)     // Catch:{ IOException -> 0x0080 }
            java.lang.String r8 = "Entity"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0080 }
            r9.<init>()     // Catch:{ IOException -> 0x0080 }
            java.lang.String r10 = "Succeed to load image - "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x0080 }
            java.lang.StringBuilder r9 = r9.append(r2)     // Catch:{ IOException -> 0x0080 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x0080 }
            p005cn.jpush.android.util.Logger.m1428v(r8, r9)     // Catch:{ IOException -> 0x0080 }
            goto L_0x001a
        L_0x0080:
            r0 = move-exception
            java.lang.String r7 = "Entity"
            java.lang.String r8 = "Write storage error,  create img file fail."
            p005cn.jpush.android.util.Logger.m1435ww(r7, r8, r0)
            r6 = 0
        L_0x008b:
            if (r6 == 0) goto L_0x00df
            java.lang.String r7 = "Entity"
            java.lang.String r8 = "photo download success"
            p005cn.jpush.android.util.Logger.m1424i(r7, r8)
        L_0x0096:
            return r13
        L_0x0097:
            java.lang.String r7 = "Entity"
            java.lang.String r8 = "download success，but failed to save the file"
            p005cn.jpush.android.util.Logger.m1420e(r7, r8)     // Catch:{ IOException -> 0x0080 }
            r6 = 0
            goto L_0x008b
        L_0x00a2:
            java.lang.String r7 = "Entity"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "download failed，imageUrl: "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r4)
            java.lang.String r8 = r8.toString()
            p005cn.jpush.android.util.Logger.m1432w(r7, r8)
            java.lang.String r7 = r13.messageId
            r8 = 1020(0x3fc, float:1.43E-42)
            java.lang.String r9 = p005cn.jpush.android.util.AndroidUtil.getDownloadFailedClientInfo(r12, r4)
            p005cn.jpush.android.helpers.ReportHelper.reportMsgActionResult(r7, r8, r9, r12)
            r6 = 0
            goto L_0x008b
        L_0x00c9:
            java.lang.String r7 = "Entity"
            java.lang.String r8 = "the photo url is null"
            p005cn.jpush.android.util.Logger.m1420e(r7, r8)
            r6 = 0
            goto L_0x008b
        L_0x00d4:
            java.lang.String r7 = "Entity"
            java.lang.String r8 = "the length of the url is 0 or null"
            p005cn.jpush.android.util.Logger.m1420e(r7, r8)
            r6 = 0
            goto L_0x008b
        L_0x00df:
            java.lang.String r7 = "Entity"
            java.lang.String r8 = "photo download failed, discard the message"
            p005cn.jpush.android.util.Logger.m1432w(r7, r8)
            java.lang.String r7 = r13.messageId
            r8 = 1014(0x3f6, float:1.421E-42)
            p005cn.jpush.android.helpers.ReportHelper.reportMsgResult(r7, r8, r12)
            r13 = 0
            goto L_0x0096
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.data.Entity.preLoadSystemViewResources(android.content.Context, cn.jpush.android.data.Entity):cn.jpush.android.data.Entity");
    }

    static boolean loadHtmlImageResources(ArrayList<String> list, Context context, String urlPrefix, String msgId, boolean isRichPush) {
        String filePath;
        boolean totalSucceed = true;
        Logger.m1428v(TAG, "action:loadHtmlImageResources - urlPrefix:" + urlPrefix);
        if (ProtocolHelper.checkValidUrl(urlPrefix) && context != null && list.size() > 0 && !TextUtils.isEmpty(msgId)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String name = (String) it.next();
                String urlpath = name;
                if (urlpath != null && !urlpath.startsWith(JPushConstants.HTTP_PRE)) {
                    urlpath = urlPrefix + name;
                }
                byte[] bytz = HttpHelper.httpGet(urlpath, 5, 5000, 4);
                if (bytz != null) {
                    String fileName = name;
                    try {
                        if (fileName.startsWith(JPushConstants.HTTP_PRE)) {
                            fileName = FileUtil.getFileNameFromUrl(fileName);
                        }
                        if (!isRichPush) {
                            filePath = DirectoryUtils.getStorageDir(context, msgId) + fileName;
                        } else {
                            filePath = DirectoryUtils.getDirectoryRichPush(context, msgId) + fileName;
                        }
                        FileUtil.createImgFile(filePath, bytz, context);
                        Logger.m1428v(TAG, "Succeed to load image - " + filePath);
                    } catch (IOException e) {
                        Logger.m1435ww(TAG, "Write storage error,  create img file fail.", e);
                        totalSucceed = false;
                    }
                } else {
                    ReportHelper.reportMsgActionResult(msgId, StatusCode.RESULT_TYPE_IMAGE_LOAD_FAIL, AndroidUtil.getDownloadFailedClientInfo(context, urlpath), context);
                    totalSucceed = false;
                }
            }
        }
        return totalSucceed;
    }

    static String loadImgRes(String url, String msgId, String name, Context context) {
        Logger.m1428v(TAG, "action:loadImgRes - url:" + url);
        String loadedImagePath = "";
        if (!ProtocolHelper.checkValidUrl(url) || context == null || TextUtils.isEmpty(msgId) || TextUtils.isEmpty(name)) {
            return loadedImagePath;
        }
        byte[] bytz = HttpHelper.httpGet(url, 5, 5000, 4);
        if (bytz != null) {
            try {
                String filePath = DirectoryUtils.getStorageDir(context, msgId) + name;
                FileUtil.createImgFile(filePath, bytz, context);
                Logger.m1428v(TAG, "Succeed to load image - " + filePath);
                return filePath;
            } catch (IOException e) {
                Logger.m1421e(TAG, "create imag file error", e);
                return loadedImagePath;
            }
        } else {
            ReportHelper.reportMsgActionResult(msgId, StatusCode.RESULT_TYPE_IMAGE_LOAD_FAIL, AndroidUtil.getDownloadFailedClientInfo(context, url), context);
            return loadedImagePath;
        }
    }

    public String getMd5() {
        String md5 = "";
        if (isMsgTypeAAndUpdate()) {
            String s = ((PkEntity) this).aMd5;
            if (s == null || "".equals(s)) {
                return null;
            }
            return s.trim();
        } else if (!isMsgTypeVideo()) {
            return md5;
        } else {
            String s2 = ((VideoEntity) this).videoMd5;
            if (s2 == null || "".equals(s2)) {
                return null;
            }
            return s2.trim();
        }
    }

    public EntityKey getEntityKey() {
        return new EntityKey(this);
    }
}
