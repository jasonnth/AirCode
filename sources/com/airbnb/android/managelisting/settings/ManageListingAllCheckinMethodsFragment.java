package com.airbnb.android.managelisting.settings;

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
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CheckInInformationRequest;
import com.airbnb.android.core.requests.CreateCheckInInformationRequest;
import com.airbnb.android.core.requests.DeleteCheckInInformationRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ListingCheckInInformationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;
import com.airbnb.android.managelisting.settings.ManageListingAllCheckInMethodsController.Listener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.HashMap;
import java.util.List;
import p032rx.Observer;

public class ManageListingAllCheckinMethodsFragment extends ManageListingBaseFragment {
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(ManageListingAllCheckinMethodsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingAllCheckinMethodsFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    @State
    HashMap<Integer, Boolean> changedMethods;
    @State
    boolean enabled;
    ManageListingAllCheckInMethodsController epoxyController;
    CheckInJitneyLogger jitneyLogger;
    private final Listener listener = new Listener() {
        public void methodClicked(CheckInInformation method) {
            if (ManageListingAllCheckinMethodsFragment.this.enabled && method != ManageListingAllCheckinMethodsFragment.this.selectedMethod) {
                if (ManageListingAllCheckinMethodsFragment.this.selectedMethod != null) {
                    ManageListingAllCheckinMethodsFragment.this.setAmenityIdSelected(ManageListingAllCheckinMethodsFragment.this.selectedMethod.getAmenityNumber(), false);
                }
                ManageListingAllCheckinMethodsFragment.this.selectedMethod = method;
                ManageListingAllCheckinMethodsFragment.this.setAmenityIdSelected(ManageListingAllCheckinMethodsFragment.this.selectedMethod.getAmenityNumber(), true);
                ManageListingAllCheckinMethodsFragment.this.epoxyController.setData(ManageListingAllCheckinMethodsFragment.this.controller.getCheckInInformation(), ManageListingAllCheckinMethodsFragment.this.selectedMethod);
                ManageListingAllCheckinMethodsFragment.this.nextButton.setEnabled(true);
            }
        }

        public void editMethodNoteClicked(CheckInInformation method) {
            ManageListingAllCheckinMethodsFragment.this.controller.actionExecutor.editCodeForCheckinType(method);
        }
    };
    @BindView
    AirButton nextButton;
    private HashMap<Integer, Boolean> originalMethodStatus;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<ListingCheckInInformationResponse> refreshMethodsListener = new C0699RL().onResponse(ManageListingAllCheckinMethodsFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingAllCheckinMethodsFragment$$Lambda$4.lambdaFactory$(this)).build();
    @State
    CheckInInformation selectedMethod;
    @BindView
    AirToolbar toolbar;

    public static ManageListingAllCheckinMethodsFragment create() {
        return new ManageListingAllCheckinMethodsFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment, AirBatchResponse response) {
        manageListingAllCheckinMethodsFragment.changedMethods.clear();
        manageListingAllCheckinMethodsFragment.refetchCheckInInformation();
    }

    static /* synthetic */ void lambda$new$1(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment, AirRequestNetworkException error) {
        manageListingAllCheckinMethodsFragment.enabled = true;
        manageListingAllCheckinMethodsFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingAllCheckinMethodsFragment.getView(), error);
    }

    static /* synthetic */ void lambda$new$2(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment, ListingCheckInInformationResponse response) {
        manageListingAllCheckinMethodsFragment.enabled = true;
        manageListingAllCheckinMethodsFragment.nextButton.setState(AirButton.State.Success);
        manageListingAllCheckinMethodsFragment.controller.setCheckInInformation(response.checkInInformation);
        manageListingAllCheckinMethodsFragment.controller.actionExecutor.checkInGuide();
    }

    static /* synthetic */ void lambda$new$4(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment, AirRequestNetworkException error) {
        manageListingAllCheckinMethodsFragment.enabled = true;
        manageListingAllCheckinMethodsFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(manageListingAllCheckinMethodsFragment.getView(), (NetworkException) error, ManageListingAllCheckinMethodsFragment$$Lambda$8.lambdaFactory$(manageListingAllCheckinMethodsFragment));
    }

