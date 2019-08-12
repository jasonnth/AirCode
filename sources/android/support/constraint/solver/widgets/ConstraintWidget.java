package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.ConnectionType;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import java.util.ArrayList;

public class ConstraintWidget {
    public static float DEFAULT_BIAS = 0.5f;
    protected ArrayList<ConstraintAnchor> mAnchors = new ArrayList<>();
    ConstraintAnchor mBaseline = new ConstraintAnchor(this, Type.BASELINE);
    int mBaselineDistance = 0;
    ConstraintAnchor mBottom = new ConstraintAnchor(this, Type.BOTTOM);
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter = new ConstraintAnchor(this, Type.CENTER);
    ConstraintAnchor mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
    ConstraintAnchor mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
    private Object mCompanionWidget;
    private int mContainerItemSkip = 0;
    private String mDebugName = null;
    protected float mDimensionRatio = 0.0f;
    protected int mDimensionRatioSide = -1;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight = 0;
    private int mDrawWidth = 0;
    private int mDrawX = 0;
    private int mDrawY = 0;
    int mHeight = 0;
    float mHorizontalBiasPercent = DEFAULT_BIAS;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle = 0;
    DimensionBehaviour mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
    ConstraintWidget mHorizontalNextWidget = null;
    public int mHorizontalResolution = -1;
    float mHorizontalWeight = 0.0f;
    boolean mHorizontalWrapVisited;
    ConstraintAnchor mLeft = new ConstraintAnchor(this, Type.LEFT);
    boolean mLeftHasCentered;
    int mMatchConstraintDefaultHeight = 0;
    int mMatchConstraintDefaultWidth = 0;
    int mMatchConstraintMaxHeight = 0;
    int mMatchConstraintMaxWidth = 0;
    int mMatchConstraintMinHeight = 0;
    int mMatchConstraintMinWidth = 0;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX = 0;
    protected int mOffsetY = 0;
    ConstraintWidget mParent = null;
    ConstraintAnchor mRight = new ConstraintAnchor(this, Type.RIGHT);
    boolean mRightHasCentered;
    private int mSolverBottom = 0;
    private int mSolverLeft = 0;
    private int mSolverRight = 0;
    private int mSolverTop = 0;
    ConstraintAnchor mTop = new ConstraintAnchor(this, Type.TOP);
    boolean mTopHasCentered;
    private String mType = null;
    float mVerticalBiasPercent = DEFAULT_BIAS;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle = 0;
    DimensionBehaviour mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
    ConstraintWidget mVerticalNextWidget = null;
    public int mVerticalResolution = -1;
    float mVerticalWeight = 0.0f;
    boolean mVerticalWrapVisited;
    private int mVisibility = 0;
    int mWidth = 0;
    private int mWrapHeight;
    private int mWrapWidth;

    /* renamed from: mX */
    protected int f2mX = 0;

