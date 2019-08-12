package com.airbnb.android.lib.tripassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.FragmentLauncher;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.core.models.HelpThreadDisplayElement;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.models.HelpThreadOnClickAction;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.responses.ReservationsResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.tripassistant.AttachmentsProvider.OnAttachmentsUpdatedListener;
import com.airbnb.android.lib.tripassistant.HelpThreadAdapter.HelpThreadAdapterListener;
import com.airbnb.android.lib.tripassistant.amenities.HTAmenityPickerActivity;
import com.airbnb.android.lib.tripassistant.amenities.HTPhotoPickerActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import p030in.uncod.android.bypass.Bypass;
import p032rx.Observer;

public class HelpThreadFragment extends AirFragment implements FragmentLauncher, OnAttachmentsUpdatedListener, HelpThreadAdapterListener {
    private static final String EXTRA_ID = "extra_id";
    private static final String EXTRA_RESERVATION_CODE = "extra_reservation_code";
    private static final int REQUEST_CODE_AMENITY_PICKER = 8390;
    private static final int REQUEST_CODE_SELECT_PHOTOS = 8391;
    private static final int TRIP_ASSISTANT_SUPPORTED_MAJOR_VERSION = 4;
    /* access modifiers changed from: private */
    public AttachmentsProvider attachmentsProvider;
    Bypass bypass;
    final RequestListener<ReservationsResponse> eligibleTripAssistantReservationRequestListener = new RequestListener<ReservationsResponse>() {
        public void onResponse(ReservationsResponse data) {
            String confirmationCode = null;
            long helpThreadId = 0;
            if (data.reservations.size() == 1 && Trebuchet.launch(TrebuchetKeys.ENABLE_TRIP_ASSISTANT) && Trebuchet.launch(TrebuchetKeys.SHOW_TRIP_ASSISTANT_IN_HELP_LINK)) {
                Reservation reservation = (Reservation) data.reservations.get(0);
                helpThreadId = reservation.getHelpThreadId();
                if (helpThreadId > 0 || FeatureToggles.showTripAssistantInHelpLink()) {
                    confirmationCode = reservation.getConfirmationCode();
                }
            }
            if (TextUtils.isEmpty(confirmationCode)) {
                HelpThreadFragment.this.startActivity(HelpCenterIntents.intentForHelpCenter(HelpThreadFragment.this.getContext()));
                HelpThreadFragment.this.getActivity().finish();
                return;
            }
            HelpThreadFragment.this.loadHelpThread(helpThreadId, confirmationCode);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            NetworkUtil.tryShowErrorWithSnackbar(HelpThreadFragment.this.recyclerView, e);
        }

        public void onRequestCompleted(boolean successful) {
            HelpThreadFragment.this.loadingView.setVisibility(8);
        }
    };
    private HelpThreadAdapter helpThreadAdapter;
    @BindView
    LoadingView loadingView;
    @BindView
    RecyclerView recyclerView;
    @State
    NodeSelection savedSelection;
    @State
    HelpThread thread;
    final RequestListener<HelpThreadResponse> threadRequestListener = new RequestListener<HelpThreadResponse>() {
        public void onResponse(HelpThreadResponse data) {
            HelpThreadFragment.this.thread = data.helpThread;
            if (HelpThreadFragment.this.thread.getHighestMajorVersion() > 4) {
                HelpThreadFragment.this.getContext().startActivity(WebViewIntentBuilder.newBuilder(HelpThreadFragment.this.getContext(), HelpThreadFragment.this.getContext().getString(C0880R.string.trip_assistant_url, new Object[]{Long.valueOf(HelpThreadFragment.this.thread.getId())})).title(C0880R.string.airbnb_help).authenticate().toIntent());
                HelpThreadFragment.this.getActivity().finish();
                return;
            }
            HelpThreadFragment.this.attachmentsProvider.setThread(HelpThreadFragment.this.thread);
            if (data.metadata.isCached()) {
                HelpThreadFragment.this.updateAdapterThread();
                HelpThreadFragment.this.scrollToBottom();
            }
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            AirRequest request = e.request();
            if (request instanceof UpdateHelpThreadRequest) {
                HelpThreadFragment.this.getSelectedNode(((UpdateHelpThreadRequest) request).getSelection()).setSelectedOption(null);
            }
            NetworkUtil.tryShowErrorWithSnackbar(HelpThreadFragment.this.recyclerView, e);
        }

        public void onRequestCompleted(boolean successful) {
            HelpThreadFragment.this.getActivity().supportInvalidateOptionsMenu();
            HelpThreadFragment.this.updateAdapterThread();
            HelpThreadFragment.this.scrollToBottom();
        }
    };
    @BindView
    AirToolbar toolbar;

