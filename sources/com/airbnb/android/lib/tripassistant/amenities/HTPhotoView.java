package com.airbnb.android.lib.tripassistant.amenities;

import android.content.Context;
import android.support.percent.PercentFrameLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class HTPhotoView extends PercentFrameLayout {
    @BindView
    ImageView deleteButton;
    @BindView
    AirImageView image;
    @BindView
    View loader;

    public HTPhotoView(Context context) {
        super(context);
        init();
    }

    public HTPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HTPhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0880R.layout.view_help_thread_photo, this);
        ButterKnife.bind((View) this);
        setClipChildren(false);
        setClipToPadding(false);
        float deleteButtonOffset = getResources().getDimension(C0880R.dimen.n2_grid_card_inner_horizontal_padding) / 2.0f;
        this.deleteButton.setTranslationX(deleteButtonOffset);
        this.deleteButton.setTranslationY(-deleteButtonOffset);
    }

    public void showLoader(boolean show) {
        ViewUtils.setVisibleIf(this.loader, show);
        ViewUtils.setGoneIf(this.deleteButton, show);
        ViewUtils.setGoneIf(this.image, show);
    }

    public void setPhoto(HelpThreadPhoto photo) {
        this.image.setImageUrl(photo.getUrl());
    }

    public void clear() {
        this.image.clear();
    }

    public void setDeleteButtonClickListener(OnClickListener listener) {
        this.deleteButton.setOnClickListener(listener);
    }
}
