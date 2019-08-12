package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.adapters.RTBChecklistAdapter;
import com.airbnb.android.listyourspacedls.adapters.RTBChecklistAdapter.Controller;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSRTBChecklistFragment extends LYSBaseFragment implements Controller {
    private RTBChecklistAdapter checklistAdapter;
    @BindView
    AirButton doneButton;
    LYSJitneyLogger jitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static LYSRTBChecklistFragment newInstance() {
        return new LYSRTBChecklistFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.checklistAdapter = new RTBChecklistAdapter(this);
        this.checklistAdapter.onRestoreInstanceState(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.fragment_listing_recycler_view_with_done, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setAdapter(this.checklistAdapter);
        updateButtons();
        return view;
    }

    private void updateButtons() {
        this.doneButton.setEnabled(this.checklistAdapter.areAllChecked());
    }

    public void togglesUpdated() {
        updateButtons();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.checklistAdapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        navigateInFlow(LYSStep.HowGuestsBookStep);
    }

    @OnClick
    public void clickNext() {
        this.jitneyLogger.logConfirmAllWithIbOff(Long.valueOf(this.controller.getListing().getId()));
        this.controller.setInstantBookAllowedCategory(InstantBookingAllowedCategory.Off);
        this.controller.popFragment();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSRequestToBookCheckList;
    }
}
