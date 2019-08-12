package com.airbnb.android.wishlists;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.UpdateWishListRequest;
import com.airbnb.android.core.responses.WishListResponse;
import com.airbnb.android.core.utils.LayoutManagerUtils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.wishlists.WLDetailsDataController.OnWLDetailsDataChanged;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.NavigationPill;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.base.Objects;
import icepick.State;
import p032rx.Observer;

public class WishListDetailsFragment extends BaseWishListDetailsFragment implements OnWLDetailsDataChanged, WLDetailsFragmentInterface {
    private static final int DIALOG_REQ_WL_DELETE_CONFIRM = 394;
    @State
    AirDate checkIn;
    @State
    AirDate checkOut;
    /* access modifiers changed from: private */
    public WLDetailsDataController dataController;
    private WishListDetailsEpoxyController epoxyController;
    @State
    GuestDetails guestFilters;
    private final OnClickListener mapPillClickListener = new OnClickListener() {
        public void onClick(View v) {
            WishListDetailsFragment.this.parentFragment.showMapWithListings(WishListDetailsFragment.this.dataController.availableListings);
        }
    };
    @BindView
    NavigationPill navigationPill;
    final RequestListener<WishListResponse> privacyRequestListener = new C0699RL().onResponse(WishListDetailsFragment$$Lambda$1.lambdaFactory$(this)).onError(WishListDetailsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    @BindView
    AirToolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.dataController = new WLDetailsDataController(savedInstanceState);
        this.epoxyController = new WishListDetailsEpoxyController(getActivity(), savedInstanceState, this.dataController);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1758R.layout.fragment_wish_list_details, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.dataController.addListener(this);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.dataController.attachInterface(this);
        LayoutManagerUtils.setGridLayout((EpoxyController) this.epoxyController, this.recyclerView, isTabletScreen() ? 12 : 6);
        this.epoxyController.setInterface(this);
        this.epoxyController.requestModelBuild();
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.swipeRefreshLayout.setScrollableChild(this.recyclerView);
        this.swipeRefreshLayout.setOnRefreshListener(WishListDetailsFragment$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onActivityCreated$0(WishListDetailsFragment wishListDetailsFragment) {
        wishListDetailsFragment.swipeRefreshLayout.setRefreshing(false);
        wishListDetailsFragment.dataController.clear();
        wishListDetailsFragment.parentFragment.refresh();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.epoxyController.cancelPendingModelBuild();
        this.epoxyController.setInterface(null);
        this.dataController.removeListener(this);
    }

    public void onResume() {
        super.onResume();
        if (saveFilterSettings()) {
            this.dataController.refresh();
            return;
        }
        this.dataController.updateItemsIfNeeded();
        this.epoxyController.requestModelBuild();
    }

    private boolean saveFilterSettings() {
        boolean changed;
        boolean z;
        boolean z2 = true;
        if (!hasLoadedWishList()) {
            return false;
        }
        AirDate newCheckIn = getWishList().getCheckin();
        if (!Objects.equal(this.checkIn, newCheckIn)) {
            changed = true;
        } else {
            changed = false;
        }
        this.checkIn = newCheckIn;
        AirDate newCheckOut = getWishList().getCheckout();
        if (!Objects.equal(this.checkOut, newCheckOut)) {
            z = true;
        } else {
            z = false;
        }
        boolean changed2 = changed | z;
        this.checkOut = newCheckOut;
        GuestDetails newGuestFilters = getWishList().getGuestDetails();
        if (Objects.equal(this.guestFilters, newGuestFilters)) {
            z2 = false;
        }
        boolean changed3 = changed2 | z2;
        this.guestFilters = newGuestFilters;
        return changed3;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
        this.dataController.onSaveInstanceState(outState);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean showPublicShareButton;
        if (getActivity() != null) {
            MenuItem privacyMenuItem = menu.findItem(C1758R.C1760id.menu_toggle_privacy);
            if (hasLoadedWishList()) {
                privacyMenuItem.setTitle(getWishList().isPrivateWishList() ? C1758R.string.wishlist_change_privacy_public : C1758R.string.wishlist_change_privacy_private);
            }
            if (!hasLoadedWishList() || !isCurrentUserWishListOwner()) {
                z = false;
            } else {
                z = true;
            }
            privacyMenuItem.setVisible(z);
            MenuItem deleteMenuItem = menu.findItem(C1758R.C1760id.menu_delete);
            if (!hasLoadedWishList() || !isCurrentUserWishListOwner()) {
                z2 = false;
            } else {
                z2 = true;
            }
            deleteMenuItem.setVisible(z2);
            MenuItem findItem = menu.findItem(C1758R.C1760id.menu_filter);
            if (!hasLoadedWishList() || !isCurrentUserWishListOwner()) {
                z3 = false;
            } else {
                z3 = true;
            }
            findItem.setVisible(z3);
            if (canCurrentUserInviteFriends() || !hasLoadedWishList() || getWishList().isPrivateWishList()) {
                showPublicShareButton = false;
            } else {
                showPublicShareButton = true;
            }
            menu.findItem(C1758R.C1760id.menu_share).setVisible(showPublicShareButton);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == C1758R.C1760id.menu_filter) {
            this.parentFragment.onFiltersClicked();
            return true;
        } else if (itemId == C1758R.C1760id.menu_delete) {
            showDeleteWishListDialog();
            return true;
        } else if (itemId == C1758R.C1760id.menu_toggle_privacy) {
            togglePrivacy();
            return true;
        } else if (itemId != C1758R.C1760id.menu_share) {
            return super.onOptionsItemSelected(item);
        } else {
            getWLLogger().clickShareButton(getWishList());
            getActivity().startActivity(ShareActivityIntents.newIntentForWishlistShare(getContext(), getWishList()));
            return true;
        }
    }

    private void showDeleteWishListDialog() {
        ZenDialog.builder().withTitle(C1758R.string.list_delete_title).withBodyText(getString(C1758R.string.wishlist_delete_confirm_msg, getWishList().getName())).withDualButton(C1758R.string.cancel, 0, C1758R.string.delete, (int) DIALOG_REQ_WL_DELETE_CONFIRM, (Fragment) this).create().show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DIALOG_REQ_WL_DELETE_CONFIRM /*394*/:
                if (resultCode == -1) {
                    this.wishListManager.deleteWishList(getWishList());
                    this.parentFragment.exitDetailsScreen();
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void togglePrivacy() {
        UpdateWishListRequest.setPrivate(getWishList().getId(), !getWishList().isPrivateWishList()).withListener((Observer) this.privacyRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$1(WishListDetailsFragment wishListDetailsFragment, WishListResponse response) {
        wishListDetailsFragment.parentFragment.handleUpdatedWishList(response.wishList);
        new SnackbarWrapper().body(wishListDetailsFragment.getString(wishListDetailsFragment.getWishList().isPrivateWishList() ? C1758R.string.list_privacy_updated_msg_private : C1758R.string.list_privacy_updated_msg_public)).view(wishListDetailsFragment.getView()).duration(-1).buildAndShow();
    }

    public void onWishListChanged() {
        getActivity().supportInvalidateOptionsMenu();
        if (!hasLoadedWishList() || (isResumed() && saveFilterSettings())) {
            this.dataController.refresh();
        } else if (!this.dataController.loadDataIfNeeded()) {
            this.dataController.removeAllUnwishlistedItems();
            this.epoxyController.requestModelBuild();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowBottomBar() {
        return true;
    }

    public void onWLDetailsDataChanged() {
        this.epoxyController.requestModelBuild();
    }

    public void onMembersChanged() {
        this.epoxyController.requestModelBuild();
    }

    public void onMembersRowClicked() {
        this.parentFragment.onMembersRowClicked();
    }

    public void onInviteFriendClicked() {
        this.parentFragment.onInviteFriendClicked();
    }

    public WishListLogger getWLLogger() {
        return this.parentFragment.getWlLogger();
    }

    public User getCurrentUser() {
        return this.mAccountManager.getCurrentUser();
    }

    public void showVotesForItem(WishListItem item) {
        this.parentFragment.showVotesForItem(item);
    }

    public void onTabShown(WLTab tab) {
        ViewLibUtils.setVisibleIf(this.navigationPill, tab == WLTab.Homes && this.dataController.hasAvailableListings());
        this.navigationPill.setRightButtonClickListener(this.mapPillClickListener);
    }

    public boolean isShowingMapPill() {
        return this.navigationPill.getVisibility() == 0;
    }

    public void showFilters() {
        this.parentFragment.onFiltersClicked();
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public WishListManager getWishListManager() {
        return this.wishListManager;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.WishList;
    }
}
