package com.appboy.models;

import android.graphics.Bitmap;
import android.net.Uri;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.enums.inappmessage.Orientation;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0375bd;
import p004bo.app.C0386bo;
import p004bo.app.C0397bz;
import p004bo.app.C0460ds;
import p004bo.app.C0495ex;
import p004bo.app.C0497ez;

public abstract class InAppMessageBase implements IInAppMessage {

    /* renamed from: a */
    public static final String f2789a = AppboyLogger.getAppboyLogTag(InAppMessageBase.class);

    /* renamed from: A */
    private boolean f2790A;

    /* renamed from: B */
    private String f2791B;

    /* renamed from: C */
    private long f2792C;

    /* renamed from: b */
    public String f2793b;

    /* renamed from: c */
    public String f2794c;

    /* renamed from: d */
    public String f2795d;

    /* renamed from: e */
    protected CropType f2796e;

    /* renamed from: f */
    protected TextAlign f2797f;

    /* renamed from: g */
    protected JSONObject f2798g;

    /* renamed from: h */
    public C0375bd f2799h;

    /* renamed from: i */
    private String f2800i;

    /* renamed from: j */
    private Map<String, String> f2801j;

    /* renamed from: k */
    private boolean f2802k;

    /* renamed from: l */
    private boolean f2803l;

    /* renamed from: m */
    private ClickAction f2804m;

    /* renamed from: n */
    private Uri f2805n;

    /* renamed from: o */
    private DismissType f2806o;

    /* renamed from: p */
    private int f2807p;

    /* renamed from: q */
    private int f2808q;

    /* renamed from: r */
    private int f2809r;

    /* renamed from: s */
    private int f2810s;

    /* renamed from: t */
    private int f2811t;

    /* renamed from: u */
    private String f2812u;

    /* renamed from: v */
    private String f2813v;

    /* renamed from: w */
    private Orientation f2814w;

    /* renamed from: x */
    private Bitmap f2815x;

    /* renamed from: y */
    private boolean f2816y;

    /* renamed from: z */
    private boolean f2817z;

    protected InAppMessageBase() {
        this.f2802k = true;
        this.f2803l = true;
        this.f2804m = ClickAction.NONE;
        this.f2806o = DismissType.AUTO_DISMISS;
        this.f2807p = 5000;
        this.f2808q = 0;
        this.f2809r = 0;
        this.f2810s = 0;
        this.f2811t = 0;
        this.f2814w = Orientation.ANY;
        this.f2816y = false;
        this.f2796e = CropType.FIT_CENTER;
        this.f2797f = TextAlign.CENTER;
        this.f2817z = false;
        this.f2790A = false;
        this.f2792C = -1;
    }

    public InAppMessageBase(JSONObject object, C0375bd appboyManager) {
        this(object.optString("message"), C0460ds.m530a(object.optJSONObject("extras"), (Map<String, String>) new HashMap<String,String>()), object.optBoolean("animate_in", true), object.optBoolean("animate_out", true), (ClickAction) C0460ds.m528a(object, "click_action", ClickAction.class, ClickAction.NONE), object.optString("uri"), object.optInt("bg_color"), object.optInt("icon_color"), object.optInt("icon_bg_color"), object.optInt("text_color"), object.optString("icon"), object.optString("image_url"), (DismissType) C0460ds.m528a(object, "message_close", DismissType.class, DismissType.AUTO_DISMISS), object.optInt("duration"), object.optString("campaign_id"), object.optString("card_id"), object.optString("trigger_id"), false, false, (Orientation) C0460ds.m528a(object, "orientation", Orientation.class, Orientation.ANY), object, appboyManager);
    }

