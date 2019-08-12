package com.airbnb.android.lib.activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.views.ClickableViewPager;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.ViewPagerFtueFragment;
import com.airbnb.android.lib.views.FtueStickyButton;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.DotsCounter;

public abstract class ViewPagerFtueBaseActivity extends AirActivity {
    private static final String ARG_CURRENT_PAGE = "current_position";
    private static final int IMAGE_FADE_ANIM_DURATION = 600;
    private static final float PARALLAX_SCALE_FACTOR = 1.4f;
    /* access modifiers changed from: private */
    public BitmapDrawable mCurrDrawable;
    protected int mCurrPage = 0;
    private int mCurrentColor = -1;
    protected ImageView mImageBg;
    private int mMaxPositionOffset = 0;
    /* access modifiers changed from: private */
    public BitmapDrawable mNextDrawable;
    private AsyncTask<Void, Void, TransitionDrawable> mProcessImagesTask;
    private FtueStickyButton mSecondStickyButton;
    private final Runnable mSetImageRunnable = ViewPagerFtueBaseActivity$$Lambda$1.lambdaFactory$(this);
    protected FtueStickyButton mStickyButton;
    private View mStickyButtonBackground;
    private View mStickyButtonDivider;
    private Toolbar mToolbar;
    private ValueAnimator mValueAnimator;

    private class FtueAdapter extends FragmentStatePagerAdapter {
        public FtueAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return ViewPagerFtueBaseActivity.this.forStep(position);
        }

