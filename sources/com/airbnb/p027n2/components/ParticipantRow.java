package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ParticipantRow */
public class ParticipantRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    private OnClickListener imageClickListener;
    @BindView
    TextView nameText;
    @BindView
    ImageView removeButton;
    private OnClickListener removeClickListener;
    @BindView
    HaloImageView userImage;

    public ParticipantRow(Context context) {
        super(context);
        init();
    }

    public ParticipantRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParticipantRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_view_participant_row, this);
        ButterKnife.bind((View) this);
    }

    public void setImageUrl(String url) {
        this.userImage.setImageUrl(url);
    }

    public void setNameText(CharSequence text) {
        this.nameText.setText(text);
    }

    public void setRemovable(boolean removable) {
        ViewLibUtils.setVisibleIf(this.removeButton, removable);
    }

    public void setImageClickListener(OnClickListener imageClickListener2) {
        this.imageClickListener = imageClickListener2;
    }

    public void setRemoveClickListener(OnClickListener removeClickListener2) {
        this.removeClickListener = removeClickListener2;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onImageClicked() {
        if (this.imageClickListener != null) {
            this.imageClickListener.onClick(this.userImage);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onRemoveClicked() {
        if (this.removeClickListener != null) {
            this.removeClickListener.onClick(this.removeButton);
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(ParticipantRow view) {
        view.setNameText("Name");
    }
}
