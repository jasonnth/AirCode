package com.airbnb.android.lib.fragments.verifiedid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.enums.AuthorizeService;
import com.airbnb.android.core.requests.AuthorizeServiceRequest;
import com.airbnb.android.core.responses.AuthorizeServiceResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame.LoaderFrameDisplay;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.login.oauth.webview.OauthActivity;
import com.airbnb.android.login.oauth.webview.OauthActivity.Service;
import com.airbnb.android.login.utils.LoginUtils;
import com.airbnb.android.login.utils.LoginUtils.GoogleAuthHelper;
import com.airbnb.android.login.utils.LoginUtils.GoogleScopeSet;
import com.airbnb.android.login.utils.LoginUtils.LoginListener;
import com.airbnb.android.login.utils.LoginUtils.NoGoogleAccountsFoundException;
import com.airbnb.android.utils.Strap;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;

public class OnlineIdFragment extends BaseVerifiedIdFragment implements VerifiedIdStrapper {
    private static final int REQUEST_CODE_LINKEDIN = 1003;
    public static final String TAG = OnlineIdFragment.class.getSimpleName();
    private CallbackManager facebookCallbackManager;
    private GoogleAuthHelper mAuthHelper;
    private CircleBadgeView mCircleBadgeView;
    /* access modifiers changed from: private */
    public final LoginListener mLoginListener = new LoginListener() {
        public Context getContext() {
            return OnlineIdFragment.this.getActivity();
        }

        public void showRequestedPermissions(AuthorizeService authorizeService) {
        }

        public void onCallbackResponse(boolean success, String errorMessage) {
            if (OnlineIdFragment.this.getActivity() != null) {
                VerifiedIdAnalytics.trackOnlineStartConfirmed(OnlineIdFragment.this.getVerifiedIdAnalyticsStrap());
                if (!(OnlineIdFragment.this.getChildFragmentManager().findFragmentById(C0880R.C0882id.verified_id_online_fragment) instanceof ConfirmedVerificationFragment)) {
                    OnlineIdFragment.this.setPendingState();
                }
                if (success) {
                    OnlineIdFragment.this.setOnlineIdRetries();
                } else {
                    onError(null, AuthorizeService.GOOGLE);
                }
            }
        }

        public void onFacebookTokenAcquired(String token) {
            if (OnlineIdFragment.this.getActivity() != null) {
                OnlineIdFragment.this.setPendingState();
                AuthorizeServiceRequest.forFacebook(getContext(), token).withListener(new NonResubscribableRequestListener<AuthorizeServiceResponse>() {
                    public void onResponse(AuthorizeServiceResponse response) {
                        if (OnlineIdFragment.this.getActivity() != null) {
                            OnlineIdFragment.this.setOnlineIdRetries();
                        }
                    }

                    public void onErrorResponse(AirRequestNetworkException error) {
                        if (OnlineIdFragment.this.getActivity() != null) {
                            OnlineIdFragment.this.mLoginListener.onError(null, AuthorizeService.FACEBOOK);
                        }
                    }
                }).execute(NetworkUtil.singleFireExecutor());
            }
        }

        public void onError(String errorMessage, AuthorizeService authorizeService) {
            OnlineIdFragment.this.getActivity().runOnUiThread(OnlineIdFragment$2$$Lambda$1.lambdaFactory$(this));
        }

        static /* synthetic */ void lambda$onError$0(C70322 r1) {
            if (OnlineIdFragment.this.getActivity() != null) {
                NetworkUtil.toastGenericNetworkError(OnlineIdFragment.this.getActivity());
                OnlineIdFragment.this.setActiveState();
            }
        }

        public void onCancel(AuthorizeService authorizeService) {
        }

        public Activity getHostingActivity() {
            return OnlineIdFragment.this.getActivity();
        }

        public void onGoogleTokenAcquired(String token) {
            if (!TextUtils.isEmpty(token)) {
                LoginUtils.postGoogleToken(OnlineIdFragment.this.mPrefsHelper, this, token);
            }
        }
    };
    private String mVerificationSuccessDescription;

    public static OnlineIdFragment newInstance() {
        return new OnlineIdFragment();
    }

