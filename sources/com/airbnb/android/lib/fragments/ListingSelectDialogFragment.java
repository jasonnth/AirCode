package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.ListingsRowAdapter;
import java.util.ArrayList;
import java.util.List;

public class ListingSelectDialogFragment extends ZenDialog {
    public static final String LISTING = "listing";
    public static final String LISTINGS = "listings";
    private ListingsRowAdapter adapter;
    private ListingsLoadedCallback callback;
    private List<Listing> listings;

    public interface ListingsLoadedCallback {
        void onListingsLoaded(List<Listing> list);
    }

    public static ListingSelectDialogFragment newInstanceForCurrentUser(List<Listing> listings2, int requestCode, Fragment targetFragment, ListingsLoadedCallback callback2) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listings", new ArrayList(listings2));
        ListingSelectDialogFragment dialog = (ListingSelectDialogFragment) new ZenBuilder(new ListingSelectDialogFragment()).withTitle(C0880R.string.select_listing).withListView(bundle).create();
        dialog.setCallback(callback2);
        dialog.setTargetFragment(targetFragment, requestCode);
        return dialog;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listings = getArguments().getParcelableArrayList("listings");
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        if (this.adapter == null) {
            this.adapter = ListingsRowAdapter.forCurrentUser(this.mAccountManager.getCurrentUser(), this.listings, this.callback);
        }
        return this.adapter;
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return ListingSelectDialogFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$getItemClickListener$0(ListingSelectDialogFragment listingSelectDialogFragment, AdapterView parent, View view, int position, long id) {
        listingSelectDialogFragment.dismiss();
        Intent intent = new Intent();
        intent.putExtra("listing", (Listing) listingSelectDialogFragment.listings.get(position));
        listingSelectDialogFragment.sendActivityResult(listingSelectDialogFragment.getTargetRequestCode(), -1, intent);
    }

    public void setCallback(ListingsLoadedCallback callback2) {
        this.callback = callback2;
    }
}