    /* renamed from: mY */
    protected int f3mY = 0;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.f2mX = 0;
        this.f3mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
    }

    public ConstraintWidget() {
        addAnchors();
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget widget) {
        this.mParent = widget;
    }

    public void setVisibility(int visibility) {
        this.mVisibility = visibility;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public String toString() {
        return (this.mType != null ? "type: " + this.mType + " " : "") + (this.mDebugName != null ? "id: " + this.mDebugName + " " : "") + "(" + this.f2mX + ", " + this.f3mY + ") - (" + this.mWidth + " x " + this.mHeight + ")" + " wrap: (" + this.mWrapWidth + " x " + this.mWrapHeight + ")";
    }

    public int getX() {
        return this.f2mX;
    }

    public int getY() {
        return this.f3mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int w;
        int w2 = this.mWidth;
        if (this.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
            return w2;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            w = Math.max(this.mMatchConstraintMinWidth, w2);
        } else if (this.mMatchConstraintMinWidth > 0) {
            w = this.mMatchConstraintMinWidth;
            this.mWidth = w;
        } else {
            w = 0;
        }
        if (this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= w) {
            return w;
        }
        return this.mMatchConstraintMaxWidth;
    }

    public int getOptimizerWrapHeight() {
        int h;
        int h2 = this.mHeight;
        if (this.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
            return h2;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            h = Math.max(this.mMatchConstraintMinHeight, h2);
        } else if (this.mMatchConstraintMinHeight > 0) {
            h = this.mMatchConstraintMinHeight;
            this.mHeight = h;
        } else {
            h = 0;
        }
        if (this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= h) {
            return h;
        }
        return this.mMatchConstraintMaxHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.f2mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.f3mY + this.mOffsetY;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int x) {
        this.f2mX = x;
    }

    public void setY(int y) {
        this.f3mY = y;
    }

    public void setOrigin(int x, int y) {
        this.f2mX = x;
        this.f3mY = y;
    }

    public void setOffset(int x, int y) {
        this.mOffsetX = x;
        this.mOffsetY = y;
    }

    public void updateDrawPosition() {
        int left = this.f2mX;
        int top = this.f3mY;
        int right = this.f2mX + this.mWidth;
        int bottom = this.f3mY + this.mHeight;
        this.mDrawX = left;
        this.mDrawY = top;
        this.mDrawWidth = right - left;
        this.mDrawHeight = bottom - top;
    }

    public void setWidth(int w) {
        this.mWidth = w;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int h) {
        this.mHeight = h;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int horizontalMatchStyle, int min, int max) {
        this.mMatchConstraintDefaultWidth = horizontalMatchStyle;
        this.mMatchConstraintMinWidth = min;
        this.mMatchConstraintMaxWidth = max;
    }

    public void setVerticalMatchStyle(int verticalMatchStyle, int min, int max) {
        this.mMatchConstraintDefaultHeight = verticalMatchStyle;
        this.mMatchConstraintMinHeight = min;
        this.mMatchConstraintMaxHeight = max;
    }

    public void setDimensionRatio(String ratio) {
        int commaIndex;
        if (ratio == null || ratio.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int dimensionRatioSide = -1;
        float dimensionRatio = 0.0f;
        int len = ratio.length();
        int commaIndex2 = ratio.indexOf(44);
        if (commaIndex2 <= 0 || commaIndex2 >= len - 1) {
            commaIndex = 0;
        } else {
            String dimension = ratio.substring(0, commaIndex2);
            if (dimension.equalsIgnoreCase("W")) {
                dimensionRatioSide = 0;
            } else if (dimension.equalsIgnoreCase("H")) {
                dimensionRatioSide = 1;
            }
            commaIndex = commaIndex2 + 1;
        }
        int colonIndex = ratio.indexOf(58);
        if (colonIndex < 0 || colonIndex >= len - 1) {
            String r = ratio.substring(commaIndex);
            if (r.length() > 0) {
                try {
                    dimensionRatio = Float.parseFloat(r);
                } catch (NumberFormatException e) {
                }
            }
        } else {
            String nominator = ratio.substring(commaIndex, colonIndex);
            String denominator = ratio.substring(colonIndex + 1);
            if (nominator.length() > 0 && denominator.length() > 0) {
                try {
                    float nominatorValue = Float.parseFloat(nominator);
                    float denominatorValue = Float.parseFloat(denominator);
                    if (nominatorValue > 0.0f && denominatorValue > 0.0f) {
                        dimensionRatio = dimensionRatioSide == 1 ? Math.abs(denominatorValue / nominatorValue) : Math.abs(nominatorValue / denominatorValue);
                    }
                } catch (NumberFormatException e2) {
                }
            }
        }
        if (dimensionRatio > 0.0f) {
            this.mDimensionRatio = dimensionRatio;
            this.mDimensionRatioSide = dimensionRatioSide;
        }
    }

    public void setHorizontalBiasPercent(float horizontalBiasPercent) {
        this.mHorizontalBiasPercent = horizontalBiasPercent;
    }

    public void setVerticalBiasPercent(float verticalBiasPercent) {
        this.mVerticalBiasPercent = verticalBiasPercent;
    }

    public void setMinWidth(int w) {
        if (w < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = w;
        }
    }

    public void setMinHeight(int h) {
        if (h < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = h;
        }
    }

    public void setWrapWidth(int w) {
        this.mWrapWidth = w;
    }

    public void setWrapHeight(int h) {
        this.mWrapHeight = h;
    }

    public void setFrame(int left, int top, int right, int bottom) {
        int w = right - left;
        int h = bottom - top;
        this.f2mX = left;
        this.f3mY = top;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.FIXED && w < this.mWidth) {
            w = this.mWidth;
        }
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.FIXED && h < this.mHeight) {
            h = this.mHeight;
        }
        this.mWidth = w;
        this.mHeight = h;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int left, int right) {
        this.f2mX = left;
        this.mWidth = right - left;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int top, int bottom) {
        this.f3mY = top;
        this.mHeight = bottom - top;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int baseline) {
        this.mBaselineDistance = baseline;
    }

    public void setCompanionWidget(Object companion) {
        this.mCompanionWidget = companion;
    }

    public void setHorizontalWeight(float horizontalWeight) {
        this.mHorizontalWeight = horizontalWeight;
    }

    public void setVerticalWeight(float verticalWeight) {
        this.mVerticalWeight = verticalWeight;
    }

    public void setHorizontalChainStyle(int horizontalChainStyle) {
        this.mHorizontalChainStyle = horizontalChainStyle;
    }

    public void setVerticalChainStyle(int verticalChainStyle) {
        this.mVerticalChainStyle = verticalChainStyle;
    }

    public void immediateConnect(Type startType, ConstraintWidget target, Type endType, int margin, int goneMargin) {
        getAnchor(startType).connect(target.getAnchor(endType), margin, goneMargin, Strength.STRONG, 0, true);
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int mAnchorsSize = this.mAnchors.size();
            for (int i = 0; i < mAnchorsSize; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(Type anchorType) {
        switch (anchorType) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case CENTER:
                return this.mCenter;
            default:
                return null;
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mHorizontalDimensionBehaviour;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mVerticalDimensionBehaviour;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mHorizontalDimensionBehaviour = behaviour;
        if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mVerticalDimensionBehaviour = behaviour;
        if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public void addToSolver(LinearSystem system, int group) {
        SolverVariable left = null;
        SolverVariable right = null;
        SolverVariable top = null;
        SolverVariable bottom = null;
        SolverVariable baseline = null;
        if (group == Integer.MAX_VALUE || this.mLeft.mGroup == group) {
            left = system.createObjectVariable(this.mLeft);
        }
        if (group == Integer.MAX_VALUE || this.mRight.mGroup == group) {
            right = system.createObjectVariable(this.mRight);
        }
        if (group == Integer.MAX_VALUE || this.mTop.mGroup == group) {
            top = system.createObjectVariable(this.mTop);
        }
        if (group == Integer.MAX_VALUE || this.mBottom.mGroup == group) {
            bottom = system.createObjectVariable(this.mBottom);
        }
        if (group == Integer.MAX_VALUE || this.mBaseline.mGroup == group) {
            baseline = system.createObjectVariable(this.mBaseline);
        }
        boolean inHorizontalChain = false;
        boolean inVerticalChain = false;
        if (this.mParent != null) {
            if ((this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft) || (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                inHorizontalChain = true;
            }
            if ((this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop) || (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                inVerticalChain = true;
            }
            if (this.mParent.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT && !inHorizontalChain) {
                if (this.mLeft.mTarget == null || this.mLeft.mTarget.mOwner != this.mParent) {
                    SolverVariable parentLeft = system.createObjectVariable(this.mParent.mLeft);
                    ArrayRow row = system.createRow();
                    row.createRowGreaterThan(left, parentLeft, system.createSlackVariable(), 0);
                    system.addConstraint(row);
                } else if (this.mLeft.mTarget != null && this.mLeft.mTarget.mOwner == this.mParent) {
                    this.mLeft.setConnectionType(ConnectionType.STRICT);
                }
                if (this.mRight.mTarget == null || this.mRight.mTarget.mOwner != this.mParent) {
                    SolverVariable parentRight = system.createObjectVariable(this.mParent.mRight);
                    ArrayRow row2 = system.createRow();
                    row2.createRowGreaterThan(parentRight, right, system.createSlackVariable(), 0);
                    system.addConstraint(row2);
                } else if (this.mRight.mTarget != null && this.mRight.mTarget.mOwner == this.mParent) {
                    this.mRight.setConnectionType(ConnectionType.STRICT);
                }
            }
            if (this.mParent.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT && !inVerticalChain) {
                if (this.mTop.mTarget == null || this.mTop.mTarget.mOwner != this.mParent) {
                    SolverVariable parentTop = system.createObjectVariable(this.mParent.mTop);
                    ArrayRow row3 = system.createRow();
                    row3.createRowGreaterThan(top, parentTop, system.createSlackVariable(), 0);
                    system.addConstraint(row3);
                } else if (this.mTop.mTarget != null && this.mTop.mTarget.mOwner == this.mParent) {
                    this.mTop.setConnectionType(ConnectionType.STRICT);
                }
                if (this.mBottom.mTarget == null || this.mBottom.mTarget.mOwner != this.mParent) {
                    SolverVariable parentBottom = system.createObjectVariable(this.mParent.mBottom);
                    ArrayRow row4 = system.createRow();
                    row4.createRowGreaterThan(parentBottom, bottom, system.createSlackVariable(), 0);
                    system.addConstraint(row4);
                } else if (this.mBottom.mTarget != null && this.mBottom.mTarget.mOwner == this.mParent) {
                    this.mBottom.setConnectionType(ConnectionType.STRICT);
                }
            }
        }
        int width = this.mWidth;
        if (width < this.mMinWidth) {
            width = this.mMinWidth;
        }
        int height = this.mHeight;
        if (height < this.mMinHeight) {
            height = this.mMinHeight;
        }
        boolean horizontalDimensionFixed = this.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalDimensionFixed = this.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT;
        if (!horizontalDimensionFixed && this.mLeft != null && this.mRight != null && (this.mLeft.mTarget == null || this.mRight.mTarget == null)) {
            horizontalDimensionFixed = true;
        }
        if (!verticalDimensionFixed && this.mTop != null && this.mBottom != null && ((this.mTop.mTarget == null || this.mBottom.mTarget == null) && (this.mBaselineDistance == 0 || (this.mBaseline != null && (this.mTop.mTarget == null || this.mBaseline.mTarget == null))))) {
            verticalDimensionFixed = true;
        }
        boolean useRatio = false;
        int dimensionRatioSide = this.mDimensionRatioSide;
        float dimensionRatio = this.mDimensionRatio;
        if (this.mDimensionRatio > 0.0f && this.mVisibility != 8) {
            if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && this.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                useRatio = true;
                if (horizontalDimensionFixed && !verticalDimensionFixed) {
                    dimensionRatioSide = 0;
                } else if (!horizontalDimensionFixed && verticalDimensionFixed) {
                    dimensionRatioSide = 1;
                    if (this.mDimensionRatioSide == -1) {
                        dimensionRatio = 1.0f / dimensionRatio;
                    }
                }
            } else if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                dimensionRatioSide = 0;
                width = (int) (((float) this.mHeight) * dimensionRatio);
                horizontalDimensionFixed = true;
            } else if (this.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                dimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    dimensionRatio = 1.0f / dimensionRatio;
                }
                height = (int) (((float) this.mWidth) * dimensionRatio);
                verticalDimensionFixed = true;
            }
        }
        boolean useHorizontalRatio = useRatio && (dimensionRatioSide == 0 || dimensionRatioSide == -1);
        boolean wrapContent = this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
        if (this.mHorizontalResolution != 2 && (group == Integer.MAX_VALUE || (this.mLeft.mGroup == group && this.mRight.mGroup == group))) {
            if (!useHorizontalRatio || this.mLeft.mTarget == null || this.mRight.mTarget == null) {
                applyConstraints(system, wrapContent, horizontalDimensionFixed, this.mLeft, this.mRight, this.f2mX, this.f2mX + width, width, this.mMinWidth, this.mHorizontalBiasPercent, useHorizontalRatio, inHorizontalChain, this.mMatchConstraintDefaultWidth, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth);
            } else {
                SolverVariable begin = system.createObjectVariable(this.mLeft);
                SolverVariable end = system.createObjectVariable(this.mRight);
                SolverVariable beginTarget = system.createObjectVariable(this.mLeft.getTarget());
                SolverVariable endTarget = system.createObjectVariable(this.mRight.getTarget());
                system.addGreaterThan(begin, beginTarget, this.mLeft.getMargin(), 3);
                system.addLowerThan(end, endTarget, this.mRight.getMargin() * -1, 3);
                if (!inHorizontalChain) {
                    system.addCentering(begin, beginTarget, this.mLeft.getMargin(), this.mHorizontalBiasPercent, endTarget, end, this.mRight.getMargin(), 4);
                }
            }
        }
        if (this.mVerticalResolution != 2) {
            boolean wrapContent2 = this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
            boolean useVerticalRatio = useRatio && (dimensionRatioSide == 1 || dimensionRatioSide == -1);
            if (this.mBaselineDistance > 0) {
                ConstraintAnchor endAnchor = this.mBottom;
                if (group == Integer.MAX_VALUE || (this.mBottom.mGroup == group && this.mBaseline.mGroup == group)) {
                    system.addEquality(baseline, top, getBaselineDistance(), 5);
                }
                int originalHeight = height;
                if (this.mBaseline.mTarget != null) {
                    height = this.mBaselineDistance;
                    endAnchor = this.mBaseline;
                }
                if (group == Integer.MAX_VALUE || (this.mTop.mGroup == group && endAnchor.mGroup == group)) {
                    if (!useVerticalRatio || this.mTop.mTarget == null || this.mBottom.mTarget == null) {
                        applyConstraints(system, wrapContent2, verticalDimensionFixed, this.mTop, endAnchor, this.f3mY, this.f3mY + height, height, this.mMinHeight, this.mVerticalBiasPercent, useVerticalRatio, inVerticalChain, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                        system.addEquality(bottom, top, originalHeight, 5);
                    } else {
                        SolverVariable begin2 = system.createObjectVariable(this.mTop);
                        SolverVariable end2 = system.createObjectVariable(this.mBottom);
                        SolverVariable beginTarget2 = system.createObjectVariable(this.mTop.getTarget());
                        SolverVariable endTarget2 = system.createObjectVariable(this.mBottom.getTarget());
                        system.addGreaterThan(begin2, beginTarget2, this.mTop.getMargin(), 3);
                        system.addLowerThan(end2, endTarget2, this.mBottom.getMargin() * -1, 3);
                        if (!inVerticalChain) {
                            system.addCentering(begin2, beginTarget2, this.mTop.getMargin(), this.mVerticalBiasPercent, endTarget2, end2, this.mBottom.getMargin(), 4);
                        }
                    }
                }
            } else if (group == Integer.MAX_VALUE || (this.mTop.mGroup == group && this.mBottom.mGroup == group)) {
                if (!useVerticalRatio || this.mTop.mTarget == null || this.mBottom.mTarget == null) {
                    applyConstraints(system, wrapContent2, verticalDimensionFixed, this.mTop, this.mBottom, this.f3mY, this.f3mY + height, height, this.mMinHeight, this.mVerticalBiasPercent, useVerticalRatio, inVerticalChain, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight);
                } else {
                    SolverVariable begin3 = system.createObjectVariable(this.mTop);
                    SolverVariable end3 = system.createObjectVariable(this.mBottom);
                    SolverVariable beginTarget3 = system.createObjectVariable(this.mTop.getTarget());
                    SolverVariable endTarget3 = system.createObjectVariable(this.mBottom.getTarget());
                    system.addGreaterThan(begin3, beginTarget3, this.mTop.getMargin(), 3);
                    system.addLowerThan(end3, endTarget3, this.mBottom.getMargin() * -1, 3);
                    if (!inVerticalChain) {
                        system.addCentering(begin3, beginTarget3, this.mTop.getMargin(), this.mVerticalBiasPercent, endTarget3, end3, this.mBottom.getMargin(), 4);
                    }
                }
            }
            if (useRatio) {
                ArrayRow row5 = system.createRow();
                if (group != Integer.MAX_VALUE && (this.mLeft.mGroup != group || this.mRight.mGroup != group)) {
                    return;
                }
                if (dimensionRatioSide == 0) {
                    system.addConstraint(row5.createRowDimensionRatio(right, left, bottom, top, dimensionRatio));
                } else if (dimensionRatioSide == 1) {
                    system.addConstraint(row5.createRowDimensionRatio(bottom, top, right, left, dimensionRatio));
                } else {
                    if (this.mMatchConstraintMinWidth > 0) {
                        system.addGreaterThan(right, left, this.mMatchConstraintMinWidth, 3);
                    }
                    if (this.mMatchConstraintMinHeight > 0) {
                        system.addGreaterThan(bottom, top, this.mMatchConstraintMinHeight, 3);
                    }
                    row5.createRowDimensionRatio(right, left, bottom, top, dimensionRatio);
                    SolverVariable error1 = system.createErrorVariable();
                    SolverVariable error2 = system.createErrorVariable();
                    error1.strength = 4;
                    error2.strength = 4;
                    row5.addError(error1, error2);
                    system.addConstraint(row5);
                }
            }
        }
    }

    private void applyConstraints(LinearSystem system, boolean wrapContent, boolean dimensionFixed, ConstraintAnchor beginAnchor, ConstraintAnchor endAnchor, int beginPosition, int endPosition, int dimension, int minDimension, float bias, boolean useRatio, boolean inChain, int matchConstraintDefault, int matchMinDimension, int matchMaxDimension) {
        SolverVariable begin = system.createObjectVariable(beginAnchor);
        SolverVariable end = system.createObjectVariable(endAnchor);
        SolverVariable beginTarget = system.createObjectVariable(beginAnchor.getTarget());
        SolverVariable endTarget = system.createObjectVariable(endAnchor.getTarget());
        int beginAnchorMargin = beginAnchor.getMargin();
        int endAnchorMargin = endAnchor.getMargin();
        if (this.mVisibility == 8) {
            dimension = 0;
            dimensionFixed = true;
        }
        if (beginTarget == null && endTarget == null) {
            system.addConstraint(system.createRow().createRowEquals(begin, beginPosition));
            if (useRatio) {
                return;
            }
            if (wrapContent) {
                system.addConstraint(LinearSystem.createRowEquals(system, end, begin, minDimension, true));
            } else if (dimensionFixed) {
                system.addConstraint(LinearSystem.createRowEquals(system, end, begin, dimension, false));
            } else {
                system.addConstraint(system.createRow().createRowEquals(end, endPosition));
            }
        } else if (beginTarget != null && endTarget == null) {
            system.addConstraint(system.createRow().createRowEquals(begin, beginTarget, beginAnchorMargin));
            if (wrapContent) {
                system.addConstraint(LinearSystem.createRowEquals(system, end, begin, minDimension, true));
            } else if (useRatio) {
            } else {
                if (dimensionFixed) {
                    system.addConstraint(system.createRow().createRowEquals(end, begin, dimension));
                } else {
                    system.addConstraint(system.createRow().createRowEquals(end, endPosition));
                }
            }
        } else if (beginTarget == null && endTarget != null) {
            system.addConstraint(system.createRow().createRowEquals(end, endTarget, endAnchorMargin * -1));
            if (wrapContent) {
                system.addConstraint(LinearSystem.createRowEquals(system, end, begin, minDimension, true));
            } else if (useRatio) {
            } else {
                if (dimensionFixed) {
                    system.addConstraint(system.createRow().createRowEquals(end, begin, dimension));
                } else {
                    system.addConstraint(system.createRow().createRowEquals(begin, beginPosition));
                }
            }
        } else if (dimensionFixed) {
            if (wrapContent) {
                system.addConstraint(LinearSystem.createRowEquals(system, end, begin, minDimension, true));
            } else {
                system.addConstraint(system.createRow().createRowEquals(end, begin, dimension));
            }
            if (beginAnchor.getStrength() != endAnchor.getStrength()) {
                if (beginAnchor.getStrength() == Strength.STRONG) {
                    system.addConstraint(system.createRow().createRowEquals(begin, beginTarget, beginAnchorMargin));
                    SolverVariable slack = system.createSlackVariable();
                    ArrayRow row = system.createRow();
                    row.createRowLowerThan(end, endTarget, slack, endAnchorMargin * -1);
                    system.addConstraint(row);
                    return;
                }
                SolverVariable slack2 = system.createSlackVariable();
                ArrayRow row2 = system.createRow();
                row2.createRowGreaterThan(begin, beginTarget, slack2, beginAnchorMargin);
                system.addConstraint(row2);
                system.addConstraint(system.createRow().createRowEquals(end, endTarget, endAnchorMargin * -1));
            } else if (beginTarget == endTarget) {
                system.addConstraint(LinearSystem.createRowCentering(system, begin, beginTarget, 0, 0.5f, endTarget, end, 0, true));
            } else if (!inChain) {
                system.addConstraint(LinearSystem.createRowGreaterThan(system, begin, beginTarget, beginAnchorMargin, beginAnchor.getConnectionType() != ConnectionType.STRICT));
                system.addConstraint(LinearSystem.createRowLowerThan(system, end, endTarget, endAnchorMargin * -1, endAnchor.getConnectionType() != ConnectionType.STRICT));
                system.addConstraint(LinearSystem.createRowCentering(system, begin, beginTarget, beginAnchorMargin, bias, endTarget, end, endAnchorMargin, false));
            }
        } else if (useRatio) {
            system.addGreaterThan(begin, beginTarget, beginAnchorMargin, 3);
            system.addLowerThan(end, endTarget, endAnchorMargin * -1, 3);
            system.addConstraint(LinearSystem.createRowCentering(system, begin, beginTarget, beginAnchorMargin, bias, endTarget, end, endAnchorMargin, true));
        } else if (inChain) {
        } else {
            if (matchConstraintDefault == 1) {
                if (matchMinDimension > dimension) {
                    dimension = matchMinDimension;
                }
                if (matchMaxDimension > 0) {
                    if (matchMaxDimension < dimension) {
                        dimension = matchMaxDimension;
                    } else {
                        system.addLowerThan(end, begin, matchMaxDimension, 3);
                    }
                }
                system.addEquality(end, begin, dimension, 3);
                system.addGreaterThan(begin, beginTarget, beginAnchorMargin, 2);
                system.addLowerThan(end, endTarget, -endAnchorMargin, 2);
                system.addCentering(begin, beginTarget, beginAnchorMargin, bias, endTarget, end, endAnchorMargin, 4);
            } else if (matchMinDimension == 0 && matchMaxDimension == 0) {
                system.addConstraint(system.createRow().createRowEquals(begin, beginTarget, beginAnchorMargin));
                system.addConstraint(system.createRow().createRowEquals(end, endTarget, endAnchorMargin * -1));
            } else {
                if (matchMaxDimension > 0) {
                    system.addLowerThan(end, begin, matchMaxDimension, 3);
                }
                system.addGreaterThan(begin, beginTarget, beginAnchorMargin, 2);
                system.addLowerThan(end, endTarget, -endAnchorMargin, 2);
                system.addCentering(begin, beginTarget, beginAnchorMargin, bias, endTarget, end, endAnchorMargin, 4);
            }
        }
    }

    public void updateFromSolver(LinearSystem system, int group) {
        if (group == Integer.MAX_VALUE) {
            setFrame(system.getObjectVariableValue(this.mLeft), system.getObjectVariableValue(this.mTop), system.getObjectVariableValue(this.mRight), system.getObjectVariableValue(this.mBottom));
        } else if (group == -2) {
            setFrame(this.mSolverLeft, this.mSolverTop, this.mSolverRight, this.mSolverBottom);
        } else {
            if (this.mLeft.mGroup == group) {
                this.mSolverLeft = system.getObjectVariableValue(this.mLeft);
            }
            if (this.mTop.mGroup == group) {
                this.mSolverTop = system.getObjectVariableValue(this.mTop);
            }
            if (this.mRight.mGroup == group) {
                this.mSolverRight = system.getObjectVariableValue(this.mRight);
            }
            if (this.mBottom.mGroup == group) {
                this.mSolverBottom = system.getObjectVariableValue(this.mBottom);
            }
        }
    }
}