        public int getCount() {
            return ViewPagerFtueBaseActivity.this.getNumPages();
        }
    }

    public abstract ViewPagerFtueFragment forStep(int i);

    public abstract int[] getBackgroundImageResIds();

    public abstract int getNumPages();

    public abstract boolean isParallaxMode();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        if (AndroidVersion.isAtLeastKitKat()) {
            getWindow().setFlags(67108864, 67108864);
        }
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mCurrPage = savedInstanceState.getInt(ARG_CURRENT_PAGE);
        }
        checkFtueImplValid();
        supportRequestWindowFeature(9);
        supportRequestWindowFeature(10);
        setContentView(C0880R.layout.activity_view_pager_ftue);
        this.mToolbar = (Toolbar) findViewById(C0880R.C0882id.toolbar);
        setSupportActionBar(this.mToolbar);
        setupTransparentActionBar();
        setupBackgroundImage();
        setupLogo();
        this.mStickyButton = (FtueStickyButton) findViewById(C0880R.C0882id.main_sticky_button);
        this.mSecondStickyButton = (FtueStickyButton) findViewById(C0880R.C0882id.second_sticky_button);
        this.mStickyButtonDivider = findViewById(C0880R.C0882id.sticky_button_divider);
        this.mStickyButtonBackground = findViewById(C0880R.C0882id.sticky_button_holder);
        initStickyAnimation();
        final ClickableViewPager viewPager = (ClickableViewPager) findViewById(C0880R.C0882id.pager_ftue_content);
        viewPager.setAdapter(new FtueAdapter(getSupportFragmentManager()));
        int colorRes = ((ViewPagerFtueFragment) ((FtueAdapter) viewPager.getAdapter()).getItem(0)).getStickyButtonColor();
        if (colorRes > 0) {
            fadeStickyToColor(colorRes);
        }
        viewPager.setOnClickListener(ViewPagerFtueBaseActivity$$Lambda$2.lambdaFactory$(this, viewPager));
        final DotsCounter dots = (DotsCounter) findViewById(C0880R.C0882id.dots_counter);
        int numDots = getNumPages();
        if (numDots > 1) {
            dots.setVisibility(0);
            dots.setNumDots(numDots);
            viewPager.setOnPageChangeListener(new OnPageChangeListener() {
                public void onPageSelected(int position) {
                    ViewPagerFtueBaseActivity.this.goToNextFtuePage(position, dots);
                    int colorRes = ((ViewPagerFtueFragment) ((FtueAdapter) viewPager.getAdapter()).getItem(position)).getStickyButtonColor();
                    if (colorRes > 0) {
                        ViewPagerFtueBaseActivity.this.fadeStickyToColor(colorRes);
                    }
                }

                public void onPageScrollStateChanged(int state) {
                }

                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (ViewPagerFtueBaseActivity.this.isParallaxMode()) {
                        ViewPagerFtueBaseActivity.this.parallaxScrollBackgroundImage(position, positionOffsetPixels);
                    }
                }
            });
            return;
        }
        dots.setNumDots(0);
        dots.setVisibility(8);
    }

    static /* synthetic */ void lambda$onCreate$0(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity, ClickableViewPager viewPager, View v) {
        if (viewPagerFtueBaseActivity.mCurrPage != viewPagerFtueBaseActivity.getLastPageIndex()) {
            viewPager.setCurrentItem(viewPagerFtueBaseActivity.mCurrPage + 1, true);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mProcessImagesTask != null) {
            this.mProcessImagesTask.cancel(true);
            this.mProcessImagesTask = null;
        }
        this.mImageBg.removeCallbacks(this.mSetImageRunnable);
    }

    private void checkFtueImplValid() {
        if (isParallaxMode() && getBackgroundImageResIds().length <= 0) {
            throw new IllegalArgumentException("Parallax mode should have at least 1 bg image");
        } else if (!isParallaxMode() && getNumPages() != getBackgroundImageResIds().length) {
            throw new IllegalArgumentException("Fade mode should have equal number of bg images as number of pages");
        }
    }

    /* access modifiers changed from: protected */
    public int getLastPageIndex() {
        return getNumPages() - 1;
    }

    /* access modifiers changed from: protected */
    public void setupTransparentActionBar() {
        Drawable actionBarBg = getResources().getDrawable(C0880R.C0881drawable.actionbar_background);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(actionBarBg);
        actionBarBg.setAlpha(0);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @TargetApi(11)
    private void setupBackgroundImage() {
        this.mImageBg = (ImageView) findViewById(C0880R.C0882id.img_ftue_bg);
        Options opts = new Options();
        opts.inPreferredConfig = Config.RGB_565;
        this.mCurrDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getBackgroundImageResIds()[this.mCurrPage], opts));
        this.mImageBg.setImageDrawable(this.mCurrDrawable);
        if (isParallaxMode()) {
            this.mImageBg.setScaleX(PARALLAX_SCALE_FACTOR);
            this.mImageBg.setScaleY(PARALLAX_SCALE_FACTOR);
        }
    }

    private void setupLogo() {
        if (getLogoRes() > 0) {
            ImageView logoView = (ImageView) ButterKnife.findById((Activity) this, C0880R.C0882id.ftue_logo);
            logoView.setVisibility(0);
            logoView.setImageResource(getLogoRes());
        }
    }

    /* access modifiers changed from: protected */
    public int getLogoRes() {
        return 0;
    }

    /* access modifiers changed from: private */
    public void parallaxScrollBackgroundImage(int position, int positionOffsetPixels) {
        int currScrollX = (int) (((float) positionOffsetPixels) * 0.05f);
        if (currScrollX > this.mMaxPositionOffset) {
            this.mMaxPositionOffset = currScrollX;
        }
        this.mImageBg.scrollTo((this.mMaxPositionOffset * position) + currScrollX, this.mImageBg.getScrollY());
    }

    private void scaleBackgroundImagesIfNeeded(final int currIndex, final int nextIndex) {
        if (this.mProcessImagesTask != null) {
            this.mProcessImagesTask.cancel(true);
        }
        this.mProcessImagesTask = new AsyncTask<Void, Void, TransitionDrawable>() {
            /* access modifiers changed from: protected */
            public TransitionDrawable doInBackground(Void... params) {
                Bitmap nextImageBitmap;
                Options opts = new Options();
                opts.inPreferredConfig = Config.RGB_565;
                if (ViewPagerFtueBaseActivity.this.mCurrDrawable == null) {
                    ViewPagerFtueBaseActivity.this.mCurrDrawable = new BitmapDrawable(ViewPagerFtueBaseActivity.this.getResources(), BitmapFactory.decodeResource(ViewPagerFtueBaseActivity.this.getResources(), ViewPagerFtueBaseActivity.this.getBackgroundImageResIds()[currIndex], opts));
                }
                if (ViewPagerFtueBaseActivity.this.mNextDrawable != null) {
                    opts.inBitmap = ViewPagerFtueBaseActivity.this.mNextDrawable.getBitmap();
                }
                try {
                    nextImageBitmap = BitmapFactory.decodeResource(ViewPagerFtueBaseActivity.this.getResources(), ViewPagerFtueBaseActivity.this.getBackgroundImageResIds()[nextIndex], opts);
                } catch (IllegalArgumentException e) {
                    opts.inBitmap = null;
                    nextImageBitmap = BitmapFactory.decodeResource(ViewPagerFtueBaseActivity.this.getResources(), ViewPagerFtueBaseActivity.this.getBackgroundImageResIds()[nextIndex], opts);
                }
                ViewPagerFtueBaseActivity.this.mNextDrawable = new BitmapDrawable(ViewPagerFtueBaseActivity.this.getResources(), nextImageBitmap);
                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{ViewPagerFtueBaseActivity.this.mCurrDrawable, ViewPagerFtueBaseActivity.this.mNextDrawable});
                BitmapDrawable temp = ViewPagerFtueBaseActivity.this.mCurrDrawable;
                ViewPagerFtueBaseActivity.this.mCurrDrawable = ViewPagerFtueBaseActivity.this.mNextDrawable;
                ViewPagerFtueBaseActivity.this.mNextDrawable = temp;
                return transitionDrawable;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(TransitionDrawable result) {
                if (ViewPagerFtueBaseActivity.this.mCurrPage == nextIndex) {
                    ViewPagerFtueBaseActivity.this.startBackgroundImagesTransition(result);
                }
            }
        };
        this.mProcessImagesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void startBackgroundImagesTransition(TransitionDrawable transitionDrawable) {
        this.mImageBg.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(600);
        this.mImageBg.removeCallbacks(this.mSetImageRunnable);
        this.mImageBg.postDelayed(this.mSetImageRunnable, 600);
    }

    private void fadeToNextBackgroundImage(int currIndex, int nextIndex) {
        scaleBackgroundImagesIfNeeded(currIndex, nextIndex);
    }

    /* access modifiers changed from: private */
    public void goToNextFtuePage(int position, DotsCounter mDots) {
        mDots.setSelectedDot(position);
        if (!isParallaxMode() && this.mCurrPage != position) {
            fadeToNextBackgroundImage(this.mCurrPage, position);
        }
        this.mCurrPage = position;
    }

    /* access modifiers changed from: protected */
    public void showSecondStickyButton(int stringRes, OnClickListener listener) {
        this.mStickyButtonDivider.setVisibility(0);
        this.mSecondStickyButton.setVisibility(0);
        this.mSecondStickyButton.setTitle(stringRes);
        this.mSecondStickyButton.setOnClickListener(listener);
    }

    private void initStickyAnimation() {
        this.mValueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(0), Integer.valueOf(0)});
        this.mValueAnimator.addUpdateListener(ViewPagerFtueBaseActivity$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initStickyAnimation$2(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity, ValueAnimator animator) {
        viewPagerFtueBaseActivity.mCurrentColor = ((Integer) animator.getAnimatedValue()).intValue();
        viewPagerFtueBaseActivity.mStickyButtonBackground.setBackgroundColor(viewPagerFtueBaseActivity.mCurrentColor);
    }

    /* access modifiers changed from: private */
    public void fadeStickyToColor(int colorResId) {
        int resolvedColor = getResources().getColor(colorResId);
        if (this.mCurrentColor == -1) {
            this.mStickyButtonBackground.setBackgroundColor(resolvedColor);
            this.mCurrentColor = resolvedColor;
            return;
        }
        this.mValueAnimator.cancel();
        this.mValueAnimator.setIntValues(new int[]{this.mCurrentColor, resolvedColor});
        this.mValueAnimator.start();
    }

    /* access modifiers changed from: protected */
    public void darkenFtuePics(boolean darken) {
        ViewUtils.setVisibleIf(findViewById(C0880R.C0882id.darken_ftue_pic), darken);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getToolbar() {
        return this.mToolbar;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_CURRENT_PAGE, this.mCurrPage);
    }
}
