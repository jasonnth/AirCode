package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import com.airbnb.android.core.interfaces.ViewPagerAbsListView;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;

public abstract class BaseLoaderListView extends FrameLayout {
    private EmptyResults mEmptyResults;
    private String mEmptyResultsSubtitle;
    private String mEmptyResultsTitle;
    private ViewPagerAbsListView mListView;
    private LoaderFrame mLoaderFrame;

    /* access modifiers changed from: protected */
    public abstract void init();

    public BaseLoaderListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getEmptyResultsAttrs(context, attrs);
        init();
    }

    public BaseLoaderListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getEmptyResultsAttrs(context, attrs);
        init();
    }

    public BaseLoaderListView(Context context) {
        super(context);
        init();
    }

    /* access modifiers changed from: protected */
    public void init(int resourceId) {
        if (!isInEditMode()) {
            LayoutInflater.from(getContext()).inflate(resourceId, this);
            this.mListView = (ViewPagerAbsListView) findViewById(C0880R.C0882id.list_view);
            this.mLoaderFrame = (LoaderFrame) findViewById(C0880R.C0882id.loader_frame);
            this.mLoaderFrame.startAnimation();
            this.mEmptyResults = (EmptyResults) findViewById(C0880R.C0882id.empty_results);
            if (!TextUtils.isEmpty(this.mEmptyResultsTitle) || !TextUtils.isEmpty(this.mEmptyResultsSubtitle)) {
                this.mEmptyResults.setTitle(this.mEmptyResultsTitle);
                this.mEmptyResults.setSubTitle(this.mEmptyResultsSubtitle);
            }
        }
    }

    private void getEmptyResultsAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.BaseLoaderListView, 0, 0);
        this.mEmptyResultsTitle = a.getString(C0880R.styleable.BaseLoaderListView_emptyResultsTitle);
        this.mEmptyResultsSubtitle = a.getString(C0880R.styleable.BaseLoaderListView_emptyResultsSubtitle);
        a.recycle();
    }

    public AbsListView getAbsListView() {
        return this.mListView.getListView();
    }

    public ViewPagerAbsListView getViewPagerListView() {
        return this.mListView;
    }

    public void showEmptyResults(boolean visible) {
        this.mEmptyResults.setVisibility(visible ? 0 : 8);
    }

    public void setEmptyResultsTitle(String title) {
        this.mEmptyResultsTitle = title;
        this.mEmptyResults.setTitle(this.mEmptyResultsTitle);
    }

    public EmptyResults getEmptyResults() {
        return this.mEmptyResults;
    }

    public void startLoader() {
        this.mLoaderFrame.startAnimation();
    }

    public void finishLoader() {
        this.mLoaderFrame.finish();
    }

    public void finishLoaderImmediate() {
        this.mLoaderFrame.finishImmediate();
    }

    public boolean isLoading() {
        return this.mLoaderFrame.isAnimating();
    }
}
