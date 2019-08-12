package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.adapters.PriceTipsDisclaimerEpoxyController;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class PriceTipsDisclaimerFragment extends AirFragment {
    private OnBackListener backListener;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C6418R.layout.recyclerview_with_toolbar, container, false);
        bindViews(v);
        setUpToolbar();
        setUpEpoxyController();
        return v;
    }

    private void setUpEpoxyController() {
        AirEpoxyController epoxyController = new PriceTipsDisclaimerEpoxyController();
        this.recyclerView.setAdapter(epoxyController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        epoxyController.requestModelBuild();
    }

    private void setUpToolbar() {
        setToolbar(this.toolbar);
        this.toolbar.setTheme(3);
        this.toolbar.setNavigationIcon(2);
        if (this.backListener != null) {
            this.toolbar.setNavigationOnClickListener(PriceTipsDisclaimerFragment$$Lambda$1.lambdaFactory$(this));
        }
    }

    public void setOnBackListener(OnBackListener backListener2) {
        this.backListener = backListener2;
    }
}