    private InAppMessageBase(String message, Map<String, String> extras, boolean animateIn, boolean animateOut, ClickAction clickAction, String uri, int backgroundColor, int iconColor, int iconBackgroundColor, int messageTextColor, String icon, String remoteImageUrl, DismissType dismissType, int durationInMilliseconds, String campaignId, String cardId, String triggerId, boolean impressionLogged, boolean clickLogged, Orientation orientation, JSONObject object, C0375bd appboyManager) {
        this.f2802k = true;
        this.f2803l = true;
        this.f2804m = ClickAction.NONE;
        this.f2806o = DismissType.AUTO_DISMISS;
        this.f2807p = 5000;
        this.f2808q = 0;
        this.f2809r = 0;
        this.f2810s = 0;
        this.f2811t = 0;
        this.f2814w = Orientation.ANY;
        this.f2816y = false;
        this.f2796e = CropType.FIT_CENTER;
        this.f2797f = TextAlign.CENTER;
        this.f2817z = false;
        this.f2790A = false;
        this.f2792C = -1;
        this.f2800i = message;
        this.f2801j = extras;
        this.f2802k = animateIn;
        this.f2803l = animateOut;
        this.f2804m = clickAction;
        if (this.f2804m == ClickAction.URI && !StringUtils.isNullOrBlank(uri)) {
            this.f2805n = Uri.parse(uri);
        }
        if (dismissType == DismissType.SWIPE) {
            this.f2806o = DismissType.MANUAL;
        } else {
            this.f2806o = dismissType;
        }
        setDurationInMilliseconds(durationInMilliseconds);
        this.f2808q = backgroundColor;
        this.f2810s = iconColor;
        this.f2811t = iconBackgroundColor;
        this.f2809r = messageTextColor;
        this.f2812u = icon;
        this.f2813v = remoteImageUrl;
        this.f2814w = orientation;
        this.f2793b = campaignId;
        this.f2794c = cardId;
        this.f2795d = triggerId;
        this.f2817z = impressionLogged;
        this.f2790A = clickLogged;
        this.f2798g = object;
        this.f2799h = appboyManager;
    }

    public String getMessage() {
        return this.f2800i;
    }

    public Map<String, String> getExtras() {
        return this.f2801j;
    }

    public int getDurationInMilliseconds() {
        return this.f2807p;
    }

    public int getBackgroundColor() {
        return this.f2808q;
    }

    public int getIconColor() {
        return this.f2810s;
    }

    public int getIconBackgroundColor() {
        return this.f2811t;
    }

    public int getMessageTextColor() {
        return this.f2809r;
    }

    public String getIcon() {
        return this.f2812u;
    }

    public String getRemoteImageUrl() {
        return this.f2813v;
    }

    public String getLocalImageUrl() {
        return this.f2791B;
    }

    public boolean getAnimateIn() {
        return this.f2802k;
    }

    public boolean getAnimateOut() {
        return this.f2803l;
    }

    public ClickAction getClickAction() {
        return this.f2804m;
    }

    public Uri getUri() {
        return this.f2805n;
    }

    public Bitmap getBitmap() {
        return this.f2815x;
    }

    public DismissType getDismissType() {
        return this.f2806o;
    }

    public boolean getImageDownloadSuccessful() {
        return this.f2816y;
    }

    public String getRemoteAssetPathForPrefetch() {
        return getRemoteImageUrl();
    }

    public Orientation getOrientation() {
        return this.f2814w;
    }

    public CropType getCropType() {
        return this.f2796e;
    }

    public TextAlign getMessageTextAlign() {
        return this.f2797f;
    }

    public long getExpirationTimestamp() {
        return this.f2792C;
    }

    public void setExpirationTimestamp(long timestamp) {
        this.f2792C = timestamp;
    }

    public void setAnimateIn(boolean animateIn) {
        this.f2802k = animateIn;
    }

    public void setAnimateOut(boolean animateOut) {
        this.f2803l = animateOut;
    }

    public void setDurationInMilliseconds(int durationInMilliseconds) {
        if (durationInMilliseconds < 999) {
            this.f2807p = 5000;
            AppboyLogger.m1739w(f2789a, "Requested in-app message duration " + durationInMilliseconds + " is lower than the minimum of " + 999 + ". Defaulting to " + this.f2807p + " milliseconds.");
            return;
        }
        this.f2807p = durationInMilliseconds;
        AppboyLogger.m1733d(f2789a, "Set in-app message duration to " + this.f2807p + " milliseconds.");
    }

