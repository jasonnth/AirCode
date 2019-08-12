package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class EmptyResultsCardView extends FrameLayout {
    private static final int SUBTITLE_LINE_COUNT_THRESHOLD = 2;
    @BindView
    Button actionButton;
    @BindView
    ImageView backgroundImg;
    private int backgroundRes;
    @BindView
    TextView cardSubtitle;
    @BindView
    TextView cardTitle;
    private boolean hasBackgroundResChanged;
    protected MemoryUtils memoryUtils;
    /* access modifiers changed from: private */
    public int originalSubtitleSidePadding;
    @BindView
    TextView topTitle;

    public EmptyResultsCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.originalSubtitleSidePadding = -1;
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.EmptyResultsCard, 0, 0);
        String topTitleText = a.getString(C0880R.styleable.EmptyResultsCard_topTitleText);
        String cardTitleText = a.getString(C0880R.styleable.EmptyResultsCard_cardTitleText);
        String cardSubtitleText = a.getString(C0880R.styleable.EmptyResultsCard_cardSubTitleText);
        a.recycle();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0880R.layout.empty_results_card, this, true);
        ButterKnife.bind((View) this);
        if (!isInEditMode()) {
            ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        }
        this.topTitle.setText(topTitleText);
        this.cardTitle.setText(cardTitleText);
        this.cardSubtitle.setText(cardSubtitleText);
        adjustSubtitlePaddingIfNecessary();
    }

    private void adjustSubtitlePaddingIfNecessary() {
        this.cardSubtitle.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int newSidePadding;
                if (EmptyResultsCardView.this.originalSubtitleSidePadding == -1) {
                    EmptyResultsCardView.this.originalSubtitleSidePadding = EmptyResultsCardView.this.cardSubtitle.getPaddingLeft();
                }
                if (EmptyResultsCardView.this.cardSubtitle.getLineCount() > 2) {
                    newSidePadding = 0;
                } else {
                    newSidePadding = EmptyResultsCardView.this.originalSubtitleSidePadding;
                }
                EmptyResultsCardView.this.cardSubtitle.setPadding(newSidePadding, EmptyResultsCardView.this.cardSubtitle.getPaddingTop(), newSidePadding, EmptyResultsCardView.this.cardSubtitle.getPaddingBottom());
                EmptyResultsCardView.this.cardSubtitle.removeOnLayoutChangeListener(this);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        loadBackgroundIfNeeded();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        loadBackgroundIfNeeded();
    }

    public EmptyResultsCardView(Context context) {
        this(context, null);
    }

    public void setBackgroundImageRes(int imageResId) {
        this.backgroundRes = imageResId;
        this.hasBackgroundResChanged = true;
        loadBackgroundIfNeeded();
    }

    private void loadBackgroundIfNeeded() {
        if (this.hasBackgroundResChanged && ViewCompat.isAttachedToWindow(this) && getVisibility() == 0) {
            this.hasBackgroundResChanged = false;
            if (this.backgroundRes <= 0 || this.memoryUtils.isLowMemoryDeviceOrOomOccurredInLastWeekProd()) {
                this.backgroundImg.setBackgroundResource(C0880R.color.c_gray_4);
                return;
            }
            try {
                Options opts = new Options();
                opts.inPreferredConfig = Config.RGB_565;
                this.backgroundImg.setImageDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), this.backgroundRes, opts)));
            } catch (OutOfMemoryError e) {
                if (BuildHelper.isReleaseBuild()) {
                    this.memoryUtils.handleCaughtOOM("empty_results_card_view");
                    return;
                }
                throw e;
            }
        }
    }

    public void setTopTitle(int titleRes) {
        this.topTitle.setText(titleRes);
    }

    public void setCardTitle(String title) {
        this.cardTitle.setText(title);
    }

    public void setCardTitle(int titleRes) {
        this.cardTitle.setText(titleRes);
    }

    public void setCardSubTitle(String subTitle) {
        this.cardSubtitle.setText(subTitle);
        adjustSubtitlePaddingIfNecessary();
    }

    public void setCardSubtitle(int subtitleRes) {
        this.cardSubtitle.setText(subtitleRes);
    }

    public void setupActionButton(int buttonTitleRes, OnClickListener onClickListener) {
        this.actionButton.setVisibility(0);
        this.actionButton.setText(buttonTitleRes);
        this.actionButton.setOnClickListener(onClickListener);
    }

    public void setButtonVisible(boolean visible) {
        ViewUtils.setVisibleIf((View) this.actionButton, visible);
    }
}
