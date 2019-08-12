package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.adapters.ListingManagersPickerAdapter;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.Check;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.List;

public class ListingManagersPickerFragment extends CohostManagementBaseFragment {
    private ListingManagersPickerAdapter adapter;
    CohostingManagementJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ListingManagersPickerFragment create() {
        return new ListingManagersPickerFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_listing_managers_picker, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new ListingManagersPickerAdapter(this.controller, getContext());
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.display();
        logImpression();
        return view;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5658R.C5660id.menu_invite_friend) {
            return false;
        }
        this.controller.actionExecutor.inviteFriend();
        this.logger.logInviteButtonFromListingManagerPickerPageClicked(this.controller.getCohostingContext());
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void dataUpdated() {
        super.dataUpdated();
        setHasOptionsMenu(showInviteCohostOption());
        this.adapter.update();
    }

    private boolean showInviteCohostOption() {
        return this.controller.isCurrentUserListingAdmin() && this.controller.hasCohostsOrInvitations() && enableInviteCohostOption();
    }

    private boolean enableInviteCohostOption() {
        boolean z;
        List<ListingManager> managerList = this.controller.getListingManagers();
        if (managerList.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        Check.state(z, "Each listing shall have at least one listing manager");
        if (managerList.size() + this.controller.getNumOfPendingCohostInvitations() < ((ListingManager) managerList.get(0)).getMaxNumberOfCohostsPerListing().intValue() + 1) {
            return true;
        }
        return false;
    }

    private void logImpression() {
        C1951CohostingContext loggingContext = this.controller.getCohostingContext();
        if (this.controller.hasCohostsOrInvitations()) {
            this.logger.logListingManagersPickerPageImpression(loggingContext);
        } else {
            this.logger.logInviteAFriendIntroPageImpression(loggingContext, this.controller.getSourceFlow());
        }
    }
}
