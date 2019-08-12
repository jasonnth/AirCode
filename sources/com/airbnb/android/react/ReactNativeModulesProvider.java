package com.airbnb.android.react;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.net.NetworkMonitor;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListManager;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.camera.ImageEditingManager;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.location.LocationModule;
import com.facebook.react.modules.netinfo.NetInfoModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.storage.AsyncStorageModule;
import com.facebook.react.modules.timepicker.TimePickerDialogModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.imagepicker.ImagePickerModule;
import com.joshblour.reactnativepermissions.ReactNativePermissionsModule;
import com.squareup.otto.Bus;
import java.util.Arrays;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ReactNativeModulesProvider {
    private final AirbnbAccountManager airbnbAccountManager;
    private final AirlockErrorHandler airlockErrorHandler;
    private final Bus bus;
    private final ReactNavigationCoordinator coordinator;
    private final CurrencyFormatter currencyFormatter;
    private final ExperimentsProvider experimentsProvider;
    private final ItineraryManager itineraryManager;
    private final NetworkMonitor networkMonitor;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final SuperHeroManager superHeroManager;
    private final WishListLogger wishListLogger;
    private final WishListManager wishListManager;

    public ReactNativeModulesProvider(AirbnbAccountManager airbnbAccountManager2, ExperimentsProvider experimentsProvider2, CurrencyFormatter currencyFormatter2, Bus bus2, OkHttpClient okHttpClient2, ReactNavigationCoordinator coordinator2, WishListManager wishListManager2, SuperHeroManager superHeroManager2, Retrofit retrofit3, WishListLogger wishListLogger2, NetworkMonitor networkMonitor2, ItineraryManager itineraryManager2, AirlockErrorHandler airlockErrorHandler2) {
        this.airbnbAccountManager = airbnbAccountManager2;
        this.experimentsProvider = experimentsProvider2;
        this.currencyFormatter = currencyFormatter2;
        this.okHttpClient = okHttpClient2;
        this.coordinator = coordinator2;
        this.bus = bus2;
        this.wishListManager = wishListManager2;
        this.superHeroManager = superHeroManager2;
        this.retrofit = retrofit3;
        this.wishListLogger = wishListLogger2;
        this.networkMonitor = networkMonitor2;
        this.itineraryManager = itineraryManager2;
        this.airlockErrorHandler = airlockErrorHandler2;
    }

    public List<NativeModule> get(ReactApplicationContext reactContext) {
        OkHttpClientProvider.replaceOkHttpClient(this.okHttpClient);
        return Arrays.asList(new NativeModule[]{new AppStateModule(reactContext), new AsyncStorageModule(reactContext), new CameraRollManager(reactContext), new ClipboardModule(reactContext), new DatePickerDialogModule(reactContext), new DialogModule(reactContext), new ImageEditingManager(reactContext), new ImageStoreManager(reactContext), new IntentModule(reactContext), new LocationModule(reactContext), new NetInfoModule(reactContext), new StatusBarModule(reactContext), new TimePickerDialogModule(reactContext), new ToastModule(reactContext), new VibrationModule(reactContext), new WebSocketModule(reactContext), new NativeAnimatedModule(reactContext), new PermissionsModule(reactContext), new AirbnbNetworkingModule(this.retrofit, reactContext, this.airlockErrorHandler, new NetworkingModule(reactContext)), new TrebuchetModule(reactContext, this.bus), new AccountModule(reactContext, this.airbnbAccountManager, this.currencyFormatter, this.bus), new WeChatModule(reactContext), new ReactDeviceInfoModule(reactContext, this.networkMonitor), new AirImageLoaderModule(reactContext), new ExperimentModule(reactContext, this.experimentsProvider, this.bus), new I18nModule(reactContext, this.bus), new WishListModule(reactContext, this.wishListManager, this.wishListLogger), new SuperHeroModule(reactContext, this.superHeroManager), new TrackingModule(reactContext), new MapProviderPickerModule(reactContext), new NavigatorModule(reactContext, this.coordinator), new ImagePickerModule(reactContext), new ReactNativePermissionsModule(reactContext), new ErrorModule(reactContext), new ItineraryModule(reactContext, this.itineraryManager)});
    }
}
