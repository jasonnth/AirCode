package com.airbnb.android.superhero;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.arguments.ReactNativeFragmentFactory;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.location.LocationClientFacade.Factory;
import com.airbnb.android.core.location.LocationClientFacade.LocationClientCallbacks;
import com.airbnb.android.core.superhero.SuperHeroInterface;
import com.airbnb.android.core.superhero.SuperHeroInterfaceState;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.superhero.SuperHeroMessage.Status;
import com.airbnb.android.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.Observer;

public class SuperHeroManagerImpl implements SuperHeroManager {
    private static final String SUPER_HERO_REACT_MODULE = "Hero";
    private final NonResubscribableRequestListener<SuperHeroMessagesResponse> allMessagesListener = new C0699RL().onResponse(SuperHeroManagerImpl$$Lambda$1.lambdaFactory$(this)).onError(SuperHeroManagerImpl$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    private Bundle currentlyShownMessageForLogging;
    private final SuperHeroJitneyLogger jitneyLogger;
    /* access modifiers changed from: private */
    public Location lastKnownLocation = null;
    /* access modifiers changed from: private */
    public LocationClientFacade locationClient;
    private final SuperHeroScheduler scheduler;
    public final NonResubscribableRequestListener<SuperHeroMessageResponse> singleMessageListener = new C0699RL().onResponse(SuperHeroManagerImpl$$Lambda$3.lambdaFactory$(this)).onError(SuperHeroManagerImpl$$Lambda$4.lambdaFactory$()).buildWithoutResubscription();
    private SuperHeroInterface superHeroInterface;
    private SuperHeroTableOpenHelper tableOpenHelper;

    static /* synthetic */ void lambda$new$0(SuperHeroManagerImpl superHeroManagerImpl, AirRequestNetworkException e) {
        if (NetworkUtil.isConnectedOrConnecting(CoreApplication.appContext())) {
            BugsnagWrapper.notify((NetworkException) e);
        }
        superHeroManagerImpl.onMessagesUpdatedOrError();
    }

    static /* synthetic */ void lambda$new$1(SuperHeroManagerImpl superHeroManagerImpl, SuperHeroMessageResponse data) {
        if (superHeroManagerImpl.superHeroInterface != null) {
            SuperHeroMessage message = data.getHeroMessage();
            if (superHeroManagerImpl.tableOpenHelper.getSuperHeroMessageById(message.mo11531id()) == null) {
                superHeroManagerImpl.tableOpenHelper.insert(message);
            }
            superHeroManagerImpl.show(SuperHeroBundleUtil.from(message));
        }
    }

    static /* synthetic */ void lambda$new$2(AirRequestNetworkException e) {
    }

    SuperHeroManagerImpl(SuperHeroScheduler scheduler2, SuperHeroTableOpenHelper openHelper, SuperHeroJitneyLogger jitneyLogger2) {
        this.scheduler = scheduler2;
        this.tableOpenHelper = openHelper;
        this.jitneyLogger = jitneyLogger2;
    }

    public void fetchAndShowSuperHeroMessages() {
        new SuperHeroMessagesRequest().withListener((Observer) this.allMessagesListener).skipCache().execute(NetworkUtil.singleFireExecutor());
    }

    public void fetchSuperHeroMessage(long id) {
        SuperHeroMessageRequest.forMessage(id).withListener((Observer) this.singleMessageListener).execute(NetworkUtil.singleFireExecutor());
    }

    public void markMessageAsPresented(long id) {
        markMessageStatus(id, Status.PRESENTED);
    }

    public void markMessageAsTriggered(long id) {
        markMessageStatus(id, Status.TRIGGERED);
    }

    private void markMessageStatus(long id, Status status) {
        if (id != -111) {
            this.tableOpenHelper.updateStatus(id, status);
            SuperHeroMessageRequest.forStatusUpdate(id, status).execute(NetworkUtil.singleFireExecutor());
        }
    }

    /* access modifiers changed from: private */
    public void onMessagesResponse(SuperHeroMessagesResponse response) {
        this.scheduler.persistAndScheduleMessages(response, SuperHeroManagerImpl$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void onMessagesUpdatedOrError() {
        onMessagesUpdatedOrError(null);
    }

    /* access modifiers changed from: private */
    public void onMessagesUpdatedOrError(Location currentLocation) {
        if (this.superHeroInterface != null) {
            List<SuperHeroMessage> superHeroMessages = this.tableOpenHelper.messagesForOnLaunch();
            if (!ListUtils.isEmpty((Collection<?>) superHeroMessages)) {
                if (currentLocation == null && this.lastKnownLocation != null) {
                    currentLocation = this.lastKnownLocation;
                    this.lastKnownLocation = null;
                }
                SuperHeroMessage defaultMessage = null;
                SuperHeroMessage messageWithLocation = null;
                double minDistance = Double.MAX_VALUE;
                for (SuperHeroMessage message : superHeroMessages) {
                    if (message.isValidAndNotYetTriggered()) {
                        if (message.should_takeover()) {
                            if (defaultMessage == null && !message.hasLocation()) {
                                defaultMessage = message;
                            }
                            if (currentLocation != null && message.hasLocation()) {
                                double distance = message.getDistanceToCurrentLocation(currentLocation);
                                if (distance < minDistance && distance < message.getRadius()) {
                                    minDistance = distance;
                                    messageWithLocation = message;
                                }
                            }
                        } else {
                            markMessageAsTriggered(message.mo11531id());
                        }
                    }
                }
                if (messageWithLocation != null) {
                    show(SuperHeroBundleUtil.from(messageWithLocation));
                } else if (defaultMessage != null) {
                    show(SuperHeroBundleUtil.from(defaultMessage));
                }
            }
        }
    }

    public void setInterface(SuperHeroInterface superHeroInterface2) {
        this.superHeroInterface = superHeroInterface2;
    }

    public void removeInterfaceIfSet(SuperHeroInterface superHeroInterface2) {
        if (this.superHeroInterface == superHeroInterface2) {
            this.superHeroInterface = null;
        }
    }

    public void handleReceiverIntent(Context context, Intent intent) {
        Bundle superHeroBundle = SuperHeroMessage.getAlarmBundle(intent);
        Check.notNull(superHeroBundle);
        if (this.superHeroInterface == null) {
            SuperHeroLocalPushNotificationUtil.show(context, superHeroBundle);
        } else if (this.superHeroInterface.getState() != SuperHeroInterfaceState.CLOSED || !SuperHeroBundleUtil.shouldTakeover(superHeroBundle)) {
            markMessageAsTriggered(SuperHeroBundleUtil.m1449id(superHeroBundle));
        } else {
            show(superHeroBundle);
        }
    }

    public void showTest() {
        Check.state(BuildHelper.isInternalSettingsEnabled());
        show(SuperHeroBundleUtil.test());
    }

    public void show(Bundle superHeroBundle) {
        if (FeatureToggles.isSuperHeroEnabled()) {
            if (superHeroBundle.getBundle(SuperHeroBundleUtil.HERO_MESSAGE_ARG) == null) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Tried to trigger SuperHero but no heroMessage is null"));
                return;
            }
            ArrayList<String> messages = superHeroBundle.getBundle(SuperHeroBundleUtil.HERO_MESSAGE_ARG).getStringArrayList("messages");
            boolean hasEmptyOrNullMessages = messages.size() == 0 || messages.contains(null);
            if (this.superHeroInterface != null && !hasEmptyOrNullMessages) {
                this.superHeroInterface.show(ReactNativeFragmentFactory.forModule(SUPER_HERO_REACT_MODULE, superHeroBundle));
                markMessageAsPresented(SuperHeroBundleUtil.m1449id(superHeroBundle));
                this.currentlyShownMessageForLogging = superHeroBundle;
                this.jitneyLogger.showHero(superHeroBundle);
            } else if (!hasEmptyOrNullMessages) {
                throw new IllegalStateException("Tried to trigger SuperHero but no SuperHeroInterface found");
            }
        }
    }

    public void presentFull() {
        if (this.superHeroInterface != null) {
            this.superHeroInterface.presentFull();
            return;
        }
        throw new IllegalStateException("Tried to present full SuperHero but no SuperHeroInterface found");
    }

    public void dismiss() {
        if (this.superHeroInterface != null) {
            this.superHeroInterface.dismiss();
            if (this.currentlyShownMessageForLogging != null) {
                this.jitneyLogger.dismissHero(this.currentlyShownMessageForLogging);
                return;
            }
            return;
        }
        throw new IllegalStateException("Tried to dismiss SuperHero but no SuperHeroInterface found");
    }

    public void clickedHeroAction(long messageId) {
        if (SuperHeroBundleUtil.m1449id(this.currentlyShownMessageForLogging) == messageId) {
            this.jitneyLogger.clickHero(this.currentlyShownMessageForLogging);
        } else {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Tried to log click on SuperHero message that wasn't currently showing."));
        }
    }

    public void setupLocationListener(final Context context) {
        if (LocationUtil.hasLocationPermission(context)) {
            this.locationClient = Factory.get(context, new LocationClientCallbacks() {
                public void onConnected() {
                    Location lastLocation = LocationUtil.getLastKnownLocation(context);
                    if (lastLocation != null) {
                        SuperHeroManagerImpl.this.onMessagesUpdatedOrError(lastLocation);
                        SuperHeroManagerImpl.this.lastKnownLocation = lastLocation;
                    }
                }

                public void onLocationUpdated(Location location) {
                    if (SuperHeroManagerImpl.this.locationClient != null) {
                        SuperHeroManagerImpl.this.locationClient.disconnectLocationClient();
                    }
                    SuperHeroManagerImpl.this.onMessagesUpdatedOrError(location);
                }
            });
            this.locationClient.connectLocationClient();
        }
    }
}
