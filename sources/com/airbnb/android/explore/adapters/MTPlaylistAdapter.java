package com.airbnb.android.explore.adapters;

import android.app.Activity;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ExplorePlaylist;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.viewcomponents.models.EditorialMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PlaylistSpacerEpoxyModel;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.n2.R;
import java.util.List;

public class MTPlaylistAdapter extends BaseExploreAdapter {
    private EditorialMarqueeEpoxyModel_ marqueeModel = new EditorialMarqueeEpoxyModel_();
    private ExplorePlaylist playlist;
    private final long playlistId;
    private PlaylistSpacerEpoxyModel spacerModel = new PlaylistSpacerEpoxyModel();
    private final int videoPosition;

    public MTPlaylistAdapter(Activity activity, ExploreDataController dataController, ExploreNavigationController navController, ExploreJitneyLogger logger, RecycledViewPool recycledViewPool, long playlistId2, int videoPosition2) {
        super(activity, dataController, navController, logger, recycledViewPool);
        this.playlistId = playlistId2;
        this.videoPosition = videoPosition2;
    }

    public void syncWithDataController() {
        this.playlist = this.dataController.getCurrentExplorePlaylist();
        this.models.clear();
        if (this.playlist != null && this.playlist.getCampaignId() == this.playlistId) {
            this.marqueeModel.title(this.playlist.getTitle()).description(this.playlist.getDescription()).kicker(this.playlist.getSubtitle()).titleTextStyle(R.style.n2_PlaylistTitleText).descriptionTextStyle(R.style.n2_PlaylistDescriptionText).kickerStyle(R.style.n2_PlaylistKickerText);
            if (FeatureToggles.areVideosEnabledOnPlaylists()) {
                this.marqueeModel.videoUrl("https://a0.muscache.com/v/73/c3/73c3f370-34c1-4ff4-bafa-85c4d4a6d324/1ec3292f55e05744bef53264af590cb1_800k_2.mp4");
                this.marqueeModel.videoPosition(this.videoPosition);
            } else {
                this.marqueeModel.imageUrl(this.playlist.getHeaderPictureSm() != null ? this.playlist.getHeaderPictureSm().getLargeUrl() : "");
            }
            this.models.add(this.marqueeModel);
            populateWithSections(this.playlist.getSections(), false);
            this.models.add(this.spacerModel);
            notifyModelsChanged();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowHeader(ExploreSection section) {
        if (TextUtils.isEmpty(section.getTitle())) {
            return false;
        }
        if (!section.equals(this.playlist.getSections().get(0)) || !section.getTitle().equals(this.playlist.getTitle())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public List<EpoxyModel<?>> postProcessListings(ExploreSection exploreSection, ExploreSection previousSection, int listingOffset, int sectionIndex, DisplayType displayType, List<EpoxyModel<?>> section) {
        return section;
    }

    /* access modifiers changed from: protected */
    public void getPosterCardClickListener(View view, TripTemplate template) {
        this.logger.experienceClick(template.getId());
        this.navController.launchTemplate(view, template, this.dataController.getTopLevelSearchParams(), this.logger, C2443MtPdpReferrer.Playlist, this.playlist.getCampaignId());
    }
}
