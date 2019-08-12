package com.airbnb.p027n2.collections;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SheetMarquee.Style;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.collections.ProfilePhotoSheet */
public class ProfilePhotoSheet extends ScrollView {
    private Delegate delegate;
    private int placeHolderImageRes;
    private HaloImageView profilePhoto;
    private AirImageView profilePhotoError;
    private SheetMarquee sheetMarquee;

    /* renamed from: com.airbnb.n2.collections.ProfilePhotoSheet$Delegate */
    public interface Delegate {
        void onProfilePhotoClick();
    }

    public ProfilePhotoSheet(Context context) {
        super(context);
        init(context, null);
    }

    public ProfilePhotoSheet(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProfilePhotoSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setDelegate(Delegate delegate2) {
        this.delegate = delegate2;
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.n2_profile_photo_sheet, this);
        bindViews();
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
        setupAttributes(attrs);
    }

    private void bindViews() {
        this.sheetMarquee = (SheetMarquee) ViewLibUtils.findById(this, R.id.profile_photo_sheet_marquee);
        this.profilePhoto = (HaloImageView) ViewLibUtils.findById(this, R.id.profile_photo_sheet_image);
        this.profilePhoto.setImageDefault();
        this.profilePhotoError = (AirImageView) ViewLibUtils.findById(this, R.id.profile_photo_sheet_image_error);
        this.profilePhoto.setOnClickListener(ProfilePhotoSheet$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$bindViews$0(ProfilePhotoSheet profilePhotoSheet, View v) {
        if (profilePhotoSheet.delegate != null) {
            profilePhotoSheet.delegate.onProfilePhotoClick();
        }
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ProfilePhotoSheet);
        Style.BABU.setStyle(this.sheetMarquee);
        this.sheetMarquee.setTitle(a.getString(R.styleable.n2_ProfilePhotoSheet_n2_titleText));
        this.sheetMarquee.setSubtitle(a.getString(R.styleable.n2_ProfilePhotoSheet_n2_subtitleText));
        this.placeHolderImageRes = a.getResourceId(R.styleable.n2_ProfilePhotoSheet_n2_placeholder, -1);
        a.recycle();
    }

    public void setMarqueeStyle(Style style) {
        style.setStyle(this.sheetMarquee);
    }

    public void setTitle(int titleResId) {
        this.sheetMarquee.setTitle(titleResId);
    }

    public void setSubtitle(int subtitleResId) {
        this.sheetMarquee.setSubtitle(subtitleResId);
    }

    public void setProfilePhoto(Uri uri) {
        this.profilePhoto.setImageUri(uri);
    }

    public void setPlaceHolderImageRes(int placeHolderImageRes2) {
        this.placeHolderImageRes = placeHolderImageRes2;
    }

    public void setDefaultPhoto() {
        if (this.placeHolderImageRes == -1) {
            this.profilePhoto.setImageDefault();
            return;
        }
        this.profilePhoto.setImageResource(this.placeHolderImageRes);
        this.profilePhoto.removeBorder();
    }

    public void updateErrorVisibility(boolean showError) {
        ViewLibUtils.setVisibleIf(this.profilePhotoError, showError);
    }

    public void setProfilePhotoBorder(int color, float thickness) {
        this.profilePhoto.setBorder(color, thickness);
    }
}
