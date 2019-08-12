package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.core.intents.DebugMenuIntents;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.HostReferralsIntents;
import com.airbnb.android.core.intents.InboxActivityIntents;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.intents.ShakeFeedbackDialogIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.intents.WhatsMyPlaceWorthIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import com.airbnb.android.lib.activities.HomeActivity.AccountMode;
import com.airbnb.android.lib.activities.SettingsActivity;
import com.airbnb.android.lib.adapters.AccountPageAdapter;
import com.airbnb.android.lib.adapters.AccountPageAdapter.Listener;
import com.airbnb.android.lib.businesstravel.network.UpdateShowTravelForWorkRequest;
import com.airbnb.android.lib.interfaces.ModeSwitchListener;
import com.airbnb.android.lib.listyourspace.LYSAnalytics;
import com.airbnb.android.profile_completion.ProfileCompletionActivity;
import com.airbnb.android.profile_completion.ProfileCompletionManager;
import com.airbnb.android.profile_completion.ProfileCompletionManager.ProfileCompletionListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.erf.Experiments;

public class AccountPageFragment extends AirFragment implements ProfileCompletionListener {
    private static final String ARG_ACCOUNT_MODE = "arg_account_mode";
    private static final int RC_PROFILE_COMPLETION = 101;
    private static final int RC_USER_PROFILE = 102;
    /* access modifiers changed from: private */
    public AccountPageAdapter accountPageAdapter;
    private final Listener adapterListener = new Listener() {
        public void continueNewListing(long listingId) {
            AccountPageFragment.this.startActivity(ListYourSpaceIntents.intentForInProgressListing(AccountPageFragment.this.getContext(), listingId, AccountMode.from(AccountPageFragment.this.getArguments().getInt(AccountPageFragment.ARG_ACCOUNT_MODE)) == AccountMode.GUEST ? "AccountDrawerTravelMode" : "AccountDrawerHostMode", "UnfinishedListing"));
        }

        public void itemSelected(AccountPageItem item) {
            boolean showListingsPage = true;
            switch (C67752.f9522x458c8fad[item.ordinal()]) {
                case 1:
                    AccountPageFragment.this.startUserProfileActivity();
                    return;
                case 2:
                    AccountPageFragment.this.accountPageAdapter.hideBadgeOnTrips();
                    AccountPageFragment.this.mPrefsHelper.markBadgeSeenAndClearedForTripsTabMove();
                    AccountPageFragment.this.startActivity(ItineraryUtils.getItineraryIntent(AccountPageFragment.this.getContext()));
                    return;
                case 3:
                    AccountPageFragment.this.startActivity(SettingsActivity.intentForDefault(AccountPageFragment.this.getContext()));
                    return;
                case 4:
                    if (Trebuchet.launch("px_help_center_react_native_v2") && Experiments.enableHelpCenterReactNative()) {
                        AccountPageFragment.this.startActivity(ReactNativeIntents.intentForHelpCenter(AccountPageFragment.this.getContext(), AccountPageFragment.this.mAccountManager.getCurrentUserId(), AccountPageFragment.this.mPrefsHelper.isGuestMode()));
                        return;
                    } else if (AccountMode.from(AccountPageFragment.this.getArguments().getInt(AccountPageFragment.ARG_ACCOUNT_MODE)) == AccountMode.GUEST) {
                        AccountPageFragment.this.startActivity(HelpCenterIntents.intentForHelpCenterWithTripAssistant(AccountPageFragment.this.getContext()));
                        return;
                    } else {
                        AccountPageFragment.this.startActivity(HelpCenterIntents.intentForHelpCenter(AccountPageFragment.this.getContext()));
                        return;
                    }
                case 5:
                    AccountPageFragment.this.startActivity(GiftCardsActivity.intent(AccountPageFragment.this.getContext()));
                    return;
                case 6:
                    AccountPageFragment.this.startActivity(ReactNativeIntents.intentForGiftCardsApp(AccountPageFragment.this.getActivity()));
                    return;
                case 7:
                    if (FeatureToggles.showFeedbackWebVersion()) {
                        AccountPageFragment.this.startActivity(HelpCenterIntents.intentForFeedback(AccountPageFragment.this.getContext(), true, AccountPageFragment.this.mCurrencyHelper.getLocalCurrencyString()).toIntent());
                        return;
                    } else {
                        AccountPageFragment.this.showDialog(ShakeFeedbackDialogIntents.newInstanceForGuestFeedback(), "guest_feedback_dialog");
                        return;
                    }
                case 8:
                    if (FeatureToggles.showFeedbackWebVersion()) {
                        AccountPageFragment.this.startActivity(HelpCenterIntents.intentForFeedback(AccountPageFragment.this.getContext(), false, AccountPageFragment.this.mCurrencyHelper.getLocalCurrencyString()).toIntent());
                        return;
                    } else {
                        AccountPageFragment.this.showDialog(ShakeFeedbackDialogIntents.newInstanceForHostFeedback(), "host_feedback_dialog");
                        return;
                    }
                case 9:
                    AccountPageFragment.this.modeSwitchListener.onClickSwitchMode(AccountMode.GUEST);
                    return;
                case 10:
                    AccountPageFragment.this.modeSwitchListener.onClickSwitchMode(AccountMode.HOST);
                    return;
                case 11:
                    AccountPageFragment.this.modeSwitchListener.onClickSwitchMode(AccountMode.TRIP_HOST);
                    return;
                case 12:
                    LYSAnalytics.trackAction("account_drawer_lys", "enter_lys", LYSAnalytics.extrasForLYSFlowStart());
                    if (Experiments.showWmpwBeforeLysFromAccountPage()) {
                        AccountPageFragment.this.startActivity(WhatsMyPlaceWorthIntents.createIntent(AccountPageFragment.this.getContext()));
                        return;
                    }
                    if (!Experiments.showNewListingAllTheTime() || !AccountPageFragment.this.mAccountManager.userHasListings() || AccountPageFragment.this.mAccountManager.userHasActiveListings()) {
                        showListingsPage = false;
                    }
                    if (showListingsPage) {
                        AccountPageFragment.this.startActivity(HomeActivityIntents.intentForListings(AccountPageFragment.this.getContext()));
                        return;
                    } else {
                        AccountPageFragment.this.startActivity(ListYourSpaceIntents.intentForNewListing(AccountPageFragment.this.getContext(), AccountMode.from(AccountPageFragment.this.getArguments().getInt(AccountPageFragment.ARG_ACCOUNT_MODE)) == AccountMode.HOST ? "AccountDrawerHostMode" : "AccountDrawerTravelMode", "ListYourSpace"));
                        return;
                    }
                case 13:
                    AccountPageFragment.this.startActivity(ReferralsIntents.newIntent(AccountPageFragment.this.getContext(), ReferralsIntents.ENTRY_POINT_AIRNAV));
                    return;
                case 14:
                    AccountPageFragment.this.startActivity(HostReferralsIntents.newIntentForHostReferrals(AccountPageFragment.this.getContext(), AccountMode.from(AccountPageFragment.this.getArguments().getInt(AccountPageFragment.ARG_ACCOUNT_MODE)) == AccountMode.HOST ? "AccountDrawerHostMode" : "AccountDrawerTravelMode"));
                    return;
                case 15:
                    AccountPageFragment.this.startActivity(WebViewIntentBuilder.newBuilder(AccountPageFragment.this.getContext(), "https://community.airbnb.com").authenticate().title(C0880R.string.community_center).toIntent());
                    return;
                case 16:
                    AccountPageFragment.this.startActivity(DebugMenuIntents.create(AccountPageFragment.this.getContext()));
                    return;
                case 17:
                    AccountPageFragment.this.startActivity(InboxActivityIntents.intentForInbox(AccountPageFragment.this.getContext(), InboxType.Host));
                    return;
                case 18:
                    AccountPageFragment.this.startActivity(LoginActivityIntents.intent(AccountPageFragment.this.getContext()));
                    return;
                case 19:
                    AccountPageFragment.this.businessTravelJitneyLogger.logAccountProfileTravelForWorkRowClicked();
                    UpdateShowTravelForWorkRequest.forUserId(AccountPageFragment.this.mAccountManager.getCurrentUserId()).execute(AccountPageFragment.this.requestManager);
                    AccountPageFragment.this.mAccountManager.getCurrentUser().setShowTravelForWork(false);
                    AccountPageFragment.this.startActivityForResult(BusinessTravelIntents.intentForWorkEmail(AccountPageFragment.this.getContext(), WorkEmailLaunchSource.AccountPage), BusinessTravelIntents.REQUEST_CODE_ADD_EMAIL);
                    return;
                case 20:
                    AccountPageFragment.this.businessTravelJitneyLogger.logAccountProfileTravelForWorkImpression();
                    return;
                case 21:
                    AccountPageFragment.this.mPrefsHelper.markBadgeSeenAndClearedForProfileCompletion();
                    AccountPageFragment.this.startActivityForResult(ProfileCompletionActivity.newIntent(AccountPageFragment.this.getContext()), 101);
                    return;
                case 22:
                    AccountPageFragment.this.startActivity(HelpCenterIntents.intentForKoreanCancellationPolicy(AccountPageFragment.this.getContext()));
                    return;
                default:
                    return;
            }
        }
    };
    BottomBarController bottomBarController;
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    ListingPromoController listingPromoController;
    /* access modifiers changed from: private */
    public ModeSwitchListener modeSwitchListener;
    ProfileCompletionManager profileCompletionManager;
    @BindView
    RecyclerView recyclerView;

