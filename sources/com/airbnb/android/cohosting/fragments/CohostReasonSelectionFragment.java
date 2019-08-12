package com.airbnb.android.cohosting.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.CohostReasonSelectionEpoxyController;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingReusableFlowJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.ArrayList;

public class CohostReasonSelectionFragment extends AirFragment implements OnBackListener {
    private static final String ARG_LISTING_ID = "listing_id";
    private static final String ARG_LISTING_MANAGER = "listing_manager";
    private static final String ARG_SELECTION_TYPES = "selection_type";
    private CohostReasonSelectionEpoxyController epoxyController;
    private Listener listener;
    CohostingReusableFlowJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    private ArrayList<CohostReasonSelectionType> selectionTypes;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        void onCohostReasonSelection(CohostReasonSelectionType cohostReasonSelectionType);
    }

    public static CohostReasonSelectionFragment newInstance(ListingManager manager, ArrayList<CohostReasonSelectionType> selectionTypes2, long listingId) {
        return (CohostReasonSelectionFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostReasonSelectionFragment()).putParcelable("listing_manager", manager)).putSerializable(ARG_SELECTION_TYPES, selectionTypes2)).putLong("listing_id", listingId)).build();
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohost_reason_selection, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        ListingManager manager = (ListingManager) getArguments().getParcelable("listing_manager");
        long listingId = getArguments().getLong("listing_id");
        this.selectionTypes = (ArrayList) getArguments().getSerializable(ARG_SELECTION_TYPES);
        setToolbar(this.toolbar);
        this.epoxyController = new CohostReasonSelectionEpoxyController(getContext(), manager.getUser().getName(), this.selectionTypes, this.listener);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.logger.logReusableReasonCaptureImpression(manager.getUser().getId(), listingId, ((CohostReasonSelectionType) this.selectionTypes.get(0)).getSourceType(), ((CohostReasonSelectionType) this.selectionTypes.get(0)).getAction());
        return view;
    }

    public boolean onBackPressed() {
        return false;
    }
}
