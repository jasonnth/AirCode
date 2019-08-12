package com.airbnb.android.lib.share;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.events.WechatShareTripFinishedEvent;
import com.airbnb.android.utils.Strap;
import java.io.IOException;

class ShareYourTripAnalytics extends BaseAnalytics {
    static final String ADD_MESSAGE = "add_message";
    static final String ADD_PHOTO = "add_photo";
    private static final String ADD_PHOTO_RESULT = "add_photo_result";
    static final String DELETE_PHTOO = "delete_photo";
    private static final String EVENT_NAME = "share_trip";
    static final String GENERATE_PHOTO_DURATION = "generate_photo_duration";
    private static final String GENERATE_PHOTO_FAILED = "generate_photo_failed";
    static final String KEY_ENTRY_POINT = "entry_point";
    private static final String KEY_ERROR_CODE = "error_code";
    private static final String KEY_HAS_MESSAGE = "has_message";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PHOTO_COUNT = "photo_count";
    static final String KEY_RESERVATION_ID = "reservation_id";
    private static final String KEY_SELECTED_PHOTO_COUNT = "selected_photo_count";
    private static final String MAX_PHOTO_TOAST = "max_photo_toast";
    private static final String SHARE_FINISHED = "share_finished";
    private static final String SHARE_TO_WECHAT = "share_to_wechat";
    static final String SHARE_TO_WECHAT_DURATION = "share_to_wechat_duration";
    private final String entityId;
    private final EntryPoint entryPoint;
    private boolean hasMessage;
    private int photoCount;

    public enum EntryPoint {
        PushNotification,
        Itinerary,
        Debug,
        Unknown
    }

    ShareYourTripAnalytics(EntryPoint entryPoint2, String entityId2) {
        this.entryPoint = entryPoint2;
        this.entityId = entityId2;
    }

    /* access modifiers changed from: 0000 */
    public void setPhotoCount(int photoCount2) {
        this.photoCount = photoCount2;
    }

    /* access modifiers changed from: 0000 */
    public void setHasMessage(boolean hasMessage2) {
        this.hasMessage = hasMessage2;
    }

    public void trackClickEvent(String target) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, target).mix(getCommonParams()));
    }

    /* access modifiers changed from: 0000 */
    public void trackPhotoSelected(int photoCount2) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, ADD_PHOTO_RESULT).mo11637kv(KEY_SELECTED_PHOTO_COUNT, photoCount2).mix(getCommonParams()));
    }

    /* access modifiers changed from: 0000 */
    public void trackMaxPhotoToastImpression() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "impression").mo11639kv(BaseAnalytics.TARGET, MAX_PHOTO_TOAST).mix(getCommonParams()));
    }

    /* access modifiers changed from: 0000 */
    public void trackGeneratePhotoFailed(IOException error) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, GENERATE_PHOTO_FAILED).mo11639kv("error", error.getMessage()).mix(getCommonParams()));
    }

    /* access modifiers changed from: 0000 */
    public void trackOperationDuration(String operation, long durationMs) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, operation).mo11638kv("value", durationMs).mix(getCommonParams()));
    }

    /* access modifiers changed from: 0000 */
    public void trackShareSubmit(String message) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, SHARE_TO_WECHAT).mo11639kv("message", message).mix(getCommonParams()));
    }

    /* access modifiers changed from: 0000 */
    public void trackShareResult(WechatShareTripFinishedEvent event) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, SHARE_FINISHED).mo11640kv("success", event.success).mo11637kv("error_code", event.errorCode).mo11639kv("error", event.errorString).mix(getCommonParams()));
    }

    private Strap getCommonParams() {
        return Strap.make().mo11639kv("reservation_id", this.entityId).mo11639kv("entry_point", this.entryPoint.name()).mo11637kv(KEY_PHOTO_COUNT, this.photoCount).mo11640kv(KEY_HAS_MESSAGE, this.hasMessage);
    }
}
