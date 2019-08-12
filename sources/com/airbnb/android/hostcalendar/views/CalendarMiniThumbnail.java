package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.ArrayList;

public class CalendarMiniThumbnail extends LinearLayout {
    @BindView
    CalendarMiniThumbnailGrid calendarThumbnail;
    @BindView
    AirImageView listingImage;
    @BindView
    AirTextView listingNameText;

    public CalendarMiniThumbnail(Context context) {
        this(context, null);
    }

    public CalendarMiniThumbnail(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarMiniThumbnail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), C6418R.layout.calendar_mini_thumbnail, this);
        ButterKnife.bind((View) this);
    }

    public void bind(Listing listing, AirDate startDate, AirDate today, ArrayList<Reservation> reservations, boolean showListingImage) {
        if (showListingImage) {
            this.listingImage.setImageUrl(listing.getThumbnailUrl());
            this.listingImage.setVisibility(0);
        } else {
            this.calendarThumbnail.bind(listing, startDate, today, reservations);
            this.calendarThumbnail.setVisibility(0);
        }
        this.listingNameText.setText(listing.getName());
    }
}
