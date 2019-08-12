package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.LayoutManagerUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreComponent.Builder;
import com.airbnb.android.explore.adapters.MTSectionsAdapter;
import com.airbnb.android.lib.ExploreBindings;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.RecyclerViewUtils;
import icepick.State;

public class MTTabFragment extends BaseExploreFragment {
    protected static final String ARG_TAB_ID = "tab_id";
    private MTSectionsAdapter adapter;
    DebugSettings debugSettings;
    @State
    int dummyState;
    @BindView
    RecyclerView recyclerView;
    private String tabId;

    public static MTTabFragment newInstance(String tabName) {
        return (MTTabFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTTabFragment()).putString(ARG_TAB_ID, tabName)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ExploreBindings) CoreApplication.instance(getContext()).componentProvider()).exploreComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        bindViews(view);
        this.tabId = getArguments().getString(ARG_TAB_ID);
        RecyclerViewUtils.setTouchSlopForNestedScrolling(this.recyclerView);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.adapter = new MTSectionsAdapter(this.tabId, getActivity(), this.debugSettings, this.dataController, this.exploreNavigationController, this.exploreJitneyLogger, this.recycledViewPool);
        LayoutManagerUtils.setGridLayout((AirEpoxyAdapter) this.adapter, this.recyclerView, isTabletScreen() ? 12 : 2);
        this.recyclerView.setRecycledViewPool(this.recycledViewPool);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.swapAdapter(this.adapter, true);
    }

    public void onResume() {
        super.onResume();
        setRecyclerViewOnScrollController();
        this.exploreJitneyLogger.registerRecyclerView(this.recyclerView);
        updateContentIfVisible();
    }

    public void onPause() {
        super.onPause();
        this.exploreJitneyLogger.unregisterRecyclerView(this.recyclerView);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.adapter = null;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        setRecyclerViewOnScrollController();
        updateContentIfVisible();
    }

    /* access modifiers changed from: protected */
    public int getLayout() {
        return C0857R.layout.fragment_mt_tab;
    }

    private void setRecyclerViewOnScrollController() {
        if (getUserVisibleHint() && this.scrollController != null && this.recyclerView != null) {
            this.scrollController.setRecyclerView(this.recyclerView);
        }
    }

    public void onTabContentUpdated(String tabId2, boolean fromNetwork, NetworkException exception) {
        if (this.tabId.equals(tabId2)) {
            updateContentIfVisible();
        }
    }

    private void updateContentIfVisible() {
        if (isResumed() && getUserVisibleHint()) {
            this.adapter.syncWithDataController();
            ExploreTab tab = this.dataController.findTabForId(this.tabId);
            if (tab != null && tab.getEmptyStateMetadata() == null && !tab.hasResults() && !this.dataController.isTabSectionLoading(tab)) {
                this.dataController.fetchTab(this.tabId);
            }
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ExploreTab;
    }

    public boolean supportsBottomBar() {
        return false;
    }

    /* access modifiers changed from: protected */
    public String getLoggingTag() {
        return getClass().getSimpleName() + " (tab: " + this.tabId + ")";
    }
}
