package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.adapters.MultiDayPriceTipsEpoxyController;
import com.airbnb.android.hostcalendar.adapters.MultiDayPriceTipsEpoxyController.OnPriceTipsDisclaimerClickedListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.ArrayList;

public class MultiDayPriceTipsFragment extends AirFragment {
    @BindView
    FixedDualActionFooter applyButton;
    private OnBackListener backListener;
    private MultiDayPriceTipsEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static MultiDayPriceTipsFragment newInstance(ArrayList<CalendarDay> days, boolean appliedPriceTips, boolean fromInsights) {
        return (MultiDayPriceTipsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new MultiDayPriceTipsFragment()).putParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS, days)).putBoolean(ArgumentConstants.ARG_APPLIED_PRICE_TIPS, appliedPriceTips)).putBoolean(ArgumentConstants.ARG_FROM_INSIGHTS, fromInsights)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        View v = inflater.inflate(C6418R.layout.fragment_multi_day_price_tips, container, false);
        bindViews(v);
        setToolbar(this.toolbar);
        if (this.backListener != null) {
            this.toolbar.setNavigationOnClickListener(MultiDayPriceTipsFragment$$Lambda$1.lambdaFactory$(this));
        }
        this.epoxyController = new MultiDayPriceTipsEpoxyController(getContext(), (OnPriceTipsDisclaimerClickedListener) getActivity());
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.epoxyController.setData(getArguments().getParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS));
        FixedDualActionFooter fixedDualActionFooter = this.applyButton;
        if (getArguments().getBoolean(ArgumentConstants.ARG_APPLIED_PRICE_TIPS, false) || getArguments().getBoolean(ArgumentConstants.ARG_FROM_INSIGHTS)) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setGoneIf(fixedDualActionFooter, z);
        this.applyButton.setButtonOnClickListener(MultiDayPriceTipsFragment$$Lambda$2.lambdaFactory$(this));
        return v;
    }

    /* access modifiers changed from: 0000 */
    public void onApplyButtonClicked(View v) {
        getActivity().setResult(-1);
        getActivity().finish();
    }

    public void setOnBackListener(OnBackListener backListener2) {
        this.backListener = backListener2;
    }
}
