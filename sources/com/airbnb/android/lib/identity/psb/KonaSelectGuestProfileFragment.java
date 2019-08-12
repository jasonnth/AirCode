package com.airbnb.android.lib.identity.psb;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.controller.BookingController.BookingActivityFacade;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.GetSavedChinaIdentitesRequest;
import com.airbnb.android.core.requests.GetSavedPassportsRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.GetSavedChinaIdentitesResponse;
import com.airbnb.android.core.responses.GetSavedPassportsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import p032rx.Observer;

public class KonaSelectGuestProfileFragment extends AirDialogFragment implements SelectionSheetOnItemClickedListener<GuestIdentity> {
    private static final String ARG_IS_BOOKER_IDENTIFICATION = "arg_is_booker_id";
    private static final String ARG_IS_P4_REDESIGN = "arg_p4_redesign";
    private static final String ARG_PRESELECTED_IDENTIFICATIONS = "arg_preselected_ids";
    private static final int REQUEST_CODE_ADD_IDENTIFICATION = 1000;
    @BindView
    AirButton addButton;
    @BindView
    AirButton addProfileButton;
    @BindView
    AirButton addProfileButtonWhite;
    @State
    boolean isBookerIdentification;
    @State
    boolean isP4Redesign;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    View loaderFrame;
    @State
    GuestIdentity newIdentity;
    @State
    ArrayList<GuestIdentity> preselectedIdentifications;
    @BindView
    AirButton primaryButton;
    @BindView
    GuestProfileSelectionView selectionView;
    @BindView
    AirToolbar toolbar;

