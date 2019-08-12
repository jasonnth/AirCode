package com.airbnb.android.nestedlistings.fragments;

import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.p002v7.app.AlertDialog.Builder;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.requests.NestedListingsRequest;
import com.airbnb.android.core.requests.UpdateNestedListingsRequest;
import com.airbnb.android.core.responses.NestedListingsResponse;
import com.airbnb.android.core.responses.UpdateNestedListingsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.nestedlistings.C7496R;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsChooseChildrenAdapter;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsChooseChildrenAdapter.NestedListingsChooseChildrenListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class NestedListingsChooseChildrenFragment extends NestedListingsBaseFragment {
    private static final String FROM_OVERVIEW = "from_overview";
    private static final String PARENT_LISTING = "parent_listing";
    private NestedListingsChooseChildrenListener actionListener = new NestedListingsChooseChildrenListener() {
        public void onSelectEntireHome() {
            if (!NestedListingsChooseChildrenFragment.this.entireHomeWarningSnackbar.isShown()) {
                NestedListingsChooseChildrenFragment.this.entireHomeWarningSnackbar.buildAndShow();
                NestedListingsChooseChildrenFragment.this.showEntireHomeWarningSnackbar = true;
                NestedListingsChooseChildrenFragment.this.saveButton.setEnabled(false);
            }
        }

        public void onUnselectEntireHome() {
            if (NestedListingsChooseChildrenFragment.this.entireHomeWarningSnackbar.isShown()) {
                NestedListingsChooseChildrenFragment.this.entireHomeWarningSnackbar.dismiss();
                NestedListingsChooseChildrenFragment.this.showEntireHomeWarningSnackbar = false;
                NestedListingsChooseChildrenFragment.this.saveButton.setEnabled(true);
            }
        }

        public void onSelectAnyListing() {
            if (NestedListingsChooseChildrenFragment.this.clearChildrenConfirmationSnackbar.isShown()) {
                NestedListingsChooseChildrenFragment.this.clearChildrenConfirmationSnackbar.dismiss();
                NestedListingsChooseChildrenFragment.this.showClearChildrenConfirmationSnackbar = false;
                NestedListingsChooseChildrenFragment.this.saveButton.setEnabled(true);
            }
        }
    };
    private NestedListingsChooseChildrenAdapter adapter;
    /* access modifiers changed from: private */
    public SnackbarWrapper clearChildrenConfirmationSnackbar;
    /* access modifiers changed from: private */
    public SnackbarWrapper entireHomeWarningSnackbar;
    private boolean fromOverview;
    final RequestListener<NestedListingsResponse> nestedListingRefreshListener = new C0699RL().onResponse(NestedListingsChooseChildrenFragment$$Lambda$3.lambdaFactory$(this)).onError(NestedListingsChooseChildrenFragment$$Lambda$4.lambdaFactory$(this)).build();
    private NestedListing parentListing;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @State
    boolean showClearChildrenConfirmationSnackbar;
    @State
    boolean showEntireHomeWarningSnackbar;
    @BindView
    AirToolbar toolbar;
    final RequestListener<UpdateNestedListingsResponse> updateNestedListingListener = new C0699RL().onResponse(NestedListingsChooseChildrenFragment$$Lambda$1.lambdaFactory$(this)).onError(NestedListingsChooseChildrenFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$1(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(nestedListingsChooseChildrenFragment.getView(), e);
        nestedListingsChooseChildrenFragment.saveButton.setState(AirButton.State.Normal);
        nestedListingsChooseChildrenFragment.adapter.setRowsEnabled(true);
    }

    static /* synthetic */ void lambda$new$2(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment, NestedListingsResponse response) {
        nestedListingsChooseChildrenFragment.saveButton.setState(AirButton.State.Success);
        nestedListingsChooseChildrenFragment.controller.setNestedListingsById(response.getNestedListingsById());
        nestedListingsChooseChildrenFragment.controller.getActionExecutor().popToFragment(NestedListingsOverviewFragment.class);
    }

    static /* synthetic */ void lambda$new$3(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(nestedListingsChooseChildrenFragment.getView(), e);
        nestedListingsChooseChildrenFragment.saveButton.setState(AirButton.State.Normal);
        nestedListingsChooseChildrenFragment.adapter.setRowsEnabled(true);
    }

    /* access modifiers changed from: private */
    public void makeNestedListingRefreshRequest() {
        this.saveButton.setState(AirButton.State.Loading);
        this.adapter.setRowsEnabled(false);
        NestedListingsRequest.forCurrentUser().withListener((Observer) this.nestedListingRefreshListener).execute(this.requestManager);
    }

    public static NestedListingsChooseChildrenFragment create(NestedListing parentListing2, boolean fromOverview2) {
        return (NestedListingsChooseChildrenFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new NestedListingsChooseChildrenFragment()).putParcelable(PARENT_LISTING, parentListing2)).putBoolean(FROM_OVERVIEW, fromOverview2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.parentListing = (NestedListing) getArguments().getParcelable(PARENT_LISTING);
        this.fromOverview = getArguments().getBoolean(FROM_OVERVIEW);
        this.adapter = new NestedListingsChooseChildrenAdapter(getContext(), this.parentListing, NestedListing.getChildrenCandidates(this.parentListing.getId(), this.controller.getNestedListingsById().values()), this.actionListener, this.fromOverview, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7496R.layout.fragment_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.fromOverview) {
            this.toolbar.setNavigationIcon(2);
        }
        this.recyclerView.setAdapter(this.adapter);
        this.entireHomeWarningSnackbar = new SnackbarWrapper().view(view).body(getContext().getString(C7496R.string.nested_listings_parent_contains_entire_home, new Object[]{this.parentListing.getName()})).action(C7496R.string.yes, NestedListingsChooseChildrenFragment$$Lambda$5.lambdaFactory$(this));
        this.clearChildrenConfirmationSnackbar = new SnackbarWrapper().view(view).body(C7496R.string.nested_listings_confirm_clear_children).action(C7496R.string.yes, NestedListingsChooseChildrenFragment$$Lambda$6.lambdaFactory$(this));
        getAirActivity().setOnBackPressedListener(NestedListingsChooseChildrenFragment$$Lambda$7.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$4(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment, View v) {
        nestedListingsChooseChildrenFragment.entireHomeWarningSnackbar.dismiss();
        nestedListingsChooseChildrenFragment.saveButton.setEnabled(true);
    }

    static /* synthetic */ void lambda$onCreateView$5(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment, View v) {
        nestedListingsChooseChildrenFragment.clearChildrenConfirmationSnackbar.dismiss();
        nestedListingsChooseChildrenFragment.saveChanges();
    }

    public void onResume() {
        super.onResume();
        if (this.showEntireHomeWarningSnackbar) {
            this.entireHomeWarningSnackbar.buildAndShow();
        }
        if (this.showClearChildrenConfirmationSnackbar) {
            this.clearChildrenConfirmationSnackbar.buildAndShow();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        if (this.fromOverview && noChildrenSelected()) {
            this.clearChildrenConfirmationSnackbar.buildAndShow();
            this.showClearChildrenConfirmationSnackbar = true;
            this.saveButton.setEnabled(false);
        } else if (canSaveChanges()) {
            saveChanges();
        } else {
            this.saveButton.setState(AirButton.State.Success);
            this.controller.getActionExecutor().popToFragment(NestedListingsOverviewFragment.class);
        }
    }

    private void saveChanges() {
        this.saveButton.setState(AirButton.State.Loading);
        this.adapter.setRowsEnabled(false);
        UpdateNestedListingsRequest.forReplaceChildren(this.parentListing.getId(), this.adapter.getSelectedListingIds()).withListener((Observer) this.updateNestedListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.selectionChanged();
    }

    private boolean noChildrenSelected() {
        return this.adapter.getSelectedListingIds().size() == 0;
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        if (!canSaveChanges()) {
            return false;
        }
        new Builder(getContext(), C7496R.C7501style.Theme_Airbnb_Dialog_Babu).setTitle(C7496R.string.listing_unsaved_changes_dialog_title).setMessage(C7496R.string.listing_unsaved_changes_dialog_message).setPositiveButton(C7496R.string.listing_unsaved_changes_dialog_confirm_button, NestedListingsChooseChildrenFragment$$Lambda$8.lambdaFactory$(this)).setNegativeButton(C7496R.string.listing_unsaved_changes_dialog_cancel_button, (OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onUnsavedChangesDiscarded() {
        getFragmentManager().popBackStack();
    }

    public void onDestroyView() {
        getAirActivity().setOnBackPressedListener(null);
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.NestedListingsChooseChildren;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("user_id", this.mAccountManager.getCurrentUserId()).mo11638kv("listing_id", this.parentListing.getId());
    }
}
