package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.primitives.StaticMapView;
import com.airbnb.p027n2.utils.LatLng;
import com.airbnb.p027n2.utils.MapOptions;

/* renamed from: com.airbnb.n2.components.decide.select.SelectMapInterstitial */
public class SelectMapInterstitial extends BaseDividerComponent {
    @BindView
    StaticMapView mapView;

    public SelectMapInterstitial(Context context) {
        super(context);
    }

    public SelectMapInterstitial(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectMapInterstitial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_select_map_interstitial;
    }

    public void setMapOptions(MapOptions mapOptions) {
        this.mapView.setup(MapOptions.builder(mapOptions).center(LatLng.builder().lat(mapOptions.center().lat()).lng(mapOptions.center().lng()).build()).build(), SelectMapInterstitial$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$setMapOptions$0(Exception e) {
    }

    public void clear() {
        this.mapView.clear();
    }

    public static void mock(SelectMapInterstitial interstitial) {
        interstitial.setMapOptions(MapOptions.builder(false).center(LatLng.builder().lat(37.771942d).lng(-122.405238d).build()).zoom(14).build());
    }
}