    public static Fragment forSelectProfiles(ArrayList<GuestIdentity> preselectedIdentifications2, boolean isBookerIdentification2, boolean isP4Redesign2) {
        return ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new KonaSelectGuestProfileFragment()).putParcelableArrayList(ARG_PRESELECTED_IDENTIFICATIONS, preselectedIdentifications2)).putBoolean(ARG_IS_BOOKER_IDENTIFICATION, isBookerIdentification2)).putBoolean(ARG_IS_P4_REDESIGN, isP4Redesign2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_select_guest_identification, container, false);
        ButterKnife.bind(this, view);
        this.selectionView.setSelectionSheetOnItemClickedListener(this);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setTheme(getAirToolbarTheme());
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.preselectedIdentifications = getArguments().getParcelableArrayList(ARG_PRESELECTED_IDENTIFICATIONS);
            this.isBookerIdentification = getArguments().getBoolean(ARG_IS_BOOKER_IDENTIFICATION);
            this.isP4Redesign = getArguments().getBoolean(ARG_IS_P4_REDESIGN);
        }
        fetchIdentities();
        setStyle(view);
        return view;
    }

    private void setStyle(View view) {
        boolean z;
        boolean z2 = true;
        GuestProfilesStyle style = GuestProfilesStyle.getStyle(this.isP4Redesign);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        this.selectionView.setStyle(style.selectionViewStyle);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        AirButton airButton = this.primaryButton;
        if (!this.isP4Redesign) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleIf((View) airButton, z);
        ViewUtils.setVisibleIf((View) this.addButton, this.isP4Redesign);
        AirButton airButton2 = this.addProfileButton;
        if (this.isP4Redesign) {
            z2 = false;
        }
        ViewUtils.setVisibleIf((View) airButton2, z2);
        ViewUtils.setVisibleIf((View) this.addProfileButtonWhite, this.isP4Redesign);
    }

    private int getAirToolbarTheme() {
        return this.isP4Redesign ? 3 : 2;
    }

    public void onResume() {
        super.onResume();
        this.primaryButton.setEnabled(this.selectionView.hasSelectedItems());
        this.addButton.setEnabled(this.selectionView.hasSelectedItems());
        if (this.newIdentity != null) {
            ((KonaManageGuestProfilesFragment) getParentFragment()).addNewGuestIdentification(this.newIdentity, this.isBookerIdentification);
        }
    }

    private void fetchIdentities() {
        startLoader();
        List<BaseRequestV2<?>> requests = new LinkedList<>();
        requests.add(new GetSavedPassportsRequest().withListener((Observer) new SimpleRequestListener<GetSavedPassportsResponse>() {
            public void onResponse(GetSavedPassportsResponse data) {
                KonaSelectGuestProfileFragment.this.addIdentificationsToSelectionView(data.passports);
            }
        }));
        requests.add(new GetSavedChinaIdentitesRequest().withListener((Observer) new SimpleRequestListener<GetSavedChinaIdentitesResponse>() {
            public void onResponse(GetSavedChinaIdentitesResponse data) {
                KonaSelectGuestProfileFragment.this.addIdentificationsToSelectionView(data.chinaIdentites);
            }
        }));
        new AirBatchRequest(requests, new NonResubscribableRequestListener<AirBatchResponse>() {
            public void onResponse(AirBatchResponse data) {
                KonaSelectGuestProfileFragment.this.stopLoader();
                boolean noIdentificationsSaved = KonaSelectGuestProfileFragment.this.selectionView.getItemCount() == 0;
                if (noIdentificationsSaved || KonaSelectGuestProfileFragment.this.selectionView.areAllIdentitiesSelected()) {
                    KonaSelectGuestProfileFragment.this.startCreateIdentificationFlow(noIdentificationsSaved);
                }
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                KonaSelectGuestProfileFragment.this.stopLoader();
                NetworkUtil.toastGenericNetworkError(KonaSelectGuestProfileFragment.this.getActivity());
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void addIdentificationsToSelectionView(List<? extends GuestIdentity> identifications) {
        if (!ListUtils.isEmpty((Collection<?>) identifications)) {
            this.selectionView.addIdentities(new ArrayList(FluentIterable.from((Iterable<E>) identifications).transform(KonaSelectGuestProfileFragment$$Lambda$1.lambdaFactory$(this)).toList()));
        }
    }

    /* access modifiers changed from: private */
    public GuestIdentity updateIdentificationSelectedState(GuestIdentity guestIdentity) {
        if (guestIdentity != null) {
            guestIdentity.setSelected(this.preselectedIdentifications.contains(guestIdentity));
        }
        return guestIdentity;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onCreateProfileClick() {
        startCreateIdentificationFlow(false);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onCreateProfileWhiteClick() {
        onCreateProfileClick();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSaveClick() {
        ((KonaManageGuestProfilesFragment) getParentFragment()).addNewGuestIdentification(this.selectionView.getSelectedItem(), this.isBookerIdentification);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onAddClick() {
        onSaveClick();
    }

    /* access modifiers changed from: private */
    public void startCreateIdentificationFlow(boolean forNoIdentificationsSaved) {
        BookingController controller;
        ReservationDetails reservationDetails = null;
        if (getActivity() instanceof BookingActivityFacade) {
            controller = ((BookingActivityFacade) getActivity()).getController();
        } else {
            controller = null;
        }
        Reservation reservation = controller == null ? null : controller.getReservation();
        if (controller != null) {
            reservationDetails = controller.getReservationDetails();
        }
        startActivityForResult(CreateIdentificationActivity.newIntent(getActivity(), forNoIdentificationsSaved, reservation, reservationDetails, this.isP4Redesign), 1000);
    }

    public void startLoader() {
        this.addButton.setState(AirButton.State.Loading);
        this.loaderFrame.setVisibility(0);
    }

    public void stopLoader() {
        this.addButton.setState(AirButton.State.Normal);
        this.loaderFrame.setVisibility(8);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                if (resultCode == -1) {
                    this.newIdentity = (GuestIdentity) data.getParcelableExtra(CreateIdentificationActivity.EXTRA_NEW_IDENTIFICATION);
                    break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onItemClicked(GuestIdentity item) {
        boolean z;
        boolean z2 = true;
        AirButton airButton = this.primaryButton;
        if (this.selectionView.getSelectedItem() != null) {
            z = true;
        } else {
            z = false;
        }
        airButton.setEnabled(z);
        AirButton airButton2 = this.addButton;
        if (this.selectionView.getSelectedItem() == null) {
            z2 = false;
        }
        airButton2.setEnabled(z2);
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(this.isP4Redesign);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ChinaGuestProfileSelection;
    }
}
