package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.TriptychView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

/* renamed from: com.airbnb.n2.components.MosaicCard */
public class MosaicCard extends FrameLayout implements DividerView {
    @BindView
    LinearLayout accessory;
    @BindView
    View bottomSpace;
    @BindView
    AirTextView commentCount;
    @BindView
    AirImageView commentIcon;
    @BindView
    View divider;
    @BindView
    AirTextView likeCount;
    @BindView
    AirImageView likeIcon;
    @BindView
    AirTextView tag;
    @BindView
    AirTextView titleTextView;
    @BindView
    TriptychView triptychView;

    public MosaicCard(Context context) {
        super(context);
        init(null);
    }

    public MosaicCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MosaicCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_mosaic_card, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_MosaicCard);
        boolean showBottomSpace = ta.getBoolean(R.styleable.n2_MosaicCard_n2_showDivider, true);
        boolean showDivider = ta.getBoolean(R.styleable.n2_MosaicCard_n2_showDivider, true);
        showBottomSpace(showBottomSpace);
        showDivider(showDivider);
        ta.recycle();
    }

    public void setImages(List<String> urls) {
        this.triptychView.setImageUrls(urls);
    }

    public void clearImages() {
        this.triptychView.clearImages();
    }

    public void setupTitle(String boldText, String regularText) {
        boolean hasTitle = !TextUtils.isEmpty(boldText);
        ViewLibUtils.setVisibleIf(this.titleTextView, hasTitle);
        if (!hasTitle) {
            this.titleTextView.setText(null);
            return;
        }
        if (regularText == null) {
            regularText = "";
        }
        this.titleTextView.setText(TextUtil.makeCircularBold(getContext(), boldText + "  " + regularText, boldText));
    }

    public void setEmptyStateDrawableRes(int emptyStateDrawableRes) {
        this.triptychView.setEmptyStateDrawableRes(emptyStateDrawableRes);
    }

    public void showCommentsAndLikes(boolean show) {
        ViewLibUtils.setVisibleIf(this.likeIcon, show);
        ViewLibUtils.setVisibleIf(this.likeCount, show);
        ViewLibUtils.setVisibleIf(this.commentIcon, show);
        ViewLibUtils.setVisibleIf(this.commentCount, show);
    }

    public void showAccessory(boolean show) {
        ViewLibUtils.setVisibleIf(this.accessory, show);
    }

    public void setCommentCount(int comments) {
        this.commentCount.setText(Integer.toString(comments));
    }

    public void setLikeCount(int likes) {
        this.likeCount.setText(Integer.toString(likes));
    }

    public void setTag(String tagText) {
        this.tag.setText(tagText);
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public void setIsMiniCard() {
        ViewLibUtils.setPaddingTop(this.titleTextView, getResources().getDimensionPixelSize(R.dimen.n2_vertical_padding_tiny));
        this.titleTextView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.n2_mini_mosaic_card_text_size));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(MosaicCard card) {
        card.setupTitle("Bold", "Regular");
        card.setCommentCount(42);
        card.setTag("Tag");
    }
}
