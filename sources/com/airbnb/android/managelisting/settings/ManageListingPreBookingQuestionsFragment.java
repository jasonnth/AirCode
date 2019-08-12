package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class ManageListingPreBookingQuestionsFragment extends ManageListingBaseFragment {
    @BindView
    FixedDualActionFooter footer;
    private ManageListingPreBookingQuestionsAdapter preBookingQuestionsAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.preBookingQuestionsAdapter = new ManageListingPreBookingQuestionsAdapter(this.controller);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_footer_bar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.preBookingQuestionsAdapter);
        setupFooter();
        return view;
    }

    private void setupFooter() {
        this.footer.setVisibility(0);
        this.footer.setButtonText(C7368R.string.save);
        this.footer.setButtonOnClickListener(ManageListingPreBookingQuestionsFragment$$Lambda$1.lambdaFactory$(this));
        this.footer.setSecondaryButtonText(C7368R.string.preview);
        this.footer.setSecondaryButtonOnClickListener(ManageListingPreBookingQuestionsFragment$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
