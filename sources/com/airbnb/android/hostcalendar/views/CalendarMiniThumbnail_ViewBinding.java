package com.airbnb.android.hostcalendar.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class CalendarMiniThumbnail_ViewBinding implements Unbinder {
    private CalendarMiniThumbnail target;

    public CalendarMiniThumbnail_ViewBinding(CalendarMiniThumbnail target2) {
        this(target2, target2);
    }

    public CalendarMiniThumbnail_ViewBinding(CalendarMiniThumbnail target2, View source) {
        this.target = target2;
        target2.calendarThumbnail = (CalendarMiniThumbnailGrid) Utils.findRequiredViewAsType(source, C6418R.C6420id.calendar_thumbnail, "field 'calendarThumbnail'", CalendarMiniThumbnailGrid.class);
        target2.listingImage = (AirImageView) Utils.findRequiredViewAsType(source, C6418R.C6420id.listing_image, "field 'listingImage'", AirImageView.class);
        target2.listingNameText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.listing_name, "field 'listingNameText'", AirTextView.class);
    }

    public void unbind() {
        CalendarMiniThumbnail target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.calendarThumbnail = null;
        target2.listingImage = null;
        target2.listingNameText = null;
    }
}
