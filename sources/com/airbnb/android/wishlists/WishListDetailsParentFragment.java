package com.airbnb.android.wishlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerController;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerControllerProvider;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerFragmentBuilder;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.interfaces.OnHomeListener;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.models.WishlistedListing;
import com.airbnb.android.core.requests.DeleteWishlistMembershipRequest;
import com.airbnb.android.core.requests.WishListDetailsRequest;
import com.airbnb.android.core.requests.WishListMembershipsRequest;
import com.airbnb.android.core.responses.WishListMembershipsResponse;
import com.airbnb.android.core.responses.WishListResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import icepick.State;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class WishListDetailsParentFragment extends AirFragment implements CalendarViewCallbacks, GuestPickerControllerProvider, OnBackListener, OnHomeListener, WishListsChangedListener {
    private static final String EXTRA_WISH_LIST_ID = "extra_wish_list";
    private final Set<OnWishListChangedListener> changeListeners = new HashSet();
    @BindView
    ViewGroup contentContainer;
    private final GuestPickerController guestPickerController = new GuestPickerController() {
        public void onGuestDetailsSaved(GuestDetails data, UpdateRequestListener listener) {
            WishListDetailsParentFragment.this.onGuestFiltersSaved(data);
            FindTweenAnalytics.trackSaveGuests(NavigationTag.WishList, data);
        }

        public NavigationTag getNavigationAnalyticsTag() {
            return NavigationTag.FindGuestSheet;
        }
    };
    final RequestListener<WishListMembershipsResponse> membersRequestListener = new C0699RL().onResponse(WishListDetailsParentFragment$$Lambda$3.lambdaFactory$(this)).onComplete(WishListDetailsParentFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<WishListMembershipsResponse> removeMemberRequestListener = new C0699RL().onError(WishListDetailsParentFragment$$Lambda$5.lambdaFactory$(this)).build();
    @State
    WishList wishList;
    private WishListAnalytics wishListAnalytics;
    private long wishListId;
    @State
    ArrayList<User> wishListMembers = new ArrayList<>();
    final RequestListener<WishListResponse> wishListRequestListener = new C0699RL().onResponse(WishListDetailsParentFragment$$Lambda$1.lambdaFactory$(this)).onError(WishListDetailsParentFragment$$Lambda$2.lambdaFactory$(this)).build();
    WishListLogger wlLogger;

    public interface OnWishListChangedListener {
        void onMembersChanged();

        void onWishListChanged();
    }

    public static WishListDetailsParentFragment instance(long wishListId2) {
        Check.state(wishListId2 != -1);
        return (WishListDetailsParentFragment) ((FragmentBundleBuilder) FragmentBundler.make(new WishListDetailsParentFragment()).putLong(EXTRA_WISH_LIST_ID, wishListId2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WishListDetailsGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.wishListId = getArguments().getLong(EXTRA_WISH_LIST_ID);
        this.wishListAnalytics = new WishListAnalytics(this.mAccountManager, this.wishListMembers);
        if (savedInstanceState == null) {
            doInitialLoad();
            showFragment(new WishListDetailsFragment(), C1758R.C1760id.content_container, FragmentTransitionType.None, false);
        } else if (this.wishList != null) {
            WishList managerCopy = this.wishListManager.getWishList(this.wishList);
            if (managerCopy != null) {
                this.wishList = managerCopy;
            }
        }
        this.wishListManager.addWishListsChangedListener(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C1758R.layout.fragment_wish_list_details_parent, container, false);
        bindViews(v);
        return v;
    }

    private void doInitialLoad() {
        this.wishList = this.wishListManager.getWishListById(this.wishListId);
        User currentUser = this.mAccountManager.getCurrentUser();
        if (!(this.wishList == null || currentUser == null)) {
            this.wishListMembers.add(currentUser);
        }
        refresh();
    }

    public void refresh() {
        this.requestManager.cancelRequests(this.wishListRequestListener);
        loadMembers();
    }

    private void loadWishList() {
        new WishListDetailsRequest(this.wishListId, isCurrentUserAMember()).withListener((Observer) this.wishListRequestListener).doubleResponse(this.wishList == null).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$1(WishListDetailsParentFragment wishListDetailsParentFragment, AirRequestNetworkException e) {
        if (NetworkUtil.isUserUnauthorized(e)) {
            Toast.makeText(wishListDetailsParentFragment.getContext(), C1758R.string.wish_list_private, 0).show();
        } else {
            NetworkUtil.toastNetworkError(wishListDetailsParentFragment.getContext(), (NetworkException) e);
        }
        wishListDetailsParentFragment.exitDetailsScreen();
    }

    public boolean isCurrentUserAMember() {
        return getWishListMembers().contains(this.mAccountManager.getCurrentUser());
    }

    public void onDestroy() {
        this.wishListManager.removeWishListsChangedListener(this);
        super.onDestroy();
    }

    public void registerOnWishListChangedListener(OnWishListChangedListener listener) {
        this.changeListeners.add(listener);
    }

    public void unregisterOnWishListChangedListener(OnWishListChangedListener listener) {
        this.changeListeners.remove(listener);
    }

    public WishList getWishList() {
        return this.wishList;
    }

    public WishListAnalytics getWishListAnalytics() {
        return this.wishListAnalytics;
    }

    public long getWishListId() {
        return this.wishListId;
    }

    public boolean isCurrentUserWishListOwner() {
        return this.wishList != null && this.mAccountManager.isCurrentUser(this.wishList.getUserId());
    }

    public void onGuestFiltersSaved(GuestDetails guestData) {
        this.wishListManager.setGuestFilters(this.wishList, guestData);
        this.wishListAnalytics.trackGuestsSet(this.wishList);
        getChildFragmentManager().popBackStack();
    }

    public List<User> getWishListMembers() {
        return this.wishListMembers;
    }

    private void loadMembers() {
        new WishListMembershipsRequest(this.wishListId).withListener((Observer) this.membersRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$2(WishListDetailsParentFragment wishListDetailsParentFragment, WishListMembershipsResponse data) {
        wishListDetailsParentFragment.wishListMembers.clear();
        wishListDetailsParentFragment.wishListMembers.addAll(data.getCollaborators());
        wishListDetailsParentFragment.notifyMembersChanged();
    }

    static /* synthetic */ void lambda$new$3(WishListDetailsParentFragment wishListDetailsParentFragment, Boolean success) {
        wishListDetailsParentFragment.wishListAnalytics.setMembers(wishListDetailsParentFragment.wishListMembers);
        wishListDetailsParentFragment.loadWishList();
    }

    public void onTweenSaveClicked() {
        getChildFragmentManager().popBackStack();
    }

    public void onFiltersClicked() {
        showModal(new WishListTweenFragment(), C1758R.C1760id.content_container, C1758R.C1760id.modal_container, true);
    }

    public void onFiltersCleared() {
        this.wishListManager.setDatesAndGuestFilters(this.wishList, null, null, new GuestDetails());
    }

    public void onGuestsClicked() {
        showModal(new GuestPickerFragmentBuilder(getWishList().getGuestDetails(), NavigationTag.WishList.trackingName).showMaxGuestsDescription(false).build(), C1758R.C1760id.content_container, C1758R.C1760id.modal_container, true);
    }

    /* access modifiers changed from: 0000 */
    public void handleUpdatedWishList(WishList wishList2) {
        WishList wlFromManager = this.wishListManager.getWishList(wishList2);
        if (wlFromManager == null || wlFromManager.getCreatedAt() <= wishList2.getCreatedAt()) {
            this.wishList = wishList2;
            if (isCurrentUserAMember()) {
                this.wishListManager.addWishList(wishList2);
            } else {
                notifyWishListChanged();
            }
        } else {
            this.wishList = wlFromManager;
            notifyWishListChanged();
        }
    }

    public void onWishListsChanged(List<WishList> list, WishListChangeInfo changeInfo) {
        if (this.wishListManager.hasWishList(this.wishList)) {
            this.wishList = this.wishListManager.getWishList(this.wishList);
            notifyWishListChanged();
        }
    }

    private void notifyWishListChanged() {
        for (OnWishListChangedListener listener : this.changeListeners) {
            listener.onWishListChanged();
        }
    }

    private void notifyMembersChanged() {
        for (OnWishListChangedListener listener : this.changeListeners) {
            listener.onMembersChanged();
        }
    }

    public void onMembersRowClicked() {
        if (this.wishList != null) {
            this.wlLogger.clickCollaborators(this.wishList);
            showModal(new WishListMembersFragment(), C1758R.C1760id.content_container, C1758R.C1760id.modal_container, true);
        }
    }

    public void showVotesForItem(WishListItem item) {
        showModal(WishListVotesFragment.instance(item), C1758R.C1760id.content_container, C1758R.C1760id.modal_container, true);
    }

    public void showMapWithListings(List<WishlistedListing> listings) {
        this.wlLogger.clickGoToMap(this.wishList);
        showModal(WishListDetailsMapFragment.instance(listings), C1758R.C1760id.content_container, C1758R.C1760id.modal_container, true);
    }

    public void removeMember(User member) {
        this.wishListMembers.remove(member);
        new DeleteWishlistMembershipRequest(this.wishList, member, this.removeMemberRequestListener).execute(this.requestManager);
        notifyMembersChanged();
        if (this.mAccountManager.isCurrentUser(member.getId())) {
            this.wishListManager.removeWishList(this.wishList);
            exitDetailsScreen();
        }
    }

    static /* synthetic */ void lambda$new$4(WishListDetailsParentFragment wishListDetailsParentFragment, AirRequestNetworkException e) {
        wishListDetailsParentFragment.wishListMembers.add(((DeleteWishlistMembershipRequest) e.request()).getUser());
        wishListDetailsParentFragment.notifyMembersChanged();
        wishListDetailsParentFragment.showNetworkError(e);
    }

    public void onDatesClicked() {
        showModal(DatesFragment.forDates(this.wishList.getCheckin(), this.wishList.getCheckout(), NavigationTag.WishList), C1758R.C1760id.content_container, C1758R.C1760id.modal_container, true);
    }

    public void onCalendarDatesApplied(AirDate checkIn, AirDate checkOut) {
        this.wishListManager.setDates(this.wishList, checkIn, checkOut);
        this.wishListAnalytics.trackDatesSet(this.wishList);
        getChildFragmentManager().popBackStack();
        FindTweenAnalytics.trackSaveDates(NavigationTag.WishList, checkIn, checkOut);
    }

    public void onStartDateClicked(AirDate start) {
    }

    public void onEndDateClicked(AirDate end) {
    }

    public void onInviteFriendClicked() {
        this.wlLogger.clickInviteCollaborators(this.wishList);
        getActivity().startActivity(ShareActivityIntents.newIntentForWishlistInvite(getContext(), getWishList()));
    }

    public GuestPickerController getGuestPickerController() {
        return this.guestPickerController;
    }

    public void showNetworkError(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(this.contentContainer, e);
    }

    public WishListLogger getWlLogger() {
        return this.wlLogger;
    }

    public boolean onBackPressed() {
        return handleBackPress();
    }

    public boolean onHomePressed() {
        return handleBackPress();
    }

    private boolean handleBackPress() {
        if (!isResumed()) {
            return true;
        }
        return getChildFragmentManager().popBackStackImmediate();
    }

    public void exitDetailsScreen() {
        ((WishListsFragment) getParentFragment()).goToIndex();
    }
}
