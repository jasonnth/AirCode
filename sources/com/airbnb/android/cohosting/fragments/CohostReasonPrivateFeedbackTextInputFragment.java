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
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingReusableFlowJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CohostReasonPrivateFeedbackTextInputFragment extends AirFragment {
    private static final String ARG_LISTING_ID = "listing_id";
    private static final String ARG_LISTING_MANAGER = "listing_manager";
    private static final String ARG_SELECTION_TYPE = "selection_type";
    @BindView
    AirButton button;
    @BindView
    AirEditTextPageView editTextPageView;
    private Listener listener;
    CohostingReusableFlowJitneyLogger logger;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        void onSubmitPrivateFeedbackInput(String str);
    }

    public static CohostReasonPrivateFeedbackTextInputFragment newInstanceForPrivateFeedback(CohostReasonSelectionType type, ListingManager manager, long listingId) {
        return (CohostReasonPrivateFeedbackTextInputFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostReasonPrivateFeedbackTextInputFragment()).putSerializable(ARG_SELECTION_TYPE, type)).putParcelable("listing_manager", manager)).putLong("listing_id", listingId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohost_reason_text_input, container, false);
        bindViews(view);
        CohostReasonSelectionType type = (CohostReasonSelectionType) getArguments().getSerializable(ARG_SELECTION_TYPE);
        ListingManager manager = (ListingManager) getArguments().getParcelable("listing_manager");
        long listingId = getArguments().getLong("listing_id");
        setupPrivateFeedbackUI(type, manager);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(CohostReasonPrivateFeedbackTextInputFragment$$Lambda$1.lambdaFactory$(this));
        setHasOptionsMenu(true);
        this.logger.logReusablePrivateFeedbackImpression(manager.getUser().getId(), listingId, type.getSourceType(), type.getAction());
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(C5658R.C5660id.skip).setTitle(C5658R.string.cohosting_reasons_skip_button_text);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5658R.C5660id.skip) {
            return false;
        }
        this.listener.onSubmitPrivateFeedbackInput(null);
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

    private void setupPrivateFeedbackUI(CohostReasonSelectionType type, ListingManager manager) {
        this.editTextPageView.setTitle(C5658R.string.cohosting_private_feedback_input_title);
        this.editTextPageView.setCaption(C5658R.string.cohosting_reason_selection_explanation);
        this.editTextPageView.setHint((CharSequence) getString(type.getPrivateFeedbackScreenPlaceholderStringRes(), manager.getUser().getFirstName()));
        this.editTextPageView.setListener(CohostReasonPrivateFeedbackTextInputFragment$$Lambda$2.lambdaFactory$(this));
        this.editTextPageView.setMinLength(1);
        this.button.setText(C5658R.string.cohosting_private_feedback_input_button);
        this.button.setOnClickListener(CohostReasonPrivateFeedbackTextInputFragment$$Lambda$3.lambdaFactory$(this));
    }
}
