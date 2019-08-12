package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.utils.LayoutManagerUtils;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.android.p3.HomeTourGalleryFragment */
public class HomeTourGalleryFragment extends HomeTourBaseFragment {
    private static final int GALLERY_IMAGE_WIDTH_DP = 120;
    private static final int TOOLBAR_OFFSET_DP = 48;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static HomeTourGalleryFragment newInstance() {
        return new HomeTourGalleryFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeTourGalleryEpoxyController epoxyController = new HomeTourGalleryEpoxyController(getContext(), this.controller);
        LayoutManagerUtils.setGridLayout((EpoxyController) epoxyController, (RecyclerView) this.recyclerView, getSpanCount());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setEpoxyControllerAndBuildModels(epoxyController);
        ((LinearLayoutManager) this.recyclerView.getLayoutManager()).scrollToPositionWithOffset(epoxyController.getSelectedRoomPosition(), ViewLibUtils.dpToPx(getContext(), 48.0f));
    }

    private int getSpanCount() {
        return (int) Math.floor((double) (ViewLibUtils.getScreenWidth(getContext()) / ViewLibUtils.dpToPx(getContext(), 120.0f)));
    }
}