    /* access modifiers changed from: private */
    public void setAmenityIdSelected(int amenityId, boolean selected) {
        if (selected != ((Boolean) this.originalMethodStatus.get(Integer.valueOf(amenityId))).booleanValue()) {
            this.changedMethods.put(Integer.valueOf(amenityId), Boolean.valueOf(selected));
        } else {
            this.changedMethods.remove(Integer.valueOf(amenityId));
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.enabled = true;
            this.changedMethods = new HashMap<>();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_next, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.epoxyController = new ManageListingAllCheckInMethodsController(this.listener);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        refreshState();
        List<CheckInInformation> enabledMethods = getEnabledCheckInMethods();
        if (enabledMethods.size() == 1) {
            this.selectedMethod = (CheckInInformation) enabledMethods.get(0);
        } else if (enabledMethods.size() > 1) {
            showTooManyMethodsDialog();
            for (CheckInInformation method : enabledMethods) {
                setAmenityIdSelected(method.getAmenityNumber(), false);
            }
            this.nextButton.setEnabled(false);
        }
        this.epoxyController.setData(this.controller.getCheckInInformation(), this.selectedMethod);
        return view;
    }

    private List<CheckInInformation> getEnabledCheckInMethods() {
        return FluentIterable.from((Iterable<E>) this.controller.getEnabledCheckInMethods()).filter(ManageListingAllCheckinMethodsFragment$$Lambda$5.lambdaFactory$(this)).toList();
    }

    static /* synthetic */ boolean lambda$getEnabledCheckInMethods$5(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment, CheckInInformation method) {
        if (manageListingAllCheckinMethodsFragment.changedMethods.containsKey(Integer.valueOf(method.getAmenityNumber()))) {
            return ((Boolean) manageListingAllCheckinMethodsFragment.changedMethods.get(Integer.valueOf(method.getAmenityNumber()))).booleanValue();
        }
        return true;
    }

    private void showTooManyMethodsDialog() {
        new Builder(getContext(), C7368R.C7373style.Theme_Airbnb_Dialog_Babu).setTitle(C7368R.string.entry_method_too_many_selected_alert_title).setMessage(C7368R.string.entry_method_too_many_selected_alert_message).setPositiveButton(C7368R.string.okay, (OnClickListener) null).show();
    }

    public void dataUpdated() {
        refreshState();
    }

    private void refreshState() {
        this.originalMethodStatus = new HashMap<>();
        for (CheckInInformation method : this.controller.getCheckInInformation()) {
            int amenityId = method.getAmenity().getAmenityId();
            this.originalMethodStatus.put(Integer.valueOf(amenityId), method.isIsOptionAvailable());
            if (this.changedMethods.containsKey(Integer.valueOf(amenityId)) && this.changedMethods.get(Integer.valueOf(amenityId)) == method.isIsOptionAvailable()) {
                this.changedMethods.remove(Integer.valueOf(amenityId));
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return !this.changedMethods.isEmpty();
    }

    @OnClick
    public void onNextClicked() {
        if (!canSaveChanges()) {
            this.controller.actionExecutor.checkInGuide();
            return;
        }
        this.enabled = false;
        this.nextButton.setState(AirButton.State.Loading);
        new AirBatchRequest(FluentIterable.from((Iterable<E>) this.controller.checkInInformation).filter(ManageListingAllCheckinMethodsFragment$$Lambda$6.lambdaFactory$(this)).transform(ManageListingAllCheckinMethodsFragment$$Lambda$7.lambdaFactory$(this)).toList(), this.batchListener).execute(this.requestManager);
        this.jitneyLogger.logCheckinGuideSaveCheckinMethodEvent(getListingId());
    }

    static /* synthetic */ BaseRequestV2 lambda$onNextClicked$7(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment, CheckInInformation method) {
        if (((Boolean) manageListingAllCheckinMethodsFragment.changedMethods.get(Integer.valueOf(method.getAmenityNumber()))).booleanValue()) {
            manageListingAllCheckinMethodsFragment.jitneyLogger.logCheckinGuideAddCheckinMethodEvent((long) method.getAmenity().getAmenityId(), manageListingAllCheckinMethodsFragment.getListingId());
            return new CreateCheckInInformationRequest((long) method.getAmenityNumber(), "", manageListingAllCheckinMethodsFragment.controller.getListingId());
        }
        manageListingAllCheckinMethodsFragment.jitneyLogger.logCheckinGuideRemoveCheckinMethodEvent((long) method.getAmenity().getAmenityId(), manageListingAllCheckinMethodsFragment.getListingId());
        return new DeleteCheckInInformationRequest(method.getListingAmenityId());
    }

    /* access modifiers changed from: private */
    public void refetchCheckInInformation() {
        CheckInInformationRequest.forAllMethods(this.controller.getListingId()).withListener((Observer) this.refreshMethodsListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingAllCheckInMethods;
    }
}
