package p005cn.jpush.android.api;

import android.content.Context;
import android.widget.RemoteViews;
import p005cn.jpush.android.JPush;

/* renamed from: cn.jpush.android.api.CustomPushNotificationBuilder */
public class CustomPushNotificationBuilder extends BasicPushNotificationBuilder {
    public int layout;
    public int layoutContentId;
    public int layoutIconDrawable = JPush.mPackageIconId;
    public int layoutIconId;
    public int layoutTitleId;

    CustomPushNotificationBuilder(Context context) {
        super(context);
    }

    public CustomPushNotificationBuilder(Context context, int layout2, int layoutIconId2, int subjectId, int contentId) {
        super(context);
        this.layout = layout2;
        this.layoutIconId = layoutIconId2;
        this.layoutTitleId = subjectId;
        this.layoutContentId = contentId;
    }

    /* access modifiers changed from: 0000 */
    public RemoteViews buildContentView(String alert) {
        RemoteViews customRemoteView = new RemoteViews(this.mContext.getPackageName(), this.layout);
        customRemoteView.setTextViewText(this.layoutTitleId, this.notificationTitle);
        customRemoteView.setImageViewResource(this.layoutIconId, this.layoutIconDrawable);
        customRemoteView.setTextViewText(this.layoutContentId, alert);
        return customRemoteView;
    }

    public String toString() {
        return "custom_____" + toPreferenceString();
    }

    /* access modifiers changed from: 0000 */
    public String toPreferenceString() {
        return super.toPreferenceString() + "_____" + this.layout + "_____" + this.layoutIconId + "_____" + this.layoutTitleId + "_____" + this.layoutContentId + "_____" + this.layoutIconDrawable;
    }

    /* access modifiers changed from: 0000 */
    public void fromPreferenceParams(String[] params) throws NumberFormatException {
        super.fromPreferenceParams(params);
        this.layout = Integer.parseInt(params[5]);
        this.layoutIconId = Integer.parseInt(params[6]);
        this.layoutTitleId = Integer.parseInt(params[7]);
        this.layoutContentId = Integer.parseInt(params[8]);
        this.layoutIconDrawable = Integer.parseInt(params[9]);
    }
}