    /* renamed from: com.airbnb.android.lib.fragments.AccountPageFragment$2 */
    static /* synthetic */ class C67752 {

        /* renamed from: $SwitchMap$com$airbnb$android$lib$fragments$AccountPageFragment$AccountPageItem */
        static final /* synthetic */ int[] f9522x458c8fad = new int[AccountPageItem.values().length];

        static {
            try {
                f9522x458c8fad[AccountPageItem.UserProfile.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f9522x458c8fad[AccountPageItem.Trips.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f9522x458c8fad[AccountPageItem.Settings.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f9522x458c8fad[AccountPageItem.Help.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f9522x458c8fad[AccountPageItem.GiftCards.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f9522x458c8fad[AccountPageItem.Gifts.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f9522x458c8fad[AccountPageItem.FeedbackGuest.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f9522x458c8fad[AccountPageItem.FeedbackHost.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f9522x458c8fad[AccountPageItem.SwitchModeGuest.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f9522x458c8fad[AccountPageItem.SwitchModeHost.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f9522x458c8fad[AccountPageItem.SwitchModeTripHost.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f9522x458c8fad[AccountPageItem.ListYourSpace.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f9522x458c8fad[AccountPageItem.InviteFriends.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f9522x458c8fad[AccountPageItem.HostReferral.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f9522x458c8fad[AccountPageItem.Groups.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f9522x458c8fad[AccountPageItem.InternalSettings.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f9522x458c8fad[AccountPageItem.HostInbox.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f9522x458c8fad[AccountPageItem.LoginOrSignUp.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f9522x458c8fad[AccountPageItem.TravelForWork.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f9522x458c8fad[AccountPageItem.TrackTravelForWork.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                f9522x458c8fad[AccountPageItem.ProfileCompletion.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f9522x458c8fad[AccountPageItem.KoreanCancellationPolicy.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
        }
    }

    public enum AccountPageItem {
        UserProfile,
        Trips,
        InternalSettings,
        SwitchModeGuest,
        SwitchModeHost,
        SwitchModeTripHost,
        Settings,
        Help,
        GiftCards,
        Gifts,
        InviteFriends,
        HostReferral,
        FeedbackGuest,
        FeedbackHost,
        Groups,
        ListYourSpace,
        HostInbox,
        LoginOrSignUp,
        TravelForWork,
        TrackTravelForWork,
        ProfileCompletion,
        KoreanCancellationPolicy
    }

    public static AccountPageFragment newInstance(AccountMode accountMode) {
        return (AccountPageFragment) ((FragmentBundleBuilder) FragmentBundler.make(new AccountPageFragment()).putInt(ARG_ACCOUNT_MODE, accountMode.ordinal())).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ModeSwitchListener) {
            this.modeSwitchListener = (ModeSwitchListener) context;
            return;
        }
        throw new IllegalStateException("Expected context to implement ModeSwitchListener");
    }

    public void onDetach() {
        super.onDetach();
        this.modeSwitchListener = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.recycler_view, container, false);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        bindViews(v);
        AccountMode accountMode = AccountMode.from(getArguments().getInt(ARG_ACCOUNT_MODE));
        this.profileCompletionManager.addUpdateListener(this);
        this.profileCompletionManager.fetchStatus();
        this.accountPageAdapter = new AccountPageAdapter(getContext(), this.mAccountManager, accountMode, this.mAirbnbApi, this.profileCompletionManager, this.mPrefsHelper, this.listingPromoController, this.adapterListener);
        this.recyclerView.setAdapter(this.accountPageAdapter);
        this.recyclerView.setHasFixedSize(true);
        return v;
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.setShowBottomBar(true, true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 468 && resultCode == -1) {
            startUserProfileActivity();
        } else if (requestCode == 101) {
            this.accountPageAdapter.notifyProfileCompletionChanged();
        } else if (requestCode == 102) {
            this.profileCompletionManager.fetchStatus();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* access modifiers changed from: private */
    public void showDialog(AirDialogFragment dialog, String tag) {
        dialog.show(getFragmentManager(), tag);
    }

    /* access modifiers changed from: private */
    public void startUserProfileActivity() {
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            startActivityForResult(UserProfileIntents.intentForCurrentUser(getContext()), 102);
        } else {
            startActivity(LoginActivityIntents.intent(getContext()));
        }
    }

    public void onFetchStatusSuccess(boolean completedStepsChanged) {
        if (completedStepsChanged) {
            this.accountPageAdapter.notifyProfileCompletionChanged();
        }
    }

    public void onFetchStatusError(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(this.recyclerView, e);
    }

    public void onDestroyView() {
        this.profileCompletionManager.removeUpdateListener(this);
        this.accountPageAdapter.destroy();
        super.onDestroyView();
    }
}
