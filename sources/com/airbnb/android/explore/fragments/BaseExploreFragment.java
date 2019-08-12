package com.airbnb.android.explore.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.view.animation.Animation;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.ExploreMParticleLogger;
import com.airbnb.android.explore.controllers.ExploreControllerInterface;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreDataController.ExploreDataChangedListener;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreMode;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreNavigationListener;
import com.airbnb.android.explore.views.ExploreScrollController;
import com.airbnb.android.utils.FragmentUtils;

public class BaseExploreFragment extends AirFragment implements ExploreDataChangedListener, ExploreNavigationListener {
    protected static final String ARG_ANIMATE_RECT = "animate_rect";
    private BottomBarController bottomBarController;
    protected ExploreDataController dataController;
    protected ExploreJitneyLogger exploreJitneyLogger;
    protected ExploreMParticleLogger exploreMParticleLogger;
    protected ExploreNavigationController exploreNavigationController;
    protected RecycledViewPool recycledViewPool;
    protected ExploreScrollController scrollController;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Check.state(getParentFragment() instanceof ExploreControllerInterface, "The parent of this Fragment is supposed to implement ExploreControllerInterface");
        if (supportsBottomBar()) {
            this.bottomBarController = CoreApplication.instance().component().bottomBarController();
        }
        this.exploreNavigationController = ((ExploreControllerInterface) getParentFragment()).getNavigationController();
        this.dataController = ((ExploreControllerInterface) getParentFragment()).getDataController();
        this.scrollController = ((ExploreControllerInterface) getParentFragment()).getScrollController();
        this.exploreJitneyLogger = ((ExploreControllerInterface) getParentFragment()).getLogger();
        this.recycledViewPool = ((ExploreControllerInterface) getParentFragment()).getViewPool();
        this.exploreMParticleLogger = new ExploreMParticleLogger(this.dataController);
    }

    public void onStart() {
        super.onStart();
        this.dataController.addDataChangedListener(this);
        this.exploreNavigationController.addNavigationListener(this);
        if (supportsBottomBar()) {
            this.bottomBarController.setShowBottomBar(shouldShowBottomBar(), true);
        }
    }

    public void onHiddenChanged(boolean hidden) {
        if (!hidden && supportsBottomBar()) {
            this.bottomBarController.setShowBottomBar(shouldShowBottomBar(), true);
        }
    }

    public void onStop() {
        super.onStop();
        this.dataController.removeDataChangedListener(this);
        this.exploreNavigationController.removeNavigationListener(this);
    }

    public void onSearchParamsUpdated() {
    }

    public void onTabsLoaded(String currentTabId, boolean fromNetwork) {
    }

    public void onTabsLoadError(NetworkException e) {
    }

    public void onTabMetadataUpdated(String tabId) {
    }

    public void onExplorePlaylistLoaded() {
    }

    public void onTabContentUpdated(String tabId, boolean fromNetwork, NetworkException exception) {
    }

    public void onExploreNavStateUpdated(ExploreMode mode, String activeTabId) {
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Rect animateRect = getArguments() == null ? null : (Rect) getArguments().getParcelable(ARG_ANIMATE_RECT);
        if (animateRect != null) {
            return FragmentUtils.animateSheetExpansion(this, enter, animateRect);
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public boolean supportsBottomBar() {
        return true;
    }

    public boolean shouldShowBottomBar() {
        return false;
    }
}
