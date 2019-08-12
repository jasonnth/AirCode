package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.support.p000v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;

public class DynamicHintPagerAdapter extends PagerAdapter {
    private final int mHintAppearanceId;
    private final String[] mHints;
    private final OnClickListener mOnClick;
    private final SparseArray<View> mViews;

    public DynamicHintPagerAdapter(Context context, int staticHintsId, int hintAppearanceId, OnClickListener onClickListener) {
        this(context.getResources().getStringArray(staticHintsId), hintAppearanceId, onClickListener);
    }

    public DynamicHintPagerAdapter(String[] staticHints, int hintAppearanceId, OnClickListener onClickListener) {
        this.mHints = staticHints;
        this.mHintAppearanceId = hintAppearanceId;
        this.mViews = new SparseArray<>();
        this.mOnClick = onClickListener;
    }

    public int getCount() {
        return this.mHints.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = (View) this.mViews.get(position);
        if (view == null) {
            view = LayoutInflater.from(container.getContext()).inflate(C0880R.layout.dynamic_hint_text, container, false);
            if (this.mHintAppearanceId > 0) {
                ((TextView) ButterKnife.findById(view, C0880R.C0882id.txt_hint)).setTextAppearance(container.getContext(), this.mHintAppearanceId);
            }
            this.mViews.put(position, view);
            if (this.mOnClick != null) {
                OnClickListener onClickListener = this.mOnClick;
                onClickListener.getClass();
                view.setOnClickListener(DynamicHintPagerAdapter$$Lambda$1.lambdaFactory$(onClickListener));
            }
        }
        ((TextView) ButterKnife.findById(view, C0880R.C0882id.txt_hint)).setText(this.mHints[position]);
        container.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