    public void setLocalImageUrl(String localImageUrl) {
        this.f2791B = localImageUrl;
    }

    public void setLocalAssetPathForPrefetch(String localUri) {
        setLocalImageUrl(localUri);
    }

    public void setBitmap(Bitmap bitmap) {
        this.f2815x = bitmap;
    }

    public void setImageDownloadSuccessful(boolean imageDownloadSuccessful) {
        this.f2816y = imageDownloadSuccessful;
    }

    public boolean logImpression() {
        if (StringUtils.isNullOrEmpty(this.f2793b) && StringUtils.isNullOrEmpty(this.f2794c) && StringUtils.isNullOrEmpty(this.f2795d)) {
            AppboyLogger.m1733d(f2789a, "Campaign, card, and trigger Ids not found. Not logging in-app message impression.");
            return false;
        } else if (this.f2817z) {
            AppboyLogger.m1737i(f2789a, "Impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.f2799h == null) {
            AppboyLogger.m1735e(f2789a, "Cannot log an in-app message impression because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.f2799h.mo6769a((C0386bo) C0397bz.m296b(this.f2793b, this.f2794c, this.f2795d));
                this.f2817z = true;
                return true;
            } catch (JSONException e) {
                this.f2799h.mo6767a((Throwable) e);
                return false;
            }
        }
    }

    public boolean logClick() {
        if (StringUtils.isNullOrBlank(this.f2793b) && StringUtils.isNullOrBlank(this.f2794c) && StringUtils.isNullOrBlank(this.f2795d)) {
            AppboyLogger.m1733d(f2789a, "Campaign, card, and trigger Ids not found. Not logging in-app message click.");
            return false;
        } else if (this.f2790A) {
            AppboyLogger.m1737i(f2789a, "Click already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.f2799h == null) {
            AppboyLogger.m1735e(f2789a, "Cannot log an in-app message click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.f2799h.mo6769a((C0386bo) C0397bz.m301c(this.f2793b, this.f2794c, this.f2795d));
                this.f2790A = true;
                return true;
            } catch (JSONException e) {
                this.f2799h.mo6767a((Throwable) e);
                return false;
            }
        }
    }

    public void onAfterClosed() {
        if (this.f2790A && !StringUtils.isNullOrEmpty(this.f2795d)) {
            this.f2799h.mo6765a((C0495ex) new C0497ez(this.f2795d));
        }
    }

    public JSONObject forJsonPut() {
        if (this.f2798g != null) {
            return this.f2798g;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("message", this.f2800i);
            jSONObject.put("duration", this.f2807p);
            jSONObject.putOpt("campaign_id", this.f2793b);
            jSONObject.putOpt("card_id", this.f2794c);
            jSONObject.putOpt("trigger_id", this.f2795d);
            jSONObject.putOpt("click_action", this.f2804m.toString());
            jSONObject.putOpt("message_close", this.f2806o.toString());
            if (this.f2805n != null) {
                jSONObject.put("uri", this.f2805n.toString());
            }
            jSONObject.put("animate_in", this.f2802k);
            jSONObject.put("animate_out", this.f2803l);
            jSONObject.put("bg_color", this.f2808q);
            jSONObject.put("text_color", this.f2809r);
            jSONObject.put("icon_color", this.f2810s);
            jSONObject.put("icon_bg_color", this.f2811t);
            jSONObject.putOpt("icon", this.f2812u);
            jSONObject.putOpt("image_url", this.f2813v);
            jSONObject.putOpt("crop_type", this.f2796e.toString());
            jSONObject.putOpt("orientation", this.f2814w.toString());
            jSONObject.putOpt("text_align_message", this.f2797f.toString());
            if (this.f2801j != null) {
                JSONObject jSONObject2 = new JSONObject();
                for (String str : this.f2801j.keySet()) {
                    jSONObject2.put(str, this.f2801j.get(str));
                }
                jSONObject.put("extras", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
