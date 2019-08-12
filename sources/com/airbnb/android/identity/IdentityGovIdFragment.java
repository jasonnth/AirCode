package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.intents.ReactNativeIntents;
import icepick.State;
import java.util.Map;

public class IdentityGovIdFragment extends BaseAccountVerificationFragment implements IdentityLoaderFragment {
    private static final String COUNTRY_SELECTION_RESPONSE_ID_COUNTRY_CODE = "selectedCountryCode";
    private static final String COUNTRY_SELECTION_RESPONSE_ID_TYPE = "selectedIDType";
    private static final String COUNTRY_SELECTION_RESPONSE_PAYLOAD = "payload";
    private static final int RC_RN_GOV_ID = 901;
    @State
    GovernmentIdType governmentIdType;
    private final Handler handler = new Handler();

    public static IdentityGovIdFragment getInstance() {
        return new IdentityGovIdFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AccountVerificationController) getContext()).initOfflineIdController(savedInstanceState, this, this.requestManager, this.navigationAnalytics);
        show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(C6533R.layout.fragment_identity_loader, container, false);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.controller.getOfflineIdController().onActivityResult(requestCode, resultCode, data)) {
            show();
        } else if (requestCode != RC_RN_GOV_ID) {
        } else {
            if (resultCode == 0) {
                this.controller.showPreviousStep();
            } else if (data != null) {
                Map<String, String> payload = (Map) data.getExtras().get(COUNTRY_SELECTION_RESPONSE_PAYLOAD);
                if (payload == null || !payload.containsKey(COUNTRY_SELECTION_RESPONSE_ID_COUNTRY_CODE) || !payload.containsKey(COUNTRY_SELECTION_RESPONSE_ID_TYPE)) {
                    this.controller.showPreviousStep();
                    return;
                }
                this.controller.getOfflineIdController().setCountryCode((String) payload.get(COUNTRY_SELECTION_RESPONSE_ID_COUNTRY_CODE));
                this.governmentIdType = GovernmentIdType.valueOf((String) payload.get(COUNTRY_SELECTION_RESPONSE_ID_TYPE));
                this.handler.post(IdentityGovIdFragment$$Lambda$1.lambdaFactory$(this));
            }
        }
    }

    /* access modifiers changed from: private */
    public void startIdScan() {
        this.controller.getOfflineIdController().startIdCaptureFlow(this.governmentIdType);
        getActivity().overridePendingTransition(C6533R.anim.fragment_enter, C6533R.anim.fragment_exit);
    }

    public void show() {
        startActivityForResult(ReactNativeIntents.intentForVerificationsV2(getContext()), RC_RN_GOV_ID);
    }

    public void onResume() {
        super.onResume();
        this.controller.getOfflineIdController().onResume();
    }
}
