package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

/* renamed from: com.airbnb.n2.primitives.TriptychView */
public class TriptychView extends PercentRelativeLayout {
    private int emptyStateDrawableRes;
    @BindView
    AirImageView leftImage;
    @BindView
    AirImageView rightBottomImage;
    @BindView
    ViewGroup rightImagesContainer;
    @BindView
    AirImageView rightTopImage;

    public TriptychView(Context context) {
        super(context);
        init();
    }

    public TriptychView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TriptychView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        inflate(getContext(), R.layout.n2_triptych_view, this);
        ButterKnife.bind((View) this);
    }

    public void setImageUrls(List<String> urls) {
        setImageUrls((String) getListItemOrNull(urls, 0), (String) getListItemOrNull(urls, 1), (String) getListItemOrNull(urls, 2));
    }

    private static <T> T getListItemOrNull(List<T> list, int position) {
        if (list != null && list.size() > position) {
            return list.get(position);
        }
        return null;
    }

    public void setImageUrls(String leftUrl, String rightTopUrl, String rightBottomUrl) {
        boolean hideImages = TextUtils.isEmpty(leftUrl);
        boolean hideRightImages = hideImages || TextUtils.isEmpty(rightTopUrl) || TextUtils.isEmpty(rightBottomUrl);
        ViewLibUtils.setGoneIf(this.leftImage, hideImages);
        ViewLibUtils.setGoneIf(this.rightImagesContainer, hideRightImages);
        if (hideImages) {
            this.leftImage.clear();
            showBackground();
        } else {
            this.leftImage.setImageUrl(leftUrl);
            setBackground(null);
        }
        if (hideRightImages) {
            this.rightTopImage.clear();
            this.rightBottomImage.clear();
            return;
        }
        this.rightTopImage.setImageUrl(rightTopUrl);
        this.rightBottomImage.setImageUrl(rightBottomUrl);
    }

    public void clearImages() {
        setImageUrls(null, null, null);
    }

    public void setEmptyStateDrawableRes(int emptyStateDrawableRes2) {
        this.emptyStateDrawableRes = emptyStateDrawableRes2;
        if (this.leftImage.getVisibility() == 8) {
            showBackground();
        }
    }

    private void showBackground() {
        if (this.emptyStateDrawableRes != 0) {
            setBackgroundResource(this.emptyStateDrawableRes);
        } else {
            setBackgroundResource(R.color.n2_foggy);
        }
    }
}
