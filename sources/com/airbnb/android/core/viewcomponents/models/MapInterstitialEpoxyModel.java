package com.airbnb.android.core.viewcomponents.models;

import android.support.p000v4.content.ContextCompat;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.MapInterstitial;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.MapOptions;

public abstract class MapInterstitialEpoxyModel extends AirEpoxyModel<MapInterstitial> {
    protected OnClickListener clickListener;
    protected boolean hideText = false;
    protected MapOptions mapOptions;
    protected boolean shortCard = false;
    protected String subtitle;
    protected String title;

    public int getDefaultLayout() {
        if (this.shortCard) {
            return C0716R.layout.view_holder_map_interstitial_short;
        }
        if (this.hideText) {
            return C0716R.layout.view_holder_map_interstitial_no_address;
        }
        return C0716R.layout.n2_view_holder_map_interstitial;
    }

    public void bind(MapInterstitial view) {
        super.bind(view);
        view.hideText(this.hideText);
        view.setTitle(this.title);
        view.setNeighborhoodText(this.subtitle);
        view.setListener(MapInterstitialEpoxyModel$$Lambda$1.lambdaFactory$());
        view.setMapOptions(this.mapOptions);
        view.setOnClickListener(this.clickListener);
        if (this.shortCard) {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.n2_white));
        }
    }

    public void unbind(MapInterstitial view) {
        super.unbind(view);
        view.setOnClickListener(null);
        view.setListener(null);
        view.clear();
    }

    public int getDividerViewType() {
        return 4;
    }
}
