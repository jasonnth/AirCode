package com.airbnb.android.superhero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.superhero.SuperHeroDataController.SuperHeroDataChangedListener;
import com.airbnb.android.superhero.adapters.SuperHeroThreadAdapter;
import com.airbnb.android.utils.ConcurrentUtil;
import com.airbnb.android.utils.ListUtils;
import java.util.Collection;

public class SuperHeroThreadFragment extends AirFragment implements SuperHeroDataChangedListener {
    private SuperHeroThreadAdapter adapter;
    private SuperHeroDataController dataController;
    SuperHeroJitneyLogger jitneyLogger;
    LoggingContextFactory loggingContextFactory;
    @BindView
    RecyclerView recyclerView;
    SuperHeroManager superHeroManager;
    SuperHeroTableOpenHelper superHeroTableOpenHelper;

    public static SuperHeroThreadFragment newInstance() {
        return new SuperHeroThreadFragment();
    }

    public static Intent newIntent(Context context) {
        return AutoAirActivity.intentForFragment(context, SuperHeroThreadFragment.class, null);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SuperHeroGraph) CoreApplication.instance(getActivity()).component()).inject(this);
        this.jitneyLogger = new SuperHeroJitneyLogger(this.loggingContextFactory);
        this.dataController = new SuperHeroDataController(getContext(), savedInstanceState, this.superHeroTableOpenHelper, this.superHeroManager);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.adapter = new SuperHeroThreadAdapter(getContext(), this.dataController);
        View view = inflater.inflate(C1713R.layout.fragment_super_hero_thread, container, false);
        bindViews(view);
        this.recyclerView.setAdapter(this.adapter);
        ((LinearLayoutManager) this.recyclerView.getLayoutManager()).setStackFromEnd(true);
        Check.state(FeatureToggles.isSuperHeroEnabled());
        if (!ListUtils.isEmpty(this.dataController.getSuperHeroMessages())) {
            this.adapter.setSuperHeroMessages(this.dataController.getSuperHeroMessages());
        }
        this.dataController.fetchMessagesForInbox();
        return view;
    }

    public void onStart() {
        super.onStart();
        this.dataController.setDataChangedListener(this);
    }

    public void onStop() {
        super.onStop();
        this.dataController.setDataChangedListener(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        this.dataController.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public void onMessagesUpdated(Collection<SuperHeroMessage> superHeroMessages) {
        this.adapter.setSuperHeroMessages(superHeroMessages);
        this.recyclerView.smoothScrollToPosition(this.adapter.getItemCount());
        ConcurrentUtil.deferParallel(SuperHeroThreadFragment$$Lambda$1.lambdaFactory$(this, superHeroMessages));
    }
}
