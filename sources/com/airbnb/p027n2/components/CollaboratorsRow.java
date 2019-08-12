package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

/* renamed from: com.airbnb.n2.components.CollaboratorsRow */
public class CollaboratorsRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    FrameLayout facesContainer;
    private OnClickListener friendsClickListener;
    @BindDimen
    int imageDiameter;
    @BindView
    ImageView inviteButtonIcon;
    @BindView
    AirButton inviteButtonText;
    private OnClickListener inviteClickListener;
    @BindView
    TextView overflowText;

    public CollaboratorsRow(Context context) {
        super(context);
        init();
    }

    public CollaboratorsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CollaboratorsRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_collaborators_row, this);
        ButterKnife.bind((View) this);
    }

    public void setButtonText(int buttonText) {
        this.inviteButtonText.setText(buttonText);
    }

    public void setButtonText(CharSequence buttonText) {
        this.inviteButtonText.setText(buttonText);
    }

    public void setImageUrls(Collection<String> urls) {
        boolean showImages;
        boolean z = true;
        if (urls.size() > 1) {
            showImages = true;
        } else {
            showImages = false;
        }
        ViewLibUtils.setGoneIf(this.inviteButtonText, showImages);
        ViewLibUtils.setVisibleIf(this.inviteButtonIcon, showImages);
        ViewLibUtils.setVisibleIf(this.facesContainer, showImages);
        TextView textView = this.overflowText;
        if (urls.size() <= 6) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(textView, z);
        this.overflowText.setText("+" + (urls.size() - 6));
        updatePhotos(urls);
    }

    private void updatePhotos(Collection<String> urls) {
        this.facesContainer.removeAllViews();
        List<String> urlsToShow = FluentIterable.from((Iterable<E>) urls).limit(6).toList();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = urlsToShow.size() - 1; i >= 0; i--) {
            HaloImageView image = (HaloImageView) inflater.inflate(R.layout.n2_view_participant_list_row_halo_image, this.facesContainer, false);
            image.setImageUrl((String) urlsToShow.get(i));
            ((MarginLayoutParams) image.getLayoutParams()).leftMargin = (int) (((double) (this.imageDiameter * i)) * 0.75d);
            this.facesContainer.addView(image);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onInviteClicked(View v) {
        if (this.inviteClickListener != null) {
            this.inviteClickListener.onClick(v);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onFriendsClicked(View v) {
        if (this.friendsClickListener != null) {
            this.friendsClickListener.onClick(v);
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setFriendsClickListener(OnClickListener friendsClickListener2) {
        this.friendsClickListener = friendsClickListener2;
    }

    public void setInviteClickListener(OnClickListener inviteClickListener2) {
        this.inviteClickListener = inviteClickListener2;
    }

    public static void mock(CollaboratorsRow view) {
        view.setButtonText((CharSequence) "Button");
    }
}
