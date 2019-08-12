package com.airbnb.android.sms;

import android.content.Context;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.Experiments;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p032rx.Observable;
import p032rx.subjects.BehaviorSubject;

public class SMSMonitor {
    private static final int RC_PERMISSION_RECEIVE_SMS = 10086;
    private final BehaviorSubject<Boolean> permissionSubject = BehaviorSubject.create();
    private final BehaviorSubject<Boolean> permissionSubjectInternal = BehaviorSubject.create();
    private final SMSReceiver receiver;

    private interface Extractor {
        String extract(String str);
    }

    public enum Type {
        Verification_Code(new VerificationCodeExtractor());
        
        private final Extractor extractor;

        private Type(Extractor extractor2) {
            this.extractor = extractor2;
        }

        public String extract(String body) {
            return this.extractor.extract(body);
        }
    }

    private static class VerificationCodeExtractor implements Extractor {
        private VerificationCodeExtractor() {
        }

        public String extract(String body) {
            Matcher matcher = Pattern.compile("(?<![0-9])([0-9]{4})(?![0-9])").matcher(body);
            if (matcher.find()) {
                return matcher.group(0);
            }
            return null;
        }
    }

    public SMSMonitor(Context context) {
        this.receiver = new SMSReceiver(context);
    }

    public Observable<String> listen(Type type, Fragment fragment) {
        this.permissionSubjectInternal.onNext(Boolean.valueOf(checkPermission(fragment)));
        return this.permissionSubjectInternal.asObservable().filter(SMSMonitor$$Lambda$1.lambdaFactory$()).switchMap(SMSMonitor$$Lambda$2.lambdaFactory$(this, fragment)).map(SMSMonitor$$Lambda$3.lambdaFactory$(fragment, type)).takeFirst(SMSMonitor$$Lambda$4.lambdaFactory$()).filter(SMSMonitor$$Lambda$5.lambdaFactory$(fragment));
    }

    static /* synthetic */ Boolean lambda$listen$0(Boolean granted) {
        return granted;
    }

    static /* synthetic */ Boolean lambda$listen$3(String result) {
        return Boolean.valueOf(!TextUtils.isEmpty(result));
    }

    static /* synthetic */ Boolean lambda$listen$4(Fragment fragment, String result) {
        SMSMonitorAnalytics.trackCodeExtracted(fragment.getClass().getSimpleName());
        if (Trebuchet.launch(TrebuchetKeys.EXPOSE_SMS_CODE_EXPERIMENT, false)) {
            return Boolean.valueOf(Experiments.autofillExtractedCode());
        }
        return Boolean.valueOf(true);
    }

    public Observable<Boolean> permission(Fragment fragment) {
        if (checkPermission(fragment)) {
            SMSMonitorAnalytics.trackPermissionGranted(fragment.getClass().getSimpleName());
            this.permissionSubject.onNext(Boolean.valueOf(true));
        } else {
            SMSMonitorAnalytics.trackPermissionDenied(fragment.getClass().getSimpleName());
            fragment.requestPermissions(new String[]{"android.permission.RECEIVE_SMS"}, RC_PERMISSION_RECEIVE_SMS);
        }
        return this.permissionSubject.asObservable();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults, Fragment source) {
        if (requestCode == RC_PERMISSION_RECEIVE_SMS && permissions2.length > 0 && "android.permission.RECEIVE_SMS".equals(permissions2[0])) {
            if (grantResults[0] == 0) {
                SMSMonitorAnalytics.trackPermissionGrantSuccess(source.getClass().getSimpleName());
                this.permissionSubject.onNext(Boolean.valueOf(true));
                return;
            }
            SMSMonitorAnalytics.trackPermissionGrantFailed(source.getClass().getSimpleName());
            this.permissionSubject.onNext(Boolean.valueOf(false));
        }
    }

    private boolean checkPermission(Fragment fragment) {
        return ActivityCompat.checkSelfPermission(fragment.getContext(), "android.permission.RECEIVE_SMS") == 0;
    }
}
