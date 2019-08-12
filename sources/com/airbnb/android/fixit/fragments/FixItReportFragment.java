package com.airbnb.android.fixit.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.fixit.C6380R;
import com.airbnb.android.fixit.FixItReportController;
import com.airbnb.android.fixit.activities.FixItReportActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class FixItReportFragment extends FixItBaseFragment {
    private static final String ARG_LISTING_NAME = "listing_name";
    private FixItReportController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static FixItReportFragment create(String listingName) {
        return (FixItReportFragment) ((FragmentBundleBuilder) FragmentBundler.make(new FixItReportFragment()).putString("listing_name", listingName)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FixItReportActivity fixItReportActivity = this.activity;
        fixItReportActivity.getClass();
        this.epoxyController = new FixItReportController(FixItReportFragment$$Lambda$1.lambdaFactory$(fixItReportActivity));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6380R.layout.fragment_fix_it_report, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.toolbar.setTitle((CharSequence) getArguments().getString("listing_name"));
        return view;
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.epoxyController.setData(this.dataController.getReport(), Boolean.valueOf(this.dataController.isLoading()));
    }
}
