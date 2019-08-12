package com.airbnb.android.lib.utils.webintent;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import java.util.List;

public class WebBrowserListAdapter extends ArrayAdapter<ResolveInfo> {
    public WebBrowserListAdapter(Context context, List<ResolveInfo> items) {
        super(context, 0, items);
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(C0880R.layout.list_item_app, parent, false);
        }
        String appName = ((ResolveInfo) getItem(pos)).activityInfo.loadLabel(getContext().getPackageManager()).toString();
        Drawable appIcon = ((ResolveInfo) getItem(pos)).activityInfo.loadIcon(getContext().getPackageManager());
        ((TextView) ButterKnife.findById(convertView, C0880R.C0882id.app_title)).setText(appName);
        ((ImageView) ButterKnife.findById(convertView, C0880R.C0882id.app_logo)).setImageDrawable(appIcon);
        return convertView;
    }
}
