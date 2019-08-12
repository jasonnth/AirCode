package com.airbnb.android.contentframework.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryCreationImagePreviewActivity extends AirActivity {
    private static final String EXTRA_IMAGE_URI = "EXTRA_IMAGE_URI";
    @BindView
    AirImageView imageView;
    @BindView
    View rootView;

    public static Intent newInstance(Context context, Uri uri) {
        Intent intent = new Intent(context, StoryCreationImagePreviewActivity.class);
        intent.putExtra(EXTRA_IMAGE_URI, uri);
        return intent;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5709R.layout.fragment_story_creation_image_preview);
        ButterKnife.bind((Activity) this);
        this.imageView.setImageURI((Uri) getIntent().getParcelableExtra(EXTRA_IMAGE_URI));
        this.rootView.setOnClickListener(StoryCreationImagePreviewActivity$$Lambda$1.lambdaFactory$(this));
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }
}
