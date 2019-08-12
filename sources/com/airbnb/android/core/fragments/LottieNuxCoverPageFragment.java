package com.airbnb.android.core.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.content.res.ResourcesCompat;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.adapters.LottieNuxCoverPageController;
import com.airbnb.android.core.enums.LottieNuxViewPagerArguments;
import com.airbnb.p027n2.components.AirToolbar;

public class LottieNuxCoverPageFragment extends AirFragment {
    public static final String EXTRA_NUX_ARGUMENTS = "extra_nux_arguments";
    private LottieNuxCoverPageController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    CoordinatorLayout rootView;
    @BindView
    AirToolbar toolbar;

    public static Intent intentForNuxArguments(Context context, LottieNuxViewPagerArguments nuxArguments) {
        return ((Builder) AutoFragmentActivity.create(context, LottieNuxCoverPageFragment.class).putParcelable("extra_nux_arguments", nuxArguments)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_recycler_view_with_toolbar_light_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.rootView.setBackgroundColor(ResourcesCompat.getColor(getResources(), C0716R.color.n2_babu, null));
        this.toolbar.setNavigationOnClickListener(LottieNuxCoverPageFragment$$Lambda$1.lambdaFactory$(this));
        this.epoxyController = new LottieNuxCoverPageController(getContext(), (LottieNuxViewPagerArguments) getArguments().getParcelable("extra_nux_arguments"));
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        return view;
    }
}
