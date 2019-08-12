package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.StaticMapView;
import com.airbnb.p027n2.primitives.StaticMapView.Listener;
import com.airbnb.p027n2.utils.LatLng;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.MapInterstitial */
public class MapInterstitial extends FrameLayout implements DividerView {
    private boolean hideAddress;
    private Listener listener;
    private MapOptions mapOptions;
    @BindView
    StaticMapView mapView;
    @BindView
    TextView subtitle;
    @BindView
    View textContainer;
    @BindView
    TextView title;

    public MapInterstitial(Context context) {
        super(context);
        init(null);
    }

    public MapInterstitial(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MapInterstitial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_map_interstitial, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.n2_loading_background));
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_MapInterstitial);
        hideText(a.getBoolean(R.styleable.n2_MapInterstitial_n2_hideAddress, false));
        a.recycle();
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public void setMapOptions(MapOptions mapOptions2) {
        this.mapOptions = mapOptions2;
        setupStaticMapView();
    }

    private void setupStaticMapView() {
        if (!this.hideAddress) {
            this.mapOptions = MapOptions.builder(this.mapOptions).center(LatLng.builder().lat(this.mapOptions.center().lat() + 0.005d).lng(this.mapOptions.center().lng()).build()).build();
        }
        if (this.mapOptions.marker() != null) {
            this.textContainer.setOnLongClickListener(MapInterstitial$$Lambda$1.lambdaFactory$(this));
        }
        this.mapView.setup(this.mapOptions, this.listener);
    }

    public void hideText(boolean hideText) {
        this.hideAddress = hideText;
        ViewLibUtils.setVisibleIf(this.textContainer, !hideText);
    }

    public void setTitle(CharSequence titleText) {
        ViewLibUtils.bindOptionalTextView(this.title, titleText);
    }

    public void setSubtitle(CharSequence subtitleText) {
        ViewLibUtils.bindOptionalTextView(this.subtitle, subtitleText);
    }

    public void setNeighborhoodText(CharSequence subtitleText) {
        boolean z;
        TextView textView = this.subtitle;
        if (!TextUtils.isEmpty(subtitleText)) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(textView, z);
        if (!TextUtils.isEmpty(subtitleText)) {
            this.subtitle.setText(getContext().getString(R.string.n2_neighborhood_x, new Object[]{subtitleText}));
        }
    }

    public void clear() {
        this.mapView.clear();
    }

    public void showDivider(boolean showDivider) {
    }

    public static void mock(MapInterstitial interstitial) {
    }
}
