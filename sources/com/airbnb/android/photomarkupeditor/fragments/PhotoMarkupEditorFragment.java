package com.airbnb.android.photomarkupeditor.fragments;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.photomarkupeditor.C0904R;
import com.airbnb.android.photomarkupeditor.ColorPickerAnimationManager;
import com.airbnb.android.photomarkupeditor.ImageAnnotationsJitneyLogger;
import com.airbnb.android.photomarkupeditor.enums.DrawingColor;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.ImageAnnotationsPageType.p016v1.C0951ImageAnnotationsPageType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.DrawOnImageView;
import com.airbnb.p027n2.primitives.DrawOnImageView.DrawOnImageViewListener;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImage.ActivityResult;
import icepick.State;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PhotoMarkupEditorFragment extends AirFragment {
    public static final String ARG_IMAGE_SOURCE = "image_source";
    public static final String ARG_IS_EDIT_MODE = "is_edit_mode";
    public static final String ARG_PAGE_TYPE = "page_type";
    private static final int ASYNC_TASK_STATUS_CROP = 2;
    private static final int ASYNC_TASK_STATUS_NONE = 0;
    private static final int ASYNC_TASK_STATUS_SAVE = 1;
    public static final String EXTRA_EDITED_IMAGE_PATH = "edited_image_path";
    private static final String PATH_DIRECTORY = "Airbnb";
    private static final int TIME_DELAY_HIDE_COLOR_PICKER_MS = 250;
    private ColorPickerAnimationManager animationManager;
    @State
    int asyncTaskStatus;
    @BindView
    View colorBabu;
    @BindView
    View colorBeach;
    @BindView
    View colorHof;
    @BindView
    FrameLayout colorPicker;
    @BindView
    View colorRausch;
    @BindView
    DrawOnImageView drawOnImageView;
    @State
    DrawingColor drawingColor;
    @BindView
    LoaderFrame fullScreenLoader;
    @State
    boolean hasEdits;
    @BindView
    ImageView iconDraw;
    @State
    String imageSource;
    /* access modifiers changed from: private */
    public Bitmap imageToSave;
    @State
    boolean isColorPickerOpen;
    @State
    boolean isCroppedOrRotated;
    private boolean isEditMode;
    /* access modifiers changed from: private */
    public boolean isHost;
    /* access modifiers changed from: private */
    public ImageAnnotationsJitneyLogger jitneyLogger;
    private final DrawOnImageViewListener listener = new DrawOnImageViewListener() {
        public void onPathsModified(int numPaths) {
            boolean z;
            boolean z2 = true;
            PhotoMarkupEditorFragment photoMarkupEditorFragment = PhotoMarkupEditorFragment.this;
            if (PhotoMarkupEditorFragment.this.isCroppedOrRotated || numPaths > 0) {
                z = true;
            } else {
                z = false;
            }
            photoMarkupEditorFragment.hasEdits = z;
            AirButton airButton = PhotoMarkupEditorFragment.this.undoButton;
            if (numPaths <= 0) {
                z2 = false;
            }
            ViewLibUtils.setVisibleIf(airButton, z2);
        }

        public void onPathDrawn() {
            PhotoMarkupEditorFragment.this.jitneyLogger.logSwipeDrawPathEvent(PhotoMarkupEditorFragment.this.isHost, PhotoMarkupEditorFragment.this.pageType);
        }
    };
    /* access modifiers changed from: private */
    public C0951ImageAnnotationsPageType pageType;
    private SaveBitmapAsyncTask saveImageTask;
    @BindView
    AirToolbar toolbar;
    @BindView
    AirButton undoButton;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AsyncTaskStatus {
    }

    private class SaveBitmapAsyncTask extends AsyncTask<Void, Void, String> {
        private SaveBitmapAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public String doInBackground(Void... voids) {
            File file = PhotoMarkupEditorFragment.this.generateNewImageFileLocation();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                PhotoMarkupEditorFragment.this.imageToSave.compress(CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return file.getPath();
            } catch (IOException e) {
                BugsnagWrapper.notify((Throwable) e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String filename) {
            if (PhotoMarkupEditorFragment.this.asyncTaskStatus == 1) {
                PhotoMarkupEditorFragment.this.returnImage(filename);
            } else if (PhotoMarkupEditorFragment.this.asyncTaskStatus == 2) {
                PhotoMarkupEditorFragment.this.launchCropActivity(filename);
            } else {
                BugsnagWrapper.throwOrNotify(new RuntimeException("Unexpected task status: " + PhotoMarkupEditorFragment.this.asyncTaskStatus));
            }
            PhotoMarkupEditorFragment.this.asyncTaskStatus = 0;
            PhotoMarkupEditorFragment.this.imageToSave = null;
        }
    }

    public static PhotoMarkupEditorFragment newInstance(String imageSource2, C0951ImageAnnotationsPageType pageType2, boolean isEditMode2) {
        return (PhotoMarkupEditorFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PhotoMarkupEditorFragment()).putString(ARG_IMAGE_SOURCE, imageSource2)).putSerializable(ARG_PAGE_TYPE, pageType2)).putBoolean(ARG_IS_EDIT_MODE, isEditMode2)).build();
    }

    public static Intent intentForMessageThread(Context context, String imageSource2) {
        return ModalActivity.intentForFragment(context, newInstance(imageSource2, C0951ImageAnnotationsPageType.MessageThread, false));
    }

    public static Intent intentForCheckInGuide(Context context, String imageSource2) {
        return ModalActivity.intentForFragment(context, newInstance(imageSource2, C0951ImageAnnotationsPageType.CheckinGuide, false));
    }

    public static Intent intentForCheckInGuideEdit(Context context, String imageSource2) {
        return ModalActivity.intentForFragment(context, newInstance(imageSource2, C0951ImageAnnotationsPageType.CheckinGuide, true));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(1);
        this.jitneyLogger = new ImageAnnotationsJitneyLogger(this.loggingContextFactory);
        this.pageType = (C0951ImageAnnotationsPageType) getArguments().getSerializable(ARG_PAGE_TYPE);
        this.isHost = this.mAccountManager.getCurrentUser().isHost();
        this.isEditMode = getArguments().getBoolean(ARG_IS_EDIT_MODE);
        if (savedInstanceState == null) {
            this.drawingColor = DrawingColor.Rausch;
            this.imageSource = getArguments().getString(ARG_IMAGE_SOURCE);
            this.asyncTaskStatus = 0;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0904R.layout.fragment_photo_markup_editor, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.animationManager = new ColorPickerAnimationManager(getContext(), this.colorPicker, this.colorHof, this.colorBeach, this.colorBabu, this.colorRausch);
        initializeColorPicker();
        setDrawingColor(DrawingColor.Rausch, this.colorRausch);
        this.drawOnImageView.setListener(this.listener);
        this.drawOnImageView.enableDrawing(true);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAirActivity().setOnBackPressedListener(PhotoMarkupEditorFragment$$Lambda$1.lambdaFactory$(this));
        getAirActivity().setOnHomePressedListener(PhotoMarkupEditorFragment$$Lambda$4.lambdaFactory$(this));
    }

    public void onDestroyView() {
        getAirActivity().setOnBackPressedListener(null);
        getAirActivity().setOnHomePressedListener(null);
        this.drawOnImageView.setListener(null);
        super.onDestroyView();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C0904R.C0907menu.photo_markup_editor, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0904R.C0906id.save) {
            if (this.isEditMode && !this.hasEdits) {
                discardChangesAndFinish();
                return true;
            } else if (this.asyncTaskStatus == 0) {
                this.asyncTaskStatus = 1;
                startBackgroundTask();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /* access modifiers changed from: private */
    public void returnImage(String editedImagePath) {
        if (editedImagePath != null) {
            Intent data = new Intent();
            data.putExtra(EXTRA_EDITED_IMAGE_PATH, editedImagePath);
            getActivity().setResult(-1, data);
            getActivity().finish();
        }
        this.jitneyLogger.logClickSaveImageEvent(this.isHost, this.pageType);
    }

    public void onResume() {
        super.onResume();
        loadImage();
        this.fullScreenLoader.setVisibility(8);
        this.fullScreenLoader.finish();
    }

    public void onStop() {
        super.onStop();
        if (this.saveImageTask != null) {
            this.saveImageTask.cancel(true);
            this.saveImageTask = null;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @OnClick
    public void onDrawIconClick() {
        if (this.isColorPickerOpen) {
            animateColorPickerClosed();
        } else {
            animateColorPickerOpen();
        }
        this.jitneyLogger.logClickDrawToolIconEvent(this.isHost, this.pageType);
    }

    @OnClick
    public void onCropIconClick() {
        if (this.asyncTaskStatus == 0) {
            this.asyncTaskStatus = 2;
            startBackgroundTask();
        }
    }

    @OnClick
    public void onUndoClick() {
        this.drawOnImageView.undoLastPath();
        this.jitneyLogger.logClickDrawToolUndoEvent(this.isHost, this.pageType);
    }

    @OnClick
    public void onSelectColorHof(View view) {
        setDrawingColor(DrawingColor.Hof, view);
        this.jitneyLogger.logClickChangeDrawColorEvent(this.isHost, this.pageType);
    }

    @OnClick
    public void onSelectColorBabu(View view) {
        setDrawingColor(DrawingColor.Babu, view);
        this.jitneyLogger.logClickChangeDrawColorEvent(this.isHost, this.pageType);
    }

    @OnClick
    public void onSelectColorBeach(View view) {
        setDrawingColor(DrawingColor.Beach, view);
        this.jitneyLogger.logClickChangeDrawColorEvent(this.isHost, this.pageType);
    }

    @OnClick
    public void onSelectColorRausch(View view) {
        setDrawingColor(DrawingColor.Rausch, view);
        this.jitneyLogger.logClickChangeDrawColorEvent(this.isHost, this.pageType);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 203) {
            ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                this.imageSource = result.getUri().toString();
                this.isCroppedOrRotated = true;
                this.jitneyLogger.logClickCropImageEvent(this.isHost, this.pageType);
                if (result.getRotation() != 0) {
                    this.jitneyLogger.logClickRotateImageEvent(this.isHost, this.pageType);
                    return;
                }
                return;
            } else if (resultCode == 204) {
                BugsnagWrapper.notify((Throwable) result.getError());
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ImageAnnotations;
    }

    private void initializeColorPicker() {
        setTint(this.colorHof.getBackground(), C0904R.color.n2_hof);
        setTint(this.colorBeach.getBackground(), C0904R.color.n2_beach);
        setTint(this.colorBabu.getBackground(), C0904R.color.n2_babu);
        setTint(this.colorRausch.getBackground(), C0904R.color.n2_rausch);
    }

    private void setTint(Drawable drawable, int colorRes) {
        Drawable drawable2 = DrawableCompat.wrap(drawable);
        int color = ContextCompat.getColor(getContext(), colorRes);
        if (VERSION.SDK_INT == 21) {
            drawable2.mutate().setColorFilter(color, Mode.SRC_IN);
        } else {
            DrawableCompat.setTint(drawable2.mutate(), color);
        }
    }

    private void resetColorPicker(DrawingColor drawingColor2) {
        View previouslySelectedColorOption = null;
        switch (drawingColor2) {
            case Babu:
                previouslySelectedColorOption = this.colorBabu;
                break;
            case Beach:
                previouslySelectedColorOption = this.colorBeach;
                break;
            case Rausch:
                previouslySelectedColorOption = this.colorRausch;
                break;
            case Hof:
                previouslySelectedColorOption = this.colorHof;
                break;
        }
        if (previouslySelectedColorOption != null) {
            setBackgroundAndTint(previouslySelectedColorOption, C0904R.C0905drawable.color_picker_circle, drawingColor2.color);
        }
    }

    private void setDrawingColor(DrawingColor drawingColor2, View view) {
        resetColorPicker(this.drawingColor);
        this.drawingColor = drawingColor2;
        this.drawOnImageView.setDrawingColor(ContextCompat.getColor(getContext(), drawingColor2.color));
        setTint(this.iconDraw.getDrawable(), drawingColor2.color);
        setBackgroundAndTint(view, C0904R.C0905drawable.color_picker_circle_with_outline, drawingColor2.color);
        this.colorPicker.postDelayed(PhotoMarkupEditorFragment$$Lambda$5.lambdaFactory$(this), 250);
    }

    private void setBackgroundAndTint(View view, int drawableRes, int colorRes) {
        view.setBackground(ContextCompat.getDrawable(getContext(), drawableRes));
        setTint(view.getBackground(), colorRes);
    }

    private void animateColorPickerOpen() {
        if (this.colorPicker != null) {
            this.colorPicker.setVisibility(0);
            this.animationManager.animateColorPickerUp();
            this.isColorPickerOpen = true;
        }
    }

    /* access modifiers changed from: private */
    public void animateColorPickerClosed() {
        this.animationManager.animateColorPickerDown();
        this.isColorPickerOpen = false;
    }

    private void loadImage() {
        Glide.with(getContext()).load(this.imageSource).asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation, boolean fromMemoryCache) {
                PhotoMarkupEditorFragment.this.drawOnImageView.setBitmap(resource);
                if (PhotoMarkupEditorFragment.this.asyncTaskStatus != 0) {
                    PhotoMarkupEditorFragment.this.startBackgroundTask();
                }
            }

            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                BugsnagWrapper.notify((Throwable) e);
            }
        });
    }

    /* access modifiers changed from: private */
    public void startBackgroundTask() {
        this.fullScreenLoader.setVisibility(0);
        this.fullScreenLoader.startAnimation();
        this.imageToSave = this.drawOnImageView.getEditedBitmap();
        this.saveImageTask = new SaveBitmapAsyncTask();
        this.saveImageTask.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void launchCropActivity(String filename) {
        Uri uri = Uri.fromFile(new File(filename));
        this.imageSource = uri.toString();
        CropImage.activity(uri).setAllowFlipping(false).start(getContext(), (Fragment) this);
        this.jitneyLogger.logClickCropRotateToolIconEvent(this.isHost, this.pageType);
    }

    /* access modifiers changed from: private */
    public File generateNewImageFileLocation() {
        File path;
        if (this.asyncTaskStatus == 1) {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            path = getContext().getCacheDir();
        }
        File path2 = new File(path, PATH_DIRECTORY);
        path2.mkdirs();
        return new File(path2, "/edited_image_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".png");
    }

    /* access modifiers changed from: private */
    public boolean onBackPressed() {
        if (!this.hasEdits) {
            return false;
        }
        new Builder(getContext(), C0904R.C0908style.Theme_Airbnb_Dialog_Babu).setTitle(C0904R.string.photo_markup_editor_unsaved_changes_dialog_title).setMessage(C0904R.string.photo_markup_editor_unsaved_changes_dialog_message).setPositiveButton(C0904R.string.photo_markup_editor_unsaved_changes_dialog_discard_button, PhotoMarkupEditorFragment$$Lambda$6.lambdaFactory$(this)).setNegativeButton(C0904R.string.photo_markup_editor_unsaved_changes_dialog_cancel_button, null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public void discardChangesAndFinish() {
        getActivity().setResult(0);
        getActivity().finish();
    }
}
