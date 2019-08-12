package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.utils.SpannableParagraphBuilder;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.adapters.SpaceTypeAdapter;
import com.airbnb.android.listyourspacedls.adapters.SpaceTypeAdapter.Listener;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSSpaceTypeFragment extends LYSBaseFragment {
    private SpaceTypeAdapter adapter;
    LYSJitneyLogger jitneyLogger;
    private final Listener listener = new Listener() {
        public void logRoomSelectPlaceType(SpaceType placeType) {
            LYSSpaceTypeFragment.this.jitneyLogger.logRoomSelectPlaceTypeEvent(placeType.serverDescKey, Long.valueOf(LYSSpaceTypeFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSSpaceTypeFragment.this.controller.getListing().getId()));
        }

        public void logRoomSelectPropertyType(PropertyType propertyType) {
            LYSSpaceTypeFragment.this.jitneyLogger.logRoomSelectPropertyTypeEvent(propertyType.name(), Long.valueOf(LYSSpaceTypeFragment.this.mAccountManager.getCurrentUserId()), Long.valueOf(LYSSpaceTypeFragment.this.controller.getListing().getId()));
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateSpaceTypePropertyTypeListener = new C0699RL().onResponse(LYSSpaceTypeFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSSpaceTypeFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSSpaceTypeFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static Fragment newInstance() {
        return new LYSSpaceTypeFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSSpaceTypeFragment lYSSpaceTypeFragment, SimpleListingResponse response) {
        lYSSpaceTypeFragment.controller.setListing(response.listing);
        lYSSpaceTypeFragment.navigateInFlow(LYSStep.SpaceType);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new SpaceTypeAdapter(getContext(), this.controller.getListing(), savedInstanceState, this.listener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        showTip(C7251R.string.lys_dls_space_type_tip, LYSSpaceTypeFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        updateSpaceTypePropertyType();
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        updateSpaceTypePropertyType();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        Listing listing = this.controller.getListing();
        return !listing.getRoomTypeKey().equals(this.adapter.getSpaceType().serverDescKey) || listing.getPropertyTypeId() != this.adapter.getPropertyType().serverDescKey;
    }

    /* access modifiers changed from: private */
    public void showTipModal() {
        this.controller.showTipModal(C7251R.string.lys_dls_space_type_tip_title, new SpannableParagraphBuilder(getContext()).append(C7251R.string.lys_dls_space_type_tip_text_title_1, C7251R.string.lys_dls_space_type_tip_text_paragraph_1).append(C7251R.string.lys_dls_space_type_tip_text_title_2, C7251R.string.lys_dls_space_type_tip_text_paragraph_2).append(C7251R.string.lys_dls_space_type_tip_text_title_3, C7251R.string.lys_dls_space_type_tip_text_paragraph_3).build(), NavigationTag.LYSRoomTypeTip);
    }

    private void updateSpaceTypePropertyType() {
        if (!this.controller.isListingCreated()) {
            updateLocalListing();
            navigateInFlow(LYSStep.SpaceType);
        } else if (canSaveChanges()) {
            sendUpdateSpaceTypePropertyTypeRequest();
        } else {
            navigateInFlow(LYSStep.SpaceType);
        }
    }

    private void sendUpdateSpaceTypePropertyTypeRequest() {
        setLoading(this.adapter);
        UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), Strap.make().mo11639kv(ListingRequestConstants.JSON_ROOM_TYPE_KEY, this.adapter.getSpaceType().serverDescKey).mo11637kv(ListingRequestConstants.JSON_PROPERTY_TYPE_KEY, this.adapter.getPropertyType().serverDescKey).mo11639kv(ListingRequestConstants.JSON_LIST_YOUR_SPACE_LAST_FINISHED_STEP_ID_KEY, this.controller.getMaxReachedStep().stepId)).withListener((Observer) this.updateSpaceTypePropertyTypeListener).execute(this.requestManager);
    }

    private void updateLocalListing() {
        this.controller.getListing().setRoomTypeKey(this.adapter.getSpaceType().serverDescKey);
        this.controller.getListing().setPropertyTypeId(this.adapter.getPropertyType().serverDescKey);
        this.controller.setListing(this.controller.getListing());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSRoomType;
    }
}
