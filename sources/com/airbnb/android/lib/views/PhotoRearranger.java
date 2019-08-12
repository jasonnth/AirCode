package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.p000v4.view.GestureDetectorCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.airbnb.android.core.interfaces.Photoable;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.AirImageListener;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PhotoRearranger extends View {
    private static final float DEFAULT_SELECTION_SCALE_FACTOR = 1.2f;
    private static final int INVALID_BITMAP_INDEX = -1;
    private static final int INVALID_POINTER_ID = -1;
    private static final String KEY_ACTIVE_BITMAP_INDEX = "active_bitmap_index";
    private static final String KEY_IS_MULTI_SELECT = "is_multi_select";
    private static final String KEY_PENDING_BITMAP_COUNT = "pending_bitmap_count";
    private static final String KEY_PREVIOUS_BITMAP_INDEX = "previous_bitmap_index";
    private static final String KEY_SELECTED_PHOTOS = "selected_photos";
    private static final String KEY_SUPER = "super_saved";
    private static final float PHOTO_SIZE_INV_RATIO = 0.6666667f;
    /* access modifiers changed from: private */
    public boolean mActiveBitmapInRemovalArea;
    /* access modifiers changed from: private */
    public int mActiveBitmapIndex;
    /* access modifiers changed from: private */
    public int mActivePointerId;
    /* access modifiers changed from: private */
    public int mAnimMS;
    /* access modifiers changed from: private */
    public Bitmap mCheckBoxSelected;
    /* access modifiers changed from: private */
    public Bitmap mCheckBoxUnselected;
    /* access modifiers changed from: private */
    public int mCheckboxPadding;
    /* access modifiers changed from: private */
    public int mContentHeight;
    /* access modifiers changed from: private */
    public int mCurrentEdgeScrollSpeed;
    /* access modifiers changed from: private */
    public DragAndDropListener mDragAndDropListener;
    private int mEdgeScrollSpeed;
    private int mEdgeScrollThreshold;
    private GestureDetectorCompat mGestureDetector;
    /* access modifiers changed from: private */
    public long mHapticFeedbackLengthMS;
    /* access modifiers changed from: private */
    public Paint mHighlightPaint;
    /* access modifiers changed from: private */
    public boolean mInMultiSelectMode;
    private HashSet<Long> mKnownIds;
    /* access modifiers changed from: private */
    public int mLastMoveX;
    /* access modifiers changed from: private */
    public int mLastMoveY;
    private int mLeadPhotoHeight;
    private int mLeadPhotoWidth;
    /* access modifiers changed from: private */
    public MultiSelectListener mMultiSelectListener;
    private int mNumPhotosPerRow;
    /* access modifiers changed from: private */
    public Paint mPaint;
    private LinkedList<PendingBitmap> mPendingBitmaps;
    private int mPendingCount = -1;
    private int mPendingPhotoAnimDurationMS;
    private Bitmap mPendingPhotoIndicator;
    private Paint mPendingPhotoPaint;
    private int mPhotoPadding;
    /* access modifiers changed from: private */
    public ArrayList<MovingBitmap> mPhotos;
    /* access modifiers changed from: private */
    public int mPrevBitmapIndex;
    private int mRemovalAreaHeight;
    private int mRemovalAreaSlop;
    /* access modifiers changed from: private */
    public ScrollerCompat mScroller;
    private int mSecondaryPhotoHeight;
    private int mSecondaryPhotoWidth;
    private HashSet<Long> mSelectedPhotos;
    /* access modifiers changed from: private */
    public float mSelectionScaleFactor;
    /* access modifiers changed from: private */
    public SingleSelectListener mSingleSelectListener;
    /* access modifiers changed from: private */
    public Vibrator mVibrator;

    public interface DragAndDropListener {
        void onDragStart();

        void onDrop(boolean z);

        void onDroppedToRemove(long j);
    }

    private class MovingBitmap {
        private Bitmap mBitmap;
        /* access modifiers changed from: private */
        public Rect mCurrentScreenSpace;
        private boolean mIsSelected;
        private ValueInterpolator mMaxHeightInterpolator;
        private ValueInterpolator mMaxWidthInterpolator;
        private ValueInterpolator mMoveBackXInterpolator = new ValueInterpolator(0, 0, 0.0f, 0.0f, null);
        private ValueInterpolator mMoveBackYInterpolator = new ValueInterpolator(0, 0, 0.0f, 0.0f, null);
        /* access modifiers changed from: private */
        public int mOffsetX;
        /* access modifiers changed from: private */
        public int mOffsetY;
        private final Photo mPhoto;
        private final Rect mScratchRect;
        private ValueInterpolator mSelectionInterpolator = new ValueInterpolator(0, 0, 1.0f, 1.0f, null);
        private Rect mTextureSpace;

        public MovingBitmap(Rect screenSpace, Photo photo) {
            final PhotoRearranger photoRearranger = PhotoRearranger.this;
            AirImageView.getImage(PhotoRearranger.this.getContext(), photo.getLargeUrl(), new AirImageListener() {
                public void onResponse(Bitmap response, boolean isImmediate) {
                    MovingBitmap.this.setBitmap(response);
                    PhotoRearranger.this.postInvalidate();
                }

                public void onErrorResponse(Exception exception) {
                }
            });
            this.mCurrentScreenSpace = screenSpace;
            this.mScratchRect = screenSpace;
            float maxWidth = (float) (screenSpace.right - screenSpace.left);
            float maxHeight = (float) (screenSpace.bottom - screenSpace.top);
            this.mMaxWidthInterpolator = new ValueInterpolator(0, 0, maxWidth, maxWidth, null);
            this.mMaxHeightInterpolator = new ValueInterpolator(0, 0, maxHeight, maxHeight, null);
            this.mOffsetX = 0;
            this.mOffsetY = 0;
            this.mPhoto = photo;
            this.mIsSelected = false;
        }

        /* access modifiers changed from: private */
        public void setBitmap(Bitmap bitmap) {
            this.mBitmap = bitmap;
            if (this.mBitmap != null) {
                this.mTextureSpace = new Rect(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
            }
        }

        public void startScale(Rect targetScreenSpace) {
            long now = System.currentTimeMillis();
            this.mMaxWidthInterpolator = new ValueInterpolator(now, ((long) PhotoRearranger.this.mAnimMS) + now, this.mMaxWidthInterpolator.getValue(now), (float) (targetScreenSpace.right - targetScreenSpace.left), new DecelerateInterpolator());
            long j = now;
            this.mMaxHeightInterpolator = new ValueInterpolator(j, now + ((long) PhotoRearranger.this.mAnimMS), this.mMaxHeightInterpolator.getValue(now), (float) (targetScreenSpace.bottom - targetScreenSpace.top), new DecelerateInterpolator());
        }

        public void startSelection() {
            long now = System.currentTimeMillis();
            this.mSelectionInterpolator = new ValueInterpolator(now, ((long) PhotoRearranger.this.mAnimMS) + now, this.mSelectionInterpolator.getValue(now), PhotoRearranger.this.mSelectionScaleFactor, new DecelerateInterpolator());
        }

        public void endSelection(Rect targetScreenSpace) {
            long now = System.currentTimeMillis();
            float current = this.mSelectionInterpolator.getValue(now);
            float selection = this.mSelectionInterpolator.getValue(now);
            float width = this.mMaxWidthInterpolator.getValue(now);
            float height = this.mMaxHeightInterpolator.getValue(now);
            if (Math.abs(1.0f - selection) > Float.MIN_VALUE) {
                float s = 0.5f * (selection - 1.0f);
                this.mScratchRect.inset((int) (s * width), (int) (s * height));
            }
            float currentScale = Math.min(((float) (this.mCurrentScreenSpace.right - this.mCurrentScreenSpace.left)) / width, ((float) (this.mCurrentScreenSpace.bottom - this.mCurrentScreenSpace.top)) / height);
            if (Math.abs(1.0f - currentScale) > Float.MIN_VALUE) {
                float s2 = 0.5f * (1.0f - currentScale);
                this.mScratchRect.inset((int) (s2 * width), (int) (s2 * height));
            }
            this.mOffsetX = this.mScratchRect.left - targetScreenSpace.left;
            this.mOffsetY = this.mScratchRect.top - targetScreenSpace.top;
            this.mSelectionInterpolator = new ValueInterpolator(now, ((long) PhotoRearranger.this.mAnimMS) + now, current, 1.0f, new DecelerateInterpolator());
            this.mMoveBackXInterpolator = new ValueInterpolator(now, now + ((long) PhotoRearranger.this.mAnimMS), (float) this.mOffsetX, 0.0f, new DecelerateInterpolator());
            this.mMoveBackYInterpolator = new ValueInterpolator(now, now + ((long) PhotoRearranger.this.mAnimMS), (float) this.mOffsetY, 0.0f, new DecelerateInterpolator());
            this.mOffsetX = 0;
            this.mOffsetY = 0;
        }

        public void moveTo(Rect screenSpace) {
            long now = System.currentTimeMillis();
            this.mMoveBackXInterpolator = new ValueInterpolator(now, ((long) PhotoRearranger.this.mAnimMS) + now, (float) (this.mCurrentScreenSpace.left - screenSpace.left), 0.0f, new DecelerateInterpolator());
            long j = now;
            this.mMoveBackYInterpolator = new ValueInterpolator(j, now + ((long) PhotoRearranger.this.mAnimMS), (float) (this.mCurrentScreenSpace.top - screenSpace.top), 0.0f, new DecelerateInterpolator());
            this.mCurrentScreenSpace = screenSpace;
        }

        public void setScreenSpace(Rect screenSpace) {
            long now = System.currentTimeMillis();
            this.mCurrentScreenSpace = screenSpace;
            this.mMoveBackXInterpolator = new ValueInterpolator(now, ((long) PhotoRearranger.this.mAnimMS) + now, (float) this.mOffsetX, 0.0f, new DecelerateInterpolator());
            this.mMoveBackYInterpolator = new ValueInterpolator(now, ((long) PhotoRearranger.this.mAnimMS) + now, (float) this.mOffsetY, 0.0f, new DecelerateInterpolator());
        }

        public void addDragDelta(int deltaX, int deltaY) {
            this.mOffsetX += deltaX;
            this.mOffsetY += deltaY;
        }

        public boolean draw(Canvas canvas, boolean highlight) {
            if (this.mBitmap == null) {
                return false;
            }
            long now = System.currentTimeMillis();
            boolean isAnimating = false;
            int maxWidth = this.mCurrentScreenSpace.right - this.mCurrentScreenSpace.left;
            int maxHeight = this.mCurrentScreenSpace.bottom - this.mCurrentScreenSpace.top;
            clone(this.mCurrentScreenSpace, this.mScratchRect);
            float currentWidth = this.mMaxWidthInterpolator.getValue(now);
            float currentHeight = this.mMaxHeightInterpolator.getValue(now);
            float currentScale = Math.min(((float) maxWidth) / currentWidth, ((float) maxHeight) / currentHeight);
            if (Math.abs(1.0f - currentScale) > Float.MIN_VALUE) {
                float s = 0.5f * (1.0f - currentScale);
                this.mScratchRect.inset((int) ((-s) * currentWidth), (int) ((-s) * currentHeight));
                isAnimating = true;
            }
            float scale = this.mSelectionInterpolator.getValue(now);
            if (Math.abs(1.0f - scale) > Float.MIN_VALUE) {
                float s2 = 0.5f * (scale - 1.0f);
                this.mScratchRect.inset((int) ((-s2) * ((float) maxWidth)), (int) ((-s2) * ((float) maxHeight)));
                isAnimating = true;
            }
            this.mScratchRect.offset(this.mOffsetX, this.mOffsetY);
            int moveBackX = (int) this.mMoveBackXInterpolator.getValue(now);
            int moveBackY = (int) this.mMoveBackYInterpolator.getValue(now);
            if (Math.abs(moveBackX) > 0 || Math.abs(moveBackY) > 0) {
                this.mScratchRect.offset(moveBackX, moveBackY);
                isAnimating = true;
            }
            canvas.drawBitmap(this.mBitmap, this.mTextureSpace, this.mScratchRect, PhotoRearranger.this.mPaint);
            if (PhotoRearranger.this.mInMultiSelectMode) {
                if (PhotoRearranger.this.mCheckBoxUnselected == null || PhotoRearranger.this.mCheckBoxSelected == null) {
                    isAnimating = true;
                } else if (this.mIsSelected) {
                    canvas.drawBitmap(PhotoRearranger.this.mCheckBoxSelected, (float) ((this.mScratchRect.right - PhotoRearranger.this.mCheckBoxSelected.getWidth()) - PhotoRearranger.this.mCheckboxPadding), (float) (this.mScratchRect.top + PhotoRearranger.this.mCheckboxPadding), PhotoRearranger.this.mPaint);
                } else {
                    canvas.drawBitmap(PhotoRearranger.this.mCheckBoxUnselected, (float) ((this.mScratchRect.right - PhotoRearranger.this.mCheckBoxUnselected.getWidth()) - PhotoRearranger.this.mCheckboxPadding), (float) (this.mScratchRect.top + PhotoRearranger.this.mCheckboxPadding), PhotoRearranger.this.mPaint);
                }
            }
            if (!highlight) {
                return isAnimating;
            }
            canvas.drawRect(this.mScratchRect, PhotoRearranger.this.mHighlightPaint);
            return isAnimating;
        }

        public void toggleSelection() {
            this.mIsSelected = !this.mIsSelected;
        }

        public void clearSelection() {
            this.mIsSelected = false;
        }

        public boolean isSelected() {
            return this.mIsSelected;
        }

        public long getId() {
            return this.mPhoto.getId();
        }

        public Photo getPhoto() {
            return this.mPhoto;
        }

        private void clone(Rect src, Rect dst) {
            dst.left = src.left;
            dst.top = src.top;
            dst.right = src.right;
            dst.bottom = src.bottom;
        }
    }

    public interface MultiSelectListener {
        void onDeselect();

        void onSelect();
    }

    private static class PendingBitmap {
        public final Point mCenter;
        public final Point mOffset;
        public final Rect mScreenSpace;

        public PendingBitmap(Rect screenSpace, Point center, Point offset) {
            this.mScreenSpace = screenSpace;
            this.mCenter = center;
            this.mOffset = offset;
        }
    }

    public interface SingleSelectListener {
        void onSelect(int i);
    }

    private static class ValueInterpolator {
        private final long mEndTime;
        private final float mEndValue;
        private final Interpolator mInterpolator;
        private final long mStartTime;
        private final float mStartValue;

        public ValueInterpolator(long startTime, long endTime, float startValue, float endValue, Interpolator interpolator) {
            this.mStartTime = startTime;
            this.mEndTime = endTime;
            this.mStartValue = startValue;
            this.mEndValue = endValue;
            if (interpolator == null) {
                this.mInterpolator = new LinearInterpolator();
            } else {
                this.mInterpolator = interpolator;
            }
        }

        public float getValue(long time) {
            if (time <= this.mStartTime) {
                return this.mStartValue;
            }
            if (time >= this.mEndTime) {
                return this.mEndValue;
            }
            float interpolatedPercent = this.mInterpolator.getInterpolation(((float) (time - this.mStartTime)) / ((float) (this.mEndTime - this.mStartTime)));
            return (this.mEndValue * interpolatedPercent) + ((1.0f - interpolatedPercent) * this.mStartValue);
        }
    }

    public PhotoRearranger(Context context) {
        super(context);
        init(context, null);
    }

    public PhotoRearranger(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.PhotoRearranger);
        init(context, a);
        a.recycle();
    }

    public PhotoRearranger(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.PhotoRearranger);
        init(context, a);
        a.recycle();
    }

    private void init(Context context, TypedArray a) {
        int pendingPhotoResId;
        int checkboxUnselectedResId;
        int checkboxSelectedResId;
        this.mPhotos = new ArrayList<>();
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Style.FILL);
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
        this.mScroller = ScrollerCompat.create(getContext());
        this.mGestureDetector = new GestureDetectorCompat(context, new SimpleOnGestureListener() {
            public boolean onDown(MotionEvent e) {
                if (PhotoRearranger.this.mActivePointerId == -1) {
                    PhotoRearranger.this.mLastMoveX = (int) e.getX();
                    PhotoRearranger.this.mLastMoveY = (int) e.getY();
                }
                return true;
            }

            public boolean onSingleTapUp(MotionEvent e) {
                int index = PhotoRearranger.this.findIndexForScreenPos((int) e.getX(), (int) e.getY(), true);
                if (PhotoRearranger.this.mInMultiSelectMode) {
                    if (index >= 0 && index < PhotoRearranger.this.mPhotos.size()) {
                        ((MovingBitmap) PhotoRearranger.this.mPhotos.get(index)).toggleSelection();
                        if (PhotoRearranger.this.mMultiSelectListener != null) {
                            if (((MovingBitmap) PhotoRearranger.this.mPhotos.get(index)).isSelected()) {
                                PhotoRearranger.this.mMultiSelectListener.onSelect();
                            } else {
                                PhotoRearranger.this.mMultiSelectListener.onDeselect();
                            }
                        }
                        PhotoRearranger.this.invalidate();
                        return true;
                    }
                } else if (PhotoRearranger.this.mSingleSelectListener != null && index >= 0 && index < PhotoRearranger.this.mPhotos.size()) {
                    PhotoRearranger.this.mSingleSelectListener.onSelect(index);
                    return true;
                }
                return false;
            }

            public void onLongPress(MotionEvent e) {
                if (PhotoRearranger.this.mActivePointerId == -1 && !PhotoRearranger.this.mInMultiSelectMode) {
                    int x = (int) e.getX();
                    int y = (int) e.getY();
                    int index = PhotoRearranger.this.findIndexForScreenPos(x, y, true);
                    if (index < PhotoRearranger.this.mPhotos.size() && index != -1) {
                        PhotoRearranger.this.mActivePointerId = e.getPointerId(0);
                        PhotoRearranger.this.mActiveBitmapIndex = index;
                        PhotoRearranger.this.mPrevBitmapIndex = index;
                        ((MovingBitmap) PhotoRearranger.this.mPhotos.get(index)).startSelection();
                        if (PhotoRearranger.this.mDragAndDropListener != null) {
                            PhotoRearranger.this.mDragAndDropListener.onDragStart();
                        }
                        PhotoRearranger.this.mVibrator.vibrate(PhotoRearranger.this.mHapticFeedbackLengthMS);
                        PhotoRearranger.this.mLastMoveX = x;
                        PhotoRearranger.this.mLastMoveY = y;
                        PhotoRearranger.this.mCurrentEdgeScrollSpeed = 0;
                        PhotoRearranger.this.mActiveBitmapInRemovalArea = false;
                        PhotoRearranger.this.invalidate();
                    }
                }
            }

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (PhotoRearranger.this.mActivePointerId == -1 && PhotoRearranger.this.mContentHeight > PhotoRearranger.this.getMeasuredHeight()) {
                    PhotoRearranger.this.mScroller.fling(PhotoRearranger.this.mScroller.getCurrX(), PhotoRearranger.this.mScroller.getCurrY(), (int) velocityX, (int) velocityY, 0, 0, PhotoRearranger.this.getMeasuredHeight() - PhotoRearranger.this.mContentHeight, 0);
                    ViewCompat.postInvalidateOnAnimation(PhotoRearranger.this);
                }
                return true;
            }
        });
        this.mGestureDetector.setIsLongpressEnabled(true);
        this.mActivePointerId = -1;
        this.mActiveBitmapIndex = -1;
        this.mPrevBitmapIndex = -1;
        this.mInMultiSelectMode = false;
        this.mPendingBitmaps = new LinkedList<>();
        this.mPendingPhotoPaint = new Paint();
        this.mPendingPhotoPaint.setStyle(Style.STROKE);
        this.mPendingPhotoPaint.setStrokeWidth(0.0f);
        this.mPendingPhotoPaint.setColor(context.getResources().getColor(C0880R.color.ml_photo_pending_border));
        this.mHighlightPaint = new Paint();
        this.mHighlightPaint.setStyle(Style.FILL);
        this.mHighlightPaint.setColor(getResources().getColor(C0880R.color.ml_photo_removal_glow));
        this.mKnownIds = new HashSet<>();
        Resources res = getResources();
        if (a != null) {
            this.mNumPhotosPerRow = a.getInteger(C0880R.styleable.PhotoRearranger_photosPerRow, res.getInteger(C0880R.integer.ml_photos_per_row));
            this.mPhotoPadding = a.getDimensionPixelOffset(C0880R.styleable.PhotoRearranger_photoPadding, res.getDimensionPixelOffset(C0880R.dimen.ml_photo_padding));
            this.mHapticFeedbackLengthMS = (long) a.getInteger(C0880R.styleable.PhotoRearranger_hapticFeedbackLengthMS, res.getInteger(C0880R.integer.ml_photo_haptic_feedback_length_ms));
            this.mEdgeScrollThreshold = a.getDimensionPixelSize(C0880R.styleable.PhotoRearranger_edgeScrollingThreshold, res.getDimensionPixelSize(C0880R.dimen.ml_photo_edge_scrolling_threshold));
            this.mEdgeScrollSpeed = a.getDimensionPixelSize(C0880R.styleable.PhotoRearranger_edgeScrollingSpeed, res.getDimensionPixelSize(C0880R.dimen.ml_photo_edge_scrolling_speed));
            this.mRemovalAreaSlop = a.getDimensionPixelOffset(C0880R.styleable.PhotoRearranger_removalAreaSlop, res.getDimensionPixelOffset(C0880R.dimen.ml_photo_removal_area_slop));
            this.mPendingPhotoAnimDurationMS = a.getInteger(C0880R.styleable.PhotoRearranger_animationDurationMS, res.getInteger(C0880R.integer.ml_photo_progress_animation_duration_ms));
            this.mAnimMS = a.getInteger(C0880R.styleable.PhotoRearranger_animationDurationMS, res.getInteger(C0880R.integer.ml_photo_animation_duration_ms));
            this.mCheckboxPadding = a.getDimensionPixelOffset(C0880R.styleable.PhotoRearranger_checkboxPadding, res.getDimensionPixelOffset(C0880R.dimen.ml_photo_checkbox_padding));
            this.mSelectionScaleFactor = a.getFloat(C0880R.styleable.PhotoRearranger_selectionScaleFactor, DEFAULT_SELECTION_SCALE_FACTOR);
            pendingPhotoResId = a.getResourceId(C0880R.styleable.PhotoRearranger_progressSpinner, C0880R.C0881drawable.spinner_indeterminent);
            checkboxUnselectedResId = a.getResourceId(C0880R.styleable.PhotoRearranger_checkBoxUnselected, C0880R.C0881drawable.checkbox_unchecked_dark);
            checkboxSelectedResId = a.getResourceId(C0880R.styleable.PhotoRearranger_checkBoxSelected, C0880R.C0881drawable.checkbox_checked_dark);
        } else {
            this.mNumPhotosPerRow = res.getInteger(C0880R.integer.ml_photos_per_row);
            this.mPhotoPadding = res.getDimensionPixelOffset(C0880R.dimen.ml_photo_padding);
            this.mHapticFeedbackLengthMS = (long) res.getInteger(C0880R.integer.ml_photo_haptic_feedback_length_ms);
            this.mEdgeScrollThreshold = res.getDimensionPixelSize(C0880R.dimen.ml_photo_edge_scrolling_threshold);
            this.mEdgeScrollSpeed = res.getDimensionPixelSize(C0880R.dimen.ml_photo_edge_scrolling_speed);
            this.mRemovalAreaSlop = res.getDimensionPixelOffset(C0880R.dimen.ml_photo_removal_area_slop);
            this.mPendingPhotoAnimDurationMS = res.getInteger(C0880R.integer.ml_photo_progress_animation_duration_ms);
            this.mAnimMS = res.getInteger(C0880R.integer.ml_photo_animation_duration_ms);
            this.mCheckboxPadding = res.getDimensionPixelOffset(C0880R.dimen.ml_photo_checkbox_padding);
            this.mSelectionScaleFactor = DEFAULT_SELECTION_SCALE_FACTOR;
            pendingPhotoResId = C0880R.C0881drawable.spinner_indeterminent;
            checkboxUnselectedResId = C0880R.C0881drawable.checkbox_unchecked_dark;
            checkboxSelectedResId = C0880R.C0881drawable.checkbox_checked_dark;
        }
        this.mPendingPhotoIndicator = BitmapFactory.decodeResource(res, pendingPhotoResId);
        this.mCheckBoxUnselected = BitmapFactory.decodeResource(res, checkboxUnselectedResId);
        this.mCheckBoxSelected = BitmapFactory.decodeResource(res, checkboxSelectedResId);
    }

    public void setDragAndDropListener(DragAndDropListener listener) {
        this.mDragAndDropListener = listener;
    }

    public void setMultiSelectListener(MultiSelectListener listener) {
        this.mMultiSelectListener = listener;
    }

    public void setSingleSelectListener(SingleSelectListener listener) {
        this.mSingleSelectListener = listener;
    }

    public void setPhotoable(Photoable photoable) {
        List<Photo> photos = photoable.getPhotos();
        Collections.sort(photos);
        HashMap<Long, String> captions = new HashMap<>(photos.size());
        for (Photo photo : photos) {
            if (!this.mKnownIds.contains(Long.valueOf(photo.getId()))) {
                if (!this.mPendingBitmaps.isEmpty()) {
                    this.mPendingBitmaps.removeFirst();
                }
                this.mPhotos.add(new MovingBitmap(findScreenSpaceForIndex(this.mPhotos.size(), false), photo));
                this.mKnownIds.add(Long.valueOf(photo.getId()));
            }
            captions.put(Long.valueOf(photo.getId()), photo.getCaption());
        }
        Iterator it = this.mPhotos.iterator();
        while (it.hasNext()) {
            MovingBitmap mb = (MovingBitmap) it.next();
            mb.getPhoto().setCaption((String) captions.get(Long.valueOf(mb.getId())));
            if (this.mSelectedPhotos != null && this.mSelectedPhotos.contains(Long.valueOf(mb.getId()))) {
                mb.toggleSelection();
            }
        }
        this.mSelectedPhotos = null;
        invalidate();
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mLeadPhotoWidth = calcLeadPhotoWidth(w);
        this.mLeadPhotoHeight = calcPhotoHeight(this.mLeadPhotoWidth);
        this.mSecondaryPhotoWidth = calcSecondaryPhotoWidth(this.mLeadPhotoWidth);
        this.mSecondaryPhotoHeight = calcPhotoHeight(this.mSecondaryPhotoWidth);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        this.mContentHeight = this.mRemovalAreaHeight + getPaddingTop() + getPaddingBottom();
        int n = getLocalPhotoCount();
        if (n > 0) {
            int leadPhotoWidth = calcLeadPhotoWidth(w);
            this.mContentHeight += calcPhotoHeight(leadPhotoWidth);
            if (n > 1) {
                int numSecondaryRows = ((n - 1) + (this.mNumPhotosPerRow - 1)) / this.mNumPhotosPerRow;
                this.mContentHeight += (this.mPhotoPadding + calcPhotoHeight(calcSecondaryPhotoWidth(leadPhotoWidth))) * numSecondaryRows;
            }
        }
        if (hMode == Integer.MIN_VALUE || hMode == 1073741824) {
            setMeasuredDimension(w, h);
        } else {
            setMeasuredDimension(w, this.mContentHeight);
        }
        recalculateAllBitmapPositions();
    }

    private int calcLeadPhotoWidth(int viewWidth) {
        return viewWidth - (getPaddingLeft() + getPaddingRight());
    }

    private int calcSecondaryPhotoWidth(int leadWidth) {
        return (leadWidth - (this.mPhotoPadding * (this.mNumPhotosPerRow - 1))) / this.mNumPhotosPerRow;
    }

    private static int calcPhotoHeight(int width) {
        return (int) (((float) width) * 0.6666667f);
    }

    public void setRemovalAreaHeight(int height) {
        this.mRemovalAreaHeight = height;
        invalidate();
        requestLayout();
    }

    private synchronized void recalculateAllBitmapPositions() {
        for (int i = 0; i < this.mPhotos.size(); i++) {
            ((MovingBitmap) this.mPhotos.get(i)).setScreenSpace(findScreenSpaceForIndex(i, false));
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipRect(0, this.mActiveBitmapIndex == -1 ? this.mRemovalAreaHeight : this.mRemovalAreaHeight + this.mRemovalAreaSlop, canvas.getWidth(), canvas.getHeight());
        this.mScroller.computeScrollOffset();
        int currY = this.mScroller.getCurrY();
        canvas.translate(0.0f, (float) currY);
        if (this.mPendingCount > 0) {
            for (int i = 0; i < this.mPendingCount; i++) {
                addPendingPhoto();
            }
            this.mPendingCount = -1;
        }
        if (getLocalPhotoCount() != 0) {
            boolean isAnimating = false;
            for (int i2 = 0; i2 < this.mPhotos.size(); i2++) {
                if (i2 != this.mActiveBitmapIndex) {
                    isAnimating |= ((MovingBitmap) this.mPhotos.get(i2)).draw(canvas, false);
                }
            }
            if (!this.mPendingBitmaps.isEmpty() && this.mPendingPhotoIndicator != null) {
                isAnimating = true;
                float degrees = (360.0f * ((float) (System.currentTimeMillis() % ((long) this.mPendingPhotoAnimDurationMS)))) / ((float) this.mPendingPhotoAnimDurationMS);
                Iterator it = this.mPendingBitmaps.iterator();
                while (it.hasNext()) {
                    PendingBitmap pb = (PendingBitmap) it.next();
                    Rect rect = pb.mScreenSpace;
                    Point c = pb.mCenter;
                    Point off = pb.mOffset;
                    canvas.drawRect(rect, this.mPendingPhotoPaint);
                    canvas.save();
                    canvas.rotate(degrees, (float) c.x, (float) c.y);
                    canvas.drawBitmap(this.mPendingPhotoIndicator, (float) (c.x + off.x), (float) (c.y + off.y), this.mPendingPhotoPaint);
                    canvas.restore();
                }
            }
            if (this.mActiveBitmapIndex != -1) {
                canvas.restore();
                canvas.save();
                canvas.translate(0.0f, (float) currY);
                isAnimating |= ((MovingBitmap) this.mPhotos.get(this.mActiveBitmapIndex)).draw(canvas, this.mActiveBitmapInRemovalArea);
            }
            canvas.restore();
            if (this.mCurrentEdgeScrollSpeed != 0) {
                isAnimating = true;
                ((MovingBitmap) this.mPhotos.get(this.mActiveBitmapIndex)).addDragDelta(0, -scroll(this.mCurrentEdgeScrollSpeed));
            }
            if (isAnimating || !this.mScroller.isFinished()) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        if (event.getPointerId(index) == this.mActivePointerId) {
            if (event.getAction() == 1) {
                if (!(findIndexForScreenPos((int) event.getX(index), (int) event.getY(index), true) == -1)) {
                    Rect screenSpace = findScreenSpaceForIndex(this.mActiveBitmapIndex, false);
                    ((MovingBitmap) this.mPhotos.get(this.mActiveBitmapIndex)).setScreenSpace(screenSpace);
                    ((MovingBitmap) this.mPhotos.get(this.mActiveBitmapIndex)).endSelection(screenSpace);
                    if (this.mActiveBitmapIndex == 0 && this.mContentHeight > getMeasuredHeight()) {
                        this.mScroller.startScroll(this.mScroller.getCurrX(), this.mScroller.getCurrY(), 0, -this.mScroller.getCurrY(), 250);
                    }
                    if (this.mDragAndDropListener != null) {
                        this.mDragAndDropListener.onDrop(this.mActiveBitmapIndex != this.mPrevBitmapIndex);
                    }
                    this.mActivePointerId = -1;
                    this.mActiveBitmapIndex = -1;
                } else if (this.mDragAndDropListener != null) {
                    this.mDragAndDropListener.onDroppedToRemove(((MovingBitmap) this.mPhotos.get(this.mActiveBitmapIndex)).getId());
                }
                this.mCurrentEdgeScrollSpeed = 0;
                invalidate();
            } else if (event.getAction() == 2) {
                int x = (int) event.getX(index);
                int y = (int) event.getY(index);
                int bitmapIndex = Math.min(findIndexForScreenPos(x, y, true), this.mPhotos.size() - 1);
                if ((bitmapIndex != this.mActiveBitmapIndex || (this.mPhotos.size() == 1 && this.mActiveBitmapInRemovalArea)) && (bitmapIndex != -1 || !this.mActiveBitmapInRemovalArea)) {
                    MovingBitmap active = (MovingBitmap) this.mPhotos.remove(this.mActiveBitmapIndex);
                    if (bitmapIndex == -1) {
                        this.mPhotos.add(active);
                        this.mActiveBitmapIndex = this.mPhotos.size() - 1;
                        this.mActiveBitmapInRemovalArea = true;
                    } else {
                        this.mActiveBitmapIndex = bitmapIndex;
                        this.mPhotos.add(this.mActiveBitmapIndex, active);
                        this.mActiveBitmapInRemovalArea = false;
                    }
                    for (int i = 0; i < this.mPhotos.size(); i++) {
                        Rect screenSpace2 = findScreenSpaceForIndex(i, false);
                        ((MovingBitmap) this.mPhotos.get(i)).startScale(screenSpace2);
                        if (i != this.mActiveBitmapIndex) {
                            ((MovingBitmap) this.mPhotos.get(i)).moveTo(screenSpace2);
                        }
                    }
                    if (this.mActiveBitmapInRemovalArea) {
                        ((MovingBitmap) this.mPhotos.get(this.mActiveBitmapIndex)).startScale(findScreenSpaceForIndex(1, false));
                    }
                }
                ((MovingBitmap) this.mPhotos.get(this.mActiveBitmapIndex)).addDragDelta(x - this.mLastMoveX, y - this.mLastMoveY);
                if (y < this.mEdgeScrollThreshold && y > this.mRemovalAreaHeight) {
                    this.mCurrentEdgeScrollSpeed = this.mEdgeScrollSpeed;
                } else if (y > getRootView().getHeight() - (this.mEdgeScrollThreshold - this.mRemovalAreaHeight)) {
                    this.mCurrentEdgeScrollSpeed = -this.mEdgeScrollSpeed;
                } else {
                    this.mCurrentEdgeScrollSpeed = 0;
                }
                this.mLastMoveX = x;
                this.mLastMoveY = y;
                invalidate();
            }
        } else if (this.mActivePointerId == -1 && event.getAction() == 2) {
            int y2 = (int) event.getY();
            scroll(y2 - this.mLastMoveY);
            this.mLastMoveY = y2;
        }
        return this.mGestureDetector.onTouchEvent(event);
    }

    private int scroll(int dy) {
        if (this.mContentHeight <= getMeasuredHeight()) {
            return 0;
        }
        int currY = this.mScroller.getCurrY();
        if (currY + dy > 0) {
            dy = -currY;
        } else if (currY + dy < getMeasuredHeight() - this.mContentHeight) {
            dy = (getMeasuredHeight() - this.mContentHeight) - currY;
        }
        this.mScroller.startScroll(this.mScroller.getCurrX(), this.mScroller.getCurrY(), 0, dy, 0);
        ViewCompat.postInvalidateOnAnimation(this);
        return dy;
    }

    /* access modifiers changed from: private */
    public int findIndexForScreenPos(int x, int y, boolean considerScroll) {
        if (y < this.mRemovalAreaHeight + this.mRemovalAreaSlop) {
            return -1;
        }
        int y2 = y - this.mRemovalAreaHeight;
        if (considerScroll) {
            y2 -= this.mScroller.getCurrY();
        }
        int leadAreaHeight = getPaddingTop() + this.mLeadPhotoHeight + this.mPhotoPadding;
        if (y2 < getPaddingTop() + this.mLeadPhotoHeight + this.mPhotoPadding) {
            return 0;
        }
        return (this.mNumPhotosPerRow * ((y2 - leadAreaHeight) / (this.mSecondaryPhotoHeight + this.mPhotoPadding))) + ((int) ((((float) x) * ((float) this.mNumPhotosPerRow)) / ((float) getWidth()))) + 1;
    }

    private Rect findScreenSpaceForIndex(int index, boolean considerScroll) {
        if (index == 0) {
            int hOffset = getPaddingLeft();
            int vOffset = this.mRemovalAreaHeight + getPaddingTop();
            if (considerScroll) {
                vOffset += this.mScroller.getCurrY();
            }
            return new Rect(hOffset, vOffset, this.mLeadPhotoWidth + hOffset, this.mLeadPhotoHeight + vOffset);
        }
        int gridIndex = index - 1;
        int hOffset2 = getPaddingLeft() + ((this.mPhotoPadding + this.mSecondaryPhotoWidth) * (gridIndex % this.mNumPhotosPerRow));
        int vOffset2 = this.mRemovalAreaHeight + getPaddingTop() + this.mLeadPhotoHeight + this.mPhotoPadding + ((this.mPhotoPadding + this.mSecondaryPhotoHeight) * (gridIndex / this.mNumPhotosPerRow));
        if (considerScroll) {
            vOffset2 += this.mScroller.getCurrY();
        }
        return new Rect(hOffset2, vOffset2, this.mSecondaryPhotoWidth + hOffset2, this.mSecondaryPhotoHeight + vOffset2);
    }

    public void removeSelectedPhoto() {
        this.mPhotos.remove(this.mActiveBitmapIndex);
        this.mActivePointerId = -1;
        this.mActiveBitmapIndex = -1;
        invalidate();
        requestLayout();
    }

    public void resetSelectedPhoto() {
        MovingBitmap active = (MovingBitmap) this.mPhotos.remove(this.mActiveBitmapIndex);
        new Rect(active.mCurrentScreenSpace).offset(active.mOffsetX, active.mOffsetY);
        Rect targetRect = findScreenSpaceForIndex(this.mPrevBitmapIndex, false);
        active.setScreenSpace(targetRect);
        active.endSelection(targetRect);
        this.mPhotos.add(this.mPrevBitmapIndex, active);
        for (int i = 0; i < this.mPhotos.size(); i++) {
            Rect screenSpace = findScreenSpaceForIndex(i, false);
            ((MovingBitmap) this.mPhotos.get(i)).startScale(screenSpace);
            if (i != this.mPrevBitmapIndex) {
                ((MovingBitmap) this.mPhotos.get(i)).moveTo(screenSpace);
            }
        }
        this.mActivePointerId = -1;
        this.mActiveBitmapIndex = -1;
        invalidate();
    }

    public void startMultiSelect() {
        this.mInMultiSelectMode = true;
        invalidate();
    }

    public void endMultiSelect() {
        Iterator it = this.mPhotos.iterator();
        while (it.hasNext()) {
            ((MovingBitmap) it.next()).clearSelection();
        }
        this.mInMultiSelectMode = false;
        invalidate();
    }

    public ArrayList<Long> getAndRemoveSelectedPhotoIds() {
        ArrayList<Long> selected = getSelectedPhotoIds();
        Iterator<MovingBitmap> iter = this.mPhotos.iterator();
        while (iter.hasNext()) {
            if (((MovingBitmap) iter.next()).isSelected()) {
                iter.remove();
            }
        }
        for (int i = 0; i < this.mPhotos.size(); i++) {
            Rect rect = findScreenSpaceForIndex(i, false);
            ((MovingBitmap) this.mPhotos.get(i)).startScale(rect);
            ((MovingBitmap) this.mPhotos.get(i)).moveTo(rect);
        }
        invalidate();
        return selected;
    }

    public ArrayList<Long> getSelectedPhotoIds() {
        ArrayList<Long> selected = new ArrayList<>();
        Iterator it = this.mPhotos.iterator();
        while (it.hasNext()) {
            MovingBitmap mb = (MovingBitmap) it.next();
            if (mb.isSelected()) {
                selected.add(Long.valueOf(mb.getId()));
            }
        }
        return selected;
    }

    public ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();
        for (int i = 0; i < this.mPhotos.size(); i++) {
            Photo photo = ((MovingBitmap) this.mPhotos.get(i)).getPhoto();
            photo.setSortOrder(i + 1);
            photos.add(photo);
        }
        return photos;
    }

    public void addPendingPhoto() {
        if (this.mPendingPhotoIndicator != null) {
            Rect rect = findScreenSpaceForIndex(getLocalPhotoCount(), false);
            this.mPendingBitmaps.add(new PendingBitmap(rect, new Point(rect.left + ((rect.right - rect.left) / 2), rect.top + ((rect.bottom - rect.top) / 2)), new Point((-this.mPendingPhotoIndicator.getWidth()) / 2, (-this.mPendingPhotoIndicator.getHeight()) / 2)));
            invalidate();
            requestLayout();
        }
    }

    public void removePendingPhoto(int count) {
        for (int i = 0; i < count && !this.mPendingBitmaps.isEmpty(); i++) {
            this.mPendingBitmaps.removeLast();
        }
        invalidate();
        requestLayout();
    }

    public int getLocalPhotoCount() {
        return this.mPhotos.size() + this.mPendingBitmaps.size();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER, superState);
        bundle.putInt(KEY_ACTIVE_BITMAP_INDEX, this.mActiveBitmapIndex);
        bundle.putInt(KEY_PREVIOUS_BITMAP_INDEX, this.mPrevBitmapIndex);
        bundle.putInt(KEY_PENDING_BITMAP_COUNT, this.mPendingBitmaps.size());
        bundle.putBoolean(KEY_IS_MULTI_SELECT, this.mInMultiSelectMode);
        bundle.putSerializable(KEY_SELECTED_PHOTOS, getSelectedPhotoIds());
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER));
        if (bundle.getInt(KEY_ACTIVE_BITMAP_INDEX) != -1) {
            this.mActiveBitmapIndex = bundle.getInt(KEY_PREVIOUS_BITMAP_INDEX);
        }
        this.mPrevBitmapIndex = bundle.getInt(KEY_PREVIOUS_BITMAP_INDEX);
        this.mPendingCount = bundle.getInt(KEY_PENDING_BITMAP_COUNT);
        this.mInMultiSelectMode = bundle.getBoolean(KEY_IS_MULTI_SELECT);
        this.mSelectedPhotos = new HashSet<>((ArrayList) bundle.getSerializable(KEY_SELECTED_PHOTOS));
    }
}
