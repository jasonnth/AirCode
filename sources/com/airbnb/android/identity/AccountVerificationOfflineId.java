package com.airbnb.android.identity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.p027n2.components.SheetMarquee;

public class AccountVerificationOfflineId extends ScrollView {
    private AccountVerificationOfflineIdListener listener;
    @BindView
    SheetMarquee offlineIdMarquee;

    public AccountVerificationOfflineId(Context context) {
        this(context, null);
    }

    public AccountVerificationOfflineId(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountVerificationOfflineId(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setListener(AccountVerificationOfflineIdListener listener2) {
        this.listener = listener2;
    }

    private void init(Context context) {
        ButterKnife.bind(this, inflate(context, C6533R.layout.account_verification_offline_id, this));
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
    }

    public void setVerificationFlow(VerificationFlow verificationFlow) {
        this.offlineIdMarquee.setSubtitle(verificationFlow.getText().getOfflineIdSubtitle());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void scanDriversLicense() {
        this.listener.startIdCaptureFlow(GovernmentIdType.DRIVING_LICENSE);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void scanPassport() {
        this.listener.startIdCaptureFlow(GovernmentIdType.PASSPORT);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void scanVisa() {
        this.listener.startIdCaptureFlow(GovernmentIdType.VISA);
    }
}
