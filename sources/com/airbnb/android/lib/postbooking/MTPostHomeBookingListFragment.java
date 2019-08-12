package com.airbnb.android.lib.postbooking;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.airbnb.android.core.responses.PostHomeBookingResponse.PHB;
import com.airbnb.android.core.utils.LayoutManagerUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.explore.adapters.MTPostHomeBookingAdapter;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class MTPostHomeBookingListFragment extends MTBasePostHomeBookingFragment {
    private MTPostHomeBookingAdapter adapter;
    @BindView
    RecyclerView recyclerView;

    public static MTPostHomeBookingListFragment newInstance(String reservationId) {
        return (MTPostHomeBookingListFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTPostHomeBookingListFragment()).putString("arg_reservation_id", reservationId)).build();
    }

    /* access modifiers changed from: protected */
    public int getLayoutRes() {
        return C0880R.layout.fragment_mt_phb;
    }

    /* access modifiers changed from: protected */
    public void onResponseLoaded(PHB phb) {
        this.adapter.refreshData(phb.sections, phb.title, phb.subtitle);
    }

    /* access modifiers changed from: protected */
    public void initViews(View view) {
        this.adapter = new MTPostHomeBookingAdapter(getActivity());
        LayoutManagerUtils.setGridLayout((AirEpoxyAdapter) this.adapter, this.recyclerView, isTabletScreen() ? 12 : 2);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.swapAdapter(this.adapter, true);
    }
}
