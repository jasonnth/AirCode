package com.braintreepayments.api;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ConfigurationException;
import com.braintreepayments.api.exceptions.GoogleApiClientException;
import com.braintreepayments.api.exceptions.GoogleApiClientException.ErrorType;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeCancelListener;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.interfaces.PaymentMethodNoncesUpdatedListener;
import com.braintreepayments.api.interfaces.QueuedCallback;
import com.braintreepayments.api.interfaces.UnionPayListener;
import com.braintreepayments.api.internal.AnalyticsDatabase;
import com.braintreepayments.api.internal.AnalyticsEvent;
import com.braintreepayments.api.internal.AnalyticsIntentService;
import com.braintreepayments.api.internal.AnalyticsSender;
import com.braintreepayments.api.internal.BraintreeHttpClient;
import com.braintreepayments.api.internal.IntegrationType;
import com.braintreepayments.api.internal.UUIDHelper;
import com.braintreepayments.api.models.AndroidPayCardNonce;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.TokenizationKey;
import com.braintreepayments.browserswitch.BrowserSwitchFragment;
import com.braintreepayments.browserswitch.BrowserSwitchFragment.BrowserSwitchResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.Wallet.WalletOptions;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import org.json.JSONException;

public class BraintreeFragment extends BrowserSwitchFragment {
    /* access modifiers changed from: private */
    public AnalyticsDatabase mAnalyticsDatabase;
    private Authorization mAuthorization;
    private final List<PaymentMethodNonce> mCachedPaymentMethodNonces = new ArrayList();
    private final Queue<QueuedCallback> mCallbackQueue = new ArrayDeque();
    /* access modifiers changed from: private */
    public BraintreeCancelListener mCancelListener;
    private Configuration mConfiguration;
    /* access modifiers changed from: private */
    public BraintreeResponseListener<Exception> mConfigurationErrorListener;
    /* access modifiers changed from: private */
    public ConfigurationListener mConfigurationListener;
    private int mConfigurationRequestAttempts = 0;
    private CrashReporter mCrashReporter;
    /* access modifiers changed from: private */
    public BraintreeErrorListener mErrorListener;
    protected GoogleApiClient mGoogleApiClient;
    private boolean mHasFetchedPaymentMethodNonces = false;
    protected BraintreeHttpClient mHttpClient;
    private String mIntegrationType;
    private boolean mNewActivityNeedsConfiguration;
    /* access modifiers changed from: private */
    public PaymentMethodNonceCreatedListener mPaymentMethodNonceCreatedListener;
    private PaymentMethodNoncesUpdatedListener mPaymentMethodNoncesUpdatedListener;
    private String mSessionId;
    private UnionPayListener mUnionPayListener;

