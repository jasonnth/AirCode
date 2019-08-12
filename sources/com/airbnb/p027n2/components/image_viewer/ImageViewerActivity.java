package com.airbnb.p027n2.components.image_viewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.image_viewer.ImageViewer.OnViewDragCallback;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.n2.components.image_viewer.ImageViewerActivity */
public class ImageViewerActivity extends AppCompatActivity implements OnViewDragCallback {
    @BindView
    View background;
    @BindView
    ImageViewer imageViewer;
    @BindView
    AirToolbar toolbar;

    public static Intent newIntent(Context context, List<String> images, int selectionIndex, String transitionNameType, long transitionNameId) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra("arg_selection_index", selectionIndex);
        intent.putStringArrayListExtra("arg_images", new ArrayList(images));
        if (transitionNameType != null) {
            intent.putExtra("arg_transition_name_type", transitionNameType);
        }
        intent.putExtra("arg_transition_name_id", transitionNameId);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n2_activity_image_viewer);
        ButterKnife.bind((Activity) this);
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(ImageViewerActivity$$Lambda$1.lambdaFactory$(this));
        Bundle extras = getIntent().getExtras();
        this.imageViewer.setData(extras.getString("arg_transition_name_type"), extras.getLong("arg_transition_name_id"), FluentIterable.from((Iterable<E>) extras.getStringArrayList("arg_images")).transform(ImageViewerActivity$$Lambda$2.lambdaFactory$()).toList());
        this.imageViewer.scrollToPosition(extras.getInt("arg_selection_index"));
        this.imageViewer.setViewDragCallback(this);
    }

    public void onViewDragged(float dragPercentage) {
        this.background.setAlpha(1.0f - dragPercentage);
    }

    public void onViewCaptured() {
        this.toolbar.animate().alpha(0.0f).setDuration(150);
    }

    public void onViewReleased(boolean isSettling) {
        if (isSettling) {
            this.toolbar.animate().alpha(1.0f).setDuration(150);
        } else {
            supportFinishAfterTransition();
        }
    }
}
