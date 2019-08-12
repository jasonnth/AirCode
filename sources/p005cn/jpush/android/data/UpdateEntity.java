package p005cn.jpush.android.data;

import android.content.Context;
import org.json.JSONObject;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.data.UpdateEntity */
public class UpdateEntity extends PkEntity {
    private static final String TAG = "UpdateEntity";
    public static final String UPDATE_MSG_ID = "1000000000";
    public static final int UPDATE_SHOW_DIALOG = 1;
    public static final int UPDATE_SHOW_NOTIFICATION = 0;
    private static final long serialVersionUID = 507897639831945397L;

    public UpdateEntity() {
        this.type = 3;
        this.aShowInfo = null;
    }

    public boolean parseContent(Context context, JSONObject thirdJsonObject) {
        Logger.m1428v(TAG, "action: parse - content");
        boolean ret = super.parseContent(context, thirdJsonObject);
        this.aShowMode = thirdJsonObject.optInt(PkEntity.KEY_A_SHOW_MODE, 0);
        return ret;
    }

    public void process(Context context) {
        Logger.m1428v(TAG, "action:process");
        if (ProtocolHelper.isNeedSDownload(this.needSDownload, this.sDownloadNetworkOption, context)) {
            ServiceInterface.executeDownload(context, this);
        } else if (this.aShowMode == 1) {
            context.startActivity(AndroidUtil.getIntentForStartPushActivity(context, this, true));
        } else if (this.aShowMode == 0) {
            NotificationHelper.showNotification(context, this, true, true);
        } else {
            Logger.m1416d(TAG, "Unknown show mode - " + this.aShowMode);
        }
    }
}
