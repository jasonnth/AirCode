package com.airbnb.android.cohosting.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingReusableFlowJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.CohostingIntents.CohostReasonType;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.CohostReasonsRequest;
import com.airbnb.android.core.responses.CohostReasonsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class CohostReasonMessageTextInputFragment extends AirFragment {
    private static final String ARG_LISTING_ID = "listing_id";
    private static final String ARG_LISTING_MANAGER = "listing_manager";
    private static final String ARG_PRIVATE_FEEDBACK = "private_feedback";
    private static final String ARG_SELECTION_TYPE = "selection_type";
    @BindView
    AirButton button;
    @BindView
    AirEditTextPageView editTextPageView;
    private Listener listener;
    @State
    long listingId;
    CohostingReusableFlowJitneyLogger logger;
    @State
    ListingManager manager;
    @State
    String privateFeedback;
    final RequestListener<CohostReasonsResponse> reasonsRequestListener = new C0699RL().onResponse(CohostReasonMessageTextInputFragment$$Lambda$1.lambdaFactory$(this)).onError(CohostReasonMessageTextInputFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;
    @State
    CohostReasonSelectionType type;

    public interface Listener {
        void onSubmitReasons();
    }

    static /* synthetic */ void lambda$new$2(CohostReasonMessageTextInputFragment cohostReasonMessageTextInputFragment, AirRequestNetworkException e) {
        cohostReasonMessageTextInputFragment.button.setEnabled(true);
        cohostReasonMessageTextInputFragment.button.setState(AirButton.State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(cohostReasonMessageTextInputFragment.getView(), CohostReasonMessageTextInputFragment$$Lambda$6.lambdaFactory$(cohostReasonMessageTextInputFragment));
    }

    public static CohostReasonMessageTextInputFragment newInstanceForMessage(CohostReasonSelectionType type2, ListingManager manager2, long listingId2, String privateFeedback2) {
        return (CohostReasonMessageTextInputFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostReasonMessageTextInputFragment()).putSerializable(ARG_SELECTION_TYPE, type2)).putParcelable("listing_manager", manager2)).putLong("listing_id", listingId2)).putString("private_feedback", privateFeedback2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohost_reason_text_input, container, false);
        bindViews(view);
        this.type = (CohostReasonSelectionType) getArguments().getSerializable(ARG_SELECTION_TYPE);
        this.manager = (ListingManager) getArguments().getParcelable("listing_manager");
        this.listingId = getArguments().getLong("listing_id");
        this.privateFeedback = getArguments().getString("private_feedback");
        setupMessageUI();
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(CohostReasonMessageTextInputFragment$$Lambda$3.lambdaFactory$(this));
        setHasOptionsMenu(true);
        this.logger.logReusableMessageImpression(this.manager.getUser().getId(), this.listingId, this.type.getSourceType(), this.type.getAction());
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(C5658R.C5660id.skip).setTitle(C5658R.string.cohosting_reasons_skip_and_finish_button_text);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5658R.C5660id.skip) {
            return false;
        }
        submitReasons(null);
        return true;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    private void setupMessageUI() {
        this.editTextPageView.setTitle(C5658R.string.cohosting_message_input_title);
        this.editTextPageView.setHint((CharSequence) getString(this.type.getMessagePlaceholderStringRes(), this.manager.getUser().getFirstName()));
        this.editTextPageView.setListener(CohostReasonMessageTextInputFragment$$Lambda$4.lambdaFactory$(this));
        this.editTextPageView.setMinLength(1);
        this.button.setText(C5658R.string.cohosting_message_input_button);
        this.button.setOnClickListener(CohostReasonMessageTextInputFragment$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void submitReasons(String message) {
        long id;
        this.logger.logSendMessageClick(message, this.manager.getUser().getId(), Long.valueOf(this.listingId), this.type.getSourceType(), this.type.getAction(), Long.valueOf((long) this.type.getReasonKey()));
        this.button.setEnabled(false);
        this.button.setState(AirButton.State.Loading);
        long j = this.listingId;
        String sourceType = this.type.getSourceType();
        String action = this.type.getAction();
        if (this.type.getCohostReasonType() == CohostReasonType.RemoveSelf) {
            id = this.mAccountManager.getCurrentUser().getId();
        } else {
            id = this.manager.getUser().getId();
        }
        new CohostReasonsRequest(j, sourceType, action, id, this.type.getReasonKey(), this.privateFeedback, message).withListener((Observer) this.reasonsRequestListener).execute(this.requestManager);
    }
}
