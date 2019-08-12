package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.BedDetailsAdapter;
import com.airbnb.android.listing.adapters.BedDetailsAdapter.Listener;
import com.airbnb.android.listing.adapters.BedDetailsAdapter.Mode;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;

public class LYSBedDetailsFragment extends LYSBaseFragment {
    private BedDetailsAdapter adapter;
    private final Listener listener = new Listener() {
        public void roomSelected(int roomNumber) {
            LYSBedDetailsFragment.this.controller.showRoomBedDetails(roomNumber);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new LYSBedDetailsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new BedDetailsAdapter(Mode.ListYourSpace, getContext(), this.controller.getBedDetails(), this.controller.getListing().getBedrooms(), this.listener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void dataUpdated() {
        this.adapter.updateRooms(this.controller.getBedDetails());
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        navigateInFlow(LYSStep.BedDetails);
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        navigateInFlow(LYSStep.BedDetails);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSBedDetails;
    }
}
