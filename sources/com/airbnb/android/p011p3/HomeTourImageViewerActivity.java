package com.airbnb.android.p011p3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.p011p3.models.RoomPhoto;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;

/* renamed from: com.airbnb.android.p3.HomeTourImageViewerActivity */
public class HomeTourImageViewerActivity extends SolitAirActivity {
    private static final String EXTRA_IMAGE = "room_photo";
    @BindView
    AirImageView imageView;
    private RoomPhoto photo;
    @BindView
    AirToolbar toolbar;

    public static Intent intent(Context context, RoomPhoto roomPhoto) {
        return new Intent(context, HomeTourImageViewerActivity.class).putExtra(EXTRA_IMAGE, roomPhoto);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7532R.layout.activity_home_tour_image_viewer);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.photo = (RoomPhoto) getIntent().getParcelableExtra(EXTRA_IMAGE);
        this.imageView.setImage(new SimpleImage(this.photo.largeUrl(), this.photo.previewEncodedPng()));
    }

    /* access modifiers changed from: protected */
    public boolean shouldLockToPortrait() {
        return false;
    }
}
