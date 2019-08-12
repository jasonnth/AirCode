package com.kakao.internal;

import android.text.TextUtils;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.kakao.KakaoParameterException;
import com.kakao.KakaoParameterException.ERROR_CODE;
import org.json.JSONException;
import org.json.JSONObject;

public final class LinkObject {
    private final Action action;
    private final int imageHeight;
    private final String imageSrc;
    private final int imageWidth;
    private final OBJTYPE objType;
    private final String text;

    public enum OBJTYPE {
        UNKNOWN("", false),
        TEXT("label", false),
        IMAGE(ContentFrameworkAnalytics.IMAGE, false),
        BUTTON("button", true),
        TEXT_LINK("link", true);
        
        /* access modifiers changed from: private */
        public final boolean actionable;
        /* access modifiers changed from: private */
        public final String value;

        private OBJTYPE(String value2, boolean actionable2) {
            this.value = value2;
            this.actionable = actionable2;
        }
    }

    private LinkObject(OBJTYPE objType2, String msg, String imageSrc2, int imageWidth2, int imageHeight2, Action action2) {
        this.objType = objType2;
        this.text = msg;
        this.imageSrc = imageSrc2;
        this.imageWidth = imageWidth2;
        this.imageHeight = imageHeight2;
        this.action = action2;
    }

    public static LinkObject newText(String text2) throws KakaoParameterException {
        if (!TextUtils.isEmpty(text2)) {
            return new LinkObject(OBJTYPE.TEXT, text2, null, 0, 0, null);
        }
        throw new KakaoParameterException(ERROR_CODE.CORE_PARAMETER_MISSING, "text type needs text.");
    }

    public static LinkObject newImage(String src, int width, int height) throws KakaoParameterException {
        if (TextUtils.isEmpty(src)) {
            throw new KakaoParameterException(ERROR_CODE.CORE_PARAMETER_MISSING, "image type needs src.");
        } else if (width <= 70) {
            throw new KakaoParameterException(ERROR_CODE.MINIMUM_IMAGE_SIZE_REQUIRED, "width of image type should be bigger than 70.");
        } else if (height > 70) {
            return new LinkObject(OBJTYPE.IMAGE, null, src, width, height, null);
        } else {
            throw new KakaoParameterException(ERROR_CODE.MINIMUM_IMAGE_SIZE_REQUIRED, "height of image type should be bigger than 70.");
        }
    }

    public static LinkObject newButton(String text2, Action action2) {
        return new LinkObject(OBJTYPE.BUTTON, text2, null, 0, 0, action2);
    }

    public JSONObject createJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("objtype", this.objType.value);
        if (!TextUtils.isEmpty(this.text)) {
            json.put("text", this.text);
        }
        if (!TextUtils.isEmpty(this.imageSrc) && this.objType == OBJTYPE.IMAGE) {
            json.put("src", this.imageSrc);
            if (this.imageWidth > 0) {
                json.put("width", this.imageWidth);
            }
            if (this.imageHeight > 0) {
                json.put("height", this.imageHeight);
            }
        }
        if (this.action != null && this.objType.actionable) {
            json.put("action", this.action.createJSONObject());
        }
        return json;
    }
}
