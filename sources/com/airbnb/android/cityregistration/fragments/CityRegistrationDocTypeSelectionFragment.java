package com.airbnb.android.cityregistration.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.adapters.CityRegistrationDocTypeSelectionAdapter;
import com.airbnb.android.cityregistration.adapters.CityRegistrationDocTypeSelectionAdapter.Listener;
import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.io.File;

public class CityRegistrationDocTypeSelectionFragment extends CityRegistrationBaseFragment {
    private static final String ARG_QUESTION = "currentQuestionBeingUpdated";
    private CityRegistrationDocTypeSelectionAdapter adapter;
    private Listener listener = CityRegistrationDocTypeSelectionFragment$$Lambda$1.lambdaFactory$(this);
    @State
    ListingRegistrationQuestion question;
    @BindView
    RecyclerView recyclerView;
    @State
    String selectedDocType;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CityRegistrationDocTypeSelectionFragment cityRegistrationDocTypeSelectionFragment, String selectedDocType2) {
        cityRegistrationDocTypeSelectionFragment.selectedDocType = selectedDocType2;
        cityRegistrationDocTypeSelectionFragment.controller.getDocPhoto(selectedDocType2);
    }

    public static CityRegistrationDocTypeSelectionFragment create(ListingRegistrationQuestion question2) {
        return (CityRegistrationDocTypeSelectionFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CityRegistrationDocTypeSelectionFragment()).putParcelable(ARG_QUESTION, question2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.question = (ListingRegistrationQuestion) getArguments().getParcelable(ARG_QUESTION);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5630R.layout.fragment_listing_recycler_view_only, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.adapter = new CityRegistrationDocTypeSelectionAdapter(getContext(), this.listener, this.question, savedInstanceState);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.adapter != null) {
            this.adapter.onSaveInstanceState(outState);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case CityRegistrationController.RC_CHOOSE_PHOTO /*202*/:
                    this.controller.handleImageCapture(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH))), this.question, this.selectedDocType);
                    return;
                default:
                    return;
            }
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationInputGroupDocTypeSelection;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("input_key", this.question.getInputKey());
    }
}
