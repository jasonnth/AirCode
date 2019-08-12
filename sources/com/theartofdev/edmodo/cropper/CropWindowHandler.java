package com.theartofdev.edmodo.cropper;

import android.graphics.RectF;
import com.theartofdev.edmodo.cropper.CropImageView.CropShape;
import com.theartofdev.edmodo.cropper.CropWindowMoveHandler.Type;

final class CropWindowHandler {
    private final RectF mEdges = new RectF();
    private final RectF mGetEdges = new RectF();
    private float mMaxCropResultHeight;
    private float mMaxCropResultWidth;
    private float mMaxCropWindowHeight;
    private float mMaxCropWindowWidth;
    private float mMinCropResultHeight;
    private float mMinCropResultWidth;
    private float mMinCropWindowHeight;
    private float mMinCropWindowWidth;
    private float mScaleFactorHeight = 1.0f;
    private float mScaleFactorWidth = 1.0f;

    CropWindowHandler() {
    }

    public RectF getRect() {
        this.mGetEdges.set(this.mEdges);
        return this.mGetEdges;
    }

    public float getMinCropWidth() {
        return Math.max(this.mMinCropWindowWidth, this.mMinCropResultWidth / this.mScaleFactorWidth);
    }

    public float getMinCropHeight() {
        return Math.max(this.mMinCropWindowHeight, this.mMinCropResultHeight / this.mScaleFactorHeight);
    }

    public float getMaxCropWidth() {
        return Math.min(this.mMaxCropWindowWidth, this.mMaxCropResultWidth / this.mScaleFactorWidth);
    }

    public float getMaxCropHeight() {
        return Math.min(this.mMaxCropWindowHeight, this.mMaxCropResultHeight / this.mScaleFactorHeight);
    }

    public float getScaleFactorWidth() {
        return this.mScaleFactorWidth;
    }

    public float getScaleFactorHeight() {
        return this.mScaleFactorHeight;
    }

    public void setCropWindowLimits(float maxWidth, float maxHeight, float scaleFactorWidth, float scaleFactorHeight) {
        this.mMaxCropWindowWidth = maxWidth;
        this.mMaxCropWindowHeight = maxHeight;
        this.mScaleFactorWidth = scaleFactorWidth;
        this.mScaleFactorHeight = scaleFactorHeight;
    }

    public void setInitialAttributeValues(CropImageOptions options) {
        this.mMinCropWindowWidth = (float) options.minCropWindowWidth;
        this.mMinCropWindowHeight = (float) options.minCropWindowHeight;
        this.mMinCropResultWidth = (float) options.minCropResultWidth;
        this.mMinCropResultHeight = (float) options.minCropResultHeight;
        this.mMaxCropResultWidth = (float) options.maxCropResultWidth;
        this.mMaxCropResultHeight = (float) options.maxCropResultHeight;
    }

    public void setRect(RectF rect) {
        this.mEdges.set(rect);
    }

    public boolean showGuidelines() {
        return this.mEdges.width() >= 100.0f && this.mEdges.height() >= 100.0f;
    }

    public CropWindowMoveHandler getMoveHandler(float x, float y, float targetRadius, CropShape cropShape) {
        Type type;
        if (cropShape == CropShape.OVAL) {
            type = getOvalPressedMoveType(x, y);
        } else {
            type = getRectanglePressedMoveType(x, y, targetRadius);
        }
        if (type != null) {
            return new CropWindowMoveHandler(type, this, x, y);
        }
        return null;
    }

    private Type getRectanglePressedMoveType(float x, float y, float targetRadius) {
        if (isInCornerTargetZone(x, y, this.mEdges.left, this.mEdges.top, targetRadius)) {
            return Type.TOP_LEFT;
        }
        if (isInCornerTargetZone(x, y, this.mEdges.right, this.mEdges.top, targetRadius)) {
            return Type.TOP_RIGHT;
        }
        if (isInCornerTargetZone(x, y, this.mEdges.left, this.mEdges.bottom, targetRadius)) {
            return Type.BOTTOM_LEFT;
        }
        if (isInCornerTargetZone(x, y, this.mEdges.right, this.mEdges.bottom, targetRadius)) {
            return Type.BOTTOM_RIGHT;
        }
        if (isInCenterTargetZone(x, y, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom) && focusCenter()) {
            return Type.CENTER;
        }
        if (isInHorizontalTargetZone(x, y, this.mEdges.left, this.mEdges.right, this.mEdges.top, targetRadius)) {
            return Type.TOP;
        }
        if (isInHorizontalTargetZone(x, y, this.mEdges.left, this.mEdges.right, this.mEdges.bottom, targetRadius)) {
            return Type.BOTTOM;
        }
        if (isInVerticalTargetZone(x, y, this.mEdges.left, this.mEdges.top, this.mEdges.bottom, targetRadius)) {
            return Type.LEFT;
        }
        if (isInVerticalTargetZone(x, y, this.mEdges.right, this.mEdges.top, this.mEdges.bottom, targetRadius)) {
            return Type.RIGHT;
        }
        if (!isInCenterTargetZone(x, y, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom) || focusCenter()) {
            return null;
        }
        return Type.CENTER;
    }

    private Type getOvalPressedMoveType(float x, float y) {
        float cellLength = this.mEdges.width() / 6.0f;
        float rightCenter = this.mEdges.left + (5.0f * cellLength);
        float cellHeight = this.mEdges.height() / 6.0f;
        float topCenter = this.mEdges.top + cellHeight;
        float bottomCenter = this.mEdges.top + (5.0f * cellHeight);
        if (x < this.mEdges.left + cellLength) {
            if (y < topCenter) {
                return Type.TOP_LEFT;
            }
            if (y < bottomCenter) {
                return Type.LEFT;
            }
            return Type.BOTTOM_LEFT;
        } else if (x < rightCenter) {
            if (y < topCenter) {
                return Type.TOP;
            }
            if (y < bottomCenter) {
                return Type.CENTER;
            }
            return Type.BOTTOM;
        } else if (y < topCenter) {
            return Type.TOP_RIGHT;
        } else {
            if (y < bottomCenter) {
                return Type.RIGHT;
            }
            return Type.BOTTOM_RIGHT;
        }
    }

    private static boolean isInCornerTargetZone(float x, float y, float handleX, float handleY, float targetRadius) {
        return Math.abs(x - handleX) <= targetRadius && Math.abs(y - handleY) <= targetRadius;
    }

    private static boolean isInHorizontalTargetZone(float x, float y, float handleXStart, float handleXEnd, float handleY, float targetRadius) {
        return x > handleXStart && x < handleXEnd && Math.abs(y - handleY) <= targetRadius;
    }

    private static boolean isInVerticalTargetZone(float x, float y, float handleX, float handleYStart, float handleYEnd, float targetRadius) {
        return Math.abs(x - handleX) <= targetRadius && y > handleYStart && y < handleYEnd;
    }

    private static boolean isInCenterTargetZone(float x, float y, float left, float top, float right, float bottom) {
        return x > left && x < right && y > top && y < bottom;
    }

    private boolean focusCenter() {
        return !showGuidelines();
    }
}
