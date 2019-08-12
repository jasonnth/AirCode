package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.SpokenLanguage;

final /* synthetic */ class SpokenLanguagesAdapter$$Lambda$1 implements OnClickListener {
    private final SpokenLanguage arg$1;
    private final ViewHolder arg$2;

    private SpokenLanguagesAdapter$$Lambda$1(SpokenLanguage spokenLanguage, ViewHolder viewHolder) {
        this.arg$1 = spokenLanguage;
        this.arg$2 = viewHolder;
    }

    public static OnClickListener lambdaFactory$(SpokenLanguage spokenLanguage, ViewHolder viewHolder) {
        return new SpokenLanguagesAdapter$$Lambda$1(spokenLanguage, viewHolder);
    }

    public void onClick(View view) {
        SpokenLanguagesAdapter.lambda$getView$0(this.arg$1, this.arg$2, view);
    }
}
