package com.tencent.p313mm.sdk.openapi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.tencent.p313mm.sdk.channel.MMessageActV2;
import com.tencent.p313mm.sdk.channel.compatible.MMessage;
import com.tencent.p313mm.sdk.channel.compatible.MMessage.Args;
import com.tencent.p313mm.sdk.channel.compatible.MMessageUtil;
import com.tencent.p313mm.sdk.modelbase.BaseReq;
import com.tencent.p313mm.sdk.modelbiz.AddCardToWXCardPackage;
import com.tencent.p313mm.sdk.modelbiz.CreateChatroom;
import com.tencent.p313mm.sdk.modelbiz.JoinChatroom;
import com.tencent.p313mm.sdk.modelbiz.OpenWebview;
import com.tencent.p313mm.sdk.modelmsg.GetMessageFromWX.Req;
import com.tencent.p313mm.sdk.modelmsg.LaunchFromWX;
import com.tencent.p313mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.p313mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.p313mm.sdk.modelmsg.ShowMessageFromWX;
import com.tencent.p313mm.sdk.modelpay.PayResp;

/* renamed from: com.tencent.mm.sdk.openapi.WXApiImplV10 */
final class WXApiImplV10 implements IWXAPI {
    private static String wxappPayEntryClassname = null;
    private String appId;
    private boolean checkSignature = false;
    private Context context;
    private boolean detached = false;

    WXApiImplV10(Context context2, String appId2, boolean checkSignature2) {
        Log.d("WXApiImplV10", "<init>, appId = " + appId2 + ", checkSignature = " + checkSignature2);
        this.context = context2;
        this.appId = appId2;
        this.checkSignature = checkSignature2;
    }

