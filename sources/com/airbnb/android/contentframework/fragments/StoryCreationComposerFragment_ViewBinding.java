package com.airbnb.android.contentframework.fragments;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryCreationComposerFragment_ViewBinding implements Unbinder {
    private StoryCreationComposerFragment target;

    public StoryCreationComposerFragment_ViewBinding(StoryCreationComposerFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5709R.C5711id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C5709R.C5711id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.imagesCarousel = (Carousel) Utils.findRequiredViewAsType(source, C5709R.C5711id.images_carousel, "field 'imagesCarousel'", Carousel.class);
        target2.titleText = (AirEditTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.title_text, "field 'titleText'", AirEditTextView.class);
        target2.titleTextCounter = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.title_char_counter, "field 'titleTextCounter'", AirTextView.class);
        target2.bodyText = (AirEditTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.body_text, "field 'bodyText'", AirEditTextView.class);
        target2.listingAppendixImage = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.listing_appendix_image, "field 'listingAppendixImage'", AirImageView.class);
        target2.listingAppendixInfoButton = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.listing_appendix_info_button, "field 'listingAppendixInfoButton'", AirImageView.class);
        target2.listingAppendixTitle = (TextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.listing_appendix_title, "field 'listingAppendixTitle'", TextView.class);
        target2.listingAppendixRatingBar = (RatingBar) Utils.findRequiredViewAsType(source, C5709R.C5711id.listing_appendix_rating_bar, "field 'listingAppendixRatingBar'", RatingBar.class);
        target2.listingAppendixSubtitle = (TextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.listing_appendix_subtitle, "field 'listingAppendixSubtitle'", TextView.class);
        target2.placeTagPill = Utils.findRequiredView(source, C5709R.C5711id.place_tag_pill, "field 'placeTagPill'");
        target2.placeTagText = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.place_tag_text, "field 'placeTagText'", AirTextView.class);
        target2.placeTagRemove = Utils.findRequiredView(source, C5709R.C5711id.place_tag_remove, "field 'placeTagRemove'");
        target2.composerInfoText = (TextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.composer_info_text, "field 'composerInfoText'", TextView.class);
        target2.titleCharLimit = source.getContext().getResources().getInteger(C5709R.integer.story_creation_title_char_limit);
    }

    public void unbind() {
        StoryCreationComposerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.scrollView = null;
        target2.imagesCarousel = null;
        target2.titleText = null;
        target2.titleTextCounter = null;
        target2.bodyText = null;
        target2.listingAppendixImage = null;
        target2.listingAppendixInfoButton = null;
        target2.listingAppendixTitle = null;
        target2.listingAppendixRatingBar = null;
        target2.listingAppendixSubtitle = null;
        target2.placeTagPill = null;
        target2.placeTagText = null;
        target2.placeTagRemove = null;
        target2.composerInfoText = null;
    }
}
