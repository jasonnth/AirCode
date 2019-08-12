package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.models.SpokenLanguage;
import com.airbnb.android.core.requests.GetSpokenLanguagesRequest;
import com.airbnb.android.core.responses.GetSpokenLanguagesResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.SpokenLanguagesAdapter;
import com.airbnb.android.lib.views.LoaderListView;
import java.util.ArrayList;

public class SpokenLanguagesDialogFragment extends ZenDialog {
    public static final String EXTRA_SPOKEN_LANGUAGES = "spoken_languages";
    private SpokenLanguagesAdapter mAdapter;
    AirbnbApi mAirbnbApi;
    private ArrayList<SpokenLanguage> mLanguages = new ArrayList<>();
    @BindView
    LoaderListView mLoaderListView;

    public static SpokenLanguagesDialogFragment newInstance(int requestCodeCancel, int requestCodeApply, Fragment targetFragment) {
        return (SpokenLanguagesDialogFragment) new ZenBuilder(new SpokenLanguagesDialogFragment()).withTitle(C0880R.string.edit_profile_languages).withCustomLayout().withDualButton(C0880R.string.cancel, requestCodeCancel, C0880R.string.apply, requestCodeApply, targetFragment).create();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View frame = inflater.inflate(C0880R.layout.fragment_spoken_languages, container, false);
        ButterKnife.bind(this, frame);
        setCustomView(frame);
        setupListViewAdapter();
        if (savedInstanceState == null) {
            fetchSpokenLanguages();
        } else {
            this.mLoaderListView.finishLoaderImmediate();
            populateLanguages(savedInstanceState.getParcelableArrayList(EXTRA_SPOKEN_LANGUAGES));
        }
        return view;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList(EXTRA_SPOKEN_LANGUAGES, this.mLanguages);
    }

    private void setupListViewAdapter() {
        ListView listView = this.mLoaderListView.getListView();
        listView.setDivider(new ColorDrawable(getResources().getColor(C0880R.color.c_gray_4)));
        listView.setDividerHeight((int) getResources().getDimension(C0880R.dimen.line_thickness));
        listView.setFooterDividersEnabled(false);
        View footer = new View(listView.getContext());
        footer.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelSize(C0880R.dimen.sticky_btn_height)));
        listView.addFooterView(footer);
        this.mAdapter = new SpokenLanguagesAdapter(getActivity(), this.mLanguages);
        this.mLoaderListView.getListView().setAdapter(this.mAdapter);
    }

    private void fetchSpokenLanguages() {
        this.mLoaderListView.startLoader();
        new GetSpokenLanguagesRequest(new NonResubscribableRequestListener<GetSpokenLanguagesResponse>() {
            public void onResponse(GetSpokenLanguagesResponse response) {
                if (SpokenLanguagesDialogFragment.this.isResumed()) {
                    SpokenLanguagesDialogFragment.this.mLoaderListView.finishLoader();
                    SpokenLanguagesDialogFragment.this.populateLanguages(response.languages);
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                SpokenLanguagesDialogFragment.this.mLoaderListView.finishLoader();
                Toast.makeText(SpokenLanguagesDialogFragment.this.getActivity(), SpokenLanguagesDialogFragment.this.getString(C0880R.string.edit_profile_failed, SpokenLanguagesDialogFragment.this.getString(ProfileSection.Languages.getTitleId())), 0).show();
                SpokenLanguagesDialogFragment.this.dismiss();
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void populateLanguages(ArrayList<SpokenLanguage> languages) {
        this.mLanguages = languages;
        this.mAdapter.clear();
        this.mAdapter.addAll(this.mLanguages);
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        Intent data = new Intent();
        data.putExtra(EXTRA_SPOKEN_LANGUAGES, this.mLanguages);
        sendActivityResult(requestCodeRight, -1, data);
    }
}
