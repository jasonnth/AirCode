package com.appboy.models;

import android.graphics.Bitmap;
import android.net.Uri;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.enums.inappmessage.Orientation;
import java.util.Map;
import org.json.JSONObject;

public interface IInAppMessage extends IPutIntoJson<JSONObject> {
    boolean getAnimateIn();

    boolean getAnimateOut();

    int getBackgroundColor();

    Bitmap getBitmap();

    ClickAction getClickAction();

    CropType getCropType();

    DismissType getDismissType();

    int getDurationInMilliseconds();

    long getExpirationTimestamp();

    Map<String, String> getExtras();

    String getIcon();

    int getIconBackgroundColor();

    int getIconColor();

    boolean getImageDownloadSuccessful();

    String getLocalImageUrl();

    String getMessage();

    int getMessageTextColor();

    Orientation getOrientation();

    String getRemoteAssetPathForPrefetch();

    String getRemoteImageUrl();

    Uri getUri();

    boolean logClick();

    boolean logImpression();

    void onAfterClosed();

    void setAnimateIn(boolean z);

    void setAnimateOut(boolean z);

    void setBitmap(Bitmap bitmap);

    void setExpirationTimestamp(long j);

    void setImageDownloadSuccessful(boolean z);

    void setLocalAssetPathForPrefetch(String str);

    void setLocalImageUrl(String str);
}
