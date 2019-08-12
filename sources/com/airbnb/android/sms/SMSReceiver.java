package com.airbnb.android.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import p032rx.Observable;
import p032rx.subjects.PublishSubject;

class SMSReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private final Context context;
    private final PublishSubject<String> newSmsSubject = PublishSubject.create();

    SMSReceiver(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: 0000 */
    public Observable<String> register() {
        return this.newSmsSubject.asObservable().doOnSubscribe(SMSReceiver$$Lambda$1.lambdaFactory$(this)).doOnUnsubscribe(SMSReceiver$$Lambda$4.lambdaFactory$(this));
    }

    public void onReceive(Context context2, Intent intent) {
        Observable filter = Observable.fromCallable(SMSReceiver$$Lambda$5.lambdaFactory$(intent)).filter(SMSReceiver$$Lambda$6.lambdaFactory$());
        PublishSubject<String> publishSubject = this.newSmsSubject;
        publishSubject.getClass();
        filter.subscribe(SMSReceiver$$Lambda$7.lambdaFactory$(publishSubject), SMSReceiver$$Lambda$8.lambdaFactory$());
    }

    static /* synthetic */ String lambda$onReceive$2(Intent intent) throws Exception {
        String action = intent.getAction();
        StringBuilder sb = new StringBuilder();
        if (action.equals(SMS_RECEIVED_ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdusData = (Object[]) bundle.get("pdus");
                SmsMessage[] msg = new SmsMessage[pdusData.length];
                for (int i = 0; i < msg.length; i++) {
                    msg[i] = SmsMessage.createFromPdu((byte[]) pdusData[i]);
                }
                for (SmsMessage temp : msg) {
                    sb.append(temp.getMessageBody());
                }
            }
        }
        return sb.toString();
    }

    static /* synthetic */ Boolean lambda$onReceive$3(String body) {
        return Boolean.valueOf(!TextUtils.isEmpty(body));
    }

    static /* synthetic */ void lambda$onReceive$4(Throwable error) {
    }
}
