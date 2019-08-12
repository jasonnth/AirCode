package com.airbnb.android.lib.fragments.verifiedid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.requests.SesameCreditVerificationRequest;
import com.airbnb.android.core.requests.SesameCreditVerificationResponse;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.SesameVerificationWebViewActivity;
import com.airbnb.p027n2.primitives.AirButton;
import p032rx.Observer;

public class SesameVerificationConnectFragment extends AirFragment {
    private static final int RC_VERIFY_SESAME = 195;
    @BindView
    EditText fullName;
    final RequestListener<SesameCreditVerificationResponse> getUrlRequestListener = new C0699RL().onResponse(SesameVerificationConnectFragment$$Lambda$1.lambdaFactory$(this)).onError(SesameVerificationConnectFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    EditText govID;
    @BindView
    LoaderFrame loaderFrame;
    @BindView
    AirButton submitButton;
    VerifiedIdAnalytics verifiedIdAnalytics;

    static /* synthetic */ void lambda$new$0(SesameVerificationConnectFragment sesameVerificationConnectFragment, SesameCreditVerificationResponse data) {
        sesameVerificationConnectFragment.loaderFrame.finish();
        sesameVerificationConnectFragment.startActivityForResult(SesameVerificationWebViewActivity.intent(sesameVerificationConnectFragment.getContext(), data.getUrl()), 195);
    }

    static /* synthetic */ void lambda$new$1(SesameVerificationConnectFragment sesameVerificationConnectFragment, AirRequestNetworkException e) {
        sesameVerificationConnectFragment.loaderFrame.finishImmediate();
        NetworkUtil.toastNetworkError(sesameVerificationConnectFragment.getContext(), (NetworkException) e);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_sesame_verification_connect, container, false);
        bindViews(view);
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 195) {
            return;
        }
        if (resultCode != -1) {
            VerifiedIdAnalytics.trackSesameVerifyResult(null, "CANCEL");
        } else if (data.getBooleanExtra(SesameVerificationWebViewActivity.EXTRA_VERIFICATION_RESULT_SUCCESS, false)) {
            VerifiedIdAnalytics.trackSesameVerifyResult(null, "SUCCESS");
            getActivity().setResult(-1);
            getActivity().finish();
        } else {
            VerifiedIdAnalytics.trackSesameVerifyResult(null, OfficialIdStatusResponse.ERROR);
            Toast.makeText(getContext(), data.getStringExtra(SesameVerificationWebViewActivity.EXTRA_VERIFICATION_RESULT_ERROR_MESSAGE), 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return BuildHelper.isReleaseBuild();
    }

    /* access modifiers changed from: 0000 */
    @OnTextChanged
    public void onAfterTextChange() {
        this.submitButton.setEnabled(hasEnteredValidInfo());
    }

    /* access modifiers changed from: 0000 */
    @OnEditorAction
    public boolean onEditorAction() {
        if (hasEnteredValidInfo()) {
            submit();
        }
        return true;
    }

    private boolean hasEnteredValidInfo() {
        return !TextUtils.isEmpty(this.fullName.getText()) && !TextUtils.isEmpty(this.govID.getText());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void submit() {
        VerifiedIdAnalytics.trackSesameStartVerifyWithSesame(null);
        this.loaderFrame.startAnimation();
        new SesameCreditVerificationRequest(this.fullName.getText().toString(), this.govID.getText().toString()).withListener((Observer) this.getUrlRequestListener).execute(this.requestManager);
    }
}