    public static BraintreeFragment newInstance(Activity activity, String authorization) throws InvalidArgumentException {
        if (activity == null) {
            throw new InvalidArgumentException("Activity is null");
        }
        FragmentManager fm = activity.getFragmentManager();
        BraintreeFragment braintreeFragment = (BraintreeFragment) fm.findFragmentByTag("com.braintreepayments.api.BraintreeFragment");
        if (braintreeFragment == null) {
            braintreeFragment = new BraintreeFragment();
            Bundle bundle = new Bundle();
            try {
                bundle.putParcelable("com.braintreepayments.api.EXTRA_AUTHORIZATION_TOKEN", Authorization.fromString(authorization));
                bundle.putString("com.braintreepayments.api.EXTRA_SESSION_ID", UUIDHelper.getFormattedUUID());
                bundle.putString("com.braintreepayments.api.EXTRA_INTEGRATION_TYPE", IntegrationType.get(activity));
                braintreeFragment.setArguments(bundle);
                try {
                    if (VERSION.SDK_INT >= 24) {
                        try {
                            fm.beginTransaction().add(braintreeFragment, "com.braintreepayments.api.BraintreeFragment").commitNow();
                        } catch (IllegalStateException e) {
                            IllegalStateException illegalStateException = e;
                            fm.beginTransaction().add(braintreeFragment, "com.braintreepayments.api.BraintreeFragment").commit();
                            try {
                                fm.executePendingTransactions();
                            } catch (IllegalStateException e2) {
                            }
                            braintreeFragment.mContext = activity.getApplicationContext();
                            return braintreeFragment;
                        } catch (NullPointerException e3) {
                            NullPointerException nullPointerException = e3;
                            fm.beginTransaction().add(braintreeFragment, "com.braintreepayments.api.BraintreeFragment").commit();
                            fm.executePendingTransactions();
                            braintreeFragment.mContext = activity.getApplicationContext();
                            return braintreeFragment;
                        }
                    } else {
                        fm.beginTransaction().add(braintreeFragment, "com.braintreepayments.api.BraintreeFragment").commit();
                        try {
                            fm.executePendingTransactions();
                        } catch (IllegalStateException e4) {
                        }
                    }
                } catch (IllegalStateException e5) {
                    throw new InvalidArgumentException(e5.getMessage());
                }
            } catch (InvalidArgumentException e6) {
                throw new InvalidArgumentException("Tokenization Key or client token was invalid.");
            }
        }
        braintreeFragment.mContext = activity.getApplicationContext();
        return braintreeFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (this.mContext == null) {
            this.mContext = getActivity().getApplicationContext();
        }
        this.mNewActivityNeedsConfiguration = false;
        this.mCrashReporter = CrashReporter.setup(this);
        this.mSessionId = getArguments().getString("com.braintreepayments.api.EXTRA_SESSION_ID");
        this.mIntegrationType = getArguments().getString("com.braintreepayments.api.EXTRA_INTEGRATION_TYPE");
        this.mAuthorization = (Authorization) getArguments().getParcelable("com.braintreepayments.api.EXTRA_AUTHORIZATION_TOKEN");
        this.mAnalyticsDatabase = AnalyticsDatabase.getInstance(getApplicationContext());
        if (this.mHttpClient == null) {
            this.mHttpClient = new BraintreeHttpClient(this.mAuthorization);
        }
        if (savedInstanceState != null) {
            List<PaymentMethodNonce> paymentMethodNonces = savedInstanceState.getParcelableArrayList("com.braintreepayments.api.EXTRA_CACHED_PAYMENT_METHOD_NONCES");
            if (paymentMethodNonces != null) {
                this.mCachedPaymentMethodNonces.addAll(paymentMethodNonces);
            }
            this.mHasFetchedPaymentMethodNonces = savedInstanceState.getBoolean("com.braintreepayments.api.EXTRA_FETCHED_PAYMENT_METHOD_NONCES");
            try {
                setConfiguration(Configuration.fromJson(savedInstanceState.getString("com.braintreepayments.api.EXTRA_CONFIGURATION")));
            } catch (JSONException e) {
            }
        } else if (this.mAuthorization instanceof TokenizationKey) {
            sendAnalyticsEvent("started.client-key");
        } else {
            sendAnalyticsEvent("started.client-token");
        }
        fetchConfiguration();
    }

