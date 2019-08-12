package com.jumio.sdk.view.fragment;

import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.jumio.commons.enums.Rotation;
import com.jumio.commons.utils.DeviceRotationManager;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.commons.view.CompatibilityLayer;
import com.jumio.commons.view.ImageSwitcherViewDrawable;
import com.jumio.commons.view.ImageSwitcherViewDrawable.OnImageSwitchedListener;
import com.jumio.core.C4404R;
import com.jumio.gui.DrawView;
import com.jumio.gui.DrawView.DrawViewInterface;
import com.jumio.gui.Images;
import com.jumio.sdk.presentation.BaseScanPresenter;
import com.jumio.sdk.presentation.BaseScanPresenter.BaseScanControl;
import com.jumio.sdk.view.fragment.IBaseFragmentCallback;
import com.jumio.sdk.view.interactors.BaseScanView;
import java.util.ArrayList;

public abstract class BaseScanFragment<Presenter extends BaseScanPresenter, FragmentCallback extends IBaseFragmentCallback> extends BaseFragment<Presenter, FragmentCallback> implements OnImageSwitchedListener, BaseScanView {
    protected MenuItem cameraMenuItem;
    protected ImageSwitcherViewDrawable cameraSwitcher;
    protected MenuItem flashMenuItem;
    protected ImageSwitcherViewDrawable flashSwitcher;
    protected Bitmap jumioBitmap;
    /* access modifiers changed from: protected */
    public RelativeLayout mFragmentRootView;
    protected ViewGroup mRootView;
    protected DrawView overlayView;
    /* access modifiers changed from: protected */
    public ImageView poweredBy;
    /* access modifiers changed from: protected */
    public DeviceRotationManager rotationManager;
    protected TextureView textureView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mRootView = container;
        this.mFragmentRootView = (RelativeLayout) inflater.inflate(C4404R.layout.fragment_scan, container, false);
        this.textureView = (TextureView) this.mFragmentRootView.findViewById(C4404R.C4406id.textureView);
        this.overlayView = (DrawView) this.mFragmentRootView.findViewById(C4404R.C4406id.overlayView);
        this.jumioBitmap = Images.getImage(getResources());
        this.poweredBy = new ImageView(container.getContext());
        this.poweredBy.setLayoutParams(new LayoutParams(-2, -2));
        this.poweredBy.setAdjustViewBounds(true);
        this.poweredBy.setImageBitmap(this.jumioBitmap);
        CompatibilityLayer.setImageViewAlpha(this.poweredBy, 0);
        this.mFragmentRootView.addView(this.poweredBy);
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(C4404R.attr.colorControlNormal, typedValue, true);
        int color = typedValue.data;
        int dp56 = ScreenUtil.dpToPx(container.getContext(), 56);
        int dp16 = ScreenUtil.dpToPx(container.getContext(), 16);
        ArrayList<Drawable> l1 = new ArrayList<>();
        l1.add(CompatibilityLayer.getDrawable(getResources(), C4404R.C4405drawable.jumio_ic_flash_on));
        l1.add(CompatibilityLayer.getDrawable(getResources(), C4404R.C4405drawable.jumio_ic_flash_off));
        ((Drawable) l1.get(0)).setColorFilter(color, Mode.SRC_ATOP);
        ((Drawable) l1.get(1)).setColorFilter(color, Mode.SRC_ATOP);
        this.flashSwitcher = new ImageSwitcherViewDrawable(container.getContext());
        this.flashSwitcher.setLayoutParams(new LinearLayoutCompat.LayoutParams(dp56, dp56));
        this.flashSwitcher.setImages(l1, dp16).setOnImageSwitchedListener(this);
        this.flashSwitcher.setVisibility(4);
        ArrayList<Drawable> l2 = new ArrayList<>();
        l2.add(CompatibilityLayer.getDrawable(getResources(), C4404R.C4405drawable.jumio_ic_camera_front));
        l2.add(CompatibilityLayer.getDrawable(getResources(), C4404R.C4405drawable.jumio_ic_camera_rear));
        ((Drawable) l2.get(0)).setColorFilter(color, Mode.SRC_ATOP);
        ((Drawable) l2.get(1)).setColorFilter(color, Mode.SRC_ATOP);
        this.cameraSwitcher = new ImageSwitcherViewDrawable(container.getContext());
        this.cameraSwitcher.setLayoutParams(new LinearLayoutCompat.LayoutParams(dp56, dp56));
        this.cameraSwitcher.setImages(l2, dp16).setOnImageSwitchedListener(this);
        this.cameraSwitcher.setVisibility(4);
        this.rotationManager = new DeviceRotationManager(getActivity(), Rotation.NATIVE);
        this.rotationManager.setAngleFromScreen();
        return this.mFragmentRootView;
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.jumioBitmap != null && !this.jumioBitmap.isRecycled()) {
            this.poweredBy.setImageBitmap(null);
            this.jumioBitmap.recycle();
            this.jumioBitmap = null;
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.overlayView.setDrawViewInterface((DrawViewInterface) getPresenter());
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        final int oldWidth = this.textureView.getWidth();
        final int oldHeight = this.textureView.getHeight();
        this.textureView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (BaseScanFragment.this.textureView.getHeight() != oldHeight && BaseScanFragment.this.textureView.getWidth() != oldWidth) {
                    BaseScanFragment.this.onLayoutRotated();
                    BaseScanFragment.this.textureView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onLayoutRotated() {
        handleBranding(CompatibilityLayer.getImageViewAlpha(this.poweredBy) != 0.0f);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.flashMenuItem = menu.add(1, 1, 1, "");
        this.flashMenuItem.setEnabled(false);
        this.flashMenuItem.setVisible(false);
        this.flashMenuItem.setShowAsAction(2);
        this.flashMenuItem.setIcon(C4404R.C4405drawable.jumio_ic_flash_off);
        this.flashMenuItem.setActionView(this.flashSwitcher);
        this.flashSwitcher.setContentDescription(getResources().getString(C4404R.string.jumio_accessibility_flash_activate));
        this.cameraMenuItem = menu.add(2, 2, 2, "");
        this.cameraMenuItem.setEnabled(false);
        this.cameraMenuItem.setVisible(false);
        this.cameraMenuItem.setShowAsAction(2);
        this.cameraSwitcher.setContentDescription(getResources().getString(C4404R.string.jumio_accessibility_camera_switch_to_front));
        this.cameraMenuItem.setIcon(C4404R.C4405drawable.jumio_ic_camera_rear);
        this.cameraMenuItem.setActionView(this.cameraSwitcher);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public TextureView getTextureView() {
        return this.textureView;
    }

    public DeviceRotationManager getRotationManager() {
        return this.rotationManager;
    }

    public void invalidateDrawView() {
        if (this.overlayView != null) {
            this.overlayView.postInvalidate();
        }
    }

    public void resetCameraControls(final boolean frontFacing, final boolean flashEnabled) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                int i;
                int i2 = 1;
                ImageSwitcherViewDrawable imageSwitcherViewDrawable = BaseScanFragment.this.cameraSwitcher;
                if (frontFacing) {
                    i = 1;
                } else {
                    i = 0;
                }
                imageSwitcherViewDrawable.switchToImageWithIndex(i);
                ImageSwitcherViewDrawable imageSwitcherViewDrawable2 = BaseScanFragment.this.flashSwitcher;
                if (!flashEnabled) {
                    i2 = 0;
                }
                imageSwitcherViewDrawable2.switchToImageWithIndex(i2);
            }
        });
    }

    public void updateCameraControls(final boolean hasMultipleCameras, final boolean hasFlash) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                BaseScanFragment.this.handleControls(hasMultipleCameras, hasFlash);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleControls(boolean hasMultipleCameras, boolean hasFlash) {
        if (this.cameraMenuItem != null) {
            this.cameraMenuItem.setEnabled(hasMultipleCameras);
            this.cameraMenuItem.setVisible(hasMultipleCameras);
        }
        if (this.flashMenuItem != null) {
            this.flashMenuItem.setEnabled(hasFlash);
            this.flashMenuItem.setVisible(hasFlash);
        }
    }

    public void updateBranding(final boolean show) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                BaseScanFragment.this.handleBranding(show);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleBranding(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Rect overlayBounds = ((BaseScanPresenter) getPresenter()).getOverlayBounds();
            if (overlayBounds == null) {
                return;
            }
            if (overlayBounds.right != 0 || overlayBounds.bottom != 0) {
                this.poweredBy.setX((float) (overlayBounds.right - this.poweredBy.getWidth()));
                this.poweredBy.setY((float) (overlayBounds.bottom + ((int) ScreenUtil.dipToPx(getActivity(), 8.0f))));
                if (CompatibilityLayer.getImageViewAlpha(this.poweredBy) == 0.0f) {
                    CompatibilityLayer.setImageViewAlpha(this.poweredBy, 1);
                    ObjectAnimator fadeIn = CompatibilityLayer.getImageViewAlphaObjectAnimator(this.poweredBy, 1, 255);
                    fadeIn.setDuration(200);
                    fadeIn.start();
                    return;
                }
                return;
            }
            return;
        }
        CompatibilityLayer.setImageViewAlpha(this.poweredBy, 0);
    }

    public void onImageSwitchStarted(ImageSwitcherViewDrawable v) {
        if (v == this.cameraSwitcher) {
            ((BaseScanPresenter) getPresenter()).control(BaseScanControl.toggleCamera);
            if (this.cameraMenuItem.getIcon() == null) {
                return;
            }
            if (this.cameraMenuItem.getIcon() == getResources().getDrawable(C4404R.C4405drawable.jumio_ic_camera_rear)) {
                this.cameraSwitcher.setContentDescription(getResources().getString(C4404R.string.jumio_accessibility_camera_switch_to_front));
            } else {
                this.cameraSwitcher.setContentDescription(getResources().getString(C4404R.string.jumio_accessibility_camera_switch_to_back));
            }
        } else if (v == this.flashSwitcher) {
            ((BaseScanPresenter) getPresenter()).control(BaseScanControl.toggleFlash);
            if (this.flashMenuItem.getIcon() == null) {
                return;
            }
            if (this.flashMenuItem.getIcon() == getResources().getDrawable(C4404R.C4405drawable.jumio_ic_flash_off)) {
                this.flashSwitcher.setContentDescription(getResources().getString(C4404R.string.jumio_accessibility_flash_activate));
            } else {
                this.flashSwitcher.setContentDescription(getResources().getString(C4404R.string.jumio_accessibility_flash_deactivate));
            }
        }
    }

    public void onImageSwitchFinished(ImageSwitcherViewDrawable v) {
    }
}
