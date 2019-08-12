package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.utils.LayoutManagerUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.adapters.MTPlaylistAdapter;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.p027n2.components.AirToolbar;

public class MTPlaylistFragment extends BaseExploreFragment {
    private static final String ARG_COLLECTION_ID = "arg_collection_id";
    private static final String ARG_REFERRER_VALUE = "arg_referrer";
    private static final String ARG_VIDEO_POSITION = "arg_video_position";
    private MTPlaylistAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static MTPlaylistFragment newInstance(long collectionId, C2443MtPdpReferrer referrer, int videoPosition) {
        return (MTPlaylistFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new MTPlaylistFragment()).putLong(ARG_COLLECTION_ID, collectionId)).putInt(ARG_REFERRER_VALUE, referrer.value)).putInt(ARG_VIDEO_POSITION, videoPosition)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_playlist, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        long playlistId = getArguments().getLong(ARG_COLLECTION_ID);
        this.adapter = new MTPlaylistAdapter(getActivity(), this.dataController, this.exploreNavigationController, this.exploreJitneyLogger, this.recycledViewPool, playlistId, getArguments().getInt(ARG_VIDEO_POSITION));
        LayoutManagerUtils.setGridLayout((AirEpoxyAdapter) this.adapter, this.recyclerView, isTabletScreen() ? 12 : 2);
        this.recyclerView.setRecycledViewPool(this.recycledViewPool);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.swapAdapter(this.adapter, true);
        this.dataController.fetchExplorePlaylist(playlistId);
        this.adapter.syncWithDataController();
        this.exploreJitneyLogger.playlistImpression(playlistId, C2443MtPdpReferrer.findByValue(getArguments().getInt(ARG_REFERRER_VALUE)));
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.adapter = null;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0857R.C0859id.menu_share) {
            return super.onOptionsItemSelected(item);
        }
        startActivity(ShareActivityIntents.newIntentForPlaylist(getContext(), this.dataController.getCurrentExplorePlaylist()));
        return true;
    }

    public void onExplorePlaylistLoaded() {
        this.adapter.syncWithDataController();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Playlists;
    }
}