    public static Intent intentForOauthActivity(Context context, Service service) {
        Intent intent = new Intent(context, OauthActivity.class);
        intent.putExtra(OauthActivity.OAUTH_SERVICE, service);
        return intent;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mAuthHelper = new GoogleAuthHelper(this.mLoginListener, GoogleScopeSet.VerifiedId);
        View view = inflater.inflate(C0880R.layout.fragment_verified_id_online, container, false);
        this.mCircleBadgeView = (CircleBadgeView) view.findViewById(C0880R.C0882id.circle_badge_view_online);
        this.mCircleBadgeView.initializeAsInactiveBadge();
        if (savedInstanceState == null) {
            Fragment contentFragment = OnlineIdChildFragment.newInstance();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.replace(C0880R.C0882id.verified_id_online_fragment, contentFragment);
            ft.commit();
            VerifiedIdAnalytics.trackOnlineStartView(getVerifiedIdAnalyticsStrap());
            VerifiedIdAnalytics.trackHealth("online", "start");
        }
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            if (requestCode == 23456) {
                this.mAuthHelper.logInToGoogleAsync();
            } else if (requestCode == 1002) {
                authorizeWeibo(data.getStringExtra("code"));
            } else if (requestCode == 1003) {
                setOnlineIdRetries();
                setPendingState();
            }
        }
        if (!(this.facebookCallbackManager == null || requestCode == 1003 || requestCode == 1002 || requestCode == 23456)) {
            this.facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void clickFacebookButton() {
        VerifiedIdAnalytics.trackOnlineStartFacebook(getVerifiedIdAnalyticsStrap());
        this.facebookCallbackManager = Factory.create();
        LoginUtils.logInToFacebook(this.facebookCallbackManager, this.mLoginListener);
        this.mVerificationSuccessDescription = getString(C0880R.string.verified_id_facebook);
    }

    public void clickLinkedInButton() {
        VerifiedIdAnalytics.trackOnlineStartLinkedIn(getVerifiedIdAnalyticsStrap());
        Intent intent = intentForOauthActivity(getActivity(), Service.LINKEDIN);
        this.mVerificationSuccessDescription = getString(C0880R.string.verified_id_linkedin);
        startActivityForResult(intent, 1003);
    }

    public void clickGoogleButton() {
        VerifiedIdAnalytics.trackOnlineStartGoogle(getVerifiedIdAnalyticsStrap());
        try {
            this.mAuthHelper.getAccountAndLogin(getActivity());
        } catch (NoGoogleAccountsFoundException | SecurityException e) {
            Log.e(TAG, "", e);
        }
        this.mVerificationSuccessDescription = getString(C0880R.string.verified_id_google);
    }

    public CircleBadgeView getCircleBadgeView() {
        return this.mCircleBadgeView;
    }

    public int getContentFragmentId() {
        return C0880R.C0882id.verified_id_online_fragment;
    }

    public void clickWeiboButton() {
        VerifiedIdAnalytics.trackOnlineStartWeibo(getVerifiedIdAnalyticsStrap());
        this.mVerificationSuccessDescription = getString(C0880R.string.verified_id_weibo);
        startActivityForResult(OauthActivity.intentForService(getActivity(), Service.WEIBO), 1002);
    }

    public String getVerificationSuccessDescription() {
        return this.mVerificationSuccessDescription;
    }

    public void setPendingState() {
        setState(false);
    }

    public void setActiveState() {
        setState(true);
    }

    private void setState(boolean enabled) {
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(!enabled);
        Fragment childFragment = getChildFragmentManager().findFragmentById(C0880R.C0882id.verified_id_online_fragment);
        if (childFragment instanceof OnlineIdChildFragment) {
            ((OnlineIdChildFragment) childFragment).enableButtons(enabled);
        }
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((VerifiedIdActivity) getActivity()).getReservationIdStringForAnalytics());
    }

    /* access modifiers changed from: private */
    public void setOnlineIdRetries() {
        ((VerifiedIdActivity) getActivity()).waitForOnlineId();
    }

    public void notifyServerFailureToConnect() {
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(false);
        Toast.makeText(getActivity(), C0880R.string.verified_id_online_id_problem, 0).show();
        setActiveState();
    }

    private void authorizeWeibo(String code) {
        setPendingState();
        AuthorizeServiceRequest.forWeibo(getContext(), false, code).withListener(new NonResubscribableRequestListener<AuthorizeServiceResponse>() {
            public void onResponse(AuthorizeServiceResponse response) {
                OnlineIdFragment.this.setOnlineIdRetries();
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                OnlineIdFragment.this.mLoginListener.onError(null, AuthorizeService.WEIBO);
            }
        }).execute(this.requestManager);
    }

    public void onGooglePermissionsCompleted(String authToken) {
        this.mLoginListener.onGoogleTokenAcquired(authToken);
    }
}
