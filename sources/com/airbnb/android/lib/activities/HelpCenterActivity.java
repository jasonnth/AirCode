package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.SupportPhoneNumber;
import com.airbnb.android.core.requests.SupportPhoneNumbersRequest;
import com.airbnb.android.core.responses.SupportPhoneNumbersResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.SupportPhoneNumberAnalytics;
import com.airbnb.android.lib.views.GroupedCell;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import java.util.Iterator;
import p032rx.Observer;

public class HelpCenterActivity extends AirActivity {
    @BindView
    TextView mOtherCountryText;
    @BindView
    LinearLayout mPhoneNumberContainer;
    @BindView
    TextView mWebsiteLink;

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_customer_service);
        setupActionBar(C0880R.string.airbnb_help, new Object[0]);
        ButterKnife.bind((Activity) this);
        setupPhoneNums();
        AirbnbEventLogger.track("mobile_help", Strap.make().mo11639kv(BaseAnalytics.OPERATION, "offline_mode"));
    }

    private void setupPhoneNums() {
        this.mWebsiteLink.setOnClickListener(HelpCenterActivity$$Lambda$1.lambdaFactory$(this));
        updateOtherCountryText(getString(C0880R.string.support_phone_number));
        final OnClickListener clickToCallListener = HelpCenterActivity$$Lambda$2.lambdaFactory$(this);
        final OnLongClickListener longClickToCopyListener = HelpCenterActivity$$Lambda$3.lambdaFactory$(this);
        new SupportPhoneNumbersRequest().withListener((Observer) new NonResubscribableRequestListener<SupportPhoneNumbersResponse>() {
            public void onResponse(SupportPhoneNumbersResponse response) {
                HelpCenterActivity.this.mPhoneNumberContainer.removeAllViews();
                if (response.numbers.size() > 0) {
                    HelpCenterActivity.this.updateOtherCountryText(((SupportPhoneNumber) response.numbers.get(0)).getNumber());
                    SupportPhoneNumberAnalytics.trackImpression(SupportPhoneNumberAnalytics.PAGE_HELP_CENTER);
                }
                Iterator it = response.numbers.iterator();
                while (it.hasNext()) {
                    SupportPhoneNumber item = (SupportPhoneNumber) it.next();
                    String phoneNumber = item.getNumber();
                    if (!TextUtils.isEmpty(phoneNumber)) {
                        GroupedCell row = new GroupedCell(HelpCenterActivity.this);
                        row.setLayoutParams(new LayoutParams(-1, -2));
                        row.setTitle((CharSequence) item.getCountry());
                        row.setContent((CharSequence) item.getNumber());
                        row.setTag(phoneNumber);
                        row.setOnClickListener(clickToCallListener);
                        row.setOnLongClickListener(longClickToCopyListener);
                        HelpCenterActivity.this.mPhoneNumberContainer.addView(row);
                    }
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
            }
        }).singleResponse().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void updateOtherCountryText(String supportNumber) {
        ClickableLinkUtils.setupClickableTextView(this.mOtherCountryText, getString(C0880R.string.offline_help_other_country, new Object[]{supportNumber}), supportNumber, C0880R.color.canonical_press_darken, HelpCenterActivity$$Lambda$4.lambdaFactory$(this, supportNumber));
    }
}
