package com.airbnb.android.lib.share;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class ShareYourTripToWechatFragment_ViewBinding implements Unbinder {
    private ShareYourTripToWechatFragment target;
    private View view2131756773;
    private View view2131756776;
    private View view2131756782;
    private View view2131756790;

    public ShareYourTripToWechatFragment_ViewBinding(final ShareYourTripToWechatFragment target2, View source) {
        this.target = target2;
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_loading_overlay, "field 'loaderFrame'", LoaderFrame.class);
        target2.airToolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_toolbar, "field 'airToolbar'", AirToolbar.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.previewContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_preview_container, "field 'previewContainer'", ViewGroup.class);
        target2.userPhoto = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_user_photo, "field 'userPhoto'", HaloImageView.class);
        target2.userTitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_user_title, "field 'userTitle'", AirTextView.class);
        target2.tripTitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_title, "field 'tripTitle'", AirTextView.class);
        target2.headerDivider = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_header_divider, "field 'headerDivider'");
        target2.addPhotoSection = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_add_photo_container, "field 'addPhotoSection'");
        target2.photoMemoriesTitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_moments_title, "field 'photoMemoriesTitle'", AirTextView.class);
        target2.photoMemoriesContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_photo_memories_container, "field 'photoMemoriesContainer'", ViewGroup.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_add_more_photos, "field 'addMorePhotos' and method 'addPhotoToMemories'");
        target2.addMorePhotos = view;
        this.view2131756776 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.addPhotoToMemories();
            }
        });
        target2.photoSectionDivider = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_photo_section_divider, "field 'photoSectionDivider'");
        target2.homeTitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_home_title, "field 'homeTitle'", AirTextView.class);
        target2.listingImageView = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_listing_image, "field 'listingImageView'", AirImageView.class);
        target2.tripMessageQuote = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_message_quote, "field 'tripMessageQuote'", AirTextView.class);
        target2.tripMessage = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_message, "field 'tripMessage'", AirTextView.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_edit_message, "field 'editMessage' and method 'editMessage'");
        target2.editMessage = (AirTextView) Utils.castView(view2, C0880R.C0882id.share_your_trip_edit_message, "field 'editMessage'", AirTextView.class);
        this.view2131756782 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.editMessage();
            }
        });
        target2.messageSectionDivider = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_message_section_divider, "field 'messageSectionDivider'");
        target2.hostedByText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_hosted_by, "field 'hostedByText'", AirTextView.class);
        target2.hostReviewCount = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_host_reviews, "field 'hostReviewCount'", AirTextView.class);
        target2.hostInfoSeparator = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_host_info_separator, "field 'hostInfoSeparator'", AirImageView.class);
        target2.hostInfoText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_host_info, "field 'hostInfoText'", AirTextView.class);
        target2.hostPhoto = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_host_photo, "field 'hostPhoto'", HaloImageView.class);
        target2.superHostIcon = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_your_trip_superhost_icon, "field 'superHostIcon'", AirImageView.class);
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_submit, "field 'submitButton' and method 'shareToWechatWithPermissionCheck'");
        target2.submitButton = view3;
        this.view2131756790 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.shareToWechatWithPermissionCheck();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.share_your_trip_add_photo, "method 'addPhotoToMemories'");
        this.view2131756773 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.addPhotoToMemories();
            }
        });
    }

    public void unbind() {
        ShareYourTripToWechatFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loaderFrame = null;
        target2.airToolbar = null;
        target2.scrollView = null;
        target2.previewContainer = null;
        target2.userPhoto = null;
        target2.userTitle = null;
        target2.tripTitle = null;
        target2.headerDivider = null;
        target2.addPhotoSection = null;
        target2.photoMemoriesTitle = null;
        target2.photoMemoriesContainer = null;
        target2.addMorePhotos = null;
        target2.photoSectionDivider = null;
        target2.homeTitle = null;
        target2.listingImageView = null;
        target2.tripMessageQuote = null;
        target2.tripMessage = null;
        target2.editMessage = null;
        target2.messageSectionDivider = null;
        target2.hostedByText = null;
        target2.hostReviewCount = null;
        target2.hostInfoSeparator = null;
        target2.hostInfoText = null;
        target2.hostPhoto = null;
        target2.superHostIcon = null;
        target2.submitButton = null;
        this.view2131756776.setOnClickListener(null);
        this.view2131756776 = null;
        this.view2131756782.setOnClickListener(null);
        this.view2131756782 = null;
        this.view2131756790.setOnClickListener(null);
        this.view2131756790 = null;
        this.view2131756773.setOnClickListener(null);
        this.view2131756773 = null;
    }
}
