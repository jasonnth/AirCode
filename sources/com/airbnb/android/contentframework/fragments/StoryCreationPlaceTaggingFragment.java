package com.airbnb.android.contentframework.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.adapters.StoryCreationPlaceSearchEpoxyController;
import com.airbnb.android.contentframework.adapters.StoryCreationPlaceSearchEpoxyController.Delegate;
import com.airbnb.android.contentframework.requests.StoryCreationSearchPlaceRequest;
import com.airbnb.android.contentframework.responses.StoryCreationSearchPlaceResponse;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.StoryCreationPlaceTag;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class StoryCreationPlaceTaggingFragment extends AirFragment implements Delegate {
    private static final String ARG_LATLNG = "ARG_LATLNG";
    private static final String ARG_SUGGESTED_PLACE_TAGS = "ARG_SUGGESTED_PLACE_TAGS";
    private static final int DELAY_AUTOCOMPLETE_MS = 400;
    public static final String EXTRA_RESULT_PLACE_TAG = "extra_result_place_tag";
    /* access modifiers changed from: private */
    public final Runnable autoCompleteRunnable = StoryCreationPlaceTaggingFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    InputMarquee inputMarquee;
    private LatLng location;
    private StoryCreationPlaceSearchEpoxyController placeSearchEpoxyController;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<StoryCreationSearchPlaceResponse> searchPlaceListener = new C0699RL().onResponse(StoryCreationPlaceTaggingFragment$$Lambda$2.lambdaFactory$(this)).onError(StoryCreationPlaceTaggingFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;

    public static Intent newIntent(Context context, ArrayList<StoryCreationPlaceTag> suggestedPlaceTags, LatLng latLng) {
        return ((Builder) ((Builder) AutoFragmentActivity.create(context, StoryCreationPlaceTaggingFragment.class).putParcelableArrayList(ARG_SUGGESTED_PLACE_TAGS, suggestedPlaceTags)).putParcelable(ARG_LATLNG, latLng)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.placeSearchEpoxyController = new StoryCreationPlaceSearchEpoxyController(this);
        this.location = (LatLng) getArguments().getParcelable(ARG_LATLNG);
        List<StoryCreationPlaceTag> suggestedPlaceTags = getArguments().getParcelableArrayList(ARG_SUGGESTED_PLACE_TAGS);
        if (suggestedPlaceTags != null && !suggestedPlaceTags.isEmpty()) {
            this.placeSearchEpoxyController.setLoadingResults(getArguments().getParcelableArrayList(ARG_SUGGESTED_PLACE_TAGS));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5709R.layout.fragment_story_creation_place_tagging, container, false);
        bindViews(view);
        setupInputMarquee();
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.placeSearchEpoxyController.getAdapter());
        return view;
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard((Activity) getActivity());
    }

    public void onDestroyView() {
        this.recyclerView.removeCallbacks(this.autoCompleteRunnable);
        super.onDestroyView();
    }

    public void onPlaceClicked(StoryCreationPlaceTag placeTag) {
        Intent resultData = new Intent();
        resultData.putExtra(EXTRA_RESULT_PLACE_TAG, placeTag);
        getActivity().setResult(-1, resultData);
        getActivity().finish();
    }

    private String getSearchText() {
        return this.inputMarquee.getText().trim();
    }

    /* access modifiers changed from: private */
    public void onSearch(String query) {
        StoryCreationSearchPlaceRequest.forQuery(query, this.location).withListener((Observer) this.searchPlaceListener).execute(this.requestManager);
        this.placeSearchEpoxyController.startLoading();
    }

    private void setupInputMarquee() {
        this.inputMarquee.addTextWatcher(new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                StoryCreationPlaceTaggingFragment.this.recyclerView.removeCallbacks(StoryCreationPlaceTaggingFragment.this.autoCompleteRunnable);
                StoryCreationPlaceTaggingFragment.this.recyclerView.postDelayed(StoryCreationPlaceTaggingFragment.this.autoCompleteRunnable, 400);
            }
        });
        this.inputMarquee.setOnEditorActionListener(StoryCreationPlaceTaggingFragment$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ boolean lambda$setupInputMarquee$3(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment, TextView v, int actionId, KeyEvent event) {
        String searchQuery = storyCreationPlaceTaggingFragment.getSearchText();
        if (!KeyboardUtils.isEnterOrSearch(actionId, event) || TextUtils.isEmpty(searchQuery)) {
            return false;
        }
        KeyboardUtils.dismissSoftKeyboard((View) v);
        storyCreationPlaceTaggingFragment.recyclerView.removeCallbacks(storyCreationPlaceTaggingFragment.autoCompleteRunnable);
        storyCreationPlaceTaggingFragment.onSearch(searchQuery);
        return true;
    }
}
