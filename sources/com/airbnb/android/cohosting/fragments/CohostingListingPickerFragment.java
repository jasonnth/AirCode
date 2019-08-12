package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.adapters.CohostingListingPickerAdapter;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.ArrayList;

public class CohostingListingPickerFragment extends AirFragment {
    private CohostingListingPickerAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostingListingPickerFragment create(ArrayList<Listing> listings, String firstName, long threadOtherUserId) {
        return (CohostingListingPickerFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingListingPickerFragment()).putParcelableArrayList("listings", listings)).putString(CohostingConstants.FIRST_NAME_FIELD, firstName)).putLong(CohostingConstants.THREAD_OTHER_USER_ID_FIELD, threadOtherUserId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_listing_picker, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new CohostingListingPickerAdapter(getContext(), getArguments().getString(CohostingConstants.FIRST_NAME_FIELD), getArguments().getParcelableArrayList("listings"), getArguments().getLong(CohostingConstants.THREAD_OTHER_USER_ID_FIELD));
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }
}
