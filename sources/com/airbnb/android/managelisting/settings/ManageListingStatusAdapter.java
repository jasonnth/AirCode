package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.view.View.OnClickListener;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.listing.ListedStatus;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.primitives.LoadingDrawable;

public class ManageListingStatusAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final LinkActionRowEpoxyModel_ deactivateListingRow = new LinkActionRowEpoxyModel_().textRes(C7368R.string.manage_listing_status_deactivate_listing);
    private final StandardRowEpoxyModel_ listedRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_status_setting_listed);
    private final StatusActionListener listener;
    private final LoadingDrawable loader = new LoadingDrawable();
    private final StandardRowEpoxyModel_ snoozedRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_status_setting_snoozed);
    private final StandardRowEpoxyModel_ unlistedRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_status_setting_unlisted);

    public interface StatusActionListener {
        void deactivateListing();

        void list();

        void snooze();

        void unlist();
    }

    public ManageListingStatusAdapter(Context context2, Listing listing, StatusActionListener listener2) {
        super(true);
        enableDiffing();
        this.listener = listener2;
        this.context = context2;
        this.loader.setColor(ContextCompat.getColor(context2, C7368R.color.n2_babu));
        setRowsEnabled(true);
        showListingInfo(listing);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_status_title), this.listedRow, this.snoozedRow, this.unlistedRow, this.deactivateListingRow});
    }

    private void showListingInfo(Listing listing) {
        resetRows();
        this.snoozedRow.subtitle((CharSequence) ListingTextUtils.getSnoozeSummaryString(this.context, listing.getSnoozeMode()));
        ListedStatus status = ListedStatus.calculate(listing);
        switch (status) {
            case Listed:
                setSelectedRow(this.listedRow);
                return;
            case Snoozed:
                setSelectedRow(this.snoozedRow);
                return;
            case Unlisted:
                setSelectedRow(this.unlistedRow);
                this.snoozedRow.hide();
                return;
            default:
                throw new UnhandledStateException(status);
        }
    }

    private void resetRows() {
        this.listedRow.rowDrawable((Drawable) null).rowDrawableRes(0);
        this.snoozedRow.rowDrawable((Drawable) null).rowDrawableRes(0).disclosure().show();
        this.unlistedRow.rowDrawable((Drawable) null).rowDrawableRes(0);
    }

    public void setListedLoading() {
        setRowsEnabled(false);
        this.listedRow.rowDrawable((Drawable) this.loader);
        notifyModelChanged(this.listedRow);
    }

    public void cancelLoading() {
        setRowsEnabled(true);
        this.listedRow.rowDrawable((Drawable) null);
        notifyModelChanged(this.listedRow);
    }

    public void setRowsEnabled(boolean enabled) {
        if (enabled) {
            this.listedRow.clickListener(ManageListingStatusAdapter$$Lambda$1.lambdaFactory$(this));
            this.snoozedRow.clickListener(ManageListingStatusAdapter$$Lambda$2.lambdaFactory$(this));
            this.unlistedRow.clickListener(ManageListingStatusAdapter$$Lambda$3.lambdaFactory$(this));
            this.deactivateListingRow.clickListener(ManageListingStatusAdapter$$Lambda$4.lambdaFactory$(this));
            return;
        }
        this.listedRow.clickListener((OnClickListener) null);
        this.snoozedRow.clickListener((OnClickListener) null);
        this.unlistedRow.clickListener((OnClickListener) null);
        this.deactivateListingRow.clickListener((OnClickListener) null);
    }

    private void setSelectedRow(StandardRowEpoxyModel_ row) {
        row.rowDrawableRes(C7368R.C7369drawable.n2_ic_check_babu);
    }

    public void updateForListing(Listing listing) {
        setRowsEnabled(true);
        showListingInfo(listing);
        notifyModelsChanged();
    }
}
