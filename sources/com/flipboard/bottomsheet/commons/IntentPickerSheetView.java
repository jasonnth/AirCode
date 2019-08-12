package com.flipboard.bottomsheet.commons;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.p000v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import flipboard.bottomsheet.commons.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressLint({"ViewConstructor"})
public class IntentPickerSheetView extends FrameLayout {
    protected Adapter adapter;
    protected final GridView appGrid;
    private int columnWidthDp = 100;
    protected Filter filter = new FilterNone();
    protected final Intent intent;
    protected final List<ActivityInfo> mixins = new ArrayList();
    protected Comparator<ActivityInfo> sortMethod = new SortAlphabetically();
    protected final TextView titleView;

    public static class ActivityInfo {
        public final ComponentName componentName;
        public Drawable icon;
        /* access modifiers changed from: private */
        public AsyncTask<Void, Void, Drawable> iconLoadTask;
        public final String label;
        public final ResolveInfo resolveInfo;

        ActivityInfo(ResolveInfo resolveInfo2, CharSequence label2, ComponentName componentName2) {
            this.resolveInfo = resolveInfo2;
            this.label = label2.toString();
            this.componentName = componentName2;
        }

        public Intent getConcreteIntent(Intent intent) {
            Intent concreteIntent = new Intent(intent);
            concreteIntent.setComponent(this.componentName);
            return concreteIntent;
        }
    }

    private class Adapter extends BaseAdapter {
        final List<ActivityInfo> activityInfos;
        final LayoutInflater inflater;
        /* access modifiers changed from: private */
        public PackageManager packageManager;

        class ViewHolder {
            final ImageView icon;
            final TextView label;

            ViewHolder(View root) {
                this.icon = (ImageView) root.findViewById(R.id.icon);
                this.label = (TextView) root.findViewById(R.id.label);
            }
        }

        public Adapter(Context context, Intent intent, List<ActivityInfo> mixins) {
            this.inflater = LayoutInflater.from(context);
            this.packageManager = context.getPackageManager();
            List<ResolveInfo> infos = this.packageManager.queryIntentActivities(intent, 0);
            this.activityInfos = new ArrayList(infos.size() + mixins.size());
            this.activityInfos.addAll(mixins);
            for (ResolveInfo info : infos) {
                ActivityInfo activityInfo = new ActivityInfo(info, info.loadLabel(this.packageManager), new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                if (IntentPickerSheetView.this.filter.include(activityInfo)) {
                    this.activityInfos.add(activityInfo);
                }
            }
            Collections.sort(this.activityInfos, IntentPickerSheetView.this.sortMethod);
        }

        public int getCount() {
            return this.activityInfos.size();
        }

        public ActivityInfo getItem(int position) {
            return (ActivityInfo) this.activityInfos.get(position);
        }

        public long getItemId(int position) {
            return (long) ((ActivityInfo) this.activityInfos.get(position)).componentName.hashCode();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = this.inflater.inflate(R.layout.sheet_grid_item, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ActivityInfo info = (ActivityInfo) this.activityInfos.get(position);
            if (info.iconLoadTask != null) {
                info.iconLoadTask.cancel(true);
                info.iconLoadTask = null;
            }
            if (info.icon != null) {
                holder.icon.setImageDrawable(info.icon);
            } else {
                holder.icon.setImageDrawable(IntentPickerSheetView.this.getResources().getDrawable(R.color.divider_gray));
                info.iconLoadTask = new AsyncTask<Void, Void, Drawable>() {
                    /* access modifiers changed from: protected */
                    public Drawable doInBackground(Void... params) {
                        return info.resolveInfo.loadIcon(Adapter.this.packageManager);
                    }

                    /* access modifiers changed from: protected */
                    public void onPostExecute(Drawable drawable) {
                        info.icon = drawable;
                        info.iconLoadTask = null;
                        holder.icon.setImageDrawable(drawable);
                    }
                };
                info.iconLoadTask.execute(new Void[0]);
            }
            holder.label.setText(info.label);
            return convertView;
        }
    }

    public interface Filter {
        boolean include(ActivityInfo activityInfo);
    }

    private class FilterNone implements Filter {
        private FilterNone() {
        }

        public boolean include(ActivityInfo info) {
            return true;
        }
    }

    public interface OnIntentPickedListener {
        void onIntentPicked(ActivityInfo activityInfo);
    }

    private class SortAlphabetically implements Comparator<ActivityInfo> {
        private SortAlphabetically() {
        }

        public int compare(ActivityInfo lhs, ActivityInfo rhs) {
            return lhs.label.compareTo(rhs.label);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        for (ActivityInfo activityInfo : this.adapter.activityInfos) {
            if (activityInfo.iconLoadTask != null) {
                activityInfo.iconLoadTask.cancel(true);
                activityInfo.iconLoadTask = null;
            }
        }
    }

    public IntentPickerSheetView(Context context, Intent intent2, String title, final OnIntentPickedListener listener) {
        super(context);
        this.intent = intent2;
        inflate(context, R.layout.grid_sheet_view, this);
        this.appGrid = (GridView) findViewById(R.id.grid);
        this.titleView = (TextView) findViewById(R.id.title);
        this.titleView.setText(title);
        this.appGrid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listener.onIntentPicked(IntentPickerSheetView.this.adapter.getItem(position));
            }
        });
        ViewCompat.setElevation(this, (float) Util.dp2px(getContext(), 16.0f));
    }

    public void setSortMethod(Comparator<ActivityInfo> sortMethod2) {
        this.sortMethod = sortMethod2;
    }

    public void setFilter(Filter filter2) {
        this.filter = filter2;
    }

    public void setColumnWidthDp(int columnWidthDp2) {
        this.columnWidthDp = columnWidthDp2;
    }

    public void setMixins(List<ActivityInfo> infos) {
        this.mixins.clear();
        this.mixins.addAll(infos);
    }

    public List<ActivityInfo> getMixins() {
        return this.mixins;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.adapter = new Adapter(getContext(), this.intent, this.mixins);
        this.appGrid.setAdapter(this.adapter);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        float density = getResources().getDisplayMetrics().density;
        getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.appGrid.setNumColumns((int) (((float) width) / (((float) this.columnWidthDp) * density)));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new ShadowOutline(w, h));
        }
    }
}
