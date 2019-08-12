package com.airbnb.android.sharing.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.view.View;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.p033ui.ShareMethodEpoxyModel_;
import com.airbnb.android.sharing.p033ui.SharePreviewEpoxyModel_;
import com.airbnb.android.sharing.shareables.Shareable;
import java.util.Collections;
import java.util.List;

public class ShareAdapter extends AirEpoxyAdapter {
    public ShareAdapter(Context context, Shareable shareable, AirbnbPreferences preferences) {
        addModel(new SharePreviewEpoxyModel_().imageUrl(shareable.getImageUrl()).imagePath(shareable.getImagePath()).title(shareable.getName()));
        Intent baseShareIntent = new Intent("android.intent.action.SEND");
        baseShareIntent.setType("text/plain");
        List<ResolveInfo> shareChannels = context.getPackageManager().queryIntentActivities(baseShareIntent, 0);
        Collections.sort(shareChannels, CustomShareActivities.getComparator(context));
        for (ResolveInfo shareChannel : shareChannels) {
            addModel(new ShareMethodEpoxyModel_().icon(shareChannel.loadIcon(context.getPackageManager())).name(shareChannel.loadLabel(context.getPackageManager()).toString()).clickListener(ShareAdapter$$Lambda$1.lambdaFactory$(shareChannel, baseShareIntent, shareable, context)));
        }
    }

    static /* synthetic */ void lambda$new$0(ResolveInfo shareChannel, Intent baseShareIntent, Shareable shareable, Context context, View v) {
        ActivityInfo info = shareChannel.activityInfo;
        baseShareIntent.setClassName(info.packageName, info.name);
        Intent finalIntent = shareable.buildIntentForPackage(baseShareIntent, CustomShareActivities.getEnum(info.packageName), info.packageName);
        if (finalIntent != null) {
            context.startActivity(finalIntent);
        }
    }
}
