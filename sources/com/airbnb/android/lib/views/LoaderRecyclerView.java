package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;

public class LoaderRecyclerView extends FrameLayout {
    @BindView
    EmptyResults mEmptyResults;
    private String mEmptyResultsSubtitle;
    private String mEmptyResultsTitle;
    @BindView
    LoaderFrame mLoaderFrame;
    @BindView
    RecyclerView mRecyclerView;

    public LoaderRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getEmptyResultsAttrs(context, attrs);
        init();
    }

    public LoaderRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoaderRecyclerView(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    public void init() {
        if (!isInEditMode()) {
            LayoutInflater.from(getContext()).inflate(C0880R.layout.loader_recycler_view, this);
            ButterKnife.bind((View) this);
            this.mLoaderFrame.startAnimation();
            if (!TextUtils.isEmpty(this.mEmptyResultsTitle) || !TextUtils.isEmpty(this.mEmptyResultsSubtitle)) {
                this.mEmptyResults.setTitle(this.mEmptyResultsTitle);
                this.mEmptyResults.setSubTitle(this.mEmptyResultsSubtitle);
            }
        }
    }

    private void getEmptyResultsAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.BaseLoaderListView, 0, 0);
            this.mEmptyResultsTitle = a.getString(C0880R.styleable.BaseLoaderListView_emptyResultsTitle);
            this.mEmptyResultsSubtitle = a.getString(C0880R.styleable.BaseLoaderListView_emptyResultsSubtitle);
            a.recycle();
        }
    }

    public void showEmptyResults(boolean emptyResultsVisible) {
        int i;
        int i2;
        int i3 = 8;
        EmptyResults emptyResults = this.mEmptyResults;
        if (emptyResultsVisible) {
            i = 0;
        } else {
            i = 8;
        }
        emptyResults.setVisibility(i);
        RecyclerView recyclerView = this.mRecyclerView;
        if (emptyResultsVisible) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        recyclerView.setVisibility(i2);
        LoaderFrame loaderFrame = this.mLoaderFrame;
        if (!emptyResultsVisible) {
            i3 = 0;
        }
        loaderFrame.setVisibility(i3);
    }

    public void setEmptyResultsTitle(String title) {
        this.mEmptyResultsTitle = title;
        this.mEmptyResults.setTitle(this.mEmptyResultsTitle);
    }

    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
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
        this.mLoaderFrame.setVisibility(8);
        this.mLoaderFrame.finish();
    }

    public LoaderFrame getLoaderFrame() {
        return this.mLoaderFrame;
    }
}
