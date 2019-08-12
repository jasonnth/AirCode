package com.airbnb.android.places.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.p002v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.map.PlaceMarkerGenerator;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.StaticMapView;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;

public class PlaceInfoView extends CardView {
    @BindView
    View addressRow;
    @BindView
    AirTextView addressView;
    @State
    String airmoji;
    @BindView
    View hoursDivider;
    @BindView
    View hoursRow;
    @BindView
    AirTextView hoursView;
    @BindView
    StaticMapView mapView;
    @BindView
    AirTextView nameView;
    @BindView
    View phoneNumberDivider;
    @BindView
    View phoneNumberRow;
    @BindView
    AirTextView phoneNumberView;
    private PlaceMarkerGenerator placeMarkerGenerator;
    @BindView
    View websiteDivider;
    @BindView
    View websiteRow;
    @BindView
    AirTextView websiteView;

    public PlaceInfoView(Context context) {
        super(context);
        init();
    }

    public PlaceInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlaceInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C7627R.layout.view_place_info, this);
        setCardElevation(getResources().getDimension(C7627R.dimen.place_info_view_card_elevation));
        setRadius(getResources().getDimension(C7627R.dimen.place_info_view_card_corner_radius));
        ButterKnife.bind((View) this);
        this.placeMarkerGenerator = new PlaceMarkerGenerator(getContext(), this);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!TextUtils.isEmpty(this.airmoji)) {
            Bitmap markerBitmap = this.placeMarkerGenerator.getBitmap(this.airmoji);
            canvas.drawBitmap(markerBitmap, (((float) this.mapView.getWidth()) / 2.0f) - (((float) markerBitmap.getWidth()) / 2.0f), (((float) this.mapView.getHeight()) / 2.0f) - ((float) markerBitmap.getHeight()), null);
        }
    }

    public void setName(CharSequence name) {
        this.nameView.setText(name);
    }

    public void setAddress(CharSequence address, OnClickListener listener) {
        boolean addressGiven = !TextUtils.isEmpty(address);
        ViewLibUtils.setVisibleIf(this.addressView, addressGiven);
        this.addressView.setText(address);
        if (addressGiven) {
            this.addressRow.setOnClickListener(listener);
        }
    }

    public void drawMap(MapOptions mapOptions, OnClickListener listener, String airmoji2) {
        this.airmoji = airmoji2;
        this.mapView.setup(mapOptions, PlaceInfoView$$Lambda$1.lambdaFactory$());
        this.mapView.setOnClickListener(listener);
    }

    public void setHours(String hoursText, OnClickListener listener) {
        boolean showHoursRow = listener != null && !TextUtils.isEmpty(hoursText);
        ViewLibUtils.setVisibleIf(this.hoursRow, showHoursRow);
        ViewLibUtils.setVisibleIf(this.hoursDivider, showHoursRow);
        this.hoursView.setText(hoursText);
        this.hoursRow.setOnClickListener(listener);
    }

    public void setPhoneNumber(String phoneNumber, OnClickListener listener) {
        boolean phoneNumberGiven = !TextUtils.isEmpty(phoneNumber);
        ViewLibUtils.setVisibleIf(this.phoneNumberRow, phoneNumberGiven);
        ViewLibUtils.setVisibleIf(this.phoneNumberDivider, phoneNumberGiven);
        this.phoneNumberView.setText(phoneNumber);
        if (phoneNumberGiven) {
            this.phoneNumberRow.setOnClickListener(listener);
        }
    }

    public void setWebsite(CharSequence website, OnClickListener listener) {
        boolean websiteGiven = !TextUtils.isEmpty(website);
        ViewLibUtils.setVisibleIf(this.websiteRow, websiteGiven);
        ViewLibUtils.setVisibleIf(this.websiteDivider, websiteGiven);
        this.websiteView.setText(MiscUtils.getHostFromUrl(website));
        if (websiteGiven) {
            this.websiteRow.setOnClickListener(listener);
        }
    }
}
