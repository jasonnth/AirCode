package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.businesstravel.BusinessTravelEmployeeFetchedEvent;
import com.airbnb.android.contentframework.StoriesExperimentUtil;
import com.airbnb.android.contentframework.events.ArticleDeletedEvent;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.GetUserFlagRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.UserFlagResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.EditProfileActivity;
import com.airbnb.android.lib.adapters.UserProfileAdapter;
import com.airbnb.android.lib.adapters.UserProfileAdapter.ClickListener;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.util.List;
import p032rx.Observer;

public class UserProfileFragment extends AirFragment {
    private UserProfileAdapter adapter;
    BusinessTravelAccountManager businessTravelAccountManager;
    @State
    boolean hideProfilePhoto;
    @State
    boolean isSelf;
    private List<Listing> listings;
    @BindView
    RecyclerView recyclerView;
    @BindView
    ViewGroup root;
    @BindView
    AirToolbar toolbar;
    @State
    User user;
    final RequestListener<UserFlagResponse> userFlagResponseRequestListener = new C0699RL().onResponse(UserProfileFragment$$Lambda$1.lambdaFactory$(this)).build();
    @State
    long userId;
    final RequestListener<ListingResponse> userListingsListener = new C0699RL().onResponse(UserProfileFragment$$Lambda$4.lambdaFactory$(this)).onError(UserProfileFragment$$Lambda$5.lambdaFactory$(this)).build();
    final RequestListener<UserResponse> userRequestListener = new C0699RL().onResponse(UserProfileFragment$$Lambda$2.lambdaFactory$(this)).onError(UserProfileFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static UserProfileFragment newInstance(Bundle args) {
        UserProfileFragment frag = new UserProfileFragment();
        frag.setArguments(args);
        return frag;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        this.mBus.register(this);
        if (savedInstanceState == null) {
            this.isSelf = getArguments().getBoolean(UserProfileIntents.ARG_IS_SELF, false);
            this.hideProfilePhoto = getArguments().getBoolean(UserProfileIntents.ARG_HIDE_PROFILE_PHOTO, false);
            if (this.isSelf) {
                this.user = this.mAccountManager.getCurrentUser();
                this.userId = this.user.getId();
                if (StoriesExperimentUtil.isStoryFeatureEnabled()) {
                    fetchUserDataWithStories();
                    return;
                }
                return;
            }
            this.userId = getArguments().getLong("user_id", -1);
            if (this.userId == -1) {
                this.userId = (long) getArguments().getInt("user_id", -1);
            }
            this.user = (User) getArguments().getParcelable("user");
            if (this.userId == -1 && this.user != null) {
                this.userId = this.user.getId();
            }
            if (StoriesExperimentUtil.isStoryFeatureEnabled()) {
                fetchUserDataWithStories();
            } else {
                new UserRequest(this.userId, (BaseRequestListener<UserResponse>) this.userRequestListener).execute(this.requestManager);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.adapter = new UserProfileAdapter(getContext(), (ClickListener) getActivity(), getArguments().getBoolean(UserProfileIntents.ARG_HIDE_REVIEWS));
        this.recyclerView.setAdapter(this.adapter);
        this.businessTravelAccountManager.fetchBusinessTravelEmployeeInfo();
        onUserOrListingsUpdated();
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.toolbar.onCreateOptionsMenu(menu, inflater);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C0880R.C0882id.menu_edit_profile).setVisible(this.isSelf);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_edit_profile) {
            return false;
        }
        startActivity(EditProfileActivity.intentForDefault(getContext()));
        return true;
    }

    public void onDestroy() {
        this.mBus.unregister(this);
        super.onDestroy();
    }

    private void fetchUserFlag() {
        new GetUserFlagRequest(FlagContent.User, this.userId, this.mAccountManager.getCurrentUserId()).withListener((Observer) this.userFlagResponseRequestListener).execute(this.requestManager);
    }

    private void onUserOrListingsUpdated() {
        getActivity().supportInvalidateOptionsMenu();
        this.adapter.updateForUser(this.user, this.listings, this.isSelf, this.hideProfilePhoto);
        if (this.businessTravelAccountManager.hasBusinessTravelEmployeeInfo()) {
            this.adapter.updateWorkModel(this.businessTravelAccountManager.getWorkEmailStatus(), this.isSelf);
        }
        if (this.listings == null && this.user != null && this.user.getListingsCount() > 0 && !this.requestManager.hasRequest((BaseRequestListener<T>) this.userListingsListener, ListingRequest.class)) {
            ListingRequest.forMySpaces(this.user.getId(), false, this.userListingsListener).doubleResponse().execute(this.requestManager);
        }
    }

    @Subscribe
    public void onFetchedBusinessTravelEmployee(BusinessTravelEmployeeFetchedEvent event) {
        this.adapter.updateWorkModel(this.businessTravelAccountManager.getWorkEmailStatus(), this.isSelf);
    }

    @Subscribe
    public void onStoryDeleted(ArticleDeletedEvent articleDeletedEvent) {
        fetchUserDataWithStories();
    }

    static /* synthetic */ void lambda$new$0(UserProfileFragment userProfileFragment, UserFlagResponse response) {
        userProfileFragment.user.setUserFlag(response.flag);
        userProfileFragment.adapter.updateForUser(userProfileFragment.user, userProfileFragment.listings, userProfileFragment.isSelf, userProfileFragment.hideProfilePhoto);
    }

    static /* synthetic */ void lambda$new$1(UserProfileFragment userProfileFragment, UserResponse userResponse) {
        userProfileFragment.user = userResponse.user;
        userProfileFragment.isSelf = userProfileFragment.user.equals(userProfileFragment.mAccountManager.getCurrentUser());
        userProfileFragment.onUserOrListingsUpdated();
    }

    static /* synthetic */ void lambda$new$3(UserProfileFragment userProfileFragment, ListingResponse listingResponse) {
        userProfileFragment.listings = listingResponse.getListingsArrayList();
        for (Listing listing : userProfileFragment.listings) {
            listing.trimForHomeCard();
        }
        userProfileFragment.onUserOrListingsUpdated();
    }

    public User getUser() {
        return this.user;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 42:
                    fetchUserFlag();
                    break;
                case BusinessTravelIntents.REQUEST_CODE_ADD_EMAIL /*468*/:
                    this.adapter.updateWorkModel(this.businessTravelAccountManager.getWorkEmailStatus(), this.isSelf);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.UserProfile;
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make().mo11638kv("id_user", this.userId);
    }

    private void fetchUserDataWithStories() {
        UserRequest.newRequestWithStories(this.userId).withListener((Observer) this.userRequestListener).execute(this.requestManager);
    }
}
