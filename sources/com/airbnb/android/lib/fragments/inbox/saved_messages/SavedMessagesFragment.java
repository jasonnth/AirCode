package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.TemplateMessage;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.DeleteCannedMessageRequest;
import com.airbnb.android.core.requests.GetCannedMessagesRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CannedMessageResponse;
import com.airbnb.android.core.responses.DeleteCannedMessageResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.RecyclerSectionedAdapter;
import com.airbnb.android.lib.utils.ThemeUtils;
import com.airbnb.android.lib.views.LoaderRecyclerView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.base.Strings;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class SavedMessagesFragment extends AirFragment implements OnBackListener {
    private static final String LISTING_ID_FIELD = "listing_id";
    private static final int REQUEST_CODE_DELETE_SAVED_MESSAGES = 145;
    private static final int REQUEST_CODE_SAVED_MESSAGES = 144;
    private final RecyclerSectionedAdapter baseAdapter = new RecyclerSectionedAdapter();
    public final NonResubscribableRequestListener<DeleteCannedMessageResponse> deleteSavedMessagesRequestListener = new C0699RL().onResponse(SavedMessagesFragment$$Lambda$3.lambdaFactory$(this)).onError(SavedMessagesFragment$$Lambda$4.lambdaFactory$(this)).onComplete(SavedMessagesFragment$$Lambda$5.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    View fullLoader;
    @State
    protected boolean isEditMode;
    MessagingJitneyLogger jitneyLogger;
    @State
    protected long listingId;
    private final NonResubscribableRequestListener<ListingResponse> listingListener = new NonResubscribableRequestListener<ListingResponse>() {
        public void onResponse(ListingResponse response) {
            SavedMessagesFragment.this.messages.addAll(SavedMessagesFragment.this.convertListingInstructionsToTemplateMessages(response.listing));
        }

        public void onErrorResponse(AirRequestNetworkException e) {
        }
    };
    @BindView
    LoaderRecyclerView loaderRecyclerView;
    private SavedMessagesTitleMarqueeAdapter marqueeAdapter;
    private final SavedMessageSelectedListener messageSelectedListener = new SavedMessageSelectedListener() {
        public void onMessageSelected(String message) {
            SavedMessagesFragment.this.jitneyLogger.savedMessageUsed();
            Intent data = new Intent();
            data.putExtra(SavedMessageSelectedListener.CHOSEN_SAVED_MESSAGE, message);
            SavedMessagesFragment.this.getActivity().setResult(-1, data);
            SavedMessagesFragment.this.getActivity().finish();
        }

        public void onMessageEdit(TemplateMessage message) {
            SavedMessagesFragment.this.startActivityForResult(CreateNewSavedMessageActivity.newIntent(SavedMessagesFragment.this.getContext(), message, SavedMessagesFragment.this.threadId), 144);
        }

        public boolean onLongClick(long messageId) {
            DeleteSavedMessageDialog.newInstance(messageId, 145, SavedMessagesFragment.this).show(SavedMessagesFragment.this.getFragmentManager(), (String) null);
            return true;
        }
    };
    @State
    protected ArrayList<TemplateMessage> messages = new ArrayList<>();
    @State
    protected boolean refreshOnResume;
    private final NonResubscribableRequestListener<AirBatchResponse> savedMessageBatchRequestListener = new NonResubscribableRequestListener<AirBatchResponse>() {
        public void onResponse(AirBatchResponse data) {
            SavedMessagesFragment.this.savedMessagesAdapter.setSavedMessages(SavedMessagesFragment.this.messages);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            NetworkUtil.tryShowErrorWithSnackbar(SavedMessagesFragment.this.getView(), e);
        }

        public void onRequestCompleted(boolean successful) {
            super.onRequestCompleted(successful);
            SavedMessagesFragment.this.loaderRecyclerView.finishLoader();
            SavedMessagesFragment.this.swipeRefreshLayout.setRefreshing(false);
        }
    };
    /* access modifiers changed from: private */
    public SavedMessagesAdapter savedMessagesAdapter;
    private SavedMessagesLinkRowAdapter savedMessagesLinkRowAdapter;
    private final NonResubscribableRequestListener<CannedMessageResponse> savedMessagesRequestListener = new C0699RL().onResponse(SavedMessagesFragment$$Lambda$1.lambdaFactory$(this)).onError(SavedMessagesFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    @State
    protected long threadId;
    @BindView
    AirToolbar toolbar;

    public static SavedMessagesFragment newInstance(long listingId2, long threadId2) {
        return (SavedMessagesFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SavedMessagesFragment()).putLong("listing_id", listingId2)).putLong(SavedMessageConstants.SAVED_MESSAGE_ID, threadId2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        ((ModalActivity) getActivity()).setOnBackPressedListener(this);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        this.listingId = getArguments().getLong("listing_id", -1);
        this.threadId = getArguments().getLong("thread_id", -1);
        this.marqueeAdapter = new SavedMessagesTitleMarqueeAdapter();
        this.savedMessagesAdapter = new SavedMessagesAdapter(this.messageSelectedListener, savedInstanceState);
        this.savedMessagesLinkRowAdapter = new SavedMessagesLinkRowAdapter(SavedMessagesFragment$$Lambda$6.lambdaFactory$(this));
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ThemeUtils.inflaterForPhonePadding(inflater).inflate(C0880R.layout.fragment_saved_messages, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        RecyclerView recyclerView = this.loaderRecyclerView.getRecyclerView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        this.swipeRefreshLayout.setScrollableChild(recyclerView);
        this.swipeRefreshLayout.setOnRefreshListener(SavedMessagesFragment$$Lambda$7.lambdaFactory$(this));
        this.baseAdapter.addAdapter(this.marqueeAdapter);
        this.baseAdapter.addAdapter(this.savedMessagesLinkRowAdapter);
        this.baseAdapter.addAdapter(this.savedMessagesAdapter);
        recyclerView.setAdapter(this.baseAdapter);
        if (savedInstanceState == null || this.requestManager.hasRequests(this.savedMessageBatchRequestListener)) {
            loadSavedMessages();
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$6(SavedMessagesFragment savedMessagesFragment) {
        savedMessagesFragment.swipeRefreshLayout.setRefreshing(true);
        savedMessagesFragment.loadSavedMessages();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.savedMessagesAdapter != null) {
            this.savedMessagesAdapter.onSaveInstanceState(outState);
        }
        if (this.marqueeAdapter != null) {
            this.marqueeAdapter.onSaveInstanceState(outState);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C0880R.C0883menu.edit_saved_messages, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        this.isEditMode = !this.isEditMode;
        this.savedMessagesAdapter.displaySavedMessages(this.isEditMode);
        item.setTitle(this.isEditMode ? C0880R.string.finish_editing : C0880R.string.edit);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 144:
                    this.refreshOnResume = true;
                    break;
                case 145:
                    this.fullLoader.setVisibility(0);
                    new DeleteCannedMessageRequest(data.getLongExtra(SavedMessageConstants.SAVED_MESSAGE_ID, -1)).withListener((Observer) this.deleteSavedMessagesRequestListener).execute(this.requestManager);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onResume() {
        super.onResume();
        getActivity().supportInvalidateOptionsMenu();
        if (this.refreshOnResume) {
            this.refreshOnResume = false;
            loadSavedMessages();
        }
    }

    public boolean onBackPressed() {
        if (!this.isEditMode) {
            return false;
        }
        this.isEditMode = false;
        this.savedMessagesAdapter.displaySavedMessages(this.isEditMode);
        getActivity().supportInvalidateOptionsMenu();
        return true;
    }

    private void loadSavedMessages() {
        this.messages.clear();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(new GetCannedMessagesRequest(this.threadId).withListener((Observer) this.savedMessagesRequestListener));
        requests.add(ListingRequest.forTemplateMessage(this.listingId).withListener((Observer) this.listingListener));
        new AirBatchRequest(requests, this.savedMessageBatchRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public List<TemplateMessage> convertListingInstructionsToTemplateMessages(Listing listing) {
        List<TemplateMessage> list = new ArrayList<>();
        if (listing != null) {
            String houseManual = listing.getHouseManual();
            if (!Strings.isNullOrEmpty(houseManual)) {
                list.add(createTemplateMessage(getResources().getString(C0880R.string.ml_field_house_manual), houseManual));
            }
            String wifi = listing.getLocalizedWirelessInfoDescription();
            if (!Strings.isNullOrEmpty(wifi)) {
                list.add(createTemplateMessage(getResources().getString(C0880R.string.wifi), wifi));
            }
            String directions = listing.getDirections();
            if (!Strings.isNullOrEmpty(directions)) {
                list.add(createTemplateMessage(getResources().getString(C0880R.string.ml_directions_title), directions));
            }
            String transit = listing.getTransit();
            if (!Strings.isNullOrEmpty(transit)) {
                list.add(createTemplateMessage(getResources().getString(C0880R.string.ml_field_getting_around), transit));
            }
            String access = listing.getAccess();
            if (!Strings.isNullOrEmpty(access)) {
                list.add(createTemplateMessage(getResources().getString(C0880R.string.ml_field_guest_access), access));
            }
            String interaction = listing.getInteraction();
            if (!Strings.isNullOrEmpty(interaction)) {
                list.add(createTemplateMessage(getResources().getString(C0880R.string.ml_field_guest_interaction), interaction));
            }
            String neighborhood = listing.getNeighborhood();
            if (!Strings.isNullOrEmpty(neighborhood)) {
                list.add(createTemplateMessage(getResources().getString(C0880R.string.ml_field_neighborhood_overview), neighborhood));
            }
        }
        return list;
    }

    private TemplateMessage createTemplateMessage(String title, String body) {
        TemplateMessage message = new TemplateMessage();
        message.setIsEditable(false);
        message.setTitle(title);
        message.setMessage(body);
        return message;
    }

    /* access modifiers changed from: private */
    public void deleteMessage(DeleteCannedMessageResponse data) {
        this.savedMessagesAdapter.deleteSavedMessage(data.templateMessage.getId());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.SavedMessages;
    }
}
