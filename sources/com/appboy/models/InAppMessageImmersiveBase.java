package com.appboy.models;

import com.airbnb.android.itinerary.TripEventModel;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0375bd;
import p004bo.app.C0386bo;
import p004bo.app.C0397bz;
import p004bo.app.C0460ds;
import p004bo.app.C0495ex;
import p004bo.app.C0497ez;

public abstract class InAppMessageImmersiveBase extends InAppMessageBase implements IInAppMessageImmersive {

    /* renamed from: i */
    protected ImageStyle f2822i;

    /* renamed from: j */
    private String f2823j;

    /* renamed from: k */
    private int f2824k;

    /* renamed from: l */
    private int f2825l;

    /* renamed from: m */
    private List<MessageButton> f2826m;

    /* renamed from: n */
    private Integer f2827n;

    /* renamed from: o */
    private TextAlign f2828o;

    /* renamed from: p */
    private boolean f2829p;

    /* renamed from: q */
    private String f2830q;

    protected InAppMessageImmersiveBase() {
        this.f2824k = 0;
        this.f2825l = 0;
        this.f2822i = ImageStyle.TOP;
        this.f2827n = null;
        this.f2828o = TextAlign.CENTER;
        this.f2830q = null;
    }

    public InAppMessageImmersiveBase(JSONObject object, C0375bd appboyManager) {
        this(object, appboyManager, object.optString(TripEventModel.HEADER), object.optInt("header_text_color"), object.optInt("close_btn_color"), (ImageStyle) C0460ds.m528a(object, "image_style", ImageStyle.class, ImageStyle.TOP), (TextAlign) C0460ds.m528a(object, "text_align_header", TextAlign.class, TextAlign.CENTER), (TextAlign) C0460ds.m528a(object, "text_align_message", TextAlign.class, TextAlign.CENTER));
        if (object.optJSONArray("btns") != null) {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = object.optJSONArray("btns");
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new MessageButton(optJSONArray.optJSONObject(i)));
            }
            setMessageButtons(arrayList);
        }
    }

    private InAppMessageImmersiveBase(JSONObject object, C0375bd appboyManager, String header, int headerTextColor, int closeButtonColor, ImageStyle imageStyle, TextAlign headerTextAlign, TextAlign messageTextAlign) {
        super(object, appboyManager);
        this.f2824k = 0;
        this.f2825l = 0;
        this.f2822i = ImageStyle.TOP;
        this.f2827n = null;
        this.f2828o = TextAlign.CENTER;
        this.f2830q = null;
        this.f2823j = header;
        this.f2824k = headerTextColor;
        this.f2825l = closeButtonColor;
        if (object.has("frame_color")) {
            this.f2827n = Integer.valueOf(object.optInt("frame_color"));
        }
        this.f2822i = imageStyle;
        this.f2828o = headerTextAlign;
        this.f2797f = messageTextAlign;
    }

    public String getHeader() {
        return this.f2823j;
    }

    public int getHeaderTextColor() {
        return this.f2824k;
    }

    public int getCloseButtonColor() {
        return this.f2825l;
    }

    public List<MessageButton> getMessageButtons() {
        return this.f2826m;
    }

    public Integer getFrameColor() {
        return this.f2827n;
    }

    public ImageStyle getImageStyle() {
        return this.f2822i;
    }

    public TextAlign getHeaderTextAlign() {
        return this.f2828o;
    }

    public void setMessageButtons(List<MessageButton> messageButtons) {
        this.f2826m = messageButtons;
    }

    public boolean logButtonClick(MessageButton messageButton) {
        if (StringUtils.isNullOrBlank(this.f2793b) && StringUtils.isNullOrBlank(this.f2794c) && StringUtils.isNullOrBlank(this.f2795d)) {
            AppboyLogger.m1733d(f2789a, "Campaign, trigger, and card Ids not found. Not logging button click.");
            return false;
        } else if (messageButton == null) {
            AppboyLogger.m1739w(f2789a, "Message button was null. Ignoring button click.");
            return false;
        } else if (this.f2829p) {
            AppboyLogger.m1737i(f2789a, "Button click already logged for this message. Ignoring.");
            return false;
        } else if (this.f2799h == null) {
            AppboyLogger.m1735e(f2789a, "Cannot log a button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                C0397bz a = C0397bz.m285a(this.f2793b, this.f2794c, this.f2795d, messageButton);
                this.f2830q = C0397bz.m291a(messageButton);
                this.f2799h.mo6769a((C0386bo) a);
                this.f2829p = true;
                return true;
            } catch (JSONException e) {
                this.f2799h.mo6767a((Throwable) e);
                return false;
            }
        }
    }

    public void onAfterClosed() {
        super.onAfterClosed();
        if (this.f2829p && !StringUtils.isNullOrBlank(this.f2795d) && !StringUtils.isNullOrBlank(this.f2830q)) {
            this.f2799h.mo6765a((C0495ex) new C0497ez(this.f2795d, this.f2830q));
        }
    }

    public JSONObject forJsonPut() {
        if (this.f2798g != null) {
            return this.f2798g;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.putOpt(TripEventModel.HEADER, this.f2823j);
            forJsonPut.put("header_text_color", this.f2824k);
            forJsonPut.put("close_btn_color", this.f2825l);
            forJsonPut.putOpt("image_style", this.f2822i.toString());
            forJsonPut.putOpt("text_align_header", this.f2828o.toString());
            if (this.f2827n != null) {
                forJsonPut.put("frame_color", this.f2827n.intValue());
            }
            if (this.f2826m != null) {
                JSONArray jSONArray = new JSONArray();
                for (MessageButton forJsonPut2 : this.f2826m) {
                    jSONArray.put(forJsonPut2.forJsonPut());
                }
                forJsonPut.put("btns", jSONArray);
            }
            return forJsonPut;
        } catch (JSONException e) {
            return null;
        }
    }
}