    @TargetApi(23)
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttach(getActivity());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mNewActivityNeedsConfiguration = true;
    }

    public void onResume() {
        super.onResume();
        if (getActivity() instanceof BraintreeListener) {
            addListener((BraintreeListener) getActivity());
            if (this.mNewActivityNeedsConfiguration && getConfiguration() != null) {
                this.mNewActivityNeedsConfiguration = false;
                postConfigurationCallback();
            }
        }
        flushCallbacks();
        if (this.mGoogleApiClient != null && !this.mGoogleApiClient.isConnected() && !this.mGoogleApiClient.isConnecting()) {
            this.mGoogleApiClient.connect();
        }
    }

    public void onPause() {
        super.onPause();
        if (getActivity() instanceof BraintreeListener) {
            removeListener((BraintreeListener) getActivity());
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("com.braintreepayments.api.EXTRA_CACHED_PAYMENT_METHOD_NONCES", (ArrayList) this.mCachedPaymentMethodNonces);
        outState.putBoolean("com.braintreepayments.api.EXTRA_FETCHED_PAYMENT_METHOD_NONCES", this.mHasFetchedPaymentMethodNonces);
        if (this.mConfiguration != null) {
            outState.putString("com.braintreepayments.api.EXTRA_CONFIGURATION", this.mConfiguration.toJson());
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.disconnect();
        }
        flushAnalyticsEvents();
    }

    public void onDetach() {
        super.onDetach();
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.disconnect();
            this.mGoogleApiClient = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCrashReporter.tearDown();
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        if (!isAdded()) {
            postCallback((Exception) new BraintreeException("BraintreeFragment is not attached to an Activity. Please ensure it is attached and try again."));
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }

    public String getReturnUrlScheme() {
        return getApplicationContext().getPackageName().toLowerCase().replace("_", "") + ".braintree";
    }

    public void onBrowserSwitchResult(int requestCode, BrowserSwitchResult browserSwitchResult, Uri uri) {
        int resultCode = 1;
        if (browserSwitchResult == BrowserSwitchResult.OK) {
            resultCode = -1;
        } else if (browserSwitchResult == BrowserSwitchResult.CANCELED) {
            resultCode = 0;
        }
        onActivityResult(requestCode, resultCode, new Intent().setData(uri));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 13487:
                ThreeDSecure.onActivityResult(this, resultCode, data);
                break;
            case 13488:
                Venmo.onActivityResult(this, resultCode, data);
                break;
            case 13489:
                AndroidPay.onActivityResult(this, resultCode, data);
                break;
            case 13591:
                PayPal.onActivityResult(this, resultCode, data);
                break;
            case 13592:
                VisaCheckout.onActivityResult(this, resultCode, data);
                break;
        }
        if (resultCode == 0) {
            postCancelCallback(requestCode);
        }
    }

    public <T extends BraintreeListener> void addListener(T listener) {
        if (listener instanceof ConfigurationListener) {
            this.mConfigurationListener = (ConfigurationListener) listener;
        }
        if (listener instanceof BraintreeCancelListener) {
            this.mCancelListener = (BraintreeCancelListener) listener;
        }
        if (listener instanceof PaymentMethodNoncesUpdatedListener) {
            this.mPaymentMethodNoncesUpdatedListener = (PaymentMethodNoncesUpdatedListener) listener;
        }
        if (listener instanceof PaymentMethodNonceCreatedListener) {
            this.mPaymentMethodNonceCreatedListener = (PaymentMethodNonceCreatedListener) listener;
        }
        if (listener instanceof BraintreeErrorListener) {
            this.mErrorListener = (BraintreeErrorListener) listener;
        }
        if (listener instanceof UnionPayListener) {
            this.mUnionPayListener = (UnionPayListener) listener;
        }
        flushCallbacks();
    }

    public <T extends BraintreeListener> void removeListener(T listener) {
        if (listener instanceof ConfigurationListener) {
            this.mConfigurationListener = null;
        }
        if (listener instanceof BraintreeCancelListener) {
            this.mCancelListener = null;
        }
        if (listener instanceof PaymentMethodNoncesUpdatedListener) {
            this.mPaymentMethodNoncesUpdatedListener = null;
        }
        if (listener instanceof PaymentMethodNonceCreatedListener) {
            this.mPaymentMethodNonceCreatedListener = null;
        }
        if (listener instanceof BraintreeErrorListener) {
            this.mErrorListener = null;
        }
        if (listener instanceof UnionPayListener) {
            this.mUnionPayListener = null;
        }
    }

    public void sendAnalyticsEvent(String eventFragment) {
        final AnalyticsEvent request = new AnalyticsEvent(this.mContext, getSessionId(), this.mIntegrationType, eventFragment);
        waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (configuration.getAnalytics().isEnabled()) {
                    BraintreeFragment.this.mAnalyticsDatabase.addEvent(request);
                }
            }
        });
    }

    private void flushAnalyticsEvents() {
        if (getConfiguration() != null && getConfiguration().toJson() != null && getConfiguration().getAnalytics().isEnabled()) {
            try {
                getApplicationContext().startService(new Intent(this.mContext, AnalyticsIntentService.class).putExtra("com.braintreepayments.api.internal.AnalyticsIntentService.EXTRA_AUTHORIZATION", getAuthorization().toString()).putExtra("com.braintreepayments.api.internal.AnalyticsIntentService.EXTRA_CONFIGURATION", getConfiguration().toJson()));
            } catch (RuntimeException e) {
                AnalyticsSender.send(getApplicationContext(), this.mAuthorization, getHttpClient(), getConfiguration().getAnalytics().getUrl(), false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void postConfigurationCallback() {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mConfigurationListener != null;
            }

            public void run() {
                BraintreeFragment.this.mConfigurationListener.onConfigurationFetched(BraintreeFragment.this.getConfiguration());
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCancelCallback(final int requestCode) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mCancelListener != null;
            }

            public void run() {
                BraintreeFragment.this.mCancelListener.onCancel(requestCode);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final PaymentMethodNonce paymentMethodNonce) {
        if (paymentMethodNonce instanceof AndroidPayCardNonce) {
            Iterator it = new ArrayList(this.mCachedPaymentMethodNonces).iterator();
            while (it.hasNext()) {
                PaymentMethodNonce cachedPaymentMethodNonce = (PaymentMethodNonce) it.next();
                if (cachedPaymentMethodNonce instanceof AndroidPayCardNonce) {
                    this.mCachedPaymentMethodNonces.remove(cachedPaymentMethodNonce);
                }
            }
        }
        this.mCachedPaymentMethodNonces.add(0, paymentMethodNonce);
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mPaymentMethodNonceCreatedListener != null;
            }

            public void run() {
                BraintreeFragment.this.mPaymentMethodNonceCreatedListener.onPaymentMethodNonceCreated(paymentMethodNonce);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postCallback(final Exception error) {
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.mErrorListener != null;
            }

            public void run() {
                BraintreeFragment.this.mErrorListener.onError(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void postOrQueueCallback(QueuedCallback callback) {
        if (!callback.shouldRun()) {
            this.mCallbackQueue.add(callback);
        } else {
            callback.run();
        }
    }

    /* access modifiers changed from: protected */
    public void flushCallbacks() {
        Queue<QueuedCallback> queue = new ArrayDeque<>();
        queue.addAll(this.mCallbackQueue);
        for (QueuedCallback callback : queue) {
            if (callback.shouldRun()) {
                callback.run();
                this.mCallbackQueue.remove(callback);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void fetchConfiguration() {
        if (getConfiguration() == null && !ConfigurationManager.isFetchingConfiguration() && this.mAuthorization != null && this.mHttpClient != null) {
            if (this.mConfigurationRequestAttempts >= 3) {
                postCallback((Exception) new ConfigurationException("Configuration retry limit has been exceeded. Create a new BraintreeFragment and try again."));
                return;
            }
            this.mConfigurationRequestAttempts++;
            ConfigurationManager.getConfiguration(this, new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    BraintreeFragment.this.setConfiguration(configuration);
                    BraintreeFragment.this.postConfigurationCallback();
                    BraintreeFragment.this.flushCallbacks();
                }
            }, new BraintreeResponseListener<Exception>() {
                public void onResponse(Exception e) {
                    final ConfigurationException exception = new ConfigurationException("Request for configuration has failed: " + e.getMessage() + ". " + "Future requests will retry up to 3 times", e);
                    BraintreeFragment.this.postCallback((Exception) exception);
                    BraintreeFragment.this.postOrQueueCallback(new QueuedCallback() {
                        public boolean shouldRun() {
                            return BraintreeFragment.this.mConfigurationErrorListener != null;
                        }

                        public void run() {
                            BraintreeFragment.this.mConfigurationErrorListener.onResponse(exception);
                        }
                    });
                    BraintreeFragment.this.flushCallbacks();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void waitForConfiguration(final ConfigurationListener listener) {
        fetchConfiguration();
        postOrQueueCallback(new QueuedCallback() {
            public boolean shouldRun() {
                return BraintreeFragment.this.getConfiguration() != null && BraintreeFragment.this.isAdded();
            }

            public void run() {
                listener.onConfigurationFetched(BraintreeFragment.this.getConfiguration());
            }
        });
    }

    /* access modifiers changed from: protected */
    public Authorization getAuthorization() {
        return this.mAuthorization;
    }

    /* access modifiers changed from: protected */
    public Context getApplicationContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    /* access modifiers changed from: protected */
    public void setConfiguration(Configuration configuration) {
        this.mConfiguration = configuration;
        getHttpClient().setBaseUrl(configuration.getClientApiUrl());
    }

    /* access modifiers changed from: protected */
    public BraintreeHttpClient getHttpClient() {
        return this.mHttpClient;
    }

    /* access modifiers changed from: protected */
    public String getSessionId() {
        return this.mSessionId;
    }

    /* access modifiers changed from: protected */
    public String getIntegrationType() {
        return this.mIntegrationType;
    }

    public void getGoogleApiClient(final BraintreeResponseListener<GoogleApiClient> listener) {
        waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                GoogleApiClient googleApiClient = BraintreeFragment.this.getGoogleApiClient();
                if (googleApiClient != null) {
                    listener.onResponse(googleApiClient);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public GoogleApiClient getGoogleApiClient() {
        if (getActivity() == null) {
            postCallback((Exception) new GoogleApiClientException(ErrorType.NotAttachedToActivity, 1));
            return null;
        }
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new Builder(getActivity()).addApi(Wallet.API, new WalletOptions.Builder().setEnvironment(AndroidPay.getEnvironment(getConfiguration().getAndroidPay())).setTheme(1).build()).build();
        }
        if (!this.mGoogleApiClient.isConnected() && !this.mGoogleApiClient.isConnecting()) {
            this.mGoogleApiClient.registerConnectionCallbacks(new ConnectionCallbacks() {
                public void onConnected(Bundle bundle) {
                }

                public void onConnectionSuspended(int i) {
                    BraintreeFragment.this.postCallback((Exception) new GoogleApiClientException(ErrorType.ConnectionSuspended, i));
                }
            });
            this.mGoogleApiClient.registerConnectionFailedListener(new OnConnectionFailedListener() {
                public void onConnectionFailed(ConnectionResult connectionResult) {
                    BraintreeFragment.this.postCallback((Exception) new GoogleApiClientException(ErrorType.ConnectionFailed, connectionResult.getErrorCode()));
                }
            });
            this.mGoogleApiClient.connect();
        }
        return this.mGoogleApiClient;
    }
}
