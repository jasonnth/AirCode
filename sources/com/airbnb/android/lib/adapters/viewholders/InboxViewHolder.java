package com.airbnb.android.lib.adapters.viewholders;

import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class InboxViewHolder extends BindableViewHolder<Thread> {
    @BindView
    TextView mDetailsTextView;
    @BindView
    View mInboxItemFrame;
    @BindView
    TextView mNameTextView;
    @BindView
    TextView mPreviewTextView;
    @BindView
    HaloImageView mProfileImageView;
    @BindView
    TextView mTimeTextView;

    public InboxViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_inbox_v2, parent, false));
    }

    public void bind(Thread thread) {
        ImageUtils.setImageUrlForUser(this.mProfileImageView, thread.getOtherUser());
        this.mNameTextView.setText(thread.getOtherUser().getName());
        this.mPreviewTextView.setText(thread.getTextPreview());
        this.mTimeTextView.setText(thread.getLastMessageAt().getElapsedTime(this.context));
        ReservationStatusDisplay statusDisplay = ReservationStatusDisplay.forStatus(thread.getReservationStatus());
        String statusText = statusDisplay.getString(this.context);
        ViewUtils.setTextAndColorSubstring(this.mDetailsTextView, this.context.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{statusText, thread.hasListing() ? thread.getListing().getName() : ""}), statusText, statusDisplay.getColor(this.context));
        boolean isUnread = thread.isUnread();
        ViewUtils.setBoldIf(this.mNameTextView, isUnread);
        ViewUtils.setBoldIf(this.mDetailsTextView, isUnread);
        this.mInboxItemFrame.setBackgroundColor(ContextCompat.getColor(this.context, isUnread ? C0880R.color.white : C0880R.color.c_gray_6));
        this.itemView.setTag(thread);
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.itemView.setOnLongClickListener(longClickListener);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }

    public void setSelected(boolean selected) {
        this.itemView.setSelected(selected);
    }
}
