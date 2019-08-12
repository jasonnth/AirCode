package com.airbnb.android.itinerary.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.ItineraryGraph;
import com.airbnb.android.itinerary.controllers.ItineraryControllerInterface;
import com.airbnb.android.itinerary.controllers.ItineraryDataController;
import com.airbnb.android.itinerary.controllers.ItineraryDataController.ItinerarySnackbarListener;
import com.airbnb.android.itinerary.controllers.ItineraryJitneyLogger;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.controllers.ItineraryPerformanceAnalytics;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;

public class ItineraryParentFragment extends AirFragment implements OnBackListener, ItineraryControllerInterface, ItinerarySnackbarListener {
    private static final String EXTRA_INIT_PAGE = "extra_init_page";
    public static final String PARAM_TIMELINE = "param_timeline";
    public static final String PARAM_TRIP = "param_trip";
    public static final String TAG = "ItineraryParentFragment";
    private final SnackbarWrapper errorSnackbarWrapper = new SnackbarWrapper().duration(0);
    @State
    boolean isFirstLoad;
    private ItineraryDataController itineraryDataController;
    private ItineraryJitneyLogger itineraryJitneyLogger;
    private ItineraryNavigationController itineraryNavigationController;
    private ItineraryPerformanceAnalytics itineraryPerformanceAnalytics;
    private ItineraryTableOpenHelper itineraryTableOpenHelper;
    PerformanceLogger performanceLogger;
    SharedPrefsHelper sharedPrefsHelper;
    private View snackbarView;

    public static ItineraryParentFragment instance(String initPage) {
        return (ItineraryParentFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ItineraryParentFragment()).putString(EXTRA_INIT_PAGE, initPage)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ItineraryGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.itineraryPerformanceAnalytics = new ItineraryPerformanceAnalytics(this.performanceLogger);
        this.itineraryJitneyLogger = new ItineraryJitneyLogger(this.loggingContextFactory);
        this.itineraryNavigationController = new ItineraryNavigationController(getActivity(), getContext(), savedInstanceState, getChildFragmentManager(), this.itineraryPerformanceAnalytics, this.itineraryJitneyLogger);
        this.itineraryTableOpenHelper = new ItineraryTableOpenHelper(getContext());
        this.itineraryDataController = new ItineraryDataController(this.itineraryTableOpenHelper, this.requestManager, this.itineraryPerformanceAnalytics, this.sharedPrefsHelper, this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5755R.layout.fragment_itinerary_base, container, false);
        loadStateForArguments();
        this.snackbarView = view;
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.itineraryDataController.onSaveInstanceState(outState);
        this.itineraryNavigationController.onSaveInstanceState(outState);
    }

    private void loadStateForArguments() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            this.itineraryNavigationController.navigateToTimeline(true);
            return;
        }
        this.isFirstLoad = true;
        String initPage = bundle.getString(EXTRA_INIT_PAGE);
        if (TextUtils.isEmpty(this.itineraryNavigationController.getCurrentFragmentTag())) {
            if (!TextUtils.isEmpty(initPage)) {
                char c = 65535;
                switch (initPage.hashCode()) {
                    case -1789641805:
                        if (initPage.equals(PARAM_TIMELINE)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 453207191:
                        if (initPage.equals(PARAM_TRIP)) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.itineraryNavigationController.navigateToTimeline(this.isFirstLoad);
                        break;
                    default:
                        this.itineraryNavigationController.navigateToTimeline(this.isFirstLoad);
                        break;
                }
            }
        } else {
            this.itineraryNavigationController.navigateToCurrentFragment(this.isFirstLoad);
        }
        bundle.clear();
    }

    public boolean onBackPressed() {
        if (this.itineraryNavigationController.handleOnBackPressed()) {
            return true;
        }
        return getChildFragmentManager().popBackStackImmediate();
    }

    public ItineraryDataController getDataController() {
        return this.itineraryDataController;
    }

    public ItineraryNavigationController getNavigationController() {
        return this.itineraryNavigationController;
    }

    public ItineraryPerformanceAnalytics getPerformanceAnalytics() {
        return this.itineraryPerformanceAnalytics;
    }

    public ItineraryTableOpenHelper getTableOpenHelper() {
        return this.itineraryTableOpenHelper;
    }

    public ItineraryJitneyLogger getJitneyLogger() {
        return this.itineraryJitneyLogger;
    }

    public void showNetworkErrorSnackbar(NetworkException exception) {
        this.errorSnackbarWrapper.view(this.snackbarView).title(getContext().getString(C5755R.string.error), true).body(NetworkUtil.getErrorMessage(getContext(), exception)).action(C5755R.string.dismiss, ItineraryParentFragment$$Lambda$1.lambdaFactory$(this)).buildAndShow();
    }

    /* access modifiers changed from: private */
    public void dimissErrorSnackbar() {
        if (this.errorSnackbarWrapper.isShown()) {
            this.errorSnackbarWrapper.dismiss();
        }
    }
}
