package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.CohostingStandard;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;

public class CohostingStandardFragment extends AirFragment {
    private static final String COHOSTING_STAT_FIELD = "cohosting_stat";
    private final Listener listener = CohostingStandardFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    RecyclerView recyclerView;
    @State
    CohostingStandard standard;
    @BindView
    AirToolbar toolbar;

    public static Bundle getBundle(CohostingStandard standards) {
        return ((BundleBuilder) new BundleBuilder().putParcelable(COHOSTING_STAT_FIELD, standards)).toBundle();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_cohosting_stat, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.standard = (CohostingStandard) getArguments().getParcelable(COHOSTING_STAT_FIELD);
        CohostingStandardEpoxyController epoxyController = new CohostingStandardEpoxyController(getContext(), this.standard, this.listener);
        epoxyController.requestModelBuild();
        this.recyclerView.setAdapter(epoxyController.getAdapter());
        return view;
    }

    /* access modifiers changed from: 0000 */
    public void goToCohostingStandardPage(Context context) {
        context.startActivity(WebViewIntentBuilder.newBuilder(context, context.getString(C7368R.string.cohosting_standard_url)).title(C7368R.string.cohosting_stats_detail_page_title).authenticate().toIntent());
    }
}
