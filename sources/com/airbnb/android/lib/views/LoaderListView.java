package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;
import com.airbnb.android.lib.C0880R;

@Deprecated
public class LoaderListView extends BaseLoaderListView {
    public LoaderListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LoaderListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoaderListView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        init(C0880R.layout.loader_list_view);
    }

    public ListView getListView() {
        AbsListView absListView = getAbsListView();
        if (absListView instanceof ListView) {
            return (ListView) absListView;
        }
        return null;
    }
}
