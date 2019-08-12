package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.p000v4.view.MarginLayoutParamsCompat;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FlexboxLayout extends ViewGroup {
    private int mAlignContent;
    private int mAlignItems;
    private boolean[] mChildrenFrozen;
    private Drawable mDividerDrawableHorizontal;
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private int mFlexWrap;
    private int mJustifyContent;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    public static class LayoutParams extends MarginLayoutParams {
        public int alignSelf = -1;
        public float flexBasisPercent = -1.0f;
        public float flexGrow = 0.0f;
        public float flexShrink = 1.0f;
        public int maxHeight = 16777215;
        public int maxWidth = 16777215;
        public int minHeight;
        public int minWidth;
        public int order = 1;
        public boolean wrapBefore;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlexboxLayout_Layout);
            this.order = a.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.flexGrow = a.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.flexShrink = a.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.alignSelf = a.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.flexBasisPercent = a.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.minWidth = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.minHeight = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.maxWidth = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.maxHeight = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.wrapBefore = a.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            a.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    private static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        public int compareTo(Order another) {
            if (this.order != another.order) {
                return this.order - another.order;
            }
            return this.index - another.index;
        }

        public String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + '}';
        }
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFlexLines = new ArrayList();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlexboxLayout, defStyleAttr, 0);
        this.mFlexDirection = a.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.mFlexWrap = a.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.mJustifyContent = a.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.mAlignItems = a.getInt(R.styleable.FlexboxLayout_alignItems, 4);
        this.mAlignContent = a.getInt(R.styleable.FlexboxLayout_alignContent, 5);
        Drawable drawable = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawableHorizontal = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawableHorizontal != null) {
            setDividerDrawableHorizontal(drawableHorizontal);
        }
        Drawable drawableVertical = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawableVertical != null) {
            setDividerDrawableVertical(drawableVertical);
        }
        int dividerMode = a.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (dividerMode != 0) {
            this.mShowDividerVertical = dividerMode;
            this.mShowDividerHorizontal = dividerMode;
        }
        int dividerModeVertical = a.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (dividerModeVertical != 0) {
            this.mShowDividerVertical = dividerModeVertical;
        }
        int dividerModeHorizontal = a.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (dividerModeHorizontal != 0) {
            this.mShowDividerHorizontal = dividerModeHorizontal;
        }
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isOrderChangedFromLastMeasurement()) {
            this.mReorderedIndices = createReorderedIndices();
        }
        if (this.mChildrenFrozen == null || this.mChildrenFrozen.length < getChildCount()) {
            this.mChildrenFrozen = new boolean[getChildCount()];
        }
        switch (this.mFlexDirection) {
            case 0:
            case 1:
                measureHorizontal(widthMeasureSpec, heightMeasureSpec);
                break;
            case 2:
            case 3:
                measureVertical(widthMeasureSpec, heightMeasureSpec);
                break;
            default:
                throw new IllegalStateException("Invalid value for the flex direction is set: " + this.mFlexDirection);
        }
        Arrays.fill(this.mChildrenFrozen, false);
    }

    public View getReorderedChildAt(int index) {
        if (index < 0 || index >= this.mReorderedIndices.length) {
            return null;
        }
        return getChildAt(this.mReorderedIndices[index]);
    }

    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        this.mReorderedIndices = createReorderedIndices(child, index, params);
        super.addView(child, index, params);
    }

    private int[] createReorderedIndices(View viewBeforeAdded, int indexForViewBeforeAdded, android.view.ViewGroup.LayoutParams paramsForViewBeforeAdded) {
        int childCount = getChildCount();
        List<Order> orders = createOrders(childCount);
        Order orderForViewToBeAdded = new Order();
        if (viewBeforeAdded == null || !(paramsForViewBeforeAdded instanceof LayoutParams)) {
            orderForViewToBeAdded.order = 1;
        } else {
            orderForViewToBeAdded.order = ((LayoutParams) paramsForViewBeforeAdded).order;
        }
        if (indexForViewBeforeAdded == -1 || indexForViewBeforeAdded == childCount) {
            orderForViewToBeAdded.index = childCount;
        } else if (indexForViewBeforeAdded < getChildCount()) {
            orderForViewToBeAdded.index = indexForViewBeforeAdded;
            for (int i = indexForViewBeforeAdded; i < childCount; i++) {
                Order order = (Order) orders.get(i);
                order.index++;
            }
        } else {
            orderForViewToBeAdded.index = childCount;
        }
        orders.add(orderForViewToBeAdded);
        return sortOrdersIntoReorderedIndices(childCount + 1, orders);
    }

    private int[] createReorderedIndices() {
        int childCount = getChildCount();
        return sortOrdersIntoReorderedIndices(childCount, createOrders(childCount));
    }

    private int[] sortOrdersIntoReorderedIndices(int childCount, List<Order> orders) {
        Collections.sort(orders);
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(childCount);
        }
        this.mOrderCache.clear();
        int[] reorderedIndices = new int[childCount];
        int i = 0;
        for (Order order : orders) {
            reorderedIndices[i] = order.index;
            this.mOrderCache.append(i, order.order);
            i++;
        }
        return reorderedIndices;
    }

    private List<Order> createOrders(int childCount) {
        List<Order> orders = new ArrayList<>(childCount);
        for (int i = 0; i < childCount; i++) {
            LayoutParams params = (LayoutParams) getChildAt(i).getLayoutParams();
            Order order = new Order();
            order.order = params.order;
            order.index = i;
            orders.add(order);
        }
        return orders;
    }

    private boolean isOrderChangedFromLastMeasurement() {
        int childCount = getChildCount();
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(childCount);
        }
        if (this.mOrderCache.size() != childCount) {
            return true;
        }
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view != null && ((LayoutParams) view.getLayoutParams()).order != this.mOrderCache.get(i)) {
                return true;
            }
        }
        return false;
    }

    private void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int childState = 0;
        this.mFlexLines.clear();
        int childCount = getChildCount();
        int paddingStart = ViewCompat.getPaddingStart(this);
        int paddingEnd = ViewCompat.getPaddingEnd(this);
        int largestHeightInRow = Integer.MIN_VALUE;
        FlexLine flexLine = new FlexLine();
        int indexInFlexLine = 0;
        flexLine.mMainSize = paddingStart + paddingEnd;
        for (int i = 0; i < childCount; i++) {
            View child = getReorderedChildAt(i);
            if (child == null) {
                addFlexLineIfLastFlexItem(i, childCount, flexLine);
            } else if (child.getVisibility() == 8) {
                flexLine.mItemCount++;
                flexLine.mGoneItemCount++;
                addFlexLineIfLastFlexItem(i, childCount, flexLine);
            } else {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.alignSelf == 4) {
                    flexLine.mIndicesAlignSelfStretch.add(Integer.valueOf(i));
                }
                int childWidth = lp.width;
                if (lp.flexBasisPercent != -1.0f && widthMode == 1073741824) {
                    childWidth = Math.round(((float) widthSize) * lp.flexBasisPercent);
                }
                child.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, childWidth), getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin, lp.height));
                checkSizeConstraints(child);
                childState = ViewCompat.combineMeasuredStates(childState, ViewCompat.getMeasuredState(child));
                largestHeightInRow = Math.max(largestHeightInRow, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                if (isWrapRequired(widthMode, widthSize, flexLine.mMainSize, lp.rightMargin + child.getMeasuredWidth() + lp.leftMargin, lp, i, indexInFlexLine)) {
                    if (flexLine.getItemCountNotGone() > 0) {
                        addFlexLine(flexLine);
                    }
                    flexLine = new FlexLine();
                    flexLine.mItemCount = 1;
                    flexLine.mMainSize = paddingStart + paddingEnd;
                    largestHeightInRow = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                    indexInFlexLine = 0;
                } else {
                    flexLine.mItemCount++;
                    indexInFlexLine++;
                }
                flexLine.mMainSize += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                flexLine.mTotalFlexGrow += lp.flexGrow;
                flexLine.mTotalFlexShrink += lp.flexShrink;
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, largestHeightInRow);
                if (hasDividerBeforeChildAtAlongMainAxis(i, indexInFlexLine)) {
                    flexLine.mMainSize += this.mDividerVerticalWidth;
                    flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
                }
                if (this.mFlexWrap != 2) {
                    flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, child.getBaseline() + lp.topMargin);
                } else {
                    flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, (child.getMeasuredHeight() - child.getBaseline()) + lp.bottomMargin);
                }
                addFlexLineIfLastFlexItem(i, childCount, flexLine);
            }
        }
        determineMainSize(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec);
        if (this.mAlignItems == 3) {
            int viewIndex = 0;
            for (FlexLine flexLine2 : this.mFlexLines) {
                int largestHeightInLine = Integer.MIN_VALUE;
                for (int i2 = viewIndex; i2 < flexLine2.mItemCount + viewIndex; i2++) {
                    View child2 = getReorderedChildAt(i2);
                    LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                    if (this.mFlexWrap != 2) {
                        int marginTop = Math.max(flexLine2.mMaxBaseline - child2.getBaseline(), lp2.topMargin);
                        largestHeightInLine = Math.max(largestHeightInLine, child2.getHeight() + marginTop + lp2.bottomMargin);
                    } else {
                        int marginBottom = Math.max((flexLine2.mMaxBaseline - child2.getMeasuredHeight()) + child2.getBaseline(), lp2.bottomMargin);
                        largestHeightInLine = Math.max(largestHeightInLine, child2.getHeight() + lp2.topMargin + marginBottom);
                    }
                }
                flexLine2.mCrossSize = largestHeightInLine;
                viewIndex += flexLine2.mItemCount;
            }
        }
        determineCrossSize(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, getPaddingTop() + getPaddingBottom());
        stretchViews(this.mFlexDirection, this.mAlignItems);
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, childState);
    }

    private void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childState = 0;
        this.mFlexLines.clear();
        int childCount = getChildCount();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int largestWidthInColumn = Integer.MIN_VALUE;
        FlexLine flexLine = new FlexLine();
        flexLine.mMainSize = paddingTop + paddingBottom;
        int indexInFlexLine = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getReorderedChildAt(i);
            if (child == null) {
                addFlexLineIfLastFlexItem(i, childCount, flexLine);
            } else if (child.getVisibility() == 8) {
                flexLine.mItemCount++;
                flexLine.mGoneItemCount++;
                addFlexLineIfLastFlexItem(i, childCount, flexLine);
            } else {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.alignSelf == 4) {
                    flexLine.mIndicesAlignSelfStretch.add(Integer.valueOf(i));
                }
                int childHeight = lp.height;
                if (lp.flexBasisPercent != -1.0f && heightMode == 1073741824) {
                    childHeight = Math.round(((float) heightSize) * lp.flexBasisPercent);
                }
                child.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width), getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin, childHeight));
                checkSizeConstraints(child);
                childState = ViewCompat.combineMeasuredStates(childState, ViewCompat.getMeasuredState(child));
                largestWidthInColumn = Math.max(largestWidthInColumn, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                if (isWrapRequired(heightMode, heightSize, flexLine.mMainSize, lp.bottomMargin + child.getMeasuredHeight() + lp.topMargin, lp, i, indexInFlexLine)) {
                    if (flexLine.getItemCountNotGone() > 0) {
                        addFlexLine(flexLine);
                    }
                    flexLine = new FlexLine();
                    flexLine.mItemCount = 1;
                    flexLine.mMainSize = paddingTop + paddingBottom;
                    largestWidthInColumn = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    indexInFlexLine = 0;
                } else {
                    flexLine.mItemCount++;
                    indexInFlexLine++;
                }
                flexLine.mMainSize += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                flexLine.mTotalFlexGrow += lp.flexGrow;
                flexLine.mTotalFlexShrink += lp.flexShrink;
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, largestWidthInColumn);
                if (hasDividerBeforeChildAtAlongMainAxis(i, indexInFlexLine)) {
                    flexLine.mMainSize += this.mDividerHorizontalHeight;
                }
                addFlexLineIfLastFlexItem(i, childCount, flexLine);
            }
        }
        determineMainSize(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec);
        determineCrossSize(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, getPaddingLeft() + getPaddingRight());
        stretchViews(this.mFlexDirection, this.mAlignItems);
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, childState);
    }

    private void checkSizeConstraints(View view) {
        boolean needsMeasure = false;
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        int childWidth = view.getMeasuredWidth();
        int childHeight = view.getMeasuredHeight();
        if (view.getMeasuredWidth() < lp.minWidth) {
            needsMeasure = true;
            childWidth = lp.minWidth;
        } else if (view.getMeasuredWidth() > lp.maxWidth) {
            needsMeasure = true;
            childWidth = lp.maxWidth;
        }
        if (childHeight < lp.minHeight) {
            needsMeasure = true;
            childHeight = lp.minHeight;
        } else if (childHeight > lp.maxHeight) {
            needsMeasure = true;
            childHeight = lp.maxHeight;
        }
        if (needsMeasure) {
            view.measure(MeasureSpec.makeMeasureSpec(childWidth, 1073741824), MeasureSpec.makeMeasureSpec(childHeight, 1073741824));
        }
    }

    private void addFlexLineIfLastFlexItem(int childIndex, int childCount, FlexLine flexLine) {
        if (childIndex == childCount - 1 && flexLine.getItemCountNotGone() != 0) {
            addFlexLine(flexLine);
        }
    }

    private void addFlexLine(FlexLine flexLine) {
        if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            if ((this.mShowDividerVertical & 4) > 0) {
                flexLine.mMainSize += this.mDividerVerticalWidth;
                flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
            }
        } else if ((this.mShowDividerHorizontal & 4) > 0) {
            flexLine.mMainSize += this.mDividerHorizontalHeight;
            flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
        }
        this.mFlexLines.add(flexLine);
    }

    private void determineMainSize(int flexDirection, int widthMeasureSpec, int heightMeasureSpec) {
        int mainSize;
        int mainSize2;
        int paddingAlongMainAxis;
        switch (flexDirection) {
            case 0:
            case 1:
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                if (widthMode == 1073741824) {
                    mainSize2 = widthSize;
                } else {
                    mainSize2 = getLargestMainSize();
                }
                paddingAlongMainAxis = getPaddingLeft() + getPaddingRight();
                break;
            case 2:
            case 3:
                int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);
                if (heightMode == 1073741824) {
                    mainSize = heightSize;
                } else {
                    mainSize = getLargestMainSize();
                }
                paddingAlongMainAxis = getPaddingTop() + getPaddingBottom();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        int childIndex = 0;
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.mMainSize < mainSize2) {
                childIndex = expandFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, flexDirection, mainSize2, paddingAlongMainAxis, childIndex, false);
            } else {
                childIndex = shrinkFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, flexDirection, mainSize2, paddingAlongMainAxis, childIndex, false);
            }
        }
    }

    private int expandFlexItems(int widthMeasureSpec, int heightMeasureSpec, FlexLine flexLine, int flexDirection, int maxMainSize, int paddingAlongMainAxis, int startIndex, boolean calledRecursively) {
        int childIndex = startIndex;
        if (flexLine.mTotalFlexGrow <= 0.0f || maxMainSize < flexLine.mMainSize) {
            return childIndex + flexLine.mItemCount;
        }
        int sizeBeforeExpand = flexLine.mMainSize;
        boolean needsReexpand = false;
        float unitSpace = ((float) (maxMainSize - flexLine.mMainSize)) / flexLine.mTotalFlexGrow;
        flexLine.mMainSize = flexLine.mDividerLengthInMainSize + paddingAlongMainAxis;
        if (!calledRecursively) {
            flexLine.mCrossSize = Integer.MIN_VALUE;
        }
        int largestCrossSize = 0;
        float accumulatedRoundError = 0.0f;
        for (int i = 0; i < flexLine.mItemCount; i++) {
            View child = getReorderedChildAt(childIndex);
            if (child != null) {
                if (child.getVisibility() == 8) {
                    childIndex++;
                } else {
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (isMainAxisDirectionHorizontal(flexDirection)) {
                        if (!this.mChildrenFrozen[childIndex]) {
                            float rawCalculatedWidth = ((float) child.getMeasuredWidth()) + (lp.flexGrow * unitSpace);
                            if (i == flexLine.mItemCount - 1) {
                                rawCalculatedWidth += accumulatedRoundError;
                                accumulatedRoundError = 0.0f;
                            }
                            int newWidth = Math.round(rawCalculatedWidth);
                            if (newWidth > lp.maxWidth) {
                                needsReexpand = true;
                                newWidth = lp.maxWidth;
                                this.mChildrenFrozen[childIndex] = true;
                                flexLine.mTotalFlexGrow -= lp.flexGrow;
                            } else {
                                accumulatedRoundError += rawCalculatedWidth - ((float) newWidth);
                                if (((double) accumulatedRoundError) > 1.0d) {
                                    newWidth++;
                                    accumulatedRoundError = (float) (((double) accumulatedRoundError) - 1.0d);
                                } else if (((double) accumulatedRoundError) < -1.0d) {
                                    newWidth--;
                                    accumulatedRoundError = (float) (((double) accumulatedRoundError) + 1.0d);
                                }
                            }
                            child.measure(MeasureSpec.makeMeasureSpec(newWidth, 1073741824), getChildHeightMeasureSpec(heightMeasureSpec, lp));
                            largestCrossSize = Math.max(largestCrossSize, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                        }
                        flexLine.mMainSize += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    } else {
                        if (!this.mChildrenFrozen[childIndex]) {
                            float rawCalculatedHeight = ((float) child.getMeasuredHeight()) + (lp.flexGrow * unitSpace);
                            if (i == flexLine.mItemCount - 1) {
                                rawCalculatedHeight += accumulatedRoundError;
                                accumulatedRoundError = 0.0f;
                            }
                            int newHeight = Math.round(rawCalculatedHeight);
                            if (newHeight > lp.maxHeight) {
                                needsReexpand = true;
                                newHeight = lp.maxHeight;
                                this.mChildrenFrozen[childIndex] = true;
                                flexLine.mTotalFlexGrow -= lp.flexGrow;
                            } else {
                                accumulatedRoundError += rawCalculatedHeight - ((float) newHeight);
                                if (((double) accumulatedRoundError) > 1.0d) {
                                    newHeight++;
                                    accumulatedRoundError = (float) (((double) accumulatedRoundError) - 1.0d);
                                } else if (((double) accumulatedRoundError) < -1.0d) {
                                    newHeight--;
                                    accumulatedRoundError = (float) (((double) accumulatedRoundError) + 1.0d);
                                }
                            }
                            child.measure(getChildWidthMeasureSpec(widthMeasureSpec, lp), MeasureSpec.makeMeasureSpec(newHeight, 1073741824));
                            largestCrossSize = Math.max(largestCrossSize, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                        }
                        flexLine.mMainSize += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                    }
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, largestCrossSize);
                    childIndex++;
                }
            }
        }
        if (needsReexpand && sizeBeforeExpand != flexLine.mMainSize) {
            expandFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, flexDirection, maxMainSize, paddingAlongMainAxis, startIndex, true);
        }
        return childIndex;
    }

    private int shrinkFlexItems(int widthMeasureSpec, int heightMeasureSpec, FlexLine flexLine, int flexDirection, int maxMainSize, int paddingAlongMainAxis, int startIndex, boolean calledRecursively) {
        int childIndex = startIndex;
        int sizeBeforeShrink = flexLine.mMainSize;
        if (flexLine.mTotalFlexShrink <= 0.0f || maxMainSize > flexLine.mMainSize) {
            return childIndex + flexLine.mItemCount;
        }
        boolean needsReshrink = false;
        float unitShrink = ((float) (flexLine.mMainSize - maxMainSize)) / flexLine.mTotalFlexShrink;
        float accumulatedRoundError = 0.0f;
        flexLine.mMainSize = flexLine.mDividerLengthInMainSize + paddingAlongMainAxis;
        int largestCrossSize = 0;
        if (!calledRecursively) {
            flexLine.mCrossSize = Integer.MIN_VALUE;
        }
        for (int i = 0; i < flexLine.mItemCount; i++) {
            View child = getReorderedChildAt(childIndex);
            if (child != null) {
                if (child.getVisibility() == 8) {
                    childIndex++;
                } else {
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (isMainAxisDirectionHorizontal(flexDirection)) {
                        if (!this.mChildrenFrozen[childIndex]) {
                            float rawCalculatedWidth = ((float) child.getMeasuredWidth()) - (lp.flexShrink * unitShrink);
                            if (i == flexLine.mItemCount - 1) {
                                rawCalculatedWidth += accumulatedRoundError;
                                accumulatedRoundError = 0.0f;
                            }
                            int newWidth = Math.round(rawCalculatedWidth);
                            if (newWidth < lp.minWidth) {
                                needsReshrink = true;
                                newWidth = lp.minWidth;
                                this.mChildrenFrozen[childIndex] = true;
                                flexLine.mTotalFlexShrink -= lp.flexShrink;
                            } else {
                                accumulatedRoundError += rawCalculatedWidth - ((float) newWidth);
                                if (((double) accumulatedRoundError) > 1.0d) {
                                    newWidth++;
                                    accumulatedRoundError -= 1.0f;
                                } else if (((double) accumulatedRoundError) < -1.0d) {
                                    newWidth--;
                                    accumulatedRoundError += 1.0f;
                                }
                            }
                            child.measure(MeasureSpec.makeMeasureSpec(newWidth, 1073741824), getChildHeightMeasureSpec(heightMeasureSpec, lp));
                            largestCrossSize = Math.max(largestCrossSize, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                        }
                        flexLine.mMainSize += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    } else {
                        if (!this.mChildrenFrozen[childIndex]) {
                            float rawCalculatedHeight = ((float) child.getMeasuredHeight()) - (lp.flexShrink * unitShrink);
                            if (i == flexLine.mItemCount - 1) {
                                rawCalculatedHeight += accumulatedRoundError;
                                accumulatedRoundError = 0.0f;
                            }
                            int newHeight = Math.round(rawCalculatedHeight);
                            if (newHeight < lp.minHeight) {
                                needsReshrink = true;
                                newHeight = lp.minHeight;
                                this.mChildrenFrozen[childIndex] = true;
                                flexLine.mTotalFlexShrink -= lp.flexShrink;
                            } else {
                                accumulatedRoundError += rawCalculatedHeight - ((float) newHeight);
                                if (((double) accumulatedRoundError) > 1.0d) {
                                    newHeight++;
                                    accumulatedRoundError -= 1.0f;
                                } else if (((double) accumulatedRoundError) < -1.0d) {
                                    newHeight--;
                                    accumulatedRoundError += 1.0f;
                                }
                            }
                            child.measure(getChildWidthMeasureSpec(widthMeasureSpec, lp), MeasureSpec.makeMeasureSpec(newHeight, 1073741824));
                            largestCrossSize = Math.max(largestCrossSize, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                        }
                        flexLine.mMainSize += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                    }
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, largestCrossSize);
                    childIndex++;
                }
            }
        }
        if (needsReshrink && sizeBeforeShrink != flexLine.mMainSize) {
            shrinkFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, flexDirection, maxMainSize, paddingAlongMainAxis, startIndex, true);
        }
        return childIndex;
    }

    private int getChildWidthMeasureSpec(int widthMeasureSpec, LayoutParams lp) {
        int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width);
        int childWidth = MeasureSpec.getSize(childWidthMeasureSpec);
        if (childWidth > lp.maxWidth) {
            return MeasureSpec.makeMeasureSpec(lp.maxWidth, MeasureSpec.getMode(childWidthMeasureSpec));
        }
        if (childWidth < lp.minWidth) {
            return MeasureSpec.makeMeasureSpec(lp.minWidth, MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return childWidthMeasureSpec;
    }

    private int getChildHeightMeasureSpec(int heightMeasureSpec, LayoutParams lp) {
        int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin, lp.height);
        int childHeight = MeasureSpec.getSize(childHeightMeasureSpec);
        if (childHeight > lp.maxHeight) {
            return MeasureSpec.makeMeasureSpec(lp.maxHeight, MeasureSpec.getMode(childHeightMeasureSpec));
        }
        if (childHeight < lp.minHeight) {
            return MeasureSpec.makeMeasureSpec(lp.minHeight, MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return childHeightMeasureSpec;
    }

    private void determineCrossSize(int flexDirection, int widthMeasureSpec, int heightMeasureSpec, int paddingAlongCrossAxis) {
        int mode;
        int size;
        switch (flexDirection) {
            case 0:
            case 1:
                mode = MeasureSpec.getMode(heightMeasureSpec);
                size = MeasureSpec.getSize(heightMeasureSpec);
                break;
            case 2:
            case 3:
                mode = MeasureSpec.getMode(widthMeasureSpec);
                size = MeasureSpec.getSize(widthMeasureSpec);
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        if (mode == 1073741824) {
            int totalCrossSize = getSumOfCrossSize() + paddingAlongCrossAxis;
            if (this.mFlexLines.size() == 1) {
                ((FlexLine) this.mFlexLines.get(0)).mCrossSize = size - paddingAlongCrossAxis;
            } else if (this.mFlexLines.size() >= 2 && totalCrossSize < size) {
                switch (this.mAlignContent) {
                    case 1:
                        int spaceTop = size - totalCrossSize;
                        FlexLine dummySpaceFlexLine = new FlexLine();
                        dummySpaceFlexLine.mCrossSize = spaceTop;
                        this.mFlexLines.add(0, dummySpaceFlexLine);
                        return;
                    case 2:
                        int spaceAboveAndBottom = (size - totalCrossSize) / 2;
                        List<FlexLine> newFlexLines = new ArrayList<>();
                        FlexLine dummySpaceFlexLine2 = new FlexLine();
                        dummySpaceFlexLine2.mCrossSize = spaceAboveAndBottom;
                        int flexLineSize = this.mFlexLines.size();
                        for (int i = 0; i < flexLineSize; i++) {
                            if (i == 0) {
                                newFlexLines.add(dummySpaceFlexLine2);
                            }
                            newFlexLines.add((FlexLine) this.mFlexLines.get(i));
                            if (i == this.mFlexLines.size() - 1) {
                                newFlexLines.add(dummySpaceFlexLine2);
                            }
                        }
                        this.mFlexLines = newFlexLines;
                        return;
                    case 3:
                        float spaceBetweenFlexLine = ((float) (size - totalCrossSize)) / ((float) (this.mFlexLines.size() - 1));
                        float accumulatedError = 0.0f;
                        List<FlexLine> newFlexLines2 = new ArrayList<>();
                        int flexLineSize2 = this.mFlexLines.size();
                        for (int i2 = 0; i2 < flexLineSize2; i2++) {
                            newFlexLines2.add((FlexLine) this.mFlexLines.get(i2));
                            if (i2 != this.mFlexLines.size() - 1) {
                                FlexLine dummySpaceFlexLine3 = new FlexLine();
                                if (i2 == this.mFlexLines.size() - 2) {
                                    dummySpaceFlexLine3.mCrossSize = Math.round(spaceBetweenFlexLine + accumulatedError);
                                    accumulatedError = 0.0f;
                                } else {
                                    dummySpaceFlexLine3.mCrossSize = Math.round(spaceBetweenFlexLine);
                                }
                                accumulatedError += spaceBetweenFlexLine - ((float) dummySpaceFlexLine3.mCrossSize);
                                if (accumulatedError > 1.0f) {
                                    dummySpaceFlexLine3.mCrossSize++;
                                    accumulatedError -= 1.0f;
                                } else if (accumulatedError < -1.0f) {
                                    dummySpaceFlexLine3.mCrossSize--;
                                    accumulatedError += 1.0f;
                                }
                                newFlexLines2.add(dummySpaceFlexLine3);
                            }
                        }
                        this.mFlexLines = newFlexLines2;
                        return;
                    case 4:
                        int spaceTopAndBottom = (size - totalCrossSize) / (this.mFlexLines.size() * 2);
                        List<FlexLine> newFlexLines3 = new ArrayList<>();
                        FlexLine dummySpaceFlexLine4 = new FlexLine();
                        dummySpaceFlexLine4.mCrossSize = spaceTopAndBottom;
                        for (FlexLine flexLine : this.mFlexLines) {
                            newFlexLines3.add(dummySpaceFlexLine4);
                            newFlexLines3.add(flexLine);
                            newFlexLines3.add(dummySpaceFlexLine4);
                        }
                        this.mFlexLines = newFlexLines3;
                        return;
                    case 5:
                        float freeSpaceUnit = ((float) (size - totalCrossSize)) / ((float) this.mFlexLines.size());
                        float accumulatedError2 = 0.0f;
                        int flexLinesSize = this.mFlexLines.size();
                        for (int i3 = 0; i3 < flexLinesSize; i3++) {
                            FlexLine flexLine2 = (FlexLine) this.mFlexLines.get(i3);
                            float newCrossSizeAsFloat = ((float) flexLine2.mCrossSize) + freeSpaceUnit;
                            if (i3 == this.mFlexLines.size() - 1) {
                                newCrossSizeAsFloat += accumulatedError2;
                                accumulatedError2 = 0.0f;
                            }
                            int newCrossSize = Math.round(newCrossSizeAsFloat);
                            accumulatedError2 += newCrossSizeAsFloat - ((float) newCrossSize);
                            if (accumulatedError2 > 1.0f) {
                                newCrossSize++;
                                accumulatedError2 -= 1.0f;
                            } else if (accumulatedError2 < -1.0f) {
                                newCrossSize--;
                                accumulatedError2 += 1.0f;
                            }
                            flexLine2.mCrossSize = newCrossSize;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void stretchViews(int flexDirection, int alignItems) {
        if (alignItems == 4) {
            int viewIndex = 0;
            for (FlexLine flexLine : this.mFlexLines) {
                int i = 0;
                while (true) {
                    if (i < flexLine.mItemCount) {
                        View view = getReorderedChildAt(viewIndex);
                        LayoutParams lp = (LayoutParams) view.getLayoutParams();
                        if (lp.alignSelf == -1 || lp.alignSelf == 4) {
                            switch (flexDirection) {
                                case 0:
                                case 1:
                                    stretchViewVertically(view, flexLine.mCrossSize);
                                    break;
                                case 2:
                                case 3:
                                    stretchViewHorizontally(view, flexLine.mCrossSize);
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                        }
                        i++;
                        viewIndex++;
                    }
                }
            }
            return;
        }
        for (FlexLine flexLine2 : this.mFlexLines) {
            Iterator it = flexLine2.mIndicesAlignSelfStretch.iterator();
            while (true) {
                if (it.hasNext()) {
                    View view2 = getReorderedChildAt(((Integer) it.next()).intValue());
                    switch (flexDirection) {
                        case 0:
                        case 1:
                            stretchViewVertically(view2, flexLine2.mCrossSize);
                            break;
                        case 2:
                        case 3:
                            stretchViewHorizontally(view2, flexLine2.mCrossSize);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                    }
                }
            }
        }
    }

    private void stretchViewVertically(View view, int crossSize) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        view.measure(MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(Math.max((crossSize - lp.topMargin) - lp.bottomMargin, 0), 1073741824));
    }

    private void stretchViewHorizontally(View view, int crossSize) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        view.measure(MeasureSpec.makeMeasureSpec(Math.max((crossSize - lp.leftMargin) - lp.rightMargin, 0), 1073741824), MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
    }

    private void setMeasuredDimensionForFlex(int flexDirection, int widthMeasureSpec, int heightMeasureSpec, int childState) {
        int calculatedMaxHeight;
        int calculatedMaxWidth;
        int widthSizeAndState;
        int heightSizeAndState;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (flexDirection) {
            case 0:
            case 1:
                calculatedMaxHeight = getSumOfCrossSize() + getPaddingTop() + getPaddingBottom();
                calculatedMaxWidth = getLargestMainSize();
                break;
            case 2:
            case 3:
                calculatedMaxHeight = getLargestMainSize();
                calculatedMaxWidth = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        switch (widthMode) {
            case Integer.MIN_VALUE:
                if (widthSize < calculatedMaxWidth) {
                    childState = ViewCompat.combineMeasuredStates(childState, 16777216);
                } else {
                    widthSize = calculatedMaxWidth;
                }
                widthSizeAndState = ViewCompat.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
                break;
            case 0:
                widthSizeAndState = ViewCompat.resolveSizeAndState(calculatedMaxWidth, widthMeasureSpec, childState);
                break;
            case 1073741824:
                if (widthSize < calculatedMaxWidth) {
                    childState = ViewCompat.combineMeasuredStates(childState, 16777216);
                }
                widthSizeAndState = ViewCompat.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
                break;
            default:
                throw new IllegalStateException("Unknown width mode is set: " + widthMode);
        }
        switch (heightMode) {
            case Integer.MIN_VALUE:
                if (heightSize < calculatedMaxHeight) {
                    childState = ViewCompat.combineMeasuredStates(childState, 256);
                } else {
                    heightSize = calculatedMaxHeight;
                }
                heightSizeAndState = ViewCompat.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
                break;
            case 0:
                heightSizeAndState = ViewCompat.resolveSizeAndState(calculatedMaxHeight, heightMeasureSpec, childState);
                break;
            case 1073741824:
                if (heightSize < calculatedMaxHeight) {
                    childState = ViewCompat.combineMeasuredStates(childState, 256);
                }
                heightSizeAndState = ViewCompat.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
                break;
            default:
                throw new IllegalStateException("Unknown height mode is set: " + heightMode);
        }
        setMeasuredDimension(widthSizeAndState, heightSizeAndState);
    }

    private boolean isWrapRequired(int mode, int maxSize, int currentLength, int childLength, LayoutParams lp, int childAbsoluteIndex, int childRelativeIndexInFlexLine) {
        boolean z = true;
        if (this.mFlexWrap == 0) {
            return false;
        }
        if (lp.wrapBefore) {
            return true;
        }
        if (mode == 0) {
            return false;
        }
        if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            if (hasDividerBeforeChildAtAlongMainAxis(childAbsoluteIndex, childRelativeIndexInFlexLine)) {
                childLength += this.mDividerVerticalWidth;
            }
            if ((this.mShowDividerVertical & 4) > 0) {
                childLength += this.mDividerVerticalWidth;
            }
        } else {
            if (hasDividerBeforeChildAtAlongMainAxis(childAbsoluteIndex, childRelativeIndexInFlexLine)) {
                childLength += this.mDividerHorizontalHeight;
            }
            if ((this.mShowDividerHorizontal & 4) > 0) {
                childLength += this.mDividerHorizontalHeight;
            }
        }
        if (maxSize >= currentLength + childLength) {
            z = false;
        }
        return z;
    }

    private int getLargestMainSize() {
        int largestSize = Integer.MIN_VALUE;
        for (FlexLine flexLine : this.mFlexLines) {
            largestSize = Math.max(largestSize, flexLine.mMainSize);
        }
        return largestSize;
    }

    private int getSumOfCrossSize() {
        int sum = 0;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            if (hasEndDividerAfterFlexLine(i)) {
                if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            sum += flexLine.mCrossSize;
        }
        return sum;
    }

    private boolean isMainAxisDirectionHorizontal(int flexDirection) {
        return flexDirection == 0 || flexDirection == 1;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean isRtl;
        boolean isRtl2;
        boolean isRtl3;
        boolean isRtl4;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        switch (this.mFlexDirection) {
            case 0:
                if (layoutDirection == 1) {
                    isRtl4 = true;
                } else {
                    isRtl4 = false;
                }
                layoutHorizontal(isRtl4, left, top, right, bottom);
                return;
            case 1:
                if (layoutDirection != 1) {
                    isRtl3 = true;
                } else {
                    isRtl3 = false;
                }
                layoutHorizontal(isRtl3, left, top, right, bottom);
                return;
            case 2:
                if (layoutDirection == 1) {
                    isRtl2 = true;
                } else {
                    isRtl2 = false;
                }
                if (this.mFlexWrap == 2) {
                    if (!isRtl2) {
                        isRtl2 = true;
                    } else {
                        isRtl2 = false;
                    }
                }
                layoutVertical(isRtl2, false, left, top, right, bottom);
                return;
            case 3:
                if (layoutDirection == 1) {
                    isRtl = true;
                } else {
                    isRtl = false;
                }
                if (this.mFlexWrap == 2) {
                    if (!isRtl) {
                        isRtl = true;
                    } else {
                        isRtl = false;
                    }
                }
                layoutVertical(isRtl, true, left, top, right, bottom);
                return;
            default:
                throw new IllegalStateException("Invalid flex direction is set: " + this.mFlexDirection);
        }
    }

    private void layoutHorizontal(boolean isRtl, int left, int top, int right, int bottom) {
        float childLeft;
        float childRight;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int currentViewIndex = 0;
        int width = right - left;
        int childBottom = (bottom - top) - getPaddingBottom();
        int childTop = getPaddingTop();
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                childBottom -= this.mDividerHorizontalHeight;
                childTop += this.mDividerHorizontalHeight;
            }
            float spaceBetweenItem = 0.0f;
            switch (this.mJustifyContent) {
                case 0:
                    childLeft = (float) paddingLeft;
                    childRight = (float) (width - paddingRight);
                    break;
                case 1:
                    childLeft = (float) ((width - flexLine.mMainSize) + paddingRight);
                    childRight = (float) (flexLine.mMainSize - paddingLeft);
                    break;
                case 2:
                    childLeft = ((float) paddingLeft) + (((float) (width - flexLine.mMainSize)) / 2.0f);
                    childRight = ((float) (width - paddingRight)) - (((float) (width - flexLine.mMainSize)) / 2.0f);
                    break;
                case 3:
                    childLeft = (float) paddingLeft;
                    int visibleItem = flexLine.getItemCountNotGone();
                    spaceBetweenItem = ((float) (width - flexLine.mMainSize)) / (visibleItem != 1 ? (float) (visibleItem - 1) : 1.0f);
                    childRight = (float) (width - paddingRight);
                    break;
                case 4:
                    int visibleCount = flexLine.getItemCountNotGone();
                    if (visibleCount != 0) {
                        spaceBetweenItem = ((float) (width - flexLine.mMainSize)) / ((float) visibleCount);
                    }
                    childLeft = ((float) paddingLeft) + (spaceBetweenItem / 2.0f);
                    childRight = ((float) (width - paddingRight)) - (spaceBetweenItem / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            float spaceBetweenItem2 = Math.max(spaceBetweenItem, 0.0f);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                View child = getReorderedChildAt(currentViewIndex);
                if (child != null) {
                    if (child.getVisibility() == 8) {
                        currentViewIndex++;
                    } else {
                        LayoutParams lp = (LayoutParams) child.getLayoutParams();
                        float childLeft2 = childLeft + ((float) lp.leftMargin);
                        float childRight2 = childRight - ((float) lp.rightMargin);
                        if (hasDividerBeforeChildAtAlongMainAxis(currentViewIndex, j)) {
                            childLeft2 += (float) this.mDividerVerticalWidth;
                            childRight2 -= (float) this.mDividerVerticalWidth;
                        }
                        if (this.mFlexWrap == 2) {
                            if (isRtl) {
                                layoutSingleChildHorizontal(child, flexLine, this.mFlexWrap, this.mAlignItems, Math.round(childRight2) - child.getMeasuredWidth(), childBottom - child.getMeasuredHeight(), Math.round(childRight2), childBottom);
                            } else {
                                layoutSingleChildHorizontal(child, flexLine, this.mFlexWrap, this.mAlignItems, Math.round(childLeft2), childBottom - child.getMeasuredHeight(), child.getMeasuredWidth() + Math.round(childLeft2), childBottom);
                            }
                        } else if (isRtl) {
                            layoutSingleChildHorizontal(child, flexLine, this.mFlexWrap, this.mAlignItems, Math.round(childRight2) - child.getMeasuredWidth(), childTop, Math.round(childRight2), childTop + child.getMeasuredHeight());
                        } else {
                            layoutSingleChildHorizontal(child, flexLine, this.mFlexWrap, this.mAlignItems, Math.round(childLeft2), childTop, Math.round(childLeft2) + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
                        }
                        childLeft = childLeft2 + ((float) child.getMeasuredWidth()) + spaceBetweenItem2 + ((float) lp.rightMargin);
                        childRight = childRight2 - ((((float) child.getMeasuredWidth()) + spaceBetweenItem2) + ((float) lp.leftMargin));
                        currentViewIndex++;
                        flexLine.mLeft = Math.min(flexLine.mLeft, child.getLeft() - lp.leftMargin);
                        flexLine.mTop = Math.min(flexLine.mTop, child.getTop() - lp.topMargin);
                        flexLine.mRight = Math.max(flexLine.mRight, child.getRight() + lp.rightMargin);
                        flexLine.mBottom = Math.max(flexLine.mBottom, child.getBottom() + lp.bottomMargin);
                    }
                }
            }
            childTop += flexLine.mCrossSize;
            childBottom -= flexLine.mCrossSize;
        }
    }

    private void layoutSingleChildHorizontal(View view, FlexLine flexLine, int flexWrap, int alignItems, int left, int top, int right, int bottom) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp.alignSelf != -1) {
            alignItems = lp.alignSelf;
        }
        int crossSize = flexLine.mCrossSize;
        switch (alignItems) {
            case 0:
            case 4:
                if (flexWrap != 2) {
                    view.layout(left, lp.topMargin + top, right, lp.topMargin + bottom);
                    return;
                } else {
                    view.layout(left, top - lp.bottomMargin, right, bottom - lp.bottomMargin);
                    return;
                }
            case 1:
                if (flexWrap != 2) {
                    view.layout(left, ((top + crossSize) - view.getMeasuredHeight()) - lp.bottomMargin, right, (top + crossSize) - lp.bottomMargin);
                    return;
                } else {
                    view.layout(left, (top - crossSize) + view.getMeasuredHeight() + lp.topMargin, right, (bottom - crossSize) + view.getMeasuredHeight() + lp.topMargin);
                    return;
                }
            case 2:
                int topFromCrossAxis = (((crossSize - view.getMeasuredHeight()) + lp.topMargin) - lp.bottomMargin) / 2;
                if (flexWrap != 2) {
                    view.layout(left, top + topFromCrossAxis, right, top + topFromCrossAxis + view.getMeasuredHeight());
                    return;
                } else {
                    view.layout(left, top - topFromCrossAxis, right, (top - topFromCrossAxis) + view.getMeasuredHeight());
                    return;
                }
            case 3:
                if (flexWrap != 2) {
                    int marginTop = Math.max(flexLine.mMaxBaseline - view.getBaseline(), lp.topMargin);
                    view.layout(left, top + marginTop, right, bottom + marginTop);
                    return;
                }
                int marginBottom = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), lp.bottomMargin);
                view.layout(left, top - marginBottom, right, bottom - marginBottom);
                return;
            default:
                return;
        }
    }

    private void layoutVertical(boolean isRtl, boolean fromBottomToTop, int left, int top, int right, int bottom) {
        float childTop;
        float childBottom;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int childLeft = getPaddingLeft();
        int currentViewIndex = 0;
        int height = bottom - top;
        int childRight = (right - left) - paddingRight;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                childLeft += this.mDividerVerticalWidth;
                childRight -= this.mDividerVerticalWidth;
            }
            float spaceBetweenItem = 0.0f;
            switch (this.mJustifyContent) {
                case 0:
                    childTop = (float) paddingTop;
                    childBottom = (float) (height - paddingBottom);
                    break;
                case 1:
                    childTop = (float) ((height - flexLine.mMainSize) + paddingBottom);
                    childBottom = (float) (flexLine.mMainSize - paddingTop);
                    break;
                case 2:
                    childTop = ((float) paddingTop) + (((float) (height - flexLine.mMainSize)) / 2.0f);
                    childBottom = ((float) (height - paddingBottom)) - (((float) (height - flexLine.mMainSize)) / 2.0f);
                    break;
                case 3:
                    childTop = (float) paddingTop;
                    int visibleItem = flexLine.getItemCountNotGone();
                    spaceBetweenItem = ((float) (height - flexLine.mMainSize)) / (visibleItem != 1 ? (float) (visibleItem - 1) : 1.0f);
                    childBottom = (float) (height - paddingBottom);
                    break;
                case 4:
                    int visibleCount = flexLine.getItemCountNotGone();
                    if (visibleCount != 0) {
                        spaceBetweenItem = ((float) (height - flexLine.mMainSize)) / ((float) visibleCount);
                    }
                    childTop = ((float) paddingTop) + (spaceBetweenItem / 2.0f);
                    childBottom = ((float) (height - paddingBottom)) - (spaceBetweenItem / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            float spaceBetweenItem2 = Math.max(spaceBetweenItem, 0.0f);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                View child = getReorderedChildAt(currentViewIndex);
                if (child != null) {
                    if (child.getVisibility() == 8) {
                        currentViewIndex++;
                    } else {
                        LayoutParams lp = (LayoutParams) child.getLayoutParams();
                        float childTop2 = childTop + ((float) lp.topMargin);
                        float childBottom2 = childBottom - ((float) lp.bottomMargin);
                        if (hasDividerBeforeChildAtAlongMainAxis(currentViewIndex, j)) {
                            childTop2 += (float) this.mDividerHorizontalHeight;
                            childBottom2 -= (float) this.mDividerHorizontalHeight;
                        }
                        if (isRtl) {
                            if (fromBottomToTop) {
                                layoutSingleChildVertical(child, flexLine, true, this.mAlignItems, childRight - child.getMeasuredWidth(), Math.round(childBottom2) - child.getMeasuredHeight(), childRight, Math.round(childBottom2));
                            } else {
                                layoutSingleChildVertical(child, flexLine, true, this.mAlignItems, childRight - child.getMeasuredWidth(), Math.round(childTop2), childRight, child.getMeasuredHeight() + Math.round(childTop2));
                            }
                        } else if (fromBottomToTop) {
                            layoutSingleChildVertical(child, flexLine, false, this.mAlignItems, childLeft, Math.round(childBottom2) - child.getMeasuredHeight(), childLeft + child.getMeasuredWidth(), Math.round(childBottom2));
                        } else {
                            layoutSingleChildVertical(child, flexLine, false, this.mAlignItems, childLeft, Math.round(childTop2), childLeft + child.getMeasuredWidth(), Math.round(childTop2) + child.getMeasuredHeight());
                        }
                        childTop = childTop2 + ((float) child.getMeasuredHeight()) + spaceBetweenItem2 + ((float) lp.bottomMargin);
                        childBottom = childBottom2 - ((((float) child.getMeasuredHeight()) + spaceBetweenItem2) + ((float) lp.topMargin));
                        currentViewIndex++;
                        flexLine.mLeft = Math.min(flexLine.mLeft, child.getLeft() - lp.leftMargin);
                        flexLine.mTop = Math.min(flexLine.mTop, child.getTop() - lp.topMargin);
                        flexLine.mRight = Math.max(flexLine.mRight, child.getRight() + lp.rightMargin);
                        flexLine.mBottom = Math.max(flexLine.mBottom, child.getBottom() + lp.bottomMargin);
                    }
                }
            }
            childLeft += flexLine.mCrossSize;
            childRight -= flexLine.mCrossSize;
        }
    }

    private void layoutSingleChildVertical(View view, FlexLine flexLine, boolean isRtl, int alignItems, int left, int top, int right, int bottom) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp.alignSelf != -1) {
            alignItems = lp.alignSelf;
        }
        int crossSize = flexLine.mCrossSize;
        switch (alignItems) {
            case 0:
            case 3:
            case 4:
                if (!isRtl) {
                    view.layout(lp.leftMargin + left, top, lp.leftMargin + right, bottom);
                    return;
                } else {
                    view.layout(left - lp.rightMargin, top, right - lp.rightMargin, bottom);
                    return;
                }
            case 1:
                if (!isRtl) {
                    view.layout(((left + crossSize) - view.getMeasuredWidth()) - lp.rightMargin, top, ((right + crossSize) - view.getMeasuredWidth()) - lp.rightMargin, bottom);
                    return;
                } else {
                    view.layout((left - crossSize) + view.getMeasuredWidth() + lp.leftMargin, top, (right - crossSize) + view.getMeasuredWidth() + lp.leftMargin, bottom);
                    return;
                }
            case 2:
                int leftFromCrossAxis = (((crossSize - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(lp)) - MarginLayoutParamsCompat.getMarginEnd(lp)) / 2;
                if (!isRtl) {
                    view.layout(left + leftFromCrossAxis, top, right + leftFromCrossAxis, bottom);
                    return;
                } else {
                    view.layout(left - leftFromCrossAxis, top, right - leftFromCrossAxis, bottom);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        boolean isRtl;
        boolean isRtl2;
        boolean isRtl3;
        boolean isRtl4;
        if (this.mDividerDrawableVertical != null || this.mDividerDrawableHorizontal != null) {
            if (this.mShowDividerHorizontal != 0 || this.mShowDividerVertical != 0) {
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                boolean fromBottomToTop = false;
                switch (this.mFlexDirection) {
                    case 0:
                        if (layoutDirection == 1) {
                            isRtl4 = true;
                        } else {
                            isRtl4 = false;
                        }
                        if (this.mFlexWrap == 2) {
                            fromBottomToTop = true;
                        }
                        drawDividersHorizontal(canvas, isRtl4, fromBottomToTop);
                        return;
                    case 1:
                        if (layoutDirection != 1) {
                            isRtl3 = true;
                        } else {
                            isRtl3 = false;
                        }
                        if (this.mFlexWrap == 2) {
                            fromBottomToTop = true;
                        }
                        drawDividersHorizontal(canvas, isRtl3, fromBottomToTop);
                        return;
                    case 2:
                        if (layoutDirection == 1) {
                            isRtl2 = true;
                        } else {
                            isRtl2 = false;
                        }
                        if (this.mFlexWrap == 2) {
                            if (!isRtl2) {
                                isRtl2 = true;
                            } else {
                                isRtl2 = false;
                            }
                        }
                        drawDividersVertical(canvas, isRtl2, false);
                        return;
                    case 3:
                        if (layoutDirection == 1) {
                            isRtl = true;
                        } else {
                            isRtl = false;
                        }
                        if (this.mFlexWrap == 2) {
                            if (!isRtl) {
                                isRtl = true;
                            } else {
                                isRtl = false;
                            }
                        }
                        drawDividersVertical(canvas, isRtl, true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void drawDividersHorizontal(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int horizontalDividerTop;
        int horizontalDividerTop2;
        int dividerLeft;
        int dividerLeft2;
        int currentViewIndex = 0;
        int paddingLeft = getPaddingLeft();
        int horizontalDividerLength = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                View view = getReorderedChildAt(currentViewIndex);
                if (!(view == null || view.getVisibility() == 8)) {
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(currentViewIndex, j)) {
                        if (isRtl) {
                            dividerLeft2 = view.getRight() + lp.rightMargin;
                        } else {
                            dividerLeft2 = (view.getLeft() - lp.leftMargin) - this.mDividerVerticalWidth;
                        }
                        drawVerticalDivider(canvas, dividerLeft2, flexLine.mTop, flexLine.mCrossSize);
                    }
                    if (j == flexLine.mItemCount - 1 && (this.mShowDividerVertical & 4) > 0) {
                        if (isRtl) {
                            dividerLeft = (view.getLeft() - lp.leftMargin) - this.mDividerVerticalWidth;
                        } else {
                            dividerLeft = view.getRight() + lp.rightMargin;
                        }
                        drawVerticalDivider(canvas, dividerLeft, flexLine.mTop, flexLine.mCrossSize);
                    }
                    currentViewIndex++;
                }
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (fromBottomToTop) {
                    horizontalDividerTop2 = flexLine.mBottom;
                } else {
                    horizontalDividerTop2 = flexLine.mTop - this.mDividerHorizontalHeight;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop2, horizontalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerHorizontal & 4) > 0) {
                if (fromBottomToTop) {
                    horizontalDividerTop = flexLine.mTop - this.mDividerHorizontalHeight;
                } else {
                    horizontalDividerTop = flexLine.mBottom;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop, horizontalDividerLength);
            }
        }
    }

    private void drawDividersVertical(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int verticalDividerLeft;
        int verticalDividerLeft2;
        int dividerTop;
        int dividerTop2;
        int currentViewIndex = 0;
        int paddingTop = getPaddingTop();
        int verticalDividerLength = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                View view = getReorderedChildAt(currentViewIndex);
                if (!(view == null || view.getVisibility() == 8)) {
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(currentViewIndex, j)) {
                        if (fromBottomToTop) {
                            dividerTop2 = view.getBottom() + lp.bottomMargin;
                        } else {
                            dividerTop2 = (view.getTop() - lp.topMargin) - this.mDividerHorizontalHeight;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop2, flexLine.mCrossSize);
                    }
                    if (j == flexLine.mItemCount - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        if (fromBottomToTop) {
                            dividerTop = (view.getTop() - lp.topMargin) - this.mDividerHorizontalHeight;
                        } else {
                            dividerTop = view.getBottom() + lp.bottomMargin;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop, flexLine.mCrossSize);
                    }
                    currentViewIndex++;
                }
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (isRtl) {
                    verticalDividerLeft2 = flexLine.mRight;
                } else {
                    verticalDividerLeft2 = flexLine.mLeft - this.mDividerVerticalWidth;
                }
                drawVerticalDivider(canvas, verticalDividerLeft2, paddingTop, verticalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerVertical & 4) > 0) {
                if (isRtl) {
                    verticalDividerLeft = flexLine.mLeft - this.mDividerVerticalWidth;
                } else {
                    verticalDividerLeft = flexLine.mRight;
                }
                drawVerticalDivider(canvas, verticalDividerLeft, paddingTop, verticalDividerLength);
            }
        }
    }

    private void drawVerticalDivider(Canvas canvas, int left, int top, int length) {
        if (this.mDividerDrawableVertical != null) {
            this.mDividerDrawableVertical.setBounds(left, top, this.mDividerVerticalWidth + left, top + length);
            this.mDividerDrawableVertical.draw(canvas);
        }
    }

    private void drawHorizontalDivider(Canvas canvas, int left, int top, int length) {
        if (this.mDividerDrawableHorizontal != null) {
            this.mDividerDrawableHorizontal.setBounds(left, top, left + length, this.mDividerHorizontalHeight + top);
            this.mDividerDrawableHorizontal.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int flexDirection) {
        if (this.mFlexDirection != flexDirection) {
            this.mFlexDirection = flexDirection;
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int flexWrap) {
        if (this.mFlexWrap != flexWrap) {
            this.mFlexWrap = flexWrap;
            requestLayout();
        }
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public void setJustifyContent(int justifyContent) {
        if (this.mJustifyContent != justifyContent) {
            this.mJustifyContent = justifyContent;
            requestLayout();
        }
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public void setAlignItems(int alignItems) {
        if (this.mAlignItems != alignItems) {
            this.mAlignItems = alignItems;
            requestLayout();
        }
    }

    public int getAlignContent() {
        return this.mAlignContent;
    }

    public void setAlignContent(int alignContent) {
        if (this.mAlignContent != alignContent) {
            this.mAlignContent = alignContent;
            requestLayout();
        }
    }

    public List<FlexLine> getFlexLines() {
        List<FlexLine> result = new ArrayList<>(this.mFlexLines.size());
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.getItemCountNotGone() != 0) {
                result.add(flexLine);
            }
        }
        return result;
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public void setDividerDrawable(Drawable divider) {
        setDividerDrawableHorizontal(divider);
        setDividerDrawableVertical(divider);
    }

    public void setDividerDrawableHorizontal(Drawable divider) {
        if (divider != this.mDividerDrawableHorizontal) {
            this.mDividerDrawableHorizontal = divider;
            if (divider != null) {
                this.mDividerHorizontalHeight = divider.getIntrinsicHeight();
            } else {
                this.mDividerHorizontalHeight = 0;
            }
            setWillNotDrawFlag();
            requestLayout();
        }
    }

    public void setDividerDrawableVertical(Drawable divider) {
        if (divider != this.mDividerDrawableVertical) {
            this.mDividerDrawableVertical = divider;
            if (divider != null) {
                this.mDividerVerticalWidth = divider.getIntrinsicWidth();
            } else {
                this.mDividerVerticalWidth = 0;
            }
            setWillNotDrawFlag();
            requestLayout();
        }
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public void setShowDivider(int dividerMode) {
        setShowDividerVertical(dividerMode);
        setShowDividerHorizontal(dividerMode);
    }

    public void setShowDividerVertical(int dividerMode) {
        if (dividerMode != this.mShowDividerVertical) {
            this.mShowDividerVertical = dividerMode;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int dividerMode) {
        if (dividerMode != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = dividerMode;
            requestLayout();
        }
    }

    private void setWillNotDrawFlag() {
        if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean hasDividerBeforeChildAtAlongMainAxis(int childAbsoluteIndex, int childRelativeIndexInFlexLine) {
        if (allViewsAreGoneBefore(childAbsoluteIndex, childRelativeIndexInFlexLine)) {
            if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                if ((this.mShowDividerVertical & 1) != 0) {
                    return true;
                }
                return false;
            } else if ((this.mShowDividerHorizontal & 1) == 0) {
                return false;
            } else {
                return true;
            }
        } else if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            if ((this.mShowDividerVertical & 2) == 0) {
                return false;
            }
            return true;
        } else if ((this.mShowDividerHorizontal & 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean allViewsAreGoneBefore(int childAbsoluteIndex, int childRelativeIndexInFlexLine) {
        for (int i = 1; i <= childRelativeIndexInFlexLine; i++) {
            View view = getReorderedChildAt(childAbsoluteIndex - i);
            if (view != null && view.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDividerBeforeFlexLine(int flexLineIndex) {
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        if (allFlexLinesAreDummyBefore(flexLineIndex)) {
            if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                if ((this.mShowDividerHorizontal & 1) == 0) {
                    return false;
                }
                return true;
            } else if ((this.mShowDividerVertical & 1) == 0) {
                return false;
            } else {
                return true;
            }
        } else if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            if ((this.mShowDividerHorizontal & 2) == 0) {
                return false;
            }
            return true;
        } else if ((this.mShowDividerVertical & 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean allFlexLinesAreDummyBefore(int flexLineIndex) {
        for (int i = 0; i < flexLineIndex; i++) {
            if (((FlexLine) this.mFlexLines.get(i)).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean hasEndDividerAfterFlexLine(int flexLineIndex) {
        boolean z = true;
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        for (int i = flexLineIndex + 1; i < this.mFlexLines.size(); i++) {
            if (((FlexLine) this.mFlexLines.get(i)).getItemCountNotGone() > 0) {
                return false;
            }
        }
        if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            return (this.mShowDividerHorizontal & 4) != 0;
        }
        if ((this.mShowDividerVertical & 4) == 0) {
            z = false;
        }
        return z;
    }
}
