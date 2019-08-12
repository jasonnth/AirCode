package com.kakao;

import com.kakao.KakaoParameterException.ERROR_CODE;
import com.kakao.internal.Action;
import com.kakao.internal.KakaoTalkLinkProtocol;
import com.kakao.internal.LinkObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

public class KakaoTalkLinkMessageBuilder {
    private final String appKey;
    private final String appVer;
    private final AtomicInteger buttonType = new AtomicInteger(0);
    private final String extra;
    private final AtomicInteger imageType = new AtomicInteger(0);
    private final List<LinkObject> linkObjList = new ArrayList();
    private final AtomicInteger linkType = new AtomicInteger(0);
    private final AtomicInteger textType = new AtomicInteger(0);

    KakaoTalkLinkMessageBuilder(String appKey2, String appVer2, String extra2) {
        this.appKey = appKey2;
        this.appVer = appVer2;
        this.extra = extra2;
    }

    public KakaoTalkLinkMessageBuilder addText(String text) throws KakaoParameterException {
        if (this.textType.getAndIncrement() == 1) {
            throw new KakaoParameterException(ERROR_CODE.DUPLICATE_OBJECTS_USED, "textType already added. each type can be added once, at most.");
        }
        this.linkObjList.add(LinkObject.newText(text));
        return this;
    }

    public KakaoTalkLinkMessageBuilder addImage(String src, int width, int height) throws KakaoParameterException {
        if (this.imageType.getAndIncrement() == 1) {
            throw new KakaoParameterException(ERROR_CODE.DUPLICATE_OBJECTS_USED, "imageType already added. each type can be added once, at most.");
        }
        this.linkObjList.add(LinkObject.newImage(src, width, height));
        return this;
    }

    public KakaoTalkLinkMessageBuilder addWebButton(String text, String url) throws KakaoParameterException {
        if (this.buttonType.getAndIncrement() == 1) {
            throw new KakaoParameterException(ERROR_CODE.DUPLICATE_OBJECTS_USED, "buttonType already added. each type can be added once, at most.");
        }
        this.linkObjList.add(LinkObject.newButton(text, Action.newActionWeb(url)));
        return this;
    }

    public String build() throws KakaoParameterException {
        try {
            if (this.linkObjList.isEmpty()) {
                throw new KakaoParameterException(ERROR_CODE.CORE_PARAMETER_MISSING, "call addAppLink or addWebLink or addAppButton or addWebButton or addText or addImage before calling build().");
            }
            StringBuilder talkLinkURL = new StringBuilder("kakaolink://send").append("?");
            talkLinkURL.append("linkver").append("=").append(URLEncoder.encode("3.5", KakaoTalkLinkProtocol.ENCODING)).append("&");
            talkLinkURL.append("apiver").append("=").append(URLEncoder.encode("3.0", KakaoTalkLinkProtocol.ENCODING)).append("&");
            talkLinkURL.append("appkey").append("=").append(URLEncoder.encode(this.appKey, KakaoTalkLinkProtocol.ENCODING)).append("&");
            talkLinkURL.append("appver").append("=").append(URLEncoder.encode(this.appVer, KakaoTalkLinkProtocol.ENCODING)).append("&");
            talkLinkURL.append("extras").append("=").append(URLEncoder.encode(this.extra, KakaoTalkLinkProtocol.ENCODING)).append("&");
            talkLinkURL.append("objs").append("=");
            JSONArray jsonArray = new JSONArray();
            for (LinkObject linkObject : this.linkObjList) {
                jsonArray.put(linkObject.createJSONObject());
            }
            return talkLinkURL.append(URLEncoder.encode(jsonArray.toString(), KakaoTalkLinkProtocol.ENCODING)).toString();
        } catch (UnsupportedEncodingException e) {
            throw new KakaoParameterException(ERROR_CODE.UNSUPPORTED_ENCODING, (Exception) e);
        } catch (JSONException e2) {
            throw new KakaoParameterException(ERROR_CODE.JSON_PARSING_ERROR, (Exception) e2);
        }
    }
}
