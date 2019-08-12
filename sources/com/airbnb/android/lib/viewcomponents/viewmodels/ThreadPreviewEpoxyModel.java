package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.ThreadPreviewRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.daimajia.swipe.SimpleSwipeListener;
import java.util.List;

public abstract class ThreadPreviewEpoxyModel extends AirEpoxyModel<ThreadPreviewRow> {
    OnClickListener actionButtonClickListener;
    CharSequence actionButtonText;
    int actionButtonTextRes;
    OnClickListener clickListener;
    CharSequence fourthRowText;
    boolean hideProfilePhoto;
    int imageRes;
    List<String> imageUrls;
    OnLongClickListener longClickListener;
    boolean multipleLineTitle;
    CharSequence profilePlaceholderText;
    boolean shouldShowSquareImage;
    boolean showActionButton;
    boolean showUnread;
    CharSequence subtitleText;
    boolean swipeEnabled;
    SimpleSwipeListener swipeListener;
    int swipeTextRes;
    CharSequence thirdRowText;
    AirDateTime timeAgo;
    CharSequence titleText;

    public ThreadPreviewEpoxyModel(long id) {
        super(id);
    }

    public void bind(ThreadPreviewRow view) {
        String str = null;
        super.bind(view);
        view.setActionButtonListener(this.actionButtonClickListener);
        view.setOnClickListener(this.clickListener);
        view.setPlaceholderText(this.hideProfilePhoto ? this.profilePlaceholderText : null);
        if (this.hideProfilePhoto) {
            view.setImageRes(C0880R.C0881drawable.no_profile_image);
        } else if (this.imageUrls != null) {
            view.setImageUrls(this.imageUrls, this.shouldShowSquareImage);
        } else {
            view.setImageRes(this.imageRes);
        }
        view.setTitleText(this.titleText);
        view.setSubtitleText(this.subtitleText);
        view.setThirdRowText(this.thirdRowText);
        view.setFourthRowText(this.fourthRowText);
        if (this.timeAgo != null) {
            str = this.timeAgo.getTimeAgoText(view.getContext());
        }
        view.setTimeAgoText(str);
        view.setShowMultiline(this.multipleLineTitle);
        if (this.actionButtonTextRes != 0) {
            view.setActionButtonText(this.actionButtonTextRes);
        } else {
            view.setActionButtonText(this.actionButtonText);
        }
        view.setActionButtonVisible(this.showActionButton);
        view.setUnreadIndicatorVisible(this.showUnread);
        if (this.swipeEnabled) {
            view.enableSwipe();
            view.setSwipeText(this.swipeTextRes);
            view.setSwipeListener(this.swipeListener);
            view.setClickListenerOnThreadPreview(this.clickListener);
            return;
        }
        view.disableSwipe();
        view.setOnLongClickListener(this.longClickListener);
    }

    public void unbind(ThreadPreviewRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
        view.setOnLongClickListener(null);
        view.setSwipeListener(null);
    }
}
