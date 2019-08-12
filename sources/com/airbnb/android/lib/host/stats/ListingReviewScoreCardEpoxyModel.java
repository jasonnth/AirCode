package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.stats.HostRecentListingReviewsCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.listing.utils.CollectionsTextUtils;
import com.airbnb.android.utils.ListingRatingUtils;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public abstract class ListingReviewScoreCardEpoxyModel extends AirEpoxyModelWithHolder<Holder> {
    CarouselItemClickListener carouselClickListener;
    final int carouselIndex;
    final Listing listing;

    static class Holder extends AirViewHolder {
        @BindView
        AirImageView image;
        @BindView
        TextView label;
        @BindView
        TextView ratingText;
        @BindView
        TextView recentRatingText;
        @BindView
        View root;
        @BindView
        TextView title;

        Holder() {
        }
    }

    public class Holder_ViewBinding implements Unbinder {
        private Holder target;

        public Holder_ViewBinding(Holder target2, View source) {
            this.target = target2;
            target2.root = Utils.findRequiredView(source, C0880R.C0882id.root, "field 'root'");
            target2.image = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_image, "field 'image'", AirImageView.class);
            target2.title = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_title, "field 'title'", TextView.class);
            target2.ratingText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.rating_text, "field 'ratingText'", TextView.class);
            target2.recentRatingText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recent_rating_text, "field 'recentRatingText'", TextView.class);
            target2.label = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.label, "field 'label'", TextView.class);
        }

        public void unbind() {
            Holder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.root = null;
            target2.image = null;
            target2.title = null;
            target2.ratingText = null;
            target2.recentRatingText = null;
            target2.label = null;
        }
    }

    public ListingReviewScoreCardEpoxyModel(Listing listing2, int carouselIndex2) {
        this.listing = listing2;
        this.carouselIndex = carouselIndex2;
    }

    public void bind(Holder holder) {
        super.bind(holder);
        holder.title.setText(this.listing.getName());
        ViewLibUtils.bindOptionalTextView(holder.ratingText, this.listing.getReviewsCount() > 0 ? ListingRatingUtils.getRatingText(holder.title.getContext(), (double) this.listing.getReviewRatingOverall(), this.listing.getReviewsCount()) : "");
        holder.recentRatingText.setText(this.listing.getReviewScores().getDescription());
        holder.recentRatingText.setTextColor(ListingReviewScoresPresenter.getStateTextColor(this.listing.getReviewScores(), holder.recentRatingText.getContext()));
        holder.image.setImageUrl(this.listing.getPictureUrl());
        if (this.carouselClickListener != null) {
            holder.root.setOnClickListener(ListingReviewScoreCardEpoxyModel$$Lambda$1.lambdaFactory$(this));
        }
        ViewLibUtils.bindOptionalTextView(holder.label, CollectionsTextUtils.getCollectionsStatusLabel(this.listing.getCollectionsApplication()));
    }

    public int getDividerViewType() {
        return 0;
    }
}
