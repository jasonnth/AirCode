package com.airbnb.android.wxapi;

import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.SignUpLoginAnalytics;
import com.airbnb.android.core.enums.AuthorizeService;
import com.airbnb.android.core.events.WechatLoginAuthCodeEvent;
import com.airbnb.android.core.events.WechatLoginFailedEvent;
import com.airbnb.android.core.events.WechatShareTripFinishedEvent;
import com.airbnb.android.core.utils.WeChatHelper;
import com.tencent.p313mm.sdk.modelbase.BaseReq;
import com.tencent.p313mm.sdk.modelbase.BaseResp;
import com.tencent.p313mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.p313mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.p313mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends AirActivity implements IWXAPIEventHandler {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXAPIFactory.createWXAPI(this, WeChatHelper.getWeChatID(this), true).handleIntent(getIntent(), this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        if (!WeChatHelper.WECHAT_LOGIN.equals(baseResp.transaction)) {
            if (WeChatHelper.WECHAT_SHARE_TRIP.equals(baseResp.transaction)) {
                switch (baseResp.errCode) {
                    case 0:
                        this.bus.post(WechatShareTripFinishedEvent.newSuccessEvent());
                        break;
                    default:
                        this.bus.post(WechatShareTripFinishedEvent.newFailureEvent(baseResp.errCode, baseResp.errStr));
                        break;
                }
            }
        } else {
            switch (baseResp.errCode) {
                case -4:
                    SignUpLoginAnalytics.trackAuthDeny(AuthorizeService.WECHAT);
                    this.bus.post(new WechatLoginFailedEvent());
                    break;
                case -2:
                    SignUpLoginAnalytics.trackAuthCancel(AuthorizeService.WECHAT);
                    this.bus.post(new WechatLoginFailedEvent());
                    break;
                case 0:
                    if (!WeChatHelper.WECHAT_LOGIN_IDENTIFIER.equals(((Resp) baseResp).state)) {
                        String unauthorizedState = ((Resp) baseResp).state;
                        SignUpLoginAnalytics.trackAuthFail(AuthorizeService.WECHAT, unauthorizedState);
                        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unauthroized wechat login launch " + unauthorizedState));
                        break;
                    } else {
                        SignUpLoginAnalytics.trackAuthSuccess(AuthorizeService.WECHAT);
                        this.bus.post(new WechatLoginAuthCodeEvent(((Resp) baseResp).code));
                        break;
                    }
                default:
                    this.bus.post(new WechatLoginFailedEvent());
                    SignUpLoginAnalytics.trackAuthFail(AuthorizeService.WECHAT, "Error Code: " + baseResp.errCode + " Error Message: " + baseResp.errStr);
                    break;
            }
        }
        finish();
    }
}
