package com.airbnb.android.login.oauth.webview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.analytics.SignUpLoginAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.enums.AuthorizeService;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.requests.OAuthCallbackRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.oauth.webview.OauthActivity.Service;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Locale;
import org.jmrtd.cbeff.ISO781611;

public class OauthFragment extends AirFragment implements OnBackListener {
    private static final String ARG_SERVICE = "service";
    public static final String TAG = OauthFragment.class.getSimpleName();
    private AirWebView mAirWebView;
    private Service mService;
    private String serviceAuthorizationURL;
    private String serviceClientId;
    private String servicePermissions;
    /* access modifiers changed from: private */
    public String serviceRedirectURI;
    /* access modifiers changed from: private */
    public String state;

    public static OauthFragment newInstance(Service service) {
        OauthFragment fragment = new OauthFragment();
        Bundle args = new Bundle();
        args.putInt("service", service.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mService = Service.values()[getArguments().getInt("service")];
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_airbnb_webview, container, false);
        this.mAirWebView = (AirWebView) view.findViewById(C7331R.C7333id.airbnb_webview);
        this.mAirWebView.addCallbacks(new AirWebViewCallbacks() {
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (url.indexOf(OauthFragment.this.serviceRedirectURI) != 0) {
                    webView.loadUrl(url);
                    return false;
                }
                Uri uri = Uri.parse(url);
                String paramState = uri.getQueryParameter("state");
                String paramError = uri.getQueryParameter("error");
                String paramCode = uri.getQueryParameter("code");
                if (!OauthFragment.this.state.equals(paramState) || paramCode == null) {
                    OauthFragment.this.handleCallbackError(paramError);
                } else {
                    OauthFragment.this.callbackWithAuthCode(paramCode);
                }
                return true;
            }
        });
        initializeService();
        connect();
        return view;
    }

    public void onDestroyView() {
        this.mAirWebView.onDestroy();
        super.onDestroyView();
    }

    public boolean onBackPressed() {
        return false;
    }

    /* access modifiers changed from: private */
    public void handleCallbackError(String paramError) {
        if (this.mService == Service.WEIBO) {
            SignUpLoginAnalytics.trackAuthFail(AuthorizeService.WEIBO, paramError);
        }
        if ("access_denied".equals(paramError)) {
            getActivity().finish();
        } else {
            Log.e(TAG, "Error when parsing callback");
        }
    }

    private void connect() {
        String authorizationUrl = this.serviceAuthorizationURL;
        this.state = generateNonce();
        this.mAirWebView.loadUrl(authorizationUrl + (!TextUtils.isEmpty(this.servicePermissions) ? "&scope=" + this.servicePermissions : "") + "&state=" + this.state + "&redirect_uri=" + this.serviceRedirectURI + "&client_id=" + this.serviceClientId);
    }

    /* access modifiers changed from: private */
    public void callbackWithAuthCode(String code) {
        if (this.mService == Service.LINKEDIN) {
            OAuthCallbackRequest.oauthCallbackServerRequest(code, OAuthCallbackRequest.SERVICE_LINKEDIN, new NonResubscribableRequestListener<Object>() {
                public void onResponse(Object response) {
                    VerifiedIdAnalytics.trackOnlineStartConfirmed(null);
                    OauthFragment.this.getActivity().setProgressBarIndeterminateVisibility(false);
                    OauthFragment.this.getActivity().setResult(-1);
                    OauthFragment.this.getActivity().finish();
                }

                public void onErrorResponse(AirRequestNetworkException error) {
                    VerifiedIdAnalytics.trackOnlineStartDenied(null);
                    OauthFragment.this.getActivity().setProgressBarIndeterminateVisibility(false);
                    NetworkUtil.toastGenericNetworkError(OauthFragment.this.getActivity());
                }
            }).execute(this.requestManager);
        } else if (getActivity() != null) {
            ((OauthActivity) getActivity()).returnCode(code);
        }
    }

    private String generateNonce() {
        return new BigInteger(ISO781611.BIOMETRIC_SUBTYPE_TAG, new SecureRandom()).toString(32);
    }

    private void initializeService() {
        switch (this.mService) {
            case LINKEDIN:
                this.serviceClientId = getString(C7331R.string.linkedin_client_id);
                this.servicePermissions = "r_fullprofile,r_emailaddress,r_network";
                this.serviceRedirectURI = "https://www.airbnb.com/oauth_callback";
                this.serviceAuthorizationURL = "https://www.linkedin.com/uas/oauth2/authorization?response_type=code";
                return;
            case WEIBO:
                this.serviceClientId = getString(C7331R.string.weibo_client_id);
                this.servicePermissions = "email";
                this.serviceRedirectURI = "https://www.airbnb.com/oauth_callback";
                this.serviceAuthorizationURL = "https://api.weibo.com/oauth2/authorize?display=mobile&language=" + Locale.getDefault().getLanguage();
                return;
            default:
                System.err.println("OauthActivity: No recognized service");
                return;
        }
    }
}
