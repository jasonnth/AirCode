package p005cn.jpush.android.data;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.data.VideoEntity */
public class VideoEntity extends Entity {
    public static final String KEY_VIDEO_DESCRIPTION = "v_info";
    public static final String KEY_VIDEO_MD5 = "v_md5";
    public static final String KEY_VIDEO_PLAYED_CLICK_URL = "v_eurl";
    public static final String KEY_VIDEO_PLAY_MODE = "v_type";
    public static final String KEY_VIDEO_PLAY_URL = "v_url";
    private static final String TAG = "VideoEntity";
    public static final int VIDEO_PLAY_MODE_ONLINE = 1;
    public static final int VIDEO_PLAY_MODE_PRELOADED = 0;
    private static final long serialVersionUID = 7448212238592505682L;
    public String _videoSavedPath;
    public String videoDescription;
    public String videoMd5;
    public int videoPlayMode;
    public String videoPlayUrl;
    public String videoPlayedClickUrl;

    public VideoEntity() {
        this.type = 2;
    }

    public JSONObject msgContentToJson() {
        JSONObject third = new JSONObject();
        try {
            third.put(KEY_VIDEO_PLAY_MODE, this.videoPlayMode);
            third.put(KEY_VIDEO_PLAY_URL, this.videoPlayUrl);
            third.put(KEY_VIDEO_PLAYED_CLICK_URL, this.videoPlayedClickUrl);
            third.put(KEY_VIDEO_DESCRIPTION, this.videoDescription);
        } catch (JSONException e) {
        }
        return third;
    }

    public boolean parseContent(Context context, JSONObject thirdJsonObject) {
        Logger.m1428v(TAG, "action: parse - content");
        this.videoPlayMode = thirdJsonObject.optInt(KEY_VIDEO_PLAY_MODE, 0);
        this.videoPlayUrl = thirdJsonObject.optString(KEY_VIDEO_PLAY_URL, "");
        this.videoPlayedClickUrl = thirdJsonObject.optString(KEY_VIDEO_PLAYED_CLICK_URL, "");
        this.videoMd5 = thirdJsonObject.optString(KEY_VIDEO_MD5, "");
        this.videoDescription = thirdJsonObject.optString(KEY_VIDEO_DESCRIPTION, "");
        return true;
    }

    public void process(Context context) {
        Logger.m1428v(TAG, "action:process");
        ReportHelper.reportMsgResult(this.messageId, StatusCode.RESULT_TYPE_JSON_LOAD_SUC, context);
        if (this.videoPlayMode == 0) {
            if (AndroidUtil.isConnected(context)) {
                ServiceInterface.executeDownload(context, this);
            }
        } else if (this.videoPlayMode == 1) {
            NotificationHelper.showNotification(context, this);
        } else {
            Logger.m1416d(TAG, "Unknown video type - " + this.videoPlayMode);
        }
    }
}
