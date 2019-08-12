package com.airbnb.android.managelisting.picker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ListingPickerInfo;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.utils.ListingProgressUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.ListingInfoRowModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ManageListingPickerEpoxyController extends AirEpoxyController {
    private static final Comparator<ListingPickerInfo> SORT_BY_LISTING_STATUS = ManageListingPickerEpoxyController$$Lambda$7.lambdaFactory$();
    private final Context context;
    StandardRowEpoxyModel_ createListingModel;
    @State
    boolean finishedLoading;
    private final Listener listener;
    EpoxyControllerLoadingModel_ loadingModel;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    @State
    ArrayList<ListingPickerInfo> sortedListings = new ArrayList<>();

    interface Listener {
        void continueNewListing(long j);

        void fixItReport(long j, String str);

        void manageListing(long j);

        void newListing();
    }

    static /* synthetic */ int lambda$static$0(ListingPickerInfo l1, ListingPickerInfo l2) {
        ListingStatus s1 = l1.getStatus();
        ListingStatus s2 = l2.getStatus();
        if (s1 == s2) {
            return l1.getName().compareToIgnoreCase(l2.getName());
        }
        if (s1 == ListingStatus.InProgress || (s1 == ListingStatus.Listed && s2 == ListingStatus.Unlisted)) {
            return -1;
        }
        return 1;
    }

    public ManageListingPickerEpoxyController(Context context2, Listener listener2, Bundle savedState) {
        this.context = context2;
        this.listener = listener2;
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    public void setListings(ArrayList<ListingPickerInfo> listings) {
        this.finishedLoading = true;
        this.sortedListings.addAll(listings);
        Collections.sort(this.sortedListings, SORT_BY_LISTING_STATUS);
        requestModelBuild();
    }

    public void clearListings() {
        this.sortedListings.clear();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marqueeModel.titleRes(C7368R.string.manage_listings_title).addTo(this);
        addListingModels();
        if (this.finishedLoading) {
            this.createListingModel.title(C7368R.string.manage_listings_create_new).rowDrawableRes(C7368R.C7369drawable.ic_action_new).clickListener(ManageListingPickerEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        } else {
            this.loadingModel.addTo(this);
        }
    }

    private void addListingModels() {
        int count = 0;
        ListingStatus currentStatus = null;
        Iterator it = this.sortedListings.iterator();
        while (it.hasNext()) {
            ListingPickerInfo listing = (ListingPickerInfo) it.next();
            if (listing.getStatus() != currentStatus) {
                currentStatus = listing.getStatus();
                addListingSectionModel(currentStatus, count);
            }
            addListingModel(listing);
            count++;
        }
    }

    private void addListingSectionModel(ListingStatus status, int index) {
        SectionHeaderEpoxyModel_ model = new SectionHeaderEpoxyModel_().m5556id((CharSequence) status.toString() + index);
        switch (status) {
            case InProgress:
                model.titleRes(C7368R.string.manage_listings_in_progress_title).addTo(this);
                return;
            case Listed:
                model.titleRes(C7368R.string.manage_listings_listed_title).addTo(this);
                return;
            case Unlisted:
                model.titleRes(C7368R.string.manage_listings_unlisted_title).addTo(this);
                return;
            default:
                return;
        }
    }

    private void addListingModel(ListingPickerInfo listing) {
        switch (listing.getStatus()) {
            case InProgress:
                createInProgressListing(listing).addTo(this);
                return;
            case Listed:
                createListedListing(listing).addTo(this);
                return;
            case Unlisted:
                createUnlistedListing(listing).addTo(this);
                return;
            default:
                return;
        }
    }

    private ListingInfoRowModel_ createInProgressListing(ListingPickerInfo listing) {
        int percentageComplete = ListingProgressUtils.getListingPercentageComplete(listing);
        return createRowBase(listing, this.context.getString(C7368R.string.manage_listings_in_progress_percentage, new Object[]{Integer.valueOf(percentageComplete)})).mo11716id(listing.getId()).onClickListener(ManageListingPickerEpoxyController$$Lambda$2.lambdaFactory$(this, listing)).progressBarVisible(true).progressBarPercentage(percentageComplete);
    }

    private ListingInfoRowModel_ createListedListing(ListingPickerInfo listing) {
        SpannableStringBuilder subtitle = new SpannableStringBuilder();
        if (listing.isInstantBookEligible()) {
            subtitle.append(generateInstantBookText(this.context, listing.isInstantBookEnabled()));
        }
        if (listing.isSmartPricingAvailable()) {
            if (subtitle.length() > 0) {
                subtitle.append("\n");
            }
            subtitle.append(generateSmartPricingText(this.context, listing.isSmartPricingEnabled()));
        }
        ListingInfoRowModel_ rowModel = createRowBase(listing, subtitle).mo11716id(listing.getId()).withHackberryLayout().onClickListener(ManageListingPickerEpoxyController$$Lambda$3.lambdaFactory$(this, listing));
        if (listing.getCollectionApplicationStatus() == 0) {
            rowModel.buttonText(C7368R.string.collections_listing_invitation_button);
            rowModel.buttonClickListener(ManageListingPickerEpoxyController$$Lambda$4.lambdaFactory$(this, listing));
        }
        if (listing.getCollectionApplicationStatus() == 5) {
            rowModel.label(C7368R.string.collections_listing_select_brand_name);
        }
        if (listing.hasPublishedFixItReport() && listing.getFixItReport().getReportType() == 0 && FeatureToggles.showFixItTool()) {
            rowModel.buttonText(C7368R.string.collections_listing_select_next_steps_button);
            rowModel.buttonClickListener(ManageListingPickerEpoxyController$$Lambda$5.lambdaFactory$(this, listing));
        }
        return rowModel;
    }

    private ListingInfoRowModel_ createUnlistedListing(ListingPickerInfo listing) {
        return createRowBase(listing, getSnoozedString(this.context, listing)).mo11716id(listing.getId()).onClickListener(ManageListingPickerEpoxyController$$Lambda$6.lambdaFactory$(this, listing));
    }

    private ListingInfoRowModel_ createRowBase(ListingPickerInfo listing, CharSequence subtitle) {
        ListingInfoRowModel_ model = new ListingInfoRowModel_().title(listing.getNameOrPlaceholder(this.context)).titleMaxLine(3).subtitleText(subtitle);
        if (TextUtils.isEmpty(listing.getThumbnailUrl())) {
            model.image(C7368R.C7369drawable.camera_icon_bg);
        } else {
            model.image(listing.getThumbnailUrl());
        }
        return model;
    }

    private SpannableStringBuilder generateInstantBookText(Context context2, boolean enabled) {
        return buildLineItemText(context2, enabled, enabled ? C7368R.string.manage_listings_instant_book_enabled : C7368R.string.manage_listings_instant_book_disabled);
    }

    private SpannableStringBuilder generateSmartPricingText(Context context2, boolean enabled) {
        return buildLineItemText(context2, enabled, enabled ? C7368R.string.manage_listings_smart_pricing_enabled : C7368R.string.manage_listings_smart_pricing_disabled);
    }

    private SpannableStringBuilder buildLineItemText(Context context2, boolean checked, int text) {
        ImageSpan imageSpan;
        if (checked) {
            imageSpan = createImageSpan(context2, C7368R.C7369drawable.icon_inline_check, C7368R.color.n2_babu);
        } else {
            imageSpan = createImageSpan(context2, C7368R.C7369drawable.icon_inline_cross, C7368R.color.n2_text_color_muted);
        }
        SpannableStringBuilder builder = new SpannableStringBuilder("  ");
        builder.setSpan(imageSpan, 0, 1, 17);
        builder.append(context2.getString(text));
        return builder;
    }

    private static String getSnoozedString(Context context2, ListingPickerInfo listing) {
        if (!listing.isSnoozed()) {
            return null;
        }
        String date = listing.getSnoozeMode().getEndDate().formatDate(context2.getString(C7368R.string.mdy_format_full));
        return context2.getString(C7368R.string.manage_listings_snoozed_description, new Object[]{date});
    }

    private static ImageSpan createImageSpan(Context context2, int drawableRes, int colorRes) {
        Drawable drawable = AppCompatResources.getDrawable(context2, drawableRes);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(context2, colorRes));
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return new ImageSpan(drawable, 1);
    }
}
