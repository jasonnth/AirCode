package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.DebugTrebuchetAdapter;
import com.squareup.otto.Bus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TrebuchetOverrideActivity extends AirActivity {
    Bus bus;
    @BindView
    RecyclerView list;
    private DebugTrebuchetAdapter trebuchetAdapter;

    public static Intent intentForDefault(Context context) {
        return new Intent(context, TrebuchetOverrideActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        setContentView(C0880R.layout.activity_trebuchet_override);
        ButterKnife.bind((Activity) this);
        setupActionBar(C0880R.string.debug_menu_override_trebuchet, new Object[0]);
        DebugSettings debugSettings = AirbnbApplication.instance(this).component().debugSettings();
        List<String> keys = new ArrayList<>();
        for (String key : debugSettings.getAllTrebuchetFlags().keySet()) {
            keys.add(key);
        }
        Collections.sort(keys);
        this.trebuchetAdapter = new DebugTrebuchetAdapter(keys, debugSettings, this.bus);
        this.list.setLayoutManager(new LinearLayoutManager(this));
        this.list.setAdapter(this.trebuchetAdapter);
    }
}