    public boolean registerApp(String appId2) {
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, ExternalAppUtils.WECHAT_APP_ID, this.checkSignature)) {
            Log.e("WXApiImplV10", "register app failed for wechat app signature check failed");
            return false;
        } else {
            Log.d("WXApiImplV10", "registerApp, appId = " + appId2);
            if (appId2 != null) {
                this.appId = appId2;
            }
            Log.d("WXApiImplV10", "register app " + this.context.getPackageName());
            Args args = new Args();
            args.targetPkg = ExternalAppUtils.WECHAT_APP_ID;
            args.action = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER";
            args.content = "weixin://registerapp?appid=" + this.appId;
            return MMessage.send(this.context, args);
        }
    }

    public boolean handleIntent(Intent data, IWXAPIEventHandler handler) {
        try {
            if (!WXApiImplComm.isIntentFromWx(data, "com.tencent.mm.openapi.token")) {
                Log.i("WXApiImplV10", "handleIntent fail, intent not from weixin msg");
                return false;
            } else if (this.detached) {
                throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
            } else {
                String content = data.getStringExtra("_mmessage_content");
                int sdkVersion = data.getIntExtra("_mmessage_sdkVersion", 0);
                String packageName = data.getStringExtra("_mmessage_appPackage");
                if (packageName == null || packageName.length() == 0) {
                    Log.e("WXApiImplV10", "invalid argument");
                    return false;
                } else if (!checkSumConsistent(data.getByteArrayExtra("_mmessage_checksum"), MMessageUtil.genCheckSum(content, sdkVersion, packageName))) {
                    Log.e("WXApiImplV10", "checksum fail");
                    return false;
                } else {
                    int cmd = data.getIntExtra("_wxapi_command_type", 0);
                    switch (cmd) {
                        case 1:
                            handler.onResp(new Resp(data.getExtras()));
                            return true;
                        case 2:
                            handler.onResp(new SendMessageToWX.Resp(data.getExtras()));
                            return true;
                        case 3:
                            handler.onReq(new Req(data.getExtras()));
                            return true;
                        case 4:
                            handler.onReq(new ShowMessageFromWX.Req(data.getExtras()));
                            return true;
                        case 5:
                            handler.onResp(new PayResp(data.getExtras()));
                            return true;
                        case 6:
                            handler.onReq(new LaunchFromWX.Req(data.getExtras()));
                            return true;
                        case 9:
                            handler.onResp(new AddCardToWXCardPackage.Resp(data.getExtras()));
                            return true;
                        case 12:
                            handler.onResp(new OpenWebview.Resp(data.getExtras()));
                            return true;
                        case 14:
                            handler.onResp(new CreateChatroom.Resp(data.getExtras()));
                            return true;
                        case 15:
                            handler.onResp(new JoinChatroom.Resp(data.getExtras()));
                            return true;
                        default:
                            Log.e("WXApiImplV10", "unknown cmd = " + cmd);
                            return false;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("WXApiImplV10", "handleIntent fail, ex = %s" + e.getMessage());
            return false;
        }
    }

    public boolean sendReq(BaseReq req) {
        if (this.detached) {
            throw new IllegalStateException("sendReq fail, WXMsgImpl has been detached");
        } else if (!WXApiImplComm.validateAppSignatureForPackage(this.context, ExternalAppUtils.WECHAT_APP_ID, this.checkSignature)) {
            Log.e("WXApiImplV10", "sendReq failed for wechat app signature check failed");
            return false;
        } else if (!req.checkArgs()) {
            Log.e("WXApiImplV10", "sendReq checkArgs fail");
            return false;
        } else {
            Log.d("WXApiImplV10", "sendReq, req type = " + req.getType());
            Bundle data = new Bundle();
            req.toBundle(data);
            if (req.getType() == 5) {
                return sendPayReq(this.context, data);
            }
            if (req.getType() == 7) {
                return sendJumpToBizProfileReq(this.context, data);
            }
            if (req.getType() == 8) {
                return sendJumpToBizWebviewReq(this.context, data);
            }
            if (req.getType() == 10) {
                return sendJumpToBizTempSessionReq(this.context, data);
            }
            if (req.getType() == 9) {
                return sendAddCardToWX(this.context, data);
            }
            if (req.getType() == 11) {
                return sendOpenRankListReq(this.context, data);
            }
            if (req.getType() == 12) {
                return sendOpenWebview(this.context, data);
            }
            if (req.getType() == 13) {
                return sendOpenBusiLuckyMoney(this.context, data);
            }
            if (req.getType() == 14) {
                return createChatroom(this.context, data);
            }
            if (req.getType() == 15) {
                return joinChatroom(this.context, data);
            }
            MMessageActV2.Args args = new MMessageActV2.Args();
            args.bundle = data;
            args.content = "weixin://sendreq?appid=" + this.appId;
            args.targetPkgName = ExternalAppUtils.WECHAT_APP_ID;
            args.targetClassName = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            return MMessageActV2.send(this.context, args);
        }
    }

    private boolean sendPayReq(Context context2, Bundle data) {
        if (wxappPayEntryClassname == null) {
            wxappPayEntryClassname = new MMSharedPreferences(context2).getString("_wxapp_pay_entry_classname_", null);
            Log.d("WXApiImplV10", "pay, set wxappPayEntryClassname = " + wxappPayEntryClassname);
            if (wxappPayEntryClassname == null) {
                Log.e("WXApiImplV10", "pay fail, wxappPayEntryClassname is null");
                return false;
            }
        }
        MMessageActV2.Args args = new MMessageActV2.Args();
        args.bundle = data;
        args.targetPkgName = ExternalAppUtils.WECHAT_APP_ID;
        args.targetClassName = wxappPayEntryClassname;
        return MMessageActV2.send(context2, args);
    }

    private boolean sendJumpToBizProfileReq(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[]{this.appId, data.getString("_wxapi_jump_to_biz_profile_req_to_user_name"), data.getString("_wxapi_jump_to_biz_profile_req_ext_msg"), data.getInt("_wxapi_jump_to_biz_profile_req_scene") + "", data.getInt("_wxapi_jump_to_biz_profile_req_profile_type") + ""}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean sendJumpToBizWebviewReq(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[]{this.appId, data.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), data.getString("_wxapi_jump_to_biz_webview_req_ext_msg"), data.getInt("_wxapi_jump_to_biz_webview_req_scene") + ""}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean sendJumpToBizTempSessionReq(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/jumpToBizTempSession"), null, null, new String[]{this.appId, data.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), data.getString("_wxapi_jump_to_biz_webview_req_session_from"), "" + data.getInt("_wxapi_jump_to_biz_webview_req_show_type")}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean sendAddCardToWX(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/addCardToWX"), null, null, new String[]{this.appId, data.getString("_wxapi_add_card_to_wx_card_list"), data.getString("_wxapi_basereq_transaction")}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean sendOpenRankListReq(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/openRankList"), null, null, new String[0], null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean sendOpenWebview(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/openWebview"), null, null, new String[]{this.appId, data.getString("_wxapi_jump_to_webview_url"), data.getString("_wxapi_basereq_transaction")}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean sendOpenBusiLuckyMoney(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/openBusiLuckyMoney"), null, null, new String[]{this.appId, data.getString("_wxapi_open_busi_lucky_money_timeStamp"), data.getString("_wxapi_open_busi_lucky_money_nonceStr"), data.getString("_wxapi_open_busi_lucky_money_signType"), data.getString("_wxapi_open_busi_lucky_money_signature"), data.getString("_wxapi_open_busi_lucky_money_package")}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean createChatroom(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/createChatroom"), null, null, new String[]{this.appId, data.getString("_wxapi_basereq_transaction"), data.getString("_wxapi_create_chatroom_group_id"), data.getString("_wxapi_create_chatroom_chatroom_name"), data.getString("_wxapi_create_chatroom_chatroom_nickname"), data.getString("_wxapi_create_chatroom_ext_msg")}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean joinChatroom(Context context2, Bundle data) {
        Cursor cursor = context2.getContentResolver().query(Uri.parse("content://com.tencent.mm.tencent.mm.tencent.mm.sdk.comm.provider/joinChatroom"), null, null, new String[]{this.appId, data.getString("_wxapi_basereq_transaction"), data.getString("_wxapi_join_chatroom_group_id"), data.getString("_wxapi_join_chatroom_chatroom_nickname"), data.getString("_wxapi_join_chatroom_ext_msg")}, null);
        if (cursor != null) {
            cursor.close();
        }
        return true;
    }

    private boolean checkSumConsistent(byte[] src, byte[] gen) {
        if (src == null || src.length == 0 || gen == null || gen.length == 0) {
            Log.e("WXApiImplV10", "checkSumConsistent fail, invalid arguments");
            return false;
        } else if (src.length != gen.length) {
            Log.e("WXApiImplV10", "checkSumConsistent fail, length is different");
            return false;
        } else {
            for (int i = 0; i < src.length; i++) {
                if (src[i] != gen[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