    public static Fragment forReservationCode(String code) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new HelpThreadFragment()).putString(EXTRA_RESERVATION_CODE, code)).build();
    }

    public static HelpThreadFragment forId(long id) {
        return (HelpThreadFragment) ((FragmentBundleBuilder) FragmentBundler.make(new HelpThreadFragment()).putLong(EXTRA_ID, id)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_help_thread, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.attachmentsProvider = new AttachmentsProvider(this.thread, this.requestManager, savedInstanceState);
        this.recyclerView.setHasFixedSize(true);
        ((LinearLayoutManager) this.recyclerView.getLayoutManager()).setStackFromEnd(true);
        this.helpThreadAdapter = new HelpThreadAdapter(getContext(), this.bypass, this, this.attachmentsProvider, this.mAccountManager.getCurrentUser());
        this.recyclerView.setAdapter(this.helpThreadAdapter);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            loadHelpThread(getArguments().getLong(EXTRA_ID), getArguments().getString(EXTRA_RESERVATION_CODE));
        }
        updateAdapterThread();
        this.attachmentsProvider.registerListener(this);
    }

    /* access modifiers changed from: private */
    public void loadHelpThread(long id, String confirmationCode) {
        if (id != 0) {
            this.loadingView.setVisibility(8);
            new HelpThreadRequest(id).withListener((Observer) this.threadRequestListener).doubleResponse().execute(this.requestManager);
        } else if (!TextUtils.isEmpty(confirmationCode)) {
            this.loadingView.setVisibility(8);
            new GetOrCreateHelpThreadForReservationRequest(confirmationCode, 4).withListener((Observer) this.threadRequestListener).execute(this.requestManager);
        } else {
            this.loadingView.setVisibility(0);
            new GetTripAssistantEligibleReservationsRequest().withListener((Observer) this.eligibleTripAssistantReservationRequestListener).execute(this.requestManager);
        }
    }

    public void onDestroyView() {
        this.attachmentsProvider.unregiserListener(this);
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.attachmentsProvider.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: private */
    public void updateAdapterThread() {
        this.helpThreadAdapter.updateThread(this.thread, isLoading());
    }

    private boolean isLoading() {
        return this.requestManager.hasRequest((BaseRequestListener<T>) this.threadRequestListener, UpdateHelpThreadRequest.class) || this.requestManager.hasRequest((BaseRequestListener<T>) this.threadRequestListener, HelpThreadRequest.class) || this.requestManager.hasRequest((BaseRequestListener<T>) this.threadRequestListener, GetOrCreateHelpThreadForReservationRequest.class);
    }

    /* access modifiers changed from: private */
    public HelpThreadNode getSelectedNode(NodeSelection selection) {
        for (HelpThreadIssue issue : this.thread.getIssues()) {
            Iterator it = issue.getNodes().iterator();
            while (true) {
                if (it.hasNext()) {
                    HelpThreadNode helpThreadNode = (HelpThreadNode) it.next();
                    if (helpThreadNode.equals(selection.node())) {
                        return helpThreadNode;
                    }
                }
            }
        }
        throw new IllegalStateException("Could not find matching node for selection: " + selection);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem refreshMenu = menu.findItem(C0880R.C0882id.refresh);
        if (refreshMenu != null) {
            refreshMenu.setVisible(this.thread != null);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.refresh) {
            return super.onOptionsItemSelected(item);
        }
        refreshThreadIfNotAlreadyLoading();
        return true;
    }

    private void refreshThreadIfNotAlreadyLoading() {
        Check.notNull(this.thread, "Must have a thread before you can refresh");
        if (!isLoading()) {
            new HelpThreadRequest(this.thread.getId()).withListener((Observer) this.threadRequestListener).skipCache().execute(this.requestManager);
            updateAdapterThread();
            scrollToBottom();
        }
    }

    public void onOptionSelected(NodeSelection nodeSelection) {
        HelpThreadOnClickAction action = nodeSelection.option().getOnClick();
        if (action != null) {
            handleClickAction(nodeSelection, action);
            return;
        }
        UpdateHelpThreadRequest.forOptionSelected(this.thread, nodeSelection, 4).withListener((Observer) this.threadRequestListener).execute(this.requestManager);
        updateNodeSelectionLocally(nodeSelection);
    }

    private void updateNodeSelectionLocally(NodeSelection selection) {
        getSelectedNode(selection).setSelectedOption(selection.option().getValue());
        updateAdapterThread();
        scrollToBottom();
    }

    private void handleClickAction(NodeSelection selection, HelpThreadOnClickAction action) {
        switch (action.getType()) {
            case ShowTypeAmenitiesPicker:
                this.savedSelection = selection;
                startActivityForResult(HTAmenityPickerActivity.newInstance(getContext(), action.getAmenities()), REQUEST_CODE_AMENITY_PICKER);
                return;
            case ShowTypeAttachmentsUploader:
                this.savedSelection = selection;
                HelpThreadIssue issue = selection.issue();
                startActivityForResult(HTPhotoPickerActivity.newInstance(getContext(), issue, new ArrayList<>(this.attachmentsProvider.getAttachmentsForIssue(issue))), REQUEST_CODE_SELECT_PHOTOS);
                return;
            default:
                return;
        }
    }

    public void onDialogLinkElementClicked(HelpThreadDisplayElement element) {
        startActivity(HelpThreadDialogActivity.intent(getContext(), element.getDialogContent()));
    }

    /* access modifiers changed from: private */
    public void scrollToBottom() {
        this.recyclerView.scrollToPosition(this.helpThreadAdapter.getItemCount() - 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_AMENITY_PICKER /*8390*/:
                handleAmenityPickerResult(resultCode, data);
                return;
            case REQUEST_CODE_SELECT_PHOTOS /*8391*/:
                handlePhotoSelectionResult(resultCode, data);
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void handleAmenityPickerResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            UpdateHelpThreadRequest.forAmenitiesSelected(this.thread, this.savedSelection, 4, data.getParcelableArrayListExtra(HTAmenityPickerActivity.EXTRA_SELECTED_AMENITIES)).withListener((Observer) this.threadRequestListener).execute(this.requestManager);
            updateNodeSelectionLocally(this.savedSelection);
        }
        this.savedSelection = null;
    }

    private void handlePhotoSelectionResult(int resultCode, Intent data) {
        HelpThreadIssue issue = (HelpThreadIssue) data.getParcelableExtra(HTPhotoPickerActivity.EXTRA_ISSUE);
        this.attachmentsProvider.setAttachmentsForIssue(issue, data.getParcelableArrayListExtra(HTPhotoPickerActivity.EXTRA_SELECTED_PHOTOS));
        if (resultCode == -1) {
            UpdateHelpThreadRequest.forOptionSelected(this.thread, this.savedSelection, 4).withListener((Observer) this.threadRequestListener).execute(this.requestManager);
            updateNodeSelectionLocally(this.savedSelection);
        }
        this.savedSelection = null;
    }

    public Bundle getDummyArguments() {
        return forReservationCode("WMBK9C").getArguments();
    }

    public void onAttachmentsUpdated() {
        this.helpThreadAdapter.updateAttachments();
    }
}
